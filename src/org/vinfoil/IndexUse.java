package org.vinfoil;

import java.util.*;

public class IndexUse {//treba da promenis inedexe da pocinju sa +1;
	//treba da odradis nesto sa merged promenljivom

	public IndexUse(){
		
	}
	public static ArrayList <FWL> WhichLaneToMerge ( ArrayList <FWL> lanes, int numOfLanesNeeded){
		ArrayList <Float> distance=new ArrayList <Float> ();
		ArrayList <Integer> Itemp=new ArrayList <Integer> ();
		ArrayList <Group> lista=new ArrayList <Group> ();
		ArrayList <FWL> lanesTemp=new ArrayList <FWL> ();
		FWL laneTemp1=new FWL ();
		FWL laneTemp2=new FWL ();
		FWL laneTemp3=new FWL ();
		float temp=0;
		int div,mod;
		int o=0;
		RecursionForIndex recur=new RecursionForIndex ();
		lanesTemp=lanes;
		if(lanesTemp.size()==numOfLanesNeeded)
			return lanesTemp;
		else{
		for(int i=0;i<lanes.size()-1;i++){
			laneTemp1=lanes.get(i);
			laneTemp2=lanes.get(i+1);
			temp=laneTemp2.getStart()-laneTemp1.getEnd();
			distance.add(temp);
		}
		lista=recur.LoadingGroups(distance);//kada ovo uradis izbrise ti se sve sa distance!Posto ti ne treba za dalje onda je sve jedno
		
		int numLan=lista.size();
		
		if(lista.size()==1){//all distances are the same
			div=lanes.size()/numOfLanesNeeded;//almost the exact number of lanes that need to be merged
			mod=lanes.size()%numOfLanesNeeded;//
			if(mod==0){//equal number of Lanes
				int k=0;
				int d=0;
				int y=0;
				int z=lanes.size();
				//lanesTemp=lanes;
				while(z-k*div>=numOfLanesNeeded){
					lanesTemp=laneTemp1.MerginOfConsecutiveLanes(lanesTemp, div, k);
					
					k++;
					 y=z-k*div;
					//return lanesTemp
				}
			}else{
				int m=lanes.size();
				int k=0;
				int d=0;
				int z=lanes.size();
				//lanesTemp=lanes;
				while(z-k*div>numOfLanesNeeded){
					if(mod!=0){
						mod--;
						//merge div consecutive lanes
						lanesTemp=laneTemp1.MerginOfConsecutiveLanes(lanesTemp, div+1, k);
						k++;
						
					}else{
						lanesTemp=laneTemp1.MerginOfConsecutiveLanes(lanesTemp, div, k);
						k++;
				}
				}
				
			}
		}else if(numLan==(lanes.size()-1)){//all distances are different
			Comp c=new Comp ();
			Collections.sort(lista, c);//the list is sorted in increasing order
			ArrayList <Integer> nj=new ArrayList <Integer> ();
			int k=lanes.size()-numOfLanesNeeded;//number of merging that needs to be performed
			Group g10=new Group ();
			//so we need to destroy k distances
			int j=-1;int z=0;
			//int o=0;
			g10=lista.get(0);
			while((k!=0)&&(j!=(g10.getIndices().size()-1))){
				g10=lista.get(z);
				j++;
				int s2=g10.getIndices().get(j);
					laneTemp1=lanesTemp.get(s2);
					laneTemp2=lanesTemp.get(s2+1);
					laneTemp3=laneTemp3.MergingOfTwoLanes(laneTemp1, laneTemp2);
					lanesTemp.remove(s2+1);
					lanesTemp.remove(s2);
					lanesTemp.add(s2, laneTemp3);				
					k--;
					z++;
					return WhichLaneToMerge ( lanesTemp, numOfLanesNeeded);
				
				
			}
			/*while(k!=0){
				k--;
			for(int i=0;i<distance.size();i++){//ne mozes da radis sa dstances ovde one su sada izbrisane mozes da radis samo sa lista
				if(Math.abs(distance.get(i)-lista.get(k).Distance)<0.000001)
					nj.add(i);//index of the distance between the lanes that need to be merged
			}
			}
			
			//now I have indices I need to merge this lanes but some of them can be consecutive lanes 3 in a row or more
			//lanesTemp=lanes;
			int l=-1;
			
			while(l!=nj.size()-1){
				l++;
				int s1=nj.get(l);
			 laneTemp1=lanesTemp.get(s1);
			 laneTemp2=lanesTemp.get(s1+1);
			 laneTemp3=laneTemp3.MergingOfTwoLanes(laneTemp1, laneTemp2);
			 lanesTemp.remove(s1+1);
			 lanesTemp.remove(s1);
			 lanesTemp.add(s1,laneTemp3);
			 return WhichLaneToMerge ( lanesTemp, numOfLanesNeeded);
			 
			}*/
		}else{
			Comp c=new Comp ();
			Collections.sort(lista, c);//the list is sorted in increasing order
			int t=0;int t1=-1;
			Group g=new Group ();
			//lanesTemp=lanes;
			int k=lanes.size()-numOfLanesNeeded;//number of merging that is needed
			while(t1!=lista.size()-1){
				g=lista.get(t);
				if(k>g.getIndices().size()){
					k-=g.getIndices().size();
					t++;
				}
					t1++;	
				
					
			}//t je grupa u kojoj mozda mozemo da preskocimo neki index
			if(t==0){
				Group g1=new Group ();
				g1=lista.get(0);
				ArrayList <Integer> indexSkip=new ArrayList <Integer> ();
				int k1=lanes.size()-numOfLanesNeeded;//this number is smaller then number of elements in first group
				if(g1.getIndices().size()>3){
				for(int i=0;i<g1.getIndices().size()-2;i++){
					if((g1.getIndices().get(i)==g1.getIndices().get(i+1)-1)&&(g1.getIndices().get(i+1)==g1.getIndices().get(i+2)-1)){
						//merge i and i+1, but skip merging i+2
						indexSkip.add(i+2);
					}
				}
				if(indexSkip.size()==0){//it doesn't meter which you merge, merge first k1
					int j=-1;
					//int o=0;
					while((k1!=0)&&(j!=(g1.getIndices().size()-1))){
						j++;
						int s2=g1.getIndices().get(j);
							laneTemp1=lanesTemp.get(s2);
							laneTemp2=lanesTemp.get(s2+1);
							laneTemp3=laneTemp3.MergingOfTwoLanes(laneTemp1, laneTemp2);
							lanesTemp.remove(s2+1);
							lanesTemp.remove(s2);
							lanesTemp.add(s2, laneTemp3);				
							k1--;
							return WhichLaneToMerge ( lanesTemp, numOfLanesNeeded);
						
						
					}
				}else{//it meters which you merge
					int h=g1.getIndices().size()-k1;
					ArrayList <Integer> g2=new ArrayList <Integer> ();
					g2=g1.getIndices();
					int f=0;
					while(h!=0){
						for(int i=0;i<g2.size();i++){
							if(indexSkip.get(f)==g2.get(i))
								g2.remove(i);
						}
						h--;
					}
					int j=-1;
					//int o=0;
					while((k1!=0)&&(j!=(g1.getIndices().size()-1))){
						j++;
						int s3=g1.getIndices().get(j);
							laneTemp1=lanesTemp.get(s3);
							laneTemp2=lanesTemp.get(s3+1);
							laneTemp3=laneTemp3.MergingOfTwoLanes(laneTemp1, laneTemp2);
							lanesTemp.remove(s3+1);
							lanesTemp.remove(s3);
							lanesTemp.add(s3, laneTemp3);
							k1--;
							return WhichLaneToMerge ( lanesTemp, numOfLanesNeeded);
						
						
					}
				}
				}else{
					//the number of lanes is <3
					int j=-1;
					//int o=0;
					while((k1!=0)&&(j!=(g1.getIndices().size()-1))){
						j++;
						int u=g1.getIndices().get(j);
							laneTemp1=lanesTemp.get(u);
							laneTemp2=lanesTemp.get(u+1);
							laneTemp3=laneTemp3.MergingOfTwoLanes(laneTemp1, laneTemp2);
							lanesTemp.remove(u+1);
							lanesTemp.remove(u);
							lanesTemp.add(u, laneTemp3);
							k1--;
							return WhichLaneToMerge ( lanesTemp, numOfLanesNeeded);
						
						
					}
				}
				
			}else{//it is not the first group, that means we merge all the lanes form the previous groups and in the 
				//t group we search for a space to skip 
				int r=0;int r1=-1;
				Group g1=new Group ();
				int b=0;
				while(r!=t){
					g1=lista.get(r);
					b+=g1.getIndices().size();
					r++;
					while(r1!=g1.getIndices().size()-1){
						r1++;
							int s=g1.getIndices().get(r1);
							laneTemp1=lanesTemp.get(s);
							laneTemp2=lanesTemp.get(s+1);
							laneTemp3=laneTemp3.MergingOfTwoLanes(laneTemp1, laneTemp2);
							lanesTemp.remove(s+1);
							lanesTemp.remove(s);
							lanesTemp.add(s, laneTemp3);
							return WhichLaneToMerge ( lanesTemp, numOfLanesNeeded);
						
					}
				}//e sad je ostala poslednja grupa
				int k5=lanes.size()-numOfLanesNeeded;//number of merging that is needed
				int k6=k5-b;//number of merging for this specific group
				Group g4=new Group ();
				g4=lista.get(t);
				ArrayList <Integer> indexSkip=new ArrayList <Integer> ();
				if(g4.getIndices().size()>3){
					for(int i=0;i<g4.getIndices().size()-2;i++){
						if((g4.getIndices().get(i)==g4.getIndices().get(i+1)-1)&&(g4.getIndices().get(i+1)==g4.getIndices().get(i+2)-1)){
							//merge i and i+1, but skip merging i+2
							indexSkip.add(i+2);
						}
					}
					if(indexSkip.size()==0){//it doesn't meter which you merge, merge first k1
						int j=-1;
						//int o=0;
						while((k6!=0)&&(j!=(g4.getIndices().size()-1))){
							j++;
							int s4=g4.getIndices().get(j);
								laneTemp1=lanesTemp.get(s4);
								laneTemp2=lanesTemp.get(s4+1);
								laneTemp3=laneTemp3.MergingOfTwoLanes(laneTemp1, laneTemp2);
								lanesTemp.remove(s4+1);
								lanesTemp.remove(s4);
								lanesTemp.add(s4, laneTemp3);
								k6--;
								return WhichLaneToMerge ( lanesTemp, numOfLanesNeeded);
							
						}
					}else{//it meters which you merge
						int h=g4.getIndices().size()-k6;
						ArrayList <Integer> g2=new ArrayList <Integer> ();
						g2=g4.getIndices();
						int f=0;
						while(h!=0){
							for(int i=0;i<g2.size();i++){
								if(indexSkip.get(f)==g2.get(i))
									g2.remove(i);
							}
							h--;
						}
						int j=-1;
					//	int o=0;
						while((k6!=0)&&(j!=(g4.getIndices().size()-1))){
							j++;
							int s5=g4.getIndices().get(j);
								laneTemp1=lanesTemp.get(s5);
								laneTemp2=lanesTemp.get(s5+1);
								laneTemp3=laneTemp3.MergingOfTwoLanes(laneTemp1, laneTemp2);
								lanesTemp.remove(s5+1);
								lanesTemp.remove(s5);
								lanesTemp.add(s5, laneTemp3);
								k6--;
								return WhichLaneToMerge ( lanesTemp, numOfLanesNeeded);
							
							
						}
					}
				}else{
					//need to merge what you need to merge
					int j=-1;
				//	int o=0;
					while((k6!=0)&&(j!=(g4.getIndices().size()-1))){
						j++;
						int u1=g4.getIndices().get(j);
							laneTemp1=lanesTemp.get(u1);
							laneTemp2=lanesTemp.get(u1+1);
							laneTemp3=laneTemp3.MergingOfTwoLanes(laneTemp1, laneTemp2);
							lanesTemp.remove(u1+1);
							lanesTemp.remove(u1);
							lanesTemp.add(u1, laneTemp3);
							k6--;
							return WhichLaneToMerge ( lanesTemp, numOfLanesNeeded);
						
					}
					
				}
			}
		
		}
		
		return lanesTemp;
		
	}
	}
}
