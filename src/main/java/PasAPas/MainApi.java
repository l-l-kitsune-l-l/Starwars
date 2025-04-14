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
            JSONObject jsonObject = apiCalls.getPlanets("Alderaan");
            JSONArray planetresults = jsonObject.getJSONArray("result");
        
            System.out.println(planetresults);
        
            printer.printDetailsPlanets(planetresults);
        
            HtmlPrinter htmlPrinter = new HtmlPrinter();
            String htmlContent = htmlPrinter.printHtmlDetailsPlanets(jsonObject);
        
            String filePath = "C:/Users/Pascal/Desktop/page.html";
            htmlPrinter.saveHtmlToFile(htmlContent, filePath);
        
        } catch (Exception e) {
            System.err.println("Erreur dans MainApi : " + e.getMessage());
            e.printStackTrace();
        }
        
    }
}
