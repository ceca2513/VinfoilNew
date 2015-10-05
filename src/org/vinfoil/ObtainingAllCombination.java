package org.vinfoil;

import java.util.*;
public class ObtainingAllCombination {
	public ObtainingAllCombination(){
		
	}
	//necu da posmatram jedno elementne kombinacije jer meni takve ne trebaju i necu da ih imam
	//namestila sam ove kombinacije da odgovaraju mom slucaju
	public ArrayList <int []> Combinations(int k, int max){
		ArrayList <int []> templist=new ArrayList <int []>();
		int [] a=new int [k];
		if (k==2){
			for(int i=2;i<max+1;i++){
				
				for(int j=2;j<max;j++){
					a[0]=i;
					a[1]=j;
					templist.add(a);
					 a=new int [k];
				}
				
			}
			
		}else{
			ArrayList <int []> templist1=new ArrayList <int []>();
			templist1=this.Combinations(k-1, max);
			int [] b=new int[k];
			int [] c=new int[k-1];
			for(int i=2;i<max;i++){
				
				int l=0;
				while(l<templist1.size()){
					c=templist1.get(l);
					for(int j=1;j<k;j++){
						b[0]=i;
						b[j]=c[j-1];
						
					}
					templist.add(b);
					b=new int[k];
					l++;
				}
			}
		}
		return templist;
	}
	public int Sum(int [] arr, int length){
		int sum=0;
		for(int i=0;i<length;i++){
			sum+=arr[i];
		}
		return sum;
	}
	public ArrayList <Combination> EliminationOfRepetition(ArrayList <Combination> oneComb){
		ArrayList <Combination> templist=new ArrayList <Combination>();
		templist=oneComb;
		Combination comb=new Combination ();
		Combination comb1=new Combination ();
		int i=0;int i1=0;
		while(i<templist.size()){
			while(i1<templist.size()){
				if(i!=i1){
			comb=templist.get(i);
			comb1=templist.get(i1);
			int [] a1=comb.getP();
			int [] a2=comb1.getP();
			ArrayList <Integer> arr1=new ArrayList <Integer>();
			ArrayList <Integer> arr2=new ArrayList <Integer>();
			for(int j=0;j<20;j++){
				arr1.add(a1[j]);
				arr2.add(a2[j]);
			}
			//sad si napunila oba niza
			int k=0;
			while (k<20){
				for(int f=0;f<arr2.size();f++){
					if(arr1.get(k)==arr2.get(f)){
						arr2.remove(f);	
					}
				}
				k++;
			}
			//sada proveravas da li ima neki element na arr2 ako nema onda su ova dva niza ista i eleiminises jedan
			if(arr2.isEmpty()){
				templist.remove(i);
				templist=this.EliminationOfRepetition(templist);
			}
				}
				i1++;
			}
			i1=0;
			i++;
		}//
		
		return templist;
	}
	public ArrayList <Combination> ListOfComb(int numOfLanes, int numOfNeededLanes){
		//ne zaboravi da moras da podelis na dva slucaja n-k<k i n-k>=k
		//moras da iscistis simetricne kombinacije
		//!!!!!!!!!!
		ArrayList <Combination> templist=new ArrayList <Combination>();
		Combination comb=new Combination();
		int max=numOfLanes-numOfNeededLanes;
		int tlim=0;
		if(numOfNeededLanes>=max)
		tlim=max-1;
		else
			tlim=max-2;
		int [] y=new int [20];
		y[0]=max+1;
		for(int i=1;i<20;i++)
			y[i]=0;
		comb.setP(y);
		templist.add(comb);
		comb=new Combination();
		if(numOfNeededLanes>=max){
			int [] y1=new int [20];
			for(int i=0;i<max;i++)
				y1[i]=2;
			for(int i=max;i<20;i++)
				y1[i]=0;
			comb.setP(y1);
			templist.add(comb);
			comb=new Combination();
		}
		for(int i=2;i<=tlim;i++){
			ArrayList <Combination> templist1=new ArrayList <Combination>();
			ArrayList <int []> elements1=this.Combinations(i, max);
			int j=0;
			while(j<elements1.size()){
				int h=max+i;
				int d=this.Sum(elements1.get(j), i);
				if(h==d){
					int [] g1=elements1.get(j);
					int [] g2=new int [20];
					//punim kombinaciju
					for(int k=0;k<20;k++){
						
						if(k<i){
							g2[k]=g1[k];
						}else{
							g2[k]=0;
						}
					}
					comb.setP(g2);
					templist1.add(comb);
					comb=new Combination();
				}
				j++;
			}
			templist1=this.EliminationOfRepetition(templist1);
			templist.addAll(templist1);
			
		}
		
		return templist;
	}

}
