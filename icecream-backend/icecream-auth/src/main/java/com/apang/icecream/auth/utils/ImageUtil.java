package com.apang.icecream.auth.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * 图片工具类
 * 
 * @author Administrator
 * 
 */
public class ImageUtil {

	/** 因为类必须为public，所以只能把构造函数给这样控制 */
	ImageUtil() {

	}

	private final ValidateCodeImage validCodeImage = new ValidateCodeImage();

	/**
	 * 把图片放大或缩小到指定尺寸,只支持JPG
	 * 
	 * @author 2007-7-30 21:31:46
	 * @param imagefile
	 *            图片文件
	 * @param width
	 *            指定宽度
	 * @param height
	 *            指定高度
	 * @param type
	 *            0:无论如何都把图片放大或缩小到指定尺寸,1:原始尺寸比指定尺寸小则保持,2:原始尺寸比指定尺寸大则保持
	 */
	public String imageToScale(String imagefile, int width, int height, int type) throws IOException {
		File in = new File(imagefile);

		String fname = in.getName();
		String result;
		int index = fname.indexOf('.');

		if (index > 0) {
			String ext = fname.substring(index + 1);
			result = in.getParent() + "/" + fname.substring(0, index) + "2." + ext;
			//ext = fname.substring(index + 1);
		} else
			result = in.getPath() + "." + fname;
		File out = new File(result);

		AffineTransform transform = new AffineTransform();
		BufferedImage srcImg = ImageIO.read(in);

		int imgw = srcImg.getWidth(), imgh = srcImg.getHeight();
		switch (type) {
		case 1: // 原始尺寸比指定尺寸小则保持
			if (imgw < width)
				width = imgw;
			if (imgh < height)
				height = imgh;
		case 2:// 2:原始尺寸比指定尺寸大则保持
			if (imgw > width)
				width = imgw;
			if (imgh > height)
				height = imgh;
		}
		if (width == imgw && height == imgh)
			return imagefile;

		double w = width / (double) imgw;
		double h = height / (double) imgh;
		transform.setToScale(w, h);
		AffineTransformOp middleAffine = new AffineTransformOp(transform, null);
		BufferedImage img = new BufferedImage(width, height, 5);
		middleAffine.filter(srcImg, img);

		ImageIO.write(img, "jpg", out);
		return result;
	}

	public String createValidateImage(OutputStream os, int vlength) throws IOException {
		return validCodeImage.createValidateImage(os, vlength);
	}

	public void createValidateImage(OutputStream os, char[] vcode) throws IOException {
		validCodeImage.createValidateImage(os, vcode);
	}

}
