/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;
// package org.hdfeos;

import java.io.File;
import java.io.FileOutputStream;
import org.jnbt.NBTOutputStream;
import java.lang.System;
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
        // TODO code application logic here
        System.out.println("HDFCRAFT version 0.0.1\n");
        File levelDatFile = new File("level.dat");
        NBTOutputStream out = new NBTOutputStream(new GZIPOutputStream(new FileOutputStream(levelDatFile)));
        try {
            out.writeTag(toNBT());
        } finally {
            out.close();
        }
    }
    
}
