package org.example;

import com.google.gson.Gson;
import org.example.Features.GuestService;
import org.example.Model.Guest;

import java.util.List;

import static spark.Spark.*;

public class SparkServer {

    public static void start() {
        // 1. Configurar el puerto (por defecto es 4567)
        port(4567);

        // 2. Servir archivos estáticos (HTML, CSS, JS) desde /public
        //    (ubicado en src/main/resources/public)
        staticFiles.location("/public");

        // 3. Endpoint GET para listar invitados
        get("/api/guests", (request, response) -> {
            response.type("application/json");
            List<Guest> guests = GuestService.getAllGuests();
            // Convertir lista a JSON con Gson
            return new Gson().toJson(guests);
        });

        // (Opcional) Endpoint POST para crear un invitado
        post("/api/guests", (request, response) -> {
            response.type("application/json");
            // El body vendrá en JSON, ejemplo: {"nombre":"Pepe","acompanante":true}
            // Lo parseamos con Gson
            Guest nuevo = new Gson().fromJson(request.body(), Guest.class);

            // Llamamos a GuestService para insertarlo
            GuestService.createGuest(nuevo.getNombre(), nuevo.isAcompanante());

            // Podríamos retornar un JSON de éxito
            return "{\"status\":\"ok\"}";
        });

        // (Opcional) Endpoint PUT para modificar un invitado
        // (Opcional) Endpoint DELETE para eliminar un invitado

        System.out.println("🚀 Servidor Spark levantado en http://localhost:4567");
    }

    public static void main(String[] args) {
        start();
    }
}