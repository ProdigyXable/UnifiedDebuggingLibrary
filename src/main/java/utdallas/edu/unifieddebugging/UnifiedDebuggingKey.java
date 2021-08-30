/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utdallas.edu.unifieddebugging;

import java.util.HashMap;
import java.util.Objects;

/**
 * Base class class representing the unified debugging sorting features
 *
 * @author Sam Benton
 */
public abstract class UnifiedDebuggingKey implements Comparable<UnifiedDebuggingKey> {

    public HashMap<Comparable, Comparable> values;

    public UnifiedDebuggingKey() {
        this.values = new HashMap();
    }

    public UnifiedDebuggingKey(HashMap<Comparable, Comparable> v) {
        this.values = v;
    }
    
    public abstract Comparable getRepresentation();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.values);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UnifiedDebuggingKey other = (UnifiedDebuggingKey) obj;
        if (!Objects.equals(this.values, other.values)) {
            return false;
        }
        return true;
    }

}
