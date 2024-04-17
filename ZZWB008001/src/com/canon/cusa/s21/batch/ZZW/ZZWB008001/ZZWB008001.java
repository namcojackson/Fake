/*
 * Copyright (c) 2022 Canon USA Inc. All rights reserved.
 * Original Author: Kohei Yamamoto Company: Fujitsu Limited Date: August 1, 2022
 */
package com.canon.cusa.s21.batch.ZZW.ZZWB008001;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDSystemEnv;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBRecordLockedException;
import business.db.PDF_MERGE_EML_OUT_OPTTMsg;
import business.db.PDF_MERGE_RQSTTMsg;

import com.aspose.pdf.facades.PdfFileEditor;
import com.canon.cusa.s21.framework.ZYP.aspose.pdf.S21PDFMerger;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAttachment;
import com.canon.cusa.s21.framework.pdf.S21PDFMergeService;
import com.canon.cusa.s21.framework.pdf.dao.PdfMergeQuery;
import com.canon.cusa.s21.framework.pdf.message.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.pdf.message.S21ExecutionStatus;
import com.canon.cusa.s21.framework.pdf.message.S21InputParameter;
import com.canon.cusa.s21.framework.pdf.message.S21PDFMergeRequest;

/**
 * Batch that merge PDF files and send email.
 * 
 * @author Kohei Yamamoto
 */
public class ZZWB008001 extends S21BatchMain {
	/** Logger */
	private static final S21Logger logger = S21LoggerFactory.getLogger(ZZWB008001.class);

	/** Value of RQST_STS_TXT column in PDF_MERGE_RQST table: WAITING */
	private static final String PDF_MERGE_RQST_RQST_STS_TXT_WAITING = "WAITING";
	/** Value of RQST_STS_TXT column in PDF_MERGE_RQST table: SUCCESS */
	private static final String PDF_MERGE_RQST_RQST_STS_TXT_SUCCESS = "SUCCESS";
	/** Value of RQST_STS_TXT column in PDF_MERGE_RQST table: FAILURE */
	private static final String PDF_MERGE_RQST_RQST_STS_TXT_FAILURE = "FAILURE";
	/** Value of PDF_MERGE_DEST_STS_TXT column in PDF_MERGE_EML_OUT_OPT table: SUCCESS */
	private static final String PDF_MERGE_EML_OUT_OPT_PDF_MERGE_DEST_STS_TXT_SUCCESS = "SUCCESS";
	/** Value of PDF_MERGE_DEST_STS_TXT column in PDF_MERGE_EML_OUT_OPT table: FAILURE */
	private static final String PDF_MERGE_EML_OUT_OPT_PDF_MERGE_DEST_STS_TXT_FAILURE = "FAILURE";
	/** Merged PDF file format */
	private static final String MERGED_FILE_PATH_FORMAT = "%s/%d/merged_%d.pdf";

	/** Root folder path of NAS */
	private String nasRootFolder;
	/** PDF merge service */
	private S21PDFMergeService pdfMergeService;
	/** PDF merger */
	private S21PDFMerger pdfMerger;
	/** Number of normal record */
	private int normalRecCnt;
	/** Number of warning record */
	private int warnRecCnt;
	/** Number of error record */
	private int errRecCnt;

