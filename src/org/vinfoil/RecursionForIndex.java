package org.vinfoil;

import java.util.*;

public class RecursionForIndex {

	public ArrayList <Group> LoadingGroups(ArrayList <Float> distance){
		ArrayList <Group> lista=new ArrayList <Group> ();
		ArrayList <Integer> index=new ArrayList <Integer> ();
		
		int j=-1;
		int k=0;
		float a=0;
		
		while(j!=distance.size()-1){
			j++;
			if(distance.get(j)>0.0000001){
				
				a=distance.get(j);
				distance.remove(j);
				distance.add(j, (float) 0);
				index.add(j);
				for(int i=0;i<distance.size();i++){
					if(Math.abs(distance.get(i)-a)<0.000001){
						index.add(i);
						distance.remove(i);
						distance.add(i, (float) 0);
					}
				}
				Group element=new Group ();
				element.setDistance(a);
				element.setIndices(index);
				lista.add(element);
				
			}
			//System.out.println(lista.size() + " size of lista");
			index = new ArrayList <Integer>();
			
		}
		
		return lista;
	}
}
