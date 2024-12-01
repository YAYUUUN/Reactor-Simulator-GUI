/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package prog2.model;

import prog2.vista.CentralUBException;

/**
 *
 * @author dortiz
 */
public interface InBombaRefrigerant {
    
    /**
     * Retorna l'identificador numèric de la bomba refrigerant.
     */
    public int getId();
    
    /**
     * Activa la bomba refrigerant. El mètode llançarà una excepció si la bomba 
     * està fora de servei i en aquest cas la bomba no es podrà activar.
     */
    public void activa() throws CentralUBException;
    
    /**
     * Desactiva la bomba refrigerant.
     */
    public void desactiva();
    
    /**
     * Retorna true si la bomba refrigerant està activada.
     */
    public boolean getActivat();
    
    /**
     * Revisa la bomba refrigerant. Es farà servir l'objecte de tipus 
     * VariableUniforme per determinar si la bomba es queda fora de servei. En 
     * cas afirmatiu, s'haurà de registrar la situació dins d'una pàgina 
     * d'incidències.
     * @param p Objecte de tipus PaginaIncidencies per a registrar si la bomba 
     * es queda fora de servei.
     */
    public void revisa (PaginaIncidencies p);
    
    /**
     * Retorna true si la bomba refrigerant està fora de servei.
     */
    public boolean getForaDeServei();
}
