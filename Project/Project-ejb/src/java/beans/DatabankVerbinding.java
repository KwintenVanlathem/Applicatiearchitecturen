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
    
    public ArrayList getVrijeMomenten(String serie, Calendar maandag, Calendar zondag) {
        List queryres = em.createNamedQuery("Reservaties.findFreeMoment").setParameter("serienummer", new BigDecimal(serie)).getResultList();
        ArrayList lijst = new ArrayList();
        Calendar datum = Calendar.getInstance();
        int maand = datum.get(Calendar.MONTH);
        for (Object r : queryres) {
            String res[] = new String[3];
            res[0] = "Vrij";
            datum = ((Reservaties) r).getReservatiesPK().getMoment();
            res[1] = Integer.toString(datum.get(Calendar.DAY_OF_MONTH));
            res[2] = Integer.toString(datum.get(Calendar.HOUR_OF_DAY));

            if(datum.after(maandag) && datum.before(zondag)){
                lijst.add(res);
            }
        }
        return lijst;
    }
    public ArrayList getReservatieMomenten(String serie, Calendar maandag, Calendar zondag) {
        List queryres = em.createNamedQuery("Reservaties.findBySerienummer").setParameter("serienummer", new BigDecimal(serie)).getResultList();
        ArrayList lijst = new ArrayList();
        Calendar datum = Calendar.getInstance();
        for (Object r : queryres) {
            String res[] = new String[3];
            res[0] = ((Reservaties) r).getGebruiker().getGebruikersnaam();
            datum = ((Reservaties) r).getReservatiesPK().getMoment();
            res[1] = Integer.toString(datum.get(Calendar.DAY_OF_MONTH));
            res[2] = Integer.toString(datum.get(Calendar.HOUR_OF_DAY));

            if(datum.after(maandag) && datum.before(zondag)){
                lijst.add(res);
            }
            
        }
        return lijst;
    }
    
    public Object getMoment(String serie, String jaar, String maand, String dag, String uur){
        Calendar date;
        date = Calendar.getInstance();
        date.set(Integer.parseInt(jaar), Integer.parseInt(maand), Integer.parseInt(dag), Integer.parseInt(uur), 0, 0);
        List queryres = em.createNamedQuery("Reservaties.findFreeMoment").setParameter("serienummer", new BigDecimal(serie)).getResultList();
        System.out.println(serie);
        System.out.println(queryres.size());
        System.out.println("Date: dag: " + date.get(Calendar.DAY_OF_MONTH )+ ", maand: " + date.get(Calendar.MONTH) + ", jaar: " + date.get(Calendar.YEAR));
        for (Object r : queryres) {
            Calendar datum = ((Reservaties)r).getReservatiesPK().getMoment();
            System.out.println("DBmoment: dag: " + datum.get(Calendar.DAY_OF_MONTH )+ ", maand: " + datum.get(Calendar.MONTH) + ", jaar: " + datum.get(Calendar.YEAR));
            if (datum.get(Calendar.DAY_OF_MONTH)== Integer.parseInt(dag)){
                if ((datum.get(Calendar.MONTH)+1)== Integer.parseInt(maand)){
                    if (datum.get(Calendar.YEAR)== Integer.parseInt(jaar)){
                        return r;
                    }
                }
            }
        }
        System.out.println("Moment niet gevonden");
        return null;
        
    }
    
    public Object getGebruiker(String naam){
        System.out.println("naam: " + naam);
        return (Gebruikers) em.createNamedQuery("Gebruikers.findByGebruikersnaam").setParameter("gebruikersnaam", naam).getSingleResult();
    }
    
    public void reserveer(String serienummer, String jaar, String maand, String dag, String uur, String gebruiker) {
        Reservaties r = (Reservaties) getMoment(serienummer,jaar, maand, dag, uur);
        System.out.println("res: " + r.toString());
        // m.setReservatiesCollection(reservatiesCollection);  //geen idee wat dit doet
       
        r.setGebruiker((Gebruikers)(em.createNamedQuery("Gebruikers.findByGebruikersnaam").setParameter("gebruikersnaam", gebruiker).getSingleResult()));
        em.persist(r);
    }
    
    public void voegVrijToe(String serienummer, String dag, String maand, String jaar, String uur){
        Reservaties r = new Reservaties();
        Calendar date = Calendar.getInstance();
        date.set(Integer.parseInt(jaar), Integer.parseInt(maand)-1, Integer.parseInt(dag), Integer.parseInt(uur), 0, 0);//kijken of dag juiste getal geeft
        r.setMachines((Machines) getMachine(serienummer));
        r.setReservatiesPK(new ReservatiesPK(new BigInteger(serienummer), date));

        em.persist(r);
    }
}