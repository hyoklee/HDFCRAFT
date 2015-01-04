    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package hdfcraft;

    import java.awt.*;
    import java.io.File;
    import java.io.IOException;
    import java.util.Random;
    import static hdfcraft.minecraft.Constants.*;
    import static hdfcraft.Constants.*;

    import ucar.ma2.ArrayFloat;
    import ucar.nc2.dataset.NetcdfDataset;
    import ucar.nc2.Variable;


    /**
     *
     * @author Simon
     */
    public class Hdfcraft {

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            System.out.println("HDFCRAFT version 0.0.1\n");

            // Download and save HDF file at the top level project directory.
            // For example, C:\Users\hyoklee\Documents\GitHub\HDFCRAFT
            // String filename = "MOD14CM1.201401.005.01.hdf";
            String filename = "Q20141722014263.L3m_SNSU_SCID_V3.0_SSS_1deg.h5";
            ucar.nc2.NetcdfFile nc = null;

            // Use toolsUI to open an HDF file and
            // determine which variable that you want to use.
            float[][] data = new float[360][180];
            try {

                nc = NetcdfDataset.openFile(filename, null);
                // Variable v = nc.findVariable("MeanCloudFraction");
                Variable v = nc.findVariable("l3m_data");

                long extent = v.getSize();
                ArrayFloat.D2 presArray;

                presArray = (ArrayFloat.D2) v.read();
                int[] shape1 = presArray.getShape();

                System.out.println("Shape[0]="+shape1[0]);
                for (int i = 0; i < shape1[0]; i++) {
                    for (int j = 0; j < shape1[1]; j++) {
                        data[j][i] = presArray.get(i, j);
                    }
                }


            } catch (IOException ioe) {
                System.out.println("Failed to open " + filename);
            } finally {
                if (null != nc) try {
                    nc.close();
                } catch (IOException ioe) {
                    System.out.println("Failed to close " + filename);
                }
            }
            // Reduce random noise.
            // HeightMapTileFactory tileFactory = TileFactoryFactory.createNoiseTileFactory(new Random().nextLong(),
           HeightMapTileFactory tileFactory = TileFactoryFactory.createFlatTileFactory(new Random().nextLong(),
                    Terrain.GRASS, DEFAULT_MAX_HEIGHT_2, 58, 62, false, true);

            World2 world = new World2(World2.DEFAULT_OCEAN_SEED, tileFactory, 256);
            world.setName("HDF");
            world.setVersion(SUPPORTED_VERSION_2);

            // Select spawn point based on your point of interest.
            // world.setSpawnPoint(new Point(308, 53));
            world.setSpawnPoint(new Point(95, 144));

            // Creative mode so that you can check global map easily.
            world.setGameType(1);
            Generator generator = Generator.values()[1];
            // Dimension dim0 = world.getDimension(0);
            world.setGenerator(generator);

            final Dimension dimension = world.getDimension(0);

            int offsetX = 0;
            int offsetY = 0;
            // See also WorldPainter's HeightMapImporter.java.
            // int worldWaterLevel = 0;
            // Sea Surface Salinity varies from 30.0 - 40.0
            int worldWaterLevel = 30;


            int tileCount = 0;
            // Tile size is 128 x 128.
            // To cover 360 x 180, we need 3 x 2 tiles.
             for (int tileX = 0; tileX < 3; tileX++) {
                 for (int tileY = 0; tileY < 2; tileY++) {
                    final Tile tile = new Tile(tileX, tileY, 256);
                    for (int x = 0; x < TILE_SIZE; x++) {
                        for (int y = 0; y < TILE_SIZE; y++) {
                            int lat = y+(tileY *TILE_SIZE);
                            int lon = x+(tileX *TILE_SIZE);
                            float val = 0.0f;

                            System.out.println(val);
                            if((lat < 180) && (lon < 360)) {
                                if (data[lon][lat] > 0)
                                    val = data[lon][lat];
                            }
                            // float scale = 3.0f;
                            float level = 30.0f;
                            if (val > 30.0 ) {
                                level = (float) ((val - 30.0) / 10.0 * 128.0);
                            }
                            //  System.out.println(level);

                            final boolean void_;

                            tile.setHeight(x, y, level);
                            tile.setWaterLevel(x, y, worldWaterLevel);
                            // tileFactory.applyTheme(tile, x, y);
                        }
                    }
                    dimension.addTile(tile);
                    tileCount++;
                }
             }
            System.out.println("done");
            WorldExporter exporter = new WorldExporter(world);
            // Change hyoklee to your user'name.
            // For Mac, use ~/Library/Application Support/minecraft/saves/
            File baseDir = new File("C:\\Users\\hyoklee\\AppData\\Roaming\\.minecraft\\saves");
            String name = filename;
            File backupDir;
            try {
                backupDir = exporter.selectBackupDir(new File(baseDir, FileUtils.sanitiseName(name)));
                exporter.export(baseDir, name, backupDir);
            } catch (IOException e) {
                throw new RuntimeException("I/O error while exporting world", e);
            }

        }


    }
