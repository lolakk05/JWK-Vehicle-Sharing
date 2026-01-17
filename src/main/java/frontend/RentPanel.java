package frontend;

import backend.ServiceRental;
import backend.ServiceVehicle;
import pojazd.Pojazd;
import strategia.StrategiaDobowa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentPanel extends JPanel {
    private MainFrame mainFrame;
    private ServiceVehicle serviceVehicle;
    private ServiceRental serviceRental;

    private Pojazd currentVehicle;

    private JLabel vehicleBrandModel;
    private JTextArea vehicleDetails;

    private JTextField dateStart;
    private JTextField dateEnd;

    private JLabel priceLabel = new JLabel();


    public RentPanel(MainFrame mainFrame, ServiceVehicle serviceVehicle, ServiceRental serviceRental) {
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
        vehicleDetails = new JTextArea("Vehicle details...");
        vehicleDetails.setEditable(false);
        vehicleDetails.setLineWrap(true);
        vehicleDetails.setWrapStyleWord(true);
        vehicleDetails.setOpaque(false);
        vehicleDetails.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        vehicleDetails.setFont(new Font("SansSerif", Font.PLAIN, 20));
        add(vehicleDetails, BorderLayout.CENTER);

        JPanel rentPanel = new JPanel();
        rentPanel.setLayout(new GridBagLayout());
        rentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        rentPanel.add(new JLabel("Data początek (DD/MM/RRRR):"), gbc);

        gbc.gridx = 1;
        dateStart = new JTextField(12);
        rentPanel.add(dateStart, gbc);

        gbc.gridx = 2;
        rentPanel.add(new JLabel("Data koniec (DD/MM/RRRR):"), gbc);

        gbc.gridx = 3;
        dateEnd = new JTextField(12);
        rentPanel.add(dateEnd, gbc);

        gbc.gridx = 4;
        JButton calculateButton = new JButton("Oblicz cenę");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculatePrice();
            }
        });
        rentPanel.add(calculateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        priceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        rentPanel.add(priceLabel, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 2;
        JButton rentButton = new JButton("Wypożycz pojazd");
        rentButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rentVehicle();
            }
        });
        rentPanel.add(rentButton, gbc);

        add(rentPanel, BorderLayout.SOUTH);

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

    private void rentVehicle() {
        if (serviceRental.getLastStrategy() == null) {
            JOptionPane.showMessageDialog(this, "Najpierw oblicz cenę!");
            return;
        }

        SimpleDateFormat sdfForService = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStartFormatted = sdfForService.format(serviceRental.getLastStartDate());
        String dateEndFormatted = sdfForService.format(serviceRental.getLastEndDate());
        
        if(serviceRental.rent(currentVehicle, dateStartFormatted, dateEndFormatted, serviceRental.getLastStrategy())) {
            serviceRental.clearCalculation();
            priceLabel.setText("");
            
            mainFrame.ChangeCard("RENT");
        };
    }

    public void getVehicle(Pojazd P) {
        this.currentVehicle = P;

        vehicleBrandModel.setText(currentVehicle.getMarka() + " " + currentVehicle.getModel());
        vehicleDetails.setText(P.toString());
    }
}
