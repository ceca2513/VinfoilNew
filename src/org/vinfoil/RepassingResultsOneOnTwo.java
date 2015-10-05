package org.vinfoil;

import java.util.*;
public class RepassingResultsOneOnTwo {
	private FWL Lane1;
	private FWL Lane2;
	private FWL Lane3;
	private Direction1andDirection2 DD;
	private ArrayList<FWL> AllLanes;
	
	public FWL getLane1(){
		return Lane1;
	}
	public void setLane1(FWL lane){
		Lane1=lane;
	}
	public FWL getLane2(){
		return Lane2;
	}
	public void setLane2(FWL lane){
		Lane2=lane;
	}
	public FWL getLane3(){
		return Lane3;
	}
	public void setLane3(FWL lane){
		Lane3=lane;
	}
	
	public Direction1andDirection2 getDD(){
		return DD;
	}
	public void setDD(Direction1andDirection2 dd){
		DD=dd;
	}
	public ArrayList<FWL> getAllLanes(){
		return AllLanes;
	}
	public void setAllLanes(ArrayList <FWL> lanes){
		AllLanes=lanes;
	}
}
