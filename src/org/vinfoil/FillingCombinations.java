package org.vinfoil;

import java.util.*;
public class FillingCombinations {
	public FillingCombinations(){
		
	}
	public ArrayList <Integer> RecPoss(int k, ArrayList <Integer> in){
		ArrayList <Integer> templist=new ArrayList <Integer>();
		//proveri da li je moguce da kombinacija koju trazis bude veca od broja elemeata koje imas na listi
		
		
		return templist;
	}
	public ArrayList <int []> Combinations(int k, ArrayList <Integer> in){
		ArrayList <int []> templist=new ArrayList <int []>();
		
		int max=in.size()-1;
		int [] a=new int [k];
		if (k==2){
			for(int i=0;i<max;i++){
				if(in.get(i)==in.get(i+1)-1){
					a[0]=in.get(i);
					a[1]=in.get(i+1);
					templist.add(a);
					 a=new int [k];	
				}
						
					
			}
			
		}else{
			ArrayList <int []> templist1=new ArrayList <int []>();

			ArrayList <Integer> inn=new ArrayList <Integer>();
			
			templist1=this.Combinations(k-1, in);
			int [] b=new int[k];
			int [] c=new int[k-1];
				int l=0;
				while(l<templist1.size()){
					//inn=(ArrayList<Integer>) in.clone();
					inn.addAll(in);
					c=templist1.get(l);
					for(int j=0;j<k-1;j++){
						b[j]=c[j];
						int g3=c[j];
						int h=inn.indexOf(g3);
						inn.remove(h);
						
					}
					for(int m=0;m<inn.size();m++){
						if(inn.get(m)==b[k-2]+1)
							b[k-1]=inn.get(m);
					}
					templist.add(b);
					if(b[k-1]==0){
						int g4=templist.indexOf(b);
						templist.remove(g4);
					}
						
					b=new int[k];
					l++;
					inn.clear();
				}
			
		}
		return templist;
	}
	public ArrayList <CombResult> Filling2( Combination c, ArrayList <Integer> S, int numOfNeededLanes, int brojClanovaKombinacije){
		ArrayList <int []> templist=new ArrayList <int []>();
		//int numOfLanes=S.size();
		ArrayList <CombResult> listakombinacija=new ArrayList <CombResult>();
		CombResult privremeno=new CombResult();
		//uzmem prvi element sa liste
		int i=0;
		int [] a=c.getP();//jedna kombinacija
		ArrayList <Integer> a1=new ArrayList <Integer>();
		
		//punim b sa brojevima lane pocev od 1 pa do br lane-1 
		//ovaj deo mozes da zamenis samo sa S
		
		//uzimam samo ne nula delove ove kombinacije
		while(i<a.length&&a[i]!=0){
			a1.add(a[i]);
			i++;
		}
		if(a1.size()==1){
			ArrayList <int []> z=new ArrayList <int []>();
			z=this.Combinations(a1.get(0), S);
			templist.addAll(z);
			int i1=0;
			while(i1<z.size()){
				privremeno.setComb(a1);
				int [] temp=z.get(i1);
				privremeno.setVector(temp);
				listakombinacija.add(privremeno);//ovo treba da vratis
				privremeno=new CombResult();
				i1++;
			}
		}else{//imam viseclanu kombinaciju
			ArrayList <CombResult> priv=new ArrayList <CombResult>();
			ArrayList <Integer> novapriv=new ArrayList <Integer>();
			novapriv.addAll(a1);
			novapriv.remove(novapriv.size()-1);
			int r1=novapriv.size();
			int [] novapriv1=new int [r1];
			for(int j=0;j<r1;j++){
				novapriv1[j]=novapriv.get(j);
			}
			Combination novaPriv=new Combination();
			novaPriv.setP(novapriv1);
			priv=this.Filling2(novaPriv, S, numOfNeededLanes, brojClanovaKombinacije-1);
			int lastIndex=a1.get(a1.size()-1);
			//trebam da odradim ostatak
			//trebam da pozovem combinacije sa ovom jednom comb i sa ostatkom
			//trebam da spojim dva dobijene rezultata
			int s=0;
			ArrayList <Integer> S1=new ArrayList <Integer>();
			//S1=(ArrayList<Integer>) S.clone();
			S1.addAll(S);
			ArrayList <Integer> novaprivcc=new ArrayList <Integer>();
			
			while(s<priv.size()){
				novaprivcc.addAll(novapriv);
				CombResult primerak=new CombResult();
				primerak=priv.get(s);
				int [] temppriv=primerak.getVector();
				ArrayList <Integer> b1=new ArrayList <Integer>();
				ArrayList <Integer> b2=new ArrayList <Integer>();
				//b1=(ArrayList<Integer>) S.clone();
				b1.addAll(S);
				for(int j=0;j<primerak.getVector().length;j++){
					//S1.remove(temppriv[j]-1);
					b2.add(temppriv[j]);
				}
				b1.removeAll(b2);
				//sad pozovi comb
				ArrayList <int []> z1=new ArrayList <int []>();
				z1=this.Combinations(lastIndex, b1);//ne radi kako treba
				//sada trebam da spajam
				int v=0;
				novaprivcc.add(lastIndex);
				while(v<z1.size()){
					int [] h=z1.get(v);
					
					privremeno.setComb(novaprivcc);
					//temppriv treba da sjedinis sa rezultatom od z1 tjs sa h
					int [] koznasta=new int [h.length+temppriv.length];
					for(int j=0;j<temppriv.length;j++){
						koznasta[j]=temppriv[j];
					}
					int n1=0; int n2=temppriv.length;
					while(n1<h.length){
						koznasta[n2]=h[n1];
						n1++;
						n2++;
					}
					privremeno.setVector(koznasta);
					listakombinacija.add(privremeno);
					privremeno=new CombResult();
					v++;
				}
				novaprivcc=new ArrayList <Integer>();
				s++;
			}
		}
		return listakombinacija;
		//return templist;
	}
	public ArrayList <AllPossibleCombinationWithMerging> FillForMerging(ArrayList <FWL> S, int NK){
		ArrayList <AllPossibleCombinationWithMerging> templist=new ArrayList <AllPossibleCombinationWithMerging>();
		ArrayList <CombResult> resultOfComb= new ArrayList <CombResult>();
		ObtainingAllCombination allComb=new ObtainingAllCombination ();
		ArrayList <Combination> combTemp=new ArrayList <Combination>();
		int numOfLanes=S.size();
		int numOfNeededLanes=NK+3;
		if(numOfNeededLanes>numOfLanes)
			numOfNeededLanes=numOfLanes-2;
		//uzimam sve moguce kombinacije
		combTemp=allComb.ListOfComb(numOfLanes, numOfNeededLanes);
		//elementi ovog niza se dimenzije 20 i popunjavaju se nulama
		int k=0;
		//S1 se puni samo indexima lanova iz skupa S i pocenje od 1
		ArrayList <Integer> S1=new ArrayList <Integer>();
		for(int i=0;i<S.size();i++)
			S1.add(i+1);
		while(k<combTemp.size()){
			Combination c=new Combination ();
			c=combTemp.get(k);
			int [] a=c.getP();
			ArrayList <Integer> a1=new ArrayList <Integer>();
			//moram da eleiminisem nule iz ovog niza
			for(int i=0;i<a.length;i++){
				if(a[i]!=0)
					a1.add(a[i]);
			} 
			int [] a2=new int [a1.size()];
			for(int i=0;i<a1.size();i++)
				a2[i]=a1.get(i);
			Combination c1=new Combination ();
			c1.setP(a2);
			//a1 sadrzi samu kombinaciju bez nula na kraju
			//ovim dobijam kombinacije i niz tih kombinacija
			resultOfComb=this.Filling2(c1, S1, numOfNeededLanes, a1.size());
			int l=0;
			ArrayList <FWL> S2=new ArrayList <FWL>();
			
			while(l<resultOfComb.size()){
				S2.addAll(S);
				CombResult primerak=new CombResult();
				primerak=resultOfComb.get(l);
				ArrayList <Integer> Comb1=primerak.getComb();//sadrzi kombinaciju
				int [] Vector1=primerak.getVector();//sadrzi niz sa konkretnom kombinacijom
				int q=0;int q1=0;int w1=0;
				ArrayList <FWL> Stemp=new ArrayList <FWL>();//pomocna promenljiva da smsetim mergovane lane u njoj
				while(q<Comb1.size()){
					int w=Comb1.get(q);
					w1=w1+w;
					FWL merge=new FWL();
					int indexStart=Vector1[q1]-1;
					int indexEnd=Vector1[w1-1]-1;
					FWL lane1=S.get(indexStart);
					FWL lane2=S.get(indexEnd);
					//ovo ce da vrati novu listu org.vinfoil.FWL samo moras da preuredis indexe
					merge=merge.MergingOfTwoLanes(lane1, lane2);
					Stemp.add(merge);
					merge=new FWL();
					lane1=new FWL();
					lane2=new FWL();
					q1=q1+w;
					q++;
				}
				//sada Stemp sadrzi sve mergovane lane i treba da updatujem sa preostalim lane
				int [] vec=new int [Vector1.length];
				for(int i=0;i<Vector1.length;i++)
					vec[i]=Vector1[i]-1;//sadrzi indexe svih lane koje su mergovane
				ArrayList <FWL> nj=new ArrayList <FWL>();
				for(int i=0;i<vec.length;i++){
					FWL s=S.get(vec[i]);
					nj.add(s);//sadrzi sve mergovane lane
				}
				S2.removeAll(nj);//izbacujem sve mergovane lane
				Stemp.addAll(S2);//na mergovanim lane dodajem preostale lane
				AllPossibleCombinationWithMerging merg=new AllPossibleCombinationWithMerging();
				merg.setListOfLanes(Stemp);
				l++;
				templist.add(merg);
				merg=new AllPossibleCombinationWithMerging();
				Stemp=new ArrayList <FWL>();
				S2=new ArrayList <FWL>();
			}
			k++;
			c1=new Combination ();
		}
		
		return templist;
	}
	//moram ovaj deo da prepravim tako da se zna kako se rapssuje ovako cak i ne znam koja lane je joined!!!
	//nije zavrsena!!!!
	public ArrayList <AllPossibleCombinationWithMerging> FillForJoining(ArrayList <FWL> S, int NK){
		ArrayList <AllPossibleCombinationWithMerging> templist=new ArrayList <AllPossibleCombinationWithMerging>();
		ArrayList <CombResult> resultOfComb= new ArrayList <CombResult>();
		ObtainingAllCombination allComb=new ObtainingAllCombination ();
		ArrayList <Combination> combTemp=new ArrayList <Combination>();
		int numOfLanes=S.size();
		int numOfNeededLanes=NK+3;
		if(numOfNeededLanes>numOfLanes)
			numOfNeededLanes=numOfLanes-2;
		//uzimam sve moguce kombinacije
		combTemp=allComb.ListOfComb(numOfLanes, numOfNeededLanes);
		//elementi ovog niza se dimenzije 20 i popunjavaju se nulama
		int k=0;
		//S1 se puni samo indexima lanova iz skupa S i pocenje od 1
		ArrayList <Integer> S1=new ArrayList <Integer>();
		for(int i=0;i<S.size();i++)
			S1.add(i+1);
		while(k<combTemp.size()){
			Combination c=new Combination ();
			c=combTemp.get(k);
			int [] a=c.getP();
			ArrayList <Integer> a1=new ArrayList <Integer>();
			//moram da eleiminisem nule iz ovog niza
			for(int i=0;i<a.length;i++){
				if(a[i]!=0)
					a1.add(a[i]);
			} 
			int [] a2=new int [a1.size()];
			for(int i=0;i<a1.size();i++)
				a2[i]=a1.get(i);
			Combination c1=new Combination ();
			c1.setP(a2);
			//a1 sadrzi samu kombinaciju bez nula na kraju
			//ovim dobijam kombinacije i niz tih kombinacija
			resultOfComb=this.Filling2(c1, S1, numOfNeededLanes, a1.size());
			int l=0;
			ArrayList <FWL> S2=new ArrayList <FWL>();
			
			while(l<resultOfComb.size()){
				S2.addAll(S);
				CombResult primerak=new CombResult();
				primerak=resultOfComb.get(l);
				ArrayList <Integer> Comb1=primerak.getComb();//sadrzi kombinaciju
				int [] Vector1=primerak.getVector();//sadrzi niz sa konkretnom kombinacijom
				int q=0;int q1=0;int w1=0;
				ArrayList <FWL> Stemp=new ArrayList <FWL>();//pomocna promenljiva da smsetim joined lane u njoj
				while(q<Comb1.size()){
					int w=Comb1.get(q);
					w1=w1+w;
					//sada trebam da posmatram samo kombinacije gde imam 3 ili dva puta po 2
					if(Comb1.get(q)==3){
						//prvo proveri uslov da se preklapa samo sa
						FWL join=new FWL();
						int indexStart=Vector1[q1]-1;
						int indexMidlle=indexStart+1;
						int indexEnd=Vector1[w1-1]-1;
						FWL lane1=S.get(indexStart);
						FWL lane2=S.get(indexEnd);
						FWL lane3=S.get(indexMidlle);
						float distance=lane3.getStart()-lane1.getEnd();
						distance=distance+lane2.getStart()-lane3.getEnd();
						if(distance>40){
							//e sad mozes da spajas
							join=join.JoiningOfThreeLanes(lane1, lane3, lane2);
							Stemp.add(join);
							//I need to flag which lane is joined
						}
						
					}
					FWL merge=new FWL();
					int indexStart=Vector1[q1]-1;
					int indexEnd=Vector1[w1-1]-1;
					FWL lane1=S.get(indexStart);
					FWL lane2=S.get(indexEnd);
					//ovo ce da vrati novu listu org.vinfoil.FWL samo moras da preuredis indexe
					merge=merge.MergingOfTwoLanes(lane1, lane2);
					Stemp.add(merge);
					merge=new FWL();
					lane1=new FWL();
					lane2=new FWL();
					q1=q1+w;
					q++;
				}
				//sada Stemp sadrzi sve mergovane lane i treba da updatujem sa preostalim lane
				int [] vec=new int [Vector1.length];
				for(int i=0;i<Vector1.length;i++)
					vec[i]=Vector1[i]-1;//sadrzi indexe svih lane koje su mergovane
				ArrayList <FWL> nj=new ArrayList <FWL>();
				for(int i=0;i<vec.length;i++){
					FWL s=S.get(vec[i]);
					nj.add(s);//sadrzi sve mergovane lane
				}
				S2.removeAll(nj);//izbacujem sve mergovane lane
				Stemp.addAll(S2);//na mergovanim lane dodajem preostale lane
				AllPossibleCombinationWithMerging merg=new AllPossibleCombinationWithMerging();
				merg.setListOfLanes(Stemp);
				l++;
				templist.add(merg);
				merg=new AllPossibleCombinationWithMerging();
				Stemp=new ArrayList <FWL>();
				S2=new ArrayList <FWL>();
			}
			k++;
			c1=new Combination ();
		}
		
		
		return templist;
	}
}
