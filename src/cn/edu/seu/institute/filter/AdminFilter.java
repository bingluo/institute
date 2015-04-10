package cn.edu.seu.institute.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

@Component
public class AdminFilter implements Filter {

	// private static AdminService adminService = (AdminService)
	// SystemContainer.lookup("adminService");

	// private AdminService adminService;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		/*
		 * AnnotationConfigWebApplicationContext ctx = new
		 * AnnotationConfigWebApplicationContext();
		 * 
		 * HttpServletRequest httpRequest = (HttpServletRequest) request;
		 * HttpSession session = httpRequest.getSession();
		 * 
		 * SecurityContext securityContext = SecurityContextHolder
		 * .getSecurityContext();
		 * 
		 * Admin admin = (Admin) session.getAttribute("admin"); if (admin !=
		 * null) { securityContext.setAdmin(admin); }
		 */

		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
