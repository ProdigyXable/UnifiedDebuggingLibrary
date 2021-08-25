/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging.classUtility;

import java.util.TreeMap;
import xable.info.unifieddebugging.UnifiedDebuggingItem;
import xable.info.unifieddebugging.UnifiedDebuggingKey;
import xable.info.unifieddebugging.UnifiedDebuggingMetric;

/**
 *
 * @author Sam Benton
 */
public class StringItemLength implements UnifiedDebuggingItem {

    String string;
    Double priority;
    UnifiedDebuggingMetric metric;

    public StringItemLength(String s) {
        this.string = s;
        this.priority = 0.5D;
    }

    TreeMap<Comparable, Comparable> mapInternalValue(String s) {
        TreeMap<Comparable, Comparable> result = new TreeMap<>();
        result.put("F1", s.length());
        return result;
    }

    public UnifiedDebuggingKey createFeature() {
        return new ExampleFeature(mapInternalValue(this.string));
    }

    @Override
    public String toString() {
        return this.string;
    }

    public double getFeaturePriority() {
        return this.priority;
    }

    @Override
    public Comparable getItemComparable() {
        return this.string;
    }

    @Override
    public void updateFeaturePriority(UnifiedDebuggingKey udk, Boolean bln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMetric(UnifiedDebuggingMetric udmb) {
        this.metric = udmb;
    }

    @Override
    public UnifiedDebuggingMetric getMetric() {
        return this.metric;
    }

    @Override
    public UnifiedDebuggingKey getFeature() {
        return this.createFeature();
    }

    @Override
    public int getNaturalOrder() {
        return 1;
    }

}
