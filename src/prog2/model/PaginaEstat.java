/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;

/**
 * Classe que hereta de PaginaBitacola i mostra el que seria una pagina per a l'estat de la central
 * @author Jiajun
 */
public class PaginaEstat extends PaginaBitacola implements Serializable{
    
    private final float demanda, insercio, outReactor, outSistema, outGenerador, outTurbina, satisfeta;
    private final int dia;

    public PaginaEstat(float demanda, float insercio, float outReactor, float outSistema, float outGenerador, float outTurbina, int dia) {
        super(dia);
        this.dia = dia;
        this.demanda = demanda;
        this.insercio = insercio;
        this.outReactor = outReactor;
        this.outSistema = outSistema;
        this.outGenerador = outGenerador;
        this.outTurbina = outTurbina;
        this.satisfeta = outTurbina / demanda * 100f;
    }
    
    @Override
    public String toString(){
       return "# Pagina Estat"
               + "\n- Dia: " + dia
               + "\n- Demanda de potencia: " + demanda
               + "\n- Insercio Barres: " + insercio + "%"
               + "\n- Output Reactor: " + outReactor + " Graus"
               + "\n- Output Sistema de Refrigeracio: " + outSistema + "Graus"
               + "\n- Output Generador de Vapor: " + outGenerador + " Graus"
               + "\n- Output Turbina: " + outTurbina + " Unitats de Potencia"
               + "\n- Demanda de Potencia Satisfeta: " + ((satisfeta > 100)? 100f: satisfeta) + " %";
    }
}
