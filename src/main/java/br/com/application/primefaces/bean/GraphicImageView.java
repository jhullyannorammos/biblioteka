package br.com.application.primefaces.bean;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named @RequestScoped
public class GraphicImageView {
	
	public StreamedContent getGraphicText() {
        try {
            return DefaultStreamedContent.builder().contentType("image/png").stream(() -> {
                        try {
                            BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
                            Graphics2D g2 = bufferedImg.createGraphics();
                            g2.drawString("This is a text", 0, 10);
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            ImageIO.write(bufferedImg, "png", os);
                            return new ByteArrayInputStream(os.toByteArray());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StreamedContent getChart() {
        try {
        	return DefaultStreamedContent.builder().contentType("image/png").stream(() -> {
                        try {
                            JFreeChart jfreechart = ChartFactory.createPieChart("Cities", createDataset(), true, true, false);
                            File chartFile = new File("dynamichart");
                            ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);
                            return new FileInputStream(chartFile);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StreamedContent getChartWithoutBuffering() {
        try {
            return DefaultStreamedContent.builder()
                    .contentType("image/png")
                    .writer((os) -> {
                        try {
                            JFreeChart jfreechart = ChartFactory.createPieChart("Cities", createDataset(), true, true, false);
                            ChartUtilities.writeChartAsPNG(os, jfreechart, 375, 300);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    })
                    .build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public InputStream getChartAsStream() {
        return getChart().getStream().get();
    }

    public byte[] getChartAsByteArray() throws IOException {
        InputStream is = getChartAsStream();
        byte[] array = new byte[is.available()];
        is.read(array);
        return array;
    }

    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("New York", Double.valueOf(45.0));
        dataset.setValue("London", Double.valueOf(15.0));
        dataset.setValue("Paris", Double.valueOf(25.2));
        dataset.setValue("Berlin", Double.valueOf(14.8));

        return dataset;
    }

}
