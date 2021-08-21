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
public class DefaultItemComparator implements Comparator<UnifiedDebuggingItemInterface> {

    public int compare(UnifiedDebuggingItemInterface o1, UnifiedDebuggingItemInterface o2) {
        int priorityComparison = o1.getComparable().compareTo(o2.getComparable());
        return priorityComparison;
    }
}
