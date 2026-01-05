package frontend;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    private MainFrame mainFrame;

    public UserPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());

        JPanel userPanel = new JPanel();
        userPanel.add(new JLabel("Zalogowano"));

        add(userPanel);
    }
}
