package clases;

import interfaces.Calculable;

public abstract class Vehiculo implements Calculable {

    protected String patente;
    protected String marca;
    protected String modelo;
    protected int horasEstimadas;

    public Vehiculo() {
    }

    public Vehiculo(String patente, String marca, String modelo, int horasEstimadas) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.horasEstimadas = horasEstimadas;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(int horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    @Override
    public abstract double calcularCosto();

    public abstract int calcularEspacios();

    public void mostrarDatos() {
        System.out.println("Patente: " + patente);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Horas estimadas: " + horasEstimadas);
        System.out.println("Espacios ocupados: " + calcularEspacios());
        System.out.println("Costo estimado: $" + calcularCosto());
    }
}