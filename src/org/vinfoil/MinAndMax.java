package org.vinfoil;

import java.util.*;
public class MinAndMax {

	private Patterns P;
	private Rectangel R;
	private Triangel T;
	private Parallelogram PR;
	private ArrayList<Rectangel> Rectangels;
	private ArrayList<Triangel> Triangels;
	private ArrayList<Parallelogram> Parallelograms;
	
	
	public float TheMostLeftPointOfAllRectagels( ArrayList<Rectangel> R1){

		//int i=1;
		float w=1100,wr=1100,wt=1100,wp=1100;
		//while (R1!=null){
		for(int i=0;i<R1.size();i++){
			R=R1.get(i);
			wr=Math.min(wr,R.getX11());
			wr=Math.min(wr, R.getX12());
			//i++;
		}
		//The most left point from all rectangles
		w=Math.min(wr, w);
		return w;
	}
	public float TheYminOfAllRectagels( ArrayList<Rectangel> R1){

		//int i=1;
		float w=1100,wr=1100,wt=1100,wp=1100;
		//while (R1!=null){
		for(int i=0;i<R1.size();i++){
			R=R1.get(i);
			wr=Math.min(wr,R.getY11());
			wr=Math.min(wr, R.getY12());
			//i++;
		}
		//The Ymin from all rectangles
		w=Math.min(wr, w);
		return w;
	}
	
	public float TheMostLeftPointOfAllTriangels(ArrayList<Triangel> T1){
		//int j=1;
		float w=1100, wt=1100;
		//while (T1!=null){
		for(int j=0;j<T1.size();j++){
			T=T1.get(j);
			wt=Math.min(wt,T.getX11());
			wt=Math.min(wt, T.getX12());
			wt=Math.min(wt, T.getX13());
			//j++;
		}
		//The most left point from all triangles
		w=Math.min(wt, w);
		return w;
	}
	public float TheYminOfAllTriangels(ArrayList<Triangel> T1){
		//int j=1;
		float w=1100, wt=1100;
		//while (T1!=null){
		for(int j=0;j<T1.size();j++){
			T=T1.get(j);
			wt=Math.min(wt,T.getY11());
			wt=Math.min(wt, T.getY12());
			wt=Math.min(wt, T.getY13());
			//j++;
		}
		//The Ymin from all triangles
		w=Math.min(wt, w);
		return w;
	}
	
	public float TheMostLeftPointOfAllParallelograms(ArrayList<Parallelogram> P){
		//int k=1;
		float w=1100,wp=1100;
		//while(P!=null){
		for(int k=0;k<P.size();k++){
			PR=P.get(k);
			wp=Math.min(wp, PR.getX11());
			wp=Math.min(wp, PR.getX12());
			wp=Math.min(wp, PR.getX13());
			wp=Math.min(wp, PR.getX14());
		
		}
		//The most left point from all parallelograms
		w=Math.min(wp, w);
			
		return w;
	}
	
	public float TheYminOfAllParallelograms(ArrayList<Parallelogram> P){
		//int k=1;
		float w=1100,wp=1100;
		//while(P!=null){
		for(int k=0;k<P.size();k++){
			PR=P.get(k);
			wp=Math.min(wp, PR.getY11());
			wp=Math.min(wp, PR.getY12());
			wp=Math.min(wp, PR.getY13());
			wp=Math.min(wp, PR.getY14());
		
		}
		//The Ymin from all parallelograms
		w=Math.min(wp, w);
			
		return w;
	}
	
	public float TheMostRightPointOfAllRectangels(ArrayList<Rectangel> R1){
		
		//int i=1;
		float w=0,wr=0,wt=0,wp=0;
		//while (R1!=null){
		for(int i=0;i<R1.size();i++){
			R=R1.get(i);
			wr=Math.max(wr,R.getX12());
			wr=Math.max(wr,R.getX11());
			
		}
		w=Math.max(wr, w);
		return w;
	}
	
