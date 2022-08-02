package by.it_academy.onliner.rest_api.service;

import by.it_academy.onliner.rest_api.model.SushiProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.it_academy.onliner.rest_api.util.ResponseBodyUtil.getListStringByJsonPath;
import static by.it_academy.onliner.rest_api.util.ResponseBodyUtil.getObjectsByJsonPath;
import static by.it_academy.onliner.util.PropertiesReader.getEndpoint;
import static by.it_academy.onliner.rest_api.util.GetRequestUtil.makeRequestAndGetResponseBody;

public class SushiService {

    public List<SushiProduct> getProducts() {
        return getObjectsByJsonPath(makeRequestAndGetResponseBody(getEndpoint("catalog.sushi"), null, null),
                "products", SushiProduct.class);
    }

    public List<String> getNamePrefixes() {
        Map<String, Object> params = new HashMap<>();
        params.put("sushi_typ[0]", "roll");
        params.put("sushi_typ[operation]", "union");
        return getListStringByJsonPath(makeRequestAndGetResponseBody(getEndpoint("catalog.sushi"), null, params),
                "products.name_prefix");
    }
}
