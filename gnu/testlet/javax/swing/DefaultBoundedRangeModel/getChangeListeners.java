// Tags: JDK1.4

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

package gnu.testlet.javax.swing.DefaultBoundedRangeModel;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Checks whether the DefaultBoundedRangeModel.getChangeListeners
 * method works correctly.
 *
 * @author Sascha Brawer (brawer@dandelis.ch)
 */
public class getChangeListeners
  implements Testlet
{
  public void test(TestHarness harness)
  {
    DefaultBoundedRangeModel dbrm;
    ChangeListener l1 = new MyListener();

    // Check #1.
    dbrm = new DefaultBoundedRangeModel();
    harness.check(dbrm.getChangeListeners().length == 0);

    // Check #2.
    dbrm.setRangeProperties(3, 2, 0, 10, false);
    harness.check(dbrm.getExtent(), 2);
  }

  private static class MyListener
    implements ChangeListener
  {
    public void stateChanged(ChangeEvent e)
    {
    }
  }
}
