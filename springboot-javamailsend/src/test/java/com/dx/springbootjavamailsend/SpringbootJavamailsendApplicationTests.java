package com.dx.springbootjavamailsend;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.StringWriter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJavamailsendApplicationTests {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	/**
	 * 发送简单的邮件
	 */
	@Test
	public void sendMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("490468784@qq.com");
		message.setTo("490468784@qq.com");
		message.setSubject("测试邮件");
		message.setText("你好，秀儿");

		mailSender.send(message);
	}

	/**
	 * 发送带附件的邮件
	 */
	@Test
	public void sendAttachmentsMail() throws MessagingException {
		File file = new File("C:\\Users\\wudongchun\\Desktop\\测试文本.txt");
		FileSystemResource systemResource = new FileSystemResource(file);

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("490468784@qq.com");
		helper.setTo("490468784@qq.com");
		helper.setSubject("测试邮件(带附件)");
		helper.setText("你好，秀儿");
//		helper.addAttachment("附件1.txt", file);
		helper.addAttachment("附件1.txt", systemResource);

		mailSender.send(mimeMessage);
	}

	/**
	 * 发送正文静态资源邮件
	 */
	@Test
	public void sendInLineMail() throws MessagingException {
		FileSystemResource systemResource = new FileSystemResource(new File("C:\\Users\\wudongchun\\Desktop\\测试.jpg"));

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("490468784@qq.com");
		helper.setTo("1159427697@qq.com");
		helper.setSubject("测试邮件(静态资源)");
		helper.setText("hello，<html><body><img src=\"cid:pic\"></body></html>", true);
		helper.addInline("pic", systemResource);

		mailSender.send(mimeMessage);
	}

	/**
	 * 模板邮件
	 */
	@Test
	public void snedTemplateMail() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		VelocityContext contex = new VelocityContext();
		contex.put("username", "秀儿");
		StringWriter stringWriter = new StringWriter();
		// 需要注意第1个参数要全路径，否则会抛异常
		velocityEngine.mergeTemplate("/templates/template.vm", "UTF-8", contex, stringWriter);

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("490468784@qq.com");
		helper.setTo("490468784@qq.com");
		helper.setSubject("模板邮件测试");
		helper.setText(stringWriter.toString(), true);

		mailSender.send(mimeMessage);
	}
}
