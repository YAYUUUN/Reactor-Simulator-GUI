/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;

/**
 * Classe que gestiona el generador de vapor de la central
 * @author Jiajun
 */
public class GeneradorVapor implements InComponent, Serializable{
    
    private boolean activat;

    public GeneradorVapor() {
    }

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
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa (PaginaIncidencies p){
        // No fa res
        ;
    }
    
    /**
     * Obté el cost operatiu del component. El cost operatiu depèn de si el 
     * component està activat. Si no està activat el cost és zero.
     * Si està activat, tindrà un cost que es pot consultar a la Taula 1 de 
     * l'enunciat de la pràctica.
     * @return 25 si està activat, 0 en cas contrari
     */
    @Override
    public float getCostOperatiu(){
        return (activat)?25:0;
    }
    
    /**
     * Calcula l'output del component donat l'input. La manera de calcular
     * l'output està descrita a la Figura 2 de l'enunciat de la pràctica.
     * @param input Input que rep el component.
     * @return output del generador de vapor
     */
    @Override
    public float calculaOutput(float input){
        return (activat)? input*0.8f:(float)30;
    }
    
}
