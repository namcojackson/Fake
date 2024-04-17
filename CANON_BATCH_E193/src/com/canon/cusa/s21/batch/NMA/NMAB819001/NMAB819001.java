package com.canon.cusa.s21.batch.NMA.NMAB819001;

import com.canon.oracle.datamgmt.server.CanonE193DataManagementInboxReader;

public class NMAB819001 {

	public static void main(String args[]){
		new CanonE193DataManagementInboxReader().processEmails();
		// TODO separate process and archive program
		// new CanonE193DataManagementInboxReader().archiveEmails();
	}
}
