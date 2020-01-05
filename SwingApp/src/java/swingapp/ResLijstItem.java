package swingapp;

import java.awt.*;
import javax.swing.*;

public class ResLijstItem extends JPanel {
    
    private JLabel naamL = new JLabel();
    private JLabel momentL = new JLabel();
    
    public ResLijstItem(String naam, String moment) {
        setLayout(new GridLayout(1, 2));
        momentL.setText(moment + ": ");
        naamL.setText(" "+naam);
        add(momentL);
        add(naamL);
    }
}