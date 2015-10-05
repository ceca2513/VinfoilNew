package org.vinfoil;

import java.util.*;

public class Moving {
	
	public Moving(){
		
	}
	//will return 2 elements of the list the first position of lane2 and the second position of lane3
	//it doesn't deal with exceptions direction 0 is not supported
	// s is current element of dir1list
	public ArrayList<HoldingResult> MovingForEmbeding( int ScaningStep, int N1, int M1, int [][] matrix1, int [][] matrix2, int [][] matrix3, int X1min, int X1max, int X2min, int X2max, int X3min, int X3max, int Y1min, int Y1max, int Y2min, int Y2max, int Y3min, int Y3max, Direction1andDirection2 P){
		ArrayList<HoldingResult> templist=new ArrayList <HoldingResult>();
		//int [][] matrix8=new int [N1][M1];
		//int [][] matrix9=new int [N1][M1];
		ZeroPadding zp=new ZeroPadding ();
		Overlaping over=new Overlaping ();
		HoldingResult temp=new HoldingResult ();
		HoldingResult temp1=new HoldingResult ();
		ArrayList<Direction1andDirection2> listOfEmbedding = new ArrayList<Direction1andDirection2> ();
		//boolean t2=false;
		//shiftovi su vec sa odgovarajucim +- u sebi treba samo da dodas shiftove ili prekucaj onaj deo gde unistavas znak
		//*****
		P.setShiftX1((int) (P.getShiftX1()*Math.signum(P.getShiftX1())));
		P.setShiftX2((int) (P.getShiftX2()*Math.signum(P.getShiftX2())));
		P.setShiftY1((int) (P.getShiftY1()*Math.signum(P.getShiftY1())));
		P.setShiftY2((int) (P.getShiftY2()*Math.signum(P.getShiftY2())));
		
		//as a return value we have to elements of holding results 1 one sets the  lane1 in its position second the  lane2
		 if(P.getDirection1()==1){
			int dir=P.getDirection2();
			switch (dir){
			case 1:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 2:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min, Y3max);
				break;
			case 3:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 4:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 5:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 6:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min, Y3max);
				break;
			case 7:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 8:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			}
		
		}else if(P.getDirection1()==2){
			int dir=P.getDirection2();
			switch (dir){
			case 1:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 2:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
				break;
			case 3:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 4:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 5:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 6:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
				break;
			case 7:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 8:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			}
		}else if(P.getDirection1()==3){
			int dir=P.getDirection2();
			switch (dir){
			case 1:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 2:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min, Y3max);
				break;
			case 3:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 4:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 5:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 6:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min, Y3max);
				break;
			case 7:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 8:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min-P.getShiftX1(), X2max-P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			}
		}else if(P.getDirection1()==4){
			int dir=P.getDirection2();
			switch (dir){
			case 1:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 2:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min, Y3max);
				break;
			case 3:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 4:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 5:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 6:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min, Y3max);
				break;
			case 7:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 8:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			}
		}else if(P.getDirection1()==5){
			int dir=P.getDirection2();
			switch (dir){
			case 1:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 2:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min, Y3max);
				break;
			case 3:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 4:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 5:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 6:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min, Y3max);
				break;
			case 7:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 8:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min+P.getShiftY1(), Y2max+P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			}
		}else if(P.getDirection1()==6){
			int dir=P.getDirection2();
			switch (dir){
			case 1:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 2:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
				break;
			case 3:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 4:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 5:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 6:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
				break;
			case 7:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min, Y2max, Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 8:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			}
		}else if(P.getDirection1()==7){
			int dir=P.getDirection2();
			switch (dir){
			case 1:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 2:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min, Y3max);
				break;
			case 3:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 4:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 5:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 6:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min, Y3max);
				break;
			case 7:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 8:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min+P.getShiftX1(), X2max+P.getShiftX1(), X3min, X3max, Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			}
		}else if(P.getDirection1()==8){
			int dir=P.getDirection2();
			switch (dir){
			case 1:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 2:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min, Y3max);
				break;
			case 3:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-P.getShiftX2(), X3max-P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 4:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 5:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min+P.getShiftY2(), Y3max+P.getShiftY2());
				break;
			case 6:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min, Y3max);
				break;
			case 7:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+P.getShiftX2(), X3max+P.getShiftX2(), Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			case 8:
				listOfEmbedding=over.ScaningForEmbedingWithThreeLanes(matrix1, matrix2, matrix3, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-P.getShiftY1(), Y2max-P.getShiftY1(), Y3min-P.getShiftY2(), Y3max-P.getShiftY2());
				break;
			}
		}
		 //you are getting out of this loop with only one lisOfEmbeding, because only one case fits!
		 //listOfEmbeding can be zero
		
		if(listOfEmbedding.get(0).getDirection2()!=0){//moze prvi da bude nula a da neki od ostalih elemenata ne bude nula, proveri zasto!!!!!
			
		
			//listOfEmbeding can have more then one element we take the first one
			temp.setRepassing(true);
			temp.setDirection(1);
			temp.setShiftX(P.getShiftX1()+listOfEmbedding.get(0).getShiftX1());
			temp.setShiftY(P.getShiftY1()+listOfEmbedding.get(0).getShiftY1());
			templist.add(0, temp);
			temp1.setRepassing(true);
			temp1.setDirection(P.getDirection2());
			temp1.setShiftX(P.getShiftX2()+listOfEmbedding.get(0).getShiftX2());
			temp1.setShiftY(P.getShiftY2()+listOfEmbedding.get(0).getShiftY2());
			templist.add(1, temp1);
			
			
		}else{//it is impossible to embed this lane whit this ScaningStep distance
			temp.setRepassing(false);
			temp.setDirection(0);
			temp.setShiftX(0);
			temp.setShiftY(0);
			templist.add(0, temp);
			templist.add(1, temp);
			
		}
					return templist;
	}
	public Direction1andDirection2 Moving2(int [][] matrix1, int [][] matrix2, int [][] matrix3, int n, int m, int ScaningStep, int X1min, int X1max, int X2min, int X2max, int X3min, int X3max, int Y1min, int Y1max, int Y2min, int Y2max, int Y3min, int Y3max){
		Direction1andDirection2 temp=new Direction1andDirection2 (0,0,0,0,0,0);
		Overlaping over=new Overlaping ();
		//lane2: x+ss;y+0; lane3: x+ss;y+0
		//lane2: x+ss;y+0; lane3: x+ss; y+ss
		//lane2: x+ss;y+0; lane3: x+ss; y-ss;
		
		//lane2: x+ss;y+0; lane3: x+0; y+0;
		
		//lane2: x+ss;y+0; lane3: x+0; y+ss;
		//lane2: x+ss;y+0; lane3: x+0; y-ss;
		//lane2: x+ss;y+0; lane3: x-ss; y+0;
		//lane2: x+ss;y+0; lane3: x-ss; y+ss;
		//lane2: x+ss;y+0; lane3: x-ss; y-ss;
		//lane2: x+ss;y+ss; lane3: x+ss;y+0
		//lane2: x+ss;y+ss; lane3: x+ss;y+ss
		//lane2: x+ss;y+ss; lane3: x+ss;y-ss
		
		//lane2: x+ss;y+ss; lane3: x+0;y+0
		
		//lane2: x+ss;y+ss; lane3: x+0;y+ss
		//lane2: x+ss;y+ss; lane3: x+0;y-ss
		//lane2: x+ss;y+ss; lane3: x-ss;y+0
		//lane2: x+ss;y+ss; lane3: x-ss;y+ss
		//lane2: x+ss;y+ss; lane3: x-ss;y-ss
		//lane2: x+ss;y-ss; lane3: x+ss;y+0
		//lane2: x+ss;y-ss; lane3: x+ss;y+ss
		//lane2: x+ss;y-ss; lane3: x+ss;y-ss
		
		//lane2: x+ss;y-ss; lane3: x+0;y+0
		
		//lane2: x+ss;y-ss; lane3: x+0;y+ss
		//lane2: x+ss;y-ss; lane3: x+0;y-ss
		//lane2: x+ss;y-ss; lane3: x-ss;y+0
		//lane2: x+ss;y-ss; lane3: x-ss;y+ss
		//lane2: x+ss;y-ss; lane3: x-ss;y-ss
		//lane2: x+0;y-ss; lane3: x+ss;y+0
		//lane2: x+0;y-ss; lane3: x+ss;y+ss
		//lane2: x+0;y-ss; lane3: x+ss;y-ss
		
		//lane2: x+0;y-ss; lane3: x+0;y+0
		
		//lane2: x+0;y-ss; lane3: x+0;y+ss
		//lane2: x+0;y-ss; lane3: x+0;y-ss
		//lane2: x+0;y-ss; lane3: x-ss;y+0
		//lane2: x+0;y-ss; lane3: x-ss;y+ss
		//lane2: x+0;y-ss; lane3: x-ss;y-ss
		//lane2: x+0;y+ss; lane3: x+ss;y+0
		//lane2: x+0;y+ss; lane3: x+ss;y+ss
		//lane2: x+0;y+ss; lane3: x+ss;y-ss
		
		//lane2: x+0;y+ss; lane3: x+0;y+0
		
		//lane2: x+0;y+ss; lane3: x+0;y+ss
		//lane2: x+0;y+ss; lane3: x+0;y-ss
		//lane2: x+0;y+ss; lane3: x-ss;y+0
		//lane2: x+0;y+ss; lane3: x-ss;y+ss
		//lane2: x+0;y+ss; lane3: x-ss;y-ss
		
		//lane2: x+0;y+0; lane3: x+ss;y+0
		
		//lane2: x-ss;y+ss; lane3: x+ss;y+0
		//lane2: x-ss;y+ss; lane3: x+ss;y+ss
		//lane2: x-ss;y+ss; lane3: x+ss;y-ss
		
		//lane2: x-ss;y+ss; lane3: x+0;y+0
		
		//lane2: x-ss;y+ss; lane3: x+0;y+ss
		//lane2: x-ss;y+ss; lane3: x+0;y-ss
		//lane2: x-ss;y+ss; lane3: x-ss;y+0
		//lane2: x-ss;y+ss; lane3: x-ss;y+ss
		//lane2: x-ss;y+ss; lane3: x-ss;y-ss
		//lane2: x-ss;y+0; lane3: x+ss;y+0
		//lane2: x-ss;y+0; lane3: x+ss;y+ss
		//lane2: x-ss;y+0; lane3: x+ss;y-ss
		
		//lane2: x-ss;y+0; lane3: x+0;y+0
		
		//lane2: x-ss;y+0; lane3: x+0;y+ss
		//lane2: x-ss;y+0; lane3: x+0;y-ss
		//lane2: x-ss;y+0; lane3: x-ss;y+0
		//lane2: x-ss;y+0; lane3: x-ss;y+ss
		//lane2: x-ss;y+0; lane3: x-ss;y-ss
		
		if(Y2max+ScaningStep<=m&&Y3max+2*ScaningStep<=m&&X2max+ScaningStep<=n&&X3max+2*ScaningStep<=n){
			boolean shift0=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix3, matrix3, X1min, X1max, X2min+ScaningStep, X2max+ScaningStep, X3min+2*ScaningStep, X3max+2*ScaningStep, Y1min, Y1max, Y2min+ScaningStep, Y2max+ScaningStep, Y3min+2*ScaningStep, Y3max+2*ScaningStep);
			if(shift0==false){
				Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
				intP1.seDirection1(0);
				intP1.setDirection2(0);
				intP1.setShiftX1(ScaningStep);
				intP1.setShiftY1(ScaningStep);
				intP1.setShiftX2(2*ScaningStep);
				intP1.setShiftY2(2*ScaningStep);
				temp=intP1;
				
			}	
		}
		
		if(temp.getShiftX1()==0){//+-
			if(X2max+ScaningStep<=n&&X3min-ScaningStep>=0&&Y2max+ScaningStep<=m&&Y3min-ScaningStep>=0){
				boolean shift01=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix2, matrix3, X1min, X1max, X2min+ScaningStep, X2max+ScaningStep, X3min-ScaningStep, X3max-ScaningStep, Y1min, Y1max, Y2min+ScaningStep, Y2max+ScaningStep, Y3min-ScaningStep, Y3max-ScaningStep);
				if(shift01==false){
					Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
					intP1.seDirection1(4);
					intP1.setDirection2(1);
					intP1.setShiftX1(ScaningStep);
					intP1.setShiftY1(ScaningStep);
					intP1.setShiftX2(-ScaningStep);
					intP1.setShiftY2(-ScaningStep);
					temp=intP1;
				}	
			}
			
			if(temp.getShiftX1()==0){//+-2
				if(X2max+ScaningStep<=n&&X3min-2*ScaningStep>=0&&Y2max+ScaningStep<=m&&Y3min-2*ScaningStep>=0){
					boolean shift011=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix2, matrix3, X1min, X1max, X2min+ScaningStep, X2max+ScaningStep, X3min-2*ScaningStep, X3max-2*ScaningStep, Y1min, Y1max, Y2min+ScaningStep, Y2max+ScaningStep, Y3min-2*ScaningStep, Y3max-2*ScaningStep);
					if(shift011==false){
						Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
						intP1.seDirection1(4);
						intP1.setDirection2(1);
						intP1.setShiftX1(ScaningStep);
						intP1.setShiftY1(ScaningStep);
						intP1.setShiftX2(-2*ScaningStep);
						intP1.setShiftY2(-2*ScaningStep);
						temp=intP1;
					}	
				}
				
				if(temp.getShiftX1()==0){
					//-+
					if(X2min-ScaningStep>=0&&X3max+ScaningStep<=n&&Y2min-ScaningStep>=0&&Y3max+ScaningStep<=m){
						boolean shift02=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix2, matrix3, X1min, X1max, X2min-ScaningStep, X2max-ScaningStep, X3min+ScaningStep, X3max+ScaningStep, Y1min, Y1max, Y2min-ScaningStep, Y2max-ScaningStep, Y3min+ScaningStep, Y3max+ScaningStep);
						if(shift02==false){
							Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
							intP1.seDirection1(4);
							intP1.setDirection2(1);
							intP1.setShiftX1(-ScaningStep);
							intP1.setShiftY1(-ScaningStep);
							intP1.setShiftX2(ScaningStep);
							intP1.setShiftY2(ScaningStep);
							temp=intP1;
						}
					}
					
					if(temp.getShiftX1()==0){//-+2
						if(X2min-ScaningStep>=0&&Y2min-ScaningStep>=0){
							boolean shift021=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix2, matrix3, X1min, X1max, X2min-ScaningStep, X2max-ScaningStep, X3min, X3max, Y1min, Y1max, Y2min-ScaningStep, Y2max-ScaningStep, Y3min, Y3max);
							if(shift021==false){
								Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
								intP1.seDirection1(4);
								intP1.setDirection2(1);
								intP1.setShiftX1(-ScaningStep);
								intP1.setShiftY1(-ScaningStep);
								intP1.setShiftX2(2*ScaningStep);
								intP1.setShiftY2(2*ScaningStep);
								temp=intP1;
							}
						}
						
						if(temp.getShiftX1()==0){//--
							if(X2min-ScaningStep>=0&&X3min-2*ScaningStep>=0&&Y2min-ScaningStep>=0&&Y3min-ScaningStep>=0){
								boolean shift03=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix2, matrix3, X1min, X1max, X2min-ScaningStep, X2max-ScaningStep, X3min-2*ScaningStep, X3max-2*ScaningStep, Y1min, Y1max, Y2min-ScaningStep, Y2max-ScaningStep, Y3min-2*ScaningStep, Y3max-2*ScaningStep);
								if(shift03==false){
									Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
									intP1.seDirection1(4);
									intP1.setDirection2(1);
									intP1.setShiftX1(-ScaningStep);
									intP1.setShiftY1(-ScaningStep);
									intP1.setShiftX2(-2*ScaningStep);
									intP1.setShiftY2(-2*ScaningStep);
									temp=intP1;
								}	
							}
							
						}
						
						
					}
					
				}
				
			}
			
		}
		//+-
		
		return temp;
	}
	public Direction1andDirection2 MovingNew(int [][] matrix1, int [][] matrix2, int [][] matrix3, int ScanStep,int N1, int M1, int X1min, int X1max, int X2min, int X2max, int X3min, int X3max, int Y1min, int Y1max, int Y2min, int Y2max, int Y3min, int Y3max){
		Direction1andDirection2 P=new Direction1andDirection2(0,0,0,0,0,0);
		boolean t=false;//promenljiva koja obezbedjuje prvi hit, kad je ona tru tada izlazimo iz ove funkcije
		//prvo obe simultano shiftujem u jednom od osam pravaca zelim da me izvaci na prvi hit
		int [][] matrixx2=new int [N1][M1];
		int [][] matrixx3=new int [N1][M1];
		int [][] matrixx4=new int [N1][M1];
		ZeroPadding zp=new ZeroPadding();
		int dir1=1; int dir2=1;
		Overlaping over=new Overlaping ();
		while(t==false&&dir1<9&&dir2<5){
			switch(dir1){
			case 1://obe su pomerene u smeru 1 za po scanstep
				if(X2min-ScanStep>=0&&Y2min-ScanStep>=0&&X3min-ScanStep>=0&&Y3min-ScanStep>=0){
					matrixx2=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 1);
					matrixx3=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 1);
					switch(dir2){
					case 1:
						if(X3min-2*ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2,matrixx4, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t2==false){
							P.seDirection1(1);
							P.setDirection2(2);
							P.setShiftX1(ScanStep);
							P.setShiftY1(ScanStep);
							P.setShiftX2(2*ScanStep);
							P.setShiftY2(ScanStep);
							t=true;
						}
						}
					break;
					case 2:
						if(Y3min-ScanStep>=0){
							matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
							boolean t6=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
							if(t6==false){
								P.seDirection1(1);
								P.setDirection2(6);
								P.setShiftX1(ScanStep);
								P.setShiftY1(ScanStep);
								P.setShiftX2(0);
								P.setShiftY2(ScanStep);
								t=true;
							}
						}
					break;
					case 3:
						if(X3min-ScanStep>=0&&Y3min-2*ScanStep>=0){
							matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
							boolean t8=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
							if(t8==false){
								P.seDirection1(1);
								P.setDirection2(8);
								P.setShiftX1(ScanStep);
								P.setShiftY1(ScanStep);
								P.setShiftX2(ScanStep);
								P.setShiftY2(2*ScanStep);
								t=true;
							}
							}
					break;
					case 4:
						if(X3min-ScanStep>=0){
							matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
							boolean t4=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max,X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max,Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
							if(t4==false){
								P.seDirection1(1);
								P.setDirection2(4);
								P.setShiftX1(ScanStep);
								P.setShiftY1(ScanStep);
								P.setShiftX2(ScanStep);
								P.setShiftY2(0);
								t=true;
							}
					}
					break;
					}
					dir2++;
				}
			break;
			case 2:
				if(X2min-ScanStep>=0&&X3min-ScanStep>=0){
					matrixx2=zp.Shifting(matrix2, ScanStep, 0, N1, M1, 2);
					matrixx3=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 2);
				switch(dir2){
				case 1:
					if(X3min-2*ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2,matrixx4, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
						if(t2==false){
							P.seDirection1(2);
							P.setDirection2(2);
							P.setShiftX1(ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(2*ScanStep);
							P.setShiftY2(0);
							t=true;
						}
						}
				break;
				case 2:
					matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
					boolean t6=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
					if(t6==false){
						P.seDirection1(2);
						P.setDirection2(8);
						P.setShiftX1(ScanStep);
						P.setShiftY1(0);
						P.setShiftX2(0);
						P.setShiftY2(0);
						t=true;
					}
				break;
				case 3:
					if(X3min-ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
						if(t8==false){
							P.seDirection1(2);
							P.setDirection2(8);
							P.setShiftX1(ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(ScanStep);
							P.setShiftY2(0);
							t=true;
						}
						}
				break;
				case 4:
					if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max,X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max,Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t4==false){
							P.seDirection1(2);
							P.setDirection2(4);
							P.setShiftX1(ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}
						}
				break;
				}
				dir2++;
				}
			break;
			case 3:
				if(X2min-ScanStep>=0&&Y2max+ScanStep<=M1&&X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
					matrixx2=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 3);
					matrixx3=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 3);
				
				switch(dir2){
				case 1:
					if(X3min-2*ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t2==false){
							P.seDirection1(3);
							P.setDirection2(2);
							P.setShiftX1(ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(2*ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 2:
					if(Y3max+ScanStep<=M1){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t6==false){
							P.seDirection1(3);
							P.setDirection2(6);
							P.setShiftX1(ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 3:
					if(X3min-ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t8==false){
							P.seDirection1(3);
							P.setDirection2(8);
							P.setShiftX1(ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(ScanStep);
							P.setShiftY2(0);
							t=true;
						}
						
					}
				break;
				case 4:
					if(X3min-ScanStep>=0&&Y3max+2*ScanStep<=M1){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
						if(t4==false){
							P.seDirection1(3);
							P.setDirection2(4);
							P.setShiftX1(ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(2*ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				}
				dir2++;
				}
			break;
			case 4:
				if(Y2max+ScanStep<=M1&&Y3max+ScanStep<=M1){
					matrixx2=zp.Shifting(matrix2, 0, ScanStep, N1, M1, 4);
					matrixx3=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 4);	
				
				switch(dir2){
				case 1:
					if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t2==false){
							P.seDirection1(4);
							P.setDirection2(2);
							P.setShiftX1(0);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 2:
					if(X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t6==false){
							P.seDirection1(4);
							P.setDirection2(6);
							P.setShiftX1(0);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 3:
					matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
					boolean t8=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
					if(t8==false){
						P.seDirection1(4);
						P.setDirection2(8);
						P.setShiftX1(0);
						P.setShiftY1(-ScanStep);
						P.setShiftX2(0);
						P.setShiftY2(0);
						t=true;
					}
				break;
				case 4:
					if(Y3max+2*ScanStep<=M1){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
						if(t4==false){
							P.seDirection1(4);
							P.setDirection2(4);
							P.setShiftX1(0);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(-2*ScanStep);
							t=true;
						}
					}
				break;
				}
				dir2++;
				}
			break;
			case 5:
				if(X2max+ScanStep<=N1&&Y2max+ScanStep<=M1&&X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
					matrixx2=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 5);
					matrixx3=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 5);
				
				switch(dir2){
				case 1:
					if(Y3min+ScanStep<=M1){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t2==false){
							P.seDirection1(5);
							P.setDirection2(2);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 2:
					if(X3max+2*ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t6==false){
							P.seDirection1(5);
							P.setDirection2(6);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(-2*ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 3:
					if(X3max+ScanStep<=N1){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t8==false){
							P.seDirection1(5);
							P.setDirection2(8);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(0);
							t=true;
						}
					}
				break;
				case 4:
					if(X3max+ScanStep<=N1&&Y3max+2*ScanStep<=M1){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
						if(t4==false){
							P.seDirection1(5);
							P.setDirection2(4);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(-2*ScanStep);
							t=true;
						}
					}	
				break;
				}
				dir2++;
				}
			break;
			case 6:
				if(X2max+ScanStep<=N1&&X3max+ScanStep<=N1){
					matrixx2=zp.Shifting(matrix2, ScanStep, 0, N1, M1, 6);
					matrixx3=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 6);
				switch(dir2){
				case 1:
					matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
					boolean t2=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
					if(t2==false){
						P.seDirection1(6);
						P.setDirection2(2);
						P.setShiftX1(-ScanStep);
						P.setShiftY1(0);
						P.setShiftX2(0);
						P.setShiftY2(0);
						t=true;
					}
				break;
				case 2:
					if(X3max+2*ScanStep<=N1){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
						if(t6==false){
							P.seDirection1(6);
							P.setDirection2(6);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(-2*ScanStep);
							P.setShiftY2(0);
							t=true;
						}
					}
				break;
				case 3:
					if(Y3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t8==false){
							P.seDirection1(6);
							P.setDirection2(8);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(ScanStep);
							t=true;
						}
					}
				break;
				case 4:
					if(X3min+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t4==false){
							P.seDirection1(6);
							P.setDirection2(4);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				}
				dir2++;
				}
			break;
			case 7:
				if(X2max+ScanStep<=N1&&Y2min-ScanStep>=0&&X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
					matrixx2=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 7);
					matrixx3=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 7);
				
				switch(dir2){
				case 1:
					if(Y3min-ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t2==false){
							P.seDirection1(7);
							P.setDirection2(2);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(ScanStep);
							t=true;
						}
					}
				break;
				case 2:
					if(X3max+2*ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t6==false){
							P.seDirection1(7);
							P.setDirection2(6);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(ScanStep);
							P.setShiftX2(-2*ScanStep);
							P.setShiftY2(ScanStep);
							t=true;
						}
					}
				break;
				case 3:
					if(X3max+ScanStep<=N1&&Y3min-2*ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
						if(t8==false){
							P.seDirection1(7);
							P.setDirection2(8);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(2*ScanStep);
							t=true;
						}
					}
				break;
				case 4:
					if(X3max+ScanStep<=N1){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
						if(t4==false){
							P.seDirection1(7);
							P.setDirection2(4);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(0);
							t=true;
						}
					}
				break;
				}
				dir2++;
				}
			break;
			case 8:
				if(Y2min-ScanStep>=0&&Y3min-ScanStep>=0){
					
					matrixx2=zp.Shifting(matrix2, 0, ScanStep, N1, M1, 8);
					matrixx3=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 8);
				switch(dir2){
				case 1:
					if(X3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t2==false){
							P.seDirection1(8);
							P.setDirection2(2);
							P.setShiftX1(0);
							P.setShiftY1(ScanStep);
							P.setShiftX2(ScanStep);
							P.setShiftY2(ScanStep);
							t=true;
						}
					}
				break;
				case 2:
					if(X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t6==false){
							P.seDirection1(8);
							P.setDirection2(6);
							P.setShiftX1(0);
							P.setShiftY1(ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(ScanStep);
							t=true;
						}
					}
				break;
				case 3:
					if(Y3min-2*ScanStep>=0){
						matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
						if(t8==false){
							P.seDirection1(8);
							P.setDirection2(8);
							P.setShiftX1(0);
							P.setShiftY1(ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(2*ScanStep);
							t=true;
						}
					}
				break;
				case 4:
					matrixx4=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
					boolean t4=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx4, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
					if(t4==false){
						P.seDirection1(8);
						P.setDirection2(4);
						P.setShiftX1(0);
						P.setShiftY1(ScanStep);
						P.setShiftX2(0);
						P.setShiftY2(0);
						t=true;
					}
				break;
				}
				dir2++;
				}
			break;
			}
		dir1++;	
		}
		return P;
	}
	public ArrayList <HoldingResult> MovingInDirection1(int [][] matrix1, int [][] matrix2, int X1min, int X1max, int X2min, int X2max, int Y1min, int Y1max, int Y2min, int Y2max, int ShiftX, int ShiftY ){
		ArrayList <HoldingResult> dir=new ArrayList <HoldingResult> ();
		
		return dir;
	}
}
