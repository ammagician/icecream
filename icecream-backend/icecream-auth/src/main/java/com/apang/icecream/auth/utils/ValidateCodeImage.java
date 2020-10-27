package com.apang.icecream.auth.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 验证码工具类
 * 
 * @author Administrator
 * 
 */
class ValidateCodeImage {

	public static void main(String[] args) {
		ValidateCodeImage t = new ValidateCodeImage();
		t.createValidateImageFile("c:/vcode.jpg");
	}

	/**
	 * 随机创建验证码图片文件
	 * 
	 * @return 字符串
	 */
	public String createValidateImageFile(String filename) {
		OutputStream out = null;
		try {
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			out = fos;
			String v = createValidateImage(out, 5);
			System.out.println(v);
			return filename;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeOS(out);
		}
	}

	/**
	 * 创建验证码图片
	 * 
	 * @param os 输出流
	 *
	 * @param vlength 验证码长度
	 *
	 * @return 返回验证码
	 */
	public String createValidateImage(OutputStream os, int vlength) throws IOException {
		// 取随机数
		char[] vcode = new char[vlength];
		Random r = new Random(System.currentTimeMillis());
		// ASC: 0=48,9=57,A=65,Z=90,a=97,z=122
		for (int i = 0; i < vlength; i++) {
			int c = r.nextInt(62);
			if (c < 10)
				vcode[i] = (char) (48 + c);// 数字
			else if (c < 36)
				vcode[i] = (char) (65 - 10 + c);// 大写字母
			else
				vcode[i] = (char) (97 - 36 + c);// 大写字母
		}
		createValidateImage(os, vcode);
		return new String(vcode);
	}

	/**
	 * 创建验证码图片
	 * 
	 * @param os 输出流
	 *
	 * @param vcode 验证码
	 *
	 * @return 返回验证码
	 */
	public void createValidateImage(OutputStream os, char[] vcode) throws IOException {
		Graphics gra = null;
		try {
			final int fontSize = 25;
			int width= fontSize * vcode.length; 
			int height= fontSize + 4;
			Font mFont = new Font("微软雅黑", Font.BOLD, fontSize); // 设置字体
			BufferedImage image = new BufferedImage(fontSize * vcode.length + 1, fontSize + 4 + 1, BufferedImage.TYPE_INT_RGB);
			gra = image.getGraphics();
//			gra.setColor(Color.yellow);// 设置背景色
			Color fg = new Color(90, 69, 117);//紫色
			Color bg = new Color(255, 255, 255);//紫色
			gra.setColor(bg);// 设置背景色
			gra.fillRect(0, 0, fontSize * vcode.length + 1, fontSize + 4 + 1);
			gra.setColor(fg);// 设置字体色
			gra.setFont(mFont);

			for (int i = 0; i < vcode.length; i++) {
				String s = String.valueOf(vcode[i]); // 输出验证码
				gra.drawString(s, i * fontSize + 4, fontSize); // 7为宽度，11为上下高度位置
			}
			
			//画横穿线
			Random random = new Random(); 
	        for (int i=0; i<2; i++) {
	            int x = random.nextInt(width - 1);     
	            int y = 1;     
	            int xl = random.nextInt(width - 1) ;     
	            int yl = height-1;     
	            gra.drawLine(x , y, xl, yl);
	        }

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			encoder.encode(image);
		} finally {
			if(gra!=null){
				gra.dispose();
			}
			closeOS(os);
		}
	}

	private void closeOS(OutputStream stream) {
		if (stream == null)
			return;
		try {
			stream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
