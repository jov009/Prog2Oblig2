import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class TwoWayLinkedListTest {

    private static TwoWayLinkedList<Double> listen = new TwoWayLinkedList<>();

    @BeforeClass
    public static void setUp() {
        listen.addLast(1.2);
        listen.addLast(3.0);
        listen.addLast(14.7);
        listen.addLast(33.1);
        listen.addLast(2.4);
        listen.addLast(0.1);
        listen.addLast(2.4);
        listen.addLast(-5.6);
    }

    @Test
    public void ContainsFirstTest() {
        assertThat(listen.contains(1.2), is(equalTo(true)));
    }

    @Test
    public void ContainsInMiddleTest() {
        assertThat(listen.contains(33.1), is(equalTo(true)));
    }

    @Test
    public void ContainsLastTest() {
        assertThat(listen.contains(-5.6), is(equalTo(true)));
    }

    @Test
    public void ContainsFalseTest() {
        assertThat(listen.contains(4.0), is(equalTo(false)));
    }

    @Test
    public void GetIndexThreeTest() {
        assertThat(listen.get(3), is(equalTo(33.1)));
    }

    @Test
    public void GetIndexZeroTest() {
        assertThat(listen.get(0), is(equalTo(1.2)));
    }

    @Test
    public void GetIndexOutOfBounds() {
        assertThat(listen.get(-2), is(equalTo(null)));
    }

    @Test
    public void IndexOfNumberTest() {
        assertThat(listen.indexOf(2.4), is(equalTo(4)));
    }

    @Test
    public void IndexOfNothingTest() {
        assertThat(listen.indexOf(0.0), is(equalTo(-1)));
    }

    @Test
    public void LastIndexOfTwiceOccuring() {
        assertThat(listen.lastIndexOf(2.4), is(equalTo(6)));
    }

    @Test
    public void LastIndexOfOnceOccuring() {
        assertThat(listen.lastIndexOf(3.0), is(equalTo(1)));
    }

    @Test
    public void LastIndexOfNotOccuring() {
        assertThat(listen.lastIndexOf(0.0), is(equalTo(-1)));
    }

    @Test
    public void SetValueTest() {
        assertThat(listen.set(2, 7.9), is(equalTo(14.7)));
    }

    @Test
    public void SetValue2Test() {
        assertThat(listen.set(5, 7.9), is(equalTo(0.1)));
    }

    @Test
    public void SetValueOutOfBoundsTest() {
        assertThat(listen.set(18, 4.5), is(equalTo(null)));
    }

    @AfterClass
    public static void TearDown(){
        System.out.println(listen.toString());
    }

}