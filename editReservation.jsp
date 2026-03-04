<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Edit Reservation - Ocean View Resort</title>
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
                    color: white;
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

                .alert {
                    border-radius: 10px;
                    border: none;
                }
            </style>
        </head>

        <body>
            <!-- Sidebar -->
            <div class="sidebar">
                <div class="brand-title"><i class="fas fa-umbrella-beach"></i>Ocean View Resort</div>
                <nav class="nav flex-column">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller/dashboard">
                        <i class="fas fa-tachometer-alt"></i>Dashboard
                    </a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller/addReservation">
                        <i class="fas fa-plus-circle"></i>Add Reservation
                    </a>
                    <a class="nav-link active" href="${pageContext.request.contextPath}/controller/viewReservations">
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
                <div class="top-header">
                    <div>
                        <h4 class="mb-0">Edit Reservation</h4>
                        <small class="text-muted">Update reservation details</small>
                    </div>
                    <div class="user-info">
                        <div class="user-avatar">
                            <c:out value="${currentUser.username.substring(0,1).toUpperCase()}" />
                        </div>
                        <div>
                            <div class="fw-bold">
                                <c:out value="${currentUser.username}" />
                            </div>
                            <small class="text-muted">Administrator</small>
                        </div>
                        <a href="${pageContext.request.contextPath}/controller/logout" class="btn btn-logout btn-sm">
                            <i class="fas fa-sign-out-alt me-1"></i>Logout
                        </a>
                    </div>
                </div>

                <div class="content-area">
                    <div class="form-container">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <i class="fas fa-exclamation-circle me-2"></i>
                                <c:out value="${errorMessage}" />
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/controller/editReservation" method="post">
                            <input type="hidden" name="id" value="${reservation.id}">

                            <!-- Guest Information -->
                            <div class="form-section">
                                <h5 class="section-title"><i class="fas fa-user me-2"></i>Guest Information</h5>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Guest ID</label>
                                        <input type="text" class="form-control" value="${reservation.guestId}" readonly>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Guest Name *</label>
                                        <input type="text" class="form-control" name="guestName"
                                            value="${reservation.guestName}" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">NIC / Passport *</label>
                                        <input type="text" class="form-control" name="nicPassport"
                                            value="${reservation.nicPassport}" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Phone *</label>
                                        <input type="tel" class="form-control" name="phone" value="${reservation.phone}"
                                            pattern="[0-9]{10}" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Email *</label>
                                        <input type="email" class="form-control" name="email"
                                            value="${reservation.email}" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Address *</label>
                                        <textarea class="form-control" name="address" rows="2"
                                            required>${reservation.address}</textarea>
                                    </div>
                                </div>
                            </div>

                            <!-- Room Details -->
                            <div class="form-section">
                                <h5 class="section-title"><i class="fas fa-bed me-2"></i>Room Details</h5>
                                <div class="row">
                                    <div class="col-md-4 mb-3">
                                        <label class="form-label">Room Type *</label>
                                        <select class="form-select" name="roomType" required>
                                            <option value="Standard" ${reservation.roomType=='Standard' ? 'selected'
                                                : '' }>Standard</option>
                                            <option value="Deluxe" ${reservation.roomType=='Deluxe' ? 'selected' : '' }>
                                                Deluxe</option>
                                            <option value="Suite" ${reservation.roomType=='Suite' ? 'selected' : '' }>
                                                Suite</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label class="form-label">AC Type *</label>
                                        <select class="form-select" name="acType" required>
                                            <option value="A/C" ${reservation.acType=='A/C' ? 'selected' : '' }>A/C
                                            </option>
                                            <option value="Non A/C" ${reservation.acType=='Non A/C' ? 'selected' : '' }>
                                                Non A/C</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4 mb-3">
                                        <label class="form-label">Meal Plan *</label>
                                        <select class="form-select" name="mealPlan" required>
                                            <option value="Only Breakfast" ${reservation.mealPlan=='Only Breakfast'
                                                ? 'selected' : '' }>Only Breakfast</option>
                                            <option value="Breakfast + Lunch"
                                                ${reservation.mealPlan=='Breakfast + Lunch' ? 'selected' : '' }>
                                                Breakfast + Lunch</option>
                                            <option value="3 Times" ${reservation.mealPlan=='3 Times' ? 'selected' : ''
                                                }>3 Times</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <!-- Guests & Dates -->
                            <div class="form-section">
                                <h5 class="section-title"><i class="fas fa-calendar me-2"></i>Stay Details</h5>
                                <div class="row">
                                    <div class="col-md-3 mb-3">
                                        <label class="form-label">Adults *</label>
                                        <input type="number" class="form-control" name="adults" min="1"
                                            value="${reservation.adults}" required>
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label class="form-label">Children</label>
                                        <input type="number" class="form-control" name="children" min="0"
                                            value="${reservation.children}">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label class="form-label">Check-in *</label>
                                        <input type="date" class="form-control" name="checkIn"
                                            value="${reservation.formattedCheckIn}" required>
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label class="form-label">Check-out *</label>
                                        <input type="date" class="form-control" name="checkOut"
                                            value="${reservation.formattedCheckOut}" required>
                                    </div>
                                </div>
                            </div>

                            <!-- Actions -->
                            <div class="text-end">
                                <a href="${pageContext.request.contextPath}/controller/viewReservations"
                                    class="btn btn-secondary me-2">
                                    <i class="fas fa-times me-2"></i>Cancel
                                </a>
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-save me-2"></i>Update Reservation
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>