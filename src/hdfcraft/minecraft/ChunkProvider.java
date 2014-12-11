/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hdfcraft.minecraft;

/**
 *
 * @author pepijn
 */
public interface ChunkProvider {
    Chunk getChunk(int x, int z);
    Chunk getChunkForEditing(int x, int z);
}