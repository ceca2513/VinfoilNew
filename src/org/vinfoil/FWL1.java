package org.vinfoil;

import java.util.Comparator;


class FWL1 implements  Comparator 
{
    public int compare(Object o1, Object o2) {
        FWL e1=(FWL)o1;
        FWL e2=(FWL)o2;

        if(e1.getStart() > e2.getStart())
        {
           return 1;
        }
        else 
        {
           return -1;
        }
        
    }

}
