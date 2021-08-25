/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging;

/**
 *
 * @author Sam Benton
 */
public abstract class UnifiedDebuggingMetric {

    protected double priority;

    public double getPriority() {
        return priority;
    }

    /**
     * Compares this feature to the passed UnifiedDebuggingFeature.According to
     * traditional unified debugging, similar features with sufficient quality
     * (sufficientQuality = true) should have increased priorities and different
     * features with sufficient quality should have decreased priorities.
     *
     * @param feature1 One feature to be compared
     * @param feature2 The other feature to be compared
     * @param bool Represents if the comparingFeature is of sufficient quality
     * (i.e. high-quality patch)
     *
     */
    public abstract void updateBlock(UnifiedDebuggingKey feature1, UnifiedDebuggingKey feature2, Boolean bool);

    public abstract String describe();
}
