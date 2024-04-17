package com.canon.oracle.custapp.util;

import com.canon.oracle.custapp.custinq.dao.CanonE193_JobDataManagementDao;

public class CanonE193MainBJob {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CanonE193_JobDataManagementDao.createMain();
		} catch (Exception exception) {
			System.err
					.println("Error occured CanonE193MainBJob "
							+ exception.getStackTrace());
		}
	}

}
