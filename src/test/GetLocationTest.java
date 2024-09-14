import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.igor.seminar.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import seminar.AbstractTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class GetLocationTest extends AbstractTest {

    private static final Logger logger
            = LoggerFactory.getLogger(GetLocationTest.class);

    @Test
    void test401Error() throws URISyntaxException, IOException {
        
        logger.info("Test 401 run");
        //given

        logger.debug("Формирование мока для сервиса GetLocation");

        stubFor(WireMock.get(urlPathEqualTo("/locations/v1/cities/autocomplete"))
                .withQueryParam("apikey", notMatching("1000000"))
                .willReturn(aResponse().withStatus(401).withBody("Error")));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(getBaseUrl() + "/locations/v1/cities/autocomplete");
        URI uri = new URIBuilder(get.getURI())
                .addParameter("apikey","2000000")
                .build();
        get.setURI(uri);
        logger.debug("http клиент создан");

        //when

        HttpResponse response = client.execute(get);

        //then
        // проверить что при формировании запроса мы действительно сделали обращение к нашему Mock
        verify(getRequestedFor(urlPathEqualTo("/locations/v1/cities/autocomplete")));
        Assertions.assertEquals(401,response.getStatusLine().getStatusCode());
    }

    @Test
    void test200ResponceCodeSamara() throws IOException, URISyntaxException {

        logger.info("Test 200 run");
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        Location bodyOk = new Location();
        bodyOk.setKey("OK");

        //Объект с неправильным входным параметром
        Location bodyError = new Location();
        bodyError.setKey("Error");

        //Мок для успешного варианта
        stubFor(WireMock.get(urlPathEqualTo("/locations/v1/cities/autocomplete"))
                .withQueryParam("q", equalTo("Samara"))
                .willReturn(aResponse().withStatus(200).withBody(objectMapper.writeValueAsString(bodyOk))));

        //Мок для неверного варианта
        stubFor(WireMock.get(urlPathEqualTo("/locations/v1/cities/autocomplete"))
                .withQueryParam("q", equalTo("error"))
                .willReturn(aResponse().withStatus(200).withBody(objectMapper.writeValueAsString(bodyError))));

        logger.debug("Мокирование для теста test200ResponceCodeSamara завершено");

        //When
        //Создание http клиента
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(getBaseUrl() + "/locations/v1/cities/autocomplete");

        //и запрос с верными параметрами
        URI uriOk = new URIBuilder(get.getURI())
                .addParameter("q", "Samara")
                .build();
        get.setURI(uriOk);

        HttpResponse responseOk = client.execute(get);

        //запрос с ошибкой
        URI uriError = new URIBuilder(get.getURI())
                .addParameter("q", "error")
                .build();
        get.setURI(uriError);

        HttpResponse responseError = client.execute(get);

        //then
        verify(2, getRequestedFor(urlPathEqualTo("/locations/v1/cities/autocomplete")));
        //тест на код 200 для 2х запросов
        Assertions.assertEquals(200, responseOk.getStatusLine().getStatusCode());
        Assertions.assertEquals(200, responseError.getStatusLine().getStatusCode());

        //Тест для "body"
        Location LocationOk = objectMapper.readValue(responseOk.getEntity().getContent(), Location.class);
        Location LocationError = objectMapper.readValue(responseError.getEntity().getContent(), Location.class);

        Assertions.assertEquals("OK", LocationOk.getKey());
        Assertions.assertEquals("Error", LocationError.getKey());
    }

    @Test
    void test200ResponceCodeSPB() throws IOException, URISyntaxException {

        logger.info("Test 200 run");
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        Location bodyOk = new Location();
        bodyOk.setKey("OK");

        //Объект с неправильным входным параметром
        Location bodyError = new Location();
        bodyError.setKey("Error");

        //Мок для успешного варианта
        stubFor(WireMock.get(urlPathEqualTo("/locations/v1/cities/autocomplete"))
                .withQueryParam("q", equalTo("SaintPetersburg"))
                .willReturn(aResponse().withStatus(200).withBody(objectMapper.writeValueAsString(bodyOk))));

        //Мок для неверного варианта
        stubFor(WireMock.get(urlPathEqualTo("/locations/v1/cities/autocomplete"))
                .withQueryParam("q", equalTo("error"))
                .willReturn(aResponse().withStatus(200).withBody(objectMapper.writeValueAsString(bodyError))));

        logger.debug("Мокирование для теста test200ResponceCodeSPB завершено");

        //When
        //Создание http клиента
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(getBaseUrl() + "/locations/v1/cities/autocomplete");

        //и запрос с верными параметрами
        URI uriOk = new URIBuilder(get.getURI())
                .addParameter("q", "SaintPetersburg")
                .build();
        get.setURI(uriOk);

        HttpResponse responseOk = client.execute(get);

        //запрос с ошибкой
        URI uriError = new URIBuilder(get.getURI())
                .addParameter("q", "error")
                .build();
        get.setURI(uriError);

        HttpResponse responseError = client.execute(get);

        //then
        verify(2, getRequestedFor(urlPathEqualTo("/locations/v1/cities/autocomplete")));
        //тест на код 200 для 2х запросов
        Assertions.assertEquals(200, responseOk.getStatusLine().getStatusCode());
        Assertions.assertEquals(200, responseError.getStatusLine().getStatusCode());

        //Тест для "body"
        Location LocationOk = objectMapper.readValue(responseOk.getEntity().getContent(), Location.class);
        Location LocationError = objectMapper.readValue(responseError.getEntity().getContent(), Location.class);

        Assertions.assertEquals("OK", LocationOk.getKey());
        Assertions.assertEquals("Error", LocationError.getKey());
    }

    @Test
    void test200ResponceCodeMoscow() throws IOException, URISyntaxException {

        logger.info("Test 200 run");
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        Location bodyOk = new Location();
        bodyOk.setKey("OK");

        //Объект с неправильным входным параметром
        Location bodyError = new Location();
        bodyError.setKey("Error");

        //Мок для успешного варианта
        stubFor(WireMock.get(urlPathEqualTo("/locations/v1/cities/autocomplete"))
                .withQueryParam("q", equalTo("Moscow"))
                .willReturn(aResponse().withStatus(200).withBody(objectMapper.writeValueAsString(bodyOk))));

        //Мок для неверного варианта
        stubFor(WireMock.get(urlPathEqualTo("/locations/v1/cities/autocomplete"))
                .withQueryParam("q", equalTo("error"))
                .willReturn(aResponse().withStatus(200).withBody(objectMapper.writeValueAsString(bodyError))));

        logger.debug("Мокирование для теста test200ResponceCodeMoscow завершено");

        //When
        //Создание http клиента
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(getBaseUrl() + "/locations/v1/cities/autocomplete");

        //и запрос с верными параметрами
        URI uriOk = new URIBuilder(get.getURI())
                .addParameter("q", "Moscow")
                .build();
        get.setURI(uriOk);

        HttpResponse responseOk = client.execute(get);

        //запрос с ошибкой
        URI uriError = new URIBuilder(get.getURI())
                .addParameter("q", "error")
                .build();
        get.setURI(uriError);

        HttpResponse responseError = client.execute(get);

        //then
        verify(2, getRequestedFor(urlPathEqualTo("/locations/v1/cities/autocomplete")));
        //тест на код 200 для 2х запросов
        Assertions.assertEquals(200, responseOk.getStatusLine().getStatusCode());
        Assertions.assertEquals(200, responseError.getStatusLine().getStatusCode());

        //Тест для "body"
        Location LocationOk = objectMapper.readValue(responseOk.getEntity().getContent(), Location.class);
        Location LocationError = objectMapper.readValue(responseError.getEntity().getContent(), Location.class);

        Assertions.assertEquals("OK", LocationOk.getKey());
        Assertions.assertEquals("Error", LocationError.getKey());
    }

    @Test
    void test200ResponceCodeKazan() throws IOException, URISyntaxException {

        logger.info("Test 200 run");
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        Location bodyOk = new Location();
        bodyOk.setKey("OK");

        //Объект с неправильным входным параметром
        Location bodyError = new Location();
        bodyError.setKey("Error");

        //Мок для успешного варианта
        stubFor(WireMock.get(urlPathEqualTo("/locations/v1/cities/autocomplete"))
                .withQueryParam("q", equalTo("Kazan"))
                .willReturn(aResponse().withStatus(200).withBody(objectMapper.writeValueAsString(bodyOk))));

        //Мок для неверного варианта
        stubFor(WireMock.get(urlPathEqualTo("/locations/v1/cities/autocomplete"))
                .withQueryParam("q", equalTo("error"))
                .willReturn(aResponse().withStatus(200).withBody(objectMapper.writeValueAsString(bodyError))));

        logger.debug("Мокирование для теста test200ResponceCodeKazan завершено");

        //When
        //Создание http клиента
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(getBaseUrl() + "/locations/v1/cities/autocomplete");

        //и запрос с верными параметрами
        URI uriOk = new URIBuilder(get.getURI())
                .addParameter("q", "Kazan")
                .build();
        get.setURI(uriOk);

        HttpResponse responseOk = client.execute(get);

        //запрос с ошибкой
        URI uriError = new URIBuilder(get.getURI())
                .addParameter("q", "error")
                .build();
        get.setURI(uriError);

        HttpResponse responseError = client.execute(get);

        //then
        verify(2, getRequestedFor(urlPathEqualTo("/locations/v1/cities/autocomplete")));
        //тест на код 200 для 2х запросов
        Assertions.assertEquals(200, responseOk.getStatusLine().getStatusCode());
        Assertions.assertEquals(200, responseError.getStatusLine().getStatusCode());

        //Тест для "body"
        Location LocationOk = objectMapper.readValue(responseOk.getEntity().getContent(), Location.class);
        Location LocationError = objectMapper.readValue(responseError.getEntity().getContent(), Location.class);

        Assertions.assertEquals("OK", LocationOk.getKey());
        Assertions.assertEquals("Error", LocationError.getKey());
    }

}