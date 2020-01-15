package beans;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DatabankVerbinding implements DatabankVerbindingRemote {

    @PersistenceContext private EntityManager em;
    
    public List getMachines() {
        List lijst = em.createNamedQuery("Machines.findAll").getResultList();
        return lijst;
    }

    public Object getMachine(String serie) {
        return em.createNamedQuery("Machines.findBySerienummer").setParameter("serienummer", new BigDecimal(serie)).getSingleResult();
    }
    
    public void newMachine(String serienummer, String opleiding, String omschrijving, String naam, String aankoopprijs, String huurprijs, String lokaal) {
        Machines m = new Machines();
        
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
    
    public ArrayList getVrijeMomenten(String serie, Calendar vandaag) {
        List queryres = em.createNamedQuery("Reservaties.findFreeMoment").setParameter("serienummer", new BigDecimal(serie)).getResultList();
        ArrayList lijst = new ArrayList();
        Calendar datum;
        Calendar eind = (Calendar) vandaag.clone();
        eind.add(Calendar.DAY_OF_YEAR, 7);
        for (Object r : queryres) {
            String res[] = new String[3];
            res[0] = "Vrij";
            datum = ((Reservaties) r).getReservatiesPK().getMoment();
            res[1] = Integer.toString(datum.get(Calendar.DAY_OF_MONTH));
            res[2] = Integer.toString(datum.get(Calendar.HOUR_OF_DAY));

            if(datum.after(vandaag) && datum.before(eind)){
                lijst.add(res);
            }
        }
        return lijst;
    }
    
    public ArrayList getReservatieMomenten(String serie, Calendar vandaag) {
        List queryres = em.createNamedQuery("Reservaties.findBySerienummer").setParameter("serienummer", new BigDecimal(serie)).getResultList();
        ArrayList lijst = new ArrayList();
        Calendar datum;
        Calendar eind = (Calendar) vandaag.clone();
        eind.add(Calendar.DAY_OF_YEAR, 7);
        for (Object r : queryres) {
            String res[] = new String[3];
            res[0] = ((Reservaties) r).getGebruiker().getGebruikersnaam();
            datum = ((Reservaties) r).getReservatiesPK().getMoment();
            res[1] = Integer.toString(datum.get(Calendar.DAY_OF_MONTH));
            res[2] = Integer.toString(datum.get(Calendar.HOUR_OF_DAY));

            if(datum.after(vandaag) && datum.before(eind)){
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
        for (Object r : queryres) {
            Calendar datum = ((Reservaties)r).getReservatiesPK().getMoment();            
            if (datum.get(Calendar.DAY_OF_MONTH)== Integer.parseInt(dag)){
                if ((datum.get(Calendar.MONTH)+1)== Integer.parseInt(maand)){
                    if (datum.get(Calendar.YEAR)== Integer.parseInt(jaar)){
                        return r;
                    }
                }
            }
        }
        return null;  
    }
    
    public ArrayList getReservaties(String gebruiker){
        List queryres = em.createNamedQuery("Reservaties.findByGebruiker").setParameter("gebruiker", ((Gebruikers)getGebruiker(gebruiker))).getResultList();
        ArrayList lijst = new ArrayList();
        Calendar date;
        for (Object r : queryres) {
            String res[] = new String[2];
            date = ((Reservaties) r).getReservatiesPK().getMoment();
            res[0] = Integer.toString(date.get(Calendar.HOUR_OF_DAY)) + " uur, " + Integer.toString(date.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(1+date.get(Calendar.MONTH)) + "/" + Integer.toString(date.get(Calendar.YEAR));
            res[1] = ((Reservaties) r).getMachines().getNaam();
            lijst.add(res);
        }
        return lijst;
    }
    public int getPrijs (String serienummer){
        Machines m = (Machines)em.createNamedQuery("Machines.findBySerienummer").setParameter("serienummer", new BigDecimal(serienummer)).getSingleResult();
        return m.getHuurprijs().intValue();
    }
    public Object getGebruiker(String naam){
        System.out.println("naam: " + naam);    //Debug info
        return (Gebruikers) em.createNamedQuery("Gebruikers.findByGebruikersnaam").setParameter("gebruikersnaam", naam).getSingleResult();
    }
    
    public void reserveer(String serienummer, String jaar, String maand, String dag, String uur, String gebruiker) {
        Reservaties r = (Reservaties) getMoment(serienummer,jaar, maand, dag, uur);
        System.out.println("res: " + r.toString()); //Debug info
       
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
    
    public boolean bestaatSerie(String serienummer) {
        return 1 >= em.createNamedQuery("Machines.findBySerienummer").setParameter("serienummer", serienummer).getResultList().size();
    }
    
    
    //Swing app
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
        Calendar date;
        for (Object r : queryres) {
            String res[] = new String[2];
            
            date = ((Reservaties) r).getReservatiesPK().getMoment();
            res[0] = Integer.toString(date.get(Calendar.HOUR_OF_DAY)) + " uur, " + Integer.toString(date.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(1+date.get(Calendar.MONTH)) + "/" + Integer.toString(date.get(Calendar.YEAR)); // aanpassen zodat alleen uur tem jaar w meegegeven
            res[1] = ((Reservaties) r).getGebruiker().getGebruikersnaam();
            lijst.add(res);
        }
        return lijst;
    }
}