package org.vinfoil;

import java.util.*;

public class HoldingConstraints {
	
	public HoldingConstraints(){
		
	}
	//lane1 must be wider then lane2, you need to perform this check
	//shift ti nije uracunat nigde
	public ArrayList <HoldingResult> Repassing(FWL lane1, FWL lane2, Patterns Shape, int ScaningStep){//lane1 is fixed, lane2 is the shifting one
		
		ArrayList <HoldingResult> templist=new ArrayList <HoldingResult> ();
		//default value for the outcome that says the shift is not possible
		HoldingResult temp=new HoldingResult();
		temp.setRepassing(false);
		temp.setDirection(0);
		temp.setShiftX(0);
		temp.setShiftY(0);
		
		SetOfPatternsForALane patternslane1=new SetOfPatternsForALane();
		SetOfPatternsForALane patternslane2=new SetOfPatternsForALane();
		MinAndMax templane1= new MinAndMax();
		MinAndMax templane2= new MinAndMax();
		
		Patterns nesto=patternslane2.PatternsForOneLane(Shape, lane2);
		//setting min and max values for the patterns of boath lanes
		int Xmin=(int) ( templane2.TheXminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2))-lane2.getStart());
		int Xmax=(int) ( templane2.TheXmaxOfAllPatterns(nesto)-lane2.getStart());
		int Ymin=(int) ( templane2.TheYminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)));
		int Ymax=(int) ( templane2.TheYmaxOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)));
		int X1min=(int) ( templane1.TheXminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1))-lane1.getStart());
		int X1max=(int) ( templane1.TheXmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1))-lane1.getStart());
		int Y1min=(int) ( templane1.TheYminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)));
		int Y1max=(int) ( templane1.TheYmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)));
		int CPC=(int) lane1.getLength();
		//setting shifts limits in all direstions
		int ShiftXdirection1=Xmin;
		int ShiftXdirection2=Xmin;
		int ShiftXdirection3=Xmin;
		int ShiftXdirection4=0;
		int ShiftXdirection5=(int) (lane1.getEnd()-lane1.getStart()-Xmax);
		int ShiftXdirection6=(int) (lane1.getEnd()-lane1.getStart()-Xmax);
		int ShiftXdirection7=(int) (lane1.getEnd()-lane1.getStart()-Xmax);
		int ShiftXdirection8=0;
		int ShiftYdirection1=Ymin;
		int ShiftYdirection2=0;
		int ShiftYdirection3=CPC-Ymax;
		int ShiftYdirection4=CPC-Ymax;
		int ShiftYdirection5=CPC-Ymax;
		int ShiftYdirection6=0;
		int ShiftYdirection7=Ymin;
		int ShiftYdirection8=Ymin;
		//two matrices for discretization
		Discretization disc=new Discretization ();	
		//seting dimensions for this matrices, remember the first matrix needs to be wider, set as a condition in following part of code
		int N1=(int) lane1.getWidth();//x coordinate
		int M1=(int) lane1.getLength();//y coordinate
		int N2=(int) lane2.getWidth();//x coordinate
		int M2=(int) lane2.getLength();//y coordinate
		
		int [][] matrix1=new int [N1][M1];
		int [][] matrix2=new int [N2][M2];
		int [][] matrix3=new int [N1][M2];
		matrix1=disc.DiscretizationOfLane(lane1, Shape);//discrete lane1
		matrix2=disc.DiscretizationOfLane(lane2, Shape);//discrete lane2, from now on we only work with discrete ones
		boolean t=false;
		//setting matrices to same dimension
		if((lane1.getWidth()>lane2.getWidth())&&(lane1.getLength()>lane2.getLength())){
			t=true;
			for(int i=0;i<N2;i++)
				for(int j=0;j<M2;j++){
					matrix3[i][j]=matrix2[i][j];
				}
			for(int i=N2;i<N1;i++)
				for(int j=M2;j<M1;j++){
					matrix3[i][j]=0;
				}
		}else if(lane1.getWidth()>lane2.getWidth()){
			t=true;
			for(int j=0;j<M1;j++){
				for(int i=0;i<N2;i++){
					matrix3[i][j]=matrix2[i][j];
				}
				for(int i=N2;i<N1;i++){
					matrix3[i][j]=0;
				}
			}
		}else if(lane1.getLength()>lane2.getLength()){
			t=true;
			for(int i=0;i<N1;i++){
				for(int j=0;j<M2;j++){
					matrix3[i][j]=matrix2[i][j];
				}
				for(int j=M2;j<M1;j++){
					matrix3[i][j]=0;
				}
			}
		}
	//dirlits contain results of every position of second lane in specific direction where patterns of 2 lanes don't overlap
		ArrayList <IntegerPoint> dir1list=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> dir2list=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> dir3list=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> dir4list=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> dir5list=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> dir6list=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> dir7list=new ArrayList <IntegerPoint>();
		ArrayList <IntegerPoint> dir8list=new ArrayList <IntegerPoint>();
		int [][] matrix4=new int [N1][M1];	
		int [][] matrix5=new int [N1][M1];
		//when matrix is moved in some direction the part that falls out is deleted the matrix is moved and the rest is zero padded
		ZeroPadding zp=new ZeroPadding();
		//every time we move matrix we zero pad it and the we check for overlapping 
		Overlaping over=new Overlaping ();
		boolean shift=false;
		boolean scan=true;
		//we move for one field of matrix in each direction
		if(t==true){//using matrix3
			//direction1
			for(int k=1;k<ShiftXdirection1;k++)
				for(int l=1;l<ShiftYdirection1;l++){
					//shift each row and column for k and l respectively
					matrix4=zp.Shifting(matrix3, k, l, N1, M1, 1);
					//after shifting of the second matrix we check for overlaping of the patterns
					//shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-k, Xmax-k, Y1min, Y1max, Ymin-l, Ymax-l);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-k, Xmax-k, Y1min, Y1max, Ymin-l, Ymax-l);
					if(shift==false){//if there is no overlap of the patterns of 2 lane we set it as a result for dirlist
						IntegerPoint intP=new IntegerPoint();
						intP.setX(k);
						intP.setY(l);
						dir1list.add(intP);
					}
				}
			//direction2
			
				for(int l=0;l<ShiftXdirection2;l++){
					matrix4=zp.Shifting(matrix3, l, 0, N1, M1, 2);
					//shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-0, Xmax-0, Y1min, Y1max, Ymin-l, Ymax-l);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-l, Xmax-l, Y1min, Y1max, Ymin-0, Ymax-0);

					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(l);
						intP.setY(0);
						dir2list.add(intP);
					}
				}
			//direction3
				for(int k=1;k<ShiftXdirection3;k++)
					for(int l=1;l<ShiftYdirection3;l++){
						matrix4=zp.Shifting(matrix3, k, l, N1, M1, 3);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-k, Xmax-k, Y1min, Y1max, Ymin+l, Ymax+l);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(k);
							intP.setY(l);
							dir3list.add(intP);
						}
					}
			//direction4
				for(int k=0;k<ShiftYdirection4;k++){
						matrix4=zp.Shifting(matrix3, 0, k, N1, M1, 4);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+0, Xmax+0, Y1min, Y1max, Ymin+k, Ymax+k);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(0);
							intP.setY(k);
							dir4list.add(intP);
						}
					}
			//direction5
				for(int k=1;k<ShiftXdirection5;k++)
					for(int l=1;l<ShiftYdirection5;l++){
						matrix4=zp.Shifting(matrix3, k, l, N1, M1, 5);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+k, Xmax+k, Y1min, Y1max, Ymin+l, Ymax+l);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(k);
							intP.setY(l);
							dir5list.add(intP);
						}
					}
			//direction6
			
					for(int l=0;l<ShiftXdirection6;l++){
						matrix4=zp.Shifting(matrix3, l, 0, N1, M1, 6);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+l, Xmax+l, Y1min, Y1max, Ymin+0, Ymax+0);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(l);
							intP.setY(0);
							dir6list.add(intP);
						}
					}
			//direction7
					for(int k=1;k<ShiftXdirection7;k++)
						for(int l=1;l<ShiftYdirection7;l++){
							matrix4=zp.Shifting(matrix3, k, l, N1, M1, 7);
							shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+k, Xmax+k, Y1min, Y1max, Ymin-l, Ymax-l);

							if(shift==false){
								IntegerPoint intP=new IntegerPoint();
								intP.setX(k);
								intP.setY(l);
								dir7list.add(intP);
							}
						}
			//direction8
					for(int k=0;k<ShiftYdirection8;k++){
							matrix4=zp.Shifting(matrix3, 0, k, N1, M1, 8);
							shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-0, Xmax-0, Y1min, Y1max, Ymin-k, Ymax-k);

							if(shift==false){
								IntegerPoint intP=new IntegerPoint();
								intP.setX(0);
								intP.setY(k);
								dir8list.add(intP);
							}
						}
					
					//we are now going to scan for 20mm embedding 
					if(dir1list.size()!=0){//if there is a position in this direction where patterns of 2 lanes do not overlap
						int s=0;
						int s1=0;
						while(s<dir1list.size()){//we are looking for all solution because we are going to call this procedure again for one-to-two lanes repassing
							matrix5=zp.Shifting(matrix3, dir1list.get(s).getX(), dir1list.get(s).getY(), N1, M1, 1);//we set second matrix on sugessted position
							//we search in every direction if it is possible to move for 20mm to embed the second lane
							//that is why we have a list of embedding
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir1list.get(s).getX(), Xmax-dir1list.get(s).getX(), Y1min, Y1max, Ymin-dir1list.get(s).getY(), Ymax-dir1list.get(s).getY());
							//if there is no solution from this direction we will not set anything
							if(listOfEmbedding.get(0).getX()==1){//jel mi zaista treba ovaj deo? isti je ko case 1
								temp.setRepassing(true);
								temp.setDirection(1);
								temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir1list.get(s).getY()+ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1://can this ever happen???????
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir1list.get(s).getY());
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX());	
										temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir1list.get(s).getY());
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX());	
										temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}else if(dir2list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir2list.size()){
							matrix5=zp.Shifting(matrix3, dir2list.get(s).getX(), dir2list.get(s).getY(), N1, M1, 2);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir2list.get(s).getX(), Xmax-dir2list.get(s).getX(), Y1min, Y1max, Ymin, Ymax);
							if(listOfEmbedding.get(1).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(2);
								temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir2list.get(s).getY());	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir2list.get(s).getY());
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX());	
										temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir2list.get(s).getY());
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX());	
										temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
						
					}else if(dir3list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir3list.size()){
							matrix5=zp.Shifting(matrix3, dir3list.get(s).getX(), dir3list.get(s).getY(), N1, M1, 3);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir3list.get(s).getX(), Xmax-dir3list.get(s).getX(), Y1min, Y1max, Ymin+dir3list.get(s).getY(), Ymax+dir3list.get(s).getY());
							if(listOfEmbedding.get(2).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(3);
								temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir3list.get(s).getY()-ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir3list.get(s).getY());
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX());	
										temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir3list.get(s).getY());
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX());	
										temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}else if(dir4list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir4list.size()){
							matrix5=zp.Shifting(matrix3, dir4list.get(s).getX(), dir4list.get(s).getY(), N1, M1, 4);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin, Xmax, Y1min, Y1max, Ymin+dir4list.get(s).getY(), Ymax+dir4list.get(s).getY());
							if(listOfEmbedding.get(3).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(4);
								temp.setShiftX(dir4list.get(s).getX());	
								temp.setShiftX(dir4list.get(s).getY()-ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir4list.get(s).getY());
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX());	
										temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir4list.get(s).getY());
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX());	
										temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}	
					}else if(dir5list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir5list.size()){
							matrix5=zp.Shifting(matrix3, dir5list.get(s).getX(), dir5list.get(s).getY(), N1, M1, 5);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir5list.get(s).getX(), Xmax+dir5list.get(s).getX(), Y1min, Y1max, Ymin+dir5list.get(s).getY(), Ymax+dir5list.get(s).getY());
							if(listOfEmbedding.get(4).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(5);
								temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
								temp.setShiftX(dir5list.get(s).getY()-ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir5list.get(s).getY());
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX());	
										temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir5list.get(s).getY());
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX());	
										temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}else if(dir6list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir6list.size()){
							matrix5=zp.Shifting(matrix3, dir6list.get(s).getX(), dir6list.get(s).getY(), N1, M1, 6);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir6list.get(s).getX(), Xmax+dir6list.get(s).getX(), Y1min, Y1max, Ymin, Ymax);
							if(listOfEmbedding.get(5).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(6);
								temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
								temp.setShiftX(dir6list.get(s).getY());	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir6list.get(s).getY());
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX());	
										temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir6list.get(s).getY());
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX());	
										temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}else if(dir7list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir7list.size()){
							matrix5=zp.Shifting(matrix3, dir7list.get(s).getX(), dir7list.get(s).getY(), N1, M1, 7);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir7list.get(s).getX(), Xmax+dir7list.get(s).getX(), Y1min, Y1max, Ymin-dir7list.get(s).getY(), Ymax-dir7list.get(s).getY());
							if(listOfEmbedding.get(6).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(7);
								temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
								temp.setShiftX(dir7list.get(s).getY()+ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir7list.get(s).getY());
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX());	
										temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir7list.get(s).getY());
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX());	
										temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}else if(dir8list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir8list.size()){
							matrix5=zp.Shifting(matrix3, dir8list.get(s).getX(), dir8list.get(s).getY(), N1, M1, 8);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin, Xmax, Y1min, Y1max, Ymin-dir8list.get(s).getY(), Ymax-dir8list.get(s).getY());
							if(listOfEmbedding.get(7).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(8);
								temp.setShiftX(dir8list.get(s).getX());	
								temp.setShiftX(dir8list.get(s).getY()+ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir8list.get(s).getY());
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX());	
										temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir8list.get(s).getY());
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX());	
										temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}
					
					/*if(dir1list.size()!=0){
						int s=0;
						while(scan==true&&s<dir1list.size()){
							matrix5=zp.Shifting(matrix3, dir1list.get(s).getX(), dir1list.get(s).getY(), N1, M1, 1);
							//moras da izvrsis proveru da li je odgovarjuce embeding moguce tjs da ali ako se shiftujes za 20
							//u bilo kom prvacu ti ne izlaze patterni sa lane, obavezno ovde jer samo ovde vidim shiftove!!!!
							scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
							if(scan==false){
								temp.setRepassing(true);
								temp.setDirection(1);
								if(dir1list.get(s).getX()==0)
								temp.setShiftX(dir1list.get(s).getX());
								else
									temp.setShiftX(dir1list.get(s).getX()+20);	
								if(dir1list.get(s).getY()==0)
								temp.setShiftY(dir1list.get(s).getY());
								else 
									temp.setShiftY(dir1list.get(s).getY()+20);
							}
						}
						
					}else
					if(dir2list.size()!=0){
						int s=0;
						while(scan==true&&s<dir2list.size()){
							matrix5=zp.Shifting(matrix3, dir2list.get(s).getX(), dir2list.get(s).getY(), N1, M1, 1);
							scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
							if(scan==false){
								temp.setRepassing(true);
								temp.setDirection(2);
								temp.setShiftX(dir2list.get(s).getX()+20);
								temp.setShiftY(dir2list.get(s).getY());
							}
						}
					}else
					if(dir3list.size()!=0){
						int s=0;	
						while(scan==true&&s<dir3list.size()){
							matrix5=zp.Shifting(matrix3, dir3list.get(s).getX(), dir3list.get(s).getY(), N1, M1, 1);
							scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
							if(scan==false){
								temp.setRepassing(true);
								temp.setDirection(3);
								if(dir3list.get(s).getX()==0)
									temp.setShiftX(dir3list.get(s).getX());
									else
										temp.setShiftX(dir3list.get(s).getX()+20);	
									if(dir3list.get(s).getY()==0)
									temp.setShiftY(dir3list.get(s).getY());
									else 
										temp.setShiftY(dir3list.get(s).getY()+20);
							}
						}
					}else
					if(dir4list.size()!=0){
						int s=0;
						while(scan==true&&s<dir4list.size()){
							matrix5=zp.Shifting(matrix3, dir4list.get(s).getX(), dir4list.get(s).getY(), N1, M1, 1);
							scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
							if(scan==false){
								temp.setRepassing(true);
								temp.setDirection(4);
								temp.setShiftX(dir4list.get(s).getX());
								temp.setShiftY(dir4list.get(s).getY()+20);
							}
						}
					}else
					if(dir5list.size()!=0){
						int s=0;
						while(scan==true&&s<dir5list.size()){
							matrix5=zp.Shifting(matrix3, dir5list.get(s).getX(), dir5list.get(s).getY(), N1, M1, 1);
							scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
							if(scan==false){
								temp.setRepassing(true);
								temp.setDirection(5);
								if(dir5list.get(s).getX()==0)
									temp.setShiftX(dir5list.get(s).getX());
									else
										temp.setShiftX(dir5list.get(s).getX()+20);	
									if(dir5list.get(s).getY()==0)
									temp.setShiftY(dir5list.get(s).getY());
									else 
										temp.setShiftY(dir5list.get(s).getY()+20);
							}
						}
					}else
					if(dir6list.size()!=0){
						int s=0;
						while(scan==true&&s<dir6list.size()){
							matrix5=zp.Shifting(matrix3, dir6list.get(s).getX(), dir6list.get(s).getY(), N1, M1, 1);
							scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
							if(scan==false){
								temp.setRepassing(true);
								temp.setDirection(6);
								temp.setShiftX(dir6list.get(s).getX());
								temp.setShiftY(dir6list.get(s).getY()+20);
							}
						}
					}else
					if(dir7list.size()!=0){
						int s=0;
						while(scan==true&&s<dir7list.size()){
							matrix5=zp.Shifting(matrix3, dir7list.get(s).getX(), dir7list.get(s).getY(), N1, M1, 1);
							scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
							if(scan==false){
								temp.setRepassing(true);
								temp.setDirection(7);
								if(dir7list.get(s).getX()==0)
									temp.setShiftX(dir7list.get(s).getX());
									else
										temp.setShiftX(dir7list.get(s).getX()+20);	
									if(dir7list.get(s).getY()==0)
									temp.setShiftY(dir7list.get(s).getY());
									else 
										temp.setShiftY(dir7list.get(s).getY()+20);
							}
						}
					}else
					if(dir8list.size()!=0){
						int s=0;
						while(scan==true&&s<dir8list.size()){
							matrix5=zp.Shifting(matrix3, dir8list.get(s).getX(), dir8list.get(s).getY(), N1, M1, 1);
							scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
							if(scan==false){
								temp.setRepassing(true);
								temp.setDirection(8);
								temp.setShiftX(dir8list.get(s).getX());
								temp.setShiftY(dir8list.get(s).getY()+20);
							}
						}
					}*/
					
		}else{//using matrix2
			//direction1
			//direction1
			for(int k=1;k<ShiftXdirection1;k++)
				for(int l=1;l<ShiftYdirection1;l++){
					matrix4=zp.Shifting(matrix2, k, l, N1, M1, 1);
					//shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-k, Xmax-k, Y1min, Y1max, Ymin-l, Ymax-l);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-k, Xmax-k, Y1min, Y1max, Ymin-l, Ymax-l);
					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(k);
						intP.setY(l);
						dir1list.add(intP);
					}
				}
			//direction2
			
				for(int l=0;l<ShiftXdirection2;l++){
					matrix4=zp.Shifting(matrix2, l, 0, N1, M1, 2);
					//shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-0, Xmax-0, Y1min, Y1max, Ymin-l, Ymax-l);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-l, Xmax-l, Y1min, Y1max, Ymin-0, Ymax-0);

					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(l);
						intP.setY(0);
						dir2list.add(intP);
					}
				}
			//direction3
				for(int k=1;k<ShiftXdirection3;k++)
					for(int l=1;l<ShiftYdirection3;l++){
						matrix4=zp.Shifting(matrix2, k, l, N1, M1, 3);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-k, Xmax-k, Y1min, Y1max, Ymin+l, Ymax+l);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(k);
							intP.setY(l);
							dir3list.add(intP);
						}
					}
			//direction4
				for(int k=0;k<ShiftYdirection4;k++){
						matrix4=zp.Shifting(matrix2, 0, k, N1, M1, 4);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+0, Xmax+0, Y1min, Y1max, Ymin+k, Ymax+k);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(0);
							intP.setY(k);
							dir4list.add(intP);
						}
					}
			//direction5
				for(int k=1;k<ShiftXdirection5;k++)
					for(int l=1;l<ShiftYdirection5;l++){
						matrix4=zp.Shifting(matrix2, k, l, N1, M1, 5);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+k, Xmax+k, Y1min, Y1max, Ymin+l, Ymax+l);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(k);
							intP.setY(l);
							dir5list.add(intP);
						}
					}
			//direction6
			
					for(int l=0;l<ShiftXdirection6;l++){
						matrix4=zp.Shifting(matrix2, l, 0, N1, M1, 6);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+l, Xmax+l, Y1min, Y1max, Ymin+0, Ymax+0);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(l);
							intP.setY(0);
							dir6list.add(intP);
						}
					}
			//direction7
					for(int k=1;k<ShiftXdirection7;k++)
						for(int l=1;l<ShiftYdirection7;l++){
							matrix4=zp.Shifting(matrix2, k, l, N1, M1, 7);
							shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+k, Xmax+k, Y1min, Y1max, Ymin-l, Ymax-l);

							if(shift==false){
								IntegerPoint intP=new IntegerPoint();
								intP.setX(k);
								intP.setY(l);
								dir7list.add(intP);
							}
						}
			//direction8
					for(int k=0;k<ShiftYdirection8;k++){
							matrix4=zp.Shifting(matrix2, 0, k, N1, M1, 8);
							shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-0, Xmax-0, Y1min, Y1max, Ymin-k, Ymax-k);

							if(shift==false){
								IntegerPoint intP=new IntegerPoint();
								intP.setX(0);
								intP.setY(k);
								dir8list.add(intP);
							}
						}
					//we are now going to scan for 20mm embeding 
					
					
					if(dir1list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir1list.size()){
							matrix5=zp.Shifting(matrix2, dir1list.get(s).getX(), dir1list.get(s).getY(), N1, M1, 1);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir1list.get(s).getX(), Xmax-dir1list.get(s).getX(), Y1min, Y1max, Ymin-dir1list.get(s).getY(), Ymax-dir1list.get(s).getY());
							if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(1);
								temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir1list.get(s).getY()+ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir1list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX());	
										temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir1list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(1);
										temp.setShiftX(dir1list.get(s).getX());	
										temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}else if(dir2list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir2list.size()){
							matrix5=zp.Shifting(matrix2, dir2list.get(s).getX(), dir2list.get(s).getY(), N1, M1, 2);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir2list.get(s).getX(), Xmax-dir2list.get(s).getX(), Y1min, Y1max, Ymin, Ymax);
							if(listOfEmbedding.get(1).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(2);
								temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir2list.get(s).getY());	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir2list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX());	
										temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir2list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(2);
										temp.setShiftX(dir2list.get(s).getX());	
										temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
						
					}else if(dir3list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir3list.size()&&temp.getRepassing()==false){
							matrix5=zp.Shifting(matrix2, dir3list.get(s).getX(), dir3list.get(s).getY(), N1, M1, 3);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir3list.get(s).getX(), Xmax-dir3list.get(s).getX(), Y1min, Y1max, Ymin+dir3list.get(s).getY(), Ymax+dir3list.get(s).getY());
							if(listOfEmbedding.get(2).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(3);
								temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir3list.get(s).getY()-ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir3list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX());	
										temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir3list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(3);
										temp.setShiftX(dir3list.get(s).getX());	
										temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}else if(dir4list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir4list.size()){
							matrix5=zp.Shifting(matrix2, dir4list.get(s).getX(), dir4list.get(s).getY(), N1, M1, 4);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin, Xmax, Y1min, Y1max, Ymin+dir4list.get(s).getY(), Ymax+dir4list.get(s).getY());
							if(listOfEmbedding.get(3).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(4);
								temp.setShiftX(dir4list.get(s).getX());	
								temp.setShiftX(dir4list.get(s).getY()-ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir4list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX());	
										temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir4list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(4);
										temp.setShiftX(dir4list.get(s).getX());	
										temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}	
					}else if(dir5list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir5list.size()){
							matrix5=zp.Shifting(matrix2, dir5list.get(s).getX(), dir5list.get(s).getY(), N1, M1, 5);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir5list.get(s).getX(), Xmax+dir5list.get(s).getX(), Y1min, Y1max, Ymin+dir5list.get(s).getY(), Ymax+dir5list.get(s).getY());
							if(listOfEmbedding.get(4).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(5);
								temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
								temp.setShiftX(dir5list.get(s).getY()-ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir5list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX());	
										temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir5list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(5);
										temp.setShiftX(dir5list.get(s).getX());	
										temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}else if(dir6list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir6list.size()){
							matrix5=zp.Shifting(matrix2, dir6list.get(s).getX(), dir6list.get(s).getY(), N1, M1, 6);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir6list.get(s).getX(), Xmax+dir6list.get(s).getX(), Y1min, Y1max, Ymin, Ymax);
							if(listOfEmbedding.get(5).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(6);
								temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
								temp.setShiftX(dir6list.get(s).getY());	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir6list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX());	
										temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir6list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(6);
										temp.setShiftX(dir6list.get(s).getX());	
										temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}else if(dir7list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir7list.size()){
							matrix5=zp.Shifting(matrix2, dir7list.get(s).getX(), dir7list.get(s).getY(), N1, M1, 7);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir7list.get(s).getX(), Xmax+dir7list.get(s).getX(), Y1min, Y1max, Ymin-dir7list.get(s).getY(), Ymax-dir7list.get(s).getY());
							if(listOfEmbedding.get(6).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(7);
								temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
								temp.setShiftX(dir7list.get(s).getY()+ScaningStep);	
								templist.add(temp);
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir7list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX());	
										temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir7list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(7);
										temp.setShiftX(dir7list.get(s).getX());	
										temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}else if(dir8list.size()!=0){
						int s=0;
						int s1=0;
						while(s<dir8list.size()){
							matrix5=zp.Shifting(matrix2, dir8list.get(s).getX(), dir8list.get(s).getY(), N1, M1, 8);
							ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
							listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin, Xmax, Y1min, Y1max, Ymin-dir8list.get(s).getY(), Ymax-dir8list.get(s).getY());
							if(listOfEmbedding.get(7).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(8);
								temp.setShiftX(dir8list.get(s).getX());	
								temp.setShiftX(dir8list.get(s).getY()+ScaningStep);	
							}else{
							while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
								switch (s1+1){
								case 1:
									if(listOfEmbedding.get(0).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
									break;
								case 2:
									if(listOfEmbedding.get(1).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir8list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 3:
									if(listOfEmbedding.get(2).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
										temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 4:
									if(listOfEmbedding.get(3).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX());	
										temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 5:
									if(listOfEmbedding.get(4).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
										templist.add(temp);
										}
									break;
								case 6:
									if(listOfEmbedding.get(5).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir8list.get(s).getY());
										templist.add(temp);
										}
									break;
								case 7:
									if(listOfEmbedding.get(6).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
										temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;
								case 8:
									if(listOfEmbedding.get(7).getX()==1){
										temp.setRepassing(true);
										temp.setDirection(8);
										temp.setShiftX(dir8list.get(s).getX());	
										temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
										templist.add(temp);
										}
									break;

								}	
								s1++;
							}
							}
							s++;
						}
					}
					
					
		}
		
		System.out.println("dirction 1 "+dir1list.size());
		System.out.println("dirction 2 "+dir2list.size());
		System.out.println("dirction 3 "+dir3list.size());
		System.out.println("dirction 4 "+dir4list.size());
		System.out.println("dirction 5 "+dir5list.size());
		System.out.println("dirction 6 "+dir6list.size());
		System.out.println("dirction 7 "+dir7list.size());
		System.out.println("dirction 8 "+dir8list.size());
		
		return templist;
	}
