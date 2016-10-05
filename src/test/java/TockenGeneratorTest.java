import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by ozol on 30.09.2016.
 */
public class TockenGeneratorTest {

    public static void main(String[] args) {
        JwtTockenGenerator generator = new JwtTockenGenerator();

        String jwtToken = generator.createJWT("id", "Провайдер токена", "", 10000L);
        System.out.println("JWT tocken: " + jwtToken);

        //Sample method to validate and read the JWT
//This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(JwtTockenGenerator.secretPhrase))
                .parseClaimsJws(jwtToken).getBody();

        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }


}

