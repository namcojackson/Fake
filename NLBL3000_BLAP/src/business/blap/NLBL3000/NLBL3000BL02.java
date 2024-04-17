/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3000;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import business.blap.NLBL3000.constant.NLBL3000Constant;
import business.db.MDSETMsg;
import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 *  Serial Number Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2012   Fujitsu         T.Ishii         Create          N/A
 * 11/27/2012   Fujitsu         Y.Taoka         Update          
 * 07/26/2013   Fujitsu         M.Nakamura      Update          R-OM031
 * 11/28/2015   CSAI            Y.Imazu         Update          CSA
 * 07/15/2016   CSAI            Y.Imazu         Update          QC#7334
 * 07/03/2017   CITS            T.Tokutomi      Update          QC#19681
 * 02/07/2018   CITS            K.Ogino         Update          QC#19857
 *</pre>
 */
public class NLBL3000BL02 extends S21BusinessHandler implements NLBL3000Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // 07/26/2013 R-OM031 Mod Start
//            if ("NLBL3000_INIT".equals(screenAplID)) {
//                doProcess_NLBL3000_INIT((NLBL3000CMsg) cMsg, (NLBL3000SMsg) sMsg);
//            } else if ("NLBL3000Scrn00_Search".equals(screenAplID)) {
//                doProcess_NLBL3000Scrn00_Search((NLBL3000CMsg) cMsg, (NLBL3000SMsg) sMsg);
//            } else if ("NLBL3000Scrn00_PageNext".equals(screenAplID)) {
//                doProcess_NLBL3000Scrn00_PageNext((NLBL3000CMsg) cMsg, (NLBL3000SMsg) sMsg);
//            } else if ("NLBL3000Scrn00_PagePrev".equals(screenAplID)) {
//                doProcess_NLBL3000Scrn00_PagePrev((NLBL3000CMsg) cMsg, (NLBL3000SMsg) sMsg);
//            } else if ("NLBL3000Scrn00_TBLColumnSort".equals(screenAplID)) {
//                doProcess_NLBL3000Scrn00_TBLColumnSort((NLBL3000CMsg) cMsg, (NLBL3000SMsg) sMsg);
//            } else if ("NLBL3000Scrn00_CMN_Close".equals(screenAplID)) {
//                doProcess_NLBL3000Scrn00_CMN_Close((NLBL3000CMsg) cMsg, (NLBL3000SMsg) sMsg);
//            } else if ("NLBL3000Scrn00_CMN_Clear".equals(screenAplID)) {
//                doProcess_NLBL3000Scrn00_CMN_Clear((NLBL3000CMsg) cMsg, (NLBL3000SMsg) sMsg);
//            }
            // The corresponding R-OM031, features Search/PageNext/PagePrev/TBLColumnSort/SMsg, was removed.

            if ("NLBL3000_INIT".equals(screenAplID)) {

                doProcess_NLBL3000_INIT((NLBL3000CMsg) cMsg, sMsg);

            } else if ("NLBL3000Scrn00_CMN_Close".equals(screenAplID)) {

                doProcess_NLBL3000Scrn00_CMN_Close((NLBL3000CMsg) cMsg, sMsg);

            } else if ("NLBL3000Scrn00_CMN_Clear".equals(screenAplID)) {

                doProcess_NLBL3000Scrn00_CMN_Clear((NLBL3000CMsg) cMsg, sMsg);
            }
            // 07/26/2013 R-OM031 Mod End

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Process of screen event NLBL3000_INIT
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    // 07/26/2013 R-OM031 Mod Start
//    private void doProcess_NLBL3000_INIT(NLBL3000CMsg bizMsg, NLBL3000SMsg sharedMsg) {
    private void doProcess_NLBL3000_INIT(NLBL3000CMsg bizMsg, EZDSMsg sharedMsg) {
    // 07/26/2013 R-OM031 Mod End

        /* 11/28/2015 CSAI Y.Imazu Del CSA START */
        // boolean errorExists = false;
        /* 11/28/2015 CSAI Y.Imazu Del CSA END */

        // 07/26/2013 R-OM031 Add Start
        if (bizMsg.ordQty_H1.getValueInt() > bizMsg.S.length()) {

            bizMsg.setMessageInfo(NLBM1294W, new String[] {MSG_SER_NUM, String.valueOf(bizMsg.S.length()), MSG_QTY});
        }
        // 07/26/2013 R-OM031 Add End

        /* 11/28/2015 CSAI Y.Imazu Add CSA START */
        if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd_H1)) {

            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, bizMsg.glblCmpyCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, bizMsg.rtlWhCd_H1.getValue());
            rtlWhTMsg = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWhTMsg);

            if (rtlWhTMsg == null) {

                bizMsg.rtlWhCd_H1.setErrorInfo(1, NLBM1334E);
                bizMsg.setMessageInfo(NLBM1334E);

            } else {

                ZYPEZDItemValueSetter.setValue(bizMsg.invtyAcctCd_H1, rtlWhTMsg.invtyAcctCd.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H1, rtlWhTMsg.rtlWhNm.getValue());
            }
        }

        List<Map<String, String>> mdseMapList = new ArrayList<Map<String, String>>();
        List<String> mdseList = new ArrayList<String>();

        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.S.no(i).mdseCd)) {

                if (mdseList.contains(bizMsg.S.no(i).mdseCd.getValue())) {

                    for (Map<String, String> mdseMapInfo : mdseMapList) {

                        if (mdseMapInfo.containsKey(bizMsg.S.no(i).mdseCd.getValue())) {

                            ZYPEZDItemValueSetter.setValue(bizMsg.S.no(i).instlBaseCtrlFlg, mdseMapInfo.get(bizMsg.S.no(i).mdseCd.getValue()));
                            break;
                        }
                    }

                } else {

                    Map<String, String> mdseMap = new HashMap<String, String>();

                    MDSETMsg mdseTMsg = new MDSETMsg();
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd_H1.getValue());
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, bizMsg.S.no(i).mdseCd.getValue());
                    mdseTMsg = (MDSETMsg) EZDFastTBLAccessor.findByKey(mdseTMsg);

                    if (mdseTMsg != null) {

                        ZYPEZDItemValueSetter.setValue(bizMsg.S.no(i).instlBaseCtrlFlg, mdseTMsg.instlBaseCtrlFlg.getValue());

                    } else {

                        ZYPEZDItemValueSetter.setValue(bizMsg.S.no(i).instlBaseCtrlFlg, ZYPConstant.FLG_OFF_N);
                    }

                    mdseList.add(bizMsg.S.no(i).mdseCd.getValue());
                    mdseMap.put(bizMsg.S.no(i).mdseCd.getValue(), bizMsg.S.no(i).instlBaseCtrlFlg.getValue());
                    mdseMapList.add(mdseMap);
                }
            }
        }
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */

        /* 11/28/2015 CSAI Y.Imazu Del CSA START */
