import com.google.gson.JsonObject;

public class ConversorMonedas {
    public double obtenerTasaCambio(JsonObject jsonObject, String codigoMoneda) {
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        return conversionRates.get(codigoMoneda).getAsDouble();
    }
}
