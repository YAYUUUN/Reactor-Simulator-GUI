/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;

/**
 * Aquesta classe implementa la classe base d'altres tres classes usades per representar pàgines de bitàcola
 * @author Alicia
 */
public class PaginaBitacola  implements Serializable{
    
    private int dia;
    
    public PaginaBitacola(int dia){
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "PaginaBitacola{" + "dia=" + dia + '}';
    }
}
