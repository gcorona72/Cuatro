package org.example;

import org.example.Features.AñadirInvitado;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcion;


        do {
            System.out.println("\n \uD83D\uDCCB Menú - Gestión de invitados");
            System.out.println("1\uFE0F⃣  Agregar invitado");
            System.out.println("2\uFE0F⃣  Salir");
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
                    System.out.println("👋 Saliendo del programa...");
                    break;
                default:
                    System.out.println("⚠️ Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 2);

        scanner.close();

        }
    }

