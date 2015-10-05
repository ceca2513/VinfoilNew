package org.vinfoil;

import java.util.*;
import java.math.*;
public class HoldingResult {
	
	private boolean Repassing;
	private int Direction;
	private int ShiftX;
	private int ShiftY;
	
	public HoldingResult(){
		
	}
	
    public boolean getRepassing(){
    	return Repassing;
    }
    public void setRepassing(boolean t){
    	Repassing=t;
    }
    public int getDirection(){
    	return Direction;
    }
    public void setDirection(int dir){
    	Direction=dir;
    }
    public int getShiftX(){
    	return ShiftX;
    }
    public void setShiftX(int x){
    	ShiftX=x;
    }
    public int getShiftY(){
    	return ShiftY;
    }
    public void setShiftY(int y){
    	ShiftY=y;
    }

}