	public float TheYmaxOfAllRectangels(ArrayList<Rectangel> R1){
		
		//int i=1;
		float w=0,wr=0,wt=0,wp=0;
		//while (R1!=null){
		for(int i=0;i<R1.size();i++){
			R=R1.get(i);
			wr=Math.max(wr,R.getY12());
			wr=Math.max(wr,R.getY11());
			
		}
		w=Math.max(wr, w);
		return w;
	}

	public float TheMostRightOfAllTriangels(ArrayList<Triangel> T1){
		//int j=1;
		float w=0,wt=0;
		//while (T1!=null){
		for(int j=0;j<T1.size();j++){
			T=T1.get(j);
			wt=Math.max(wt,T.getX11());
			wt=Math.max(wt,T.getX12());
			wt=Math.max(wt, T.getX13());
		
		}
		w=Math.max(wt, w);
		return w;
	}
	
	public float TheYmaxOfAllTriangels(ArrayList<Triangel> T1){
		//int j=1;
		float w=0,wt=0;
		//while (T1!=null){
		for(int j=0;j<T1.size();j++){
			T=T1.get(j);
			wt=Math.max(wt,T.getY11());
			wt=Math.max(wt,T.getY12());
			wt=Math.max(wt, T.getY13());
		
		}
		w=Math.max(wt, w);
		return w;
	}
	
	public float TheMostRightOfAllParallelograms(ArrayList<Parallelogram> P1){
		//int k=1;
		float w=0,wp=0;
		//while(P.Paralelograms!=null){
		for(int k=0;k<P1.size();k++){
			PR=P1.get(k);
			wp=Math.max(wp, PR.getX11());
			wp=Math.max(wp, PR.getX12());
			wp=Math.max(wp, PR.getX13());
			wp=Math.max(wp, PR.getX14());
			//k++;
		}
		w=Math.max(wp, w);
			
		return w;
		
	}
	
	public float TheYmaxOfAllParallelograms(ArrayList<Parallelogram> P1){
		//int k=1;
		float w=0,wp=0;
		//while(P.Paralelograms!=null){
		for(int k=0;k<P1.size();k++){
			PR=P1.get(k);
			wp=Math.max(wp, PR.getY11());
			wp=Math.max(wp, PR.getY12());
			wp=Math.max(wp, PR.getY13());
			wp=Math.max(wp, PR.getY14());
			//k++;
		}
		w=Math.max(wp, w);
			
		return w;
		
	}
	
	public float TheXminOfAllPatterns(Patterns P){
		
		float Xmin=0;
		Xmin=this.TheMostLeftPointOfAllRectagels(P.getRectangels());
		Xmin=Math.min(Xmin, this.TheMostLeftPointOfAllTriangels(P.getTriangels()));
		Xmin=Math.min(Xmin,this.TheMostLeftPointOfAllParallelograms(P.getParallelograms()));
		
		return Xmin;
	}
	
	public float TheXmaxOfAllPatterns(Patterns P){
		
		float Xmax=0;
		Xmax=this.TheMostRightPointOfAllRectangels(P.getRectangels());
		Xmax=Math.max(Xmax, this.TheMostRightOfAllTriangels(P.getTriangels()));
		Xmax=Math.max(Xmax, this.TheMostRightOfAllParallelograms(P.getParallelograms()));
		return Xmax;
	}
	
	public float TheYminOfAllPatterns (Patterns P){
		
		float Ymin=0;
		Ymin=this.TheYminOfAllRectagels(P.getRectangels());
		Ymin=Math.min(Ymin, this.TheYminOfAllTriangels(P.getTriangels()));
		Ymin=Math.min(Ymin, this.TheYminOfAllParallelograms(P.getParallelograms()));
		return Ymin;
	}
	
	public float TheYmaxOfAllPatterns(Patterns P){
		
		float Ymax=0;
		Ymax=this.TheYmaxOfAllRectangels(P.getRectangels());
		Ymax=Math.max(Ymax, this.TheYmaxOfAllTriangels(P.getTriangels()));
		Ymax=Math.max(Ymax, this.TheYmaxOfAllParallelograms(P.getParallelograms()));
		return Ymax;
	}
	
}
