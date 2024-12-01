/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;

/**
 * Aquesta classe també hereta de PaginaBitacola i genera el que seria una pagina de bitacola per la exonomia de la central
 * @author Jiajun
 */
class PaginaEconomica extends PaginaBitacola implements Serializable{

    private final float beneficis;
    private final float penalitzacio;
    private final float costOperatiu;
    private final float guanys;
    private final int dia;

    public PaginaEconomica(int dia, float beneficis, float penalitzacio, float costOperatiu, float guanys) {
        super(dia);
        this.dia = dia;
        this.beneficis = beneficis;
        this.penalitzacio = penalitzacio;
        this.costOperatiu = costOperatiu;
        this.guanys = guanys;
    }

    @Override
    public String toString() {
        return "# PaginaEconomica"
                + "\n- Dia: " + dia
                + "\n- Beneficis: " + beneficis + " Unitats Economiques"
                + "\n- Penalitzacio Exces Produccio: " + penalitzacio + " Unitats Economiques"
                + "\n- Cost Operatiu: " + costOperatiu + " Unitats Economiques"
                + "\n- Guanys acumulats: " + guanys + " Unitats Economiques\n";
    }
    
}
