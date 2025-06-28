// Sample dish data
const dishesData = {
    'hot-dishes': [
        {
            id: 1,
            name: 'One Pot Chicken Biryani',
            price: 4.97,
            image: 'https://images.unsplash.com/photo-1563379091339-03246963d51a?w=300&h=300&fit=crop&crop=center',
            available: 30
        },
        {
            id: 2,
            name: 'Fresh Corn Grill Tacos',
            price: 3.74,
            image: 'https://images.unsplash.com/photo-1565299624946-b28f40a0ca4b?w=300&h=300&fit=crop&crop=center',
            available: 25
        },
        {
            id: 3,
            name: 'Crispy Chicken Parmesan',
            price: 4.37,
            image: 'https://images.unsplash.com/photo-1604503468506-a8da13d82791?w=300&h=300&fit=crop&crop=center',
            available: 20
        },
        {
            id: 4,
            name: 'Prawns Wrapped In Noodles',
            price: 2.82,
            image: 'https://images.unsplash.com/photo-1555939594-58d7cb561ad1?w=300&h=300&fit=crop&crop=center',
            available: 35
        },
        {
            id: 5,
            name: 'Spicy Ginger Szechuan Beef',
            price: 5.63,
            image: 'https://images.unsplash.com/photo-1603133872878-684f208fb84b?w=300&h=300&fit=crop&crop=center',
            available: 15
        },
        {
            id: 6,
            name: 'Sushi Spicy Tuna Roll',
            price: 3.34,
            image: 'https://images.unsplash.com/photo-1579584425555-c3ce17fd4351?w=300&h=300&fit=crop&crop=center',
            available: 20
        }
    ],
    'soup': [
        {
            id: 7,
            name: 'Tomato Basil Soup',
            price: 2.99,
            image: 'https://images.unsplash.com/photo-1547592166-23ac45744acd?w=300&h=300&fit=crop&crop=center',
            available: 25
        },
        {
            id: 8,
            name: 'Chicken Noodle Soup',
            price: 3.49,
            image: 'https://images.unsplash.com/photo-1569718212165-3a8278d5f624?w=300&h=300&fit=crop&crop=center',
            available: 30
        },
        {
            id: 9,
            name: 'Mushroom Cream Soup',
            price: 3.25,
            image: 'https://images.unsplash.com/photo-1547592180-85f173990554?w=300&h=300&fit=crop&crop=center',
            available: 22
        }
    ],
    'starter': [
        {
            id: 10,
            name: 'Caesar Salad',
            price: 4.25,
            image: 'https://images.unsplash.com/photo-1546793665-c74683f339c1?w=300&h=300&fit=crop&crop=center',
            available: 20
        },
        {
            id: 11,
            name: 'Garlic Bread',
            price: 2.50,
            image: 'https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?w=300&h=300&fit=crop&crop=center',
            available: 40
        },
        {
            id: 12,
            name: 'Buffalo Wings',
            price: 5.99,
            image: 'https://images.unsplash.com/photo-1608039755401-742074f0548d?w=300&h=300&fit=crop&crop=center',
            available: 18
        }
    ],
    'cold-dishes': [
        {
            id: 13,
            name: 'Greek Salad',
            price: 4.50,
            image: 'https://images.unsplash.com/photo-1540420773420-3366772f4999?w=300&h=300&fit=crop&crop=center',
            available: 25
        },
        {
            id: 14,
            name: 'Caprese Salad',
            price: 3.75,
            image: 'https://images.unsplash.com/photo-1592417817098-8fd3d9eb14a5?w=300&h=300&fit=crop&crop=center',
            available: 30
        }
    ],
    'beverages': [
        {
            id: 15,
            name: 'Fresh Orange Juice',
            price: 2.25,
            image: 'https://images.unsplash.com/photo-1613478223719-2ab802602423?w=300&h=300&fit=crop&crop=center',
            available: 50
        },
        {
            id: 16,
            name: 'Iced Coffee',
            price: 2.75,
            image: 'https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=300&h=300&fit=crop&crop=center',
            available: 35
        }
    ],
    'dessert': [
        {
            id: 17,
            name: 'Chocolate Cake',
            price: 4.99,
            image: 'https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=300&h=300&fit=crop&crop=center',
            available: 15
        },
        {
            id: 18,
            name: 'Ice Cream Sundae',
            price: 3.50,
            image: 'https://images.unsplash.com/photo-1563805042-7684c019e1cb?w=300&h=300&fit=crop&crop=center',
            available: 20
        }
    ]
};

