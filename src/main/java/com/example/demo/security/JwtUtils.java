package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {
	private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);
	
	public boolean validateJwtToken(String authToken) {
		try {
		Jwts.parser().setSigningKey("semillaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1"
				+ "").parseClaimsJws(authToken);
		return true;
		
		}catch (Exception e) {
			LOG.error("errrror", e);
		}
		
		return false;
	}
	
	public String getUserNameFromJetToken(String token) {
		return Jwts.parser().setSigningKey("semillaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1").parseClaimsJws(token).getBody().getSubject();

	}

}
