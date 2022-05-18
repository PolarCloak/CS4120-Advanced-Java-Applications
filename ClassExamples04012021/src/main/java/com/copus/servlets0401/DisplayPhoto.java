package com.copus.servlets0401;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//This is example is not from the textbook.
//Displays thumbnail sized photo  (You will need to change the path to an image variable fin
//Contribution from https://stackoverflow.com/questions/2938698/jsp-how-to-scale-an-image

//I would like to revise this program to place image in a particular location on the page.
//this looks like a promising link http://geek.thekramms.com/Oreilly/books/javaenterprise/servlet/ch06_01.htm


@WebServlet(name = "DisplayPhoto")
public class DisplayPhoto extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException
    {
        response.setContentType("image/jpeg");
        ServletOutputStream out;
        out = response.getOutputStream();
        InputStream fin = new FileInputStream("d:\\Pictures\\BJC\\p36.jpg");

        // And get the thumbnail dimensions as request parameters as well.
        int thumbWidth = 100;    //Integer.parseInt(request.getParameter("w"));
        int thumbHeight = 100;   //Integer.parseInt(request.getParameter("h"));


        // Now scale the image using Java 2D API to the desired thumb size.
        BufferedImage image = ImageIO.read(fin);          //(imageInput);
        BufferedImage thumb = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumb.createGraphics();
        graphics2D.setBackground(Color.WHITE);
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0, 0, thumbWidth, thumbHeight);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

        // Write the image as JPG to the response along with correct content type.
       // response.setContentType("image/jpeg");
        ImageIO.write(thumb, "JPG", response.getOutputStream());


    }
}

