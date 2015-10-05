package org.vinfoil;

import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//int i=1,j=0,k=0;
		long startTime = System.nanoTime();
		float NewFoilPositionx1, NewFoilPositionx2,FoilWidth,NeededFoilWidth = 0;
		float w1=1100,w2=0;
		float wp1,wr1,wt1,wp2,wr2,wt2;
		float paperwidth=0;
		int numOfLanes=0;
		int sheetWidth=0;
		float foilWidth=0;
		float position1, position2, position3, position4, position5, position6,position7;
		float widthPos1, widthPos2,widthPos3,widthPos4,widthPos5, widthPos6, widthPos7;
		float PT=0;
		float PR=0;
		float PP=0;

		InitialFoilRoll IFR=new InitialFoilRoll();
		IFR.setLength(10000000);
		IFR.setStep(10);
		ArrayList <Integer> IFRW=new ArrayList<Integer>();
		//Initial foil roll info

		Paper P=new Paper();
		P.setPaperLength(682);
		P.setPaperWidth(1008);
		CenteringToPS CTPS=new CenteringToPS();
		//org.vinfoil.Paper info

		PrintingSheet PS=new PrintingSheet();
		PS.setLength(942);
		PS.setWidth(1060);
		PS.setCPC(942);
		//Printing sheet info

		MinAndMax MyStrategy1=new MinAndMax();
		Patterns Shape=new Patterns();

		int NK=0;//number of knifes
		NK=2;
		//Paterns info
		ReadExcelPatterns readExcelPatterns = new ReadExcelPatterns();
		readExcelPatterns.read(Shape);
		System.out.println("rectangles "+Shape.getRectangels().size());
		System.out.println("triangles "+Shape.getTriangels().size());
		System.out.println("parallelograms "+Shape.getParallelograms().size());

		ArrayList <Parallelogram> MyListOfParallelograms=new ArrayList<Parallelogram>();
		ArrayList <Rectangel> MyListOfRectangels=new ArrayList<Rectangel>();
		ArrayList <Triangel> MyListOfTriangels=new ArrayList<Triangel>();

		P.setOffsetx1(CTPS.CenteringOfInitialRoll(PS.getWidth(),P));//New position of the paper according to PScoordiante
		System.out.println("offset"+P.getOffsetx1());
		paperwidth=P.getPaperWidth()+P.getOffsetx1();//need to do something about this!!!!!!!!!!
		System.out.println("offset2 "+paperwidth);
		int offset=(int) P.getOffsetx1();
		int paperwidth1=(int) P.getPaperWidth();

	/*org.vinfoil.CreateLanes nj=new org.vinfoil.CreateLanes ();
		org.vinfoil.FWL q=new org.vinfoil.FWL ();
		q=nj.CreateLaneForAParalelogram(PAR1);
		System.out.println("begining of lane is "+q.Start);
		
		System.out.println("ja"+MyListOfParalelograms.size());
		ArrayList <org.vinfoil.FWL> S0=new ArrayList <org.vinfoil.FWL> ();
		ArrayList <org.vinfoil.FWL> S=new ArrayList <org.vinfoil.FWL> ();
		org.vinfoil.InitialSetOfLanes I=new org.vinfoil.InitialSetOfLanes ();
		org.vinfoil.FWL p=new org.vinfoil.FWL ();
		S0=I.InitialS0(MyListOfParalelograms, MyListOfTriangles, MyListOfRectangles);
		System.out.println("broj elemenata u S0 "+S0.size());
		for(int i=0;i<S0.size();i++){
			p=S0.get(i);
			System.out.println("I start "+p.Start);
			System.out.println("I end"+p.End);
			System.out.println("the number of lane is "+i);
		}
		S=I.CreatingS0(S0, false);//initial should be false
		
		for(int i=0;i<S.size();i++){
			p=S.get(i);
			System.out.println("start "+p.Start);
			System.out.println("end"+p.End);
			System.out.println("the number of lane is "+i);
		}
		System.out.println("ja");*/
		
		/*org.vinfoil.FWL MyLane1=new org.vinfoil.FWL();
		org.vinfoil.FWL MyLane2=new org.vinfoil.FWL();
		org.vinfoil.FWL MyLane3=new org.vinfoil.FWL();
		org.vinfoil.FWL MyLane4=new org.vinfoil.FWL();
		org.vinfoil.FWL NewLaneMerging=new org.vinfoil.FWL();
		org.vinfoil.FWL a=new org.vinfoil.FWL();
		org.vinfoil.FWL b=new org.vinfoil.FWL();
		ArrayList <org.vinfoil.FWL> g=new ArrayList <org.vinfoil.FWL>();
		ArrayList <org.vinfoil.FWL> x=new ArrayList <org.vinfoil.FWL>();
		boolean t;
		float lm=0,ln=0,lk=0;
		org.vinfoil.Overlaping ovl=new org.vinfoil.Overlaping ();
		MyLane1.setStart(50);
		MyLane2.setStart(312);
		MyLane1.setEnd(292);
		MyLane2.setEnd(610);
		MyLane1.setLength(PS.CPC);
		MyLane2.setLength(PS.CPC);
		MyLane3.setStart(615);
		MyLane3.setEnd(750);
		MyLane3.setLength(PS.CPC);
		MyLane4.setStart(755);
		MyLane4.setEnd(863);
		MyLane4.setLength(PS.CPC);
		
		MyLane1.setWidth(MyLane1.End-MyLane1.Start);
		MyLane2.setWidth(MyLane2.End-MyLane2.Start);
		MyLane3.setWidth(MyLane3.End-MyLane3.Start);
		MyLane4.setWidth(MyLane4.End-MyLane4.Start);
		
		org.vinfoil.KnivesConstraints kc=new org.vinfoil.KnivesConstraints ();
		
		g.add(MyLane1);
		g.add(MyLane2);
		g.add(MyLane3);
		g.add(MyLane4);
		org.vinfoil.Extending h=new org.vinfoil.Extending();
		lm=10-MyLane1.Width%10;
		ln=10-MyLane2.Width%10;
		lk=10-MyLane3.Width%10;
		System.out.println("lane extend "+lm);
		t=h.CheckIfLaneCanBeExtended2(g, MyLane1, paperwidth, lm);
		System.out.println(t);
		t=h.CheckIfLaneCanBeExtended2(g, MyLane2, paperwidth, ln);
		System.out.println(t);
		t=h.CheckIfLaneCanBeExtended2(g, MyLane3, paperwidth, lk);
		System.out.println(t);
		x=kc.RoundingLanaesToResolution(g, paperwidth);
		System.out.println("broj zaokruzenih lane je "+x.size());
		for(int i=0;i<x.size();i++){
			System.out.println("pocetak "+x.get(i).Start);
			System.out.println("kraj "+x.get(i).End);
			System.out.println("sirina je "+x.get(i).Width);
		}
		x=h.ExtendingAll1(x, 20, paperwidth);
		System.out.println("broj zaokruzenih i extnedovanih lane je "+x.size());
		for(int i=0;i<x.size();i++){
			System.out.println("pocetak "+x.get(i).Start);
			System.out.println("kraj "+x.get(i).End);
			System.out.println("sirina je "+x.get(i).Width);
		}*/

		//t=ovl.CheckOverlapingOfTwoLanes(MyLane1, MyLane2);
		/*System.out.println(t);
		g.add(MyLane1);
		//g.add(MyLane2);
		org.vinfoil.Extending h=new org.vinfoil.Extending();
		//x=h.ExtendingAll(g, 85, P.PaperWidth, true);
		t=h.CheckIfLaneCanBeExtended(g, MyLane1, P.PaperWidth);
		System.out.println(g.size());
		x=h.ExtendingOneLane(g, MyLane1, P.PaperWidth);
		System.out.println("extendingtest");
		for(int i=0;i<x.size();i++){
			a=x.get(i);
			System.out.println("lane num "+i);
			System.out.println("starts at "+a.Start);
			System.out.println("ends at "+a.End);
			System.out.println("is of width "+a.Width);
		}*/
		
		
		
		/*org.vinfoil.Extending h=new org.vinfoil.Extending();
		boolean t;
		t=h.CheckIfLaneCanBeExtended(g, MyLane1, P.PaperWidth);
		if(t=true)
		x=h.ExtendingOneLane(g, MyLane1, P.PaperWidth);
		else System.out.print("No extending possible");
		x=
		
		//x=h.ExtendingAll(g, 100, P.PaperWidth, true);
		a=x.get(0);
		//b=x.get(2);
		System.out.println("first lane start "+a.Start);
		System.out.println("first lane end "+a.End);
		System.out.println("second lane start "+b.Start);
		System.out.println("second lane start "+b.End);*/
		
		/*NewLaneMerging=NewLaneMerging.MergingOfTwoLanes(MyLane1, MyLane2);
		System.out.println("merging start "+NewLaneMerging.Start);
		System.out.println("merging end "+NewLaneMerging.End);
		System.out.println("merging width "+NewLaneMerging.Width);*/


		AdditionalStatistic as=new AdditionalStatistic ();
		for(int i=0;i<Shape.getRectangels().size();i++){
			Rectangel Rtemp = Shape.getRectangels().get(i);
			Rtemp.setX11(Rtemp.getX11()+P.getOffsetx1());
			Rtemp.setX12(Rtemp.getX12()+P.getOffsetx1());
			MyListOfRectangels.add(Rtemp);
			if(5+P.getOffsetx1()>Rtemp.getX11()||5+P.getOffsetx1()>Rtemp.getX12()||Rtemp.getX11()<P.getOffsetx1()-5||Rtemp.getX12()<P.getOffsetx1()-5)
				System.out.println("WARNING the inputed data is not 5mm from the edge of the paper, this will cause inccorect output result R");

		}
		//while(MyListOfTriangles!=null){
			for(int j=0;j<Shape.getTriangels().size();j++){
			Triangel Ttemp = Shape.getTriangels().get(j);
			Ttemp.setX11(Ttemp.getX11()+P.getOffsetx1());
			Ttemp.setX12(Ttemp.getX12()+P.getOffsetx1());
			Ttemp.setX13(Ttemp.getX13()+P.getOffsetx1());
			MyListOfTriangels.add(Ttemp);
			if(5+P.getOffsetx1()>Ttemp.getX11()||5+P.getOffsetx1()>Ttemp.getX12()||5+P.getOffsetx1()>Ttemp.getX13()||Ttemp.getX11()<P.getOffsetx1()-5||Ttemp.getX12()<P.getOffsetx1()-5||Ttemp.getX13()<P.getOffsetx1()-5)
				System.out.println("WARNING the inputed data is not 5mm from the edge of the paper, this will cause inccorect output result T");

		}
		//while(MyListOfParalelograms!=null){
			for(int k=0;k<Shape.getParallelograms().size();k++){
			Parallelogram PARtemp = Shape.getParallelograms().get(k);
			PARtemp.setX11(PARtemp.getX11()+P.getOffsetx1());
			PARtemp.setX12(PARtemp.getX12()+P.getOffsetx1());
			PARtemp.setX13(PARtemp.getX13()+P.getOffsetx1());
			PARtemp.setX14(PARtemp.getX14()+P.getOffsetx1());
			MyListOfParallelograms.add(PARtemp);
			if(5+P.getOffsetx1()>PARtemp.getX11()||5+P.getOffsetx1()>PARtemp.getX12()||5+P.getOffsetx1()>PARtemp.getX13()||PARtemp.getX11()<P.getOffsetx1()-5||PARtemp.getX12()<P.getOffsetx1()-5||PARtemp.getX13()<P.getOffsetx1()-5)
				System.out.println("WARNING the inputed data is not 5mm from the edge of the paper, this will cause inccorect output result P");

		}

			ArrayList <Float> PTriangels=new ArrayList <Float> ();
			ArrayList <Float> PRectangels=new ArrayList <Float> ();
			ArrayList <Float> PParallelograms=new ArrayList <Float> ();

			float sumr=0;
			float sumt=0;
			float sump=0;
			for(int i=0;i<MyListOfRectangels.size();i++){
				Rectangel Rtemp=MyListOfRectangels.get(i);
				PR=Rtemp.getA()*Rtemp.getB();
				//PRectangels.add(PR);
				sumr+=PR;
			}
			for(int i=0;i<MyListOfTriangels.size();i++){
				Triangel Ttemp=MyListOfTriangels.get(i);
				PT=as.HeronsFormula(Ttemp.getA(), Ttemp.getB(), Ttemp.getC());
				sumt+=PT;
			}
			for(int i=0;i<MyListOfParallelograms.size();i++){
				Parallelogram Ptemp=MyListOfParallelograms.get(i);
				PP=as.SurfaceAreaOfParalleogram(Ptemp.getA(), Ptemp.getHa());
				sump+=PP;
			}

		float sum=sumr+sumt+sump;
		System.out.println("The total area with bounded patterns is "+sum/10000);
		System.out.println("The area of paper "+(P.getPaperLength()*P.getPaperWidth())/10000);

			Strategy1 stg1=new Strategy1 ();
			stg1.Str1(MyListOfParallelograms, MyListOfRectangels, MyListOfTriangels, IFR,PS.getCPC());


			IFRW=IFR.getWidths(IFR.getStep());

		Strategy2 Stra2=new Strategy2 ();
		InitialSetOfLanes Set0=new InitialSetOfLanes ();
		ArrayList <FWL> newSet=new ArrayList <FWL> ();
		ArrayList <FWL> newSet1=new ArrayList <FWL> ();
		ArrayList <FWL> newSet2=new ArrayList <FWL> ();
		InitialSetOfLanes bnk=new InitialSetOfLanes ();
		float mnwidth=0,extension=0,mnwidth1=0;
		int in=0;
		Extending e=new Extending ();

		newSet=Set0.InitialS0(MyListOfParallelograms, MyListOfTriangels, MyListOfRectangels, PS.getCPC());
		System.out.println("number of lanes in initial set with overlapping "+newSet.size());
		/* for(int i=0;i<newSet.size();i++)
		  {
			 System.out.println("Lane "+i);
				System.out.println("begins "+newSet.get(i).Start);
				System.out.println("ends "+newSet.get(i).End);
				System.out.println("and is of width "+newSet.get(i).Width);
		 }*/

		newSet1=Set0.CreatingS0(newSet, false,PS.getCPC());
		System.out.println("number of lanes in initial set without overlapping "+newSet1.size());
		 for(int i=0;i<newSet1.size();i++)
		  {
			 System.out.println("Lane "+i);
				System.out.println("begins "+newSet1.get(i).getStart());
				System.out.println("ends "+newSet1.get(i).getEnd());
				System.out.println("and is of width "+newSet1.get(i).getWidth());
		 }

		FWL experimentdis=new FWL ();
		experimentdis=newSet1.get(0);
		Discretization tempdis=new Discretization ();
		int [][] matrix1= new int [(int) experimentdis.getWidth()][(int) experimentdis.getLength()];
		Patterns expdis=new Patterns();
		SetOfPatternsForALane expedis=new SetOfPatternsForALane ();
		//expdis=expedis.PatternsForOneLane(Shape, experimentdis);
		//matrix1=tempdis.DiscretizationOfLane(experimentdis, expdis);
		FWL test1=new FWL();
		FWL test2=new FWL();
		FWL test3=new FWL();
		test1=newSet1.get(0);
		test2=newSet1.get(1);
		test3=newSet1.get(0);
		HoldingConstraints testing=new HoldingConstraints();
		HoldingResult res=new HoldingResult();


		int [][] matrixx1=new int [10][10];
		int [][] matrixx2=new int [10][10];
		int [][] matrixx3=new int [10][10];
		int [][] matrixx4=new int [10][10];
		/*for(int i=0;i<10;i++)
			for(int j=0;j<10;j++){
				matrixx1[i][j]=0;
				matrixx2[i][j]=0;
				matrixx4[i][j]=1;
			}
		
		matrixx1[5][5]=1;
		matrixx1[5][6]=1;
		matrixx2[5][5]=1;
		matrixx2[5][6]=1;
		matrixx1[5][7]=1;
		matrixx1[5][8]=1;
		matrixx2[5][7]=1;
		matrixx2[5][8]=1;
		matrixx1[5][4]=1;
		matrixx1[5][9]=1;
		matrixx2[5][4]=1;
		matrixx2[5][9]=1;
		org.vinfoil.Overlaping testic=new org.vinfoil.Overlaping();
		boolean d=testic.CheckOverlapingOfTwoDiscretizedLanes(matrixx1, matrixx2, 5, 5, 5, 5, 5, 5, 6, 6);
		System.out.println(d);
		
		org.vinfoil.ZeroPadding zeP=new org.vinfoil.ZeroPadding();
		matrixx3=zeP.Shifting(matrixx4, 4, 4, 10, 10, 7);
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(matrixx3[i][j]+"");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		matrixx3=zeP.Shifting(matrixx4, 4, 4, 10, 10, 8);
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(matrixx3[i][j]+"");
			}
			System.out.print("\n");
		}*/
		
		/*if(test1.getWidth()>test2.getWidth()){
		res=testing.Repassing(test1, test2, Shape, 20);
		}else{
			res=testing.Repassing(test2, test1, Shape, 20);
		}
		
		
		System.out.println("Is repassing possibile? "+res.getRepassing());
		System.out.println("In which direction "+res.getDirection());
		System.out.println("How much we should move in X (i) direction "+res.getShiftX());
		System.out.println("How much we should move in Y (j) direction "+res.getShiftY());
		org.vinfoil.ZeroPadding zeP=new org.vinfoil.ZeroPadding();*/
		ArrayList <HoldingResult> res1=new ArrayList <HoldingResult>();
		//res1=testing.Repassing2(test1, test2, test1, Shape, 20);
		//res1=testing.Repassing3(test1, test2, test1, Shape, 20);
