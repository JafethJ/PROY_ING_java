function mostrarPasoPago() {
    document.getElementById('pasoDireccion').style.display = 'none';
    document.getElementById('pasoPago').style.display = 'block';
}
function mostrarPasoDireccion() {
    document.getElementById('pasoDireccion').style.display = 'block';
    document.getElementById('pasoPago').style.display = 'none';
}
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('metodoPago').addEventListener('change', function() {
        document.getElementById('datosTarjeta').style.display = this.value === 'tarjeta' ? 'block' : 'none';
    });
});
function confirmarPago() {
    // Aquí envías los datos al backend usando fetch/AJAX
    alert('¡Pedido confirmado!');
    // Cierra el modal si quieres
    var modal = bootstrap.Modal.getInstance(document.getElementById('pagoModal'));
    modal.hide();
}
function abrirModalPago(e) {
    e.preventDefault();
    var modal = new bootstrap.Modal(document.getElementById('pagoModal'));
    modal.show();
}