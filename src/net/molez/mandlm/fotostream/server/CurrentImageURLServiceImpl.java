package net.molez.mandlm.fotostream.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import net.molez.mandlm.fotostream.client.CurrentImageURLService;

@SuppressWarnings("serial")
public class CurrentImageURLServiceImpl extends RemoteServiceServlet implements CurrentImageURLService 
{
	public String getCurrentImageURL() 
	{
		List<String> fileList = new ArrayList<String>();
		
		fileList.add("img/dummy_1.jpg");
		fileList.add("img/dummy_2.jpg");
		
		return fileList.get(new Random().nextInt(fileList.size()));
	}
}
