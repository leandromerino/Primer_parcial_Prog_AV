/*MERINO Y ROGES*/

package principal;

import java.util.Scanner;

import clases.Garage;
import clases.Auto;
import clases.Camion;
import clases.Moto;
import clases.Vehiculo;

import excepciones.GarageLlenoException;
import excepciones.HorasInvalidasException;
import excepciones.PatenteDuplicadaException;
import excepciones.VehiculoNoEncontradoException;

public class principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== SISTEMA DE GARAGE =====");

        int capacidad = leerEnteroPositivo(
                scanner,
                "Ingrese la capacidad del garage: "
        );

        Garage garage = new Garage(capacidad);

        int opcion = 0;

        while (opcion != 6) {

            System.out.println();
            System.out.println("===== SISTEMA DE GARAGE =====");
            System.out.println("1. Registrar ingreso");
            System.out.println("2. Registrar salida");
            System.out.println("3. Listar vehículos");
            System.out.println("4. Estado del garage");
            System.out.println("5. Reportes");
            System.out.println("6. Salir");

            opcion = leerEntero(scanner, "Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    registrarIngreso(scanner, garage);
                    break;

                case 2:
                    registrarSalida(scanner, garage);
                    break;

                case 3:
                    garage.listarVehiculos();
                    break;

                case 4:
                    garage.mostrarEstado();
                    break;

                case 5:
                    garage.generarReportes();
                    break;

                case 6:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción inválida. Ingrese una opción entre 1 y 6.");
                    break;
            }
        }

        scanner.close();
    }


    public static void registrarIngreso(Scanner scanner, Garage garage) {

        System.out.println();
        System.out.println("===== REGISTRAR INGRESO =====");

        System.out.println("1. Moto");
        System.out.println("2. Auto");
        System.out.println("3. Camion");

        int tipo;

        do {

            tipo = leerEntero(
                    scanner,
                    "Seleccione el tipo de vehículo: "
            );

            if (tipo < 1 || tipo > 3) {
                System.out.println("Tipo de vehículo inválido.");
            }

        } while (tipo < 1 || tipo > 3);


        String patente = leerTexto(
                scanner,
                "Ingrese la patente: "
        );

        String marca = leerTexto(
                scanner,
                "Ingrese la marca: "
        );

        String modelo = leerTexto(
                scanner,
                "Ingrese el modelo: "
        );

        int horas = leerEnteroPositivo(
                scanner,
                "Ingrese las horas estimadas: "
        );


        Vehiculo vehiculo = null;

        switch (tipo) {

            case 1:
                vehiculo = new Moto(
                        patente,
                        marca,
                        modelo,
                        horas
                );
                break;

            case 2:
                vehiculo = new Auto(
                        patente,
                        marca,
                        modelo,
                        horas
                );
                break;

            case 3:
                vehiculo = new Camion(
                        patente,
                        marca,
                        modelo,
                        horas
                );
                break;
        }


        try {

            garage.ingresarVehiculo(vehiculo);

            System.out.println();
            System.out.println("Vehículo ingresado correctamente.");
            System.out.println("Patente: " + vehiculo.getPatente());
            System.out.println("Marca: " + vehiculo.getMarca());
            System.out.println("Modelo: " + vehiculo.getModelo());
            System.out.println("Horas estimadas: " + vehiculo.getHorasEstimadas());
            System.out.println("Espacios ocupados: " + vehiculo.calcularEspacios());
            System.out.println("Costo estimado: $" + vehiculo.calcularCosto());

        } catch (GarageLlenoException e) {

            System.out.println(e.getMessage());

        } catch (PatenteDuplicadaException e) {

            System.out.println(e.getMessage());

        } catch (HorasInvalidasException e) {

            System.out.println(e.getMessage());
        }
    }


    public static void registrarSalida(Scanner scanner, Garage garage) {

        System.out.println();
        System.out.println("===== REGISTRAR SALIDA =====");

        garage.listarPatentes();

        if (garage.getVehiculos().isEmpty()) {
            return;
        }

        String patenteSalida = leerTexto(
                scanner,
                "Ingrese la patente del vehículo: "
        );

        try {

            Vehiculo vehiculoSalida =
                    garage.sacarVehiculo(patenteSalida);

            System.out.println();
            System.out.println("===== RESUMEN DE SALIDA =====");
            System.out.println("Patente: " + vehiculoSalida.getPatente());
            System.out.println("Marca: " + vehiculoSalida.getMarca());
            System.out.println("Modelo: " + vehiculoSalida.getModelo());
            System.out.println("Horas estimadas: " + vehiculoSalida.getHorasEstimadas());
            System.out.println("Espacios liberados: " + vehiculoSalida.calcularEspacios());
            System.out.println("Costo total estimado: $" + vehiculoSalida.calcularCosto());
            System.out.println("Vehículo retirado correctamente.");

        } catch (VehiculoNoEncontradoException e) {

            System.out.println(e.getMessage());
        }
    }


    public static int leerEntero(Scanner scanner, String mensaje) {

        while (true) {

            System.out.print(mensaje);

            String entrada = scanner.nextLine();

            try {

                return Integer.parseInt(entrada);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Dato inválido. Debe ingresar un número entero."
                );
            }
        }
    }


    public static int leerEnteroPositivo(
            Scanner scanner,
            String mensaje) {

        int numero;

        do {

            numero = leerEntero(scanner, mensaje);

            if (numero <= 0) {

                System.out.println(
                        "El valor debe ser mayor a 0."
                );
            }

        } while (numero <= 0);

        return numero;
    }


    public static String leerTexto(
            Scanner scanner,
            String mensaje) {

        String texto;

        do {

            System.out.print(mensaje);

            texto = scanner.nextLine().trim();

            if (texto.isEmpty()) {

                System.out.println(
                        "El campo no puede estar vacío."
                );
            }

        } while (texto.isEmpty());

        return texto;
    }
}