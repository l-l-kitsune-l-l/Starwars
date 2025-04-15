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
                    JSONObject planet = results.getJSONObject(i).getJSONObject("properties");

                    htmlBuilder.append("<div class='planet'>")
                            .append("<h2>").append(planet.getString("name")).append("</h2>")
                            .append("<p><strong>Rotation Period:</strong> ").append(planet.getString("rotation_period")).append("</p>")
                            .append("<p><strong>Orbital Period:</strong> ").append(planet.getString("orbital_period")).append("</p>")
                            .append("<p><strong>Diameter:</strong> ").append(planet.getString("diameter")).append("</p>")
                            .append("<p><strong>Gravity:</strong> ").append(planet.getString("gravity")).append("</p>")
                            .append("<p><strong>Terrain:</strong> ").append(planet.getString("terrain")).append("</p>")
                            .append("<p><strong>Surface Water:</strong> ").append(planet.getString("surface_water")).append("</p>")
                            .append("<p><strong>Population:</strong> ").append(planet.getString("population")).append("</p>");

                    // Champs facultatifs : residents & films
                    if (planet.has("residents")) {
                        htmlBuilder.append("<p>Residents field found (but not resolved by API by default)</p>");
                    }

                    if (planet.has("films")) {
                        htmlBuilder.append("<p>Films field found (but not resolved by API by default)</p>");
                    }

                    htmlBuilder.append("</div>");
                }
            }

            htmlBuilder.append("</body></html>");

            codeHtml = htmlBuilder.toString();
        }
        return codeHtml;
    }

    public void saveHtmlToFile(String htmlContent, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(htmlContent);
            System.out.println("✅ HTML sauvegardé dans : " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Erreur lors de la sauvegarde : " + filePath);
            e.printStackTrace();
        }
    }

    public void saveInteractivePlanetsPage(String filePath) {
        String htmlContent = """
            <!DOCTYPE html>
            <html lang="fr">
            <head>
                <meta charset="UTF-8">
                <title>SWAPI - Planètes</title>
                <style>
                    body { font-family: Arial, sans-serif; padding: 20px; }
                    h1 { color: #333; }
                    select, button { font-size: 16px; margin-right: 10px; }
                    #details { margin-top: 20px; }
                    .label { font-weight: bold; }
                </style>
            </head>
            <body>
                <h1>🌍 Choisissez une planète</h1>
                
                <select id="planetSelect">
                    <option disabled selected>Chargement...</option>
                </select>
                <button onclick="loadPlanetDetails()">Afficher les détails</button>
    
                <div id="details"></div>
    
                <script>
                    let planetsData = [];
    
                    window.onload = async function () {
                        const response = await fetch("https://swapi.tech/api/planets");
                        const data = await response.json();
                        planetsData = data.results;
    
                        const select = document.getElementById("planetSelect");
                        select.innerHTML = "";
    
                        planetsData.forEach((planet) => {
                            const option = document.createElement("option");
                            option.value = planet.uid;
                            option.text = planet.name;
                            select.appendChild(option);
                        });
                    };
    
                    async function loadPlanetDetails() {
                        const uid = document.getElementById("planetSelect").value;
                        const response = await fetch(`https://swapi.tech/api/planets/${uid}`);
                        const data = await response.json();
                        const props = data.result.properties;
    
                        document.getElementById("details").innerHTML = `
                            <h2>${props.name}</h2>
                            <p><span class='label'>Rotation Period:</span> ${props.rotation_period}</p>
                            <p><span class='label'>Orbital Period:</span> ${props.orbital_period}</p>
                            <p><span class='label'>Diameter:</span> ${props.diameter}</p>
                            <p><span class='label'>Gravity:</span> ${props.gravity}</p>
                            <p><span class='label'>Terrain:</span> ${props.terrain}</p>
                            <p><span class='label'>Surface Water:</span> ${props.surface_water}</p>
                            <p><span class='label'>Population:</span> ${props.population}</p>
                        `;
                    }
                </script>
            </body>
            </html>
            """;
    
        saveHtmlToFile(htmlContent, filePath);
    }
    
}
