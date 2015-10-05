package org.vinfoil;

import java.util.*;

public class Extending {
	
	

	public Extending(){
		
	}
	//t should be true at the beginning 
	public static ArrayList <FWL> ExtendingAll(ArrayList <FWL> lanes, float extension,float offset, float paperwidth, boolean t){
		
		ArrayList <FWL> a=new ArrayList <FWL> ();
		FWL b=new FWL ();
		FWL f=new FWL ();
		FWL h=new FWL ();
		float [] c = null;
		c= new float [20];
		float e=0,d=0,g=0,ex=0,pw=0, exl=0,rex=0,m=0;
		int j=0,k=0,j1=0;
		 
		ex=extension;
		pw=paperwidth;
	
		//there is no exception for one lane
		if(lanes.size()==1){
			b=lanes.get(0);
			m=b.getStart()+paperwidth-b.getEnd();
			if(m<ex){
				a=null;//no extension possible ALLERT!!!!
			System.out.println("Extension is not possible, please insert the correct parameters!");
			}
			else{
				if(b.getStart()-offset<ex/2){//beginning of the paper
					f.setStart(offset);
					f.setEnd(ex-b.getStart()+offset+b.getEnd());
					f.setWidth(f.getEnd()-f.getStart());
					f.setLength(b.getLength());
					a.add(f);
				}else if(paperwidth-b.getEnd()<ex/2){
					f.setEnd(paperwidth);
					f.setStart(b.getStart()-ex+paperwidth);
					f.setWidth(f.getEnd()-f.getStart());
					f.setLength(b.getLength());
					a.add(f);
				}else{
					f.setStart(Math.round(ex/2));
					f.setEnd(ex-Math.round(ex/2)+b.getEnd());
					f.setWidth(f.getEnd()-f.getStart());
					f.setLength(b.getLength());
					a.add(f);
				}
			}
				
				
		return a;
		}else{
		
		//is extension possible, calculate all the distances between the lanes
		for(int i=0;i< lanes.size()-1;i++){
			b=lanes.get(i);
			f=lanes.get(i+1);
			e=f.getStart()-b.getEnd();
			c[i]=e;//creating the array of all distances between the lane
			d=Math.max(d, e);//calculating the max distance
			g+=e;//calculating the sum of all distances
		}
		//dodajem jos rastojanje u odnosu na pocetak i kraj
		j=lanes.size()-1;//promenice se; e pa nece; ukupan broj rastojanja izmedju lane
		b=lanes.get(0);
		c[j]=b.getStart()-offset;//adding the distance between the origin and first lane
		d=Math.max(d, b.getStart()-offset);
		g=g+b.getStart();
		k=lanes.size();//broj lane
		
		exl=extension/k;//extension for each lane
		rex=Math.round(exl/2);//rounded extension for each side of the lane
		
		f=lanes.get(k-1);
		c[j+1]=paperwidth-f.getEnd();//adding the distance between the last lane and the end of the paper
		d=Math.max(d, paperwidth-f.getEnd());
		g=g+paperwidth-f.getEnd();
		//nalazim mesto gde imama najvece rastojanje
		for(int i=0;i<=j+2;i++){
			if(d==c[i])
				j1=i;
		}//index of the max distance 
		//da li mogu da extendujem 3 mogucnosti
		if(g<extension+(k-1)*5){//extending is not possible need to ALERT!!!
			a=null;
			System.out.println("Extension is not possible, please insert the correct parameters!");
		return a;	
		}
		else if(g==extension+(k-1)*5){//extending is possible but the foil is almost the same as the paper
			//treba da doradis ovaj deo za celobrojno deljenje!!!!!DORADJENO
			b.setStart(offset);
			f=lanes.get(0);
			b.setEnd(f.getEnd()+exl-Math.round(exl/2));
			a.add(b);
			for(int i=1;i<lanes.size();i++){
				b=lanes.get(i);
				f.setStart(b.getStart()-exl/2);
				f.setEnd(b.getEnd()+exl-Math.round(exl/2));
				f.setWidth(f.getEnd()-f.getStart());
				f.setLength(b.getLength());
				a.add(f);
			}
			b=lanes.get(k-1);
			f.setStart(b.getStart()-exl/2);
			f.setEnd(paperwidth);
			f.setWidth(f.getEnd()-f.getStart());
			f.setLength(b.getLength());
			a.add(f);
			return a;
		}
		else{//g>extension+(k-1)*5, extending is possible
			//checking if all distances are <=extension for a lane +5
			if(j==0){//exception when we have only one lane; ovo nije exception
			if(c[0]<exl/2)
				t=false;
			else if(c[1]<exl/2)
				t=false;
			}
			else{
			for(int i=0;i<=j;i++){
				if(c[i]<exl+5)
					t=false;
			}
			if(c[j]<exl/2)
				t=false;
			if(c[j+1]<exl/2)
				t=false;
			}
			//sada znamo da li mozemo ravnomerno da rasporedimo extension ili ne postoje 2 mogucnosti
			if(t==true){
				
				if(j1==j){//first distance is the max
					b=lanes.get(0);
					b.setStart(b.getStart()-(extension-(2*k-1)*rex));
					b.setEnd(b.getEnd()+rex);
					b.setWidth(b.getEnd()-b.getStart());
					a.add(b);
					for(int i=1;i<lanes.size();i++){
						b=lanes.get(i);
						b.setStart(b.getStart()-rex);
						b.setEnd(b.getEnd()+rex);
						b.setWidth(b.getEnd()-b.getStart());
						a.add(b);
					}

					
				}
				else if(j1==j+1){//last distance is the max
					b=lanes.get(k-1);
					b.setStart(b.getStart()-rex);
					b.setEnd(b.getEnd()+(extension-(2*k-1)*rex));
					b.setWidth(b.getEnd()-b.getStart());
					a.add(b);
					for(int i=0;i<lanes.size()-1;i++){
						b=lanes.get(i);
						b.setStart(b.getStart()-rex);
						b.setEnd(b.getEnd()+rex);
						b.setWidth(b.getEnd()-b.getStart());
						a.add(b);
					}

					
				}
				else{
					for(int i=0;i<lanes.size();i++){
						b=lanes.get(i);	
						if(i==j1){
							b.setStart(b.getStart()-rex);
							b.setEnd(b.getEnd()+(extension-(2*k-1)*rex));
							b.setWidth(b.getEnd()-b.getStart());
							a.add(b);
						}
						else{
							
							b.setStart(b.getStart()-rex);
							b.setEnd(b.getEnd()+rex);
							b.setWidth(b.getEnd()-b.getStart());
							a.add(b);
						}
					}
					
				}
				return a;
				
			}
			else{//ne mozemo ravnomerno da rasporedimo moramo da merge i da pozovemo rekurentno
				//find the lanes that can not be extended
				a=lanes;
				if(c[j]<exl/2){
					b=a.get(0);
					b.setStart(offset);
					b.setWidth(b.getEnd()-b.getStart());
					a.remove(0);
					a.add(0, b);
				}
				if(c[j+1]<exl/2){
					b=a.get(k-1);
					b.setEnd(paperwidth);
					b.setWidth(b.getEnd()-b.getStart());
					a.remove(k-1);
					a.add(k-1, b);
				}
				for(int i=0;i<=j-1;i++){
					if(c[i]<exl+5){
						b=a.get(i);
						f=a.get(i+1);
						a.remove(i+1);
						a.remove(i);
						
						h=h.MergingOfTwoLanes(b, f);
						a.add(h);
						
					}
					
						
				}
				return ExtendingAll(a,ex,pw,offset,true);
			}
			
			
		}
		}
		
	}//method ExtendingAll ends here!
	
