package org.vinfoil;

import java.util.*;
public class Strategy3and4 {
	public Strategy3and4(){
		
	}
	public void Strategy3AND4(ArrayList <FWL> lanes, int NK, int offset, int paperwidth, Patterns Shape, int ScanStep, InitialFoilRoll IFR){
		//prvo odradis constraints od 5mm i 100mm
		Constraints5mmand100mm cons=new Constraints5mmand100mm();
		ArrayList <FWL> lanes1=new ArrayList <FWL>();
		ArrayList <ResultOfStrategy4> rs=new ArrayList <ResultOfStrategy4>();
		//org.vinfoil.ResultOfStrategy4 rstemp=new org.vinfoil.ResultOfStrategy4(false,false);
		List <HoldingResult> HS=Collections.synchronizedList(new ArrayList <HoldingResult>());
		HoldingResult hs=new HoldingResult();
		ArrayList <HoldingResult> hsTemp=new ArrayList <HoldingResult>();
		lanes1=cons.LaneConstraints(lanes, paperwidth, offset, NK);
		//onda zaokruglis sve lane
		KnivesConstraints KC=new KnivesConstraints();
		ArrayList <FWL> lanes2=KC.RoundingLanaesToResolution(lanes1, paperwidth, offset);
		ArrayList <RepassingResultsOneLaneOnly> rstemp=new ArrayList <RepassingResultsOneLaneOnly>();
		ArrayList <RepassingResultsOneLaneOnly> rstemp3=new ArrayList <RepassingResultsOneLaneOnly>();
		RepassingResultsOneLaneOnly resultForPrinting3=new RepassingResultsOneLaneOnly();
		RepassingResultsOneLaneOnly temprs=new RepassingResultsOneLaneOnly();
		RepassingResultsOneLaneOnly temprs1=new RepassingResultsOneLaneOnly();
		RepassingResultsOneLaneOnly temprs2=new RepassingResultsOneLaneOnly();
		ArrayList <RepassingResultsOneLaneTwoTimes> rstemp1=new ArrayList <RepassingResultsOneLaneTwoTimes>();
		RepassingResultsOneLaneTwoTimes resultForPrinting1=new RepassingResultsOneLaneTwoTimes();
		RepassingResultsOneLaneTwoTimes temprs3=new RepassingResultsOneLaneTwoTimes();
		ArrayList <RepassingResultsOneOnTwo> rstemp2=new ArrayList <RepassingResultsOneOnTwo>();
		RepassingResultsOneOnTwo resultForPrinting2=new RepassingResultsOneOnTwo();
		RepassingResultsOneOnTwo temprs4=new RepassingResultsOneOnTwo();
		
		boolean t=false;
		//onda proveravas uslov da je broj lane u skupu <=NK+3
		if(lanes2.size()<=NK+3){//ako jeste onda direktala proveravas repassing condition i ako je to daje resenje ne moras da ides dalje;dobro 
			for(int i=0;i<lanes2.size();i++)
				for(int j=0;j<lanes2.size();j++){//ovde duplopregledavam iste stvari
					if(i!=j){
						FWL lane1=lanes2.get(i);
						FWL lane2=lanes2.get(j);
						if(lane1.getWidth()>=lane2.getWidth()&&Math.abs(lane2.getEnd()-lane1.getStart())<900){//prva lane mora da bude sira od druge lane
						OneOnOneRepass ooor=new OneOnOneRepass();
						synchronized(HS){
							HS=ooor.OneOnOneRepassRun(lane1, lane2, Shape, ScanStep);
							//dobili smo rezultate repassing
							if(HS.size()!=0){//ako imamo repassing, zelimo da zapamtimo to resenje
								//ako imamo vise od dva repassing zelimo ono koje obuhvata sire lane
								//uzimam prvo resenje
								hs=HS.get(0);
								temprs.setHS(hs);
								temprs.setLane1(lane1);
								temprs.setLane2(lane2);
								temprs.setAllLanes(lanes2);
								rstemp.add(temprs);
							}
						}
					}
					}
				}
		}//dobro
			//ovde pregledavas rezultat repassing
			
			if(rstemp.size()!=0){//ako postoji makar jedno resenje;dobro
				t=true;
				//moram da ugradim ako ima samo jedno resenje sta onda, probbacu dva puta da ga repassujem
				if(rstemp.size()==1){//dobro
					OneOnTwoRepass ootr=new OneOnTwoRepass();
					List<Direction1andDirection2> templistdd = Collections.synchronizedList(new ArrayList<Direction1andDirection2>());
					Direction1andDirection2 d1d2=new Direction1andDirection2(0,0,0,0,0,0);
					for(int i=0;i<lanes2.size();i++){
						temprs=rstemp.get(0);
						FWL lane1=temprs.getLane1();
						FWL lane2=temprs.getLane2();
						FWL lane3=lanes2.get(i);
						if(lane1!=lane3&&lane2!=lane3&&lane2.getWidth()>lane3.getWidth()){
							templistdd=ootr.OneOnTwoRepassRun(lane1, lane2, lane3, Shape, ScanStep);
							synchronized(templistdd){
								Iterator <Direction1andDirection2> iterator=templistdd.iterator();
								boolean t1=false;
							       while (iterator.hasNext()&&t1==false){
								d1d2=templistdd.get(0);
								if(d1d2.getDirection1()!=0&&d1d2.getDirection2()!=0){
									//imamo resenje i treba da ga sacuvamo
									t1=true;
									temprs4.setDD(d1d2);
									temprs4.setLane1(lane1);
									temprs4.setLane2(lane2);
									temprs4.setLane3(lane3);
									temprs4.setAllLanes(lanes2);
									rstemp2.add(temprs4);
								}
							}
							       if(rstemp2.size()==0){
							    	   //ako ne moze dva puta da se repassuje imam samo jedan repass
							    	   rstemp3.add(temprs);
							    	   //ovde bi trebalo da se isproba join i extend
							       }
						}
					}
					}	
				}//ovde se zavrsava rstemp1;dobro
				if(rstemp.size()==2){//dobro
					temprs=rstemp.get(0);
					temprs1=rstemp.get(1);
					temprs3.setAllLanes(lanes2);
					temprs3.setLane1(temprs.getLane1());
					temprs3.setLane2(temprs.getLane2());
					temprs3.setLane3(temprs1.getLane1());
					temprs3.setLane4(temprs1.getLane2());
					temprs3.setHS1(temprs.getHS());
					temprs3.setHS2(temprs1.getHS());
					rstemp1.add(temprs3);
				}//dobro
				if(rstemp.size()>2){//trazima repassing najsirise 2 lane;dobro
					temprs=rstemp.get(0);
					temprs1=rstemp.get(1);
					if(temprs.getLane1().getWidth()+temprs.getLane2().getWidth()<temprs1.getLane1().getWidth()+temprs1.getLane2().getWidth()){
						temprs=rstemp.get(1);
						temprs1=rstemp.get(0);
					}
					for(int i=2;i<rstemp.size()-2;i++){
						temprs2=rstemp.get(i);
						float width1=temprs.getLane1().getWidth()+temprs.getLane2().getWidth();
						float width2=temprs1.getLane1().getWidth()+temprs1.getLane2().getWidth();
						float width3=temprs2.getLane1().getWidth()+temprs2.getLane2().getWidth();
						if(width1<width3)
							temprs=temprs2;
						else if(width2<width3)
							temprs1=temprs2;
					}
					
					temprs3.setAllLanes(lanes2);
					temprs3.setLane1(temprs.getLane1());
					temprs3.setLane2(temprs.getLane2());
					temprs3.setLane3(temprs1.getLane1());
					temprs3.setLane4(temprs1.getLane2());
					temprs3.setHS1(temprs.getHS());
					temprs3.setHS2(temprs1.getHS());
					rstemp1.add(temprs3);
				}//dobro
			}//dobro
			
			//ova dva su sa najvecom sirinom ili jedina dva ili samo jedno
		
		
		/*
		if(lanes2.size()>NK+3||rstemp.size()==0){//dobro
			//ako ne onda na te lane provuces kroz sve moguce merging kombinacije
			//na svakom od dobijenih skupova zelis da proveris repass
			org.vinfoil.FillingCombinations fc=new org.vinfoil.FillingCombinations ();
			List <org.vinfoil.AllPossibleCombinationWithMerging> tempFC=Collections.synchronizedList(new ArrayList <org.vinfoil.AllPossibleCombinationWithMerging>());
			tempFC=fc.FillForMerging(lanes2, NK);
			org.vinfoil.AllPossibleCombinationWithMerging combTemp=new org.vinfoil.AllPossibleCombinationWithMerging();
			synchronized(tempFC){//dobro
				int l=0;
				while(l<tempFC.size()){//dobro
					combTemp=tempFC.get(l);//jedna lista svih vec mergovanih laneova, treba ispsitati repass condidion na nju
					System.out.println("working with combination "+l);
					ArrayList <org.vinfoil.FWL> listOfMergComb=new ArrayList <org.vinfoil.FWL>();
					listOfMergComb.addAll(combTemp.getListOfLanes());
					for(int i=0;i<listOfMergComb.size();i++){//dobro
						for(int j=0;j<listOfMergComb.size();j++){
							if(i!=j){
								//
								org.vinfoil.FWL lane1=listOfMergComb.get(i);
								org.vinfoil.FWL lane2=listOfMergComb.get(j);
								if(lane1.getWidth()>=lane2.getWidth()&&Math.abs(lane2.getEnd()-lane1.getStart())<900){//prva lane mora da bude sira od druge lane
								org.vinfoil.OneOnOneRepass ooor=new org.vinfoil.OneOnOneRepass();
								synchronized(HS){
									HS=ooor.OneOnOneRepassRun(lane1, lane2, Shape, ScanStep);
									
									//dobili smo rezultate repassing
									if(HS.size()!=0){//ako imamo repassing, zelimo da zapamtimo to resenje
										//ako imamo vise od dva repassing zelimo ono koje obuhvata sire lane
										//uzimam prvo resenje
										hs=HS.get(0);
										temprs.setHS(hs);
										temprs.setLane1(lane1);
										temprs.setLane2(lane2);
										temprs.setAllLanes(listOfMergComb);
										rstemp.add(temprs);
									}
								}
							}
							}
						}
					}//dobro
					//sad ih na poprilicno slican nacin razvrstavam
					//ovde pregledavas rezultat repassing
					if(rstemp.size()!=0){//ako postoji makar jedno resenje;dobro
						t=true;
						//moram da ugradim ako ima samo jedno resenje sta onda, probbacu dva puta da ga repassujem
						if(rstemp.size()==1){//dobro
							org.vinfoil.OneOnTwoRepass ootr=new org.vinfoil.OneOnTwoRepass();
							List<org.vinfoil.Direction1andDirection2> templistdd = Collections.synchronizedList(new ArrayList<org.vinfoil.Direction1andDirection2>());
							org.vinfoil.Direction1andDirection2 d1d2=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
							for(int i=0;i<lanes2.size();i++){
								temprs=rstemp.get(0);
								org.vinfoil.FWL lane1=temprs.getLane1();
								org.vinfoil.FWL lane2=temprs.getLane2();
								org.vinfoil.FWL lane3=lanes2.get(i);
								if(lane1!=lane3&&lane2!=lane3&&lane2.getWidth()>lane3.getWidth()){
									templistdd=ootr.OneOnTwoRepassRun(lane1, lane2, lane3, Shape, ScanStep);
									synchronized(templistdd){
										Iterator <org.vinfoil.Direction1andDirection2> iterator=templistdd.iterator();
										boolean t1=false;
									       while (iterator.hasNext()&&t1==false){
										d1d2=templistdd.get(0);
										if(d1d2.getDirection1()!=0&&d1d2.getDirection2()!=0){
											//imamo resenje i treba da ga sacuvamo
											t1=true;
											temprs4.setDD(d1d2);
											temprs4.setLane1(lane1);
											temprs4.setLane2(lane2);
											temprs4.setLane3(lane3);
											temprs4.setAllLanes(lanes2);
											rstemp2.add(temprs4);
										}
									}
									       if(rstemp2.size()==0){
									    	   //ako ne moze dva puta da se repassuje imam samo jedan repass
									    	   rstemp3.add(temprs);
									       }
								}
							}
							}	
						}//dobro
						if(rstemp.size()==2){//dobro
							temprs=rstemp.get(0);
							temprs1=rstemp.get(1);
							temprs3.setAllLanes(lanes2);
							temprs3.setLane1(temprs.getLane1());
							temprs3.setLane2(temprs.getLane2());
							temprs3.setLane3(temprs1.getLane1());
							temprs3.setLane4(temprs1.getLane2());
							temprs3.setHS1(temprs.getHS());
							temprs3.setHS2(temprs1.getHS());
							rstemp1.add(temprs3);
						}//dobro
						if(rstemp.size()>2){//trazima repassing najsirise 2 lane; dobro
							temprs=rstemp.get(0);
							temprs1=rstemp.get(1);
							if(temprs.getLane1().getWidth()+temprs.getLane2().getWidth()<temprs1.getLane1().getWidth()+temprs1.getLane2().getWidth()){
								temprs=rstemp.get(1);
								temprs1=rstemp.get(0);
							}
							for(int i=2;i<rstemp.size()-2;i++){
								temprs2=rstemp.get(i);
								float width1=temprs.getLane1().getWidth()+temprs.getLane2().getWidth();
								float width2=temprs1.getLane1().getWidth()+temprs1.getLane2().getWidth();
								float width3=temprs2.getLane1().getWidth()+temprs2.getLane2().getWidth();
								if(width1<width3)
									temprs=temprs2;
								else if(width2<width3)
									temprs1=temprs2;
							}
							
							temprs3.setAllLanes(lanes2);
							temprs3.setLane1(temprs.getLane1());
							temprs3.setLane2(temprs.getLane2());
							temprs3.setLane3(temprs1.getLane1());
							temprs3.setLane4(temprs1.getLane2());
							temprs3.setHS1(temprs.getHS());
							temprs3.setHS2(temprs1.getHS());
							rstemp1.add(temprs3);
						}//dobro
					}//dobro
					//
					l++;
				}//dobro
			}//dobro
			//ovde dodje deo za joining i extneding
			//smisli nacin da sacuvas resenja:resenje treba da izadje kao skup lane sa njihovim pozicijama,
			//repassing ako je moguc na koju lane se repassuje za koliko se pomera, ili ne
			//onda pozoves joinings
			//onda pozoves extending ali na mergovane lane sa uslovom <=NK+1
		}//dobro
		
		*/
		//moras da sacekas da ti se svi thread izvrse
		//while (Thread.activeCount() > 2) {
		//}
 
	 
		//u sva resenja racunas i resenje dobijeno iz strategije 1 i 2
		//extendujes do okrgule 450 initial foil roll width
		//System.out.println("koliko resenja imam one lane two times "+rstemp1.size());
		//System.out.println("koliko resenje imam one on two"+rstemp2.size());
		//System.out.println("koliko resenja imam only one on one"+rstemp3.size());
		System.out.println("unutra"+rstemp1.size());
		System.out.println("koliko resenja imam one lane two times "+rstemp1.size());
	}
	
	//
		

}
