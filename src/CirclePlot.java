import java.awt.Point;
import java.util.Arrays;

public class CirclePlot {
	private int[] xPoints;
	private int[] yPoints;
	private static CirclePlot instance = null;
	
	// TODO = Only have the calc occur once !!!
	private CirclePlot(){
		generateCirclePoints(10, 100, new Point(200,200));
	}
	
	public static CirclePlot getInstance(){
		if(instance == null){
			return new CirclePlot();
		}else{
			return instance;
		}
	}

	/**
	 * 
	 * @param g
	 * @param points number of edges
	 * @param radius
	 * @param center
	 */
	protected void generateCirclePoints(int points, double radius, Point center){
		xPoints = new int[points]; 
		yPoints = new int[points]; 
		double slice = 2 * Math.PI / points;
	    for (int i = 0; i < points; i++)
	    {
	        double angle = slice * i;
	        int newX = (int)(center.getX() + radius * Math.cos(angle));
	        int newY = (int)(center.getY() + radius * Math.sin(angle));
	        xPoints[i] = newX;
	        yPoints[i] = newY;
	    }
//	    g.drawPolygon(new Polygon(xPoints, yPoints, xPoints.length));
	}

	protected int[] getxPoints() {
		return xPoints;
	}

	protected int[] getyPoints() {
		return yPoints;
	}
	
	
}
