package com.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/getImages")
public class GetImages extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext context = request.getServletContext();
        String imagesFolderPath = context.getRealPath("/") + "images/" + request.getParameter("id") + "/";

        File folder = new File(imagesFolderPath);
        List<String> imageUrls = new ArrayList<>();

        if (folder.exists() && folder.isDirectory()) {
            File[] imageFiles = folder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png");
                }
            });

            for (File imageFile : imageFiles) {
                String imageName = imageFile.getName();
                String imagePath = "/NibInk/images/"+request.getParameter("id") + "/" + imageName;
                imageUrls.add(imagePath);
            }
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{ \"images\": " + new Gson().toJson(imageUrls) + " }");
        out.flush();
    }

}