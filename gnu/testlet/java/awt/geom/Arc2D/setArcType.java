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

package gnu.testlet.java.awt.geom.Arc2D;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.geom.Arc2D;

/**
 * Tests the setArcType() method in the {@link Arc2D} class.
 */
public class setArcType implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    Arc2D arc = new Arc2D.Double();
    arc.setArcType(Arc2D.OPEN);
    harness.check(arc.getArcType() == Arc2D.OPEN);
    arc.setArcType(Arc2D.CHORD);
    harness.check(arc.getArcType() == Arc2D.CHORD);
    arc.setArcType(Arc2D.PIE);
    harness.check(arc.getArcType() == Arc2D.PIE);
    
    boolean pass = false;
    try {
      arc.setArcType(99);
    }
    catch (IllegalArgumentException e) {
      pass = true;
    }
    harness.check(pass);
  }

}
