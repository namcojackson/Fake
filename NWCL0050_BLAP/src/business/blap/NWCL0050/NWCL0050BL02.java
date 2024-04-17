/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0050;

import static business.blap.NWCL0050.constant.NWCL0050Constant.COMMA;
import static business.blap.NWCL0050.constant.NWCL0050Constant.CONST_MAX_ROW_CNT;
import static business.blap.NWCL0050.constant.NWCL0050Constant.CONST_RECORD_PER_PAGE;
import static business.blap.NWCL0050.constant.NWCL0050Constant.KEY_INFO_CD_10;
import static business.blap.NWCL0050.constant.NWCL0050Constant.NWCM0096E;
import static business.blap.NWCL0050.constant.NWCL0050Constant.NWCM0097W;
import static business.blap.NWCL0050.constant.NWCL0050Constant.NWCM0098E;
import static business.blap.NWCL0050.constant.NWCL0050Constant.NWCM0099E;
import static business.blap.NWCL0050.constant.NWCL0050Constant.NWCM0101W;
import static business.blap.NWCL0050.constant.NWCL0050Constant.NWCM0143E;
import static business.blap.NWCL0050.constant.NWCL0050Constant.NWCM0160E;
import static business.blap.NWCL0050.constant.NWCL0050Constant.NWCM0161E;
import static business.blap.NWCL0050.constant.NWCL0050Constant.NZZM0000E;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWCL0050.common.NWCL0050CommonLogic;
import business.blap.NWCL0050.constant.NWCL0050Constant;
import business.db.INV_PRT_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_BAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_BR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_CTRL_REC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_SMRY_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CLS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileReader;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/26   Fujitsu         T.Yoshida       Create          N/A
 * 2015/11/09   Fujitsu         H.Nagashima     Update          CSA
 * 2016/02/24   SRAA            K.Aratani       Update          QC603
 * 2017/03/06   Fujitsu         K.Ishizuka      Update          QC#17954
 * 2017/03/07   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2017/12/28   Hitachi         E.Kameishi      Update          QC#20312
 * 2018/07/11   Fujitsu         M.Ohno          Update          QC#19801
 * 2018/09/19   Hitachi         Y.Takeno        Update          QC#19578
 * 2019/02/26   Fujitsu         Y.Kanefusa      Update          S21_NA#30519
 *</pre>
 */
