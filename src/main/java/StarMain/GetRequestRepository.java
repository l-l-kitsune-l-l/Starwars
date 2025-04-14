package StarMain;

import org.json.JSONObject;

public class GetRequestRepository {

    private final API api;

    public GetRequestRepository(API api) {
        this.api = api;
    }

    public JSONObject getAll(String path, String searchquery) {
        try {
            System.out.println("📥 Repository getAll → path = " + path + ", search = " + searchquery);
            JSONObject jsonObject = api.getBuilder(path, searchquery);
            System.out.println("📤 Réponse API : " + jsonObject);
            return jsonObject;
        } catch (Exception e) {
            System.err.println("❌ Erreur dans getAll pour path = " + path);
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject innerRequest(String uri) {
        try {
            System.out.println(" Appel interne à l'URL : " + uri);
            JSONObject jsonObject = api.innerRequest(uri);
            System.out.println(" Réponse interne : " + jsonObject);
            return jsonObject;
        } catch (Exception e) {
            System.err.println(" Erreur dans innerRequest pour URL : " + uri);
            e.printStackTrace();
            return null;
        }
    }
}
