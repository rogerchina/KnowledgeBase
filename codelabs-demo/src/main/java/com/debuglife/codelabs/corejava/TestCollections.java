package com.debuglife.codelabs.corejava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

public class TestCollections {

	public static void main(String[] args) {

	}

}

@NotThreadSafe
class BadListHelper<E> {
	/**
	 * the object locked by synchronized is BadListHelper<E>
	 * 
	 * in retured list, the objected locked is another one.
	 * 
	 * so it is not thread-safe.
	 */

	public List<E> list = Collections.synchronizedList(new ArrayList<E>());

	public synchronized boolean putIfAbsent(E x) {

		boolean absent = !list.contains(x);

		if (absent)

			list.add(x);

		return absent;

	}

	// same functionality with putIfAbsent(E x)
	public boolean putIfAbsent1(E x) {

		synchronized (this) {

			boolean absent = !list.contains(x);

			if (absent) {
				list.add(x);
			}

			return absent;
		}

	}

}

@ThreadSafe
class GoodListHelper<E> {

	public List<E> list = Collections.synchronizedList(new ArrayList<E>());

	public boolean putIfAbsent(E x) {

		synchronized (list) {

			boolean absent = !list.contains(x);

			if (absent) {
				list.add(x);
			}

			return absent;

		}
	}
}