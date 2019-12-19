/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author kwint
 */
public class ResLijstItem extends JPanel {
    
    private JLabel naamL = new JLabel();
    private JLabel momentL = new JLabel();
    
    public ResLijstItem(String naam, String moment) {
        setLayout(new GridLayout(1, 2));
        momentL.setText(moment + ": ");
        naamL.setText(" "+naam);
        add(momentL);
        add(naamL);
        this.setBackground(new Color(200, 200, 200));
        if(naam.equals("VRIJ")) {
            this.setBackground(new Color(230, 230, 230));
        }
    }
}