	//this method is intended for extending without merging, so the output is true if extending is possible 
	//with respect to the constraint that distance between lanes is 5mm 
	public boolean CheckIfLaneCanBeExtended(ArrayList <FWL> lanes,FWL lane, float paperwidth, float offset){
		boolean t=true;
		float a=0,b=0,extension=0;
		float []c;
		int j=0,k=0;
		FWL d,e=new FWL();
		
		c=new float[20];
		k=lanes.size()-1;//last index of lanes
		extension=100-lane.getWidth();
		
		for(int i=0;i<lanes.size();i++){
			d=lanes.get(i);
			if(i!=lanes.size()-1){
				e=lanes.get(i+1);
				c[i]=e.getStart()-d.getEnd();
			}
			if(d.getStart()==lane.getStart()&&d.getEnd()==lane.getEnd())
				j=i;//index of lane we are extending		
		}
		d=lanes.get(0);
		c[k]=d.getStart()-offset;
		e=lanes.get(k);
		c[k+1]=paperwidth-e.getEnd();
		//one lane only
		if(lanes.size()==1){
			d=lanes.get(0);
			if(d.getStart()-offset+paperwidth-d.getEnd()<extension)
				t=false;
			else if(d.getStart()-offset<extension/2){
				if(paperwidth-d.getEnd()<extension-d.getStart())
					t=false;
				else
					t=true;
			}else if(paperwidth-d.getEnd()<extension/2){
				if(d.getStart()-offset<extension-paperwidth+d.getEnd())
					t=false;
				else
					t=true;
			}else{
				t=true;
			}
		}//one lane exception finished
		else{
		if(j==0){//first lane
			
			if(c[0]+c[k]<extension+5)
				t=false;
			else if(c[k]<extension/2){
				if(c[0]<extension-c[k]+5)
					t=false;
				else
					t=true;
			}else if(c[0]<extension/2+5){
				if(c[k]<extension-c[0]-5)
					t=false;
				else 
					t=true;
			}else t=true;
			
		}else if(j==k){//last lane
			if(c[k+1]+c[k-1]<extension+5)
				t=false;
			else if(c[k+1]<extension/2){
				if(c[k-1]<extension-c[k+1]+5)
					t=false;
				else
					t=true;
			}else if(c[k-1]<extension/2+5){
				if(c[k+1]<extension-c[k-1]-5)
					t=false;
				else
					t=true;
			}else t=true;
		}else{//some lane in between
			if(c[j-1]+c[j]<extension+10)
				t=false;
			else t=true;
		}
		}
		
		return t;
	}
	//it is understood once you call this procedure that you have checked if extension is possible ALERT that!!!
	//extension is enabled with respect to 5mm constraint
	//the extension when the extension is predefined, it is in use for rounding lanes to 10mm resolution
	public ArrayList <FWL> ExtendingOneLane1 (ArrayList <FWL> lanes, FWL lane, float paperwidth,float offset, float exten){
		
		ArrayList <FWL> a=new ArrayList <FWL> ();
		int j=-1,k;
		float e=0,f=0,extension;
		float [] g;
		FWL b,c,d=new FWL();
		extension=exten;
		g=new float[20];
		k=lanes.size()-1;//the last index of all lanes
	
		
		//System.out.println("pocetak prve lane "+lanes.get(0).Start);
		for(int i=0;i<lanes.size();i++){
			b=lanes.get(i);
			if(i!=lanes.size()-1){
				c=lanes.get(i+1);
				g[i]=c.getStart()-b.getEnd();
			}
			if(b.getStart()==lane.getStart()&&b.getEnd()==lane.getEnd())
				j=i;//index of lane we are extending		
		}
		b=lanes.get(0);
		g[k]=b.getStart()-offset;
		c=lanes.get(k);
		g[k+1]=paperwidth-c.getEnd();
		
		//exception for one lane only 
		if(lanes.size()==1){
			//System.out.println("unutra samo jedna ");
			 if(g[k]<extension/2){
				b=lanes.get(0);
				b.setEnd(b.getEnd()+extension-b.getStart());
				b.setStart(offset);
				b.setWidth(b.getEnd()-b.getStart());
				a.add(b);
				
			}else if(g[k+1]<extension/2){
				b=lanes.get(0);
				b.setStart(b.getStart()-extension+b.getEnd());
				b.setWidth(b.getEnd()-b.getStart());
				a.add(b);
				
			}else{
				b=lanes.get(0);
				b.setStart(b.getStart()-Math.round(extension/2));
				b.setEnd(b.getEnd()+extension-Math.round(extension/2));
				b.setWidth(b.getEnd()-b.getStart());
				a.add(b);
				
			}
			// a=lanes;
		}//izuzetak za samo jedan lane napisan :P
		else{
			a=lanes;
			
		if(j==0){//first lane needs to be extended 
			//System.out.println("unutra prva lane");
			b=lanes.get(0);
			c=lanes.get(1);
			
			 if(g[k]<extension/2){
				b.setEnd(b.getEnd()+extension-b.getStart()+offset);
				b.setStart(offset);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(0);
				a.add(0, b);
			}else if(g[0]<extension/2+5){
				
				b.setStart(b.getStart()-extension+g[0]-5);
				b.setEnd(c.getStart()-5);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(0);
				a.add(0, b);
				
			}else{
				b.setStart(b.getStart()-extension+Math.round(extension/2));
				b.setEnd(b.getEnd()+Math.round(extension/2));
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(0);
				a.add(0, b);
			}
			
		}
		else if(j==lanes.size()-1){//the last lane needs to be extended
			//System.out.println("unutra poslednja lane");
			b=lanes.get(k);
			c=lanes.get(k-1);
			
			if(g[k+1]<extension/2){
				//System.out.println("unutra poslednja lane 1");
				b.setStart(b.getStart()-extension+g[k+1]);
				b.setEnd(paperwidth);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(k);
				a.add(k, b);
			}else if(g[k-1]<extension/2+5){
				//System.out.println("unutra poslednja lane 2");
				b.setStart(c.getEnd()+5);
				b.setEnd(b.getEnd()+extension-g[k-1]+5);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(k);
				a.add(k, b);
			}else{
				//System.out.println("unutra poslednja lane 3");
				b.setStart(b.getStart()-Math.round(extension/2));
				b.setEnd(b.getEnd()+extension-Math.round(extension/2));
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(k);
				a.add(k, b);
			}
		}
		else{//not the first not the last, in between lane needs to be extended 
			//System.out.println("unutra nije ni prva ni poslednja");
			b=lanes.get(j);
			c=lanes.get(j-1);
			d=lanes.get(j+1);
			
			if(g[j-1]<extension/2+5){
				b.setEnd(b.getEnd()+extension-g[j-1]+5);
				b.setStart(c.getEnd()+5);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(j);
				a.add(j, b);	
			}else if(g[j]<extension/2+5){
				b.setStart(b.getStart()-extension+g[j]-5);
				b.setEnd(d.getStart()-5);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(j);
				a.add(j, b);
			}else{
				b.setStart(b.getStart()-Math.round(extension/2));
				b.setEnd(b.getEnd()+extension-Math.round(extension/2));
				b.setWidth(b.getEnd()-b.getStart());
				
				a.remove(j);
				a.add(j, b);
			}
			
		}
		}
	
		
		
		return a;
		
	}
	
