package StarMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser {
    public static void main(String[] args) {
        String responseBuilder = "{\"results\":[" +
                "{\"title\":\"Return of the Jedi\",\"episode_id\":6,\"opening_crawl\":\"Luke Skywalker has returned to his home planet of Tatooine in an attempt to rescue his friend Han Solo from the clutches of the vile gangster Jabba the Hutt.\",\"director\":\"Richard Marquand\",\"producer\":\"Howard G. Kazanjian, George Lucas, Rick McCallum\",\"release_date\":\"1983-05-25\"}," +
                "{\"title\":\"The Empire Strikes Back\",\"episode_id\":5,\"opening_crawl\":\"It is a dark time for the Rebellion. Although the Death Star has been destroyed, Imperial troops have driven the Rebel forces from their hidden base and pursued them across the galaxy.\",\"director\":\"Irvin Kershner\",\"producer\":\"Gary Kurtz, Rick McCallum\",\"release_date\":\"1980-05-17\"}," +
                "{\"title\":\"A New Hope\",\"episode_id\":4,\"opening_crawl\":\"It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory against the evil Galactic Empire.\",\"director\":\"George Lucas\",\"producer\":\"Gary Kurtz, Rick McCallum\",\"release_date\":\"1977-05-25\"}]}";

        try {
            JSONObject jsonObject = new JSONObject(responseBuilder);
            JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject film = results.getJSONObject(i);
                String title = film.getString("title");
                int episodeId = film.getInt("episode_id");
                String director = film.getString("director");
                String releaseDate = film.getString("release_date");
                System.out.println("Title: " + title);
                System.out.println("Episode ID: " + episodeId);
                System.out.println("Director: " + director);
                System.out.println("Release Date: " + releaseDate);
                System.out.println("-----------------------");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
