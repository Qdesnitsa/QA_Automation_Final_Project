package by.it_academy.onliner.rest_api.util;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestUtil {
    private GetRequestUtil() {
    }

    public static ResponseBody makeRequestAndGetResponseBody(String endpoint, Map<String, Object> headers,
                                                             Map<String, Object> params) {
        Response response = given()
                .headers(MapUtils.emptyIfNull(headers))
                .params(MapUtils.emptyIfNull(params))
                .when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        return response.getBody();
    }

}