	/**
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        new ZZWB008001().executeBatch(ZZWB008001.class.getSimpleName());
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

		pdfMergeService = new S21PDFMergeService();
		pdfMerger = new S21PDFMerger();

		normalRecCnt = 0;
		warnRecCnt = 0;
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
		List<PDF_MERGE_RQSTTMsg> pdfMergeRqstList = pdfMergeQuery.selectPdfMergeRqstByRqstStsTxt(glblCmpyCd, PDF_MERGE_RQST_RQST_STS_TXT_WAITING);

		for (PDF_MERGE_RQSTTMsg pdfMergeRqstTMsg : pdfMergeRqstList) {
			boolean isError = false;
			boolean isWarning = false;

			S21ExecutionStatus status = null;
			S21PDFMergeRequest request = null;
			String mergedFilePath = null;
			byte[] mergedFileData = null;

			try {
				long pdfMergePqstPk = pdfMergeRqstTMsg.pdfMergeRqstPk.getValue().longValue();

				try {
					// Lock record in PDF_MERGE_RQST
					pdfMergeRqstTMsg = pdfMergeQuery.lockPdfMergeRqst(glblCmpyCd, pdfMergePqstPk, PDF_MERGE_RQST_RQST_STS_TXT_WAITING);

					if (pdfMergeRqstTMsg == null) {
						// If the target record in the PDF_MERGE_RQST table is already modified.
						logger.info("Target record in the PDF_MERGE_RQST table is already modified. :pdfMergeRqstPk=" + pdfMergePqstPk);
						continue;
					}
				} catch (EZDDBRecordLockedException e) {
					// If the target record in the PDF_MERGE_RQST table is already locked.
					logger.info("Target record in the PDF_MERGE_RQST table is already locked. :pdfMergeRqstPk=" + pdfMergePqstPk, e);
					continue;
				}

				// Set merge start date and time
				pdfMergeRqstTMsg.procStartTs.setValue(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));

				// Get merge request
				request = new S21PDFMergeRequest();
				status = pdfMergeService.getRequest(pdfMergeRqstTMsg.pdfMergeRqstPk.getValue().longValue(), request);

				if (status.getStatus() != null && status.getStatus().equals(S21PDFMergeRequest.STATUS_NOERROR)) {
					List<String> mergeFilePathList = new ArrayList<String>();

					// Delete page of PDF files
					for (S21InputParameter inputParameter : request.getFilePathList()) {
						if (inputParameter.getDelPage() != null) {
							String pageDeletedFilePath = getPageDeletedFilePath(inputParameter.getFilePath());

							PdfFileEditor pdfEditor = new PdfFileEditor();
							pdfEditor.delete(inputParameter.getFilePath(), convertIntArray(inputParameter.getDelPage()), pageDeletedFilePath);

							mergeFilePathList.add(pageDeletedFilePath);
						} else {
							mergeFilePathList.add(inputParameter.getFilePath());
						}
					}

					// Merge PDF files
					mergedFileData = pdfMerger.merge(mergeFilePathList);
					mergedFilePath = String.format(MERGED_FILE_PATH_FORMAT, nasRootFolder, request.getMrgRequestPK(), request.getMrgRequestPK());

					if (request.getFileOutFlg() != null && request.getFileOutFlg().equals(ZYPConstant.FLG_ON_Y)) {
						if (mergedFileData != null) {
							writeFile(mergedFilePath, mergedFileData);
						} else {
							isWarning = true;
							logger.error(S21MessageFunc.clspGetMessage("ZZWM0054W"));
							status.setStatus(S21PDFMergeRequest.STATUS_FAILURE);
							status.setErrCd("ZZWM0054W");
						}
					}
				}

				// Update record in PDF_MERGE_RQST
				if (request.getFileOutFlg() != null && request.getFileOutFlg().equals(ZYPConstant.FLG_ON_Y)) {
					updatePdfMergeRqst(status, pdfMergeRqstTMsg, mergedFilePath);
				} else {
					updatePdfMergeRqst(status, pdfMergeRqstTMsg, null);
				}
			} catch (Exception e) {
				isWarning = true;
				logger.error(S21MessageFunc.clspGetMessage("ZZWM0054W"), e);
				status.setStatus(S21PDFMergeRequest.STATUS_FAILURE);
				status.setErrCd("ZZWM0054W");

				// Update record in PDF_MERGE_RQST
				updatePdfMergeRqst(status, pdfMergeRqstTMsg, mergedFilePath);
			}

			// Send mail
			if (request.getEmailOutFlg() != null && request.getEmailOutFlg().equals(ZYPConstant.FLG_ON_Y)) {
				long emlOutOptPk = -1;

				try {
					if (status.getStatus() != null && status.getStatus().equals(S21PDFMergeRequest.STATUS_NOERROR)) {
						S21EmailOutputParameter emailOutputParameter = request.getMailParam();
						emlOutOptPk = emailOutputParameter.getEmlOutOptPK();

						S21Mail mail = new S21Mail(glblCmpyCd);
						mail.setFromAddress(emailOutputParameter.getFromAddress());
						mail.setToAddressList(emailOutputParameter.getToAddressList());
						mail.setCcAddressList(emailOutputParameter.getCcAddressList());
						mail.setBccAddressList(emailOutputParameter.getBccAddressList());
						mail.setSubject(emailOutputParameter.getSubject());
						mail.setText(emailOutputParameter.getBodyText());

						S21MailAttachment attachment = new S21MailAttachment(glblCmpyCd);
						attachment.setAttachment(mergedFileData);
						if (emailOutputParameter.getAttachFileName() != null && !emailOutputParameter.getAttachFileName().isEmpty()) {
							attachment.setFileName(emailOutputParameter.getAttachFileName());
						} else {
							attachment.setFileName(mergedFilePath.replaceFirst("^.+/", ""));
						}
						mail.setAttachment(attachment);

						String mlEventPk = mail.sendMail();

						// Update record in PDF_MERGE_EML_OUT_OPT
						PDF_MERGE_EML_OUT_OPTTMsg pdfMergeEmlOutOptNormalTMsg = pdfMergeQuery.selectPdfMergeEmlOutOptByPk(glblCmpyCd, emlOutOptPk);
						pdfMergeEmlOutOptNormalTMsg.mlEventPk.setValue(BigDecimal.valueOf(Long.valueOf(mlEventPk)));
						pdfMergeEmlOutOptNormalTMsg.pdfMergeDestStsTxt.setValue(PDF_MERGE_EML_OUT_OPT_PDF_MERGE_DEST_STS_TXT_SUCCESS);
						S21FastTBLAccessor.update(pdfMergeEmlOutOptNormalTMsg);
					} else {
						S21EmailOutputParameter emailOutputParameter = request.getErrMailParam();
						emlOutOptPk = emailOutputParameter.getEmlOutOptPK();

						S21Mail mail = new S21Mail(glblCmpyCd);
						mail.setFromAddress(emailOutputParameter.getFromAddress());
						mail.setToAddressList(emailOutputParameter.getToAddressList());
						mail.setCcAddressList(emailOutputParameter.getCcAddressList());
						mail.setBccAddressList(emailOutputParameter.getBccAddressList());
						mail.setSubject(emailOutputParameter.getSubject());
						mail.setText(emailOutputParameter.getBodyText());

						String mlEventPk = mail.sendMail();

						// Update record in PDF_MERGE_EML_OUT_OPT
						PDF_MERGE_EML_OUT_OPTTMsg pdfMergeEmlOutOptNormalTMsg = pdfMergeQuery.selectPdfMergeEmlOutOptByPk(glblCmpyCd, emlOutOptPk);
						pdfMergeEmlOutOptNormalTMsg.mlEventPk.setValue(BigDecimal.valueOf(Long.valueOf(mlEventPk)));
						pdfMergeEmlOutOptNormalTMsg.pdfMergeDestStsTxt.setValue(PDF_MERGE_EML_OUT_OPT_PDF_MERGE_DEST_STS_TXT_SUCCESS);
						S21FastTBLAccessor.update(pdfMergeEmlOutOptNormalTMsg);
					}

					EZDConnectionMgr.getInstance().commit();
				} catch (Exception e) {
					if (emlOutOptPk == -1) {
						EZDConnectionMgr.getInstance().rollback();
					} else {
						// Update record in PDF_MERGE_EML_OUT_OPT
						PDF_MERGE_EML_OUT_OPTTMsg pdfMergeEmlOutOptNormalTMsg = pdfMergeQuery.selectPdfMergeEmlOutOptByPk(glblCmpyCd, emlOutOptPk);
						pdfMergeEmlOutOptNormalTMsg.pdfMergeDestStsTxt.setValue(PDF_MERGE_EML_OUT_OPT_PDF_MERGE_DEST_STS_TXT_FAILURE);
						S21FastTBLAccessor.update(pdfMergeEmlOutOptNormalTMsg);
						EZDConnectionMgr.getInstance().commit();
					}

					isError = true;
					logger.error(S21MessageFunc.clspGetMessage("ZZWM0055E"), e);
				}
			}

			if (isError) {
				errRecCnt++;
			} else if (isWarning) {
				warnRecCnt++;
			} else {
				normalRecCnt++;
			}
		}

		if (errRecCnt > 0) {
			throw new S21AbendException("ZZWM0055E");
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see com.canon.cusa.s21.framework.batch.S21BatchMain#termRoutine()
	 */
	@Override
	protected void termRoutine() {
		setRecordCount(normalRecCnt, errRecCnt, normalRecCnt + warnRecCnt + errRecCnt);

		if (warnRecCnt == 0 && errRecCnt == 0) {
			setTermState(TERM_CD.NORMAL_END);
		} else if (warnRecCnt > 0 && errRecCnt == 0) {
			setTermState(TERM_CD.WARNING_END);
		} else {
			setTermState(TERM_CD.ABNORMAL_END);
		}
	}

