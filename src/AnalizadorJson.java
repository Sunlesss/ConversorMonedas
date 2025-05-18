import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AnalizadorJson {
    public JsonObject analizarJson(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }
}
