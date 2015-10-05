package org.vinfoil.thread;

import org.vinfoil.HoldingResult;
import org.vinfoil.Overlaping;
import org.vinfoil.Point;
import org.vinfoil.ZeroPadding;

import java.lang.*;
import java.util.*;

public class ThreadHoldingConstraint4 extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.HS=this.Direction4();
		System.out.println("fourth thread "+this.HS.size());
	}
	public ThreadHoldingConstraint4(){
		//constructor
		super();
	}
	//matrices need to be same dimension we zero pad the smaller, the m=CPC
	public int [][] SetingMaticesToSameDimension(){
		int [][] matrix3=new int [this.n1][this.m];
		
		for(int j=0;j<this.m;j++){
			for(int i=0;i<this.n2;i++){
			matrix3[i][j]=this.discreteLane2[i][j];
		}
		for(int i=this.n2;i<this.n1;i++){
			matrix3[i][j]=0;
		}
		}
		return matrix3;
	}
	public List <HoldingResult> Direction4(){
		List<HoldingResult> templist = Collections.synchronizedList(new ArrayList<HoldingResult>());
		//limitation for the shifts
		int ShiftX=0;
		int ShiftY=this.m-this.Y2max;
		
		int [][] matrixTemp=new int [this.n1][this.m];
  		int [][] matrixPadded=new int [this.n1][this.m];
  		if(this.n2<this.n1)
  		matrixPadded=this.SetingMaticesToSameDimension();
  		else matrixPadded=this.discreteLane2;
		ZeroPadding zp=new ZeroPadding ();
		Overlaping over=new Overlaping ();
		boolean shift=true;// it is true by default -> if it is false then there is no overlapping of patterns
		
		//all possible not overlapping solutions (x,y shifts) 

		List <Point> listOfEmbeding=Collections.synchronizedList(new ArrayList <Point>());
		
		
		
			for(int j=0;j<ShiftY;j++){
				//zero padding for appropriate shift
				matrixTemp=zp.Shifting(matrixPadded, 0, j, this.n1, this.m, 4);
				//checking if at this shift we have overlapping of patterns from both lanes
				shift=over.CheckOverlapingOfTwoDiscretizedLanes(this.discreteLane1, matrixTemp, this.X1min, this.X1max, this.X2min, this.X2max, this.Y1min, this.Y1max, this.Y2min+j, this.Y2max+j);
				if(shift==false){
					//ajde da isprobam drugi pristup hocu odmah da trazim 20mm embeding
					listOfEmbeding=over.ScaningForEmbedingWithTwoLanes(this.discreteLane1, matrixTemp, this.ScanStep, this.n1, this.m, this.X1min, this.X1max, this.X2min, this.X2max, this.Y1min, this.Y1max, this.Y2min+j, this.Y2max+j);
					//znaci ako imam osam clana cije je prvo polje X=0, onda ne mogu da embedujem u ovom resenju u tom slucaju samo preskacem ceo ovaj deo i idem na neko drugo resenje
					synchronized (listOfEmbeding){
						Iterator<Point> iterator = listOfEmbeding.iterator();
						//ako dodas templist.size()==0 ond ides na priblizno prvi hit (najvise osam resenja)
						//ako ne onda imas sva resenja
					       while (iterator.hasNext()&&templist.size()==0){
					    	   int direction =(int) iterator.next().getY();
					    	   HoldingResult temp=new HoldingResult();
						switch(direction){
						case 1:
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(-this.ScanStep);
							temp.setShiftY(j-this.ScanStep);
							templist.add(temp);
							break;
						case 2:
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(-this.ScanStep);
							temp.setShiftY(j);
							templist.add(temp);
							break;
						case 3:
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(-this.ScanStep);
							temp.setShiftY(j+this.ScanStep);
							templist.add(temp);
							break;
						case 4:
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(0);
							temp.setShiftY(j+this.ScanStep);
							templist.add(temp);
							break;
						case 5:
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(+this.ScanStep);
							temp.setShiftY(j+this.ScanStep);
							templist.add(temp);
							break;
						case 6:
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(+this.ScanStep);
							temp.setShiftY(j);
							templist.add(temp);
							break;
						case 7:
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(+this.ScanStep);
							temp.setShiftY(j-this.ScanStep);
							templist.add(temp);
							break;
						case 8:
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(0);
							temp.setShiftY(j-this.ScanStep);
							templist.add(temp);
							break;
						}
					}
					}
				}
				shift=true;
			}
		
		return templist;
	}
	//treba da ih napunis pre koriscenja da ne zaboravis
		private int [][] discreteLane1;
		private int [][] discreteLane2;
		private int X1min;
		private int X1max;
		private int Y1min;
		private int Y1max;
		private int X2min;
		private int X2max;
		private int Y2min;
		private int Y2max;
		private int n1;
		private int n2;
		private int m;
		private int ScanStep;
		private List <HoldingResult> HS;
		public int [][] getDiscreteLane1(){
	    	return discreteLane1;
	    }
	    public void setDiscreteLane1(int [][] Top){
	    	discreteLane1=Top;
	    }
	    public int [][] getDiscreteLane2(){
	    	return discreteLane2;
	    }
	    public void setDiscreteLane2(int [][] Top){
	    	discreteLane2=Top;
	    }
	    public int getX1min(){
	    	return X1min;
	    }
	    public void setX1min(int min){
	    	X1min=min;
	    }
	    public int getX1max(){
	    	return X1max;
	    }
	    public void setX1max(int max){
	    	X1max=max;
	    }
	    public int getX2min(){
	    	return X2min;
	    }
	    public void setX2min(int min){
	    	X2min=min;
	    }
	    public int getX2max(){
	    	return X2max;
	    }
	    public void setX2max(int max){
	    	X2max=max;
	    }
	    public int getY1min(){
	    	return Y1min;
	    }
	    public void setY1min(int min){
	    	Y1min=min;
	    }
	    public int getY1max(){
	    	return Y1max;
	    }
	    public void setY1max(int max){
	    	Y1max=max;
	    }
	    public int getY2min(){
	    	return Y2min;
	    }
	    public void setY2min(int min){
	    	Y2min=min;
	    }
	    public int getY2max(){
	    	return Y2max;
	    }
	    public void setY2max(int max){
	    	Y2max=max;
	    }
	    public int getN1(){
	    	return n1;
	    }
	    public void setN1(int min){
	    	n1=min;
	    }
	    public int getN2(){
	    	return n2;
	    }
	    public void setN2(int min){
	    	n2=min;
	    }
	    public int getM(){
	    	return m;
	    }
	    public void setM(int min){
	    	m=min;
	    }
	    public int getScanStep(){
	    	return ScanStep;
	    }
	    public void setScanStep(int min){
	    	ScanStep=min;
	    }
	    public List <HoldingResult> getHS(){
		  	  return HS;
		    }
		    public void setHs(List <HoldingResult> hs){
		  	  HS=hs;
		    }
}
