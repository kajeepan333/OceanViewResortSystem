<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Login - Ocean View Resort</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
            <style>
                body {
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    min-height: 100vh;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                }

                .login-container {
                    background: rgba(255, 255, 255, 0.95);
                    border-radius: 20px;
                    box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
                    overflow: hidden;
                    width: 100%;
                    max-width: 900px;
                    min-height: 500px;
                }

                .login-image {
                    background: url('https://images.unsplash.com/photo-1566073771259-6a8506099945?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80') center/cover;
                    min-height: 500px;
                }

                .login-form {
                    padding: 40px;
                }

                .brand-title {
                    color: #764ba2;
                    font-weight: bold;
                    font-size: 2rem;
                    margin-bottom: 10px;
                }

                .brand-subtitle {
                    color: #6c757d;
                    margin-bottom: 30px;
                }

                .form-control {
                    border-radius: 10px;
                    border: 1px solid #e0e0e0;
                    padding: 12px 15px;
                    margin-bottom: 20px;
                }

                .form-control:focus {
                    border-color: #764ba2;
                    box-shadow: 0 0 0 0.2rem rgba(118, 75, 162, 0.25);
                }

                .btn-login {
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    border: none;
                    border-radius: 10px;
                    padding: 12px;
                    font-weight: 600;
                    width: 100%;
                    margin-bottom: 15px;
                }

                .btn-login:hover {
                    background: linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%);
                    transform: translateY(-2px);
                    box-shadow: 0 5px 15px rgba(118, 75, 162, 0.3);
                }

                .btn-signup {
                    background: #28a745;
                    border: none;
                    border-radius: 10px;
                    padding: 12px;
                    font-weight: 600;
                    width: 100%;
                }

                .btn-signup:hover {
                    background: #218838;
                    transform: translateY(-2px);
                    box-shadow: 0 5px 15px rgba(40, 167, 69, 0.3);
                }

                .forgot-password {
                    color: #764ba2;
                    text-decoration: none;
                    font-size: 0.9rem;
                }

                .forgot-password:hover {
                    text-decoration: underline;
                }

                .alert {
                    border-radius: 10px;
                    border: none;
                    margin-bottom: 20px;
                }

                .input-group-text {
                    background: #f8f9fa;
                    border: 1px solid #e0e0e0;
                    border-right: none;
                    border-radius: 10px 0 0 10px;
                }

                .form-control.with-icon {
                    border-left: none;
                    border-radius: 0 10px 10px 0;
                }
            </style>
        </head>

        <body>
            <div class="login-container">
                <div class="row g-0 h-100">
                    <div class="col-md-6">
                        <div class="login-image"></div>
                    </div>
                    <div class="col-md-6">
                        <div class="login-form">
                            <div class="text-center mb-4">
                                <i class="fas fa-umbrella-beach fa-3x text-primary mb-3"></i>
                                <h1 class="brand-title">Ocean View Resort</h1>
                                <p class="brand-subtitle">Room Reservation System</p>
                            </div>

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

                            <form action="${pageContext.request.contextPath}/controller/login" method="post">
                                <div class="input-group mb-3">
                                    <span class="input-group-text">
                                        <i class="fas fa-user"></i>
                                    </span>
                                    <input type="text" class="form-control with-icon" name="username"
                                        placeholder="Username" required>
                                </div>

                                <div class="input-group mb-3">
                                    <span class="input-group-text">
                                        <i class="fas fa-lock"></i>
                                    </span>
                                    <input type="password" class="form-control with-icon" name="password"
                                        placeholder="Password" required>
                                </div>

                                <button type="submit" class="btn btn-primary btn-login">
                                    <i class="fas fa-sign-in-alt me-2"></i>Login
                                </button>
                            </form>

                            <div class="text-center mt-3">
                                <p class="mb-0">Don't have an account?
                                    <a href="${pageContext.request.contextPath}/controller/signup"
                                        class="text-primary fw-bold text-decoration-none">Sign Up Now</a>
                                </p>
                            </div>

                            <div class="text-center mt-3">
                                <a href="${pageContext.request.contextPath}/controller/forgotPassword"
                                    class="text-secondary text-decoration-none">
                                    <i class="fas fa-key me-1"></i>Forgot Password?
                                </a>
                            </div>

                            <div class="text-center mt-4">
                                <small class="text-muted">
                                    <i class="fas fa-info-circle me-1"></i>
                                    Default Admin: admin / Admin@123
                                </small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>