// Order management
let currentOrder = [
    {
        id: 1,
        name: 'One Pot Chicken Biryani',
        price: 4.97,
        quantity: 1,
        image: 'https://images.unsplash.com/photo-1563379091339-03246963d51a?w=300&h=300&fit=crop&crop=center',
        note: 'Please, just a little bit spicy only.'
    },
    {
        id: 6,
        name: 'Sushi Spicy Tuna Roll',
        price: 3.34,
        quantity: 3,
        image: 'https://images.unsplash.com/photo-1579584425555-c3ce17fd4351?w=300&h=300&fit=crop&crop=center',
        note: ''
    },
    {
        id: 3,
        name: 'Crispy Chicken Parmesan',
        price: 4.37,
        quantity: 2,
        image: 'https://images.unsplash.com/photo-1604503468506-a8da13d82791?w=300&h=300&fit=crop&crop=center',
        note: ''
    },
    {
        id: 5,
        name: 'Spicy Ginger Szechuan Beef',
        price: 5.63,
        quantity: 1,
        image: 'https://images.unsplash.com/photo-1603133872878-684f208fb84b?w=300&h=300&fit=crop&crop=center',
        note: ''
    }
];

let currentCategory = 'hot-dishes';
let currentOrderType = 'dine-in';

// DOM Elements
const dishesGrid = document.getElementById('dishes-grid');
const orderItems = document.getElementById('order-items');
const subtotalElement = document.getElementById('subtotal');
const discountElement = document.getElementById('discount');
const dateElement = document.querySelector('.date');

// Initialize the app
document.addEventListener('DOMContentLoaded', function() {
    renderDishes();
    renderOrderItems();
    updateOrderSummary();
    setupEventListeners();
    
    // Update date to current date
    if (dateElement) {
        const now = new Date();
        const options = { 
            weekday: 'long', 
            year: 'numeric', 
            month: 'long', 
            day: 'numeric' 
        };
        dateElement.textContent = now.toLocaleDateString('en-US', options);
    }
});

// Setup event listeners
function setupEventListeners() {
    // Category navigation
    document.querySelectorAll('.category-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            document.querySelector('.category-btn.active').classList.remove('active');
            this.classList.add('active');
            currentCategory = this.dataset.category;
            renderDishes();
        });
    });

    // Order type toggle
    document.querySelectorAll('.toggle-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            document.querySelector('.toggle-btn.active').classList.remove('active');
            this.classList.add('active');
            currentOrderType = this.dataset.type;
        });
    });

    // Search functionality
    document.querySelector('.search-input').addEventListener('input', function(e) {
        const searchTerm = e.target.value.toLowerCase();
        filterDishes(searchTerm);
    });

    // Navigation items
    document.querySelectorAll('.nav-item').forEach(item => {
        item.addEventListener('click', function(e) {
            e.preventDefault();
            document.querySelector('.nav-item.active').classList.remove('active');
            this.classList.add('active');
        });
    });

    // Continue to payment
    const continueBtn = document.querySelector('.continue-btn');
    if (continueBtn) {
        continueBtn.addEventListener('click', function() {
            if (currentOrder.length === 0) {
                alert('Please add items to your order first!');
                return;
            }
            
            // Simulate payment process
            this.textContent = 'Processing...';
            this.disabled = true;
            
            setTimeout(() => {
                alert('Order placed successfully! Thank you for your order.');
                this.textContent = 'Continue to Payment';
                this.disabled = false;
                
                // Reset order for demo (optional)
                // currentOrder = [];
                // renderOrderItems();
                // updateOrderSummary();
            }, 2000);
        });
    }
}

// Render dishes based on current category
function renderDishes() {
    const dishes = dishesData[currentCategory] || [];
    
    dishesGrid.innerHTML = dishes.map(dish => `
        <div class="dish-card" onclick="addToOrder(${dish.id})">
            <div class="dish-image" style="background-image: url('${dish.image}')"></div>
            <h3 class="dish-name">${dish.name}</h3>
            <p class="dish-price">$${dish.price.toFixed(2)}</p>
            <p class="dish-availability">${dish.available} Bowls available</p>
        </div>
    `).join('');
}

