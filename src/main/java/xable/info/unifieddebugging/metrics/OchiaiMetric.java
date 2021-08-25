/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging.metrics;

import java.util.HashSet;
import java.util.Set;
import xable.info.unifieddebugging.UnifiedDebuggingKey;
import xable.info.unifieddebugging.UnifiedDebuggingMetric;

/**
 * Implements the Ochiai formula
 *
 * @author Sam Benton
 */
public class OchiaiMetric extends UnifiedDebuggingMetric {

    int sh = 1;
    int sl = 1;
    int dh = 1;
    int dl = 1;

    public OchiaiMetric() {
        super();
        this.priority = 0.5D;
    }

    @Override
    public void updateBlock(UnifiedDebuggingKey udk1, UnifiedDebuggingKey udk2, Boolean bool) {
        Set<Comparable> set1 = new HashSet(udk1.values.keySet());
        Set<Comparable> set2 = new HashSet(udk2.values.keySet());

        Set<Comparable> union = new HashSet();
        union.addAll(set1);
        union.addAll(set2);

        for (Comparable key : union) {
            Comparable o1 = udk1.values.get(key);
            Comparable o2 = udk2.values.get(key);

            if (bool.equals(Boolean.TRUE)) {
                if (this.compareAgainstPotentialNull(o1, o2)) {
                    this.sh++;
                } else {
                    this.dh++;
                }
            } else {
                if (this.compareAgainstPotentialNull(o1, o2)) {
                    this.sl++;
                } else {
                    this.dl++;
                }
            }
        }

        this.priority = this.sh / Math.sqrt(((this.sh + this.dh) * (this.sh + this.sl)));
    }

    @Override
    public String describe() {
        return String.format("sh=%d, dh=%d, sl=%d, dl=%d, p=%f", this.sh, this.dh, this.sl, this.dl, this.priority);
    }

    boolean compareAgainstPotentialNull(Comparable c1, Comparable c2) {
        boolean b1 = c1 == c2;

        if (b1) {
            return b1;
        } else {
            if (c1 != null) {
                return c1.equals(c2);
            } else {
                return c2.equals(c1);
            }
        }
    }
}
