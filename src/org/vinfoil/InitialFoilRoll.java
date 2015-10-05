package org.vinfoil;

import java.util.*;

public class InitialFoilRoll{

	private int Step;
	private ArrayList <Integer> Widths;
	private int Length;
	private int WorkingWidth;

	public InitialFoilRoll(){
		
	}
	public int getStep(){
		return Step;
	}
	public void setStep(int w){
		Step=w;
	}
	public int getLength(){
		return Length;
	}
	public void setLength(int l){
		Length=l;
	}
	public ArrayList<Integer> getWidths(int w){
		int x=300;
		
		ArrayList <Integer> Widths1=new ArrayList<Integer>();
		Widths1.add(x);
		while(x<1100){
			Widths1.add(x+w);
			x=x+w;
		}
		return Widths1;
	}
	/*public float getOffsetx1(){
		return Offsetx1;
	}
	public float getOffsetx2(){
		return Offsetx2;
	}
	public void setOffsetx2(){
		Offsetx2=Offsetx1+WorkingWidth;
	}*/
}
