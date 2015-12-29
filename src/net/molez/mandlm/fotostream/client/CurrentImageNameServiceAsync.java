package net.molez.mandlm.fotostream.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CurrentImageNameServiceAsync 
{
	void getCurrentImageName(AsyncCallback<String> callback);
}
