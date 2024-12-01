/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classs que maneja el llibre de Bitàcola o les pàgines d'aquest
 * @author Jiajun
 */
public class Bitacola implements InBitacola, Serializable{

    private final ArrayList<PaginaBitacola> pagines;
    
    public Bitacola() {
        pagines = new ArrayList();
    }
    
    /**
     * Afegeix una pagina a la bitàcola.
     * @param p Objecte de tipus PaginaBitacola
     */
    @Override
    public void afegeixPagina(PaginaBitacola p){
        pagines.add(p);
    }
    
    /**
     * Obté una llista amb totes les pàgines d'incidències contingudes dins de la bitàcola
     * @return 
     */
    @Override
    public List<PaginaIncidencies> getIncidencies(){
        final List<PaginaIncidencies> incidencies = new ArrayList();
        for (PaginaBitacola pagina : pagines){
            if (pagina instanceof PaginaIncidencies) incidencies.add((PaginaIncidencies)pagina);
        }
        return incidencies;
    }
    
    @Override
    public String toString(){
        StringBuilder cadena = new StringBuilder();
        for (PaginaBitacola pagina : pagines){
            cadena.append(pagina).append("\n");
        }
        return "Pagines:\n" + cadena;
    }
    
}
