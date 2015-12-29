package net.molez.mandlm.fotostream.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("currentImageName")
public interface CurrentImageNameService extends RemoteService 
{
	String getCurrentImageName();
}
