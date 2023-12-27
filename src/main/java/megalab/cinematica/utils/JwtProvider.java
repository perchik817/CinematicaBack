package megalab.cinematica.utils;


import io.jsonwebtoken.*;
import megalab.cinematica.exceptions.TokenValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class JwtProvider {
    public String generateToken(Long id, int expirationDate){
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MINUTE, expirationDate);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, "key")
                .claim("id", id)
                .compact();
    }

    public String generateAccessToken(Long id){
        return generateToken(id, 1);
    }

    public String generateRefreshToken(Long id){
        return generateToken(id, 3 * 5);
    }

    public Long validateToken (String token){
        try{
            Claims claims;
            claims = Jwts.parser().setSigningKey("key").parseClaimsJws(token).getBody();
            if (claims != null) {
                return Long.valueOf(String.valueOf(claims.get("id")));
            }
            else {
                throw new TokenValidationException("Token is empty");
            }
        } catch (ExpiredJwtException e) {
            throw new TokenValidationException("Token has expired. Please, log in");
        } catch (MalformedJwtException e) {
            throw new TokenValidationException("Token has been hacked");
        } catch (ResponseStatusException e) {
            throw new TokenValidationException("Token is empty");
        } catch (Exception e) {
            throw new TokenValidationException("Token hasn't passed the validation");
        }
    }
}
