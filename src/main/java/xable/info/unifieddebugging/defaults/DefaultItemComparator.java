/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging.defaults;

import java.util.Comparator;
import xable.info.unifieddebugging.UnifiedDebuggingItem;

/**
 * A default comparator, optionally included, usable to compare UnifiedDebugging
 * items features
 *
 * @author Sam Benton
 */
public class DefaultItemComparator implements Comparator<UnifiedDebuggingItem> {

    @Override
    public int compare(UnifiedDebuggingItem o1, UnifiedDebuggingItem o2) {
        int priorityComparison = o1.getItemComparable().compareTo(o2.getItemComparable());
        return priorityComparison;
    }
}
