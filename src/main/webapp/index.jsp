<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Student Management System</h1>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/students/list">Students</a></li>
                    <li><a href="${pageContext.request.contextPath}/login?action=logout">Logout</a></li>
                </ul>
            </nav>
        </header>
        
        <main>
            <h2>Welcome to Student Management System</h2>
            <p>This system allows you to manage student records. You can:</p>
            
            <ul style="margin: 20px 0; padding-left: 20px;">
                <li>View all student records</li>
                <li>Add new students</li>
                <li>Update existing student information</li>
                <li>Delete student records</li>
            </ul>
            
            <div style="margin-top: 30px;">
                <a href="${pageContext.request.contextPath}/students/list" class="btn btn-primary">View All Students</a>
                <a href="${pageContext.request.contextPath}/students/new" class="btn btn-secondary" style="margin-left: 10px;">Add New Student</a>
            </div>
        </main>
        
        <footer>
            <p>&copy; <%= new java.util.Date().getYear() + 1900 %> Student Management System</p>
        </footer>
    </div>
</body>
</html>