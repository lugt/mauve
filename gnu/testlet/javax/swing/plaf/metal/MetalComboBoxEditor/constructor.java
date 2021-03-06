// Tags: JDK1.5

// Copyright (C) 2005, 2006 David Gilbert <david.gilbert@object-refinery.com>

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

package gnu.testlet.javax.swing.plaf.metal.MetalComboBoxEditor;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalComboBoxEditor;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
* Some tests for the constructor() in the {@link MetalComboBoxEditor} 
* class.
*/
public class constructor implements Testlet
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    // test with DefaultMetalTheme
    try
      {
        MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
        UIManager.setLookAndFeel(new MetalLookAndFeel());
      }
    catch (UnsupportedLookAndFeelException e)
      {
        e.printStackTrace();  
      }
    MetalComboBoxEditor editor = new MetalComboBoxEditor();
    JTextField tf = (JTextField) editor.getEditorComponent();
    harness.check(tf.getColumns(), 9);
    harness.check(tf.getInsets(), new Insets(2, 2, 2, 0));
    harness.check(tf.getMargin(), new Insets(0, 0, 0, 0));
    harness.check(!(tf.getInsets() instanceof UIResource));
  }
  
}
