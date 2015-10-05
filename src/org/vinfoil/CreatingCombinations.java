package org.vinfoil;

import java.util.*;
public class CreatingCombinations {
	public CreatingCombinations(){
		
	}
	//we put only elements from initial set; ustvari lakse ti je samo da preuzmes rezultat strategije 1 
	public ArrayList <FWL> CreatingSetsOfPossibleSolutionsOneElement(ArrayList <FWL> allLanes){
		ArrayList <FWL> templist=new ArrayList <FWL>();
		
		return templist;
	}
	public ArrayList <SetTwoElements> CreatingSetOfTwoElements(ArrayList <FWL> allLanes){
		ArrayList <SetTwoElements> templist=new ArrayList <SetTwoElements>();
		SetTwoElements temp=new SetTwoElements();
		ArrayList <FWL> alltemp1=new ArrayList <FWL>();
		ArrayList <FWL> alltemp2=new ArrayList <FWL>();
		alltemp1=allLanes;
		alltemp2=allLanes;
		Iterator <FWL> iterator1=alltemp1.iterator();
		Iterator <FWL> iterator2=alltemp2.iterator();
		int i=0;int j=0;
	       while (iterator1.hasNext()){
	    	   temp.setLane1(alltemp1.get(i));
	    	   alltemp2.remove(alltemp1.get(i));
	    	   i++;
	    	   while(iterator2.hasNext()){
	    		   temp.setLane2(alltemp2.get(j));
	    		   j++;
	    		   templist.add(temp);
	    	   }
	       }
		
		return templist;
	}
	public ArrayList <SetThreeElements> CreatingSetOfThreeElements(ArrayList <FWL> allLanes){
		ArrayList <SetThreeElements> templist=new ArrayList <SetThreeElements>();
		ArrayList <SetTwoElements> templist1=new ArrayList <SetTwoElements>();
		SetThreeElements tempthree=new SetThreeElements();
		templist1=this.CreatingSetOfTwoElements(allLanes);
		Iterator <SetTwoElements> iterator=templist1.iterator();
		FWL temp1=new FWL();
		FWL temp2=new FWL();
		FWL temp3=new FWL();
		int j=0;
		for(int i=0;i<allLanes.size();i++){
			while(iterator.hasNext()){
				temp1=templist1.get(j).getLane1();
				temp2=templist1.get(j).getLane2();
				temp3=allLanes.get(i);
				j++;
				if(temp3!=temp1&&temp3!=temp2){
					tempthree.setLane1(temp1);
					tempthree.setLane2(temp2);
					tempthree.setLane3(temp3);
					templist.add(tempthree);
				}	
			}
			
		}
		return templist;
	}
	public ArrayList <SetFourElements> CreatingSetOfFourElements(ArrayList <FWL> allLanes){
		ArrayList <SetFourElements> templist=new ArrayList <SetFourElements>();
		ArrayList <SetThreeElements> templist1=new ArrayList <SetThreeElements>();
		SetFourElements tempfour=new SetFourElements();
		templist1=this.CreatingSetOfThreeElements(allLanes);
		Iterator <SetThreeElements> iterator=templist1.iterator();
		FWL temp1=new FWL();
		FWL temp2=new FWL();
		FWL temp3=new FWL();
		FWL temp4=new FWL();
		int j=0;
		for(int i=0;i<allLanes.size();i++){
			while(iterator.hasNext()){
				temp1=templist1.get(j).getLane1();
				temp2=templist1.get(j).getLane2();
				temp3=templist1.get(j).getLane3();
				temp4=allLanes.get(i);
				j++;
				if(temp4!=temp1&&temp4!=temp2&&temp4!=temp3){
					tempfour.setLane1(temp1);
					tempfour.setLane2(temp2);
					tempfour.setLane3(temp3);
					tempfour.setLane4(temp4);
					templist.add(tempfour);
				}	
			}
		}
		return templist;
	}
	public ArrayList <SetFiveElements> CreatingSetOfFiveElements(ArrayList <FWL> allLanes){
		ArrayList <SetFiveElements> templist=new ArrayList <SetFiveElements>();
		ArrayList <SetFourElements> templist1=new ArrayList <SetFourElements>();
		SetFiveElements tempfive=new SetFiveElements();
		templist1=this.CreatingSetOfFourElements(allLanes);
		Iterator <SetFourElements> iterator=templist1.iterator();
		FWL temp1=new FWL();
		FWL temp2=new FWL();
		FWL temp3=new FWL();
		FWL temp4=new FWL();
		FWL temp5=new FWL();
		int j=0;
		for(int i=0;i<allLanes.size();i++){
			while(iterator.hasNext()){
				temp1=templist1.get(j).getLane1();
				temp2=templist1.get(j).getLane2();
				temp3=templist1.get(j).getLane3();
				temp4=templist1.get(j).getLane4();
				temp5=allLanes.get(i);
				j++;
				if(temp5!=temp1&&temp5!=temp2&&temp5!=temp3&&temp5!=temp4){
					tempfive.setLane1(temp1);
					tempfive.setLane2(temp2);
					tempfive.setLane3(temp3);
					tempfive.setLane4(temp4);
					tempfive.setLane5(temp5);
					templist.add(tempfive);
				}	
			}
		}
		return templist;
	}
	public ArrayList <SetSixElements> CreatingSetOfSixElements(ArrayList <FWL> allLanes){
		ArrayList <SetSixElements> templist=new ArrayList <SetSixElements>();
		ArrayList <SetFiveElements> templist1=new ArrayList <SetFiveElements>();
		SetSixElements tempsix=new SetSixElements();
		templist1=this.CreatingSetOfFiveElements(allLanes);
		Iterator <SetFiveElements> iterator=templist1.iterator();
		FWL temp1=new FWL();
		FWL temp2=new FWL();
		FWL temp3=new FWL();
		FWL temp4=new FWL();
		FWL temp5=new FWL();
		FWL temp6=new FWL();
		int j=0;
		for(int i=0;i<allLanes.size();i++){
			while(iterator.hasNext()){
				temp1=templist1.get(j).getLane1();
				temp2=templist1.get(j).getLane2();
				temp3=templist1.get(j).getLane3();
				temp4=templist1.get(j).getLane4();
				temp5=templist1.get(j).getLane5();
				temp6=allLanes.get(i);
				j++;
				if(temp6!=temp1&&temp6!=temp2&&temp6!=temp3&&temp6!=temp4&&temp6!=temp5){
					tempsix.setLane1(temp1);
					tempsix.setLane2(temp2);
					tempsix.setLane3(temp3);
					tempsix.setLane4(temp4);
					tempsix.setLane5(temp5);
					tempsix.setLane6(temp6);
					templist.add(tempsix);
				}	
			}
		}
		return templist;
	}
	public ArrayList <SetSevenElements> CreatingSetOfSevenElements(ArrayList <FWL> allLanes){
		ArrayList <SetSevenElements> templist=new ArrayList <SetSevenElements>();
		ArrayList <SetSixElements> templist1=new ArrayList <SetSixElements>();
		SetSevenElements tempsix=new SetSevenElements();
		templist1=this.CreatingSetOfSixElements(allLanes);
		Iterator <SetSixElements> iterator=templist1.iterator();
		FWL temp1=new FWL();
		FWL temp2=new FWL();
		FWL temp3=new FWL();
		FWL temp4=new FWL();
		FWL temp5=new FWL();
		FWL temp6=new FWL();
		FWL temp7=new FWL();
		int j=0;
		for(int i=0;i<allLanes.size();i++){
			while(iterator.hasNext()){
				temp1=templist1.get(j).getLane1();
				temp2=templist1.get(j).getLane2();
				temp3=templist1.get(j).getLane3();
				temp4=templist1.get(j).getLane4();
				temp5=templist1.get(j).getLane5();
				temp6=templist1.get(j).getLane6();
				temp7=allLanes.get(i);
				j++;
				if(temp7!=temp1&&temp7!=temp2&&temp7!=temp3&&temp7!=temp4&&temp7!=temp5&&temp7!=temp6){
					tempsix.setLane1(temp1);
					tempsix.setLane2(temp2);
					tempsix.setLane3(temp3);
					tempsix.setLane4(temp4);
					tempsix.setLane5(temp5);
					tempsix.setLane6(temp6);
					tempsix.setLane7(temp7);
					templist.add(tempsix);
				}	
			}
		}
		return templist;
	}
	public ArrayList <SetEightElements> CreatingSetOfEightElements(ArrayList <FWL> allLanes){
		ArrayList <SetEightElements> templist=new ArrayList <SetEightElements>();
		ArrayList <SetSevenElements> templist1=new ArrayList <SetSevenElements>();
		SetEightElements tempsix=new SetEightElements();
		templist1=this.CreatingSetOfSevenElements(allLanes);
		Iterator <SetSevenElements> iterator=templist1.iterator();
		FWL temp1=new FWL();
		FWL temp2=new FWL();
		FWL temp3=new FWL();
		FWL temp4=new FWL();
		FWL temp5=new FWL();
		FWL temp6=new FWL();
		FWL temp7=new FWL();
		FWL temp8=new FWL();
		int j=0;
		for(int i=0;i<allLanes.size();i++){
			while(iterator.hasNext()){
				temp1=templist1.get(j).getLane1();
				temp2=templist1.get(j).getLane2();
				temp3=templist1.get(j).getLane3();
				temp4=templist1.get(j).getLane4();
				temp5=templist1.get(j).getLane5();
				temp6=templist1.get(j).getLane6();
				temp7=templist1.get(j).getLane7();
				temp7=allLanes.get(i);
				j++;
				if(temp8!=temp1&&temp8!=temp2&&temp8!=temp3&&temp8!=temp4&&temp8!=temp5&&temp8!=temp6&&temp8!=temp7){
					tempsix.setLane1(temp1);
					tempsix.setLane2(temp2);
					tempsix.setLane3(temp3);
					tempsix.setLane4(temp4);
					tempsix.setLane5(temp5);
					tempsix.setLane6(temp6);
					tempsix.setLane7(temp7);
					tempsix.setLane8(temp8);
					templist.add(tempsix);
				}	
			}
		}
		return templist;
	}
}
