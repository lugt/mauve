//Tags: JDK1.2

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

package gnu.testlet.java.awt.geom.Rectangle2D;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
* Some tests for the add() methods in the {@link Rectangle2D} class.
*/
public class add implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    // add(double, double)
    Rectangle2D r = new Rectangle2D.Double();
    r.add(1.0, 2.0);
    harness.check(r.getX(), 0.0);
    harness.check(r.getY(), 0.0);
    harness.check(r.getWidth(), 1.0);
    harness.check(r.getHeight(), 2.0);

    // add(Point2D)
    r = new Rectangle2D.Double();
    r.add(new Point2D.Double(1.0, 2.0));
    harness.check(r.getX(), 0.0);
    harness.check(r.getY(), 0.0);
    harness.check(r.getWidth(), 1.0);
    harness.check(r.getHeight(), 2.0);

    boolean pass = false;
    try
    {
      r.add((Point2D) null);
    }
    catch (NullPointerException e) 
    {
      pass = true;
    }
    harness.check(pass);
    
    // add(Rectangle2D)
    r = new Rectangle2D.Double();
    r.add(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
    harness.check(r.getX(), 0.0);
    harness.check(r.getY(), 0.0);
    harness.check(r.getWidth(), 4.0);
    harness.check(r.getHeight(), 6.0);
    
    pass = false;
    try
    {
      r.add((Rectangle2D) null);
    }
    catch (NullPointerException e) 
    {
      pass = true;
    }
    harness.check(pass);
     
  }

}