package core;

import java.util.HashMap;
import java.util.Map;

public class RandomNumberGenerator {
	
	private Map<Double, Double> distribution;
    private double distSum;

    public RandomNumberGenerator() {
        distribution = new HashMap<>();
    }

    public RandomNumberGenerator addNumber(double value, double distribution) {
        if (this.distribution.get(value) != null) {
            distSum -= this.distribution.get(value);
        }
        this.distribution.put(value, distribution);
        distSum += distribution;
        return this;
    }

    public double getRandomNumber() {
        double rand = Math.random();
        double ratio = 1.0f / distSum;
        double tempDist = 0;
        for (Double i : distribution.keySet()) {
            tempDist += distribution.get(i);
            if (rand / ratio <= tempDist) {
                return i;
            }
        }
        return 0;
    }
}
