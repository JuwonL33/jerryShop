package com.jerry.jerryShop.util;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jerry.jerryShop.member.Member;
import com.jerry.jerryShop.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
	
	private final PasswordEncoder passwordEncoder;
	
	private final MemberRepository memberRepository;
	
	private final JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from_addr;
	
	@Transactional
	public Mail createMailAndChangePassword(String email, String username) {
		String str = getTempPassword();
		Mail mail = new Mail();
		mail.setAddress(email);
		mail.setTitle(username+"님의 JerryShop 임시 비밀번호 안내 이메일입니다.");
		mail.setMessage("안녕하세요. JerryShop 임시비밀번호 안내 이메일입니다."
				+ "[" + username + "]" +"님의 임시 비밀번호는 "
				+ str + " 입니다.");
		updatePassword(str, username, email);
		return mail;
	}
	
	public String getTempPassword() {
		char[] charSet = new char[] { '0','1','2','3','4','5','6','7','8','9','0',
				'A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
		String tempPassword = "";
		
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			tempPassword += charSet[idx];
		}
		return tempPassword;
	}
	
	public void updatePassword(String tempPassword, String username, String email) {
		String password = passwordEncoder.encode(tempPassword);
		Optional<Member> _member = this.memberRepository.findByUsernameAndEmail(username, email);
		if(!_member.isEmpty()) {
			Member member = _member.get();
			member.setPassword(password);
			this.memberRepository.save(member);
		}
	}
	
	public void sendMail(Mail mail) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getAddress());
		message.setFrom(from_addr);
		message.setSubject(mail.getTitle());
		message.setText(mail.getMessage());
		log.info("비밀번호 찾기 이메일 전송 완료");
		
		this.mailSender.send(message);
	}
}
