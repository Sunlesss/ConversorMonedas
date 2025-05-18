import java.net.http.HttpRequest;
import java.util.Scanner;
import com.google.gson.JsonObject;

public class InterfazUsuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteHttp clienteHttp = new ClienteHttp();
        SolicitudHttp solicitudHttp = new SolicitudHttp();
        RespuestaHttp respuestaHttp = new RespuestaHttp();
        AnalizadorJson analizadorJson = new AnalizadorJson();
        ConversorMonedas conversorMonedas = new ConversorMonedas();
        CalculadoraConversion calculadoraConversion = new CalculadoraConversion();

        // CAMBIA AQUÍ POR TU PROPIA API KEY de https://www.exchangerate-api.com/
        String apiKey = "baefddca95b95f34cf01e08e";
        String baseUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

        while (true) {
            System.out.println("Sea bienvenido/a al Conversor de Moneda");
            System.out.println("1) USD a ARS");
            System.out.println("2) USD a BOB");
            System.out.println("3) USD a BRL");
            System.out.println("4) USD a CLP");
            System.out.println("5) USD a COP");
            System.out.println("6) USD a USD");
            System.out.println("7) Salir");
            System.out.print("Elija una opción válida: ");
            int opcion = scanner.nextInt();

            if (opcion == 7) {
                System.out.println("Gracias por usar el conversor de monedas.");
                break;
            }

            System.out.print("Ingrese la cantidad en USD: ");
            double cantidad = scanner.nextDouble();

            try {
                HttpRequest request = solicitudHttp.construirSolicitud(baseUrl);
                String respuesta = respuestaHttp.obtenerRespuesta(clienteHttp.getClient(), request);
                JsonObject jsonObject = analizadorJson.analizarJson(respuesta);

                if (opcion < 1 || opcion > 6) {
                    System.out.println("Opción no válida.");
                    continue;
                }

                String codigoMoneda = switch (opcion) {
                    case 1 -> "ARS";
                    case 2 -> "BOB";
                    case 3 -> "BRL";
                    case 4 -> "CLP";
                    case 5 -> "COP";
                    case 6 -> "USD";
                    default -> "USD"; // nunca se llega aquí, pero requerido por el compilador
                };


                double tasaCambio = conversorMonedas.obtenerTasaCambio(jsonObject, codigoMoneda);
                double resultado = calculadoraConversion.convertir(cantidad, tasaCambio);

                System.out.printf("Resultado: %.2f USD = %.2f %s%n", cantidad, resultado, codigoMoneda);
            } catch (Exception e) {
                System.out.println("Error al realizar la conversión: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
