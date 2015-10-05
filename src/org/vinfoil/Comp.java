package org.vinfoil;

import java.util.Comparator;

class Comp implements Comparator {
	 public int compare(Object o1, Object o2) {
	        Group e1=(Group)o1;
	        Group e2=(Group)o2;

	        if(e1.getDistance() > e2.getDistance())
	        {
	           return 1;
	        }
	        else 
	        {
	           return -1;
	        }
	        
	    }
}



