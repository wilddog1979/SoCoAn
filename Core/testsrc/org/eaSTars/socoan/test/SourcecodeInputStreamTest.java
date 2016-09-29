package org.eaSTars.socoan.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.test.AbstractLangTest;
import org.junit.Test;

public class SourcecodeInputStreamTest extends AbstractLangTest {

	@Test
	public void testSingleByteRead() {
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("SingleByteRead".getBytes()));
		
		assertNotNull("Test object is null", sis);
		
		int value = -1;
		try {
			value = sis.read();
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		assertEquals("Checking the read value", 'S', value);
		
		checkLeftover(sis, "ingleByteRead");
	}
	
	@Test
	public void testMultiByteRead() {
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("MultiByteRead".getBytes()));
		
		assertNotNull("Test object is null", sis);
		
		char[] buffer = new char[5];
		
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
		
		checkLeftover(sis, "ByteRead");
	}

	@Test
	public void testUnreadOneByte() {
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("UnreadOneByteRead".getBytes()));
		
		assertNotNull("Test object is null", sis);
		
		int value = -1;
		try {
			value = sis.read();
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		assertEquals("Checking the 1st read value", 'U', value);
		
		checkLeftover(sis, "nreadOneByteRead");
		
		try {
			value = sis.read();
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		assertEquals("Checking the 2nd read value", 'n', value);
		
		checkLeftover(sis, "readOneByteRead");
		
		sis.unread((char)value);
		
		checkLeftover(sis, "nreadOneByteRead");
		
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
		SourcecodeInputReader uis = new SourcecodeInputReader(new ByteArrayInputStream("UnreadMultiByteRead".getBytes()));
		
		assertNotNull("Test object is null", uis);
		
		char[] buffer = new char[6];
		
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
		
		checkLeftover(uis, "MultiByteRead");
		
		uis.unread("read");
		
		checkLeftover(uis, "readMultiByteRead");
		
		buffer = new char[4];
		
		try {
			uis.read(buffer);
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		
		assertEquals("Checking 7rd byte", 'r', buffer[0]);
		assertEquals("Checking 8th byte", 'e', buffer[1]);
		assertEquals("Checking 9th byte", 'a', buffer[2]);
		assertEquals("Checking 10th byte", 'd', buffer[3]);
		
		checkLeftover(uis, "MultiByteRead");
		
		buffer = new char[5];
		
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
		
		checkLeftover(uis, "ByteRead");
	}
	
	@Test
	public void testPartialUnreaded() {
		SourcecodeInputReader uis = new SourcecodeInputReader(new ByteArrayInputStream("PartialUnreadByteRead".getBytes()));
		
		assertNotNull("Test object is null", uis);
		
		char[] buffer = new char[7];
		
		try {
			uis.read(buffer);
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		
		assertEquals("Checking 1st byte", 'P', buffer[0]);
		assertEquals("Checking 2nd byte", 'a', buffer[1]);
		assertEquals("Checking 3rd byte", 'r', buffer[2]);
		assertEquals("Checking 4th byte", 't', buffer[3]);
		assertEquals("Checking 5th byte", 'i', buffer[4]);
		assertEquals("Checking 6th byte", 'a', buffer[5]);
		assertEquals("Checking 7th byte", 'l', buffer[6]);
		
		uis.unread("Partial");
		
		buffer = new char[13];
		
		try {
			uis.read(buffer);
		} catch (IOException e) {
			fail("Reading failed " + e.getMessage());
		}
		
		assertEquals("Checking 1st byte", 'P', buffer[0]);
		assertEquals("Checking 2nd byte", 'a', buffer[1]);
		assertEquals("Checking 3rd byte", 'r', buffer[2]);
		assertEquals("Checking 4th byte", 't', buffer[3]);
		assertEquals("Checking 5th byte", 'i', buffer[4]);
		assertEquals("Checking 6th byte", 'a', buffer[5]);
		assertEquals("Checking 7th byte", 'l', buffer[6]);
		
		assertEquals("Checking 8th byte", 'U', buffer[7]);
		assertEquals("Checking 9th byte", 'n', buffer[8]);
		assertEquals("Checking 10th byte", 'r', buffer[9]);
		assertEquals("Checking 11th byte", 'e', buffer[10]);
		assertEquals("Checking 12th byte", 'a', buffer[11]);
		assertEquals("Checking 13th byte", 'd', buffer[12]);
	}
}
