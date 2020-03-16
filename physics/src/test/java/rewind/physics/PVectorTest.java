package rewind.physics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PVectorTest {

    @Test
    public void testDefaultConstructor() {
        PVector p = new PVector();
        assertTrue("Default constructor not created in PVector", p.x == 0 && p.y == 0);
    }

    @Test
    public void testIntConstructor() {
        PVector p = new PVector(2, 7);
        assertEquals(2.0, p.x, 0.0001);
        assertEquals(7.0, p.y, 0.0001);

    }

    @Test
    public void testFloatConstructor() {
        PVector p = new PVector(2.12, 7.345);
        assertEquals(2.12, p.x, 0.0001);
        assertEquals(7.345, p.y, 0.0001);
    }

    @Test
    public void testCopy() {
        PVector a = new PVector(3.3, 5.78);
        PVector b = new PVector();

        b.copy(a);

        assertEquals("Copy method is not copying supplied PVector", 3.3, b.x, 0.0001);
        assertEquals("Copy method is not copying supplied PVector", 5.78, b.y, 0.0001);
    }

    @Test
    public void testFromAngle() {
        PVector p;
        p = PVector.fromAngle(10);

        assertEquals("PVector.fromAngle is not returning a PVector correctly from a supplied angle", -0.8390715, p.x, 0.0001);
        assertEquals("PVector.fromAngle is not returning a PVector correctly from a supplied angle", -0.5440211, p.y, 0.0001);

        p = PVector.fromAngle(0);

        assertEquals("PVector.fromAngle is not returning a PVector correctly from a supplied angle", 1.0, p.x, 0.0001);
        assertEquals("PVector.fromAngle is not returning a PVector correctly from a supplied angle", 0, p.y, 0.0001);

        p = PVector.fromAngle(-17);

        assertEquals("PVector.fromAngle is not returning a PVector correctly from a supplied angle", -0.27516335, p.x, 0.0001);
        assertEquals("PVector.fromAngle is not returning a PVector correctly from a supplied angle", 0.96139747, p.y, 0.0001);

    }

    @Test
    public void testMag() {
        PVector p = new PVector(17, 32);
        PVector q = new PVector();
        PVector r = new PVector(-17, 23);
        PVector s = new PVector(24, 0);

        assertEquals("mag() not returning the correct magnitude for the vector supplied", 36.23534, p.mag(), 0.0001);
        assertEquals("mag() not returning the correct magnitude for the vector supplied", 0, q.mag(), 0.0001);
        assertEquals("mag() not returning the correct magnitude for the vector supplied", 28.6006, r.mag(), 0.0001);
        assertEquals("mag() not returning the correct magnitude for the vector supplied", 24.0, s.mag(), 0.0001);

    }

    @Test
    public void testMagSq() {
        PVector p = new PVector(17, 32);
        PVector q = new PVector();
        PVector r = new PVector(-17, 23);
        PVector s = new PVector(24, 0);

        assertEquals("mag() not returning the correct magnitude for the vector supplied", 1313.0, p.magSq(), 0.0001);
        assertEquals("mag() not returning the correct magnitude for the vector supplied", 0, q.magSq(), 0.0001);
        assertEquals("mag() not returning the correct magnitude for the vector supplied", 818, r.magSq(), 0.0001);
        assertEquals("mag() not returning the correct magnitude for the vector supplied", 576, s.magSq(), 0.0001);
    }

    @Test
    public void testAdd() {
        PVector p = new PVector(-17, 23.3);
        PVector q = new PVector(2, 3.9);


        assertEquals("add() is not returning a PVector correctly from a supplied parameters", -17.0, p.x, 0.0001);
        assertEquals("add() is not returning a PVector correctly from a supplied parameters", 23.3, p.y, 0.0001);

        assertEquals("add() is not returning a PVector correctly from a supplied parameters", 2.0, q.x, 0.0001);
        assertEquals("add() is not returning a PVector correctly from a supplied parameters", 3.9, q.y, 0.0001);

        p.add(q);
        assertEquals("add() modifying the PVector object directly. It shouldn't do this", -17.0, p.x, 0.0001);
        assertEquals("add() modifying the PVector object directly. It shouldn't do this", 23.3, p.y, 0.0001);

        p = p.add(q);
        assertEquals("add() is not adding the vectors correctly", -15.0, p.x, 0.0001);
        assertEquals("add() is not adding the vectors correctly", 27.2, p.y, 0.0001);

    }

    @Test
    public void testStaticAdd() {
        PVector x = new PVector(1, 2);
        PVector y = new PVector(3, 4);
        PVector p = PVector.add(x, y);

        assertEquals("Static add is not functioning correctly", 4.0, p.x, 0.0001);
        assertEquals("Static add is not functioning correctly", 6.0, p.y, 0.0001);
    }

    @Test
    public void testStaticSub() {
        PVector x = new PVector(1, 2);
        PVector y = new PVector(3, 4);
        PVector p = PVector.sub(x, y);

        assertEquals("Static sub is not functioning correctly", -2.0, p.x, 0.0001);
        assertEquals("Static sub is not functioning correctly", -2.0, p.y, 0.0001);
    }


    @Test
    public void testAddxy() {
        PVector p = new PVector(-17, 23.3);


        assertEquals("add() is not returning a PVector correctly from a supplied parameters", -17.0, p.x, 0.0001);
        assertEquals("add() is not returning a PVector correctly from a supplied parameters", 23.3, p.y, 0.0001);

        p.add(2, 3.9);
        assertEquals("add() modifying the PVector object directly. It shouldn't do this", -17.0, p.x, 0.0001);
        assertEquals("add() modifying the PVector object directly. It shouldn't do this", 23.3, p.y, 0.0001);

        p = p.add(2, 3.9);
        assertEquals("add() is not adding the vectors correctly", -15.0, p.x, 0.0001);
        assertEquals("add() is not adding the vectors correctly", 27.2, p.y, 0.0001);

        PVector q = new PVector(-17, 23.3);
        q.add(2, 4);

        assertEquals("add() is not adding the vectors correctly", -17.0, q.x, 0.0001);
        assertEquals("add() is not adding the vectors correctly", 23.3, q.y, 0.0001);
        PVector e = new PVector(-17, 23.3);

        e = e.add(2, 4);
        assertEquals("add() is not adding the vectors correctly", -15.0, e.x, 0.0001);
        assertEquals("add() is not adding the vectors correctly", 27.3, e.y, 0.0001);
    }

    @Test
    public void testSub() {
        PVector p = new PVector(-17, 23.3);
        PVector q = new PVector(2, 3.9);


        assertEquals("sub() is not returning a PVector correctly from a supplied parameters", -17.0, p.x, 0.0001);
        assertEquals("sub() is not returning a PVector correctly from a supplied parameters", 23.3, p.y, 0.0001);

        assertEquals("sub() is not returning a PVector correctly from a supplied parameters", 2.0, q.x, 0.0001);
        assertEquals("sub() is not returning a PVector correctly from a supplied parameters", 3.9, q.y, 0.0001);

        p.sub(q);
        assertEquals("sub() modifying the PVector object directly. It shouldn't do this", -17.0, p.x, 0.0001);
        assertEquals("sub() modifying the PVector object directly. It shouldn't do this", 23.3, p.y, 0.0001);

        p = p.sub(q);
        assertEquals("sub() is not subtracting the vectors correctly", -19.0, p.x, 0.0001);
        assertEquals("sub() is not subtracting the vectors correctly", 19.4, p.y, 0.0001);


    }

    @Test
    public void testSubxy() {
        PVector p = new PVector(-17, 23.3);

        assertEquals("sub() is not returning a PVector correctly from a supplied parameters", -17.0, p.x, 0.0001);
        assertEquals("sub() is not returning a PVector correctly from a supplied parameters", 23.3, p.y, 0.0001);

        p.sub(2, 3.9);
        assertEquals("sub() modifying the PVector object directly. It shouldn't do this", -17.0, p.x, 0.0001);
        assertEquals("sub() modifying the PVector object directly. It shouldn't do this", 23.3, p.y, 0.0001);

        p = p.sub(2, 3.9);
        assertEquals("sub() is not subtracting the vectors correctly", -19.0, p.x, 0.0001);
        assertEquals("sub() is not subtracting the vectors correctly", 19.4, p.y, 0.0001);

        PVector q = new PVector(-17, 23.3);
        q.sub(2, 4);

        assertEquals("sub() is not adding the vectors correctly", -17.0, q.x, 0.0001);
        assertEquals("sub() is not adding the vectors correctly", 23.3, q.y, 0.0001);
        PVector e = new PVector(-17, 23.3);

        e = e.sub(2, 4);
        assertEquals("sub() is not adding the vectors correctly", -19.0, e.x, 0.0001);
        assertEquals("sub() is not adding the vectors correctly", 19.3, e.y, 0.0001);
    }

    @Test
    public void testMult() {
        PVector p = new PVector(-17, 23.3);
        PVector q = new PVector(-17, 23.3);

        p.mult(2);

        assertEquals("mult() is not multiplying the vector by the scalar value correctly", -34.0, p.x, 0.0001);
        assertEquals("mult() is not multiplying the vector by the scalar value correctly", 46.6, p.y, 0.0001);

        q.mult(-3.23);
        assertEquals("mult() is not multiplying the vector by the scalar value correctly", 54.91, q.x, 0.001);
        assertEquals("mult() is not multiplying the vector by the scalar value correctly", -75.259, q.y, 0.001);

    }

    @Test
    public void testDist() {
        PVector p = new PVector(-17, 23.3);
        PVector q = new PVector(2, 3.9);

        PVector r = new PVector(0, 0);
        PVector s = new PVector(2, 3);
        double d;

        d = p.dist(q);

        assertEquals("Dist() is not calculating distance correctly", 27.154373168945312, d, 0.0001);

        d = r.dist(s);
        assertEquals("Dist() is not calculating distance correctly", 3.605551, d, 0.0001);

    }

    @Test
    public void testStaticDist() {
        PVector p = new PVector(2, 3);
        PVector q = new PVector(4, 5);
        PVector r = new PVector(2.7, 3.1);
        PVector s = new PVector(4.2, 5.7);

        double d1 = PVector.dist(p, q);
        double d2 = PVector.dist(r, s);

        assertEquals("Dist() is not calculating distance correctly", 2.828427, d1, 0.0001);

        assertEquals("Dist() is not calculating distance correctly", 3.0016658, d2, 0.0001);


    }

    @Test
    public void testDot() {
        PVector p = new PVector(-17, 23.3);
        PVector q = new PVector(2, 3.9);

        double d;

        d = p.dot(q);
        assertEquals("Dot() is not calculating dot product correctly", 56.87, d, 0.0001);
    }

    @Test
    public void testSetMag() {
        PVector p = new PVector(3, 4);
        PVector q = new PVector(-17, 23.3);

        p.setMag(10);

        assertEquals("setMag() method is not setting the magnitude of the vector properly", 6.0, p.x, 0.001);
        assertEquals("setMag() method is not setting the magnitude of the vector properly", 8.0, p.y, 0.001);

        q.setMag(0.5);

        assertEquals("setMag() method is not setting the magnitude of the vector properly", -0.29470396, q.x, 0.001);
        assertEquals("setMag() method is not setting the magnitude of the vector properly", 0.4039178, q.y, 0.001);

        q.setMag(0);
        assertEquals("setMag() method is not setting the magnitude of the vector properly", 0, q.x, 0.001);
        assertEquals("setMag() method is not setting the magnitude of the vector properly", 0, q.y, 0.001);

        q.setMag(11);
        assertEquals("setMag() method is not setting the magnitude of the vector properly", 0, q.x, 0.001);
        assertEquals("setMag() method is not setting the magnitude of the vector properly", 0, q.y, 0.001);
    }

    @Test
    public void testHeading() {
        PVector q = new PVector(-17, 23.3);
        PVector p = new PVector(-3, -4);

        double h;

        h = q.heading();
        assertEquals("heading() isnt returning the correct heading for the vector supplied", 2.2011, h, 0.0001);

        h = p.heading();
        assertEquals("heading() isnt returning the correct heading for the vector supplied", -2.2142, h, 0.0001);

    }

    @Test
    public void testNormalise() {
        PVector q = new PVector(30, 40.2);
        q.normalise();
        assertEquals("normalise() method is not normalising the vector properly", 0.5980844, q.x, 0.001);
        assertEquals("normalise() method is not normalising the vector properly", 0.8014331, q.y, 0.001);

    }

    @Test
    public void testDiv() {
        PVector q = new PVector(30, 40.2);

        q.div(2);
        assertEquals("div() method is not dividing the vector properly", 15, q.x, 0.001);
        assertEquals("div() method is not dividing the vector properly", 20.1, q.y, 0.001);

        PVector e = new PVector(30, 40);
        e.div(2);
        assertEquals("div() method is not dividing the vector properly", 15, e.x, 0.001);
        assertEquals("div() method is not dividing the vector properly", 20, e.y, 0.001);


    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivByZero() {
        PVector q = new PVector(30, 40.2);

        q.div(0);
    }

    @Test
    public void testLimit() {
        PVector q = new PVector(30, 40.2);
        q.limit(3);
        assertEquals("limit() is not limiting the vector.", 1.7942531, q.x, 0.0001);
        assertEquals("limit() is not limiting the vector.", 2.4042993, q.y, 0.0001);

        PVector e = new PVector(30, 40);
        e.limit(3);
        assertEquals("limit() is not limiting the vector.", 1.8000001, e.x, 0.0001);
        assertEquals("limit() is not limiting the vector.", 2.4, e.y, 0.0001);
    }

    @Test
    public void testEqualsStatic(){
        PVector p = new PVector(1,2);
        PVector q = new PVector(1,2);
        PVector r = new PVector(3,3);

        boolean res1 = PVector.equals(p,q);
        boolean res2 = PVector.equals(q,r);
        boolean res3 = PVector.equals(p,r);

        assertTrue("Static PVector.equals not operating correctly", res1);
        assertTrue("Static PVector.equals not operating correctly", !res2);
        assertTrue("Static PVector.equals not operating correctly", !res3);
    }

    @Test
    public void testEquals(){
        PVector p = new PVector(5,6);
        PVector q = new PVector(5,6);
        PVector r = new PVector(3,3);

        boolean res1 = p.equals(q);
        boolean res2 = q.equals(r);
        boolean res3 = p.equals(r);

        assertTrue("Static equals method not operating correctly", res1);
        assertTrue("Static equals method not operating correctly", !res2);
        assertTrue("Static equals method not operating correctly", !res3);
    }
}
