package Domain;

import lombok.Data;

/**
 * Created by Alex on 4/10/16.
 */
@Data
public class Style {
    private int id;
    private String name;
    private Submodel submodel;
    private String trim;
}
