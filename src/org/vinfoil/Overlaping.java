package org.vinfoil;

import java.util.*;

public class Overlaping {
	
	public Overlaping(){
		
	}
	public boolean CheckOverlapingOfTwoLanes(FWL lane1, FWL lane2){
		boolean a = false;
		if((lane1.getStart()<=lane2.getStart())&&(lane2.getStart()<=lane1.getEnd())&&(lane1.getEnd()<=lane2.getEnd()))
			a=true;
		else if((lane2.getStart()<=lane1.getStart())&&(lane1.getStart()<=lane2.getEnd())&&(lane2.getEnd()<=lane1.getEnd()))
			a=true;
		else if((lane1.getStart()<=lane2.getStart())&&(lane2.getEnd()<=lane1.getEnd()))
			a=true;
		else if((lane2.getStart()<=lane1.getStart())&&(lane1.getEnd()<=lane2.getEnd()))
			a=true;
		return a;
	}
//if false there is no overlapping, if true then there is
	public static boolean OverlapingOfRectangels(Rectangel r1, Rectangel r2){
		boolean t=false;
		if(((r1.getX11()<=r2.getX11())&&(r2.getX11()<=r1.getX12())&&(r1.getY11()>=r2.getY11())&&(r2.getY11()>=r1.getY12()))||((r1.getX11()<=r2.getX12())&&(r2.getX12()<=r1.getX12())&&(r1.getY11()>=r2.getY12())&&(r2.getY12()>=r1.getY12())))
		t=true;
		else if(((r2.getX11()<=r1.getX11())&&(r1.getX11()<=r2.getX12())&&(r2.getY11()>=r1.getY11())&&(r1.getY11()>=r2.getY12()))||((r2.getX11()<=r1.getX12())&&(r1.getX12()<=r2.getX12())&&(r2.getY11()>=r1.getY12())&&(r1.getY12()>=r2.getY12())))
			t=true;
			return t;
	}
	//if false there is no overlapping, if true then there is
	public static boolean OverlapingOfTriangels(Triangel t1, Triangel t2){
		boolean t=false;
		if((t1.getX11()<=t2.getX11())&&((t2.getX11()<=t1.getX12())||(t2.getX11()<=t1.getX13()))&&(t1.getY11()>=t2.getY11())&&((t2.getY11()>=t1.getY12())||(t2.getY11()>=t1.getY13())))
			t=true;
		else if((t1.getX11()<=t2.getX12())&&((t2.getX12()<=t1.getX12())||(t2.getX12()<=t1.getX13()))&&(t1.getY11()>=t2.getY12())&&((t2.getY12()>=t1.getY12())||(t2.getY12()>=t1.getY13())))
			t=true;
		else if((t1.getX11()<=t2.getX13())&&((t2.getX13()<=t1.getX12())||(t2.getX13()<=t1.getX13()))&&(t1.getY11()>=t2.getY13())&&((t2.getY13()>=t1.getY12())||(t2.getY13()>=t1.getY13())))
			t=true;
		else if((t2.getX11()<=t1.getX11())&&((t1.getX11()<=t2.getX12())||(t1.getX11()<=t2.getX13()))&&(t2.getY11()>=t1.getY11())&&((t1.getY11()>=t2.getY12())||(t1.getY11()>=t2.getY13())))
			t=true;
		else if((t2.getX11()<=t1.getX12())&&((t1.getX12()<=t2.getX12())||(t1.getX12()<=t2.getX13()))&&(t2.getY11()>=t1.getY12())&&((t1.getY12()>=t2.getY12())||(t1.getY12()>=t2.getY13())))
			t=true;
		else if((t2.getX11()<=t1.getX13())&&((t1.getX13()<=t2.getX12())||(t1.getX13()<=t2.getX13()))&&(t2.getY11()>=t1.getY13())&&((t1.getY13()>=t2.getY12())||(t1.getY13()>=t2.getY13())))
			t=true;
		
		return t;
	}
	//if false there is no overlapping, if true then there is
	public static boolean OverlapingOfParallelograms(Parallelogram p1, Parallelogram p2){
		boolean t=false;
		if((p1.getX11()<=p2.getX11())&&((p2.getX11()<=p1.getX12())||(p2.getX11()<=p1.getX13())||(p2.getX11()<=p1.getX14()))&&(p1.getY11()>=p2.getY11())&&((p2.getY11()>=p1.getY12())||(p2.getY11()>=p1.getY13())||(p2.getY11()>=p1.getY14())))
			t=true;
		else if((p1.getX11()<=p2.getX12())&&((p2.getX12()<=p1.getX12())||(p2.getX12()<=p1.getX13())||(p2.getX12()<=p1.getX14()))&&(p1.getY11()>=p2.getY12())&&((p2.getY12()>=p1.getY12())||(p2.getY12()>=p1.getY13())||(p2.getY12()>=p1.getY14())))
			t=true;
		else if((p1.getX11()<=p2.getX13())&&((p2.getX13()<=p1.getX12())||(p2.getX13()<=p1.getX13())||(p2.getX13()<=p1.getX14()))&&(p1.getY11()>=p2.getY13())&&((p2.getY13()>=p1.getY12())||(p2.getY13()>=p1.getY13())||(p2.getY13()>=p1.getY14())))
			t=true;
		else if((p1.getX11()<=p2.getX14())&&((p2.getX14()<=p1.getX12())||(p2.getX14()<=p1.getX13())||(p2.getX14()<=p1.getX14()))&&(p1.getY11()>=p2.getY14())&&((p2.getY14()>=p1.getY12())||(p2.getY14()>=p1.getY13())||(p2.getY14()>=p1.getY14())))
			t=true;
		else if((p2.getX11()<=p1.getX11())&&((p1.getX11()<=p2.getX12())||(p1.getX11()<=p2.getX13())||(p1.getX11()<=p2.getX14()))&&(p2.getY11()>=p1.getY11())&&((p1.getY11()>=p2.getY12())||(p1.getY11()>=p2.getY13())||(p1.getY11()>=p2.getY14())))
			t=true;
		else if((p2.getX11()<=p1.getX12())&&((p1.getX12()<=p2.getX12())||(p1.getX12()<=p2.getX13())||(p1.getX12()<=p2.getX14()))&&(p2.getY11()>=p1.getY12())&&((p1.getY12()>=p2.getY12())||(p1.getY12()>=p2.getY13())||(p1.getY12()>=p2.getY14())))
			t=true;
		else if((p2.getX11()<=p1.getX13())&&((p1.getX13()<=p2.getX12())||(p1.getX13()<=p2.getX13())||(p1.getX13()<=p2.getX14()))&&(p2.getY11()>=p1.getY13())&&((p1.getY13()>=p2.getY12())||(p1.getY13()>=p2.getY13())||(p1.getY13()>=p2.getY14())))
			t=true;
		else if((p2.getX11()<=p1.getX14())&&((p1.getX14()<=p2.getX12())||(p1.getX14()<=p2.getX13())||(p1.getX14()<=p2.getX14()))&&(p2.getY11()>=p1.getY14())&&((p1.getY14()>=p2.getY12())||(p1.getY14()>=p2.getY13())||(p1.getY14()>=p2.getY14())))
			t=true;
		
		return t;
	}
	//if false there is no overlapping, if true then there is
	public static boolean OverlapingOfRectangelAndTriangel(Rectangel r, Triangel t1){
		boolean t=false;
		
		return t;
	}
	//if false there is no overlapping, if true then there is
	public static boolean OverlapingOfRectangelAndParallelogram(Rectangel r, Parallelogram p){
		boolean t=false;
		//if(())
		
		return t;
	}
	//if false there is no overlapping, if true then there is
	public static boolean OverlapingOfTriangelAndParallelogram(Triangel t1, Parallelogram p){
		boolean t=false;
		
		return t;
	}
	//if false there is no overlapping, if true then there is
	//you need to first read patterns for each lane
	public static boolean CheckOverlapingOfPatternsForTwoLanes( Patterns patternsForLane1, Patterns patternsForLane2){
		boolean t=false;
		ArrayList <Parallelogram> parTemp= new ArrayList <Parallelogram> ();
		ArrayList <Rectangel> recTemp=new ArrayList <Rectangel> ();
		ArrayList <Triangel> triTemp=new ArrayList <Triangel> ();
		Parallelogram PARtemp=new Parallelogram ();
		Rectangel Rtemp= new Rectangel ();
		Triangel Ttemp=new Triangel ();
		
		parTemp=patternsForLane1.getParallelograms();
		recTemp=patternsForLane1.getRectangels();
		
		
		
		return t;
	}
	//if false there is no overlapping, if true then there is
	
