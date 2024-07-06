package com.v2micro.v2microtwo.security;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;

import com.v2microthree.V2MicroJwtAuthorities.JwtClaimsParser;

import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	
	private Environment environment;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public AuthorizationFilter(AuthenticationManager authenticationManager,
			Environment environment) {
		super(authenticationManager);
		this.environment = environment;
	}
	
    @Override
    protected void doFilterInternal(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {
    	
        String authorizationHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));

        if (authorizationHeader == null
                || !authorizationHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        //authentication.setDetails(authentication);
       
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean resLtr =  req.authenticate(res);
        String gg = req.getAuthType();
        
        System.out.println(gg);
        System.out.println(resLtr);
        chain.doFilter(req, res);
       
        //authentication.setDetails(authentication);
       
    }
    
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String authorizationHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));

        if (authorizationHeader == null) {
            return null;
        }

        String token = authorizationHeader.replace(environment.getProperty("authorization.token.header.prefix"), "").trim();
        String tokenSecret = environment.getProperty("token.secret");
        
        if(tokenSecret==null) return null;
        
        JwtClaimsParser jwtClaimsParser = new JwtClaimsParser(token, tokenSecret);
        String userId = jwtClaimsParser.getJwtSubject();
        
        if (userId == null) {
            return null;
        }
        UsernamePasswordAuthenticationToken ng =  new UsernamePasswordAuthenticationToken(userId, null, jwtClaimsParser.getUserAuthorities());
        return ng;

    }
    

}