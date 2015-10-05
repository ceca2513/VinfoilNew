package org.vinfoil;

import java.util.*;

public class KnivesConstraints {
	
	public KnivesConstraints(){
		
	}
	//this works if lanes do not overlap or coincide or one is inside other, other wise the result is crap!!!
	//need to protect it from that!!!!!
	public ArrayList <FWL> RoundingLanaesToResolution(ArrayList <FWL> lanes, float paperwidth, float offset){
		ArrayList <FWL> a=new ArrayList <FWL> ();
		FWL b=new FWL ();
		FWL d=new FWL ();
		FWL f=new FWL ();
		FWL g=new FWL ();
		Extending e=new Extending ();
		float c=0;
		boolean t;
		
		
		a.addAll(lanes);
		if(a.size()==1){//one lane
			b=a.get(0);
			if(b.getWidth()%10!=0){
				c=10-b.getWidth()%10;
				t=e.CheckIfLaneCanBeExtended2(a, b, paperwidth, offset, c);
				if(t==true)
					a=e.ExtendingOneLane1(a, b, paperwidth, offset, c);
				else 
					System.out.println("This rounding extension is not possible");
			}
		}
		else{
		for(int i=0;i<a.size();i++){
			b=a.get(i);
			//System.out.println("koliko puta ulazis u for petlju "+i);
			if(b.getWidth()%10!=0){
				c=10-b.getWidth()%10;
				//System.out.println(c);
				t=e.CheckIfLaneCanBeExtended2(a, b, paperwidth, offset, c);
				//System.out.println("moze da se ekstenduje "+t);
				if(t==true){
					a=e.ExtendingOneLane1(a, b, paperwidth, offset, c);
					return RoundingLanaesToResolution(a,paperwidth, offset);
				}
				else {//we can not extend the lane so we need to merge it
					if(i==0){//first lane
					d.MergingOfTwoLanes(b, a.get(i+1));
					a.remove(i+1);
					a.remove(i);
					a.add(i, d);
					return RoundingLanaesToResolution(a,paperwidth, offset);
					}else if(i==a.size()-1){//last lane
					d.MergingOfTwoLanes(b, a.get(i-1));
					a.remove(i);
					a.remove(i-1);
					a.add(i-1, d);
					return RoundingLanaesToResolution(a,paperwidth, offset);
					}else{//in between lane
						d=a.get(i-1);
						f=a.get(i+1);
						g=g.MergingOfTwoLanes(b, d);
						g=g.MergingOfTwoLanes(g, f);
						a.remove(i+1);
						a.remove(i);
						a.remove(i-1);
						a.add(i-1, g);
						return RoundingLanaesToResolution(a,paperwidth, offset);
					}

				}
			}
		}
	}
		
		return a;
	}

}
