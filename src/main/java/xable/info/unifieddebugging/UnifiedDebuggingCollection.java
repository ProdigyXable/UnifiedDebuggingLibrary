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

/**
 *
 * @author Sam Benton
 */
public class UnifiedDebuggingCollection<T extends UnifiedDebuggingItemInterface> {

    LinkedList<UnifiedDebuggingItemInterface> internalList;

    Comparator<UnifiedDebuggingItemInterface> defaultItemComparator;

    Comparator<UnifiedDebuggingFeature> defaultFeatureComparator;

    TreeMap<UnifiedDebuggingFeature, LinkedList<UnifiedDebuggingItemInterface>> bucket;

    public UnifiedDebuggingCollection(Collection<UnifiedDebuggingItemInterface> c, Comparator<UnifiedDebuggingItemInterface> itemComparator, Comparator<UnifiedDebuggingFeature> featureComparator) {
        this.internalList = new LinkedList<>();
        this.defaultItemComparator = itemComparator;
        if (featureComparator == null) {
            featureComparator = new DefaultFeatureComparator();
        }
        this.defaultFeatureComparator = featureComparator;
        this.bucket = new TreeMap<>(this.defaultFeatureComparator);
        for (UnifiedDebuggingItemInterface item : c) {
            if (!this.bucket.containsKey(item.createFeature())) {
                LinkedList<UnifiedDebuggingItemInterface> newList = new LinkedList();
                this.bucket.put(item.createFeature(), newList);
            }
            ((LinkedList<UnifiedDebuggingItemInterface>) this.bucket.get(item.createFeature())).add(item);
        }
        if (this.defaultItemComparator != null) {
            for (UnifiedDebuggingFeature key : this.bucket.keySet()) {
                Collections.sort(this.bucket.get(key), this.defaultItemComparator);
            }
        }
    }

    public void updateItems(UnifiedDebuggingFeature o, Boolean b) {
        for (UnifiedDebuggingFeature udf : this.bucket.keySet()) {
            udf.updateFeaturePriority(o, b);
        }
    }

    public TreeMap<UnifiedDebuggingFeature, LinkedList<UnifiedDebuggingItemInterface>> getBucket() {
        return this.bucket;

    }
}
