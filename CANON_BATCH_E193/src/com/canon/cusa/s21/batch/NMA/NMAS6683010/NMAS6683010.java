package com.canon.cusa.s21.batch.NMA.NMAS6683010;

import com.canon.oracle.datamgmt.server.CanonE193DataManagementInboxReader;

public class NMAS6683010 {

	public static void main(String args[]){
		new CanonE193DataManagementInboxReader().processEmails();
		// TODO separate process and archive program
		// new CanonE193DataManagementInboxReader().archiveEmails();
	}
}
