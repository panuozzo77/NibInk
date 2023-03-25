
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowRequestHeaders
 */
@WebServlet("/ShowRequestHeaders")
public class ShowRequestHeaders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowRequestHeaders() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		/*	
			"Chrome", "Chrome"
			"Apple", "Safari",
			"Firefox", "Firefox"
			"Netscape", "Netscape"
			"MSIE", "Explorer",
			"Gecko", "Mozilla",
		 */		
		String userAgent = request.getHeader("User-Agent");
		if(userAgent != null && userAgent.indexOf("MSIE") != -1) {
			out.print("Microsoft Explorer");
		} else {
			out.print("Netscape");
		}
		
		String title = "Servlet Example: Showing Request Headers";
		out.println("<HTML>" + 
				"<BODY BGCOLOR=\"#FDF5E6\">" + 
				"<H1 ALIGN=CENTER>" + title + "</H1>"
				+ "<B>Request Method: </B>" + request.getMethod() + "<BR>" 
				+ "<B>Request URI: </B>" + request.getRequestURI() + "<BR>" 
				+ "<B>Request Protocol: </B>" + request.getProtocol() + "<BR><BR>"  
				+ "<TABLE BORDER=1 ALIGN=CENTER>" 
				+ "<TR BGCOLOR=\"#FFAD00\">"
				+ "<TH>Header Name</TH><TH>Header Value</TH>");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			out.println("<TR><TD>" + headerName + "</TD><TD>" + request.getHeader(headerName) + "</TD>");
		}
		out.println("</TABLE></BODY></HTML>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
