package PasAPas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

public class HtmlPrinter {

    private String codeHtml;
    private final API api = new API(); // Ajout du "final" selon le hint

    public String printHtmlDetailsPlanets(JSONObject planetData) {
        if (planetData != null) {
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("""
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Planets Data</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f4;
                            padding: 20px;
                        }
                        h1 {
                            color: #333;
                        }
                        .planet {
                            background-color: #fff;
                            border: 1px solid #ddd;
                            border-radius: 8px;
                            padding: 15px;
                            margin-bottom: 20px;
                            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                        }
                        ul {
                            padding-left: 20px;
                        }
                    </style>
                </head>
                <body>
                    <h1>Planets Data</h1>
            """);

            if (planetData.has("result")) {
                JSONArray results = planetData.getJSONArray("result");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject planet = results.getJSONObject(i);
                    JSONObject properties = planet.getJSONObject("properties");

                    htmlBuilder.append("<div class='planet'>");
                    htmlBuilder.append("<h2>").append(properties.getString("name")).append("</h2>");
                    htmlBuilder.append("<p><strong>Rotation Period:</strong> ").append(properties.getString("rotation_period")).append("</p>");
                    htmlBuilder.append("<p><strong>Orbital Period:</strong> ").append(properties.getString("orbital_period")).append("</p>");
                    htmlBuilder.append("<p><strong>Diameter:</strong> ").append(properties.getString("diameter")).append("</p>");
                    htmlBuilder.append("<p><strong>Gravity:</strong> ").append(properties.getString("gravity")).append("</p>");
                    htmlBuilder.append("<p><strong>Terrain:</strong> ").append(properties.getString("terrain")).append("</p>");
                    htmlBuilder.append("<p><strong>Surface Water:</strong> ").append(properties.getString("surface_water")).append("</p>");
                    htmlBuilder.append("<p><strong>Population:</strong> ").append(properties.getString("population")).append("</p>");

                    // Résidents
                    if (planet.has("residents") && !planet.isNull("residents")) {
                        JSONArray residents = planet.getJSONArray("residents");
                        if (residents.length() > 0) {
                            htmlBuilder.append("<p>Residents:</p><ul>");
                            for (int j = 0; j < residents.length(); j++) {
                                htmlBuilder.append("<li>").append(residents.getString(j)).append("</li>");
                            }
                            htmlBuilder.append("</ul>");
                        } else {
                            htmlBuilder.append("<p>No known residents.</p>");
                        }
                    }
                    

                    // Films
                    if (planet.has("films") && !planet.isNull("films")) {
                        JSONArray films = planet.getJSONArray("films");
                        if (films.length() > 0) {
                            htmlBuilder.append("<p>Films:</p><ul>");
                            for (int j = 0; j < films.length(); j++) {
                                htmlBuilder.append("<li>").append(films.getString(j)).append("</li>");
                            }
                            htmlBuilder.append("</ul>");
                        } else {
                            htmlBuilder.append("<p>No known films.</p>");
                        }
                    } else {
                        htmlBuilder.append("<p>No film data available for this planet.</p>");
                    }
                    

                    htmlBuilder.append("</div>");
                }
            }

            htmlBuilder.append("</body></html>");
            codeHtml = htmlBuilder.toString();
            System.out.println(codeHtml);
        }

        return codeHtml;
    }

    public void saveHtmlToFile(String htmlContent, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(htmlContent);
            System.out.println("HTML content has been saved to: " + filePath);

            // Ouvre automatiquement le fichier dans le navigateur
            File htmlFile = new File(filePath);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde ou de l'ouverture du fichier HTML : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
