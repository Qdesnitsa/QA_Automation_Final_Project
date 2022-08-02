package by.it_academy.onliner.rest_api.service;

import by.it_academy.onliner.rest_api.model.SushiProduct;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SushiServiceTest {
    private final SushiService productsService = new SushiService();

    @Test
    public void testProductNamesAreNotEmpty() {
        assertThat(productsService.getProducts())
                .as("Not every product has name")
                .noneMatch(element -> element.getName().isEmpty());
    }

    @Test
    public void testProductNamesOfTypeRollsContainsPrefixRolls() {
        List<SushiProduct> facets = productsService.getProducts();
        assertThat(productsService.getNamePrefixes())
                .as("Not every name_prefix contains text 'Роллы' ")
                .allMatch(element -> element.contains("Роллы"));
    }
}
