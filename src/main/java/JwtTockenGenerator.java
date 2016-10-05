import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by ozol on 30.09.2016.
 */
public class JwtTockenGenerator {

    public final static String secretPhrase = "11111secretkey";

    // https://stormpath.com/blog/token-authentication-scalable-user-mgmt
    // https://stormpath.com/blog/jwt-java-create-verify
    // https://stormpath.com/blog/token-auth-for-java
    // https://stormpath.com/blog/jjwt-how-it-works-why
    // https://stormpath.com/blog/jwt-the-right-way
    // https://auth0.com/learn/json-web-tokens/

    /*
    http://jwtbuilder.jamiekurtz.com/ - пример заполненных claims
    claims - пользовательские данные, которые должны проверяться на защищённом сервисе, чтобы авторизовать запрос
         - issuer=authorise-service.com - имя того, кто выдал клиенту токен (сервис авторизации - identity provider)
         - аudience=remote-service.com - имя защищённого сервиса, entry point
         - expireson=1435937883 - дата экспирации токена
         - subject - идентификатор пользователя
         - username=John Smith
         - userrole=Admin
     */

    /**
     * @param id
     * @param issuer    - имя издателя токена (сервис авторизации - identity provider)
     * @param subject   - идентификатор пользователя
     * @param ttlMillis
     * @return
     */
    public String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("SecretKey");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(SignatureAlgorithm.HS256, secretPhrase);


        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpZCIsImlhdCI6MTQ3NTIzNzc1Mywic3ViIjoiIiwiaXNzIjoi0JjQt9C00LDRgtC10LvRjCIsImV4cCI6MTQ3NTIzNzc2M30.9z8ZZpNlcv4fTo6jd-oNx7djpdG1ronE3h4JgovZ24o
        // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpZCIsImlhdCI6MTQ3NTIzOTk2Nywic3ViIjoiIiwiaXNzIjoi0JjQt9C00LDRgtC10LvRjCIsImV4cCI6MTQ3NTIzOTk3N30.VXtPq0YvrEP2Q3WZh-xbj5if4SRUXO4S8LQ7KhWkc0A

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }


}
