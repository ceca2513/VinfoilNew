package org.vinfoil;

import java.util.*;


public class Line {
	
	private float X;
	private float Y;
	
	
	
	public Line(){
		
	}
	public float getX(){
    	return X;
    }
    public void setX(float Top){
    	X=Top;
    }
    public float getY(){
    	return Y;
    }
    public void setY(float Top){
    	Y=Top;
    }
	
	public Line CreteLineThroughTwoPoints(Point A, Point B){
		
		Line p=new Line();
		float a=0;
		float b=0;
		float c=0;
		
		a=B.getX()-A.getX();
		b=B.getY()-A.getY();
		c=p.getX()*b/a-A.getX()*b/a-A.getY();
		p.setY(c);
		return p;
		
	}
	//need to see what happends if they have one coordinate same
	
	public ArrayList <IntegerPoint> SegmentDiscretization(IntegerPoint A, IntegerPoint B){
		
		ArrayList dis=new ArrayList <IntegerPoint>();
		
		Line q=new Line();
		int a=0;
		int b=0;
		int c=0;
		int X1;
		
		a=B.getX()-A.getX();
		b=B.getY()-A.getY();
		//System.out.println(a);
		//System.out.println(b);
		if(a==0){//x=x1=x2
			int f=Math.min(A.getY(), B.getY());
			if(f>0)
				f=f-1;
			for(int i=f;i<=Math.max(A.getY(), B.getY());i++){
				IntegerPoint C=new IntegerPoint ();
				C.setX(A.getX());
				C.setY(i);
				dis.add(C);
			}
		}else if(b==0){//y=y1=y2
			int f=Math.min(A.getX(), B.getX());
			if(f>0)
				f=f-1;
			for(int i=f;i<=Math.max(A.getX(), B.getX());i++){//ako je bilo koje od ova dva nula onda pocinjec od minus jedan
				IntegerPoint C=new IntegerPoint ();
				C.setY(A.getY());
				C.setX(i);
				dis.add(C);
			}
		}else{
		
		int Xmin=Math.min(A.getX(), B.getX());
		int Xmax=Math.max(A.getX(), B.getX());
		for(int i=0;i<=Math.abs(B.getX()-A.getX());i++){
			IntegerPoint C=new IntegerPoint ();
			C.setX(Xmin+i);
			c=(int) (C.getX()*b/a-A.getX()*b/a+A.getY());
			C.setY((int) Math.ceil(c));
			dis.add(C);
		}
	}
		
		return dis;
	}
	
	//true if point is inside the rectangle 
	//based on barycentric coordinates
	public boolean PointInsideTriangel(Triangel T,IntegerPoint D){
		
		boolean t=false;
		float lambda1=0;
		float lambda2=0;
		float lambda3=0;
		IntegerPoint A=new IntegerPoint();
		IntegerPoint B=new IntegerPoint();
		IntegerPoint C=new IntegerPoint();
	
		
		/*A.setX((int) T.getX11());
		A.setY((int) T.getY11());
		B.setX((int) T.getX12());
		B.setY((int) T.getY12());
		C.setX((int) T.getX13());
		C.setY((int) T.getY13());*/
		float X1=T.getX11();float Y1=T.getY11();
		float X2=T.getX12(); float Y2=T.getY12();
		float X3=T.getX13(); float Y3=T.getY13();
		float X=D.getX(); float Y=D.getY();
		//lambda1=((B.getY()-C.getY())*(D.getX()-C.getX())+(C.getX()-B.getX())*(D.getY()-C.getY()))/((B.getY()-C.getY())*(A.getX()-C.getX())+(C.getX()-B.getX())*(A.getY()-C.getY()));
		//lambda2=((C.getY()-A.getY())*(D.getX()-C.getX())+(A.getX()-C.getX())*(D.getY()-C.getY()))/((B.getY()-C.getY())*(A.getX()-C.getX())+(C.getX()-B.getX())*(A.getY()-C.getY()));
		lambda1=(((Y2-Y3)*(X-X3)+(X3-X2)*(Y-Y3))/((Y2-Y3)*(X1-X3)+(X3-X2)*(Y1-Y3)));
		lambda2=(((Y3-Y1)*(X-X3)+(X1-X3)*(Y-Y3))/((Y2-Y3)*(X1-X3)+(X3-X2)*(Y1-Y3)));
		lambda3=1-lambda1-lambda2;
		//System.out.println(lambda1);
		//System.out.println(lambda2);
		//System.out.println(lambda3);
		if((lambda1>=0)&&(lambda2>=0)&&(lambda3>=0)&&(lambda1<=1)&&(lambda2<=1)&&(lambda3<=1))
		//if((lambda1>0)&&(lambda2>0)&&(lambda3>0))
			t=true;
		
		return t;
	}
	
