package attendance.system.central.named;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public class NamedProperties {

    public final static String Hikari = "spring.datasource.hikari";

    public final static String JwtSecret = "${spring.jwt.secret}";
    public final static String JwtExpirationTime = "${spring.jwt.expirationTimeMs}";
}
