import java.net.URI;
import java.net.http.HttpRequest;

public class SolicitudHttp {
    public HttpRequest construirSolicitud(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }
}