	//this is checking the extension when the extension is predefined, it is in use for rounding lanes to 10mm resolution
	public boolean CheckIfLaneCanBeExtended2(ArrayList <FWL> lanes,FWL lane, float paperwidth,float offset, float exten){
		boolean t=true;
		float a=0,b=0;
		float []c;
		int j=0,k=0;
		FWL d,e=new FWL();
		float extension=0;
		c=new float[20];
		k=lanes.size()-1;//the last index of the lanes
		extension=exten;
		
		for(int i=0;i<lanes.size();i++){
			d=lanes.get(i);
			if(i!=lanes.size()-1){
				e=lanes.get(i+1);
				c[i]=e.getStart()-d.getEnd();
			}
			if(d.getStart()==lane.getStart()&&d.getEnd()==lane.getEnd())
				j=i;//the index of the lane we are extending		
		}
		d=lanes.get(0);
		c[k]=d.getStart()-offset;
		e=lanes.get(k);
		c[k+1]=paperwidth-e.getEnd();
		//one lane only
		if(lanes.size()==1){
			d=lanes.get(0);
			if(d.getStart()-offset+paperwidth-d.getEnd()<extension)
				t=false;
			else if(d.getStart()-offset<extension/2){
				if(paperwidth-d.getEnd()<extension-d.getStart())
					t=false;
				else
					t=true;
			}else if(paperwidth-d.getEnd()<extension/2){
				if(d.getStart()-offset<extension-paperwidth+d.getEnd())
					t=false;
				else
					t=true;
			}else{
				t=true;
			}
		}
		else{
		if(j==0){//first lane
			
			if(c[0]+c[k]<extension+5)
				t=false;
			else if(c[k]<extension/2){
				if(c[0]<extension-c[k]+5)
					t=false;
				else
					t=true;
			}else if(c[0]<extension/2+5){
				if(c[k]<extension-c[0]-5)
					t=false;
				else 
					t=true;
			}else t=true;
			
		}else if(j==k){//last lane
			if(c[k+1]+c[k-1]<extension+5)
				t=false;
			else if(c[k+1]<extension/2){
				if(c[k-1]<extension-c[k+1]+5)
					t=false;
				else
					t=true;
			}else if(c[k-1]<extension/2+5){
				if(c[k+1]<extension-c[k-1]-5)
					t=false;
				else
					t=true;
			}else t=true;
		}else{//some lane in between
			if(c[j-1]+c[j]<extension+10)
				t=false;
			else t=true;
		}
		}
		
		return t;
	}
	
