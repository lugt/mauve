// Tags: JDK1.2

// Copyright (C) 2006 Red Hat.

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

package gnu.testlet.javax.swing.text.DefaultStyledDocument.ElementBuffer;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;

public class ElementStructure5 extends DefaultStyledDocument implements Testlet
{
  /**
   * Starts the test run.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    try
      {
        h2 = harness;
        ElementStructure5 doc = new ElementStructure5();
        Element root = doc.getDefaultRootElement();

        // Add a first line of text.
        doc.insertString(0, "first line of text. \n", null);
        harness.check(root.getElementCount() == 2);
        harness.check(root.getElement(0).getStartOffset() == 0);
        harness.check(root.getElement(0).getEndOffset() == 21);
        harness.check(root.getElement(1).getStartOffset() == 21);
        harness.check(root.getElement(1).getEndOffset() == 22);


        // Add another line of text.
        doc.insertString(21, "second line of text. \n", null);
        harness.check(root.getElementCount() == 3);
        harness.check(root.getElement(0).getElementCount() == 1);
        harness.check(root.getElement(1).getElementCount() == 1);
        harness.check(root.getElement(2).getElementCount() == 1);


        Element first = root.getElement(0).getElement(0);
        harness.check(first.getStartOffset() == 0);
        harness.check(first.getEndOffset() == 21);

        Element second = root.getElement(1).getElement(0);
        harness.check(second.getStartOffset() == 21);
        harness.check(second.getEndOffset() == 43);

        Element third = root.getElement(2).getElement(0);
        harness.check(third.getStartOffset() == 43);
        harness.check(third.getEndOffset() == 44);
        
        // printElements(doc.getDefaultRootElement(), 0);
      }
    catch (Exception t)
      {
        // t.printStackTrace();
        harness.debug(t);
      }
    catch (AssertionError e)
      {
        // e.printStackTrace();
        harness.debug(e);
      }
  }
  
  static TestHarness h2;

  static int numInserts = 0;

  static int numLeaves = 0;

  static int numBranches = 0;

  // We override the constructor so we can explicitly set the type of the
  // buffer to be our ElementBuffer2, allowing us to test some internals.
  public ElementStructure5()
  {
    super();
    buffer = new ElementBuffer2(createDefaultRoot());
  }

  // Overriding this method allows us to check that the right number
  // of newLines was encountered and that the event has the proper
  // offset and length.
  protected void insertUpdate(DefaultDocumentEvent ev, AttributeSet attr)
  {
    int l = ev.getLength();
    int o = ev.getOffset();
    if (numInserts == 0)
      {
        h2.checkPoint("first doc event");
        h2.check(o == 0);
        h2.check(l == 21);
      }
    else if (numInserts == 1)
    {
      h2.checkPoint("second doc event");
      h2.check(o == 21);      
      h2.check(l == 22);
    } 
    else
      h2.fail ("too many calls to DefaultStyledDocument.insertUpdate");

    super.insertUpdate(ev, attr);
  }


  // A class to be the buffer of the styled document.
  // This allows us to check that some values are correct internally within
  // the ElementBuffer.
  public class ElementBuffer2 extends ElementBuffer
  {
    public ElementBuffer2(Element root)
    {
      super(root);
    }

    // This method allows us to check that the ElementSpecs generated by 
    // DefaultStyledDocument.insertUpdate are correct.
    protected void insertUpdate(ElementSpec[] data)
    {
      numInserts++;
      if (numInserts == 1)
        {
          h2.checkPoint("ElementBuffer insertUpdate: first insertion");
          h2.check (data.length == 3);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 21);

          h2.check(data[1].getType() == ElementSpec.EndTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);

          h2.check(data[2].getType() == ElementSpec.StartTagType);
          h2.check
            (data[2].getDirection() == ElementSpec.JoinFractureDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 0);
        }
      else if (numInserts == 2)
        {
          h2.checkPoint("ElementBuffer insertUpdate: second insertion");
          h2.check (data.length == 5);
          h2.check(data[0].getType() == ElementSpec.EndTagType);
          h2.check(data[0].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 0);
          
          h2.check(data[1].getType() == ElementSpec.StartTagType);
          h2.check(data[1].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[1].getOffset() == 0);
          h2.check(data[1].getLength() == 0);          
          
          h2.check(data[2].getType() == ElementSpec.ContentType);
          h2.check(data[2].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[2].getOffset() == 0);
          h2.check(data[2].getLength() == 22);
          
          h2.check(data[3].getType() == ElementSpec.EndTagType);
          h2.check(data[3].getDirection() == ElementSpec.OriginateDirection);
          h2.check(data[3].getOffset() == 0);
          h2.check(data[3].getLength() == 0);
          
          h2.check(data[4].getType() == ElementSpec.StartTagType);
          h2.check(data[4].getDirection() == ElementSpec.JoinNextDirection);
          h2.check(data[4].getOffset() == 0);
          h2.check(data[4].getLength() == 0);          
        }
      else if (numInserts == 3)
        {
          h2.checkPoint("ElementBuffer insertUpdate: third insertion");
          h2.check (data.length == 1);
          h2.check(data[0].getType() == ElementSpec.ContentType);
          h2.check
            (data[0].getDirection() == ElementSpec.JoinPreviousDirection);
          h2.check(data[0].getOffset() == 0);
          h2.check(data[0].getLength() == 1);          
        }
      else
        h2.fail("too many ElementSpecs created");
      super.insertUpdate(data);
    }
  }
}
