import java.net.http.HttpClient;

public class ClienteHttp {
    private final HttpClient client;

    public ClienteHttp() {
        this.client = HttpClient.newHttpClient();
    }

    public HttpClient getClient() {
        return client;
    }
}
