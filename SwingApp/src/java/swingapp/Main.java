/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

import beans.DatabankVerbindingRemote;
import javax.ejb.EJB;

/**
 *
 * @author r0661567
 */
public class Main {

    private static MainView frame;

    @EJB private static DatabankVerbindingRemote verbinding;
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        frame = new MainView(this, verbinding.getMachinesNamen());
    }
    
    public void toonResOverzicht(String serie) {
        System.out.println(serie);
        String m[];
        for (Object o : verbinding.getMachineReservaties(serie)) {
            m = (String[]) o;
            System.out.println(m[0]);
            System.out.println(m[1]);
        }
    }
}
