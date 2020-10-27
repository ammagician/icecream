package com.apang.icecream.auth.security;

public class UserCount {
	private static int count = 0;

	public static void add() {
		++count;
	}

	public static void remove() {
		--count;
	}

	public static int now() {
		return count;
	}
}
