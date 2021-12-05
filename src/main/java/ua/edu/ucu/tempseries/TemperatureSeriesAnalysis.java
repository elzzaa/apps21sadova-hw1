package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int size = 0;
    public static final int MAX_NUM=273;
    public static final int MIN_NUM=-273;

    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[]{};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries.clone();
        this.size = temperatureSeries.length;

    }

    private void check() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        this.check();
        double sum = 0;
        for (double temp: temperatureSeries) {
            sum += temp;
        }
        return sum/size;
    }

    public double deviation() {
        double average = this.average();
        double sumTemp = 0;
        for (double temp: temperatureSeries) {
            sumTemp += Math.pow(temp-average, 2);
        }
        return sumTemp/Math.sqrt(size);
    }

    public double min() {
        return findTempClosestToValue(MIN_NUM);
    }

    public double max() {
        return findTempClosestToValue(MAX_NUM);
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        this.check();
        double closest = temperatureSeries[0];
        double diff = Math.abs(closest - tempValue);
        for (double temp: temperatureSeries) {
            if (Math.abs(temp - tempValue) < diff) {
                diff = Math.abs(temp - tempValue);
                closest = temp;
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        int counter = 0;
        for (double temp: temperatureSeries) {
            if (temp < tempValue) {
                counter++;
            }
        }
        double[] res = new double[counter];
        int i = 0, j = 0;
        while (i < size) {
            if (temperatureSeries[i] < tempValue) {
                res[j] = temperatureSeries[i];
                j++;
            }
            i++;
        }
        return res;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int counter = 0;
        for (double temp: temperatureSeries) {
            if (temp > tempValue) {
                counter ++;
            }
        }
        double[] res = new double[counter];
        int i = 0, j = 0;
        while (i < size) {
            if (temperatureSeries[i] > tempValue) {
                res[j] = temperatureSeries[i];
                j++;
            }
            i++;
        }
        return res;
    }

    public TempSummaryStatistics summaryStatistics() {
        check();
        double avgTemp = this.average();
        double devTemp = this.deviation();
        double minTemp = this.min();
        double maxTemp = this.max();
        return new TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);
    }

    public int addTemps(double... temps) {
        check();
        for (double temp: temps) {
            if (temp < MIN_NUM) {
                throw new InputMismatchException();
            }
        }
        int sum = 0;
        int sizeAdd = size;
        while (temps.length + size > sizeAdd) {
            sizeAdd *= 2;
        }
        double[] newTemps = new double[sizeAdd];
        for (int i = 0; i < size; i++) {
            newTemps[i] = temperatureSeries[i];
            sum += (int) temperatureSeries[i];
        }
        temperatureSeries = newTemps;
        for (double temp: temps) {
            temperatureSeries[size] = temp;
            size++;
            sum += (int) temp;
        }
        return sum;

    }

    public double[] getTemperatureSeries() {
        return this.temperatureSeries;
    }
}
