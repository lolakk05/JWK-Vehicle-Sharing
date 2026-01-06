package frontend;

import app.Main;
import app.Session;
import pojazd.Pojazd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VehicleListPanel extends JPanel {
    private MainFrame mainFrame;
    private JPanel vehicleListPanel;

    public VehicleListPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        add(new JLabel("Vehicle List"), BorderLayout.NORTH);

        vehicleListPanel = new JPanel();
        vehicleListPanel.setLayout(new BoxLayout(vehicleListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(vehicleListPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Wyloguj się");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Session.logout();
                mainFrame.ChangeCard("LOGIN");
            }
        });
        add(backButton, BorderLayout.SOUTH);
        refreshList();
    }

    public void refreshList() {
        vehicleListPanel.removeAll();

        ArrayList<Pojazd> pojazdy = Main.pojazdy;

        for (Pojazd p : pojazdy) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

            JLabel label = new JLabel(p.getMarka() + " " + p.getModel() + " (" + p.getStatus() + ")");
            JButton btnDetails = new JButton("Szczegóły");

            row.add(label);
            row.add(btnDetails);

            vehicleListPanel.add(row);
        }
        vehicleListPanel.revalidate();
        vehicleListPanel.repaint();
    }
}
