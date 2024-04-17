package com.canon.oracle.custapp.util;

import com.canon.oracle.datamgmt.server.CanonE193DataManagementInboxReader;

public class CanonE193DataMgmtInboxReaderBJob {
	public static void main(String[] args) {
		try {
			new CanonE193DataManagementInboxReader().processEmails();
		} catch (Exception exception) {
			System.err
					.println("Error occured CanonE193DataMgmtInboxReaderBJob "
							+ exception.getStackTrace());
		}
	}
}
