package StarMain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;

import org.json.JSONArray;
import org.json.JSONObject;

public class HtmlFilmPrinter {

    public void printHtmlFromFilms(JSONArray films, String filePath) {
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("""
            <html>
            <head>
                <meta charset="UTF-8">
                <title>Star Wars Films</title>
                <style>
                    body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }
                    .film { background-color: #fff; padding: 15px; margin-bottom: 20px; border-radius: 10px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
                    h2 { color: #333; }
                    p { margin: 5px 0; }
                </style>
            </head>
            <body>
                <h1>📽️ Star Wars Films</h1>
        """);

        for (int i = 0; i < films.length(); i++) {
            JSONObject film = films.getJSONObject(i);

            htmlBuilder.append("<div class='film'>");
            htmlBuilder.append("<h2>").append("🎬 ").append(film.getString("title")).append("</h2>");
            htmlBuilder.append("<p><strong>Episode:</strong> ").append(film.getInt("episode_id")).append("</p>");
            htmlBuilder.append("<p><strong>Director:</strong> ").append(film.getString("director")).append("</p>");
            htmlBuilder.append("<p><strong>Release Date:</strong> ").append(film.getString("release_date")).append("</p>");
            htmlBuilder.append("</div>");
        }

        htmlBuilder.append("</body></html>");

        saveHtmlToFile(htmlBuilder.toString(), filePath);
    }

    private void saveHtmlToFile(String htmlContent, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(htmlContent);
            System.out.println("✅ HTML enregistré à : " + filePath);

            File htmlFile = new File(filePath);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            }
        } catch (IOException e) {
            System.err.println("❌ Erreur lors de la génération ou de l'ouverture du HTML");
            e.printStackTrace();
        }
    }
}
