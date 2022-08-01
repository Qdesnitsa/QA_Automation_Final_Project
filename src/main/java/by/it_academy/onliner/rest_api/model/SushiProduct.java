package by.it_academy.onliner.rest_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SushiProduct {
    private int id;
    private String key;
    private String name;
    @JsonProperty("full_name")
    private String fullName;

    public SushiProduct() {}

    public SushiProduct(int id, String key, String name, String fullName) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SushiProduct that = (SushiProduct) o;
        return id == that.id && Objects.equals(key, that.key) && Objects.equals(name, that.name) &&
                Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, name, fullName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                " ;";
    }
}
