package assignment5;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Polygon;



public class Star {
	 private Polygon polygon;
	    

	    private double xFactor;
	    private double yFactor;


	    private Double[] xCoords;
	    private Double[] yCoords;
	    

	    private Double[] points;
	   
	    
	    private DoubleProperty scaleXProperty;
	    private DoubleProperty scaleYProperty;

	    private static final Double[] basePoints =  new Double[]
	    {
	        0.500d, 0.000d,
	        0.335d, 0.314d,
	        0.000d, 0.382d,
	        0.233d, 0.643d,
	        0.191d, 1.000d,
	        0.500d, 0.847d,
	        0.809d, 1.000d,
	        0.767d, 0.643d,
	        1.000d, 0.382d,
	        0.665d, 0.314d
	    };
	    
	    public Star(double xFactor, double yFactor) {

	    
	        this.xFactor = xFactor;
	        this.yFactor = yFactor;

	  
	        xCoords = new Double[10];
	        yCoords = new Double[10];
	        int temp = 0;
	        for (int i = 0; i < 10; i++) {
	            xCoords[i] = basePoints[temp] * this.xFactor;
	            temp++;
	            yCoords[i] = basePoints[temp] * this.yFactor;
	            temp++;
	        }
	        
	    
	        points = new Double[20];
	        temp = 0;
	        for (int i = 0; i < 20; i++) {
	            points[i] = xCoords[temp];
	            i++;
	            points[i] = yCoords[temp];
	            temp++;
	        }
	        
	
	        polygon = new Polygon();
	        polygon.getPoints().addAll(points);
	        
	     
	        scaleXProperty = new SimpleDoubleProperty(this.xFactor);
	        scaleXProperty.addListener(new ChangeListener<Number>(){
	            @Override
	            public void changed(ObservableValue<? extends Number> observable, Number oldXFactor, Number newXFactor) {
	                int i = 0, j = 0;
	                while (i < 10) {
	                    xCoords[i] = basePoints[j] * newXFactor.doubleValue();
	                    i++;
	                    j+=2;
	                }
	                redrawStar();
	            }            
	        });
	        
	       
	        scaleYProperty = new SimpleDoubleProperty(this.yFactor);
	        scaleYProperty.addListener(new ChangeListener<Number>(){
	            @Override
	            public void changed(ObservableValue<? extends Number> observable, Number oldYFactor, Number newYFactor) {
	                int i = 0, j = 1;
	                while (i < 10) {
	                    yCoords[i] = basePoints[j] * newYFactor.doubleValue() * 0.8;
	                    i++;
	                    j+=2;
	                }
	                redrawStar();
	            }            
	        });

	    }

	   
	    private void redrawStar() {

	       
	        Double[] newPoints = new Double[20];
	        int temp = 0;
	        for (int i = 0; i < 20; i++) {
	            newPoints[i] = xCoords[temp];
	            i++;
	            newPoints[i] = yCoords[temp];
	            temp++;
	        }
	        points = newPoints;
	        polygon.getPoints().clear();
	        polygon.getPoints().addAll(points);

	    }

	    
	    public Polygon getStarPolygon() {
	        return this.polygon;
	    }

	    public void setFactors(double xFactor, double yFactor) {
	        this.xFactor = xFactor;
	        this.yFactor = yFactor;
	    }
	    
	    public DoubleProperty getXFactorProperty() {
	        return this.scaleXProperty;
	    }
	    
	    public DoubleProperty getYFactorProperty() {
	        return this.scaleYProperty;
	    }
}
