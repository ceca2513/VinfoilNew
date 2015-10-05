package org.vinfoil;

import java.util.*;
public class FWL {
	
	private float Width;
	private float Length;
	private float Start;
	private float End;
	
	
	public float getWidth(){
		return Width;
	}
	public void setWidth(float width){
		Width=width;
	}
	public float getLength(){
		return Length;
	}
	public void setLength(float length){
		Length=length;
	}
	public float getStart(){
		return Start;
	}
	public void setStart(float start){
		Start=start;
	}
	public float getEnd(){
		return End;
	}
	public void setEnd(float end){
		End=end;
	}
	public FWL(){
		
	}
	//it is understood that one lane is not inside other; it works when they are overlapping 
	public FWL MergingOfTwoLanes(FWL lane1, FWL lane2){
		FWL lane=new FWL();
		//float w;
		if(lane1.Start<lane2.Start){
		lane.setStart(lane1.Start);
		lane.getStart();
		lane.setEnd(lane2.End);
		lane.getEnd();
		//w=lane2.End-lane1.Start;
		lane.setWidth(lane2.End-lane1.Start);
		lane.getWidth();
		lane.setLength(lane1.Length);
		lane.getLength();}
		else{
			lane.setStart(lane2.Start);
			lane.setEnd(lane1.End);
			lane.setWidth(lane.End-lane.Start);
			lane.setLength(lane1.Length);
		}
		return lane;
		
	}
	public FWL JoiningOfTwoLanes(FWL lane1, FWL lane2){
		FWL lane=new FWL();
		//float w;
		if(lane1.Start<lane2.Start){
		lane.setStart(lane1.Start);
		lane.setWidth(lane1.getWidth()+lane2.getWidth()+20);
		lane.setEnd(lane.getStart()+lane.getWidth());
		lane.setLength(lane1.Length);
		}
		else{
			lane.setStart(lane2.Start);
			lane.setWidth(lane1.getWidth()+lane2.getWidth()+20);
			lane.setEnd(lane.getStart()+lane.getWidth());
			lane.setLength(lane1.Length);
		}
		return lane;
		
	}
	//lane1 is Start lane 2is Middle lane3 is End
	public FWL JoiningOfThreeLanes(FWL lane1, FWL lane2, FWL lane3){
		FWL lane=new FWL();
		FWL laneStart=new FWL ();
		FWL laneMiddle=new FWL ();
		FWL laneEnd=new FWL ();
		float min=Math.min(lane1.getStart(), lane2.getStart());
		min=Math.min(min, lane3.getStart());
		float max=Math.max(lane1.getStart(), lane2.getStart());
		max=Math.max(max, lane3.getStart());
		if(lane1.getStart()==min){
			if(lane2.getStart()==max){
				laneStart=lane1;
				laneMiddle=lane3;
				laneEnd=lane2;
			}else{
				laneStart=lane1;
				laneMiddle=lane2;
				laneEnd=lane2;
			}
		}else if(lane2.getStart()==min){
			if(lane1.getStart()==max){
				laneStart=lane2;
				laneMiddle=lane3;
				laneEnd=lane1;
			}else{
				laneStart=lane2;
				laneMiddle=lane1;
				laneEnd=lane3;
			}
		}else{
			if(lane1.getStart()==max){
				laneStart=lane3;
				laneMiddle=lane2;
				laneEnd=lane1;
			}else{
				laneStart=lane3;
				laneMiddle=lane1;
				laneEnd=lane2;
			}
		}
		lane.setStart(laneStart.getStart());
		lane.setWidth(laneStart.getWidth()+laneMiddle.getWidth()+laneEnd.getWidth()+40);
		lane.setEnd(lane.getStart()+lane.getWidth());
		lane.setLength(lane1.getLength());
		return lane;
		
	}
	
	public ArrayList <FWL> MerginOfConsecutiveLanes(ArrayList <FWL> lanes, int numOfLanes, int indexStart){
		ArrayList <FWL> temp=new ArrayList <FWL> ();
		FWL lane1=new FWL ();
		FWL lane2=new FWL ();
		FWL lane3=new FWL ();
		int k=indexStart+numOfLanes;//index sa kojim treba da se zavrsi
		temp=lanes;
		if(numOfLanes==0){
			return temp;
			
		}else{
		if(k>lanes.size()){
			System.out.println("WARNING the wanted merging is out of bound!");
			temp=null;
		}
		else{
			while(k!=indexStart){
				k--;
				lane1=lanes.get(indexStart);
				lane2=lanes.get(indexStart+1);
				lane3=this.MergingOfTwoLanes(lane1, lane2);
				temp.remove(indexStart+1);
				temp.remove(indexStart);
				temp.add(indexStart, lane3);
				int num=numOfLanes-2;
				return MerginOfConsecutiveLanes(temp, num, indexStart);
		}	
		}
		
		return temp;
	}
	}
	//public org.vinfoil.FWL Joining()

}
