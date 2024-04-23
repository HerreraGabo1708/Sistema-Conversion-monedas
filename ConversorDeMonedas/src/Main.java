public class Main {
    public static void main(String[] args) {
        String apiUrl = "https://api.exchangerate-api.com/v4/latest/USD"; // URL de la API de tasas de cambio
        InterfazConsola interfaz = new InterfazConsola(); // Crear instancia de InterfazConsola
        interfaz.start(apiUrl); // Iniciar el proceso interactivo
    }
}
