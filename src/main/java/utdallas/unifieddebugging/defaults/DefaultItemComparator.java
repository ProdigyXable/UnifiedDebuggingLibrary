/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utdallas.edu.unifieddebugging.defaults;

import java.util.Comparator;
import utdallas.edu.unifieddebugging.UnifiedDebuggingItem;

/**
 * A default comparator usable to compare UnifiedDebugging items. Sorts on (1)
 * item priority, (2) item natural ordering, and (3) individual item comparable
 *
 * @author Sam Benton
 */
public class DefaultItemComparator implements Comparator<UnifiedDebuggingItem> {

    @Override
    public int compare(UnifiedDebuggingItem o1, UnifiedDebuggingItem o2) {
        int priorityCompare = Double.compare(o1.getMetric().getPriority(), o2.getMetric().getPriority());

        if (priorityCompare == 0) {
            int orderCompare = Integer.compare(o1.getNaturalOrder(), o2.getNaturalOrder());

            if (orderCompare == 0) {
                int compare = o1.getItemComparable().compareTo(o2.getItemComparable());
                return compare;
            } else {
                return orderCompare;
            }
        } else {
            // Descending priority order
            return -1 * priorityCompare;
        }
    }
}
