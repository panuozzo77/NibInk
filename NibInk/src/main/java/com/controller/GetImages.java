package com.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetImages")
public class GetImages extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imagesFolderPath = getServletContext().getRealPath("/")+"images/"+request.getParameter("id")+"/";
        System.out.println("Getting images from: " + imagesFolderPath);
        List<String> imageUrls = new ArrayList<>();
        
        File folder = new File(imagesFolderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] imageFiles = folder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png");
                }
            });

            for (File imageFile : imageFiles) {
                Path imagePath = imageFile.toPath();
                String imageUrl = imagePath.toString();
                imageUrls.add(imageUrl);
            }
        }
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{ \"images\": " + imageUrls.toString() + " }");
        out.flush();
    }
}