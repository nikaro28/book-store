package com.getir.bookstore.config.security;

import com.getir.bookstore.module.user.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

  private final Logger logger = LoggerFactory.getLogger(JWTUtil.class);
  private final String jwtSecret;
  private final Long ttl;

  public JWTUtil(
      @Value("${security.jwtoken.secret-key}") String jwtSecret,
      @Value("${security.jwtoken.ttl}") Long ttl) {
    this.jwtSecret = jwtSecret;
    this.ttl = ttl;
  }

  public String generateAccessToken(String userName, List<Role> roles) {
    Claims claims = Jwts.claims().setSubject(userName);
    claims.put(
        "roles",
        roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
            .filter(Objects::nonNull)
            .collect(Collectors.toList()));

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + ttl)) // 30 mins
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public Date getExpirationDate(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration();
  }

  public boolean validate(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (Exception ex) {
      logger.error("Invalid JWT token - {}", ex.getMessage());
    }

    return false;
  }

  public List<GrantedAuthority> getRoles(String token) {
    List<Map<String, String>> roleClaims =
        Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody()
            .get("roles", List.class);
    return roleClaims.stream()
        .map(roleClaim -> new SimpleGrantedAuthority(roleClaim.get("authority")))
        .collect(Collectors.toList());
  }
}
