package Controllers;

import Business.EdmundsClient;
import Domain.Make;
import Domain.Style;

import java.util.List;

/**
 * Created by Alex on 4/10/16.
 */
public class EdmundsController {
    private EdmundsClient edmundsClient = new EdmundsClient();

    public List<Make> getEdmundsMakes (){
        return edmundsClient.getEdmundsMakes();
    }

    public List<Style> getEdmundsStyles (String make, String model, int year ){
        return edmundsClient.getEdmundsStyles(make, model, year);
    }
}
