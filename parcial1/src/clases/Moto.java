package clases;

public class Moto extends Vehiculo {

    public Moto() {
    }

    public Moto(String patente, String marca, String modelo, int horasEstimadas) {
        super(patente, marca, modelo, horasEstimadas);
    }

    @Override
    public double calcularCosto() {
        return horasEstimadas * 700;
    }

    @Override
    public int calcularEspacios() {
        return 1;
    }
}