package model.service;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public OnlinePaymentService getOnlinePaymentService() {
		return onlinePaymentService;
	}

	public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, int months) {
		
		Calendar cal = Calendar.getInstance();
		
		double baseValue = contract.getTotalValue()/months;
		
		for (int i = 1;i <= months;i++) {
			cal.setTime(contract.getDate());
			cal.add(Calendar.MONTH, i);
			Date dueDate = cal.getTime();
			
			double amount = baseValue + onlinePaymentService.interest(baseValue, i);
			
			amount = amount + onlinePaymentService.paymentFee(amount);
			
			contract.addInstallment(new Installment(dueDate, amount));
		}
		
	}
}