// Filter dishes based on search term
function filterDishes(searchTerm) {
    const dishes = dishesData[currentCategory] || [];
    const filteredDishes = dishes.filter(dish => 
        dish.name.toLowerCase().includes(searchTerm)
    );
    
    dishesGrid.innerHTML = filteredDishes.map(dish => `
        <div class="dish-card" onclick="addToOrder(${dish.id})">
            <div class="dish-image" style="background-image: url('${dish.image}')"></div>
            <h3 class="dish-name">${dish.name}</h3>
            <p class="dish-price">$${dish.price.toFixed(2)}</p>
            <p class="dish-availability">${dish.available} Bowls available</p>
        </div>
    `).join('');
}

// Add item to order
function addToOrder(dishId) {
    // Find dish in all categories
    let dish = null;
    for (const category in dishesData) {
        dish = dishesData[category].find(d => d.id === dishId);
        if (dish) break;
    }
    
    if (!dish) return;
    
    // Check if item already exists in order
    const existingItem = currentOrder.find(item => item.id === dishId);
    
    if (existingItem) {
        existingItem.quantity += 1;
    } else {
        currentOrder.push({
            id: dish.id,
            name: dish.name,
            price: dish.price,
            quantity: 1,
            image: dish.image,
            note: ''
        });
    }
    
    renderOrderItems();
    updateOrderSummary();
    
    // Add visual feedback
    const dishCards = document.querySelectorAll('.dish-card');
    dishCards.forEach(card => {
        if (card.onclick && card.onclick.toString().includes(dishId)) {
            card.style.transform = 'scale(0.95)';
            setTimeout(() => {
                card.style.transform = '';
            }, 150);
        }
    });
}

// Render order items
function renderOrderItems() {
    orderItems.innerHTML = currentOrder.map((item, index) => `
        <div class="order-item-container">
            <div class="order-item">
                <div class="item-info">
                    <div class="item-image" style="background-image: url('${item.image}')"></div>
                    <div class="item-details">
                        <h4>${item.name}</h4>
                        <p>$${item.price.toFixed(2)}</p>
                    </div>
                </div>
                <div class="quantity-controls">
                    <button class="qty-btn" onclick="updateQuantity(${item.id}, -1)">
                        <i class="fas fa-minus"></i>
                    </button>
                    <span>${item.quantity}</span>
                    <button class="qty-btn" onclick="updateQuantity(${item.id}, 1)">
                        <i class="fas fa-plus"></i>
                    </button>
                </div>
                <div class="item-price">$${(item.price * item.quantity).toFixed(2)}</div>
            </div>
            ${item.note !== undefined ? `
                <textarea class="order-note" placeholder="Order Note..." 
                    onchange="updateNote(${item.id}, this.value)">${item.note}</textarea>
            ` : ''}
            <button class="delete-btn" onclick="removeFromOrder(${item.id})">
                <i class="fas fa-trash"></i>
            </button>
        </div>
    `).join('');
}

// Update item quantity
function updateQuantity(itemId, change) {
    const item = currentOrder.find(item => item.id === itemId);
    if (item) {
        item.quantity += change;
        if (item.quantity <= 0) {
            removeFromOrder(itemId);
        } else {
            renderOrderItems();
            updateOrderSummary();
        }
    }
}

// Update item note
function updateNote(itemId, note) {
    const item = currentOrder.find(item => item.id === itemId);
    if (item) {
        item.note = note;
    }
}

// Remove item from order
function removeFromOrder(itemId) {
    currentOrder = currentOrder.filter(item => item.id !== itemId);
    renderOrderItems();
    updateOrderSummary();
}

// Update order summary
function updateOrderSummary() {
    const subtotal = currentOrder.reduce((total, item) => total + (item.price * item.quantity), 0);
    const discount = 2.74; // Fixed discount for demo
    
    subtotalElement.textContent = `$${subtotal.toFixed(2)}`;
    discountElement.textContent = `$${discount.toFixed(2)}`;
}

// Add ripple effect
function addRippleEffect(element) {
    element.addEventListener('click', function(e) {
        const ripple = document.createElement('span');
        const rect = this.getBoundingClientRect();
        const size = Math.max(rect.width, rect.height);
        const x = e.clientX - rect.left - size / 2;
        const y = e.clientY - rect.top - size / 2;
        
        ripple.style.width = ripple.style.height = size + 'px';
        ripple.style.left = x + 'px';
        ripple.style.top = y + 'px';
        ripple.classList.add('ripple');
        
        this.appendChild(ripple);
        
        setTimeout(() => {
            ripple.remove();
        }, 600);
    });
}

// Add ripple effect to buttons
document.querySelectorAll('.toggle-btn, .category-btn, .continue-btn').forEach(addRippleEffect);