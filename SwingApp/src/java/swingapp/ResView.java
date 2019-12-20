/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kwint
 */
public class ResView extends JFrame {
    
    public ResLijstItem item;
    public JPanel header;
    
    public ResView(ArrayList reservaties) {
        Container pane = getContentPane();
        setLayout(new GridLayout(reservaties.size()+1,1));
        
        header = new JPanel();
        JLabel naamL = new JLabel("Naam");
        JLabel momentL = new JLabel("Moment");
        header.setLayout(new GridLayout(1,2));
        header.add(momentL);
        header.add(naamL);
        add(header);
        
        String naam;
        String moment;
        String r[];
        for(Object o : reservaties) {
            r = (String[]) o;
            naam = r[1];
            moment = r[0];
            item = new ResLijstItem(naam, moment);
            pane.add(item);
        }
        
        pack();
        setVisible(true);
    }
}
