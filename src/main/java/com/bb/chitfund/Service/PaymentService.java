package com.bb.chitfund.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bb.chitfund.Dto.PaymentDto;
import com.bb.chitfund.Dto.SchemePendingPaymentDto;
import com.bb.chitfund.Dto.SchemeUserPendingPaymentDto;
import com.bb.chitfund.Dto.UserMonthlyPendingPaymentDto;
import com.bb.chitfund.Entity.Payment;
import com.bb.chitfund.Entity.Scheme;
import com.bb.chitfund.Entity.User;
import com.bb.chitfund.Repository.PaymentRepository;
import com.bb.chitfund.Repository.SchemeRepository;
import com.bb.chitfund.Repository.UserRepository;

@Service
public class PaymentService {

	@Autowired
	public PaymentRepository paymentRepo;

	@Autowired
	public UserRepository userRepo;

	@Autowired
	public SchemeRepository schemeRepo;

	@Autowired
	public AssignSchemeService assignSchemeService;

	@Value("${installmentDay}")
	private int installmentDay;

	public String adminPayment(PaymentDto paymentDto) throws Exception {
		String userCode = paymentDto.getUserId();
		String schemeName = paymentDto.getSchemeId();
		String InstallmentDate = paymentDto.getNextInstallmentDate();
		String cutDate = InstallmentDate.substring(0, 7);

		User user = userRepo.findByUserCode(userCode);
		Scheme scheme = schemeRepo.findBySchemeName(schemeName);

		int userId = user.getId();
		int schemeId = scheme.getId();
		String status = "nextInstallment";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String paidDate = formatter.format(date);
		Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(paidDate);

		Optional<Payment> optional = Optional
				.ofNullable(paymentRepo.findPaymentDetails(userId, schemeId, cutDate, status));

		if (optional.isPresent()) {
			Payment payment = optional.get();

			payment.setPaidDate(currentDate);
			payment.setPaymentType("Admin");
			payment.setStatus("Approved");

			long schemeAmount = paymentDto.getInstallmentAmount();
			long collectedSchemeAmount = scheme.getCollectedSchemeAmount();

			collectedSchemeAmount = collectedSchemeAmount + schemeAmount;

			scheme.setCollectedSchemeAmount(collectedSchemeAmount);

			paymentRepo.save(payment);
			schemeRepo.save(scheme);
			return "Admin Payment Done";
		}
		return "Already paid for this month";
	}

	public String userPayment(int paymentId) throws Exception {
		Optional<Payment> paymentRecord = paymentRepo.findById(paymentId);
		if (paymentRecord.isPresent()) {
			Payment payment = paymentRecord.get();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String paidDate = formatter.format(date);
			Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(paidDate);

			payment.setPaidDate(currentDate);
			payment.setPaymentType("User");
			payment.setStatus("Pending");
			paymentRepo.save(payment);

		} else {

			throw new RuntimeException("Invalid Payment ID");
		}
		return "User Payment is Done";
	}

	public String acceptPayment(int paymentId) throws Exception {
		Optional<Payment> paymentRecord = paymentRepo.findById(paymentId);
		if (paymentRecord.isPresent()) {
			Payment payment = paymentRecord.get();
			Scheme scheme = payment.getScheme();

			long schemeAmount = scheme.getPayAmount();
			long collectedSchemeAmount = scheme.getCollectedSchemeAmount();

			collectedSchemeAmount = collectedSchemeAmount + schemeAmount;
			payment.setStatus("Approved");
			scheme.setCollectedSchemeAmount(collectedSchemeAmount);
			paymentRepo.save(payment);
			schemeRepo.save(scheme);
		}
		return "Payment is Accepted";
	}

