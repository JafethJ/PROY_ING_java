package com.example.demo.controller;

import com.example.demo.model.CarritoItem;
import com.example.demo.model.Extra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "*") // Permite CORS desde cualquier origen
public class CarritoController {
    private static final double TARIFA_DELIVERY = 2.00; 

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarAlCarrito(@RequestBody CarritoItem item, HttpSession session) {
        try {
            List<CarritoItem> carrito = obtenerCarrito(session);
            
            // Verificar si el producto ya existe en el carrito
            Optional<CarritoItem> existente = carrito.stream()
                .filter(i -> i.getIdProducto().equals(item.getIdProducto()))
                .findFirst();
            
            if (existente.isPresent()) {
                // Actualizar cantidad si ya existe
                CarritoItem actual = existente.get();
                actual.setCantidad(actual.getCantidad() + item.getCantidad());
            } else {
                // Agregar nuevo item
                carrito.add(item);
            }
            
            session.setAttribute("carrito", carrito);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al agregar al carrito: " + e.getMessage());
        }
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<CarritoItem>> obtenerCarritoCompleto(HttpSession session) {
        return ResponseEntity.ok(obtenerCarrito(session));
    }

    @GetMapping("/total")
    public ResponseEntity<Double> obtenerTotalCarrito(HttpSession session) {
        List<CarritoItem> carrito = obtenerCarrito(session);
        double total = carrito.stream()
            .mapToDouble(item -> item.calcularPrecioTotal())
            .sum();
        return ResponseEntity.ok(total);
    }

    @PutMapping("/actualizar/{idProducto}")
public ResponseEntity<?> actualizarItem(
        @PathVariable Integer idProducto,
        @RequestBody Map<String, Integer> request,
        HttpSession session) {
    
    List<CarritoItem> carrito = obtenerCarrito(session);
    Integer cambio = request.get("cambio");
    
    for (int i = 0; i < carrito.size(); i++) {
        CarritoItem item = carrito.get(i);
        if (item.getIdProducto().equals(idProducto)) {
            // Actualizar cantidad según el cambio (+1 o -1)
            int nuevaCantidad = item.getCantidad() + cambio;
            
            // Validar que la cantidad no sea menor a 1
            if (nuevaCantidad < 1) {
                return ResponseEntity.badRequest().body("La cantidad no puede ser menor a 1");
            }
            
            item.setCantidad(nuevaCantidad);
            session.setAttribute("carrito", carrito);
            return ResponseEntity.ok(carrito);
        }
    }
    
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Producto no encontrado en el carrito");
}

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<?> eliminarItem(
            @PathVariable Integer idProducto,
            HttpSession session) {
        
        List<CarritoItem> carrito = obtenerCarrito(session);
        boolean removed = carrito.removeIf(item -> item.getIdProducto().equals(idProducto));
        
        if (removed) {
            session.setAttribute("carrito", carrito);
            return ResponseEntity.ok(carrito);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Producto no encontrado en el carrito");
        }
    }

    @DeleteMapping("/vaciar")
    public ResponseEntity<?> vaciarCarrito(HttpSession session) {
        session.removeAttribute("carrito");
        return ResponseEntity.ok("Carrito vaciado correctamente");
    }

    // Nuevos endpoints para el frontend
    @GetMapping("/cantidad")
    public ResponseEntity<Map<String, Integer>> obtenerCantidadItems(HttpSession session) {
        List<CarritoItem> carrito = obtenerCarrito(session);
        int cantidad = carrito.size();
        
        Map<String, Integer> response = new HashMap<>();
        response.put("cantidad", cantidad);
        return ResponseEntity.ok(response);
    }

  @GetMapping("/resumen")
public ResponseEntity<?> obtenerResumenCarrito(HttpSession session) {
    try {
        List<CarritoItem> carrito = obtenerCarrito(session);
        if (carrito == null || carrito.isEmpty()) {
            return ResponseEntity.ok().body(Map.of("mensaje", "El carrito está vacío"));
        }

        List<Map<String, Object>> itemsResponse = new ArrayList<>();
        double subtotal = 0;

        for (CarritoItem item : carrito) {
            if (item == null) continue;
            
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("idProducto", item.getIdProducto());
            itemMap.put("nombreProducto", item.getNombreProducto());
            itemMap.put("precioUnitario", item.getPrecioUnitario());
            itemMap.put("precioAgrandado", item.getPrecioAgrandado());
            itemMap.put("agrandado", item.isAgrandado());
            itemMap.put("cantidad", item.getCantidad());
            itemMap.put("imagen", item.getImagen());
            
            // Procesar extras directamente en el ítem
            List<Map<String, Object>> extrasItem = new ArrayList<>();
            if (item.getExtras() != null) {
                for (Extra extra : item.getExtras()) {
                    if (extra != null) {
                        extrasItem.add(Map.of(
                            "idExtra", extra.getIdDecorador(),
                            "nombre", extra.getNombre(),
                            "precio", extra.getPrecio()
                        ));
                    }
                }
            }
            itemMap.put("extras", extrasItem);
            
            double precioItem = item.calcularPrecioTotal();
            itemMap.put("precioTotal", precioItem);
            subtotal += precioItem;
            
            itemsResponse.add(itemMap);
        }

        double total = subtotal + TARIFA_DELIVERY;

        Map<String, Object> response = Map.of(
            "items", itemsResponse,
            "subtotal", subtotal,
            "delivery", TARIFA_DELIVERY,
            "total", total,
            "cantidadItems", carrito.size()
        );
        
        session.setAttribute("resumenFactura", response);
        return ResponseEntity.ok(response);
        
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error", "Error al generar resumen: " + e.getMessage()));
    }
}

@GetMapping("/debug-resumen")
public ResponseEntity<Map<String, Object>> debugResumen(HttpSession session) {
    // Obtiene los mismos datos que usaría el resumen normal
    Map<String, Object> resumenFactura = (Map<String, Object>) session.getAttribute("resumenFactura");
    
    if (resumenFactura == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Collections.singletonMap("error", "No hay datos de resumen en la sesión"));
    }
    
    // Datos adicionales para depuración
    Map<String, Object> debugInfo = new HashMap<>();
    debugInfo.put("resumenCompleto", resumenFactura);
    debugInfo.put("sessionId", session.getId());
    debugInfo.put("horaCreacion", new Date(session.getCreationTime()));
    debugInfo.put("ultimoAcceso", new Date(session.getLastAccessedTime()));
    
    return ResponseEntity.ok(debugInfo);
}

private List<CarritoItem> obtenerCarrito(HttpSession session) {
    try {
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");
        return carrito != null ? carrito : new ArrayList<>();
    } catch (Exception e) {
        return new ArrayList<>();
    }
}

