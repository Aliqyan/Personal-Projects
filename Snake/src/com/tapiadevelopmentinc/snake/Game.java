package com.tapiadevelopmentinc.snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JLabel;



public class Game extends Canvas implements Runnable {

	
	private static final long serialVersionUID = 6503812276980334303L;
	private static boolean running = false;
	private static Thread thread;
	public static int width;
	public static int height;
	public static int adjusterX = 0;
	public static int adjusterY = 0;
	int snakeDimension = 40;

	private Menu menu;
	private Handler handler;
	
	public enum STATE {
		Menu,
		Help,
		Game,
		Pause,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public static void JFrameAdjuster(){
		if(OSFinder.isMac()){
			height += 22;
			adjusterY = 22;
		}else if(OSFinder.isWindows()){
			height += 40;
			width += 6;
			adjusterX = 6;
			adjusterY = 40;
		}
	} 
	public static void main(String[] args) {
		chooseDimensions();
		JFrameAdjuster();
		new Game();
	}
	private static void chooseDimensions() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width/2;
		height = screenSize.height/2;
	}
	public Game(){
		handler = new Handler();
		menu = new Menu(this, handler);

		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Window("Snake", width, height, this);
		if(gameState == STATE.Game){
			loadGame();
		}
		


	}
	public void loadGame(){
		handler.addObject(new Snake( (int)((width/2 - snakeDimension/2)/snakeDimension) *snakeDimension , (int)((height/2 - snakeDimension)/snakeDimension) * snakeDimension, ID.Snake, handler));
		
		int foodX = (int)((Math.random() * width)/snakeDimension) *snakeDimension;
		int foodY = (int)((Math.random() * height)/snakeDimension) *snakeDimension;
		foodX = Game.clamp(foodX, 0, width - snakeDimension - adjusterX);
		foodY = Game.clamp(foodY, 0, height - snakeDimension - adjusterY);;
		handler.addObject(new Food( foodX , foodY, ID.Food, handler));
	}
	@Override
	public void run() {
		 this.requestFocus();
		 long lastTime = System.nanoTime();
	        double amountOfTicks = 60.0;
	        double ns = 1000000000 / amountOfTicks;
	        double delta = 0;
	        long timer = System.currentTimeMillis();
	        int frames = 0;
	        while(running){
	                    long now = System.nanoTime();
	                    delta += (now - lastTime) / ns;
	                    lastTime = now;
	                    while(delta >=1){
	                    	tick();
	                        delta--;
	                    }
	                    if(running){
	                      	render();
	                    }
	                    frames++;
	                          
	                    if(System.currentTimeMillis() - timer > 1000){
	                      	timer += 1000;
	                        //System.out.println("FPS: "+ frames);
	                        frames = 0;
	                    }
	        }
	        stop();
		
	}
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;		
	}
	
	public synchronized static void stop() {
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static int counter = 0;
	private void render() {
		counter++;
		//System.out.println(counter);
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		

		if(gameState == STATE.Game){
			
			Font newFont = new Font("Courier New", 1, 50);
			g.setFont(newFont);
			g.setColor(Color.white);
			g.drawString("Score: " + Snake.snakeSize, 20, 55);
			handler.render(g);
			
		}else if(gameState == STATE.Menu ||gameState == STATE.Help || gameState == STATE.End){
			menu.render(g);
		}else if( gameState == STATE.Pause){
			Font newFont = new Font("Courier New", 1, 50);
			g.setFont(newFont);
			g.setColor(Color.white);
			g.drawString("Score: " + Snake.snakeSize, 20, 55);
			handler.render(g);
			menu.render(g);

		}
		
		

		//hud.render(g);
		bs.show();		

		g.dispose();
	}
	private void tick() {
		if(gameState == STATE.Game){
			handler.tick();
		}else if(gameState == STATE.Menu || gameState == STATE.End){
			menu.tick();
		}
		
	}
	public static int clamp(int var, int min, int max){
		if(var > max){
			return var = min;
		}else if(var < min){
			return var = max;
		}else{
			return var;
		}
	}

}
