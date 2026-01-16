package frontend;

import backend.ServiceRental;
import backend.ServiceVehicle;
import pojazd.Pojazd;
import pojazd.Rower;
import strategia.StrategiaCenowa;
import strategia.StrategiaDlugoterminowa;
import strategia.StrategiaDobowa;
import strategia.StrategiaMinutowa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VehicleDetailPanel extends JPanel {
    private MainFrame mainFrame;
    private ServiceVehicle serviceVehicle;
    private ServiceRental serviceRental;

    private Pojazd currentVehicle;

    private JLabel vehicleBrandModel;
    private JTextArea vehicleDetails;

    private JTextField dateStart;
    private JTextField dateEnd;

    private JLabel priceLabel = new JLabel();


    public VehicleDetailPanel(MainFrame mainFrame, ServiceVehicle serviceVehicle, ServiceRental serviceRental) {
        this.mainFrame = mainFrame;
        this.serviceVehicle= serviceVehicle;
        this.serviceRental = serviceRental;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        vehicleBrandModel = new JLabel("Vehicle Brand:");
        vehicleBrandModel.setFont(new Font("SansSerif", Font.PLAIN, 32));
        JButton returnButton = new JButton("Powrót");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.ChangeCard("MAIN");
            }
        });

        topPanel.add(vehicleBrandModel, BorderLayout.WEST);
        topPanel.add(returnButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        vehicleDetails = new JTextArea("Vehicle Details");
        vehicleDetails.setEditable(false);
        vehicleDetails.setLineWrap(true);
        vehicleDetails.setWrapStyleWord(true);
        vehicleDetails.setOpaque(false);
        vehicleDetails.setBorder(null);
        vehicleDetails.setFont(new Font("SansSerif", Font.PLAIN, 20));
        add(vehicleDetails, BorderLayout.CENTER);

        JPanel rentPanel  = new JPanel();
        rentPanel.setLayout(new FlowLayout());
        rentPanel.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
        rentPanel.add(new JLabel("Data początek(DD/MM/RRRR): "));
        dateStart = new JTextField(10);
        rentPanel.add(dateStart);
        rentPanel.add(new JLabel("Data koniec:"));
        dateEnd = new JTextField(10);
        rentPanel.add(dateEnd);
        JButton calculateButton = new JButton("Oblicz");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculatePrice();
            }
        });
        rentPanel.add(calculateButton);
        rentPanel.add(priceLabel);
        add(rentPanel, BorderLayout.SOUTH);

        JButton rentButton = new JButton("Wypożycz");
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rentVehicle();
            }
        });
        rentPanel.add(rentButton);

    }

    public void calculatePrice() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            Date startDate = sdf.parse(dateStart.getText());
            Date endDate = sdf.parse(dateEnd.getText());

            if(endDate.before(startDate)) {
                JOptionPane.showMessageDialog(this, "Data zwrotu nie może być wcześniejsza");
                return;
            }

            serviceRental.calculateRental(currentVehicle, startDate, endDate);
            
            priceLabel.setText(String.format("%.2f PLN", serviceRental.getLastPrice()));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Błędny format daty! Użyj formatu DD/MM/RRRR");
        }
    }

    private void function() {
        // Placeholder for additional functionality if needed in the future
    }


    private void rentVehicle() {
        if (serviceRental.getLastStrategy() == null) {
            JOptionPane.showMessageDialog(this, "Najpierw oblicz cenę!");
            return;
        }

        SimpleDateFormat sdfForService = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStartFormatted = sdfForService.format(serviceRental.getLastStartDate());
        String dateEndFormatted = sdfForService.format(serviceRental.getLastEndDate());
        
        serviceRental.rent(currentVehicle, dateStartFormatted, dateEndFormatted, serviceRental.getLastStrategy());
        
        JOptionPane.showMessageDialog(this, "Pojazd został wypożyczony!");
        
        serviceRental.clearCalculation();
        priceLabel.setText("");
        
        mainFrame.ChangeCard("MAIN");
    }

    public void getVehicle(Pojazd P) {
        this.currentVehicle = P;

        vehicleBrandModel.setText(currentVehicle.getMarka() + " " + currentVehicle.getModel());
        vehicleDetails.setText(P.toString());
    }
}
