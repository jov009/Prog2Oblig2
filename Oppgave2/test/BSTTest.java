import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BSTTest {

    static BST tree;

    @BeforeClass
    public static void setUp(){
        Integer[] arr = new Integer[]{
                3, 6, 45, 783, 23, 1, 23, 2, 3, 56
        };
        tree = new BST(arr);
        tree.preorder();
        System.out.println();
    }

    @Test
    public void InsertNewValueLargest(){
        assertThat(tree.insert(1024), is(equalTo(true)));
    }

    @Test
    public void InsertNewValueSmallest(){
        assertThat(tree.insert(-5), is(equalTo(true)));
    }

    @Test
    public void InsertNewValueMiddle(){
        assertThat(tree.insert(18), is(equalTo(true)));
    }

    @Test
    public void DeleteFirst(){
        assertThat(tree.delete(2), is(equalTo(true)));
    }


    @Test
    public void DeleteSecond(){
        assertThat(tree.delete(56), is(equalTo(true)));
    }

    @Test
    public void DeleteNull(){
        assertThat(tree.delete(null), is(equalTo(false)));
    }

    @Test
    public void DeleteNotThere(){
        assertThat(tree.delete(7), is(equalTo(false)));
    }

    @Test
    public void GetPathLeft(){
        ArrayList<Integer> thePath;
        thePath = new ArrayList<>(Arrays.asList(3, 1));
        assertThat(tree.getPath(1), is(equalTo(thePath)));
    }

    @Test
    public void GetPathRight(){
        ArrayList<Integer> thePath;
        thePath = new ArrayList<>(Arrays.asList(3, 6, 45, 23));
        assertThat(tree.getPath(23), is(equalTo(thePath)));
    }

    @Test
    public void GetPathLeaf(){
        ArrayList<Integer> thePath;
        thePath = new ArrayList<>(Arrays.asList(3, 6, 45, 783));
        assertThat(tree.getPath(783), is(equalTo(thePath)));
    }

    @Test
    public void GetPathNowhere(){
        assertThat(tree.getPath(-86), is(equalTo(null)));
    }

    @AfterClass
    public static void tearDown(){
        tree.preorder();
        System.out.println();
    }


}