package com.tapiadevelopmentinc.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tapiadevelopmentinc.snake.Game.STATE;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	final int UP = KeyEvent.VK_W, DOWN = KeyEvent.VK_S, LEFT = KeyEvent.VK_A, RIGHT = KeyEvent.VK_D;
	static boolean horizontal = false;
	static boolean vertical = false;
	static int currKey = 0;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int currKey = e.getKeyCode();
		
		if(Game.counter > 20){
			Game.counter = 0;
			GameObject tempObject = handler.object.get(0);
			if(tempObject.getID() == ID.Snake){
				//key events for player 1
				
				
				if(Game.gameState == STATE.Game && currKey == UP && !vertical){
					//System.out.println("up");
					tempObject.setVert(1);
					tempObject.setHoriz(0);

					vertical = true;
					horizontal = false;
				}
				else if(Game.gameState == STATE.Game && currKey == DOWN && !vertical){
					//System.out.println("down");
					tempObject.setVert(2);
					tempObject.setHoriz(0);

					vertical = true;
					horizontal = false;
				}
				else if(Game.gameState == STATE.Game && currKey == RIGHT && !horizontal){
					//System.out.println("right");
					tempObject.setVert(0);
					tempObject.setHoriz(2);

					horizontal = true;
					vertical = false;

				}
				else if(Game.gameState == STATE.Game && currKey == LEFT && !horizontal){
					//System.out.println("left");
					tempObject.setVert(0);
					tempObject.setHoriz(1);

					horizontal = true;
					vertical = false;

				}

			}
		}
		
		
		if(currKey == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_P || key == KeyEvent.VK_SPACE){
			if(Game.gameState == STATE.Pause){
				Game.gameState = STATE.Game;
			}else if(Game.gameState == STATE.Game){	
				Game.gameState = STATE.Pause;
			}

		}
	}
	
}
