/* getChars.java -- Some checks for the getChars() method in the GapContent
                    class.
   Copyright (C) 2006  David Gilbert <david.gilbert@object-refinery.com>
This file is part of Mauve.

Mauve is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Mauve is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mauve; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

*/

// Tags: 1.2

package gnu.testlet.javax.swing.text.GapContent;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.text.BadLocationException;
import javax.swing.text.GapContent;
import javax.swing.text.Segment;

public class getChars implements Testlet
{
    
  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    GapContent gc = new GapContent();
    Segment seg = new Segment();
    
    // check default result
    try
    {
      gc.getChars(0, 1, seg);
    }
    catch (BadLocationException e)
    {
      // ignore - tests below will fail if this happens
    }
    harness.check(seg.offset, 0);
    harness.check(seg.count, 1);
    harness.check(seg.array[0], '\n');

    // if len goes past end of range, should get BadLocationException
    boolean pass = false;
    try
    {
      gc.getChars(0, 2, seg);
    }
    catch (BadLocationException e)
    {
      pass = true;
    }
    harness.check(pass);

    // add some more text
    try
    {
      gc.insertString(0, "ABCDEFG");
    }
    catch (BadLocationException e)
    {
    }
    harness.check(gc.length(), 8);
    
    // if index < 0 should get BadLocationException
    pass = false;
    try
    {
      gc.getChars(-1, 3, seg);
    }
    catch (BadLocationException e)
    {
      pass = true;
    }
    harness.check(pass);
    
    // if index > end of text should get BadLocationException
    pass = false;
    try
    {
      gc.getChars(99, 1, seg);
    }
    catch (BadLocationException e)
    {
      pass = true;
    }
    harness.check(pass);
    
    // if len goes past end of range, should get BadLocationException
    pass = false;
    try
    {
      gc.getChars(0, 99, seg);
    }
    catch (BadLocationException e)
    {
      pass = true;
    }
    harness.check(pass);
    
    // try a zero length string
    try
    {
      gc.getChars(1, 0, seg);
    }
    catch (BadLocationException e)
    {
    }
    harness.check(seg.offset, 1);
    harness.check(seg.count, 0);
    
    // what happens for null Segment
    pass = false;
    try
    {
      gc.getChars(0, 1, null);
    }
    catch (NullPointerException e)
    {
      pass = true;
    }
    catch (BadLocationException e)
    {
      // ignore
    }
    harness.check(pass);
    
    // what happens if we update the Segment array, does that change the 
    // StringContent
    Segment seg2 = new Segment();
    Segment seg3 = new Segment();
    GapContent gc2 = new GapContent();
    try
    {
      gc2.insertString(0, "XYZ");
      gc2.getChars(0, 3, seg2);
      seg2.array[1] = '5';
      gc2.getChars(0, 3, seg3);
    }
    catch (BadLocationException e) 
    {
      // ignore
    }
    harness.check(seg2.array[1], '5');
    harness.check(seg3.array[1], '5');
  }

}