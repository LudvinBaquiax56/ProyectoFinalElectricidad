package org.example;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        menu();
    }

    public static void menu(){
        int opcion;
        do {
            System.out.println("==== Menú ====");
            System.out.println("1. Calculadora Electrónica");
            System.out.println("2. Calculadora de Impedencia");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    calculadora();
                    break;
                case 2:
                    calculadora2();
                    break;
                case 3:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 3);
        scanner.close();
    }

    public static void calculadora() {
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Calculadora Electrónica - Impedancia en Circuitos de CA");
        System.out.println("-------------------------------------------------------");

        System.out.print("Ingrese la resistencia (R en ohmios): ");
        double resistencia = scanner.nextDouble();

        System.out.print("Ingrese la inductancia (L en henrios): ");
        double inductancia = scanner.nextDouble();

        System.out.print("Ingrese la capacitancia (C en faradios): ");
        double capacitancia = scanner.nextDouble();

        System.out.print("Ingrese la frecuencia (f en hertzios): ");
        double frecuencia = scanner.nextDouble();

        // Cálculo de impedancias
        double impedanciaR = resistencia;
        double impedanciaL = 2 * Math.PI * frecuencia * inductancia;
        double impedanciaC = 1 / (2 * Math.PI * frecuencia * capacitancia);

        System.out.println("\nImpedancia del resistor (Zr): " + df.format(impedanciaR) + " ohmios");
        System.out.println("Impedancia del inductor (Zl): " + df.format(impedanciaL) + " ohmios");
        System.out.println("Impedancia del condensador (Zc): " + df.format(impedanciaC) + " ohmios");

        // Cálculo de la impedancia total en serie y paralelo
        double impedanciaTotalSerie = impedanciaR + impedanciaL + impedanciaC;
        impedanciaTotalSerie = Math.sqrt(impedanciaR * impedanciaR +
                (impedanciaL-impedanciaC)*(impedanciaL-impedanciaC));
        double impedanciaTotalParalelo = 1 / (1 / impedanciaR + 1 / impedanciaL + 1 / impedanciaC);

        System.out.println("\nImpedancia total en serie: " + df.format(impedanciaTotalSerie) + " ohmios");
        System.out.println("Impedancia total en paralelo: " + df.format(impedanciaTotalParalelo) + " ohmios");

        // Cálculo del ángulo de fase (para la impedancia en serie)
        double anguloFase = Math.toDegrees(Math.atan(impedanciaL - impedanciaC / (impedanciaR)));

        System.out.println("Ángulo de fase (θ) en serie: " + df.format(anguloFase) + " grados");

    }

    public static void calculadora2(){

        System.out.print("Ingrese la frecuencia central (fc) en Hertz: ");
        double fc = scanner.nextDouble();

        System.out.print("Ingrese el valor del capacitor (C) en faradios: ");
        double C = scanner.nextDouble();

        double inductor = calcularInductor(fc, C);
        System.out.println("El valor del inductor necesario es: " + inductor + " henrios.");

    }

    private static double calcularInductor(double fc, double C) {
        double factor = 1 / (4 * Math.pow(Math.PI, 2) * Math.pow(fc, 2) * C);
        return factor;
    }
}