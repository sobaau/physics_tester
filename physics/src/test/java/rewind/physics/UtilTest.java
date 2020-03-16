package rewind.physics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static rewind.physics.Util.remap;
import static rewind.physics.Util.constrain;

public class UtilTest {

    @Test(expected = ArithmeticException.class)
    public void testremap() throws ArithmeticException {
        double d = 900;
        double rm = remap(d, 0, 2000, 0, 1);

        assertEquals("remap not working properly", 0.45, rm, 0.001);

        double excep = remap(d, 0, 0, 0, 1); // test the divide by zero exception

    }

    @Test
    public void testconstrain(){
        assertEquals(20, constrain(40,1,20), 0.001);
        assertEquals(1, constrain(-1,1,20), 0.001);
        assertEquals(5, constrain(5,1,20), 0.001);


    }


}
