package testParser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GsonParser {
    public static void main(String[] args) {
        String jsonString = "{\"films\": [{\"title\":\"Return of the Jedi\",\"episode_id\":6,\"director\":\"Richard Marquand\",\"release_date\":\"1983-05-25\"},{\"title\":\"The Empire Strikes Back\",\"episode_id\":5,\"director\":\"Irvin Kershner\",\"release_date\":\"1980-05-17\"},{\"title\":\"A New Hope\",\"episode_id\":4,\"director\":\"George Lucas\",\"release_date\":\"1977-05-25\"}]}";

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        JsonArray filmsArray = jsonObject.getAsJsonArray("films");

        for (JsonElement filmElement : filmsArray) {
            JsonObject filmObject = filmElement.getAsJsonObject();
            String title = filmObject.get("title").getAsString();
            int episodeId = filmObject.get("episode_id").getAsInt();
            String director = filmObject.get("director").getAsString();
            String releaseDate = filmObject.get("release_date").getAsString();

            System.out.println("Title: " + title);
            System.out.println("Episode ID: " + episodeId);
            System.out.println("Director: " + director);
            System.out.println("Release Date: " + releaseDate);
            System.out.println("-----------------------");
        }
    }
}
