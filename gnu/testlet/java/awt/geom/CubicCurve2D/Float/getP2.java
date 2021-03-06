// Tags: JDK1.2

// Copyright (C) 2003 Sascha Brawer <brawer@dandelis.ch>

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

package gnu.testlet.java.awt.geom.CubicCurve2D.Float;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.awt.geom.CubicCurve2D;


/**
 * Checks whether the CubicCurve2D.Float.getP2() method works
 * correctly.
 *
 * @author Sascha Brawer (brawer@dandelis.ch)
 */
public class getP2
  implements Testlet
{
  public void test(TestHarness harness)
  {
    CubicCurve2D.Float curve;

    curve = new CubicCurve2D.Float(-1, -2, -3, 42, -5, -6, -77.7f, 88.8f);
    harness.check(curve.getP2().getX(), -77.7f); // Check 1
    harness.check(curve.getP2().getY(), 88.8f);  // Check 2
  }
}
