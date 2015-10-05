package org.vinfoil;

import java.util.*;
public class Patterns {

	private ArrayList <Rectangel> Rectangels;
	private ArrayList <Triangel> Triangels;
	private ArrayList <Parallelogram> Parallelograms;
	
	public Patterns(){
		
	}
	public ArrayList <Rectangel> getRectangels(){
		
		return Rectangels;
	}
	public void setRectangels(ArrayList<Rectangel> rectangels){
		Rectangels=rectangels;
	}
	public ArrayList <Triangel> getTriangels (){
		
		return Triangels;
	}
	public void setTriangels(ArrayList<Triangel> triangels){
		Triangels=triangels;
	}
	public ArrayList <Parallelogram> getParallelograms (){
		
		return Parallelograms;
	}
	public void setParalleorgrams(ArrayList<Parallelogram> parallelograms){
		Parallelograms=parallelograms;
	}
}
