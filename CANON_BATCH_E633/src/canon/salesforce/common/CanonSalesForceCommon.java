															   
package canon.salesforce.common;

import com.canon.usa.s21.integration.service.salesforce.Soap;
import com.canon.usa.s21.integration.service.salesforce.UnexpectedErrorFault;
import com.canon.usa.s21.integration.service.salesforce.enterprise.*;
import com.canon.usa.s21.integration.service.salesforce.wrapper.SalesforceProxy;

import java.util.Properties;

	public class CanonSalesForceCommon implements CanonSalesForceConstants{

	private Properties props  = null;
	private String uniqueDate = null;
	

	public static void main(String[] args) {
		CanonSalesForceCommon obj = new CanonSalesForceCommon();
	}

	public CanonSalesForceCommon() {
		try {
		      loadProperties();
		      intializeEnv();
		    }
		    catch (Exception e) {
		      //log.info("Exception CanonE633LFSPPSSFCommon "+e);
		       System.out.println(e.getMessage());
		    }
	}
	
	public String[] getLoginCredentials(String handler){
		String[] loginCredentials = new String[2];
		
		System.out.println("getLoginCredentials::handler: " +handler);
		
		try {
		
			if (handler.contains("LFS")) {
				loginCredentials[0] = props.getProperty(LFS_SALESFORCE_USERNAME);
				loginCredentials[1] = props.getProperty(LFS_SALESFORCE_PASSWORD);
			}else if (handler.contains("PPS")) {
				loginCredentials[0] = props.getProperty(PPS_SALESFORCE_USERNAME);
				loginCredentials[1] = props.getProperty(PPS_SALESFORCE_PASSWORD);
			}else {
				loginCredentials[0] = props.getProperty(CBS_SALESFORCE_USERNAME);
				loginCredentials[1] = props.getProperty(CBS_SALESFORCE_PASSWORD);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return loginCredentials;
	}
	
	public String getProperty(String strPropertyName) {
		String strPropertyValue = "";
		try {
			strPropertyValue = props.getProperty(strPropertyName);
		}
		catch (Exception e) {
			System.err.println("Exception: "+e.getMessage());
			//System.out.println(e.getMessage());
		}
		return strPropertyValue;
	}

	private void intializeEnv() throws Exception {
		System.setProperty("https.proxyHost", props.getProperty(CBS_PROXY_SERVER));
		System.setProperty("https.proxyPort", props.getProperty(CBS_PROXY_PORT));
		uniqueDate = getUniqueDate();
		System.out.println("uniqueDate "+uniqueDate);
		//System.out.println("props.getProperty(CBS_SALESFORCE_USERNAME) "+props.getProperty(CBS_SALESFORCE_USERNAME));
		//System.out.println("props.getProperty(CBS_SALESFORCE_PASSWORD) "+props.getProperty(CBS_SALESFORCE_PASSWORD));
	}

	private  void loadProperties() throws Exception {
		props = new Properties();

		StringBuffer tempStr = new StringBuffer(10);
		tempStr.append("canon/salesforce/bulk/");
		tempStr.append(SALESFORCE_PROP);
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(tempStr.toString()));
		}
		catch (Exception ieExp) {
			props = null;

			System.err.println("Exception"+ieExp);
			System.err.println("Exception in loading file "+tempStr.toString());
			// ieExp.printStackTrace();
			System.exit(1);
		}
	}

	public static String getUniqueDate() {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat sdfmm = new java.text.SimpleDateFormat("MMddyyyyHHmmss");

		String frmtDate = sdfmm.format(date);
		return frmtDate;
	}


}