	//true if point inside rectangle
	//straight forward aproach but it doesnt work when rectangle is rotated 
	public boolean PointInsideRectangle (Rectangel R, IntegerPoint E){
		
		boolean t=false;
		IntegerPoint A=new IntegerPoint ();
		IntegerPoint B=new IntegerPoint ();
		
		float Xmin=0;
		float Xmax=0;
		float Ymin=0;
		float Ymax=0;
		
		A.setX((int) R.getX11());
		A.setY((int) R.getY11());
		B.setX((int) R.getX12());
		B.setY((int) R.getY12());
		if(A.getX()>B.getX()){
			Xmin=B.getX();
			Xmax=A.getX();
		}	
		else{
			Xmin=A.getX();
			Xmax=B.getX();
		}			
		if(A.getY()>B.getY()){
			Ymax=A.getY();
			Ymin=B.getY();
		}
		else{
			Ymax=B.getY();
			Ymin=A.getY();
		}
		if((E.getX()>Xmin)&&(E.getX()<Xmax)&&(E.getY()<Ymax)&&(E.getY()>Ymin))
			t=true;

		return t;
	}
	//true if point is inside parallelogram
	//if the light ray enters through point that is inside the object the light ray should intersect 
	//with odd number of edges otherwise this point is outside the polygon
	public boolean PointInsideParalleogram(Parallelogram P, IntegerPoint E){
		boolean t=false;
		int counter=0;
		float Xintersection;
		IntegerPoint A=new IntegerPoint();
		IntegerPoint B=new IntegerPoint();
		IntegerPoint C=new IntegerPoint();
		IntegerPoint D=new IntegerPoint();
		
		A.setX((int) P.getX11());
		A.setY((int) P.getY11());
		B.setX((int) P.getX12());
		B.setY((int) P.getY12());
		C.setX((int) P.getX13());
		C.setY((int) P.getY13());
		D.setX((int) P.getX14());
		D.setY((int) P.getY14());
		if(E.getY()>Math.min(A.getY(), B.getY())){
			if(E.getY()<=Math.max(A.getY(), B.getY())){
				if(E.getX()<=Math.max(A.getX(), B.getX())){
					if(A.getY()!=B.getY()){
						Xintersection=(E.getY()-A.getY())*(B.getX()-A.getX())/(B.getY()-A.getY())+A.getX();
						if(A.getX()==B.getX()||E.getX()<=Xintersection)
							counter++;
					}
				}
			}
		}
		
		if(E.getY()>Math.min(B.getY(), C.getY())){
			if(E.getY()<=Math.max(B.getY(), C.getY())){
				if(E.getX()<=Math.max(B.getX(), C.getX())){
					if(A.getY()!=C.getY()){
						Xintersection=(E.getY()-B.getY())*(C.getX()-B.getX())/(C.getY()-B.getY())+B.getX();
						if(B.getX()==C.getX()||E.getX()<=Xintersection)
							counter++;
					}
				}
			}
		}
		
		if(E.getY()>Math.min(C.getY(), D.getY())){
			if(E.getY()<=Math.max(C.getY(), D.getY())){
				if(E.getX()<=Math.max(C.getX(), D.getX())){
					if(C.getY()!=D.getY()){
						Xintersection=(E.getY()-C.getY())*(D.getX()-C.getX())/(D.getY()-C.getY())+C.getX();
						if(C.getX()==D.getX()||E.getX()<=Xintersection)
							counter++;
					}
				}
			}
		}
		
		if(E.getY()>Math.min(D.getY(), A.getY())){
			if(E.getY()<=Math.max(D.getY(), A.getY())){
				if(E.getX()<=Math.max(D.getX(), A.getX())){
					if(D.getY()!=A.getY()){
						Xintersection=(E.getY()-D.getY())*(A.getX()-D.getX())/(A.getY()-D.getY())+D.getX();
						if(D.getX()==A.getX()||E.getX()<=Xintersection)
							counter++;
					}
				}
			}
		}
		if(counter%2==0)
			t=false;
		else
			t=true;
		
		
		return t;
	}
	//true if point inside rectangle
	//it works for the rectangle which edges are not perpendicular to coordinate axis
	public boolean PointInsidePSquedRectangle(Rectangel R, IntegerPoint F){
		boolean t=false;
		int counter=0;
		float Xintersection;
		IntegerPoint A=new IntegerPoint();
		IntegerPoint B=new IntegerPoint();
		IntegerPoint C=new IntegerPoint();
		IntegerPoint D=new IntegerPoint();
		
		A.setX((int) R.getX11());
		A.setY((int) R.getY11());
		B.setX((int) R.getX12());
		B.setY((int) R.getY12());
		C.setX((int) R.getX11());
		C.setY((int) R.getY12());
		D.setX((int) R.getX12());
		D.setY((int) R.getY11());
		if(F.getY()>Math.min(A.getY(), B.getY())){
			if(F.getY()<=Math.max(A.getY(), B.getY())){
				if(F.getX()<=Math.max(A.getX(), B.getX())){
					if(A.getY()!=B.getY()){
						Xintersection=(F.getY()-A.getY())*(B.getX()-A.getX())/(B.getY()-A.getY())+A.getX();
						if(A.getX()==B.getX()||F.getX()<=Xintersection)
							counter++;
					}
				}
			}
		}
		
		if(F.getY()>Math.min(B.getY(), C.getY())){
			if(F.getY()<=Math.max(B.getY(), C.getY())){
				if(F.getX()<=Math.max(B.getX(), C.getX())){
					if(A.getY()!=C.getY()){
						Xintersection=(F.getY()-B.getY())*(C.getX()-B.getX())/(C.getY()-B.getY())+B.getX();
						if(B.getX()==C.getX()||F.getX()<=Xintersection)
							counter++;
					}
				}
			}
		}
		
		if(F.getY()>Math.min(C.getY(), D.getY())){
			if(F.getY()<=Math.max(C.getY(), D.getY())){
				if(F.getX()<=Math.max(C.getX(), D.getX())){
					if(C.getY()!=D.getY()){
						Xintersection=(F.getY()-C.getY())*(D.getX()-C.getX())/(D.getY()-C.getY())+C.getX();
						if(C.getX()==D.getX()||F.getX()<=Xintersection)
							counter++;
					}
				}
			}
		}
		
		if(F.getY()>Math.min(D.getY(), A.getY())){
			if(F.getY()<=Math.max(D.getY(), A.getY())){
				if(F.getX()<=Math.max(D.getX(), A.getX())){
					if(D.getY()!=A.getY()){
						Xintersection=(F.getY()-D.getY())*(A.getX()-D.getX())/(A.getY()-D.getY())+D.getX();
						if(D.getX()==A.getX()||F.getX()<=Xintersection)
							counter++;
					}
				}
			}
		}
		if(counter%2==0)
			t=false;
		else
			t=true;
		
		
		return t;
	}
	//true if the point is inside the parallelogram
	//the algorithm is based on mine implementation of ray entering the object!!!!
	public boolean PointInsideRectangel2(Rectangel R, IntegerPoint P ){
		
		boolean t=false;
		int counter=0;
		IntegerPoint A=new IntegerPoint();
		IntegerPoint B=new IntegerPoint();
		IntegerPoint C=new IntegerPoint();
		IntegerPoint D=new IntegerPoint();
		
		A.setX((int) R.getX11());
		A.setY((int) R.getY11());
		B.setX((int) R.getX12());
		B.setY((int) R.getY11());
		C.setX((int) R.getX12());
		C.setY((int) R.getY12());
		D.setX((int) R.getX11());
		D.setY((int) R.getY12());
		int Xintersect1=0;
		int Xintersect2=0;
		int Xintersect3=0;
		int Xintersect4=0;
		

		
		
		int XminAB=Math.min(A.getX(), B.getX());
		int XmaxAB=Math.max(A.getX(), B.getX());
		int YminAB=Math.min(A.getY(), B.getY());
		int YmaxAB=Math.max(A.getY(), B.getY());
		
		int XminBC=Math.min(C.getX(), B.getX());
		int XmaxBC=Math.max(C.getX(), B.getX());
		int YminBC=Math.min(C.getY(), B.getY());
		int YmaxBC=Math.max(C.getY(), B.getY());
		
		int XminCD=Math.min(C.getX(), D.getX());
		int XmaxCD=Math.max(C.getX(), D.getX());
		int YminCD=Math.min(C.getY(), D.getY());
		int YmaxCD=Math.max(C.getY(), D.getY());
		
		int XminDA=Math.min(A.getX(), D.getX());
		int XmaxDA=Math.max(A.getX(), D.getX());
		int YminDA=Math.min(A.getY(), D.getY());
		int YmaxDA=Math.max(A.getY(), D.getY());
		
		if(A.getY()==B.getY()){
			if((P.getY()==A.getY()) &&(P.getX()>=XminAB)&&(P.getX()<=XmaxAB))
				counter++;	
		}else{
			Xintersect1=((P.getY()-A.getY())/(B.getY()-A.getY()))*(B.getX()-A.getX())+A.getX();
		if((P.getY()>=YminAB)&&(P.getY()<=YmaxAB)&&(Xintersect1>=XminAB)&&(Xintersect1<=XmaxAB)&&(P.getX()<=Xintersect1))
			counter++;
		}
		
		if(B.getY()==C.getY()){
			if((P.getY()==B.getY()) &&(P.getX()>=XminBC)&&(P.getX()<=XmaxBC))
				counter++;
		}else{
			
			Xintersect2=((P.getY()-B.getY())/(C.getY()-B.getY())*(C.getX()-B.getX()))+B.getX();
		if((P.getY()>=YminBC)&&(P.getY()<=YmaxBC)&&(Xintersect2>=XminBC)&&(Xintersect2<=XmaxBC)&&(P.getX()<=Xintersect2))
			counter++;
		}
		if(C.getY()==D.getY()){
			if((P.getY()==C.getY()) &&(P.getX()>=XminCD)&&(P.getX()<=XmaxCD))
				counter++;
		}else{
			Xintersect3=((P.getY()-C.getY())/(D.getY()-C.getY()))*(D.getX()-C.getX())+C.getX();
		if((P.getY()>=YminDA)&&(P.getY()<=YmaxCD)&&(Xintersect3>=XminCD)&&(Xintersect3<=XmaxCD)&&(P.getX()<=Xintersect3))
			counter++;
		}
		if(D.getY()==A.getY()){
			if((P.getY()==A.getY()) &&(P.getX()>=XminDA)&&(P.getX()<=XmaxDA))
				counter++;	
		}else{
			Xintersect4=((P.getY()-D.getY())/(A.getY()-D.getY()))*(A.getX()-D.getX())+D.getX();	
		if((P.getY()>=YminDA)&&(P.getY()<=YmaxDA)&&(Xintersect4>=XminDA)&&(Xintersect4<=XmaxDA)&&(P.getX()<=Xintersect4))
			counter++;
		}
	
		
		if(counter%2==0)
			t=false;
		else 
			t=true;
		
		return t;
	}
	
