/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import prog2.vista.CentralUBException;

/**
 * Classe que representa el component Reactor de la central
 * @author Jiajun
 */
public class Reactor implements InComponent, Serializable{
    
    private float temperatura;
    private boolean activat;

    public Reactor() {
        this.temperatura = 30;
    }

    public boolean isActivat() {
        return activat;
    }

    /**
     * retorna la temperatura del reactor
     * @return temperatura
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     * fa un set la temperatura del reactor
     * @param temperatura
     */
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }
    
    /**
     * Activa el component. El mètode llançarà
     * una excepció en determinades situacions explicades
     * a la Taula 1 de l'enunciat de la pràctica.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activa() throws CentralUBException{
        if (temperatura > 1000) 
            throw new CentralUBException("No es pot activar el reactor mentre es superi la temperatura maxima de 1.000 graus.");
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
        if (this.temperatura > 1000){
            desactiva();
            p.afegeixIncidencia("El reactor es va desactivar per superar la temperatura maxima\n");
        }
    }
    
    /**
     * Obté el cost operatiu del component. El cost operatiu depèn de si el 
     * component està activat. Si no està activat el cost és zero.
     * Si està activat, tindrà un cost que es pot consultar a la Taula 1 de 
     * l'enunciat de la pràctica.
     * @return cost operatiu
     */
    @Override
    public float getCostOperatiu(){
        return (activat)? 30f:0;
    }
    
    /**
     * Calcula l'output del component donat l'input. La manera de calcular
     * l'output està descrita a la Figura 2 de l'enunciat de la pràctica.
     * @param input Input que rep el component.
     * @return output
     */
    @Override
    public float calculaOutput(float input){
        return (activat)? (temperatura +  ((100 - input) * 10)):temperatura;
    }

    @Override
    public String toString() {
        return "- Temperatura: " + temperatura + ", Activat: " + activat;
    }
}
