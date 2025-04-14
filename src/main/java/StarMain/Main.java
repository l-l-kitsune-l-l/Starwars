package StarMain;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArgumentSwitcher argumentSwitcher = new ArgumentSwitcher();

        try (Scanner scanner = new Scanner(System.in)) {
            String accessPoint;

            System.out.println("Bienvenue dans StarWars Explorer ✨");
            System.out.println("Tapez 'films', 'planets' ou 'starships' pour explorer, ou 'exit' pour quitter.");

            while (true) {
                System.out.print("\nCommande (films/planets/starships/exit) : ");
                accessPoint = scanner.nextLine().trim().toLowerCase();

                if (accessPoint.equals("exit")) {
                    System.out.println("👋 À bientôt !");
                    break;
                }

                if (!accessPoint.equals("films") && !accessPoint.equals("planets") && !accessPoint.equals("starships")) {
                    System.out.println("❌ Commande inconnue. Réessayez.");
                    continue;
                }

                System.out.print("Critère de recherche (ou Entrée pour tout afficher) : ");
                String searchQuery = scanner.nextLine().trim();
                System.out.println("🔍 Recherche de " + accessPoint + (searchQuery.isEmpty() ? " (tout)" : " pour : " + searchQuery));

                argumentSwitcher.switcher(accessPoint, searchQuery);
            }
        }
    }
}
