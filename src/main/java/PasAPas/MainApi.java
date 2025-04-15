package PasAPas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Desktop;
import java.io.File;

public class MainApi {

    public static void main(String[] args) {
        API apiCalls = new API();
        Printer printer = new Printer();

        try {
            // Appel à l’API SWAPI pour les planètes
            JSONObject jsonObject = apiCalls.getPlanets("Alderaan");
            JSONArray planetresults = jsonObject.getJSONArray("result");

            // Affichage console des détails
            System.out.println(planetresults);
            printer.printDetailsPlanets(planetresults);

            // Instancier le générateur HTML
            HtmlPrinter htmlPrinter = new HtmlPrinter();

            // ✅ Génération de la page interactive
            String interactiveFilePath = "interactive_planets.html";
            htmlPrinter.saveInteractivePlanetsPage(interactiveFilePath);

            // ✅ Ouverture automatique dans le navigateur
            File htmlFile = new File(interactiveFilePath);
            if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
                System.out.println("✅ Page interactive ouverte dans le navigateur !");
            } else {
                System.err.println("❌ Fichier HTML introuvable : " + interactiveFilePath);
            }

            // ✅ Génération de la page statique
            String staticHtmlContent = htmlPrinter.printHtmlDetailsPlanets(jsonObject);
            String staticFilePath = "C:/Users/tekoh/Projet Programmation/html/page.html";
            htmlPrinter.saveHtmlToFile(staticHtmlContent, staticFilePath);

        } catch (Exception e) {
            System.err.println("Erreur dans MainApi : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
