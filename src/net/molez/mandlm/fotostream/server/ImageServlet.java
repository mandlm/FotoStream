package net.molez.mandlm.fotostream.server;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ImageServlet extends HttpServlet 
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String imageName = request.getParameter("name");
		File f = new File("img/" + imageName);
		
		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		
		ImageIO.write(ImageIO.read(f), "jpg", out);
		out.close();
	}
}