	public List<PaymentDto> getAcceptPaymentList() {
		String status = "Pending";
		List<Payment> pendingPaymentList = paymentRepo.findAcceptPaymentList(status);
		List<PaymentDto> acceptPayList = new ArrayList<>();
		for (Payment pendingPaymentRec : pendingPaymentList) {

			User user = pendingPaymentRec.getUser();
			Scheme scheme = pendingPaymentRec.getScheme();

			int id = pendingPaymentRec.getId();
			String userName = user.getUserName();
			String userCode = user.getUserCode();
			String schemeName = scheme.getSchemeName();
			long schemeAmount = scheme.getSchemeAmount();
			long installmentAmount = scheme.getPayAmount();
			Date date = pendingPaymentRec.getPaidDate();

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String paidDate = format1.format(date);

			PaymentDto paymentDto = new PaymentDto();

			paymentDto.setId(id);
			paymentDto.setUserName(userName);
			paymentDto.setUserId(userCode);
			paymentDto.setSchemeId(schemeName);
			paymentDto.setSchemeAmount(schemeAmount);
			paymentDto.setInstallmentAmount(installmentAmount);
			paymentDto.setPaidAmountDate(paidDate);

			acceptPayList.add(paymentDto);

		}

		return acceptPayList;
	}

	public List<SchemePendingPaymentDto> schemePendingPayment() throws Exception {

		String status = "nextInstallment";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String paidDate = formatter.format(date);
		Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(paidDate);

		List<Object[]> schemePendingPayList = paymentRepo.schemePendingPaymentList(status, currentDate);

		List<SchemePendingPaymentDto> schemePendingPayments = new ArrayList<>();

		for (Object[] obj : schemePendingPayList) {

			SchemePendingPaymentDto schemePayPending = new SchemePendingPaymentDto();

			String schemeName = obj[0].toString();

			String startDate = obj[1].toString();
			Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);

			String endDate = obj[2].toString();
			Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

			String schemeDuration = obj[3].toString();
			int Duration = Integer.valueOf(schemeDuration);

			String numberOfUser = obj[4].toString();
			int numOfUser = Integer.valueOf(numberOfUser);

			String pendingPaymentCount = obj[5].toString();
			int pendingPayCount = Integer.valueOf(pendingPaymentCount);

			String id = obj[6].toString();
			int schemeId = Integer.valueOf(id);

			schemePayPending.setSchemeName(schemeName);
			schemePayPending.setStartDate(sDate);
			schemePayPending.setEndDate(eDate);
			schemePayPending.setSchemeDuration(Duration);
			schemePayPending.setNumberOfUser(numOfUser);
			schemePayPending.setPendingPaymentCount(pendingPayCount);
			schemePayPending.setId(schemeId);

			schemePendingPayments.add(schemePayPending);

		}

