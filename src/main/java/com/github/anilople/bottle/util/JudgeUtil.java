package com.github.anilople.bottle.util;

import java.util.List;

/** 判断工具类. */
public class JudgeUtil {

  /**
   * @param spiritMove 精灵移动的场景
   * @param jarOpenStrategy 罐子打开的顺序
   * @return 在某种精灵移动的场景下，这种罐子打开的顺序，是否能发现精灵？
   * @throws IllegalArgumentException 如果2个list的长度不同
   */
  public static boolean existEqual(List<Integer> spiritMove, List<Integer> jarOpenStrategy) {
    if (spiritMove.size() != jarOpenStrategy.size()) {
      throw new IllegalArgumentException(
          "list's size should be equal, not "
              + spiritMove.size()
              + " and "
              + jarOpenStrategy.size());
    }
    final int len = spiritMove.size();
    for (int i = 0; i < len; i++) {
      if (spiritMove.get(i).equals(jarOpenStrategy.get(i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param jarOpenStrategy 罐子打开的顺序
   * @param spiritMoves 精灵移动的所有场景
   * @return 在某个罐子打开顺序下，精灵的所有移动是否都可以被找到？
   */
  public static boolean existEqualInAll(
      List<Integer> jarOpenStrategy, List<List<Integer>> spiritMoves) {
    for (List<Integer> spiritMove : spiritMoves) {
      if (!existEqual(spiritMove, jarOpenStrategy)) {
        return false;
      }
    }
    return true;
  }

  /** 判断这个移动场景是不是精灵的合法移动，精灵每次只能移动一步到左边或者右边，所以相邻的元素差值的绝对值必须为1. */
  public static boolean isSpiritMove(List<Integer> values) {
    for (int i = 0; i < values.size() - 1; i++) {
      if (1 != Math.abs(values.get(i) - values.get(i + 1))) {
        return false;
      }
    }
    return true;
  }
}
