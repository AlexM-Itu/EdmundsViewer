package Controllers;

import Business.EdmundsClient;
import Domain.Make;

import java.util.List;

/**
 * Created by Alex on 4/10/16.
 */
public class EdmundsController {
    private EdmundsClient edmundsClient = new EdmundsClient();

    public List<Make> getEdmundsMakes (){
        return edmundsClient.getEdmundsMakes();
    }
}
