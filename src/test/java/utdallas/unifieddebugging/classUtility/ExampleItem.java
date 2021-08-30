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
public final class ExampleItem implements UnifiedDebuggingItem {

    UnifiedDebuggingMetric metric;
    UnifiedDebuggingKey feature;

    Boolean e1;
    Boolean e2;
    Boolean e3;
    Boolean e4;

    int naturalOrder;

    public ExampleItem(Boolean i1, Boolean i2, Boolean i3, Boolean i4, int order) {
        this.e1 = i1;
        this.e2 = i2;
        this.e3 = i3;
        this.e4 = i4;

        this.feature = createFeature();

        this.naturalOrder = order;
    }

    @Override
    public Comparable getItemComparable() {
        return String.format("%s-%s-%s-%s", this.e1, this.e2, this.e3, this.e4);
    }

    public UnifiedDebuggingKey createFeature() {
        HashMap<Comparable, Comparable> result = new HashMap<>();
        if (this.e1 != null) {
            result.put("e1", this.e1);
        }
        if (this.e2 != null) {
            result.put("e2", this.e2);
        }
        if (this.e3 != null) {
            result.put("e3", this.e3);
        }
        if (this.e4 != null) {
            result.put("e4", this.e4);
        }
        return new ExampleFeature(result);
    }

    @Override
    public void updateFeaturePriority(UnifiedDebuggingKey udk, Boolean bool) {
        this.metric.updateBlock(this.feature, udk, bool);
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
        return this.feature;
    }

    public String toString() {
        return (String) this.getItemComparable();
    }

    @Override
    public int getNaturalOrder() {
        return this.naturalOrder;
    }
}