	//it is understood once you call this procedure that you have checked if extension is possible ALERT that!!!
		//extension is enabled with respect to 5mm constraint
	//this extension of one lane to 100mm
public ArrayList <FWL> ExtendingOneLane (ArrayList <FWL> lanes, FWL lane, float paperwidth, float offset){
		
		ArrayList <FWL> a=new ArrayList <FWL> ();
		int j=-1,k;
		float e=0,f=0, extension;
		float [] g;
		FWL b,c,d=new FWL();
		
		g=new float[20];
		k=lanes.size()-1;//last index lanes
		extension=100-lane.getWidth();
		
		//System.out.println("pocetak prve lane "+lanes.get(0).Start);
		for(int i=0;i<lanes.size();i++){
			b=lanes.get(i);
			if(i!=lanes.size()-1){
				c=lanes.get(i+1);
				g[i]=c.getStart()-b.getEnd();
			}
			if(b.getStart()==lane.getStart()&&b.getEnd()==lane.getEnd())
				j=i;//index of the lane we are extending		
		}
		b=lanes.get(0);
		g[k]=b.getStart()-offset;
		c=lanes.get(k);
		g[k+1]=paperwidth-c.getEnd();
		
		//one lane only
		if(lanes.size()==1){
			//System.out.println("unutra samo jedna ");
			 if(g[k]<extension/2){
				b=lanes.get(0);
				b.setEnd(b.getEnd()+extension-b.getStart()+offset);
				b.setStart(offset);
				b.setWidth(b.getEnd()-b.getStart());
				a.add(b);
				
			}else if(g[k+1]<extension/2){
				b=lanes.get(0);
				b.setStart(b.getStart()-extension+b.getStart());
				b.setWidth(b.getEnd()-b.getStart());
				a.add(b);
				
			}else{
				b=lanes.get(0);
				b.setStart(b.getStart()-Math.round(extension/2));
				b.setEnd(b.getEnd()+extension-Math.round(extension/2));
				b.setWidth(b.getEnd()-b.getStart());
				a.add(b);
				
			}
			// a=lanes;
		}//izuzetak za samo jedan lane napisan :P
		else{
			a=lanes;
		if(j==0){//extending first lane
			//System.out.println("unutra prva lane");
			b=lanes.get(0);
			c=lanes.get(1);
			
			 if(g[k]<extension/2){
				b.setEnd(b.getEnd()+extension-b.getStart()+offset);
				b.setStart(offset);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(0);
				a.add(0, b);
			}else if(g[0]<extension/2+5){
				
				b.setStart(b.getStart()-extension+g[0]-5);
				b.setEnd(c.getStart()-5);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(0);
				a.add(0, b);
				
			}else{
				b.setStart(b.getStart()-extension+Math.round(extension/2));
				b.setEnd(b.getEnd()+Math.round(extension/2));
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(0);
				a.add(0, b);
			}
			
		}
		else if(j==lanes.size()-1){//extending last lane
			//System.out.println("unutra poslednja lane");//poslednja lane treba da se extenduje
			b=lanes.get(k);
			c=lanes.get(k-1);
			
			if(g[k+1]<extension/2){
				//System.out.println("unutra poslednja lane 1");
				b.setStart(b.getStart()-extension+g[k+1]);
				b.setEnd(paperwidth);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(k);
				a.add(k, b);
			}else if(g[k-1]<extension/2+5){
				//System.out.println("unutra poslednja lane 2");
				b.setStart(c.getEnd()+5);
				b.setEnd(b.getEnd()+extension-g[k-1]+5);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(k);
				a.add(k, b);
			}else{
				//System.out.println("unutra poslednja lane 3");
				b.setStart(b.getStart()-Math.round(extension/2));
				b.setEnd(b.getEnd()+extension-Math.round(extension/2));
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(k);
				a.add(k, b);
			}
		}
		else{//some lane in between
			//System.out.println("unutra nije ni prva ni poslednja");
			b=lanes.get(j);
			c=lanes.get(j-1);
			d=lanes.get(j+1);
			
			if(g[j-1]<extension/2+5){
				b.setEnd(b.getEnd()+extension-g[j-1]+5);
				b.setStart(c.getEnd()+5);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(j);
				a.add(j, b);	
			}else if(g[j]<extension/2+5){
				b.setStart(b.getStart()-extension+g[j]-5);
				b.setEnd(d.getStart()-5);
				b.setWidth(b.getEnd()-b.getStart());
				a.remove(j);
				a.add(j, b);
			}else{
				b.setStart(b.getStart()-Math.round(extension/2));
				b.setEnd(b.getEnd()+extension-Math.round(extension/2));
				b.setWidth(b.getEnd()-b.getStart());
				
				a.remove(j);
				a.add(j, b);
			}
			
		}
		}
		
		
		return a;
		
	}
//all lanes have already been rounded to 10mm 
	//ovo koristim kad zaokruzujem initial foil roll
	public ArrayList <FWL> ExtendingAll1(ArrayList <FWL> lanes, int extension, float paperwidth, float offset){
		ArrayList <FWL> a=new ArrayList <FWL> ();
		ArrayList <Float> distance=new ArrayList <Float> ();
		ArrayList <Boolean> possibleExtension=new ArrayList <Boolean> ();
		FWL temp1=new FWL ();
		FWL temp2=new FWL ();
		FWL temp3=new FWL ();
		int numLanes=0; int mod10 = 0,numOfExtendableLanes=0;
		int possCounter=0;
		float sum=0;
		
		temp1=lanes.get(0);
		distance.add(temp1.getStart()-offset);//distance bewtween origin and the first lane and the origin is not i (0,0) it is offset
		sum=temp1.getStart()-offset;

		
		for(int i=0;i<lanes.size()-1;i++){
			temp1=lanes.get(i);
			temp2=lanes.get(i+1);
			distance.add(i+1,temp2.getStart()-temp1.getEnd());
			sum=sum+temp2.getStart()-temp1.getEnd();
			
			
		}
		numLanes=lanes.size();
		temp1=lanes.get(numLanes-1);
		distance.add(paperwidth-temp1.getEnd());//all distances
		//System.out.println("num of lanes "+numLanes);
		//System.out.println("num of distances "+distance.size());
		sum+=paperwidth-temp1.getEnd();
		
		
		for(int i=0;i<lanes.size();i++){
			if(i==0){//first lane
				if(distance.get(0)+distance.get(1)<15)
					possibleExtension.add(0, false);
				else {
					possibleExtension.add(0, true);
					possCounter++;
				}
			}else if(i==lanes.size()-1){//last lane
				if(possibleExtension.get(i-1)==true){
					if(distance.get(i)+distance.get(i+1)<20)
						possibleExtension.add(i,false);
					else {
						possibleExtension.add(i, true);
						possCounter++;
					}
				}else {
				if(distance.get(i)+distance.get(i+1)<15)
					possibleExtension.add(i,false);
				else {
					possibleExtension.add(i, true);
					possCounter++;
				}
				
				}
			}else{//in between
				if(possibleExtension.get(i-1)==true){
					if(distance.get(i)+distance.get(i+1)<25)
						possibleExtension.add(i, false);
					else{
						possibleExtension.add(i, true);
						possCounter++;
					}
				}else{
					if(distance.get(i)+distance.get(i+1)<20)
						possibleExtension.add(i, false);
					else{
						possibleExtension.add(i, true);
						possCounter++;
					}
					
				}
				
			}
		}
		//for(int i=0;i<possibleExtension.size();i++)
			//System.out.println("info of possible extents is "+possibleExtension.get(i));
		
		mod10=extension/10;
		a=lanes;
		if(lanes.size()==1){//one lane exception
			if(extension%10!=0){
				System.out.println("The required extension is not in 10mm resolution, please insert correct parameters");
			}else{
				temp1=lanes.get(0);
				if(temp1.getStart()-offset+paperwidth-temp1.getEnd()<extension){
					System.out.println("The required extension is not possible, please insert correct parameters");
				}else{
					
					if(temp1.getStart()-offset<extension/2){
						temp1.setEnd(temp1.getEnd()+extension-temp1.getStart()+offset);
						temp1.setStart(offset);
						temp1.setWidth(temp1.getEnd()-temp1.getStart());
						a.add(temp1);
						
					}else if(paperwidth-temp1.getEnd()<extension/2){
						temp1.setStart(temp1.getStart()-extension+paperwidth-temp1.getEnd());
						temp1.setEnd(paperwidth);
						temp1.setWidth(temp1.getEnd()-temp1.getStart());
						a.add(temp1);
						
					}else{
						temp1.setStart(temp1.getStart()-extension/2);
						temp1.setEnd(temp1.getEnd()+extension/2);
						temp1.setWidth(temp1.getEnd()-temp1.getStart());
						a.add(temp1);
						
					}
				}
				
			}
		}
		else{
			if(extension%10!=0){
				System.out.println("The required extension is not in 10mm resolution, please insert correct parameters");
			}else if((sum-(numLanes-1)*5)<extension){
				System.out.println("The required extension is not possible or it is not possible to round it to 10mm resolution, please insert correct parameters");
			}
			else{//now we claim that extension is possible for the +10mm resolution in one iteration 
		if(mod10>numLanes){//broj lane je manji od mod10, znaci nekoliko lane mora da ima extenziju vecu 10mm; 
			//some lanes need bigger extension then +10mm
			if(numLanes==possCounter){//sve lane mogu da se extenduju +10; all lanes can have +10mm extension
				for(int i=0;i<a.size();i++){
					temp1=a.get(i);
					if(i==0){
						if(distance.get(0)<=5){//left side
							temp1.setStart(offset);
							temp1.setEnd(temp1.getEnd()+10-distance.get(0));
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							float s=distance.get(0);
							float x=distance.get(1);
							distance.remove(1);
							distance.add(1, x-10+s);
							distance.remove(0);
							distance.add(0, (float) 0);
						}else if(distance.get(1)<=10){//right side
							temp1.setStart(temp1.getStart()-15+distance.get(1));
							temp1.setEnd(temp1.getEnd()+distance.get(1)-5);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							float s=distance.get(0);
							float x=distance.get(1);
							distance.remove(1);
							distance.add(1, (float) 5);
							distance.remove(0);
							distance.add(0, s-15+x);
						}else{//ok
							temp1.setStart(temp1.getStart()-5);
							temp1.setEnd(temp1.getEnd()+5);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
						}
					}else if(i==a.size()-1){
						if(distance.get(i)<=10){//left side
							float s=distance.get(i);//left side
							float x=distance.get(i+1);//right side
							temp1.setStart(temp1.getStart()-s+5);
							temp1.setEnd(temp1.getEnd()+15-s);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							
							distance.remove(i+1);
							distance.add(i+1, x-15+s);
							distance.remove(i);
							distance.add(i, (float)5);
							
						}else if(distance.get(i+1)<=5){//right side
							float s=distance.get(i);
							float x=distance.get(i+1);
							temp1.setStart(temp1.getStart()-10+x);
							temp1.setEnd(paperwidth);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							
							distance.remove(i+1);
							distance.add(i+1, (float) 0);
							distance.remove(i);
							distance.add(i, s-10+x);
						}else{//ok
							temp1.setStart(temp1.getStart()-5);
							temp1.setEnd(temp1.getEnd()+5);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
						}
						
					}else{
						if(distance.get(i-1)<=10){//left side is not good
							float s=distance.get(i);//left side
							float x=distance.get(i+1);//right side
							temp1.setStart(temp1.getStart()-s+5);
							temp1.setEnd(temp1.getEnd()+15-s);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							
							distance.remove(i+1);
							distance.add(i+1, x-15+s);
							distance.remove(i);
							distance.add(i, (float)5);
						}else if(distance.get(i)<10){//right side is not good
							float s=distance.get(i);
							float x=distance.get(i+1);
							temp1.setStart(temp1.getStart()-15+x);
							temp1.setEnd(temp1.getEnd()+x-5);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							
							distance.remove(i+1);
							distance.add(i+1, (float) 5);
							distance.remove(i);
							distance.add(i, s-15+x);
						}else{//ok
							temp1.setStart(temp1.getStart()-5);
							temp1.setEnd(temp1.getEnd()+5);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
						}
						
					}
				}
				return ExtendingAll1(a,(extension-possCounter*10),paperwidth,offset);
			}else{//ne mogu sve lane da se extenduju za +10
				//not all lanes can be extended, we search for the ones that can and extend them
				for(int i=0;i<a.size();i++){
					temp1=a.get(i);
					if(possibleExtension.get(i)==true){
						if(i==0){
							if(distance.get(0)<=5){//left side
								temp1.setStart(offset);
								temp1.setEnd(temp1.getEnd()+10-distance.get(0));
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								float s=distance.get(0);
								float x=distance.get(1);
								distance.remove(1);
								distance.add(1, x-10+s);
								distance.remove(0);
								distance.add(0, (float) 0);
							}else if(distance.get(1)<=10){//right side
								temp1.setStart(temp1.getStart()-15+distance.get(1));
								temp1.setEnd(temp1.getEnd()+distance.get(1)-5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								float s=distance.get(0);
								float x=distance.get(1);
								distance.remove(1);
								distance.add(1, (float) 5);
								distance.remove(0);
								distance.add(0, s-15+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
						}else if(i==a.size()-1){
							if(distance.get(i)<=10){//left side
								float s=distance.get(i);//left side
								float x=distance.get(i+1);//right side
								temp1.setStart(temp1.getStart()-s+5);
								temp1.setEnd(temp1.getEnd()+15-s);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, x-15+s);
								distance.remove(i);
								distance.add(i, (float)5);
								
							}else if(distance.get(i+1)<=5){//right side
								float s=distance.get(i);
								float x=distance.get(i+1);
								temp1.setStart(temp1.getStart()-10+x);
								temp1.setEnd(paperwidth);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, (float) 0);
								distance.remove(i);
								distance.add(i, s-10+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
							
						}else{
							if(distance.get(i-1)<=10){//left side is not good
								float s=distance.get(i);//left side
								float x=distance.get(i+1);//right side
								temp1.setStart(temp1.getStart()-s+5);
								temp1.setEnd(temp1.getEnd()+15-s);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, x-15+s);
								distance.remove(i);
								distance.add(i, (float)5);
							}else if(distance.get(i)<10){//right side is not good
								float s=distance.get(i);
								float x=distance.get(i+1);
								temp1.setStart(temp1.getStart()-15+x);
								temp1.setEnd(temp1.getEnd()+x-5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, (float) 5);
								distance.remove(i);
								distance.add(i, s-15+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
						}
					}
				}
				return ExtendingAll1(a,(extension-possCounter*10),paperwidth,offset);
			}
			
		}else if(mod10==numLanes){//ako je moguce sve treba da se raspodeli podjednako
			if(numLanes==possCounter){//idealna situacija sve mogu i treba da se extenduju
				for(int i=0;i<a.size();i++){
					temp1=a.get(i);
					if(i==0){
						if(distance.get(0)<=5){//left side
							temp1.setStart(offset);
							temp1.setEnd(temp1.getEnd()+10-distance.get(0));
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							float s=distance.get(0);
							float x=distance.get(1);
							distance.remove(1);
							distance.add(1, x-10+s);
							distance.remove(0);
							distance.add(0, (float) 0);
						}else if(distance.get(1)<=10){//right side
							temp1.setStart(temp1.getStart()-15+distance.get(1));
							temp1.setEnd(temp1.getEnd()+distance.get(1)-5);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							float s=distance.get(0);
							float x=distance.get(1);
							distance.remove(1);
							distance.add(1, (float) 5);
							distance.remove(0);
							distance.add(0, s-15+x);
						}else{//ok
							temp1.setStart(temp1.getStart()-5);
							temp1.setEnd(temp1.getEnd()+5);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
						}
					}else if(i==a.size()-1){
						if(distance.get(i)<=10){//left side
							float s=distance.get(i);//left side
							float x=distance.get(i+1);//right side
							temp1.setStart(temp1.getStart()-s+5);
							temp1.setEnd(temp1.getEnd()+15-s);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							
							distance.remove(i+1);
							distance.add(i+1, x-15+s);
							distance.remove(i);
							distance.add(i, (float)5);
							
						}else if(distance.get(i+1)<=5){//right side
							float s=distance.get(i);
							float x=distance.get(i+1);
							temp1.setStart(temp1.getStart()-10+x);
							temp1.setEnd(paperwidth);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							
							distance.remove(i+1);
							distance.add(i+1, (float) 0);
							distance.remove(i);
							distance.add(i, s-10+x);
						}else{//ok
							temp1.setStart(temp1.getStart()-5);
							temp1.setEnd(temp1.getEnd()+5);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
						}
						
					}else{
						if(distance.get(i-1)<=10){//left side is not good
							float s=distance.get(i);//left side
							float x=distance.get(i+1);//right side
							temp1.setStart(temp1.getStart()-s+5);
							temp1.setEnd(temp1.getEnd()+15-s);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							
							distance.remove(i+1);
							distance.add(i+1, x-15+s);
							distance.remove(i);
							distance.add(i, (float)5);
						}else if(distance.get(i)<10){//right side is not good
							float s=distance.get(i);
							float x=distance.get(i+1);
							temp1.setStart(temp1.getStart()-15+x);
							temp1.setEnd(temp1.getEnd()+x-5);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
							
							distance.remove(i+1);
							distance.add(i+1, (float) 5);
							distance.remove(i);
							distance.add(i, s-15+x);
						}else{//ok
							temp1.setStart(temp1.getStart()-5);
							temp1.setEnd(temp1.getEnd()+5);
							temp1.setWidth(temp1.getEnd()-temp1.getStart());
							a.remove(i);
							a.add(i, temp1);
						}
					}
				}
				
			}else{// neke mogu da se exstenduju za +10 ali neke ne
				for(int i=0;i<a.size();i++){
					temp1=a.get(i);
					if(possibleExtension.get(i)==true){
						if(i==0){
							if(distance.get(0)<=5){//left side
								temp1.setStart(offset);
								temp1.setEnd(temp1.getEnd()+10-distance.get(0));
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								float s=distance.get(0);
								float x=distance.get(1);
								distance.remove(1);
								distance.add(1, x-10+s);
								distance.remove(0);
								distance.add(0, (float) 0);
							}else if(distance.get(1)<=10){//right side
								temp1.setStart(temp1.getStart()-15+distance.get(1));
								temp1.setEnd(temp1.getEnd()+distance.get(1)-5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								float s=distance.get(0);
								float x=distance.get(1);
								distance.remove(1);
								distance.add(1, (float) 5);
								distance.remove(0);
								distance.add(0, s-15+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
						}else if(i==a.size()-1){
							if(distance.get(i)<=10){//left side
								float s=distance.get(i);//left side
								float x=distance.get(i+1);//right side
								temp1.setStart(temp1.getStart()-s+5);
								temp1.setEnd(temp1.getEnd()+15-s);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, x-15+s);
								distance.remove(i);
								distance.add(i, (float)5);
								
							}else if(distance.get(i+1)<=5){//right side
								float s=distance.get(i);
								float x=distance.get(i+1);
								temp1.setStart(temp1.getStart()-10+x);
								temp1.setEnd(paperwidth);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, (float) 0);
								distance.remove(i);
								distance.add(i, s-10+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
							
						}else{
							if(distance.get(i-1)<=10){//left side is not good
								float s=distance.get(i);//left side
								float x=distance.get(i+1);//right side
								temp1.setStart(temp1.getStart()-s+5);
								temp1.setEnd(temp1.getEnd()+15-s);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, x-15+s);
								distance.remove(i);
								distance.add(i, (float)5);
							}else if(distance.get(i)<10){//right side is not good
								float s=distance.get(i);
								float x=distance.get(i+1);
								temp1.setStart(temp1.getStart()-15+x);
								temp1.setEnd(temp1.getEnd()+x-5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, (float) 5);
								distance.remove(i);
								distance.add(i, s-15+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
						}
					}
				}
				return ExtendingAll1(a,(extension-possCounter*10),paperwidth,offset);
				
			}
			
		}else{//ne moraju sve lane da se extenduju
			if(mod10<possCounter){//ima dovoljno lane da se extenduje nije bitno koja
				int n=mod10;
				int j=0;
				while(n!=0){
					temp1=a.get(j);
					if(possibleExtension.get(j)==true){
						if(j==0){
							if(distance.get(0)<=5){//left side
								temp1.setStart(offset);
								temp1.setEnd(temp1.getEnd()+10-distance.get(0));
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(j);
								a.add(j, temp1);
								float s=distance.get(0);
								float x=distance.get(1);
								distance.remove(1);
								distance.add(1, x-10+s);
								distance.remove(0);
								distance.add(0, (float) 0);
							}else if(distance.get(1)<=10){//right side
								temp1.setStart(temp1.getStart()-15+distance.get(1));
								temp1.setEnd(temp1.getEnd()+distance.get(1)-5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(j);
								a.add(j, temp1);
								float s=distance.get(0);
								float x=distance.get(1);
								distance.remove(1);
								distance.add(1, (float) 5);
								distance.remove(0);
								distance.add(0, s-15+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(j);
								a.add(j, temp1);
							}
						}else if(j==a.size()-1){
							if(distance.get(j)<=10){//left side
								float s=distance.get(j);//left side
								float x=distance.get(j+1);//right side
								temp1.setStart(temp1.getStart()-s+5);
								temp1.setEnd(temp1.getEnd()+15-s);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(j);
								a.add(j, temp1);
								
								distance.remove(j+1);
								distance.add(j+1, x-15+s);
								distance.remove(j);
								distance.add(j, (float)5);
								
							}else if(distance.get(j+1)<=5){//right side
								float s=distance.get(j);
								float x=distance.get(j+1);
								temp1.setStart(temp1.getStart()-10+x);
								temp1.setEnd(paperwidth);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(j);
								a.add(j, temp1);
								
								distance.remove(j+1);
								distance.add(j+1, (float) 0);
								distance.remove(j);
								distance.add(j, s-10+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(j);
								a.add(j, temp1);
							}
							
						}else{
							if(distance.get(j-1)<=10){//left side is not good
								float s=distance.get(j);//left side
								float x=distance.get(j+1);//right side
								temp1.setStart(temp1.getStart()-s+5);
								temp1.setEnd(temp1.getEnd()+15-s);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(j);
								a.add(j, temp1);
								
								distance.remove(j+1);
								distance.add(j+1, x-15+s);
								distance.remove(j);
								distance.add(j, (float)5);
							}else if(distance.get(j)<10){//right side is not good
								float s=distance.get(j);
								float x=distance.get(j+1);
								temp1.setStart(temp1.getStart()-15+x);
								temp1.setEnd(temp1.getEnd()+x-5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(j);
								a.add(j, temp1);
								
								distance.remove(j+1);
								distance.add(j+1, (float) 5);
								distance.remove(j);
								distance.add(j, s-15+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(j);
								a.add(j, temp1);
							}
						}
						j++;
						n--;
					}else j++;
				}
				
			}else if(mod10==possCounter){//taman dovoljan broj njih moze da se extenduje
				for(int i=0;i<a.size();i++){
					temp1=a.get(i);
					if(possibleExtension.get(i)==true){
						if(i==0){
							if(distance.get(0)<=5){//left side
								temp1.setStart(offset);
								temp1.setEnd(temp1.getEnd()+10-distance.get(0));
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								float s=distance.get(0);
								float x=distance.get(1);
								distance.remove(1);
								distance.add(1, x-10+s);
								distance.remove(0);
								distance.add(0, (float) 0);
							}else if(distance.get(1)<=10){//right side
								temp1.setStart(temp1.getStart()-15+distance.get(1));
								temp1.setEnd(temp1.getEnd()+distance.get(1)-5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								float s=distance.get(0);
								float x=distance.get(1);
								distance.remove(1);
								distance.add(1, (float) 5);
								distance.remove(0);
								distance.add(0, s-15+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
						}else if(i==a.size()-1){
							if(distance.get(i)<=10){//left side
								float s=distance.get(i);//left side
								float x=distance.get(i+1);//right side
								temp1.setStart(temp1.getStart()-s+5);
								temp1.setEnd(temp1.getEnd()+15-s);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, x-15+s);
								distance.remove(i);
								distance.add(i, (float)5);
								
							}else if(distance.get(i+1)<=5){//right side
								float s=distance.get(i);
								float x=distance.get(i+1);
								temp1.setStart(temp1.getStart()-10+x);
								temp1.setEnd(paperwidth);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, (float) 0);
								distance.remove(i);
								distance.add(i, s-10+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
							
						}else{
							if(distance.get(i-1)<=10){//left side is not good
								float s=distance.get(i);//left side
								float x=distance.get(i+1);//right side
								temp1.setStart(temp1.getStart()-s+5);
								temp1.setEnd(temp1.getEnd()+15-s);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, x-15+s);
								distance.remove(i);
								distance.add(i, (float)5);
							}else if(distance.get(i)<10){//right side is not good
								float s=distance.get(i);
								float x=distance.get(i+1);
								temp1.setStart(temp1.getStart()-15+x);
								temp1.setEnd(temp1.getEnd()+x-5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, (float) 5);
								distance.remove(i);
								distance.add(i, s-15+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
						}
					}
				}
			
				
			}else{//nema dovoljno mora da se zove rekurentno
				for(int i=0;i<a.size();i++){
					temp1=a.get(i);
					if(possibleExtension.get(i)==true){
						if(i==0){
							if(distance.get(0)<=5){//left side
								temp1.setStart(offset);
								temp1.setEnd(temp1.getEnd()+10-distance.get(0));
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								float s=distance.get(0);
								float x=distance.get(1);
								distance.remove(1);
								distance.add(1, x-10+s);
								distance.remove(0);
								distance.add(0, (float) 0);
							}else if(distance.get(1)<=10){//right side
								temp1.setStart(temp1.getStart()-15+distance.get(1));
								temp1.setEnd(temp1.getEnd()+distance.get(1)-5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								float s=distance.get(0);
								float x=distance.get(1);
								distance.remove(1);
								distance.add(1, (float) 5);
								distance.remove(0);
								distance.add(0, s-15+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
						}else if(i==a.size()-1){
							if(distance.get(i)<=10){//left side
								float s=distance.get(i);//left side
								float x=distance.get(i+1);//right side
								temp1.setStart(temp1.getStart()-s+5);
								temp1.setEnd(temp1.getEnd()+15-s);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, x-15+s);
								distance.remove(i);
								distance.add(i, (float)5);
								
							}else if(distance.get(i+1)<=5){//right side
								float s=distance.get(i);
								float x=distance.get(i+1);
								temp1.setStart(temp1.getStart()-10+x);
								temp1.setEnd(paperwidth);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, (float) 0);
								distance.remove(i);
								distance.add(i, s-10+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
							
						}else{
							if(distance.get(i-1)<=10){//left side is not good
								float s=distance.get(i);//left side
								float x=distance.get(i+1);//right side
								temp1.setStart(temp1.getStart()-s+5);
								temp1.setEnd(temp1.getEnd()+15-s);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, x-15+s);
								distance.remove(i);
								distance.add(i, (float)5);
							}else if(distance.get(i)<10){//right side is not good
								float s=distance.get(i);
								float x=distance.get(i+1);
								temp1.setStart(temp1.getStart()-15+x);
								temp1.setEnd(temp1.getEnd()+x-5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
								
								distance.remove(i+1);
								distance.add(i+1, (float) 5);
								distance.remove(i);
								distance.add(i, s-15+x);
							}else{//ok
								temp1.setStart(temp1.getStart()-5);
								temp1.setEnd(temp1.getEnd()+5);
								temp1.setWidth(temp1.getEnd()-temp1.getStart());
								a.remove(i);
								a.add(i, temp1);
							}
						}
					}
				}
				return ExtendingAll1(a,(extension-possCounter*10),paperwidth,offset);
				
			}
			
		}
		}
	}
		
		return a;
		
	}
}
