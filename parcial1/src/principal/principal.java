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

        System.out.print("Ingrese la capacidad del garage: ");
        int capacidad = scanner.nextInt();

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
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {

            case 1:

                System.out.println("===== REGISTRAR INGRESO =====");

                System.out.println("1. Moto");
                System.out.println("2. Auto");
                System.out.println("3. Camion");
                System.out.print("Seleccione el tipo de vehículo: ");

                int tipo = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Ingrese la patente: ");
                String patente = scanner.nextLine();

                System.out.print("Ingrese la marca: ");
                String marca = scanner.nextLine();

                System.out.print("Ingrese el modelo: ");
                String modelo = scanner.nextLine();

                System.out.print("Ingrese las horas estimadas: ");
                int horas = scanner.nextInt();

                Vehiculo vehiculo = null;

                switch (tipo) {

                    case 1:
                        vehiculo = new Moto(patente, marca, modelo, horas);
                        break;

                    case 2:
                        vehiculo = new Auto(patente, marca, modelo, horas);
                        break;

                    case 3:
                        vehiculo = new Camion(patente, marca, modelo, horas);
                        break;

                    default:
                        System.out.println("Tipo de vehículo inválido.");
                        break;
                }

                if (vehiculo != null) {

                    try {

                        garage.ingresarVehiculo(vehiculo);

                        System.out.println("Vehículo ingresado correctamente.");
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

                break;

            case 2:

                System.out.println("===== REGISTRAR SALIDA =====");

                scanner.nextLine();

                System.out.print("Ingrese la patente del vehículo: ");
                String patenteSalida = scanner.nextLine();

                try {

                    Vehiculo vehiculoSalida = garage.sacarVehiculo(patenteSalida);

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
                    System.out.println("Opción inválida.");
                    break;
            }
        }

        scanner.close();
    }
}


