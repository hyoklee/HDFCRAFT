/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft.renderers;

/**
 *
 * @author pepijn
 */
public interface NibbleLayerRenderer extends LayerRenderer {
    int getPixelColour(int x, int y, int underlyingColour, int value);
}