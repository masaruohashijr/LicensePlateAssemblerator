package com.vindixit.iterator;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Test;

public class LicensePlateIteratorTest {

	private ArrayList<String> ar = new ArrayList<String>();

	@Test
	public final void testHasNextWithInvalidStateCodeInsideArray() {
		ar.add("CA-1234");
		ar.add("XX-1234");
		ar.add("OH-1774");
		ar.add("NE-4787");
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		assertTrue(licensePlateIterator.hasNext());
		LicensePlate next = licensePlateIterator.next();
		System.out.println(next.get_code());
		assertTrue(licensePlateIterator.hasNext());
	}
	
	@Test
	public final void testHasNextWithInvalidStateCodeAtTheEndOfTheArray() {
		ar.add("XX-1234");
		ar.add("XX-1234");
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		assertFalse(licensePlateIterator.hasNext());
	}
	
	@Test
	public final void testNextWhenFirstElementIsInvalid() {	
		ar.add("XX-1234");
		ar.add("NE-1234");
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		LicensePlate _next = licensePlateIterator.next();
		assertNotNull(_next);
		assertEquals(_next.get_code(), new LicensePlate("NE-1234").get_code());
	}
	
	@Test
	public final void testHasNextWithEmptyIterator() {	
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		assertFalse(licensePlateIterator.hasNext());
	}
	
	@Test
	public final void testNextOnAnEmptyCollection() {	
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		assertNull(licensePlateIterator.next());
	}
	
	@Test
	public final void testHasNextThreeTimesWithInvalidSingleElement() {	
		ar.add("XX-1234");
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		assertFalse(licensePlateIterator.hasNext());
		assertFalse(licensePlateIterator.hasNext());
		assertFalse(licensePlateIterator.hasNext());
	}
	
	@Test
	public final void testHasNextThreeTimesWithValidSingleElement() {	
		ar.add("CA-1234");
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		assertTrue(licensePlateIterator.hasNext());
		assertTrue(licensePlateIterator.hasNext());
		assertTrue(licensePlateIterator.hasNext());
	}
	
	@Test
	public final void testHasNextTwiceWithOnlyOneValid() {
		ar.add("CA-1234");
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		assertTrue(licensePlateIterator.hasNext());
		licensePlateIterator.next();
		assertFalse(licensePlateIterator.hasNext());
	}
	
	@Test
	public final void testHasNextTwiceWithOnlyOneInvalid() {
		ar.add("XX-1234");
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		assertFalse(licensePlateIterator.hasNext());
		licensePlateIterator.next();
		assertFalse(licensePlateIterator.hasNext());
	}
	
	@Test
	public final void testNextTwiceWithOnlyOneValid() {
		ar.add("NE-1234");
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		LicensePlate _next = licensePlateIterator.next();
		assertNotNull(_next);
		assertEquals(_next.get_code(), new LicensePlate("NE-1234").get_code());
		_next = licensePlateIterator.next();
		assertNull(_next);
	}
	
	@Test
	public final void testNextTwiceWithOnlyOneInvalid() {
		ar.add("XX-1234");
		LicensePlateIterator licensePlateIterator = new LicensePlateIterator(ar.iterator());
		LicensePlate _next = licensePlateIterator.next();
		assertNull(_next);
		_next = licensePlateIterator.next();
		assertNull(_next);
	}
	
}
