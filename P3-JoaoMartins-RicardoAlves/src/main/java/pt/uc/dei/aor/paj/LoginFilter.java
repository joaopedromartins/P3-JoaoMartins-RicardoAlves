package pt.uc.dei.aor.paj;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/resources/secure/*")
public class LoginFilter implements Filter {


	/**
	 * Checks if user is logged in. If not it redirects to the login.xhtml page.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Get the loginBean from session attribute
		HttpServletRequest req = (HttpServletRequest) request;

		//System.out.println(req.getSession().getAttribute("loggedin"));

		if (req.getSession().getAttribute("loggedin") != null){

			boolean loginBean = (boolean) req.getSession().getAttribute("loggedin");
			if (loginBean)
				chain.doFilter(request, response);
			else{
				req.getRequestDispatcher("/resources/paginas/login.xhtml").forward(request, response);
			}

		}

		if (req.getSession().getAttribute("loggedin") == null)
			req.getRequestDispatcher("/resources/paginas/login.xhtml").forward(request, response);


		// For the first application request there is no loginBean in the session so user needs to log in
		// For other requests loginBean is present but we need to check if user has logged in successfully
		//        if (loginBean == null || loginBean.getUserLogged() == null) {
		//            String contextPath = ((HttpServletRequest)request).getContextPath();
		//            ((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
		//        }
		//         
		//        chain.doFilter(request, response);

	}

	public void init(FilterConfig config) throws ServletException {
		//System.out.println("filter is working");
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}  

}

