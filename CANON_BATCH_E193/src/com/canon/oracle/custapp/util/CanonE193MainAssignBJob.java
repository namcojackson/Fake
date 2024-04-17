package com.canon.oracle.custapp.util;


import com.canon.oracle.custapp.custinq.dao.CanonE193_JobDataManagementDao;

public class CanonE193MainAssignBJob {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CanonE193_JobDataManagementDao.mainAssign();
		} catch (Exception exception) {
			System.err
					.println("Error occured CanonE193MainAssignBJob "
							+ exception.getStackTrace());
		}
	}

}
