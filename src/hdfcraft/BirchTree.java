/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;

import hdfcraft.minecraft.Material;
import static hdfcraft.minecraft.Material.*;

/**
 *
 * @author pepijn
 */
public class BirchTree extends DeciduousTree {
    public BirchTree() {
        super(WOOD_BIRCH, LEAVES_BIRCH);
    }

    public BirchTree(Material leafMaterial) {
        super(WOOD_BIRCH, leafMaterial);
    }

    private static final long serialVersionUID = 1L;
}