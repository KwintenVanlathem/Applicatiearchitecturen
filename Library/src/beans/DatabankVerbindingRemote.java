/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author kwint
 */
@Remote
public interface DatabankVerbindingRemote {
    
    public List getMachines();
    public Object getMachine(String serie);
    public void newMachine(String serienummer, String opleiding, String omschrijving, String naam, String aankoopprijs, String huurprijs, String lokaal);
    public void updateMachine(String serienummer, String opleiding, String omschrijving, String naam, String aankoopprijs, String huurprijs, String lokaal);
    public String getDocentOpleiding(String username);
    public ArrayList getMachinesNamen();
    public ArrayList getMachineReservaties(String serie);
    public ArrayList getVrijeMomenten(String serie, Calendar vandaag);
    public ArrayList getReservatieMomenten(String serie, Calendar vandaag);
    public int getPrijs (String serienummer);
    public ArrayList getReservaties(String gebruiker);
    public Object getMoment(String serienummer,String jaar, String maand, String dag, String uur);
    public void voegVrijToe(String serienummer, String dag, String maand, String jaar, String uur);
    public void reserveer(String serienummer, String jaar, String maand, String dag, String uur, String gebruiker);
}
