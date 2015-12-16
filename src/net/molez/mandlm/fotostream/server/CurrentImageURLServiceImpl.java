package net.molez.mandlm.fotostream.server;

import java.io.File;
import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import net.molez.mandlm.fotostream.client.CurrentImageURLService;

@SuppressWarnings("serial")
public class CurrentImageURLServiceImpl extends RemoteServiceServlet implements CurrentImageURLService 
{
	public String getCurrentImageURL() 
	{
		File imageFolder = new File("img/");
		
		File[] fileList = imageFolder.listFiles();
		
		return fileList[new Random().nextInt(fileList.length)].getPath();
	}
}
