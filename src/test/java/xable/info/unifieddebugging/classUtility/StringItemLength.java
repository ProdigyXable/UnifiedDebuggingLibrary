/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging.classUtility;

import java.util.TreeMap;
import xable.info.unifieddebugging.UnifiedDebuggingFeature;
import xable.info.unifieddebugging.UnifiedDebuggingItem;

/**
 *
 * @author Sam Benton
 */
public class StringItemLength implements UnifiedDebuggingItem {

    String string;

    Double initialPriority;

    public StringItemLength(String s) {
        this.string = s;
        this.initialPriority = 0.5D;
    }

    TreeMap<Comparable, Comparable> mapInternalValue(String s) {
        TreeMap<Comparable, Comparable> result = new TreeMap<>();
        result.put("F1", s.length());
        return result;
    }

    @Override
    public UnifiedDebuggingFeature createFeature() {
        return new ExampleFeature(mapInternalValue(this.string), this.initialPriority);
    }

    @Override
    public String toString() {
        return this.string;
    }

    public double getFeaturePriority() {
        return this.initialPriority;
    }

    @Override
    public Comparable getItemComparable() {
        return this.string;
    }
}
