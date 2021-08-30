/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utdallas.edu.unifieddebugging.metrics.factories;

import utdallas.edu.unifieddebugging.UnifiedDebuggingMetric;

/**
 * Template factory class to create UnifiedDebuggingMetrics
 *
 * @author Sam Benton
 */
public abstract class MetricFactory {

    /**
     * Creates an instance of UnifiedDebuggingMetric, optionally based on
     * properties from this class
     *
     * @return An instance of UnifiedDebugggingMetric
     */
    public abstract UnifiedDebuggingMetric create();
}