public class NWCL0050BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWCL0050CMsg bizMsg = (NWCL0050CMsg) cMsg;
            NWCL0050SMsg glblMsg = (NWCL0050SMsg) sMsg;

            if ("NWCL0050_INIT".equals(screenAplID)) {
                doProcess_NWCL0050_INIT(bizMsg, glblMsg);
            } else if ("NWCL0050Scrn00_Search_Invoice".equals(screenAplID)) {
                doProcess_NWCL0050Scrn00_Search_Invoice(bizMsg, glblMsg);
//            } else if ("NWCL0050Scrn00_PageJump".equals(screenAplID)) {
//                doProcess_NWCL0050Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NWCL0050Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWCL0050Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NWCL0050Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWCL0050Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NWCL0050Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWCL0050Scrn00_TBLColumnSort(bizMsg, glblMsg);
            } else if ("NWCL0050Scrn00_OpenWin_Payment".equals(screenAplID)) {
                doProcess_NWCL0050Scrn00_OpenWin_Payment(bizMsg, glblMsg);
            } else if ("NWCL0050Scrn00_OpenWin_MailEntry".equals(screenAplID)) {
                doProcess_NWCL0050Scrn00_OpenWin_MailEntry(bizMsg, glblMsg);
            } else if ("NWCL0050Scrn00_OpenWin_ManualInv".equals(screenAplID)) {
                doProcess_NWCL0050Scrn00_OpenWin_ManualInv(bizMsg, glblMsg);
            } else if ("NWCL0050Scrn00_CMN_Clear".equals(screenAplID)) { // MOD S21_NA QC#13856 
                doProcess_NWCL0050Scrn00_CMN_Clear(bizMsg, glblMsg);// MOD S21_NA QC#13856 
//CSA ADD Start
            } else if ("NWCL0050_NWCL0060".equals(screenAplID)) {
                doProcess_NWCL0050_NWCL0060(bizMsg, glblMsg);
//CSA ADD End
            } else if ("NWCL0050Scrn00_OpenWin_FileDownload".equals(screenAplID)) {
                doProcess_NWCL0050_OpenWin_FileDownload(bizMsg, glblMsg);
            } else if ("NWCL0050Scrn00_CMN_Download".equals(screenAplID)) { // ADD S21_NA QC#13856
                doProcess_NWCL0050_CMN_Download(bizMsg, glblMsg); // ADD S21_NA QC#13856
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * INIT Event
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
    private void doProcess_NWCL0050_INIT(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {

        // create pulldown and initial value
        createPullDown(cMsg);
        //cMsg.invProcTpCd.setValue(INV_PROC_TP.SPECIAL_BILLING);
        // START 2017/12/28 E.Kameishi [QC#20312,ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.keyInfoCd)) {
            cMsg.keyInfoCd.setValue(KEY_INFO_CD_10);
        }
        // QC#53014 2019/09/17 Mod Start
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_SP, ZYPConstant.CHKBOX_ON_Y);
        // QC#53014 2019/09/17 Mod End
        if (ZYPCommonFunc.hasValue(cMsg.invNum)) {
            doProcess_NWCL0050Scrn00_Search_Invoice(cMsg, sMsg);
        }
        // END 2017/12/28 E.Kameishi [QC#20312,ADD]
    }

    /**
     * <pre>
     * CMN_Clear Event
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
    private void doProcess_NWCL0050Scrn00_CMN_Clear(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {

        // clear
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        doProcess_NWCL0050_INIT(cMsg, sMsg);
    }

    /**
     * <pre>
     * PageJump Event
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
//    private void doProcess_NWCL0050Scrn00_PageJump(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {
//
//        //QC#10665
//        int recordsOfPage = Integer.parseInt(cMsg.keyInfoCd.getValue());
////        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum_BK.getValueInt());
//        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum_BK.getValueInt(), recordsOfPage);
//        ZYPTableUtil.clear(cMsg.A);
//
//        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
//        int i = pagenationFrom;
//        for (; i < pagenationFrom + cMsg.A.length(); i++) {
//            if (i < sMsg.A.getValidCount()) {
//                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
//            } else {
//                break;
//            }
//        }
//        cMsg.A.setValidCount(i - pagenationFrom);
//
//        // set value to pagination items
//        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
//        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
//    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
    private void doProcess_NWCL0050Scrn00_PageNext(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {

        //QC#10665
        int recordsOfPage = Integer.parseInt(sMsg.keyInfoCd.getValue());
//        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());
        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), recordsOfPage);
        ZYPTableUtil.clear(cMsg.A);

        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        //QC#10665
//        for (; i < pagenationFrom + cMsg.A.length(); i++) {
        for (; i < pagenationFrom + recordsOfPage; i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
    private void doProcess_NWCL0050Scrn00_PagePrev(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {

        //QC#10665
        int recordsOfPage = Integer.parseInt(sMsg.keyInfoCd.getValue());
//        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());
        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), recordsOfPage);
        ZYPTableUtil.clear(cMsg.A);

        //QC#10665
//        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - recordsOfPage - 1);
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
//        for (; i < pagenationFrom + cMsg.A.length(); i++) {
        for (; i < pagenationFrom + recordsOfPage; i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagination items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * <pre>
     * Search_Invoice Event
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
    private void doProcess_NWCL0050Scrn00_Search_Invoice(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        S21SsmEZDResult ssmResult = NWCL0050Query.getInstance().getInvPrintInfo(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // 200over
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NWCM0101W, new String[] {String.valueOf(sMsg.A.length()) });
                queryResCnt = sMsg.A.length();
            }

            // 1page copy（SMsg -> CMsg）
            int i = 0;
            //QC#10665
            int recordsOfPage = Integer.parseInt(cMsg.keyInfoCd.getValue());
            sMsg.keyInfoCd.setValue(cMsg.keyInfoCd.getValue());
            for (; i < sMsg.A.getValidCount(); i++) {
                //QC#10665
//                if (i == cMsg.A.length()) {
                if (i == recordsOfPage) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(1);
            //QC#10665
            //QC#10665 Reopen
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            //cMsg.xxPageShowToNum.setValue(recordsOfPage);
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
            //QC#10665
            BigDecimal pageCnt = new BigDecimal(queryResCnt).divide(new BigDecimal(recordsOfPage), BigDecimal.ROUND_UP);
            cMsg.xxPageShowCurNum.setValue(1);
            cMsg.xxPageShowTotNum.setValue(pageCnt);

        } else {
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * <pre>
     * TBLColumnSort Event
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
    private void doProcess_NWCL0050Scrn00_TBLColumnSort(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {

        //QC#10665
        int recordsOfPage = Integer.parseInt(sMsg.keyInfoCd.getValue());
//        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());
        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), recordsOfPage);

        if ("A".equals(cMsg.xxSortTblNm.getValue())) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(cMsg.xxSortItemNm.getValue(), cMsg.xxSortOrdByTxt.getValue());
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // copy (SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                //if (i == cMsg.A.length()) {
                if (i == recordsOfPage) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set pagination
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        }
    }

    /**
     * <pre>
     * OpenWin Payment Event
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
    private void doProcess_NWCL0050Scrn00_OpenWin_Payment(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {

        //QC#10665
        int recordsOfPage = Integer.parseInt(sMsg.keyInfoCd.getValue());
//        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());
        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), recordsOfPage);
//        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        List<Integer> selectedRows = getSelectedRows(sMsg);

        if (!isAnyCheckBoxChecked(cMsg, sMsg, selectedRows)) {
            return;
        }
        // QC#30519 2019/02/26 Add Start
        if (!checkSelectMaxCntForPayment(cMsg, sMsg, selectedRows)) {
            return;
        }
        // QC#30519 2019/02/26 Add End

        if (!checkMultipleAccount(cMsg, sMsg, selectedRows)) {
            return;
        }

        // 2018/07/11 QC#19801 add start
        if (!checkEipRptRqstPk(cMsg, sMsg, selectedRows)) {
            return;
        }
        // 2018/07/11 QC#19801 add end

        //QC#10665
        StringBuffer sb = new StringBuffer();
        for (Integer idx : selectedRows) {
            String invNum = sMsg.A.no(idx).invNum_A.getValue();
            if (!ZYPCommonFunc.hasValue(invNum)) {
                //select consolidated invoice
                // QC#30519 2019/02/26 Add Start
                // cMsg.setMessageInfo(NWCM0143E);
                // return;
                continue;
                // QC#30519 2019/02/26 Add End
            }
            if (sb.length() >0) {
                sb.append(",");
            }
            sb.append(invNum);
        }
        String dsAcctNum = sMsg.A.no(selectedRows.get(0)).billToDsAcctNum_A.getValue();
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_PM, dsAcctNum);
        ZYPEZDItemValueSetter.setValue(cMsg.xxFldValTxt_PM, sb.toString());
    }
    // QC#30519 2019/02/26 Add Start
    private List<Integer> getSelectedRows(NWCL0050SMsg sMsg) {
        List<Integer> consoliList = new ArrayList<Integer>();
        List<Integer> rtnList = new ArrayList<Integer>();
        List<Integer> list = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        for (Integer i : list) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i.intValue()).invNum_A)) {
                consoliList.add(i);
            } else {
                rtnList.add(i);
            }
        }
        for (Integer i : consoliList) {
            String conslBillNum = sMsg.A.no(i.intValue()).conslBillNum_A.getValue();
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                NWCL0050_ASMsg row = sMsg.A.no(j);
                if (S21StringUtil.isEquals(conslBillNum, row.conslBillNum_A.getValue())) {
                    if (!ZYPConstant.FLG_ON_Y.equals(row.xxChkBox_A.getValue())) {
                        rtnList.add(new Integer(j));
                    }
                }
            }
        }
        Collections.sort(rtnList);
        return rtnList;
    }
    // QC#30519 2019/02/26 Add End

    /**
     * <pre>
     * OpenWin MailEntry Event
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
    private void doProcess_NWCL0050Scrn00_OpenWin_MailEntry(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {

        //QC#10665
        int recordsOfPage = Integer.parseInt(sMsg.keyInfoCd.getValue());
//        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());
        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), recordsOfPage);
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        if (!isAnyCheckBoxChecked(cMsg, sMsg, selectedRows)) {
            return;
        }

        if (!checkSelectMaxCnt(cMsg, sMsg, selectedRows)) {
            return;
        }

        // START 2018/09/19 [QC#19578, ADD]
        if (!checkMultipleAccount(cMsg, sMsg, selectedRows)) {
            return;
        }
        // END   2018/09/19 [QC#19578, ADD]

        if (!ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxDplyCtrlFlg_EM.getValue())) {
            if (!checkConsolidated(cMsg, sMsg, selectedRows, cMsg.xxDplyCtrlFlg_EM)) {
                return;
            }
        }

        // 2018/07/11 QC#19801 add start
        if (!checkEipRptRqstPk(cMsg, sMsg, selectedRows)) {
            return;
        }
        // 2018/07/11 QC#19801 add end

        ZYPTableUtil.clear(cMsg.B);
        int i = 0;
        for (; i < selectedRows.size(); i++) {
            int checkedRowNum = selectedRows.get(i);
            NWCL0050_BCMsg bcMsg = cMsg.B.no(i);
            ZYPEZDItemValueSetter.setValue(bcMsg.invEmlAddr_B, sMsg.A.no(checkedRowNum).invEmlAddr_A.getValue());
            ZYPEZDItemValueSetter.setValue(bcMsg.xxFilePathTxt_B, getFileName(sMsg.A.no(checkedRowNum).invFileUrl_A.getValue()));
            ZYPEZDItemValueSetter.setValue(bcMsg.invFileUrl_B, sMsg.A.no(checkedRowNum).invFileUrl_A.getValue());
            //CSA ADD Start
            ZYPEZDItemValueSetter.setValue(bcMsg.eipRptRqstPk_B, sMsg.A.no(checkedRowNum).eipRptRqstPk_A.getValue());
            if (ZYPCommonFunc.hasValue(sMsg.A.no(checkedRowNum).invNum_A)) {
                ZYPEZDItemValueSetter.setValue(bcMsg.invNum_B, sMsg.A.no(checkedRowNum).invNum_A.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(bcMsg.conslBillNum_B, sMsg.A.no(checkedRowNum).conslBillNum_A.getValue());
            }
            //CSA ADD End
            // START 2018/09/19 [QC#19578, ADD]
            ZYPEZDItemValueSetter.setValue(bcMsg.billToDsAcctNm_B, sMsg.A.no(checkedRowNum).billToDsAcctNm_A.getValue());
            ZYPEZDItemValueSetter.setValue(bcMsg.billToDsAcctNum_B, sMsg.A.no(checkedRowNum).billToDsAcctNum_A.getValue());
            // END   2018/09/19 [QC#19578, ADD]
        }

        cMsg.B.setValidCount(i);
        ZYPEZDItemValueSetter.setValue(cMsg.xxSfxKeyTxt, "B");
    }
    
    // 2018/07/11 QC#19801 add start
    /**
     * <pre>
     * OpenWin MailEntry Event
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
    private void doProcess_NWCL0050Scrn00_OpenWin_ManualInv(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {
        int recordsOfPage = Integer.parseInt(sMsg.keyInfoCd.getValue());
        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), recordsOfPage);
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        if (!isAnyCheckBoxChecked(cMsg, sMsg, selectedRows)) {
            return;
        }

        if (selectedRows.size() != 1) {
            cMsg.setMessageInfo(NWCM0099E, new String[] {"1" });
            return;
        }

        // QC#30519 2019/02/26 Add Start
        for (Integer i : selectedRows) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i.intValue()).invNum_A)) {
                cMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NWCM0143E);
                cMsg.setMessageInfo(NWCM0143E);
                return;
            }
        }
        // QC#30519 2019/02/26 Add End
    }
    // 2018/07/11 QC#19801 add end

    // CSA ADD Start
    /**
     * <pre>
     * return from NWCL0060
     * </pre>
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
    private void doProcess_NWCL0050_NWCL0060(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {

        //QC#10665
        int recordsOfPage = Integer.parseInt(sMsg.keyInfoCd.getValue());
//        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt());
        copyCurrentPageToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), recordsOfPage);
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        int i = 0;
        for (; i < selectedRows.size(); i++) {
            int checkedRowNum = selectedRows.get(i);
            
            INV_PRT_CTRLTMsg invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtCtrlPk, sMsg.A.no(checkedRowNum).invPrtCtrlPk_A.getValue());
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.scrInvEmlTs, cMsg.scrInvEmlTs.getValue());
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.scrInvEmlAddr, cMsg.scrInvEmlAddr.getValue());
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.scrInvEmlSubjTxt, cMsg.scrInvEmlSubjTxt.getValue());
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.scrInvEmlCmntTxt, cMsg.scrInvEmlCmntTxt.getValue());

            S21ApiTBLAccessor.updateSelectionField(invPrtCtrlTMsg, new String[]{"scrInvEmlTs","scrInvEmlAddr","scrInvEmlSubjTxt","scrInvEmlCmntTxt"});

        }
    }


    // CSA ADD End

    private void doProcess_NWCL0050_OpenWin_FileDownload(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {
        
        String inbBillNum = "";
        String fullFilePath = "";
        try {
            int idx = cMsg.xxCellIdx.getValueInt();
            // Get particular sub folder path (e.g.. 20151114/customInvoice_invNum_8005135.xls)
            String subPath = cMsg.A.no(idx).invFileUrl_AU.getValue();

            if (ZYPCommonFunc.hasValue(cMsg.A.no(idx).conslBillNum_A)) {
                inbBillNum = cMsg.A.no(idx).conslBillNum_A.getValue();
            } else {
                inbBillNum = cMsg.A.no(idx).invNum_A.getValue();
            }
            
            if (!ZYPCommonFunc.hasValue(subPath)) {
                //ZYPM0007E=0,Download file [@] is not found.
                cMsg.setMessageInfo("ZYPM0007E", new String[]{inbBillNum});
                return;
            }
            
            // ZYPFileUtil.getInvoiceStoredDirectoryPath (return /WebSphere/apps/custbill or /s21_nas/custbill)
            String rootPath =  ZYPFileUtil.getInvoiceStoredDirectoryPath();
            if (rootPath == null) {
                rootPath = "/WebSphere/apps/custbill";
            }

//Debug
//subPath =  "custombilling/2015/e479/1/42/49/1424961_43791220.xls";
            
            String fileName = cMsg.A.no(idx).invFileUrl_DF.getValue();
            if (!ZYPCommonFunc.hasValue(fileName)) {
                fileName = new File(subPath).getName();
            }
            cMsg.attFileNm.setValue(fileName);
            // Full path 
            if (subPath.startsWith("/")) {
                fullFilePath = rootPath + subPath;
            } else {
                fullFilePath = rootPath + "/" + subPath;
            }
            
//Debug
//fullFilePath = "C:\\S21_EZDeveloper\\dvlppc\\00temp\\updownfiles\\download\\1424961_43791220.xls";
    
            // Returns UTF8 encoded byte[] of file 
            byte[] byteFile = ZYPFileReader.getByteArray(fullFilePath);
            cMsg.xxFileData.setTempFilePath(null, cMsg.attFileNm.getValue(), "");
            ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), byteFile);
            
        } catch(Exception e) {
            //ZYPM0007E=0,Download file [@] is not found.
            cMsg.setMessageInfo("ZYPM0007E", new String[]{fullFilePath});
            return;
        }
        
    }
    
    /**
     * create Record per Page
     * @param cMsg NWCL0050CMsg
     */
    private void createPullDown(NWCL0050CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(INV_PRT_BAT_TP.class, cMsg.invPrtBatTpCd_PL, cMsg.invPrtBatTpDescTxt_PL);
        ZYPCodeDataUtil.createPulldownList(INV_PRT_BR.class, cMsg.invPrtBrCd_PL, cMsg.invPrtBrDescTxt_PL);
        ZYPCodeDataUtil.createPulldownList(INV_PROC_TP.class, cMsg.invProcTpCd_PL, cMsg.invProcTpDescTxt_PL);
        ZYPCodeDataUtil.createPulldownList(ORD_CLS.class, cMsg.ordClsCd_PL, cMsg.ordClsDescTxt_PL);
        ZYPCodeDataUtil.createPulldownList(INV_SMRY_LINE_TP.class, cMsg.invSmryLineTpCd_PL, cMsg.invSmryLineTpDescTxt_PL);
        createRecPagePullDown(cMsg);
    }

    /**
     * create Record per Page
     * @param cMsg NWCL0050CMsg
     */
    private void createRecPagePullDown(NWCL0050CMsg cMsg) {

        String recPerPage = ZYPCodeDataUtil.getVarCharConstValue(CONST_RECORD_PER_PAGE, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(recPerPage)) {
            return;
        }

        String[] recPerPageArray = recPerPage.split(COMMA);

        for (int i = 0; i < recPerPageArray.length; i++) {
            cMsg.keyInfoCd_PL.no(i).setValue(recPerPageArray[i]);
            cMsg.xxRptTpTxt_PL.no(i).setValue(recPerPageArray[i]);
        }
    }

    /**
     * copy NWCL0050_ACMsgArray to NWCL0050_ASMsgArray
     * @param fromPageNum From Page Number
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     */
//    private void copyCurrentPageToSMsg(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg, int fromPageNum) {
    private void copyCurrentPageToSMsg(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg, int fromPageNum, int recordsOfPage) {

        int fromIdx = fromPageNum - 1;
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
        // for (int i = 0; i < recordsOfPage; i++) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) { // MOD S21_NA QC#17954
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(fromIdx + i), null);
        }
    }

    /**
     * is Any CheckBox is Checked.
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     * @param selectedRows Selected Rows
     * @return true : Any Checked
     */
    private boolean isAnyCheckBoxChecked(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg, List<Integer> selectedRows) {

        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NWCM0096E);
            return false;
        }

        return true;
    }

    /**
     * check select max count
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     * @param selectedRows Selected Rows
     * @return true : No Error
     */
    private boolean checkSelectMaxCnt(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg, List<Integer> selectedRows) {

        int maxRowCnt = ZYPCodeDataUtil.getNumConstValue(CONST_MAX_ROW_CNT, getGlobalCompanyCode()).intValue();

        if (selectedRows.size() > maxRowCnt) {
            cMsg.setMessageInfo(NWCM0099E, new String[] {String.valueOf(maxRowCnt) });
            return false;
        }

        return true;
    }

    // QC#30519 2019/02/26 Add Start
    private boolean checkSelectMaxCntForPayment(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg, List<Integer> selectedRows) {

        int maxRowCnt = NWCL0050Constant.PAYMENT_MAX_CNT;

        if (selectedRows.size() >= maxRowCnt) {
            cMsg.setMessageInfo(NWCM0161E, new String[] {String.valueOf(maxRowCnt) });
            return false;
        }

        return true;
    }
    // QC#30519 2019/02/26 Add End
    /**
     * Check Consolidated
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     * @param selectedRows Selected Rows
     * @param xxDplyCtrlFlg Control Flag
     * @return true : No Error
     */
    private boolean checkConsolidated(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg, List<Integer> selectedRows, EZDCStringItem xxDplyCtrlFlg) {

        int pageFromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pageToIdx = cMsg.xxPageShowToNum.getValueInt() - 1;

        for (int i = 0; i < selectedRows.size(); i++) {
            int checkedRowNum = selectedRows.get(i);
            String invPrtCtrlRecCd = sMsg.A.no(checkedRowNum).invPrtCtrlRecCd_A.getValue();

            if (ZYPCommonFunc.hasValue(sMsg.A.no(checkedRowNum).conslBillNum_A) && !INV_PRT_CTRL_REC.CONSOLIDATED_BILL.equals(invPrtCtrlRecCd)) {
                if (checkedRowNum >= pageFromIdx && checkedRowNum <= pageToIdx) {
                    cMsg.A.no(checkedRowNum - pageFromIdx).xxChkBox_A.setErrorInfo(2, NWCM0097W);
                }

                cMsg.setMessageInfo(NWCM0097W);
                xxDplyCtrlFlg.setValue(ZYPConstant.CHKBOX_ON_Y);
            }
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(xxDplyCtrlFlg.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * Check Multiple Account
     * @param cMsg NWCL0050CMsg
     * @param sMsg NWCL0050SMsg
     * @param selectedRows Selected Rows
     * @param xxDplyCtrlFlg Control Flag
     * @return true : No Error
     */
    private boolean checkMultipleAccount(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg, List<Integer> selectedRows) {

        int firstCheckedRowNum = selectedRows.get(0);
        String targetAcctNum = sMsg.A.no(firstCheckedRowNum).billToDsAcctNum_A.getValue();

        for (int i = 1; i < selectedRows.size(); i++) {
            int checkedRowNum = selectedRows.get(i);
            String billToDsAcctNum = sMsg.A.no(checkedRowNum).billToDsAcctNum_A.getValue();

            if (!targetAcctNum.equals(billToDsAcctNum)) {
                cMsg.setMessageInfo(NWCM0098E);
                return false;
            }

            targetAcctNum = billToDsAcctNum;
        }

        return true;
    }

    /**
     * get File Name
     * @param invFileUrl Invoice File URL
     * @return File Name
     */
    private String getFileName(String invFileUrl) {

        if (!ZYPCommonFunc.hasValue(invFileUrl)) {
            return null;
        }

        return new File(invFileUrl).getName();
    }
    
    // ADD START S21_NA QC#13856
    /**
     * DownLoad Event
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NWCL0050_CMN_Download(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg) {
        NWCL0050CommonLogic.csvDownload(cMsg,sMsg);
    }
 
    // ADD END S21_NA QC#13856
    // 2018/07/11 QC#19801 add start
    private boolean checkEipRptRqstPk(NWCL0050CMsg cMsg, NWCL0050SMsg sMsg, List<Integer> selectedRows) {
        boolean errorResult = true;
        int pageFromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pageToIdx = cMsg.xxPageShowToNum.getValueInt() - 1;
        
        for (int i=0; i < selectedRows.size(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(selectedRows.get(i)).eipRptRqstPk_A)) {
                if (selectedRows.get(i) >= pageFromIdx && selectedRows.get(i) <= pageToIdx) {
                    cMsg.A.no(selectedRows.get(i) - pageFromIdx).xxChkBox_A.setErrorInfo(1, NWCM0160E);
                }
                errorResult = false;
                cMsg.setMessageInfo(NWCM0160E);
            }
        }
        return errorResult;
    }
    // 2018/07/11 QC#19801 add end
}
