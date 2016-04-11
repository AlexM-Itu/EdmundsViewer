package Domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Alex on 4/10/16.
 */
@Data
public class Model {
    private String id;
    private String name;
    private String niceName;
    private List<ModelYear> years;

    public String toString(){
        return name;
    }
}
