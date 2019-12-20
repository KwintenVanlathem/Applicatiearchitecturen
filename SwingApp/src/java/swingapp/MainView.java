/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author kwint
 */
public class MainView extends JFrame {
    
    public OverzichtLijstItem item;
    public JPanel header;
    
    public MainView(Main c, ArrayList machines) {
        Container pane = getContentPane();
        setLayout(new GridLayout(machines.size()+1,1));
        
        header = new JPanel();
        JLabel serieL = new JLabel("Serienummer");
        JLabel naamL = new JLabel("Naam");
        JLabel knopL = new JLabel(" ");
        header.setLayout(new GridLayout(1,3));
        header.add(serieL);
        header.add(naamL);
        header.add(knopL);
        add(header);
        
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
