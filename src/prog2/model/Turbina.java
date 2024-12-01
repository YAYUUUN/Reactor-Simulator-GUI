/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;

/**
 * Representa la turbina de la central 
 * @author Jiajun
 */
public class Turbina implements InComponent, Serializable{
    
    boolean activat;
    
    /**
     * Activa el component. El mètode llançarà
     * una excepció en determinades situacions explicades
     * a la Taula 1 de l'enunciat de la pràctica.
     */
    @Override
    public void activa(){
        this.activat = true;
    }
       
    /**
     * Desactiva el component.
     */
    @Override
    public void desactiva(){
        this.activat = false;
    }
    
    /**
     * Revisa el component. Com a resultat de la revisió, podria podria sorgir 
     * una incidència que s'ha de registrar dins d'una pàgina d'incidències.
     * *** Aquest mètode no afecta ja que no pot tindre incidencies, per això no s'implementa
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa (PaginaIncidencies p){
        // no fa res
        ;
    }
    
    /**
     * Obté el cost operatiu del component. El cost operatiu depèn de si el 
     * component està activat. Si no està activat el cost és zero.
     * Si està activat, tindrà un cost que es pot consultar a la Taula 1 de 
     * l'enunciat de la pràctica.
     * @return el cost operatiu
     */
    @Override
    public float getCostOperatiu(){
        return (activat)? 20:0;
    }
    
    /**
     * Calcula l'output del component donat l'input. La manera de calcular
     * l'output està descrita a la Figura 2 de l'enunciat de la pràctica.
     * @param input Input que rep el component.
     * @return output de la turbina, que és la potencia generada per la central
     */
    @Override
    public float calculaOutput(float input){
        return (!activat || input < 100)? 0:2*input;
    }
    
}
