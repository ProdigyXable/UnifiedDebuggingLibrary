/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import xable.info.unifieddebugging.metrics.factories.MetricFactory;

/**
 * The primary class of this library. Create an instance of this class when you
 * wish to utilize UnifiedDebugging
 *
 * @author Sam Benton
 * @param <T> Any user-defined class extending UnifiedDebuggingItem
 */
public class UnifiedDebuggingCollection<T extends UnifiedDebuggingItem> {

    // Sortable list of collection items
    LinkedList<UnifiedDebuggingItem> internalList;

    // Comparator to sort list collection items
    Comparator<UnifiedDebuggingItem> listSortingComparator;

    // HashMap for efficient updating of collection items
    HashMap<UnifiedDebuggingKey, LinkedList<UnifiedDebuggingItem>> dataBucket;

    // MetricFactory to initialize the metrics of new collection items
    MetricFactory metricFactory;

    /**
     * Main constructor for this library
     *
     * @param collection Collection of items to be used for unified debugging.
     * Features are automatically extracted from these items and inserted into
     * the HashMap
     * @param factory A MetricFactory that creates instances of
     * UnifiedDebuggingMetric for new UnifiedDebuggingItems
     * @see xable.info.unifieddebugging.metrics.factories.MetricFactory
     * @see xable.info.unifieddebugging.UnifiedDebuggingKey
     * @see xable.info.unifieddebugging.UnifiedDebuggingItem
     * @see xable.info.unifieddebugging.UnifiedDebuggingItem#createFeature()
     * @param itemComparator Comparator to sort UnifiedDebugging items. If null,
     * no sorting is used.
     */
    public UnifiedDebuggingCollection(Collection<UnifiedDebuggingItem> collection, Comparator<UnifiedDebuggingItem> itemComparator, MetricFactory factory) {
        assertNotNull("Initialization Metric Check", factory);

        this.listSortingComparator = itemComparator;
        this.internalList = new LinkedList<>();

        this.metricFactory = factory;
        this.dataBucket = new HashMap();

        /**
         * Add items from collection to (1) sortable list and (2) hashmap
         */
        for (UnifiedDebuggingItem item : collection) {

            // Ensures item's metric initialized / not null
            if (item.getMetric() == null) {
                if (!this.dataBucket.containsKey(item.getFeature())) {
                    // If item's feature is new, create new metric for key
                    item.setMetric(factory.create());
                } else {
                    // Else, retrieve existing metric for feature and insert into new items
                    UnifiedDebuggingMetric sharedFeatureMetric = this.dataBucket.get(item.getFeature()).getFirst().getMetric();
                    item.setMetric(sharedFeatureMetric);
                }
            }

            this.internalList.add(item);

            // If item's feature does not exist in map, add empty list to map key
            if (!this.dataBucket.containsKey(item.getFeature())) {
                LinkedList<UnifiedDebuggingItem> newList = new LinkedList();
                this.dataBucket.put(item.getFeature(), newList);
            }

            // Add item to map
            this.dataBucket.get(item.getFeature()).add(item);
        }

        // Ensure collection is sorted after initialization
        this.sortCollection();
    }

    /**
     * Compares all features in the TreeMap dataBucket to the passed
     * UnifiedDebuggingFeature.According to traditional unified debugging,
     * similar features with sufficient quality (sufficientQuality = true)
     * should have increased priorities and different features with sufficient
     * quality should have decreased priorities.
     *
     * @param comparingItem The feature to be compared
     * @param sufficientQuality Represents if the comparingFeature is of
     * sufficient quality (i.e. high-quality patch)
     */
    public void updateItems(UnifiedDebuggingItem comparingItem, Boolean sufficientQuality) {
        UnifiedDebuggingKey comparingFeature = comparingItem.getFeature();

        for (UnifiedDebuggingKey udf : this.dataBucket.keySet()) {
            LinkedList<UnifiedDebuggingItem> list = this.dataBucket.get(udf);
            UnifiedDebuggingItem item = list.getFirst();

            item.updateFeaturePriority(comparingFeature, sufficientQuality);

            /**
             * Not necessary to manually update each item's metric because all
             * items with the same feature now share the same metric instance.
             * E.g. updating one metric updates all similar items
             */
            if (false) {
                UnifiedDebuggingMetric block = item.getMetric();
                for (UnifiedDebuggingItem i : list) {
                    i.setMetric(block);
                }
            }
        }

        // Ensure collection is sorted updating items
        this.sortCollection();
    }

    public HashMap<UnifiedDebuggingKey, LinkedList<UnifiedDebuggingItem>> getDataBucket() {
        return this.dataBucket;
    }

    /**
     * Removes the first / leading element from the collection. Trims removed
     * element from sortable list and map
     *
     * @return The first / leading element from the collection
     */
    public UnifiedDebuggingItem pop() {
        UnifiedDebuggingItem item = this.internalList.pop();
        UnifiedDebuggingKey feature = item.getFeature();

        // Minor optimization, reducing the map size over time
        UnifiedDebuggingItem mapFirstElement = this.dataBucket.get(feature).peekFirst();
        assertEquals("Ensure 1-to-1 proper deletion", item, mapFirstElement);

        this.dataBucket.get(feature).removeFirst();
        if (this.dataBucket.get(feature).isEmpty()) {
            // Trims keys with empty lists
            this.dataBucket.remove(feature);
        }

        return item;
    }

    final public void sortCollection() {
        if (this.listSortingComparator != null) {
            Collections.sort(this.internalList, this.listSortingComparator);
        }
    }
}
