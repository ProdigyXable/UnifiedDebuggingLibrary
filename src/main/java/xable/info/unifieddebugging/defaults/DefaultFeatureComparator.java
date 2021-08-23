/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging.defaults;

import java.util.Comparator;
import xable.info.unifieddebugging.UnifiedDebuggingFeatureSet;

/**
 * A default comparator used to compare UnifiedDebugging sorting features This
 * class is used when no comparator is given to an instance of
 * UnifiedDebuggingCollection
 *
 * @author Sam Benton
 */
public class DefaultFeatureComparator implements Comparator<UnifiedDebuggingFeatureSet> {

    @Override
    public int compare(UnifiedDebuggingFeatureSet o1, UnifiedDebuggingFeatureSet o2) {
        int priorityComparison = Double.compare(o1.getFeaturePriority(), o2.getFeaturePriority());

        String o1String = "";
        String o2String = "";

        for (Comparable key : o1.values.keySet()) {
            o1String = String.format("%s/%s=%s", o1String, key, o1.values.get(key));
        }
        for (Comparable key : o2.values.keySet()) {
            o2String = String.format("%s/%s=%s", o2String, key, o2.values.get(key));
        }

        if (priorityComparison == 0) {
            return o1String.compareTo(o2String);
        }

        return priorityComparison;
    }
}
