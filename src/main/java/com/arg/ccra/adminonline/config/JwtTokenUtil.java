package com.arg.ccra.adminonline.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.arg.ccra.adminonline.models.User;
import com.arg.ccra.adminonline.models.api.UserAPI;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 * @author sitthichaim
 *
 */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 4920283549102498659L;
	
	@Value("${jwt.secret}")
	private String secret;

    public Long getUserIdFormToken(HttpServletRequest request) {
    	String token = request.getHeader("Authorization").substring(7);
    	final Claims claims = getAllClaimsFromToken(token);
    	return claims.get("id", Long.class);
    }
    
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
    // public String getLangFromHeader(HttpServletRequest request) {
    // 	String lang = request.getHeader("lang");
    // 	return StringUtils.isBlank(lang) ? DEFAULT_LANG : lang;
    // }
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	public String generateToken(UserAPI user) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, user.getUserid());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 5l * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public Boolean validateToken(String token, User autUser) {
		if (null == autUser) return false;
		final Long uid = getUserIdFromToken(token);
		return (uid.equals(autUser.getUID().longValue()) && !isTokenExpired(token));
	}
	
	public void authenticate(String user, String passwordEnc, UserAPI autUser) throws Exception{
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(passwordEnc);
        System.out.println("Hash end .."+ hashedPassword);
        
        // if(!passwordEncoder.matches(passwordEnc, autUser.get))
        // {
        //     throw new Exception("Authen Fail");
        // }
    }
	
	public Long getUserIdFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return claims.get("id", Long.class);
	}

	public String getUsernameFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return claims.get("name", String.class);
	}

	

    
}
