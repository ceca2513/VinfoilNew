package org.vinfoil;

public class Paper {

	private float PaperWidth;
	private float PaperLength;
	private float Offsetx1;
	private float Offsetx2;
	
	public Paper(){
		
	}
	public float getPaperWidth(){
		return PaperWidth;
	}
	public void setPaperWidth(float width){
		PaperWidth=width;
	}
	public float getPaperLength(){
		return PaperLength;
	}
	public void setPaperLength(float length){
		PaperLength=length;
	}
	public float getOffsetx1(){
		return Offsetx1;
	}
	public void setOffsetx1(float off1){
		Offsetx1=off1;
	}
	public float getOffsetx2(){
		return Offsetx2;
	}
	public void setOffsetx2(float off2){
		Offsetx2=off2;
	}
	
}
