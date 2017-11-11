package com.tapiadevelopmentinc.snake;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Window extends Canvas {

	private static final long serialVersionUID = -4862769661114639276L;
	static JFrame myFrame;
	Game game;
	static String name;
	private static Panel controlPanel;
	static boolean done = false;

	public Window(String title, int width, int height, Game game) {
		myFrame = new JFrame(title);

		myFrame.setSize(width, height);
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);

		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/input/Icon.png"));
		myFrame.setIconImage(icon.getImage());
		this.game = game;
		myFrame.add(game);
		myFrame.setVisible(true);
		game.start();
	}

	public Window(String title, int width, int height) {
		myFrame = new JFrame(title);

		myFrame.setSize(width, height);
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);

		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/input/Icon.png"));
		myFrame.setIconImage(icon.getImage());
		
		controlPanel = new Panel();
		controlPanel.setBackground(Color.black);
		//controlPanel.setLayout(new FlowLayout());
		Font font1 = new Font("arial", 1, 30);
		
		
		
		JLabel update = new JLabel("You lost with a score of " + Snake.snakeSize + ".                      ");
		update.setForeground(Color.WHITE);
		update.setFont(font1);

		JLabel info = new JLabel("Please enter your name:");
		info.setForeground(Color.WHITE);
		info.setFont(font1);

		TextField nameText = new TextField(6);
		nameText.setFont(font1);

		Button submit = new Button("GO");
		submit.setFont(font1);
		submit.setSize(200, 200);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					String name = nameText.getText().trim();
					if(name.length() > 7){
						Menu.currentName = name.substring(0, 8);
					}else{
						Menu.currentName = name;
					}
					myFrame.dispose();

			}
		});
		controlPanel.add(update);

		controlPanel.add(info);

		controlPanel.add(nameText);
		controlPanel.add(submit);

		myFrame.add(controlPanel);
		myFrame.setVisible(true);
		done = true;

	}

}
