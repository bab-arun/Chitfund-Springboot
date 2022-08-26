package com.bb.chitfund.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bb.chitfund.Dto.AssignUserListDto;
import com.bb.chitfund.Entity.AssignScheme;
import com.bb.chitfund.Entity.Payment;
import com.bb.chitfund.Entity.Scheme;
import com.bb.chitfund.Entity.User;
import com.bb.chitfund.Repository.AssignSchemeRepository;
import com.bb.chitfund.Repository.PaymentRepository;
import com.bb.chitfund.Repository.SchemeRepository;
import com.bb.chitfund.Repository.UserRepository;

@Service
public class AssignSchemeService {

	@Autowired
	public UserRepository userRepo;

	@Autowired
	public SchemeRepository schemeRepo;

	@Autowired
	public AssignSchemeRepository assignSchemeRepo;

	@Autowired
	public PaymentRepository paymentRepo;

	@Value("${installmentDay}")
	private int installmentDay;

	public void saveAssignScheme(AssignUserListDto assignUserListDto) throws Exception {

		String schemeName = assignUserListDto.getSchemeName();
		List<String> userCodeList = assignUserListDto.getUserCodeList();

		Scheme scheme = schemeRepo.findBySchemeName(schemeName);

		long assignUserCount = scheme.getAssignScheme().size();
		long userCount = scheme.getNumberOfUser();

		for (String userCode : userCodeList) {
			if (assignUserCount < userCount) {
				AssignScheme assignScheme = new AssignScheme();

				User user = userRepo.findByUserCode(userCode);
				assignScheme.setUser(user);
				assignScheme.setScheme(scheme);
				assignSchemeRepo.save(assignScheme);

				Date date = scheme.getStartDate();

				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.set(Calendar.DAY_OF_MONTH, installmentDay);
				Date installmentDate = cal.getTime();

				nextInstallmentPaymentSetter(user, scheme, installmentDate);

				assignUserCount++;

			}
		}

	}

	public void nextInstallmentPaymentSetter(User user, Scheme scheme, Date nextInstallmentDate) throws Exception {

		int schemeDuration = scheme.getSchemeDuration();

		Date tempDate = nextInstallmentDate;

		for (int i = 0; i < schemeDuration; i++) {
			Payment payment = new Payment();

			payment.setUser(user);
			payment.setScheme(scheme);
			payment.setInstallmentDate(tempDate);
			payment.setStatus("nextInstallment");
			paymentRepo.save(payment);

			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDate);
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
			Date Date = cal.getTime();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = format1.format(Date);

			tempDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

		}
	}

}
