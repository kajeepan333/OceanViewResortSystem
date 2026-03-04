<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error - Ocean View Resort</title>
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

            .error-container {
                background: rgba(255, 255, 255, 0.95);
                border-radius: 20px;
                padding: 50px;
                text-align: center;
                max-width: 500px;
                box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
            }

            .error-icon {
                font-size: 5rem;
                color: #dc3545;
                margin-bottom: 20px;
            }

            .error-code {
                font-size: 4rem;
                font-weight: 700;
                color: #764ba2;
            }

            .error-message {
                color: #6c757d;
                margin: 15px 0 30px;
            }

            .btn-home {
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                border: none;
                border-radius: 10px;
                padding: 12px 30px;
                color: white;
                font-weight: 600;
                text-decoration: none;
                display: inline-block;
            }

            .btn-home:hover {
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(118, 75, 162, 0.3);
                color: white;
            }
        </style>
    </head>

    <body>
        <div class="error-container">
            <div class="error-icon">
                <i class="fas fa-exclamation-triangle"></i>
            </div>
            <div class="error-code">
                <%= (request.getAttribute("jakarta.servlet.error.status_code") !=null) ?
                    request.getAttribute("jakarta.servlet.error.status_code") : "Error" %>
            </div>
            <p class="error-message">
                <% Integer statusCode=(Integer) request.getAttribute("jakarta.servlet.error.status_code"); if
                    (statusCode !=null && statusCode==403) { %>
                    <strong>Access Restricted.</strong><br>
                    Direct access to JSP pages is not allowed for security reasons.
                    <br>Please use the official application routes.
                    <% } else if (statusCode !=null && statusCode==404) { %>
                        <strong>Page Not Found.</strong><br>
                        The page you are looking for could not be found or has been moved.
                        <% } else if (statusCode !=null && statusCode==500) { %>
                            <strong>Internal Server Error.</strong><br>
                            An unexpected error occurred on the server.
                            <% } else { %>
                                An unexpected error occurred.
                                <% } %>
            </p>
            <div class="d-grid gap-2">
                <a href="${pageContext.request.contextPath}/controller/login" class="btn-home">
                    <i class="fas fa-sign-in-alt me-2"></i>Go to Login
                </a>
                <a href="${pageContext.request.contextPath}/controller/dashboard" class="btn-home"
                    style="background: #6c757d;">
                    <i class="fas fa-tachometer-alt me-2"></i>Go to Dashboard
                </a>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>