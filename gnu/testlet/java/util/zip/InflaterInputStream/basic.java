// Tags: JDK1.1

// Copyright (C) 1999, 2004 Cygnus Solutions

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
// Boston, MA 02111-1307, USA.  */

package gnu.testlet.java.util.zip.InflaterInputStream;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.util.zip.*;
import java.io.*;

public class basic implements Testlet
{
  public void test (TestHarness harness)
  {
    harness.checkPoint ("compressing string");
    String s = "data to be written, data to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,ata to be written,data to be written,data to be written,data to be written,data to be written,data to be written,data to be written,data to be written";

    try {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DeflaterOutputStream dos = new DeflaterOutputStream(bos);
    new PrintStream(dos).print(s);
    dos.close();

    byte[] deflated_data = bos.toByteArray();
    harness.check(deflated_data.length < s.length());

    InflaterInputStream iis = new InflaterInputStream(new ByteArrayInputStream(deflated_data));
    String inflated = new BufferedReader(new InputStreamReader(iis)).readLine();
    harness.check(s, inflated);
    
    byte[] buffer = new byte[10];
    int count = iis.read(buffer, 0, 0);
    harness.check(count, 0);

    count = iis.read(buffer, 0, 1);
    harness.check(count, -1);

    } catch(IOException e)
	{
	    harness.check(false, "deflation tests fail");
	}

    try
      {
	harness.checkPoint("three lines");
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	DeflaterOutputStream dos = new DeflaterOutputStream(bos);
	new PrintStream(dos).print("line one\nline two\nline three");
	dos.close();

	byte[] deflated_data = bos.toByteArray();

	InflaterInputStream iis = new InflaterInputStream(new ByteArrayInputStream(deflated_data));

	byte[] reinflated_data = new byte[1024];
	harness.check(iis.read(reinflated_data), 30);
	harness.check(iis.read(reinflated_data), -1);
      }
    catch(IOException e)
      {
	harness.check(false, "deflation tests fail");
      }

    // There are apparently programs out there that depend on this behaviour.
    harness.checkPoint("Constructor");
    boolean exception_thrown = false;
    try
      {
	InflaterInputStream iis = new InflaterInputStream(null);
      }
    catch (NullPointerException npe)
      {
	exception_thrown = true;
      }
    harness.check(exception_thrown);

    ByteArrayInputStream bais = new ByteArrayInputStream(new byte[1]);
    exception_thrown = false;
    try
      {
	InflaterInputStream iis = new InflaterInputStream(bais, null);
      }
    catch (NullPointerException npe)
      {
	exception_thrown = true;
      }
    harness.check(exception_thrown);

    exception_thrown = false;
    try
      {
	InflaterInputStream iis = new InflaterInputStream(null,
							  new Inflater(),
							  1024);
      }
    catch (NullPointerException npe)
      {
	exception_thrown = true;
      }
    harness.check(exception_thrown);

    exception_thrown = false;
    try
      {
	InflaterInputStream iis = new InflaterInputStream(bais,
							  null,
							  1024);
      }
    catch (NullPointerException npe)
      {
	exception_thrown = true;
      }
    harness.check(exception_thrown);

    exception_thrown = false;
    try
      {
	InflaterInputStream iis = new InflaterInputStream(bais,
							  new Inflater(),
							  -1024);
      }
    catch (IllegalArgumentException iae)
      {
	exception_thrown = true;
      }
    harness.check(exception_thrown);
  }
}
