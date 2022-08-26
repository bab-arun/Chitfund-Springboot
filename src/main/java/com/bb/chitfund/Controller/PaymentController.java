package com.bb.chitfund.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bb.chitfund.Dto.PaymentDto;
import com.bb.chitfund.Dto.SchemePendingPaymentDto;
import com.bb.chitfund.Dto.SchemeUserPendingPaymentDto;
import com.bb.chitfund.Dto.UserMonthlyPendingPaymentDto;
import com.bb.chitfund.Service.PaymentService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class PaymentController {

	@Autowired
	public PaymentService paymentService;

	@PostMapping("/adminPayment/payment")
	@ResponseBody
	public String adminPayment(@RequestBody PaymentDto paymentDto) throws Exception {
		String result = paymentService.adminPayment(paymentDto);
		return result;
	}

	@GetMapping("/getUserPaymentList/duePaymentList")
	@ResponseBody
	public List<PaymentDto> getUserPaymentRecord(@RequestParam(name = "userCode") String userCode,
			@RequestParam(name = "schemeName") String schemeName) throws Exception {
		List<PaymentDto> nextInstallmentUserList = paymentService.getUserPaymentRecord(userCode, schemeName);
		return nextInstallmentUserList;

	}

	@GetMapping("/userPayment/payment")
	@ResponseBody
	public String userPayment(@RequestParam(name = "paymentId") int paymentId) throws Exception {
		String result = paymentService.userPayment(paymentId);
		return result;
	}

	@GetMapping("/acceptPaymentList/userList")
	@ResponseBody
	public List<PaymentDto> getAcceptPaymentList() {
		List<PaymentDto> acceptPayList = paymentService.getAcceptPaymentList();
		return acceptPayList;
	}

	@GetMapping("/acceptPayment/admin")
	@ResponseBody
	public String acceptPayment(@RequestParam(name = "paymentId") int paymentId) throws Exception {
		String result = paymentService.acceptPayment(paymentId);
		return result;
	}

	@GetMapping("/schemePaymentpendingList/schemePending")
	@ResponseBody
	public List<SchemePendingPaymentDto> schemePaymentpendingList() throws Exception {
		List<SchemePendingPaymentDto> schemePendingPayment = paymentService.schemePendingPayment();
		return schemePendingPayment;
	}

	@GetMapping("/schemeUserPendingPayment/schemeUser")
	@ResponseBody
	public List<SchemeUserPendingPaymentDto> getSchemeUserPendingPayment(@RequestParam(name = "schemeId") long schemeId)
			throws Exception {
		List<SchemeUserPendingPaymentDto> schemeUserPendingPaymentList = paymentService
				.getSchemeUserPendingPayment(schemeId);
		return schemeUserPendingPaymentList;
	}

	@GetMapping("/userMonthlyPending/userPendingPayment")
	@ResponseBody
	public List<UserMonthlyPendingPaymentDto> userMonthlyPending(@RequestParam(name = "userCode") String userCode,
			@RequestParam(name = "schemeName") String schemeName) throws Exception {
		List<UserMonthlyPendingPaymentDto> userMonthlyPendingList = paymentService.userMonthlyPending(userCode,
				schemeName);
		return userMonthlyPendingList;
	}

	@GetMapping("/getUserPaymentHistoryList/paymentHistoryList")
	@ResponseBody
	public List<PaymentDto> getUserPaymentHistory(@RequestParam(name = "userCode") String userCode,
			@RequestParam(name = "schemeName") String schemeName) throws Exception {
		List<PaymentDto> nextInstallmentUserList = paymentService.getUserPaymentHistory(userCode, schemeName);
		return nextInstallmentUserList;

	}

}
