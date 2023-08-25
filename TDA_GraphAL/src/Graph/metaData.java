/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 *
 * @author alexx
 */
public class metaData implements Comparable<metaData>{
    private String metaData;
    
    public metaData(String metaData){
        this.metaData = metaData;
    }
    
    public void setMetaData(String metaData){
        this.metaData = metaData;
    }
    
    public String getMetaData(){
        return metaData;
    }
    
    //ECORADR QUE UN COMPARADOR DENTRO DE UNA CLASE, DEBE DEVOLVER UN ENTERO CIONDICIONAL
    @Override
    public int compareTo(metaData o) {
        return this.metaData.compareTo(o.getMetaData());
    }
}
