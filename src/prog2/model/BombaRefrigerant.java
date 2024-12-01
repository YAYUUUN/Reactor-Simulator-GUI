/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import prog2.vista.CentralUBException;

/**
 * Representa una de les bombes del sistema de refrigeració
 * @author Jiajun
 */
public class BombaRefrigerant implements InBombaRefrigerant, Serializable{
    
    private final int id;
    private boolean activat, foraServei;
    private final VariableUniforme variableUniforme;
    
    public BombaRefrigerant(VariableUniforme variableUniforme, int id){
        this.id = id;
        this.variableUniforme = variableUniforme;
    }

    /**
     * Retorna l'identificador numèric de la bomba refrigerant.
     * @return l'identificador de la bomba
     */
    @Override
    public int getId(){
        return this.id;
    }
    
    /**
     * Activa la bomba refrigerant. El mètode llançarà una excepció si la bomba 
     * està fora de servei i en aquest cas la bomba no es podrà activar.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activa() throws CentralUBException{
        if (foraServei){
            throw new CentralUBException("La bomba amb id:" + id + ", esta fora de servei, no es pot activar");
        }
        this.activat = true;
    }
    
    /**
     * Desactiva la bomba refrigerant.
     */
    @Override
    public void desactiva(){
        this.activat = false;
    }
    
    /**
     * Retorna true si la bomba refrigerant està activada.
     * @return true si està activat, false en cas contrari
     */
    @Override
    public boolean getActivat(){
        return this.activat;
    }
    
    /**
     * Revisa la bomba refrigerant. Es farà servir l'objecte de tipus 
     * VariableUniforme per determinar si la bomba es queda fora de servei. En 
     * cas afirmatiu, s'haurà de registrar la situació dins d'una pàgina 
     * d'incidències.
     * @param p Objecte de tipus PaginaIncidencies per a registrar si la bomba 
     * es queda fora de servei.
     */
    @Override
    public void revisa (PaginaIncidencies p){
        int probabilitat = variableUniforme.seguentValor() - 20;
        if (probabilitat <= 0){
            foraServei = true;
            desactiva();
            p.afegeixIncidencia("La bomba refrig. " + id + " esta fora de servei\n");
        }
    }
    
    /**
     * Retorna true si la bomba refrigerant està fora de servei.
     * @return retorna un booleà
     */
    @Override
    public boolean getForaDeServei(){
        return foraServei;
    }
    
}
