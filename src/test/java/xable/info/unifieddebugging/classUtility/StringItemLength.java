/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging.classUtility;

import java.util.TreeMap;
import xable.info.unifieddebugging.UnifiedDebuggingFeature;
import xable.info.unifieddebugging.UnifiedDebuggingItemInterface;

/**
 *
 * @author Sam Benton
 */
public class StringItemLength implements UnifiedDebuggingItemInterface {

    String string;

    Double initialPriority;

    public StringItemLength(String s) {
        this.string = s;
        this.initialPriority = Double.valueOf(0.5D);
    }

    TreeMap<Object, Comparable> mapInternalValue(String s) {
        TreeMap<Object, Comparable> result = new TreeMap<>();
        result.put("F1", Integer.valueOf(s.length()));
        return result;
    }

    public UnifiedDebuggingFeature createFeature() {
        return new ExampleFeature(mapInternalValue(this.string), this.initialPriority.doubleValue());
    }

    public String toString() {
        return this.string;
    }

    public double getFeaturePriority() {
        return this.initialPriority.doubleValue();
    }

    public Comparable getComparable() {
        return this.string;
    }
}