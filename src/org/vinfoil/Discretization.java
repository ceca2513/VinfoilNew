package org.vinfoil;

import java.util.*;


public class Discretization {
	
	public Discretization(){
		
	}
	public int [][] DiscretizationOfLane(FWL lane, Patterns inPatterns){//intPatterns are all patterns for one sheet
		
		int a=0, b=0, c=0;
		a=(int)lane.getLength();
		b=(int)lane.getWidth();
		c=(int)lane.getStart();
		int [][] matrix= new int [ b][ a];
		SetOfPatternsForALane set=new SetOfPatternsForALane();
		Patterns shape=new Patterns ();
		ArrayList <Rectangel> tempR=new ArrayList <Rectangel>();
		ArrayList <Triangel> tempT=new ArrayList <Triangel>();
		ArrayList <Parallelogram> tempP=new ArrayList <Parallelogram>();
		Triangel tri=new Triangel ();
		Rectangel rec=new Rectangel();
		Parallelogram par=new Parallelogram ();
		Line l=new Line ();
		ArrayList <IntegerPoint> segments1=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> segments2=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> segments3=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> segments4=new ArrayList <IntegerPoint>();
		
		for(int i=0;i<b-1;i++){
			for(int j=0;j<a-1;j++){
				matrix[i][j]=0;
			}
		}
		shape=set.PatternsForOneLane(inPatterns, lane);	
		tempR=shape.getRectangels();
		tempT=shape.getTriangels();
		tempP=shape.getParallelograms();
		int r=tempR.size()-1;
		int t=tempT.size()-1;
		int p=tempP.size()-1;
		while(r>=0){//setting 1 for all rectangles 
			rec=tempR.get(r);
			//finding min and max for for loops
			int Xmin=(int) Math.min(rec.getX11(), rec.getX12())-c;
			int Xmax=(int) Math.max(rec.getX11(), rec.getX12())-c;
			int Ymin=(int) Math.min(rec.getY11(), rec.getY12());
			int Ymax=(int) Math.max(rec.getY11(), rec.getY12());
			IntegerPoint A = new IntegerPoint ();
			IntegerPoint B = new IntegerPoint ();
			IntegerPoint C= new IntegerPoint ();
			IntegerPoint D= new IntegerPoint ();
			
			//A.setX((int)rec.getX11()-c);
			A.setX((int)rec.getX11()-c);
			A.setY((int)rec.getY11());
			B.setX((int)rec.getX12()-c);
			B.setY((int)rec.getY11());
			C.setX((int)rec.getX12()-c);
			C.setY((int)rec.getY12());
			D.setX((int)rec.getX11()-c);
			D.setY((int)rec.getY12());
			Rectangel rectemp=new Rectangel ();
			rectemp.setX11(A.getX());
			rectemp.setX12(C.getX());
			rectemp.setY11(A.getY());
			rectemp.setY12(C.getY());
			segments1=l.SegmentDiscretization(A, B);
			
			int u1=segments1.size()-1;
			while(u1!=0){//setting all fields from segment AB to 1
				IntegerPoint E=new IntegerPoint();
						E=segments1.get(u1);
				matrix[E.getX()][E.getY()]=1;

				u1--;
			}
			segments2=l.SegmentDiscretization(B, C);
			int u2=segments2.size()-1;
			while(u2!=0){//setting all fields from segment BC to 1
				IntegerPoint E1=new IntegerPoint();
				E1=segments2.get(u2);
				matrix[E1.getX()][E1.getY()]=1;
				u2--;
			}
			segments3=l.SegmentDiscretization(C, D);
			int u3=segments3.size()-1;
			while(u3!=0){//setting all fields from segment CD to 1
				IntegerPoint E=new IntegerPoint();
				E=segments3.get(u3);
				matrix[E.getX()][E.getY()]=1;
				u3--;
			}
			segments4=l.SegmentDiscretization(D, A);
			int u4=segments4.size()-1;
			while(u4!=0){//setting all fields from segment DA to 1
				IntegerPoint E=new IntegerPoint();
				E=segments4.get(u4);
				matrix[E.getX()][E.getY()]=1;
				u4--;
			}
			//setting all elements inside rectangle to 1
			for(int i=Xmin+1;i<Xmax;i++)
				for(int j=Ymin+1;j<Ymax;j++){
					IntegerPoint F=new IntegerPoint ();
					F.setX(i);
					F.setY(j);
					boolean inside=false;
					inside=l.PointInsideRectangel2(rectemp, F);
					if(inside==true){
						matrix[(int) F.getX()][(int) F.getY()]=1;
					}
				}
			r--;
		}
		
		while(t>=0){//setting 1 for all triangles 
			tri=tempT.get(t);
			//finding min and max for for loops
			int Xmin=(int) Math.min(tri.getX11(), tri.getX12());
			Xmin=(int)Math.min(Xmin, tri.getX13())-c;
			int Xmax=(int) Math.max(tri.getX11(), tri.getX12());
			Xmax=(int)Math.max(Xmax, tri.getX13())-c;
			int Ymin=(int) Math.min(tri.getY11(), tri.getY12());
			Ymin=(int)Math.min(Ymin, tri.getY13());
			int Ymax=(int) Math.max(tri.getY11(), tri.getY12());
			Ymax=(int)Math.max(Ymax, tri.getY13());
			IntegerPoint A = new IntegerPoint ();
			IntegerPoint B = new IntegerPoint ();
			IntegerPoint C= new IntegerPoint ();
			IntegerPoint D= new IntegerPoint ();
			A.setX((int)tri.getX11()-c);
			A.setY((int)tri.getY11());
			B.setX((int)tri.getX12()-c);
			B.setY((int)tri.getY12());
			C.setX((int)tri.getX13()-c);
			C.setY((int)tri.getY13());
			Triangel triTemp=new Triangel ();
			triTemp.setX11(A.getX());
			triTemp.setX12(B.getX());
			triTemp.setX13(C.getX());
			triTemp.setY11(A.getY());
			triTemp.setY12(B.getY());
			triTemp.setY13(C.getY());
			
			segments1=l.SegmentDiscretization(A, B);
			int u1=segments1.size()-1;
			while(u1!=0){//setting all fields from segment AB to 1
				IntegerPoint E=new IntegerPoint();
				E=segments1.get(u1);
				matrix[E.getX()][E.getY()]=1;
				u1--;
			}
			segments2=l.SegmentDiscretization(B, C);
			int u2=segments2.size()-1;
			while(u2!=0){//setting all fields from segment BC to 1
				IntegerPoint E=new IntegerPoint();
				E=segments2.get(u2);
				matrix[E.getX()][E.getY()]=1;
				u2--;
			}
			segments3=l.SegmentDiscretization(C, A);
			int u3=segments3.size()-1;
			while(u3!=0){//setting all fields from segment CD to 1
				IntegerPoint E=new IntegerPoint();
				E=segments3.get(u3);
				matrix[E.getX()][E.getY()]=1;
				u3--;
			}
			
			//setting all elements inside triangle to 1
			for(int i=Xmin+1;i<Xmax;i++)
				for(int j=Ymin+1;j<Ymax;j++){
					IntegerPoint F=new IntegerPoint ();
					F.setX(i);
					F.setY(j);
					boolean inside=false;
					inside=l.PointInsideTriangel(triTemp, F);
					if(inside==true){
						matrix[i][j]=1;
					}
				}
			t--;
		}
		
		while(p>=0){//setting 1 for all parallelograms 
			par=tempP.get(p);
			//finding min and max for for loops
			int Xmin=(int) Math.min(par.getX11(), par.getX12());
			Xmin=(int) Math.min(Xmin, par.getX13());
			Xmin=(int) Math.min(Xmin, par.getX14())-c;
			int Xmax=(int) Math.max(par.getX11(), par.getX12());
			Xmax=(int) Math.max(Xmax, par.getX13());
			Xmax=(int) Math.max(Xmax, par.getX14())-c;
			int Ymin=(int) Math.min(par.getY11(), par.getY12());
			Ymin=(int) Math.min(Ymin, par.getY13());
			Ymin=(int) Math.min(Ymin, par.getY14());
			int Ymax=(int) Math.max(par.getY11(), par.getY12());
			Ymax=(int) Math.max(Ymax, par.getY13());
			Ymax=(int) Math.max(Ymax, par.getY14());
			IntegerPoint A = new IntegerPoint ();
			IntegerPoint B = new IntegerPoint ();
			IntegerPoint C= new IntegerPoint ();
			IntegerPoint D= new IntegerPoint ();
			A.setX((int)par.getX11()-c);
			A.setY((int)par.getY11());
			B.setX((int)par.getX12()-c);
			B.setY((int)par.getY12());
			C.setX((int)par.getX13()-c);
			C.setY((int)par.getY13());
			D.setX((int)par.getX14()-c);
			D.setY((int)par.getY14());
			Parallelogram parTemp=new Parallelogram ();
			parTemp.setX11(A.getX());
			parTemp.setX12(B.getX());
			parTemp.setX13(C.getX());
			parTemp.setX14(D.getX());
			parTemp.setY11(A.getY());
			parTemp.setY12(B.getY());
			parTemp.setY13(C.getY());
			parTemp.setY14(D.getY());
			segments1=l.SegmentDiscretization(A, B);
			int u1=segments1.size()-1;
			while(u1!=0){//setting all fields from segment AB to 1
				IntegerPoint E=new IntegerPoint();
				E=segments1.get(u1);
				matrix[E.getX()][E.getY()]=1;
				u1--;
			}
			segments2=l.SegmentDiscretization(B, C);
			int u2=segments2.size()-1;
			while(u2!=0){//setting all fields from segment BC to 1
				IntegerPoint E=new IntegerPoint();
				E=segments2.get(u2);
				matrix[E.getX()][E.getY()]=1;
				u2--;
			}
			segments3=l.SegmentDiscretization(C, D);
			int u3=segments3.size()-1;
			while(u3!=0){//setting all fields from segment CD to 1
				IntegerPoint E=new IntegerPoint();
				E=segments3.get(u3);
				matrix[E.getX()][E.getY()]=1;
				u3--;
			}
			segments4=l.SegmentDiscretization(D, A);
			int u4=segments4.size()-1;
			while(u4!=0){//setting all fields from segment DA to 1
				IntegerPoint E=new IntegerPoint();
				E=segments4.get(u4);
				matrix[E.getX()][E.getY()]=1;
				u4--;
			}
			for(int i=Xmin+1;i<Xmax;i++)
				for(int j=Ymin+1;j<Ymax;j++){
					IntegerPoint F=new IntegerPoint ();
					F.setX(i);
					F.setY(j);
					boolean inside=false;
					inside=l.PointInsideParallelogram2(parTemp, F);
					if(inside==true){
						matrix[i][j]=1;
					}
				}
			p--;
		}
		
		return matrix;
	}
	//*******
public int [][] DiscretizationOfLaneAddingScanStep(FWL lane, Patterns inPatterns, int ScanStep){//intPatterns are all patterns for one sheet
		
		int a=0, b=0, c=0;
		a=(int)lane.getLength();
		b=(int)lane.getWidth();
		c=(int)lane.getStart();//the shift of all coordinates to set a lane beginning to (0,0)
		int [][] matrix= new int [ b][ a];
		SetOfPatternsForALane set=new SetOfPatternsForALane();
		Patterns shape=new Patterns ();
		ArrayList <Rectangel> tempR=new ArrayList <Rectangel>();
		ArrayList <Triangel> tempT=new ArrayList <Triangel>();
		ArrayList <Parallelogram> tempP=new ArrayList <Parallelogram>();
		Triangel tri=new Triangel ();
		Rectangel rec=new Rectangel();
		Parallelogram par=new Parallelogram ();
		Line l=new Line ();
		ArrayList <IntegerPoint> segments1=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> segments2=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> segments3=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> segments4=new ArrayList <IntegerPoint>();
		//seating to whole matrix to 0
		for(int i=0;i<b-1;i++){
			for(int j=0;j<a-1;j++){
				matrix[i][j]=0;
			}
		}
		shape=set.PatternsForOneLane(inPatterns, lane);	//obtaining shape for one lane
		tempR=shape.getRectangels();//list of rectangles for this lane
		tempT=shape.getTriangels();//list of triangles for this lane
		tempP=shape.getParallelograms();//list of parallelograms for this lane
		int r=tempR.size()-1;//last index
		int t=tempT.size()-1;
		int p=tempP.size()-1;
		while(r>=0){//setting 1 for all rectangles 
			rec=tempR.get(r);
			//finding min and max for for loops
			int Xmin=(int) Math.min(rec.getX11(), rec.getX12())-c;
			int Xmax=(int) Math.max(rec.getX11(), rec.getX12())-c;
			int Ymin=(int) Math.min(rec.getY11(), rec.getY12());
			int Ymax=(int) Math.max(rec.getY11(), rec.getY12());
			//adding ScanStep
			if(Xmin-ScanStep<0)
				Xmin=0;
			else Xmin=Xmin-ScanStep;
			if(Xmax+ScanStep>lane.getWidth()-1)
				Xmax=(int) lane.getWidth()-1;
			else Xmax=Xmax+ScanStep;
			if(Ymin-ScanStep<0)
				Ymin=0;
			else Ymin=Ymin-ScanStep;
			if(Ymax+ScanStep>a-1)
				Ymax=(int) a-1;
			else Ymax=Ymax+ScanStep;
			
			IntegerPoint A = new IntegerPoint ();
			IntegerPoint B = new IntegerPoint ();
			IntegerPoint C= new IntegerPoint ();
			IntegerPoint D= new IntegerPoint ();
			
			//A.setX((int)rec.getX11()-c);
			A.setX((int)rec.getX11()-c);
			A.setY((int)rec.getY11());
			if(A.getX()-ScanStep<0)
				A.setX(0);
			else A.setX(A.getX()-ScanStep);
			if(A.getY()+ScanStep>a-1)
				A.setY(a-1);
			else A.setY(A.getY()+ScanStep);
			B.setX((int)rec.getX12()-c);
			B.setY((int)rec.getY11());
			if(B.getX()+ScanStep>b-1)
				B.setX(b-1);
			else B.setX(B.getX()+ScanStep);
			if(B.getY()+ScanStep>a-1)
				B.setY(a-1);
			else B.setY(B.getY()+ScanStep);
			C.setX((int)rec.getX12()-c);
			C.setY((int)rec.getY12());
			if(C.getX()+ScanStep>b-1)
				C.setX(b-1);
			else C.setX(C.getX()+ScanStep);
			if(C.getY()-ScanStep<0)
				C.setY(0);
			else C.setY(C.getY()-ScanStep);
			D.setX((int)rec.getX11()-c);
			D.setY((int)rec.getY12());
			if(D.getX()-ScanStep<0)
				D.setX(0);
			else D.setX(D.getX()-ScanStep);
			if(D.getY()-ScanStep<0)
				D.setY(0);
			else D.setY(D.getY()-ScanStep);
			Rectangel rectemp=new Rectangel ();
			rectemp.setX11(A.getX());
			rectemp.setX12(C.getX());
			rectemp.setY11(A.getY());
			rectemp.setY12(C.getY());
			segments1=l.SegmentDiscretization(A, B);
			int u1=segments1.size()-1;
			while(u1!=0){//setting all fields from segment AB to 1
				IntegerPoint E=new IntegerPoint();
						E=segments1.get(u1);
				matrix[E.getX()][E.getY()]=1;

				u1--;
			}
			segments2=l.SegmentDiscretization(B, C);
			int u2=segments2.size()-1;
			while(u2!=0){//setting all fields from segment BC to 1
				IntegerPoint E1=new IntegerPoint();
				E1=segments2.get(u2);
				matrix[E1.getX()][E1.getY()]=1;
				u2--;
			}
			segments3=l.SegmentDiscretization(C, D);
			int u3=segments3.size()-1;
			while(u3!=0){//setting all fields from segment CD to 1
				IntegerPoint E=new IntegerPoint();
				E=segments3.get(u3);
				matrix[E.getX()][E.getY()]=1;
				u3--;
			}
			segments4=l.SegmentDiscretization(D, A);
			int u4=segments4.size()-1;
			while(u4!=0){//setting all fields from segment DA to 1
				IntegerPoint E=new IntegerPoint();
				E=segments4.get(u4);
				matrix[E.getX()][E.getY()]=1;
				u4--;
			}
			//setting all elements inside rectangle to 1
			for(int i=Xmin+1;i<Xmax;i++)
				for(int j=Ymin+1;j<Ymax;j++){
					IntegerPoint F=new IntegerPoint ();
					F.setX(i);
					F.setY(j);
					boolean inside=false;
					inside=l.PointInsideRectangel2(rectemp, F);
					if(inside==true){
						matrix[(int) F.getX()][(int) F.getY()]=1;
					}
				}
			r--;
		}
		
		while(t>=0){//setting 1 for all triangles 
			tri=tempT.get(t);
			//finding min and max for for loops
			int Xmin=(int) Math.min(tri.getX11(), tri.getX12());
			Xmin=(int)Math.min(Xmin, tri.getX13())-c;
			int Xmax=(int) Math.max(tri.getX11(), tri.getX12());
			Xmax=(int)Math.max(Xmax, tri.getX13())-c;
			int Ymin=(int) Math.min(tri.getY11(), tri.getY12());
			Ymin=(int)Math.min(Ymin, tri.getY13());
			int Ymax=(int) Math.max(tri.getY11(), tri.getY12());
			Ymax=(int)Math.max(Ymax, tri.getY13());
			//adding ScanStep
			if(Xmin-ScanStep<0)
				Xmin=0;
			else Xmin=Xmin-ScanStep;
			if(Xmax+ScanStep>lane.getWidth()-1)
				Xmax=(int) lane.getWidth()-1;
			else Xmax=Xmax+ScanStep;
			if(Ymin-ScanStep<0)
				Ymin=0;
			else Ymin=Ymin-ScanStep;
			if(Ymax+ScanStep>a-1)
				Ymax=(int) a-1;
			else Ymax=Ymax+ScanStep;
			IntegerPoint A = new IntegerPoint ();
			IntegerPoint B = new IntegerPoint ();
			IntegerPoint C= new IntegerPoint ();
			A.setX((int)tri.getX11()-c);
			A.setY((int)tri.getY11());
			B.setX((int)tri.getX12()-c);
			B.setY((int)tri.getY12());
			C.setX((int)tri.getX13()-c);
			C.setY((int)tri.getY13());
			int ymin=Math.min(A.getY(), B.getY());
			ymin=Math.min(ymin, C.getY());
			int ymax=Math.max(A.getY(), B.getY());
			ymax=Math.max(ymax, C.getY());
			if(A.getX()-ScanStep<0)
				A.setX(0);
			else A.setX(A.getX()-ScanStep);
			if(B.getX()+ScanStep>b-1)
			B.setX(b-1);
			else B.setX(B.getX()+ScanStep);
			if(C.getX()==A.getX())
				C.setX(A.getX());
			else if(C.getX()==B.getX())
				C.setX(B.getX());
			if(A.getY()==ymin){
				if(A.getY()-ScanStep<0)
					A.setY(0);
				else A.setY(A.getY()-ScanStep);
			}else if(A.getY()==ymax){
				if(A.getY()+ScanStep>a-1)
					A.setY(a-1);
				else A.setY(A.getY()+ScanStep);
			}
			if(B.getY()==ymin){
				if(B.getY()-ScanStep<0)
					B.setY(0);
				else B.setY(B.getY()-ScanStep);
			}else if(B.getY()==ymax){
				if(B.getY()+ScanStep>a-1)
					B.setY(a-1);
				else B.setY(B.getY()+ScanStep);
			}
			if(C.getY()==ymin){
				if(C.getY()-ScanStep<0)
					C.setY(0);
				else C.setY(C.getY()-ScanStep);
			}else if(C.getY()==ymax){
				if(C.getY()+ScanStep>a-1)
					C.setY(a-1);
				else C.setY(C.getY()+ScanStep);
			}
			Triangel triTemp=new Triangel ();
			triTemp.setX11(A.getX());
			triTemp.setX12(B.getX());
			triTemp.setX13(C.getX());
			triTemp.setY11(A.getY());
			triTemp.setY12(B.getY());
			triTemp.setY13(C.getY());
			
			segments1=l.SegmentDiscretization(A, B);
			int u1=segments1.size()-1;
			while(u1!=0){//setting all fields from segment AB to 1
				IntegerPoint E=new IntegerPoint();
				E=segments1.get(u1);
				matrix[E.getX()][E.getY()]=1;
				u1--;
			}
			segments2=l.SegmentDiscretization(B, C);
			int u2=segments2.size()-1;
			while(u2!=0){//setting all fields from segment BC to 1
				IntegerPoint E=new IntegerPoint();
				E=segments2.get(u2);
				matrix[E.getX()][E.getY()]=1;
				u2--;
			}
			segments3=l.SegmentDiscretization(C, A);
			int u3=segments3.size()-1;
			while(u3!=0){//setting all fields from segment CD to 1
				IntegerPoint E=new IntegerPoint();
				E=segments3.get(u3);
				matrix[E.getX()][E.getY()]=1;
				u3--;
			}
			
			//setting all elements inside triangle to 1
			for(int i=Xmin+1;i<Xmax;i++)
				for(int j=Ymin+1;j<Ymax;j++){
					IntegerPoint F=new IntegerPoint ();
					F.setX(i);
					F.setY(j);
					boolean inside=false;
					inside=l.PointInsideTriangel(triTemp, F);
					if(inside==true){
						matrix[i][j]=1;
					}
				}
			t--;
		}
		
		while(p>=0){//setting 1 for all parallelograms 
			par=tempP.get(p);
			//finding min and max for for loops
			int Xmin=(int) Math.min(par.getX11(), par.getX12());
			Xmin=(int) Math.min(Xmin, par.getX13());
			Xmin=(int) Math.min(Xmin, par.getX14())-c;
			int Xmax=(int) Math.max(par.getX11(), par.getX12());
			Xmax=(int) Math.max(Xmax, par.getX13());
			Xmax=(int) Math.max(Xmax, par.getX14())-c;
			int Ymin=(int) Math.min(par.getY11(), par.getY12());
			Ymin=(int) Math.min(Ymin, par.getY13());
			Ymin=(int) Math.min(Ymin, par.getY14());
			int Ymax=(int) Math.max(par.getY11(), par.getY12());
			Ymax=(int) Math.max(Ymax, par.getY13());
			Ymax=(int) Math.max(Ymax, par.getY14());
			//adding ScanStep
			if(Xmin-ScanStep<0)
				Xmin=0;
			else Xmin=Xmin-ScanStep;
			if(Xmax+ScanStep>lane.getWidth()-1)
				Xmax=(int) lane.getWidth()-1;
			else Xmax=Xmax+ScanStep;
			if(Ymin-ScanStep<0)
				Ymin=0;
			else Ymin=Ymin-ScanStep;
			if(Ymax+ScanStep>a-1)
				Ymax=(int) a-1;
			else Ymax=Ymax+ScanStep;
			IntegerPoint A = new IntegerPoint ();
			IntegerPoint B = new IntegerPoint ();
			IntegerPoint C= new IntegerPoint ();
			IntegerPoint D= new IntegerPoint ();
			A.setX((int)par.getX11()-c);
			A.setY((int)par.getY11());
			if(A.getX()-ScanStep<0)
				A.setX(0);
			else A.setX(A.getX()-ScanStep);
			if(A.getY()+ScanStep>a-1)
				A.setY(a-1);
			else A.setY(A.getY()+ScanStep);
			B.setX((int)par.getX12()-c);
			B.setY((int)par.getY12());
			if(B.getX()+ScanStep>b-1)
				B.setX(b-1);
			else B.setX(B.getX()+ScanStep);
			if(B.getY()+ScanStep>a-1)
				B.setY(a-1);
			else B.setY(B.getY()+ScanStep);
			C.setX((int)par.getX13()-c);
			C.setY((int)par.getY13());
			if(C.getX()+ScanStep>b-1)
				C.setX(b-1);
			else C.setX(C.getX()+ScanStep);
			if(C.getY()-ScanStep<0)
				C.setY(0);
			else C.setY(C.getY()-ScanStep);
			D.setX((int)par.getX14()-c);
			D.setY((int)par.getY14());
			if(D.getX()-ScanStep<0)
				D.setX(0);
			else D.setX(D.getX()-ScanStep);
			if(D.getY()-ScanStep<0)
				D.setY(0);
			else D.setY(D.getY()-ScanStep);
			Parallelogram parTemp=new Parallelogram ();
			parTemp.setX11(A.getX());
			parTemp.setX12(B.getX());
			parTemp.setX13(C.getX());
			parTemp.setX14(D.getX());
			parTemp.setY11(A.getY());
			parTemp.setY12(B.getY());
			parTemp.setY13(C.getY());
			parTemp.setY14(D.getY());
			segments1=l.SegmentDiscretization(A, B);
			int u1=segments1.size()-1;
			while(u1!=0){//setting all fields from segment AB to 1
				IntegerPoint E=new IntegerPoint();
				E=segments1.get(u1);
				matrix[E.getX()][E.getY()]=1;
				u1--;
			}
			segments2=l.SegmentDiscretization(B, C);
			int u2=segments2.size()-1;
			while(u2!=0){//setting all fields from segment BC to 1
				IntegerPoint E=new IntegerPoint();
				E=segments2.get(u2);
				matrix[E.getX()][E.getY()]=1;
				u2--;
			}
			segments3=l.SegmentDiscretization(C, D);
			int u3=segments3.size()-1;
			while(u3!=0){//setting all fields from segment CD to 1
				IntegerPoint E=new IntegerPoint();
				E=segments3.get(u3);
				matrix[E.getX()][E.getY()]=1;
				u3--;
			}
			segments4=l.SegmentDiscretization(D, A);
			int u4=segments4.size()-1;
			while(u4!=0){//setting all fields from segment DA to 1
				IntegerPoint E=new IntegerPoint();
				E=segments4.get(u4);
				matrix[E.getX()][E.getY()]=1;
				u4--;
			}
			for(int i=Xmin+1;i<Xmax;i++)
				for(int j=Ymin+1;j<Ymax;j++){
					IntegerPoint F=new IntegerPoint ();
					F.setX(i);
					F.setY(j);
					boolean inside=false;
					inside=l.PointInsideParallelogram2(parTemp, F);
					if(inside==true){
						matrix[i][j]=1;
					}
				}
			p--;
		}
		
		return matrix;
	}

	//*******
public int [][] UnitingResults(int [][] matrix1, int [][] matrix2, int ShiftX, int ShiftY, int direction, int n, int m){
	int [][] matrixtemp=new int [n][m];
	int [][] matrixtemp1=new int [n][m];
	ZeroPadding zp=new ZeroPadding();
	
	matrixtemp1=zp.Shifting(matrix2, ShiftX, ShiftY, n, m, direction);
	
	for(int i=0;i<n;i++)
		for(int j=0;j<m;j++){
			if(matrix1[i][j]==1)
				matrixtemp[i][j]=1;
			else if(matrixtemp1[i][j]==1)
				matrixtemp[i][j]=1;
			else matrixtemp[i][j]=0;
		}
	
	return matrixtemp;
}

}
