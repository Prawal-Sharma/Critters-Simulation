package assignment5;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Polygon;


public class Diamond {
	private Polygon polygon;
    private Diamond ref = this;

    private DoubleProperty width;
    private DoubleProperty height;
    
    private double h;
    private double w;

    /**
     * Constructor for a diamond
     * @param width is the horizontal component from vertex to vertex
     * @param height is the vertical component from vertex to vertex
     */
    public Diamond(double width, double height) {

        this.polygon = new Polygon();

        // Set the properties
        this.height = new SimpleDoubleProperty(height);
        this.width = new SimpleDoubleProperty(width);

        this.height.addListener(new ChangeListener<Number>() {  // listens for changes to the world height
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldHeight, Number newHeight) {
                ref.h = newHeight.doubleValue();
                redrawDiamond();
            }
        });

        this.width.addListener(new ChangeListener<Number>() { // listens for
                                                              // changes to the
                                                              // world width
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldWidth, Number newWidth) {
                ref.w = newWidth.doubleValue();
                redrawDiamond();
            }
        });

        // Set values
        this.w = width;
        this.h = height;

        // Set defaults
        polygon.getPoints().addAll(new Double[] { w / 2, 0.0, 0.0, h / 2, w / 2, h, w, h / 2 });

    }

    /**
     * Redraws the diamond according to sizes
     */
    private void redrawDiamond() {
        polygon.getPoints().clear();
        polygon.getPoints().addAll(new Double[] { w / 2, 0.0, 0.0, h / 2, w / 2, h, w, h / 2 });
    }

    /*
     * Return the triangle as a polygon object
     */
    public Polygon getTrianglePolygon() {
        return this.polygon;
    }

    /**
     * Gives the property of the diamond for binding.
     * @return the property
     */
    public DoubleProperty getPropertyHeight() {
        return height;
    }

    /**
     * Gives the property of the diamond for binding.
     * @return the property
     */
    public DoubleProperty getPropertyWidth() {
        return width;
    }

}
