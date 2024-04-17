package com.canon.cusa.s21.batch.ZZW.ZZWB009001;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import parts.dbcommon.EZDDBCICarrier;

public class ZZWB009001Test {
	private static final String GLOBAL_COMPANY_CODE = "ACY";

	@BeforeClass
	@SuppressWarnings("unchecked")
	public static void setUpBeforeClass() throws Exception {
		// 環境変数を設定
		Class<?> clazz = Class.forName("java.lang.ProcessEnvironment");
		Field field = clazz.getDeclaredField("theCaseInsensitiveEnvironment");
		field.setAccessible(true);

		Map<String, String> environmentMap = (Map<String, String>) field.get(null);
		environmentMap.put("GLBL_CMPY_CD", GLOBAL_COMPANY_CODE);
		environmentMap.put("USER_COMPANY_CD", GLOBAL_COMPANY_CODE);

		EZDDBCICarrier.initBatch("TestS21PDFMerge", new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()), null, "YAMA");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			ZZWB009001 batch = new ZZWB009001();
			batch.executeBatch(ZZWB009001.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
