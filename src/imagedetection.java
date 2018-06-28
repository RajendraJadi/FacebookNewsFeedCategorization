
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


public class imagedetection 
{
	boolean saveImage(String imageUrl,String name) throws Exception
	{
		
		String destinationFile = imageUrl;
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(name);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
		return true;
	}
	boolean detectFace()
	{
		
		return true;
	}
	
	public static void main(String[] args) throws Exception 
	{
		imagedetection i = new imagedetection();
		i.saveImage("http://graph.facebook.com/1606400914/picture?width=350&height=350","dummy.jpg");
	}
}
