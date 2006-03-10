// Tags: JDK1.4

// Copyright (C) 2006  Red Hat

// This file is part of Mauve.

// Mauve is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.

// Mauve is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with Mauve; see the file COPYING.  If not, write to
// the Free Software Foundation, 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.

package gnu.testlet.javax.imageio.plugins.jpeg;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import gnu.testlet.java.awt.geom.Rectangle2D.Double.outcode;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.util.Arrays;

import javax.imageio.spi.IIORegistry;

/**
 * Test JPEGHuffmanTable construction and static fields.
 */
public class TestJPEGHuffmanTable implements Testlet {
	public void test(TestHarness h) {
		JPEGHuffmanTable t = null;
		boolean constructionFailed = false;

		// Some valid data for construction testing.
		short[] ACChrominanceLengths = { 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0,
				1, 2, 0x77 };

		short[] ACChrominanceValues = { 0x00, 0x01, 0x02, 0x03, 0x11, 0x04,
				0x05, 0x21, 0x31, 0x06, 0x12, 0x41, 0x51, 0x07, 0x61, 0x71,
				0x13, 0x22, 0x32, 0x81, 0x08, 0x14, 0x42, 0x91, 0xa1, 0xb1,
				0xc1, 0x09, 0x23, 0x33, 0x52, 0xf0, 0x15, 0x62, 0x72, 0xd1,
				0x0a, 0x16, 0x24, 0x34, 0xe1, 0x25, 0xf1, 0x17, 0x18, 0x19,
				0x1a, 0x26, 0x27, 0x28, 0x29, 0x2a, 0x35, 0x36, 0x37, 0x38,
				0x39, 0x3a, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a,
				0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x63, 0x64,
				0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x73, 0x74, 0x75, 0x76,
				0x77, 0x78, 0x79, 0x7a, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87,
				0x88, 0x89, 0x8a, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 0x98,
				0x99, 0x9a, 0xa2, 0xa3, 0xa4, 0xa5, 0xa6, 0xa7, 0xa8, 0xa9,
				0xaa, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6, 0xb7, 0xb8, 0xb9, 0xba,
				0xc2, 0xc3, 0xc4, 0xc5, 0xc6, 0xc7, 0xc8, 0xc9, 0xca, 0xd2,
				0xd3, 0xd4, 0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xda, 0xe2, 0xe3,
				0xe4, 0xe5, 0xe6, 0xe7, 0xe8, 0xe9, 0xea, 0xf2, 0xf3, 0xf4,
				0xf5, 0xf6, 0xf7, 0xf8, 0xf9, 0xfa };

		short[] ACLuminanceLengths = { 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0,
				0, 1, 0x7d };

		short[] ACLuminanceValues = { 0x01, 0x02, 0x03, 0x00, 0x04, 0x11, 0x05,
				0x12, 0x21, 0x31, 0x41, 0x06, 0x13, 0x51, 0x61, 0x07, 0x22,
				0x71, 0x14, 0x32, 0x81, 0x91, 0xa1, 0x08, 0x23, 0x42, 0xb1,
				0xc1, 0x15, 0x52, 0xd1, 0xf0, 0x24, 0x33, 0x62, 0x72, 0x82,
				0x09, 0x0a, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x25, 0x26, 0x27,
				0x28, 0x29, 0x2a, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3a,
				0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x53, 0x54,
				0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x63, 0x64, 0x65, 0x66,
				0x67, 0x68, 0x69, 0x6a, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
				0x79, 0x7a, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89, 0x8a,
				0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 0x98, 0x99, 0x9a, 0xa2,
				0xa3, 0xa4, 0xa5, 0xa6, 0xa7, 0xa8, 0xa9, 0xaa, 0xb2, 0xb3,
				0xb4, 0xb5, 0xb6, 0xb7, 0xb8, 0xb9, 0xba, 0xc2, 0xc3, 0xc4,
				0xc5, 0xc6, 0xc7, 0xc8, 0xc9, 0xca, 0xd2, 0xd3, 0xd4, 0xd5,
				0xd6, 0xd7, 0xd8, 0xd9, 0xda, 0xe1, 0xe2, 0xe3, 0xe4, 0xe5,
				0xe6, 0xe7, 0xe8, 0xe9, 0xea, 0xf1, 0xf2, 0xf3, 0xf4, 0xf5,
				0xf6, 0xf7, 0xf8, 0xf9, 0xfa };

		short[] DCChrominanceLengths = { 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,
				0, 0, 0 };

		short[] DCChrominanceValues = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

		short[] DCLuminanceLengths = { 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
				0, 0, 0 };

		short[] DCLuminanceValues = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

		// Test that it is impossible to construct an invalid Huffman table.
		// Both arguments are null.
		try {
			t = new JPEGHuffmanTable(null, null);
		} catch (IllegalArgumentException e) {
			constructionFailed = true;
		}
		h.check(constructionFailed);

		// values argument is null.
		constructionFailed = false;
		try {
			t = new JPEGHuffmanTable(ACLuminanceLengths, null);
		} catch (IllegalArgumentException e) {
			constructionFailed = true;
		}
		h.check(constructionFailed);

		// lengths argument is null.
		constructionFailed = false;
		try {
			t = new JPEGHuffmanTable(null, ACLuminanceValues);
		} catch (IllegalArgumentException e) {
			constructionFailed = true;
		}
		h.check(constructionFailed);

		// lengths argument has length > 16.
		short[] boguslengths = { 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1,
				0x7d, 5 };

		constructionFailed = false;
		try {
			t = new JPEGHuffmanTable(boguslengths, ACLuminanceValues);
		} catch (IllegalArgumentException e) {
			constructionFailed = true;
		}
		h.check(constructionFailed);

		// values argument has length > 256.
		short[] bogusvalues = new short[257];
		System.arraycopy(ACLuminanceValues, 0, bogusvalues, 0, 128);
		System.arraycopy(ACLuminanceValues, 0, bogusvalues, 128, 128);
		bogusvalues[256] = 0x4a;

		constructionFailed = false;
		try {
			t = new JPEGHuffmanTable(ACLuminanceLengths, bogusvalues);
		} catch (IllegalArgumentException e) {
			constructionFailed = true;
		}
		h.check(constructionFailed);

		// check bogus length value
		ACLuminanceLengths[3] = 16;
		constructionFailed = false;
		try {
			t = new JPEGHuffmanTable(ACLuminanceLengths, ACLuminanceValues);
		} catch (IllegalArgumentException e) {
			constructionFailed = true;
		}
		h.check(constructionFailed);
		// restore original value
		ACLuminanceLengths[3] = 3;

		// check bogus length total
		ACLuminanceLengths[9] = 6;
		constructionFailed = false;
		try {
			t = new JPEGHuffmanTable(ACLuminanceLengths, ACLuminanceValues);
		} catch (IllegalArgumentException e) {
			constructionFailed = true;
		}
		h.check(constructionFailed);
		// restore original value
		ACLuminanceLengths[9] = 5;

		// check bogus number of values
		short[] valueslessone = new short[ACLuminanceValues.length - 1];
		System.arraycopy(ACLuminanceValues, 0, valueslessone, 0,
				ACLuminanceValues.length - 1);
		constructionFailed = false;
		try {
			t = new JPEGHuffmanTable(ACLuminanceLengths, valueslessone);
		} catch (IllegalArgumentException e) {
			constructionFailed = true;
		}
		h.check(constructionFailed);

		// check StdACChrominance
		short[] lengths = JPEGHuffmanTable.StdACChrominance.getLengths();
		h.check (Arrays.equals(lengths, ACChrominanceLengths));

		short[] values = JPEGHuffmanTable.StdACChrominance.getValues();
		h.check (Arrays.equals(values, ACChrominanceValues));

		// check StdACLuminance
		lengths = JPEGHuffmanTable.StdACLuminance.getLengths();
		h.check(Arrays.equals(lengths, ACLuminanceLengths));

		values = JPEGHuffmanTable.StdACLuminance.getValues();
		h.check(Arrays.equals(values, ACLuminanceValues));

		// check StdDCChrominance
		lengths = JPEGHuffmanTable.StdDCChrominance.getLengths();
		h.check (Arrays.equals(lengths, DCChrominanceLengths));

		values = JPEGHuffmanTable.StdDCChrominance.getValues();
		h.check (Arrays.equals(values, DCChrominanceValues));

		// check StdDCLuminance
		lengths = JPEGHuffmanTable.StdDCLuminance.getLengths();
		h.check(Arrays.equals(lengths, DCLuminanceLengths));

		values = JPEGHuffmanTable.StdDCLuminance.getValues();
		h.check(Arrays.equals(values, DCLuminanceValues));
	}
}
