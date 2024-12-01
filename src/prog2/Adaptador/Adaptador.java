/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.Adaptador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import prog2.model.SistemaRefrigeracio;
import prog2.model.Dades;
import prog2.vista.CentralUBException;

/**
 * Aquesta clase es un intermediari entre els paquets prog2.vista i prog2.model.
 * Conté tots els métodes necessaris en el menú o gestió del programa, d'aquesta forma millora la reutilització
 * del codi. Aquesta estructura de patró MVC ajuda a implementar pràctiques de seguretat del codi de forma més efectiva
 * @author Alicia
 */
public class Adaptador implements Serializable{
    private Dades dades;
    
    public Adaptador(){
        dades = new Dades();
    }
    
    
    // 1
    /**
     * Mètode que retorna un float que és el percentatge d'inserció de les barres de control del reactor
     * @return el percentatge d'inserció
     */
    public float getInsercioBarres(){
        return dades.getInsercioBarres();
    }
    
    /**
     * Mètode que serveix de setter de la variable insercioBarres de la classe Reactor
     * @param insercio
     * @throws CentralUBException
     */
    public void setInsercioBarres(float insercio) throws CentralUBException{
        dades.setInsercioBarres(insercio);
    }
    
    
    // 2    
    /**
     * Aquest mètode retorna un objecte de tipus reactor per a, posteriorment, ser imprimit en el menú
     * es podria fer de la següent manera, pero es el mateix
     * "public String getMostraReactor(){
     * return dades.mostraReactor().toString();
     * }"
     * @return Objecte de tipus Reactor
     */
    public String getMostraReactor(){
        return dades.mostraReactor().toString();
    }
    
    /**
     * Una funció que activa el reactor, pot llençar excepcio si la temperatura es major a 100 graus
     * @throws CentralUBException 
     */
    public void activaReactor() throws CentralUBException{
        dades.activaReactor();
    }
    
    /**
     * Funció per a desactivar el reactor
     */
    public void desactivaReactor(){
        dades.desactivaReactor();
    }
    
    
    // 3
    /**
     * Funcio que retorna un objecte sistema refrigeracio per a ser imprimit amb el seu toString()
     * @return Objecte de tipus SistemaRefrigeracio
     */
    public SistemaRefrigeracio getSistemaRefrigerant(){
        return dades.mostraSistemaRefrigeracio();
    }
    
    /**
     * Funció per a desactivar una bomba amb l'id passat per paràmetre
     * @param id 
     */
    public void desactivaBomba(int id){
        dades.desactivaBomba(id);
    }
    
    /**
     * Una funció per a activar una bomba amb l'id passat per paràmetre, però pot llençar una excepció
     * si está fora de servei
     * @param id
     * @throws CentralUBException 
     */
    public void activaBomba(int id) throws CentralUBException{
        dades.activaBomba(id);
    }
    
    
    // 4
    /**
     * Mostra l'estat actual de la central
     * @param demanda
     * @return 
     */
    public String mostratEstat(float demanda){
        return dades.mostraEstat(demanda).toString();
    }
    
    
    // 5
    /**
     *  Mostra tot el contingut de la bitàcola  fins al dia actual, incloent las pàgines d'estat, econòmiques i d'incidències
     * @return Objecte de tipus Bitacola
     */
    public String mostraBitacola(){
        return dades.mostraBitacola().toString();
    }
    
    
    // 6
    /**
     * Retorna una llista de les incidencies de la pagina de bitàcola fins el dia actual
     * @return Objecte de tipus List que conte pàgines d'incidències
     */
    public String getLlistaIncidencies(){
        return dades.mostraIncidencies().toString();
    }    
    
    // 7
    /**
     * Crida al metode finalitzaDia() de la classe Dades per a fer els moviments necessaris per a tancar un dia
     * @param demandaPotencia
     * @return Un string que es la pgina de bitacola del dia actual
     */
    public String finalitzaDia(float demandaPotencia){
        return dades.finalitzaDia(demandaPotencia).toString();
    }
    
    
    // 8
    /**
     * Guarda els objectes en un fitxer camiDesti
     * @param camiDesti
     * @throws CentralUBException
     * @throws IOException 
     */
    public void guardar(String camiDesti) throws CentralUBException, IOException{
        // obre o crea l'arxiu
        File fitxer = new File(camiDesti);
        FileOutputStream fis = new FileOutputStream(fitxer);
        ObjectOutputStream oos = new ObjectOutputStream(fis);
        // escriu sobre l'arxiu l'objecte que es vol guardar
        oos.writeObject(this.dades);
        // tanquem els fluxos
        oos.close();
        fis.close();
    }
    
    
    // 9
    /**
     * Carrega els objectes del fitxer de memòria camiOrigen
     * @param camiOrigen
     * @throws CentralUBException
     * @throws IOException 
     */
    public void carregar(String camiOrigen) throws CentralUBException, IOException{
        Dades dades = new Dades();
        try{
            // obre l'arxiu passat per parametre
            File fitxer = new File(camiOrigen);
            FileInputStream fin = new FileInputStream(fitxer);
            ObjectInputStream ois = new ObjectInputStream(fin);
            
            // assigna el nou objecte creat com el que hi havÃ­a en l'arxiu
            dades = (Dades) ois.readObject();
            // tanquem el fluxos
            ois.close();
            fin.close();
            this.dades = dades;
        }catch(ClassNotFoundException e){
            throw new CentralUBException("Classe no trobada");
        }
    }
    
    /**
     * Es un getter del dia per a mostrarlo a la app
     * @return el Dia
     */
    public int getDia(){
        return dades.getDia();
    }
    
    /**
     * Getter per a mostrar els guanys Acumulats
     * @return guanysAcumulats
     */
    public float getGuanys(){
        return dades.getGuanysAcumulats();
    }
    
    /**
     * Getter de l'atribut activat del component
     * @return boolean
     */
    public boolean getActivatReactor(){
        return dades.mostraReactor().isActivat();
    }
    
    /**
     * Getter de l'atribut foraDeServei del component
     * @param num
     * @return boolean
     */
    public boolean getForaServei(int num){
        return dades.mostraSistemaRefrigeracio().getBomba(num).getForaDeServei();
    }  
    
    /**
     * Getter de l'atribut activat del component
     * @param num
     * @return boolean
     */
    public boolean getActivatBomba(int num){
        return dades.mostraSistemaRefrigeracio().getBomba(num).getActivat();
    }  
}