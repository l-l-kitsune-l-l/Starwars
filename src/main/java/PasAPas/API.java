package PasAPas;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.json.JSONObject;

public class API {

    // URL de base de l'API pour les planètes
    private static final String PLANETS_API_URL = "https://swapi.tech/api/planets/";

    /**
     * Méthode pour récupérer les données JSON d'une planète par nom
     */
    public JSONObject getPlanets(String searchquery) {
        try {
            String urlString = PLANETS_API_URL;
            if (searchquery != null && !searchquery.isEmpty()) {
                urlString += "?name=" + searchquery;
            }

            System.out.println("searchquery : " + searchquery);
            URI uri = new URI(urlString);

            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                StringBuilder responseBuilder = new StringBuilder();
                while (scanner.hasNext()) {
                    responseBuilder.append(scanner.nextLine());
                }

                return new JSONObject(responseBuilder.toString());
            }

        } catch (IOException | URISyntaxException e) {
            System.err.println("Erreur lors de la récupération des planètes : " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Méthode pour récupérer dynamiquement un nom (ou titre) à partir d'une URL fournie par l'API SWAPI
     */
    public String getNameFromUrl(String url) {
        try {
            URI uri = new URI(url);
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                StringBuilder responseBuilder = new StringBuilder();
                while (scanner.hasNext()) {
                    responseBuilder.append(scanner.nextLine());
                }

                JSONObject response = new JSONObject(responseBuilder.toString());
                JSONObject result = response.getJSONObject("result");
                JSONObject properties = result.getJSONObject("properties");

                if (properties.has("name")) {
                    return properties.getString("name");
                } else if (properties.has("title")) {
                    return properties.getString("title");
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel à l'URL : " + url);
            e.printStackTrace();
        }

        return "Unknown";
    }
}
