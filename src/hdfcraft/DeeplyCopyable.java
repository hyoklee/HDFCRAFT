/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hdfcraft;

/**
 * An object that can make a deep copy of itself.
 * 
 * @author pepijn
 */
public interface DeeplyCopyable<T> {
    T deepCopy();
}
