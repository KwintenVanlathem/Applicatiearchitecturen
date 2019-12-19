/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author kwint
 */
public class ResView extends JFrame {
    
    public ResLijstItem item;
    
    public ResView(ArrayList reservaties) {
        Container pane = getContentPane();
        setLayout(new GridLayout(reservaties.size(),1));
        
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
