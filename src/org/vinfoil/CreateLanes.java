package org.vinfoil;

public class CreateLanes {

	public CreateLanes(){
		
	}
	public FWL CreateLaneForAParalelogram(Parallelogram P, float CPC){
		//Creates a lane for a paralelogram
		FWL lane=new FWL();
		float a=0,b=0;
		a=Math.min(P.getX11(), P.getX12());
		b=Math.max(P.getX13(), P.getX14());
		lane.setStart(a-5);
		lane.setEnd(b+5);
		lane.setWidth(lane.getEnd()-lane.getStart());
		lane.setLength(CPC);
		return lane;
		
	}
	public FWL CreateLaneForATriangel(Triangel T,float CPC){
		//Creates  a lane for a triangle
		FWL lane=new FWL();
		float a=0,b=0;
		a=Math.min(T.getX11(), T.getX12());
		a=Math.min(a, T.getX13());
		b=Math.max(T.getX11(), T.getX12());
		b=Math.max(b, T.getX13());
		lane.setStart(a-5);
		lane.setEnd(b+5);
		lane.setWidth(lane.getEnd()-lane.getStart());
		lane.setLength(CPC);
		
		return lane;
		
	}
	public FWL CreateLaneForARectangel(Rectangel R,float CPC){
		//Creates  a lane for a rectangle
		FWL lane=new FWL();
		float a=0,b=0;
		a=Math.min(R.getX11(), R.getX12());
		
		b=Math.max(R.getX11(), R.getX12());
	
		lane.setStart(a-5);
		lane.setEnd(b+5);
		lane.setWidth(lane.getEnd()-lane.getStart());
		lane.setLength(CPC);
		
		return lane;
		
	}
	//this procedure will return the first lane as an output if there is no overlapping between the lanes
	public FWL CreateNewLaneIfOverlap(FWL lane1, FWL lane2){
		FWL lane=new FWL();
		Overlaping o=new Overlaping ();
		if(o.CheckOverlapingOfTwoLanes(lane1, lane2)==true){
			if(lane1.getStart()>=lane2.getStart()){
				lane.setStart(lane2.getStart());
				if(lane1.getEnd()>=lane2.getEnd()){
					lane.setEnd(lane1.getEnd());
					lane.setWidth(lane.getEnd()-lane.getStart());
				}
				else{
					lane.setEnd(lane2.getEnd());
					lane.setWidth(lane.getEnd()-lane.getStart());
				}
			}
			else
			{
				lane.setStart(lane1.getStart());
				if(lane1.getEnd()>=lane2.getEnd()){
					lane.setEnd(lane1.getEnd());
					lane.setWidth(lane.getEnd()-lane.getStart());
				}
				else{
					lane.setEnd(lane2.getEnd());
					lane.setWidth(lane.getEnd()-lane.getStart());
				}
			}
				
		}
		else lane=lane1;
		lane.setLength(lane1.getLength());
		return lane;
	}
}
