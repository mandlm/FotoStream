package net.molez.mandlm.fotostream.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import net.molez.mandlm.fotostream.client.CurrentImageNameService;

@SuppressWarnings("serial")
public class CurrentImageNameServiceImpl extends RemoteServiceServlet implements CurrentImageNameService 
{
	private List<File> imageFiles = new ArrayList<File>();
	
	private void readImageFiles()
	{
		imageFiles.clear();
		
		File imageFolder = new File("img/");
		File[] files = imageFolder.listFiles();
		
		for (File imageFile : files)
		{
			if (imageFile.isFile() && imageFile.getName().endsWith(".jpg"))
			{
				imageFiles.add(imageFile);
			}
		}
		
		imageFiles.sort(new Comparator<File>()
		{
			@Override
			public int compare(File file0, File file1) 
			{
				Long file0Date = file0.lastModified();
				Long file1Date = file1.lastModified();
				
				return file0Date.compareTo(file1Date);
			}
		});		
	}
	
	public String getCurrentImageName() 
	{
		int newImageSeconds = 60;
		
		readImageFiles();
		
		if (imageFiles.isEmpty())
		{
			return "img/no_image.png";
		}
		
		File latestFile = imageFiles.get(imageFiles.size() - 1);
		Date latestFileDate = new Date(latestFile.lastModified());
		
		if (latestFileDate.before(new Date(new Date().getTime() - newImageSeconds * 1000)))
		{
			return imageFiles.get(new Random().nextInt(imageFiles.size())).getName();
		}
		else
		{
			return latestFile.getName();
		}
	}
}
