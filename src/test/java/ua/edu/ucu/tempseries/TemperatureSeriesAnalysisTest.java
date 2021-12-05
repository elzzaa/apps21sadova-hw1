package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {-4.12, 1.72, 6.4, -5.89};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(-5.89, seriesAnalysis.min(), 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {-4.12, 1.72, 6.4, -5.89};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(6.4, seriesAnalysis.max(), 0.00001);
    }

    @Test
    public void testClosestToZero() {
        double[] temperatureSeries = {-4.12, 1.72, 6.4, -5.89};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(1.72, seriesAnalysis.findTempClosestToZero(), 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {2.0, -4,12, 3.5, -2.1};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(69.16963238878749, seriesAnalysis.deviation(), 0.00001);
        double[] temperatureSeries2 = {-3, 2, 3, 1, 4};
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);
        assertEquals(13.058636988598773, seriesAnalysis2.deviation(), 0.001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {-4.12, 1.72, 6.4, -5.89};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expected = {-4.12, -5.89};
        assertEquals(expected.length, seriesAnalysis.findTempsLessThen(0).length);
        assertEquals(expected[0], seriesAnalysis.findTempsLessThen(0)[0], 0.0);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {-4.12, 13.72, 6.4, -5.89, 11.01};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expected = {13.72, 11.01};
        assertEquals(expected.length, seriesAnalysis.findTempsGreaterThen(6.4).length);
        assertEquals(expected[0], seriesAnalysis.findTempsGreaterThen(6.4)[0], 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsForEmpty() {
        double[] temperatureSeriesEmpty = {};
        TemperatureSeriesAnalysis seriesAnalysisEmpty = new TemperatureSeriesAnalysis(temperatureSeriesEmpty);
        seriesAnalysisEmpty.summaryStatistics();
    }

    @Test
    public void testSummaryStatistics(){
        double[] temperatureSeries = {-4.12, 13.72, 6.4, -5.89};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals("TempSummaryStatistics: \n" +
                        "Average temperature is 2.5275\n" +
                        "Standard deviation is 127.65593750000002\n" +
                        "Minimal temperature is -5.89\n" +
                        "Maximal temperature is 13.72",
                seriesAnalysis.summaryStatistics().toString());
    }
}

