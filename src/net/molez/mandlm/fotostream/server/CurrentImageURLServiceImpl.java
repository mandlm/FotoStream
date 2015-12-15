package net.molez.mandlm.fotostream.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import net.molez.mandlm.fotostream.client.CurrentImageURLService;

@SuppressWarnings("serial")
public class CurrentImageURLServiceImpl extends RemoteServiceServlet implements CurrentImageURLService 
{
	public String getCurrentImageURL() 
	{
		return "img/dummy.jpg";
	}
}
