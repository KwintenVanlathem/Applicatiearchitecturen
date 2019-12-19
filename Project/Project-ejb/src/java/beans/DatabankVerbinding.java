/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
            res[0] = ((Reservaties) r).getReservatiesPK().getMoment();
            res[1] = ((Reservaties) r).getGebruiker().getGebruikersnaam();
            lijst.add(res);
        }
        return lijst;
    }
}