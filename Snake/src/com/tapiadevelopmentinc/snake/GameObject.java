package com.tapiadevelopmentinc.snake;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class GameObject {
	public int x,y;
	protected ID id;
	protected int choiceVert = 0, choiceHoriz = 0;
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
	public void setId(ID id){
		this.id = id;
	}
	public ID getID(){
		return id;
	}
	public void setVert(int choiceVert){
		this.choiceVert = choiceVert;
	}
	public void setHoriz(int choiceHoriz){
		this.choiceHoriz = choiceHoriz;
	}
	
	
}