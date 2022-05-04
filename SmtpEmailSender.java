public class SmtpEmailSender {
  
   
     String SMTP_USERNAME = "test01";
     String SMTP_PASSWORD = "test01";
     String HOST = "127.0.0.1";
     
     int PORT = 25;
  
 
  public EmailVO sendMail2(org.json.JSONObject jsonObject , int status) throws MessagingException, JSONException, UnsupportedEncodingException {
		
		EmailVO vo = new EmailVO();
		Properties props = System.getProperties();
		String TO = jsonObject.get("user_email").toString();  
//		 
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.port", PORT); 
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.auth", "true");
		
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.port", PORT);
		
		  Session smtpSession = Session.getInstance(props, new javax.mail.Authenticator() {
	    		protected PasswordAuthentication getPasswordAuthentication() {
	    			return new PasswordAuthentication(SMTP_USERNAME,SMTP_PASSWORD);
	    		}
	    	});  
		  
		  try {
			
				MimeMessage message = new MimeMessage(smtpSession);
				
				message.setFrom(new InternetAddress(jsonObject.get("sender").toString(),jsonObject.get("sender_name").toString())); // 발신자 설정
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO)); // 수신자 설정
				
				message.setSubject(jsonObject.get("subject").toString()); // 제목
				
				message.setContent(jsonObject.get("map_content").toString(), "text/html; charset=UTF-8"); // 메일 내용
				
				Transport.send(message); // 전송
				
				
			} catch(AddressException e) {
				e.printStackTrace();
			} catch(MessagingException e) {
				e.printStackTrace();
			}
		  
        return vo;
    }
		
  
  
}