	public boolean CheckOverlapingOfTwoDiscretizedLanes(int [][] matrix1, int [][] matrix2, int X1min, int X1max, int X2min, int X2max, int Y1min, int Y1max, int Y2min, int Y2max){
		boolean t=false;
		int i=0; int j=0;
		i=Math.min(X1min, X2min);
		j=Math.min(Y1min, Y2min);
		int i1=Math.max(X1max, X2max);
		int j1=Math.max(Y1max, Y2max);
		while((t==false)&&(i<=i1)){
			while(j<=j1&&(t==false)){
				if((matrix1[i][j]==1)&&(matrix1[i][j]==matrix2[i][j])){
				//if(matrix1[i][j]==1){
					//if any 1 coincide then patterns are overlapping
					t=true;	
				}
					
					
				j++;
			}
			 j=Math.min(Y1min, Y2min);
			
			i++;
			
		}
		
		return t;
	}
	
	//if false there is no overlapping, if true then there is
	
	public boolean CheckOverlapingOfThreeDiscretizedLanes(int [][] matrix1, int [][] matrix2, int [][] matrix3, int X1min, int X1max, int X2min, int X2max, int X3min, int X3max, int Y1min, int Y1max, int Y2min, int Y2max, int Y3min, int Y3max){
		boolean t=false;
		int i=0; int j=0;
		i=Math.min(X1min, X2min);
		i=Math.min(i, X3min);
		j=Math.min(Y1min, Y2min);
		j=Math.min(j, Y3min);
		int i1=Math.max(X1max, X2max);
		i1=Math.max(i1, X3max);
		int j1=Math.max(Y1max, Y2max);
		j1=Math.max(j1, Y3max);
		while((t==false)&&(i<=i1)){
			while(j<=j1&&(t==false)){
				if((matrix1[i][j]==1)&&(matrix2[i][j]==1)&&(matrix3[i][j]==1)){
				//if(matrix1[i][j]==1){
					//if any 1 coincide then patterns are overlapping
					t=true;	
				}
					
					
				j++;
			}
			j=Math.min(Y1min, Y2min);
			j=Math.min(j, Y3min);
			i++;
			
		}
		
		return t;
	}
	
