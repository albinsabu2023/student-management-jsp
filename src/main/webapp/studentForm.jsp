<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Management System - Student Form</title>
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
            <h2>
                <c:if test="${student != null}">Edit Student</c:if>
                <c:if test="${student == null}">Add New Student</c:if>
            </h2>
            
            <div class="form-container">
                <c:if test="${student != null}">
                    <form action="${pageContext.request.contextPath}/students/update" method="post">
                    <input type="hidden" name="id" value="<c:out value='${student.id}' />">
                </c:if>
                <c:if test="${student == null}">
                    <form action="${pageContext.request.contextPath}/students/insert" method="post">
                </c:if>
                
                    <div class="form-group">
                        <label for="rollNumber">Roll Number</label>
                        <input type="text" id="rollNumber" name="rollNumber" value="<c:out value='${student.rollNumber}' />" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <input type="text" id="firstName" name="firstName" value="<c:out value='${student.firstName}' />" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" id="lastName" name="lastName" value="<c:out value='${student.lastName}' />" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" value="<c:out value='${student.email}' />" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="dob">Date of Birth</label>
                        <input type="date" id="dob" name="dob" value="<fmt:formatDate value='${student.dob}' pattern='yyyy-MM-dd' />">
                    </div>
                    
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select id="gender" name="gender">
                            <option value="">Select</option>
                            <option value="Male" <c:if test="${student.gender == 'Male'}">selected</c:if>>Male</option>
                            <option value="Female" <c:if test="${student.gender == 'Female'}">selected</c:if>>Female</option>
                            <option value="Other" <c:if test="${student.gender == 'Other'}">selected</c:if>>Other</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="address">Address</label>
                        <textarea id="address" name="address" rows="3"><c:out value='${student.address}' /></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="phoneNumber">Phone Number</label>
                        <input type="text" id="phoneNumber" name="phoneNumber" value="<c:out value='${student.phoneNumber}' />">
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Save</button>
                        <a href="${pageContext.request.contextPath}/students/list" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </main>
        
        <footer>
            <p>&copy; <%= new java.util.Date().getYear() + 1900 %> Student Management System</p>
        </footer>
    </div>
</body>
</html>