/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import static java.lang.Math.min;
import java.util.ArrayList;
import prog2.vista.CentralUBException;

/**
 * Classe que tracta el component SistemaRefrigeracio de la central
 * @author Jiajun
 */
public class SistemaRefrigeracio implements InComponent, Serializable{
    
    private final ArrayList<BombaRefrigerant> bombes;

    public SistemaRefrigeracio() {
        this.bombes = new ArrayList();
    }

    public ArrayList<BombaRefrigerant> getBombes() {
        return bombes;
    }
    
    /**
     * Retorna la bomba que te l'id passat per paràmetre
     * @param id
     * @return null si no troba la bomba, en cas contrari, la bomba
     */
    public BombaRefrigerant getBomba(int id){
        for (BombaRefrigerant bomba: bombes){
            if (id == bomba.getId()) return bomba;
        }
        // aquesta instrucció és per si no troba la bomba, encara que suposadament sempre es trobarà
        return null;
    }
    
    /**
     * Afegeix una bomba a la llista de bombes de la classe
     * @param b
     */
    public void afegirBomba(BombaRefrigerant b){
        bombes.add(b);
    }
    
    /**
     * Activa el sistema de refrigeració, que signifca que s'activen totes les bombes que no estiguin fora de servei.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activa() throws CentralUBException{
        int fs = 0;
        for (BombaRefrigerant bomba : bombes){
            if (bomba.getForaDeServei()){
                fs += 1;
            }
            else{
                bomba.activa();
            }
        }
        if (fs == bombes.size()) {
            throw new CentralUBException("Totes les bombes estan fora de servei");
        }
    }
       
    /**
     * Desactiva el component.
     */
    @Override
    public void desactiva(){
        for (BombaRefrigerant bomba: bombes){
            bomba.desactiva();
        }
    }
    
    /**
     * Revisa el component. Com a resultat de la revisió, podria podria sorgir 
     * una incidència que s'ha de registrar dins d'una pàgina d'incidències.
     * @param p Objecte de tipus PaginaIncidencies.
     */
    @Override
    public void revisa (PaginaIncidencies p){
        for (BombaRefrigerant bomba: bombes){
            if (!bomba.getForaDeServei()) bomba.revisa(p);
        }
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
        return (float) (125 * activats());
    }
    
    /**
     * Calcula l'output del component donat l'input. La manera de calcular
     * l'output està descrita a la Figura 2 de l'enunciat de la pràctica.
     * @param input Input que rep el component.
     * @return output del sistema de refrigeració
     */
    @Override
    public float calculaOutput(float input){
        return min(input, 250 * activats());
    }
        
    /**
     * Mètode auxiliar que es podría no implementar que calcula el nombre de bombes que estan activat
     * @return enter del nº de bombes activades
     */
    public int activats(){
        int activats = 0;
        for (BombaRefrigerant bomba : bombes){
            if (bomba.getActivat()) activats += 1;
        }
        return activats;
    }
    
    @Override
    public String toString(){
        StringBuilder cadena = new StringBuilder();
        for (BombaRefrigerant bomba: bombes){
            cadena.append("- Bomba ").append(bomba.getId()).append(bomba.getActivat()? " activada, ":" desactivada, ").append(bomba.getForaDeServei()?" fora de servei\n":" en servei\n");
        }
        return cadena.toString();
    }
}
