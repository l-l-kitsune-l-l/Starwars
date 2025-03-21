package PasAPas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class HtmlPrinter {
	
	private String codeHtml;
    
	public String printHtmlDetailsPlanets(JSONObject planetData) {
        if (planetData != null) {
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<html><head><title>Planets Data</title></head><body>");
            htmlBuilder.append("<h1>Planets Data</h1>");

            // Vérifie si la clé "results" est présente dans le JSON
            if (planetData.has("result")) {
                JSONArray results = planetData.getJSONArray("result");

                // Parcourt chaque objet planète
                for (int i = 0; i < results.length(); i++) {
                    JSONObject planet = results.getJSONObject(i);
                    htmlBuilder.append("<h2>").append("Name: ").append(planet.getJSONObject("properties").getString("name")).append("</h2>");
                    htmlBuilder.append("<p>").append("Rotation Period: ").append(planet.getJSONObject("properties").getString("rotation_period")).append("</p>");
                    htmlBuilder.append("<p>").append("Orbital Period: ").append(planet.getJSONObject("properties").getString("orbital_period")).append("</p>");
                    htmlBuilder.append("<p>").append("Diameter: ").append(planet.getJSONObject("properties").getString("diameter")).append("</p>");
                    htmlBuilder.append("<p>").append("Gravity: ").append(planet.getJSONObject("properties").getString("gravity")).append("</p>");
                    htmlBuilder.append("<p>").append("Terrain: ").append(planet.getJSONObject("properties").getString("terrain")).append("</p>");
                    htmlBuilder.append("<p>").append("Surface Water: ").append(planet.getJSONObject("properties").getString("surface_water")).append("</p>");
                    htmlBuilder.append("<p>").append("Population: ").append(planet.getJSONObject("properties").getString("population")).append("</p>");
                    System.out.println(codeHtml);
//                    // Ajoute les résidents
//                    JSONArray residents = planet.getJSONArray("residents");
//                    if (residents.length() > 0) {
//                        htmlBuilder.append("<p>").append("Residents: ").append("</p><ul>");
//                        for (int j = 0; j < residents.length(); j++) {
//                            htmlBuilder.append("<li>").append(residents.getString(j)).append("</li>");
//                        }
//                        htmlBuilder.append("</ul>");
//                    }
//
//                    // Ajoute les films
//                    JSONArray films = planet.getJSONArray("films");
//                    if (films.length() > 0) {
//                        htmlBuilder.append("<p>").append("Films: ").append("</p><ul>");
//                        for (int j = 0; j < films.length(); j++) {
//                            htmlBuilder.append("<li>").append(films.getString(j)).append("</li>");
//                        }
//                        htmlBuilder.append("</ul>");
//                    }
               }
            }
            htmlBuilder.append("</body></html>");

            // Stocke le contenu HTML dans une variable
            codeHtml = htmlBuilder.toString();
            System.out.println(codeHtml);
        }
        
        // Renvoie le contenu HTML généré
        return codeHtml;
    }
    
    public void saveHtmlToFile(String htmlContent, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(htmlContent);
            System.out.println("HTML content has been saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
