// Copyright (c) 1998  Cygnus Solutions

// Written by Tom Tromey <tromey@cygnus.com>

// KNOWN BUGS:
//   - should look for /*{ ... }*/ and treat contents as expected
//     output of test.  In this case we should redirect System.out
//     to a temp file we create.

package gnu.testlet;
import java.io.*;

public class SimpleTestHarness extends TestHarness
{
  private int count = 0;
  private int failures = 0;
  private int total = 0;
  private boolean verbose = false;
  private String description;
  private String last_check;
  private File srcdir;

  public void check (boolean result)
    {
      String d = (description
		  + ((last_check == null) ? "" : (": " + last_check))
		  + " (number " + (count + 1) + ")");
      if (! result)
	{
	  System.out.println("FAIL: " + d);
	  ++failures;
	}
      else if (verbose)
	{
	  System.out.println("PASS: " + d);
	}
      ++count;
      ++total;
    }

  public File getSourceDirectory ()
    {
      return srcdir;
    }

  public void checkPoint (String name)
    {
      last_check = name;
      count = 0;
    }

  private void runtest (String name)
    {
      // Try to ensure we start off with a reasonably clean slate.
      System.gc();
      System.runFinalization();

      checkPoint (null);

      Testlet t = null;
      try
	{
	  Class k = Class.forName (name);
	  t = (Testlet) (k.newInstance());
	}
      catch (Throwable ex)
	{
	  String d = "FAIL: uncaught exception loading " + name;
	  if (verbose)
	    d += ": " + ex.toString();
	  System.out.println (d);
	  ++failures;
	  ++total;
	}

      if (t != null)
	{
	  description = name;
	  try
	    {
	      t.test (this);
	    }
	  catch (Throwable ex)
	    {
	      String d = ("FAIL: " + description
			  + ": uncaught exception at number "
			  + (count + 1));
	      if (verbose)
		d += ": " + ex.toString();
	      System.out.println (d);
	      ++failures;
	      ++total;
	    }
	}
    }

  private int done ()
    {
      System.out.println(failures + " of " + total + " tests failed");
      return failures > 0 ? 1 : 0;
    }

  private SimpleTestHarness (String srcdir)
    {
      this.srcdir = new File (srcdir);
    }

  public static void main (String[] args)
    {
      if (args.length < 1 || args.length > 2
	  || (args.length == 2 && !args[0].equals("-verbose")))
	{
	  System.err.println ("usage: SimpleTestHarness [-verbose] SRCDIR");
	  System.exit(1);
	}

      SimpleTestHarness harness
	= new SimpleTestHarness (args[args.length - 1]);
      harness.verbose = args.length == 2;

      BufferedReader r
	= new BufferedReader (new InputStreamReader (System.in));
      while (true)
	{
	  String cname = null;
	  try
	    {
	      cname = r.readLine ();
	    }
	  catch (IOException x)
	    {
	      // Nothing.
	    }
	  if (cname == null)
	    break;
	  if (cname.endsWith ("~"))
	    {
	      // This means the file is a helper and not a test.
	      continue;
	    }
	  harness.runtest (cname);
	}

      System.exit(harness.done());
    }
}
