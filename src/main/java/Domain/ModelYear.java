package Domain;

import lombok.Data;

/**
 * Created by Alex on 4/10/16.
 */
@Data
public class ModelYear {
    private int id;
    private int year;

    public String toString(){
        return Integer.toString(year);
    }
}
