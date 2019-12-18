/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import java.util.List;
import java.awt.*;
import javax.swing.*;
//import beans.

/**
 *
 * @author kwint
 */
public class MainView extends JFrame {
    
    public OverzichtLijstItem item;
    
    public MainView(Main c, List[] machines) {
        Container pane = getContentPane();
        setLayout(new GridLayout(1,1));
        
        
        for (Object m : machines[0]) {
            System.out.println(m);
        }
        
        
        item = new OverzichtLijstItem(c, "1234", "Test");
        pane.add(item);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
