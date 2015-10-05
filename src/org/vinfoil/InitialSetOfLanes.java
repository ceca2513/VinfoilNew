package org.vinfoil;

import java.util.*;
public class InitialSetOfLanes {

	public InitialSetOfLanes(){
		
	}
	@SuppressWarnings("unchecked")
	public ArrayList <FWL> InitialS0(ArrayList <Parallelogram> parallelograms,ArrayList <Triangel> triangels, ArrayList <Rectangel>rectangels, float CPC){
		ArrayList <FWL> S=new ArrayList <FWL> ();
		CreateLanes lanes=new CreateLanes ();
		Parallelogram p=new Parallelogram ();
		Triangel t=new Triangel ();
		Rectangel r=new Rectangel ();
		FWL f=new FWL ();
		
		for(int i=0;i<parallelograms.size();i++){
			p=parallelograms.get(i);
			f=lanes.CreateLaneForAParalelogram(p,CPC);
			S.add(f);
			//S.containsAll(lanes.CreateLaneForAParalelogram(p));
		}
		for(int i=0;i<triangels.size();i++){
			t=triangels.get(i);
			f=lanes.CreateLaneForATriangel(t, CPC);
			S.add(f);
		
		}
		for(int i=0;i<rectangels.size();i++){
			r=rectangels.get(i);
			f=lanes.CreateLaneForARectangel(r,CPC);
			S.add(f);
			
		}
		//S0 is now initialized with all possible lanes
		FWL1 c=new FWL1 ();
		Collections.sort(S, c);
		
		return S;
	}
	//t should be false at the beginning 
	public ArrayList <FWL> CreatingS0(ArrayList <FWL> S0, boolean t,float CPC){
		ArrayList <FWL> S=new ArrayList <FWL> ();
		FWL a=new FWL ();
		FWL b=new FWL ();
		Overlaping c=new Overlaping ();
		CreateLanes d=new CreateLanes ();
		int j=0;
		
		 t=false;
		 S=S0;
		//this doesn't work if you have only one lane!!!
		for(int i=0;i<S.size()-1;i++){
			a=S.get(i);
			b=S.get(i+1);
			t=c.CheckOverlapingOfTwoLanes(a, b);
			//System.out.println(t);
			if(t==true){
				a=d.CreateNewLaneIfOverlap(a, b);
				S.remove(i+1);
				S.remove(i);
				j++;
				S.add(i, a);
				//System.out.println("new lane starts"+a.Start);
				//System.out.println("new lane ends"+a.End);
				//System.out.println("j "+j);
				FWL1 cc=new FWL1 ();
				Collections.sort(S, cc);
				return CreatingS0(S,false, CPC);
				
			}
			
		}
		
		return S;
	}
}
