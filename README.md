# 🏄‍♂️ Sistema de Gestión - Escuela de Surf

Este proyecto es una aplicación de consola en **Java** diseñada para administrar integralmente una escuela de surf. Permite gestionar alumnos, instructores, equipamiento, reservas de clases y alquileres, utilizando persistencia de datos a través de archivos **JSON**.

## 🚀 Funcionalidades Principales

El sistema permite realizar las siguientes operaciones a través de un menú interactivo:

* **Gestión de Usuarios:** Alta y consulta de Alumnos, Instructores y Clientes.
* **Clases de Surf:**
    * Creación de clases (Grupales o Particulares).
    * Asignación de instructores.
    * Control de cupos (validación de disponibilidad).
* **Reservas:** Inscripción de alumnos a clases con validación de fechas y pagos.
* **Alquileres:** Gestión de alquiler de equipos (Tablas, Trajes de Neoprene, etc.) con cálculo de costos por día.
* **Sistema de Pagos:**
    * Registro de pagos por Efectivo, Tarjeta o Transferencia.
    * Control de morosidad (verificación de pagos vencidos).
* **Persistencia:** Guardado y carga automática de datos (Repositorios) en formato JSON (`escuelaDeSurf.json`).

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java.
* **Persistencia:** JSON (org.json).
* **IDE Recomendado:** IntelliJ IDEA.
* **Librerías:**
    * `libreriaJSON.jar` (Incluida en el proyecto para el manejo de objetos JSON).

## 📋 Estructura del Proyecto

El proyecto está organizado en paquetes para mantener una arquitectura limpia:

* `Clases`: Contiene la lógica de negocio (Alumno, Instructor, Reserva, Pago, etc.).
* `Enumeradores`: Define tipos estáticos (NivelDeSurf, TipoClase, MetodoPago, etc.).
* `ExcepcionesPersonalizadas`: Manejo de errores específicos (CupoLleno, PagoPendiente, etc.).
* `Interfaces`: Define comportamientos comunes (`InterfazJson`).
* `Utiles`: Herramientas para lectura y escritura de archivos (`JsonUtiles`).

## ⚙️ Instalación y Ejecución

1.  Clona este repositorio:
    ```bash
    git clone https://github.com/Ciro836/TP-FINAL---Escuela-de-Surf.git
    ```
2.  Abre el proyecto en tu IDE (IntelliJ IDEA recomendado).
3.  Asegúrate de agregar la librería `libreriaJSON.jar` al **Classpath** del proyecto (Project Structure -> Libraries).
4.  Ejecuta el archivo `Main.java`.

## 📄 Ejemplo de Uso

Al iniciar, el sistema cargará la base de datos existente. Podrás navegar por opciones como:

> 1. Agregar Alumno
> 3. Agregar Clase de Surf
> 7. Agregar Alquiler
> 19. Grabar repositorios a JSON

---
*Trabajo Práctico Final - Programación II*