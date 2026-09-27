package clases;

import java.util.ArrayList;

import excepciones.GarageLlenoException;
import excepciones.HorasInvalidasException;
import excepciones.PatenteDuplicadaException;
import excepciones.VehiculoNoEncontradoException;

public class Garage {

    private int capacidadMaxima;
    private ArrayList<Vehiculo> vehiculos;

    public Garage() {
        vehiculos = new ArrayList<Vehiculo>();
    }

    public Garage(int capacidadMaxima) {

        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad del garage debe ser mayor a 0."
            );
        }

        this.capacidadMaxima = capacidadMaxima;
        vehiculos = new ArrayList<Vehiculo>();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {

        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad del garage debe ser mayor a 0."
            );
        }

        this.capacidadMaxima = capacidadMaxima;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }


    public int calcularEspaciosOcupados() {

        int espaciosOcupados = 0;

        for (Vehiculo vehiculo : vehiculos) {
            espaciosOcupados += vehiculo.calcularEspacios();
        }

        return espaciosOcupados;
    }


    public int calcularEspaciosDisponibles() {

        return capacidadMaxima - calcularEspaciosOcupados();
    }


    public void ingresarVehiculo(Vehiculo vehiculo)
            throws GarageLlenoException,
                   PatenteDuplicadaException,
                   HorasInvalidasException {

        if (vehiculo == null) {
            throw new IllegalArgumentException(
                    "El vehículo no puede ser nulo."
            );
        }

        if (vehiculo.getHorasEstimadas() <= 0) {
            throw new HorasInvalidasException(
                    "Las horas estimadas deben ser mayores a 0."
            );
        }

        for (Vehiculo v : vehiculos) {

            if (v.getPatente().equalsIgnoreCase(
                    vehiculo.getPatente())) {

                throw new PatenteDuplicadaException(
                        "La patente ya se encuentra registrada."
                );
            }
        }

        if (vehiculo.calcularEspacios()
                > calcularEspaciosDisponibles()) {

            throw new GarageLlenoException(
                    "No hay espacio suficiente en el garage."
            );
        }

        vehiculos.add(vehiculo);
    }


    public Vehiculo buscarPorPatente(String patente)
            throws VehiculoNoEncontradoException {

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.getPatente()
                    .equalsIgnoreCase(patente)) {

                return vehiculo;
            }
        }

        throw new VehiculoNoEncontradoException(
                "No se encontró ningún vehículo con la patente: "
                        + patente
        );
    }


    public Vehiculo sacarVehiculo(String patente)
            throws VehiculoNoEncontradoException {

        Vehiculo vehiculo = buscarPorPatente(patente);

        vehiculos.remove(vehiculo);

        return vehiculo;
    }


    public void listarVehiculos() {

        System.out.println();
        System.out.println("===== VEHÍCULOS ESTACIONADOS =====");

        if (vehiculos.isEmpty()) {

            System.out.println(
                    "No hay vehículos estacionados."
            );

            return;
        }

        for (Vehiculo vehiculo : vehiculos) {

            System.out.println("----------------------------");

            vehiculo.mostrarDatos();
        }
    }


    public void mostrarEstado() {

        System.out.println();
        System.out.println("===== ESTADO DEL GARAGE =====");

        System.out.println(
                "Capacidad total: "
                        + capacidadMaxima
        );

        System.out.println(
                "Espacios ocupados: "
                        + calcularEspaciosOcupados()
        );

        System.out.println(
                "Espacios disponibles: "
                        + calcularEspaciosDisponibles()
        );
    }


    public void generarReportes() {

        int cantidadMotos = 0;
        int cantidadAutos = 0;
        int cantidadCamiones = 0;

        double recaudacionTotal = 0;

        for (Vehiculo vehiculo : vehiculos) {

            recaudacionTotal += vehiculo.calcularCosto();

            if (vehiculo instanceof Moto) {
                cantidadMotos++;
            }

            if (vehiculo instanceof Auto) {
                cantidadAutos++;
            }

            if (vehiculo instanceof Camion) {
                cantidadCamiones++;
            }
        }

        System.out.println();
        System.out.println("===== REPORTES DEL GARAGE =====");

        System.out.println(
                "Cantidad total de vehículos: "
                        + vehiculos.size()
        );

        System.out.println(
                "Cantidad de motos: "
                        + cantidadMotos
        );

        System.out.println(
                "Cantidad de autos: "
                        + cantidadAutos
        );

        System.out.println(
                "Cantidad de camiones: "
                        + cantidadCamiones
        );

        System.out.println(
                "Espacios ocupados: "
                        + calcularEspaciosOcupados()
        );

        System.out.println(
                "Espacios disponibles: "
                        + calcularEspaciosDisponibles()
        );

        System.out.println(
                "Recaudación total estimada: $"
                        + recaudacionTotal
        );
    }
}