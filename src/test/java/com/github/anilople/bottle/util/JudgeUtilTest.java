package com.github.anilople.bottle.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class JudgeUtilTest {

  @Test
  public void isSpiritMove() {
    assertTrue(JudgeUtil.isSpiritMove(Collections.<Integer>emptyList()));
    assertTrue(JudgeUtil.isSpiritMove(Arrays.asList(1)));
    assertTrue(JudgeUtil.isSpiritMove(Arrays.asList(1, 2, 3)));
    assertTrue(JudgeUtil.isSpiritMove(Arrays.asList(1, 2, 1)));
    assertTrue(JudgeUtil.isSpiritMove(Arrays.asList(3, 2, 3)));
    assertFalse(JudgeUtil.isSpiritMove(Arrays.asList(4, 2)));
  }

  @Test
  public void existEqual() {
    assertTrue(JudgeUtil.existEqual(Arrays.asList(1, 2, 3), Arrays.asList(3, 2, 1)));
    assertFalse(JudgeUtil.existEqual(Arrays.asList(1, 3, 3), Arrays.asList(3, 2, 1)));
  }

  @Test
  public void existEqualInAll() {
    assertTrue(
        JudgeUtil.existEqualInAll(
            Arrays.asList(1, 2, 3), Arrays.asList(Arrays.asList(3, 2, 1), Arrays.asList(1, 3, 3))));
    assertFalse(
        JudgeUtil.existEqualInAll(
          
            Arrays.   asList(1, 2, 3),   
                Arrays.asList(Arrays.asList(3, 3, 1), Arrays.asList(3, 2, 1), Arrays.asList(1, 3, 3))));
  }
  
  
  
}
