//Tags: JDK1.0

//Copyright (C) 2004 David Gilbert <david.gilbert@object-refinery.com>

//Mauve is free software; you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation; either version 2, or (at your option)
//any later version.

//Mauve is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.

//You should have received a copy of the GNU General Public License
//along with Mauve; see the file COPYING.  If not, write to
//the Free Software Foundation, 59 Temple Place - Suite 330,
//Boston, MA 02111-1307, USA.  */

package gnu.testlet.java.awt.Rectangle;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Rectangle;

/**
 * Checks that the grow() method works correctly.
 */
public class grow implements Testlet {

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)   
  {
    Rectangle r = new Rectangle(0, 0, 0, 0);
    r.grow(5, 7);
    harness.check(r.x == -5);
    harness.check(r.y == -7);
    harness.check(r.width == 10);
    harness.check(r.height == 14);
  }

}