// Test for method java.lang.Package.getClass().getDeclaredFields()

// Copyright (C) 2012, 2013, 2014, 2015, 2016 Pavel Tisnovsky <ptisnovs@redhat.com>

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

package gnu.testlet.java.lang.Package.classInfo;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.lang.Package;
import java.util.Map;
import java.util.HashMap;



/**
 * Test for method java.lang.Package.getClass().getDeclaredFields()
 */
public class getDeclaredFields implements Testlet
{

    /**
     * Runs the test using the specified harness.
     *
     * @param harness  the test harness (<code>null</code> not permitted).
     */
    public void test(TestHarness harness)
    {
        // map of declared fields which should exists
        Map<String, String> testedDeclaredFields = null;

        // map of declared fields for (Open)JDK6
        Map<String, String> testedDeclaredFields_jdk6 = new HashMap<String, String>();

        // map of declared fields for (Open)JDK7
        Map<String, String> testedDeclaredFields_jdk7 = new HashMap<String, String>();

        // map for fields declared in (Open)JDK6
        testedDeclaredFields_jdk6.put("private static java.util.Map java.lang.Package.pkgs", "pkgs");
        testedDeclaredFields_jdk6.put("private static java.util.Map java.lang.Package.urls", "urls");
        testedDeclaredFields_jdk6.put("private static java.util.Map java.lang.Package.mans", "mans");
        testedDeclaredFields_jdk6.put("private final java.lang.String java.lang.Package.pkgName", "pkgName");
        testedDeclaredFields_jdk6.put("private final java.lang.String java.lang.Package.specTitle", "specTitle");
        testedDeclaredFields_jdk6.put("private final java.lang.String java.lang.Package.specVersion", "specVersion");
        testedDeclaredFields_jdk6.put("private final java.lang.String java.lang.Package.specVendor", "specVendor");
        testedDeclaredFields_jdk6.put("private final java.lang.String java.lang.Package.implTitle", "implTitle");
        testedDeclaredFields_jdk6.put("private final java.lang.String java.lang.Package.implVersion", "implVersion");
        testedDeclaredFields_jdk6.put("private final java.lang.String java.lang.Package.implVendor", "implVendor");
        testedDeclaredFields_jdk6.put("private final java.net.URL java.lang.Package.sealBase", "sealBase");
        testedDeclaredFields_jdk6.put("private final transient java.lang.ClassLoader java.lang.Package.loader", "loader");
        testedDeclaredFields_jdk6.put("private transient java.lang.Class java.lang.Package.packageInfo", "packageInfo");

        // map for fields declared in (Open)JDK7
        testedDeclaredFields_jdk7.put("private static java.util.Map java.lang.Package.pkgs", "pkgs");
        testedDeclaredFields_jdk7.put("private static java.util.Map java.lang.Package.urls", "urls");
        testedDeclaredFields_jdk7.put("private static java.util.Map java.lang.Package.mans", "mans");
        testedDeclaredFields_jdk7.put("private final java.lang.String java.lang.Package.pkgName", "pkgName");
        testedDeclaredFields_jdk7.put("private final java.lang.String java.lang.Package.specTitle", "specTitle");
        testedDeclaredFields_jdk7.put("private final java.lang.String java.lang.Package.specVersion", "specVersion");
        testedDeclaredFields_jdk7.put("private final java.lang.String java.lang.Package.specVendor", "specVendor");
        testedDeclaredFields_jdk7.put("private final java.lang.String java.lang.Package.implTitle", "implTitle");
        testedDeclaredFields_jdk7.put("private final java.lang.String java.lang.Package.implVersion", "implVersion");
        testedDeclaredFields_jdk7.put("private final java.lang.String java.lang.Package.implVendor", "implVendor");
        testedDeclaredFields_jdk7.put("private final java.net.URL java.lang.Package.sealBase", "sealBase");
        testedDeclaredFields_jdk7.put("private final transient java.lang.ClassLoader java.lang.Package.loader", "loader");
        testedDeclaredFields_jdk7.put("private transient java.lang.Class java.lang.Package.packageInfo", "packageInfo");

        // create instance of a class Package
        final Object o = Package.getPackage("java.lang");

        // get a runtime class of an object "o"
        final Class c = o.getClass();

        // get the right map containing declared field signatures
        testedDeclaredFields = getJavaVersion() < 7 ? testedDeclaredFields_jdk6 : testedDeclaredFields_jdk7;

        // get all declared fields for this class
        java.lang.reflect.Field[] declaredFields = c.getDeclaredFields();

        // expected number of declared fields
        final int expectedNumberOfDeclaredFields = testedDeclaredFields.size();

        // basic check for a number of declared fields
        harness.check(declaredFields.length, expectedNumberOfDeclaredFields);

        // check if all fields exist
        for (java.lang.reflect.Field declaredField: declaredFields) {
            String fieldName = declaredField.getName();
            String fieldString = declaredField.toString();
            harness.check(testedDeclaredFields.containsKey(fieldString));
            harness.check(testedDeclaredFields.get(fieldString), fieldName);
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

