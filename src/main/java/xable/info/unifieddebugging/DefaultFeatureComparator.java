/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging;

import java.util.Comparator;

/**
 *
 * @author Sam Benton
 */
public class DefaultFeatureComparator implements Comparator<UnifiedDebuggingFeature> {

    public int compare(UnifiedDebuggingFeature o1, UnifiedDebuggingFeature o2) {
        int priorityComparison = Double.compare(o1.getFeaturePriority().doubleValue(), o2.getFeaturePriority().doubleValue());
        String o1String = "";
        String o2String = "";
        for (Object key : o1.values.keySet()) {
            o1String = String.format("%s/%s=%s", new Object[]{o1String, key, o1.values.get(key)});
        }
        for (Object key : o2.values.keySet()) {
            o2String = String.format("%s/%s=%s", new Object[]{o2String, key, o2.values.get(key)});
        }
        if (priorityComparison == 0) {
            return o1String.compareTo(o2String);
        }
        return priorityComparison;
    }
}
