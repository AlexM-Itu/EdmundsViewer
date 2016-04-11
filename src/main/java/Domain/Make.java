package Domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Alex on 4/10/16.
 */
@Data
public class Make {
    private int id;
    private List<Model> models;
    private String name;
    private String niceName;

    public String toString(){
        return name;
    }
}
