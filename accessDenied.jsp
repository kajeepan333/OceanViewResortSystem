<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Access Denied - Ocean View Resort</title>
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

            .denied-container {
                background: rgba(255, 255, 255, 0.95);
                border-radius: 20px;
                padding: 50px;
                text-align: center;
                max-width: 500px;
                box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
            }

            .denied-icon {
                font-size: 5rem;
                color: #ffc107;
                margin-bottom: 20px;
            }

            .denied-title {
                font-size: 2rem;
                font-weight: 700;
                color: #764ba2;
                margin-bottom: 15px;
            }

            .denied-message {
                color: #6c757d;
                margin-bottom: 30px;
            }

            .btn-back {
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                border: none;
                border-radius: 10px;
                padding: 12px 30px;
                color: white;
                font-weight: 600;
                text-decoration: none;
                display: inline-block;
                margin: 5px;
            }

            .btn-back:hover {
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(118, 75, 162, 0.3);
                color: white;
            }
        </style>
    </head>

    <body>
        <div class="denied-container">
            <div class="denied-icon">
                <i class="fas fa-ban"></i>
            </div>
            <div class="denied-title">Access Denied</div>
            <p class="denied-message">
                You do not have permission to access this page.<br>
                Please contact an administrator if you believe this is a mistake.
            </p>
            <a href="${pageContext.request.contextPath}/controller/dashboard" class="btn-back">
                <i class="fas fa-arrow-left me-2"></i>Back to Dashboard
            </a>
            <a href="${pageContext.request.contextPath}/controller/logout" class="btn-back"
                style="background: #dc3545;">
                <i class="fas fa-sign-out-alt me-2"></i>Logout
            </a>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>