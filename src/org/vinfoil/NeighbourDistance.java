package org.vinfoil;

public class NeighbourDistance {
	private float NeighbourLeft;
	private float NeighbourRight;
	private FWL Lane;
	
	public float getNeighbourLeft(){
		return NeighbourLeft;
	}
	public void setNeighbourLeft(float n){
		NeighbourLeft=n;
	}
	public float getNeighbourRight(){
		return NeighbourRight;
	}
	public void setNeighbourRight(float n){
		NeighbourRight=n;
	}
	public FWL getLane(){
		return Lane;
	}
	public void setLane(FWL lane){
		Lane=lane;
	}
}
