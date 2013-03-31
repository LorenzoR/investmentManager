package org.investmentManager.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.investmentManager.model.Stock;
import org.investmentManager.service.StockService;
import org.springframework.stereotype.Component;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.data.Property;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Component
public class MyVaadinUI extends UI {

	@Override
	protected void init(VaadinRequest request) {

		SpringContextHelper helper = new SpringContextHelper(VaadinServlet
				.getCurrent().getServletContext());
		final StockService stockService = (StockService) helper
				.getBean("stockService");

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		
		final HorizontalLayout tablesLayout = new HorizontalLayout();
		tablesLayout.setWidth("1500px");
		tablesLayout.setMargin(true);
		
		layout.addComponent(tablesLayout);

		getPage().setTitle("Investment Manager");

		final ArrayList<Stock> stocks = (ArrayList<Stock>) stockService
				.getStocks("YPFD", 0, Integer.MAX_VALUE);
		
		final ArrayList<String> names = (ArrayList<String>) stockService.getStocksName();
		System.out.println(names.toString());

		Button button = new Button("Click Me!!");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label(stocks.get(0).toString()));
			}
		});
		layout.addComponent(button);

		tablesLayout.addComponent(createTable("Stock Table", stocks));
		tablesLayout.addComponent(createTable("Bonds Table", stocks));
		tablesLayout.addComponent(createTable("Funds Table", stocks));

		layout.addComponent(createChart(stocks));
	}

	private Table createTable(String title, ArrayList<Stock> stocks) {
		Table table = new Table(title) {
			@Override
			protected String formatPropertyValue(Object rowId, Object colId,
					Property property) {
				// Format by property type
				if (property.getType() == Date.class) {
					SimpleDateFormat df = new SimpleDateFormat(
							"dd MMM yyyy");
					return df.format((Date) property.getValue());
				}

				return super.formatPropertyValue(rowId, colId, property);
			}
		};
		table.addContainerProperty("Nombre", String.class, null);
		table.addContainerProperty("Fecha", Date.class, null);
		table.addContainerProperty("Cantidad", Double.class, null);
		table.addContainerProperty("Variacion", String.class, null);

		Double lastValue = null;
		Double var = null;
		for (Stock stock : stocks) {

			if (lastValue == null) {
				var = 0.0;
			} else {
				var = (stock.getMoneyAmount() * 100 / lastValue) - 100;
			}

			table.addItem(
					new Object[] { stock.getName(),
							stock.getLastQuoteDatetime(),
							stock.getMoneyAmount(),
							String.format("%.2g%n", var) + "%" }, stock.getId());

			lastValue = stock.getMoneyAmount();
		}

		return table;
	}

	private Chart createChart(ArrayList<Stock> stocks) {
		// Dates
		ArrayList<String> dates = new ArrayList<String>();
		ArrayList<Double> values = new ArrayList<Double>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");

		for (Stock aStock : stocks) {
			dates.add(sdf.format(aStock.getLastQuoteDatetime()));
			values.add(aStock.getMoneyAmount());
		}

		Chart chart = new Chart(ChartType.LINE);
		// chart.setWidth("800px"); // 100% by default
		chart.setHeight("600px"); // 400px by default

		Configuration conf = chart.getConfiguration();
		conf.setTitle("Valores " + stocks.get(0).getName());
		// conf.setSubTitle("Kills Grouped by Counties");

		// Disable markers from lines
		PlotOptionsLine plotOptions = new PlotOptionsLine();
		plotOptions.setMarker(new Marker(false));
		conf.setPlotOptions(plotOptions);

		// Chart Data
		ListSeries series = new ListSeries("Valor");
		series.setData(values.toArray(new Double[] {}));
		conf.addSeries(series);

		// Axis configuration
		XAxis xaxis = new XAxis();
		xaxis.setCategories(dates.toArray(new String[] {}));
		xaxis.setTitle("Fecha");
		conf.addxAxis(xaxis);

		// Set the Y axis title
		YAxis yaxis = new YAxis();
		yaxis.setTitle("Valor");
		yaxis.getLabels().setFormatter(
				"function() {return Math.floor(this.value) + \'$\';}");
		yaxis.getLabels().setStep(2);
		conf.addyAxis(yaxis);

		return chart;
	}

}
