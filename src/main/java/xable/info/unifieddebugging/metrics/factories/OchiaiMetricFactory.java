/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging.metrics.factories;

import xable.info.unifieddebugging.UnifiedDebuggingMetric;
import xable.info.unifieddebugging.metrics.OchiaiMetric;

/**
 * Creates an instance of the OchiaiMetric class
 * @see xable.info.unifieddebugging.metrics.OchiaiMetric
 * @author Sam Benton
 */
public class OchiaiMetricFactory extends MetricFactory {

    @Override
    public UnifiedDebuggingMetric create() {
        return new OchiaiMetric();
    }
}
