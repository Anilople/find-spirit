package com.github.anilople.bottle.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 数据生成的工具类. */
public class GenerateUtil {

  /**
   * n数字（0到n-1）可以使用无数次 必须选m个数字.
   *
   * <p>总共有n^m种选择方法.
   *
   * @return 所有选择方案（有顺序）
   */
  public static List<List<Integer>> combination(int n, int m) {
    // 不考虑溢出
    int valueBound = (int) Math.pow(n, m);
    System.out.println(n + "^" + m + "=" + valueBound);
    List<List<Integer>> answer = new ArrayList<>();
    for (int value = 0; value < valueBound; value++) {
      List<Integer> oneCombination = divide(value, n, m);
      answer.add(oneCombination);
    }
    return answer;
  }

  /**
   * @param value 值
   * @param n 进制
   * @param m 位数
   * @return 分开后的表示，每个值在0到n-1之间
   */
  public static List<Integer> divide(int value, int n, int m) {
    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      int number = value % n;
      int nextValue = value / n;
      numbers.add(number);
      // update
      value = nextValue;
    }
    Collections.reverse(numbers);
    return numbers;
  }

  public static List<List<Integer>> copyOf(List<List<Integer>> matrix) {
    List<List<Integer>> newMatrix = new ArrayList<>(matrix);
    for (int index = 0; index < newMatrix.size(); index++) {
      newMatrix.set(index, new ArrayList<>(matrix.get(index)));
    }
    return newMatrix;
  }
}
