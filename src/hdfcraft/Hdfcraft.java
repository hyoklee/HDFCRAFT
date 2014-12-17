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
    import java.util.List;
    import static hdfcraft.minecraft.Constants.*;
    import static hdfcraft.Constants.*;

    import ucar.ma2.ArrayFloat;
    import ucar.multiarray.ArrayMultiArray;
    import ucar.multiarray.IndexIterator;
    import ucar.multiarray.MultiArray;
    import ucar.multiarray.MultiArrayImpl;
    import ucar.netcdf.Attribute;
    import ucar.netcdf.Netcdf;
    import ucar.netcdf.NetcdfFile;
    import ucar.nc2.dataset.NetcdfDataset;
    import ucar.nc2.Variable;
    import ucar.netcdf.VariableIterator;
    import ucar.nc2.*;


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
                System.out.println("HDFCRAFT version 0.0.1\n");

            String filename = "MOD14CM1.201401.005.01.hdf";
            // java.io.File = new File(filename);
            // NetcdfFile nc = null;
            ucar.nc2.NetcdfFile nc = null;
            float[][] data = new float[360][180];
            try {
                // nc = new NetcdfDataset.openFile(filename, null);
                // ucar.nc2.NetcdfFile
                nc = NetcdfDataset.openFile(filename, null);
                Variable v = nc.findVariable("MeanCloudFraction");

                long extent = v.getSize();
                ArrayFloat.D2 presArray;

                presArray = (ArrayFloat.D2) v.read();
                int[] shape1 = presArray.getShape();

                System.out.print("Shape[0]="+shape1[0]);
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
                // File file = new File("test.hdf");
                // image = ImageIO.read(file);
                HeightMapTileFactory tileFactory = TileFactoryFactory.createNoiseTileFactory(new Random().nextLong(),
                        Terrain.GRASS, DEFAULT_MAX_HEIGHT_2, 58, 62, false, true, 20, 1.0);

                World2 world = new World2(World2.DEFAULT_OCEAN_SEED, tileFactory, 256);
                world.setName("HDFCRAFT");
                world.setVersion(SUPPORTED_VERSION_2);
                world.setSpawnPoint(new Point(139, 14));

            world.setGameType(0);
                Generator generator = Generator.values()[0];
                // Dimension dim0 = world.getDimension(0);
                world.setGenerator(generator);

            final Dimension dimension = world.getDimension(0);

            int offsetX = 0;
            int offsetY = 0;
            // int worldWaterLevel = 62; // HeightMapImporter.java
            int worldWaterLevel = 6;

            final int widthInTiles = 10;
            final int heightInTiles = 5;
            final int totalTileCount = widthInTiles * heightInTiles;
            int tileCount = 0;
             for (int tileX = 0; tileX < 3; tileX++) {
                 for (int tileY = 0; tileY < 2; tileY++) {
                    final Tile tile = new Tile(tileX, tileY, 256);
                    // final Tile tile = new Tile(tileX, tileY, 0);
                    for (int x = 0; x < TILE_SIZE; x++) {
                        for (int y = 0; y < TILE_SIZE; y++) {
                            // final float level = 0.0f;
                            int lat = y+tileY*TILE_SIZE;
                            int lon = x+tileX*TILE_SIZE;
                            float val = 0.0f;


                            System.out.println(val);
                            if((lat < 180) && (lon < 360)) {
                                if (data[lon][lat] > 0)
                                    val = data[lon][lat];
                            }

                            final float level = (float)(255.0 * val);
                            System.out.println(level);

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
            File baseDir = new File(".");
            String name = "HDF";
            File backupDir;
            try {
                backupDir = exporter.selectBackupDir(new File(baseDir, FileUtils.sanitiseName(name)));
                exporter.export(baseDir, name, backupDir);
            } catch (IOException e) {
                throw new RuntimeException("I/O error while exporting world", e);
            }

        }


    }
