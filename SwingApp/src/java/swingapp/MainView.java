package swingapp;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainView extends JFrame {
    
    private OverzichtLijstItem item;
    
    private final JPanel header = new JPanel();;
    private final JLabel serieL = new JLabel("Serienummer");
    private final JLabel naamL = new JLabel("Naam");
    private final JLabel knopL = new JLabel();
    
    public MainView(Main c, ArrayList machines) {
        Container pane = getContentPane();
        setLayout(new GridLayout(machines.size()+1,1));
        
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
