# Aplicación Java con Spark y Supabase

Este proyecto es una aplicación Java que utiliza el framework **Spark** para crear un servidor web que interactúa con una base de datos **Supabase**. Proporciona una API RESTful para gestionar invitados en una base de datos PostgreSQL alojada en Supabase.

---

## 🚀 Características principales

- **Servidor Spark**: Levanta un servidor web ligero en Java.
- **Conexión a Supabase**: Se conecta a una base de datos PostgreSQL alojada en Supabase.
- **Endpoints RESTful**:
  - `GET /api/guests`: Obtiene todos los invitados.
  - `POST /api/guests`: Crea un nuevo invitado.
  - `PUT /api/guests/:id`: Actualiza un invitado existente.
  - `DELETE /api/guests/:id`: Elimina un invitado.
- **Apertura automática del navegador**: Al iniciar la aplicación, se abre automáticamente la URL del servidor en tu navegador predeterminado.

---

## 📦 Requisitos

- **Java JDK 8 o superior**.
- **Dependencias Maven**:
  - Spark (framework web).
  - PostgreSQL JDBC (controlador para PostgreSQL).
  - Gson (manejo de JSON).
- **Conexión a una base de datos Supabase**.

---

## 🔧 Configuración

### Puerto del servidor

El servidor Spark está configurado para escuchar en el puerto **4567**. Esto se define en la clase `SparkServer` con la siguiente línea de código:

## 🚀 Despliegue en Railway

Este proyecto está configurado para ser desplegado en **Railway** utilizando un archivo `railway.json` y un `Dockerfile`. Sigue estos pasos para desplegar tu aplicación:

### Configuración del `railway.json`

El archivo `railway.json` define cómo se debe construir y desplegar la aplicación en Railway. Aquí está la configuración utilizada:

```json
{
  "$schema": "https://railway.app/railway.schema.json",
  "build": {
    "builder": "DOCKERFILE",
    "dockerfilePath": "Dockerfile"
  },
  "deploy": {
    "runtime": "V2",
    "numReplicas": 1,
    "sleepApplication": false,
    "multiRegionConfig": {
      "us-west2": {
        "numReplicas": 1
      }
    },
    "restartPolicyType": "ON_FAILURE",
    "restartPolicyMaxRetries": 10
  }
}
Pasos para desplegar en Railway
Conectar el repositorio a Railway:

Ve a Railway.

Crea un nuevo proyecto y selecciona "Deploy from GitHub repo".

Conecta tu cuenta de GitHub y selecciona este repositorio.

Configuración automática:

Railway detectará automáticamente el archivo railway.json y usará el Dockerfile para construir la aplicación.

Variables de entorno:

Asegúrate de agregar las variables de entorno necesarias (como credenciales de base de datos) en la pestaña "Variables" del proyecto en Railway.

Despliegue:

Railway comenzará a desplegar la aplicación automáticamente.

Una vez completado, se proporcionará una URL para acceder a la aplicación.

Monitoreo:

Puedes monitorear el estado de tu aplicación, ver logs y gestionar recursos desde el panel de control de Railway.


```java
Spark.port(4567);º
Gson: Biblioteca para serializar y deserializar objetos Java a JSON y viceversa.

Notas Adicionales
Asegúrate de que el puerto 4567 esté disponible en tu máquina antes de ejecutar la aplicación.

Si necesitas cambiar el puerto, modifica la línea Spark.port(4567); en la clase SparkServer y asegúrate de actualizar cualquier referencia a http://localhost:4567 en tu código.

