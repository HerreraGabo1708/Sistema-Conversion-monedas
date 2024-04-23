import java.util.Scanner;
import java.util.InputMismatchException;

public class InterfazConsola {
    private Logica logica;
    private Scanner scanner;

    public InterfazConsola() {
        this.logica = new Logica(); // Instancia de la clase Logica
        this.scanner = new Scanner(System.in); // Escáner para capturar entradas del usuario
    }

    public void start(String apiUrl) {
        try {
            // Intentar obtener tasas de cambio de la API
            logica.fetchExchangeRates(apiUrl);

            boolean running = true;

            while (running) {
                // Mostrar el menú al usuario
                System.out.println("\nMenú de Conversión de Monedas:");
                System.out.println("1. Convertir monedas");
                System.out.println("2. Salir");

                System.out.print("Elige una opción: ");

                int choice;

                try {
                    choice = scanner.nextInt(); // Capturar la opción del usuario

                    switch (choice) {
                        case 1:
                            convertCurrency(); // Llamar al método de conversión
                            break;
                        case 2:
                            running = false; // Salir del bucle y terminar el programa
                            System.out.println("Gracias por usar el conversor de monedas.");
                            break;
                        default:
                            System.out.println("Opción no válida. Inténtalo de nuevo.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Por favor, ingresa un número.");
                    scanner.next(); // Limpiar la entrada inválida
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // Manejo de errores al obtener tasas de cambio
        }
    }

    private void convertCurrency() {
        try {
            System.out.print("Moneda de origen (código, por ejemplo, USD): ");
            String fromCurrency = scanner.next().toUpperCase(); // Capturar moneda de origen

            System.out.print("Moneda de destino (código, por ejemplo, EUR): ");
            String toCurrency = scanner.next().toUpperCase(); // Capturar moneda de destino

            System.out.print("Cantidad a convertir: ");
            double amount = scanner.nextDouble(); // Capturar la cantidad a convertir

            // Realizar la conversión
            double result = logica.convert(fromCurrency, toCurrency, amount);

            System.out.println("Resultado de la conversión: " + result + " " + toCurrency);

        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor, ingresa una cantidad numérica.");
            scanner.next(); // Limpiar el error de entrada
        } catch (Exception e) {
            System.out.println("Error durante la conversión: " + e.getMessage());
        }
    }
}
