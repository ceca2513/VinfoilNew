package org.vinfoil;

import java.util.*;
import java.math.*;
public class ZeroPadding {
	
	public ZeroPadding(){
		
	}
	public int [][] Shifting(int [][] matrix, int ShiftX, int ShiftY, int n, int m, int direction){
		
		int [][] matrix1=new int [n][m];
		int Shiftx=0; int Shifty=0;
		if(ShiftX<0)
			Shiftx=-ShiftX;
		else Shiftx=ShiftX;
		if(ShiftY<0)
			Shifty=-ShiftY;
		else Shifty=ShiftY;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				matrix1[i][j]=0;
		switch (direction){
		case 1:
			for(int i=0;i<n-Shiftx;i++)
				for(int j=0;j<m-Shifty;j++){
					matrix1[i][j]=matrix[i+Shiftx][j+Shifty];
				}
			
			break;
		case 2:
			for(int j=0;j<m;j++){
				for(int i=0; i<n-ShiftX;i++){
					matrix1[i][j]=matrix[i+Shiftx][j];
				}
					
			}
			break;
		case 3:
		
				for(int i=0;i<n-Shiftx;i++){
					for(int j=Shifty;j<m;j++){
						matrix1[i][j]=matrix[i+Shiftx][j-Shifty];
					}
				}
				
					
			break;
		case 4:
			for(int i=0;i<n;i++){
				for(int j=Shifty;j<m;j++ ){
					matrix1[i][j]=matrix[i][j-Shifty];
				}
				
			}
			break;
		case 5:
			
			for(int i=Shiftx;i<n;i++)
				for(int j=Shifty;j<m;j++)
					matrix1[i][j]=matrix[i-Shiftx][j-Shifty];
			break;
		case 6:
					for(int i=Shiftx;i<n;i++)
						for(int j=0;j<m;j++)
						matrix1[i][j]=matrix[i-Shiftx][j];

			break;
		case 7:
			
				for(int i=Shiftx;i<n;i++)
					for(int j=0;j<m-Shifty;j++)
						matrix1[i][j]=matrix[i-Shiftx][j+Shifty];
			break;
		case 8:
			for(int i=0;i<n;i++){
				
					for(int j=0;j<m-Shifty;j++)
						matrix1[i][j]=matrix[i][j+Shifty];	
			}
			break;
			
		}
		
		
		return matrix1;
	}

}
