package Negocio;

public class Juego {
    public enum Categoria{
        ACCION,
        ROMPECABEZAS,
        DEPORTE
    }

    private String nombre;
    private Categoria categoria;
    private int tamanio;
    private float precio;
    private int cantidadLicencias;
    private int cantidadVendidas;

    public Juego(String nombre, Categoria categoria, int tamanio, float precio,
                 int cantidadLicencias, int cantidadVendidas) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.tamanio = tamanio;
        this.precio = precio;
        this.cantidadLicencias = cantidadLicencias;
        this.cantidadVendidas = cantidadVendidas;
    }

    public void setTamanio(int tamanio) {
        if(tamanio < 0){
            tamanio = 0;
        }
        this.tamanio = tamanio;
    }

    public void setPrecio(float precio) {
        if (precio < 0){
            precio = 0;
        }
        this.precio = precio;
    }

    public void setCantidadLicencias(int cantidadLicencias) {
        if(cantidadLicencias < 0){
            cantidadLicencias = 0;
        }
        this.cantidadLicencias = cantidadLicencias;
    }

    public void setCantidadVendidas(int cantidadVendidas) {
        if (cantidadVendidas < 0) {
            cantidadVendidas = 0;
        }
        this.cantidadVendidas = cantidadVendidas;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getTamanio() {
        return tamanio;
    }

    public float getPrecio() {
        return precio;
    }

    public int getCantidadLicencias() {
        return cantidadLicencias;
    }

    public int getCantidadVendidas() {
        return cantidadVendidas;
    }

    public boolean venderLicencias(int cantidad){
        boolean respuesta = false;

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
        }
        else if (cantidad > cantidadLicencias) {
            System.out.println("No hay esas licencias disponibles.");
        }
        else {
            cantidadLicencias -= cantidad;
            cantidadVendidas += cantidad;
            respuesta = true;
        }
        return respuesta;
    }

    public void comprarLicencias(int cantidad){
        cantidadLicencias += cantidad;
    }

    public void mostrarInformacion(){
        System.out.println("Nombre -> " + getNombre());
        System.out.println("Categoria -> "+ getCategoria());
        System.out.println("Tamanio -> " + getTamanio());
        System.out.println("Precio -> " + getPrecio());
        System.out.println("Licencias disponibles -> "+getCantidadLicencias());
        System.out.println("Licencias Vendidas -> " + getCantidadVendidas());
    }
}
