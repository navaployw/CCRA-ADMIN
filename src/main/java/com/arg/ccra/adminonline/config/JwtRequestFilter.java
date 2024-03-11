package com.arg.ccra.adminonline.config;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.arg.ccra.adminonline.dao.AdminLoginDAO;
import com.arg.ccra.adminonline.models.User;
import com.arg.ccra.adminonline.models.api.UserAPI;
import com.arg.ccra.adminonline.repositorys.UserAPIRepo;
import com.arg.ccra.adminonline.services.AdminService;
import com.arg.ccra.adminonline.services.JWTService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author sitthichaim
 *
 */
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private final JwtTokenUtil jwtTokenUtil;
	private final JWTService jwtService;
	@Autowired
    private AdminService adminService;
	// private final UserRepo userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
                
       logger.info("##################HEADER###############");
       Enumeration<String> values = request.getHeaderNames();
       while (values.hasMoreElements()) {
       String nextElement = values.nextElement();
           logger.info(MessageFormat.format("Header Name:value [{0}:{1}]", nextElement, request.getHeader(nextElement)));
       }
       logger.info("##################END##################");
       
       logger.info("METHOD :: " + request.getMethod());
       
       if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
           logger.info("TYPE OPTIONS..... do chain.....");
           chain.doFilter(request, response);
       }
       logger.info("NEXT.......");
       
		Long userId = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken = requestTokenHeader.substring(7);
			try {
				userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
				System.out.println("USER ID " + userId);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
		        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is in valid");
			} catch (ExpiredJwtException e) {
				System.out.println("********* Token is in valid *********");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is in valid");
			}
			
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		// Once we get the token validate it.
		if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	           logger.info("loadUserByUsername...."+ userId);
			    
	           User userDetails = adminService.findByUid(userId);
               
			   Boolean valid = jwtTokenUtil.validateToken(jwtToken, userDetails);  
			   logger.info("loadUserByUsername.... userDetails" + userDetails );      
			   logger.info("loadUserByUsername.... valid " + valid );      
			// if token is valid configure Spring Security to manually set
			// authentication
			if (Boolean.TRUE.equals(valid) && null != userDetails) {
				
	        	response.setHeader("Authorization", "Bearer " + jwtService.generateToken(userDetails.getUID().longValue(), userDetails.getUserName()));

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
                                       new UsernamePasswordAuthenticationToken(userDetails, null, null); //add permission 
                               
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		chain.doFilter(request, response);
	}
       
   private void prepareSession(HttpServletRequest request, UserAPI ud){
       HttpSession s = request.getSession();
//           s.setAttribute("profile", ud.getOrgPermissions());
   }

}