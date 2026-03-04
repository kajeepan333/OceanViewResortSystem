<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Help - Ocean View Resort</title>
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

                .help-card {
                    background: white;
                    border-radius: 15px;
                    padding: 25px;
                    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
                    margin-bottom: 25px;
                }

                .help-card h5 {
                    color: #764ba2;
                    margin-bottom: 15px;
                }

                .help-card .step {
                    display: flex;
                    gap: 15px;
                    margin-bottom: 15px;
                }

                .help-card .step-number {
                    width: 35px;
                    height: 35px;
                    border-radius: 50%;
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    color: white;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    font-weight: bold;
                    flex-shrink: 0;
                }

                .price-table th {
                    background: #f8f9fa;
                    color: #764ba2;
                }

                .accordion-button:not(.collapsed) {
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    color: white;
                }

                .accordion-button:focus {
                    box-shadow: 0 0 0 0.2rem rgba(118, 75, 162, 0.25);
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller/generateBill">
                        <i class="fas fa-file-invoice"></i>Generate Bill</a>
                    <a class="nav-link active" href="${pageContext.request.contextPath}/controller/help">
                        <i class="fas fa-question-circle"></i>Help</a>
                    <a class="nav-link text-warning" href="${pageContext.request.contextPath}/controller/logout">
                        <i class="fas fa-sign-out-alt"></i>Logout</a>
                </nav>
            </div>

            <!-- Main Content -->
            <div class="main-content">
                <div class="top-header">
                    <div>
                        <h4 class="mb-0">Help & Guide</h4>
                        <small class="text-muted">Learn how to use the reservation system</small>
                    </div>
                    <div class="ms-3">
                        <a href="${pageContext.request.contextPath}/controller/dashboard"
                            class="btn btn-outline-primary btn-sm">
                            <i class="fas fa-arrow-left me-1"></i>Back to Dashboard
                        </a>
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
                        <a href="${pageContext.request.contextPath}/controller/logout" class="btn btn-logout btn-sm">
                            <i class="fas fa-sign-out-alt me-1"></i>Logout
                        </a>
                    </div>
                </div>

                <div class="content-area">
                    <!-- How to Add a Reservation -->
                    <div class="help-card">
                        <h5><i class="fas fa-plus-circle me-2"></i>How to Add a Reservation</h5>
                        <div class="step">
                            <div class="step-number">1</div>
                            <div>Click <strong>"Add Reservation"</strong> from the sidebar.</div>
                        </div>
                        <div class="step">
                            <div class="step-number">2</div>
                            <div>Fill in the guest's personal information (name, NIC/passport, address, phone, email).
                            </div>
                        </div>
                        <div class="step">
                            <div class="step-number">3</div>
                            <div>Select the room type, A/C preference, and meal plan.</div>
                        </div>
                        <div class="step">
                            <div class="step-number">4</div>
                            <div>Enter the number of adults and children.</div>
                        </div>
                        <div class="step">
                            <div class="step-number">5</div>
                            <div>Choose check-in and check-out dates, then click <strong>"Save Reservation"</strong>.
                            </div>
                        </div>
                    </div>

                    <!-- Room Pricing -->
                    <div class="help-card">
                        <h5><i class="fas fa-money-bill-wave me-2"></i>Room Pricing (Per Night)</h5>
                        <div class="table-responsive">
                            <table class="table table-bordered price-table">
                                <thead>
                                    <tr>
                                        <th>Room Type</th>
                                        <th>A/C</th>
                                        <th>Non A/C</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Standard</td>
                                        <td>Rs. 6,000</td>
                                        <td>Rs. 4,000</td>
                                    </tr>
                                    <tr>
                                        <td>Deluxe</td>
                                        <td>Rs. 10,000</td>
                                        <td>Rs. 8,000</td>
                                    </tr>
                                    <tr>
                                        <td>Suite</td>
                                        <td>Rs. 16,000</td>
                                        <td>Rs. 14,000</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <!-- Meal Pricing -->
                    <div class="help-card">
                        <h5><i class="fas fa-utensils me-2"></i>Meal Plans (Per Person Per Day)</h5>
                        <div class="table-responsive">
                            <table class="table table-bordered price-table">
                                <thead>
                                    <tr>
                                        <th>Meal Plan</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Only Breakfast</td>
                                        <td>Rs. 500</td>
                                    </tr>
                                    <tr>
                                        <td>Breakfast + Lunch</td>
                                        <td>Rs. 2,000</td>
                                    </tr>
                                    <tr>
                                        <td>3 Times (Full Board)</td>
                                        <td>Rs. 3,500</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <!-- Taxes -->
                    <div class="help-card">
                        <h5><i class="fas fa-receipt me-2"></i>Taxes & Charges</h5>
                        <ul>
                            <li><strong>VAT:</strong> 18% applied on the subtotal</li>
                            <li><strong>Service Charge:</strong> 5% applied on the subtotal</li>
                        </ul>
                    </div>

                    <!-- FAQ -->
                    <div class="help-card">
                        <h5><i class="fas fa-question me-2"></i>Frequently Asked Questions</h5>
                        <div class="accordion" id="faqAccordion">
                            <div class="accordion-item border-0 mb-2">
                                <h2 class="accordion-header">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#faq1">
                                        How is the bill calculated?
                                    </button>
                                </h2>
                                <div id="faq1" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                                    <div class="accordion-body">
                                        Bill = (Room Price × Nights) + (Meal Price × Total Guests × Nights).
                                        Then VAT (18%) and Service Charge (5%) are added to the subtotal.
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-item border-0 mb-2">
                                <h2 class="accordion-header">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#faq2">
                                        Who can edit or delete reservations?
                                    </button>
                                </h2>
                                <div id="faq2" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                                    <div class="accordion-body">
                                        Only administrators can edit or delete reservations. Staff members
                                        can add new reservations and view existing ones.
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-item border-0 mb-2">
                                <h2 class="accordion-header">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#faq3">
                                        How do I generate a PDF bill?
                                    </button>
                                </h2>
                                <div id="faq3" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                                    <div class="accordion-body">
                                        Go to "Generate Bill", enter the Guest ID, click Search, then
                                        click "Download PDF Bill" to download the professional bill document.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Contact -->
                    <div class="help-card">
                        <h5><i class="fas fa-phone-alt me-2"></i>Need More Help?</h5>
                        <p>Contact our support team:</p>
                        <ul>
                            <li><strong>Email:</strong> support@oceanview.com</li>
                            <li><strong>Phone:</strong> +94 91 123 4567</li>
                        </ul>
                    </div>
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>