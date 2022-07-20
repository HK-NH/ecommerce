package hk.ecommerce.util;

public class JwtConstants {

    public static final String SECRET = "HamzaKecha";

    public static final String AUTH_HEADER = "Authorization";

    public static final String PREFIX = "Bearer ";

    public static final String CLAIMROLES= "roles";

    public static final int EXPIRATIONACCESSTIME = 36000*6;

    public static final int EXPIRATIONREFRESHTIME = 24 * 3600 * 1000;
}
