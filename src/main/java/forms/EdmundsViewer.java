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
    private JProgressBar progressBar1;
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

            generalView.setVisible(true);
        }
    }
}
