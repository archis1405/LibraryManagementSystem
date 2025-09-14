Library Management System
A comprehensive Java-based Library Management System demonstrating Object-Oriented Programming principles, SOLID principles, and design patterns.

Features
Core Features

Book Management: Add, remove, update, and search books by title, author, or ISBN
Patron Management: Register library members and track their borrowing history
Lending System: Comprehensive checkout and return functionality with due dates
Inventory Tracking: Real-time monitoring of book availability and status

Advanced Features

Multi-branch Support: Manage multiple library branches with book transfers
Reservation System: Allow patrons to reserve books that are currently checked out
Smart Notifications: Automatic notifications when reserved books become available
Recommendation Engine: Personalized book recommendations based on borrowing history
Comprehensive Logging: Track all library operations with timestamps

Architecture
The system follows a layered architecture with clear separation of concerns:

Presentation : LibraryManagementSystem (main class)

Service : Book Service, Patron Service, Lending Service, Library Service, Recommendation Service, Reservation Service 

Repository : Book Repository, Patron Repository, Branch Repository, Reservation Repository

Model : Book, Patron, Branch, Reservation


Design Patterns
1. Factory Pattern

Location: com.library.factory.LibraryFactory
Purpose: Creates and configures service instances with proper dependencies
Benefits: Centralizes object creation, reduces coupling

javaLibraryService library = LibraryFactory.createLibraryService();
2. Observer Pattern

Location: com.library.observer.*
Purpose: Implements notification system for book availability
Components:

Observer interface
Subject interface
NotificationService implementation



3. Strategy Pattern

Location: com.library.strategy.*
Purpose: Provides different search algorithms for books
Strategies:

TitleSearchStrategy
AuthorSearchStrategy
ISBNSearchStrategy



4. Repository Pattern

Location: com.library.repository.*
Purpose: Abstracts data access logic
Benefits: Testable, maintainable, swappable data sources

SOLID Principles
Single Responsibility Principle (SRP)

Each class has one primary responsibility
BookService handles book operations only
PatronService manages patron-related functionality
LendingService focuses on checkout/return logic

Open/Closed Principle (OCP)

System is extensible through interfaces
New search strategies can be added without modifying existing code
New notification types can be implemented via Observer pattern

Liskov Substitution Principle (LSP)

All strategy implementations are interchangeable
Observer implementations can be substituted without breaking functionality

Interface Segregation Principle (ISP)

Focused interfaces: Observer, Subject, SearchStrategy
Clients depend only on methods they use

Dependency Inversion Principle (DIP)

High-level modules depend on abstractions
Services depend on repository interfaces, not concrete implementations