//        // error exists
//        if (errorExists) {
//
//            return;
//        }
        /* 11/28/2015 CSAI Y.Imazu Del CSA END */

        // search
        // 07/26/2013 R-OM031 Del Start
        // search(bizMsg, sharedMsg);
        // 07/26/2013 R-OM031 Del End
    }

    // 07/26/2013 R-OM031 Del Start
//    /**
//     * <pre>
//     * Process of screen event NLBL3000Scrn00_Search
//     * </pre>
//     * @param bizMsg Business Component Interface Message
//     * @param sharedMsg Global area information
//     */
//    private void doProcess_NLBL3000Scrn00_Search(NLBL3000CMsg bizMsg, NLBL3000SMsg sharedMsg) {
//
//        boolean errorExists = false;
//
//        if (errorExists) {
//            // error exists
//            return;
//        }
//
//        // search
//        search(bizMsg, sharedMsg);
//    }
    // 07/26/2013 R-OM031 Del End

    /**
     * <pre>
     * Process of screen event NLBL3000Scrn00_CMN_Clear
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    // 07/26/2013 R-OM031 Mod Start
//    private void doProcess_NLBL3000Scrn00_CMN_Clear(NLBL3000CMsg bizMsg, NLBL3000SMsg sharedMsg) {
    private void doProcess_NLBL3000Scrn00_CMN_Clear(NLBL3000CMsg bizMsg, EZDSMsg sharedMsg) {
    // 07/26/2013 R-OM031 Mod End

        /* 11/28/2015 CSAI Y.Imazu Add CSA START */
        bizMsg.xxWrnSkipFlg_H1.clear();
        bizMsg.xxErrFlg_H1.clear();
        bizMsg.xxLineNum_H1.clear();

        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {

            // QC#12919
            if (SCR_EDT_TP_EDIT.equals(bizMsg.xxScrEdtTpCd_H1.getValue()) //
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.S.no(i).xxEdtModeFlg.getValue())) {
                bizMsg.S.no(i).serNum.clear();
                bizMsg.S.no(i).serNum_S1.clear();
                bizMsg.S.no(i).svcMachMstrPk.clear();
                bizMsg.S.no(i).curLocNum.clear();
            }
        }
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */

        /* 11/28/2015 CSAI Y.Imazu Del CSA START */
