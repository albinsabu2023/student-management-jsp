<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Management System - Student List</title>
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
            <h2>Student List</h2>
            
            <div style="margin: 20px 0;">
                <a href="${pageContext.request.contextPath}/students/new" class="btn btn-primary">Add New Student</a>
            </div>
            
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Roll Number</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Date of Birth</th>
                            <th>Gender</th>
                            <th>Phone</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${listStudent}">
                            <tr>
                                <td><c:out value="${student.id}" /></td>
                                <td><c:out value="${student.rollNumber}" /></td>
                                <td><c:out value="${student.firstName} ${student.lastName}" /></td>
                                <td><c:out value="${student.email}" /></td>
                                <td>
                                    <fmt:formatDate value="${student.dob}" pattern="yyyy-MM-dd" />
                                </td>
                                <td><c:out value="${student.gender}" /></td>
                                <td><c:out value="${student.phoneNumber}" /></td>
                                <td class="action-buttons">
                                    <a href="${pageContext.request.contextPath}/students/edit?id=<c:out value='${student.id}' />" class="btn btn-primary">Edit</a>
                                    <a href="${pageContext.request.contextPath}/students/delete?id=<c:out value='${student.id}' />" 
                                       class="btn btn-danger" 
                                       onclick="return confirm('Are you sure you want to delete this student?')">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <c:if test="${empty listStudent}">
                <p style="text-align: center; margin-top: 20px;">No students found. Click on "Add New Student" to create one.</p>
            </c:if>
        </main>
        
        <footer>
            <p>&copy; <%= new java.util.Date().getYear() + 1900 %> Student Management System</p>
        </footer>
    </div>
</body>
</html>