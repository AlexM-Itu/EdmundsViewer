package Business;

import Domain.Make;
import Domain.Style;
import Domain.StyleDetail;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 4/10/16.
 */
public class EdmundsClient {
    private Client client = Client.create();
    private final String EDMUNDS_API_KEY = "xr3hykfbfwrkvemabbgs65p9";

    public List<Make> getEdmundsMakes(){
        WebResource webResource = client.resource("https://api.edmunds.com/api/vehicle/v2/makes?fmt=json&api_key=" + EDMUNDS_API_KEY);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200)
            throw new RuntimeException("Failed to get Edmunds makes: HTTP error code : " + response.getStatus());

       String result =  response.getEntity(String.class);
       return new Gson().fromJson(result,EdmundsMakesResult.class).getMakes();
    }

    public List<Style> getEdmundsStyles (String make, String model, int year){
        WebResource webResource = client.resource("http://api.edmunds.com/api/vehicle/v2/" + make + "/" + model + "/" + year + "?fmt=json&api_key=" + EDMUNDS_API_KEY);

        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200)
            throw new RuntimeException("Failed to get Edmunds styles: HTTP error code : " + response.getStatus());

        String result =  response.getEntity(String.class);
        return new Gson().fromJson(result,EdmundsStylesResult.class).getStyles();
    }

    public StyleDetail getStyleDetails (int styleId){
        WebResource webResource = client.resource("https://api.edmunds.com/api/vehicle/v2/styles/"+styleId+"?fmt=json&view=full&api_key=" + EDMUNDS_API_KEY);

        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200)
            throw new RuntimeException("Failed to get Edmunds style details: HTTP error code : " + response.getStatus());

        String result =  response.getEntity(String.class);
        return new Gson().fromJson(result,StyleDetail.class);
    }
}
