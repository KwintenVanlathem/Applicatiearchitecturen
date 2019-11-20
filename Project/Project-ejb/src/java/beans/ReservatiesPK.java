/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kwint
 */
@Embeddable
public class ReservatiesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SERIENUMMER")
    private BigInteger serienummer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MOMENT")
    private String moment;

    public ReservatiesPK() {
    }

    public ReservatiesPK(BigInteger serienummer, String moment) {
        this.serienummer = serienummer;
        this.moment = moment;
    }

    public BigInteger getSerienummer() {
        return serienummer;
    }

    public void setSerienummer(BigInteger serienummer) {
        this.serienummer = serienummer;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serienummer != null ? serienummer.hashCode() : 0);
        hash += (moment != null ? moment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservatiesPK)) {
            return false;
        }
        ReservatiesPK other = (ReservatiesPK) object;
        if ((this.serienummer == null && other.serienummer != null) || (this.serienummer != null && !this.serienummer.equals(other.serienummer))) {
            return false;
        }
        if ((this.moment == null && other.moment != null) || (this.moment != null && !this.moment.equals(other.moment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ReservatiesPK[ serienummer=" + serienummer + ", moment=" + moment + " ]";
    }
    
}
