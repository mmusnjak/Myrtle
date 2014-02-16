package hr.mmusnjak.myrtle.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Servlet implementation class Counter
 */
@WebServlet(description = "Counts the number of visits to page", urlPatterns = { "/Counter" })
public class Counter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int count;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Counter() {
		super();
		// TODO Auto-generated constructor stub - will not be fixed
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Set a cookie for the user, so that the counter does not increate
		// every time the user press refresh
		Context ctx;
		String myVar = "";
		try {
			ctx = (Context) (new InitialContext().lookup("java:comp/env"));
			myVar = (String) ctx.lookup("config");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();
		InputStream input = null;

		if (myVar != "") {
			try {
				input = new FileInputStream(myVar);
				prop.load(input);
				System.out.println(prop.getProperty("marko"));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		HttpSession session = request.getSession(true);
		// Set the session valid for 5 secs
		session.setMaxInactiveInterval(5);
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		if (session.isNew()) {
			count++;
		}
		out.println("This site has been accessed " + count + " times. ");
		out.println("Configuration location: " + myVar);
	}

}
