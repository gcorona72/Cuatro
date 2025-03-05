package org.example;

import org.example.Features.AñadirInvitado;
import org.example.Features.BuscarInvitado;
import org.example.Features.EliminarInvitado;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcion;


        do {
            System.out.println("\n \uD83D\uDCCB Menú - Gestión de invitados");
            System.out.println("1\uFE0F⃣  Agregar invitado");
            System.out.println("2️⃣ Eliminar Invitado");
            System.out.println("3️⃣ Buscar invitado");
            System.out.println("4\uFE0F⃣  Salir");
            System.out.print("Elige una opción: ");


            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del invitado: ");
                    String nombre = scanner.next();
                    AñadirInvitado.agregarInvitado(nombre);
                    break;
                case 2:
                    System.out.print("Ingrese el ID del invitado a eliminar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    EliminarInvitado.eliminarInvitado(id);
                    break;
                case 3:
                    System.out.print("Ingrese el ID del invitado a buscar: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine();
                    BuscarInvitado.buscarInvitado(idBuscar);
                    break;
                case 4:
                    System.out.println("👋 Saliendo del programa...");
                    break;
                default:
                    System.out.println("⚠️ Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();

        }
    }

