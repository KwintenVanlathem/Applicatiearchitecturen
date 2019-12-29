/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kwint
 */
@Stateless
public class DatabankVerbinding implements DatabankVerbindingRemote {

    @PersistenceContext private EntityManager em;
    
    public List getMachines() {
        List lijst = em.createNamedQuery("Machines.findAll").getResultList();
        return lijst;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Object getMachine(String serie) {
        return em.createNamedQuery("Machines.findBySerienummer").setParameter("serienummer", new BigDecimal(serie)).getSingleResult();
    }
    
    public void newMachine(String serienummer, String opleiding, String omschrijving, String naam, String aankoopprijs, String huurprijs, String lokaal) {
        Machines m = new Machines();
        
       // m.setReservatiesCollection(reservatiesCollection);  //geen idee wat dit doet
        m.setSerienummer(new BigDecimal(serienummer));
        m.setOpleiding(opleiding);
        m.setOmschrijving(omschrijving);
        m.setNaam(naam);
        m.setLokaal(lokaal);
        m.setHuurprijs(new BigInteger(huurprijs));
        m.setAankoopprijs(new BigInteger(aankoopprijs));
        
        em.persist(m);
    }
    
    public void updateMachine(String serienummer, String opleiding, String omschrijving, String naam, String aankoopprijs, String huurprijs, String lokaal) {
        Machines m = (Machines) getMachine(serienummer);
        
       // m.setReservatiesCollection(reservatiesCollection);  //geen idee wat dit doet
        m.setSerienummer(new BigDecimal(serienummer));
        m.setOpleiding(opleiding);
        m.setOmschrijving(omschrijving);
        m.setNaam(naam);
        m.setLokaal(lokaal);
        m.setHuurprijs(new BigInteger(huurprijs));
        m.setAankoopprijs(new BigInteger(aankoopprijs));
        
        em.persist(m);
    }
    
    public String getDocentOpleiding(String username) {
        Docenten d = (Docenten) em.createNamedQuery("Docenten.findByNaam").setParameter("naam", username).getSingleResult();
        return d.getOpleiding();
    }
    
    
    public ArrayList getMachinesNamen() {
        ArrayList lijst = new ArrayList();
        for (Object m : getMachines()) {
            String pair[] = new String[2];
            pair[0] = (((Machines) m).getNaam());
            pair[1] = (((Machines) m).getSerienummer()).toString();
            lijst.add(pair);
        }
        return lijst;
    }
    
    public ArrayList getMachineReservaties(String serie) {
        List queryres = em.createNamedQuery("Reservaties.findBySerienummer").setParameter("serienummer", new BigDecimal(serie)).getResultList();
        ArrayList lijst = new ArrayList();
        for (Object r : queryres) {
            String res[] = new String[2];
            res[0] = ((Reservaties) r).getReservatiesPK().getMoment().toString();
            res[1] = ((Reservaties) r).getGebruiker().getGebruikersnaam();
            lijst.add(res);
        }
        return lijst;
    }
    
    public ArrayList getVrijeMomenten(String serie) {
        List queryres = em.createNamedQuery("Reservaties.findFreeMoment").setParameter("serienummer", new BigDecimal(serie)).getResultList();
        ArrayList lijst = new ArrayList();
        for (Object r : queryres) {
            String res[] = new String[3];
            res[0] = "Vrij";
            res[1] = Integer.toString(((Reservaties) r).getReservatiesPK().getMoment().get(Calendar.DAY_OF_WEEK));
            res[2] = Integer.toString(((Reservaties) r).getReservatiesPK().getMoment().get(Calendar.HOUR_OF_DAY));
            lijst.add(res);
        }
        return lijst;
    }
    public ArrayList getReservatieMomenten(String serie) {
        List queryres = em.createNamedQuery("Reservaties.findBySerienummer").setParameter("serienummer", new BigDecimal(serie)).getResultList();
        ArrayList lijst = new ArrayList();
        Calendar datum = Calendar.getInstance();
        int week = datum.get(Calendar.WEEK_OF_YEAR);
        datum.setFirstDayOfWeek(Calendar.MONDAY);
        System.out.print("first day of week" + Integer.toString(datum.getFirstDayOfWeek()));
        System.out.print("toegevoegd, week_of_year:" + Integer.toString(week));
        for (Object r : queryres) {
            String res[] = new String[3];
            res[0] = ((Reservaties) r).getGebruiker().getGebruikersnaam();
            System.out.print(res[0]);
            datum = ((Reservaties) r).getReservatiesPK().getMoment();
            res[1] = Integer.toString(datum.get(Calendar.DAY_OF_WEEK));
            res[2] = Integer.toString(datum.get(Calendar.HOUR_OF_DAY));
            System.out.print("week_of_year:" + Integer.toString(datum.get(Calendar.WEEK_OF_YEAR)));
            lijst.add(res);
            //if (datum.get(Calendar.WEEK_OF_YEAR)== week)
            //{
            //    System.out.print("toegevoegd, week_of_year:" + Integer.toString(datum.get(Calendar.WEEK_OF_YEAR)));
            //    lijst.add(res);
            //}
            
        }
        return lijst;
    }
    
    public Object getMoment(String serienummer, String dag, String uur){
        Calendar date;
        date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_WEEK, Integer.parseInt(dag));
        date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(uur));
        return (Reservaties) em.createNamedQuery("Reservaties.findByNaam").setParameter("serienummer", serienummer).setParameter("datum", date).getSingleResult();
    }
    
    public Object getGebruiker(String naam){
        return (Gebruikers) em.createNamedQuery("findByGebruikersnaam").setParameter("gebruikersnaam", naam).getSingleResult();
    }
    
    public void reserveer(String serienummer, String dag, String uur, String gebruiker) {
        Reservaties r = (Reservaties) getMoment(serienummer, dag, uur);
        
        // m.setReservatiesCollection(reservatiesCollection);  //geen idee wat dit doet
       
        r.setGebruiker((Gebruikers)getGebruiker(gebruiker));
          
        em.persist(r);
    }
    
    public void voegVrijToe(String serienummer, String dag, String maand, String jaar, String uur){
        Reservaties r = new Reservaties();
        Calendar date = Calendar.getInstance();
        date.set(Integer.parseInt(jaar), Integer.parseInt(maand), Integer.parseInt(dag), Integer.parseInt(uur), 0, 0);//kijken of dag juiste getal geeft
        r.setMachines((Machines) getMachine(serienummer));
        r.setReservatiesPK(new ReservatiesPK(new BigInteger(serienummer), date));

        em.persist(r);
    }
}