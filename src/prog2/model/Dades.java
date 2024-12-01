/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.List;
import prog2.vista.CentralUBException;

/**
 * Classe principal del paquet prog2.model i és on es troben les dades del programa
 * @author Jiajun
 */
public class Dades implements InDades, Serializable{
    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 200;

    // Afegir atributs:
    private final VariableUniforme variableUniforme;
    private float insercioBarres;
    private final Reactor reactor;
    private final SistemaRefrigeracio sistemaRefrigeracio;
    private final GeneradorVapor generadorVapor;
    private final Turbina turbina;
    private final Bitacola bitacola;
    private int dia;
    private float guanysAcumulats;

    public Dades(){
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;
        
        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);
        
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);
    }

    /**
     * getter de dia
     * @return int
     */
    public int getDia() {
        return dia;
    }

    /**
     * getter de guanysAcumulats
     * @return float
     */
    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }
    
    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a 
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou 
     * beneficis, penalització per excès de potència, costos operatius y 
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){
        // Completar
        float costOperatiu = (-(reactor.getCostOperatiu()) -
                            (sistemaRefrigeracio.getCostOperatiu()) -
                            (generadorVapor.getCostOperatiu()) -
                            (turbina.getCostOperatiu()));
        float penalitzacio = (demandaPotencia < calculaPotencia())? PENALITZACIO_EXCES_POTENCIA : 0;
        // els beneficis
        float beneficis = ((demandaPotencia >= calculaPotencia())? calculaPotencia():demandaPotencia - PENALITZACIO_EXCES_POTENCIA)
                            *PREU_UNITAT_POTENCIA - costOperatiu;
        
        PaginaEconomica pagina = new PaginaEconomica(dia, beneficis, penalitzacio, costOperatiu, guanysAcumulats);
        // els diners acumulats
        guanysAcumulats += beneficis;
        return pagina;
    }
    
    /**
     * Actualitza l'estat de la central. El mètodo ha de establir la nova
     * temperatura del reactor i revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void actualitzaEstatCentral(PaginaIncidencies paginaIncidencies) {
        // Completar
        
            reactor.revisa(paginaIncidencies);
            sistemaRefrigeracio.revisa(paginaIncidencies);
            generadorVapor.revisa(paginaIncidencies);
            turbina.revisa(paginaIncidencies);
        if (sistemaRefrigeracio.activats() == 0){
            reactor.setTemperatura(reactor.calculaOutput(insercioBarres));
        }
        else {
            // si el valor passat per parametre es menor a 30, es posa automaticament la temperatura a 30
            float temperaturaDiferencia = (reactor.calculaOutput(insercioBarres) - sistemaRefrigeracio.activats() * 250);
            reactor.setTemperatura(temperaturaDiferencia < 30 ? 30 : temperaturaDiferencia);
        }
    }
    
    /**
     * Duu a terme les accions relacionades amb la finalització d'un dia.
     * Per això és necessari coneixer la demanda de potència actual per al dia
     * en curs.
     * @param demandaPotencia Demanda de potència actual de la central.
     * @return Bitacola del dia actual
     */
    @Override
    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);
        
        // Genera pàgina d'estat
        PaginaEstat paginaEstat = mostraEstat(demandaPotencia);

        // Actualitza estat central i registra incidencies
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        actualitzaEstatCentral(paginaIncidencies);
        

        // Incrementa dia
        dia += 1;
        
        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);
        
        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }
    
    /**
     * Retorna el grau d'inserció de les barres de control en percentatge.
     * @return el percentatge de la inserció de les barres
     */
    @Override
    public float getInsercioBarres(){
        return insercioBarres;
    }
    
    /**
     * Estableix el grau d'inserció de les barres de control en percentatge.
     * @param insercioBarres Percentatge d'inserció de les barres de control.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void setInsercioBarres(float insercioBarres) throws CentralUBException{
        if (insercioBarres < 0 || insercioBarres > 100){
            throw new CentralUBException("La insercio de les barres, ha de ser un nombre real entre 0 i 100\n");
        }
        this.insercioBarres = insercioBarres;
    }
     
    /**
     * Activa el reactor de la central.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activaReactor() throws CentralUBException{
        reactor.activa();
    }

    /**
     * Desactiva el reactor de la central.
     */
    @Override
    public void desactivaReactor(){
        reactor.desactiva();
    }
    
    /**
     * Retorna l'objecte que contè el reactor de la central.
     * @return objecte reactor
     */
    @Override
    public Reactor mostraReactor(){
        return reactor;
    }
    
    /**
     * Activa una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     * @throws prog2.vista.CentralUBException
     */
    @Override
    public void activaBomba(int id) throws CentralUBException{
        sistemaRefrigeracio.getBomba(id).activa();
    }
    
    /**
     * Desactiva una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar.
     */
    @Override
    public void desactivaBomba(int id){
        sistemaRefrigeracio.getBomba(id).desactiva();
    }
    
    /**
     * Retorna l'objecte que contè el sistema de refrigeració de la central.
     * @return objecte SistemaRefrigerant
     */
    @Override
    public SistemaRefrigeracio mostraSistemaRefrigeracio(){
        return sistemaRefrigeracio;
    }
    
    /**
     * Retorna la potència generada per la central. Aquesta potència es 
     * l'output de la turbina. Es pot consultar la Figura 2 a l'enunciat per
     * veure els detalls.
     * @return potencia generada
     */
    @Override
    public float calculaPotencia(){
        return turbina.calculaOutput(
                generadorVapor.calculaOutput(
                sistemaRefrigeracio.calculaOutput(
                reactor.calculaOutput(insercioBarres)
                )));
    }
    
    /**
     * Retorna una pàgina de estat per a la configuració actual de la central.
     * Amb aquest propòsit és necessari coneixer la demanda de potència actual.
     * @param demandaPotencia Demanda de potència actual.
     * @return l'estat actual de la central
     */
    @Override
    public PaginaEstat mostraEstat(float demandaPotencia){
        PaginaEstat paginaEstat = new PaginaEstat(
                demandaPotencia, 
                insercioBarres, 
                reactor.calculaOutput(insercioBarres), 
                sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres)), 
                generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres))), 
                calculaPotencia(), 
                dia);
        return paginaEstat;
    }
      
    /**
     * Retorna la bitacola de la central.
     * @return objecte bitacola
     */
    @Override
    public Bitacola mostraBitacola(){
        return bitacola;
    }
    
    /**
     * Retorna una llista amb totes les pàgines d'incidències de la bitàcola de
     * la central.
     * @return objecte llista amb les incidencies fins el dia actual
     */
    @Override
    public List<PaginaIncidencies> mostraIncidencies(){
        return bitacola.getIncidencies();
    }
    
}
