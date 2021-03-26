/*
 * Mouse2Sticks
 * (c) Justus Languell 2021
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")

class MainCanvas extends JComponent 
{

	static int scaler = 2;
	static int speed = 5;
	static int zone = 100;
	static int glide = 100;

	int a;
	int b;

	public void set(int a, int b) 
	{
			this.a = a;
			this.b = b;
			repaint();
	}

	public void paint(Graphics g) 
	{
			super.paint(g);
			g.drawRect(a,b,5,5);
	}

	public static void main(String args[]) throws InterruptedException 
	{

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.getHeight();
		screenSize.getWidth();
		int sw = screenSize.width;
		int sh = screenSize.height;
		int cx = sw / 2;
		int cy = sh / 2;
		int scx = cx / scaler;
		int scy = cy / scaler;

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, sw/scaler, sh/scaler);
		MainCanvas canvas = new MainCanvas();

		window.getContentPane().add(canvas);
		window.setVisible(true);

		Thread.sleep(100);

		int X = scx;
		int Y = scy;

		while (true) 
		{

			PointerInfo pointInfo = MouseInfo.getPointerInfo();
			Point point = pointInfo.getLocation();

			int x = (int) point.getX();
			int y = (int) point.getY();

			float dx = (cx - x) * -1;
			float dy = (cy - y) * -1;
			
			int sx = (int)((dx / (zone)) * speed);
			int sy = (int)((dy / (zone)) * speed);

			X = X + sx;
			Y = Y + sy;

			canvas.set((int)(X/glide),(int)(Y/glide));
			window.getContentPane().add(canvas);
			window.setVisible(true);

		}
	}
}