//        boolean errorExists = false;
//
//        // error exists
//        if (errorExists) {
//
//            return;
//        }
        /* 11/28/2015 CSAI Y.Imazu Del CSA END */

        // search
        // 07/26/2013 R-OM031 Del Start
        // search(bizMsg, sharedMsg);
        // 07/26/2013 R-OM031 Del End
    }

    // 07/26/2013 R-OM031 Del Start
//    /**
//     * <pre>
//     * Process of screen event NLBL3000Scrn00_PageNext
//     * </pre>
//     * @param bizMsg Business Component Interface Message
//     * @param sharedMsg Global area information
//     */
//    private void doProcess_NLBL3000Scrn00_PageNext(NLBL3000CMsg bizMsg, NLBL3000SMsg sharedMsg) {
//
//        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
//        NLBL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sharedMsg.A);
//    }
//
//    /**
//     * <pre>
//     * Process of screen event NLBL3000Scrn00_PagePrev
//     * </pre>
//     * @param bizMsg Business Component Interface Message
//     * @param sharedMsg Global area information
//     */
//    private void doProcess_NLBL3000Scrn00_PagePrev(NLBL3000CMsg bizMsg, NLBL3000SMsg sharedMsg) {
//
//        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
//        NLBL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sharedMsg.A);
//    }
//
//    /**
//     * <pre>
//     * Process of screen event NLBL3000Scrn00_TBLColumnSort
//     * </pre>
//     * @param bizMsg Business Component Interface Message
//     * @param sharedMsg Global area information
//     */
//    private void doProcess_NLBL3000Scrn00_TBLColumnSort(NLBL3000CMsg bizMsg, NLBL3000SMsg sharedMsg) {
//
//        String sortTblNm = bizMsg.xxSortTblNm.getValue();
//        String sortItemNm = bizMsg.xxSortItemNm.getValue();
//        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();
//
//        // Table:A
//        if ("A".equals(sortTblNm)) {
//
//            // execute column sort function
//            S21SortTarget sortTarget = new S21SortTarget(sharedMsg.A, sharedMsg.A.no(0).getBaseContents());
//            S21SortKey sortKey = new S21SortKey();
//            sortKey.add(sortItemNm, sortOrdBy);
//            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sharedMsg.A.getValidCount());
//
//            int i = 0;
//            for (; i < sharedMsg.A.getValidCount(); i++) {
//                if (i == bizMsg.A.length()) {
//                    break;
//                }
//                EZDMsg.copy(sharedMsg.A.no(i), null, bizMsg.A.no(i), null);
//            }
//            bizMsg.A.setValidCount(i);
//
//            bizMsg.xxPageShowFromNum.setValue(1);
//            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
//        }
//    }
    // 07/26/2013 R-OM031 Del End

    /**
     * <pre>
     * Process of screen event NLBL3000Scrn00_CMN_Close
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    // 07/26/2013 R-OM031 Mod Start
//    private void doProcess_NLBL3000Scrn00_CMN_Close(NLBL3000CMsg bizMsg, NLBL3000SMsg sharedMsg) {
    private void doProcess_NLBL3000Scrn00_CMN_Close(NLBL3000CMsg bizMsg, EZDSMsg sharedMsg) {
    // 07/26/2013 R-OM031 Mod End

        if (!SCR_EDT_TP_EDIT.equals(bizMsg.xxScrEdtTpCd_H1.getValue())) {

            return;
        }

        HashMap<String, List<String>> serialList = new HashMap<String, List<String>>();
        HashMap<String, List<String>> duplicateSerialList = new HashMap<String, List<String>>();

        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {

            NLBL3000_SCMsg serial = bizMsg.S.no(i);

            if (ZYPCommonFunc.hasValue(serial.serNum)) {

                String mdseCd = bizMsg.S.no(i).mdseCd.getValue();

                if (serialList.containsKey(mdseCd)) {
                    List<String> list = serialList.get(mdseCd);

                    if (list.contains(serial.serNum.getValue())) {

                        if (duplicateSerialList.containsKey(mdseCd)) {
                            List<String> dupList = duplicateSerialList.get(mdseCd);
                            dupList.add(serial.serNum.getValue());
                        } else {
                            List<String> dupList = new ArrayList<String>();
                            dupList.add(serial.serNum.getValue());
                            duplicateSerialList.put(mdseCd, dupList);
                        }
                    } else {
                        list.add(serial.serNum.getValue());
                    }
                } else {
                    List<String> list = new ArrayList<String>();
                    list.add(serial.serNum.getValue());
                    serialList.put(mdseCd, list);
                }
            }
        }

        /* 11/28/2015 CSAI Y.Imazu Add CSA START */
        /*************************************************************
         * 1. Error Check
         ************************************************************/
        if (isSerChkErr(bizMsg, duplicateSerialList)) {

            ZYPEZDItemValueSetter.setValue(bizMsg.xxErrFlg_H1, ZYPConstant.FLG_ON_Y);
            return;
        }

        /*************************************************************
         * 2. Warning Check
         ************************************************************/
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxWrnSkipFlg_H1.getValue())) {

            if (isSerChkWrn(bizMsg)) {

                ZYPEZDItemValueSetter.setValue(bizMsg.xxErrFlg_H1, ZYPConstant.FLG_ON_Y);
            }
        }
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */

//      // 07/26/2013 R-OM031 Del Start
//        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {
//            NLBL3000_SCMsg serial = bizMsg.S.no(i);
//            if (ZYPCommonFunc.hasValue(serial.serNum)) {
//                if (duplicateSerialList.contains(serial.serNum.getValue())) {
//                    serial.serNum.setErrorInfo(1, NLBM1265E, new String[] {"Serial #" });
//                } else if (ZYPConstant.FLG_ON_Y.equals(serial.xxEdtModeFlg.getValue())) {
//                    if (!existsSerNum(bizMsg, serial.serNum.getValue())) {
//                        serial.serNum.setErrorInfo(1, NLBM1002E, new String[] {"Serial #", "Serial Master" });
//                    }
//                }
//            }
//        }
//      // 07/26/2013 R-OM031 Del End

        /* 11/28/2015 CSAI Y.Imazu Del CSA START */
//        // 11/27/2012 ADD START
//        // Set XX_RQST_TP_CD
//        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {
//
//            NLBL3000_SCMsg serial = bizMsg.S.no(i);
//
//            if (ZYPConstant.FLG_OFF_N.equals(serial.xxEdtModeFlg.getValue())) {
//
//                continue;
//            }
//
//            if (ZYPCommonFunc.hasValue(serial.xxRqstTpCd)) {
//
//                if ("2".equals(serial.xxRqstTpCd.getValue()) || "3".equals(serial.xxRqstTpCd.getValue())) {
//
//                    if (ZYPCommonFunc.hasValue(serial.serNum)) {
//
//                        serial.xxRqstTpCd.setValue("2");
//
//                    } else {
//
//                        serial.xxRqstTpCd.setValue("3");
//                    }
//                }
//
//                if (("1".equals(serial.xxRqstTpCd.getValue()))) {
//
//                    if (ZYPCommonFunc.hasValue(serial.serNum) && !ZYPCommonFunc.hasValue(serial.xxTrxRefPk)) {
//
//                        serial.xxRqstTpCd.setValue("1");
//
//                    } else {
//
//                        serial.xxRqstTpCd.clear();
//                    }
//                }
//
//            } else {
//
//                if (ZYPCommonFunc.hasValue(serial.serNum) && !ZYPCommonFunc.hasValue(serial.xxTrxRefPk)) {
//
//                    serial.xxRqstTpCd.setValue("1");
//
//                } else if (ZYPCommonFunc.hasValue(serial.serNum)
//                        && (!serial.serNum.getValue().equals(serial.serNum_S1.getValue()) || !serial.mdseCd.getValue().equals(serial.mdseCd_S1.getValue()))
//                        && ZYPCommonFunc.hasValue(serial.xxTrxRefPk)) {
//
//                    serial.xxRqstTpCd.setValue("2");
//
//                } else if (!ZYPCommonFunc.hasValue(serial.serNum)
//                        && ZYPCommonFunc.hasValue(serial.xxTrxRefPk)) {
//
//                    serial.xxRqstTpCd.setValue("3");
//
//                } else {
//
//                    serial.xxRqstTpCd.clear();
//                }
//            }
//        }
//
//        // Delete Serial List
//        List<Integer> deleteRowList = new ArrayList<Integer>();
//
//        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {
//
//            NLBL3000_SCMsg serial = bizMsg.S.no(i);
//
//            if (!ZYPCommonFunc.hasValue(serial.serNum) && !ZYPCommonFunc.hasValue(serial.xxRqstTpCd) && !ZYPCommonFunc.hasValue(serial.xxTrxRefPk)) {
//
//                deleteRowList.add(i);
//            }
//        }
//
//        if (0 < deleteRowList.size()) {
//
//            ZYPTableUtil.deleteRows(bizMsg.S, deleteRowList);
//        }
//        // 11/27/2012 ADD END
//
//        // 07/26/2013 R-OM031 Add Start
//        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {
//
//            NLBL3000_SCMsg serial = bizMsg.S.no(i);
//
//            if (ZYPCommonFunc.hasValue(serial.serNum)) {
//
//                if (duplicateSerialList.contains(serial.serNum.getValue())) {
//
//                    serial.serNum.setErrorInfo(1, NLBM1265E, new String[] {"Serial #" });
//                }
//            }
//        }
//        // 07/26/2013 R-OM031 Add End
        /* 11/28/2015 CSAI Y.Imazu Del CSA END */
    }

    /* 11/28/2015 CSAI Y.Imazu Add CSA START */
    /**
     * <pre>
     * Check Serial Number (Error)
     * </pre>
     * @param bizMsg NLBL3000CMsg
     * @param duplicateSerialList List<String>
     * @return boolean
     */
    private boolean isSerChkErr(NLBL3000CMsg bizMsg, HashMap<String, List<String>> duplicateSerialList) {

        boolean isSerChkErr = false;

        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.S.no(i).serNum)) {

                /*************************************************************
                 * 1. Duplicate Check
                 ************************************************************/
                String mdseCd = bizMsg.S.no(i).mdseCd.getValue();
                if(duplicateSerialList.containsKey(mdseCd)){
                    List<String> dupSerNumList = duplicateSerialList.get(mdseCd);

                    if (dupSerNumList.contains(bizMsg.S.no(i).serNum.getValue())) {

                        isSerChkErr = setErrWrnInfo(bizMsg, i, 1, NLBM1265E, new String[] {"Serial#" }, isSerChkErr);
                        continue;
                    }
                }

                /* 07/15/2016 CSAI Y.Imazu Add QC#7334 START */
                /*************************************************************
                 * 2. Already Ship/Receive Check
                 ************************************************************/
                if (ZYPCommonFunc.hasValue(bizMsg.S.no(i).xxHdrNum)) {

                    if (INBD_OTBD.OUTBOUND.equals(bizMsg.inbdOtbdCd_H1.getValue())) {

                        S21SsmEZDResult ssmResult = NLBL3000Query.getInstance().getShipSerCnt(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.S.no(i).xxHdrNum.getValue(), bizMsg.S.no(i).serNum.getValue());

                        if (ssmResult.isCodeNormal()) {

                            BigDecimal shipSerCnt = (BigDecimal) ssmResult.getResultObject();

                            if (shipSerCnt != null && shipSerCnt.intValue() > 0) {

                                isSerChkErr = setErrWrnInfo(bizMsg, i, 1, NLBM1353E, null, isSerChkErr);
                                continue;
                            }
                        }

                    } else if (INBD_OTBD.INBOUND.equals(bizMsg.inbdOtbdCd_H1.getValue())) {

                        S21SsmEZDResult ssmResult = NLBL3000Query.getInstance().getRcvSerCnt(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.S.no(i).xxHdrNum.getValue(), bizMsg.S.no(i).serNum.getValue());

                        if (ssmResult.isCodeNormal()) {

                            BigDecimal rcvSerCnt = (BigDecimal) ssmResult.getResultObject();

                            if (rcvSerCnt != null && rcvSerCnt.intValue() > 0) {

                                isSerChkErr = setErrWrnInfo(bizMsg, i, 1, NLAM1338E, null, isSerChkErr);
                                continue;
                            }
                        }
                    }
                }
                /* 07/15/2016 CSAI Y.Imazu Add QC#7334 END */

                /*************************************************************
                 * 3. Machine Master Check
                 ************************************************************/
                S21SsmEZDResult ssmResult = NLBL3000Query.getInstance().getSvcMachMstr(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.S.no(i).serNum.getValue(), bizMsg.S.no(i).mdseCd.getValue());

                if (ssmResult.isCodeNormal()) {

                    Map<String, Object> svcMachMstrMap = (Map<String, Object>) ssmResult.getResultObject();

                    if (svcMachMstrMap != null) {

                        String svcMachMstrStsCd = (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD");
                        String allocTrxHdrNum = (String) svcMachMstrMap.get("TRX_HDR_NUM");
                        BigDecimal svcConfigMatrPK = (BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK");
                        ZYPEZDItemValueSetter.setValue(bizMsg.S.no(i).svcMachMstrPk, (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.S.no(i).curLocNum, (String) svcMachMstrMap.get("CUR_LOC_NUM"));

                        // QC#19681 07/03/2017 get Serial Allocation Transaction Number.
                        String serialAllocationTrxHdrNum = getSerialAllocTrxHdrNum( //
                                bizMsg.glblCmpyCd_H1.getValue(), bizMsg.S.no(i).xxHdrNum.getValue(), bizMsg.xxHdrNum_H1.getValue(), bizMsg.inbdOtbdCd_H1.getValue());

                        if (ZYPCommonFunc.hasValue(allocTrxHdrNum) && !allocTrxHdrNum.equals(serialAllocationTrxHdrNum)) {

                            isSerChkErr = setErrWrnInfo(bizMsg, i, 1, NLZM2317E, null, isSerChkErr);
                            continue;

                        } else if (ZYPCommonFunc.hasValue(bizMsg.S.no(i).svcConfigMstrPk) && !ZYPCommonFunc.hasValue(svcConfigMatrPK)) {

                            isSerChkErr = setErrWrnInfo(bizMsg, i, 1, NLZM2319E, null, isSerChkErr);
                            continue;

                        } else if (ZYPCommonFunc.hasValue(svcConfigMatrPK) && !svcConfigMatrPK.equals(bizMsg.S.no(i).svcConfigMstrPk.getValue())) {

                            isSerChkErr = setErrWrnInfo(bizMsg, i, 1, NLZM2324E, null, isSerChkErr);
                            continue;

                        } else if (INBD_OTBD.OUTBOUND.equals(bizMsg.inbdOtbdCd_H1.getValue())
                                && (SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd) || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd))) {

                            isSerChkErr = setErrWrnInfo(bizMsg, i, 1, NLZM2318E, null, isSerChkErr);
                            continue;
                        }

                    } else {

                        bizMsg.S.no(i).svcMachMstrPk.clear();
                        bizMsg.S.no(i).curLocNum.clear();
                    }

                }

                /*************************************************************
                 * 4. Asset Master Check
                 ************************************************************/
                if (INVTY_ACCT.ASSET.equals(bizMsg.invtyAcctCd_H1.getValue())) {

                    ssmResult = NLBL3000Query.getInstance().getDsAssetMstr(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.S.no(i).serNum.getValue(), bizMsg.S.no(i).mdseCd.getValue());

                    if (ssmResult.isCodeNormal()) {

                        Map<String, Object> dsAssetMstrMap = (Map<String, Object>) ssmResult.getResultObject();

                        if (dsAssetMstrMap == null) {

                            isSerChkErr = setErrWrnInfo(bizMsg, i, 1, NLBM1290E, null, isSerChkErr);
                        }

                    } else {

                        isSerChkErr = setErrWrnInfo(bizMsg, i, 1, NLBM1290E, null, isSerChkErr);
                    }
                }
            }
        }

        return isSerChkErr;
    }

    /**
     * <pre>
     * Check Serial Number (Warning)
     * </pre>
     * @param bizMsg NLBL3000CMsg
     * @return boolean
     */
    private boolean isSerChkWrn(NLBL3000CMsg bizMsg) {

        boolean isSerChkWrn = false;

        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.S.no(i).serNum)) {

                /*************************************************************
                 * 1. Location Check
                 ************************************************************/
                if (ZYPCommonFunc.hasValue(bizMsg.S.no(i).svcMachMstrPk)) {

                    if (INBD_OTBD.OUTBOUND.equals(bizMsg.inbdOtbdCd_H1.getValue())
                            && !bizMsg.S.no(i).invtyLocCd.getValue().equals(bizMsg.S.no(i).curLocNum.getValue())) {

                        isSerChkWrn = setErrWrnInfo(bizMsg, i, 2, NLBM1299W, null, isSerChkWrn);

                    } else if (INBD_OTBD.INBOUND.equals(bizMsg.inbdOtbdCd_H1.getValue()) && ZYPCommonFunc.hasValue(bizMsg.S.no(i).curLocNum)
                            && !bizMsg.S.no(i).curLocNum.getValue().equals(bizMsg.S.no(i).invtyLocCd.getValue())) {

                        isSerChkWrn = setErrWrnInfo(bizMsg, i, 2, NLBM1337W, null, isSerChkWrn);
                    }

                } else {

                    /*************************************************************
                     * 2. Serial Range Check
                     ************************************************************/
                    S21SsmEZDResult ssmResultLst = NLBL3000Query.getInstance().getMdseSerNumRng(bizMsg.glblCmpyCd_H1.getValue(), bizMsg.S.no(i).mdseCd.getValue());

                    if (ssmResultLst.isCodeNormal()) {

                        List<Map<String, Object>> mdseSerRngList = (List<Map<String, Object>>) ssmResultLst.getResultObject();

                        if (mdseSerRngList != null) {

                            boolean isRngChkOk = false;
                            String fromSerNum = null;
                            String thruSerNum = null;

                            for (Map<String, Object> mdseSerRngMap : mdseSerRngList) {

                                fromSerNum = (String) mdseSerRngMap.get("FROM_SER_NUM");
                                thruSerNum = (String) mdseSerRngMap.get("THRU_SER_NUM");

                                if (!ZYPCommonFunc.hasValue(fromSerNum) && !ZYPCommonFunc.hasValue(thruSerNum)) {

                                    isRngChkOk = true;
                                    break;

                                } else if (ZYPCommonFunc.hasValue(fromSerNum) && !ZYPCommonFunc.hasValue(thruSerNum) && fromSerNum.compareTo(bizMsg.S.no(i).serNum.getValue()) <= 0) {

                                    if (bizMsg.S.no(i).serNum.getValue().length() >= fromSerNum.length()) {

                                        isRngChkOk = true;
                                        break;
                                    }

                                } else if (!ZYPCommonFunc.hasValue(fromSerNum) && ZYPCommonFunc.hasValue(thruSerNum) && thruSerNum.compareTo(bizMsg.S.no(i).serNum.getValue()) >= 0) {

                                    if (bizMsg.S.no(i).serNum.getValue().length() <= thruSerNum.length()) {

                                        isRngChkOk = true;
                                        break;
                                    }

                                } else if (fromSerNum.compareTo(bizMsg.S.no(i).serNum.getValue()) <= 0 && thruSerNum.compareTo(bizMsg.S.no(i).serNum.getValue()) >= 0) {

                                    if (bizMsg.S.no(i).serNum.getValue().length() >= fromSerNum.length() && bizMsg.S.no(i).serNum.getValue().length() <= thruSerNum.length()) {

                                        isRngChkOk = true;
                                        break;
                                    }
                                }
                            }

                            if (!isRngChkOk) {

                                isSerChkWrn = setErrWrnInfo(bizMsg, i, 2, NLBM1338W, new String[] {fromSerNum, thruSerNum }, isSerChkWrn);
                            }
                        }
                    }
                }

            } else {

                /*************************************************************
                 * 3. Mandatory Check
                 ************************************************************/
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.S.no(i).xxEdtModeFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.S.no(i).serNumTakeFlg.getValue())) {

                    isSerChkWrn = setErrWrnInfo(bizMsg, i, 2, NLBM1300W, new String[] {"Serial#" }, isSerChkWrn);
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_ON_Y);
        return isSerChkWrn;
    }
    /* 11/28/2015 CSAI Y.Imazu Add CSA END */

    /**
     * <pre>
     * Set Error/Warning Information
     * </pre>
     * @param bizMsg NLBL3000CMsg
     * @param errLine int
     * @param errCode int
     * @param errMsgId String
     * @param errMsgArgs String[]
     * @param isAlrdyErr boolean
     * @return boolean
     */
    private boolean setErrWrnInfo(NLBL3000CMsg bizMsg, int errLine, int errCode, String errMsgId, String[] errMsgArgs, boolean isAlrdyErr) {

        if (errMsgArgs == null) {

            bizMsg.S.no(errLine).serNum.setErrorInfo(errCode, errMsgId);

        } else {

            bizMsg.S.no(errLine).serNum.setErrorInfo(errCode, errMsgId, errMsgArgs);
        }

        if (!isAlrdyErr) {

            ZYPEZDItemValueSetter.setValue(bizMsg.xxLineNum_H1, Integer.toString(errLine));
        }

        return true;
    }

    // QC#19681 07/03/2017 Add Method
    /**
     * getSerialAllocTrxHdrNum
     * @param glblCmpyCd String
     * @param soNum String
     * @param srcOrdNum String
     * @return allocationTrxHdrNum String
     */
    private String getSerialAllocTrxHdrNum(String glblCmpyCd, String soNum, String srcOrdNum, String inbdOtbdCd) {
        // Check SO_NUM
        S21SsmEZDResult soResult = NLBL3000Query.getInstance().getTrxHdrNumForSO(glblCmpyCd, soNum);

        if (soResult.isCodeNormal()) {
            Map map = (Map) soResult.getResultObject();
            String trxHdrNum = (String) map.get("TRX_HDR_NUM");

            if (ZYPCommonFunc.hasValue(trxHdrNum)) {
                return trxHdrNum;
            }
        }

        // Check RWS_NUM
        S21SsmEZDResult rwsResult = NLBL3000Query.getInstance().getTrxHdrNumForRWS(glblCmpyCd, soNum);

        if (rwsResult.isCodeNormal()) {
            Map map = (Map) rwsResult.getResultObject();
            String trxHdrNum = (String) map.get("SER_ALLOC_TRX_HDR_NUM");
            String sceOrdTpCd = (String) map.get("SCE_ORD_TP_CD");

            if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) && INBD_OTBD.INBOUND.equals(inbdOtbdCd)) {

                S21SsmEZDResult rwsResultForSc = NLBL3000Query.getInstance().getTrxHdrNumForRwsSubContract(glblCmpyCd, soNum);

                if (rwsResultForSc.isCodeNormal()) {
                    Map map2 = (Map) rwsResultForSc.getResultObject();
                    String trxHdrNum2 = (String) map2.get("TRX_HDR_NUM");

                    if (ZYPCommonFunc.hasValue(trxHdrNum2)) {
                        return trxHdrNum2;
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(trxHdrNum)) {
                return trxHdrNum;
            }
        }

        return srcOrdNum;
    }

    // 07/26/2013 R-OM031 Del Start