    @PostMapping("/confirmar-pedido")
    public ResponseEntity<?> confirmarPedido(@RequestBody Map<String, String> datosCliente, HttpSession session) {
        Map<String, Object> resumenFactura = (Map<String, Object>) session.getAttribute("resumenFactura");
        
        if (resumenFactura == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No hay un carrito activo para confirmar");
        }
        
        // Agregar información del cliente al resumen
        resumenFactura.put("nombreCliente", datosCliente.get("nombre"));
        resumenFactura.put("direccion", datosCliente.get("direccion"));
        resumenFactura.put("idUsuario", datosCliente.get("idUsuario"));
        
        // Lógica para guardar en BD (ajustada sin productos adicionales)
        /*
        1. Guardar en tabla pedidos:
        - nombre: datosCliente.get("nombre")
        - productos: concatenar solo nombres de productos principales
        - extras: concatenar nombres de extras
        
        2. Guardar en tabla factura:
        - idUsuario: datosCliente.get("idUsuario")
        - direccion: datosCliente.get("direccion")
        - total: resumenFactura.get("total")
        
        3. Guardar en tabla factura_items:
        - Solo productos principales
        
        4. Guardar en tabla factura_extras:
        - Solo extras (sin productos adicionales)
        */
        
        return ResponseEntity.ok(resumenFactura);
    }

    
}