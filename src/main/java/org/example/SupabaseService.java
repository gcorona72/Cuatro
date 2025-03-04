package org.example;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SupabaseService {
    // Credenciales y tabla
    private static final String SUPABASE_URL = "https://ndapktzfypmgnlqhtxcy.supabase.co";
    // API key anon que me has proporcionado
    private static final String SUPABASE_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im5kYXBrdHpmeXBtZ25scWh0eGN5Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDEwOTczNjYsImV4cCI6MjA1NjY3MzM2Nn0.8fJgmOpKZiWQdDVx7Y-e6NvKPw8vVpUiLT1w4qb0HE8";
    private static final String SUPABASE_TABLE = "guests";

    private final OkHttpClient client = new OkHttpClient();

    // Obtener todos los invitados
    public List<Guest> getAllGuests() throws IOException {
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/" + SUPABASE_TABLE + "?select=*")
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Authorization", "Bearer " + SUPABASE_API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("[getAllGuests] Response code: " + response.code());
            String responseData = response.body().string();
            System.out.println("[getAllGuests] Response body: " + responseData);

            if (!response.isSuccessful()) {
                throw new IOException("Error en getAllGuests: " + response.code() + " - " + responseData);
            }

            JSONArray jsonArray = new JSONArray(responseData);
            List<Guest> guests = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                guests.add(new Guest(
                        obj.getInt("id"),
                        obj.getString("name"),
                        obj.getString("email"),
                        obj.getString("phone"),
                        obj.getString("rsvp_status"),
                        obj.getBoolean("plus_one"),
                        obj.optString("notes", "")
                ));
            }
            return guests;
        }
    }

    // Agregar un nuevo invitado
    public void addGuest(Guest guest) throws IOException {
        JSONObject json = new JSONObject();
        json.put("name", guest.getName());
        json.put("email", guest.getEmail());
        json.put("phone", guest.getPhone());
        json.put("rsvp_status", guest.getRsvpStatus());
        json.put("plus_one", guest.isPlusOne());
        json.put("notes", guest.getNotes());

        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/" + SUPABASE_TABLE)
                .post(body)
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Authorization", "Bearer " + SUPABASE_API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("[addGuest] Response code: " + response.code());
            String respBody = response.body().string();
            System.out.println("[addGuest] Response body: " + respBody);

            if (!response.isSuccessful()) {
                throw new IOException("Error en addGuest: " + response.code() + " - " + respBody);
            }
        }
    }

    // Actualizar un invitado
    public void updateGuest(Guest guest) throws IOException {
        JSONObject json = new JSONObject();
        json.put("name", guest.getName());
        json.put("email", guest.getEmail());
        json.put("phone", guest.getPhone());
        json.put("rsvp_status", guest.getRsvpStatus());
        json.put("plus_one", guest.isPlusOne());
        json.put("notes", guest.getNotes());

        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/" + SUPABASE_TABLE + "?id=eq." + guest.getId())
                .patch(body)
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Authorization", "Bearer " + SUPABASE_API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("[updateGuest] Response code: " + response.code());
            String respBody = response.body().string();
            System.out.println("[updateGuest] Response body: " + respBody);

            if (!response.isSuccessful()) {
                throw new IOException("Error en updateGuest: " + response.code() + " - " + respBody);
            }
        }
    }

    // Eliminar un invitado por ID
    public void deleteGuest(int id) throws IOException {
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/" + SUPABASE_TABLE + "?id=eq." + id)
                .delete()
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Authorization", "Bearer " + SUPABASE_API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("[deleteGuest] Response code: " + response.code());
            String respBody = response.body().string();
            System.out.println("[deleteGuest] Response body: " + respBody);

            if (!response.isSuccessful()) {
                throw new IOException("Error en deleteGuest: " + response.code() + " - " + respBody);
            }
        }
    }
}
