/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2.model;

import java.util.ArrayList;
import java.util.List;
import prog2.vista.CentralUBException;

/**
 *
 * @author dortiz
 */
public interface InDades {
    
    /**
     * Retorna el grau d'inserció de les barres de control en percentatge.
     */
    public float getInsercioBarres(); 
    
    /**
     * Estableix el grau d'inserció de les barres de control en percentatge.
     * @param insercioBarres Percentatge d'inserció de les barres de control.
     */
    public void setInsercioBarres(float insercioBarres) throws CentralUBException;
     
    /**
     * Activa el reactor de la central.
     */
    public void activaReactor() throws CentralUBException;

    /**
     * Desactiva el reactor de la central.
     */
    public void desactivaReactor();
    
    /**
     * Retorna l'objecte que contè el reactor de la central.
     */
    public Reactor mostraReactor();
    
    /**
     * Activa una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     */
    public void activaBomba(int id) throws CentralUBException;
    
    /**
     * Desactiva una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     */
    public void desactivaBomba(int id); 
    
    /**
     * Retorna l'objecte que contè el sistema de refrigeració de la central.
     */
    public SistemaRefrigeracio mostraSistemaRefrigeracio();
    
    /**
     * Retorna la potència generada per la central. Aquesta potència es 
     * l'output de la turbina. Es pot consultar la Figura 2 a l'enunciat per
     * veure els detalls.
     */
    public float calculaPotencia();
    
    /**
     * Retorna una pàgina de estat per a la configuració actual de la central.
     * Amb aquest propòsit és necessari coneixer la demanda de potència actual.
     * @param demandaPotencia Demanda de potència actual.
     */
    public PaginaEstat mostraEstat(float demandaPotencia);
      
    /**
     * Retorna la bitacola de la central.
     */
    public Bitacola mostraBitacola();
    
    /**
     * Retorna una llista amb totes les pàgines d'incidències de la bitàcola de
     * la central.
     */
    public List<PaginaIncidencies> mostraIncidencies();
    
    /**
     * Duu a terme les accions relacionades amb la finalització d'un dia.
     * Per això és necessari coneixer la demanda de potència actual per al dia
     * en curs.
     * @param demandaPotencia Demanda de potència actual de la central.
     */
    public Bitacola finalitzaDia(float demandaPotencia);
}
