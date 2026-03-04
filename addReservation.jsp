<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Add Reservation - Ocean View Resort</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
            <style>
                body {
                    background: #f8f9fa;
                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                }

                .sidebar {
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    min-height: 100vh;
                    color: white;
                    position: fixed;
                    top: 0;
                    left: 0;
                    width: 250px;
                    z-index: 1000;
                    box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
                }

                .sidebar .nav-link {
                    color: rgba(255, 255, 255, 0.8);
                    padding: 15px 20px;
                    border-radius: 0;
                    transition: all 0.3s;
                }

                .sidebar .nav-link:hover,
                .sidebar .nav-link.active {
                    color: white;
                    background: rgba(255, 255, 255, 0.1);
                    border-left: 4px solid #fff;
                }

                .sidebar .nav-link i {
                    width: 20px;
                    margin-right: 10px;
                }

                .main-content {
                    margin-left: 250px;
                    min-height: 100vh;
                }

                .top-header {
                    background: white;
                    padding: 15px 30px;
                    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                }

                .user-info {
                    display: flex;
                    align-items: center;
                    gap: 15px;
                }

                .user-avatar {
                    width: 40px;
                    height: 40px;
                    border-radius: 50%;
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    color: white;
                    font-weight: bold;
                }

                .content-area {
                    padding: 30px;
                }

                .form-container {
                    background: white;
                    border-radius: 15px;
                    padding: 30px;
                    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
                }

                .form-section {
                    margin-bottom: 30px;
                    padding-bottom: 20px;
                    border-bottom: 1px solid #e9ecef;
                }

                .form-section:last-child {
                    border-bottom: none;
                    margin-bottom: 0;
                }

                .section-title {
                    color: #764ba2;
                    font-weight: bold;
                    margin-bottom: 20px;
                    font-size: 1.1rem;
                }

                .form-control,
                .form-select {
                    border-radius: 10px;
                    border: 1px solid #e0e0e0;
                    padding: 12px 15px;
                }

                .form-control:focus,
                .form-select:focus {
                    border-color: #764ba2;
                    box-shadow: 0 0 0 0.2rem rgba(118, 75, 162, 0.25);
                }

                .btn-primary {
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    border: none;
                    border-radius: 10px;
                    padding: 12px 30px;
                    font-weight: 600;
                }

                .btn-primary:hover {
                    background: linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%);
                    transform: translateY(-2px);
                    box-shadow: 0 5px 15px rgba(118, 75, 162, 0.3);
                }

                .btn-secondary {
                    border-radius: 10px;
                    padding: 12px 30px;
                    font-weight: 600;
                }

                .brand-title {
                    font-size: 1.5rem;
                    font-weight: bold;
                    margin-bottom: 30px;
                    padding: 20px;
                    border-bottom: 1px solid rgba(255, 255, 255, 0.1);
                }

                .brand-title i {
                    margin-right: 10px;
                }

                .btn-logout {
                    background: #dc3545;
                    border: none;
                    border-radius: 25px;
                    padding: 8px 20px;
                    color: white;
                }

                .btn-logout:hover {
                    background: #c82333;
                }

                .room-price-display {
                    background: #f8f9fa;
                    border-radius: 10px;
                    padding: 15px;
                    margin-top: 10px;
                }

                .price-highlight {
                    color: #28a745;
                    font-weight: bold;
                    font-size: 1.2rem;
                }

                .alert {
                    border-radius: 10px;
                    border: none;
                }
            </style>
        </head>

        <body>
            <!-- Sidebar -->
            <div class="sidebar">
                <div class="brand-title">
                    <i class="fas fa-umbrella-beach"></i>
                    Ocean View Resort
                </div>
                <nav class="nav flex-column">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller/dashboard">
                        <i class="fas fa-tachometer-alt"></i>Dashboard
                    </a>
                    <a class="nav-link active" href="${pageContext.request.contextPath}/controller/addReservation">
                        <i class="fas fa-plus-circle"></i>Add Reservation
                    </a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller/viewReservations">
                        <i class="fas fa-list"></i>View Reservations
                    </a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller/generateBill">
                        <i class="fas fa-file-invoice"></i>Generate Bill
                    </a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller/help">
                        <i class="fas fa-question-circle"></i>Help
                    </a>
                    <a class="nav-link text-warning" href="${pageContext.request.contextPath}/controller/logout">
                        <i class="fas fa-sign-out-alt"></i>Logout
                    </a>
                </nav>
            </div>

            <!-- Main Content -->
            <div class="main-content">
                <!-- Top Header -->
                <div class="top-header">
                    <div>
                        <h4 class="mb-0">Add New Reservation</h4>
                        <small class="text-muted">Create a new room reservation</small>
                    </div>
                    <div class="user-info">
                        <span class="text-muted">Welcome back,</span>
                        <div class="user-avatar">
                            ${currentUser.username.substring(0,1).toUpperCase()}
                        </div>
                        <div>
                            <div class="fw-bold">${currentUser.username}</div>
                            <small class="text-muted">Administrator</small>
                        </div>
                        <a href="${pageContext.request.contextPath}/controller/logout" class="btn btn-logout btn-sm">
                            <i class="fas fa-sign-out-alt me-1"></i>Logout
                        </a>
                    </div>
                </div>

                <!-- Content Area -->
                <div class="content-area">
                    <div class="form-container">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <i class="fas fa-exclamation-circle me-2"></i>
                                ${errorMessage}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <i class="fas fa-check-circle me-2"></i>
                                ${successMessage}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/controller/saveReservation" method="post"
                            id="reservationForm">
                            <!-- Guest Information Section -->
                            <div class="form-section">
                                <h5 class="section-title">
                                    <i class="fas fa-user me-2"></i>Guest Information
                                </h5>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="guestName" class="form-label">Guest Name *</label>
                                        <input type="text" class="form-control" id="guestName" name="guestName"
                                            required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="nicPassport" class="form-label">NIC / Passport *</label>
                                        <input type="text" class="form-control" id="nicPassport" name="nicPassport"
                                            required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="address" class="form-label">Address *</label>
                                        <textarea class="form-control" id="address" name="address" rows="2"
                                            required></textarea>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="phone" class="form-label">Phone Number *</label>
                                        <input type="tel" class="form-control" id="phone" name="phone"
                                            pattern="[0-9]{10}" required>
                                        <small class="text-muted">10-digit number</small>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="email" class="form-label">Email *</label>
                                        <input type="email" class="form-control" id="email" name="email" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="guestId" class="form-label">Guest ID</label>
                                        <input type="text" class="form-control" id="guestId" name="guestId" readonly>
                                        <small class="text-muted">Auto-generated</small>
                                    </div>
                                </div>
                            </div>

                            <!-- Room Details Section -->
                            <div class="form-section">
                                <h5 class="section-title">
                                    <i class="fas fa-bed me-2"></i>Room Details
                                </h5>
                                <div class="row">
                                    <div class="col-md-4 mb-3">
                                        <label for="roomType" class="form-label">Room Type *</label>
                                        <select class="form-select" id="roomType" name="roomType" required>
                                            <option value="">Select Room Type</option>
                                            <option value="Standard">Standard</option>
                                            <option value="Deluxe">Deluxe</option>
                                            <option value="Suite">Suite</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="acType" class="form-label">Special Request *</label>
                                        <select class="form-select" id="acType" name="acType" required>
                                            <option value="">Select Type</option>
                                            <option value="A/C">A/C</option>
                                            <option value="Non A/C">Non A/C</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label for="mealPlan" class="form-label">Meal Plan *</label>
                                        <select class="form-select" id="mealPlan" name="mealPlan" required>
                                            <option value="">Select Meal Plan</option>
                                            <option value="Only Breakfast">Only Breakfast</option>
                                            <option value="Breakfast + Lunch">Breakfast + Lunch</option>
                                            <option value="3 Times">3 Times</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="room-price-display" id="roomPriceDisplay" style="display: none;">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <span>Room Price per Night:</span>
                                        <span class="price-highlight" id="roomPrice">Rs. 0</span>
                                    </div>
                                </div>
                            </div>

                            <!-- Guest Count Section -->
                            <div class="form-section">
                                <h5 class="section-title">
                                    <i class="fas fa-users me-2"></i>Number of Guests
                                </h5>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="adults" class="form-label">Adults *</label>
                                        <input type="number" class="form-control" id="adults" name="adults" min="1"
                                            value="1" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="children" class="form-label">Children</label>
                                        <input type="number" class="form-control" id="children" name="children" min="0"
                                            value="0">
                                    </div>
                                </div>
                            </div>

                            <!-- Dates Section -->
                            <div class="form-section">
                                <h5 class="section-title">
                                    <i class="fas fa-calendar me-2"></i>Stay Duration
                                </h5>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="checkIn" class="form-label">Check-in Date *</label>
                                        <input type="date" class="form-control" id="checkIn" name="checkIn" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="checkOut" class="form-label">Check-out Date *</label>
                                        <input type="date" class="form-control" id="checkOut" name="checkOut" required>
                                    </div>
                                </div>
                            </div>

                            <!-- Form Actions -->
                            <div class="text-end">
                                <button type="button" class="btn btn-secondary me-2" onclick="resetForm()">
                                    <i class="fas fa-redo me-2"></i>Reset
                                </button>
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-save me-2"></i>Save Reservation
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            <script>
                // Room prices
                const roomPrices = {
                    'Standard': { 'A/C': 6000, 'Non A/C': 4000 },
                    'Deluxe': { 'A/C': 10000, 'Non A/C': 8000 },
                    'Suite': { 'A/C': 16000, 'Non A/C': 14000 }
                };

                // Generate guest ID on page load
                window.onload = function () {
                    generateGuestId();
                    setMinDate();
                };

                function generateGuestId() {
                    const guestId = 'GUEST' + Math.floor(Math.random() * 1000000).toString().padStart(6, '0');
                    document.getElementById('guestId').value = guestId;
                }

                function setMinDate() {
                    const today = new Date().toISOString().split('T')[0];
                    document.getElementById('checkIn').setAttribute('min', today);
                    document.getElementById('checkOut').setAttribute('min', today);
                }

                // Update room price display
                document.getElementById('roomType').addEventListener('change', updateRoomPrice);
                document.getElementById('acType').addEventListener('change', updateRoomPrice);

                function updateRoomPrice() {
                    const roomType = document.getElementById('roomType').value;
                    const acType = document.getElementById('acType').value;
                    const priceDisplay = document.getElementById('roomPriceDisplay');
                    const priceElement = document.getElementById('roomPrice');

                    if (roomType && acType) {
                        const price = roomPrices[roomType][acType];
                        priceElement.textContent = 'Rs. ' + price.toLocaleString();
                        priceDisplay.style.display = 'block';
                    } else {
                        priceDisplay.style.display = 'none';
                    }
                }

                // Validate dates
                document.getElementById('checkIn').addEventListener('change', validateDates);
                document.getElementById('checkOut').addEventListener('change', validateDates);

                function validateDates() {
                    const checkIn = document.getElementById('checkIn').value;
                    const checkOut = document.getElementById('checkOut').value;

                    if (checkIn && checkOut) {
                        const checkInDate = new Date(checkIn);
                        const checkOutDate = new Date(checkOut);
                        const today = new Date();
                        today.setHours(0, 0, 0, 0);

                        if (checkInDate < today) {
                            alert('Check-in date cannot be in the past');
                            document.getElementById('checkIn').value = '';
                            return;
                        }

                        if (checkOutDate <= checkInDate) {
                            alert('Check-out date must be after check-in date');
                            document.getElementById('checkOut').value = '';
                            return;
                        }

                        // Set minimum check-out date
                        const nextDay = new Date(checkInDate);
                        nextDay.setDate(nextDay.getDate() + 1);
                        document.getElementById('checkOut').setAttribute('min', nextDay.toISOString().split('T')[0]);
                    }
                }

                function resetForm() {
                    if (confirm('Are you sure you want to reset the form?')) {
                        document.getElementById('reservationForm').reset();
                        generateGuestId();
                        document.getElementById('roomPriceDisplay').style.display = 'none';
                    }
                }

                // Form validation
                document.getElementById('reservationForm').addEventListener('submit', function (e) {
                    const phone = document.getElementById('phone').value;
                    if (phone && !/^[0-9]{10}$/.test(phone)) {
                        e.preventDefault();
                        alert('Please enter a valid 10-digit phone number');
                        return;
                    }

                    const email = document.getElementById('email').value;
                    if (email && !email.includes('@')) {
                        e.preventDefault();
                        alert('Please enter a valid email address');
                        return;
                    }
                });
            </script>
        </body>

        </html>