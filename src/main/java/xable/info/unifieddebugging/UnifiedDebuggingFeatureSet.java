/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging;

import java.util.TreeMap;

/**
 * Base class class representing the unified debugging sorting features
 * @author Sam Benton
 */
public abstract class UnifiedDebuggingFeatureSet implements Comparable<UnifiedDebuggingFeatureSet> {

    protected Double priority;

    public TreeMap<Comparable, Comparable> values;

    public UnifiedDebuggingFeatureSet(TreeMap<Comparable, Comparable> v, double p) {
        this.values = v;
        this.priority = p;
    }

    public abstract Double getFeaturePriority();

    /**
     * Compares this feature to the passed UnifiedDebuggingFeature. According to
     * traditional unified debugging, similar features with sufficient quality
     * (sufficientQuality = true) should have increased priorities and different
     * features with sufficient quality should have decreased priorities.
     *
     * @param paramUnifiedDebuggingFeature The feature to be compared
     * @param paramBoolean Represents if the comparingFeature is of sufficient
     * quality (i.e. high-quality patch)
     *
     */
    public abstract void updateFeaturePriority(UnifiedDebuggingFeatureSet paramUnifiedDebuggingFeature, Boolean paramBoolean);
}
