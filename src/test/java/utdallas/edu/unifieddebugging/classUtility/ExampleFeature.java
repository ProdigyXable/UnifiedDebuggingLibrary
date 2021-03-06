/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utdallas.edu.unifieddebugging.classUtility;

import java.util.HashMap;
import utdallas.edu.unifieddebugging.UnifiedDebuggingKey;

/**
 *
 * @author Sam Benton
 */
public class ExampleFeature extends UnifiedDebuggingKey {

    public ExampleFeature(HashMap<Comparable, Comparable> v) {
        super(v);
    }

    @Override
    public int compareTo(UnifiedDebuggingKey o) {
        return this.getRepresentation().compareTo(o.getRepresentation());
    }

    public String toString() {
        return String.format("Feature: %s", this.values.toString());
    }

    /**
     *
     * @return A comparable
     */
    @Override
    public Comparable getRepresentation() {
        String result = "";

        for (Comparable k : this.values.keySet()) {
            result += String.format("%s=%s/", k, this.values.get(k));
        }
        return result;
    }

}
