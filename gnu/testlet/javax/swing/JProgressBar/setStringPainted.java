/* setStringPainted.java -- some checks for the setStringPainted()
       method in the JProgressBar class.
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

package gnu.testlet.javax.swing.JProgressBar;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JProgressBar;

public class setStringPainted implements Testlet, PropertyChangeListener
{
  PropertyChangeEvent lastEvent;
  
  public void propertyChange(PropertyChangeEvent event) 
  {
    lastEvent = event;
  }

  public void test(TestHarness harness)
  {
    JProgressBar pb = new JProgressBar();
    harness.check(pb.isStringPainted(), false);
    
    pb.addPropertyChangeListener(this);
    pb.setStringPainted(false);
    harness.check(lastEvent, null);
    
    pb.setStringPainted(true);
    harness.check(pb.isStringPainted(), true);
    harness.check(lastEvent.getPropertyName(), "stringPainted");
    harness.check(lastEvent.getOldValue(), Boolean.FALSE);
    harness.check(lastEvent.getNewValue(), Boolean.TRUE);
  }
}
