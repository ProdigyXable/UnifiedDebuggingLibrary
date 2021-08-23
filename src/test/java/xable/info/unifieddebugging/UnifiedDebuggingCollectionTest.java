/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xable.info.unifieddebugging;

import java.util.Collection;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import xable.info.unifieddebugging.classUtility.ExampleFeature;
import xable.info.unifieddebugging.classUtility.SeAprExample;
import xable.info.unifieddebugging.classUtility.StringItemLength;
import xable.info.unifieddebugging.classUtility.StringItemNormal;

/**
 *
 * @author Sam Benton
 */
public class UnifiedDebuggingCollectionTest {

    public UnifiedDebuggingCollectionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorTest1() {

        LinkedList list = new LinkedList();
        StringItemNormal s1 = new StringItemNormal("Apple");
        StringItemNormal s2 = new StringItemNormal("Ball");
        StringItemNormal s3 = new StringItemNormal("Coconut");
        StringItemNormal s4 = new StringItemNormal("Ball");
        StringItemNormal s5 = new StringItemNormal("XYZ");

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

        UnifiedDebuggingCollection<StringItemNormal> udc = new UnifiedDebuggingCollection(list, null, null);
        assertEquals("Proper bucket size check", 4, udc.bucket.keySet().size());

        assertEquals("Proper duplicate listing", 2, udc.bucket.get(s2.createFeature()).size());

        for (Collection c : udc.bucket.values()) {
            assertFalse("Check to see every key has non-empty colleciton", c.isEmpty());
        }
    }

    @Test
    public void constructorTest2() {
        LinkedList list = new LinkedList();
        StringItemLength s1 = new StringItemLength("1");
        StringItemLength s2 = new StringItemLength("11");
        StringItemLength s3 = new StringItemLength("111");
        StringItemLength s4 = new StringItemLength("2");
        StringItemLength s5 = new StringItemLength("11");

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

        UnifiedDebuggingCollection<StringItemLength> udc = new UnifiedDebuggingCollection(list, null, null);

        assertEquals("Proper bucket size check", 3, udc.bucket.keySet().size());

        LinkedList temp = new LinkedList();
        temp.add(s1);
        temp.add(s4);

        assertEquals("Proper ordering - Check 1", temp, udc.getBucket().get(s4.createFeature()));
        temp.clear();

        temp.add(s2);
        temp.add(s5);

        assertEquals("Proper ordering - Check 2", temp, udc.getBucket().get(s2.createFeature()));

        for (Collection c : udc.bucket.values()) {
            assertFalse("Check to see every key has non-empty colleciton", c.isEmpty());
        }

    }

    @Test
    public void reprioritizeTest() {

        LinkedList list = new LinkedList();
        SeAprExample p1 = new SeAprExample(true, true, true, null);
        SeAprExample p2 = new SeAprExample(true, true, true, true);
        SeAprExample p3 = new SeAprExample(null, true, true, null);
        SeAprExample p4 = new SeAprExample(true, null, null, null);
        SeAprExample p5 = new SeAprExample(null, null, null, true);
        SeAprExample p6 = new SeAprExample(null, null, null, null);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);

        UnifiedDebuggingCollection<SeAprExample> udc = new UnifiedDebuggingCollection(list, null, null);

        assertEquals("Proper bucket size check", 6, udc.bucket.size());

        ExampleFeature ef;

        udc.updateItems(p1.createFeature(), Boolean.FALSE);

        ef = getKey(udc.bucket.keySet(), p2.createFeature());
        assertEquals(String.format("%.2f", 0.32), String.format("%.2f", ef.getFeaturePriority()));

        ef = getKey(udc.bucket.keySet(), p3.createFeature());
        assertEquals(String.format("%.2f", 0.35), String.format("%.2f", ef.getFeaturePriority()));

        ef = getKey(udc.bucket.keySet(), p4.createFeature());
        assertEquals(String.format("%.2f", 0.41), String.format("%.2f", ef.getFeaturePriority()));

        udc.updateItems(p4.createFeature(), Boolean.TRUE);

        ef = getKey(udc.bucket.keySet(), p2.createFeature());
        assertEquals(String.format("%.2f", 0.33), String.format("%.2f", ef.getFeaturePriority()));

        ef = getKey(udc.bucket.keySet(), p3.createFeature());
        assertEquals(String.format("%.2f", 0.22), String.format("%.2f", ef.getFeaturePriority()));

    }

    private ExampleFeature getKey(Collection<UnifiedDebuggingFeatureSet> bucket, UnifiedDebuggingFeatureSet udf) {
        ExampleFeature ef = null;
        for (UnifiedDebuggingFeatureSet key : bucket) {
            if (key.toString().equals(udf.toString())) {
                ef = (ExampleFeature) key;
            }
        }

        assertNotNull(ef);
        return ef;
    }

}
