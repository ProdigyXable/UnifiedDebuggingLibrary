/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging.classUtility;

import java.util.LinkedList;
import java.util.TreeMap;
import xable.info.unifieddebugging.UnifiedDebuggingFeature;

/**
 *
 * @author Sam Benton
 */
public class ExampleFeature extends UnifiedDebuggingFeature {

    public int sl = 1;
    public int dh = 1;
    public int dl = 1;
    public int sh = 1;

    public ExampleFeature(TreeMap<Comparable, Comparable> v, double p) {
        super(v, p);
    }

    @Override
    public Double getFeaturePriority() {
        return this.priority;
    }

    @Override
    public int compareTo(UnifiedDebuggingFeature o) {
        return ((Comparable) this.values.get(this.values.firstKey())).compareTo(o.values.get(o.values.firstKey()));
    }

    public String toString() {
        return String.format("Feature: %s", new Object[]{this.values.toString()});
    }

    public void updateFeaturePriority(UnifiedDebuggingFeature o, Boolean b) {
        LinkedList list = processMapCollection(this.values, o.values, b, new LinkedList());

        processMapCollection(o.values, this.values, b, list);
        this.priority = this.sh / Math.sqrt(((this.sh + this.dh) * (this.sh + this.sl)));
    }

    private LinkedList processMapCollection(TreeMap<Comparable, Comparable> values, TreeMap<Comparable, Comparable> o, Boolean b, LinkedList<Object> list) {
        for (Comparable key : values.keySet()) {
            if (!list.contains(key)) {
                list.add(key);

                Comparable o1 = values.get(key);
                Comparable o2 = o.get(key);

                if (b.equals(Boolean.TRUE)) {
                    if (o1.equals(o2)) {
                        this.sh++;
                    } else {
                        this.dh++;
                    }
                } else {
                    if (o1.equals(o2)) {
                        this.sl++;
                    } else {
                        this.dl++;
                    }
                }
            }
        }
        
        return list;
    }
}
