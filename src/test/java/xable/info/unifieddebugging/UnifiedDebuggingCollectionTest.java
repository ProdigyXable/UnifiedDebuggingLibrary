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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import xable.info.unifieddebugging.classUtility.ExampleItem;
import xable.info.unifieddebugging.classUtility.StringItemLength;
import xable.info.unifieddebugging.classUtility.StringItemNormal;
import xable.info.unifieddebugging.defaults.DefaultItemComparator;
import xable.info.unifieddebugging.metrics.factories.MetricFactory;
import xable.info.unifieddebugging.metrics.factories.OchiaiMetricFactory;

/**
 *
 * @author Sam Benton
 */
public class UnifiedDebuggingCollectionTest {

    MetricFactory testFactory = new OchiaiMetricFactory();

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

        UnifiedDebuggingCollection<StringItemNormal> udc = new UnifiedDebuggingCollection(list, null, testFactory);
        assertEquals("Proper bucket size check", 4, udc.dataBucket.keySet().size());

        assertEquals("Proper duplicate listing", 2, udc.dataBucket.get(s2.createFeature()).size());

        for (Collection c : udc.dataBucket.values()) {
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

        UnifiedDebuggingCollection<StringItemLength> udc = new UnifiedDebuggingCollection(list, null, testFactory);

        assertEquals("Proper bucket size check", 3, udc.dataBucket.keySet().size());

        LinkedList temp = new LinkedList();
        temp.add(s1);
        temp.add(s4);

        assertEquals("Proper ordering - Check 1", temp, udc.getDataBucket().get(s4.createFeature()));
        temp.clear();

        temp.add(s2);
        temp.add(s5);

        assertEquals("Proper ordering - Check 2", temp, udc.getDataBucket().get(s2.createFeature()));

        for (Collection c : udc.dataBucket.values()) {
            assertFalse("Check to see every key has non-empty colleciton", c.isEmpty());
        }

    }

    @Test
    public void reprioritizeTest() {
        LinkedList list = new LinkedList();
        ExampleItem p1 = new ExampleItem(true, true, true, null, 1);
        ExampleItem p2 = new ExampleItem(true, true, true, true, 2);
        ExampleItem p3 = new ExampleItem(null, true, true, null, 3);
        ExampleItem p4 = new ExampleItem(true, null, null, null, 4);
        ExampleItem p5 = new ExampleItem(null, null, null, true, 5);
        ExampleItem p6 = new ExampleItem(null, null, null, null, 6);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);

        UnifiedDebuggingCollection<ExampleItem> udc = new UnifiedDebuggingCollection(list, null, testFactory);

        assertEquals("Proper bucket size check", 6, udc.dataBucket.size());

        udc.updateItems(p1, Boolean.FALSE);

        for (UnifiedDebuggingItem item : udc.internalList) {
            System.out.println(String.format("%s %s", item.getMetric().describe(), item.getItemComparable()));
        }

        assertEquals(String.format("%.2f", 0.32), String.format("%.2f", p2.getMetric().getPriority()));
        assertEquals(String.format("%.2f", 0.35), String.format("%.2f", p3.getMetric().getPriority()));
        assertEquals(String.format("%.2f", 0.41), String.format("%.2f", p4.getMetric().getPriority()));

        udc.updateItems(p4, Boolean.TRUE);
        assertEquals(String.format("%.2f", 0.33), String.format("%.2f", p2.getMetric().getPriority()));
        assertEquals(String.format("%.2f", 0.22), String.format("%.2f", p3.getMetric().getPriority()));
    }

    @Test
    public void popTest() {
        LinkedList list = new LinkedList();
        ExampleItem p1 = new ExampleItem(true, true, true, null, 1);
        ExampleItem p2 = new ExampleItem(true, true, true, true, 2);
        ExampleItem p3 = new ExampleItem(null, true, true, null, 3);
        ExampleItem p4 = new ExampleItem(true, null, null, null, 4);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        
        UnifiedDebuggingCollection<ExampleItem> udc = new UnifiedDebuggingCollection(list, new DefaultItemComparator(), testFactory);
        assertEquals("Proper bucket size check", 4, udc.dataBucket.size());

        ExampleItem i = (ExampleItem) udc.pop();
        assertEquals("Proper pop check - p1", p1.getFeature(), i.getFeature());
        udc.updateItems(i, Boolean.FALSE);

        i = (ExampleItem) udc.pop();
        assertEquals("Proper pop check - p4", p4.getFeature(), i.getFeature());
        assertEquals(String.format("%.2f", 0.41), String.format("%.2f", i.getMetric().getPriority()));
        udc.updateItems(i, Boolean.TRUE);

        i = (ExampleItem) udc.pop();
        assertEquals("Proper pop check - p2", p2.getFeature(), i.getFeature());
        assertEquals(String.format("%.2f", 0.33), String.format("%.2f", i.getMetric().getPriority()));
    }
}
