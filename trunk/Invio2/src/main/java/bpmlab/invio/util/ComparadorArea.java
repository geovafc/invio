/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.util;

import bpmlab.invio.entidade.Area;

/**
 *
 * @author Junior
 */
public class ComparadorArea implements java.util.Comparator {  
    @Override  
    public int compare(Object o1, Object o2){  
    Area c1 = (Area) o1;  
    Area c2 = (Area) o2;  
  
     return c1.getNome().compareTo(c2.getNome());  
  }  

 
    
}
