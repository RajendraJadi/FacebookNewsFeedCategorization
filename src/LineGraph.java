import java.awt.Color;
   
    import org.jfree.chart.ChartFactory;
    import org.jfree.chart.ChartFrame;
    import org.jfree.chart.JFreeChart;
    import org.jfree.chart.plot.PlotOrientation;
    import org.jfree.chart.plot.XYPlot;
    import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
    import org.jfree.data.xy.XYSeries;
    import org.jfree.data.xy.XYSeriesCollection;
    import org.jfree.ui.RectangleInsets;
   

public class LineGraph 
{
	LineGraph()
	{
		 XYSeries series1 = new XYSeries("Incorrectly Classified");
         series1.add(20, 10);
         series1.add(40, 20);
         series1.add(70, 50);

XYSeries series2 = new XYSeries("Correctly Classified");
         series2.add(20, 30);
         series2.add(40, 40);
         series2.add(70, 10);
                
XYSeriesCollection xyDataset = new XYSeriesCollection();
                   xyDataset.addSeries(series1);
                   xyDataset.addSeries(series2);

JFreeChart chart     = ChartFactory.createXYLineChart("Classification Efficiency Graph","efficiency %","Numbers of Posts",xyDataset,PlotOrientation.VERTICAL,true,false,false);
           chart.setBackgroundPaint(Color.white); 
                
XYPlot plot      = (XYPlot) chart.getPlot();
       plot.setBackgroundPaint       (Color.white);
       plot.setDomainGridlinePaint   (Color.GREEN);
       plot.setRangeGridlinePaint    (Color.orange);
       plot.setAxisOffset            (new RectangleInsets(50, 0, 20, 5));
       plot.setDomainCrosshairVisible(true);
       plot.setRangeCrosshairVisible (true);

XYLineAndShapeRenderer  renderer  = (XYLineAndShapeRenderer) plot.getRenderer();      
                        renderer.setBaseShapesVisible(true);
                        renderer.setBaseShapesFilled (true);

ChartFrame frame = new ChartFrame("Efficiency Graph", chart);
           frame.setSize   (450, 250);
           frame.setVisible(true);       
	}
		 public static void main(String[] args) 
		 {
	        LineGraph graph = new LineGraph();        
		 }  
	}


