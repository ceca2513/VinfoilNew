package org.vinfoil;

import java.util.*;
public class RepassingResultsOneLaneTwoTimes {
	private FWL Lane1;
	private FWL Lane2;
	private FWL Lane3;
	private FWL Lane4;
	private HoldingResult HS1;
	private HoldingResult HS2;
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
	public FWL getLane4(){
		return Lane4;
	}
	public void setLane4(FWL lane){
		Lane4=lane;
	}
	public HoldingResult getHS1(){
		return HS1;
	}
	public void setHS1(HoldingResult hs){
		HS1=hs;
	}
	public HoldingResult getHS2(){
		return HS2;
	}
	public void setHS2(HoldingResult hs){
		HS2=hs;
	}
	public ArrayList<FWL> getAllLanes(){
		return AllLanes;
	}
	public void setAllLanes(ArrayList <FWL> lanes){
		AllLanes=lanes;
	}
}
