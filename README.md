# Gestión de Invitados

Este es un proyecto simple de gestión de invitados desarrollado en Java. El programa permite realizar operaciones básicas de CRUD (Crear, Leer, Actualizar, Eliminar) sobre una base de datos MySQL. Además, se incluyen pruebas unitarias para garantizar el correcto funcionamiento de las funcionalidades.

## Requisitos

- Java JDK 8 o superior
- MySQL Server
- MySQL Connector/J (Driver JDBC para MySQL)
- JUnit 5 (para ejecutar las pruebas unitarias)

## Configuración

1. **Base de Datos**:
   - Asegúrate de tener MySQL instalado y en ejecución.
   - Ejecuta el archivo `DatabaseSetup.java` para crear la base de datos y la tabla necesarias.
     ```bash
     java -cp .;mysql-connector-java-x.x.xx.jar org.example.Database.DatabaseSetup
     ```
   - Asegúrate de que el usuario y la contraseña en `DatabaseSetup.java` coincidan con los de tu servidor MySQL.

2. **Dependencias**:
   - Descarga el driver JDBC para MySQL desde [aquí](https://dev.mysql.com/downloads/connector/j/).
   - Asegúrate de incluir el archivo `.jar` en el classpath al compilar y ejecutar el proyecto.
   - Si estás utilizando un IDE como IntelliJ IDEA o Eclipse, asegúrate de agregar JUnit 5 a las dependencias del proyecto.

## Estructura del Proyecto

- **Main.java**: Punto de entrada del programa. Contiene el menú principal para gestionar los invitados.
- **Features/**: Contiene las clases que implementan las funcionalidades del CRUD.
  - **AñadirInvitado.java**: Permite agregar un nuevo invitado.
  - **EliminarInvitado.java**: Permite eliminar un invitado existente.
  - **BuscarInvitado.java**: Permite buscar un invitado por su ID.
  - **ActualizarInvitado.java**: Permite actualizar el nombre de un invitado.
  - **ListarInvitados.java**: Lista todos los invitados registrados.
- **Database/**: Contiene las clases relacionadas con la conexión y configuración de la base de datos.
  - **DatabaseConnection.java**: Gestiona la conexión a la base de datos.
  - **DatabaseSetup.java**: Crea la base de datos y la tabla necesarias.
- **Tests/**: Contiene las pruebas unitarias para cada funcionalidad.
  - **AñadirInvitadoTest.java**: Pruebas para la funcionalidad de agregar invitados.
  - **EliminarInvitadoTest.java**: Pruebas para la funcionalidad de eliminar invitados.
  - **BuscarInvitadoTest.java**: Pruebas para la funcionalidad de buscar invitados.
  - **ActualizarInvitadoTest.java**: Pruebas para la funcionalidad de actualizar invitados.
  - **ListarInvitadoTest.java**: Pruebas para la funcionalidad de listar invitados.
  - **DatabaseConnectionTest.java**: Pruebas para la conexión a la base de datos.

## Ejecución

1. Compila el proyecto:
   ```bash
   javac -cp .;mysql-connector-java-x.x.xx.jar org/example/*.java org/example/Features/*.java org/example/Database/*.java
