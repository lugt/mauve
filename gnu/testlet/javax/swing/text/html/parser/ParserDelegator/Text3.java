/* Text3.java -- Whitespace handling tests
   Copyright (C) 2007 Copyright (C) 2005 Audrius Meskauskas
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

package gnu.testlet.javax.swing.text.html.parser.ParserDelegator;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * Checks if the whitespace is correctly consumed aroung the B (bold)
 * tag. 
 */
public class Text3 extends Text implements Testlet
{
  public void test(TestHarness a_harness)
  {
    harness = a_harness;
    hideImplied = true;

    // No whitespace.
    verify("abc<b>def</b>ghi",
     "<html><head></head><body>61'a 62'b 63'c <b>64'd 65'e 66'f </b>67'g 68'h 69'i </body></html>","no ws"
          );
    
    // Onse space before b must be preserved
    verify("abc <b>def</b>ghi",
      "<html><head></head><body>61'a 62'b 63'c 20 <b>64'd 65'e 66'f </b>67'g 68'h 69'i </body></html>","ws before b"
          );
    
    // Two spaces must mutate into 1
    verify("abc  <b>def</b>ghi",
      "<html><head></head><body>61'a 62'b 63'c 20 <b>64'd 65'e 66'f </b>67'g 68'h 69'i </body></html>","ws before b 2"
          );
    

    verify("abc<b> def</b>ghi",
      "<html><head></head><body>61'a 62'b 63'c <b>20 64'd 65'e 66'f </b>67'g 68'h 69'i </body></html>","ws after b"
          );
    
    verify("abc<b>  def</b>ghi",
      "<html><head></head><body>61'a 62'b 63'c <b>20 64'd 65'e 66'f </b>67'g 68'h 69'i </body></html>","ws after b 2"
          );
    
    
    verify("abc<b>def </b>ghi","<html><head></head><body>61'a 62'b 63'c <b>64'd 65'e 66'f 20 </b>67'g 68'h 69'i </body></html>","ws before end of b"
          );
    
    verify("abc<b>def  </b>ghi","<html><head></head><body>61'a 62'b 63'c <b>64'd 65'e 66'f 20 </b>67'g 68'h 69'i </body></html>","ws before end of b 2"
          );
    
    verify("abc<b>def</b> ghi",
           "<html><head></head><body>61'a 62'b 63'c <b>64'd 65'e 66'f </b>20 67'g 68'h 69'i </body></html>","ws after end of b"
          );
    
    verify("abc<b>def</b> ghi",
           "<html><head></head><body>61'a 62'b 63'c <b>64'd 65'e 66'f </b>20 67'g 68'h 69'i </body></html>","ws after end of b 2"
          );
    
    // Two spaces, one before and one after, must mutate into the single one.
    verify("abc<b>def </b> ghi",
           "<html><head></head><body>61'a 62'b 63'c <b>64'd 65'e 66'f 20 </b>67'g 68'h 69'i </body></html>",
           "whitespace clash 1"
          );
    
    verify("abc <b> def</b>ghi",
           "<html><head></head><body>61'a 62'b 63'c 20 <b>64'd 65'e 66'f </b>67'g 68'h 69'i </body></html>",
           "whitespace clash 1"
          );
    
    
    
    

  }

}
