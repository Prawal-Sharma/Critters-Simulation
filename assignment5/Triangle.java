package assignment5;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Polygon;


public class Triangle {
	 private Polygon polygon;

	    private DoubleProperty height;
	    private DoubleProperty base;
	    
	    private double h;
	    private double b;

	    public Triangle(double base, double height) {

	        this.polygon = new Polygon();

	        // Set the properties
	        this.height = new SimpleDoubleProperty(base);
	        this.base = new SimpleDoubleProperty(height);

	        this.height.addListener(new ChangeListener<Number>() {  // listens for changes to the world height
	            @Override
	            public void changed(ObservableValue<? extends Number> ov, Number oldHeight, Number newHeight) {
	                h = newHeight.doubleValue();
	                redrawTriangle();
	            }
	        });

	        this.base.addListener(new ChangeListener<Number>() {   // listens for changes to the world width
	            @Override
	            public void changed(ObservableValue<? extends Number> ov, Number oldBase, Number newBase) {
	                b = newBase.doubleValue();
	                redrawTriangle();
	            }
	        });

	        // Set values
	        this.b = base;
	        this.h = height;
	 
	        // Set defaults
	        polygon.getPoints().addAll(new Double[] { b / 2, 0.0, b, h, 0.0, h });

	    }
	    
	    private void redrawTriangle() {
	        polygon.getPoints().clear();
	        polygon.getPoints().addAll(new Double[] { b / 2, 0.0, b, h, 0.0, h });
	    }

	    /*
	     * Return the triangle as a polygon object
	     */
	    public Polygon getTrianglePolygon() {
	        return this.polygon;
	    }

	    public DoubleProperty getPropertyHeight() {
	        return height;
	    }

	    public void setHeight(DoubleProperty height) {
	        this.height = height;
	    }

	    public DoubleProperty getPropertyBase() {
	        return base;
	    }

	    public void setPropertyBase(DoubleProperty base) {
	        this.base = base;
	    }

}
