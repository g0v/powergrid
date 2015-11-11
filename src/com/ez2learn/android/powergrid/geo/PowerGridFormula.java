package com.ez2learn.android.powergrid.geo;

/**
	@brief The core formulas of Taiwan power grid position system
	
	reference to http://wiki.osgeo.org/index.php?title=Taiwan_Power_Company_grid&uselang=zh-tw
**/
public class PowerGridFormula {
	static final int D_EW = 80000; // Dimension east-west
	static final int D_NS = 50000; // Dimension north-south
	static final int TAIWAN_LEFT = 90000;
	static final int TAIWAN_TOP = 2800000;
	static final String TAIWAN_MAP = 
		"_ABC\n" +  
		"_DEF\n" +
		"_GH_\n" +
		"JKL_\n" +
		"MNO_\n" +
		"PQR_\n" +
		"_TU_\n" +
		"_VW\n";
    /** 
		@brief find the base coordinate by area char
	**/
	static public double[] findBaseCoordinate(char target) {
		int y = TAIWAN_TOP;
		int x = 10000;
		for(String line: TAIWAN_MAP.split("\n")) {
			x = 10000;
			y -= D_NS;
			boolean found = false;
			for(int i = 0; i < line.length(); ++i) {
				char c = line.charAt(i);
				x += D_EW;
				if(c == target) {
					found = true;
					break;
				}
			}
			if(found) {
				break;
			}
		}
		return new double[]{x, y};
	}
	
	/**
	 	@brief Convert power grid coordinate to TWD67 coordinate
	**/
	static public double[] convert(String code) {
		code = code.toUpperCase();
		char g = code.charAt(0);
		int pp = Integer.parseInt(code.substring(1, 3));
		int qq = Integer.parseInt(code.substring(3, 5));
		int r = code.charAt(5) - 'A';
		int s = code.charAt(6) - 'A';
		int t =  Integer.parseInt(code.substring(7, 8));
		int u =  Integer.parseInt(code.substring(8, 9));
		
		double result[] = findBaseCoordinate((char)g);
		double x = result[0];
		double y = result[1];
    	
    	x += pp * 800;
    	y += qq * 500;
    	x += r * 100;
    	y += s * 100;
    	x += t * 10;
    	y += u * 10;
    	
    	if(code.length() == 11) {
    		int v = Integer.parseInt(code.substring(9, 10));
    		int w = Integer.parseInt(code.substring(10, 11));
    		x += v;
    		y += w;
    	}
    	return new double[]{x, y};
	}
}
