package org.eaSTars.socoan.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.junit.Test;

public class SourcecodeInputStreamTest {

	@Test
	public void testSingleByteRead() {
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("SingleByteRead".getBytes()));
		
		assertNotNull("Test object is null", sis);
		
		int value = -1;
		try {
			value = sis.read();
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		assertEquals("Checking the read value", 'S', value);
		
		try {
			assertEquals("Checking amount of available bytes", 13, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testMultiByteRead() {
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("MultiByteRead".getBytes()));
		
		assertNotNull("Test object is null", sis);
		
		byte[] buffer = new byte[5];
		
		try {
			sis.read(buffer);
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		
		assertEquals("Checking 1st byte", 'M', buffer[0]);
		assertEquals("Checking 2nd byte", 'u', buffer[1]);
		assertEquals("Checking 3rd byte", 'l', buffer[2]);
		assertEquals("Checking 4th byte", 't', buffer[3]);
		assertEquals("Checking 5th byte", 'i', buffer[4]);
		
		try {
			assertEquals("Checking amount of available bytes", 8, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}

	@Test
	public void testUnreadOneByte() {
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("UnreadOneByteRead".getBytes()));
		
		assertNotNull("Test object is null", sis);
		
		int value = -1;
		try {
			value = sis.read();
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		assertEquals("Checking the 1st read value", 'U', value);
		
		try {
			assertEquals("Checking amount of available bytes", 16, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
		
		try {
			value = sis.read();
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		assertEquals("Checking the 2nd read value", 'n', value);
		
		try {
			assertEquals("Checking amount of available bytes", 15, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
		
		sis.unread(value);
		
		try {
			assertEquals("Checking amount of available bytes", 16, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
		
		try {
			value = sis.read();
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		assertEquals("Checking the 3nd read value", 'n', value);
		
		try {
			value = sis.read();
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		assertEquals("Checking the 4th read value", 'r', value);
	}
	
	@Test
	public void testUnreadMultiByte() {
		SourcecodeInputStream uis = new SourcecodeInputStream(new ByteArrayInputStream("UnreadMultiByteRead".getBytes()));
		
		assertNotNull("Test object is null", uis);
		
		byte[] buffer = new byte[6];
		
		try {
			uis.read(buffer);
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		
		assertEquals("Checking 1st byte", 'U', buffer[0]);
		assertEquals("Checking 2nd byte", 'n', buffer[1]);
		assertEquals("Checking 3rd byte", 'r', buffer[2]);
		assertEquals("Checking 4th byte", 'e', buffer[3]);
		assertEquals("Checking 5th byte", 'a', buffer[4]);
		assertEquals("Checking 6th byte", 'd', buffer[5]);
		
		try {
			assertEquals("Checking amount of available bytes", 13, uis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
		
		buffer = new byte[4];
		buffer[0] = 'r';
		buffer[1] = 'e';
		buffer[2] = 'a';
		buffer[3] = 'd';
		
		uis.unread(buffer);
		
		try {
			assertEquals("Checking amount of available bytes", 17, uis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
		
		buffer = new byte[4];
		
		try {
			uis.read(buffer);
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		
		assertEquals("Checking 7rd byte", 'r', buffer[0]);
		assertEquals("Checking 8th byte", 'e', buffer[1]);
		assertEquals("Checking 9th byte", 'a', buffer[2]);
		assertEquals("Checking 10th byte", 'd', buffer[3]);
		
		try {
			assertEquals("Checking amount of available bytes", 13, uis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
		
		buffer = new byte[5];
		
		try {
			uis.read(buffer);
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		
		assertEquals("Checking 11th byte", 'M', buffer[0]);
		assertEquals("Checking 12th byte", 'u', buffer[1]);
		assertEquals("Checking 13rd byte", 'l', buffer[2]);
		assertEquals("Checking 14th byte", 't', buffer[3]);
		assertEquals("Checking 15th byte", 'i', buffer[4]);
		
		try {
			assertEquals("Checking amount of available bytes", 8, uis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
}
