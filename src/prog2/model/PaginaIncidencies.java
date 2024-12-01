/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe heredada de paginaBitacola que representa les pàgines d'incidència del llibre
 * @author Jiajun
 */
public class PaginaIncidencies extends PaginaBitacola implements Serializable{
    
    private final ArrayList<String> incidencies;
    private final int dia;
    
    public PaginaIncidencies(int dia) {
        super(dia);
        this.dia = dia;
        incidencies = new ArrayList();
    }
    
    /**
     * afegeix una incidencia a la llista d'incidències
     * @param descIncidencia
     */
    public void afegeixIncidencia(String descIncidencia){
        incidencies.add(descIncidencia);
    }
    
    @Override
    public String toString(){
        StringBuilder cadena = new StringBuilder();
        for (String i : incidencies){
            cadena.append("\n- Descripcio Incidencia:").append(i);
        }
        return "\n# Pagina Incidencies\n"
                + "- Dia: " + dia
                + cadena + "\n";
    }
}
