package com.zifangsky.OnlineFriend.servlet.member;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 生成随机图片用做验证码
 * */
public class HandleDrawValidateCode extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 120;  //图片宽度
	private static final int HEIGHT = 30;  //图片高度
		
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession(true);
		
		//创建一张图片
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//得到图片
		Graphics graphics = bufferedImage.getGraphics();
		//设置图片背景色
		setBackGround(graphics);
		//设置图片边框
		setBordor(graphics);
		//在图片上画干扰线，用了4种颜色，共20条线条
		drawRandomLine(graphics,Color.GREEN);
		drawRandomLine(graphics,new Color(246,255,145));
		drawRandomLine(graphics,new Color(225,174,252));
		drawRandomLine(graphics,new Color(120,202,254));
		//在图片上写随机字符，并记录生成的序列
		String randomText = drawRandomText((Graphics2D) graphics);
		
		session.setAttribute("checkcode", randomText);
		//设置响应头通知浏览器以图片的形式打开
		response.setContentType("image/jpeg");
		//设置响应头控制浏览器不要缓存
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		//将图片写给浏览器
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());

	}
	
	/**
	 * 设置图片背景色
	 * */
	private void setBackGround(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
	}

	/**
	 * 设置图片边框
	 * */
	private void setBordor(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	}
	
	/**
	 * 在图片上画干扰线
	 * */
	private void drawRandomLine(Graphics graphics,Color color) {
		graphics.setColor(color);
		//设置线条个数并画线
		for(int i = 0;i < 5;i++){
			int x1 = new Random().nextInt(WIDTH);
			int x2 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);
			int y2 = new Random().nextInt(HEIGHT);
			graphics.drawLine(x1, y1, x2, y2);
		}
	}
	
	/**
	 * 在图片上写随机字符，数字和字母的组合
	 * @param length 字符串的长度
	 * 
	 * @return 返回生成的字符串序列
	 * */
	private String drawRandomText(Graphics2D graphics) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("宋体", Font.BOLD, 20));
		
		//数字和字母的组合
		String baseNumLetter = "123456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		StringBuffer sBuffer = new StringBuffer();
		
		int x = 5;  //旋转原点的 x 坐标
		String ch = "";
		Random random = new Random();
		for(int i = 0;i < 4;i++){
			//设置字体旋转角度
			int degree = random.nextInt() % 30;  //角度小于30度
			int dot = random.nextInt(baseNumLetter.length());
			ch = baseNumLetter.charAt(dot) + ""; 
			sBuffer.append(ch);
			
			//正向旋转
			graphics.rotate(degree * Math.PI / 180, x, 20);
			graphics.drawString(ch, x, 20);
			
			//反向旋转
			graphics.rotate(-degree * Math.PI / 180, x, 20);
			x += 30;
		}
		
		return sBuffer.toString();
	}

	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request, response);
	}
}

