package org.vinfoil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OneOnOneRepass {

	public OneOnOneRepass(){
		
	}
	public List <HoldingResult> OneOnOneRepassRun(FWL lane1, FWL lane2, Patterns Shape, int ScanStep){
		List <HoldingResult> HS=Collections.synchronizedList(new ArrayList <HoldingResult>());
		Discretization dis1=new Discretization();
		MinAndMax templane1= new MinAndMax();
		MinAndMax templane2= new MinAndMax();
		SetOfPatternsForALane patternslane1=new SetOfPatternsForALane();
		SetOfPatternsForALane patternslane2=new SetOfPatternsForALane();
		Patterns nesto=patternslane2.PatternsForOneLane(Shape, lane2);
		float test=templane1.TheXminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1));
		int X2min=(int) ( templane2.TheXminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2))-lane2.getStart());
		int X2max=(int) ( templane2.TheXmaxOfAllPatterns(nesto)-lane2.getStart());
		int Y2min=(int) ( templane2.TheYminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)));
		int Y2max=(int) ( templane2.TheYmaxOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)));
		int X1min=(int) ( templane1.TheXminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1))-lane1.getStart());
		int X1max=(int) ( templane1.TheXmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1))-lane1.getStart());
		int Y1min=(int) ( templane1.TheYminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)));
		int Y1max=(int) ( templane1.TheYmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)));
		int [][] matrixdis2=dis1.DiscretizationOfLane(lane2, Shape);
		int [][] matrixdis1=dis1.DiscretizationOfLaneAddingScanStep(lane1, Shape, ScanStep);
		
		
		
		ThreadHoldingConstraint11 thc11=new ThreadHoldingConstraint11(); 
		ThreadHoldingConstraint21 thc21=new ThreadHoldingConstraint21(); 
		ThreadHoldingConstraint31 thc31=new ThreadHoldingConstraint31(); 
		ThreadHoldingConstraint41 thc41=new ThreadHoldingConstraint41(); 
		ThreadHoldingConstraint51 thc51=new ThreadHoldingConstraint51(); 
		ThreadHoldingConstraint61 thc61=new ThreadHoldingConstraint61(); 
		ThreadHoldingConstraint71 thc71=new ThreadHoldingConstraint71(); 
		ThreadHoldingConstraint81 thc81=new ThreadHoldingConstraint81(); 
		//sada moram da napravim nove bez embedinga
		if(X1min-ScanStep<0)
			X1min=0;
		else X1min=X1min-ScanStep;
		if(X1max+ScanStep>lane1.getWidth()-1)
			X1max=(int) lane1.getWidth()-1;
		else X1max=X1max+ScanStep;
		if(Y1min-ScanStep<0)
			Y1min=0;
		else Y1min=Y1min-ScanStep;
		if(Y1max+ScanStep>lane1.getLength()-1)
			Y1max=(int) lane1.getLength()-1;
		else Y1max=Y1max+ScanStep;
		thc11.setDiscreteLane1(matrixdis1);thc11.setDiscreteLane2(matrixdis2);
		thc11.setX1min(X1min);thc11.setX1max(X1max);
		thc11.setX2min(X2min);thc11.setX2max(X2max);
		thc11.setY1min(Y1min);thc11.setY1max(Y1max);
		thc11.setY2min(Y2min);thc11.setY2max(Y2max);
		thc11.setN1((int)lane1.getWidth());thc11.setN2((int)lane2.getWidth());
		thc11.setM((int)lane1.getLength());thc11.setScanStep(ScanStep);
		
		thc21.setDiscreteLane1(matrixdis1);thc21.setDiscreteLane2(matrixdis2);
		thc21.setX1min(X1min);thc21.setX1max(X1max);
		thc21.setX2min(X2min);thc21.setX2max(X2max);
		thc21.setY1min(Y1min);thc21.setY1max(Y1max);
		thc21.setY2min(Y2min);thc21.setY2max(Y2max);
		thc21.setN1((int)lane1.getWidth());thc21.setN2((int)lane2.getWidth());
		thc21.setM((int)lane1.getLength());thc21.setScanStep(ScanStep);
		
		thc31.setDiscreteLane1(matrixdis1);thc31.setDiscreteLane2(matrixdis2);
		thc31.setX1min(X1min);thc31.setX1max(X1max);
		thc31.setX2min(X2min);thc31.setX2max(X2max);
		thc31.setY1min(Y1min);thc31.setY1max(Y1max);
		thc31.setY2min(Y2min);thc31.setY2max(Y2max);
		thc31.setN1((int)lane1.getWidth());thc31.setN2((int)lane2.getWidth());
		thc31.setM((int)lane1.getLength());thc31.setScanStep(ScanStep);
		
		thc41.setDiscreteLane1(matrixdis1);thc41.setDiscreteLane2(matrixdis2);
		thc41.setX1min(X1min);thc41.setX1max(X1max);
		thc41.setX2min(X2min);thc41.setX2max(X2max);
		thc41.setY1min(Y1min);thc41.setY1max(Y1max);
		thc41.setY2min(Y2min);thc41.setY2max(Y2max);
		thc41.setN1((int)lane1.getWidth());thc41.setN2((int)lane2.getWidth());
		thc41.setM((int)lane1.getLength());thc41.setScanStep(ScanStep);
		
		thc51.setDiscreteLane1(matrixdis1);thc51.setDiscreteLane2(matrixdis2);
		thc51.setX1min(X1min);thc51.setX1max(X1max);
		thc51.setX2min(X2min);thc51.setX2max(X2max);
		thc51.setY1min(Y1min);thc51.setY1max(Y1max);
		thc51.setY2min(Y2min);thc51.setY2max(Y2max);
		thc51.setN1((int)lane1.getWidth());thc51.setN2((int)lane2.getWidth());
		thc51.setM((int)lane1.getLength());thc51.setScanStep(ScanStep);
		
		thc61.setDiscreteLane1(matrixdis1);thc61.setDiscreteLane2(matrixdis2);
		thc61.setX1min(X1min);thc61.setX1max(X1max);
		thc61.setX2min(X2min);thc61.setX2max(X2max);
		thc61.setY1min(Y1min);thc61.setY1max(Y1max);
		thc61.setY2min(Y2min);thc61.setY2max(Y2max);
		thc61.setN1((int)lane1.getWidth());thc61.setN2((int)lane2.getWidth());
		thc61.setM((int)lane1.getLength());thc61.setScanStep(ScanStep);
		
		thc71.setDiscreteLane1(matrixdis1);thc71.setDiscreteLane2(matrixdis2);
		thc71.setX1min(X1min);thc71.setX1max(X1max);
		thc71.setX2min(X2min);thc71.setX2max(X2max);
		thc71.setY1min(Y1min);thc71.setY1max(Y1max);
		thc71.setY2min(Y2min);thc71.setY2max(Y2max);
		thc71.setN1((int)lane1.getWidth());thc71.setN2((int)lane2.getWidth());
		thc71.setM((int)lane1.getLength());thc71.setScanStep(ScanStep);
		
		thc81.setDiscreteLane1(matrixdis1);thc81.setDiscreteLane2(matrixdis2);
		thc81.setX1min(X1min);thc81.setX1max(X1max);
		thc81.setX2min(X2min);thc81.setX2max(X2max);
		thc81.setY1min(Y1min);thc81.setY1max(Y1max);
		thc81.setY2min(Y2min);thc81.setY2max(Y2max);
		thc81.setN1((int)lane1.getWidth());thc81.setN2((int)lane2.getWidth());
		thc81.setM((int)lane1.getLength());thc81.setScanStep(ScanStep);
		

		thc11.run(); 
		try{
			thc11.join();
			
		}catch(Exception e){System.out.println(e);} 
		thc21.start();
		try{
			thc21.join();
			
		}catch(Exception e){System.out.println(e);} 
		thc31.run();
		try{
			thc31.join();
			
		}catch(Exception e){System.out.println(e);} 
		thc41.run();
		try{
			thc41.join();
			
		}catch(Exception e){System.out.println(e);} 
		thc51.run();
		try{
			thc51.join();
			
		}catch(Exception e){System.out.println(e);} 
		thc61.run();
		try{
			thc61.join();
			
		}catch(Exception e){System.out.println(e);} 
		thc71.start();
		try{
			thc71.join();
			
		}catch(Exception e){System.out.println(e);}
		thc81.start();
		try{
			thc81.join();
			
		}catch(Exception e){System.out.println(e);}
		HS.addAll(thc11.getHS());
		HS.addAll(thc21.getHS());
		HS.addAll(thc31.getHS());
		HS.addAll(thc41.getHS());
		HS.addAll(thc51.getHS());
		HS.addAll(thc61.getHS());
		HS.addAll(thc71.getHS());
		HS.addAll(thc81.getHS());
		/*thc11.run(); 
		thc21.start();
		thc31.run();
		thc41.run();
		thc51.run();
		thc61.run();
		thc71.start();
		thc81.start();
		HS.addAll(thc11.getHS());
		HS.addAll(thc21.getHS());
		HS.addAll(thc31.getHS());
		HS.addAll(thc41.getHS());
		HS.addAll(thc51.getHS());
		HS.addAll(thc61.getHS());
		HS.addAll(thc71.getHS());
		HS.addAll(thc81.getHS());*/
		//uniting results 
		return HS;
	}
}
