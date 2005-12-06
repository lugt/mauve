// Tags: not-a-test

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
// the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, 
// Boston, MA 02110-1301 USA.

package gnu.testlet.javax.swing.plaf.metal.MetalFileChooserUI;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalFileChooserUI;

public class MyMetalFileChooserUI extends MetalFileChooserUI 
{
  public class MyFilterComboBoxModel extends FilterComboBoxModel
  {
    public MyFilterComboBoxModel()
    {
      
    }
  }

  public MyMetalFileChooserUI(JFileChooser fc) 
  {
    super(fc);
  }
  
  /**
   * Provides access to this protected method in the super class.
   */
  public JPanel getButtonPanel()
  {
    return super.getButtonPanel();
  }
  
  /**
   * Provides access to this protected method in the super class.
   */
  public JPanel getBottomPanel()
  {
    return super.getBottomPanel();
  }
  
  public ActionMap createActionMap()
  {
    return super.createActionMap();
  }
  
  public FilterComboBoxModel createFilterComboBoxModel() 
  {
    return super.createFilterComboBoxModel();
  }
  
  public JButton getApproveButton(JFileChooser fc)
  {
    return super.getApproveButton(fc);
  }
}
