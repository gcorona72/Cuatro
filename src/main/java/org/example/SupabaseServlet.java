package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

@WebServlet(name = "SupabaseServlet", urlPatterns = {"/SupabaseServlet"})
public class SupabaseServlet extends HttpServlet {

    private SupabaseService service;

    @Override
    public void init() throws ServletException {
        super.init();
        // Iniciamos la instancia del servicio que contiene las llamadas a Supabase
        service = new SupabaseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Por defecto, listar invitados en JSON
        listGuests(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // action=add / update / delete
        String action = request.getParameter("action");
        if (action == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("{\"error\":\"missing action parameter\"}");
            return;
        }

        switch (action) {
            case "add":
                addGuest(request, response);
                break;
            case "update":
                updateGuest(request, response);
                break;
            case "delete":
                deleteGuest(request, response);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("{\"error\":\"unknown action\"}");
        }
    }

    // 1) LISTAR INVITADOS (GET)
    private void listGuests(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            List<Guest> guests = service.getAllGuests();
            JSONArray jsonArr = new JSONArray();
            for (Guest g : guests) {
                JSONObject obj = new JSONObject();
                obj.put("id", g.getId());
                obj.put("name", g.getName());
                obj.put("email", g.getEmail());
                obj.put("phone", g.getPhone());
                obj.put("rsvp_status", g.getRsvpStatus());
                obj.put("plus_one", g.isPlusOne());
                obj.put("notes", g.getNotes());
                jsonArr.put(obj);
            }
            response.getWriter().print(jsonArr.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // 2) AGREGAR INVITADO (POST action=add)
    private void addGuest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        String body = readBody(request);
        try {
            JSONObject json = new JSONObject(body);
            // Campos
            String name = json.optString("name");
            String email = json.optString("email");
            String phone = json.optString("phone");
            String rsvp = json.optString("rsvp_status");
            boolean plusOne = json.optBoolean("plus_one", false);
            String notes = json.optString("notes", "");

            // ID = 0 (Supabase lo generará)
            Guest newGuest = new Guest(0, name, email, phone, rsvp, plusOne, notes);
            service.addGuest(newGuest);

            response.getWriter().println("{\"success\":true}");
        } catch (JSONException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("{\"error\":\"" + e.getMessage() + "\"}");
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // 3) ACTUALIZAR INVITADO (POST action=update)
    private void updateGuest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        String body = readBody(request);
        try {
            JSONObject json = new JSONObject(body);
            int id = json.getInt("id");
            String name = json.optString("name");
            String email = json.optString("email");
            String phone = json.optString("phone");
            String rsvp = json.optString("rsvp_status");
            boolean plusOne = json.optBoolean("plus_one", false);
            String notes = json.optString("notes", "");

            Guest guest = new Guest(id, name, email, phone, rsvp, plusOne, notes);
            service.updateGuest(guest);

            response.getWriter().println("{\"success\":true}");
        } catch (JSONException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("{\"error\":\"" + e.getMessage() + "\"}");
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // 4) ELIMINAR INVITADO (POST action=delete)
    private void deleteGuest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        String body = readBody(request);
        try {
            JSONObject json = new JSONObject(body);
            int id = json.getInt("id");
            service.deleteGuest(id);

            response.getWriter().println("{\"success\":true}");
        } catch (JSONException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("{\"error\":\"" + e.getMessage() + "\"}");
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // Utilidad para leer el body de la request
    private String readBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
