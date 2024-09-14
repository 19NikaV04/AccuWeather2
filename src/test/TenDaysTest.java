import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.igor.home.DailyForecast;
import org.igor.home.Headline;
import org.igor.home.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import seminar.AbstractTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

public class TenDaysTest extends AbstractTest {

    private static final Logger logger
            = LoggerFactory.getLogger(FiveDaysTest.class);

            
    @Test
    void test500ErrorSPB() throws URISyntaxException, IOException {

        logger.info("Test 500 run");
        //given
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = new Weather();
        Headline headline = new Headline();
        headline.setCategory("Категория");
        headline.setText("Текст");
        weather.setHeadline(headline);
        DailyForecast dailyForecast = new DailyForecast();
        List<DailyForecast> dailyForecasts = new ArrayList<>();
        dailyForecasts.add(dailyForecast);
        weather.setDailyForecasts(dailyForecasts);
        logger.debug("Формирование мока для сервиса test500ErrorSPB");

        stubFor(WireMock.get(urlPathEqualTo("/forecasts/v1/daily/10day/295212"))
//                .withQueryParam("locationKey", notMatching("1000000"))
                .willReturn(aResponse().withStatus(500)
                        .withBody(mapper.writeValueAsString(weather))));

        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet(getBaseUrl() + "/forecasts/v1/daily/10day/295212");
        URI uri = new URIBuilder(get.getURI())
//                .addParameter("apikey","2000000")
                .build();
        get.setURI(uri);
        logger.debug("http клиент создан");

        //when
        HttpResponse response = client.execute(get);

        //then
        // проверить,что при формировнии запроса мы действительно сделали обращение к нашему Mock
        verify(getRequestedFor(urlPathEqualTo("/forecasts/v1/daily/10day/295212")));
        Assertions.assertEquals(500,response.getStatusLine().getStatusCode());

        Weather responseBody = mapper.readValue(response.getEntity().getContent(), Weather.class);
        Assertions.assertEquals("Категория", responseBody.getHeadline().getCategory());
        Assertions.assertEquals("Текст", responseBody.getHeadline().getText());
        Assertions.assertEquals(1, responseBody.getDailyForecasts().size());
    }

    @Test
    void test500ErrorSamara() throws URISyntaxException, IOException {

        logger.info("Test 500 run");
        //given
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = new Weather();
        Headline headline = new Headline();
        headline.setCategory("Категория");
        headline.setText("Текст");
        weather.setHeadline(headline);
        DailyForecast dailyForecast = new DailyForecast();
        List<DailyForecast> dailyForecasts = new ArrayList<>();
        dailyForecasts.add(dailyForecast);
        weather.setDailyForecasts(dailyForecasts);
        logger.debug("Формирование мока для сервиса test500ErrorSamara");

        stubFor(WireMock.get(urlPathEqualTo("/forecasts/v1/daily/10day/290396"))
//                .withQueryParam("locationKey", notMatching("1000000"))
                .willReturn(aResponse().withStatus(500)
                        .withBody(mapper.writeValueAsString(weather))));

        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet(getBaseUrl() + "/forecasts/v1/daily/10day/290396");
        URI uri = new URIBuilder(get.getURI())
//                .addParameter("apikey","2000000")
                .build();
        get.setURI(uri);
        logger.debug("http клиент создан");

        //when
        HttpResponse response = client.execute(get);

        //then
        // проверить,что при формировнии запроса мы действительно сделали обращение к нашему Mock
        verify(getRequestedFor(urlPathEqualTo("/forecasts/v1/daily/10day/290396")));
        Assertions.assertEquals(500,response.getStatusLine().getStatusCode());

        Weather responseBody = mapper.readValue(response.getEntity().getContent(), Weather.class);
        Assertions.assertEquals("Категория", responseBody.getHeadline().getCategory());
        Assertions.assertEquals("Текст", responseBody.getHeadline().getText());
        Assertions.assertEquals(1, responseBody.getDailyForecasts().size());
    }

    @Test
    void test500ErrorMoscow() throws URISyntaxException, IOException {

        logger.info("Test 500 run");
        //given
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = new Weather();
        Headline headline = new Headline();
        headline.setCategory("Категория");
        headline.setText("Текст");
        weather.setHeadline(headline);
        DailyForecast dailyForecast = new DailyForecast();
        List<DailyForecast> dailyForecasts = new ArrayList<>();
        dailyForecasts.add(dailyForecast);
        weather.setDailyForecasts(dailyForecasts);
        logger.debug("Формирование мока для сервиса test500ErrorMoscow");

        stubFor(WireMock.get(urlPathEqualTo("/forecasts/v1/daily/10day/294021"))
//                .withQueryParam("locationKey", notMatching("1000000"))
                .willReturn(aResponse().withStatus(500)
                        .withBody(mapper.writeValueAsString(weather))));

        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet(getBaseUrl() + "/forecasts/v1/daily/10day/294021");
        URI uri = new URIBuilder(get.getURI())
//                .addParameter("apikey","2000000")
                .build();
        get.setURI(uri);
        logger.debug("http клиент создан");

        //when
        HttpResponse response = client.execute(get);

        //then
        // проверить,что при формировнии запроса мы действительно сделали обращение к нашему Mock
        verify(getRequestedFor(urlPathEqualTo("/forecasts/v1/daily/10day/294021")));
        Assertions.assertEquals(500,response.getStatusLine().getStatusCode());

        Weather responseBody = mapper.readValue(response.getEntity().getContent(), Weather.class);
        Assertions.assertEquals("Категория", responseBody.getHeadline().getCategory());
        Assertions.assertEquals("Текст", responseBody.getHeadline().getText());
        Assertions.assertEquals(1, responseBody.getDailyForecasts().size());
    }

    @Test
    void test500ErrorKazan() throws URISyntaxException, IOException {

        logger.info("Test 500 run");
        //given
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = new Weather();
        Headline headline = new Headline();
        headline.setCategory("Категория");
        headline.setText("Текст");
        weather.setHeadline(headline);
        DailyForecast dailyForecast = new DailyForecast();
        List<DailyForecast> dailyForecasts = new ArrayList<>();
        dailyForecasts.add(dailyForecast);
        weather.setDailyForecasts(dailyForecasts);
        logger.debug("Формирование мока для сервиса test500ErrorKazan");

        stubFor(WireMock.get(urlPathEqualTo("/forecasts/v1/daily/10day/295954"))
//                .withQueryParam("locationKey", notMatching("1000000"))
                .willReturn(aResponse().withStatus(500)
                        .withBody(mapper.writeValueAsString(weather))));

        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet(getBaseUrl() + "/forecasts/v1/daily/10day/295954");
        URI uri = new URIBuilder(get.getURI())
//                .addParameter("apikey","2000000")
                .build();
        get.setURI(uri);
        logger.debug("http клиент создан");

        //when
        HttpResponse response = client.execute(get);

        //then
        // проверить,что при формировнии запроса мы действительно сделали обращение к нашему Mock
        verify(getRequestedFor(urlPathEqualTo("/forecasts/v1/daily/10day/295954")));
        Assertions.assertEquals(500,response.getStatusLine().getStatusCode());

        Weather responseBody = mapper.readValue(response.getEntity().getContent(), Weather.class);
        Assertions.assertEquals("Категория", responseBody.getHeadline().getCategory());
        Assertions.assertEquals("Текст", responseBody.getHeadline().getText());
        Assertions.assertEquals(1, responseBody.getDailyForecasts().size());
    }


}