package org.vinfoil;

public class CenteringToPS {

	//public int PaperWidth;
	//public int PaperLength;
	private Paper P;
	
	public CenteringToPS(){
		
	}
	public float CenteringOfInitialRoll(int WidthOfPS, Paper P1){
		//Centering Initial foil roll according to the width of PS
		
		//org.vinfoil.InitialFoilRoll IFR=new org.vinfoil.InitialFoilRoll();
		float Offset;
		

		if(P1.getPaperWidth()<WidthOfPS){
			Offset=WidthOfPS/2-P1.getPaperWidth()/2;
		
		}
		else {
			Offset=0;
	
		}
		return Offset;	//Output is offsetx1
	}
	public Rectangel CenteringOfRectangels (float Offset, Rectangel R){
		//After we obtain this new offsetx1 we can center each rectangle based on this offset
		Rectangel R1=new Rectangel();
		R1.setX11(R.getX11()+Offset);
		R1.setX12(R.getX12()+Offset);
		return R1;
		
		
	}
	public Triangel CenteringOfTriangels (float Offset, Triangel T){
		//After we obtain this new offsetx1 we can center now each triangle based on this offset
		Triangel T1=new Triangel();
		T1.setX11(T.getX11()+Offset);
		T1.setX12(T.getX12()+Offset);
		T1.setX13(T.getX13()+Offset);
		
		return T1;
	}
	public Parallelogram CenteringOfParallelogram (float Offset, Parallelogram P){
		//After we obtain this new offsetx1 we can center now each parallelogram based on this offset
		Parallelogram P1=new Parallelogram();
		P1.setX11(P.getX11()+Offset);
		P1.setX12(P.getX12()+Offset);
		P1.setX13(P.getX13()+Offset);
		
		return P1;
	}
}
