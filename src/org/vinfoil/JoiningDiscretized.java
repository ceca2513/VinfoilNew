package org.vinfoil;

import java.util.*;
public class JoiningDiscretized {
	public JoiningDiscretized(){
		
	}
	public int [][] Joining(int [][] matrix1, int [][] matrix2, int n1, int n2, int m, int ScanStep){
		int n=n1+n2+ScanStep;
		int [][] matrix3=new int [n][m];
		for(int j=0;j<m;j++){
			for(int i=0;i<n1;i++){
				matrix3[i][j]=matrix1[i][j];
			}
			for(int i=n1;i<n1+ScanStep;i++){
				matrix3[i][j]=0;
			}
			int k=0;
			for(int i=n1+ScanStep;i<n;i++){
				matrix3[i][j]=matrix2[k][j];
				k++;
			}
		}
		
		return matrix3;
	}
	public int [][] Extend(int [][]matrix1, int n, int m, int extension){
		int [][] matrix2=new int [n+extension][m];
		for(int j=0;j<m;j++){
			for(int i=0;i<n;i++)
				matrix2[i][j]=matrix1[i][j];
			for(int i=n;i<n+extension;i++)
				matrix2[i][j]=0;
		}
		return matrix2;
	}
}
