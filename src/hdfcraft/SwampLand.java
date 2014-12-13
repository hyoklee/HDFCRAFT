/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

/**
 *
 * @author pepijn
 */
public class SwampLand extends TreeLayer<SwampLand> {
    private SwampLand() {
        super("Swamp", "swamp land", 42, 'w');
        exporter = new SwampLandExporter(this);
    }

    @Override
    public SwampLandExporter getExporter() {
        return exporter;
    }
    
    @Override
    public TreeType pickTree(Random random) {
        return SWAMP_TREE;
    }

    @Override
    public int getDefaultMaxWaterDepth() {
        return 1;
    }

    @Override
    public int getDefaultTreeChance() {
        return 7680;
    }

    @Override
    public int getDefaultMushroomIncidence() {
        return 10;
    }

    @Override
    public float getDefaultMushroomChance() {
        return PerlinNoise.getLevelForPromillage(200);
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        exporter = new SwampLandExporter(this);
    }
    
    private transient SwampLandExporter exporter;
    
    public static final SwampLand INSTANCE = new SwampLand();
    
    private static final SwampTree SWAMP_TREE = new SwampTree();
    private static final long serialVersionUID = 1L;
}