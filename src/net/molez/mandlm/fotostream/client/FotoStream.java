package net.molez.mandlm.fotostream.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class FotoStream implements EntryPoint 
{
	private final CurrentImageURLServiceAsync imageURLService = GWT.create(CurrentImageURLService.class);

	public void onModuleLoad() 
	{
		final Label label = new Label();
		RootPanel.get("textContainer").add(label);
		
		
		final Image image = new Image();
		image.setVisible(false);
		image.addLoadHandler(new LoadHandler() 
		{
			@Override
			public void onLoad(LoadEvent event) 
			{
				int clientHeight = Window.getClientHeight();
				int clientWidth = Window.getClientWidth();
				
				int imageHeight = image.getHeight();
				int imageWidth = image.getWidth();
				
				double heightScale = (double)clientHeight / (double)imageHeight;
				double widthScale = (double)clientWidth / (double)imageWidth;
				
				double scale = Math.min(heightScale, widthScale);

				int targetWidth = (int) (scale * (double)imageWidth);
				int targetHeight = (int) (scale * (double)imageHeight);
				
				label.setText(
						"Client: " + clientWidth + "x" + clientHeight + " " +
						"Image: " + imageWidth + "x" + imageHeight + " " +
						"Target: " + targetWidth + "x" + targetHeight);
				
				image.setVisible(true);
				image.setWidth("50%");
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
