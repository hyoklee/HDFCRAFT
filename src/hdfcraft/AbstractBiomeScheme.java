/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;

import static hdfcraft.Constants.*;

/**
 *
 * @author pepijn
 */
public abstract class AbstractBiomeScheme implements BiomeScheme {
    @Override
    public int[] getBiomes(int x, int y, int width, int height) {
        int[] translatedBiomes;
        if ((width == TILE_SIZE) && (height == TILE_SIZE)) {
            translatedBiomes = TRANSLATED_BIOMES_BUFFER;
        } else {
            translatedBiomes = new int[width * height];
        }
        getBiomes(x, y, width, height, translatedBiomes);
        return translatedBiomes;
    }

    private static final int[] TRANSLATED_BIOMES_BUFFER = new int[TILE_SIZE * TILE_SIZE];
}