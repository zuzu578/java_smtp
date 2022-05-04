# java_smtp
java에서 smtp 를 이용하여 메일전송하기 


# 우분투에서 smtp 설치 

sudo apt update
sudo apt-get install openssl sendmail sasl2-bin
sudo sendmailconfig

전부 yes 선택 

sudo vi /etc/mail/sendmail.mc

......
dnl DAEMON_OPTIONS(`Family=inet,  Name=MTA-v4, Port=smtp, Addr=127.0.0.1')dnl
......
dnl DAEMON_OPTIONS(`Family=inet,  Name=MSP-v4, Port=submission, M=Ea, Addr=127.0.0.1')dnl
......

위 두라인을 찾아서 커멘트 (주석 한다 )

sudo vi /etc/mail/local-host-names

......
skyer9.pe.kr
mail.skyer9.pe.kr


위 파일에 도메인을 추가한다.


sudo su -
m4 /etc/mail/sendmail.mc > /etc/mail/sendmail.cf
exit


위 명령으로 sendmail.cf 파일이 생성됩니다.


sudo systemctl restart sendmail

재기동 한다 .


sudo tail -100 /var/spool/mail/root



telnet localhost 25
helo mail_sender
mail from : root@skyer9.pe.kr
rcpt to : root@skyer9.pe.kr
data
subject: 1212
1111
.
quit
# sendmail -t root@skyer9.pe.kr




참조 문헌 : https://www.skyer9.pe.kr/wordpress/?p=468
