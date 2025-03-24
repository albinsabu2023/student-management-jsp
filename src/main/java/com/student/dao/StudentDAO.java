package com.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.student.model.Student;
import com.student.util.DatabaseUtil;

public class StudentDAO {
	
    // SQL Queries
    private static final String INSERT_STUDENT_SQL = 
        "INSERT INTO students (roll_number, first_name, last_name, email, dob, gender, address, phone_number) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID = 
        "SELECT * FROM students WHERE id = ?";
    private static final String SELECT_ALL_STUDENTS = 
        "SELECT * FROM students";
    private static final String DELETE_STUDENT_SQL = 
        "DELETE FROM students WHERE id = ?";
    private static final String UPDATE_STUDENT_SQL = 
        "UPDATE students SET roll_number = ?, first_name = ?, last_name = ?, email = ?, " +
        "dob = ?, gender = ?, address = ?, phone_number = ? WHERE id = ?";
    private static final String SELECT_STUDENT_BY_ROLL = 
        "SELECT * FROM students WHERE roll_number = ?";
    
    // Create database table if it doesn't exist
    public void createStudentTable() {
        String createTableSQL = 
            "CREATE TABLE IF NOT EXISTS students (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "roll_number VARCHAR(20) UNIQUE NOT NULL," +
            "first_name VARCHAR(50) NOT NULL," +
            "last_name VARCHAR(50) NOT NULL," +
            "email VARCHAR(100) UNIQUE NOT NULL," +
            "dob DATE," +
            "gender VARCHAR(10)," +
            "address VARCHAR(255)," +
            "phone_number VARCHAR(20)" +
            ")";
        
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            DatabaseUtil.printSQLException(e);
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
    }
    
    // Insert Student
    public boolean insertStudent(Student student) {
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL);
            preparedStatement.setString(1, student.getRollNumber());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setDate(5, student.getDob() != null ? new java.sql.Date(student.getDob().getTime()) : null);
            preparedStatement.setString(6, student.getGender());
            preparedStatement.setString(7, student.getAddress());
            preparedStatement.setString(8, student.getPhoneNumber());
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            DatabaseUtil.printSQLException(e);
            return false;
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
    }
    
    // Select Student by ID
    public Student selectStudent(int id) {
        Student student = null;
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	System.out.println(rs.getString("roll_number"));
                String rollNumber = rs.getString("roll_number");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                student = new Student(id, rollNumber, firstName, lastName, email, dob, gender, address, phoneNumber);
            }
        } catch (SQLException e) {
            DatabaseUtil.printSQLException(e);
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return student;
    }
    
    // Select Student by Roll Number
    public Student selectStudentByRoll(String rollNumber) {
        Student student = null;
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ROLL);
            preparedStatement.setString(1, rollNumber);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                student = new Student(id, rollNumber, firstName, lastName, email, dob, gender, address, phoneNumber);
            }
        } catch (SQLException e) {
            DatabaseUtil.printSQLException(e);
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return student;
    }
    
    // Select All Students
    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String rollNumber = rs.getString("roll_number");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                students.add(new Student(id, rollNumber, firstName, lastName, email, dob, gender, address, phoneNumber));
            }
        } catch (SQLException e) {
            DatabaseUtil.printSQLException(e);
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return students;
    }
    
    // Update Student
    public boolean updateStudent(Student student) {
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_SQL);
            preparedStatement.setString(1, student.getRollNumber());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setDate(5, student.getDob() != null ? new java.sql.Date(student.getDob().getTime()) : null);
            preparedStatement.setString(6, student.getGender());
            preparedStatement.setString(7, student.getAddress());
            preparedStatement.setString(8, student.getPhoneNumber());
            preparedStatement.setInt(9, student.getId());
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            DatabaseUtil.printSQLException(e);
            return false;
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
    }
    
    // Delete Student
    public boolean deleteStudent(int id) {
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_SQL);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            DatabaseUtil.printSQLException(e);
            return false;
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
    }
}