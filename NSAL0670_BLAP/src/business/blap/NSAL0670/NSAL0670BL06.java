/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0670;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static business.blap.NSAL0670.constant.NSAL0670Constant.*;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0670.common.NSAL0670CommonLogic;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTL_STS_VTMsg;
import business.db.DS_CONTR_DTL_STS_VTMsgArray;
import business.db.DS_CONTR_TRKTMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxSvcMemoListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC046001.NSZC046001;
import com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Cancel Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kasai         Create          N/A
 * 2015/12/15   Hitachi         K.Kasai         Update          QC1659
 * 2015/12/21   Hitachi         K.Kasai         Update          QC2219
 * 2015/12/21   Hitachi         K.Kasai         Update          QC2224
 * 2016/06/03   Hitachi         T.Tomita        Update          QC1523, 4624
 * 2016/11/28   Hitachi         T.Mizuki        Update          QC4210
 * 2017/05/08   Hitachi         K.Kitachi       Update          QC18268
 * 2017/05/24   Hitachi         K.Kitachi       Update          QC18268
 *</pre>
 */
public class NSAL0670BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0670CMsg cMsg = (NSAL0670CMsg) arg0;
        // mod start 2016/11/28 CSA QC#4210
        NSAL0670SMsg sMsg = (NSAL0670SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0670Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0670Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0670Scrn00_CMN_Submit(NSAL0670CMsg cMsg, NSAL0670SMsg sMsg) {

        NSAL0670CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        //Validation
        if (!checkExistSelectCheckbox(sMsg)) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        //Update Process
        NSAL0670_ASMsg aSMsgIn = new NSAL0670_ASMsg();
        BigDecimal preContrPk = BigDecimal.ZERO;
        BigDecimal curContrPk = BigDecimal.ZERO;
        boolean contrHdrProc = false;
        boolean errFlg = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            aSMsgIn = sMsg.A.no(i);
            curContrPk = aSMsgIn.dsContrPk_A1.getValue();
            if (!curContrPk.equals(preContrPk)) {
                contrHdrProc = false;
            }
            //call api
            if (FLG_ON_Y.equals(aSMsgIn.xxChkBox_A1.getValue())) {
                if (BigDecimal.ZERO.equals(aSMsgIn.dsContrDtlPk_A1.getValue())) {
                    //Contract header and Contract Detail Process
                    // ADD START 2015/12/15 K.Kasai [QC1659]
                    if (!checkExclHdr(cMsg, aSMsgIn)) {
                        // record updated or deleted by somebody else
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RETURN_MSG_EXCL);
                        errFlg = true;
                        continue;
                    }
                    // ADD END 2015/12/15 K.Kasai [QC1659]
                    NSZC046001PMsg pMsg = setParamForHeader(cMsg, aSMsgIn);
                    // ADD START 2015/12/15 K.Kasai [QC1659]
                    if (pMsg == null) {
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RETURN_MSG_NO_DTL);
                        errFlg = true;
                        continue;
                    }
                    // ADD END 2015/12/15 K.Kasai [QC1659]
                    errFlg = executeApi(pMsg, aSMsgIn);
                    contrHdrProc = true;
                } else if (!contrHdrProc) {
                    //Contract Detail Process
                    // ADD START 2015/12/15 K.Kasai [QC1659]
                    if (!checkExclDtl(cMsg, aSMsgIn)) {
                        // record updated or deleted by somebody else
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RETURN_MSG_EXCL);
                        errFlg = true;
                        continue;
                    }
                    // ADD END 2015/12/15 K.Kasai [QC1659]
                    NSZC046001PMsg pMsg = setParamForDetail(cMsg, aSMsgIn);
                    errFlg = executeApi(pMsg, aSMsgIn);
                }
                // add start 2016/06/03 CSA Defect#1523, 4624
                if (!callContrTrkAPI(cMsg, curContrPk)) {
                    errFlg = true;
                    continue;
                }
                // add end 2016/06/03 CSA Defect#1523, 4624
            }
            preContrPk = curContrPk;
        }
        if (errFlg) {
            cMsg.setMessageInfo(NSAM0394W, new String[] {});
        } else {
            cMsg.setMessageInfo(NSAM0200I, new String[] {});
        }

        int noCmsg = cMsg.A.no(0).xxRowNum_A1.getValueInt();
        cMsg.A.clear();
        int count = 0;
        for (int i = noCmsg; i < sMsg.A.getValidCount(); i++) {
            if (count < cMsg.A.length()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(count), null);
                count++;
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(count);

    }
    // ADD START 2015/12/15 K.Kasai [QC1659]
    /**
     * @param cMsg
     * @param aSMsgIn
     * @return boolean
     */
    private boolean checkExclHdr(NSAL0670CMsg cMsg, NSAL0670_ASMsg aSMsgIn) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.dsContrPk, aSMsgIn.dsContrPk_A1);
        DS_CONTRTMsg checkMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        // Time stamp Check
        String beforeEzUpTime = aSMsgIn.ezUpTime_AH.getValue();
        String beforeEzUpTimeZone = aSMsgIn.ezUpTimeZone_AH.getValue();
        String currentEzUpTime = checkMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = checkMsg.ezUpTimeZone.getValue();
        return ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone);
    }
    /**
     * @param cMsg
     * @param aSMsgIn
     * @return boolean
     */
    private boolean checkExclDtl(NSAL0670CMsg cMsg, NSAL0670_ASMsg aSMsgIn) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, aSMsgIn.dsContrDtlPk_A1);
        DS_CONTR_DTLTMsg checkMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        // Time stamp Check
        String beforeEzUpTime = aSMsgIn.ezUpTime_AD.getValue();
        String beforeEzUpTimeZone = aSMsgIn.ezUpTimeZone_AD.getValue();
        String currentEzUpTime = checkMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = checkMsg.ezUpTimeZone.getValue();
        return ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone);
    }

    /**
     * @param cMsg
     * @param aSMsg
     * @return DS_CONTR_DTL_STS_VTMsg
     */
    private static DS_CONTR_DTL_STS_VTMsg getDsContrDtlPk(NSAL0670CMsg cMsg, NSAL0670_ASMsg aSMsg) {
        DS_CONTR_DTL_STS_VTMsg inDtlStsVTMsg = new DS_CONTR_DTL_STS_VTMsg();
        inDtlStsVTMsg.setSQLID("005");
        inDtlStsVTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inDtlStsVTMsg.setConditionValue("dsContrPk01", aSMsg.dsContrPk_A1.getValue());
        inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd01", DS_CONTR_CTRL_STS.ENTERED);
        inDtlStsVTMsg.setConditionValue("dsContrCtrlStsCd02", DS_CONTR_CTRL_STS.DRAFT);
        return inDtlStsVTMsg;
    }

    /**
     * @param cMsg
     * @param aCMsg
     * @return DS_CONTR_DTL_STS_VTMsgArray
     */
    private static DS_CONTR_DTL_STS_VTMsg getDsContrDtlStsV(DS_CONTR_DTLTMsg tMsg) {
        DS_CONTR_DTL_STS_VTMsg inTMsg = new DS_CONTR_DTL_STS_VTMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", tMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("dsContrDtlPk01", tMsg.dsContrDtlPk.getValue());
        DS_CONTR_DTL_STS_VTMsgArray tMsgArray = (DS_CONTR_DTL_STS_VTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (tMsgArray.length() == 0) {
            return null;
        }
        return (DS_CONTR_DTL_STS_VTMsg) tMsgArray.get(0);
    }

    // START 2017/05/08 K.Kitachi [QC#18268, DEL]
//    /**
//     * @param cMsg
//     * @param aSMsg
//     * @return DS_CONTR_DTLTMsgArray
//     */
//    private static DS_CONTR_DTLTMsgArray getDsContrDtl(NSAL0670CMsg cMsg, NSAL0670_ASMsg aSMsg) {
//        DS_CONTR_DTLTMsg inTMsg = new DS_CONTR_DTLTMsg();
//        inTMsg.setSQLID("001");
//        inTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
//        inTMsg.setConditionValue("dsContrPk01", aSMsg.dsContrPk_A1.getValue());
//        return (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
//    }
    // END 2017/05/08 K.Kitachi [QC#18268, DEL]

    /**
     * @param pMsg NSZC046001PMsg
     * @param aSMsgIn NSAL0670_ASMsg
     * @return boolean
     */
    private static boolean hasValueList(NSZC046001PMsg pMsg, NSAL0670_ASMsg aSMsgIn) {
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg); 
            S21ApiMessage msg = msgList.get(0);
            setValue(aSMsgIn.xxGenlFldAreaTxt_A1, getRtnMsg(msg.getXxMsgid(), msg.getXxMsgPrmArray()));
            return true;
        }
        if (hasValue(pMsg.xxMsgId_HD)) {
            setValue(aSMsgIn.xxGenlFldAreaTxt_A1, pMsg.xxDsMultMsgDplyTxt_HD.getValue());
            return true;
        }
        for (int i = 0; i < pMsg.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg contrDtlPMsg = pMsg.xxContrDtlList.no(i);
            if (hasValue(contrDtlPMsg.xxMsgId_DT)) {
                setValue(aSMsgIn.xxGenlFldAreaTxt_A1, contrDtlPMsg.xxDsMultMsgDplyTxt_DT.getValue());
                return true;
            }
        }
        for (int i = 0; i < pMsg.xxSvcMemoList.getValidCount(); i++) {
            NSZC046001_xxSvcMemoListPMsg svcMemoPMsg = pMsg.xxSvcMemoList.no(i);
            if (hasValue(svcMemoPMsg.xxMsgId)) {
                setValue(aSMsgIn.xxGenlFldAreaTxt_A1, svcMemoPMsg.xxDsMultMsgDplyTxt.getValue());
                return true;
            }
        }
        return false;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    private static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }
    // ADD END 2015/12/15 K.Kasai [QC1659]

    /**
     * @param sMsg
     * @param existCheck
     * @return
     */
    private boolean checkExistSelectCheckbox(NSAL0670SMsg sMsg) {
        boolean existCheck = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                existCheck = true;
                break;
            }
        }
        return existCheck;
    }

    /**
     * @param cMsg
     * @param aSMsgIn
     * @param index
     */
    private NSZC046001PMsg setParamForHeader(NSAL0670CMsg cMsg, NSAL0670_ASMsg aSMsgIn) {
        NSZC046001PMsg inPMsg = new NSZC046001PMsg();
        setValue(inPMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inPMsg.xxModeCd, NSZC046001Constant.MODE_DELETE);
        setValue(inPMsg.slsDt, ZYPDateUtil.getSalesDate());
        setValue(inPMsg.dsContrSrcTpCd, aSMsgIn.dsContrSrcTpCd_A1);
        setValue(inPMsg.psnCd, cMsg.getUserID());
        setValue(inPMsg.dsContrPk, aSMsgIn.dsContrPk_A1);
        setValue(inPMsg.dsContrNum, aSMsgIn.dsContrNum_A1);
        int j = 0;
        // MOD START 2015/12/15 K.Kasai [QC1659]
//        while (aCMsgIn.dsContrPk_A1.getValue().equals(cMsg.A.no(i).dsContrPk_A1.getValue())) {
//            setValue(inPMsg.xxContrDtlList.no(j).dsContrDtlPk, cMsg.A.no(i).dsContrDtlPk_A1);
//            setValue(inPMsg.xxContrDtlList.no(j).dsContrDtlStsCd, DS_CONTR_DTL_STS.CANCELLED);
//            i++;
//            j++;
//        }
        // START 2017/05/24 K.Kitachi [QC#18268, MOD]
//        DS_CONTR_DTL_STS_VTMsg inDtlStsVTMsg = getDsContrDtlPk(cMsg, aSMsgIn);
//        DS_CONTR_DTL_STS_VTMsgArray outDtlStsVTMsgArray = (DS_CONTR_DTL_STS_VTMsgArray) EZDTBLAccessor.findByCondition(inDtlStsVTMsg);
//        for (; j < outDtlStsVTMsgArray.getValidCount(); j++) {
//            setValue(inPMsg.xxContrDtlList.no(j).dsContrDtlPk, outDtlStsVTMsgArray.no(j).dsContrDtlPk);
//            setValue(inPMsg.xxContrDtlList.no(j).dsContrDtlStsCd, DS_CONTR_DTL_STS.CANCELLED);
//            setParamForBllgMtr(cMsg, dsContrDtlPk, inPMsg);
//        }
        List<BigDecimal> dsContrDtlPkList = NSAL0670Query.getInstance().getDsContrDtlPkListFromDtlStsV(cMsg, aSMsgIn);
        for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
            setValue(inPMsg.xxContrDtlList.no(j).dsContrDtlPk, dsContrDtlPk);
            setValue(inPMsg.xxContrDtlList.no(j).dsContrDtlStsCd, DS_CONTR_DTL_STS.CANCELLED);
            setParamForBllgMtr(cMsg, dsContrDtlPk, inPMsg);
            j++;
        }
        // END 2017/05/24 K.Kitachi [QC#18268, MOD]
        // MOD END 2015/12/15 K.Kasai [QC1659]
        setValue(inPMsg.xxSvcMemoList.no(0).xxModeCd, NSZC046001Constant.XX_MODE_CREATE);
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.CANCEL_CONTRACT_OR_MACHINE);
        setValue(inPMsg.xxSvcMemoList.no(0).svcCmntTxt, cMsg.svcCmntTxt_H1);
        setValue(inPMsg.xxSvcMemoList.no(0).dsContrPk, aSMsgIn.dsContrPk_A1);
        setValue(inPMsg.xxSvcMemoList.no(0).lastUpdUsrId, cMsg.getUserID());
        setValue(inPMsg.xxSvcMemoList.no(0).lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        inPMsg.xxContrDtlList.setValidCount(j);
        inPMsg.xxSvcMemoList.setValidCount(1);
        return inPMsg;
    }

    /**
     * @param cMsg
     * @param aCMsgIn
     * @param index
     * @param i
     */
    private NSZC046001PMsg setParamForDetail(NSAL0670CMsg cMsg, NSAL0670_ASMsg aSMsgIn) {
        NSZC046001PMsg inPMsg = new NSZC046001PMsg();
        setValue(inPMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inPMsg.xxModeCd, NSZC046001Constant.MODE_DELETE);
        setValue(inPMsg.slsDt, ZYPDateUtil.getSalesDate());
        setValue(inPMsg.dsContrSrcTpCd, aSMsgIn.dsContrSrcTpCd_A1);
        setValue(inPMsg.psnCd, cMsg.getUserID());
        setValue(inPMsg.dsContrPk, aSMsgIn.dsContrPk_A1);
        setValue(inPMsg.dsContrNum, aSMsgIn.dsContrNum_A1);
        // START 2017/05/08 K.Kitachi [QC#18268, MOD]
//        DS_CONTR_DTLTMsgArray outDtlTMsgArray = getDsContrDtl(cMsg, aSMsgIn);
        List<BigDecimal> dsContrDtlPkList = NSAL0670Query.getInstance().getDsContrDtlPkList(cMsg, aSMsgIn);
//        if (outDtlTMsgArray.getValidCount() == 0) {
        if (dsContrDtlPkList.size() == 0) {
            return null;
        }
        BigDecimal dsContrDtlPk = null;
        boolean cancelHeader = false;
//        for (int j = 0; j < outDtlTMsgArray.getValidCount(); j++) {
        for (int j = 0; j < dsContrDtlPkList.size(); j++) {
//            DS_CONTR_DTLTMsg dtlTMsg = outDtlTMsgArray.no(j);
            DS_CONTR_DTLTMsg dtlTMsg = getDsContrDtlTMsg(cMsg.glblCmpyCd.getValue(), dsContrDtlPkList.get(j));
            if (!hasValue(aSMsgIn.dsContrDtlPk_A1)
                    || dtlTMsg == null
                    || aSMsgIn.dsContrDtlPk_A1.getValue().compareTo(dtlTMsg.dsContrDtlPk.getValue()) == 0) {
                continue;
            }
            DS_CONTR_DTL_STS_VTMsg dtlStsVTMsg = getDsContrDtlStsV(dtlTMsg);
            if (dtlStsVTMsg == null) {
                continue;
            }
            if (DS_CONTR_DTL_TP.FLEET.equals(dtlTMsg.dsContrDtlTpCd.getValue())
                    || DS_CONTR_DTL_TP.AGGREGATE.equals(dtlTMsg.dsContrDtlTpCd.getValue())) {
                dsContrDtlPk = dtlTMsg.dsContrDtlPk.getValue();
                if (DS_CONTR_CTRL_STS.DRAFT.equals(dtlStsVTMsg.dsContrCtrlStsCd.getValue())
                        || DS_CONTR_CTRL_STS.ENTERED.equals(dtlStsVTMsg.dsContrCtrlStsCd.getValue())) {
                    cancelHeader = true;
                }
            } else {
                if (DS_CONTR_CTRL_STS.DRAFT.equals(dtlStsVTMsg.dsContrCtrlStsCd.getValue())
                        || DS_CONTR_CTRL_STS.ENTERED.equals(dtlStsVTMsg.dsContrCtrlStsCd.getValue())) {
                    cancelHeader = false;
                }
            }
        }
        // END 2017/05/08 K.Kitachi [QC#18268, MOD]
        setValue(inPMsg.xxContrDtlList.no(0).dsContrDtlPk, aSMsgIn.dsContrDtlPk_A1);
        setValue(inPMsg.xxContrDtlList.no(0).dsContrDtlStsCd, DS_CONTR_DTL_STS.CANCELLED);
        inPMsg.xxContrDtlList.setValidCount(1);
        if (hasValue(dsContrDtlPk)
                 && cancelHeader) {
            setValue(inPMsg.xxContrDtlList.no(1).dsContrDtlPk, dsContrDtlPk);
            setValue(inPMsg.xxContrDtlList.no(1).dsContrDtlStsCd, DS_CONTR_DTL_STS.CANCELLED);
            inPMsg.xxContrDtlList.setValidCount(2);
            // START 2017/05/08 K.Kitachi [QC#18268, ADD]
            setParamForBllgMtr(cMsg, dsContrDtlPk, inPMsg);
            // END 2017/05/08 K.Kitachi [QC#18268, ADD]
        }
        setValue(inPMsg.xxSvcMemoList.no(0).xxModeCd, NSZC046001Constant.XX_MODE_CREATE);
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.CANCEL_CONTRACT_OR_MACHINE);
        setValue(inPMsg.xxSvcMemoList.no(0).svcCmntTxt, cMsg.svcCmntTxt_H1);
        setValue(inPMsg.xxSvcMemoList.no(0).dsContrPk, aSMsgIn.dsContrPk_A1);
        setValue(inPMsg.xxSvcMemoList.no(0).dsContrDtlPk, aSMsgIn.dsContrDtlPk_A1);
        setValue(inPMsg.xxSvcMemoList.no(0).lastUpdUsrId, cMsg.getUserID());
        setValue(inPMsg.xxSvcMemoList.no(0).lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        inPMsg.xxSvcMemoList.setValidCount(1);
        // START 2017/05/08 K.Kitachi [QC#18268, ADD]
        setParamForBllgMtr(cMsg, aSMsgIn.dsContrDtlPk_A1.getValue(), inPMsg);
        // END 2017/05/08 K.Kitachi [QC#18268, ADD]
        return inPMsg;
    }

    /**
     * @param pMsg NSZC046001PMsg
     * @param aSMsgIn NSAL0670_ASMsg
     */
    public static boolean executeApi(NSZC046001PMsg pMsg, NSAL0670_ASMsg aSMsgIn) {
        boolean errFlg = false;
        NSZC046001 api = new NSZC046001();
        api.execute(pMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        // MOD START 2015/12/15 K.Kasai [QC1659]
//        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
//            setValue(aCMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
        if (hasValueList(pMsg, aSMsgIn)) {
            // MOD END 2015/12/15 K.Kasai [QC1659]
            // ***** rollback
            EZDConnectionMgr.getInstance().rollback();
            errFlg = true;
        } else {
            setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
            // mod end 2016/11/28 CSA QC#4210
            // ***** commit
            EZDConnectionMgr.getInstance().commit();
        }
        return errFlg;
    }

    // add start 2016/06/03 CSA Defect#1523, 4624
    private boolean callContrTrkAPI(NSAL0670CMsg cMsg, BigDecimal dsContrPk) {
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String userId = getContextUserInfo().getUserId();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String stsMemoRsnCd = DS_CONTR_TRK_RSN.CONTRACT_MODE_CHANGE;
        String stsMemoTxt = cMsg.svcCmntTxt_H1.getValue();
        DS_CONTR_TRKTMsg dsContrTrkTMsg = new DS_CONTR_TRKTMsg();
        if (hasValue(stsMemoTxt) && stsMemoTxt.length() > dsContrTrkTMsg.getAttr("stsMemoTxt").getDigit()) {
            stsMemoTxt = stsMemoTxt.substring(0, dsContrTrkTMsg.getAttr("stsMemoTxt").getDigit());
        }
        if (!NSXC001001ContractTracking.callContrTrk(glblCmpyCd, ContrTrkProcMode.USER_OPERATION.code, dsContrPk, userId, slsDt, stsMemoRsnCd, stsMemoTxt, ONBATCH_TYPE.ONLINE)) {
            cMsg.setMessageInfo(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }
    // add end 2016/06/03 CSA Defect#1523, 4624

    // START 2017/05/08 K.Kitachi [QC#18268, ADD]
    private DS_CONTR_DTLTMsg getDsContrDtlTMsg(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private void setParamForBllgMtr(NSAL0670CMsg cMsg, BigDecimal dsContrDtlPk, NSZC046001PMsg inPMsg) {
        DS_CONTR_BLLG_MTRTMsgArray tMsgArray = getDsContrBllgMtrList(cMsg.glblCmpyCd.getValue(), dsContrDtlPk);
        int bllgMtrCnt = inPMsg.xxDsContrBllgMtrList.getValidCount();
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            setValue(inPMsg.xxDsContrBllgMtrList.no(bllgMtrCnt).dsContrDtlPk, tMsgArray.no(i).dsContrDtlPk);
            setValue(inPMsg.xxDsContrBllgMtrList.no(bllgMtrCnt).dsContrBllgMtrPk, tMsgArray.no(i).dsContrBllgMtrPk);
            bllgMtrCnt++;
        }
        inPMsg.xxDsContrBllgMtrList.setValidCount(bllgMtrCnt);
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg inTMsg = new DS_CONTR_BLLG_MTRTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    }
    // END 2017/05/08 K.Kitachi [QC#18268, ADD]
}
