// Tags: JDK1.1

/* Copyright (C) 1999 Cygnus Solutions

   This file is part of Mauve.

   Mauve is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   Mauve is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with Mauve; see the file COPYING.  If not, write to
   the Free Software Foundation, 59 Temple Place - Suite 330,
   Boston, MA 02111-1307, USA.  */

package gnu.testlet.java.io.DataInputStream;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.io.*;

public class readLine implements Testlet
{
  public void test (TestHarness harness)
    {
	byte[] ib = { };

	DataInputStream din = 
	    new DataInputStream (new ByteArrayInputStream (ib));

	try {
	    harness.check (din.readLine(), null);
	} catch (IOException ex) {
	    harness.fail ("unexpected exception " + ex);
	}

	byte[] ib1 = { (byte) '\n' };
	din = new DataInputStream (new ByteArrayInputStream (ib1));
	try {
	    harness.check (din.readLine(), "");
	} catch (IOException ex) {
	    harness.fail ("unexpected exception " + ex);
	}

	byte[] ib2 = { (byte) '\r' };
	din = new DataInputStream (new ByteArrayInputStream (ib2));
	try {
	    harness.check (din.readLine(), "");
	} catch (IOException ex) {
	    harness.fail ("unexpected exception " + ex);
	}

	byte[] ib3 = { (byte) '\r', (byte) '\n' };
	din = new DataInputStream (new ByteArrayInputStream (ib3));
	try {
	    harness.check (din.readLine(), "");
	} catch (IOException ex) {
	    harness.fail ("unexpected exception " + ex);
	}
    }
}
