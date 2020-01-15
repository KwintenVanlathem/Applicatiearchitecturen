package swingapp;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResView extends JFrame {
    
    private ResLijstItem item;
    
    private final JPanel header = new JPanel();
    private final JLabel naamL = new JLabel(" Naam");
    private final JLabel momentL = new JLabel("Moment");
    
    public ResView(ArrayList reservaties) {
        Container pane = getContentPane();
        setLayout(new GridLayout(reservaties.size()+1,1));
        
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
