package com.github.anilople.bottle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.anilople.bottle.util.GenerateUtil;
import com.github.anilople.bottle.util.JudgeUtil;

public class BottleApplication {

	public static void main(String[] args) {
		// 5个罐子，选6次
		System.out.println("罐子的编号分别为" + Arrays.asList(0, 1, 2, 3, 4));
		List<List<Integer>> strategies = GenerateUtil.combination(5, 6);
		System.out.println("策略总共有" + strategies.size() + "种");

		// 找出符合精灵在6次打开罐子前的合法移动顺序
		List<List<Integer>> spiritMoves = strategies.stream().filter(JudgeUtil::isSpiritMove)
				.collect(Collectors.toList());
		System.out.println("精灵移动的方式有" + spiritMoves.size() + "种");

		System.out.println("开始搜索让精灵无处可躲的罐子打开顺序");
		
		// 一定能找到精灵的策略
		List<List<Integer>> greatStrategies = new ArrayList<>();
		
		for (List<Integer> strategy : strategies) {
			if (JudgeUtil.existEqualInAll(strategy, spiritMoves)) {
				// 这个策略符合题目要求
				greatStrategies.add(strategy);
			}
		}
		
		// 输出找到的策略
		System.out.println("找到了" + greatStrategies.size() + "种策略，分别是");
		for (List<Integer> strategy : greatStrategies) {
			System.out.println(strategy);
		}
	}

}
