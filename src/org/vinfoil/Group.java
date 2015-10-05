package org.vinfoil;

import java.util.*;
import java.math.*;
public class Group {

	private float Distance;
	private ArrayList <Integer> Indices;
	
	public Group(){
		
	}
	
	public void setDistance(float d){
		Distance=d;
	}
	public float getDistance(){
		return Distance;
	}
	public void setIndices(ArrayList <Integer> i){
		
		Indices=i;
	}
	public ArrayList <Integer> getIndices(){
		return Indices;
	}
}
