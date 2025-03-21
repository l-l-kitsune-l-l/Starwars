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
            // URL de l'API SWAPI
            System.out.println("path : " + path);
            System.out.println("searchquery : "+ searchquery);
        	
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
            

            // Lire la réponse
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder responseBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                responseBuilder.append(scanner.nextLine());
            }
            scanner.close();
            
            System.out.println("On a récupéré la réponse de l'API : " + responseBuilder);

            // Convertir la réponse en JSONObject

            return new JSONObject(responseBuilder.toString());

   
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject innerRequest(String uri) {
        // Implémentez ceci si vous devez envoyer des requêtes internes
        return null;
    }
}