	//matrix1 is the one that has zero padding, matrix 2 is fixed one
	//if it returns false then there is some overlapping between patterns
	public boolean ScanForEmbeding(int [][] matrix1, int [][] matrix2, int scanStep, int N1, int M1, int N2, int M2, int X1min, int X1max, int Y1min, int Y1max){
		boolean t=true;
	
		for( int i=X1min; i<=X1max;i++)
			for(int j=Y1min;j<=Y1max;j++){
				if(matrix1[i][j]==1){
					int i1=i-scanStep;
					int i2=i+scanStep;
					int j1=j-scanStep;
					int j2=j+scanStep;
					if(i1<0)
						i1=0;
					if(i2>N2)
						i2=N2;
					if(j1<0)
						j1=0;
					if(j2>M2)
						j2=M2;
					int p=j1;
					int k=i1;
					while(t==true&&k<=i2){
						while(t==true&&p<=j2){
							if(matrix2[k][p]==1)
								t=false;
							p++;
						}
						p=j1;
						k++;
					}
					/*for(int k=i1;k<=i2;k++)
						for(int p=j1;p<=j2;p++){
							if(matrix2[k][p]==1)
								t=false;
						}*/
				}
				
			}
		return t;
	}
	public ArrayList <Point> ScaningForEmbedingWithTwoLanes(int [][] matrix1, int [][] matrix2,int ScanStep,int N1, int M1, int X1min, int X1max, int X2min, int X2max, int Y1min, int Y1max, int Y2min, int Y2max){
		Point P=new Point(0,0);//the first coordinate shows if it is possible to embed 0-no 1-yes;
		//second coordinate tells about the direction
		ArrayList <Point> listOfEmb=new ArrayList <Point>();
		ZeroPadding zp=new ZeroPadding();
		int [][] matrixx1 =new int [N1][M1];
		boolean t=true;
		//direction 1? da li je uoste moguce toliki zere pading moras da proversi
		//da ne bi ti ispao nijedan pattern a mozes to da proveris i u holding constraints razmisli
		//direction 1
		if(X2min-ScanStep>=0&&Y2min-ScanStep>=0){
		matrixx1=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 1);
		t=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min-ScanStep, X2max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep);
		if(t==false){
			P.setX(1);
			P.setY(1);
			listOfEmb.add(P);
			
			
		}
		}
		P=new Point(0,0);
		matrixx1=new int [N1][M1];
		//direction 2
		if(X2min-ScanStep>=0){
		matrixx1=zp.Shifting(matrix2, ScanStep, 0, N1, M1, 2);
		t=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min-ScanStep, X2max-ScanStep, Y1min, Y1max, Y2min, Y2max);
		if(t==false){
			P.setX(1);
			P.setY(2);
			listOfEmb.add(P);
		
			t=true;
		}
		}
		P=new Point(0,0);
		matrixx1=new int [N1][M1];
		//direction 3
		if(X2min-ScanStep>=0&&Y2max+ScanStep<M1){
		matrixx1=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 3);
		t=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min-ScanStep, X2max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep);
		if(t==false){
			P.setX(1);
			P.setY(3);
			listOfEmb.add(P);
			t=true;
		}
		}
		P=new Point(0,0);
		matrixx1=new int [N1][M1];
		//direction 4
		if(Y2max+ScanStep<M1){
		matrixx1=zp.Shifting(matrix2, 0, ScanStep, N1, M1, 4);
		t=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min, X2max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep);
		if(t==false){
			P.setX(1);
			P.setY(4);
			listOfEmb.add(P);
			t=true;
		}
		}
		P=new Point(0,0);
		matrixx1=new int [N1][M1];
		//direction 5
		if(X2max+ScanStep<N1&&Y2max+ScanStep<M1){
		matrixx1=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 5);
		t=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min+ScanStep, X2max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep);
		if(t==false){
			P.setX(1);
			P.setY(5);
			listOfEmb.add(P);
			t=true;
		}
		}
		P=new Point(0,0);
		matrixx1=new int [N1][M1];
		//direction 6
		if(X2max+ScanStep<N1){
		matrixx1=zp.Shifting(matrix2, ScanStep, 0, N1, M1, 6);
		t=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min+ScanStep, X2max+ScanStep, Y1min, Y1max, Y2min, Y2max);
		if(t==false){
			P.setX(1);
			P.setY(6);
			listOfEmb.add(P);
			t=true;
		}
		}
		P=new Point(0,0);
		matrixx1=new int [N1][M1];
		//direction 7
		if(X2max+ScanStep<N1&&Y2min-ScanStep>=0){
		matrixx1=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 7);
		t=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min+ScanStep, X2max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep);
		if(t==false){
			P.setX(1);
			P.setY(7);
			listOfEmb.add(P);
			t=true;
		}
		}
		P=new Point(0,0);
		matrixx1=new int [N1][M1];
		//direction 8
		if(Y2min-ScanStep>=0){
		matrixx1=zp.Shifting(matrix2, 0, ScanStep, N1, M1, 4);
		t=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min, X2max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep);
		if(t==false){
			P.setX(1);
			P.setY(8);
			listOfEmb.add(P);
			t=true;
		}
		}
	
		return listOfEmb;
	}
	//every odd index in the given array is for the first repast lane every even index is for the second repast lane
	//podrazumeva se da su shiftovi uracunati, vraca pomeraj samo za scan, ako vrati direction 0 ne moze da se embeduje
	public ArrayList <Direction1andDirection2> ScaningForEmbedingWithThreeLanes(int [][] matrix1, int [][] matrix2, int [][] matrix3, int ScanStep,int N1, int M1, int X1min, int X1max, int X2min, int X2max, int X3min, int X3max, int Y1min, int Y1max, int Y2min, int Y2max, int Y3min, int Y3max){
		Direction1andDirection2 P1=new Direction1andDirection2(0,0,0,0,0,0);
		ArrayList <Direction1andDirection2> listOfEmb=new ArrayList <Direction1andDirection2>();
		ZeroPadding zp=new ZeroPadding();
		int [][] matrixx1 =new int [N1][M1];
		int [][] matrixx2=new int [N1][M1];
		boolean t=true;
		boolean t1=true;
		
		if(X2min-ScanStep>=0&&Y2min-ScanStep>=0){
			matrixx1=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 1);
			t1=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min-ScanStep, X2max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep);
			if(t1==false){
				
				//embedding the third lane
				//direction1
				if(X3min-ScanStep>=0&&Y3min-ScanStep>=0){
					matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 1);
					t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
					if(t==false){
						P1.seDirection1(1);
						P1.setDirection2(1);
						P1.setShiftX1(ScanStep);
						P1.setShiftY1(ScanStep);
						P1.setShiftX2(ScanStep);
						P1.setShiftY2(ScanStep);
						listOfEmb.add(P1);
					}
					}
				//direction 2
					P1=new Direction1andDirection2(0,0,0,0,0,0);
					matrixx2=new int [N1][M1];
					if(X3min-ScanStep>=0){
					matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 2);
					t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1,matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
					if(t==false){
						P1.seDirection1(1);
						P1.setDirection2(2);
						P1.setShiftX1(ScanStep);
						P1.setShiftY1(ScanStep);
						P1.setShiftX2(ScanStep);
						P1.setShiftY2(0);
						listOfEmb.add(P1);
						t=true;
					}
					}
					//direction 3
					P1=new Direction1andDirection2(0,0,0,0,0,0);
					matrixx2=new int [N1][M1];
					if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
					matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 3);
					t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min+ScanStep, Y3max+ScanStep);
					if(t==false){
						P1.seDirection1(1);
						P1.setDirection2(3);
						P1.setShiftX1(ScanStep);
						P1.setShiftY1(ScanStep);
						P1.setShiftX2(ScanStep);
						P1.setShiftY2(-ScanStep);
						listOfEmb.add(P1);
						t=true;
					}
					}
					//direction 4
					P1=new Direction1andDirection2(0,0,0,0,0,0);
					matrixx2=new int [N1][M1];
					if(Y3max+ScanStep<=M1){
					matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 4);
					t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max,Y2min-ScanStep, Y2max-ScanStep, Y3min+ScanStep, Y3max+ScanStep);
					if(t==false){
						P1.seDirection1(1);
						P1.setDirection2(4);
						P1.setShiftX1(ScanStep);
						P1.setShiftY1(ScanStep);
						P1.setShiftX2(0);
						P1.setShiftY2(-ScanStep);
						listOfEmb.add(P1);
						t=true;
					}
					}
					//direction 5
					P1=new Direction1andDirection2(0,0,0,0,0,0);
					matrixx2=new int [N1][M1];
					if(X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
					matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 5);
					t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min-ScanStep, X2max-ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min+ScanStep, Y3max+ScanStep);
					if(t==false){
						P1.seDirection1(1);
						P1.setDirection2(5);
						P1.setShiftX1(ScanStep);
						P1.setShiftY1(ScanStep);
						P1.setShiftX2(-ScanStep);
						P1.setShiftY2(-ScanStep);
						listOfEmb.add(P1);
						t=true;
					}
					}
					//direction 6
					P1=new Direction1andDirection2(0,0,0,0,0,0);
					matrixx2=new int [N1][M1];
					if(X3max+ScanStep<=N1){
					matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 6);
					t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
					if(t==false){
						P1.seDirection1(1);
						P1.setDirection2(6);
						P1.setShiftX1(ScanStep);
						P1.setShiftY1(ScanStep);
						P1.setShiftX2(-ScanStep);
						P1.setShiftY2(0);
						listOfEmb.add(P1);
						t=true;
					}
					}
					//direction 7
					P1=new Direction1andDirection2(0,0,0,0,0,0);
					matrixx2=new int [N1][M1];
					if(X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
					matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 7);
					t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
					if(t==false){
						P1.seDirection1(1);
						P1.setDirection2(7);
						P1.setShiftX1(ScanStep);
						P1.setShiftY1(ScanStep);
						P1.setShiftX2(-ScanStep);
						P1.setShiftY2(ScanStep);
						listOfEmb.add(P1);
						t=true;
					}
					}
					//direction 8
					P1=new Direction1andDirection2(0,0,0,0,0,0);
					matrixx2=new int [N1][M1];
					if(Y3min-ScanStep>=0){
					matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 8);
					t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
					if(t==false){
						P1.seDirection1(1);
						P1.setDirection2(8);
						P1.setShiftX1(ScanStep);
						P1.setShiftY1(ScanStep);
						P1.setShiftX2(0);
						P1.setShiftY2(ScanStep);
						listOfEmb.add(P1);
						t=true;
					}
					}
				
				//
					t1=true;
			}
			}else{//embedding not possible 
				P1.seDirection1(1);
				P1.setDirection2(0);
				P1.setShiftX1(0);
				P1.setShiftY1(0);
				P1.setShiftX2(0);
				P1.setShiftY2(0);
				listOfEmb.add(P1);
				t1=true;
			}
		//direction 2
		
			matrixx1=new int [N1][M1];
			if(X2min-ScanStep>=0){
				matrixx1=zp.Shifting(matrix2, ScanStep, 0, N1, M1, 2);
				t1=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min-ScanStep, X2max-ScanStep, Y1min, Y1max, Y2min, Y2max);
				if(t1==false){
					
				//
					//embedding the third lane
					//direction1
					if(X3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 1);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(2);
							P1.setDirection2(1);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
						}
						}
					//direction 2
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 2);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1,matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(2);
							P1.setDirection2(2);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 3
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 3);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(2);
							P1.setDirection2(3);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 4
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 4);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max,Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(2);
							P1.setDirection2(4);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(0);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 5
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 5);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min-ScanStep, X2max-ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(2);
							P1.setDirection2(5);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 6
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 6);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(2);
							P1.setDirection2(6);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 7
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 7);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(2);
							P1.setDirection2(7);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 8
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 8);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(2);
							P1.setDirection2(8);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(0);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					
					//
					t1=true;
				}
				}else{
					P1.seDirection1(2);
					P1.setDirection2(0);
					P1.setShiftX1(0);
					P1.setShiftY1(0);
					P1.setShiftX2(0);
					P1.setShiftY2(0);
					listOfEmb.add(P1);
					t1=true;
				}
			//direction 3
				P1=new Direction1andDirection2(0,0,0,0,0,0);
				matrixx1=new int [N1][M1];
				if(X2min-ScanStep>=0&&Y2max+ScanStep<=M1){
				matrixx1=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 3);
				t1=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min-ScanStep, X2max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep);
				if(t1==false){
					
					//
					//embedding the third lane
					//direction1
					if(X3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 1);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(3);
							P1.setDirection2(1);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//direction 2
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 2);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1,matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(3);
							P1.setDirection2(2);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 3
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 3);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(3);
							P1.setDirection2(3);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 4
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 4);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max,Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(3);
							P1.setDirection2(4);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(0);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 5
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 5);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min-ScanStep, X2max-ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(3);
							P1.setDirection2(5);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 6
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 6);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(3);
							P1.setDirection2(6);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 7
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 7);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(3);
							P1.setDirection2(7);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 8
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 8);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min+ScanStep,  Y2max+ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(3);
							P1.setDirection2(8);
							P1.setShiftX1(ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(0);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//
					t1=true;
				}
				}else{
					P1.seDirection1(3);
					P1.setDirection2(0);
					P1.setShiftX1(0);
					P1.setShiftY1(0);
					P1.setShiftX2(0);
					P1.setShiftY2(0);
					listOfEmb.add(P1);
					t1=true;
				}
				//direction 4
				P1=new Direction1andDirection2(0,0,0,0,0,0);
				matrixx1=new int [N1][M1];
				if(Y2max+ScanStep<=M1){
				matrixx1=zp.Shifting(matrix2, 0, ScanStep, N1, M1, 4);
				t1=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min, X2max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep);
				if(t1==false){
					
					//
					//embedding the third lane
					//direction1
					if(X3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 1);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(4);
							P1.setDirection2(1);
							P1.setShiftX1(0);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//direction 2
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 2);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1,matrixx2, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(4);
							P1.setDirection2(2);
							P1.setShiftX1(0);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 3
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 3);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(4);
							P1.setDirection2(3);
							P1.setShiftX1(0);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 4
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 4);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min, X2max, X3min, X3max, Y1min, Y1max,Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(4);
							P1.setDirection2(4);
							P1.setShiftX1(0);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(0);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 5
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 5);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(4);
							P1.setDirection2(5);
							P1.setShiftX1(0);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 6
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 6);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(4);
							P1.setDirection2(6);
							P1.setShiftX1(0);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 7
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 7);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(4);
							P1.setDirection2(7);
							P1.setShiftX1(0);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 8
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 8);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(4);
							P1.setDirection2(8);
							P1.setShiftX1(0);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(0);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//
					t1=true;
				}
				}else{
					P1.seDirection1(4);
					P1.setDirection2(0);
					P1.setShiftX1(0);
					P1.setShiftY1(0);
					P1.setShiftX2(0);
					P1.setShiftY2(0);
					listOfEmb.add(P1);
					t1=true;
				}
				//direction 5
				P1=new Direction1andDirection2(0,0,0,0,0,0);
				matrixx1=new int [N1][M1];
				if(X2max+ScanStep<=N1&&Y2max+ScanStep<=M1){
				matrixx1=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 5);
				t1=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min+ScanStep, X2max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep);
				if(t1==false){
					
					//
					//embedding the third lane
					//direction1
					if(X3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 1);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(5);
							P1.setDirection2(1);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//direction 2
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 2);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1,matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(5);
							P1.setDirection2(2);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 3
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 3);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(5);
							P1.setDirection2(3);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 4
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 4);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max,Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(5);
							P1.setDirection2(4);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(0);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 5
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 5);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(5);
							P1.setDirection2(5);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 6
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 6);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(5);
							P1.setDirection2(6);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 7
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 7);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(5);
							P1.setDirection2(7);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 8
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 8);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(5);
							P1.setDirection2(8);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(-ScanStep);
							P1.setShiftX2(0);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//
					t1=true;
				}
				}else{
					P1.seDirection1(5);
					P1.setDirection2(0);
					P1.setShiftX1(0);
					P1.setShiftY1(0);
					P1.setShiftX2(0);
					P1.setShiftY2(0);
					listOfEmb.add(P1);
					t1=true;
				}
				//direction 6
				P1=new Direction1andDirection2(0,0,0,0,0,0);
				matrixx1=new int [N1][M1];
				if(X2max+ScanStep<=N1){
				matrixx1=zp.Shifting(matrix2, ScanStep, 0, N1, M1, 6);
				t1=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min+ScanStep, X2max+ScanStep, Y1min, Y1max, Y2min, Y2max);
				if(t1==false){
					
					//
					//embedding the third lane
					//direction1
					if(X3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 1);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min+ScanStep, X2max+ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(6);
							P1.setDirection2(1);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//direction 2
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 2);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1,matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(6);
							P1.setDirection2(2);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 3
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 3);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(6);
							P1.setDirection2(3);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 4
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 4);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max,Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(6);
							P1.setDirection2(4);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(0);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 5
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 5);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(6);
							P1.setDirection2(5);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 6
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 6);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(6);
							P1.setDirection2(6);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 7
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 7);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(6);
							P1.setDirection2(7);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 8
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 8);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min,  Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(6);
							P1.setDirection2(8);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(0);
							P1.setShiftX2(0);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//
					t1=true;
				}
				}else{
					P1.seDirection1(6);
					P1.setDirection2(0);
					P1.setShiftX1(0);
					P1.setShiftY1(0);
					P1.setShiftX2(0);
					P1.setShiftY2(0);
					listOfEmb.add(P1);
					t1=true;
				}
				//direction 7
				P1=new Direction1andDirection2(0,0,0,0,0,0);
				matrixx1=new int [N1][M1];
				if(X2max+ScanStep<=N1&&Y2min-ScanStep>=0){
				matrixx1=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 7);
				t1=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min+ScanStep, X2max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep);
				if(t1==false){
					
					//
					//embedding the third lane
					//direction1
					if(X3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 1);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(7);
							P1.setDirection2(1);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//direction 2
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 2);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1,matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(7);
							P1.setDirection2(2);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 3
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 3);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(7);
							P1.setDirection2(3);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 4
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 4);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max,Y2min-ScanStep, Y2max-ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(7);
							P1.setDirection2(4);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(0);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 5
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 5);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(7);
							P1.setDirection2(5);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 6
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 6);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(7);
							P1.setDirection2(6);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 7
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 7);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(7);
							P1.setDirection2(7);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 8
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 8);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max,  Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(7);
							P1.setDirection2(8);
							P1.setShiftX1(-ScanStep);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(0);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//
					t1=true;
				}
				}else{
					P1.seDirection1(7);
					P1.setDirection2(0);
					P1.setShiftX1(0);
					P1.setShiftY1(0);
					P1.setShiftX2(0);
					P1.setShiftY2(0);
					listOfEmb.add(P1);
					t1=true;
				}
				//direction 8
				P1=new Direction1andDirection2(0,0,0,0,0,0);
				matrixx1=new int [N1][M1];
				if(Y2min-ScanStep>=0){
				matrixx1=zp.Shifting(matrix2, 0, ScanStep, N1, M1, 8);
				t1=this.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrixx1, X1min, X1max, X2min, X2max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep);
				if(t1==false){
					
					//
					//embedding the third lane
					//direction1
					if(X3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 1);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(8);
							P1.setDirection2(1);
							P1.setShiftX1(0);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//direction 2
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 2);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1,matrixx2, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(8);
							P1.setDirection2(2);
							P1.setShiftX1(0);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 3
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 3);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(8);
							P1.setDirection2(3);
							P1.setShiftX1(0);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 4
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 4);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min, X2max, X3min, X3max, Y1min, Y1max,Y2min-ScanStep, Y2max-ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(8);
							P1.setDirection2(4);
							P1.setShiftX1(0);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(0);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 5
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 5);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max,X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min+ScanStep, Y3max+ScanStep);
						if(t==false){
							P1.seDirection1(8);
							P1.setDirection2(5);
							P1.setShiftX1(0);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(-ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 6
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1){
						matrixx2=zp.Shifting(matrix3, ScanStep, 0, N1, M1, 6);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
						if(t==false){
							P1.seDirection1(8);
							P1.setDirection2(6);
							P1.setShiftX1(0);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(0);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 7
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 7);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(8);
							P1.setDirection2(7);
							P1.setShiftX1(0);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(-ScanStep);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
						//direction 8
						P1=new Direction1andDirection2(0,0,0,0,0,0);
						matrixx2=new int [N1][M1];
						if(Y3min-ScanStep>=0){
						matrixx2=zp.Shifting(matrix3, 0, ScanStep, N1, M1, 8);
						t=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx1, matrixx2, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t==false){
							P1.seDirection1(8);
							P1.setDirection2(8);
							P1.setShiftX1(0);
							P1.setShiftY1(ScanStep);
							P1.setShiftX2(0);
							P1.setShiftY2(ScanStep);
							listOfEmb.add(P1);
							t=true;
						}
						}
					//
					t1=true;
				}
				}else{
					P1.seDirection1(8);
					P1.setDirection2(0);
					P1.setShiftX1(0);
					P1.setShiftY1(0);
					P1.setShiftX2(0);
					P1.setShiftY2(0);
					listOfEmb.add(P1);
					t1=true;
				}
		
		return listOfEmb;
	}
	//podrazumeva se da su lane vec shiftovane da ovda dodajes samo scanstep
	public Direction1andDirection2 ScaningForEmbeddingThreeLanseFirstHit(int [][] matrix1, int [][] matrix2, int [][] matrix3, int ScanStep,int N1, int M1, int X1min, int X1max, int X2min, int X2max, int X3min, int X3max, int Y1min, int Y1max, int Y2min, int Y2max, int Y3min, int Y3max){
		
		Direction1andDirection2 temp=new Direction1andDirection2(0,0,0,0,0,0);
		Direction1andDirection2 P=new Direction1andDirection2(0,0,0,0,0,0);
		boolean t=false;//promenljiva koja obezbedjuje prvi hit, kad je ona tru tada izlazimo iz ove funkcije
		//prvo obe simultano shiftujem u jednom od osam pravaca zelim da me izvaci na prvi hit
		int [][] matrixx2=new int [N1][M1];
		int [][] matrixx3=new int [N1][M1];
		ZeroPadding zp=new ZeroPadding();
		int dir1=1; int dir2=1;
		while(t==false&&dir1<9&&dir2<9){
			switch(dir1){
			case 1://obe su pomerene u smeru 1 za po scanstep
				if(X2min-ScanStep>=0&&Y2min-ScanStep>=0&&X3min-ScanStep>=0&&Y3min-ScanStep>=0){
					matrixx2=zp.Shifting(matrix2, ScanStep, ScanStep, N1, M1, 1);
					
					matrixx3=zp.Shifting(matrix3, ScanStep, ScanStep, N1, M1, 1);
					//boolean t1=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
				//****
					switch(dir2){
					case 1:
						if(X3min-2*ScanStep>=0&&Y3min-2*ScanStep>=0){
							matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 1);
							boolean t1=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
							if(t1==false){
								P.seDirection1(1);
								P.setDirection2(1);
								P.setShiftX1(ScanStep);
								P.setShiftY1(ScanStep);
								P.setShiftX2(2*ScanStep);
								P.setShiftY2(2*ScanStep);
								t=true;
							}
							}
					break;
					case 2:
						if(X3min-2*ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2,matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
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
					case 3:
						if(X3min-2*ScanStep>=0){
							matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 3);
							boolean t3=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
							if(t3==false){
								P.seDirection1(1);
								P.setDirection2(3);
								P.setShiftX1(ScanStep);
								P.setShiftY1(ScanStep);
								P.setShiftX2(2*ScanStep);
								P.setShiftY2(0);
								t=true;
							}
						}
					break;
					case 4:
						if(X3min-ScanStep>=0){
							matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
							boolean t4=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max,X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max,Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
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
					case 5:
						
							matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 5);
							boolean t5=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
							if(t5==false){
								P.seDirection1(1);
								P.setDirection2(5);
								P.setShiftX1(ScanStep);
								P.setShiftY1(ScanStep);
								P.setShiftX2(0);
								P.setShiftY2(0);
								t=true;
							}
						
					break;
					case 6:
						if(Y3min-ScanStep>=0){
							matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
							boolean t6=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
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
					case 7:
						if(Y3min-2*ScanStep>=0)	{
							matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 7);
							boolean t7=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
							if(t7==false){
								P.seDirection1(1);
								P.setDirection2(7);
								P.setShiftX1(ScanStep);
								P.setShiftY1(ScanStep);
								P.setShiftX2(0);
								P.setShiftY2(2*ScanStep);
								t=true;
							}
						}
					break;
					case 8:
						if(X3min-ScanStep>=0&&Y3min-2*ScanStep>=0){
							matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
							boolean t8=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
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
					if(X3min-2*ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 1);
						boolean t1=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t1==false){
							P.seDirection1(2);
							P.setDirection2(1);
							P.setShiftX1(ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(2*ScanStep);
							P.setShiftY2(ScanStep);
							t=true;
						}
						}
				break;
				case 2:
					if(X3min-2*ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2,matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
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
				case 3:
					if(X3min-2*ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 3);
						boolean t3=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t3==false){
							P.seDirection1(2);
							P.setDirection2(8);
							P.setShiftX1(ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(2*ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}	
					}
				break;
				case 4:
					if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
					matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
					boolean t4=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max,X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max,Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
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
				case 5:
					if(Y3max+ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 5);
						boolean t5=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t5==false){
							P.seDirection1(2);
							P.setDirection2(8);
							P.setShiftX1(ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(0);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 6:
					
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
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
				case 7:
					if(Y3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 7);
						boolean t7=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t7==false){
							P.seDirection1(2);
							P.setDirection2(8);
							P.setShiftX1(ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(0);
							P.setShiftY2(ScanStep);
							t=true;
						}
					}
				break;
				case 8:
					if(X3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
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
					if(X3min-2*ScanStep>=0&&Y3min-2*ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 1);
						boolean t1=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t1==false){
							P.seDirection1(3);
							P.setDirection2(1);
							P.setShiftX1(ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 2:
					if(X3min-2*ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
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
				case 3:
					if(X3min-2*ScanStep>=0&&Y3max+2*ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 3);
						boolean t3=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-2*ScanStep, X3max-2*ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
						if(t3==false){
							P.seDirection1(3);
							P.setDirection2(3);
							P.setShiftX1(ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 4:
					if(X3min-ScanStep>=0&&Y3max+2*ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
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
				case 5:
					if(Y3max+2*ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 5);
						boolean t5=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
						if(t5==false){
							P.seDirection1(3);
							P.setDirection2(5);
							P.setShiftX1(ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(-2*ScanStep);
							t=true;
						}
					}
				break;
				case 6:
					if(Y3max+ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
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
				case 7:
				
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 7);
						boolean t7=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t7==false){
							P.seDirection1(3);
							P.setDirection2(7);
							P.setShiftX1(ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(0);
							t=true;
						}
					
				break;
				case 8:
					if(X3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min-ScanStep, X2max-ScanStep, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
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
					if(X3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 1);
						boolean t1=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t1==false){
							P.seDirection1(4);
							P.setDirection2(1);
							P.setShiftX1(0);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(ScanStep);
							P.setShiftY2(0);
							t=true;
						}
					}
				break;
				case 2:
					if(X3min-ScanStep>=0&&Y3max+ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
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
				case 3:
					if(X3min-ScanStep>=0&&Y3max+2*ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 3);
						boolean t3=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
						if(t3==false){
							P.seDirection1(4);
							P.setDirection2(3);
							P.setShiftX1(0);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(ScanStep);
							P.setShiftY2(-2*ScanStep);
							t=true;
						}
					}
				break;
				case 4:
					if(Y3max+2*ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
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
				case 5:
					if(X3max+ScanStep<=N1&&Y3max+2*ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 5);
						boolean t5=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
						if(t5==false){
							P.seDirection1(4);
							P.setDirection2(5);
							P.setShiftX1(0);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(-2*ScanStep);
							t=true;
						}
					}
				break;
				case 6:
					if(X3max+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
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
				case 7:
					if(X3max+ScanStep<=N1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 7);
						boolean t7=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t7==false){
							P.seDirection1(4);
							P.setDirection2(7);
							P.setShiftX1(0);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(0);
							t=true;
						}
					}
				break;
				case 8:
					
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
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
					
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 1);
						boolean t1=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t1==false){
							P.seDirection1(5);
							P.setDirection2(1);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(0);
							t=true;
						}
					
				break;
				case 2:
					if(Y3min+ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
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
				case 3:
					if(Y3max+2*ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 3);
						boolean t3=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
						if(t3==false){
							P.seDirection1(5);
							P.setDirection2(3);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(-2*ScanStep);
							t=true;
						}
					}
				break;
				case 4:
					if(X3max+ScanStep<=N1&&Y3max+2*ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
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
				case 5:
					if(X3max+2*ScanStep<=N1&&Y3max+2*ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 5);
						boolean t5=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+2*ScanStep, Y3max+2*ScanStep);
						if(t5==false){
							P.seDirection1(5);
							P.setDirection2(5);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(-2*ScanStep);
							P.setShiftY2(-2*ScanStep);
							t=true;
						}
					}
				break;
				case 6:
					if(X3max+2*ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min+ScanStep, Y3max+ScanStep);
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
				case 7:
					if(X3max+2*ScanStep<=N1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 7);
						boolean t7=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
						if(t7==false){
							P.seDirection1(5);
							P.setDirection2(7);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(-ScanStep);
							P.setShiftX2(-2*ScanStep);
							P.setShiftY2(0);
							t=true;
						}
					}
				break;
				case 8:
					if(X3max+ScanStep<=N1){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min+ScanStep, Y2max+ScanStep, Y3min, Y3max);
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
					if(Y3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 1);
						boolean t1=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t1==false){
							P.seDirection1(6);
							P.setDirection2(1);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(0);
							P.setShiftY2(ScanStep);
							t=true;
						}
					}
				break;
				case 2:
					
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
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
				case 3:
					if(Y3max+ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 5);
						boolean t3=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t3==false){
							P.seDirection1(6);
							P.setDirection2(3);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(0);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 4:
					if(X3min+ScanStep<=N1&&Y3max+ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
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
				case 5:
					if(X3max+2*ScanStep<=N1&&Y3max+2*ScanStep<=M1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 5);
						boolean t5=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min+ScanStep, Y3max+ScanStep);
						if(t5==false){
							P.seDirection1(6);
							P.setDirection2(5);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(-2*ScanStep);
							P.setShiftY2(-ScanStep);
							t=true;
						}
					}
				break;
				case 6:
					if(X3max+2*ScanStep<=N1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
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
				case 7:
					if(X3max+2*ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 7);
						boolean t7=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
						if(t7==false){
							P.seDirection1(6);
							P.setDirection2(7);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(0);
							P.setShiftX2(-2*ScanStep);
							P.setShiftY2(ScanStep);
							t=true;
						}
					}
				break;
				case 8:
					if(Y3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min, Y2max, Y3min-ScanStep, Y3max-ScanStep);
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
					if(Y3min-2*ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 1);
						boolean t1=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
						if(t1==false){
							P.seDirection1(7);
							P.setDirection2(1);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(2*ScanStep);
							t=true;
						}
					}
				break;
				case 2:
					if(Y3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
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
				case 3:
				
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 3);
						boolean t3=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
						if(t3==false){
							P.seDirection1(7);
							P.setDirection2(3);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(0);
							t=true;
						}
					
				break;
				case 4:
					if(X3max+ScanStep<=N1){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
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
				case 5:
					if(X3max+2*ScanStep<=N1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 5);
						boolean t5=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
						if(t5==false){
							P.seDirection1(7);
							P.setDirection2(5);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(ScanStep);
							P.setShiftX2(-2*ScanStep);
							P.setShiftY2(0);
							t=true;
						}
					}
				break;
				case 6:
					if(X3max+2*ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
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
				case 7:
					if(X3max+2*ScanStep<=N1&&Y3min-2*ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 7);
						boolean t7=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+2*ScanStep, X3max+2*ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min+2*ScanStep, Y3max-2*ScanStep);
						if(t7==false){
							P.seDirection1(7);
							P.setDirection2(7);
							P.setShiftX1(-ScanStep);
							P.setShiftY1(ScanStep);
							P.setShiftX2(-2*ScanStep);
							P.setShiftY2(2*ScanStep);
							t=true;
						}
					}
				break;
				case 8:
					if(X3max+ScanStep<=N1&&Y3min-2*ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min+ScanStep, X2max+ScanStep, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
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
					if(Y3min-2*ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 1);
						boolean t1=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
						if(t1==false){
							P.seDirection1(8);
							P.setDirection2(1);
							P.setShiftX1(0);
							P.setShiftY1(ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(2*ScanStep);
							t=true;
						}
					}
				break;
				case 2:
					if(X3min-ScanStep>=0&&Y3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 2);
						boolean t2=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min-ScanStep, X3max-ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
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
				case 3:
					if(X3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 3);
						boolean t3=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
						if(t3==false){
							P.seDirection1(8);
							P.setDirection2(3);
							P.setShiftX1(0);
							P.setShiftY1(ScanStep);
							P.setShiftX2(0);
							P.setShiftY2(ScanStep);
							t=true;
						}
					}
				break;
				case 4:
				
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 4);
						boolean t4=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
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
				case 5:
					if(X3max+ScanStep<=N1){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 5);
						boolean t5=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min, Y3max);
						if(t5==false){
							P.seDirection1(8);
							P.setDirection2(5);
							P.setShiftX1(0);
							P.setShiftY1(ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(0);
							t=true;
						}
					}
				break;
				case 6:
					if(X3max+ScanStep<=N1&&Y3min-ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, 0, N1, M1, 6);
						boolean t6=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-ScanStep, Y3max-ScanStep);
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
				case 7:
					if(X3max+ScanStep<=N1&&Y3min-2*ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, ScanStep, ScanStep, N1, M1, 7);
						boolean t7=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min+ScanStep, X3max+ScanStep, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
						if(t7==false){
							P.seDirection1(8);
							P.setDirection2(7);
							P.setShiftX1(0);
							P.setShiftY1(ScanStep);
							P.setShiftX2(-ScanStep);
							P.setShiftY2(2*ScanStep);
							t=true;
						}
					}
				break;
				case 8:
					if(Y3min-2*ScanStep>=0){
						matrixx3=zp.Shifting(matrixx3, 0, ScanStep, N1, M1, 8);
						boolean t8=this.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrixx2, matrixx3, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-ScanStep, Y2max-ScanStep, Y3min-2*ScanStep, Y3max-2*ScanStep);
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
				}
				dir2++;
				}
			break;
			}
			dir1++;
		}
		
		return P;//ako nijedan pravac nije moguc vratice praznu listu
	}
}
