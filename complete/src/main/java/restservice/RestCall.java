package restservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;

import org.json.*;

import javax.servlet.http.HttpUtils;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.net.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class RestCall {

    final String BASEURL = "https://vehicle-api.3rdeyecam.com/tem/vehicles/";
    String fullURL;

    String path;

    String params;

    Credential thirdEyeCreds = new Credential("3rdEyeCreds.txt");

    Object json;

    /**
     *
     * @param type
     * @param params
     * @param path
     */
    public RestCall(String type, String params, String path) throws IOException {

        super();

        this.path = path;
        this.params = params;
        fullURL = BASEURL + path;

        if (type.toUpperCase().equals("GET")) {
            doGetRequest();
        } else if (type.toUpperCase().equals("POST")) {
            doPostRequest();
        }
    }

    public static void main(String[] args) throws IOException {
        //VehicleList myVehicles = new VehicleList("Orlando (100)");
    }

    private void doGetRequest() throws IOException {
        HttpClient restClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(this.fullURL + params);

        getRequest.addHeader("Authorization", thirdEyeCreds.getBase64Enc());

        String getResponse = null;
        //HttpEntity responseEntity = null;

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        try {
            getResponse = restClient.execute(getRequest, responseHandler);

            Object json;

            if (getResponse.charAt(0) == '[') {
                json = new JSONArray(getResponse);
            } else {
                json = new JSONObject(getResponse);
            }

            this.json = json;

        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(responseHandler);
        //System.out.println(getResponse);

        //HashMap[] json = (HashMap[]) (responseEntity);

        //String responseAsString = EntityUtils.toString(responseEntity);

        //return null;
    }

    public void doPostRequest() {

    }

    public Object getJson() {
        return json;
    }

}
