//Tags: not-a-test

//Copyright (C) 2004 Robert Schuster <thebohemian@gmx.net>

//This file is part of Mauve.

//Mauve is free software; you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation; either version 2, or (at your option)
//any later version.

//Mauve is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.

//You should have received a copy of the GNU General Public License
//along with Mauve; see the file COPYING.  If not, write to
//the Free Software Foundation, 59 Temple Place - Suite 330,
//Boston, MA 02111-1307, USA.

package gnu.testlet.javax.swing.JComboBox;

import javax.swing.ComboBoxModel;
import javax.swing.AbstractListModel;

/** A non-mutable ComboBoxModel implementation for use in a simple
 * selection test. The model supports unselection.
 * 
 * @author Robert Schuster
 */
public class TestModel1 extends AbstractListModel implements ComboBoxModel {

        private String[] principle = { "Free", "As", "In", "Freedom" };

	/** The currently selected item. Defaults to "In". This is needed
	 * for the test.
	 */
        private String selected = principle[2];

        public void setSelectedItem(Object o) {
        	if(o == null) {
        		selected = null;
        		return;
        	}
        	
                if(!(o instanceof String))
                         return;

                String str = (String) o;

                for(int i=0; i < principle.length; i++) {
                        if(principle[i].equals(str)) {
                                selected = principle[i];
                                return;
                        }
                }
        }

        public Object getSelectedItem() {
                return selected;
        }

        public Object getElementAt(int index) {
                return principle[index];
        }

        public int getSize() {
                return principle.length;
        }
        
}
