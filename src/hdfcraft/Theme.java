/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;

import java.io.Serializable;

/**
 *
 * @author SchmitzP
 */
public interface Theme extends Serializable {
    /**
     * Apply the theme to the specified coordinates of the tile. The coordinates
     * are relative to the tile, not absolute world coordinates.
     * 
     * @param tile
     * @param x
     * @param y 
     */
    void apply(Tile tile, int x, int y);
    int getMaxHeight();
    void setMaxHeight(int maxHeight, HeightTransform transform);
    long getSeed();
    void setSeed(long seed);
    int getWaterHeight();
    void setWaterHeight(int waterHeight);
    Theme clone();
}