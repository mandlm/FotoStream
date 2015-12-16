package net.molez.mandlm.fotostream.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class FotoStream implements EntryPoint 
{
	private final CurrentImageURLServiceAsync imageURLService = GWT.create(CurrentImageURLService.class);

	public void onModuleLoad() 
	{
		final Image image = new Image();
		image.setVisible(false);
		image.addLoadHandler(new LoadHandler() 
		{
			@Override
			public void onLoad(LoadEvent event) 
			{
				image.setVisible(true);
				image.setWidth("100%");
			}
		});
		
		Timer imageReloadTimer = new Timer()
		{
			@Override
			public void run() 
			{
				imageURLService.getCurrentImageURL(new AsyncCallback<String>()
				{
					@Override
					public void onFailure(Throwable caught) 
					{
						DialogBox errorMsg = new DialogBox();
						
						errorMsg.setTitle("Error loading image");
						errorMsg.setHTML(caught.getMessage());
						errorMsg.show();
					}

					@Override
					public void onSuccess(String result) 
					{
						if (result != image.getUrl())
						{
							image.setUrl(result);
						}
					}			
				});
			}	
		};
		
		imageReloadTimer.scheduleRepeating(5000);
		
		RootPanel.get("imageContainer").add(image);
	}
}
