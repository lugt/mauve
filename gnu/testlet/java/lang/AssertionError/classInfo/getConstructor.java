// Test for method java.lang.AssertionError.getClass().getConstructor()

// Copyright (C) 2012, 2013 Pavel Tisnovsky <ptisnovs@redhat.com>

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
// the Free Software Foundation, Inc., 51 Franklin Street,
// Fifth Floor, Boston, MA 02110-1301 USA.

// Tags: JDK1.5

package gnu.testlet.java.lang.AssertionError.classInfo;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.AssertionError;
import java.util.Map;
import java.util.HashMap;



/**
 * Test for method java.lang.AssertionError.getClass().getConstructor()
 */
public class getConstructor implements Testlet
{

    /**
     * Runs the test using the specified harness.
     *
     * @param harness  the test harness (<code>null</code> not permitted).
     */
    public void test(TestHarness harness)
    {
        // following constructors should exist
        Map<String, Class[]> constructorsThatShouldExist_jdk6 = new HashMap<String, Class[]>();
        constructorsThatShouldExist_jdk6.put("java.lang.AssertionError", new Class[] {});
        constructorsThatShouldExist_jdk6.put("java.lang.AssertionError", new Class[] {java.lang.Object.class});
        constructorsThatShouldExist_jdk6.put("java.lang.AssertionError", new Class[] {boolean.class});
        constructorsThatShouldExist_jdk6.put("java.lang.AssertionError", new Class[] {char.class});
        constructorsThatShouldExist_jdk6.put("java.lang.AssertionError", new Class[] {int.class});
        constructorsThatShouldExist_jdk6.put("java.lang.AssertionError", new Class[] {long.class});
        constructorsThatShouldExist_jdk6.put("java.lang.AssertionError", new Class[] {float.class});
        constructorsThatShouldExist_jdk6.put("java.lang.AssertionError", new Class[] {double.class});

        Map<String, Class[]> constructorsThatShouldExist_jdk7 = new HashMap<String, Class[]>();
        constructorsThatShouldExist_jdk7.put("java.lang.AssertionError", new Class[] {int.class});
        constructorsThatShouldExist_jdk7.put("java.lang.AssertionError", new Class[] {long.class});
        constructorsThatShouldExist_jdk7.put("java.lang.AssertionError", new Class[] {float.class});
        constructorsThatShouldExist_jdk7.put("java.lang.AssertionError", new Class[] {double.class});
        constructorsThatShouldExist_jdk7.put("java.lang.AssertionError", new Class[] {java.lang.String.class, java.lang.Throwable.class});
        constructorsThatShouldExist_jdk7.put("java.lang.AssertionError", new Class[] {});
        constructorsThatShouldExist_jdk7.put("java.lang.AssertionError", new Class[] {java.lang.Object.class});
        constructorsThatShouldExist_jdk7.put("java.lang.AssertionError", new Class[] {boolean.class});
        constructorsThatShouldExist_jdk7.put("java.lang.AssertionError", new Class[] {char.class});

        // get the right map containing constructor signatures
        Map<String, Class[]> constructorsThatShouldExist = getJavaVersion() < 7 ? constructorsThatShouldExist_jdk6 : constructorsThatShouldExist_jdk7;

        // create instance of a class AssertionError
        final Object o = new AssertionError();

        // get a runtime class of an object "o"
        final Class c = o.getClass();

        // check if all required constructors really exist
        for (Map.Entry<String, Class[]> constructorThatShouldExists : constructorsThatShouldExist.entrySet()) {
            try {
                java.lang.reflect.Constructor constructor = c.getConstructor(constructorThatShouldExists.getValue());
                harness.check(constructor != null);
                String constructorName = constructor.getName();
                harness.check(constructorName != null);
                harness.check(constructorName, constructorThatShouldExists.getKey());
            }
            catch (Exception e) {
                harness.check(false);
            }
        }
    }

    /**
     * Returns version of Java. The input could have the following form: "1.7.0_06"
     * and we are interested only in "7" in this case.
     * 
     * @return Java version
     */
    protected int getJavaVersion() {
        String javaVersionStr = System.getProperty("java.version");
        String[] parts = javaVersionStr.split("\\.");
        return Integer.parseInt(parts[1]);
    }
}

