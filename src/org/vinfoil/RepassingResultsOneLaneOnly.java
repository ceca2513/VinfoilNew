package org.vinfoil;

import java.util.*;
public class RepassingResultsOneLaneOnly {
	private FWL Lane1;
	private FWL Lane2;
	private HoldingResult HS;
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
	public HoldingResult getHS(){
		return HS;
	}
	public void setHS(HoldingResult hs){
		HS=hs;
	}
	public ArrayList<FWL> getAllLanes(){
		return AllLanes;
	}
	public void setAllLanes(ArrayList <FWL> lanes){
		AllLanes=lanes;
	}
}
