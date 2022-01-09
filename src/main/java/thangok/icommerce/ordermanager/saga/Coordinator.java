package thangok.icommerce.ordermanager.saga;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class Coordinator {

    @Bean("stock")
    public WebClient getStockClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8083/api/stock")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8083/api/stock"))
                .build();
    }

    @Bean("payment")
    public WebClient getPaymentClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8083/api/payment")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8083/api/payment"))
                .build();
    }
}
