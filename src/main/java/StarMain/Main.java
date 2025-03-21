package StarMain;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer le point d'accès que vous souhaitez afficher (films, planets, starships) :");
        String accessPoint = scanner.nextLine();
        System.out.println("accessPoint : " + accessPoint);
        
        String searchQuery = null;
        if (accessPoint.equals("films") || accessPoint.equals("planets") || accessPoint.equals("starships")) {
            System.out.println("Veuillez entrer votre critère de recherche (ou appuyez sur Entrée pour afficher tout) :");
            searchQuery = scanner.nextLine();
            System.out.println("searchQuery : " + searchQuery);
        } else {
            System.out.println("Point d'accès non valide.");
            scanner.close();
            return;
        }
        scanner.close();

        // Instanciez ArgumentSwitcher et traitez les arguments saisis par l'utilisateur
        ArgumentSwitcher argumentSwitcher = new ArgumentSwitcher();
        argumentSwitcher.switcher(accessPoint, searchQuery);
    }
}
