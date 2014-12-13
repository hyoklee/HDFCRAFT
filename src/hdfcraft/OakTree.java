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
public class OakTree extends DeciduousTree {
    public OakTree() {
        super(WOOD_OAK, LEAVES_OAK);
    }

    public OakTree(Material leafMaterial) {
        super(WOOD_OAK, leafMaterial);
    }

    private static final long serialVersionUID = 1L;
}