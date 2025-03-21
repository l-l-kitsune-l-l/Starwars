package testParser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonParser {
    public static void main(String[] args) {
        String jsonString = "{\"films\": [{\"title\":\"Return of the Jedi\",\"episode_id\":6,\"director\":\"Richard Marquand\",\"release_date\":\"1983-05-25\"},{\"title\":\"The Empire Strikes Back\",\"episode_id\":5,\"director\":\"Irvin Kershner\",\"release_date\":\"1980-05-17\"},{\"title\":\"A New Hope\",\"episode_id\":4,\"director\":\"George Lucas\",\"release_date\":\"1977-05-25\"}]}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode filmsArray = rootNode.get("films");

            for (JsonNode filmNode : filmsArray) {
                String title = filmNode.get("title").asText();
                int episodeId = filmNode.get("episode_id").asInt();
                String director = filmNode.get("director").asText();
                String releaseDate = filmNode.get("release_date").asText();

                System.out.println("Title: " + title);
                System.out.println("Episode ID: " + episodeId);
                System.out.println("Director: " + director);
                System.out.println("Release Date: " + releaseDate);
                System.out.println("-----------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
