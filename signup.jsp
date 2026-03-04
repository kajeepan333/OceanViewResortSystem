<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Sign Up - Ocean View Resort</title>
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

                .signup-container {
                    background: rgba(255, 255, 255, 0.95);
                    border-radius: 20px;
                    box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
                    overflow: hidden;
                    width: 100%;
                    max-width: 500px;
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
                }

                .form-control:focus {
                    border-color: #764ba2;
                    box-shadow: 0 0 0 0.2rem rgba(118, 75, 162, 0.25);
                }

                .btn-signup {
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    border: none;
                    border-radius: 10px;
                    padding: 12px;
                    font-weight: 600;
                    width: 100%;
                    color: white;
                }

                .btn-signup:hover {
                    background: linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%);
                    transform: translateY(-2px);
                    box-shadow: 0 5px 15px rgba(118, 75, 162, 0.3);
                    color: white;
                }

                .login-link {
                    color: #764ba2;
                    text-decoration: none;
                }

                .login-link:hover {
                    text-decoration: underline;
                }

                .alert {
                    border-radius: 10px;
                    border: none;
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

                .password-requirements {
                    font-size: 0.8rem;
                    color: #6c757d;
                    margin-top: 5px;
                }
            </style>
        </head>

        <body>
            <div class="signup-container">
                <div class="text-center mb-4">
                    <i class="fas fa-umbrella-beach fa-3x text-primary mb-3"></i>
                    <h1 class="brand-title">Create Account</h1>
                    <p class="brand-subtitle">Join Ocean View Resort as Staff</p>
                </div>

                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="fas fa-exclamation-circle me-2"></i>
                        <c:out value="${errorMessage}" />
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="fas fa-check-circle me-2"></i>
                        <c:out value="${successMessage}" />
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/controller/signup" method="post" id="signupForm">
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                        <input type="text" class="form-control with-icon" name="username" placeholder="Username"
                            value="${username}" required minlength="3">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                        <input type="email" class="form-control with-icon" name="email" placeholder="Email Address"
                            value="${email}" required>
                    </div>

                    <div class="input-group mb-1">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        <input type="password" class="form-control with-icon" name="password" placeholder="Password"
                            required minlength="8" id="password">
                    </div>
                    <div class="password-requirements mb-3">
                        Min 8 chars, 1 uppercase, 1 lowercase, 1 number, 1 special character
                    </div>

                    <div class="input-group mb-4">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        <input type="password" class="form-control with-icon" name="confirmPassword"
                            placeholder="Confirm Password" required id="confirmPassword">
                    </div>

                    <button type="submit" class="btn btn-signup mb-3">
                        <i class="fas fa-user-plus me-2"></i>Create Account
                    </button>
                </form>

                <div class="text-center">
                    <div class="form-footer">
                        Already have an account? <a href="${pageContext.request.contextPath}/controller/login">Login
                            here</a>
                    </div>
                </div>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
                <script>
                    document.getElementById('signupForm').addEventListener('submit', function (e) {
                        var password = document.getElementById('password').value;
                        var confirmPassword = document.getElementById('confirmPassword').value;

                        if (password !== confirmPassword) {
                            e.preventDefault();
                            alert('Passwords do not match!');
                            return;
                        }

                        if (password.length < 8) {
                            e.preventDefault();
                            alert('Password must be at least 8 characters long.');
                            return;
                        }
                    });
                </script>
        </body>

        </html>