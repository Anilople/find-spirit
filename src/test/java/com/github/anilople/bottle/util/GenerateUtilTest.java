package com.github.anilople.bottle.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class GenerateUtilTest {

	@Test
	public void combination() {
		List<List<Integer>> matrix = GenerateUtil.combination(5, 6);
		assertEquals(15625, matrix.size());
	}
	
	@Test
	public void divide() {
		List<Integer> numbers = GenerateUtil.divide(1234, 10, 4);
		assertEquals(4, numbers.size());
		assertEquals(1, numbers.get(0).intValue());
		assertEquals(2, numbers.get(1).intValue());
		assertEquals(3, numbers.get(2).intValue());
		assertEquals(4, numbers.get(3).intValue());
	}

}
