package org.vinfoil;

import java.util.*;

public class SetOfPatternsForALane {
	
	public SetOfPatternsForALane(){
		
	}
	//returns set of patterns for one lane
	public Patterns PatternsForOneLane(Patterns intPatterns, FWL lane){
		Patterns shapesTemp=new Patterns ();
		ArrayList <Parallelogram> parTemp= new ArrayList <Parallelogram> ();
		ArrayList <Rectangel> recTemp=new ArrayList <Rectangel> ();
		ArrayList <Triangel> triTemp=new ArrayList <Triangel> ();
		Parallelogram PARtemp=new Parallelogram ();
		Rectangel Rtemp= new Rectangel ();
		Triangel Ttemp=new Triangel ();
		
		
		
		for(int i=0;i<intPatterns.getParallelograms().size();i++){
			PARtemp=intPatterns.getParallelograms().get(i);
		if((lane.getStart()<=PARtemp.getX11())&&(lane.getStart()<=PARtemp.getX12())&&(lane.getStart()<=PARtemp.getX13())&&(lane.getStart()<=PARtemp.getX14())&&(PARtemp.getX11()<=lane.getEnd())&&(PARtemp.getX12()<=lane.getEnd())&&(PARtemp.getX13()<=lane.getEnd())&&(PARtemp.getX14()<=lane.getEnd()))
			parTemp.add(PARtemp);
		}
		shapesTemp.setParalleorgrams(parTemp);
		for(int i=0;i<intPatterns.getRectangels().size();i++){
			Rtemp=intPatterns.getRectangels().get(i);
			if((lane.getStart()<=Rtemp.getX11())&&(lane.getStart()<=Rtemp.getX12())&&(Rtemp.getX11()<=lane.getEnd())&&(Rtemp.getX12()<=lane.getEnd()))
				recTemp.add(Rtemp);
			
		}
		shapesTemp.setRectangels(recTemp);
		for(int i=0;i<intPatterns.getTriangels().size();i++){
			Ttemp=intPatterns.getTriangels().get(i);
			if((lane.getStart()<=Ttemp.getX11())&&(lane.getStart()<=Ttemp.getX12())&&(lane.getStart()<=Ttemp.getX13())&&(Ttemp.getX11()<=lane.getEnd())&&(Ttemp.getX12()<=lane.getEnd())&&(Ttemp.getX13()<=lane.getEnd()))
			triTemp.add(Ttemp);
		}
		shapesTemp.setTriangels(triTemp);
		
		return shapesTemp;
	}

}
