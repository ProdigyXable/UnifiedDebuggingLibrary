/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging.defaults;

import java.util.Comparator;
import xable.info.unifieddebugging.UnifiedDebuggingKey;

/**
 * A default comparator used to compare UnifiedDebugging sorting features This
 * class is used when no comparator is given to an instance of
 * UnifiedDebuggingCollection. Usable in a TreeMap-based
 * UnifiedDebuggingCollection
 *
 * @author Sam Benton
 */
public class DefaultFeatureComparator implements Comparator<UnifiedDebuggingKey> {

    @Override
    public int compare(UnifiedDebuggingKey o1, UnifiedDebuggingKey o2) {
        Comparable o1compare = o1.getRepresentation();
        Comparable o2compare = o2.getRepresentation();

        return o1compare.compareTo(o2compare);
    }
}
