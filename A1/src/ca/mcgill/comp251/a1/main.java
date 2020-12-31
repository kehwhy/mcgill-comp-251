import static org.junit.Assert.*;

import java.util.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This tester uses JUnit 4, please add that to your build path.
 * 
 * This tester aims to firstly test basic usage of the hash maps, and then will
 * test edge cases after said basic usage.
 * 
 * 
 */
public class main {
  
  int seed = (int) System.nanoTime();

  /**
   * Test outputs of chaining hash function against calculated outputs
   */
  @Test
  public void testHashForChaining() {
    Chaining c = new Chaining(4, 0, -1);
    assertEquals(3, c.chain(1));
    assertEquals(1, c.chain(5));
    assertEquals(2, c.chain(100));
  }

  
  /**
   * Test if you can insert a single key into a chaining table when there are 
   * no elements in the bucket
   */
  @Test
  public void testChainingSingleKeyInsert() {
    Chaining c = new Chaining(4, 0, -1);
    int key = 14;
    int collisions = c.insertKey(key);
    assertEquals(0, collisions);
  }
  
  
  /**
   * Test if you can insert into chaining table when there are no elements in the
   * bucket
   */
  @Test
  public void testChainingBasicArrayInsert() {
    Chaining c = new Chaining(3, 0, -1);
    int[] keyArray = {0,1,2,3};
    int collisions = c.insertKeyArray(keyArray);
    assertEquals(0, collisions);
  }
  
  /**
   * Test insert function for a key-table combo resulting in a single collision
   */
  @Test
  public void testChainingSingleCollisionInsert() {
    Chaining c = new Chaining(4, 0, -1);
    int[] keyArray = {1,1};
    int collisions = c.insertKeyArray(keyArray);
    assertEquals(1, collisions);
  }
  
  /**
   * Test insert function for a key-table combo resulting in a full table
   * Each subsequent insert will cause collisions
   */
  @Test
  public void testChainingFullTableInsert() {
    Chaining c = new Chaining(4, 0, -1);
    int[] fillTable = {0,1,2,3,4};
    int[] extraKeys = {55,79,7,34};
    int collisions = c.insertKeyArray(fillTable) + c.insertKeyArray(extraKeys);
    assertNotEquals(0, collisions);
  }

  /**
   * Check outputs of probe hash function against calculated outputs
   */
  @Test
  public void testProbeHash() {
    Open_Addressing p = new Open_Addressing(4, 0, -1);
    assertEquals(3, p.probe(1, 0));
    assertEquals(3, p.probe(5, 2));
  }

  /**
   * Insert one value only
   */
  @Test
  public void testProbeSingleKeyInsert() {
    Open_Addressing p = new Open_Addressing(2, 0, -1);
    int key = 155;
    int collisions = p.insertKey(key);
    assertEquals(0, collisions);
  }
  
  /**
   * Insert several keys with no conflicts
   */
  @Test
  public void testProbeMultipleKeysInsert() {
    Open_Addressing p = new Open_Addressing(10, 0, -1);
    int[] keyArray = {0,1,4,2,5,6};
    int collisions = p.insertKeyArray(keyArray);
    assertEquals(0, collisions);
  }

  /**
   * Insert of identical probes resulting in single collision
   */
  @Test
  public void testProbeSingleConflictInsert() {
    Open_Addressing p = new Open_Addressing(3, 0, -1);
    int[] keyArray = {1, 1};
    int collisions = p.insertKeyArray(keyArray);
    assertEquals(1, collisions);
  }

  /**
   * More keys than slots means at least one collision
   */
  @Test
  public void testProbeOverflowInsert() {
    Open_Addressing p = new Open_Addressing(2, 0, -1);
    int[] keyArray = {1, 2, 3};
    p.insertKeyArray(keyArray);
    int collisions = p.insertKey(5);
    assertNotEquals(0, collisions);
  }

  /**
   * Removal were key is immediately found
   */
  @Test
  public void testProbeRemoval() {
    Open_Addressing p = new Open_Addressing(10, 0, -1);
    int[] keyArray = {1,5,97,457,3,678,76};
    p.insertKeyArray(keyArray);
    int collisions = p.removeKey(1);
    int checkRemoval = p.Table[p.probe(1, 0)];
    
    assertEquals(0, collisions);
    assertEquals(-1, checkRemoval);

  }

  /**
   * Removal were key is not in the first slot checked
   */
  public void testProbeCollisionsRemoval() {
    Open_Addressing p = new Open_Addressing(3, 0, -1);
    int[] keyArray = { 1, 2, 2, 3 };
    p.insertKeyArray(keyArray);
    
    int collisions = p.removeKey(2);

    assertNotEquals(0, collisions);
  }

  /**
   * Remove when there is no matching key in the table
   */
  public void testProbeKeyNotFoundRemoval() {
    Open_Addressing o = new Open_Addressing(10, 0, -1);
    int collisions = o.removeKey(2);
    int removedVal = o.Table[o.probe(2, 0)];
    assertEquals(-1, removedVal);
    assertEquals(o.m, collisions);
  }

}
