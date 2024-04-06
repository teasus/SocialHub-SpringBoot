package com.resser.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtTokenValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
		
		//bearer jwt
		
		if(jwt != null) {
			jwt =jwt.substring(7);
			
			try {
				SecretKey key =Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
				System.out.println("jwt key by me = "+ JwtConstant.SECRET_KEY);
				System.out.println("KKEYS "+ key);
				System.out.println("JwttHeader = "+ jwt);
				//Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
				Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
				//cLAIMS ARE IMMUTABLE JSON						
				String email = String.valueOf(claims.get("email"));
				String  authorities = String.valueOf(claims.get("authorities"));
				
				List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,auths);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			
			}catch (Exception ex) {
				throw new BadCredentialsException("Invalid TOkenn");
			}
			
			
		}
		filterChain.doFilter(request, response);
		
	}

	
	

}
