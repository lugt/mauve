// Tags: JDK1.2

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
// the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, 
// Boston, MA 02110-1301 USA.

package gnu.testlet.javax.swing.JFileChooser;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.io.File;

import javax.swing.JFileChooser;

/**
 * Some checks for the getSelectedFiles() method of the 
 * {@link JFileChooser} class.
 */
public class getSelectedFiles implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)       
  {
    JFileChooser fc = new JFileChooser();
    File[] files = fc.getSelectedFiles();
    harness.check(files.length, 0);
    
    File f1 = new File("X");
    files = new File[] { f1 };
    fc.setSelectedFiles(files);
    File[] ff = fc.getSelectedFiles();
    harness.check(ff[0], f1);
        
    // try null
    fc.setSelectedFiles(null);
    ff = fc.getSelectedFiles();
    harness.check(ff.length, 0);
    
    // try an empty array
    fc.setSelectedFiles(new File[0]);
    ff = fc.getSelectedFiles();
    harness.check(ff.length, 0);    
  }

}
