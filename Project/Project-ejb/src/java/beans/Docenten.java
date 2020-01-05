package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "DOCENTEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docenten.findAll", query = "SELECT d FROM Docenten d")
    , @NamedQuery(name = "Docenten.findByNaam", query = "SELECT d FROM Docenten d WHERE d.naam = :naam")
    , @NamedQuery(name = "Docenten.findByOpleiding", query = "SELECT d FROM Docenten d WHERE d.opleiding = :opleiding")})
public class Docenten implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAAM")
    private String naam;
    @Size(max = 10)
    @Column(name = "OPLEIDING")
    private String opleiding;
    @JoinColumn(name = "NAAM", referencedColumnName = "GEBRUIKERSNAAM", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Gebruikers gebruikers;

    public Docenten() {
    }

    public Docenten(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOpleiding() {
        return opleiding;
    }

    public void setOpleiding(String opleiding) {
        this.opleiding = opleiding;
    }

    public Gebruikers getGebruikers() {
        return gebruikers;
    }

    public void setGebruikers(Gebruikers gebruikers) {
        this.gebruikers = gebruikers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (naam != null ? naam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docenten)) {
            return false;
        }
        Docenten other = (Docenten) object;
        if ((this.naam == null && other.naam != null) || (this.naam != null && !this.naam.equals(other.naam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Docenten[ naam=" + naam + " ]";
    }
    
}
