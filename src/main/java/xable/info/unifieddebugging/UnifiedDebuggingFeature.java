/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging;

import java.util.TreeMap;

/**
 *
 * @author Sam Benton
 */
public abstract class UnifiedDebuggingFeature implements Comparable<UnifiedDebuggingFeature> {

    protected Double priority;

    public TreeMap<Object, Comparable> values;

    public UnifiedDebuggingFeature(TreeMap<Object, Comparable> v, double p) {
        this.values = v;
        this.priority = Double.valueOf(p);
    }

    public abstract Double getFeaturePriority();

    public abstract void updateFeaturePriority(UnifiedDebuggingFeature paramUnifiedDebuggingFeature, Boolean paramBoolean);

    public abstract String toString();
}
