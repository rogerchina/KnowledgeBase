/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.corejava.http;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class CopyOfHttpPingTest {
	public static void main(String[] args) {
		try {
			for (int i = 1005; i < 1105; i++) {
				URI uri = new URI("https:");
				File f = new File("c:\\test" + i + ".jpg");
				if (!f.exists()) {
					f.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(f);
				ImageIO.write(getImageFromRemote(uri), "jpg", fos);

				System.out.println(f.getAbsolutePath() + " is ok");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static BufferedImage getImageFromRemote(final URI uri) {
		try {
			URLConnection con = uri.toURL().openConnection();
			con.setConnectTimeout(2000);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setDefaultUseCaches(false);
			con.setRequestProperty("Content-Type", "application/octet-stream");
			System.out.println("1" + con);
			try {
				InputStream imageStream = con.getInputStream();
				System.out.println("2" + con.getInputStream());
				return ImageIO.read(imageStream);
			} finally {

			}
		} catch (Exception ex) {
		}
		return null;
	}
}