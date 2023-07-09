package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.model.Review;
import com.model.ReviewManager;

/**
 * Servlet implementation class AddReviewServlet
 */
@WebServlet("/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Review review = new Review();
		ReviewManager rm = new ReviewManager();
	    java.util.Date currentDate = new java.util.Date();
	    //System.out.println("data corrente util.Date: "+currentDate);
	    java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
	    //System.out.println("data corrente sql.Date: "+sqlDate);

		HttpSession session = request.getSession();
		review.setItemId(Integer.parseInt(request.getParameter("itemId")));
		review.setUserId(((int) session.getAttribute("id")));
		review.setStarRating((int) Integer.parseInt(request.getParameter("rating")));
	    review.setDate(sqlDate);
		review.setText(request.getParameter("review"));
		if(request.getParameter("modify").equals("yes"))
			rm.modifyReviewUser(review);
		else
			rm.addReview(review);
		response.sendRedirect("/NibInk/JSP/product.jsp?id="+request.getParameter("itemId"));

	}

}