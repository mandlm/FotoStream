package net.molez.mandlm.fotostream.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("currentImageURL")
public interface CurrentImageURLService extends RemoteService 
{
	String getCurrentImageURL();
}
