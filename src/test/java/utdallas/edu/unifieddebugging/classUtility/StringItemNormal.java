/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utdallas.edu.unifieddebugging.classUtility;

import java.util.HashMap;
import utdallas.edu.unifieddebugging.UnifiedDebuggingItem;
import utdallas.edu.unifieddebugging.UnifiedDebuggingKey;
import utdallas.edu.unifieddebugging.UnifiedDebuggingMetric;

/**
 *
 * @author Sam Benton
 */
public class StringItemNormal implements UnifiedDebuggingItem {

    String string;
    Double initialPriority;
    UnifiedDebuggingMetric metric;

    public StringItemNormal(String s) {
        this.string = s;
        this.initialPriority = 0.5D;
    }

    HashMap<Comparable, Comparable> mapInternalValue(String s) {
        HashMap<Comparable, Comparable> result = new HashMap<>();
        result.put("F1", s);
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
        return this.initialPriority;
    }

    public void updateFeaturePriority() {
        throw new UnsupportedOperationException("Not supported yet.");
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
