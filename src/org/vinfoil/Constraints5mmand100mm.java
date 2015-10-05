package org.vinfoil;

import java.util.ArrayList;


public class Constraints5mmand100mm {
public ArrayList <FWL> LaneConstraints(ArrayList <FWL> S0, float paperwidth, float offset, int NK ){
		
		ArrayList <FWL> S=new ArrayList <FWL> ();
		float dis1=0, dis2=0, m=0;
		float extension=0,ex=0;
		ArrayList <Float> dist=new ArrayList <Float> ();
		ArrayList <Float> dist1=new ArrayList <Float> ();
		int j=0,k=0;
		ArrayList <Integer> indexOfDis=new ArrayList <Integer> ();
		FWL a=new FWL ();
		FWL b=new FWL ();
		FWL c=new FWL ();
		FWL d=new FWL ();
		Extending e=new Extending ();
		boolean t;
		
		S=S0;
		for(int i=0;i<S.size()-1;i++){
			a=S.get(i);
			b=S.get(i+1);
			m=b.getStart()-a.getEnd();
			dist.add(m);
			if(dist.get(i)<5){
				indexOfDis.add(j);
				j++;
			}
		}
		//sada mi ne treba sredjena kasnije
		
		if(indexOfDis.size()!=0){//first constraint is violated
			for(int i=0;i<indexOfDis.size();i++){
				j=indexOfDis.get(i);
				a=S.get(j);
				b=S.get(j+1);
				a=c.MergingOfTwoLanes(a, b);
				S.remove(j+1);
				S.remove(j);
				S.add(j, a);
				
			}
			
		}
		
		//first constraint is not violated any more
		for(int i=0;i<S.size();i++){
			a=S.get(i);
			if(a.getWidth()<100){
				t=e.CheckIfLaneCanBeExtended(S, a, paperwidth,offset);
				//System.out.println(t);
				if(t==true){
					S=e.ExtendingOneLane(S, a, paperwidth,offset);
					//System.out.println("t");
					
				}
				else{//the extension is not possible it means that some merging is needed
					//System.out.println("trghjk2");
					if(i==0){//first lane
						//System.out.println("trghjk3");
						b=S.get(i+1);
						dis1=a.getStart();
						dis2=b.getStart()-a.getEnd();
						extension=100-a.getWidth();
						if((dis2<extension/2+5)&&(dis1+dis2>extension)){
							ex=extension/2-dis2;
							if(ex>Math.round(ex))
								ex=Math.round(ex)+1;
							else ex=Math.round(ex);
							a.setStart(offset+ex);
							a=c.MergingOfTwoLanes(a, b);
							S.remove(i+1);
							S.remove(i);
							S.add(i, a);//kad postavis na pocetak rastojanje izmedju pocetka i kraja ce biti 0 i uvek ce biti manje
						}else{//normal merging
							a.setStart(offset);
							a=c.MergingOfTwoLanes(a, b);
							S.remove(i+1);
							S.remove(i);
							S.add(i, a);//ovde si stala
						} 
					}else if(i==S.size()-1){//last lane
						//System.out.println("trghjk");
						b=S.get(i-1);
						dis1=a.getStart()-b.getEnd();
						dis2=paperwidth-a.getEnd();
						if((dis1<extension/2+5)&&(dis1+dis2>extension)){
							ex=extension-dis1;
							if(ex>Math.round(ex))
								ex=Math.round(ex)+1;
							else ex=Math.round(ex);
							a.setEnd(paperwidth-ex);
							a=c.MergingOfTwoLanes(a, b);
							S.remove(i);
							S.remove(i-1);
							S.add(i-1, a);
						}else {
							//System.out.println("trghjk1");
							a=c.MergingOfTwoLanes(a, b);
							S.remove(i);
							S.remove(i-1);
							S.add(i-1, a);
						}
					}else{//in the midlle lane; it doesn't work if you have less then 3 lanes
						//System.out.println("trghjk4");		
					b=S.get(i-1);
					c=S.get(i+1);
					a=d.MergingOfTwoLanes(a, c);
					a=d.MergingOfTwoLanes(a, b);
					S.remove(i+1);
					S.remove(i);
					S.remove(i-1);
					S.add(i-1, a);
					
					
						
					}
					return LaneConstraints(S,paperwidth,offset,NK);
				}
				
			}
			
		}
		//the second constraint is not violated
		
		return S;
	}


}
