package org.pokemonrun;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.pokemonrun.util.Edge;

/**
 * Edge Tester.
 *
 * @author <Authors name>
 * @since <pre>���� 22, 2019</pre>
 * @version 1.0
 */
public class EdgeTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: intersects(Edge e)
     *
     */
    @Test
    public void testIntersects() throws Exception {
        Edge a = new Edge(0, 1, 1, 0), b = new Edge(0, 0, 1, 1);
        Assert.assertTrue(a.intersects(b));
        Assert.assertTrue(b.intersects(a));
        a = new Edge(0, 1, 1, 1);
        b = new Edge(0, 0, 1, 0);
        Assert.assertFalse(a.intersects(b));
        Assert.assertFalse(b.intersects(a));
        // a node on another edge
        a = new Edge(0, 2, 2, 0);
        b = new Edge(0, 0, 1, 1);
        Assert.assertTrue(a.intersects(b));
        Assert.assertTrue(b.intersects(a));
        a = new Edge(0, 2, 1, 1);
        b = new Edge(0, 0, 1, 1);
        Assert.assertTrue(a.intersects(b));
        Assert.assertTrue(b.intersects(a));
        a = new Edge(0, 3, 3, 0);
        b = new Edge(0, 0, 1, 1);
        Assert.assertFalse(a.intersects(b));
        Assert.assertFalse(b.intersects(a));
    }

    /**
     *
     * Method: equals(Object o)
     *
     */
    @Test
    public void testEquals() throws Exception {
        Edge a = new Edge(0, 1, 1, 0), b = new Edge(0, 0, 1, 1);
        Assert.assertFalse(a.equals(b));
        Assert.assertFalse(b.equals(a));
        a = new Edge(0, 1, 1, 1);
        b = new Edge(1, 1, 0, 1);
        Assert.assertTrue(a.equals(b));
        Assert.assertTrue(b.equals(a));
        a = new Edge(0, 1, 1, 1);
        b = new Edge(0, 1, 1, 1);
        Assert.assertTrue(a.equals(b));
        Assert.assertTrue(b.equals(a));
    }

    @Test
    public void testContains() throws Exception {
        Edge a = new Edge(0, 1, 1, 0);
        Assert.assertFalse(a.contains(0, 0));
        Assert.assertFalse(a.contains(1, 1));
        Assert.assertFalse(a.contains(-1, 2));
        Assert.assertFalse(a.contains(2, -1));
        Assert.assertFalse(a.contains(0.4, 0.4));
        Assert.assertTrue(a.contains(0, 1));
        Assert.assertTrue(a.contains(1, 0));
        Assert.assertTrue(a.contains(0.5, 0.5));
    }

    @Test
    public void testIsPoint() throws Exception {
        Edge a = new Edge(0, 1, 1, 0);
        Assert.assertFalse(a.isPoint());
        a = new Edge(0.1, 1, 0, 1);
        Assert.assertFalse(a.isPoint());
        a = new Edge(0, 1, 0, 1);
        Assert.assertTrue(a.isPoint());
    }

}
