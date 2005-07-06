// Tags: JDK1.3
// Uses: MyAction

// Copyright (C) 2005 David Gilbert <david.gilbert@object-refinery.com>

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

package gnu.testlet.javax.swing.AbstractAction;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * Some tests for the putValue() method in the {@link AbstractAction} class.
 */
public class putValue implements Testlet
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    MyAction a1 = new MyAction();
    
    a1.putValue(Action.NAME, "Name");
    harness.check(a1.getValue(Action.NAME), "Name");
    a1.putValue(Action.NAME, "Name2");
    harness.check(a1.getValue(Action.NAME), "Name2");
    a1.putValue(Action.NAME, null);
    harness.check(a1.getValue(Action.NAME), null);
 
    // try null key - it is not specified what happens here, Sun's 
    // implementation allows it but then throws an exception if you try to
    // retrieve the value using the null key...
    boolean pass = false;
    try
    {
      a1.putValue(null, "XYZ");
      /*Object value =*/ a1.getValue(null);
    }
    catch (NullPointerException e) 
    {
      pass = true;
    }
    harness.check(pass);
  }

}
