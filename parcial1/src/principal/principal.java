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

        // VALIDAR CAPACIDAD DEL GARAGE
        System.out.print("Ingrese la capacidad del garage: ");
        int capacidad = scanner.nextInt();

        while (capacidad <= 0) {
            System.out.println("La capacidad del garage debe ser mayor a 0.");
            System.out.print("Ingrese nuevamente la capacidad: ");
            capacidad = scanner.nextInt();
        }

        Garage garage = new Garage(capacidad);

        int opcion = 0;

        while (opcion != 6) {

            System.out.println();
            System.out.println("===== SISTEMA DE GARAGE =====");
            System.out.println();
            System.out.println("1. Registrar ingreso");
            System.out.println();
            System.out.println("2. Registrar salida");
            System.out.println();
            System.out.println("3. Listar vehículos");
            System.out.println();
            System.out.println("4. Estado del garage");
            System.out.println();
            System.out.println("5. Reportes");
            System.out.println();
            System.out.println("6. Salir");
            System.out.println();

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {

                // ==========================================
                // REGISTRAR INGRESO
                // ==========================================
                case 1:

                    System.out.println();
                    System.out.println("===== REGISTRAR INGRESO =====");
                    System.out.println();
                    System.out.println("1. Moto");
                    System.out.println();
                    System.out.println("2. Auto");
                    System.out.println();
                    System.out.println("3. Camion");
                    System.out.println();

                    System.out.print("Seleccione el tipo de vehículo: ");
                    int tipo = scanner.nextInt();

                    scanner.nextLine();

                    Vehiculo vehiculo = null;

                    switch (tipo) {

                        case 1:
                            vehiculo = crearMoto(scanner);
                            break;

                        case 2:
                            vehiculo = crearAuto(scanner);
                            break;

                        case 3:
                            vehiculo = crearCamion(scanner);
                            break;

                        default:
                            System.out.println("Tipo de vehículo inválido.");
                            break;
                    }

                    if (vehiculo != null) {

                        try {

                            garage.ingresarVehiculo(vehiculo);

                            System.out.println();
                            System.out.println("Vehículo ingresado correctamente.");
                            System.out.println("Espacios ocupados: " 
                                    + vehiculo.calcularEspacios());
                            System.out.println("Costo estimado: $" 
                                    + vehiculo.calcularCosto());

                        } catch (GarageLlenoException e) {

                            System.out.println(e.getMessage());

                        } catch (PatenteDuplicadaException e) {

                            System.out.println(e.getMessage());

                        } catch (HorasInvalidasException e) {

                            System.out.println(e.getMessage());
                        }
                    }

                    break;

                // ==========================================
                // REGISTRAR SALIDA
                // ==========================================
                case 2:

                    System.out.println();
                    System.out.println("===== REGISTRAR SALIDA =====");

                    scanner.nextLine();

                    System.out.print("Ingrese la patente del vehículo: ");
                    String patenteSalida = scanner.nextLine();

                    while (patenteSalida.trim().isEmpty()) {

                        System.out.println("La patente no puede estar vacía.");
                        System.out.print("Ingrese la patente nuevamente: ");

                        patenteSalida = scanner.nextLine();
                    }

                    try {

                        Vehiculo vehiculoSalida =
                                garage.sacarVehiculo(patenteSalida);

                        System.out.println();
                        System.out.println("===== RESUMEN DE SALIDA =====");
                        System.out.println();

                        System.out.println("Patente: "
                                + vehiculoSalida.getPatente());

                        System.out.println("Marca: "
                                + vehiculoSalida.getMarca());

                        System.out.println("Modelo: "
                                + vehiculoSalida.getModelo());

                        System.out.println("Horas estimadas: "
                                + vehiculoSalida.getHorasEstimadas());

                        System.out.println("Espacios liberados: "
                                + vehiculoSalida.calcularEspacios());

                        System.out.println("Costo total estimado: $"
                                + vehiculoSalida.calcularCosto());

                        System.out.println();
                        System.out.println("Vehículo retirado correctamente.");

                    } catch (VehiculoNoEncontradoException e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                // ==========================================
                // LISTAR VEHICULOS
                // ==========================================
                case 3:

                    garage.listarVehiculos();

                    break;

                // ==========================================
                // ESTADO DEL GARAGE
                // ==========================================
                case 4:

                    garage.mostrarEstado();

                    break;

                // ==========================================
                // REPORTES
                // ==========================================
                case 5:

                    garage.generarReportes();

                    break;

                // ==========================================
                // SALIR
                // ==========================================
                case 6:

                    System.out.println();
                    System.out.println("Programa finalizado.");

                    break;

                // ==========================================
                // OPCION INVALIDA
                // ==========================================
                default:

                    System.out.println("Opción inválida.");

                    break;
            }
        }

        scanner.close();
    }


    // ======================================================
    // CREAR MOTO
    // ======================================================

    public static Moto crearMoto(Scanner scanner) {

        System.out.println();

        System.out.print("Ingrese la patente: ");
        String patente = scanner.nextLine();

        while (patente.trim().isEmpty()) {

            System.out.println("La patente no puede estar vacía.");
            System.out.print("Ingrese la patente nuevamente: ");

            patente = scanner.nextLine();
        }


        System.out.print("Ingrese la marca: ");
        String marca = scanner.nextLine();

        while (marca.trim().isEmpty()) {

            System.out.println("La marca no puede estar vacía.");
            System.out.print("Ingrese la marca nuevamente: ");

            marca = scanner.nextLine();
        }


        System.out.print("Ingrese el modelo: ");
        String modelo = scanner.nextLine();

        while (modelo.trim().isEmpty()) {

            System.out.println("El modelo no puede estar vacío.");
            System.out.print("Ingrese el modelo nuevamente: ");

            modelo = scanner.nextLine();
        }


        System.out.print("Ingrese las horas estimadas: ");
        int horas = scanner.nextInt();

        return new Moto(patente, marca, modelo, horas);
    }


    // ======================================================
    // CREAR AUTO
    // ======================================================

    public static Auto crearAuto(Scanner scanner) {

        System.out.println();

        System.out.print("Ingrese la patente: ");
        String patente = scanner.nextLine();

        while (patente.trim().isEmpty()) {

            System.out.println("La patente no puede estar vacía.");
            System.out.print("Ingrese la patente nuevamente: ");

            patente = scanner.nextLine();
        }


        System.out.print("Ingrese la marca: ");
        String marca = scanner.nextLine();

        while (marca.trim().isEmpty()) {

            System.out.println("La marca no puede estar vacía.");
            System.out.print("Ingrese la marca nuevamente: ");

            marca = scanner.nextLine();
        }


        System.out.print("Ingrese el modelo: ");
        String modelo = scanner.nextLine();

        while (modelo.trim().isEmpty()) {

            System.out.println("El modelo no puede estar vacío.");
            System.out.print("Ingrese el modelo nuevamente: ");

            modelo = scanner.nextLine();
        }


        System.out.print("Ingrese las horas estimadas: ");
        int horas = scanner.nextInt();

        return new Auto(patente, marca, modelo, horas);
    }


    // ======================================================
    // CREAR CAMION
    // ======================================================

    public static Camion crearCamion(Scanner scanner) {

        System.out.println();

        System.out.print("Ingrese la patente: ");
        String patente = scanner.nextLine();

        while (patente.trim().isEmpty()) {

            System.out.println("La patente no puede estar vacía.");
            System.out.print("Ingrese la patente nuevamente: ");

            patente = scanner.nextLine();
        }


        System.out.print("Ingrese la marca: ");
        String marca = scanner.nextLine();

        while (marca.trim().isEmpty()) {

            System.out.println("La marca no puede estar vacía.");
            System.out.print("Ingrese la marca nuevamente: ");

            marca = scanner.nextLine();
        }


        System.out.print("Ingrese el modelo: ");
        String modelo = scanner.nextLine();

        while (modelo.trim().isEmpty()) {

            System.out.println("El modelo no puede estar vacío.");
            System.out.print("Ingrese el modelo nuevamente: ");

            modelo = scanner.nextLine();
        }


        System.out.print("Ingrese las horas estimadas: ");
        int horas = scanner.nextInt();

        return new Camion(patente, marca, modelo, horas);
    }
}
