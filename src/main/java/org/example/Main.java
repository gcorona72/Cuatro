package org.example;

import org.example.Database.SupabaseConnection;
import org.example.Features.GuestService;
import org.example.Model.Guest;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Probamos la conexión antes de ejecutar el programa
        Connection conn = SupabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ Conexión exitosa a Supabase!");
        } else {
            System.out.println("❌ Error al conectar con la base de datos.");
            return; // Si la conexión falla, salimos del programa
        }

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nMenú - Gestión de Invitados (Supabase)");
            System.out.println("1. Agregar Invitado");
            System.out.println("2. Ver Todos");
            System.out.println("3. Ver por ID");
            System.out.println("4. Modificar");
            System.out.println("5. Eliminar");
            System.out.println("6. Salir");
            System.out.print("Elige opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("¿Acompañante? (true/false): ");
                    boolean acompanante = sc.nextBoolean();
                    sc.nextLine();

                    GuestService.createGuest(nombre, acompanante);
                    break;
                case 2:
                    List<Guest> allGuests = GuestService.getAllGuests();
                    System.out.println("\nLista de Invitados:");
                    for (Guest g : allGuests) {
                        System.out.println("#" + g.getId() + " - " + g.getNombre() + " (Acompañante: " + g.isAcompanante() + ")");
                    }
                    break;
                case 3:
                    System.out.print("ID a consultar: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine();
                    Guest encontrado = GuestService.getGuestById(idBuscar);
                    if (encontrado != null) {
                        System.out.println("Encontrado: " + encontrado.getNombre() + " (Acompañante: " + encontrado.isAcompanante() + ")");
                    } else {
                        System.out.println("No se encontró el invitado.");
                    }
                    break;
                case 4:
                    System.out.print("ID a modificar: ");
                    int idMod = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String newName = sc.nextLine();
                    System.out.print("¿Nuevo acompañante? (true/false): ");
                    boolean newAcomp = sc.nextBoolean();
                    sc.nextLine();

                    GuestService.updateGuest(idMod, newName, newAcomp);
                    break;
                case 5:
                    System.out.print("ID a eliminar: ");
                    int idDel = sc.nextInt();
                    sc.nextLine();
                    GuestService.deleteGuest(idDel);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 6);

        sc.close();

        // Cierra la conexión después de usarla
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("✅ Conexión cerrada correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
