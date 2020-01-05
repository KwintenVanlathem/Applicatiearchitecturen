package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "MACHINES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Machines.findAll", query = "SELECT m FROM Machines m")
    , @NamedQuery(name = "Machines.findByNaam", query = "SELECT m FROM Machines m WHERE m.naam = :naam")
    , @NamedQuery(name = "Machines.findByOmschrijving", query = "SELECT m FROM Machines m WHERE m.omschrijving = :omschrijving")
    , @NamedQuery(name = "Machines.findByLokaal", query = "SELECT m FROM Machines m WHERE m.lokaal = :lokaal")
    , @NamedQuery(name = "Machines.findByOpleiding", query = "SELECT m FROM Machines m WHERE m.opleiding = :opleiding")
    , @NamedQuery(name = "Machines.findBySerienummer", query = "SELECT m FROM Machines m WHERE m.serienummer = :serienummer")
    , @NamedQuery(name = "Machines.findByAankoopprijs", query = "SELECT m FROM Machines m WHERE m.aankoopprijs = :aankoopprijs")
    , @NamedQuery(name = "Machines.findByHuurprijs", query = "SELECT m FROM Machines m WHERE m.huurprijs = :huurprijs")})
public class Machines implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "NAAM")
    private String naam;
    @Size(max = 150)
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @Size(max = 5)
    @Column(name = "LOKAAL")
    private String lokaal;
    @Size(max = 10)
    @Column(name = "OPLEIDING")
    private String opleiding;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERIENUMMER")
    private BigDecimal serienummer;
    @Column(name = "AANKOOPPRIJS")
    private BigInteger aankoopprijs;
    @Column(name = "HUURPRIJS")
    private BigInteger huurprijs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "machines")
    private Collection<Reservaties> reservatiesCollection;

    public Machines() {
    }

    public Machines(BigDecimal serienummer) {
        this.serienummer = serienummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getLokaal() {
        return lokaal;
    }

    public void setLokaal(String lokaal) {
        this.lokaal = lokaal;
    }

    public String getOpleiding() {
        return opleiding;
    }

    public void setOpleiding(String opleiding) {
        this.opleiding = opleiding;
    }

    public BigDecimal getSerienummer() {
        return serienummer;
    }

    public void setSerienummer(BigDecimal serienummer) {
        this.serienummer = serienummer;
    }

    public BigInteger getAankoopprijs() {
        return aankoopprijs;
    }

    public void setAankoopprijs(BigInteger aankoopprijs) {
        this.aankoopprijs = aankoopprijs;
    }

    public BigInteger getHuurprijs() {
        return huurprijs;
    }

    public void setHuurprijs(BigInteger huurprijs) {
        this.huurprijs = huurprijs;
    }

    @XmlTransient
    public Collection<Reservaties> getReservatiesCollection() {
        return reservatiesCollection;
    }

    public void setReservatiesCollection(Collection<Reservaties> reservatiesCollection) {
        this.reservatiesCollection = reservatiesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serienummer != null ? serienummer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Machines)) {
            return false;
        }
        Machines other = (Machines) object;
        if ((this.serienummer == null && other.serienummer != null) || (this.serienummer != null && !this.serienummer.equals(other.serienummer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Machines[ serienummer=" + serienummer + " ]";
    }
    
}
