package core;

public class Constants {
	
	public static RandomNumberGenerator randNumber = new RandomNumberGenerator()
			.addNumber(0.5d,  0.05d)
			.addNumber(0.75d, 0.1d)
			.addNumber(1.0d,  0.35d)
			.addNumber(1.25d, 0.2d)
			.addNumber(1.5d,  0.15d)
			.addNumber(1.75d, 0.1d)
			.addNumber(2.0d,  0.05d);
}
