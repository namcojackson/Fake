/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2080;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFBL2080.common.NFBL2080CommonLogic;
import business.blap.NFBL2080.constant.NFBL2080Constant;
import business.file.NFBL2080F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * AP Invoice I/F Error Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Miyauchi        Create          N/A
 * 2016/08/04   Fujitsu         C.Tanaka        Update          QC#12632
 * 2018/10/13   Hitachi         J.Kim           Update          QC#28723
 * 2018/11/30   Hitachi         J.Kim           Update          QC#29477
 * 2019/01/09   Fujitsu         Y.Matsui        Update          QC#29884
 * 2019/05/20   Hitachi         H.Umeda         Update          QC#50204,50205
 * </pre>
 */
public class NFBL2080BL02 extends S21BusinessHandler implements NFBL2080Constant {

    @Override
    protected void doProcess(EZDCMsg bizMsg, EZDSMsg glblMsg) {
        super.preDoProcess(bizMsg, glblMsg);

        try {

            String screenAplID = bizMsg.getScreenAplID();
            NFBL2080CMsg cMsg = (NFBL2080CMsg) bizMsg;
            NFBL2080SMsg sMsg = (NFBL2080SMsg) glblMsg;

            cMsg.setCommitSMsg(true);

            //Error Message Clear
            sMsg.clearErrorInfo();
            cMsg.clearErrorInfo();

            // NFBL2080_INIT
            if (SCRN_MSG_02.NFBL2080_INIT.name().equals(screenAplID)) {
                doProcess_NFBL2080_INIT(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_Click_Search.name().equals(screenAplID)) {
                doProcess_NFBL2080_Click_Search(cMsg, sMsg);
//            } else if ((SCRN_MSG_02.NFBL2080Scrn00_Click_Reprocess.name().equals(screenAplID))) {
//            	doProcess_NFBL2080_INIT(cMsg, sMsg);
//            } else if (SCRN_MSG_02.NFBL2080Scrn00_Click_Cancel.name().equals(screenAplID)) {
//            	doProcess_NFBL2080_INIT(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_Check_All.name().equals(screenAplID)) {
                doProcess_NFBL2080_Check_All(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_Un_Check_All.name().equals(screenAplID)) {
                doProcess_NFBL2080_Un_Check_All(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_Click_vndInvNum.name().equals(screenAplID)) {
            	doProcess_NFBL2080_Click_vndInvNum(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_CMN_Return.name().equals(screenAplID)) {
                doProcess_NFBL2080Scrn00_CMN_Return(cMsg, sMsg);
//            } else if (SCRN_MSG_02.NFBL2080Scrn00_CMN_Submit.name().equals(screenAplID)) {
//            	doProcess_NFBL2080_Click_Search(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_OpenWin_Mdse.name().equals(screenAplID)) {
            	doProcess_NFBL2080Scrn00_OpenWin_Mdse(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_NMAL6800.name().equals(screenAplID)) {
            	doProcess_NFBL2080Scrn00_NMAL6800(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_PageJump.name().equals(screenAplID)) {
            } else if (SCRN_MSG_02.NFBL2080Scrn00_PageNext.name().equals(screenAplID)) {
                doProcess_NFBL2080Scrn00_PageNext(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_Header.name().equals(screenAplID)) {
                doProcess_NFBL2080Scrn00_Header(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_Detail.name().equals(screenAplID)) {
                doProcess_NFBL2080Scrn00_Detail(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_PagePrev.name().equals(screenAplID)) {
            	doProcess_NFBL2080Scrn00_PagePrev(cMsg, sMsg);
            } else if (SCRN_MSG_02.NFBL2080Scrn00_CMN_Reset.name().equals(screenAplID)) {
                doProcess_NFBL2080_INIT(cMsg, sMsg);
            // START 2018/10/12 J.Kim [QC#28723,ADD]
            } else if ("NFBL2080Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFBL2080Scrn00_CMN_Download(cMsg, sMsg);
            // END 2018/10/12 J.Kim [QC#28723,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(bizMsg, glblMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2080_INIT(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        NFBL2080CMsg bizMsg = (NFBL2080CMsg) cMsg;
        NFBL2080SMsg globalMsg = (NFBL2080SMsg) sMsg;
        bizMsg.clear();
        globalMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(globalMsg.B);

        //Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);
        //VND_INV_PROC_STS
        ZYPCodeDataUtil.createPulldownList(VND_INV_PROC_STS.class, bizMsg.vndInvProcStsCd, bizMsg.vndInvProcStsNm, ":");
        for (int i = 0; i < bizMsg.vndInvProcStsCd.length(); i++) {
            ZYPEZDItemValueSetter.setValue(globalMsg.vndInvProcStsCd.no(i), bizMsg.vndInvProcStsCd.no(i).getValue());
            ZYPEZDItemValueSetter.setValue(globalMsg.vndInvProcStsNm.no(i), bizMsg.vndInvProcStsNm.no(i).getValue());
        }
        //INV_AR_PROC_STS
        NFBL2080CommonLogic.createInvArProcStsPulldownList(cMsg, sMsg);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2080_Click_Search(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        //Clear the A List
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        // Clear [Header] tab information.
        NFBL2080CommonLogic.clearHeaderTab(cMsg, sMsg);

        // Clear [Detail] tab information.
        NFBL2080CommonLogic.clearDetailTab(cMsg, sMsg);

        //Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);

        // Keep search condition.
        NFBL2080CommonLogic.keepSearchCondition(cMsg);

        //find vendor invoice data
        // START 2019/01/09 Y.Matsui [QC#29884,MOD]
        NFBL2080CommonLogic.findAndSetVndInvDataList(cMsg, sMsg, true);
        // END   2019/01/09 Y.Matsui [QC#29884,MOD]
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2080_Click_vndInvNum(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg){

        //Add Start QC#12632
        String vndCd = cMsg.vndCd.getValue();
        String vndInvProcStsCd = cMsg.vndInvProcStsCd_P1.getValue();
        String invArProcStsCd = cMsg.invArProcStsCd_P1.getValue();
        String vndInvNum = cMsg.vndInvNum.getValue();
        String soNum = cMsg.soNum.getValue();
        String xxCratDt_S1 = cMsg.xxCratDt_S1.getValue();
        String xxCratDt_S2 = cMsg.xxCratDt_S2.getValue();
        String poOrdNum = cMsg.poOrdNum.getValue();
        String ediPoOrdNum = cMsg.ediPoOrdNum.getValue();
        String batErrMsgTxt = cMsg.batErrMsgTxt.getValue();
        //Add End QC#12632

        //Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);

        //Get Header Invoice Information
        NFBL2080CommonLogic.getVndHdrInfo((NFBL2080CMsg)cMsg, (NFBL2080SMsg)sMsg);
        //Get Detail Invoice Information
        NFBL2080CommonLogic.getVndDtlInfo((NFBL2080CMsg)cMsg, (NFBL2080SMsg)sMsg);

        // START 2018/11/30 J.Kim [QC#29477,MOD]
        // EZDMsg.copy(sMsg, null, cMsg, null);
        ZYPEZDItemValueSetter.setValue(cMsg.locNm_H1, sMsg.locNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvNum_H1, sMsg.vndInvNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.soNum_H1, sMsg.soNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvProcStsCd_H1, sMsg.vndInvProcStsCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum_H1, sMsg.poOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.ediPoOrdNum_H1, sMsg.ediPoOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvBolLineNum_H1, sMsg.vndInvBolLineNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.custIssPoNum_H1, sMsg.custIssPoNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxCratDt_H1, sMsg.xxCratDt_H1);
        // END 2018/11/30 J.Kim [QC#29477,MOD]
        // START 2019/05/20 H.Umeda [QC#50204,50205,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.invTpCd_H1, sMsg.invTpCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.itrlIntfcId_H1, sMsg.itrlIntfcId_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.crDrRsnCd_H1, sMsg.crDrRsnCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxInvCratFlg_H1,sMsg.xxInvCratFlg_H1);
        // END   2019/05/20 H.Umeda [QC#50204,50205,ADD]

        //Add Start QC#12632
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, vndCd);
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvProcStsCd_P1, vndInvProcStsCd);
        ZYPEZDItemValueSetter.setValue(cMsg.invArProcStsCd_P1, invArProcStsCd);
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvNum, vndInvNum);
        ZYPEZDItemValueSetter.setValue(cMsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(cMsg.xxCratDt_S1, xxCratDt_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxCratDt_S2, xxCratDt_S2);
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(cMsg.ediPoOrdNum, ediPoOrdNum);
        ZYPEZDItemValueSetter.setValue(cMsg.batErrMsgTxt, batErrMsgTxt);
        NFBL2080CommonLogic.keepSearchCondition(cMsg);
        //Add End QC#12632
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2080_Check_All(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        //Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);

        for (int iCnt=0; iCnt<cMsg.A.getValidCount(); iCnt++ ) {
           ZYPEZDItemValueSetter.setValue(cMsg.A.no(iCnt).xxChkBox_A1, FLG.Y.name());
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2080_Un_Check_All(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {
        //Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);

        for (int iCnt=0; iCnt<cMsg.A.getValidCount(); iCnt++ ) {
           ZYPEZDItemValueSetter.setValue(cMsg.A.no(iCnt).xxChkBox_A1, FLG.N.name());
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2080Scrn00_CMN_Return(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2080Scrn00_PageNext(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        //Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_P1.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );

        // set value to pagenation items
        cMsg.xxPageShowFromNum_P1.setValue( pagenationFrom + 1 );
        cMsg.xxPageShowToNum_P1.setValue( pagenationFrom + cMsg.A.getValidCount() );

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2080Scrn00_PagePrev(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        //Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_P1.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum_P1.setValue( pagenationFrom );
        cMsg.xxPageShowToNum_P1.setValue( pagenationFrom + cMsg.A.getValidCount() - 1 );

    }

    /***
     *<dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2080Scrn00_Header(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {
        //Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);
    }

    /***
     *<dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2080Scrn00_Detail(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {
        //Set Display Tab Index
        NFBL2080CommonLogic.getSetTabIndex(cMsg, sMsg);
    }

    /***
     *<dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2080Scrn00_OpenWin_Mdse(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {
    }

    /***
     *<dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFBL2080Scrn00_NMAL6800(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {
    }

    // START 2018/10/12 J.Kim [QC#28723,ADD]
    /**
     * Download
     * @param cMsg NFBL2080CMsg
     * @param sMsg NFBL2080SMsg
     */
    private void doProcess_NFBL2080Scrn00_CMN_Download(NFBL2080CMsg cMsg, NFBL2080SMsg sMsg) {

        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(MSG_ID.NZZM0000E.name());
            return;
        }

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NFBL2080Constant.FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFBL2080Query.getInstance().getClass());
            Map<String, Object> params = NFBL2080CommonLogic.setParamFindVndInvData(cMsg, getGlobalCompanyCode());

            stmtSelect = ssmLLClient.createPreparedStatement("findVndInvList", params, execParam);
            rs = stmtSelect.executeQuery();

            if (!rs.next()) {
                cMsg.setMessageInfo(MSG_ID.NZZM0000E.name());
                return;
            }

            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    private void writeCsvFile(NFBL2080CMsg cMsg, ResultSet rs) throws SQLException {

        NFBL2080F00FMsg fMsg = new NFBL2080F00FMsg();
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_NAME), CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        // write header
        csvOutFile.writeHeader(CSV_HEADER);

        do {

            if (rs.getRow() >= CSV_LIMIT_COUNT) {
                cMsg.setMessageInfo(MSG_ID.NZZM0001W.name());
            }

            fMsg.addExclusionItem("vndInvBolLineNum");
            ZYPEZDItemValueSetter.setValue(fMsg.locNm, rs.getString("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.vndInvNum, rs.getString("VND_INV_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.vndInvProcStsCd, rs.getString("VND_INV_PROC_STS_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.invArProcStsCd, rs.getString("INV_AR_PROC_STS_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.poOrdNum, rs.getString("PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ediPoOrdNum, rs.getString("EDI_PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxCratDt, rs.getString("CRAT_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.invTotDealNetAmt, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
            fMsg.xxAttDataCmntTxt.clear();

            // Get Message Information
            NFBL2080Query queryObj = NFBL2080Query.getInstance();
            String vndInvNum = rs.getString("VND_INV_NUM");
            String vndInvBolLineNum = rs.getString("VND_INV_BOL_LINE_NUM");
            S21SsmEZDResult ssmResult = queryObj.findVndInvErrInfo(vndInvNum, vndInvBolLineNum, null, null, null);

            if (ssmResult.isCodeNormal()) {

                List<Map<String, Object>> vndInvErrList = NFBL2080CommonLogic.vndInvErrList(ssmResult);

                int iCnt = 0;
                StringBuffer sb = new StringBuffer();
                List<String> vndInvErrStrList = new ArrayList<String>();
                for (Map<String, Object> vndInvErr : vndInvErrList) {
                    String vndInvErrMsgId = (String) vndInvErr.get(VND_INV_ERR_MSG_ID);
                    String errMsg = NFBL2080CommonLogic.editErrMsg(vndInvErr);
                    if (!vndInvErrStrList.contains(vndInvErrMsgId)) {
                        if (sb.length() > 0) {
                            sb.append(NEW_LINE);
                        }
                        sb.append(errMsg);
                        vndInvErrStrList.add(vndInvErrMsgId);
                        iCnt++;
                    }
                    if (iCnt >= 99) {
                        break;
                    }
                }
                ZYPEZDItemValueSetter.setValue(fMsg.xxAttDataCmntTxt, sb.toString());
            }

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }
    // END 2018/10/12 J.Kim [QC#28723,ADD]
}
