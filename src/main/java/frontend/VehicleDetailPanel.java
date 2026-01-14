package frontend;

import app.Main;
import pojazd.Pojazd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class VehicleDetailPanel extends JPanel {
    private MainFrame mainFrame;
    private Main appLogic;

    private Pojazd currentVehicle;

    private JLabel vehicleBrandModel;
    private JTextArea vehicleDetails;

    private JTextField dateStart;
    private JTextField dateEnd;

    private JLabel priceLabel = new JLabel();


    public VehicleDetailPanel(MainFrame mainFrame, Main appLogic) {
        this.mainFrame = mainFrame;
        this.appLogic = appLogic;

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

            long diffInDays = TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);

            if(diffInDays == 0) diffInDays = 1;

            double price = diffInDays * currentVehicle.getCenaBazowa();
            priceLabel.setText(String.format("%.2f", price));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Błędny format daty! Użyj formatu DD/MM/RRRR");
        }
    }

    public void getVehicle(Pojazd P) {
        this.currentVehicle = P;

        vehicleBrandModel.setText(currentVehicle.getMarka() + " " + currentVehicle.getModel());
        vehicleDetails.setText(P.toString());
    }
}