		return schemePendingPayments;
	}

	public List<SchemeUserPendingPaymentDto> getSchemeUserPendingPayment(long schemeId) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String paidDate = formatter.format(date);
		Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(paidDate);

		String status = "nextInstallment";

		List<Object[]> schemeUserPendingPaymentList = paymentRepo.getschemeUserPendingPaymentList(schemeId, currentDate,
				status);

		List<SchemeUserPendingPaymentDto> schemeUserPendingList = new ArrayList<>();

		for (Object[] obj : schemeUserPendingPaymentList) {

			SchemeUserPendingPaymentDto schemeUserPendingPaymentDto = new SchemeUserPendingPaymentDto();

			String id = obj[0].toString();
			int userId = Integer.valueOf(id);

			String userCode = obj[1].toString();

			String userName = obj[2].toString();

			String number = obj[3].toString();
			long Mobile = Long.parseLong(number);

			String pendingCount = obj[4].toString();
			int pendingPaymentCount = Integer.valueOf(pendingCount);

			schemeUserPendingPaymentDto.setUserId(userId);
			schemeUserPendingPaymentDto.setUserCode(userCode);
			schemeUserPendingPaymentDto.setUserName(userName);
			schemeUserPendingPaymentDto.setMobile(Mobile);
			schemeUserPendingPaymentDto.setPendingPaymentCount(pendingPaymentCount);

			schemeUserPendingList.add(schemeUserPendingPaymentDto);

		}

		return schemeUserPendingList;
	}

	public List<UserMonthlyPendingPaymentDto> userMonthlyPending(String userCode, String schemeName) throws Exception {
		long userId = userRepo.findByCode(userCode);
		Scheme scheme = schemeRepo.findBySchemeName(schemeName);
		long schemeId = scheme.getId();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String paidDate = formatter.format(date);
		Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(paidDate);

		List<Object[]> userPendingPayList = paymentRepo.getUserMonthlyPendingpay(userId, schemeId, currentDate);

		List<UserMonthlyPendingPaymentDto> monthlyUserPendingList = new ArrayList<>();
		for (Object[] rec : userPendingPayList) {

			UserMonthlyPendingPaymentDto userMonthlyPendingPayment = new UserMonthlyPendingPaymentDto();
			String installmentDate = rec[0].toString();
			String status = rec[1].toString();

			if (rec[2] != null) {
				String checkDate = rec[2].toString();
				userMonthlyPendingPayment.setPaidDate(checkDate);
			} else {
				userMonthlyPendingPayment.setPaidDate("Not Paid");
			}

			userMonthlyPendingPayment.setInstallmentDate(installmentDate);
			userMonthlyPendingPayment.setStatus(status);
			monthlyUserPendingList.add(userMonthlyPendingPayment);

		}

		return monthlyUserPendingList;
	}

	public List<PaymentDto> getUserPaymentRecord(String userCode, String schemeName1) throws Exception {
		User user = userRepo.findByUserCode(userCode);
		int userId = user.getId();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currDate = formatter.format(date);
		Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(currDate);
		String status = "nextInstallment";

		List<PaymentDto> nextInstallmentUserList = new ArrayList<>();

		List<Payment> payment = new ArrayList<>();
		if (schemeName1 == null || "".equals(schemeName1)) {
			payment = paymentRepo.getlist(userId, currentDate, status);
		} else {
			Scheme scheme = schemeRepo.findBySchemeName(schemeName1);
			int schemeId = scheme.getId();
			payment = paymentRepo.getlistschemeId(userId, schemeId, currentDate, status);
		}

		for (Payment pay : payment) {
			PaymentDto paymentDto = new PaymentDto();

			Scheme scheme = pay.getScheme();

			String schemeName = scheme.getSchemeName();
			long schemeAmount = scheme.getSchemeAmount();
			long payAmount = scheme.getPayAmount();
			int paymentId = pay.getId();
			Date installmentDate = pay.getInstallmentDate();

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = format1.format(installmentDate);

			paymentDto.setId(paymentId);
			paymentDto.setUserId(userCode);
			paymentDto.setSchemeId(schemeName);
			paymentDto.setSchemeAmount(schemeAmount);
			paymentDto.setInstallmentAmount(payAmount);
			paymentDto.setNextInstallmentDate(dateStr);
			paymentDto.setPaymentType("User");
			nextInstallmentUserList.add(paymentDto);

		}
		return nextInstallmentUserList;
	}

	public List<PaymentDto> getUserPaymentHistory(String userCode, String schemeName) {
		User user = userRepo.findByUserCode(userCode);
		int userId = user.getId();
		String status = "Approved";

		List<PaymentDto> userPaymentList = new ArrayList<>();

		List<Payment> payment = new ArrayList<>();

		if (schemeName == null || "".equals(schemeName)) {
			payment = paymentRepo.getPaymentHistoryList(userId, status);
		} else {
			Scheme scheme = schemeRepo.findBySchemeName(schemeName);
			int schemeId = scheme.getId();
			payment = paymentRepo.getPaymentHistroyschemeList(userId, schemeId, status);
		}

		for (Payment pay : payment) {
			PaymentDto paymentDto = new PaymentDto();

			Scheme scheme = pay.getScheme();

			String schemeName1 = scheme.getSchemeName();
			long schemeAmount = scheme.getSchemeAmount();
			long payAmount = scheme.getPayAmount();
			int paymentId = pay.getId();
			Date paidDate = pay.getPaidDate();
			Date installDate=pay.getInstallmentDate();

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = format1.format(paidDate);
			String instalDate=format1.format(installDate);

			paymentDto.setId(paymentId);
			paymentDto.setUserId(userCode);
			paymentDto.setSchemeId(schemeName1);
			paymentDto.setSchemeAmount(schemeAmount);
			paymentDto.setInstallmentAmount(payAmount);
			paymentDto.setInstalDate(instalDate);
			paymentDto.setPaidAmountDate(dateStr);
			paymentDto.setStatus(pay.getStatus());

			userPaymentList.add(paymentDto);

		}
		return userPaymentList;
	}
}