	/**
	 * Convert Integer list to int array.
	 * 
	 * @param integerList Integer list
	 * @return int array
	 */
	private int[] convertIntArray(List<Integer> integerList) {
		int[] intArray = new int[integerList.size()];

		for (int j = 0; j < integerList.size(); j++) {
			intArray[j] = integerList.get(j);
		}

		return intArray;
	}

	/**
	 * Get file path after deletion.
	 * 
	 * @param filePath File path
	 * @return File path after deletion
	 */
	private String getPageDeletedFilePath(String filePath) {
		StringBuilder deletedFilePath = new StringBuilder();

		String[] splitedFilePaths = filePath.split("/");
		splitedFilePaths[splitedFilePaths.length - 2] = splitedFilePaths[splitedFilePaths.length - 2] + "/work";

		for (String splitedFilePath : splitedFilePaths) {
			deletedFilePath.append(splitedFilePath).append("/");
		}

		return deletedFilePath.substring(0, deletedFilePath.length() - 1);
	}

	/**
	 * Write file.
	 * 
	 * @param file File
	 * @param fileData file data
	 * @throws IOException
	 */
	private void writeFile(String filePath, byte[] fileData) throws IOException {
		File file = new File(filePath);

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdir();
		}

		OutputStream out = null;

		try {
			out = new FileOutputStream(file);
			out.write(fileData);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * Update record in PDF_MERGE_RQST.
	 * 
	 * @param status Execution status of PDF merge
	 * @param pdfMergeRqstTMsg TMsg of PDF_MERGE_RQST
	 * @param mergedFilePath Merged PDF file path
	 */
	private void updatePdfMergeRqst(S21ExecutionStatus status, PDF_MERGE_RQSTTMsg pdfMergeRqstTMsg, String mergedFilePath) {
		try {
			// Update record in PDF_MERGE_RQST
			if (status.getStatus() != null && status.getStatus().equals(S21PDFMergeRequest.STATUS_NOERROR)) {
				pdfMergeRqstTMsg.rqstStsTxt.setValue(PDF_MERGE_RQST_RQST_STS_TXT_SUCCESS);
			} else {
				pdfMergeRqstTMsg.rqstStsTxt.setValue(PDF_MERGE_RQST_RQST_STS_TXT_FAILURE);
				pdfMergeRqstTMsg.procErrCd.setValue(status.getErrCd());
			}
			pdfMergeRqstTMsg.procEndTs.setValue(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
			if (mergedFilePath != null && !mergedFilePath.isEmpty()) {
				String[] splitedMergedFilePath = mergedFilePath.split("/");
				pdfMergeRqstTMsg.pdfFilePathTxt.setValue(splitedMergedFilePath[splitedMergedFilePath.length - 2]);
				pdfMergeRqstTMsg.pdfFileNm.setValue(splitedMergedFilePath[splitedMergedFilePath.length - 1]);
			}
			S21FastTBLAccessor.update(pdfMergeRqstTMsg);

			// Unlock record in PDF_MERGE_RQST
			EZDConnectionMgr.getInstance().commit();
		} catch (Exception e) {
			// Unlock record in PDF_MERGE_RQST
			EZDConnectionMgr.getInstance().rollback();

			logger.error(S21MessageFunc.clspGetMessage("ZZWM0054W"), e);
		}
	}
}
