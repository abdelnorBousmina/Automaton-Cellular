package view;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryLabelWidthType;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A simple demonstration application showing how to create a horizontal 3D bar chart using data
 * from a {@link CategoryDataset}.
 *
 */
public class Chart extends JFrame {

	/**
	 * Automatically added
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Le dataset a afficher
	 */
	private DefaultCategoryDataset dataset;

	/**
	 * Titre de la fenetre et du Chart
	 */
	private String chartTitle;

	/**
	 * Constructeur. Definit le titre de la fenetre et du Chart
	 * @param title  le titre
	 */
	public Chart(final String title) {

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
	 * @param serie serie du point
	 * @param cat categorie du point
	 */
	public void addPoint(double val, String serie, String cat)
	{
		dataset.addValue(val, serie, cat);
	}

	/**
	 * Instancie le Chart
	 * @param dataset le dataset d'entree
	 * @return Le Chart nouvellement cree
	 */
	private JFreeChart createChart(final CategoryDataset dataset) {

		final JFreeChart chart = ChartFactory.createBarChart3D(
				chartTitle,       			 // chart title
				"ID Personnes",              // domain axis label
				"Unites",                  // range axis label
				dataset,                     // data
				PlotOrientation.HORIZONTAL,  // orientation
				true,                        // include legend
				true,                        // tooltips
				false                        // urls
				);

		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setForegroundAlpha(1.0f);

		// left align the category labels...
		final CategoryAxis axis = plot.getDomainAxis();
		final CategoryLabelPositions p = axis.getCategoryLabelPositions();

		final CategoryLabelPosition left = new CategoryLabelPosition(
				RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT, 
				TextAnchor.CENTER_LEFT, 0.0,
				CategoryLabelWidthType.RANGE, 0.30f
				);
		axis.setCategoryLabelPositions(CategoryLabelPositions.replaceLeftPosition(p, left));

		return chart;        

	}

}