/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;

import java.io.Serializable;
import java.lang.*;


/**
 *
 * @author pepijn
 */
public interface ExporterSettings<L extends Layer> extends Serializable, java.lang.Cloneable {
    boolean isApplyEverywhere();
    
    L getLayer();
    
    ExporterSettings<L> clone();
}