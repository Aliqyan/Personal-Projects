package com.tapiadevelopmentinc.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import com.tapiadevelopmentinc.snake.Game.STATE;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> scores = new ArrayList<String>();
	private int num;
	int ticker1 = 0;
	int ticker2 = 0;
	boolean doneLoading = false;
	boolean doneRendering = false;
	static String currentName;
	Image img1;

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();

		// try again button for end
		if (game.gameState == STATE.End && mouseOver(mX, mY, (game.width - game.adjusterX - 300) / 4, 600, 300, 96)) {
			KeyInput.horizontal = false;
			KeyInput.vertical = false;
			Snake.snakeSize = 1;
			doneLoading = false;
			doneRendering = false;
			currentName = null;
			handler.object.clear();
			game.loadGame();
			game.gameState = STATE.Game;
			ticker1 = 0;
			ticker2 = 0;
			return;
		}
		// Quit button for end
		if (game.gameState == STATE.End
				&& mouseOver(mX, mY, (game.width - game.adjusterX - 300) / 4 * 3, 600, 300, 96)) {
			System.exit(1);
		}

		// back button for help
		if (game.gameState == STATE.Help && mouseOver(mX, mY, (game.width - game.adjusterX - 300) / 2, 600, 300, 96)) {
			game.gameState = STATE.Menu;
			return;
		}
		if (game.gameState == STATE.Menu) {
			// play button
			if (mouseOver(mX, mY, (game.width - game.adjusterX - 300) / 2, 350, 300, 96)) {
				game.gameState = STATE.Game;
				game.loadGame();
			}

			// Help button
			if (mouseOver(mX, mY, (game.width - game.adjusterX - 300) / 2, 475, 300, 96)) {
				game.gameState = STATE.Help;
			}
			// quit button
			if (mouseOver(mX, mY, (game.width - game.adjusterX - 300) / 2, 600, 300, 96)) {
				System.exit(1);

			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	boolean stop = false;
	boolean imageReady = false;
	public void tick() {
		if(game.gameState == STATE.Menu){
			try {
				img1 = ImageIO.read(getClass().getResource("/input/Snake_Logo.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			imageReady = true;

		}
		if ((game.gameState == STATE.End && ticker1 == 0)) {
			loadNames();
			loadScores();
			new Window("Prompt", 585, 150);

			ticker1++;

		}
	}

	private boolean mouseOver(int mX, int mY, int x, int y, int width, int height) {
		return (mX > x && mX < x + width && mY > y && mY < y + height);
	}

	public void render(Graphics g) {
		Font font1 = new Font("arial", 1, 80);

		Font font2 = new Font("arial", 1, 40);
		Font font3 = new Font("arial", 1, 20);
		Font font4 = new Font("arial", 1, 30);

		if (game.gameState == STATE.Menu && imageReady) {

			g.setFont(font1);
			g.setColor(Color.white);
			//draw logo
			new DrawImg(g, img1);
			
			g.setFont(font2);
			g.drawRect((game.width - game.adjusterX - 300) / 2, 350, 300, 96);
			g.drawString("Play", (game.width - game.adjusterX) / 2 - 40, 360+50);

			g.drawRect((game.width - game.adjusterX - 300) / 2, 475, 300, 96);
			g.drawString("Help", (game.width - game.adjusterX) / 2 - 40, 510+25);

			g.drawRect((game.width - game.adjusterX - 300) / 2, 600, 300, 96);
			g.drawString("Quit", (game.width - game.adjusterX) / 2 - 40, 660);
		} else if (game.gameState == STATE.Help) {

			g.setFont(font1);
			g.setColor(Color.white);
			g.drawString("Help", (game.width - game.adjusterX)/2 - 90, 100);
			
			g.setFont(font2);

			g.drawString("Instructions:", 200, 200);
			g.drawString("___________", 200, 200);
			

			g.drawString("--> Eat food to gain points.", 200, 275);
			g.drawString("--> If you crash into yourself, you die.", 200, 325);


			g.drawString("--> WASD to move.", 200, 375);
			g.drawString("--> P or Space to pause.", 200, 425);
			g.drawString("--> ESC to Quit whilst playing.", 200, 475);

			g.setFont(font2);

			g.drawRect((game.width - game.adjusterX - 300) / 2, 600, 300, 96);
			g.drawString("Back", (game.width - game.adjusterX) / 2 - 40, 660);
		} else if (game.gameState == STATE.End) {
			g.setFont(font1);
			g.setColor(Color.white);
			g.drawString("Game Over!", (game.width- - game.adjusterX) / 2 - 230, 100);
			

			if (currentName != null && !doneRendering) {
				if(ticker2 == 0){
					System.out.println(currentName);
					updateLeaderboard();
					ticker2++;
					doneLoading = true;

				}
				if (doneLoading ) {
					g.setFont(font2);

					g.drawString("Leaderboards", (game.width- - game.adjusterX) / 2 - 140, 170);

					g.setFont(font4);

					// row 1
					g.drawString("Ranking", 150, 210+30);
					g.drawString("Player", (game.width - game.adjusterX - 100) / 3 + 170, 210+30);
					g.drawString("Score", (game.width - game.adjusterX - 100) / 3 * 2 + 160, 210+30);

					g.drawRect(50, 170+30, (game.width - game.adjusterX - 100) / 3, 60);
					g.drawRect((game.width - game.adjusterX - 100) / 3 + 50, 170+30,
							(game.width - game.adjusterX - 100) / 3, 60);
					g.drawRect((game.width - game.adjusterX - 100) / 3 * 2 + 50, 170+30,
							(game.width - game.adjusterX - 100) / 3, 60);

					// row 2

					for (int i = 1; i <= 5; i++) {
						
						g.drawString(i + "", 200, 210 +30 + 60 * (i));
						String tempName;
						String tempScore;
						// System.out.println(i + ", " + num);
						if (i <= num) {
							// System.out.println(names);
							// System.out.println(scores);

							tempName = names.get(i - 1).trim();
							tempScore = scores.get(i - 1).trim();
						} else {
							tempName = "-";
							tempScore = "-";
						}

						g.drawString(
								tempName, (game.width - game.adjusterX - 100) / 3
										+ (game.width - game.adjusterX - 100) / 6 + 50 - (tempName.length() * 15) / 2,
								210 + 30 + 60 * (i));
						g.drawString(
								tempScore, (game.width - game.adjusterX - 100) / 3 * 2
										+ (game.width - game.adjusterX - 100) / 6 + 50 - (tempScore.length() * 15) / 2,
								210 + 30 + 60 * (i));

						g.drawRect(50, 170 + 30 +60 * i, (game.width - game.adjusterX - 100) / 3, 60);
						g.drawRect((game.width - game.adjusterX - 100) / 3 + 50, 170 +30 + 60 * i,
								(game.width - game.adjusterX - 100) / 3, 60);
						g.drawRect((game.width - game.adjusterX - 100) / 3 * 2 + 50, 170+30 + 60 * i,
								(game.width - game.adjusterX - 100) / 3, 60);

					}
				}
				g.drawRect((game.width - game.adjusterX - 300) / 4, 600, 300, 96);
				g.drawString("Try Again", (game.width - game.adjusterX) / 4 - 10, 660);

				g.drawRect((game.width - game.adjusterX - 300) / 4 * 3, 600, 300, 96);
				g.drawString("Quit", (game.width - game.adjusterX) / 4 * 3 - 120, 660);
			}
			doneRendering = false;
		}else if(game.gameState == STATE.Pause){
			Color color = new Color(0f,0f,0f,0.75f);
			g.setColor(color);
			g.fillRect(0, 0, Game.width, Game.height);
			
			g.setFont(font1);
			g.setColor(Color.white);
			g.drawString("Paused", (game.width- - game.adjusterX) / 2 - 150, (game.height- - game.adjusterY) /2 - 100 );
			g.setFont(font2);

			g.drawString("Press Space or P to resume", (game.width - game.adjusterX) / 2 - 260, 560);

		}

	}

	private void updateLeaderboard() {

		try {
			// System.out.println(num);
			String userHomeFolder = System.getProperty("user.home");
			File textFile = new File(userHomeFolder, "leaderboards.dat");
			textFile.createNewFile();
			FileWriter fw = new FileWriter(textFile);
			boolean added = false;
			for (int i = 0; i < scores.size(); i++) {
				if (Integer.parseInt(scores.get(i)) < Snake.snakeSize) {
					num++;
					names.add(i, currentName);
					scores.add(i, "" + Snake.snakeSize);
					added = true;
					break;
				}
			}
			if (!added) {
				num++;
				names.add(currentName);
				scores.add("" + Snake.snakeSize);
			}

			fw.write(num + "\n");
			// System.out.println("-->" + num);

			for (int i = 0; i < num; i++) {
				fw.write(names.get(i) + ":" + scores.get(i) + "\n");
			}

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("a");

	}

	private void loadNames()  {
		names.clear();
		try {
			String userHomeFolder = System.getProperty("user.home");
			BufferedReader br = null;
			File textFile = new File(userHomeFolder, "leaderboards.dat");

			 if(textFile.exists()){
				FileReader fr = new FileReader(textFile);
				 br = new BufferedReader(fr);
			 }else if(!textFile.exists()){
				InputStream input = getClass().getResourceAsStream("/input/leaderboards.dat");
				br = new BufferedReader(new InputStreamReader(input));

			}
			
			num = Integer.parseInt(br.readLine());

			for (int i = 0; i < num; i++) {
				names.add(br.readLine().split(":")[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadScores() {
		scores.clear();

		try {
			String userHomeFolder = System.getProperty("user.home");
			BufferedReader br = null;
			File textFile = new File(userHomeFolder, "leaderboards.dat");

			 if(textFile.exists()){
				FileReader fr = new FileReader(textFile);
				 br = new BufferedReader(fr);
			 }else if(!textFile.exists()){
				InputStream input = getClass().getResourceAsStream("/input/leaderboards.dat");
				br = new BufferedReader(new InputStreamReader(input));

			}
			num = Integer.parseInt(br.readLine());
			for (int i = 0; i < num; i++) {
				scores.add(br.readLine().split(":")[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
