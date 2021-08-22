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
public class SeAprExample implements UnifiedDebuggingItem {

    Boolean e1;

    Boolean e2;

    Boolean e3;

    Boolean e4;

    public SeAprExample(Boolean i1, Boolean i2, Boolean i3, Boolean i4) {
        this.e1 = i1;
        this.e2 = i2;
        this.e3 = i3;
        this.e4 = i4;
    }

    @Override
    public Comparable getItemComparable() {
        return String.format("%s-%s-%s-%s", new Object[]{this.e1, this.e2, this.e3, this.e4});
    }

    @Override
    public UnifiedDebuggingFeature createFeature() {
        TreeMap<Comparable, Comparable> result = new TreeMap<>();
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
        return new ExampleFeature(result, 0.5D);
    }
}