/*		res1=testing.Repassing(test1, test2, Shape, 20);
		System.out.println("broj resenja sa repassing je "+res1.size());
		for(int i=0;i<res1.size();i++){
			System.out.println("Is repassing of one lane over two lanes possible "+res1.get(i).getRepassing());
			System.out.println(" lane: "+i);
			System.out.println("In which direction "+res1.get(i).getDirection());
			System.out.println("How much we should move in X (i) direction "+res1.get(i).getShiftX());
			System.out.println("How much we should move in Y (j) direction "+res1.get(i).getShiftY());
				
		}*/
		Discretization dis1=new Discretization();
		MinAndMax templane1= new MinAndMax();
		MinAndMax templane2= new MinAndMax();
		SetOfPatternsForALane patternslane1=new SetOfPatternsForALane();
		SetOfPatternsForALane patternslane2=new SetOfPatternsForALane();
		Patterns nesto=patternslane2.PatternsForOneLane(Shape, newSet1.get(0));
		int X2min=(int) ( templane2.TheXminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, newSet1.get(0)))-newSet1.get(0).getStart());
		int X2max=(int) ( templane2.TheXmaxOfAllPatterns(nesto)-newSet1.get(0).getStart());
		int Y2min=(int) ( templane2.TheYminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, newSet1.get(0))));
		int Y2max=(int) ( templane2.TheYmaxOfAllPatterns(patternslane2.PatternsForOneLane(Shape, newSet1.get(0))));
		int X1min=(int) ( templane1.TheXminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, newSet1.get(0)))-newSet1.get(0).getStart());
		int X1max=(int) ( templane1.TheXmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, newSet1.get(0)))-newSet1.get(0).getStart());
		int Y1min=(int) ( templane1.TheYminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, newSet1.get(0))));
		int Y1max=(int) ( templane1.TheYmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, newSet1.get(0))));
		/*int [][] matrixdis1=dis1.DiscretizationOfLane(newSet1.get(0), Shape);
		int [][] matrixdis2=dis1.DiscretizationOfLaneAddingScanStep(newSet1.get(0), Shape, 20);
		
		
		
		org.vinfoil.thread.ThreadHoldingConstraint1 thc1=new org.vinfoil.thread.ThreadHoldingConstraint1();
		org.vinfoil.thread.ThreadHoldingConstraint2 thc2=new org.vinfoil.thread.ThreadHoldingConstraint2();
		org.vinfoil.thread.ThreadHoldingConstraint3 thc3=new org.vinfoil.thread.ThreadHoldingConstraint3();
		org.vinfoil.thread.ThreadHoldingConstraint4 thc4=new org.vinfoil.thread.ThreadHoldingConstraint4();
		org.vinfoil.thread.ThreadHoldingConstraint5 thc5=new org.vinfoil.thread.ThreadHoldingConstraint5();
		org.vinfoil.thread.ThreadHoldingConstraint6 thc6=new org.vinfoil.thread.ThreadHoldingConstraint6();
		org.vinfoil.thread.ThreadHoldingConstraint7 thc7=new org.vinfoil.thread.ThreadHoldingConstraint7();
		org.vinfoil.thread.ThreadHoldingConstraint8 thc8=new org.vinfoil.thread.ThreadHoldingConstraint8();
		//sada moram da napravim nove bez embedinga
		
		thc1.setDiscreteLane1(matrixdis1);thc1.setDiscreteLane2(matrixdis1);
		thc1.setX1min(X1min);thc1.setX1max(X1max);
		thc1.setX2min(X2min);thc1.setX2max(X2max);
		thc1.setY1min(Y1min);thc1.setY1max(Y1max);
		thc1.setY2min(Y2min);thc1.setY2max(Y2max);
		thc1.setN1((int)newSet1.get(0).getWidth());thc1.setN2((int)newSet1.get(0).getWidth());
		thc1.setM((int)newSet1.get(0).getLength());thc1.setScanStep(20);
		
		thc2.setDiscreteLane1(matrixdis1);thc2.setDiscreteLane2(matrixdis1);
		thc2.setX1min(X1min);thc2.setX1max(X1max);
		thc2.setX2min(X2min);thc2.setX2max(X2max);
		thc2.setY1min(Y1min);thc2.setY1max(Y1max);
		thc2.setY2min(Y2min);thc2.setY2max(Y2max);
		thc2.setN1((int)newSet1.get(0).getWidth());thc2.setN2((int)newSet1.get(0).getWidth());
		thc2.setM((int)newSet1.get(0).getLength());thc2.setScanStep(20);
		
		thc3.setDiscreteLane1(matrixdis1);thc3.setDiscreteLane2(matrixdis1);
		thc3.setX1min(X1min);thc3.setX1max(X1max);
		thc3.setX2min(X2min);thc3.setX2max(X2max);
		thc3.setY1min(Y1min);thc3.setY1max(Y1max);
		thc3.setY2min(Y2min);thc3.setY2max(Y2max);
		thc3.setN1((int)newSet1.get(0).getWidth());thc3.setN2((int)newSet1.get(0).getWidth());
		thc3.setM((int)newSet1.get(0).getLength());thc3.setScanStep(20);
		
		thc4.setDiscreteLane1(matrixdis1);thc4.setDiscreteLane2(matrixdis1);
		thc4.setX1min(X1min);thc4.setX1max(X1max);
		thc4.setX2min(X2min);thc4.setX2max(X2max);
		thc4.setY1min(Y1min);thc4.setY1max(Y1max);
		thc4.setY2min(Y2min);thc4.setY2max(Y2max);
		thc4.setN1((int)newSet1.get(0).getWidth());thc4.setN2((int)newSet1.get(0).getWidth());
		thc4.setM((int)newSet1.get(0).getLength());thc4.setScanStep(20);
		
		thc5.setDiscreteLane1(matrixdis1);thc5.setDiscreteLane2(matrixdis1);
		thc5.setX1min(X1min);thc5.setX1max(X1max);
		thc5.setX2min(X2min);thc5.setX2max(X2max);
		thc5.setY1min(Y1min);thc5.setY1max(Y1max);
		thc5.setY2min(Y2min);thc5.setY2max(Y2max);
		thc5.setN1((int)newSet1.get(0).getWidth());thc5.setN2((int)newSet1.get(0).getWidth());
		thc5.setM((int)newSet1.get(0).getLength());thc5.setScanStep(20);
		
		thc6.setDiscreteLane1(matrixdis1);thc6.setDiscreteLane2(matrixdis1);
		thc6.setX1min(X1min);thc6.setX1max(X1max);
		thc6.setX2min(X2min);thc6.setX2max(X2max);
		thc6.setY1min(Y1min);thc6.setY1max(Y1max);
		thc6.setY2min(Y2min);thc6.setY2max(Y2max);
		thc6.setN1((int)newSet1.get(0).getWidth());thc6.setN2((int)newSet1.get(0).getWidth());
		thc6.setM((int)newSet1.get(0).getLength());thc6.setScanStep(20);
		
		thc7.setDiscreteLane1(matrixdis1);thc7.setDiscreteLane2(matrixdis1);
		thc7.setX1min(X1min);thc7.setX1max(X1max);
		thc7.setX2min(X2min);thc7.setX2max(X2max);
		thc7.setY1min(Y1min);thc7.setY1max(Y1max);
		thc7.setY2min(Y2min);thc7.setY2max(Y2max);
		thc7.setN1((int)newSet1.get(0).getWidth());thc7.setN2((int)newSet1.get(0).getWidth());
		thc7.setM((int)newSet1.get(0).getLength());thc7.setScanStep(20);
		
		thc8.setDiscreteLane1(matrixdis1);thc8.setDiscreteLane2(matrixdis1);
		thc8.setX1min(X1min);thc8.setX1max(X1max);
		thc8.setX2min(X2min);thc8.setX2max(X2max);
		thc8.setY1min(Y1min);thc8.setY1max(Y1max);
		thc8.setY2min(Y2min);thc8.setY2max(Y2max);
		thc8.setN1((int)newSet1.get(0).getWidth());thc8.setN2((int)newSet1.get(0).getWidth());
		thc8.setM((int)newSet1.get(0).getLength());thc8.setScanStep(20);*/
		
		/*thc1.start(); 
		thc2.start();
		thc3.run();
		thc4.start();
		thc5.start();
		thc6.start();
		thc7.start();
		thc8.start();*/
		
		
		/*org.vinfoil.thread.ThreadHoldingConstraint11 thc11=new org.vinfoil.thread.ThreadHoldingConstraint11();
		org.vinfoil.thread.ThreadHoldingConstraint21 thc21=new org.vinfoil.thread.ThreadHoldingConstraint21();
		org.vinfoil.thread.ThreadHoldingConstraint31 thc31=new org.vinfoil.thread.ThreadHoldingConstraint31();
		org.vinfoil.thread.ThreadHoldingConstraint41 thc41=new org.vinfoil.thread.ThreadHoldingConstraint41();
		org.vinfoil.thread.ThreadHoldingConstraint51 thc51=new org.vinfoil.thread.ThreadHoldingConstraint51();
		org.vinfoil.thread.ThreadHoldingConstraint61 thc61=new org.vinfoil.thread.ThreadHoldingConstraint61();
		org.vinfoil.thread.ThreadHoldingConstraint71 thc71=new org.vinfoil.thread.ThreadHoldingConstraint71();
		org.vinfoil.thread.ThreadHoldingConstraint81 thc81=new org.vinfoil.thread.ThreadHoldingConstraint81();
		//sada moram da napravim nove bez embedinga
		if(X1min-20<0)
			X1min=0;
		else X1min=X1min-20;
		if(X1max+20>newSet1.get(0).getWidth()-1)
			X1max=(int) newSet1.get(0).getWidth()-1;
		else X1max=X1max+20;
		if(Y1min-20<0)
			Y1min=0;
		else Y1min=Y1min-20;
		if(Y1max+20>newSet1.get(0).getLength()-1)
			Y1max=(int) newSet1.get(0).getLength()-1;
		else Y1max=Y1max+20;
		thc11.setDiscreteLane1(matrixdis2);thc11.setDiscreteLane2(matrixdis1);
		thc11.setX1min(X1min);thc11.setX1max(X1max);
		thc11.setX2min(X2min);thc11.setX2max(X2max);
		thc11.setY1min(Y1min);thc11.setY1max(Y1max);
		thc11.setY2min(Y2min);thc11.setY2max(Y2max);
		thc11.setN1((int)newSet1.get(0).getWidth());thc11.setN2((int)newSet1.get(0).getWidth());
		thc11.setM((int)newSet1.get(0).getLength());thc11.setScanStep(20);
		
		thc21.setDiscreteLane1(matrixdis2);thc21.setDiscreteLane2(matrixdis1);
		thc21.setX1min(X1min);thc21.setX1max(X1max);
		thc21.setX2min(X2min);thc21.setX2max(X2max);
		thc21.setY1min(Y1min);thc21.setY1max(Y1max);
		thc21.setY2min(Y2min);thc21.setY2max(Y2max);
		thc21.setN1((int)newSet1.get(0).getWidth());thc21.setN2((int)newSet1.get(0).getWidth());
		thc21.setM((int)newSet1.get(0).getLength());thc21.setScanStep(20);
		
		thc31.setDiscreteLane1(matrixdis2);thc31.setDiscreteLane2(matrixdis1);
		thc31.setX1min(X1min);thc31.setX1max(X1max);
		thc31.setX2min(X2min);thc31.setX2max(X2max);
		thc31.setY1min(Y1min);thc31.setY1max(Y1max);
		thc31.setY2min(Y2min);thc31.setY2max(Y2max);
		thc31.setN1((int)newSet1.get(0).getWidth());thc31.setN2((int)newSet1.get(0).getWidth());
		thc31.setM((int)newSet1.get(0).getLength());thc31.setScanStep(20);
		
		thc41.setDiscreteLane1(matrixdis2);thc41.setDiscreteLane2(matrixdis1);
		thc41.setX1min(X1min);thc41.setX1max(X1max);
		thc41.setX2min(X2min);thc41.setX2max(X2max);
		thc41.setY1min(Y1min);thc41.setY1max(Y1max);
		thc41.setY2min(Y2min);thc41.setY2max(Y2max);
		thc41.setN1((int)newSet1.get(0).getWidth());thc41.setN2((int)newSet1.get(0).getWidth());
		thc41.setM((int)newSet1.get(0).getLength());thc41.setScanStep(20);
		
		thc51.setDiscreteLane1(matrixdis2);thc51.setDiscreteLane2(matrixdis1);
		thc51.setX1min(X1min);thc51.setX1max(X1max);
		thc51.setX2min(X2min);thc51.setX2max(X2max);
		thc51.setY1min(Y1min);thc51.setY1max(Y1max);
		thc51.setY2min(Y2min);thc51.setY2max(Y2max);
		thc51.setN1((int)newSet1.get(0).getWidth());thc51.setN2((int)newSet1.get(0).getWidth());
		thc51.setM((int)newSet1.get(0).getLength());thc51.setScanStep(20);
		
		thc61.setDiscreteLane1(matrixdis2);thc61.setDiscreteLane2(matrixdis1);
		thc61.setX1min(X1min);thc61.setX1max(X1max);
		thc61.setX2min(X2min);thc61.setX2max(X2max);
		thc61.setY1min(Y1min);thc61.setY1max(Y1max);
		thc61.setY2min(Y2min);thc61.setY2max(Y2max);
		thc61.setN1((int)newSet1.get(0).getWidth());thc61.setN2((int)newSet1.get(0).getWidth());
		thc61.setM((int)newSet1.get(0).getLength());thc61.setScanStep(20);
		
		thc71.setDiscreteLane1(matrixdis2);thc71.setDiscreteLane2(matrixdis1);
		thc71.setX1min(X1min);thc71.setX1max(X1max);
		thc71.setX2min(X2min);thc71.setX2max(X2max);
		thc71.setY1min(Y1min);thc71.setY1max(Y1max);
		thc71.setY2min(Y2min);thc71.setY2max(Y2max);
		thc71.setN1((int)newSet1.get(0).getWidth());thc71.setN2((int)newSet1.get(0).getWidth());
		thc71.setM((int)newSet1.get(0).getLength());thc71.setScanStep(20);
		
		thc81.setDiscreteLane1(matrixdis2);thc81.setDiscreteLane2(matrixdis1);
		thc81.setX1min(X1min);thc81.setX1max(X1max);
		thc81.setX2min(X2min);thc81.setX2max(X2max);
		thc81.setY1min(Y1min);thc81.setY1max(Y1max);
		thc81.setY2min(Y2min);thc81.setY2max(Y2max);
		thc81.setN1((int)newSet1.get(0).getWidth());thc81.setN2((int)newSet1.get(0).getWidth());
		thc81.setM((int)newSet1.get(0).getLength());thc81.setScanStep(20);
		
		thc11.run(); 
		thc21.start();
		thc31.run();
		thc41.run();
		thc51.run();
		thc61.run();
		thc71.start();
		thc81.start();*/
		 ObtainingAllCombination item=new ObtainingAllCombination();
