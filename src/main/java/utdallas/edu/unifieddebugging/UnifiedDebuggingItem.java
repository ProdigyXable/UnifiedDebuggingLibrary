/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utdallas.edu.unifieddebugging;

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
     * @return Creates a UnifiedDebuggingKey based on the class' properties
     */
    public UnifiedDebuggingKey getFeature();

    /**
     * Used in the DefaultItemComparator, defines a natural ordering of item
     * elements, used when the priorities of two item's match
     *
     * @see utdallas.edu.unifieddebugging.defaults.DefaultItemComparator
     * @return
     */
    public int getNaturalOrder();

    public void updateFeaturePriority(UnifiedDebuggingKey comparingFeature, Boolean sufficientQuality);

    /**
     * Set an item's metric
     *
     * @param block
     */
    public void setMetric(UnifiedDebuggingMetric block);

    /**
     * Retrieves an item's metric
     *
     * @return An item's metric
     */
    public UnifiedDebuggingMetric getMetric();
}
