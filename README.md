README
Este proyecto es una aplicación Java que utiliza el framework Spark para crear un servidor web que interactúa con una base de datos Supabase. A continuación, se detallan los aspectos clave del proyecto, incluyendo el puerto que se debe utilizar.

Requisitos
Java JDK 8 o superior.

Dependencias Maven (Spark, PostgreSQL JDBC, Gson).

Conexión a una base de datos Supabase.

Configuración del Puerto
El servidor Spark está configurado para escuchar en el puerto 4567. Esto se define en la clase SparkServer con la siguiente línea de código:

java
Copy
Spark.port(4567);
Por lo tanto, cuando ejecutes la aplicación, el servidor estará disponible en:

Copy
http://localhost:4567
Ejecución del Proyecto
Clona el repositorio:

bash
Copy
git clone <URL_DEL_REPOSITORIO>
cd <DIRECTORIO_DEL_PROYECTO>
Configura la conexión a Supabase:
Asegúrate de que las credenciales de la base de datos en la clase SupabaseConnection sean correctas.

Compila y ejecuta el proyecto:
Si estás utilizando Maven, puedes compilar y ejecutar el proyecto con los siguientes comandos:

bash
Copy
mvn clean install
mvn exec:java -Dexec.mainClass="org.example.Main"
Accede al servidor:
Una vez que el servidor esté en funcionamiento, abre tu navegador y visita:

Copy
http://localhost:4567
Endpoints Disponibles
GET /api/guests: Obtiene todos los invitados.

POST /api/guests: Crea un nuevo invitado.

PUT /api/guests/:id: Actualiza un invitado existente.

DELETE /api/guests/:id: Elimina un invitado.

Estructura del Proyecto
Main: Punto de entrada de la aplicación. Inicia el servidor Spark y verifica la conexión a Supabase.

SparkServer: Configura y maneja las rutas del servidor Spark.

SupabaseConnection: Maneja la conexión a la base de datos Supabase.

Features: Contiene las clases que realizan operaciones CRUD en la base de datos.

Dependencias
Spark: Framework web ligero para Java.

PostgreSQL JDBC: Controlador para conectarse a bases de datos PostgreSQL.

Gson: Biblioteca para serializar y deserializar objetos Java a JSON y viceversa.

Notas Adicionales
Asegúrate de que el puerto 4567 esté disponible en tu máquina antes de ejecutar la aplicación.

Si necesitas cambiar el puerto, modifica la línea Spark.port(4567); en la clase SparkServer y asegúrate de actualizar cualquier referencia a http://localhost:4567 en tu código.
