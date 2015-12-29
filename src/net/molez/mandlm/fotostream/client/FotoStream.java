package net.molez.mandlm.fotostream.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class FotoStream implements EntryPoint 
{
	private final CurrentImageNameServiceAsync imageNameService = GWT.create(CurrentImageNameService.class);

	public void onModuleLoad() 
	{
		final Image image = new Image();
		image.setVisible(false);
		image.addLoadHandler(new LoadHandler() 
		{
			@Override
			public void onLoad(LoadEvent event) 
			{
				int clientWidth = Window.getClientWidth();
				int clientHeight = Window.getClientHeight();

				double clientAspect = (double)clientWidth / (double)clientHeight;
				
				int imageWidth = image.getOffsetWidth();
				int imageHeight = image.getOffsetHeight();
				
				double imageAspect = (double)imageWidth / (double)imageHeight;

				int widthScalePercent = 100;
				if (imageAspect > 0 && clientAspect > 0 && imageAspect < clientAspect)
				{
					widthScalePercent = (int)(((double)imageAspect / (double)clientAspect) * 100.0);
				}

				image.setVisible(true);
				image.setWidth(widthScalePercent + "%");
			}
		});
		
		Timer imageReloadTimer = new Timer()
		{
			@Override
			public void run() 
			{
				imageNameService.getCurrentImageName(new AsyncCallback<String>()
				{
					@Override
					public void onFailure(Throwable caught) 
					{
						image.setUrl("img/no_image.png");
					}

					@Override
					public void onSuccess(String result) 
					{
						image.setUrl("image?name=" + result);
					}			
				});
			}	
		};
		
		imageReloadTimer.scheduleRepeating(5000);
		
		RootPanel.get("imageContainer").add(image);
	}
}