/*		 ArrayList <org.vinfoil.Combination> itemcomb=new ArrayList <org.vinfoil.Combination>();
		 itemcomb=item.ListOfComb(10, 4);
		 System.out.println("itemcomb br elem "+itemcomb.size());
		 for(int i=0;i<itemcomb.size();i++){
			 org.vinfoil.Combination lj=itemcomb.get(i);
			 int [] klj=new int [20];
			 klj=lj.getP();
			 for(int j=0;j<20;j++){
				 System.out.print(klj[j]);
				
			 }
			 System.out.print("\n");
		 }*/
		FillingCombinations fill=new FillingCombinations();
		ArrayList <Integer> inn=new ArrayList<Integer>();
		inn.add(1);inn.add(2);inn.add(3); inn.add(4); inn.add(5);inn.add(6);inn.add(7); inn.add(8);inn.add(9);inn.add(10);
		ArrayList <int []> combitem=new ArrayList <int []>();
		combitem=fill.Combinations(5, inn);
		System.out.println("broj elemenata "+combitem.size());
		
		/*for(int i=0;i<combitem.size();i++){
			int [] a=combitem.get(i);
			int y=0;
			while(y<a.length){
				System.out.print(a[y]);
				y++;
			}
			System.out.println("\n");
		}*/
		ArrayList <CombResult> item2=new ArrayList <CombResult>();
		int [] combinci={3,2};
		Combination combinaci=new Combination ();
		combinaci.setP(combinci);
		ArrayList <Integer> Skup=new ArrayList <Integer>();
		Skup.add(1);Skup.add(2);Skup.add(3);Skup.add(4);Skup.add(5);Skup.add(6);Skup.add(7);Skup.add(8);
		/*item2=fill.Filling2(combinaci, Skup, 5, 2);
		for(int i=0;i<item2.size();i++){
			org.vinfoil.CombResult ghsf=new org.vinfoil.CombResult();
			ghsf=item2.get(i);
			int [] dddd=ghsf.getVector();
			System.out.println("elementi vectora");
			for(int j=0;j<ghsf.getVector().length;j++){
				
				System.out.print(dddd[j]);
			}
			System.out.println("\n");
			System.out.println("kombinacija");
			for(int j=0;j<ghsf.getComb().size();j++){
				
				System.out.print(ghsf.getComb().get(j));
			}
			System.out.println("\n");
		}*/
		/*ArrayList <org.vinfoil.AllPossibleCombinationWithMerging> fgdhsafdh=new ArrayList <org.vinfoil.AllPossibleCombinationWithMerging>();
		fgdhsafdh=fill.FillForMerging(newSet1, 3);
		org.vinfoil.AllPossibleCombinationWithMerging fgfg=new org.vinfoil.AllPossibleCombinationWithMerging();
		System.out.println("broj skupova sa merging "+fgdhsafdh.size());
		for(int i=0;i<fgdhsafdh.size();i++){
			fgfg=fgdhsafdh.get(i);
			System.out.println("iz skupa "+i);
			for(int j=0;j<fgfg.getListOfLanes().size();j++){
				System.out.println("lane "+j);
				System.out.println("begins "+fgfg.getListOfLanes().get(j).getStart());
				System.out.println("ends "+fgfg.getListOfLanes().get(j).getEnd());	
			}
			
		}
		org.vinfoil.OneOnTwoRepass oneOnTwo=new org.vinfoil.OneOnTwoRepass();
		oneOnTwo.OneOnTwoRepassRun(newSet1.get(0), newSet1.get(0), newSet1.get(0), Shape, 20);*/
		Strategy3and4 rezultat=new Strategy3and4();
		rezultat.Strategy3AND4(newSet1, 5, offset, paperwidth1, Shape, 20,IFR);


		 //Using drawing to show results
      /*  org.vinfoil.DrawPanels panel1 = new org.vinfoil.DrawPanels(test1,test2);
        panel1.allPatterns=Shape;
        //panel1.Direction=res.getDirection();
        panel1.Direction=8;
        panel1.myFWL1=test1;
        panel1.myFWL2=test2;
        //panel1.ShiftX=res.getShiftX();
        panel1.ShiftX=20;
        //panel1.ShiftY=res.getShiftY();
        panel1.ShiftY=20;
        // window for drawing
        //    // window for drawing
        System.out.println(panel1.myFWL1.getLength());
        JFrame application1 = new JFrame();      
        application1.setTitle("Repassing Results");

        application1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // set frame to exit
        // when it is closed
        application1.add(panel1);

        application1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        application1.setVisible(true);*/
        
        
		
		
		
		
		
		
		
		/*for(int i=0;i<(int)experimentdis.getWidth();i++){
			for(int j=0;j<experimentdis.getLength();j++){
				System.out.print(matrix1[i][j]+"");
			}
			System.out.print("\n");
		}*/
	   /* org.vinfoil.Parallelogram ja=new org.vinfoil.Parallelogram ();
	    ja.setX11(10);
	    ja.setY11(15);6
	    ja.setX12(20);
	    ja.setY12(15);
	    ja.setX13(23);
	    ja.setY13(30);
	    ja.setX14(13);
	    ja.setY14(30);
	    
	    org.vinfoil.IntegerPoint F=new org.vinfoil.IntegerPoint ();
	    F.setX(12);
	    F.setY(16);
	    org.vinfoil.Line sss=new org.vinfoil.Line ();
	    System.out.println(sss.PointInsideParallelogram2(ja, F));
	    int [][] matrix2= new int [50][50];
	    for(int i=0;i<50;i++)
	    	for(int j=0;j<50;j++)
	    		matrix2[i][j]=0;
	    ArrayList <org.vinfoil.IntegerPoint> segments1=new ArrayList <org.vinfoil.IntegerPoint>();
	    ArrayList <org.vinfoil.IntegerPoint> segments2=new ArrayList <org.vinfoil.IntegerPoint>();
	    ArrayList <org.vinfoil.IntegerPoint> segments3=new ArrayList <org.vinfoil.IntegerPoint>();
	    ArrayList <org.vinfoil.IntegerPoint> segments4=new ArrayList <org.vinfoil.IntegerPoint>();
	    //setting 1 for all rectangles 
			org.vinfoil.Parallelogram rec=ja;
			//finding min and max for for loops
			int Xmin=(int) Math.min(rec.getX11(), rec.getX12());
			Xmin=(int) Math.min(Xmin, rec.getX13());
			Xmin=(int) Math.min(Xmin, rec.getX14());
			int Xmax=(int) Math.max(rec.getX11(), rec.getX12());
			Xmax=(int) Math.max(Xmax, rec.getX13());
			Xmax=(int) Math.max(Xmax, rec.getX14());
			int Ymin=(int) Math.min(rec.getY11(), rec.getY12());
			Ymin=(int) Math.min(Ymin, rec.getY13());
			Ymin=(int) Math.min(Ymin, rec.getY14());
			int Ymax=(int) Math.max(rec.getY11(), rec.getY12());
			Ymax=(int) Math.max(Ymax, rec.getY13());
			Ymax=(int) Math.max(Ymax, rec.getY14());
			org.vinfoil.IntegerPoint A = new org.vinfoil.IntegerPoint ();
			org.vinfoil.IntegerPoint B = new org.vinfoil.IntegerPoint ();
			org.vinfoil.IntegerPoint C= new org.vinfoil.IntegerPoint ();
			org.vinfoil.IntegerPoint D= new org.vinfoil.IntegerPoint ();
			
			//A.setX((int)rec.getX11()-c);
			A.setX((int)rec.getX11());
			A.setY((int)rec.getY11());
			B.setX((int)rec.getX12());
			B.setY((int)rec.getY12());
			C.setX((int)rec.getX13());
			C.setY((int)rec.getY13());
			D.setX((int)rec.getX14());
			D.setY((int)rec.getY14());
			org.vinfoil.Parallelogram rectemp=new org.vinfoil.Parallelogram ();
			rectemp.setX11(A.getX());
			rectemp.setX12(B.getX());
			rectemp.setY11(A.getY());
			rectemp.setY12(B.getY());
			rectemp.setX13(C.getX());
			rectemp.setY13(C.getY());
			rectemp.setX14(D.getX());
			rectemp.setY14(D.getY());
			segments1=sss.SegmentDiscretization(A, B);
			
			int u1=segments1.size()-1;
			while(u1!=0){//setting all fields from segment AB to 1
				org.vinfoil.IntegerPoint E=new org.vinfoil.IntegerPoint();
						E=segments1.get(u1);
				matrix2[E.getX()][E.getY()]=1;

				u1--;
			}
			segments2=sss.SegmentDiscretization(B, C);
			int u2=segments2.size()-1;
			while(u2!=0){//setting all fields from segment BC to 1
				org.vinfoil.IntegerPoint E1=new org.vinfoil.IntegerPoint();
				E1=segments2.get(u2);
				matrix2[E1.getX()][E1.getY()]=1;
				u2--;
			}
			segments3=sss.SegmentDiscretization(C, D);
			int u3=segments3.size()-1;
			while(u3!=0){//setting all fields from segment CD to 1
				org.vinfoil.IntegerPoint E=new org.vinfoil.IntegerPoint();
				E=segments3.get(u3);
				matrix2[E.getX()][E.getY()]=1;
				u3--;
			}
			segments4=sss.SegmentDiscretization(D, A);
			int u4=segments4.size()-1;
			while(u4!=0){//setting all fields from segment DA to 1
				org.vinfoil.IntegerPoint E=new org.vinfoil.IntegerPoint();
				E=segments4.get(u4);
				matrix2[E.getX()][E.getY()]=1;
				u4--;
			}
			//setting all elements inside rectangle to 1
			for(int i=Xmin+1;i<Xmax;i++)
				for(int j=Ymin+1;j<Ymax;j++){
					org.vinfoil.IntegerPoint F1=new org.vinfoil.IntegerPoint ();
					F1.setX(i);
					F1.setY(j);
					boolean inside=false;
					inside=sss.PointInsideParallelogram2(rectemp, F1);
					//System.out.println(inside);
					if(inside==true){
						matrix2[i][j]=1;
					}
				}
			
			for(int i=0;i<50;i++){
				for(int j=0;j<50;j++){
					System.out.print(matrix2[i][j]+"");
				}
				System.out.print("\n");
			}*/


		newSet=Stra2.St2(newSet1, paperwidth, P.getOffsetx1(), NK);
		System.out.println("number of lanes after strategy 2 "+newSet.size());
		 for(int i=0;i<newSet.size();i++)
		  {

			 System.out.println("Lane "+i);
				System.out.println("begins "+newSet.get(i).getStart());
				System.out.println("ends "+newSet.get(i).getEnd());
				System.out.println("and is of width "+newSet.get(i).getWidth());
		 }

		 ArrayList <Float> distance=new ArrayList <Float> ();
		 for(int i=0;i<newSet.size()-1;i++){
			 distance.add(newSet.get(i+1).getEnd()-newSet.get(i).getStart());
		 }

		 float mnwidth2=0;
		 for(int i=0;i<newSet.size();i++)
				mnwidth2+=newSet.get(i).getWidth();
			System.out.println("Minimum needed width before rounding "+mnwidth2);
			System.out.println("The foil consumption without rounding "+(mnwidth2*PS.getCPC())/10000);


		 KnivesConstraints round=new KnivesConstraints();
		 newSet=round.RoundingLanaesToResolution(newSet, paperwidth, P.getOffsetx1());
		 System.out.println("after rounding");
		 
		
		 
		/* for(int i=0;i<newSet.size();i++)
		  {
			 
			 System.out.println("Lane "+i);
				System.out.println("begins "+newSet.get(i).Start);
				System.out.println("ends "+newSet.get(i).End);
				System.out.println("and is of width "+newSet.get(i).Width);
		 }*/
		for(int i=0;i<newSet.size();i++)
			mnwidth+=newSet.get(i).getWidth();
		System.out.println("Minimum needed width "+mnwidth);
		System.out.println("The foil consumption after rounding "+(mnwidth*PS.getCPC())/10000);



		while((in<IFRW.size())&&mnwidth>IFRW.get(in)){

			in++;
		}
		System.out.println("oo"+in);
		if(in==0)
			extension=IFRW.get(0)-mnwidth;
		else extension=IFRW.get(in)-mnwidth;
		System.out.println("extension "+extension);

		if(extension!=0){
		newSet2=e.ExtendingAll1(newSet, (int) extension, paperwidth, P.getOffsetx1());
		System.out.println(newSet2.size());
		numOfLanes=newSet2.size();
		for(int i=0;i<newSet2.size();i++){
			mnwidth1+=newSet2.get(i).getWidth();
			System.out.println("Lane "+i);
			System.out.println("begins "+newSet2.get(i).getStart());

			System.out.println("ends "+newSet2.get(i).getEnd());
			System.out.println("and is of width "+newSet2.get(i).getWidth());
		}
		System.out.println("Initial foil roll  "+mnwidth1);

		} else{
			numOfLanes=newSet.size();
			for(int i=0;i<newSet.size();i++)
			  {

				 System.out.println("Lane "+i);
					System.out.println("begins "+newSet.get(i).getStart());
					System.out.println("ends "+newSet.get(i).getEnd());
					System.out.println("and is of width "+newSet.get(i).getWidth());
			 }
		}
		if(mnwidth1==0){
			System.out.println("Total foil consumption for one paper sheet is "+(mnwidth*PS.getCPC())/10000 +"cm2");
			foilWidth=mnwidth;
			System.out.println("Initial foil roll  "+foilWidth);
		}
		else{
		System.out.println("Total foil consumption for one paper sheet is "+(mnwidth1*PS.getCPC())/10000 +"cm2");
		foilWidth=mnwidth1;
		System.out.println("Initial foil roll  "+foilWidth);
		}



		try {
            int str = (int) mnwidth;
           // File newTextFile = new File("C:\\Users\\sstojano\\workspace\\Vinfoil\\thetextfile.txt");


            File outFile = new File ("output.txt");
            FileWriter fWriter = new FileWriter (outFile);
            PrintWriter pWriter = new PrintWriter (fWriter);
            pWriter.println (numOfLanes);//number  of lanes
            pWriter.println (0);//A buttons
            pWriter.println (0);//B buttons
            pWriter.println (0);//Winder Selected
            pWriter.println (P.getPaperWidth());//Sheet width
            pWriter.println (foilWidth);//Foil width
            if(extension==0){
            	for(int i=0;i<newSet.size();i++){
            		pWriter.println (newSet.get(i).getStart());//position i
            		            	}
            	for(int i=7-newSet.size();i<=7;i++){
            		pWriter.println (0);//position

            	}
            	for(int i=0;i<newSet.size();i++){
            		pWriter.println (newSet.get(i).getWidth());// Width position 1
            	}
            	for(int i=7-newSet.size();i<=7;i++){
            		pWriter.println (0);// Width position 1
            	}

            }else{
            	for(int i=0;i<newSet2.size();i++){
            		pWriter.println (newSet2.get(i).getStart());//position i
            	}
            	for(int i=7-newSet2.size();i<=7;i++){
            		pWriter.println (0);//position
            	}
            	for(int i=0;i<newSet2.size();i++){
            		pWriter.println (newSet2.get(i).getWidth());// Width position 1
            	}
            	for(int i=7-newSet2.size();i<=7;i++){
            		pWriter.println (0);// Width position 1
            	}

            }

            pWriter.println (0);// Offset position 1
            pWriter.println (0);// Offset position 2
            pWriter.println (0);// Offset position 3
            pWriter.println (0);// Offset position 4
            pWriter.println (0);// Offset position 5
            pWriter.println (0);// Offset position 6
            pWriter.println (0);// Offset position 7


            pWriter.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }

		while (Thread.activeCount() > 2) {
		}

		long estimatedTime = System.nanoTime() - startTime;
		System.out.println("Execution time in nanosec "+estimatedTime);
		double ext=estimatedTime/1000000000;
		System.out.println("Execution time in sec "+ext);


	}






}
