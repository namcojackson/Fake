package canon.apps.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.activation.DataSource;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import canon.apps.common.CanonEmailUtil;

public class TestCanonEmail {

	static String TO_ADDRESS="jwang_consultant@cusa.canon.com";
	static String CC_ADDRESS="smittal@cusa.canon.com";
	
	public static void main(String[] args) {
		TestCanonEmail t = new TestCanonEmail();
		try {
			t.simpleEmail();
			t.emailAttachment();
			t.htmlEmail();
			t.streamDataSource();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void simpleEmail() throws EmailException {
		
		CanonEmailUtil.createSimpleEmail().setSubject("Test Simple Mail")
				.setMsg("This is a test mail ... :-)")
				.addTo(TO_ADDRESS)
//				.addTo("abc@test.com","abc2@test.com","abc3@test.com")
				.addCc(CC_ADDRESS)
//				.addBcc("bcc@test.com")
				.send();

	}

	public void emailAttachment() throws EmailException, MalformedURLException {
		CanonEmailUtil
				.createMultiPartEmail()
				.attach(new File("C:\\Users\\Q05058\\Documents\\iRADV_Security_WhitePaper.pdf"))
				.attach(new URL(
						"http://www.usa.canon.com/assets/sys/images/canon_main_logo.gif"),
						"Canon logo", "Canon logo")
				.setSubject("Test Mail attachment")
				.setMsg("This is a test mail ... :-)")
				.addCc(CC_ADDRESS)
				.addTo(TO_ADDRESS)
				.send();
	}

	public void  htmlEmail() throws EmailException, MalformedURLException{
		
		HtmlEmail  email=CanonEmailUtil.createHtmlEmail();
		email.setSubject("Test Html Formatted  Mail")
			.setMsg("This is a test mail ... :-)")
			.addCc(CC_ADDRESS)
			.addTo(TO_ADDRESS);
		URL url = new URL("http://www.usa.canon.com/assets/sys/images/canon_main_logo.gif");
		String cid = email.embed(url, "Canon logo");
		email.setHtmlMsg("<html>The Canon logo - <img src=\"cid:"+cid+"\"></html>");
		email.send();		

	}
	
	public void streamDataSource() throws EmailException, IOException {
		DataSource	ds=	new DataSource(){
			@Override
			public String getContentType() {
				 return "application/pdf";
			}
			@Override
			public InputStream getInputStream() throws IOException {
				// http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4267294
				// JDK-4267294 : InputStream must be created in getInputStream() of the DataSource, not passed in
				InputStream is=new FileInputStream("C:\\Users\\Q05058\\Documents\\iRADV_Security_WhitePaper.pdf");
				return is;
			}
			@Override
			public String getName() {
				return "iRADV_Security_WhitePaper.pdf";
			}
			@Override
			public OutputStream getOutputStream() throws IOException {
				return null;
			}
		};
		
		CanonEmailUtil
				.createMultiPartEmail()
				.attach(ds,null,"iRADV Security WhitePaper")
				.setSubject("Test Mail Stream Data Source")
				.setMsg("This is a test mail ... :-)")
				.addTo(TO_ADDRESS)
				.send();
	}
	
}
