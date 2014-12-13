/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;

import java.util.Random;


/**
 *
 * @author pepijn
 */
public class Jungle extends TreeLayer<Jungle> {
    private Jungle() {
        super("Jungle", "a jungle", 43);
    }

    @Override
    public TreeType pickTree(Random random) {
        int rnd = random.nextInt(20);
        if (rnd == 0) {
            return OAK_TREE;
        } else if (rnd < 10) {
            return JUNGLE_TREE;
        } else {
            return BUSH;
        }
    }

    public int getDefaultTreeChance() {
        return 640;
    }

    @Override
    public int getDefaultLayerStrengthCap() {
        return 9;
    }
    
    public static final Jungle INSTANCE = new Jungle();
    
    private static final JungleTree JUNGLE_TREE = new JungleTree();
    private static final OakTree    OAK_TREE    = new OakTree();
    private static final Bush       BUSH        = new Bush();
    private static final long serialVersionUID = 1L;
}