# 🏄‍♂️ Management System - Surf School

This project is a console-based application built in **Java**, designed to comprehensively manage a surf school. It handles students, instructors, equipment, class reservations, and rentals, utilizing **JSON** files for data persistence.

> **⚠️ Note:** The source code of this project (variable names, comments, class names, and console interface) is written in **Spanish**.

## 🚀 Key Features

The system allows users to perform the following operations through an interactive menu:

* **User Management:** Registration and lookup of Students, Instructors, and Clients.
* **Surf Classes:**
    * Creation of classes (Group or Private).
    * Instructor assignment.
    * Capacity control (availability validation).
* **Reservations:** Student enrollment in classes with date and payment validation.
* **Rentals:** Equipment rental management (Surfboards, Wetsuits, etc.) with daily cost calculation.
* **Payment System:**
    * Payment registration via Cash, Card, or Bank Transfer.
    * Delinquency control (verification of overdue payments).
* **Persistence:** Automatic data saving and loading (Repositories) in JSON format (`escuelaDeSurf.json`).

## 🛠️ Technologies Used

* **Language:** Java.
* **Persistence:** JSON (org.json).
* **Recommended IDE:** IntelliJ IDEA.
* **Libraries:**
    * `libreriaJSON.jar` (Included in the project for handling JSON objects).

## 📋 Project Structure

The project is organized into packages to maintain a clean architecture:

* `Clases`: Contains the business logic (Student, Instructor, Reservation, Payment, etc.).
* `Enumeradores`: Defines static types (SurfLevel, ClassType, PaymentMethod, etc.).
* `ExcepcionesPersonalizadas`: Handles specific errors (FullCapacity, PendingPayment, etc.).
* `Interfaces`: Defines common behaviors (`InterfazJson`).
* `Utiles`: Tools for reading and writing files (`JsonUtiles`).

## ⚙️ Installation and Execution

1.  Clone this repository:
    ```bash
    git clone [https://github.com/Ciro836/TP-FINAL---Escuela-de-Surf.git](https://github.com/Ciro836/TP-FINAL---Escuela-de-Surf.git)
    ```
2.  Open the project in your IDE (IntelliJ IDEA is recommended).
3.  Make sure to add the `libreriaJSON.jar` library to the project's **Classpath** (Project Structure -> Libraries).
4.  Run the `Main.java` file.

## 📄 Usage Example

Upon starting, the system will load the existing database. You will be able to navigate through options such as:

> 1. Add Student (Agregar Alumno)
> 3. Add Surf Class (Agregar Clase de Surf)
> 7. Add Rental (Agregar Alquiler)
> 19. Save repositories to JSON (Grabar repositorios a JSON)

---
*Final Project - Programming II*
