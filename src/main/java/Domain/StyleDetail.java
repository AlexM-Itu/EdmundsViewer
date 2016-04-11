package Domain;

import lombok.Data;

/**
 * Created by Alex on 4/10/16.
 */
@Data
public class StyleDetail {
    private String primaryBodyType;
    private String vehicleSize;
    private String vehicleStyle;
    //todo colors
    private String drivenWheels;
    private Engine engine;
    private Make make;
    private Model model;
    private ModelYear year;
    private int numOfDoors;
    private Price price;
    private Transmission transmission;
    private String trim;
}

