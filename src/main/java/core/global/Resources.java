package core.global;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Resources {

    private String name;
    private String description;

    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static URL getImage(String image){
        return Resources.class.getClassLoader().getResource("icons/" + image);
    }

    public static URL getConfig(String file){
        return Resources.class.getClassLoader().getResource("config/" + file);
    }

    public static void getSettings(){

        final ObjectMapper objectMapper = new ObjectMapper();
        List<Resources> langList = null;
        try {
            langList = objectMapper.readValue(
                    getConfig("config.json"),
                    new TypeReference<List<Resources>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        langList.forEach(x -> System.out.println(x.toString()));

    }


}
