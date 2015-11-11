package com.ez2learn.android.powergrid.geo;

/**
	@brief this class define parameters of TM(Transverse Mercator)
**/
public class TMParameter {
	private double dx;		// the delta of x coordinate
	private double dy;		// the delta of y coordinate
	private double lon0; 	// original point of long (should be radians)
	private double k0;		// scale along long0
	private double a;		// Equatorial radius
	private double b;		// Polar radius
	private double e; 		// Eccentricity
	
	public TMParameter(double dx, double dy, double lon0, double k0, double a, double b, double e) {
		this.dx = dx;
		this.dy = dy;
		this.lon0 = lon0;
		this.k0 = k0;
		this.a = a;
		this.b = b;
		this.e = e;
	}
	
	public double getDx() {
		return dx;
	}
	
	public double getDy() {
		return dy;
	}
	
	public double getLon0() {
		return lon0;
	}
	
	public double getK0() {
		return k0;
	}
	
	public double getA() {
		return a;
	}
	
	public double getB() {
		return b;
	}
	
	public double getE() {
		return e;
	}
}
