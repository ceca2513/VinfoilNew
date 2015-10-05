package org.vinfoil;

import java.util.*;

public class Strategy1 {
	
	public Strategy1(){
		
	}
	
	public static void Str1(ArrayList <Parallelogram> MyListOfParallelograms, ArrayList <Rectangel> MyListOfRectangels,ArrayList <Triangel> MyListOfTriangels, InitialFoilRoll IFR, float CPC){
		MinAndMax MyStrategy1=new MinAndMax();
		float NewFoilPositionx1, NewFoilPositionx2,FoilWidth,NeededFoilWidth = 0;
		float w1=1100,w2=0;
		float wp1,wr1,wt1,wp2,wr2,wt2;
		float paperwidth=0;
		ArrayList <Integer> IFRW=new ArrayList<Integer>();


	
		wp1 = MyStrategy1.TheMostLeftPointOfAllParallelograms(MyListOfParallelograms);
		//System.out.println("plevo"+wp1);
		w1=Math.min(w1,wp1);
		//System.out.println("levo"+w1);
		wr1=MyStrategy1.TheMostLeftPointOfAllRectagels(MyListOfRectangels);
		//System.out.println("rlevo "+wr1);
		w1=Math.min(w1, wr1);
		//System.out.println("levo"+w1);
		wt1=MyStrategy1.TheMostLeftPointOfAllTriangels(MyListOfTriangels);
		//System.out.println("tlevo "+wt1);
		w1=Math.min(w1, wt1);
		//System.out.println("levo"+w1);
		NewFoilPositionx1=w1;
		System.out.println("New foil position1 "+(NewFoilPositionx1-5));
		
		wp2=MyStrategy1.TheMostRightOfAllParallelograms(MyListOfParallelograms);
		//System.out.println("desno"+wp2);
		w2=Math.max(w2, wp2);
		//System.out.println("pdesno"+w2);
		wr2=MyStrategy1.TheMostRightPointOfAllRectangels(MyListOfRectangels);
		//System.out.println("rdesno"+wr2);
		w2=Math.max(w2, wr2);
		//System.out.println("desno"+w2);
		wt2=MyStrategy1.TheMostRightOfAllTriangels(MyListOfTriangels);
		//System.out.println("tdesno"+wt2);
		w2=Math.max(w2, wt2);
		//System.out.println("desno"+w2);
		NewFoilPositionx2=w2;
		System.out.println("New foil position2 "+(NewFoilPositionx2+5));
		
		/*NewFoilPositionx1=MyStrategy1.TheMostLeftPoint(Shape)-5;
		NewFoilPositionx2=MyStrategy1.TheMoastRightPoint(Shape)+5;*/
		FoilWidth=NewFoilPositionx2-NewFoilPositionx1+10;
		System.out.println("Minimum necessary width with Strategy 1 is: "+FoilWidth);
		System.out.println("The area of foil with strategy 1 "+(FoilWidth*CPC)/10000);
		//System.out.println("levo"+w1);
		//System.out.println("desno"+w2);
		IFRW=IFR.getWidths(IFR.getStep());
		for(int i=0;i<IFRW.size();i++){
			
			if(FoilWidth==IFRW.get(i)){
				NeededFoilWidth=IFRW.get(i);
			}
			else if(FoilWidth>IFRW.get(i))
				if(FoilWidth<IFRW.get(i+1))
					NeededFoilWidth=IFRW.get(i+1);
		
		}
		System.out.println("Needed foil width with Strategy 1 is "+NeededFoilWidth);
		NewFoilPositionx1=NewFoilPositionx1-Math.round((NeededFoilWidth-FoilWidth)/2)-5;
		NewFoilPositionx2=NewFoilPositionx2+(NeededFoilWidth-FoilWidth)-Math.round((NeededFoilWidth-FoilWidth)/2)+5;
		System.out.println("First position in PS is "+NewFoilPositionx1);
		System.out.println("Second position in PS is "+NewFoilPositionx2);
		System.out.println("The foil consumption with strategy 1 "+(NeededFoilWidth*CPC)/10000);
		
	}

}
