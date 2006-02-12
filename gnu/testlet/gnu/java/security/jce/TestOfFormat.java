/* TestOfFormat.java
   Copyright (C) 2006 Free Software Foundation, Inc.
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

// Tags: GNU-CRYPTO JDK1.4

package gnu.testlet.gnu.java.security.jce;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import gnu.java.security.Registry;
import gnu.java.security.provider.Gnu;
import gnu.javax.crypto.jce.GnuCrypto;
import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * Conformance tests for the encoding / decoding of X.509 and PKCS#8 formats
 * for the DSS and RSA key-pairs generated by the GNU Classpath implementation
 * of the key-pair generators and the "Encoded" key-factory.
 */
public class TestOfFormat
    implements Testlet
{
  private KeyPairGenerator dssKPG;

  private KeyPairGenerator rsaKPG;

  private KeyPairGenerator dhKPG;

  private KeyFactory encodedKF;

  public void test(TestHarness harness)
  {
    setUp(harness);

    testDSSSymmetry(harness);
    testRSASymmetry(harness);
    testDHSymmetry(harness);
  }

  private void setUp(TestHarness harness)
  {
    Security.addProvider(new Gnu());
    Security.addProvider(new GnuCrypto());
    Security.addProvider(new FakeProvider());
    try
      {
        dssKPG = KeyPairGenerator.getInstance(Registry.DSS_KPG,
                                              Registry.GNU_SECURITY);
        rsaKPG = KeyPairGenerator.getInstance(Registry.RSA_KPG,
                                              Registry.GNU_SECURITY);
        dhKPG = KeyPairGenerator.getInstance(Registry.DH_KPG,
                                             Registry.GNU_CRYPTO);
        encodedKF = KeyFactory.getInstance("Encoded", "FakeProvider");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("setUp(): " + x.getMessage());
      }
  }

  private void testDSSSymmetry(TestHarness harness)
  {
    harness.checkPoint("testDSSSymmetry");

    try
      {
        dssKPG.initialize(512);
        KeyPair kp = dssKPG.generateKeyPair();
        harness.check(kp != null, "MUST generate valid DSS keypair");

        PublicKey p1 = kp.getPublic();

        String f1 = p1.getFormat();
        harness.check("X.509".equalsIgnoreCase(f1),
                      "DSS public key format MUST be X.509");
        byte[] encoded1 = p1.getEncoded();

        X509EncodedKeySpec spec1 = new X509EncodedKeySpec(encoded1);
        PublicKey p2 = encodedKF.generatePublic(spec1);
        harness.check(p1.equals(p2), "Two DSS public keys MUST be equal");

        PrivateKey p3 = kp.getPrivate();

        String f2 = p3.getFormat();
        harness.check("PKCS#8".equalsIgnoreCase(f2),
                      "DSS private key format MUST be PKCS#8");
        byte[] encoded2 = p3.getEncoded();

        PKCS8EncodedKeySpec spec2 = new PKCS8EncodedKeySpec(encoded2);
        PrivateKey p4 = encodedKF.generatePrivate(spec2);
        harness.check(p3.equals(p4), "Two DSS private keys MUST be equal");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("testDSSSymmetry(): " + x.getMessage());
      }
  }

  private void testRSASymmetry(TestHarness harness)
  {
    harness.checkPoint("testRSASymmetry");

    try
      {
        rsaKPG.initialize(1024);
        KeyPair kp = rsaKPG.generateKeyPair();
        harness.check(kp != null, "MUST generate valid RSA keypair");

        PublicKey p1 = kp.getPublic();

        String f1 = p1.getFormat();
        harness.check("X.509".equalsIgnoreCase(f1),
                      "RSA public key format MUST be X.509");
        byte[] encoded1 = p1.getEncoded();

        X509EncodedKeySpec spec1 = new X509EncodedKeySpec(encoded1);
        PublicKey p2 = encodedKF.generatePublic(spec1);
        harness.check(p1.equals(p2), "Two RSA public keys MUST be equal");

        PrivateKey p3 = kp.getPrivate();

        String f2 = p3.getFormat();
        harness.check("PKCS#8".equalsIgnoreCase(f2),
                      "RSA private key format MUST be PKCS#8");
        byte[] encoded2 = p3.getEncoded();

        PKCS8EncodedKeySpec spec2 = new PKCS8EncodedKeySpec(encoded2);
        PrivateKey p4 = encodedKF.generatePrivate(spec2);
        harness.check(p3.equals(p4), "Two RSA private keys MUST be equal");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("testRSASymmetry(): " + x.getMessage());
      }
  }

  private void testDHSymmetry(TestHarness harness)
  {
    harness.checkPoint("testDHSymmetry");

    try
      {
        dhKPG.initialize(512);
        KeyPair kp = dhKPG.generateKeyPair();
        harness.check(kp != null, "MUST generate valid DH keypair");

        PublicKey p1 = kp.getPublic();

        String f1 = p1.getFormat();
        harness.check("X.509".equalsIgnoreCase(f1),
                      "DH public key format MUST be X.509");
        byte[] encoded1 = p1.getEncoded();

        X509EncodedKeySpec spec1 = new X509EncodedKeySpec(encoded1);
        PublicKey p2 = encodedKF.generatePublic(spec1);
        harness.check(p1.equals(p2), "Two DH public keys MUST be equal");

        PrivateKey p3 = kp.getPrivate();

        String f2 = p3.getFormat();
        harness.check("PKCS#8".equalsIgnoreCase(f2),
                      "DH private key format MUST be PKCS#8");
        byte[] encoded2 = p3.getEncoded();

        PKCS8EncodedKeySpec spec2 = new PKCS8EncodedKeySpec(encoded2);
        PrivateKey p4 = encodedKF.generatePrivate(spec2);
        harness.check(p3.equals(p4), "Two DH private keys MUST be equal");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("testDHSymmetry(): " + x.getMessage());
      }
  }
}
