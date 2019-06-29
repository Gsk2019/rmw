package unittest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtHelper {

    private final static String base64Secret = "rumaiwanglllkkkjjjwwwjjjjjjoooo=";
    private final static int expiresSecond = 172800000;

    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String createJWT(String token) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("token", token)
                /*.claim("tk", roles)
                .claim("user_privilege", privileges)*/
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
//        if (expiresSecond >= 0) {
//            long expMillis = nowMillis + expiresSecond;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp).setNotBefore(now);
//        }

        //生成JWT
        return builder.compact();
    }

    public static void main(String[] args) {
        System.out.println(JwtHelper.parseJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbiI6IjExIn0.Q3tM4LdprR0QUPLmIMWVzwWsOw4t60ZlujRLKxu9wmg"));

        System.out.println(JwtHelper.createJWT("11"));


    }
}
