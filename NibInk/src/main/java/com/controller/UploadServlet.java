package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
fileSizeThreshold = 0, 
maxFileSize = 1048576, 
maxRequestSize = 2097152
)
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadServlet() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException {
    	    // Directory where the files will be saved
    	    String uploadDir = getServletContext().getRealPath("/")+"images/"+request.getParameter("itemId")+"/";
    		//String uploadDir = "/NibInk/images/" + request.getParameter("itemId") +"/";
    	    System.out.println("upload images in: "+ uploadDir);
    	    File dir = new File(uploadDir);
    	    if (!dir.exists()) {
    	      dir.mkdirs();
    	    }

    	    Part thumbnailPart = request.getPart("thumbnail");
    	    String thumbnailFileName = "thumbnail.png";
    	    String thumbnailFilePath = uploadDir + thumbnailFileName;
    	    thumbnailPart.write(thumbnailFilePath);

    	    // Process the additional photos
    	    List<Part> photoParts = (List<Part>) request.getParts();
    	    int photoIndex = 1;
    	    for (Part photoPart : photoParts) {
    	      if (photoPart.getName().equals("photos[]")) {
    	        String photoFileName = "image" + photoIndex + ".png";
    	        String photoFilePath = uploadDir + photoFileName;
    	        photoPart.write(photoFilePath);
    	        photoIndex++;
    	      }
    	    }

    	    response.sendRedirect("/NibInk/JSP/itemEntry.jsp?id="+ request.getParameter("itemId"));
    	  }
}
