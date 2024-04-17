/*
 * Copyright (c) 2022 Canon USA Inc. All rights reserved.
 * Original Author: Kohei Yamamoto Company: Fujitsu Limited Date: August 1, 2022
 */
package com.canon.cusa.s21.batch.ZZW.ZZWB009001;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import parts.common.EZDSystemEnv;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDConnectionMgr;
import business.db.PDF_MERGE_EML_ADDRTMsg;
import business.db.PDF_MERGE_EML_OUT_OPTTMsg;
import business.db.PDF_MERGE_FILE_INP_OPTTMsg;
import business.db.PDF_MERGE_RQSTTMsg;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.pdf.dao.PdfMergeQuery;

/**
 * Batch that remove expired PDF merge results.
 * 
 * @author Kohei Yamamoto
 */
public class ZZWB009001 extends S21BatchMain {
	/** Logger */
	private static final S21Logger logger = S21LoggerFactory.getLogger(ZZWB009001.class);

	/** Root folder path of NAS */
	private String nasRootFolder;
	/** Hold quentity of PDF files (hours) */
	private int fileHldQty;
	/** Number of normal record */
	private int normalRecCnt;
	/** Number of error record */
	private int errRecCnt;

	/**
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        new ZZWB009001().executeBatch(ZZWB009001.class.getSimpleName());
    }

	/*
	 * (非 Javadoc)
	 * 
	 * @see com.canon.cusa.s21.framework.batch.S21BatchMain#initRoutine()
	 */
	@Override
	protected void initRoutine() {
		nasRootFolder = EZDSystemEnv.getString("S21_PDF_MRG_MOUNT_NAS_PATH");
		if (nasRootFolder.endsWith("/")) {
			nasRootFolder = nasRootFolder.replaceFirst("/+$", "");
		}

		fileHldQty = EZDSystemEnv.getInt("S21_PDF_MRG_FILE_HLD_QTY", 0);

		normalRecCnt = 0;
		errRecCnt = 0;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see com.canon.cusa.s21.framework.batch.S21BatchMain#mainRoutine()
	 */
	@Override
	protected void mainRoutine() {
		// Get global company code
		String glblCmpyCd = getGlobalCompanyCode();

		PdfMergeQuery pdfMergeQuery = new PdfMergeQuery();

		// Select record from PDF_MERGE_RQST
		List<PDF_MERGE_RQSTTMsg> pdfMergeRqstList = pdfMergeQuery.selectPdfMergeRqstByProcEndTs(glblCmpyCd, getTargetProcEndTs());

		for (PDF_MERGE_RQSTTMsg pdfMergeRqst : pdfMergeRqstList) {
			try {
				long pdfMergeRqstPk = pdfMergeRqst.pdfMergeRqstPk.getValue().longValue();

				// Select record from PDF_MERGE_FILE_INP_OPT
				List<PDF_MERGE_FILE_INP_OPTTMsg> pdfMergeFileInpOptList = pdfMergeQuery.selectPdfMergeFileInpOptByPdfMergeRqstPk(glblCmpyCd, pdfMergeRqstPk);

				// Select record from PDF_MERGE_EML_OUT_OPT
				List<PDF_MERGE_EML_OUT_OPTTMsg> pdfMergeEmlOutOptList = pdfMergeQuery.selectPdfMergeEmlOutOptByPdfMergeRqstPk(glblCmpyCd, pdfMergeRqstPk);

				for (PDF_MERGE_EML_OUT_OPTTMsg pdfMergeEmlOutOpt : pdfMergeEmlOutOptList) {
					// Select record from PDF_MERGE_EML_ADDR
					long pdfMergeEmlOutOptPk = pdfMergeEmlOutOpt.pdfMergeEmlOutOptPk.getValue().longValue();
					List<PDF_MERGE_EML_ADDRTMsg> pdfMergeEmlAddrList = pdfMergeQuery.selectPdfMergeEmlAddrByPdfMergeEmlOutOptPk(glblCmpyCd, pdfMergeEmlOutOptPk);

					// Delete record from PDF_MERGE_EML_ADDR
					S21FastTBLAccessor.removePhysical(pdfMergeEmlAddrList.toArray(new EZDTMsg[pdfMergeEmlAddrList.size()]));
				}

				// Delete record from PDF_MERGE_EML_OUT_OPT
				S21FastTBLAccessor.removePhysical(pdfMergeEmlOutOptList.toArray(new EZDTMsg[pdfMergeEmlOutOptList.size()]));

				// Delete record from PDF_MERGE_FILE_INP_OPT
				S21FastTBLAccessor.removePhysical(pdfMergeFileInpOptList.toArray(new EZDTMsg[pdfMergeFileInpOptList.size()]));

				// Delete record from PDF_MERGE_RQST
				S21FastTBLAccessor.removePhysical(new EZDTMsg[] { pdfMergeRqst });

				// NASのファイル削除
				File directory = new File(nasRootFolder + "/" + pdfMergeRqst.pdfMergeRqstPk.getValue().longValue());
				if (directory.exists()) {
					removeNasDirectory(directory.getPath());
				}

				EZDConnectionMgr.getInstance().commit();

				normalRecCnt++;
			} catch (Exception e) {
				EZDConnectionMgr.getInstance().rollback();

				logger.error(S21MessageFunc.clspGetMessage("ZZWM0050E"), e);
				errRecCnt++;
			}
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see com.canon.cusa.s21.framework.batch.S21BatchMain#termRoutine()
	 */
	@Override
	protected void termRoutine() {
		setRecordCount(normalRecCnt, errRecCnt, normalRecCnt + errRecCnt);

		if (errRecCnt == 0) {
			setTermState(TERM_CD.NORMAL_END);
		} else {
			setTermState(TERM_CD.ABNORMAL_END);
		}
	}

	/**
	 * Get PROC_END_TS threshold of PDF_MERGE_RQST.
	 * 
	 * @return PROC_END_TS threshold
	 */
	private String getTargetProcEndTs() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US);
		dateFormat.setTimeZone(TimeZone.getDefault());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, -fileHldQty);
		Date date = cal.getTime();
		String result = dateFormat.format(date);

		return result;
	}

	/**
	 * Remove nas repository.
	 */
	private void removeNasDirectory(String directoryPath) {
		File directory = new File(directoryPath);

		rmdir(directoryPath);

		if (directory.exists()) {
			throw new IllegalStateException("Directory cannot be deleted. :" + directoryPath);
		}
	}

	/**
	 * Remove directory.
	 * 
	 * @param directoryPath
	 */
	private void rmdir(String directoryPath) {
		File directory = new File(directoryPath);

		for (String filePath : directory.list()) {
			File file = new File(directoryPath + "/" + filePath);
			if (file.isDirectory()) {
				rmdir(directoryPath + "/" + filePath);
			} else {
				file.delete();
			}
		}

		directory.delete();
	}
}
