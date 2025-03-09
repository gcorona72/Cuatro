package org.example;

import com.google.gson.Gson;
import org.example.Features.*;
import spark.Spark;

import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class SparkServer {

    public static void start() {
        // Configura el puerto
        Spark.port(4567);

        // Servir estáticos desde /public (carpeta en src/main/resources/public)
        staticFiles.location("/public");

        // GET: Leer todos los invitados
        get("/api/guests", (req, res) -> {
            res.type("application/json");

            List<Map<String, Object>> invitados = ListarInvitados.getAll();

            return new Gson().toJson(invitados);
        });


        // POST: Crear un nuevo invitado
        post("/api/guests", (req, res) -> {
            res.type("application/json");

            Map<String, Object> requestData = new Gson().fromJson(req.body(), Map.class);
            String nombre = (String) requestData.get("nombre");
            boolean acompanante = Boolean.parseBoolean(requestData.get("acompanante").toString());

            if (nombre == null || nombre.trim().isEmpty()) {
                return "{\"status\":\"error\", \"message\": \"El nombre no puede estar vacío.\"}";
            }

            AñadirInvitado.add(nombre, acompanante);
            return "{\"status\":\"ok\"}";
        });

        // PUT: Actualizar un invitado existente
        put("/api/guests/:id", (req, res) -> {
            res.type("application/json");

            int id = Integer.parseInt(req.params("id"));

            // Leer datos del JSON
            Map<String, Object> requestData = new Gson().fromJson(req.body(), Map.class);
            String newNombre = (String) requestData.get("nombre");
            boolean newAcompanante = Boolean.parseBoolean(requestData.get("acompanante").toString());

            if (newNombre == null || newNombre.trim().isEmpty()) {
                return "{\"status\":\"error\", \"message\": \"El nombre no puede estar vacío.\"}";
            }

            ActualizarInvitado.update(id, newNombre, newAcompanante);
            return "{\"status\":\"ok\"}";
        });

        // DELETE: Eliminar invitado
        delete("/api/guests/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params("id"));
            EliminarInvitado.delete(id);
            return "{\"status\":\"ok\"}";
        });

        System.out.println("🚀 Servidor Spark levantado en http://localhost:4567");
    }
}
