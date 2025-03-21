package StarMain;
import org.json.JSONObject;

public class GetRequestRepository {

    private API api;

    public GetRequestRepository(API api) {
        this.api = api;
    }

    public JSONObject getAll(String path, String searchquery) {
        JSONObject jsonObject = null;
        try {
        	System.out.println("Repository getALL " + path + "   " + searchquery);
            jsonObject = api.getBuilder(path, searchquery);
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

//    public JSONObject innerRequest(String uri) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject = api.innerRequest(uri);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }
}
