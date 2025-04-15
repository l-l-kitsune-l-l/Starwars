package PasAPas;

import java.awt.Desktop;
import java.io.File;

public class MainApi {

    public static void main(String[] args) {
        try {
            // Créer une instance de HtmlPrinter
            HtmlPrinter htmlPrinter = new HtmlPrinter();

            // Chemin du fichier à générer
            String fullPath = "interactive_swapi_all.html";

            // Générer la page HTML complète
            htmlPrinter.saveFullInteractivePage(fullPath);

            // Ouvrir automatiquement dans le navigateur
            File htmlFile = new File(fullPath);
            if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
                System.out.println("✅ Page interactive SWAPI ouverte dans le navigateur.");
            } else {
                System.err.println("❌ Le fichier HTML n'a pas été trouvé.");
            }

        } catch (Exception e) {
            System.err.println("❌ Erreur dans MainApi : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
