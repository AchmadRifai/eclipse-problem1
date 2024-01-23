package com.jogjanode.jogjanodeproblem1;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class App {
    public static void main( String[] args ) throws IOException {
        try (final var in = new Scanner(System.in)) {
        	final var path = System.getenv("OUTPUT_PATH");
        	final var f = new File(Objects.nonNull(path) && !path.isEmpty() && !path.isBlank() ? path : "./out.txt");
        	if (!f.getParentFile().exists()) f.getParentFile().mkdirs();
        	if (f.exists()) f.delete();
        	try (final var out = new PrintStream(f)) {
        		final var line1 = in.nextLine();
        		final var nk = validatingLine1(line1);
        		final var line2 = in.nextLine();
        		final var prices = validatingLine2(line2);
        		if (nk[0] != prices.length) throw new RuntimeException("Invalid item size");
        		final var result = maximumToys(prices, nk[1]);
        		out.println(result);
        		System.out.println(result);
        	}
        }
    }

	public static Integer maximumToys(Integer[] prices, Integer k) {
		var budget = k;
		var cart = 0;
		for (var i : Stream.of(prices).sorted().toList()) {
			if (budget >= i) {
				budget -= i;
				cart++;
			} else break;
		}
		return cart;
	}

	public static Integer[] validatingLine2(String s) {
		return Stream.of(s.split(" "))
				.filter(s1 -> !s1.isEmpty() && !s1.isBlank())
				.map(Integer::parseInt)
				.filter(i -> i > 0)
				.toList()
				.toArray(new Integer[] {});
	}

	public static Integer[] validatingLine1(String s) {
		final var sa = validatingLine2(s);
		if (sa.length != 2) throw new RuntimeException("Invalid format numbers");
		return sa;
	}
}
