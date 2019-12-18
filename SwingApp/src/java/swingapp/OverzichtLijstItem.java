/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author kwint
 */
public class OverzichtLijstItem extends JPanel {
    
    private JButton resKnop = new JButton("Bekijk reservatie overzicht");
    private JLabel naamL = new JLabel();
    private JLabel serieL = new JLabel();
    Main controller;
    
    public OverzichtLijstItem(Main c, String serie, String naam) {
        controller = c;
        setLayout(new GridLayout(1,3));
        serieL.setText(serie);
        naamL.setText(naam);
        add(serieL);
        add(naamL);
        add(resKnop);
        resKnop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                controller.toonResOverzicht(serieL.getText());
            }
        });
    }
}
