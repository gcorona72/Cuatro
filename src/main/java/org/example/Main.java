package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

// Asegúrate de que SupabaseServlet use jakarta.servlet.*
public class Main {
    public static void main(String[] args) throws Exception {
        // Crea servidor Jetty en el puerto 8080
        Server server = new Server(8081);


        // Configura el contexto
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Mapea el servlet en "/SupabaseServlet"
        // NOTA: SupabaseServlet debe extender jakarta.servlet.http.HttpServlet
        ServletHolder servletHolder = new ServletHolder(new SupabaseServlet());
        context.addServlet(servletHolder, "/SupabaseServlet");

        // (Opcional) Servir archivos estáticos (index.html) desde /src/main/resources/web/
        // context.setResourceBase("src/main/resources/web");
        // context.addServlet(DefaultServlet.class, "/");

        // Inicia Jetty
        server.start();
        System.out.println("Servidor Jetty corriendo en http://localhost:8080");
        server.join();
    }
}