public ArrayList <HoldingResult> Repassing2(FWL lane1, FWL lane2, FWL lane3, Patterns Shape, int ScaningStep){//lane1 is fixed, lane2 is the shifting one
		
		long startTime = System.nanoTime();
		HoldingResult temp=new HoldingResult();
		HoldingResult temp1=new HoldingResult();
		ArrayList <HoldingResult> templist=new ArrayList <HoldingResult> ();
		ArrayList <HoldingResult> templist1=new ArrayList <HoldingResult> ();
		temp.setRepassing(false);
		temp.setDirection(0);
		temp.setShiftX(0);
		temp.setShiftY(0);
		
		SetOfPatternsForALane patternslane1=new SetOfPatternsForALane();
		SetOfPatternsForALane patternslane2=new SetOfPatternsForALane();
		SetOfPatternsForALane patternslane3=new SetOfPatternsForALane();
		MinAndMax templane1= new MinAndMax();
		MinAndMax templane2= new MinAndMax();
		MinAndMax templane3= new MinAndMax();
		
		Patterns nesto=patternslane2.PatternsForOneLane(Shape, lane2);
		Patterns nesto1=patternslane3.PatternsForOneLane(Shape, lane3);
		int X2min=(int) ( templane2.TheXminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2))-lane2.getStart());
		int X2max=(int) ( templane2.TheXmaxOfAllPatterns(nesto)-lane2.getStart());
		int Y2min=(int) ( templane2.TheYminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)));
		int Y2max=(int) ( templane2.TheYmaxOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)));
		int X1min=(int) ( templane1.TheXminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1))-lane1.getStart());
		int X1max=(int) ( templane1.TheXmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1))-lane1.getStart());
		int Y1min=(int) ( templane1.TheYminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)));
		int Y1max=(int) ( templane1.TheYmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)));
		int X3min=(int) (templane3.TheXminOfAllPatterns(patternslane3.PatternsForOneLane(Shape, lane3))-lane3.getStart());
		int X3max=(int) ( templane3.TheXmaxOfAllPatterns(nesto1)-lane3.getStart());
		int Y3min=(int) ( templane3.TheYminOfAllPatterns(patternslane3.PatternsForOneLane(Shape, lane3)));
		int Y3max=(int) ( templane3.TheYmaxOfAllPatterns(patternslane3.PatternsForOneLane(Shape, lane3)));
		int CPC=(int) lane1.getLength();
		
		int Shift1Xdirection1=X2min;
		int Shift1Xdirection2=X2min;
		int Shift1Xdirection3=X2min;
		int Shift1Xdirection4=0;
		int Shift1Xdirection5=(int) (lane1.getEnd()-lane1.getStart()-X2max);
		int Shift1Xdirection6=(int) (lane1.getEnd()-lane1.getStart()-X2max);
		int Shift1Xdirection7=(int) (lane1.getEnd()-lane1.getStart()-X2max);
		int Shift1Xdirection8=0;
		int Shift1Ydirection1=Y2min;
		int Shift1Ydirection2=0;
		int Shift1Ydirection3=CPC-Y2max;
		int Shift1Ydirection4=CPC-Y2max;
		int Shift1Ydirection5=CPC-Y2max;
		int Shift1Ydirection6=0;
		int Shift1Ydirection7=Y2min;
		int Shift1Ydirection8=Y2min;
		int Shift2Xdirection1=X3min;
		int Shift2Xdirection2=X3min;
		int Shift2Xdirection3=X3min;
		int Shift2Xdirection4=0;
		int Shift2Xdirection5=(int) (lane1.getEnd()-lane1.getStart()-X3max);
		int Shift2Xdirection6=(int) (lane1.getEnd()-lane1.getStart()-X3max);
		int Shift2Xdirection7=(int) (lane1.getEnd()-lane1.getStart()-X3max);
		int Shift2Xdirection8=0;
		int Shift2Ydirection1=Y3min;
		int Shift2Ydirection2=0;
		int Shift2Ydirection3=CPC-Y3max;
		int Shift2Ydirection4=CPC-Y3max;
		int Shift2Ydirection5=CPC-Y3max;
		int Shift2Ydirection6=0;
		int Shift2Ydirection7=Y3min;
		int Shift2Ydirection8=Y3min;
		//two matrices for discretization
		Discretization disc=new Discretization ();	
		int N1=(int) lane1.getWidth();
		int M1=(int) lane1.getLength();
		int N2=(int) lane2.getWidth();
		int M2=(int) lane2.getLength();
		int N3=(int) lane3.getWidth();
		int M3=(int) lane3.getLength();
		
		int [][] matrix1=new int [N1][M1];
		int [][] matrix2=new int [N2][M2];
		int [][] matrix3=new int [N1][M2];
		int [][] matrix4=new int [N1][M1];	
		int [][] matrix5=new int [N1][M1];	
		int [][] matrix6=new int [N1][M1];
		int [][] matrix7=new int [N1][M1];
		int [][] matrix3i=new int [N1][M2];
		matrix1=disc.DiscretizationOfLane(lane1, Shape);//discrete lane1
		matrix2=disc.DiscretizationOfLane(lane2, Shape);//discrete lane2, from now on we only work with discrete ones
		matrix3=disc.DiscretizationOfLane(lane3, Shape);//discrete lane3
		boolean t=false;
		boolean t1=false;
		//setting matrices to same dimension
		 if(lane1.getWidth()>lane2.getWidth()){
			t=true;
			for(int j=0;j<M1;j++){
				for(int i=0;i<N2;i++){
					matrix4[i][j]=matrix2[i][j];
				}
				for(int i=N2;i<N1;i++){
					matrix4[i][j]=0;
				}
			}
		}
		 if(t==true){
			 matrix2=matrix4;
		 }
		 if(lane1.getWidth()>lane3.getWidth()){
				t1=true;
				for(int j=0;j<M1;j++){
					for(int i=0;i<N3;i++){
						matrix5[i][j]=matrix2[i][j];
					}
					for(int i=N3;i<N1;i++){
						matrix5[i][j]=0;
					}
				}
			}
		 if(t1==true){
			 matrix3=matrix5;
		 }
	
		ArrayList <Direction1andDirection2> dir1list=new ArrayList <Direction1andDirection2>();
		ArrayList <Direction1andDirection2> dir2list=new ArrayList <Direction1andDirection2>();
		ArrayList <Direction1andDirection2> dir3list=new ArrayList <Direction1andDirection2>();
		ArrayList <Direction1andDirection2> dir4list=new ArrayList <Direction1andDirection2>();
		ArrayList <Direction1andDirection2> dir5list=new ArrayList <Direction1andDirection2>();
		ArrayList <Direction1andDirection2> dir6list=new ArrayList <Direction1andDirection2>();
		ArrayList <Direction1andDirection2> dir7list=new ArrayList <Direction1andDirection2>();
		ArrayList <Direction1andDirection2> dir8list=new ArrayList <Direction1andDirection2>();
		DirectionList DIRList=new DirectionList();
		
		ZeroPadding zp=new ZeroPadding();
		Overlaping over=new Overlaping ();
		boolean shift1=false;
		boolean shift=false;
		boolean scan=true;
		

			//direction1 for lane2
			/*for(int k=0;k<Shift1Xdirection1;k++)
				for(int l=0;l<Shift1Ydirection1;l++){
					matrix6=zp.Shifting(matrix2, k, l, N1, M1, 1);
					shift1=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix6, X1min, X1max, X2min-k, X2max-k, Y1min, Y1max, Y2min-l, Y2max-l);
					if(shift1==false){
						int q=0;	
					matrix3i=zp.Shifting(matrix3, k, l, N1, M1, 1);
					//******
						//direction 1 for lane3
					for(int k1=0;k1<(Shift2Xdirection1-k);k1++)
						for(int l1=0;l1<(Shift2Ydirection1-l);l1++){
							matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 1);
							shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
							if(shift==false){
								org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
								//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
								//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
								intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
								intP1.seDirection1(1);
								intP1.setDirection2(1);
								intP1.setShiftX1(k+intP.getShiftX1());
								intP1.setShiftY1(l+intP.getShiftY1());
								intP1.setShiftX2(k1+intP.getShiftX2());
								intP1.setShiftY2(l1+intP.getShiftY2());
								if(intP.getShiftX1()!=0)
								dir1list.add(intP1);
								
								if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
									 q=0;
								}else{
									q=1;
								}
								if(q==1)
									break;
							}
						}
					if(q==1)
						break;
					//direction2 for lane3
					
						for(int l1=0;l1<(Shift2Xdirection2-k);l1++){
							matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 2);
							shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
							if(shift==false){
								org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
								//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
								//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
								intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
								intP1.seDirection1(1);
								intP1.setDirection2(2);
								intP1.setShiftX1(k+intP.getShiftX1());
								intP1.setShiftY1(l+intP.getShiftY1());
								intP1.setShiftX2(l1+intP.getShiftX2());
								intP1.setShiftY2(0+intP.getShiftY2());
								if(intP.getShiftX1()!=0)
								dir1list.add(intP1);
								if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
									 q=0;
								}else{
									q=1;
								}
								if(q==1)
									break;
							}
						}
						if(q==1)
							break;
					//direction3 for lane3
						for(int k1=0;k1<(Shift2Xdirection3-k);k1++)
							for(int l1=0;l1<(Shift2Ydirection3-l);l1++){
								matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 3);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,  X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,  X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
									intP1.seDirection1(1);
									intP1.setDirection2(3);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(l+intP.getShiftY1());
									intP1.setShiftX2(k1+intP.getShiftX2());
									intP1.setShiftY2(-l1+intP.getShiftY2());
									if(intP.getShiftX1()!=0)
									dir1list.add(intP1);
									if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
										 q=0;
									}else{
										q=1;
									}
									if(q==1)
										break;
								}
							}
						if(q==1)
							break;
					//direction4 for lane3
						for(int k1=0;k1<(Shift2Ydirection4-l);k1++){
								matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 4);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+k1, Y3max+k1);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+k1, Y3max+k1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+k1, Y3max+k1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+k1, Y3max+k1);
									intP1.seDirection1(1);
									intP1.setDirection2(4);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(l+intP.getShiftY1());
									intP1.setShiftX2(0+intP.getShiftX2());
									intP1.setShiftY2(-k1+intP.getShiftY2());
									if(intP.getShiftX1()!=0)
									dir1list.add(intP1);
									if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
										 q=0;
									}else{
										q=1;
									}
									if(q==1)
										break;
								}
							}
						if(q==1)
							break;
					//direction5 for lane3
						for(int k1=0;k1<(Shift2Xdirection5-k);k1++)
							for(int l1=0;l1<(Shift2Ydirection5-l);l1++){
								matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 5);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
									intP1.seDirection1(1);
									intP1.setDirection2(5);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(l+intP.getShiftY1());
									intP1.setShiftX2(-k1+intP.getShiftX2());
									intP1.setShiftY2(-l1+intP.getShiftY2());
									if(intP.getShiftX1()!=0)
									dir1list.add(intP1);
									if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
										 q=0;
									}else{
										q=1;
									}
									if(q==1)
										break;
								}
							}
						if(q==1)
							break;
					//direction6 for lane3
					
							for(int l1=0;l1<(Shift2Xdirection6-k);l1++){
								matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 6);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
									intP1.seDirection1(1);
									intP1.setDirection2(6);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(l+intP.getShiftY1());
									intP1.setShiftX2(-l1+intP.getShiftX2());
									intP1.setShiftY2(0+intP.getShiftY2());
									if(intP.getShiftX1()!=0)
									dir1list.add(intP1);
									if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
										 q=0;
									}else{
										q=1;
									}
									if(q==1)
										break;
								}
							}
							if(q==1)
								break;
					//direction7 for lane3
							for(int k1=0;k1<(Shift2Xdirection7-k);k1++)
								for(int l1=0;l1<(Shift2Ydirection7-l);l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 7);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
										intP1.seDirection1(1);
										intP1.setDirection2(7);
										intP1.setShiftX1(k+intP.getShiftX1());
										intP1.setShiftY1(l+intP.getShiftY1());
										intP1.setShiftX2(-k1+intP.getShiftX2());
										intP1.setShiftY2(l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
										dir1list.add(intP1);
										if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
											 q=0;
										}else{
											q=1;
										}
										if(q==1)
											break;
									}
								}
							if(q==1)
								break;
					//direction8 for lane3
							for(int k1=0;k1<(Shift2Ydirection8-l);k1++){
									matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 8);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-k1, Y3max-k1);
									if(shift==false){				
								org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
								//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-k1, Y3max-k1);
								//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-k1, Y3max-k1);
								intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-k1, Y3max-k1);
								intP1.seDirection1(1);
								intP1.setDirection2(8);
								intP1.setShiftX1(k+intP.getShiftX1());
								intP1.setShiftY1(l+intP.getShiftY1());
								intP1.setShiftX2(0+intP.getShiftX2());
								intP1.setShiftY2(k1+intP.getShiftY2());
								if(intP.getShiftX1()!=0)
								dir1list.add(intP1);
								if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
									 q=0;
								}else{
									q=1;
								}
								if(q==1)
									break;
									}
								}
							if(q==1)
								break;
					//*****
					}	
				}
			
			//direction2 for lane2
			shift1=false;
			shift=false;
			System.out.println("broj elemenata iz prve liste je "+dir1list.size());
				for(int l=0;l<Shift1Xdirection2;l++){
					matrix6=zp.Shifting(matrix2, l, 0, N1, M1, 2);
					shift1=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix6, X1min, X1max, X2min-l, X2max-l, Y1min, Y1max, Y2min-0, Y2max-0);

					if(shift1==false){
						matrix3i=zp.Shifting(matrix3, l, 0, N1, M1, 2);
						int q=0;
						//direction 1 for lane3
					for(int k1=0;k1<(Shift2Xdirection1-l);k1++)
						for(int l1=0;l1<Shift2Ydirection1;l1++){
							matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 1);
							shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-l, X2max-l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-0, Y2max-0, Y3min-l1, Y3max-l1);
							if(shift==false){
								org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
								//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-l, X2max-l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
								//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
								intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
								intP1.seDirection1(2);
								intP1.setDirection2(1);
								intP1.setShiftX1(l+intP.getShiftX1());
								intP1.setShiftY1(0+intP.getShiftY1());
								intP1.setShiftX2(k1+intP.getShiftX2());
								intP1.setShiftY2(l1+intP.getShiftY2());
								if(intP.getShiftX1()!=0)
								dir2list.add(intP1);
								if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
									 q=0;
								}else{
									q=1;
								}
								if(q==1)
									break;
									
							}
						}
					if(q==1)
						break;
					//direction2 for lane3
					
						for(int l1=0;l1<(Shift2Xdirection2-l);l1++){
							matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 2);
							shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-l, X2max-l, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-0, Y2max-0, Y3min, Y3max);
							if(shift==false){
								org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
								//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-l, X2max-l, X3min-l1, X3max-l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
								//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min-l1, X3max-l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
								intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min-l1, X3max-l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
								intP1.seDirection1(2);
								intP1.setDirection2(2);
								intP1.setShiftX1(l+intP.getShiftX1());
								intP1.setShiftY1(0+intP.getShiftY1());
								intP1.setShiftX2(l1+intP.getShiftX2());
								intP1.setShiftY2(0+intP.getShiftY2());
								if(intP.getShiftX1()!=0)
								dir2list.add(intP1);
								if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
									 q=0;
								}else{
									q=1;
								}
								if(q==1)
									break;
							}
						}
						if(q==1)
							break;
					//direction3 for lane3
						for(int k1=0;k1<(Shift2Xdirection3-l);k1++)
							for(int l1=0;l1<Shift2Ydirection3;l1++){
								matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 3);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-l, X2max-l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-0, Y2max-0, Y3min+l1, Y3max+l1);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-l, X2max-l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
									intP1.seDirection1(2);
									intP1.setDirection2(3);
									intP1.setShiftX1(l+intP.getShiftX1());
									intP1.setShiftY1(0+intP.getShiftY1());
									intP1.setShiftX2(k1+intP.getShiftX2());
									intP1.setShiftY2(-l1+intP.getShiftY2());
									if(intP.getShiftX1()!=0)
									dir2list.add(intP1);
									if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
										 q=0;
									}else{
										q=1;
									}
									if(q==1)
										break;
								}
							}
						if(q==1)
							break;
					//direction4 for lane3
						for(int k1=0;k1<Shift2Ydirection4;k1++){
								matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 4);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-l, X2max-l, X3min, X3max, Y1min, Y1max, Y2min-0, Y2max-0, Y3min+k1, Y3max+k1);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-l, X2max-l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+k1, Y3max+k1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+k1, Y3max+k1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+k1, Y3max+k1);
									intP1.seDirection1(2);
									intP1.setDirection2(4);
									intP1.setShiftX1(l+intP.getShiftX1());
									intP1.setShiftY1(0+intP.getShiftY1());
									intP1.setShiftX2(0+intP.getShiftX2());
									intP1.setShiftY2(-k1+intP.getShiftY2());
									if(intP.getShiftX1()!=0)
									dir2list.add(intP1);
									if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
										 q=0;
									}else{
										q=1;
									}
									if(q==1)
										break;
								}
							}
						if(q==1)
							break;
					//direction5 for lane3
						for(int k1=0;k1<(Shift2Xdirection5-l);k1++)
							for(int l1=0;l1<Shift2Ydirection5;l1++){
								matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 5);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-l, X2max-l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-0, Y2max-0, Y3min+l1, Y3max+l1);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-l, X2max-l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
									intP1.seDirection1(2);
									intP1.setDirection2(5);
									intP1.setShiftX1(l+intP.getShiftX1());
									intP1.setShiftY1(0+intP.getShiftY1());
									intP1.setShiftX2(-k1+intP.getShiftX2());
									intP1.setShiftY2(-l1+intP.getShiftY2());
									if(intP.getShiftX1()!=0)
									dir2list.add(intP1);
									if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
										 q=0;
									}else{
										q=1;
									}
									if(q==1)
										break;
								}
							}
						if(q==1)
							break;
					//direction6 for lane3
					
							for(int l1=0;l1<(Shift2Xdirection6-l);l1++){
								matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 6);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-l, X2max-l, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-0, Y2max-0, Y3min, Y3max);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-l, X2max-l, X3min+l1, X3max+l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min+l1, X3max+l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min+l1, X3max+l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
									intP1.seDirection1(2);
									intP1.setDirection2(6);
									intP1.setShiftX1(l+intP.getShiftX1());
									intP1.setShiftY1(0+intP.getShiftY1());
									intP1.setShiftX2(-l1+intP.getShiftX2());
									intP1.setShiftY2(0+intP.getShiftY2());
									if(intP.getShiftX1()!=0)
									dir2list.add(intP1);
									if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
										 q=0;
									}else{
										q=1;
									}
									if(q==1)
										break;
								}
							}
							if(q==1)
								break;
					//direction7 for lane3
							for(int k1=0;k1<(Shift2Xdirection7-l);k1++)
								for(int l1=0;l1<Shift2Ydirection7;l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 7);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-l, X2max-l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-0, Y2max-0, Y3min-l1, Y3max-l1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-l, X2max-l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,  X1min, X1max, X2min-l, X2max-l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,  X1min, X1max, X2min-l, X2max-l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
										intP1.seDirection1(2);
										intP1.setDirection2(7);
										intP1.setShiftX1(l+intP.getShiftX1());
										intP1.setShiftY1(0+intP.getShiftY1());
										intP1.setShiftX2(-k1+intP.getShiftX2());
										intP1.setShiftY2(l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir2list.add(intP1);
										if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
											 q=0;
										}else{
											q=1;
										}
										if(q==1)
											break;
									}
								}
							if(q==1)
								break;
					//direction8 for lane3
							for(int k1=0;k1<Shift2Ydirection8;k1++){
									matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 8);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-l, X2max-l, X3min, X3max, Y1min, Y1max, Y2min-0, Y2max-0, Y3min-k1, Y3max-k1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-l, X2max-l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-k1, Y3max-k1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-k1, Y3max-k1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-l, X2max-l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-k1, Y3max-k1);
										intP1.seDirection1(2);
										intP1.setDirection2(8);
										intP1.setShiftX1(l+intP.getShiftX1());
										intP1.setShiftY1(0+intP.getShiftY1());
										intP1.setShiftX2(0+intP.getShiftX2());
										intP1.setShiftY2(k1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir2list.add(intP1);
										if(intP.getDirection1()==0&&intP.getDirection2()==0&&intP.getShiftX1()==0&&intP.getShiftX2()==0&&intP.getShiftY1()==0&&intP.getShiftY2()==0){
											 q=0;
										}else{
											q=1;
										}
										if(q==1)
											break;
									}
								}
							if(q==1)
								break;
					//*****
					}
				}
				System.out.println("broje elementa sa druge liste je "+dir2list.size());*/
				/*shift1=false;
				shift=false;
			//direction3 for lane2
				for(int k=0;k<Shift1Xdirection3;k++)
					for(int l=0;l<Shift1Ydirection3;l++){
						matrix6=zp.Shifting(matrix2, k, l, N1, M1, 3);
						shift1=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix6, X1min, X1max, X2min-k, X2max-k, Y1min, Y1max, Y2min+l, Y2max+l);

						if(shift1==false){
							matrix3i=zp.Shifting(matrix3, k, l, N1, M1, 3);
							//******
							//direction 1 for lane3
						for(int k1=0;k1<(Shift2Xdirection1-k);k1++)
							for(int l1=0;l1<(Shift2Ydirection1-l);l1++){
								matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 1);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
								if(shift==false){
								    org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									intP1.seDirection1(3);
									intP1.setDirection2(1);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(-l+intP.getShiftY1());
									intP1.setShiftX2(k1+intP.getShiftX2());
									intP1.setShiftY2(l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir3list.add(intP1);
								}
							}
						//direction2 for lane3
						
							for(int l1=0;l1<(Shift2Xdirection2-k);l1++){
								matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 2);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
								if(shift==false){
									 org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min-k, X2max-k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min-k, X2max-k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									intP1.seDirection1(3);
									intP1.setDirection2(2);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(-l+intP.getShiftY1());
									intP1.setShiftX2(l1+intP.getShiftX2());
									intP1.setShiftY2(0+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir3list.add(intP1);
								}
							}
						//direction3 for lane3
							for(int k1=0;k1<(Shift2Xdirection3-k);k1++)
								for(int l1=0;l1<(Shift2Ydirection3-l);l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 3);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+l1, Y3max+l1);
									if(shift==false){
										 org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+l1, Y3max+l1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+l1, Y3max+l1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+l1, Y3max+l1);
									intP1.seDirection1(3);
									intP1.setDirection2(3);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(-l+intP.getShiftY1());
										intP1.setShiftX2(k1+intP.getShiftX2());
										intP1.setShiftY2(-l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir3list.add(intP1);
									}
								}
						//direction4 for lane3
							for(int k1=0;k1<(Shift2Ydirection4-l);k1++){
									matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 4);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+k1, Y3max+k1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+k1, Y3max+k1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+k1, Y3max+k1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+k1, Y3max+k1);
									intP1.seDirection1(3);
									intP1.setDirection2(4);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(-l+intP.getShiftY1());
									intP1.setShiftX2(0+intP.getShiftX2());
									intP1.setShiftY2(-k1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir3list.add(intP1);
									}
								}
						//direction5 for lane3
							for(int k1=0;k1<(Shift2Xdirection5-k);k1++)
								for(int l1=0;l1<(Shift2Ydirection5-l);l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 5);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+l1, Y3max+l1);
									if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+l1, Y3max+l1);
								//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+l1, Y3max+l1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+l1, Y3max+l1);
									intP1.seDirection1(3);
									intP1.setDirection2(5);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(-l+intP.getShiftY1());
										intP1.setShiftX2(-k1+intP.getShiftX2());
										intP1.setShiftY2(-l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir3list.add(intP1);
									}
								}
						//direction6 for lane3
						
								for(int l1=0;l1<(Shift2Xdirection6-k);l1++){
									matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 6);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min-k, X2max-k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min-k, X2max-k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									intP1.seDirection1(3);
									intP1.setDirection2(6);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(-l+intP.getShiftY1());
										intP1.setShiftX2(-l1+intP.getShiftX2());
										intP1.setShiftY2(0+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir3list.add(intP1);
									}
								}
						//direction7 for lane3
								for(int k1=0;k1<(Shift2Xdirection7-k);k1++)
									for(int l1=0;l1<(Shift2Ydirection7-l);l1++){
										matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 7);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
										if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									intP1.seDirection1(3);
									intP1.setDirection2(7);
									intP1.setShiftX1(k+intP.getShiftX1());
									intP1.setShiftY1(-l+intP.getShiftY1());
											intP1.setShiftX2(-k1+intP.getShiftX2());
											intP1.setShiftY2(l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir3list.add(intP1);
										}
									} 
						//direction8 for lane3
								for(int k1=0;k1<(Shift2Ydirection8-l);k1++){
										matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 8);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-k1, Y3max-k1);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-k1, Y3max-k1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-k1, Y3max-k1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min-k, X2max-k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-k1, Y3max-k1);
											intP1.seDirection1(3);
											intP1.setDirection2(8);
											intP1.setShiftX1(k+intP.getShiftX1());
											intP1.setShiftY1(-l+intP.getShiftY1());
											intP1.setShiftX2(0+intP.getShiftX2());
											intP1.setShiftY2(k1+intP.getShiftY2());
												if(intP.getShiftX1()!=0)
													dir3list.add(intP1);
										}
									}
						
						//*****
						}
					}
				shift1=false;
				shift=false;
				System.out.println("broje elemanata trece liste je "+dir3list.size());
			//direction4 for lane2
				for(int k=0;k<Shift1Ydirection4;k++){
						matrix6=zp.Shifting(matrix2, 0, k, N1, M1, 4);
						shift1=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix6, X1min, X1max, X2min+0, X2max+0, Y1min, Y1max, Y2min+k, Y2max+k);

						if(shift1==false){
							matrix3i=zp.Shifting(matrix3, 0, k, N1, M1, 4);
							//******
							//direction 1 for lane3
						for(int k1=0;k1<Shift2Xdirection1;k1++)
							for(int l1=0;l1<(Shift2Ydirection1-k);l1++){
								matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 1);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-l1, Y3max-l1);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-l1, Y3max-l1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-l1, Y3max-l1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-l1, Y3max-l1);
										intP1.seDirection1(4);
										intP1.setDirection2(1);
										intP1.setShiftX1(0+intP.getShiftX1());
										intP1.setShiftY1(-k+intP.getShiftY1());
										intP1.setShiftX2(k1+intP.getShiftX2());
										intP1.setShiftY2(l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
										dir4list.add(intP1);
									
									
								}
							}
						//direction2 for lane3
						
							for(int l1=0;l1<Shift2Xdirection2;l1++){
								matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 2);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min, Y3max);
								if(shift==false){
									
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min, Y3max);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min, Y3max);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min, Y3max);
										intP1.seDirection1(4);
										intP1.setDirection2(2);
										intP1.setShiftX1(0+intP.getShiftX1());
										intP1.setShiftY1(-k+intP.getShiftY1());
										intP1.setShiftX2(l1+intP.getShiftX2());
										intP1.setShiftY2(0+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
										dir4list.add(intP1);
									}
									
							}
						//direction3 for lane3
							for(int k1=0;k1<Shift2Xdirection3;k1++)
								for(int l1=0;l1<(Shift2Ydirection3-k);l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 3);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+l1, Y3max+l1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+l1, Y3max+l1);
							//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+l1, Y3max+l1);
								intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+l1, Y3max+l1);
											intP1.seDirection1(4);
											intP1.setDirection2(3);
											intP1.setShiftX1(0+intP.getShiftX1());
											intP1.setShiftY1(-k+intP.getShiftY1());
											intP1.setShiftX2(k1+intP.getShiftX2());
											intP1.setShiftY2(-l1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
											dir4list.add(intP1);
									}
								}
						//direction4 for lane3
							for(int k1=0;k1<(Shift2Ydirection4-k);k1++){
									matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 4);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+k1, Y3max+k1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+k1, Y3max+k1);
								//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+k1, Y3max+k1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+k1, Y3max+k1);
											intP1.seDirection1(4);
											intP1.setDirection2(4);
											intP1.setShiftX1(0+intP.getShiftX1());
											intP1.setShiftY1(-k+intP.getShiftY1());
											intP1.setShiftX2(0+intP.getShiftX2());
											intP1.setShiftY2(-k1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
											dir4list.add(intP1);
										}
								}
						//direction5 for lane3
							for(int k1=0;k1<Shift2Xdirection5;k1++)
								for(int l1=0;l1<(Shift2Ydirection5-k);l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 5);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+l1, Y3max+l1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+l1, Y3max+l1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+l1, Y3max+l1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min+l1, Y3max+l1);
											intP1.seDirection1(4);
											intP1.setDirection2(5);
											intP1.setShiftX1(0+intP.getShiftX1());
											intP1.setShiftY1(-k+intP.getShiftY1());
											intP1.setShiftX2(-k1+intP.getShiftX2());
											intP1.setShiftY2(-l1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
											dir4list.add(intP1);	
										}
								}
						//direction6 for lane3
						
								for(int l1=0;l1<Shift2Xdirection6;l1++){
									matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 6);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min, Y3max);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min, Y3max);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min, Y3max);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min, Y3max);
											intP1.seDirection1(4);
											intP1.setDirection2(6);
											intP1.setShiftX1(0+intP.getShiftX1());
											intP1.setShiftY1(-k+intP.getShiftY1());
											intP1.setShiftX2(-l1+intP.getShiftX2());
											intP1.setShiftY2(0+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
											dir4list.add(intP1);	
										}
										
								}
						//direction7 for lane3
								for(int k1=0;k1<Shift2Xdirection7;k1++)
									for(int l1=0;l1<(Shift2Ydirection7-k);l1++){
										matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 7);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-l1, Y3max-l1);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-l1, Y3max-l1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-l1, Y3max-l1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-l1, Y3max-l1);
												intP1.seDirection1(4);
												intP1.setDirection2(7);
												intP1.setShiftX1(0+intP.getShiftX1());
												intP1.setShiftY1(-k+intP.getShiftY1());
												intP1.setShiftX2(-k1+intP.getShiftX2());
												intP1.setShiftY2(l1+intP.getShiftY2());
												if(intP.getShiftX1()!=0)
												dir4list.add(intP1);	
											}
											
									}
						//direction8 for lane3
								for(int k1=0;k1<(Shift2Ydirection8-k);k1++){
										matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 8);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-k1, Y3max-k1);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-k1, Y3max-k1);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-k1, Y3max-k1);
												intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min+k, Y2max+k, Y3min-k1, Y3max-k1);
												intP1.seDirection1(4);
												intP1.setDirection2(8);
												intP1.setShiftX1(0+intP.getShiftX1());
												intP1.setShiftY1(-k+intP.getShiftY1());
												intP1.setShiftX2(0+intP.getShiftX2());
												intP1.setShiftY2(k1+intP.getShiftY2());
												if(intP.getShiftX1()!=0)
												dir4list.add(intP1);
											}
											
									}
						
						//*****
						}
					}
				
				shift1=false;
				shift=false;
				System.out.println("broj elementa sa cetvrte liste je "+dir4list.size());
			//direction5 for lane2
				for(int k=0;k<Shift1Xdirection5;k++)
					for(int l=0;l<Shift1Ydirection5;l++){
						matrix6=zp.Shifting(matrix2, k, l, N1, M1, 5);
						shift1=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix6, X1min, X1max, X2min+k, X2max+k, Y1min, Y1max, Y2min+l, Y2max+l);

						if(shift1==false){
							matrix3i=zp.Shifting(matrix3, k, l, N1, M1, 5);
							//******
							//direction 1 for lane3
						for(int k1=0;k1<(Shift2Xdirection1-k);k1++)
							for(int l1=0;l1<(Shift2Ydirection1-l);l1++){
								matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 1);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
								if(shift==false){
								    org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									intP1.seDirection1(5);
									intP1.setDirection2(1);
									intP1.setShiftX1(-k+intP.getShiftX1());
									intP1.setShiftY1(-l+intP.getShiftY1());
									intP1.setShiftX2(k1+intP.getShiftX2());
									intP1.setShiftY2(l1+intP.getShiftY2());
									if(intP.getShiftX1()!=0)
									dir5list.add(intP1);
								}
							}
						//direction2 for lane3
						
							for(int l1=0;l1<(Shift2Xdirection2-k);l1++){
								matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 2);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
								//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									intP1.seDirection1(5);
									intP1.setDirection2(2);
									intP1.setShiftX1(-k+intP.getShiftX1());
									intP1.setShiftY1(-l+intP.getShiftY1());
									intP1.setShiftX2(l1+intP.getShiftX2());
									intP1.setShiftY2(0+intP.getShiftY2());
									if(intP.getShiftX1()!=0)
									dir5list.add(intP1);
								}
							}
						//direction3 for lane3
							for(int k1=0;k1<(Shift2Xdirection3-k);k1++)
								for(int l1=0;l1<(Shift2Ydirection3-l);l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 3);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+l1, Y3max+l1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
							//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
								intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
										intP1.seDirection1(5);
										intP1.setDirection2(3);
										intP1.setShiftX1(-k+intP.getShiftX1());
										intP1.setShiftY1(-l+intP.getShiftY1());
										intP1.setShiftX2(k1+intP.getShiftX2());
										intP1.setShiftY2(-l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
										dir5list.add(intP1);
									}
								}
						//direction4 for lane3
							for(int k1=0;k1<(Shift2Ydirection4-l);k1++){
									matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 4);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+k1, Y3max+k1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+k1, Y3max+k1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+k1, Y3max+k1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+k1, Y3max+k1);
										intP1.seDirection1(5);
										intP1.setDirection2(4);
										intP1.setShiftX1(-k+intP.getShiftX1());
										intP1.setShiftY1(-l+intP.getShiftY1());
										intP1.setShiftX2(0+intP.getShiftX2());
										intP1.setShiftY2(-k1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
										dir5list.add(intP1);
									}
								}
						//direction5 for lane3
							for(int k1=0;k1<(Shift2Xdirection5-k);k1++)
								for(int l1=0;l1<(Shift2Ydirection5-l);l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 5);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min+l1, Y3max+l1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
										intP1.seDirection1(5);
										intP1.setDirection2(5);
										intP1.setShiftX1(-k+intP.getShiftX1());
										intP1.setShiftY1(-l+intP.getShiftY1());
										intP1.setShiftX2(-k1+intP.getShiftX2());
										intP1.setShiftY2(-l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
									    dir5list.add(intP1);
									}
								}
						//direction6 for lane3
						
								for(int l1=0;l1<(Shift2Xdirection6-k);l1++){
									matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 6);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min, Y3max);
										intP1.seDirection1(5);
										intP1.setDirection2(6);
										intP1.setShiftX1(-k+intP.getShiftX1());
										intP1.setShiftY1(-l+intP.getShiftY1());
										intP1.setShiftX2(-l1+intP.getShiftX2());
										intP1.setShiftY2(0+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
									dir5list.add(intP1);
									}
								}
						//direction7 for lane3
								for(int k1=0;k1<(Shift2Xdirection7-k);k1++)
									for(int l1=0;l1<(Shift2Ydirection7-l);l1++){
										matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 7);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-l1, Y3max-l1);
											intP1.seDirection1(5);
											intP1.setDirection2(7);
											intP1.setShiftX1(-k+intP.getShiftX1());
											intP1.setShiftY1(-l+intP.getShiftY1());
											intP1.setShiftX2(-k1+intP.getShiftX2());
											intP1.setShiftY2(l1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
									dir5list.add(intP1);
										}
									}
						//direction8 for lane3
								for(int k1=0;k1<(Shift2Ydirection8-l);k1++){
										matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 8);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-k1, Y3max-k1);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
									org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
									//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-k1, Y3max-k1);
									//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-k1, Y3max-k1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min+l, Y2max+l, Y3min-k1, Y3max-k1);
											intP1.seDirection1(5);
											intP1.setDirection2(8);
											intP1.setShiftX1(-k+intP.getShiftX1());
											intP1.setShiftY1(-l+intP.getShiftY1());
											intP1.setShiftX2(0+intP.getShiftX2());
											intP1.setShiftY2(k1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
									dir5list.add(intP1);
										}
									}
						
						//*****
						}
					}
				
				shift1=false;
				shift=false;
			//direction6 for lane2 
				System.out.println("broje elemenata 5 liste je "+dir5list.size());
				
			
				for(int l=0;l<Shift1Xdirection6;l++){
						matrix6=zp.Shifting(matrix2, l, 0, N1, M1, 6);
						shift1=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix6, X1min, X1max, X2min+l, X2max+l, Y1min, Y1max, Y2min+0, Y2max+0);

						if(shift1==false){
							matrix3i=zp.Shifting(matrix3, l, 0, N1, M1, 6);
							//******
							//direction 1 for lane3
						for(int k1=0;k1<(Shift2Xdirection1-l);k1++)
							for(int l1=0;l1<Shift2Ydirection1;l1++){
								matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 1);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+l, X2max+l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
								if(shift==false){
								org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
								//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+l, X2max+l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
								//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
									intP1.seDirection1(6);
									intP1.setDirection2(1);
									intP1.setShiftX1(-l+intP.getShiftX1());
									intP1.setShiftY1(0+intP.getShiftY1());
									intP1.setShiftX2(k1+intP.getShiftX2());
									intP1.setShiftY2(l1+intP.getShiftY2());
								if(intP.getShiftX1()!=0)
								dir6list.add(intP1);
								}
							}
						//direction2 for lane3
						
							for(int l1=0;l1<(Shift2Xdirection2-l);l1++){
								matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 2);
								shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+l, X2max+l, X3min-l1, X3max-l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
								if(shift==false){
									org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
								org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
								//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+l, X2max+l, X3min-l1, X3max-l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
								//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min-l1, X3max-l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
									intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min-l1, X3max-l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
									intP1.seDirection1(6);
									intP1.setDirection2(2);
									intP1.setShiftX1(-l+intP.getShiftX1());
									intP1.setShiftY1(0+intP.getShiftY1());
									intP1.setShiftX2(l1+intP.getShiftX2());
									intP1.setShiftY2(0+intP.getShiftY2());
								if(intP.getShiftX1()!=0)
								dir6list.add(intP1);
								}
							}
						//direction3 for lane3
							for(int k1=0;k1<(Shift2Xdirection3-l);k1++)
								for(int l1=0;l1<Shift2Ydirection3;l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 3);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+l, X2max+l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+l, X2max+l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min-k1, X3max-k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
											intP1.seDirection1(6);
											intP1.setDirection2(3);
											intP1.setShiftX1(-l+intP.getShiftX1());
											intP1.setShiftY1(0+intP.getShiftY1());
											intP1.setShiftX2(k1+intP.getShiftX2());
											intP1.setShiftY2(-l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
										dir6list.add(intP1);
									}
								}
						//direction4 for lane3
							for(int k1=0;k1<Shift2Ydirection4;k1++){
									matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 4);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+l, X2max+l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+k1, Y3max+k1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+l, X2max+l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+k1, Y3max+k1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+k1, Y3max+k1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min+k1, Y3max+k1);
											intP1.seDirection1(6);
											intP1.setDirection2(4);
											intP1.setShiftX1(-l+intP.getShiftX1());
											intP1.setShiftY1(0+intP.getShiftY1());
											intP1.setShiftX2(0+intP.getShiftX2());
											intP1.setShiftY2(-k1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
										dir6list.add(intP1);
									}
								}
						//direction5 for lane3
							for(int k1=0;k1<(Shift2Xdirection5-l);k1++)
								for(int l1=0;l1<Shift2Ydirection5;l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 5);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+l, X2max+l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+l, X2max+l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+l, X2max+l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+l, X2max+l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min+l1, Y3max+l1);
											intP1.seDirection1(6);
											intP1.setDirection2(5);
											intP1.setShiftX1(-l+intP.getShiftX1());
											intP1.setShiftY1(0+intP.getShiftY1());
											intP1.setShiftX2(-k1+intP.getShiftX2());
											intP1.setShiftY2(-l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
										dir6list.add(intP1);
									}
								}
						//direction6 for lane3
						
								for(int l1=0;l1<(Shift2Xdirection6-l);l1++){
									matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 6);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+l, X2max+l, X3min+l1, X3max+l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+l, X2max+l, X3min+l1, X3max+l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min+l1, X3max+l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min+l1, X3max+l1, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max);
											intP1.seDirection1(6);
											intP1.setDirection2(6);
											intP1.setShiftX1(-l+intP.getShiftX1());
											intP1.setShiftY1(0+intP.getShiftY1());
											intP1.setShiftX2(-l1+intP.getShiftX2());
											intP1.setShiftY2(0+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
										dir6list.add(intP1);
									}
								}
						//direction7 for lane3
								for(int k1=0;k1<(Shift2Xdirection7-l);k1++)
									for(int l1=0;l1<Shift2Ydirection7;l1++){
										matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 7);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+l, X2max+l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+l, X2max+l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
												intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min+k1, X3max+k1, Y1min, Y1max, Y2min, Y2max, Y3min-l1, Y3max-l1);
												intP1.seDirection1(6);
												intP1.setDirection2(7);
												intP1.setShiftX1(-l+intP.getShiftX1());
												intP1.setShiftY1(0+intP.getShiftY1());
												intP1.setShiftX2(-k1+intP.getShiftX2());
												intP1.setShiftY2(l1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
											dir6list.add(intP1);
										}
									}
						//direction8 for lane3
								for(int k1=0;k1<Shift2Ydirection8;k1++){
										matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 8);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+l, X2max+l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-k1, Y3max-k1);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+l, X2max+l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-k1, Y3max-k1);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-k1, Y3max-k1);
												intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,X1min, X1max, X2min+l, X2max+l, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min-k1, Y3max-k1);
												intP1.seDirection1(6);
												intP1.setDirection2(8);
												intP1.setShiftX1(-l+intP.getShiftX1());
												intP1.setShiftY1(0+intP.getShiftY1());
												intP1.setShiftX2(0+intP.getShiftX2());
												intP1.setShiftY2(k1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
											dir6list.add(intP1);
										}
									}
						
						//*****
						}
					}
				System.out.println("broje elemenata sete vrste je "+dir6list.size());
					shift1=false;
					shift=false;
			//direction7 for lane2
					for(int k=0;k<Shift1Xdirection7;k++)
						for(int l=0;l<Shift1Ydirection7;l++){
							matrix6=zp.Shifting(matrix2, k, l, N1, M1, 7);
							shift1=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix6, X1min, X1max, X2min+k, X2max+k, Y1min, Y1max, Y2min-l, Y2max-l);

							if(shift1==false){
								matrix3i=zp.Shifting(matrix3, k, l, N1, M1, 7);
								//******
								//direction 1 for lane3
							for(int k1=0;k1<(Shift2Xdirection1-k);k1++)
								for(int l1=0;l1<Shift2Ydirection1-l;l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 1);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
										intP1.seDirection1(7);
										intP1.setDirection2(1);
										intP1.setShiftX1(-k+intP.getShiftX1());
										intP1.setShiftY1(l+intP.getShiftY1());
										intP1.setShiftX2(k1+intP.getShiftX2());
										intP1.setShiftY2(l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
												dir7list.add(intP1);
									}
								}
							//direction2 for lane3
							
								for(int l1=0;l1<Shift2Xdirection2-k;l1++){
									matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 2);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
									if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,  X1min, X1max, X2min+k, X2max+k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,  X1min, X1max, X2min+k, X2max+k, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
										intP1.seDirection1(7);
										intP1.setDirection2(2);
										intP1.setShiftX1(-k+intP.getShiftX1());
										intP1.setShiftY1(l+intP.getShiftY1());
										intP1.setShiftX2(l1+intP.getShiftX2());
										intP1.setShiftY2(0+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
												dir7list.add(intP1);
									}
								}
							//direction3 for lane3
								for(int k1=0;k1<Shift2Xdirection3-k;k1++)
									for(int l1=0;l1<Shift2Ydirection3-l;l1++){
										matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 3);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,  X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,  X1min, X1max, X2min+k, X2max+k, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
											intP1.seDirection1(7);
											intP1.setDirection2(3);
											intP1.setShiftX1(-k+intP.getShiftX1());
											intP1.setShiftY1(l+intP.getShiftY1());
											intP1.setShiftX2(k1+intP.getShiftX2());
											intP1.setShiftY2(-l1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
													dir7list.add(intP1);
										}
									}
							//direction4 for lane3
								for(int k1=0;k1<Shift2Ydirection4-l;k1++){
										matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 4);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+k1, Y3max+k1);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+k1, Y3max+k1);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+k1, Y3max+k1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+k1, Y3max+k1);
											intP1.seDirection1(7);
											intP1.setDirection2(4);
											intP1.setShiftX1(-k+intP.getShiftX1());
											intP1.setShiftY1(l+intP.getShiftY1());
											intP1.setShiftX2(0+intP.getShiftX2());
											intP1.setShiftY2(-k1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
													dir7list.add(intP1);
										}
									}
							//direction5 for lane3
								for(int k1=0;k1<Shift2Xdirection5-k;k1++)
									for(int l1=0;l1<Shift2Ydirection5-l;l1++){
										matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 5);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min+l1, Y3max+l1);
											intP1.seDirection1(7);
											intP1.setDirection2(5);
											intP1.setShiftX1(-k+intP.getShiftX1());
											intP1.setShiftY1(l+intP.getShiftY1());
											intP1.setShiftX2(-k1+intP.getShiftX2());
											intP1.setShiftY2(-l1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
													dir7list.add(intP1);
										}
									}
							//direction6 for lane3
							
									for(int l1=0;l1<Shift2Xdirection6-k;l1++){
										matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 6);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
										if(shift==false){
											org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
											org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1,  X1min, X1max, X2min+k, X2max+k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1,  X1min, X1max, X2min+k, X2max+k, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min, Y3max);
											intP1.seDirection1(7);
											intP1.setDirection2(6);
											intP1.setShiftX1(-k+intP.getShiftX1());
											intP1.setShiftY1(l+intP.getShiftY1());
											intP1.setShiftX2(-l1+intP.getShiftX2());
											intP1.setShiftY2(0+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
													dir7list.add(intP1);
										}
									}
							//direction7 for lane3
									for(int k1=0;k1<Shift2Xdirection7-k;k1++)
										for(int l1=0;l1<Shift2Ydirection7-l;l1++){
											matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 7);
											shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
											if(shift==false){
												org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
												org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
												org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
												//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
												//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
												intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-l1, Y3max-l1);
												intP1.seDirection1(7);
												intP1.setDirection2(7);
												intP1.setShiftX1(-k+intP.getShiftX1());
												intP1.setShiftY1(l+intP.getShiftY1());
												intP1.setShiftX2(-k1+intP.getShiftX2());
												intP1.setShiftY2(l1+intP.getShiftY2());
												if(intP.getShiftX1()!=0)
														dir7list.add(intP1);
											}
										}
							//direction8 for lane3
									for(int k1=0;k1<Shift2Ydirection8-l;k1++){
											matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 8);
											shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-k1, Y3max-k1);
											if(shift==false){
										org.vinfoil.Direction1andDirection2 intP=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Direction1andDirection2 intP1=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
										org.vinfoil.Moving mov2=new org.vinfoil.Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-k1, Y3max-k1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-k1, Y3max-k1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min+k, X2max+k, X3min, X3max, Y1min, Y1max, Y2min-l, Y2max-l, Y3min-k1, Y3max-k1);
										intP1.seDirection1(7);
										intP1.setDirection2(8);
										intP1.setShiftX1(-k+intP.getShiftX1());
										intP1.setShiftY1(l+intP.getShiftY1());
										intP1.setShiftX2(0+intP.getShiftX2());
										intP1.setShiftY2(k1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
												dir7list.add(intP1);
											}
										}
							
							//*****
							}
						}
					System.out.println("broj elemanata sedme vrste je "+dir7list.size());*/
					shift1=false;
					shift=false;
			//direction8 for lane2
					for(int k=0;k<Shift1Ydirection8;k++){
							matrix6=zp.Shifting(matrix2, 0, k, N1, M1, 8);
							shift1=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix6, X1min, X1max, X2min-0, X2max-0, Y1min, Y1max, Y2min-k, Y2max-k);

							if(shift1==false){
								matrix3i=zp.Shifting(matrix3, 0, k, N1, M1, 8);
								//******
								//direction 1 for lane3
							for(int k1=0;k1<Shift2Xdirection1;k1++)
								for(int l1=0;l1<Shift2Ydirection1-k;l1++){
									matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 1);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-l1, Y3max-l1);
									if(shift==false){
										Direction1andDirection2 intP=new Direction1andDirection2(0,0,0,0,0,0);
										Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
										Moving mov2=new Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-l1, Y3max-l1);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-l1, Y3max-l1);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-l1, Y3max-l1);
										intP1.seDirection1(8);
										intP1.setDirection2(1);
											intP1.setShiftX1(0+intP.getShiftX1());
											intP1.setShiftY1(k+intP.getShiftY1());
											intP1.setShiftX2(k1+intP.getShiftX2());
											intP1.setShiftY2(l1+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir8list.add(intP1);
									}
								}
							//direction2 for lane3
							
								for(int l1=0;l1<Shift2Xdirection2;l1++){
									matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 2);
									shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min, Y3max);
									if(shift==false){
										Direction1andDirection2 intP=new Direction1andDirection2(0,0,0,0,0,0);
										Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
										Moving mov2=new Moving ();
										//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min, Y3max);
										//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min, Y3max);
										intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-l1, X3max-l1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min, Y3max);
										intP1.seDirection1(8);
										intP1.setDirection2(2);
											intP1.setShiftX1(0+intP.getShiftX1());
											intP1.setShiftY1(k+intP.getShiftY1());
											intP1.setShiftX2(l1+intP.getShiftX2());
											intP1.setShiftY2(0+intP.getShiftY2());
										if(intP.getShiftX1()!=0)
											dir8list.add(intP1);
									}
								}
							//direction3 for lane3
								for(int k1=0;k1<Shift2Xdirection3;k1++)
									for(int l1=0;l1<Shift2Ydirection3-k;l1++){
										matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 3);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+l1, Y3max+l1);
										if(shift==false){
											Direction1andDirection2 intP=new Direction1andDirection2(0,0,0,0,0,0);
											Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
											Moving mov2=new Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+l1, Y3max+l1);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+l1, Y3max+l1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min-k1, X3max-k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+l1, Y3max+l1);
											intP1.seDirection1(8);
											intP1.setDirection2(3);
												intP1.setShiftX1(0+intP.getShiftX1());
												intP1.setShiftY1(k+intP.getShiftY1());
												intP1.setShiftX2(k1+intP.getShiftX2());
												intP1.setShiftY2(-l1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
												dir8list.add(intP1);
										}
									}
							//direction4 for lane3
								for(int k1=0;k1<Shift2Ydirection4-k;k1++){
										matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 4);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+k1, Y3max+k1);
										if(shift==false){
											Direction1andDirection2 intP=new Direction1andDirection2(0,0,0,0,0,0);
											Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
											Moving mov2=new Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+k1, Y3max+k1);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+k1, Y3max+k1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+k1, Y3max+k1);
											intP1.seDirection1(8);
											intP1.setDirection2(4);
												intP1.setShiftX1(0+intP.getShiftX1());
												intP1.setShiftY1(k+intP.getShiftY1());
												intP1.setShiftX2(0+intP.getShiftX2());
												intP1.setShiftY2(-k1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
												dir8list.add(intP1);
										}
									}
							//direction5 for lane3
								for(int k1=0;k1<Shift2Xdirection5;k1++)
									for(int l1=0;l1<Shift2Ydirection5-k;l1++){
										matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 5);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+l1, Y3max+l1);
										if(shift==false){
											Direction1andDirection2 intP=new Direction1andDirection2(0,0,0,0,0,0);
											Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
											Moving mov2=new Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+l1, Y3max+l1);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+l1, Y3max+l1);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min+l1, Y3max+l1);
											intP1.seDirection1(8);
											intP1.setDirection2(5);
												intP1.setShiftX1(0+intP.getShiftX1());
												intP1.setShiftY1(k+intP.getShiftY1());
												intP1.setShiftX2(-k1+intP.getShiftX2());
												intP1.setShiftY2(-l1+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
												dir8list.add(intP1);
										}
									}
							//direction6 for lane3
							
									for(int l1=0;l1<Shift2Xdirection6;l1++){
										matrix7=zp.Shifting(matrix3i, l1, 0, N1, M1, 6);
										shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min, Y3max);
										if(shift==false){
											Direction1andDirection2 intP=new Direction1andDirection2(0,0,0,0,0,0);
											Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
											Moving mov2=new Moving ();
											//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min, Y3max);
											//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min, Y3max);
											intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+l1, X3max+l1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min, Y3max);
											intP1.seDirection1(8);
											intP1.setDirection2(6);
												intP1.setShiftX1(0+intP.getShiftX1());
												intP1.setShiftY1(k+intP.getShiftY1());
												intP1.setShiftX2(-l1+intP.getShiftX2());
												intP1.setShiftY2(0+intP.getShiftY2());
											if(intP.getShiftX1()!=0)
												dir8list.add(intP1);
										}
									}
							//direction7 for lane3
									for(int k1=0;k1<Shift2Xdirection7;k1++)
										for(int l1=0;l1<Shift2Ydirection7-k;l1++){
											matrix7=zp.Shifting(matrix3i, k1, l1, N1, M1, 7);
											shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-l1, Y3max-l1);
											if(shift==false){
												Direction1andDirection2 intP=new Direction1andDirection2(0,0,0,0,0,0);
												Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
												Moving mov2=new Moving ();
												//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-l1, Y3max-l1);
												//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-l1, Y3max-l1);
												intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min+k1, X3max+k1, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-l1, Y3max-l1);
												intP1.seDirection1(8);
												intP1.setDirection2(7);
													intP1.setShiftX1(0+intP.getShiftX1());
													intP1.setShiftY1(k+intP.getShiftY1());
													intP1.setShiftX2(-k1+intP.getShiftX2());
													intP1.setShiftY2(l1+intP.getShiftY2());
												if(intP.getShiftX1()!=0)
													dir8list.add(intP1);
											}
										}
							//direction8 for lane3
									for(int k1=0;k1<Shift2Ydirection8-k;k1++){
											matrix7=zp.Shifting(matrix3i, 0, k1, N1, M1, 8);
											shift=over.CheckOverlapingOfThreeDiscretizedLanes(matrix1, matrix6, matrix7, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-k1, Y3max-k1);
											if(shift==false){
												Direction1andDirection2 intP=new Direction1andDirection2(0,0,0,0,0,0);
												Direction1andDirection2 intP1=new Direction1andDirection2(0,0,0,0,0,0);
												Moving mov2=new Moving ();
												//intP=mov2.Moving2(matrix1, matrix6, matrix7, N1, M1, ScaningStep, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-k1, Y3max-k1);
												//intP=over.ScaningForEmbeddingThreeLanseFirstHit(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-k1, Y3max-k1);
												intP=mov2.MovingNew(matrix1, matrix6, matrix7, ScaningStep, N1, M1, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min-k, Y2max-k, Y3min-k1, Y3max-k1);
												intP1.seDirection1(8);
												intP1.setDirection2(8);
													intP1.setShiftX1(0+intP.getShiftX1());
													intP1.setShiftY1(k+intP.getShiftY1());
													intP1.setShiftX2(0+intP.getShiftX2());
													intP1.setShiftY2(k1+intP.getShiftY2());
												if(intP.getShiftX1()!=0)
													dir8list.add(intP1);
											}
										}
							
							//*****
							}
						}
					System.out.println("broje elemenata iz 8 liste "+dir8list.size());
				  long estimatedTime = System.nanoTime() - startTime;
					 System.out.println("Execution time in nanosec "+estimatedTime);
					double ext=estimatedTime/1000000000;
					System.out.println("Execution time in sec "+ext);
					int [][] matrix8=new int [N1][M1];
					int [][] matrix9=new int [N1][M1];
					Moving mov=new Moving();
		
					//as a return value we have to elements of holding results 1 one sets the  lane1 in its position second the  lane2
	/*	if(dir1list.size()!=0){
			int s=0;
			boolean t2=false;
			while(s<dir1list.size()&&t2==false){
				
				org.vinfoil.Direction1andDirection2 P=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
				int direction=0;
				P=dir1list.get(s);
				
				matrix8=zp.Shifting(matrix2, (int) (Math.signum(P.getShiftX1())*P.getShiftX1()), (int) (Math.signum(P.getShiftY1())*P.getShiftY1()), N1, M1, P.getDirection1());
				matrix9=zp.Shifting(matrix3, (int)(Math.signum(P.getShiftX2())*P.getShiftX2()), (int) (Math.signum(P.getShiftY2())*P.getShiftY2()), N1, M1, P.getDirection2());
				ArrayList <org.vinfoil.Direction1andDirection2> listOfEmbedding=new ArrayList <org.vinfoil.Direction1andDirection2>();
				direction=P.getDirection2();
				int ShiftX1=0;int ShiftY1=0;
				int ShiftX2=0; int ShiftY2=0;
				if(dir1list.get(s).getShiftX1()!=0){
					ShiftX1=-dir1list.get(s).getShiftX1();
				}
				if(dir1list.get(s).getShiftX2()!=0){
					ShiftX2=-dir1list.get(s).getShiftX2();
				}
				if(dir1list.get(s).getShiftY1()!=0){
					ShiftY1=-dir1list.get(s).getShiftY1();
				}
				if(dir1list.get(s).getShiftY2()!=0){
					ShiftY2=-dir1list.get(s).getShiftY2();
				}
				//templist1=mov.MovingForEmbeding( ScaningStep, N1, M1, matrix1, matrix8, matrix9, X1min, X1max, X2min+ShiftX1, X2max+ShiftX1, X3min+ShiftX2, X3max+ShiftX2, Y1min, Y1max, Y2min+ShiftY1, Y2max+ShiftY1, Y3min+ShiftY2, Y3max+ShiftY2, P);
				templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix8, matrix9, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max, P);
				if(templist1.get(0).getRepassing()==false)
					t2=true;
				if(templist1.get(1).getRepassing()==false)
					t2=true;
				templist.add(templist1.get(0));
				templist.add(templist1.get(1));
				s++;
			}
		}else if(dir2list.size()!=0){
			int s=0;
			boolean t2=false;
			while(s<dir2list.size()&&t2==false){
				
				org.vinfoil.Direction1andDirection2 P=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
				int direction=0;
				P=dir2list.get(s);
				matrix8=zp.Shifting(matrix2, (int) (Math.signum(P.getShiftX1())*P.getShiftX1()), (int) (Math.signum(P.getShiftY1())*P.getShiftY1()), N1, M1, P.getDirection1());
				matrix9=zp.Shifting(matrix3, (int)(Math.signum(P.getShiftX2())*P.getShiftX2()), (int) (Math.signum(P.getShiftY2())*P.getShiftY2()), N1, M1, P.getDirection2());
				ArrayList <org.vinfoil.Direction1andDirection2> listOfEmbedding=new ArrayList <org.vinfoil.Direction1andDirection2>();
				direction=P.getDirection2();
				int ShiftX1=0;int ShiftY1=0;
				int ShiftX2=0; int ShiftY2=0;
				if(dir2list.get(s).getShiftX1()!=0){
					ShiftX1=-dir2list.get(s).getShiftX1();
				}
				if(dir2list.get(s).getShiftX2()!=0){
					ShiftX2=-dir2list.get(s).getShiftX2();
				}
				if(dir2list.get(s).getShiftY1()!=0){
					ShiftY1=-dir2list.get(s).getShiftY1();
				}
				if(dir2list.get(s).getShiftY2()!=0){
					ShiftY2=-dir2list.get(s).getShiftY2();
				}
				//templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix2, matrix3, X1min, X1max, X2min+ShiftX1, X2max+ShiftX1, X3min+ShiftX2, X3max+ShiftX2, Y1min, Y1max, Y2min+ShiftY1, Y2max+ShiftY1, Y3min+ShiftY2, Y3max+ShiftY2, P);
				templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix8, matrix9, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max, P);
				if(templist1.get(0).getRepassing()==false)
					t2=true;
				if(templist1.get(1).getRepassing()==false)
					t2=true;
				templist.add(templist1.get(0));
				templist.add(templist1.get(1));
				s++;
			}
		}else if(dir3list.size()!=0){
			int s=0;
			boolean t2=false;
			while(s<dir3list.size()&&t2==false){
				
				org.vinfoil.Direction1andDirection2 P=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
				int direction=0;
				P=dir3list.get(s);
				matrix8=zp.Shifting(matrix2, (int) (Math.signum(P.getShiftX1())*P.getShiftX1()), (int) (Math.signum(P.getShiftY1())*P.getShiftY1()), N1, M1, P.getDirection1());
				matrix9=zp.Shifting(matrix3, (int)(Math.signum(P.getShiftX2())*P.getShiftX2()), (int) (Math.signum(P.getShiftY2())*P.getShiftY2()), N1, M1, P.getDirection2());
				ArrayList <org.vinfoil.Direction1andDirection2> listOfEmbedding=new ArrayList <org.vinfoil.Direction1andDirection2>();
				direction=P.getDirection2();
				int ShiftX1=0;int ShiftY1=0;
				int ShiftX2=0; int ShiftY2=0;
				if(dir3list.get(s).getShiftX1()!=0){
					ShiftX1=-dir3list.get(s).getShiftX1();
				}
				if(dir3list.get(s).getShiftX2()!=0){
					ShiftX2=-dir3list.get(s).getShiftX2();
				}
				if(dir3list.get(s).getShiftY1()!=0){
					ShiftY1=-dir3list.get(s).getShiftY1();
				}
				if(dir3list.get(s).getShiftY2()!=0){
					ShiftY2=-dir3list.get(s).getShiftY2();
				}
				//templist1=mov.MovingForEmbeding( ScaningStep, N1, M1, matrix1, matrix2, matrix3, X1min, X1max, X2min+ShiftX1, X2max+ShiftX1, X3min+ShiftX2, X3max+ShiftX2, Y1min, Y1max, Y2min+ShiftY1, Y2max+ShiftY1, Y3min+ShiftY2, Y3max+ShiftY2, P);
				templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix8, matrix9, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max, P);
				if(templist1.get(0).getRepassing()==false)
					t2=true;
				if(templist1.get(1).getRepassing()==false)
					t2=true;
				templist.add(templist1.get(0));
				templist.add(templist1.get(1));
				s++;
			}
		}else if(dir4list.size()!=0){
			int s=0;
			boolean t2=false;
			while(s<dir4list.size()&&t2==false){
				
				org.vinfoil.Direction1andDirection2 P=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
				int direction=0;
				P=dir4list.get(s);
				matrix8=zp.Shifting(matrix2, (int) (Math.signum(P.getShiftX1())*P.getShiftX1()), (int) (Math.signum(P.getShiftY1())*P.getShiftY1()), N1, M1, P.getDirection1());
				matrix9=zp.Shifting(matrix3, (int)(Math.signum(P.getShiftX2())*P.getShiftX2()), (int) (Math.signum(P.getShiftY2())*P.getShiftY2()), N1, M1, P.getDirection2());
				ArrayList <org.vinfoil.Direction1andDirection2> listOfEmbedding=new ArrayList <org.vinfoil.Direction1andDirection2>();
				direction=P.getDirection2();
				int ShiftX1=0;int ShiftY1=0;
				int ShiftX2=0; int ShiftY2=0;
				if(dir4list.get(s).getShiftX1()!=0){
					ShiftX1=-dir4list.get(s).getShiftX1();
				}
				if(dir4list.get(s).getShiftX2()!=0){
					ShiftX2=-dir4list.get(s).getShiftX2();
				}
				if(dir4list.get(s).getShiftY1()!=0){
					ShiftY1=-dir4list.get(s).getShiftY1();
				}
				if(dir4list.get(s).getShiftY2()!=0){
					ShiftY2=-dir4list.get(s).getShiftY2();
				}//ovde vec uracunavas shift za dir tako da to ne treba da radis i u mov
				//templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix2, matrix3, X1min, X1max, X2min+ShiftX1, X2max+ShiftX1, X3min+ShiftX2, X3max+ShiftX2, Y1min, Y1max, Y2min+ShiftY1, Y2max+ShiftY1, Y3min+ShiftY2, Y3max+ShiftY2, P);
				templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix8, matrix9, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max, P);
				if(templist1.get(0).getRepassing()==true)
					t2=true;
				if(templist1.get(1).getRepassing()==true)
					t2=true;
				templist.add(templist1.get(0));
				templist.add(templist1.get(1));
				s++;
			}
		}else if(dir5list.size()!=0){
			int s=0;
			boolean t2=false;
			while(s<dir5list.size()&&t2==false){
				
				org.vinfoil.Direction1andDirection2 P=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
				int direction=0;
				P=dir5list.get(s);
				matrix8=zp.Shifting(matrix2, (int) (Math.signum(P.getShiftX1())*P.getShiftX1()), (int) (Math.signum(P.getShiftY1())*P.getShiftY1()), N1, M1, P.getDirection1());
				matrix9=zp.Shifting(matrix3, (int)(Math.signum(P.getShiftX2())*P.getShiftX2()), (int) (Math.signum(P.getShiftY2())*P.getShiftY2()), N1, M1, P.getDirection2());
				ArrayList <org.vinfoil.Direction1andDirection2> listOfEmbedding=new ArrayList <org.vinfoil.Direction1andDirection2>();
				direction=P.getDirection2();
				int ShiftX1=0;int ShiftY1=0;
				int ShiftX2=0; int ShiftY2=0;
				if(dir5list.get(s).getShiftX1()!=0){
					ShiftX1=-dir5list.get(s).getShiftX1();
				}
				if(dir5list.get(s).getShiftX2()!=0){
					ShiftX2=-dir5list.get(s).getShiftX2();
				}
				if(dir5list.get(s).getShiftY1()!=0){
					ShiftY1=-dir5list.get(s).getShiftY1();
				}
				if(dir5list.get(s).getShiftY2()!=0){
					ShiftY2=-dir5list.get(s).getShiftY2();
				}
				//templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix2, matrix3, X1min, X1max, X2min+ShiftX1, X2max+ShiftX1, X3min+ShiftX2, X3max+ShiftX2, Y1min, Y1max, Y2min+ShiftY1, Y2max+ShiftY1, Y3min+ShiftY2, Y3max+ShiftY2, P);
				templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix8, matrix9, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max, P);
				if(templist1.get(0).getRepassing()==false)
					t2=true;
				if(templist1.get(1).getRepassing()==false)
					t2=true;
				templist.add(templist1.get(0));
				templist.add(templist1.get(1));
				s++;
			}
			
		}else if(dir6list.size()!=0){
			int s=0;
			boolean t2=false;
			while(s<dir6list.size()&&t2==false){
				
				org.vinfoil.Direction1andDirection2 P=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
				int direction=0;
				P=dir6list.get(s);
				matrix8=zp.Shifting(matrix2, (int) (Math.signum(P.getShiftX1())*P.getShiftX1()), (int) (Math.signum(P.getShiftY1())*P.getShiftY1()), N1, M1, P.getDirection1());
				matrix9=zp.Shifting(matrix3, (int)(Math.signum(P.getShiftX2())*P.getShiftX2()), (int) (Math.signum(P.getShiftY2())*P.getShiftY2()), N1, M1, P.getDirection2());
				ArrayList <org.vinfoil.Direction1andDirection2> listOfEmbedding=new ArrayList <org.vinfoil.Direction1andDirection2>();
				direction=P.getDirection2();
				int ShiftX1=0;int ShiftY1=0;
				int ShiftX2=0; int ShiftY2=0;
				if(dir6list.get(s).getShiftX1()!=0){
					ShiftX1=-dir6list.get(s).getShiftX1();
				}
				if(dir6list.get(s).getShiftX2()!=0){
					ShiftX2=-dir6list.get(s).getShiftX2();
				}
				if(dir6list.get(s).getShiftY1()!=0){
					ShiftY1=-dir6list.get(s).getShiftY1();
				}
				if(dir6list.get(s).getShiftY2()!=0){
					ShiftY2=-dir6list.get(s).getShiftY2();
				}
				//templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix2, matrix3, X1min, X1max, X2min+ShiftX1, X2max+ShiftX1, X3min+ShiftX2, X3max+ShiftX2, Y1min, Y1max, Y2min+ShiftY1, Y2max+ShiftY1, Y3min+ShiftY2, Y3max+ShiftY2, P);
				templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix8, matrix9, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max, P);
				if(templist1.get(0).getRepassing()==false)
					t2=true;
				if(templist1.get(1).getRepassing()==false)
					t2=true;
				templist.add(templist1.get(0));
				templist.add(templist1.get(1));
				s++;
			}
		}else if(dir7list.size()!=0){
			int s=0;
			boolean t2=false;
			while(s<dir7list.size()&&t2==false){
				
				org.vinfoil.Direction1andDirection2 P=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
				int direction=0;
				P=dir7list.get(s);
				matrix8=zp.Shifting(matrix2, (int) (Math.signum(P.getShiftX1())*P.getShiftX1()), (int) (Math.signum(P.getShiftY1())*P.getShiftY1()), N1, M1, P.getDirection1());
				matrix9=zp.Shifting(matrix3, (int)(Math.signum(P.getShiftX2())*P.getShiftX2()), (int) (Math.signum(P.getShiftY2())*P.getShiftY2()), N1, M1, P.getDirection2());
				ArrayList <org.vinfoil.Direction1andDirection2> listOfEmbedding=new ArrayList <org.vinfoil.Direction1andDirection2>();
				direction=P.getDirection2();
				int ShiftX1=0;int ShiftY1=0;
				int ShiftX2=0; int ShiftY2=0;
				if(dir7list.get(s).getShiftX1()!=0){
					ShiftX1=-dir7list.get(s).getShiftX1();
				}
				if(dir7list.get(s).getShiftX2()!=0){
					ShiftX2=-dir7list.get(s).getShiftX2();
				}
				if(dir7list.get(s).getShiftY1()!=0){
					ShiftY1=-dir7list.get(s).getShiftY1();
				}
				if(dir7list.get(s).getShiftY2()!=0){
					ShiftY2=-dir7list.get(s).getShiftY2();
				}
				//templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix2, matrix3, X1min, X1max, X2min+ShiftX1, X2max+ShiftX1, X3min+ShiftX2, X3max+ShiftX2, Y1min, Y1max, Y2min+ShiftY1, Y2max+ShiftY1, Y3min+ShiftY2, Y3max+ShiftY2, P);
				templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix8, matrix9, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max, P);
				if(templist1.get(0).getRepassing()==false)
					t2=true;
				if(templist1.get(1).getRepassing()==false)
					t2=true;
				templist.add(templist1.get(0));
				templist.add(templist1.get(1));
				s++;
			}
		}else if(dir8list.size()!=0){
			int s=0;
			boolean t2=false;
			while(s<dir8list.size()&&t2==false){
				
				org.vinfoil.Direction1andDirection2 P=new org.vinfoil.Direction1andDirection2(0,0,0,0,0,0);
				int direction=0;
				P=dir8list.get(s);
				matrix8=zp.Shifting(matrix2, (int) (Math.signum(P.getShiftX1())*P.getShiftX1()), (int) (Math.signum(P.getShiftY1())*P.getShiftY1()), N1, M1, P.getDirection1());
				matrix9=zp.Shifting(matrix3, (int)(Math.signum(P.getShiftX2())*P.getShiftX2()), (int) (Math.signum(P.getShiftY2())*P.getShiftY2()), N1, M1, P.getDirection2());
				ArrayList <org.vinfoil.Direction1andDirection2> listOfEmbedding=new ArrayList <org.vinfoil.Direction1andDirection2>();
				direction=P.getDirection2();
				int ShiftX1=0;int ShiftY1=0;
				int ShiftX2=0; int ShiftY2=0;
				if(dir8list.get(s).getShiftX1()!=0){
					ShiftX1=-dir8list.get(s).getShiftX1();
				}
				if(dir8list.get(s).getShiftX2()!=0){
					ShiftX2=-dir8list.get(s).getShiftX2();
				}
				if(dir8list.get(s).getShiftY1()!=0){
					ShiftY1=-dir8list.get(s).getShiftY1();
				}
				if(dir8list.get(s).getShiftY2()!=0){
					ShiftY2=-dir8list.get(s).getShiftY2();
				}
				//templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix2, matrix3, X1min, X1max, X2min+ShiftX1, X2max+ShiftX1, X3min+ShiftX2, X3max+ShiftX2, Y1min, Y1max, Y2min+ShiftY1, Y2max+ShiftY1, Y3min+ShiftY2, Y3max+ShiftY2, P);
				templist1=mov.MovingForEmbeding(ScaningStep, N1, M1, matrix1, matrix8, matrix9, X1min, X1max, X2min, X2max, X3min, X3max, Y1min, Y1max, Y2min, Y2max, Y3min, Y3max, P);
				if(templist1.get(0).getRepassing()==false)
					t2=true;
				if(templist1.get(1).getRepassing()==false)
					t2=true;
				templist.add(templist1.get(0));
				templist.add(templist1.get(1));
				s++;
			}
		}*/
					int y=0;
					
					while(y<dir4list.size()){
						HoldingResult g1=new HoldingResult();
						HoldingResult g2=new HoldingResult();
						g1.setDirection(dir4list.get(y).getDirection1());
						g2.setDirection(dir4list.get(y).getDirection2());
						if(g1.getDirection()!=0)
							g1.setRepassing(true);
						else g1.setRepassing(false);
						if(g2.getDirection()!=0)
							g2.setRepassing(true);
						else g2.setRepassing(false);
						g1.setShiftX(dir4list.get(y).getShiftX1());
						g2.setShiftX(dir4list.get(y).getShiftX2());
						g1.setShiftY(dir4list.get(y).getShiftY1());
						g2.setShiftY(dir4list.get(y).getShiftY2());
						templist.add(g1);
						templist.add(g2);
					}
					return templist;	//it can have more than 2 elements, every odd is for the first direction every even is for the second				
}
public ArrayList <HoldingResult> Repassing4(int [][] matrix1, int [][] matrix2, int N1, int M1, int ScaningStep, int X1min, int X1max, int X2min, int X2max, int Y1min, int Y1max, int Y2min, int Y2max){//lane1 is fixed, lane2 is the shifting one
	
	ArrayList <HoldingResult> templist=new ArrayList <HoldingResult> ();
	HoldingResult temp=new HoldingResult();
	temp.setRepassing(false);
	temp.setDirection(0);
	temp.setShiftX(0);
	temp.setShiftY(0);
	
	
	int ShiftXdirection1=X2min;
	int ShiftXdirection2=X2min;
	int ShiftXdirection3=X2min;
	int ShiftXdirection4=0;
	int ShiftXdirection5= (N1-X2max);
	int ShiftXdirection6= (N1-X2max);
	int ShiftXdirection7= (N1-X2max);
	int ShiftXdirection8=0;
	int ShiftYdirection1=Y2min;
	int ShiftYdirection2=0;
	int ShiftYdirection3=M1-Y2max;
	int ShiftYdirection4=M1-Y2max;
	int ShiftYdirection5=M1-Y2max;
	int ShiftYdirection6=0;
	int ShiftYdirection7=Y2min;
	int ShiftYdirection8=Y2min;
	//two matrices for discretization
	int Xmin=X2min;
	int Xmax=X2max;
	int Ymin=Y2min; int Ymax=Y2max;
	boolean t=false;
	//setting matrices to same dimension


	ArrayList <IntegerPoint> dir1list=new ArrayList <IntegerPoint>();
	ArrayList <IntegerPoint> dir2list=new ArrayList <IntegerPoint>();
	ArrayList <IntegerPoint> dir3list=new ArrayList <IntegerPoint>();
	ArrayList <IntegerPoint> dir4list=new ArrayList <IntegerPoint>();
	ArrayList <IntegerPoint> dir5list=new ArrayList <IntegerPoint>();
	ArrayList <IntegerPoint> dir6list=new ArrayList <IntegerPoint>();
	ArrayList <IntegerPoint> dir7list=new ArrayList <IntegerPoint>();
	ArrayList <IntegerPoint> dir8list=new ArrayList <IntegerPoint>();
	int [][] matrix4=new int [N1][M1];	
	int [][] matrix5=new int [N1][M1];
	int [][] matrix3=new int [N1][M1];	
	matrix3=matrix2;
	ZeroPadding zp=new ZeroPadding();
	Overlaping over=new Overlaping ();
	boolean shift=false;
	boolean scan=true;
	
	if(t==true){//using matrix3
		//direction1
		for(int k=0;k<ShiftXdirection1;k++)
			for(int l=0;l<ShiftYdirection1;l++){
				matrix4=zp.Shifting(matrix3, k, l, N1, M1, 1);
				//shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-k, Xmax-k, Y1min, Y1max, Ymin-l, Ymax-l);
				shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, X2min-k, X2max-k, Y1min, Y1max, Y2min-l, Y2max-l);
				if(shift==false){
					IntegerPoint intP=new IntegerPoint();
					intP.setX(k);
					intP.setY(l);
					dir1list.add(intP);
				}
			}
		//direction2
		
			for(int l=0;l<ShiftXdirection2;l++){
				matrix4=zp.Shifting(matrix3, l, 0, N1, M1, 2);
				//shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-0, Xmax-0, Y1min, Y1max, Ymin-l, Ymax-l);
				shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, X2min-l, X2max-l, Y1min, Y1max, Y2min-0, Y2max-0);

				if(shift==false){
					IntegerPoint intP=new IntegerPoint();
					intP.setX(l);
					intP.setY(0);
					dir2list.add(intP);
				}
			}
		//direction3
			for(int k=0;k<ShiftXdirection3;k++)
				for(int l=0;l<ShiftYdirection3;l++){
					matrix4=zp.Shifting(matrix3, k, l, N1, M1, 3);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, X2min-k, X2max-k, Y1min, Y1max, Y2min+l, Y2max+l);

					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(k);
						intP.setY(l);
						dir3list.add(intP);
					}
				}
		//direction4
			for(int k=0;k<ShiftYdirection4;k++){
					matrix4=zp.Shifting(matrix3, 0, k, N1, M1, 4);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+0, Xmax+0, Y1min, Y1max, Ymin+k, Ymax+k);

					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(0);
						intP.setY(k);
						dir4list.add(intP);
					}
				}
		//direction5
			for(int k=0;k<ShiftXdirection5;k++)
				for(int l=0;l<ShiftYdirection5;l++){
					matrix4=zp.Shifting(matrix3, k, l, N1, M1, 5);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+k, Xmax+k, Y1min, Y1max, Ymin+l, Ymax+l);

					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(k);
						intP.setY(l);
						dir5list.add(intP);
					}
				}
		//direction6
		
				for(int l=0;l<ShiftXdirection6;l++){
					matrix4=zp.Shifting(matrix3, l, 0, N1, M1, 6);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+l, Xmax+l, Y1min, Y1max, Ymin+0, Ymax+0);

					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(l);
						intP.setY(0);
						dir6list.add(intP);
					}
				}
		//direction7
				for(int k=0;k<ShiftXdirection7;k++)
					for(int l=0;l<ShiftYdirection7;l++){
						matrix4=zp.Shifting(matrix3, k, l, N1, M1, 7);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+k, Xmax+k, Y1min, Y1max, Ymin-l, Ymax-l);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(k);
							intP.setY(l);
							dir7list.add(intP);
						}
					}
		//direction8
				for(int k=0;k<ShiftYdirection8;k++){
						matrix4=zp.Shifting(matrix3, 0, k, N1, M1, 8);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-0, Xmax-0, Y1min, Y1max, Ymin-k, Ymax-k);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(0);
							intP.setY(k);
							dir8list.add(intP);
						}
					}
				
				//we are now going to scan for 20mm embedding 
				if(dir1list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir1list.size()){
						matrix5=zp.Shifting(matrix3, dir1list.get(s).getX(), dir1list.get(s).getY(), N1, M1, 1);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir1list.get(s).getX(), Xmax-dir1list.get(s).getX(), Y1min, Y1max, Ymin-dir1list.get(s).getY(), Ymax-dir1list.get(s).getY());
						if(listOfEmbedding.get(0).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(1);
							temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
							temp.setShiftX(dir1list.get(s).getY()+ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(1);
								temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY());
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX());	
									temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY());
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX());	
									temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}else if(dir2list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir2list.size()){
						matrix5=zp.Shifting(matrix3, dir2list.get(s).getX(), dir2list.get(s).getY(), N1, M1, 2);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir2list.get(s).getX(), Xmax-dir2list.get(s).getX(), Y1min, Y1max, Ymin, Ymax);
						if(listOfEmbedding.get(1).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(2);
							temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
							temp.setShiftX(dir2list.get(s).getY());	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(2);
								temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY());
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX());	
									temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY());
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX());	
									temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
					
				}else if(dir3list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir3list.size()){
						matrix5=zp.Shifting(matrix3, dir3list.get(s).getX(), dir3list.get(s).getY(), N1, M1, 3);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir3list.get(s).getX(), Xmax-dir3list.get(s).getX(), Y1min, Y1max, Ymin+dir3list.get(s).getY(), Ymax+dir3list.get(s).getY());
						if(listOfEmbedding.get(2).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(3);
							temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
							temp.setShiftX(dir3list.get(s).getY()-ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(3);
								temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY());
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX());	
									temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY());
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX());	
									temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}else if(dir4list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir4list.size()){
						matrix5=zp.Shifting(matrix3, dir4list.get(s).getX(), dir4list.get(s).getY(), N1, M1, 4);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin, Xmax, Y1min, Y1max, Ymin+dir4list.get(s).getY(), Ymax+dir4list.get(s).getY());
						if(listOfEmbedding.get(3).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(dir4list.get(s).getX());	
							temp.setShiftX(dir4list.get(s).getY()-ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(4);
								temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY());
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX());	
									temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY());
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX());	
									temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}	
				}else if(dir5list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir5list.size()){
						matrix5=zp.Shifting(matrix3, dir5list.get(s).getX(), dir5list.get(s).getY(), N1, M1, 5);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir5list.get(s).getX(), Xmax+dir5list.get(s).getX(), Y1min, Y1max, Ymin+dir5list.get(s).getY(), Ymax+dir5list.get(s).getY());
						if(listOfEmbedding.get(4).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(5);
							temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
							temp.setShiftX(dir5list.get(s).getY()-ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(5);
								temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY());
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX());	
									temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY());
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX());	
									temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}else if(dir6list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir6list.size()){
						matrix5=zp.Shifting(matrix3, dir6list.get(s).getX(), dir6list.get(s).getY(), N1, M1, 6);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir6list.get(s).getX(), Xmax+dir6list.get(s).getX(), Y1min, Y1max, Ymin, Ymax);
						if(listOfEmbedding.get(5).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(6);
							temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
							temp.setShiftX(dir6list.get(s).getY());	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(6);
								temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY());
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX());	
									temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY());
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX());	
									temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}else if(dir7list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir7list.size()){
						matrix5=zp.Shifting(matrix3, dir7list.get(s).getX(), dir7list.get(s).getY(), N1, M1, 7);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir7list.get(s).getX(), Xmax+dir7list.get(s).getX(), Y1min, Y1max, Ymin-dir7list.get(s).getY(), Ymax-dir7list.get(s).getY());
						if(listOfEmbedding.get(6).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(7);
							temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
							temp.setShiftX(dir7list.get(s).getY()+ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(7);
								temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY());
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX());	
									temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY());
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX());	
									temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}else if(dir8list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir8list.size()){
						matrix5=zp.Shifting(matrix3, dir8list.get(s).getX(), dir8list.get(s).getY(), N1, M1, 8);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin, Xmax, Y1min, Y1max, Ymin-dir8list.get(s).getY(), Ymax-dir8list.get(s).getY());
						if(listOfEmbedding.get(7).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(8);
							temp.setShiftX(dir8list.get(s).getX());	
							temp.setShiftX(dir8list.get(s).getY()+ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(8);
								temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY());
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX());	
									temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY());
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX());	
									temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}
				
				/*if(dir1list.size()!=0){
					int s=0;
					while(scan==true&&s<dir1list.size()){
						matrix5=zp.Shifting(matrix3, dir1list.get(s).getX(), dir1list.get(s).getY(), N1, M1, 1);
						//moras da izvrsis proveru da li je odgovarjuce embeding moguce tjs da ali ako se shiftujes za 20
						//u bilo kom prvacu ti ne izlaze patterni sa lane, obavezno ovde jer samo ovde vidim shiftove!!!!
						scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
						if(scan==false){
							temp.setRepassing(true);
							temp.setDirection(1);
							if(dir1list.get(s).getX()==0)
							temp.setShiftX(dir1list.get(s).getX());
							else
								temp.setShiftX(dir1list.get(s).getX()+20);	
							if(dir1list.get(s).getY()==0)
							temp.setShiftY(dir1list.get(s).getY());
							else 
								temp.setShiftY(dir1list.get(s).getY()+20);
						}
					}
					
				}else
				if(dir2list.size()!=0){
					int s=0;
					while(scan==true&&s<dir2list.size()){
						matrix5=zp.Shifting(matrix3, dir2list.get(s).getX(), dir2list.get(s).getY(), N1, M1, 1);
						scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
						if(scan==false){
							temp.setRepassing(true);
							temp.setDirection(2);
							temp.setShiftX(dir2list.get(s).getX()+20);
							temp.setShiftY(dir2list.get(s).getY());
						}
					}
				}else
				if(dir3list.size()!=0){
					int s=0;	
					while(scan==true&&s<dir3list.size()){
						matrix5=zp.Shifting(matrix3, dir3list.get(s).getX(), dir3list.get(s).getY(), N1, M1, 1);
						scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
						if(scan==false){
							temp.setRepassing(true);
							temp.setDirection(3);
							if(dir3list.get(s).getX()==0)
								temp.setShiftX(dir3list.get(s).getX());
								else
									temp.setShiftX(dir3list.get(s).getX()+20);	
								if(dir3list.get(s).getY()==0)
								temp.setShiftY(dir3list.get(s).getY());
								else 
									temp.setShiftY(dir3list.get(s).getY()+20);
						}
					}
				}else
				if(dir4list.size()!=0){
					int s=0;
					while(scan==true&&s<dir4list.size()){
						matrix5=zp.Shifting(matrix3, dir4list.get(s).getX(), dir4list.get(s).getY(), N1, M1, 1);
						scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
						if(scan==false){
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(dir4list.get(s).getX());
							temp.setShiftY(dir4list.get(s).getY()+20);
						}
					}
				}else
				if(dir5list.size()!=0){
					int s=0;
					while(scan==true&&s<dir5list.size()){
						matrix5=zp.Shifting(matrix3, dir5list.get(s).getX(), dir5list.get(s).getY(), N1, M1, 1);
						scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
						if(scan==false){
							temp.setRepassing(true);
							temp.setDirection(5);
							if(dir5list.get(s).getX()==0)
								temp.setShiftX(dir5list.get(s).getX());
								else
									temp.setShiftX(dir5list.get(s).getX()+20);	
								if(dir5list.get(s).getY()==0)
								temp.setShiftY(dir5list.get(s).getY());
								else 
									temp.setShiftY(dir5list.get(s).getY()+20);
						}
					}
				}else
				if(dir6list.size()!=0){
					int s=0;
					while(scan==true&&s<dir6list.size()){
						matrix5=zp.Shifting(matrix3, dir6list.get(s).getX(), dir6list.get(s).getY(), N1, M1, 1);
						scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
						if(scan==false){
							temp.setRepassing(true);
							temp.setDirection(6);
							temp.setShiftX(dir6list.get(s).getX());
							temp.setShiftY(dir6list.get(s).getY()+20);
						}
					}
				}else
				if(dir7list.size()!=0){
					int s=0;
					while(scan==true&&s<dir7list.size()){
						matrix5=zp.Shifting(matrix3, dir7list.get(s).getX(), dir7list.get(s).getY(), N1, M1, 1);
						scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
						if(scan==false){
							temp.setRepassing(true);
							temp.setDirection(7);
							if(dir7list.get(s).getX()==0)
								temp.setShiftX(dir7list.get(s).getX());
								else
									temp.setShiftX(dir7list.get(s).getX()+20);	
								if(dir7list.get(s).getY()==0)
								temp.setShiftY(dir7list.get(s).getY());
								else 
									temp.setShiftY(dir7list.get(s).getY()+20);
						}
					}
				}else
				if(dir8list.size()!=0){
					int s=0;
					while(scan==true&&s<dir8list.size()){
						matrix5=zp.Shifting(matrix3, dir8list.get(s).getX(), dir8list.get(s).getY(), N1, M1, 1);
						scan=over.ScanForEmbeding(matrix1, matrix5, ScaningStep, N1, M1, N1, M1,X1min, X1max, Y1min, Y1max);
						if(scan==false){
							temp.setRepassing(true);
							temp.setDirection(8);
							temp.setShiftX(dir8list.get(s).getX());
							temp.setShiftY(dir8list.get(s).getY()+20);
						}
					}
				}*/
				
	}else{//using matrix2
		//direction1
		//direction1
		for(int k=0;k<ShiftXdirection1;k++)
			for(int l=0;l<ShiftYdirection1;l++){
				matrix4=zp.Shifting(matrix2, k, l, N1, M1, 1);
				//shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-k, Xmax-k, Y1min, Y1max, Ymin-l, Ymax-l);
				shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-k, Xmax-k, Y1min, Y1max, Ymin-l, Ymax-l);
				if(shift==false){
					IntegerPoint intP=new IntegerPoint();
					intP.setX(k);
					intP.setY(l);
					dir1list.add(intP);
				}
			}
		//direction2
		
			for(int l=0;l<ShiftXdirection2;l++){
				matrix4=zp.Shifting(matrix2, l, 0, N1, M1, 2);
				//shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-0, Xmax-0, Y1min, Y1max, Ymin-l, Ymax-l);
				shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-l, Xmax-l, Y1min, Y1max, Ymin-0, Ymax-0);

				if(shift==false){
					IntegerPoint intP=new IntegerPoint();
					intP.setX(l);
					intP.setY(0);
					dir2list.add(intP);
				}
			}
		//direction3
			for(int k=0;k<ShiftXdirection3;k++)
				for(int l=0;l<ShiftYdirection3;l++){
					matrix4=zp.Shifting(matrix2, k, l, N1, M1, 3);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-k, Xmax-k, Y1min, Y1max, Ymin+l, Ymax+l);

					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(k);
						intP.setY(l);
						dir3list.add(intP);
					}
				}
		//direction4
			for(int k=0;k<ShiftYdirection4;k++){
					matrix4=zp.Shifting(matrix2, 0, k, N1, M1, 4);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+0, Xmax+0, Y1min, Y1max, Ymin+k, Ymax+k);

					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(0);
						intP.setY(k);
						dir4list.add(intP);
					}
				}
		//direction5
			for(int k=0;k<ShiftXdirection5;k++)
				for(int l=0;l<ShiftYdirection5;l++){
					matrix4=zp.Shifting(matrix2, k, l, N1, M1, 5);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+k, Xmax+k, Y1min, Y1max, Ymin+l, Ymax+l);

					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(k);
						intP.setY(l);
						dir5list.add(intP);
					}
				}
		//direction6
		
				for(int l=0;l<ShiftXdirection6;l++){
					matrix4=zp.Shifting(matrix2, l, 0, N1, M1, 6);
					shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+l, Xmax+l, Y1min, Y1max, Ymin+0, Ymax+0);

					if(shift==false){
						IntegerPoint intP=new IntegerPoint();
						intP.setX(l);
						intP.setY(0);
						dir6list.add(intP);
					}
				}
		//direction7
				for(int k=0;k<ShiftXdirection7;k++)
					for(int l=0;l<ShiftYdirection7;l++){
						matrix4=zp.Shifting(matrix2, k, l, N1, M1, 7);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin+k, Xmax+k, Y1min, Y1max, Ymin-l, Ymax-l);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(k);
							intP.setY(l);
							dir7list.add(intP);
						}
					}
		//direction8
				for(int k=0;k<ShiftYdirection8;k++){
						matrix4=zp.Shifting(matrix2, 0, k, N1, M1, 8);
						shift=over.CheckOverlapingOfTwoDiscretizedLanes(matrix1, matrix4, X1min, X1max, Xmin-0, Xmax-0, Y1min, Y1max, Ymin-k, Ymax-k);

						if(shift==false){
							IntegerPoint intP=new IntegerPoint();
							intP.setX(0);
							intP.setY(k);
							dir8list.add(intP);
						}
					}
				//we are now going to scan for 20mm embeding 
				
				
				if(dir1list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir1list.size()){
						matrix5=zp.Shifting(matrix2, dir1list.get(s).getX(), dir1list.get(s).getY(), N1, M1, 1);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir1list.get(s).getX(), Xmax-dir1list.get(s).getX(), Y1min, Y1max, Ymin-dir1list.get(s).getY(), Ymax-dir1list.get(s).getY());
						if(listOfEmbedding.get(0).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(1);
							temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
							temp.setShiftX(dir1list.get(s).getY()+ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(1);
								temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
								templist.add(temp);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX());	
									temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(1);
									temp.setShiftX(dir1list.get(s).getX());	
									temp.setShiftX(dir1list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}else if(dir2list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir2list.size()){
						matrix5=zp.Shifting(matrix2, dir2list.get(s).getX(), dir2list.get(s).getY(), N1, M1, 2);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir2list.get(s).getX(), Xmax-dir2list.get(s).getX(), Y1min, Y1max, Ymin, Ymax);
						if(listOfEmbedding.get(1).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(2);
							temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
							temp.setShiftX(dir2list.get(s).getY());	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(1).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(2);
								temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
								templist.add(temp);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX());	
									temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(2);
									temp.setShiftX(dir2list.get(s).getX());	
									temp.setShiftX(dir2list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
					
				}else if(dir3list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir3list.size()&&temp.getRepassing()==false){
						matrix5=zp.Shifting(matrix2, dir3list.get(s).getX(), dir3list.get(s).getY(), N1, M1, 3);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin-dir3list.get(s).getX(), Xmax-dir3list.get(s).getX(), Y1min, Y1max, Ymin+dir3list.get(s).getY(), Ymax+dir3list.get(s).getY());
						if(listOfEmbedding.get(2).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(3);
							temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
							temp.setShiftX(dir3list.get(s).getY()-ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(3);
								temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
								templist.add(temp);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX());	
									temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(3);
									temp.setShiftX(dir3list.get(s).getX());	
									temp.setShiftX(dir3list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}else if(dir4list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir4list.size()){
						matrix5=zp.Shifting(matrix2, dir4list.get(s).getX(), dir4list.get(s).getY(), N1, M1, 4);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin, Xmax, Y1min, Y1max, Ymin+dir4list.get(s).getY(), Ymax+dir4list.get(s).getY());
						if(listOfEmbedding.get(3).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(4);
							temp.setShiftX(dir4list.get(s).getX());	
							temp.setShiftX(dir4list.get(s).getY()-ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(4);
								temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
								templist.add(temp);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX());	
									temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(4);
									temp.setShiftX(dir4list.get(s).getX());	
									temp.setShiftX(dir4list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}	
				}else if(dir5list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir5list.size()){
						matrix5=zp.Shifting(matrix2, dir5list.get(s).getX(), dir5list.get(s).getY(), N1, M1, 5);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir5list.get(s).getX(), Xmax+dir5list.get(s).getX(), Y1min, Y1max, Ymin+dir5list.get(s).getY(), Ymax+dir5list.get(s).getY());
						if(listOfEmbedding.get(4).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(5);
							temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
							temp.setShiftX(dir5list.get(s).getY()-ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(5);
								temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
								templist.add(temp);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX());	
									temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(5);
									temp.setShiftX(dir5list.get(s).getX());	
									temp.setShiftX(dir5list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}else if(dir6list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir6list.size()){
						matrix5=zp.Shifting(matrix2, dir6list.get(s).getX(), dir6list.get(s).getY(), N1, M1, 6);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir6list.get(s).getX(), Xmax+dir6list.get(s).getX(), Y1min, Y1max, Ymin, Ymax);
						if(listOfEmbedding.get(5).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(6);
							temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
							temp.setShiftX(dir6list.get(s).getY());	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(6);
								temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
								templist.add(temp);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX());	
									temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(6);
									temp.setShiftX(dir6list.get(s).getX());	
									temp.setShiftX(dir6list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}else if(dir7list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir7list.size()){
						matrix5=zp.Shifting(matrix2, dir7list.get(s).getX(), dir7list.get(s).getY(), N1, M1, 7);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin+dir7list.get(s).getX(), Xmax+dir7list.get(s).getX(), Y1min, Y1max, Ymin-dir7list.get(s).getY(), Ymax-dir7list.get(s).getY());
						if(listOfEmbedding.get(6).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(7);
							temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
							temp.setShiftX(dir7list.get(s).getY()+ScaningStep);	
							templist.add(temp);
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(7);
								temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
								templist.add(temp);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX());	
									temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(7);
									temp.setShiftX(dir7list.get(s).getX());	
									temp.setShiftX(dir7list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}else if(dir8list.size()!=0){
					int s=0;
					int s1=0;
					while(s<dir8list.size()){
						matrix5=zp.Shifting(matrix2, dir8list.get(s).getX(), dir8list.get(s).getY(), N1, M1, 8);
						ArrayList <Point> listOfEmbedding=new ArrayList <Point> ();
						listOfEmbedding=over.ScaningForEmbedingWithTwoLanes(matrix1, matrix5, ScaningStep, N1, M1, X1min, X1max, Xmin, Xmax, Y1min, Y1max, Ymin-dir8list.get(s).getY(), Ymax-dir8list.get(s).getY());
						if(listOfEmbedding.get(7).getX()==1){
							temp.setRepassing(true);
							temp.setDirection(8);
							temp.setShiftX(dir8list.get(s).getX());	
							temp.setShiftX(dir8list.get(s).getY()+ScaningStep);	
						}else{
						while(s1<listOfEmbedding.size()&&temp.getRepassing()==false){
							switch (s1+1){
							case 1:
								if(listOfEmbedding.get(0).getX()==1){
								temp.setRepassing(true);
								temp.setDirection(8);
								temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
								temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
								templist.add(temp);
								}
								break;
							case 2:
								if(listOfEmbedding.get(1).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 3:
								if(listOfEmbedding.get(2).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()+ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 4:
								if(listOfEmbedding.get(3).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX());	
									temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 5:
								if(listOfEmbedding.get(4).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY()-ScaningStep);
									templist.add(temp);
									}
								break;
							case 6:
								if(listOfEmbedding.get(5).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY());
									templist.add(temp);
									}
								break;
							case 7:
								if(listOfEmbedding.get(6).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX()-ScaningStep);	
									temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;
							case 8:
								if(listOfEmbedding.get(7).getX()==1){
									temp.setRepassing(true);
									temp.setDirection(8);
									temp.setShiftX(dir8list.get(s).getX());	
									temp.setShiftX(dir8list.get(s).getY()+ScaningStep);
									templist.add(temp);
									}
								break;

							}	
							s1++;
						}
						}
						s++;
					}
				}
				
				
	}
	
	System.out.println("dirction 1 "+dir1list.size());
	System.out.println("dirction 2 "+dir2list.size());
	System.out.println("dirction 3 "+dir3list.size());
	System.out.println("dirction 4 "+dir4list.size());
	System.out.println("dirction 5 "+dir5list.size());
	System.out.println("dirction 6 "+dir6list.size());
	System.out.println("dirction 7 "+dir7list.size());
	System.out.println("dirction 8 "+dir8list.size());
	
	return templist;
}
public ArrayList <HoldingResult> Repassing3(FWL lane1, FWL lane2, FWL lane3, Patterns Shape, int ScaningStep){
	
	long startTime = System.nanoTime();
	HoldingResult temp=new HoldingResult();
	HoldingResult temp1=new HoldingResult();
	ArrayList <HoldingResult> templist=new ArrayList <HoldingResult> ();
	ArrayList <HoldingResult> templist1=new ArrayList <HoldingResult> ();
	ArrayList <HoldingResult> templist2=new ArrayList <HoldingResult> ();
	temp.setRepassing(false);
	temp.setDirection(0);
	temp.setShiftX(0);
	temp.setShiftY(0);
	
	SetOfPatternsForALane patternslane1=new SetOfPatternsForALane();
	SetOfPatternsForALane patternslane2=new SetOfPatternsForALane();
	SetOfPatternsForALane patternslane3=new SetOfPatternsForALane();
	MinAndMax templane1= new MinAndMax();
	MinAndMax templane2= new MinAndMax();
	MinAndMax templane3= new MinAndMax();
	
	Patterns nesto=patternslane2.PatternsForOneLane(Shape, lane2);
	Patterns nesto1=patternslane3.PatternsForOneLane(Shape, lane3);
	int X2min=(int) ( templane2.TheXminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2))-lane2.getStart());
	int X2max=(int) ( templane2.TheXmaxOfAllPatterns(nesto)-lane2.getStart());
	int Y2min=(int) ( templane2.TheYminOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)));
	int Y2max=(int) ( templane2.TheYmaxOfAllPatterns(patternslane2.PatternsForOneLane(Shape, lane2)));
	int X1min=(int) ( templane1.TheXminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1))-lane1.getStart());
	int X1max=(int) ( templane1.TheXmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1))-lane1.getStart());
	int Y1min=(int) ( templane1.TheYminOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)));
	int Y1max=(int) ( templane1.TheYmaxOfAllPatterns(patternslane1.PatternsForOneLane(Shape, lane1)));
	int X3min=(int) (templane3.TheXminOfAllPatterns(patternslane3.PatternsForOneLane(Shape, lane3))-lane3.getStart());
	int X3max=(int) ( templane3.TheXmaxOfAllPatterns(nesto1)-lane3.getStart());
	int Y3min=(int) ( templane3.TheYminOfAllPatterns(patternslane3.PatternsForOneLane(Shape, lane3)));
	int Y3max=(int) ( templane3.TheYmaxOfAllPatterns(patternslane3.PatternsForOneLane(Shape, lane3)));
	int CPC=(int) lane1.getLength();
	
	int Shift1Xdirection1=X2min;
	int Shift1Xdirection2=X2min;
	int Shift1Xdirection3=X2min;
	int Shift1Xdirection4=0;
	int Shift1Xdirection5=(int) (lane1.getEnd()-lane1.getStart()-X2max);
	int Shift1Xdirection6=(int) (lane1.getEnd()-lane1.getStart()-X2max);
	int Shift1Xdirection7=(int) (lane1.getEnd()-lane1.getStart()-X2max);
	int Shift1Xdirection8=0;
	int Shift1Ydirection1=Y2min;
	int Shift1Ydirection2=0;
	int Shift1Ydirection3=CPC-Y2max;
	int Shift1Ydirection4=CPC-Y2max;
	int Shift1Ydirection5=CPC-Y2max;
	int Shift1Ydirection6=0;
	int Shift1Ydirection7=Y2min;
	int Shift1Ydirection8=Y2min;
	int Shift2Xdirection1=X3min;
	int Shift2Xdirection2=X3min;
	int Shift2Xdirection3=X3min;
	int Shift2Xdirection4=0;
	int Shift2Xdirection5=(int) (lane1.getEnd()-lane1.getStart()-X3max);
	int Shift2Xdirection6=(int) (lane1.getEnd()-lane1.getStart()-X3max);
	int Shift2Xdirection7=(int) (lane1.getEnd()-lane1.getStart()-X3max);
	int Shift2Xdirection8=0;
	int Shift2Ydirection1=Y3min;
	int Shift2Ydirection2=0;
	int Shift2Ydirection3=CPC-Y3max;
	int Shift2Ydirection4=CPC-Y3max;
	int Shift2Ydirection5=CPC-Y3max;
	int Shift2Ydirection6=0;
	int Shift2Ydirection7=Y3min;
	int Shift2Ydirection8=Y3min;
	//two matrices for discretization
	Discretization disc=new Discretization ();	
	int N1=(int) lane1.getWidth();
	int M1=(int) lane1.getLength();
	int N2=(int) lane2.getWidth();
	int M2=(int) lane2.getLength();
	int N3=(int) lane3.getWidth();
	int M3=(int) lane3.getLength();
	
	int [][] matrix1=new int [N1][M1];
	int [][] matrix2=new int [N2][M2];
	int [][] matrix3=new int [N1][M2];
	int [][] matrix4=new int [N1][M1];	
	int [][] matrix5=new int [N1][M1];	
	int [][] matrix6=new int [N1][M1];
	int [][] matrix7=new int [N1][M1];
	int [][] matrix3i=new int [N1][M2];
	matrix1=disc.DiscretizationOfLane(lane1, Shape);//discrete lane1
	matrix2=disc.DiscretizationOfLane(lane2, Shape);//discrete lane2, from now on we only work with discrete ones
	matrix3=disc.DiscretizationOfLane(lane3, Shape);//discrete lane3
	boolean t=false;
	boolean t1=false;
	//setting matrices to same dimension
	 if(lane1.getWidth()>lane2.getWidth()){
		t=true;
		for(int j=0;j<M1;j++){
			for(int i=0;i<N2;i++){
				matrix4[i][j]=matrix2[i][j];
			}
			for(int i=N2;i<N1;i++){
				matrix4[i][j]=0;
			}
		}
	}
	 if(t==true){
		 matrix2=matrix4;
	 }
	 if(lane1.getWidth()>lane3.getWidth()){
			t1=true;
			for(int j=0;j<M1;j++){
				for(int i=0;i<N3;i++){
					matrix5[i][j]=matrix2[i][j];
				}
				for(int i=N3;i<N1;i++){
					matrix5[i][j]=0;
				}
			}
		}
	 if(t1==true){
		 matrix3=matrix5;
	 }


	DirectionList DIRList=new DirectionList();
	
	ZeroPadding zp=new ZeroPadding();
	Overlaping over=new Overlaping ();
	boolean shift1=false;
	boolean shift=false;
	boolean scan=true;
	templist1=this.Repassing(lane1, lane2, Shape, ScaningStep);
	int s=0;
	while(s<templist1.size()&&(t=false)){//e pa ne moze ovako ova petlja
		matrix6=zp.Shifting(matrix2, templist1.get(s).getShiftX(), templist1.get(s).getShiftY(), N1, M1, templist1.get(s).getDirection());
		for(int i=0;i<N1;i++)
			for(int j=0;j<M1;j++){
				if(matrix1[i][j]==1||matrix6[i][j]==1){
					matrix7[i][j]=1;
				}else{
					matrix7[i][j]=0;
				}
				int X1min1=X1min; int Y1min1=Y1min;
				int X1max1=X1max; int Y1max1=Y1min;
				//matrica 7 je kombinacija resenja ne preklapanja dve lane, sada hocu da ubodem trecu ali moram prvo da pronadjem min i max
				for(int i1=0;i1<N1;i1++)
					for(int j1=0;j1<M1;j1++){
						if(matrix7[i1][j1]==1){
							X1min1=Math.min(X1min1, i);
							X1max1=Math.max(X1max1, i);
							Y1min1=Math.min(Y1min1, j);
							Y1max1=Math.max(Y1max1, j);
							}
					}
				templist=this.Repassing4(matrix7, matrix3, N1, M1, ScaningStep, X1min1, X1max1, X2min, X2max, Y1min1, Y1max1, Y2min, Y2max);
				if(templist.size()!=0)
					t=true;
			}
		
	}
	return templist;
}
}
