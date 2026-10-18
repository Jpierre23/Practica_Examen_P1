package Negocio;

import javax.swing.*;
import java.util.Scanner;

public class AppStore {
    public final static int MIN_ROMPECABEZAS = 25;
    public final static int MIN_DEPORTES = 20;
    public final static int MIN_ACCION = 15;
    public final static int LIMITE_DEPORTE = 10;
    public final static int LIMITE_ACCION = 15;

    //descuentos
    public final static float DESCUENTO_1 = 0.20f;
    public final static float DESCUENTO_2 = 0.15f;
    public final static float PORCENTAJE_MIN = 0.25f;

    private Juego juego1;
    private Juego juego2;
    private Juego juego3;
    private Juego juego4;

    public AppStore() {
        juego1 = new Juego("Clash Royale", Juego.Categoria.ACCION, 250, 2.50f, 100, 0);
        juego2 = new Juego("FIFA", Juego.Categoria.DEPORTE, 10000, 75.0f, 150, 0);
        juego3 = new Juego("Minecraft", Juego.Categoria.ROMPECABEZAS, 160, 25.0f, 20, 3);
        juego4 = new Juego("Ajedrez", Juego.Categoria.ROMPECABEZAS, 100, 1000.0f, 15, 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AppStore appStore = new AppStore();
        int opc;
        String nombre;
        int cantidad;

        do {
            System.out.println("======== AppStore =======");
            System.out.println("1. Visualizar informacion del juego");
            System.out.println("2. Comprar Licencias de un juego");
            System.out.println("3. Vender Licencias de un juego");
            System.out.println("4. Consultar el juego mas vendido");
            System.out.println("5. Consultar los descuentos aplicados por volumen de compra");
            System.out.println("6. Compras sugeridas (política porcentaje)");
            System.out.println("7. Compras sugeridas (política categoría)");
            System.out.println("8. Salir");
            opc = sc.nextInt();

            switch (opc){
                case 1:
                    System.out.println("Ingrese el nombre del juego: ");
                    sc.nextLine();
                    nombre = sc.nextLine();
                    appStore.mostrarInformacionJuegos(nombre);
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Ingrese el nombre del juego: ");
                    nombre = sc.nextLine();
                    System.out.println("Ingrese la cantidad que desea comprar: ");
                    cantidad = sc.nextInt();
                    appStore.comprarLicenciasJuegos(nombre, cantidad);

                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("Ingrese el nombre del juego: ");
                    nombre = sc.nextLine();
                    System.out.println("Ingrese la cantidad que desea comprar: ");
                    cantidad = sc.nextInt();
                    appStore.venderLicenciasJuegos(nombre, cantidad);
                    break;
                case 4:
                    System.out.println("Juego mas vendido: " + appStore.darJuegoMasVendido());
                    break;
                case 5:
                    System.out.print("Licencias de rompecabezas: ");
                    int rompecabezas = sc.nextInt();
                    System.out.print("Licencias de deportes: ");
                    int deportes = sc.nextInt();
                    System.out.print("Licencias de acción: ");
                    int accion = sc.nextInt();

                    float descuento = appStore.calcularDescuento(rompecabezas, deportes, accion);

                    if (descuento == 0){
                        System.out.println("No aplica ningún descuento.");
                    }
                    else{
                        System.out.println("Descuento aplicado: " + (descuento * 100) + "%");
                    }
                    break;
                case 6:
                    appStore.metodo1();
                    break;
                case 7:
                    System.out.println("Compras por categoría: " + appStore.metodo2());
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }while (opc != 8);
    }

    public Juego buscarJuego(String nombre){
        Juego resultado = null;

        if (juego1.getNombre().equalsIgnoreCase(nombre)){
            resultado = juego1;
        }
        else if (juego2.getNombre().equalsIgnoreCase(nombre)) {
            resultado = juego2;
        }
        else if (juego3.getNombre().equalsIgnoreCase(nombre)) {
            resultado = juego3;
        }
        else if (juego4.getNombre().equalsIgnoreCase(nombre)) {
            resultado = juego4;
        }
        return resultado;
    }

    public void mostrarInformacionJuegos(String nombre){
        Juego juego = buscarJuego(nombre);

        if (juego != null){
            juego.mostrarInformacion();
        }
        else {
            System.out.println("El juego no existe");
        }
    }

    public void comprarLicenciasJuegos(String nombre, int cantidad){
        Juego juego = buscarJuego(nombre);

        if(juego == null){
            System.out.println("El juego no existe");
        }
        else if (cantidad <= 0){
            System.out.println("La cantidad debe ser mayor a 0");
        }
        else{
            juego.comprarLicencias(cantidad);
            System.out.println("Compra exitosa. Quedan: "
                    + juego.getCantidadLicencias() + " cantidad de licencias disponibles de " + juego.getNombre());
        }
    }

    public void venderLicenciasJuegos(String nombre, int cantidad){
        Juego juego = buscarJuego(nombre);

        if (juego == null) {
            System.out.println("El juego no existe.");
        }
        else if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
        }
        else {
            boolean vendido = juego.venderLicencias(cantidad);

            if (vendido) {
                System.out.println("Venta exitosa de " + juego.getNombre());
                System.out.println("Licencias disponibles: " + juego.getCantidadLicencias());
                System.out.println("Total vendidas: " + juego.getCantidadVendidas());
            }
        }
    }

    public String darJuegoMasVendido(){
        Juego mayor = juego1;

        if (juego2.getCantidadVendidas() > mayor.getCantidadVendidas()){
            mayor = juego2;
        }
        if (juego3.getCantidadVendidas() > mayor.getCantidadVendidas()){
            mayor = juego3;
        }
        if (juego4.getCantidadVendidas() > mayor.getCantidadVendidas()){
            mayor = juego4;
        }

        String respuesta = mayor.getNombre();

        if (mayor.getCantidadVendidas() == 0){
            respuesta = "NINGUNO";
        }

        return respuesta;
    }

    public String darJuegoMenosVendido(){
        Juego menor = juego1;

        if (juego2.getCantidadVendidas() < menor.getCantidadVendidas()){
            menor = juego2;
        }
        if (juego3.getCantidadVendidas() < menor.getCantidadVendidas()){
            menor = juego3;
        }
        if (juego4.getCantidadVendidas() < menor.getCantidadVendidas()){
            menor = juego4;
        }

        String respuesta = menor.getNombre();
        int base = juego1.getCantidadVendidas();

        if (juego2.getCantidadVendidas() == base
                && juego3.getCantidadVendidas() == base
                && juego4.getCantidadVendidas() == base){
            respuesta = "NINGUNO";
        }

        return respuesta;
    }

    public float calcularDescuento(int rompecabezas, int deportes, int accion){
        float descuento = 0;

        if (rompecabezas >= MIN_ROMPECABEZAS){
            descuento = DESCUENTO_1;
        }
        else if (deportes >= MIN_DEPORTES && accion >= MIN_ACCION){
            descuento = DESCUENTO_2;
        }

        return descuento;
    }

    public String darComprasPorPorcentaje(){
        String menosVendido = darJuegoMenosVendido();
        String respuesta = "";

        if (!menosVendido.equals("NINGUNO")){
            Juego menor = buscarJuego(menosVendido);
            float umbral = menor.getCantidadLicencias() * PORCENTAJE_MIN;

            if (juego1.getCantidadLicencias() < umbral){
                respuesta += juego1.getNombre() + " ";
            }
            if (juego2.getCantidadLicencias() < umbral){
                respuesta += juego2.getNombre() + " ";
            }
            if (juego3.getCantidadLicencias() < umbral){
                respuesta += juego3.getNombre() + " ";
            }
            if (juego4.getCantidadLicencias() < umbral){
                respuesta += juego4.getNombre() + " ";
            }
        }

        if (respuesta.equals("")){
            respuesta = "NINGUNO";
        }

        return respuesta.trim();
    }

    public void metodo1(){
        String compras = darComprasPorPorcentaje();

        if (compras.equals("NINGUNO")){
            System.out.println("No es necesario comprar licencias por la política porcentaje.");
        }
        else{
            System.out.println("Según la política porcentaje, se deben comprar licencias de: " + compras);
        }
    }

    public boolean seDebeComprarPorCategoria(String nombre) {
        Juego juego = buscarJuego(nombre);

        if (juego == null) {
            return false;
        }

        if (juego.getCategoria() == Juego.Categoria.DEPORTE
                && juego.getCantidadLicencias() < LIMITE_DEPORTE) {
            return true;
        }

        if (juego.getCategoria() == Juego.Categoria.ACCION
                && juego.getCantidadLicencias() < LIMITE_ACCION) {
            return true;
        }

        return false;
    }
    public String metodo2() {
        String respuesta = "";

        if (seDebeComprarPorCategoria(juego1.getNombre())) {
            respuesta += juego1.getNombre() + " ";
        }

        if (seDebeComprarPorCategoria(juego2.getNombre())) {
            respuesta += juego2.getNombre() + " ";
        }

        if (seDebeComprarPorCategoria(juego3.getNombre())) {
            respuesta += juego3.getNombre() + " ";
        }

        if (seDebeComprarPorCategoria(juego4.getNombre())) {
            respuesta += juego4.getNombre() + " ";
        }

        if (respuesta.equals("")) {
            respuesta = "NINGUNO";
        }

        return respuesta.trim();
    }
}
