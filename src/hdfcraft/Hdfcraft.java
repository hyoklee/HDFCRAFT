/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnbt.NBTOutputStream;
import java.util.zip.GZIPOutputStream;
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
    }
    
}
