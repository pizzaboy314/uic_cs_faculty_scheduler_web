package connection.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubString user = request.getParameter("user");
		String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String loginAtrributeName = "loggedIn";
        String o = (String)request.getSession().getAttribute(loginAtrributeName);
        if (o == null && StringUtils.equals("user", user) && "pass".equals(pwd)){
        	request.setAttribute(loginAtrributeName, "pass");
        	Cookie d = new Cookie("LoggedIn", "true");
        	response.addCookie(d);
            //setting cookie to expiry in 30 mins
        }
        response.sendRedirect("");
	}

}
