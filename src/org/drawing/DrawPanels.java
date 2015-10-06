package org.drawing;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.*;

import javax.swing.*;

import org.vinfoil.Discretization;
import org.vinfoil.FWL;
import org.vinfoil.Patterns;
import org.vinfoil.ZeroPadding;

public class DrawPanels extends JPanel{
	
	protected FWL myFWL1;
	protected FWL myFWL2;
	protected Patterns allPatterns;
	protected int ShiftX;
	protected int ShiftY;
	protected int Direction;

	
	public DrawPanels(FWL temp1, FWL temp2){
		super();
		setBackground(Color.WHITE);
		//this.myFWL1=temp1;
		//this.myFWL2=temp2;
	}
	
	public void paintComponent (Graphics g){
		int width=getWidth();
		int height=getHeight();
		
		super.paintComponent(g);
		
		DrawResults(g);
		
	}

	private void DrawResults(Graphics g) {
		// TODO Auto-generated method stub
		int OriginX=50;
		int OriginY=50;
		int ZoomSize=50000000;
		int stringoffset = 250;
		
		Rectangle theFWL1=new Rectangle();
		Rectangle theFWL2=new Rectangle();
		
		theFWL1.x=0+OriginX;
		theFWL1.y=0+OriginY;
		theFWL1.width=(int) this.myFWL1.getWidth();
		theFWL1.height=(int) this.myFWL1.getLength();//drawing first lane
		
		theFWL2.x=0+OriginX;		
		theFWL2.y=0+OriginY;
		theFWL2.width=(int) this.myFWL2.getWidth();
		theFWL2.height=(int) this.myFWL2.getLength();
		

		
		if(this.Direction==1){
			if(this.ShiftX==0){
				theFWL2.x=theFWL2.x-this.ShiftX;
			}else{
			theFWL2.x=theFWL2.x-this.ShiftX;
			}if(this.ShiftY==0){
				theFWL2.y=theFWL2.y-this.ShiftY;	
			}else{
			theFWL2.y=theFWL2.y-this.ShiftY;
			}
		}else
		if(this.Direction==2){
			theFWL2.x=theFWL2.x-this.ShiftX;
			
		}else
		if(this.Direction==3){
			if(this.ShiftX==0){
				theFWL2.x=theFWL2.x-this.ShiftX;
			}else{
				theFWL2.x=theFWL2.x-this.ShiftX;
			}
			if(this.ShiftY==0){
				theFWL2.y=theFWL2.y+this.ShiftY;
			}else{
				theFWL2.y=theFWL2.y+this.ShiftY;
			}
	
		}else
		if(this.Direction==4){
			
			theFWL2.y=theFWL2.y+this.ShiftY;
					
		}else
		if(this.Direction==5){
			if(this.ShiftX==0){
				theFWL2.x=theFWL2.x+this.ShiftX;
			}else{
				theFWL2.x=theFWL2.x+this.ShiftX;
			}
			if(this.ShiftY==0){
				theFWL2.y=theFWL2.y+this.ShiftY;		
			}else{
				theFWL2.y=theFWL2.y+this.ShiftY;		
			}	
		}else
		if(this.Direction==6){
			theFWL2.x=theFWL2.x+this.ShiftX;
					
		}else
		if(this.Direction==7){
			if(this.ShiftX==0){
				theFWL2.x=theFWL2.x+this.ShiftX;	
			}else{
				theFWL2.x=theFWL2.x+this.ShiftX;
			}
			if(this.ShiftY==0){
				theFWL2.y=theFWL2.y-this.ShiftY;	
			}else{
				theFWL2.y=theFWL2.y-this.ShiftY;
			}					
		}else
		if(this.Direction==8){
			theFWL2.y=theFWL2.y-this.ShiftY;
						
		}
		//drawing second lane

		Discretization myFWLDis=new Discretization ();
		int [][] matrix1 =new int [(int) this.myFWL1.getWidth()][(int) this.myFWL1.getLength()];
		int [][] matrix2 =new int [(int) this.myFWL2.getWidth()][(int) this.myFWL2.getLength()];
		
		matrix1=myFWLDis.DiscretizationOfLane(this.myFWL1, this.allPatterns);
		matrix2=myFWLDis.DiscretizationOfLane(this.myFWL2, this.allPatterns);
		
		ZeroPadding zpTemp=new ZeroPadding();
		//it needs to be zeropadded first to be the same dimension as lane1?
		
		//matrix2=zpTemp.Shifting(matrix2, this.ShiftX, this.ShiftY, (int)this.myFWL1.getWidth(), (int)this.myFWL1.getLength(), this.Direction);
		matrix2=zpTemp.Shifting(matrix2, 20, 20, (int)this.myFWL1.getWidth(), (int)this.myFWL1.getLength(), this.Direction);
		/*for(int i=0;i<(int)this.myFWL1.getWidth();i++){
			for(int j=0;j<(int)this.myFWL1.getLength();j++){
				System.out.print(matrix2[i][j]+"");
			}
			System.out.print("\n");
		}*/
		
		ArrayList <Point> FWL1Points=new ArrayList <Point>();
		ArrayList <Point> FWL2Points=new ArrayList <Point>();
		for(int i=0;i<(int) this.myFWL1.getWidth();i++)
			for(int j=0;j<(int) this.myFWL1.getLength();j++){
				Point Ptemp1=new Point();
				Point Ptemp2=new Point();
				if(matrix1[i][j]==1){
					Ptemp1.x=i+OriginX;
					Ptemp1.y=j+OriginY;
					FWL1Points.add(Ptemp1);
				}
				if(matrix2[i][j]==1){
					Ptemp2.x=i+OriginX;
					Ptemp2.y=j+OriginY;
					FWL2Points.add(Ptemp2);
				}
			
			}//drawing all the patterns on both lanes
		
		Graphics2D g2=(Graphics2D) g;
		g2.setColor(Color.BLACK);

        g2.drawString("Direction: " + this.Direction , stringoffset, stringoffset);
        switch (this.Direction){
		case 1:
			g2.drawString("Shift in X direction is: + "+ this.ShiftX+"mm", stringoffset, 20+stringoffset);
	        g2.drawString("Shift in Y direction is: + "+ this.ShiftY+"mm", stringoffset, 40+stringoffset);
			break;
		case 2:
			g2.drawString("Shift in X direction is: + "+ this.ShiftX+"mm", stringoffset, 20+stringoffset);
	        g2.drawString("Shift in Y direction is: 0 mm", stringoffset, 40+stringoffset);
			break;
		case 3:
			g2.drawString("Shift in X direction is: + "+ this.ShiftX+"mm", stringoffset, 20+stringoffset);
	        g2.drawString("Shift in Y direction is: - "+ this.ShiftY+"mm", stringoffset, 40+stringoffset);
			break;
		case 4:
			g2.drawString("Shift in X direction is: 0 mm", stringoffset, 20+stringoffset);
	        g2.drawString("Shift in Y direction is: - "+ this.ShiftY+"mm", stringoffset, 40+stringoffset);
			break;
		case 5:
			g2.drawString("Shift in X direction is: - "+ this.ShiftX+"mm", stringoffset, 20+stringoffset);
	        g2.drawString("Shift in Y direction is: - "+ this.ShiftY+"mm", stringoffset, 40+stringoffset);
			break;
		case 6:
			g2.drawString("Shift in X direction is: - "+ this.ShiftX+"mm", stringoffset, 20+stringoffset);
	        g2.drawString("Shift in Y direction is: 0 mm", stringoffset, 40+stringoffset);
			break;
		case 7:
			g2.drawString("Shift in X direction is: - "+ this.ShiftX+"mm", stringoffset, 20+stringoffset);
	        g2.drawString("Shift in Y direction is: + "+ this.ShiftY+"mm", stringoffset, 40+stringoffset);
			break;
		case 8:
			g2.drawString("Shift in X direction is: 0 mm", stringoffset, 20+stringoffset);
	        g2.drawString("Shift in Y direction is: + "+ this.ShiftY+"mm", stringoffset, 40+stringoffset);
			break;
        }
        
        
		g2.setColor(Color.BLACK);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		g2.drawRect(theFWL1.x, theFWL1.y, theFWL1.width, theFWL1.height);
		g2.fillRect(theFWL1.x, theFWL1.y, theFWL1.width, theFWL1.height);
		g2.setColor(Color.LIGHT_GRAY);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		g2.drawRect(theFWL2.x,theFWL2.y,theFWL2.width, theFWL2.height);
		g2.fillRect(theFWL2.x,theFWL2.y,theFWL2.width, theFWL2.height);
		
		g2.setColor(Color.BLUE);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
		for(int i=0;i<FWL1Points.size();i++){
			g2.drawRect(FWL1Points.get(i).x, FWL1Points.get(i).y, 1, 1);
			g2.fillRect(FWL1Points.get(i).x, FWL1Points.get(i).y, 1, 1);
		}
		g2.setColor(Color.GREEN);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
		for(int i=0;i<FWL2Points.size();i++){
			g2.drawRect(FWL2Points.get(i).x, FWL2Points.get(i).y, 1, 1);
			g2.fillRect(FWL2Points.get(i).x, FWL2Points.get(i).y, 1, 1);
		}
		 
		
		
	}

}
