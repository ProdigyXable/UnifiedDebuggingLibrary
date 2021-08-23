/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;
import xable.info.unifieddebugging.defaults.DefaultFeatureComparator;

/**
 * The primary class of this library. Create an instance of this class when you
 * wish to utilize UnifiedDebugging
 *
 * @author Sam Benton
 * @param <T> Any user-defined class extending UnifiedDebuggingItem
 */
public class UnifiedDebuggingCollection<T extends UnifiedDebuggingItem> {

    LinkedList<UnifiedDebuggingItem> internalList;

    Comparator<UnifiedDebuggingItem> defaultItemComparator;
    Comparator<UnifiedDebuggingFeatureSet> defaultFeatureComparator;

    TreeMap<UnifiedDebuggingFeatureSet, LinkedList<UnifiedDebuggingItem>> bucket;

    /**
     * Main constructor for this library
     *
     * @param collection Collection of items to be used for unified debugging.
     * Features are automatically extracted from this items via
     * @see xable.info.unifieddebugging.UnifiedDebuggingFeatureSet
     * @see xable.info.unifieddebugging.UnifiedDebuggingItem
     * @see xable.info.unifieddebugging.UnifiedDebuggingItem#createFeature()
     * @param itemComparator Comparator to sort UnifiedDebugging items. If null,
     * no sorting is used.
     * @param featureComparator Comparator to sort UnifiedDebugging features. If
     * null, DefaultFeatureComparator is used.
     */
    public UnifiedDebuggingCollection(Collection<UnifiedDebuggingItem> collection, Comparator<UnifiedDebuggingItem> itemComparator, Comparator<UnifiedDebuggingFeatureSet> featureComparator) {
        this.internalList = new LinkedList<>();
        this.defaultItemComparator = itemComparator;
        if (featureComparator == null) {
            featureComparator = new DefaultFeatureComparator();
        }
        this.defaultFeatureComparator = featureComparator;
        this.bucket = new TreeMap<>(this.defaultFeatureComparator);

        for (UnifiedDebuggingItem item : collection) {
            if (!this.bucket.containsKey(item.createFeature())) {
                LinkedList<UnifiedDebuggingItem> newList = new LinkedList();
                this.bucket.put(item.createFeature(), newList);
            }

            this.bucket.get(item.createFeature()).add(item);
        }

        if (this.defaultItemComparator != null) {
            for (UnifiedDebuggingFeatureSet key : this.bucket.keySet()) {
                Collections.sort(this.bucket.get(key), this.defaultItemComparator);
            }
        }
    }

    /**
     * Compares all features in the TreeMap bucket to the passed
     * UnifiedDebuggingFeature. According to traditional unified debugging,
     * similar features with sufficient quality (sufficientQuality = true)
     * should have increased priorities and different features with sufficient
     * quality should have decreased priorities.
     *
     * @param comparingFeature The feature to be compared
     * @param succifientQuality Represents if the comparingFeature is of
     * sufficient quality (i.e. high-quality patch)
     */
    public void updateItems(UnifiedDebuggingFeatureSet comparingFeature, Boolean succifientQuality) {
        for (UnifiedDebuggingFeatureSet udf : this.bucket.keySet()) {
            udf.updateFeaturePriority(comparingFeature, succifientQuality);
        }
    }

    public TreeMap<UnifiedDebuggingFeatureSet, LinkedList<UnifiedDebuggingItem>> getBucket() {
        return this.bucket;

    }
}
