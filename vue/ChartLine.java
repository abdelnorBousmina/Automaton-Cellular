package vue;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;


/**
 * A simple demonstration application showing how to create a horizontal 3D bar chart using data
 * from a {@link CategoryDataset}.
 *
 */
public class ChartLine extends JFrame {
    
	/**
	 * Automatically added
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Dataset à afficher
	 */
	private DefaultCategoryDataset dataset;
	
	/**
	 * Titre de la fenêtre et du Chart
	 */
	private String chartTitle;
	
    /**
     * Constructeur. Définit le titre de la fenêtre et du Chart
     * @param title  le titre
     */
    public ChartLine(final String title) {

        super(title);
        
        chartTitle = title;
        
        // create the chart...
        dataset = new DefaultCategoryDataset();
        
        final JFreeChart chart = createChart(dataset);
        
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

        pack();
	    RefineryUtilities.centerFrameOnScreen(this);
	    setVisible(true);

    }

    /**
     * Ajout d'un point au Chart
     * @param val valeur du point
     * @param serie série du point
     * @param cat catégorie du point
     */
    public void addPoint(double val, String serie, String cat)
    {
    	dataset.addValue(val, serie, cat);
    }
    
    /**
     * Instancie le Chart
     * @param dataset le dataset d'entrée
     * @return Le Chart nouvellement créé
     */
    private JFreeChart createChart(final CategoryDataset dataset) {
        
    	// create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            chartTitle,       		   // chart title
            "Nb sorties",            // domain axis label
            "Secondes",                // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
       
        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

        renderer.setSeriesShapesVisible(0, true);
        
        renderer.setSeriesStroke(
            0, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {10.0f, 6.0f}, 0.0f
            )
        );
        
        return chart;
    
    }

}