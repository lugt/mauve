/* getChildBeanContextChild.java -- some checks for the 
       getChildBeanContextChild() method in the BeanContextSupport class.
   Copyright (C) 2006 David Gilbert <david.gilbert@object-refinery.com>
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
// Uses: MyBeanContextSupport

package gnu.testlet.java.beans.beancontext.BeanContextSupport;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.beans.beancontext.BeanContextChild;
import java.beans.beancontext.BeanContextProxy;
import java.beans.beancontext.BeanContextSupport;

public class getChildBeanContextChild implements Testlet
{
  static class MyBeanContextProxy implements BeanContextProxy
  {
    BeanContextChild bcs;
    public MyBeanContextProxy(BeanContextChild bcs)
    {
      this.bcs = bcs; 
    }
    public BeanContextChild getBeanContextProxy() 
    {
      return bcs;
    }
  }
  static class BadProxy extends BeanContextSupport implements BeanContextProxy
  {
    BeanContextChild bcs;
    public BadProxy(BeanContextChild bcs)
    {
      this.bcs = bcs; 
    }

    public BeanContextChild getBeanContextProxy() 
    {
      return bcs;
    }
  }
  
  public void test(TestHarness harness)
  {
    // try a regular BeanContextChild
    MyBeanContextSupport bcs1 = new MyBeanContextSupport();
    harness.check(MyBeanContextSupport.getChildBeanContextChildX(bcs1), bcs1);
    
    // try a proxy
    MyBeanContextSupport bcs2 = new MyBeanContextSupport();
    MyBeanContextProxy proxy = new MyBeanContextProxy(bcs2);
    harness.check(MyBeanContextSupport.getChildBeanContextChildX(proxy), bcs2);
    
    // try bad proxy
    BadProxy bp = new BadProxy(bcs2);
    boolean pass = false;
    try
    {
      MyBeanContextSupport.getChildBeanContextChildX(bp);
    }
    catch (IllegalArgumentException e)
    {
      pass = true;
    }
    harness.check(pass);
    
    // try null
    harness.check(MyBeanContextSupport.getChildBeanContextChildX(null), null);
  }
}
