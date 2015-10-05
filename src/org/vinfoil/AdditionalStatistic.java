package org.vinfoil;

import java.util.*;
import java.math.*;
public class AdditionalStatistic {
	
	public AdditionalStatistic(){
		
	}
	public static float Distance(float x1, float y1, float x2, float y2){
		float d=0;
		d=(float) Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
		return d;
	}
	public static float HeronsFormula(float a, float b, float c){
		float P=0;
		float s=0;
		s=(a+b+c)/2;
		P=(float) Math.sqrt(s*(s-a)*(s-b)*(s-c));
		return P;
	}
	public static float SurfaceAreaOfParalleogram(float a, float ha){
		float P=0;
		P=a*ha;
		return P;
	}
	public static float SurfaceAreaOfRectangel(float a, float b){
		float P=0;
		P=a*b;
		return P;
	}
}
