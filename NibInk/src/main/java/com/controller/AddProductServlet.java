package com.controller;

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;//porco can
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.model.Item;
import com.model.ItemVariant;
import com.model.SaveImage;
import com.model.DAOItem;
import com.model.DAOVariant;


@WebServlet("/AddProductServlet")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddProductServlet() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOItem db1 = new DAOItem();
		String codenumber = String.valueOf(db1.getItemsNumber()+1);
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		String color = request.getParameter("color");
		String dimensions = request.getParameter("dimensions");
		String description = request.getParameter("description");
		float price = Float.valueOf(request.getParameter("price"));
		float vat = Float.valueOf(request.getParameter("vat"));
		int weight = Integer.parseInt(request.getParameter("weight"));
		Part file=request.getPart("imageFile");
		//sto provando a capire il problema
		String save_path="/NibInk/images/"+codenumber;
		String fileName= file.getName();
		InputStream fileContenuto = file.getInputStream();
		SaveImage.salvaImmagine(save_path,fileContenuto, fileName);
		//sto provando a capire il problema
		int qnt = 0;
		
		Item it = new Item(codenumber, title, type, color, dimensions, description, price, vat, weight);
		db1 = new DAOItem();
		db1.addItemToDB(it);
		
		if(type.equals("pen")) {
			ItemVariant itemV=new ItemVariant(it);
			
			String sizes[]=request.getParameter("optionValues").split(",");
			for(String s : sizes) {
				itemV.addVariation(s.toUpperCase(), qnt);
			}
			DAOVariant db2 = new DAOVariant();
			db2.addVariantToDB(itemV);
		}
		/*if(file != null && file.getName() != null && !file.getName().isEmpty())
		{
			String save_path="/NibInk/images/"+codenumber;
			String fileName= file.getName();
			InputStream fileContenuto = file.getInputStream();
			try {
				
			    SaveImage.salvaImmagine(save_path,fileContenuto, fileName);
			    // Utilizza l'input stream del file
			    
			} finally {
			    // Chiudi l'input stream del file
			    if (fileContenuto != null) {
			        fileContenuto.close();
			    }
			    else
			    	System.out.println("non ce nessun file");
			}
		}*/
		
		//SaveImage.salvaImmagine(save_path,fileContenuto, fileName);
		response.sendRedirect("/NibInk/JSP/itemEntry.jsp");	
	}

}

