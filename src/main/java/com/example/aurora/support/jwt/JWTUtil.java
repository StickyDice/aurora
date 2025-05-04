package com.example.aurora.support.jwt;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.aurora.features.user.domain.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.lang.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTUtil {
  private final SecretKey jwtAccessSecret;
  private final SecretKey jwtRefreshSecret;

  public JWTUtil(
      @Value("${jwt.secret.access}") String jwtAccessSecret,
      @Value("${jwt.secret.refresh}") String jwtRefreshSecret) {
    this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
    this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
  }

  // 1 hour
  private Integer accessTokenExp = 3600;

  // 7 days
  private Integer refreshTokenExp = 3600 * 24 * 7;

  public String generateAccessToken(@NonNull User client) {
    final LocalDateTime currentTime = LocalDateTime.now();
    final Instant accessExpirationInstant = currentTime.plusSeconds(accessTokenExp).atZone(ZoneId.systemDefault())
        .toInstant();
    final Date accessExpiration = Date.from(accessExpirationInstant);

    return Jwts.builder().setSubject(client.getId().toString()).setExpiration(accessExpiration)
        .signWith(jwtAccessSecret).claim("fullname", client.getFullname()).compact();
  }

  public String generateRefreshToken(@NonNull User client) {
    final LocalDateTime now = LocalDateTime.now();
    final Instant refreshExpirationInstant = now.plusSeconds(refreshTokenExp).atZone(ZoneId.systemDefault())
        .toInstant();
    final Date refreshExpiration = Date.from(refreshExpirationInstant);
    return Jwts.builder()
        .setSubject(client.getId().toString())
        .setExpiration(refreshExpiration)
        .signWith(jwtRefreshSecret)
        .compact();
  }

  public Boolean validateAccessToken(@NonNull String token) {
    return validateToken(token, jwtAccessSecret);
  }

  public Boolean validateRefreshToken(@NonNull String token) {
    return validateToken(token, jwtRefreshSecret);
  }

  public Claims getAccessClaims(@NonNull String token) {
    return getClaims(token, jwtAccessSecret);
  }

  public Claims getRefreshClaims(@NonNull String token) {
    return getClaims(token, jwtRefreshSecret);
  }

  private Claims getClaims(@NonNull String token, @NonNull Key secret) {
    return Jwts.parserBuilder()
        .setSigningKey(secret)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Boolean validateToken(@NonNull String token, @NonNull Key secret) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(secret)
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException expEx) {
      log.error("Token expired", expEx);
    } catch (UnsupportedJwtException unsEx) {
      log.error("Unsupported jwt", unsEx);
    } catch (MalformedJwtException mjEx) {
      log.error("Malformed jwt", mjEx);
    } catch (SignatureException sEx) {
      log.error("Invalid signature", sEx);
    } catch (Exception e) {
      log.error("invalid token", e);
    }
    return false;
  }
}
