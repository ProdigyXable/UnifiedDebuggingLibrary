/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utdallas.edu.unifieddebugging.metrics.factories;

import utdallas.edu.unifieddebugging.UnifiedDebuggingMetric;
import utdallas.edu.unifieddebugging.metrics.OchiaiMetric;

/**
 * Creates an instance of the OchiaiMetric class
 * @see utdallas.edu.unifieddebugging.metrics.OchiaiMetric
 * @author Sam Benton
 */
public class OchiaiMetricFactory extends MetricFactory {

    @Override
    public UnifiedDebuggingMetric create() {
        return new OchiaiMetric();
    }
}
