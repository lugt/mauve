// Tags: JDK1.2 

// Copyright (C) 2005, 2006, David Gilbert <david.gilbert@object-refinery.com>

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
// the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, 
// Boston, MA 02110-1301 USA.

package gnu.testlet.javax.swing.plaf.metal.MetalIconFactory;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.Icon;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.metal.MetalIconFactory;

/**
 * Some checks for the getInternalFrameAltMaximizeIcon() method.
 */
public class getInternalFrameAltMaximizeIcon implements Testlet 
{

  /**
   * Runs the test using the specified harness. 
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)   
  {
    Icon icon = MetalIconFactory.getInternalFrameAltMaximizeIcon(12);
    harness.check(icon.getIconWidth(), 12);
    harness.check(icon.getIconHeight(), 12);
    harness.check(icon instanceof UIResource);
    // check that a new instance is returned each time
    Icon icon2 = MetalIconFactory.getInternalFrameAltMaximizeIcon(12);
    harness.check(icon != icon2);
  }

}
