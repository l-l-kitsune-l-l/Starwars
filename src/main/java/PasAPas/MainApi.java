package PasAPas;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainApi {

    public static void main(String[] args) {
        // Création de l'instance de l'API
        API apiCalls = new API();
        // Création de l'instance de l'imprimante
        Printer printer = new Printer();

        try {
            // Appel à l'API pour récupérer les données des planètes
            JSONObject jsonObject = apiCalls.getPlanets("Alderaan");
            // Extraction des résultats des planètes
            JSONArray planetresults = jsonObject.getJSONArray("result");
            
            System.out.println(planetresults);
            // Impression des détails des planètes
            printer.printDetailsPlanets(planetresults);
            
            // Création d'une instance de HtmlPrinter
            HtmlPrinter htmlPrinter = new HtmlPrinter();

            // Générer le code HTML
            String htmlContent = htmlPrinter.printHtmlDetailsPlanets(jsonObject);

            // Spécifier le chemin du fichier où vous souhaitez enregistrer le contenu HTML
            String filePath = "C:/Users/Pascal/Desktop/page.html";

            // Enregistrer le contenu HTML dans le fichier spécifié
            htmlPrinter.saveHtmlToFile(htmlContent, filePath);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
