package org.vinfoil;

import java.util.*;
public class ResultOfStrategy4 {
	private ArrayList <FWL> Lanes;
	private FWL MasterLaneA;
	private FWL SlaveLaneA;
	private FWL MasterLaneB;
	private FWL SlaveLaneB;
	private FWL MasterLane;
	private FWL SlaveLane1;
	private FWL SlaveLane2;
	private boolean RepassingOneOnOne;
	private boolean RepassingOneOnTwo;
	
	public ResultOfStrategy4(boolean r1, boolean r2){
		RepassingOneOnOne=false;
		RepassingOneOnTwo=false;	
	}
	public ArrayList <FWL> getLanes(){
		return Lanes;
	}
	public void setLanes(ArrayList <FWL> lanes){
		Lanes=lanes;
	}
	public FWL getMasterLaneA(){
		return MasterLaneA;
	}
	public void setMasterLaneA( FWL m){
		MasterLaneA=m;
	}
	public FWL getSlaveLaneA(){
		return SlaveLaneA;
	}
	public void setSlaveLaneA(FWL s){
		SlaveLaneA=s;
	}
	public FWL getMasterLaneB(){
		return MasterLaneB;
	}
	public void setMasterLaneB( FWL m){
		MasterLaneB=m;
	}
	public FWL getSlaveLaneB(){
		return SlaveLaneB;
	}
	public void setSlaveLaneB(FWL s){
		SlaveLaneB=s;
	}
	public FWL getMasterLane(){
		return MasterLane;
	}
	public void setMasterLane( FWL m){
		MasterLane=m;
	}
	public FWL getSlaveLane1(){
		return SlaveLane1;
	}
	public void setSlaveLane1(FWL s){
		SlaveLane1=s;
	}
	public FWL getSlaveLane2(){
		return SlaveLane2;
	}
	public void setSlaveLane2(FWL s){
		SlaveLane2=s;
	}
	public boolean getRepassingOneOnOne(){
		return RepassingOneOnOne;
	}
	public void setRepassingOneOnOne( boolean r){
		RepassingOneOnOne=r;
	}
	public boolean getRepassingOneOnTwo(){
		return RepassingOneOnTwo;
	}
	public void setRepassingOneOnTwo( boolean r){
		RepassingOneOnTwo=r;
	}
}
