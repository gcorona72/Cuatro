package org.example;

import com.google.gson.Gson;
import org.example.Features.GuestService;
import org.example.Model.Guest;
import spark.Spark;

import java.util.List;

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
            List<Guest> guests = GuestService.getAllGuests();
            return new Gson().toJson(guests);
        });

        // POST: Crear un nuevo invitado
        post("/api/guests", (req, res) -> {
            res.type("application/json");
            Guest nuevo = new Gson().fromJson(req.body(), Guest.class);
            GuestService.createGuest(nuevo.getNombre(), nuevo.isAcompanante());
            return "{\"status\":\"ok\"}";
        });

        // PUT: Actualizar un invitado existente
        put("/api/guests/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params("id"));
            Guest invitadoActualizado = new Gson().fromJson(req.body(), Guest.class);
            GuestService.updateGuest(id, invitadoActualizado.getNombre(), invitadoActualizado.isAcompanante());
            return "{\"status\":\"ok\"}";
        });

        // DELETE: Eliminar invitado
        delete("/api/guests/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params("id"));
            GuestService.deleteGuest(id);
            return "{\"status\":\"ok\"}";
        });

        System.out.println("🚀 Servidor Spark levantado en http://localhost:4567");
    }
}
