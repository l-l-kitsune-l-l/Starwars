package StarMain;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class ArgumentSwitcher {

    private static final API apiCalls = new API();
    private GetRequestRepository repository = new GetRequestRepository(apiCalls);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Printer printer = new Printer();

    public void switcher(String command, String searchquery) {

        JSONObject jsonObject;

        System.out.println("Méthode Switcher");
        System.out.println("command : " + command);
        System.out.println("searchQuery : " + searchquery);
        
        switch (command) {
            case "films":
            	  System.out.println("on a choisi 'films'");
            	jsonObject = repository.getAll("films", searchquery);
            	System.out.println("je suis arrivé ici !!!");
                JSONArray filmresults = jsonObject.getJSONArray("result");
                printer.printDetailsFilms(filmresults);
                break;
            case "planets":
                jsonObject = repository.getAll("planets", searchquery);
                JSONArray planetresults = jsonObject.getJSONArray("results");
                printer.printDetailsPlanets(planetresults);
                break;
            case "starships":
                jsonObject = repository.getAll("starships", searchquery);
                JSONArray starshipresults = jsonObject.getJSONArray("results");
                printer.printDetailsStarships(starshipresults);
                break;
            default:
                System.out.println(command + " is not an available command");
                break;
        }
    }
}
