package com.ez2learn.android.powergrid.geo;

public class Util {
	
	/**
	 	@brief Convert TWD67 coordinate to TWD97 coordinate
	**/
	static public double[] twd67ToTwd97(double x, double y) {
	    double a = 0.00001549;
	    double b = 0.000006521;
	    double x97 = x + 807.8 + a*x + b*y;
	    double y97 = y - 248.6 + a*y + b*x;
	    return new double[] {x97, y97, 0.0};
	}
}
