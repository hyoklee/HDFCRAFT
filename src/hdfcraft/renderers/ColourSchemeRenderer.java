/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft.renderers;

import hdfcraft.ColourScheme;

/**
 *
 * @author pepijn
 */
public interface ColourSchemeRenderer extends LayerRenderer {
    void setColourScheme(ColourScheme colourScheme);
}
