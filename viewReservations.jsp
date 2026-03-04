<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>View Reservations - Ocean View Resort</title>
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

                    .table-container {
                        background: white;
                        border-radius: 15px;
                        padding: 25px;
                        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
                    }

                    .search-form {
                        background: white;
                        border-radius: 15px;
                        padding: 20px;
                        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
                        margin-bottom: 20px;
                    }

                    .form-control,
                    .form-select {
                        border-radius: 10px;
                        border: 1px solid #e0e0e0;
                        padding: 10px 15px;
                    }

                    .form-control:focus {
                        border-color: #764ba2;
                        box-shadow: 0 0 0 0.2rem rgba(118, 75, 162, 0.25);
                    }

                    .btn-search {
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        border: none;
                        border-radius: 10px;
                        padding: 10px 20px;
                        color: white;
                        font-weight: 600;
                    }

                    .btn-search:hover {
                        color: white;
                        transform: translateY(-1px);
                    }

                    .table th {
                        background: #f8f9fa;
                        color: #764ba2;
                        font-weight: 600;
                    }

                    .alert {
                        border-radius: 10px;
                        border: none;
                    }

                    .badge-room {
                        background: #764ba2;
                    }

                    .btn-action {
                        border-radius: 8px;
                        padding: 5px 12px;
                        font-size: 0.85rem;
                        margin: 2px;
                    }
                </style>
            </head>

            <body>
                <!-- Sidebar -->
                <div class="sidebar">
                    <div class="brand-title">
                        <i class="fas fa-umbrella-beach"></i>Ocean View Resort
                    </div>
                    <nav class="nav flex-column">
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller/dashboard">
                            <i class="fas fa-tachometer-alt"></i>Dashboard
                        </a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller/addReservation">
                            <i class="fas fa-plus-circle"></i>Add Reservation
                        </a>
                        <a class="nav-link active"
                            href="${pageContext.request.contextPath}/controller/viewReservations">
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
                            <h4 class="mb-0">View Reservations</h4>
                            <small class="text-muted">All guest reservations</small>
                        </div>
                        <div class="user-info">
                            <span class="text-muted">Welcome,</span>
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
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <i class="fas fa-check-circle me-2"></i>
                                <c:out value="${successMessage}" />
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <i class="fas fa-exclamation-circle me-2"></i>
                                <c:out value="${errorMessage}" />
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <!-- Search -->
                        <div class="search-form">
                            <form action="${pageContext.request.contextPath}/controller/viewReservations" method="get"
                                class="row g-3 align-items-end">
                                <div class="col-md-8">
                                    <label class="form-label">Search by Guest Name</label>
                                    <input type="text" class="form-control" name="search"
                                        placeholder="Enter guest name..." value="${searchQuery}">
                                </div>
                                <div class="col-md-2">
                                    <button type="submit" class="btn btn-search w-100">
                                        <i class="fas fa-search me-1"></i>Search
                                    </button>
                                </div>
                                <div class="col-md-2">
                                    <a href="${pageContext.request.contextPath}/controller/viewReservations"
                                        class="btn btn-secondary w-100" style="border-radius: 10px; padding: 10px;">
                                        <i class="fas fa-redo me-1"></i>Reset
                                    </a>
                                </div>
                            </form>
                        </div>

                        <!-- Reservations Table -->
                        <div class="table-container">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h5 class="mb-0">
                                    <i class="fas fa-list me-2 text-primary"></i>Reservations
                                    <span class="badge bg-secondary ms-2">${reservations.size()}</span>
                                </h5>
                                <a href="${pageContext.request.contextPath}/controller/addReservation"
                                    class="btn btn-search btn-sm">
                                    <i class="fas fa-plus me-1"></i>New Reservation
                                </a>
                            </div>

                            <c:choose>
                                <c:when test="${not empty reservations}">
                                    <div class="table-responsive">
                                        <table class="table table-hover align-middle">
                                            <thead>
                                                <tr>
                                                    <th>Guest ID</th>
                                                    <th>Guest Name</th>
                                                    <th>Room</th>
                                                    <th>Check-in</th>
                                                    <th>Check-out</th>
                                                    <th>Guests</th>
                                                    <th>Amount (Rs.)</th>
                                                    <c:if test="${isAdmin}">
                                                        <th>Actions</th>
                                                    </c:if>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="res" items="${reservations}">
                                                    <tr>
                                                        <td><strong>
                                                                <c:out value="${res.guestId}" />
                                                            </strong></td>
                                                        <td>
                                                            <c:out value="${res.guestName}" />
                                                        </td>
                                                        <td>
                                                            <span class="badge badge-room">
                                                                <c:out value="${res.roomType}" />
                                                            </span>
                                                            <small class="text-muted">
                                                                <c:out value="${res.acType}" />
                                                            </small>
                                                        </td>
                                                        <td>
                                                            <c:out value="${res.formattedCheckIn}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${res.formattedCheckOut}" />
                                                        </td>
                                                        <td>
                                                            <c:out value="${res.adults}" />A
                                                            <c:if test="${res.children > 0}">
                                                                +
                                                                <c:out value="${res.children}" />C
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <fmt:formatNumber value="${res.totalAmount}"
                                                                pattern="#,##0.00" />
                                                        </td>
                                                        <c:if test="${isAdmin}">
                                                            <td>
                                                                <a href="${pageContext.request.contextPath}/controller/editReservation?id=${res.id}"
                                                                    class="btn btn-warning btn-action">
                                                                    <i class="fas fa-edit"></i>
                                                                </a>
                                                                <a href="${pageContext.request.contextPath}/controller/deleteReservation?id=${res.id}"
                                                                    class="btn btn-danger btn-action"
                                                                    onclick="return confirm('Are you sure you want to delete this reservation?');">
                                                                    <i class="fas fa-trash"></i>
                                                                </a>
                                                                <a href="${pageContext.request.contextPath}/controller/generateBill?guestId=${res.guestId}"
                                                                    class="btn btn-info btn-action text-white">
                                                                    <i class="fas fa-file-invoice"></i>
                                                                </a>
                                                            </td>
                                                        </c:if>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="text-center py-5">
                                        <i class="fas fa-inbox fa-3x text-muted mb-3"></i>
                                        <p class="text-muted">
                                            <c:choose>
                                                <c:when test="${not empty searchQuery}">
                                                    No reservations found for "
                                                    <c:out value="${searchQuery}" />"
                                                </c:when>
                                                <c:otherwise>
                                                    No reservations yet. Create your first reservation!
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            </body>

            </html>