/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
}
