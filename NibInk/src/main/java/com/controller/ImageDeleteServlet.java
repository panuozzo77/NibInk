package com.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteImage")
public class ImageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ImageDeleteServlet() {
        super();
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageUrl = request.getParameter("imageUrl");
        if (imageUrl != null && !imageUrl.isEmpty()) {
            deleteImage(imageUrl);
        }
    }

    private void deleteImage(String imageUrl) {
    	String file = imageUrl.substring(imageUrl.indexOf("/images"));
        String uploadDir = getServletContext().getRealPath("/") + file;
        File imageFile = new File(uploadDir);
        if (imageFile.exists()) {
            imageFile.delete();
        }
    }

}
