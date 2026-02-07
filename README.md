
Spring MVC Blog Management System

This is a comprehensive web application for blog management, built using the Spring MVC framework and Hibernate ORM. 
The project features a public-facing blog for readers and a secure administration panel for content management.

Key Features:
Full CRUD Functionality: Complete management of authors, categories, tags, and blog posts.
Advanced Query Logic: Custom HQL implementations for "Most Viewed Posts," "Related Content," and robust search functionality.
Spring Security: Admin panel protection with BCrypt password hashing and role-based access control.
Relational Database: Complex entity mapping including Many-to-Many (Tags) and One-to-Many (Categories) relationships.
User Experience: Dynamic delete modals, server-side form validation, and author image rendering.

Tech Stack:
Backend: Java 17+, Spring MVC, Hibernate 5.
Security: Spring Security with BCrypt.
Database: MySQL.
Frontend: JSP, JSTL, Bootstrap, AdminLTE Template.

Project Structure:
cubes.main.dao: Data Access Object implementations using Hibernate SessionFactory.
cubes.main.entity: Database entity mappings.
cubes.main.rest: REST controllers for API functionality.
SecurityConfig.java: Java-based configuration for application security.
