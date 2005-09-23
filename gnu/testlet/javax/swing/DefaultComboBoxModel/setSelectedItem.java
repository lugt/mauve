// Tags: JDK1.4

// Copyright (C) 2005 David Gilbert <david.gilbert@object-refinery.com>

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
// Boston, MA 02111-1307, USA.  

package gnu.testlet.javax.swing.DefaultComboBoxModel;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Some checks for the setSelectedItem() method in the 
 * {@link DefaultComboBoxModel} class.
 */
public class setSelectedItem 
  implements Testlet, ListDataListener 
{
  int index0;
  int index1;
  int eventType;
  
  public void contentsChanged(ListDataEvent event) 
  {
    eventType = event.getType();
    index0 = event.getIndex0();
    index1 = event.getIndex1();
  }
  
  public void intervalAdded(ListDataEvent event) 
  {
    eventType = event.getType();
    index0 = event.getIndex0();
    index1 = event.getIndex1();
  }
  
  public void intervalRemoved(ListDataEvent event) 
  {
    eventType = event.getType();
    index0 = event.getIndex0();
    index1 = event.getIndex1();
  }

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {   
    DefaultComboBoxModel m = new DefaultComboBoxModel(new Object[] {"A", "B", "C"});
    harness.check(m.getSelectedItem(), "A");
    
    m.setSelectedItem("C");
    harness.check(m.getSelectedItem(), "C");
    harness.check(eventType, ListDataEvent.CONTENTS_CHANGED);
    harness.check(index0, 0);
    harness.check(index1, 0);
    
    eventType = -1;
    index0 = -1;
    index1 = -1;
    m.setSelectedItem(null);
    harness.check(m.getSelectedItem(), null);
    harness.check(eventType, -1);
    harness.check(index0, -1);
    harness.check(index1, -1);

    eventType = -1;
    index0 = -1;
    index1 = -1;
    m.setSelectedItem("Z");
    harness.check(m.getSelectedItem(), "Z");
    harness.check(m.getSize(), 3);
    harness.check(m.getIndexOf("Z"), -1);
    harness.check(eventType, -1);
    harness.check(index0, -1);
    harness.check(index1, -1);
  }
}

