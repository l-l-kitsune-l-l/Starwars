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

    public void saveFullInteractivePage(String filePath) {
        String htmlContent = """
        <!DOCTYPE html>
        <html lang='fr'>
        <head>
            <meta charset='UTF-8'>
            <title>Star Wars Data Explorer</title>
            <style>
                :root {
                    --main-bg: #1c1e22;
                    --card-bg: #2c2f33;
                    --text-color: #f5f5f5;
                    --accent-color: #7289da;
                }
                body {
                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                    background-color: var(--main-bg);
                    color: var(--text-color);
                    margin: 0;
                    padding: 20px;
                }
                h1 {
                    text-align: center;
                    margin-bottom: 40px;
                    color: var(--accent-color);
                }
                .section {
                    background-color: var(--card-bg);
                    padding: 20px;
                    border-radius: 10px;
                    margin-bottom: 30px;
                    box-shadow: 0 4px 8px rgba(0,0,0,0.2);
                }
                h2 {
                    color: var(--accent-color);
                    margin-bottom: 10px;
                }
                select, button {
                    font-size: 16px;
                    padding: 8px;
                    margin: 10px 0;
                    border: none;
                    border-radius: 5px;
                    background-color: #3b3f45;
                    color: var(--text-color);
                    outline: none;
                }
                button {
                    background-color: var(--accent-color);
                    cursor: pointer;
                    transition: background-color 0.3s ease;
                }
                button:hover {
                    background-color: #5b6eae;
                }
                .label {
                    font-weight: bold;
                    display: inline-block;
                    width: 180px;
                    color: #aabbee;
                }
                .details {
                    margin-top: 15px;
                    line-height: 1.8;
                    border-left: 4px solid var(--accent-color);
                    padding-left: 15px;
                    background-color: #25282d;
                    border-radius: 6px;
                    padding: 15px;
                    box-shadow: inset 0 0 8px rgba(0,0,0,0.3);
                }
            </style>
        </head>
        <body>
            <h1>Star Wars Data Explorer</h1>
    
            <div class='section'>
                <h2>🌍 Planètes</h2>
                <select id='planets'></select>
                <button onclick="fetchDetails('planets')">Voir détails</button>
                <div id='details-planets' class='details'></div>
            </div>
    
            <div class='section'>
                <h2>📽️ Films</h2>
                <select id='films'></select>
                <button onclick="fetchDetails('films')">Voir détails</button>
                <div id='details-films' class='details'></div>
            </div>
    
            <div class='section'>
                <h2>👤 Personnages</h2>
                <select id='people'></select>
                <button onclick="fetchDetails('people')">Voir détails</button>
                <div id='details-people' class='details'></div>
            </div>
    
            <div class='section'>
                <h2>🚀 Vaisseaux</h2>
                <select id='starships'></select>
                <button onclick="fetchDetails('starships')">Voir détails</button>
                <div id='details-starships' class='details'></div>
            </div>
    
            <div class='section'>
                <h2>🚗 Véhicules</h2>
                <select id='vehicles'></select>
                <button onclick="fetchDetails('vehicles')">Voir détails</button>
                <div id='details-vehicles' class='details'></div>
            </div>
    
            <div class='section'>
                <h2>🧬 Espèces</h2>
                <select id='species'></select>
                <button onclick="fetchDetails('species')">Voir détails</button>
                <div id='details-species' class='details'></div>
            </div>
    
            <script>
                const types = ['planets', 'films', 'people', 'starships', 'vehicles', 'species'];
    
                window.onload = () => {
                    types.forEach(async type => {
                        const res = await fetch(`https://swapi.tech/api/${type}`);
                        const data = await res.json();
                        const select = document.getElementById(type);
                        select.innerHTML = '';
                        const items = data.results || data.result || [];
                        items.forEach(item => {
                            const option = document.createElement('option');
                            option.value = item.uid || item.id;
                            option.textContent = item.name || item.title || 'Sans nom';
                            select.appendChild(option);
                        });
                    });
                };
    
                async function fetchDetails(type) {
                    const uid = document.getElementById(type).value;
                    const res = await fetch(`https://swapi.tech/api/${type}/${uid}`);
                    const data = await res.json();
                    const props = data.result.properties;
    
                    let html = `<h3 style='color:#fff;'>${props.name || props.title}</h3>`;
                    for (const key in props) {
                        const value = props[key];
    
                        // Si le champ est un tableau d'URLs (comme starships, characters, planets, vehicles, species)
                        if (Array.isArray(value) && value.length > 0 && typeof value[0] === 'string' && value[0].startsWith('https://')) {
                            html += `<p><span class='label'>${key.replace(/_/g, ' ')}:</span> <ul>`;
                            for (const url of value) {
                                try {
                                    const subRes = await fetch(url);
                                    const subData = await subRes.json();
                                    const name = subData.result.properties.name || subData.result.properties.title;
                                    html += `<li>${name}</li>`;
                                } catch (err) {
                                    html += `<li>Erreur de chargement</li>`;
                                }
                            }
                            html += `</ul></p>`;
                        } else {
                            html += `<p><span class='label'>${key.replace(/_/g, ' ')}:</span> ${value}</p>`;
                        }
                    }
                    document.getElementById(`details-${type}`).innerHTML = html;
                }
            </script>
        </body>
        </html>
        """;
    
        saveHtmlToFile(htmlContent, filePath);
    }
    
}
