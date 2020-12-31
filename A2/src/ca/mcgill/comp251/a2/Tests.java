package ca.mcgill.comp251.a2;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;

public class Tests {
  
  @Test
  public void testUnion1() {
    DisjointSets set = new DisjointSets(6);
    set.union(2, 3);
    set.union(2, 3);
    set.union(2, 1);
    set.union(4, 5);
    set.union(3, 1);
    set.union(2, 4);
    assertEquals(set.toString(), "2 set(s):\n" + "0 : 0\n" + "5 : 1,2,3,4,5\n");
  }
  
  @Test
  public void testUnion2() {
    DisjointSets set = new DisjointSets(4);
    set.union(0, 1);
    set.union(2, 3);
    set.union(0, 2);
    assertEquals(set.toString(), "1 set(s):\n" + "3 : 0,1,2,3\n");
  }
  
  @Test
  public void testUnionOutOfBounds() {
    DisjointSets set = new DisjointSets(1);
    set.union(1, 2);
    assertEquals(set.toString(), "1 set(s):\n0 : 0\n");
  }
  
  @Test
  public void kruskal () {
    WGraph g = new WGraph("g1.txt");
    WGraph t = Kruskal.kruskal(g);
    assertEquals(t.toString(), 
        "9\n"
        + "2 5 1\n"
        + "6 8 2\n"
        + "2 4 3\n"
        + "3 7 5\n"
        + "5 7 6\n"
        + "1 3 8\n"
        + "3 6 8\n"
        + "0 1 10");
  }
  
  @Test
  public void greedyHW () {
    int[] weights = new int[] {23, 60, 14, 25, 7}; 
    int[] deadlines = new int[] {3, 1, 2, 1, 3};
    int m = weights.length;
    HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
    int[] res = schedule.SelectAssignments();
    assertEquals(Arrays.toString(res), "[1, 2, 0]");
  }
  
  @Test
  public void greedyHW2 () {
    int[] weights = new int[] {23, 60, 14, 25, 18}; 
    int[] deadlines = new int[] {3, 1, 2, 1, 3};
    int m = weights.length;
    HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
    int[] res = schedule.SelectAssignments();
    assertEquals(Arrays.toString(res), "[1, 4, 0]");
  }
  
  @Test
  public void greedyHW3 () {
    int[] weights = new int[] {10, 10}; 
    int[] deadlines = new int[] {3, 6};
    int m = weights.length;
    HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
    int[] res = schedule.SelectAssignments();
    assertEquals(Arrays.toString(res), "[-1, -1, 0, -1, -1, 1]");
  }
  
  @Test
  public void greedyHW4 () {
    int[] weights = new int[] {5, 60, 45, 90}; 
    int[] deadlines = new int[] {1, 3, 3, 3};
    int m = weights.length;
    HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
    int[] res = schedule.SelectAssignments();
    assertEquals(Arrays.toString(res), "[2, 1, 3]");
  }
  
  @Test
  public void greedyHW5 () {
    int[] weights = new int[] {45, 15, 9, 12, 6, 17}; 
    int[] deadlines = new int[] {1, 2, 3, 3, 2, 4};
    int m = weights.length;
    HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
    int[] res = schedule.SelectAssignments();
    assertEquals(Arrays.toString(res), "[0, 1, 3, 5]");
  }
  
  @Test
  public void greedyHW6 () {
    int[] weights = new int[] {45, 15, 9, 12, 6, 17}; 
    int[] deadlines = new int[] {1, 1, 1, 1, 1, 6};
    int m = weights.length;
    HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
    int[] res = schedule.SelectAssignments();
    assertEquals(Arrays.toString(res), "[0, -1, -1, -1, -1, 5]");
  }
  
  
  
}