	public boolean PointInsideParallelogram2(Parallelogram PAR, IntegerPoint P ){
		
		boolean t=false;
		int counter=0;
		IntegerPoint A=new IntegerPoint();
		IntegerPoint B=new IntegerPoint();
		IntegerPoint C=new IntegerPoint();
		IntegerPoint D=new IntegerPoint();
		
		A.setX((int) PAR.getX11());
		A.setY((int) PAR.getY11());
		B.setX((int) PAR.getX12());
		B.setY((int) PAR.getY12());
		C.setX((int) PAR.getX13());
		C.setY((int) PAR.getY13());
		D.setX((int) PAR.getX14());
		D.setY((int) PAR.getY14());
		float Xintersect1=0;
		float Xintersect2=0;
		float Xintersect3=0;
		float Xintersect4=0;
		

		
		
		int XminAB=Math.min(A.getX(), B.getX());
		int XmaxAB=Math.max(A.getX(), B.getX());
		int YminAB=Math.min(A.getY(), B.getY());
		int YmaxAB=Math.max(A.getY(), B.getY());
		
		int XminBC=Math.min(C.getX(), B.getX());
		int XmaxBC=Math.max(C.getX(), B.getX());
		int YminBC=Math.min(C.getY(), B.getY());
		int YmaxBC=Math.max(C.getY(), B.getY());
		
		int XminCD=Math.min(C.getX(), D.getX());
		int XmaxCD=Math.max(C.getX(), D.getX());
		int YminCD=Math.min(C.getY(), D.getY());
		int YmaxCD=Math.max(C.getY(), D.getY());
		
		int XminDA=Math.min(A.getX(), D.getX());
		int XmaxDA=Math.max(A.getX(), D.getX());
		int YminDA=Math.min(A.getY(), D.getY());
		int YmaxDA=Math.max(A.getY(), D.getY());
		
		if(A.getY()==B.getY()){
			if((P.getY()==A.getY()) &&(P.getX()>=XminAB)&&(P.getX()<=XmaxAB))
				counter++;	
		}else{
			Xintersect1=(((float)(P.getY()-A.getY())/(float)(B.getY()-A.getY()))*(B.getX()-A.getX()))+A.getX();
		if((P.getY()>=YminAB)&&(P.getY()<=YmaxAB)&&(Xintersect1>=XminAB)&&(Xintersect1<=XmaxAB)&&(P.getX()<=Xintersect1))
			counter++;
		}
		
		if(B.getY()==C.getY()){
			if((P.getY()==B.getY()) &&(P.getX()>=XminBC)&&(P.getX()<=XmaxBC))
				counter++;
		}else{
			
			Xintersect2=((float)(P.getY()-B.getY())/(float)(C.getY()-B.getY())*(C.getX()-B.getX()))+B.getX();
		if((P.getY()>=YminBC)&&(P.getY()<=YmaxBC)&&(Xintersect2>=XminBC)&&(Xintersect2<=XmaxBC)&&(P.getX()<=Xintersect2))
			counter++;
		}
		if(C.getY()==D.getY()){
			if((P.getY()==C.getY()) &&(P.getX()>=XminCD)&&(P.getX()<=XmaxCD))
				counter++;
		}else{
			Xintersect3=(((float)(P.getY()-C.getY())/(float)(D.getY()-C.getY()))*(D.getX()-C.getX()))+C.getX();
		if((P.getY()>=YminDA)&&(P.getY()<=YmaxCD)&&(Xintersect3>=XminCD)&&(Xintersect3<=XmaxCD)&&(P.getX()<=Xintersect3))
			counter++;
		}
		if(D.getY()==A.getY()){
			if((P.getY()==A.getY()) &&(P.getX()>=XminDA)&&(P.getX()<=XmaxDA))
				counter++;	
		}else{
			Xintersect4=(((float)(P.getY()-D.getY())/(float)(A.getY()-D.getY()))*(A.getX()-D.getX()))+D.getX();	
		if((P.getY()>=YminDA)&&(P.getY()<=YmaxDA)&&(Xintersect4>=XminDA)&&(Xintersect4<=XmaxDA)&&(P.getX()<=Xintersect4))
			counter++;
		}
	
		
		if(counter%2==0)
			t=false;
		else 
			t=true;
		
		return t;
	}


}
