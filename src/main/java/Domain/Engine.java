package Domain;

import lombok.Data;

@Data
public class Engine{
    private String code;
    private String compressorType;
    private String configuration;
    private int Cylinder;
    private String fuelType;
    private int horsepower;
    private Rpm rpm;
}
