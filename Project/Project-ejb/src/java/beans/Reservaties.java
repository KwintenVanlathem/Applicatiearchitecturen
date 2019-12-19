/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kwint
 */
@Entity
@Table(name = "RESERVATIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservaties.findAll", query = "SELECT r FROM Reservaties r")
    , @NamedQuery(name = "Reservaties.findBySerienummer", query = "SELECT r FROM Reservaties r WHERE r.reservatiesPK.serienummer = :serienummer")
    , @NamedQuery(name = "Reservaties.findByMoment", query = "SELECT r FROM Reservaties r WHERE r.reservatiesPK.moment = :moment")})
public class Reservaties implements Serializable {

    @JoinColumn(name = "GEBRUIKER", referencedColumnName = "GEBRUIKERSNAAM")
    @ManyToOne
    private Gebruikers gebruiker;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservatiesPK reservatiesPK;
    @JoinColumn(name = "SERIENUMMER", referencedColumnName = "SERIENUMMER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Machines machines;

    public Reservaties() {
    }

    public Reservaties(ReservatiesPK reservatiesPK) {
        this.reservatiesPK = reservatiesPK;
    }

    public Reservaties(BigInteger serienummer, String moment) {
        this.reservatiesPK = new ReservatiesPK(serienummer, moment);
    }

    public ReservatiesPK getReservatiesPK() {
        return reservatiesPK;
    }

    public void setReservatiesPK(ReservatiesPK reservatiesPK) {
        this.reservatiesPK = reservatiesPK;
    }

    public Machines getMachines() {
        return machines;
    }

    public void setMachines(Machines machines) {
        this.machines = machines;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservatiesPK != null ? reservatiesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservaties)) {
            return false;
        }
        Reservaties other = (Reservaties) object;
        if ((this.reservatiesPK == null && other.reservatiesPK != null) || (this.reservatiesPK != null && !this.reservatiesPK.equals(other.reservatiesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Reservaties[ reservatiesPK=" + reservatiesPK + " ]";
    }

    public Gebruikers getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruikers gebruiker) {
        this.gebruiker = gebruiker;
    }
    
}
