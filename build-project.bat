@echo off
echo =========================================================
echo OCEAN VIEW RESORT - Hotel Reservation System
echo =========================================================
echo.
echo Building Complete Production-Level Java Web Application
echo.

cd /d "%~dp0"

echo [1/5] Cleaning previous builds...
call mvn clean

echo.
echo [2/5] Compiling with dependencies...
call mvn compile

echo.
echo [3/5] Running tests...
call mvn test

echo.
echo [4/5] Packaging WAR file...
call mvn package

echo.
echo [5/5] Installing to local repository...
call mvn install

echo.
echo =========================================================
echo BUILD COMPLETED SUCCESSFULLY!
echo =========================================================
echo.
echo WAR file location: target\OceanViewResort.war
echo.
echo Next steps:
echo 1. Start XAMPP MySQL (port 3306)
echo 2. Start Apache Tomcat 10.1.52
echo 3. Deploy WAR file to Tomcat webapps folder
echo 4. Access: http://localhost:8080/OceanViewResort/
echo.
echo Default Login: admin / Admin@123
echo.
echo =========================================================

pause
