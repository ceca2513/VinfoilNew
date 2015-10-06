package org.drawing;
import org.vinfoil.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Graphics2D;


import javax.swing.*;

import org.vinfoil.FWL;
import org.vinfoil.Patterns;
import org.vinfoil.RepassingResultsOneLaneOnly;

public class DrawingOneOneOneOnly extends JPanel{
	
	protected Patterns allPatterns;;
	protected RepassingResultsOneLaneOnly ResultList;

	
	public DrawingOneOneOneOnly(RepassingResultsOneLaneOnly result){
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
		
		Rectangle theFWL=new Rectangle();
		Rectangle theFWL1=new Rectangle();
		Rectangle theFWL2=new Rectangle();
		Polygon theTriangel=new Polygon();
		Polygon theParallelogram=new Polygon();
		FWL lane=new FWL();
		
		SetOfPatternsForALane set=new SetOfPatternsForALane();
		Patterns patterns=set.PatternsForOneLane(this.allPatterns, this.ResultList.getLane1());
		
		Graphics2D g2=(Graphics2D) g;
		g2.setColor(Color.BLACK);
		
		g2.setColor(Color.BLACK);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		for(int i=0;i<this.ResultList.getAllLanes().size();i++){
			lane=this.ResultList.getAllLanes().get(i);
			theFWL.x=(int) (lane.getStart()+OriginX);
			theFWL.y=0+OriginY;
			theFWL.width=(int) lane.getWidth();
			theFWL.height=(int) lane.getLength();
			
			
			g2.drawRect(theFWL.x, theFWL.y, theFWL.width, theFWL.height);
			g2.fillRect(theFWL.x, theFWL.y, theFWL.width, theFWL.height);
			
		}
		g2.setColor(Color.GREEN);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		 for(int i=0;i<allPatterns.getRectangels().size();i++){
			 Rectangel r=new Rectangel();
			 r=allPatterns.getRectangels().get(i);
			 theFWL1.x=(int) (r.getX11()+OriginX);
			 theFWL1.y=(int)(r.getY12()+OriginY);
			 theFWL1.width=(int)(r.getX12()-r.getX11());
			 theFWL1.height=(int) (r.getY11()-r.getY12());
			 
			 g2.drawRect(theFWL.x, theFWL.y, theFWL.width, theFWL.height);
				g2.fillRect(theFWL.x, theFWL.y, theFWL.width, theFWL.height);
		 }
		 g2.setColor(Color.GREEN);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		 for(int i=0;i<allPatterns.getParallelograms().size();i++){
			 Parallelogram p=new Parallelogram ();
			 p=allPatterns.getParallelograms().get(i);
			 theParallelogram.addPoint((int)p.getX11(), (int)p.getY11());
			 theParallelogram.addPoint((int)p.getX12(), (int)p.getY12());
			 theParallelogram.addPoint((int)p.getX13(), (int)p.getY13());
			 theParallelogram.addPoint((int)p.getX14(), (int)p.getY14());
			 
			 g2.drawPolygon(theParallelogram);
			 g2.fillPolygon(theParallelogram);
		 }
		 g2.setColor(Color.GREEN);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		 for(int i=0;i<allPatterns.getTriangels().size();i++){
			 Triangel t=new Triangel();
			 t=allPatterns.getTriangels().get(i);
			 theTriangel.addPoint((int)t.getX11(), (int)t.getY11());
			 theTriangel.addPoint((int)t.getX12(), (int)t.getY12());
			 theTriangel.addPoint((int)t.getX13(), (int)t.getY13());
			 
			 g2.drawPolygon(theTriangel);
			 g2.fillPolygon(theTriangel);
		 }
		 
		 //sada ispuni repassovanu lane
		 
		 g2.setColor(Color.LIGHT_GRAY);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		 theFWL2.x=(int) (ResultList.getLane2().getStart()+ResultList.getHS().getShiftX()+OriginX);
		 theFWL2.y=ResultList.getHS().getShiftY()+OriginY;
		 theFWL2.width=(int) ResultList.getLane1().getWidth();
		 theFWL2.width=(int) ResultList.getLane1().getLength();
		 
		 g2.setColor(Color.GREEN);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		 for(int i=0;i<patterns.getRectangels().size();i++){
			 Rectangel r=new Rectangel();
			 r=patterns.getRectangels().get(i);
			 theFWL1.x=(int) (r.getX11()+OriginX+ResultList.getHS().getShiftX());
			 theFWL1.y=(int)(r.getY12()+OriginY+ResultList.getHS().getShiftY());
			 theFWL1.width=(int)(r.getX12()-r.getX11());
			 theFWL1.height=(int) (r.getY11()-r.getY12());
			 
			 g2.drawRect(theFWL.x, theFWL.y, theFWL.width, theFWL.height);
				g2.fillRect(theFWL.x, theFWL.y, theFWL.width, theFWL.height);
		 }
		 
		 g2.setColor(Color.GREEN);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		 for(int i=0;i<patterns.getParallelograms().size();i++){
			 Parallelogram p=new Parallelogram ();
			 p=patterns.getParallelograms().get(i);
			 theParallelogram.addPoint((int)p.getX11()+ResultList.getHS().getShiftX(), (int)p.getY11()+ResultList.getHS().getShiftY());
			 theParallelogram.addPoint((int)p.getX12()+ResultList.getHS().getShiftX(), (int)p.getY12()+ResultList.getHS().getShiftY());
			 theParallelogram.addPoint((int)p.getX13()+ResultList.getHS().getShiftX(), (int)p.getY13()+ResultList.getHS().getShiftY());
			 theParallelogram.addPoint((int)p.getX14()+ResultList.getHS().getShiftX(), (int)p.getY14()+ResultList.getHS().getShiftY());
			 
			 g2.drawPolygon(theParallelogram);
			 g2.fillPolygon(theParallelogram);
		 }
		 g2.setColor(Color.GREEN);
		 ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		 for(int i=0;i<patterns.getTriangels().size();i++){
			 Triangel t=new Triangel();
			 t=patterns.getTriangels().get(i);
			 theTriangel.addPoint((int)t.getX11()+ResultList.getHS().getShiftX(), (int)t.getY11()+ResultList.getHS().getShiftY());
			 theTriangel.addPoint((int)t.getX12()+ResultList.getHS().getShiftX(), (int)t.getY12()+ResultList.getHS().getShiftY());
			 theTriangel.addPoint((int)t.getX13()+ResultList.getHS().getShiftX(), (int)t.getY13()+ResultList.getHS().getShiftY());
			 
			 g2.drawPolygon(theTriangel);
			 g2.fillPolygon(theTriangel);
		 }

	}

}
