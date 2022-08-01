package by.it_academy.onliner.rest_api.util;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;

import java.util.List;

public class ResponseBodyUtil {
    private ResponseBodyUtil() {
    }

    public static <T> List<T> getObjectsByJsonPath(ResponseBody responseBody, String jsonPath, Class<T> genericType) {
        return responseBody
                .jsonPath()
                .getList(jsonPath, genericType);
    }

    public static List<String> getListStringByJsonPath(ResponseBody responseBody, String jsonPath) {
        return responseBody
                .jsonPath()
                .getList(jsonPath);
    }

    public static String getStringJsonValue(ResponseBody responseBody, String jsonPath) {
        return JsonPath.from(responseBody.asString()).getString(jsonPath);
    }
}
