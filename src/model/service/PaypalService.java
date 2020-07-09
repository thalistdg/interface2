package model.service;

public class PaypalService implements OnlinePaymentService{
	
	private static final double FEE_PERCENTAGE = 0.02;
	private static final double MONTHLY_INTEREST = 0.01;
	
	@Override
	public double paymentFee(double value) {
		return value * FEE_PERCENTAGE;
	}
	
	@Override
	public double interest(double value, int months) {
		return value * MONTHLY_INTEREST * months;
	}
}
