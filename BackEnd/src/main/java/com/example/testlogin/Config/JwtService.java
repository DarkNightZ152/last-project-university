package com.example.testlogin.Config;

import com.example.testlogin.Model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private static final String SECRET_KEY =
    "456367252f496434564f362666432c797c3d3028216477562725464852";

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public String extractRoles(String token) {
    return (String) extractAllClaims(token).get("Authorities");
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateUserToken(Users user) {
    return generateUserToken(new HashMap<>(), user);
  }

  public String generateUserToken(
    Map<String, Object> extractClaims,
    Users user
  ) {
    return Jwts //Had changed here!
      .builder()
      .claims(extractClaims)
      .subject(user.getUsername())
      .claim("Password", user.getPassword())
      .claim("UserID", user.getID())
      .claim("Authorities", user.getRole()) //Thêm dữ liệu vào payload của token
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
      .signWith(getSignInKey(), Jwts.SIG.HS256)
      .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    return (isUserValid(token, userDetails) && !isTokenExpired(token));
  }

  public boolean isUserValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername());
  }

  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parser()
      .verifyWith(getSignInKey()) //was change from setSigningKey() to verifyWith() because of deprecated
      .build()
      .parseSignedClaims(token)
      .getPayload(); //was change from parseClaimsJws(token) to parseSignedClaims(token) because of deprecated
  }

  private SecretKey getSignInKey() { //was change from Key to SecretKey
    byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyByte);
  }
}
