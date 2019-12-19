/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import java.util.List;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
//import beans.

/**
 *
 * @author kwint
 */
public class MainView extends JFrame {
    
    public OverzichtLijstItem item;
    
    public MainView(Main c, ArrayList machines) {
        Container pane = getContentPane();
        setLayout(new GridLayout(machines.size(),1));
        
        String naam;
        String id;
        String m[];
        for (Object o : machines) {
            m = (String[]) o;
            naam = m[0];
            id = m[1];
            item = new OverzichtLijstItem(c, id, naam);
            pane.add(item);
        }
        
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
