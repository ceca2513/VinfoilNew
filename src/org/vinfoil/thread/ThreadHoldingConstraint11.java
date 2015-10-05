package org.vinfoil.thread;

import org.vinfoil.HoldingResult;
import org.vinfoil.Overlaping;
import org.vinfoil.Point;
import org.vinfoil.ZeroPadding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ThreadHoldingConstraint11 extends Thread {
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		HoldingResult temp=new HoldingResult();
		this.HS=this.Direction1();
		System.out.println("first thread "+this.HS.size());
	}
	public ThreadHoldingConstraint11(){
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
	public List <HoldingResult> Direction1(){
		List<HoldingResult> templist = Collections.synchronizedList(new ArrayList<HoldingResult>());
		//limitation for the shifts
		int ShiftX=0;
		int ShiftY=0;
		
			ShiftX=this.X2min;
			 ShiftY=this.Y2min;

		
		
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
		
		
		for(int i=1;i<ShiftX;i++)
			for(int j=1;j<ShiftY;j++){
				//zero padding for appropriate shift
				matrixTemp=zp.Shifting(matrixPadded, i, j, this.n1, this.m, 1);
				//checking if at this shift we have overlapping of patterns from both lanes
				shift=over.CheckOverlapingOfTwoDiscretizedLanes(this.discreteLane1, matrixTemp, this.X1min, this.X1max, this.X2min-i, this.X2max-i, this.Y1min, this.Y1max, this.Y2min-j, this.Y2max-j);
				if(shift==false){
					HoldingResult temp=new HoldingResult();
					temp.setRepassing(true);
					temp.setDirection(1);
					temp.setShiftX(-i);
					temp.setShiftY(-j);
					templist.add(temp);
				}
			
				
				shift=true;
			}
		
		return templist;
	}
	
	//treba da ih napunis pre koriscenja da ne zaboravis

}
