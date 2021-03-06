// Test of the method BigDecimal.longValueExact()

// Copyright 2012 Red Hat, Inc.
// Written by Pavel Tisnovsky <ptisnovs@redhat.com>

// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published 
// by the Free Software Foundation, either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software Foundation
// Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307 USA

package gnu.testlet.java.math.BigDecimal;

import java.math.BigDecimal;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

/**
  * Check for method BigDecimal.longValueExact()
  */
public class longValueExact implements Testlet
{
  /**
   * Entry point to this test.
   */
  public void test(TestHarness harness)
  {
    // simple conversions
    harness.check((0)           == (new BigDecimal("0")).longValueExact());
    harness.check((1)           == (new BigDecimal("1")).longValueExact());
    harness.check((99)          == (new BigDecimal("99")).longValueExact());
    harness.check((100)         == (new BigDecimal("100")).longValueExact());
    harness.check((127)         == (new BigDecimal("127")).longValueExact());
    harness.check((-127)        == (new BigDecimal("-127")).longValueExact());
    harness.check((128)         == (new BigDecimal("128")).longValueExact());
    harness.check((-128)        == (new BigDecimal("-128")).longValueExact());
    harness.check((32767)       == (new BigDecimal("32767")).longValueExact());
    harness.check((-32768)      == (new BigDecimal("-32768")).longValueExact());
    harness.check((2147483647)  == ((new BigDecimal("2147483647")).longValueExact()));
    harness.check((-2147483647) == ((new BigDecimal("-2147483647")).longValueExact()));

    // overflow/underflow
    testException(harness, "18446744073709551615");
    testException(harness, "18446744073709551616");

    // truncation
    testException(harness, "100.1");
    testException(harness, "100.9");
  }

  /**
    * Check if exception is thrown during exact conversion to a long.
    */
  private void testException(TestHarness harness, String numberStr)
  {
    try {
        new BigDecimal(numberStr).longValueExact();
        harness.fail("ArithmeticException not thrown as expected for the number: " + numberStr + "!");
    }
    catch (ArithmeticException e) {
        // ok
    }
  }
}

