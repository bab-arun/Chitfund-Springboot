package com.bb.chitfund.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bb.chitfund.dto.SchemePendingPaymentDto;
import com.bb.chitfund.repository.UserRepository;

@Service
@EnableScheduling
public class EmailSenderService {

	@Autowired
	public JavaMailSender mailSender;

	@Autowired
	public UserRepository userRepo;

	@Value("${installmentDay}")
	private int installmentDay;

	@Autowired
	public PaymentService paymentService;
	
	private String fromEmail="bytesandbinarieschitfund@gmail.com";

	@Scheduled(cron = "${userNotificationDate}")
	public void userSendEmail() throws MessagingException {

		String status = "nextInstallment";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currDate = formatter.format(date);
		String cutDate = currDate.substring(0, 7);
		String installmentDate = cutDate + "-" + installmentDay;

		List<String[]> userEmailList = userRepo.getEmails(status, cutDate);

		for (String[] mail : userEmailList) {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(fromEmail);
			helper.setTo(mail);
			helper.setSubject("Alert! Chitfund Installment Payment For " + mail[1] + "!!");
			helper.setText("Hi " + mail[2]
					+ " , This is a Alert Notification to pay Installment amount for your chitscheme for this month.Please pay on before "
					+ installmentDate + ".");
			mailSender.send(message);

		}

	}

	@Scheduled(cron = "${adminNotificationDate}")
	public void adminSendEmail() throws  MessagingException, ParseException   {

	    String closingTag="</td>";
		List<SchemePendingPaymentDto> schemePendingPayments = paymentService.schemePendingPayment();

		///////////////////////// content1

		StringBuilder htmlContent1 = new StringBuilder("<!DOCTYPE html>\r\n" + "\r\n" + "<html>\r\n" + "\r\n" + "\r\n"
				+ "\r\n" + "<head>\r\n" + "\r\n" + "    <style>\r\n" + "\r\n" + "        table,\r\n" + "\r\n"
				+ "        td {\r\n" + "\r\n" + "            border: 1px solid;\r\n"
				+ "            text-align:center;\r\n" + "\r\n" + "            \r\n" + "\r\n" + "        }\r\n" + "\r\n"
				+ "        \r\n" + "\r\n" + "        th{\r\n" + "\r\n" + "        border: 1px solid;\r\n" + "\r\n"
				+ "        width:20%\r\n" + "\r\n" + "        }\r\n" + "\r\n" + "    </style>\r\n" + "\r\n"
				+ "</head>\r\n" + "\r\n" + "    <body>\r\n" + "\r\n" + "        <table class=\"table\">\r\n" + "\r\n"
				+ "            <tr >\r\n" + "\r\n" + "                <th class=\"th\" >Scheme Name</th>\r\n" + "\r\n"
				+ "                <th  class=\"th\">Start Date</th>\r\n" + "\r\n"
				+ "                <th  class=\"th\">End Date</th>\r\n" + "\r\n"
				+ "                <th  class=\"th\" >Number of Users</th>\r\n" + "\r\n"
				+ "                <th  class=\"th\" >Pending Pay Count</th></tr>\r\n" + "                \r\n"
				+ "               \r\n" + "\r\n" + "");

		///////////////////////////////// content 2
		String navigation = "http://localhost:3000/pendingpayment";
		for (SchemePendingPaymentDto pendingList : schemePendingPayments) {

			Date sDate = pendingList.getStartDate();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = format.format(sDate);

			Date eDate = pendingList.getEndDate();
			String endDate = format.format(eDate);

			htmlContent1.append("<tr>");
			htmlContent1.append("<td>").append(pendingList.getSchemeName()).append(closingTag);
			htmlContent1.append("<td>").append(startDate).append(closingTag);
			htmlContent1.append("<td>").append(endDate).append(closingTag);
			htmlContent1.append("<td>").append(pendingList.getNumberOfUser()).append(closingTag);
			String hrefUrl = "<td><a href=" + "\"" + navigation + "\">";
			htmlContent1.append(hrefUrl).append(pendingList.getPendingPaymentCount()).append("</a></td>");

			htmlContent1.append("</tr>");

		}

		/////////////////////////////// content 3

		String htmlContents = htmlContent1.append("        </table>\r\n" + "\r\n" + "    </body>\r\n" + "</html>")
				.toString();

		/////////////////////////////////////////

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(fromEmail);
		helper.setTo(fromEmail);
		helper.setSubject("Notification for Admin with Pending Chitfund Payment List");
		helper.setText(htmlContents, true);
		mailSender.send(message);

	}
}