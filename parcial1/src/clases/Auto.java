package clases;

public class Auto extends Vehiculo {

    public Auto() {
    }

    public Auto(String patente, String marca, String modelo, int horasEstimadas) {
        super(patente, marca, modelo, horasEstimadas);
    }

    @Override
    public double calcularCosto() {
        return horasEstimadas * 1000;
    }

    @Override
    public int calcularEspacios() {
        return 2;
    }
}