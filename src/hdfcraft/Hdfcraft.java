/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;

import java.awt.image.Raster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnbt.NBTOutputStream;

import javax.imageio.ImageIO;
import java.util.zip.GZIPOutputStream;
import java.awt.image.BufferedImage;
import static hdfcraft.minecraft.Constants.*;
import static hdfcraft.Constants.*;

/**
 *
 * @author Simon
 */
public class Hdfcraft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //try {
            // TODO code application logic here
            System.out.println("HDFCRAFT version 0.0.1\n");

            // TODO load a sample image file.
            // File file = new File("test.hdf");
            // image = ImageIO.read(file);
            HeightMapTileFactory tileFactory = TileFactoryFactory.createNoiseTileFactory(new Random().nextLong(),
                    Terrain.GRASS, DEFAULT_MAX_HEIGHT_2, 58, 62, false, true, 20, 1.0);

            World2 world = new World2(World2.DEFAULT_OCEAN_SEED, tileFactory, 255);
            world.setName("HDFCRAFT");
            final Dimension dimension = world.getDimension(0);

        int offsetX = 0;
        int offsetY = 0;
        int worldWaterLevel = 62; // HeightMapImporter.java

        final int widthInTiles = 36;
        final int heightInTiles = 18;
        final int totalTileCount = widthInTiles * heightInTiles;
        int tileCount = 0;
        for (int tileX = 0; tileX < 36; tileX++) {
            for (int tileY = 0; tileY < 18; tileY++) {
                final Tile tile = new Tile(tileX, tileY, 255);
                // final Tile tile = new Tile(tileX, tileY, 0);
                for (int x = 0; x < TILE_SIZE; x++) {
                    for (int y = 0; y < TILE_SIZE; y++) {
                        final float level = 0.0f;
                        final boolean void_;

                        tile.setHeight(x, y, level);
                        tile.setWaterLevel(x, y, worldWaterLevel);
                        tileFactory.applyTheme(tile, x, y);
                    }
                }
                dimension.addTile(tile);
                tileCount++;
                if(tileCount % 1000 == 0)
                    System.out.print(".");
           }
        }
        System.out.println("done");
        WorldExporter exporter = new WorldExporter(world);
       /*

        File levelDatFile = new File("level.dat");
            try {
                NBTOutputStream out = new NBTOutputStream(new GZIPOutputStream(
                        new FileOutputStream(levelDatFile)));

            }
            catch (IOException ex) {
                Logger.getLogger(Hdfcraft.class.getName()).log(Level.SEVERE, null, ex);
            }
            //try (NBTOutputStream out = new NBTOutputStream(new GZIPOutputStream(
            //        new FileOutputStream(levelDatFile)))) {
             //    out.writeTag(toNBT());
            // }
       // }    catch (IOException ex) {
       //     Logger.getLogger(Hdfcraft.class.getName()).log(Level.SEVERE, null, ex);
        // }
        */
    }

    
}
