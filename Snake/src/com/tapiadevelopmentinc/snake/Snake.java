package com.tapiadevelopmentinc.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.tapiadevelopmentinc.snake.Game.STATE;



public class Snake extends GameObject {

	Random r = new Random();
	Handler handler;
	int velX = 0, velY = 0;
	static int snakeSize = 1;
	ArrayList<Integer> positionX = new ArrayList<Integer>();
	ArrayList<Integer> positionY = new ArrayList<Integer>();
	int dir = 0;

	static int snakeDimension = 40;
	public Snake(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		


		this.handler = handler;


	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, snakeDimension, snakeDimension);
	}

	public void tick() {
		
		//make sure you can move into intended spots only
		//up
		if (choiceVert == 1 && x % snakeDimension == 0) {
			velY = -8;
			velX = 0;
			dir = 0;
		} 
		//down
		else if (choiceVert == 2 && x % snakeDimension == 0) {
			velY = 8;
			velX = 0;
			dir = 1;
		}
		//left
		if (choiceHoriz == 1 && y % snakeDimension == 0) {
			velX = -8;
			velY = 0;
			dir = 2;
		} 
		//right
		else if (choiceHoriz == 2 && y % snakeDimension == 0) {
			velX = 8;
			velY = 0;
			dir = 3;
		}
		
		x += velX;
		y += velY;
		//note adjust these values for the window size, chosen by pixels in window
		x = Game.clamp(x, 0, Game.width - snakeDimension - Game.adjusterX);
		y = Game.clamp(y, 0, Game.height - snakeDimension - Game.adjusterY);
		
		positionX.add(0, x);
		positionY.add(0, y);
		
		//System.out.println(positionX);
		//System.out.println(positionY);
		//System.out.println();
		
		if(positionX.size() > 1000){
			positionX.subList(1000, positionX.size()).clear();
			positionY.subList(1000, positionY.size()).clear();

		}
		collision();
		if((snakeSize >= 3 && snakeCollision())){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Game.gameState = STATE.End;
		}

	}
	private void collision(){
		for(int i = 0; i < handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getID() == ID.Food){ // TEMP OBJ IS ENEMY
				//collision  code
				if(getBounds().intersects(tempObject.getBounds())){
					Food.x = (int)((Math.random() * Game.width)/snakeDimension) *snakeDimension;
					Food.y = (int)((Math.random() * Game.height)/snakeDimension) *snakeDimension;
					Food.x = Game.clamp(Food.x, 0, Game.width - snakeDimension - Game.adjusterX);
					Food.y = Game.clamp(Food.y, 0, Game.height - snakeDimension - Game.adjusterY);
					//System.out.println( Food.x + " , " + Food.y);
					snakeSize++;
					//placement();
					//handler.addObject(new SnakeBody( positionX.get(1), positionY.get(1), ID.SnakeBody, handler));
					//System.out.println(snakeSize);
				}
				
			}
		}
	}
	
	//collision of snake head and body
	private boolean snakeCollision() {

		for(int i = 20; i < (snakeSize)*5; i++){
			
			if(x == (int)positionX.get(i) && y == (int)positionY.get(i)){
				return true;
			}
		}
		return false;
	}
	/*
	private void placement() {
		positionX.add(positionX.get(0));
		positionY.add(positionY.get(0));

		if(velY == -5){
			positionY.set(1,positionY.get(0)-20);
		}else if(velY == 5){
			positionY.set(1,positionY.get(0)+20);
		}else if(velX == -5){
			positionX.set(1,positionX.get(0)+20);
		}else if(velX == 5){
			positionX.set(1,positionX.get(0)-20);
		}
		
	}
	 */
	public void render(Graphics g) {
		// UP, DOWN, LEfT, RIGHT
		// x,y ordered
		int[][] eyesPosition1 = {{6,10},{6,24}, {10,6}, {24, 6}};
		int[][] eyesPosition2 = {{28,10},{28,24}, {10, 28}, {24, 28}};

		
		for(int i = 1; i< snakeSize; i++){
			g.setColor(Color.white);
			g.fillRect(positionX.get(5*i), positionY.get(5*i), snakeDimension, snakeDimension);

			g.setColor(Color.green);
			g.fillRect(positionX.get(5*i) + 1 , positionY.get(5*i) + 1 , snakeDimension-2, snakeDimension-2);



		}
		g.setColor(Color.white);
		g.fillRect(x, y, snakeDimension, snakeDimension);
		
		g.setColor(Color.green);
		g.fillRect(x+1, y+1, snakeDimension-2, snakeDimension-2);
		
		g.setColor(Color.black);
		g.fillRect(x+eyesPosition1[dir][0], y+eyesPosition1[dir][1], 6, 6);
		
		g.setColor(Color.black);
		g.fillRect(x+eyesPosition2[dir][0], y+eyesPosition2[dir][1], 6, 6);

	}

}
