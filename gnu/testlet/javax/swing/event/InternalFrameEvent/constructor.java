/* constructor.java -- some checks for the constructor in the
       InternalFrameEvent class.
   Copyright (C) 2006 David Gilbert <david.gilbert@object-refinery.com>
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

// Tags: JDK1.2

package gnu.testlet.javax.swing.event.InternalFrameEvent;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;

public class constructor implements Testlet
{
  public void test(TestHarness harness)
  {
    JInternalFrame f = new JInternalFrame("Title");
    InternalFrameEvent e = new InternalFrameEvent(f, 
            InternalFrameEvent.INTERNAL_FRAME_ACTIVATED);
    harness.check(e.getID(), InternalFrameEvent.INTERNAL_FRAME_ACTIVATED);
    harness.check(e.getInternalFrame(), f);
    
    // null frame
    boolean pass = false;
    try
    {
      e = new InternalFrameEvent(null, InternalFrameEvent.INTERNAL_FRAME_CLOSED);
    }
    catch (IllegalArgumentException event)
    {
      pass = true;
    }
    harness.check(pass);
    
    // unrecognised id
    e = new InternalFrameEvent(f, -999);
    harness.check(e.getID(), -999);
  }
}
