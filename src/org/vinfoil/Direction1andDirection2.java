package org.vinfoil;

public class Direction1andDirection2 {

	private int Direction1;
	private int Direction2;
	private int ShiftX1;
	private int ShiftY1;
	private int ShiftX2;
	private int ShiftY2;
	
	
    public int getDirection1(){
    	return Direction1;
    }
    public void seDirection1(int d){
    	Direction1=d;
    }
    public int getDirection2(){
    	return Direction2;
    }
    public void setDirection2(int d){
    	Direction2=d;
    }
    public int getShiftX1(){
    	return ShiftX1;
    }
    public void setShiftX1(int s){
    	ShiftX1=s;
    }
    public int getShiftY1(){
    	return ShiftY1;
    }
    public void setShiftY1(int s){
    	ShiftY1=s;
    }
    public int getShiftX2(){
    	return ShiftX2;
    }
    public void setShiftX2(int s){
    	ShiftX2=s;
    }
    public int getShiftY2(){
    	return ShiftY2;
    }
    public void setShiftY2(int s){
    	ShiftY2=s;
    }
    public Direction1andDirection2(int d1, int d2, int sx1, int sy1, int sx2, int sy2){
    	Direction1=d1;
    	Direction2=d2;
    	ShiftX1=sx1;
    	ShiftY1=sy1;
    	ShiftX2=sx2;
    	ShiftY2=sy2;
    }
}
