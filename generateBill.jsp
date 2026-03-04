<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Generate Bill - Ocean View Resort</title>
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

                    .search-card,
                    .bill-card {
                        background: white;
                        border-radius: 15px;
                        padding: 25px;
                        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
                        margin-bottom: 25px;
                    }

                    .form-control {
                        border-radius: 10px;
                        border: 1px solid #e0e0e0;
                        padding: 12px 15px;
                    }

                    .form-control:focus {
                        border-color: #764ba2;
                        box-shadow: 0 0 0 0.2rem rgba(118, 75, 162, 0.25);
                    }

                    .btn-search {
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        border: none;
                        border-radius: 10px;
                        padding: 12px 25px;
                        color: white;
                        font-weight: 600;
                    }

                    .btn-search:hover {
                        color: white;
                        transform: translateY(-1px);
                    }

                    .btn-download {
                        background: #28a745;
                        border: none;
                        border-radius: 10px;
                        padding: 12px 25px;
                        color: white;
                        font-weight: 600;
                    }

                    .btn-download:hover {
                        background: #218838;
                        color: white;
                        transform: translateY(-1px);
                    }

                    .bill-header {
                        text-align: center;
                        padding: 20px;
                        border-bottom: 2px solid #764ba2;
                        margin-bottom: 20px;
                    }

                    .bill-header h3 {
                        color: #764ba2;
                    }

                    .bill-section-title {
                        color: #764ba2;
                        font-weight: 600;
                        margin: 20px 0 10px;
                    }

                    .bill-table td {
                        padding: 8px 15px;
                    }

                    .bill-table .label {
                        color: #6c757d;
                        font-weight: 600;
                    }

                    .bill-total {
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        color: white;
                        border-radius: 10px;
                        padding: 15px 20px;
                        font-size: 1.2rem;
                        font-weight: 700;
                        text-align: center;
                        margin-top: 15px;
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
                            <i class="fas fa-tachometer-alt"></i>Dashboard</a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller/addReservation">
                            <i class="fas fa-plus-circle"></i>Add Reservation</a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller/viewReservations">
                            <i class="fas fa-list"></i>View Reservations</a>
                        <a class="nav-link active" href="${pageContext.request.contextPath}/controller/generateBill">
                            <i class="fas fa-file-invoice"></i>Generate Bill</a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller/help">
                            <i class="fas fa-question-circle"></i>Help</a>
                        <a class="nav-link text-warning" href="${pageContext.request.contextPath}/controller/logout">
                            <i class="fas fa-sign-out-alt"></i>Logout</a>
                    </nav>
                </div>

                <!-- Main Content -->
                <div class="main-content">
                    <div class="top-header">
                        <div>
                            <h4 class="mb-0">Generate Bill</h4>
                            <small class="text-muted">Search and generate bills for reservations</small>
                        </div>
                        <div class="user-info">
                            <div class="user-avatar">
                                <c:out value="${currentUser.username.substring(0,1).toUpperCase()}" />
                            </div>
                            <div>
                                <div class="fw-bold">
                                    <c:out value="${currentUser.username}" />
                                </div>
                                <small class="text-muted">
                                    <c:out value="${currentUser.role}" />
                                </small>
                            </div>
                            <a href="${pageContext.request.contextPath}/controller/logout"
                                class="btn btn-logout btn-sm">
                                <i class="fas fa-sign-out-alt me-1"></i>Logout
                            </a>
                        </div>
                    </div>

                    <div class="content-area">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <i class="fas fa-exclamation-circle me-2"></i>
                                <c:out value="${errorMessage}" />
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <!-- Search Form -->
                        <div class="search-card">
                            <h5><i class="fas fa-search me-2 text-primary"></i>Search Reservation</h5>
                            <form action="${pageContext.request.contextPath}/controller/generateBill" method="post"
                                class="row g-3 align-items-end mt-2">
                                <div class="col-md-8">
                                    <label class="form-label">Enter Guest ID</label>
                                    <input type="text" class="form-control" name="guestId"
                                        placeholder="e.g., GUEST001234" required>
                                </div>
                                <div class="col-md-4">
                                    <button type="submit" class="btn btn-search w-100">
                                        <i class="fas fa-search me-2"></i>Search
                                    </button>
                                </div>
                            </form>
                        </div>

                        <!-- Bill Display -->
                        <c:if test="${not empty reservation}">
                            <div class="bill-card">
                                <div class="bill-header">
                                    <h3><i class="fas fa-umbrella-beach me-2"></i>OCEAN VIEW RESORT</h3>
                                    <p class="text-muted mb-0">123 Beach Road, Galle, Sri Lanka</p>
                                    <p class="text-muted">Tel: +94 91 123 4567 | Email: info@oceanview.com</p>
                                </div>

                                <h6 class="bill-section-title"><i class="fas fa-user me-2"></i>Guest Information</h6>
                                <table class="table bill-table table-borderless">
                                    <tr>
                                        <td class="label">Guest ID:</td>
                                        <td>
                                            <c:out value="${reservation.guestId}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">Guest Name:</td>
                                        <td>
                                            <c:out value="${reservation.guestName}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">NIC/Passport:</td>
                                        <td>
                                            <c:out value="${reservation.nicPassport}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">Phone:</td>
                                        <td>
                                            <c:out value="${reservation.phone}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">Email:</td>
                                        <td>
                                            <c:out value="${reservation.email}" />
                                        </td>
                                    </tr>
                                </table>

                                <h6 class="bill-section-title"><i class="fas fa-bed me-2"></i>Reservation Details</h6>
                                <table class="table bill-table table-borderless">
                                    <tr>
                                        <td class="label">Room Type:</td>
                                        <td>
                                            <c:out value="${reservation.roomType}" /> (
                                            <c:out value="${reservation.acType}" />)
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">Guests:</td>
                                        <td>
                                            <c:out value="${reservation.adults}" /> Adults,
                                            <c:out value="${reservation.children}" /> Children
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">Check-in:</td>
                                        <td>
                                            <c:out value="${reservation.formattedCheckIn}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">Check-out:</td>
                                        <td>
                                            <c:out value="${reservation.formattedCheckOut}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">Nights:</td>
                                        <td>
                                            <c:out value="${reservation.numberOfNights}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">Meal Plan:</td>
                                        <td>
                                            <c:out value="${reservation.mealPlan}" />
                                        </td>
                                    </tr>
                                </table>

                                <h6 class="bill-section-title"><i class="fas fa-calculator me-2"></i>Bill Calculation
                                </h6>
                                <table class="table bill-table table-borderless">
                                    <tr>
                                        <td class="label">Subtotal:</td>
                                        <td>Rs.
                                            <fmt:formatNumber value="${subtotal}" pattern="#,##0.00" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">VAT (18%):</td>
                                        <td>Rs.
                                            <fmt:formatNumber value="${vat}" pattern="#,##0.00" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">Service Charge (5%):</td>
                                        <td>Rs.
                                            <fmt:formatNumber value="${serviceCharge}" pattern="#,##0.00" />
                                        </td>
                                    </tr>
                                </table>

                                <div class="bill-total">
                                    GRAND TOTAL: Rs.
                                    <fmt:formatNumber value="${grandTotal}" pattern="#,##0.00" />
                                </div>

                                <p class="text-muted text-center mt-2 mb-0">
                                    <small>
                                        <c:out value="${strategyDescription}" />
                                    </small>
                                </p>

                                <div class="text-center mt-4">
                                    <a href="${pageContext.request.contextPath}/controller/generateBill?guestId=${reservation.guestId}&action=download"
                                        class="btn btn-download">
                                        <i class="fas fa-file-pdf me-2"></i>Download PDF Bill
                                    </a>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            </body>

            </html>