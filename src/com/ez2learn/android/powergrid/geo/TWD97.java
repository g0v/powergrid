package com.ez2learn.android.powergrid.geo;

/**
	@brief TWD64 projection parameters
	
	reference to 
	http://rskl.geog.ntu.edu.tw/team/gis/doc/ArcGIS/WGS84%20and%20TM2.htm
	http://blog.minstrel.idv.tw/2004/06/taiwan-datum-parameter.html
**/
public class TWD97 extends TMParameter {
	public TWD97() {
		super(250000.00, 0.0, Math.toRadians(121.0), 0.9999, 6378137.0, 6356752.3141, 0.0818201799960599);
	}
}
