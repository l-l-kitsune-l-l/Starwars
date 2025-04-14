package StarMain;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.json.JSONObject;

public class API {

    public JSONObject getBuilder(String path, String searchquery) {
        try {
            System.out.println("path : " + path);
            System.out.println("searchquery : " + searchquery);
    
            String urlString = "https://swapi.tech/api/" + path + "/";
            if (searchquery != null && !searchquery.isEmpty()) {
                urlString += "?search=" + searchquery;
            }
    
            URI uri = new URI(urlString);
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            System.out.println("class API (URL): " + conn);
            System.out.println("URI: " + uri);
            conn.setRequestMethod("GET");
            conn.connect();
    
            // Lire la réponse avec try-with-resources
            StringBuilder responseBuilder = new StringBuilder();
            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                while (scanner.hasNext()) {
                    responseBuilder.append(scanner.nextLine());
                }
            }
    
            System.out.println("On a récupéré la réponse de l'API : " + responseBuilder);
            return new JSONObject(responseBuilder.toString());
    
        } catch (IOException | URISyntaxException e) {
            System.err.println("Erreur lors de l'appel à l'API SWAPI : " + e.getMessage());
            e.printStackTrace();
        }
    
        return null;
    }
    

    public JSONObject innerRequest(String urlString) {
        try {
            URI uri = new URI(urlString);
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
    
            StringBuilder responseBuilder = new StringBuilder();
            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                while (scanner.hasNext()) {
                    responseBuilder.append(scanner.nextLine());
                }
            }
    
            return new JSONObject(responseBuilder.toString());
    
        } catch (IOException | URISyntaxException e) {
            System.err.println("Erreur lors de l'appel à l'URL interne : " + urlString);
            e.printStackTrace();
        }
    
        return null;
    }
    public String getNameFromUrl(String url) {
        JSONObject response = innerRequest(url);
    
        if (response != null && response.has("result")) {
            JSONObject result = response.getJSONObject("result");
            JSONObject properties = result.getJSONObject("properties");
    
            if (properties.has("name")) {
                return properties.getString("name");
            } else if (properties.has("title")) {
                return properties.getString("title");
            }
        }
    
        return "Unknown";
    }
    
    
}
