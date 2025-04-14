package StarMain;

import org.json.JSONArray;
import org.json.JSONObject;

public class Printer {

    private final API api = new API(); // ← Ajout de "final" ✅
    // repository non utilisé donc soit à supprimer, soit à laisser avec final pour éliminer le hint
    private final GetRequestRepository repository = new GetRequestRepository(api); // ← Ajout de "final" ✅

    // on affiche les films
    public void printDetailsFilms(JSONArray results) {
        for (int i = 0; i < results.length(); i++) {
            JSONObject film = results.getJSONObject(i);
            System.out.println("Title : " + film.getJSONObject("properties").getString("title"));
            System.out.println("Episode number : " + film.getJSONObject("properties").getInt("episode_id"));
            System.out.println("Opening crawl : " + film.getJSONObject("properties").getString("opening_crawl"));
            System.out.println("Director : " + film.getJSONObject("properties").getString("director"));
            System.out.println("Producer : " + film.getJSONObject("properties").getString("producer"));
            System.out.println("Release Date : " + film.getJSONObject("properties").getString("release_date"));

            JSONArray characters = film.getJSONObject("properties").getJSONArray("characters");
            System.out.println("\nCharacters :");
            printArrayValues(characters, "name");

            JSONArray planets = film.getJSONObject("properties").getJSONArray("planets");
            System.out.println("\nPlanets :");
            printArrayValues(planets, "name");

            JSONArray starships = film.getJSONObject("properties").getJSONArray("starships");
            System.out.println("\nStarships :");
            printArrayValues(starships, "name");

            JSONArray vehicles = film.getJSONObject("properties").getJSONArray("vehicles");
            System.out.println("\nVehicles :");
            printArrayValues(vehicles, "name");

            JSONArray species = film.getJSONObject("properties").getJSONArray("species");
            System.out.println("\nSpecies :");
            printArrayValues(species, "name");

            System.out.println("\nFin du film !");
        }
    }

    // détails de la planète
    public void printDetailsPlanets(JSONArray planetresults) {
        for (int i = 0; i < planetresults.length(); i++) {
            JSONObject planet = planetresults.getJSONObject(i);
            System.out.println("Planet : " + planet.getString("name"));
            System.out.println("Rotation Period : " + planet.getString("rotation_period"));
            System.out.println("Orbital Period : " + planet.getString("orbital_period"));
            System.out.println("Diameter : " + planet.getString("diameter"));
            System.out.println("Gravity : " + planet.getString("gravity"));
            System.out.println("Terrain : " + planet.getString("terrain"));
            System.out.println("Surface water : " + planet.getString("surface_water"));
            System.out.println("Population : " + planet.getString("population"));

            JSONArray residents = planet.getJSONArray("residents");
            System.out.println("\nResidents :");
            printArrayValues(residents, "name");

            JSONArray films = planet.getJSONArray("films");
            System.out.println("\nFilms :");
            printArrayValues(films, "title");

            System.out.println("\n");
        }
    }

    public void printDetailsStarships(JSONArray starshipresults) {
        for (int i = 0; i < starshipresults.length(); i++) {
            JSONObject starship = starshipresults.getJSONObject(i);
            System.out.println("Name : " + starship.getString("name"));
            System.out.println("Model : " + starship.getString("model"));
            System.out.println("Manufacturer : " + starship.getString("manufacturer"));
            System.out.println("Cost in credits : " + starship.getString("cost_in_credits"));
            System.out.println("Length : " + starship.getString("length"));
            System.out.println("Max Atmosphering Speed : " + starship.getString("max_atmosphering_speed"));
            System.out.println("Crew : " + starship.getString("crew"));
            System.out.println("Passengers : " + starship.getString("passengers"));
            System.out.println("Cargo Capacity : " + starship.getString("cargo_capacity"));
            System.out.println("Consumables : " + starship.getString("consumables"));
            System.out.println("Hyperdrive Rating : " + starship.getString("hyperdrive_rating"));
            System.out.println("MGLT : " + starship.getString("MGLT"));
            System.out.println("Starship Class : " + starship.getString("starship_class"));

            JSONArray pilots = starship.getJSONArray("pilots");
            System.out.println("\nPilots :");
            printArrayValues(pilots, "name");

            JSONArray films = starship.getJSONArray("films");
            System.out.println("\nFilms :");
            printArrayValues(films, "title");

            System.out.println("\n");
        }
    }

    // ✅ On garde le paramètre key, même s’il n’est pas utilisé, pour ne rien casser
    private void printArrayValues(JSONArray jsonArray, String key) {
        for (int j = 0; j < jsonArray.length(); j++) {
            String value = jsonArray.getString(j);
            System.out.println(value);
        }
        if (jsonArray.length() == 0) {
            System.out.println("No results");
        }
    }
}
