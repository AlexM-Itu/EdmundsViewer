package forms;

import Controllers.EdmundsController;
import Domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.List;

/**
 * Created by Alex on 4/10/16.
 */
public class EdmundsViewer extends JFrame{
    private JComboBox makeComboBox;
    private JComboBox modelComboBox;
    private JComboBox yearComboBox;
    private JComboBox styleComboBox;
    private JPanel rootPanel;
    private JLabel lblMake;
    private JLabel lblModel;
    private JLabel lblYear;
    private JLabel lblStyle;
    private JLabel lblTrim;
    private JLabel lblSize;
    private JLabel lblType;
    private JLabel lblDrivenWheels;
    private JLabel lblNumOfDoors;
    private JPanel generalInfo;
    private JPanel priceInfo;
    private JLabel lblPrivateParty;
    private JLabel lblRetail;
    private JLabel lblTradeIn;
    private JPanel mainDetailPanel;
    private JPanel enginePanel;
    private JLabel lblEngineCode;
    private JLabel lblCompressorType;
    private JLabel lblEngineConfiguration;
    private JLabel lblCylinersNumber;
    private JLabel lblFueltype;
    private JLabel lblHorsepower;
    private JLabel lblTorque;
    private JPanel transmissionPanel;
    private JLabel lblTransmissionName;
    private JLabel lblNumberOfSpeeds;
    private JLabel lblTransmissionType;
    private JPanel generalView;

    private EdmundsController controller = new EdmundsController();

    public EdmundsViewer(){
        super("Edmunds Viewer");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(rootPanel);
        setSize(1000, 700);
        setMinimumSize(new Dimension(700, 700));

        List<Make> makes = controller.getEdmundsMakes();

        for(Make make: makes)
            makeComboBox.addItem(make);

        loadModels();
        loadModelYears();
        loadStyles();
        loadStyleDetails();

        makeComboBox.addActionListener(e -> {
              loadModels();
              loadStyles();
              loadStyleDetails();
        });

        modelComboBox.addActionListener(e-> {
            loadModelYears();
            loadStyles();
        });

        yearComboBox.addActionListener(e-> {
            loadStyles();
            loadStyleDetails();
        });
    }

    public void loadModels(){
        modelComboBox.removeAllItems();
        Object selectedModels = makeComboBox.getSelectedItem();
        if (selectedModels != null){
            for(Model model: ((Make) selectedModels).getModels())
                modelComboBox.addItem(model);

            modelComboBox.setEnabled(true);
        }
    }

    public void loadModelYears(){
        yearComboBox.removeAllItems();
        Object selectedYear = modelComboBox.getSelectedItem();
        if(selectedYear != null){
            for(ModelYear year: ((Model) selectedYear).getYears())
                yearComboBox.addItem(year);

            yearComboBox.setEnabled(true);
        }
    }

    private void loadStyles() {
        Object selectedMake = makeComboBox.getSelectedItem();
        Object selectedModel = modelComboBox.getSelectedItem();
        Object selectedYear = yearComboBox.getSelectedItem();

        if (selectedMake != null && selectedModel != null && selectedYear != null){
            styleComboBox.removeAllItems();

            List<Style> availableStyles = controller.getEdmundsStyles(((Make)selectedMake).getNiceName(), ((Model) selectedModel).getNiceName(), ((ModelYear)selectedYear).getYear());
            for(Style style : availableStyles)
                styleComboBox.addItem(style);

            styleComboBox.setEnabled(true);
        }
    }

    private void loadStyleDetails (){
        Object selectedStyle = styleComboBox.getSelectedItem();
        if (selectedStyle != null){
            StyleDetail styleDetail = controller.getEdmundsStyleDetails(((Style)selectedStyle).getId());

            lblMake.setText(styleDetail.getMake().getName());
            lblModel.setText(styleDetail.getModel().getName());
            lblYear.setText(Integer.toString(styleDetail.getYear().getYear()));
            lblStyle.setText(styleDetail.getVehicleStyle());
            lblTrim.setText(styleDetail.getTrim());
            lblSize.setText(styleDetail.getVehicleSize());
            lblNumOfDoors.setText(Integer.toString(styleDetail.getNumOfDoors()));

            lblPrivateParty.setText(Integer.toString(styleDetail.getPrice().getUsedPrivateParty()));
            lblRetail.setText(Integer.toString(styleDetail.getPrice().getUsedTmvRetail()));
            lblTradeIn.setText(Integer.toString(styleDetail.getPrice().getUsedTradeIn()));

            lblEngineCode.setText(styleDetail.getEngine().getCode());
            lblCompressorType.setText(styleDetail.getEngine().getCompressorType());
            lblFueltype.setText(styleDetail.getEngine().getFuelType());
            lblCylinersNumber.setText(Integer.toString(styleDetail.getEngine().getCylinder()));
            lblHorsepower.setText(Integer.toString(styleDetail.getEngine().getHorsepower()));
            lblTorque.setText(Integer.toString(styleDetail.getEngine().getRpm().getTorque()));

            lblTransmissionName.setText(styleDetail.getTransmission().getName());
            lblNumberOfSpeeds.setText(Integer.toString(styleDetail.getTransmission().getNumberOfSpeeds()));
            lblTransmissionType.setText(styleDetail.getTransmission().getTransmissionType());

            mainDetailPanel.setVisible(true);
        }
    }
}
