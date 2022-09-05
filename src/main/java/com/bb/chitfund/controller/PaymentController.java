package com.bb.chitfund.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bb.chitfund.dto.PaymentDto;
import com.bb.chitfund.dto.SchemePendingPaymentDto;
import com.bb.chitfund.dto.SchemeUserPendingPaymentDto;
import com.bb.chitfund.dto.UserMonthlyPendingPaymentDto;
import com.bb.chitfund.service.PaymentService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class PaymentController {

	@Autowired
	public PaymentService paymentService;

	@PostMapping("/adminPayment/payment")
	@ResponseBody
	public String adminPayment(@RequestBody PaymentDto paymentDto) throws ParseException {
		return paymentService.adminPayment(paymentDto);

	}

	@GetMapping("/getUserPaymentList/duePaymentList")
	@ResponseBody
	public List<PaymentDto> getUserPaymentRecord(@RequestParam(name = "userCode") String userCode,
			@RequestParam(name = "schemeName") String schemeName) throws ParseException {
		return paymentService.getUserPaymentRecord(userCode, schemeName);

	}

	@GetMapping("/userPayment/payment")
	@ResponseBody
	public String userPayment(@RequestParam(name = "paymentId") int paymentId) throws ParseException {
		return paymentService.userPayment(paymentId);
	}

	@GetMapping("/acceptPaymentList/userList")
	@ResponseBody
	public List<PaymentDto> getAcceptPaymentList() {
		return paymentService.getAcceptPaymentList();
	}

	@GetMapping("/acceptPayment/admin")
	@ResponseBody
	public String acceptPayment(@RequestParam(name = "paymentId") int paymentId)  {
		return paymentService.acceptPayment(paymentId);

	}

	@GetMapping("/schemePaymentpendingList/schemePending")
	@ResponseBody
	public List<SchemePendingPaymentDto> schemePaymentpendingList() throws ParseException {
		return paymentService.schemePendingPayment();

	}

	@GetMapping("/schemeUserPendingPayment/schemeUser")
	@ResponseBody
	public List<SchemeUserPendingPaymentDto> getSchemeUserPendingPayment(@RequestParam(name = "schemeId") long schemeId)
			throws ParseException {
		return paymentService.getSchemeUserPendingPayment(schemeId);

	}

	@GetMapping("/userMonthlyPending/userPendingPayment")
	@ResponseBody
	public List<UserMonthlyPendingPaymentDto> userMonthlyPending(@RequestParam(name = "userCode") String userCode,
			@RequestParam(name = "schemeName") String schemeName) throws ParseException {
		return paymentService.userMonthlyPending(userCode, schemeName);

	}

	@GetMapping("/getUserPaymentHistoryList/paymentHistoryList")
	@ResponseBody
	public List<PaymentDto> getUserPaymentHistory(@RequestParam(name = "userCode") String userCode,
			@RequestParam(name = "schemeName") String schemeName) {
		return paymentService.getUserPaymentHistory(userCode, schemeName);
		

	}

}
