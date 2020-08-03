package com.paulo.pcompress.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.stereotype.Service;

import com.paulo.pcompress.entity.RequestFile;
import com.paulo.pcompress.entity.ResponseFile;

@Service
public class CompressService {

	public void validate(File file) {
		// TODO Auto-generated method stub
		
	}

	public ResponseFile compress(RequestFile requestFile) {
		try {
			File file = this.createImage(requestFile.getBase64File());
			
			byte[] imageByte = Base64.getDecoder().decode(requestFile.getBase64File());
			String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(imageByte));
			String extension = "";
			if(contentType.equals("image/jpeg"))
				extension = "jpeg";
			
			BufferedImage image = ImageIO.read(file);
			
			File compressedImageFile = new File("compressed_image." + extension);
		    OutputStream os = new FileOutputStream(compressedImageFile);

		    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(extension);
		    ImageWriter writer = (ImageWriter) writers.next();

		    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
		    writer.setOutput(ios);

		    ImageWriteParam param = writer.getDefaultWriteParam();

		    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		    param.setCompressionQuality(0.4f);  // Change the quality value you prefer
		    writer.write(null, new IIOImage(image, null, null), param);

		    os.close();
		    ios.close();
		    writer.dispose();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private File createImage(String base64String) throws IOException {
		byte[] imageByte = Base64.getDecoder().decode(base64String);
		String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(imageByte));
		String extension = "";
		if(contentType.equals("image/jpeg"))
			extension = "jpeg";
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		BufferedImage image = ImageIO.read(bis);
		bis.close();
		File outputfile = new File("image." + extension);
		ImageIO.write(image, extension, outputfile);
		return outputfile;
	}

}
