/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;

import java.io.File;

/**
 *
 * @author pepijn
 */
public interface MinecraftJarProvider {
    File getMinecraftJar(int biomeAlgorithm);
}