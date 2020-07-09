package model.service;

public interface OnlinePaymentService {
	
	double paymentFee(double value);
	double interest(double value, int month);
}
