// Copyright (c) 1998  Cygnus Solutions

// Written by Anthony Green <green@cygnus.com>

package gnu.testlet;

public interface Testlet
{
  // This runs the test.
  public abstract void test (TestHarness harness);

  // This must return a description of the test.
  public abstract String description ();
}
