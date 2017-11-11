package com.tapiadevelopmentinc.snake;

import java.awt.Graphics;
import java.util.LinkedList;


// responsible for adding and keeping track of identities of game objects
public class Handler {

LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	
	
	public void tick(){
		for(int i = 0; i< object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
			
		} 
	}
	public void render(Graphics g){
		for(int i =0; i< object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	

}
