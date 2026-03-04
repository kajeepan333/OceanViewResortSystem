<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Ocean View Resort - Online Room Reservation</title>
            <meta name="description"
                content="Ocean View Resort - Your perfect seaside getaway. Book your room online today.">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
            <style>
                * {
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;
                }

                body {
                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                    overflow-x: hidden;
                }

                .hero-section {
                    background: linear-gradient(135deg, rgba(102, 126, 234, 0.85) 0%, rgba(118, 75, 162, 0.85) 100%),
                        url('https://images.unsplash.com/photo-1566073771259-6a8506099945?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80') center/cover;
                    min-height: 100vh;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    text-align: center;
                    color: white;
                }

                .hero-content h1 {
                    font-size: 3.5rem;
                    font-weight: 700;
                    margin-bottom: 20px;
                    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
                }

                .hero-content p {
                    font-size: 1.3rem;
                    margin-bottom: 40px;
                    opacity: 0.9;
                }

                .btn-hero {
                    background: white;
                    color: #764ba2;
                    border: none;
                    border-radius: 50px;
                    padding: 15px 40px;
                    font-size: 1.1rem;
                    font-weight: 600;
                    transition: all 0.3s;
                    text-decoration: none;
                    display: inline-block;
                    margin: 10px;
                }

                .btn-hero:hover {
                    transform: translateY(-3px);
                    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
                    color: #764ba2;
                }

                .btn-hero-outline {
                    background: transparent;
                    color: white;
                    border: 2px solid white;
                    border-radius: 50px;
                    padding: 13px 38px;
                    font-size: 1.1rem;
                    font-weight: 600;
                    transition: all 0.3s;
                    text-decoration: none;
                    display: inline-block;
                    margin: 10px;
                }

                .btn-hero-outline:hover {
                    background: white;
                    color: #764ba2;
                    transform: translateY(-3px);
                }

                .features-section {
                    padding: 80px 0;
                    background: #f8f9fa;
                }

                .feature-card {
                    text-align: center;
                    padding: 40px 20px;
                    border-radius: 15px;
                    background: white;
                    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
                    transition: transform 0.3s;
                    height: 100%;
                }

                .feature-card:hover {
                    transform: translateY(-5px);
                }

                .feature-card i {
                    font-size: 3rem;
                    color: #764ba2;
                    margin-bottom: 20px;
                }

                .feature-card h3 {
                    font-size: 1.3rem;
                    margin-bottom: 15px;
                    color: #333;
                }

                .feature-card p {
                    color: #6c757d;
                }

                .section-title {
                    text-align: center;
                    margin-bottom: 50px;
                }

                .section-title h2 {
                    color: #764ba2;
                    font-size: 2.5rem;
                    font-weight: 700;
                }

                .section-title p {
                    color: #6c757d;
                    font-size: 1.1rem;
                }

                footer {
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    color: white;
                    padding: 30px 0;
                    text-align: center;
                }
            </style>
        </head>

        <body>
            <!-- Hero Section -->
            <section class="hero-section">
                <div class="hero-content">
                    <i class="fas fa-umbrella-beach fa-4x mb-4"></i>
                    <h1>Ocean View Resort</h1>
                    <p>Experience luxury at its finest with breathtaking ocean views<br>
                        Book your dream stay with us today</p>
                    <div>
                        <a href="${pageContext.request.contextPath}/controller/login" class="btn-hero">
                            <i class="fas fa-sign-in-alt me-2"></i>Login
                        </a>
                        <a href="${pageContext.request.contextPath}/controller/signup" class="btn-hero-outline">
                            <i class="fas fa-user-plus me-2"></i>Sign Up
                        </a>
                    </div>
                </div>
            </section>

            <!-- Features Section -->
            <section class="features-section">
                <div class="container">
                    <div class="section-title">
                        <h2>Why Choose Us</h2>
                        <p>Premium facilities and world-class service await you</p>
                    </div>
                    <div class="row g-4">
                        <div class="col-md-4">
                            <div class="feature-card">
                                <i class="fas fa-bed"></i>
                                <h3>Luxury Rooms</h3>
                                <p>Choose from Standard, Deluxe, and Suite rooms with A/C or Non-A/C options for the
                                    perfect stay.</p>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="feature-card">
                                <i class="fas fa-utensils"></i>
                                <h3>Dining Options</h3>
                                <p>Flexible meal plans from breakfast-only to full-board. Enjoy fresh, locally sourced
                                    cuisine.</p>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="feature-card">
                                <i class="fas fa-water"></i>
                                <h3>Ocean Views</h3>
                                <p>Wake up to stunning views of the Indian Ocean from your private balcony every
                                    morning.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Footer -->
            <footer>
                <div class="container">
                    <p>&copy; 2024 Ocean View Resort. All Rights Reserved.</p>
                    <p>123 Beach Road, Galle, Sri Lanka | Tel: +94 91 123 4567</p>
                </div>
            </footer>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>