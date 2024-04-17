package canon.apps.common;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * Canon Email Utility based on Apache Commons Email 1.4 release
 * 
 * @see TestCanonEmail for examples
 */
public class CanonEmailUtil {

/*	public static Email init(Email email){
		email.setHostName("varhpr198.cusa.canon.com");  //mail01.cusa.canon.com
		email.setAuthenticator(new DefaultAuthenticator("", ""));
		try {
			email.setFrom("noreply@csa.canon.com", "Canon Solutions America");
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return email;
	}
*/	public static Email init(Email email){
		System.out.println("in BATCH CanonEmailUtil ");
		String hostName = CanonCustomProfile.getSystemProfileValue("CANON_EMAIL_SER_HOST_NAME");
		System.out.println("in BATCH CanonEmailUtil hostName is "+hostName);
		email.setHostName(hostName);  //"varhpr198.cusa.canon.com");  //mail01.cusa.canon.com
		try {
			//email.setFrom("S21_Operation@cusa.canon.com", "S21 CSA"); commented for  QC51405
			email.setFrom("noreply@csa.canon.com", "S21 CSA");
		} catch (EmailException e) {
			e.printStackTrace();
			System.out.println("in BATCH CanonEmailUtil error out");
		}
		return email;
	}	
	
	/**
	 * Usage example:
	 *	CanonEmailUtil.createSimpleEmail().setSubject("Test Simple Mail")
	 *		.setMsg("This is a test mail ... :-)")
	 *		.addTo(TO_ADDRESS)
	 *		.addTo("abc@test.com","abc2@test.com","abc3@test.com")
	 *		.addCc(CC_ADDRESS)
	 *		.addBcc("bcc@test.com")
	 *		.send(); 
	 */
	public static Email createSimpleEmail(){
		return init(new SimpleEmail());
	}
	
	/**
	 * Usage example
	 *	CanonEmailUtil
	 *		.createMultiPartEmail()
	 *		.attach(new File("invoice.pdf"))
	 * 		.attach(new URL("http://www.usa.canon.com/assets/sys/images/canon_main_logo.gif"),"Canon logo", "Canon logo")
	 *		.attach(dataSource, name, description)
	 *		.setSubject("Test Mail attachment")
	 *		.setMsg("This is a test mail ... :-)")
	 *		.addTo(TO_ADDRESS).send();
	 * 
	 */
	public static MultiPartEmail createMultiPartEmail(){
		MultiPartEmail email=new MultiPartEmail();
		init(email);
		return email;
	}
	
	/**
	 * Usage example:	
	 *  HtmlEmail  email=CanonEmailUtil.createHtmlEmail();
	 *	email.setSubject("Test Html Formatted  Mail")
	 *		.setMsg("This is a test mail ... :-)")
	 *		.addTo(TO_ADDRESS);
	 *	URL url = new URL("http://www.usa.canon.com/assets/sys/images/canon_main_logo.gif");
	 *	String cid = email.embed(url, "Canon logo");
	 *	email.setHtmlMsg("<html>The Canon logo - <img src=\"cid:"+cid+"\"></html>");
	 *	email.send();		
 	 */
	public static HtmlEmail createHtmlEmail(){
		HtmlEmail email=new HtmlEmail ();
		init(email);
		return email;
	}
	
	public static boolean isProd(){
		return "Y".equals(CanonProfile.getStringValue("IS_PROD_SYSTEM", "N"));
	}
	
}
