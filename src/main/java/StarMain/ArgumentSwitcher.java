package StarMain;

import org.json.JSONArray;
import org.json.JSONObject;

public class ArgumentSwitcher {

    private static final API apiCalls = new API();
    private final GetRequestRepository repository = new GetRequestRepository(apiCalls);
    private final Printer printer = new Printer();

    public void switcher(String command, String searchquery) {
        System.out.println("Méthode Switcher");
        System.out.println("command : " + command);
        System.out.println("searchQuery : " + searchquery);
    
        switch (command) {
            case "films" -> {
                System.out.println("on a choisi 'films'");
                JSONObject jsonObject = repository.getAll("films", searchquery);
                JSONArray filmresults = jsonObject.getJSONArray("result");
                printer.printDetailsFilms(filmresults);
            }
            case "planets" -> {
                JSONObject jsonObject = repository.getAll("planets", searchquery);
                JSONArray planetresults = jsonObject.getJSONArray("results");
                printer.printDetailsPlanets(planetresults);
            }
            case "starships" -> {
                JSONObject jsonObject = repository.getAll("starships", searchquery);
                JSONArray starshipresults = jsonObject.getJSONArray("results");
                printer.printDetailsStarships(starshipresults);
            }
            default -> System.out.println(command + " is not an available command");
        }
    }

    
}
