/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import beans.DatabankVerbindingRemote;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author r0661567
 */
public class Main {

    private static Frame frame;
    private static MainView f;

    @EJB private static DatabankVerbindingRemote verbinding;
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        //verbinding = new Databank();
        List machines[] = verbinding.getMachinesNamen();
        f = new MainView(this, machines);
    }
    
    public void toonResOverzicht(String serie) {
        System.out.println(serie);
    }
}
