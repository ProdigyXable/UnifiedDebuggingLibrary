/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging;

/**
 * Interface addable to any class to allow it to work with this unified
 * debugging library
 *
 * @author Sam Benton
 */
public interface UnifiedDebuggingItem {

    /**
     *
     * @return Comparable to be used within unified debugging
     */
    Comparable getItemComparable();

    /**
     *
     * @return Creates a UnifiedDebuggingFeature based on the class' properties
     */
    UnifiedDebuggingFeature createFeature();
}
