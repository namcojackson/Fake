package com.canon.oracle.custapp.custinq.dao;

import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

public class Canon_E193_EmailNotification {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
       try {
		new CanonE193MailNotificationDao().processEmails();
	} catch (CanonCustAppExceptionUtil e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
