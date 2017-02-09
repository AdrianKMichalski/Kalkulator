package pp5.kalkulator.logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Random;

/**
 * @author Adrian Michalski
 */
public abstract class MD5HashGenerator {
    protected String generateHash() {
        try {
            long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
            long randomLong = new Random().nextLong();

            String toHash = "CALCULATOR" + timestamp + randomLong;

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(toHash.getBytes());
            BigInteger bigInt = new BigInteger(1, digest);
            return bigInt.toString(16).substring(0, 12);
        } catch (NoSuchAlgorithmException pE) {
            throw new RuntimeException(pE);
        }
    }
}