//    /**
//     * search
//     * @param bizMsg Business Component Interface Message
//     * @param sharedMsg Global area information
//     */
//    private void search(NLBL3000CMsg bizMsg, NLBL3000SMsg sharedMsg) {
//
//        bizMsg.A.clear();
//        sharedMsg.A.clear();
//        bizMsg.A.setValidCount(0);
//        sharedMsg.A.setValidCount(0);
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        // Global Company Code
//        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
//        // Merchandise Code
//        ssmParam.put("mdseCd", bizMsg.mdseCd_H1.getValue());
//        // Stock Status Code
//        List<String> stkStsCdList = new ArrayList<String>();
//        stkStsCdList.add(STK_STS.GOOD);
//        stkStsCdList.add(STK_STS.RANK_B);
//        ssmParam.put("stkStsCdList", stkStsCdList.toArray(new String[0]));
//        // Serial Master Status Code
//        if (ZYPCommonFunc.hasValue(bizMsg.serMstrStsCd_H1.no(0))) {
//            List<String> serMstrStsList = new ArrayList<String>();
//            for (int i = 0; i < bizMsg.serMstrStsCd_H1.length(); i++) {
//                if (ZYPCommonFunc.hasValue(bizMsg.serMstrStsCd_H1.no(i))) {
//                    serMstrStsList.add(bizMsg.serMstrStsCd_H1.no(i).getValue());
//                }
//            }
//            ssmParam.put("serMstrStsList", serMstrStsList.toArray(new String[0]));
//        }
//        ssmParam.put("rowNum", sharedMsg.A.length() + 1);
//
//        S21SsmEZDResult ssmResult = NLBL3000Query.getInstance().search(ssmParam, sharedMsg);
//
//        if (ssmResult.isCodeNotFound()) {
//
//            bizMsg.xxPageShowFromNum.clear();
//            bizMsg.xxPageShowToNum.clear();
//            bizMsg.xxPageShowOfNum.clear();
//
//            bizMsg.setMessageInfo(NZZM0000E);
//
//        } else {
//            if (ssmResult.getQueryResultCount() > sharedMsg.A.length()) {
//                bizMsg.setMessageInfo(NZZM0001W);
//                sharedMsg.A.setValidCount(sharedMsg.A.length());
//            } else {
//                sharedMsg.A.setValidCount(ssmResult.getQueryResultCount());
//            }
//
//            int j = 0;
//            for (; j < sharedMsg.A.getValidCount(); j++) {
//
//                NLBL3000_ASMsg asMsg = sharedMsg.A.no(j);
//
//                if (j < bizMsg.A.length()) {
//
//                    EZDMsg.copy(asMsg, null, bizMsg.A.no(j), null);
//                    bizMsg.A.setValidCount(j + 1);
//                }
//            }
//
//            bizMsg.xxPageShowFromNum.setValue(1);
//            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
//            bizMsg.xxPageShowOfNum.setValue(sharedMsg.A.getValidCount());
//        }
//    }
//
//    /**
//     * Existence Serial Number
//     * @param serNum String
//     * @param bizMsg Business Component Interface Message
//     * @return
//     */
//    private boolean existsSerNum(NLBL3000CMsg bizMsg, String serNum) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        // Global Company Code
//        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
//        // Merchandise Code
//        ssmParam.put("mdseCd", bizMsg.mdseCd_H1.getValue());
//        // Stock Status Code
//        ssmParam.put("stkStsCd_GOOD", STK_STS.GOOD);
//        // Serial Number
//        ssmParam.put("serNum", serNum);
//
//        // Serial Master Status Code
//        if (ZYPCommonFunc.hasValue(bizMsg.serMstrStsCd_H1.no(0))) {
//            List<String> serMstrStsList = new ArrayList<String>();
//            for (int i = 0; i < bizMsg.serMstrStsCd_H1.length(); i++) {
//                if (ZYPCommonFunc.hasValue(bizMsg.serMstrStsCd_H1.no(i))) {
//                    serMstrStsList.add(bizMsg.serMstrStsCd_H1.no(i).getValue());
//                }
//            }
//            ssmParam.put("serMstrStsList", serMstrStsList.toArray(new String[0]));
//        }
//
//        S21SsmEZDResult ssmResult = NLBL3000Query.getInstance().getSerNum(ssmParam);
//
//        return !ssmResult.isCodeNotFound();
//    }
    // 07/26/2013 R-OM031 Del End
}
