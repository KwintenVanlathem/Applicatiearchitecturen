package swingapp;

import beans.DatabankVerbindingRemote;
import javax.ejb.EJB;


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
        //System.out.println("Toon Reservaties van " + serie);    //Debug info
        new ResView(verbinding.getMachineReservaties(serie));
    }
}
