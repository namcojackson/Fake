/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3120;

import static business.blap.NLBL3120.constant.NLBL3120Constant.NATM0001W;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM0002E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM0009E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM0052E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1231E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1295E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1301W;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1303E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1307E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1308E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1310E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1327W;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1328E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1381E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1328W;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLZM2273E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLZM2274E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLZM2278E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.SCRN_ID;
import static business.blap.NLBL3120.constant.NLBL3120Constant.ZZM9000E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.ZZM9037E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLBL3120.common.NLBL3120CommonLogic;
import business.blap.NLBL3120.constant.NLBL3120Constant;
import business.db.RTRN_TRK_NTFY_WRKTMsg;
import business.db.RTRN_TRK_STSTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_SCHD_DTL_TRKTMsg;
import business.db.SCHD_COORD_ASGTMsg;
import business.db.SCHD_COORD_ASGTMsgArray;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsg;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsgArray;
import business.parts.NLZC205001PMsg;
import business.parts.NWZC180001PMsg;
import business.parts.NWZC180001_xxMsgIdListPMsg;

import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_TRK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 * 2016/11/21   CITS            T.Tokutomi      Update          QC#15145
 * 2016/12/19   CITS            M.Naito         Update          QC#16280
 * 2017/06/28   CITS            T.Kikuhara      Update          QC#18993
 * 2016/06/28   CITS            T.Kikuhara      Update          QC#19137
 * 2017/06/28   CITS            T.Kikuhara      Update          QC#19524
 * 2023/08/01   Hitachi         M.Kikushima     Update          QC#61677
 *</pre>
 */
public class NLBL3120BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3120Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_CMN_Submit((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_Release_SO".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_Release_SO((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_SaveSearch((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_DeleteSearch((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_CMN_Submit
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_CMN_Submit(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // Input Check
        if (!inputCheckSubmit(cMsg, sMsg, glblCmpyCd)) {
            // Having Error
            return;
        }
        // Warning
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {
            if (!inputCheckWaringSubmit(cMsg, sMsg, glblCmpyCd, slsDt)) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                cMsg.setMessageInfo(NATM0001W, new String[]{"Submit"});
                return;
            }
        }
        cMsg.xxWrnSkipFlg.clear();

        // Update SHPG_ORD_SCHD
        int errIndex = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

            if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())) {
                continue;
            }

            if (!changedValueLine(sMsgALine)) {
                continue;
            }

            // SO
            if (ZYPCommonFunc.hasValue(sMsgALine.soNum_A1)) {
                if (!updateSo(cMsg, sMsgALine, glblCmpyCd, slsDt)) {
                    errIndex = i;
                    break;
                }

            // RWS
            } else if (ZYPCommonFunc.hasValue(sMsgALine.rwsNum_A1)) {
                if (!updateRws(cMsg, sMsgALine, glblCmpyCd, slsDt)) {
                    errIndex = i;
                    break;
                }
            }
        }

        if (0 <= errIndex) {
            int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndexForError(errIndex, cMsg, sMsg);
            int fromNum = nextRecIdx;
            NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
        }
        // Normal End
        cMsg.setMessageInfo(ZZZM9003I, new String[]{"Submit"});
        return;
    }

    /**
     * doProcess_NLBL3120Scrn00_Release_SO
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_Release_SO(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        String glblCmpyCd = getGlobalCompanyCode();

        // Input check
        if (!inputCheckReleaseSo(cMsg, sMsg, glblCmpyCd)) {
            return;
        }

        // Update SHPG_ORD
        int errIndex = -1;

        NLZC205001 nlzc205001 = new NLZC205001();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxPgFlg_A1.getValue())) {
                continue;
            }

            if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())) {
                continue;
            }

            ArrayList<NLZC205001PMsg> soApiList = new ArrayList<NLZC205001PMsg>();
            NLZC205001PMsg pMsg = new NLZC205001PMsg();

            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.soNum, sMsgALine.soNum_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC205001.MODE_WMS_DROP);

            soApiList.add(pMsg);

            nlzc205001.execute(soApiList, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(soApiList.get(NLBL3120Constant.FIRST_RECORD))) {
                NLZC205001PMsg result = soApiList.get(NLBL3120Constant.FIRST_RECORD);
                sMsgALine.xxPgFlg_A1.setErrorInfo(1, result.xxMsgIdList.no(NLBL3120Constant.FIRST_RECORD).xxMsgId.getValue());

                if (errIndex == -1) {
                    errIndex = i;
                }
            }
        }

        if (0 <= errIndex) {
            int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndexForError(errIndex, cMsg, sMsg);
            int fromNum = nextRecIdx;
            NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
        }
        // Normal End
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Drop SO" });
        return;
    }

    /**
     * 
     * @param sMsgALine
     * @return Error Index
     */
    private boolean updateSo(NLBL3120CMsg cMsg, NLBL3120_ASMsg sMsgALine, String glblCmpyCd, String slsDt) {

        // Update SHPG_ORD
        SHPG_ORDTMsg shpgOrd = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrd.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrd.soNum, sMsgALine.soNum_A1);

        shpgOrd = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shpgOrd);
        if (shpgOrd == null) {
            // Error
            sMsgALine.xxChkBox_A2.setErrorInfo(1, NLBM0009E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(sMsgALine.ezUpTime_SO.getValue(), sMsgALine.ezUpTimeZone_SO.getValue(), shpgOrd.ezUpTime.getValue(), shpgOrd.ezUpTimeZone.getValue())) {
            // anyone update
            sMsgALine.xxChkBox_A2.setErrorInfo(1, NLBM0009E);
            return false;
        }

        // execute SO API
        NLZC205001PMsg pMsg = new NLZC205001PMsg();
        ArrayList<NLZC205001PMsg> pMsgList = new ArrayList<NLZC205001PMsg>();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, sMsgALine.soNum_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC205001.MODE_MODIFY);
        // START 2023/08/01 M.Kikushima [QC#61677, MOD]
        //QC#19137 DEL START
        //ZYPEZDItemValueSetter.setValue(pMsg.schdCoordStsCd, getSchdCoordStsSo(sMsgALine));
        ZYPEZDItemValueSetter.setValue(pMsg.schdCoordStsCd, sMsgALine.schdCoordStsCd_A1.getValue());
        //QC#19137 DEL END
        // END 2023/08/01 M.Kikushima [QC#61677, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.tempSchdRsnCd, shpgOrd.tempSchdRsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.tempSchdCmntTxt, shpgOrd.tempSchdCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.schdDurnTmNum, shpgOrd.schdDurnTmNum);
        ZYPEZDItemValueSetter.setValue(pMsg.schdIstlDt, shpgOrd.schdIstlDt);
        ZYPEZDItemValueSetter.setValue(pMsg.schdIstlTm, shpgOrd.schdIstlTm);

        if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.rddDt, sMsgALine.schdPickUpDt_A1);
        }
        if (ZYPCommonFunc.hasValue(sMsgALine.schdCoordPsnCd_A1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.schdCoordPsnCd, sMsgALine.schdCoordPsnCd_A1);
        }
        if (ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.carrCd, sMsgALine.carrCd_A1);
        }
        if (ZYPCommonFunc.hasValue(sMsgALine.shpgSvcLvlCd_A1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, sMsgALine.shpgSvcLvlCd_A1);
        }
        if (ZYPCommonFunc.hasValue(sMsgALine.carrAcctNum_A1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, sMsgALine.carrAcctNum_A1);
        }

        pMsgList.add(pMsg);

        NLZC205001 nlzc205001 = new NLZC205001();
        nlzc205001.execute(pMsgList, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsgList.get(NLBL3120Constant.FIRST_RECORD))) {
            sMsgALine.xxChkBox_A2.setErrorInfo(1, //
                    pMsgList.get(NLBL3120Constant.FIRST_RECORD).xxMsgIdList.no(NLBL3120Constant.FIRST_RECORD).xxMsgId.getValue());

            return false;
        }

        return true;
    }

    private boolean updateRws(NLBL3120CMsg cMsg, NLBL3120_ASMsg sMsgALine, String glblCmpyCd, String slsDt) {

        S21SsmEZDResult ssmRes = NLBL3120Query.getInstance().getRwsSchdDtlForUpd(sMsgALine);
        if (!ssmRes.isCodeNormal()) {
            // Error
            sMsgALine.xxChkBox_A2.setErrorInfo(1, NLBM0009E);
            return false;
        }

        List<Map<String, String>> resList = (List<Map<String, String>>) ssmRes.getResultObject();

        for (Map<String, String> resMap : resList) {
            RWS_DTLTMsg rwsDtl = new RWS_DTLTMsg();
            rwsDtl.glblCmpyCd.setValue(glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwsDtl.rwsNum, resMap.get("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(rwsDtl.rwsDtlLineNum, resMap.get("RWS_DTL_LINE_NUM"));

            rwsDtl = (RWS_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(rwsDtl);

            String rtrnRsnCd = resMap.get("RTRN_RSN_CD");
            String mdseCd = resMap.get("MDSE_CD");

            String rtrnTrkStsCd = getRtrnTrkSts(sMsgALine, glblCmpyCd, slsDt, rtrnRsnCd, mdseCd);
            if (rtrnTrkStsCd == null) {
                sMsgALine.xxChkBox_A2.setErrorInfo(1, ZZM9037E);
                return false;
            }

            // Check time stamp
            if (sMsgALine.ezUpTime_RS.getValue().compareTo(rwsDtl.ezUpTime.getValue()) < 0) {
                // anyone update
                sMsgALine.xxChkBox_A2.setErrorInfo(1, NLBM0009E);
                return false;
            }

            // Update
            ZYPEZDItemValueSetter.setValue(rwsDtl.schdCoordPsnCd, sMsgALine.schdCoordPsnCd_A1);
            ZYPEZDItemValueSetter.setValue(rwsDtl.schdPickUpDt, sMsgALine.schdPickUpDt_A1);
            ZYPEZDItemValueSetter.setValue(rwsDtl.shpgSvcLvlCd, sMsgALine.shpgSvcLvlCd_A1);
            ZYPEZDItemValueSetter.setValue(rwsDtl.carrCd, sMsgALine.carrCd_A1);
            ZYPEZDItemValueSetter.setValue(rwsDtl.rtrnTrkStsCd,  rtrnTrkStsCd);
            //QC#19137 DEL START
            //ZYPEZDItemValueSetter.setValue(rwsDtl.schdCoordStsCd, getSchdCoordStsRws(sMsgALine));
            //QC#19137 DEL END

            EZDTBLAccessor.update(rwsDtl);
            String returnCode = rwsDtl.getReturnCode();
            if (!RTNCD_NORMAL.equals(returnCode)) {
                cMsg.setMessageInfo(NLBM1295E, new String[]{new RWS_DTLTMsg().getTableName()});
                return false;
            }

            // Insert RWS_SCHD_DTL_TRK
            RWS_SCHD_DTL_TRKTMsg rwsSchdDtlTrk = new RWS_SCHD_DTL_TRKTMsg();
            EZDMsg.copy(rwsDtl, null, rwsSchdDtlTrk, null);
            BigDecimal rwsSchdDtlTrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RWS_SCHD_DTL_TRK_SQ);
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.rwsSchdDtlTrkPk, rwsSchdDtlTrkPk);
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.updUsrId, rwsDtl.ezUpUserID);
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.updTs, rwsDtl.ezUpTime);
            EZDTBLAccessor.insert(rwsSchdDtlTrk);
            returnCode = rwsSchdDtlTrk.getReturnCode();
            if (!RTNCD_NORMAL.equals(returnCode)) {
                cMsg.setMessageInfo(NLBM1295E, new String[]{new RWS_SCHD_DTL_TRKTMsg().getTableName()});
                return false;
            }

            RTRN_TRK_STSTMsg rtrnTrkSts = new RTRN_TRK_STSTMsg();
            rtrnTrkSts.glblCmpyCd.setValue(glblCmpyCd);
            rtrnTrkSts.rtrnTrkStsCd.setValue(rtrnTrkStsCd);
            rtrnTrkSts = (RTRN_TRK_STSTMsg) S21CodeTableAccessor.findByKey(rtrnTrkSts);
            if (ZYPConstant.FLG_ON_Y.equals(rtrnTrkSts.ntfyMlSendFlg.getValue())) {

                // Insert RTRN_TRK_NTFY_WRK
                RTRN_TRK_NTFY_WRKTMsg rtrnTrkNtfyWrk = new RTRN_TRK_NTFY_WRKTMsg();
                EZDMsg.copy(rwsDtl, null, rtrnTrkNtfyWrk, null);
                BigDecimal rtrnTrkNtfyWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RTRN_TRK_NTFY_WRK_SQ);
                ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrk.rtrnTrkNtfyWrkPk, rtrnTrkNtfyWrkPk);
                ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrk.updUsrId, rwsDtl.ezUpUserID);
                ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrk.updTs, rwsDtl.ezUpTime);
                EZDTBLAccessor.insert(rtrnTrkNtfyWrk);
                returnCode = rtrnTrkNtfyWrk.getReturnCode();
                if (!RTNCD_NORMAL.equals(returnCode)) {
                    cMsg.setMessageInfo(NLBM1295E, new String[]{new RTRN_TRK_NTFY_WRKTMsg().getTableName()});
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * inputCheckSubmit
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean inputCheckSubmit(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg, String  glblCmpyCd) {

        boolean hasErr = false;
        int firstErrIdx = -1;
        boolean changedVal = false;
        boolean selectVal = false;

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String loginPsnId = getContextUserInfo().getUserId();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            boolean hasErrLine = false;
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

            if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())) {
                continue;
            }
            selectVal = true;
            changedVal = changedValueLine(sMsgALine);

            if (!changedVal) {
                // Skip
                continue;
            }

            // Input check in Apply Info
            if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {
                if (slsDt.compareTo(sMsgALine.schdPickUpDt_A1.getValue()) > 0) {
                    // Past Date
                    sMsgALine.schdPickUpDt_A1.setErrorInfo(1, NLBM1231E, new String[]{"Scheduled Date"});
                    hasErrLine = true;
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(sMsgALine.schdCoordPsnCd_A1)) {

                SCHD_COORD_ASGTMsg schdCoordAsg = new SCHD_COORD_ASGTMsg();
                schdCoordAsg.setSQLID("003");
                schdCoordAsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                schdCoordAsg.setConditionValue("schdCoordPsnCd01", sMsgALine.schdCoordPsnCd_A1.getValue());
                SCHD_COORD_ASGTMsgArray schdCoordAsgList = (SCHD_COORD_ASGTMsgArray) EZDTBLAccessor.findByCondition(schdCoordAsg);
                if (schdCoordAsgList.length() == 0) {
                    // Not found
                    sMsgALine.schdCoordPsnCd_A1.setErrorInfo(1, NLZM2278E, new String[]{"Coordinator"});
                    hasErrLine = true;
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(sMsgALine.schdCoordPsnCd_A1)) {

                if (!NLBL3120CommonLogic.isAssignCoordPsn(
                        glblCmpyCd
                        , sMsgALine.rtlWhCd_A1.getValue()
                        , loginPsnId
                        , sMsgALine.schdCoordPsnCd_A1.getValue())) {
                    sMsgALine.schdCoordPsnCd_A1.setErrorInfo(1, NLBM1328E);
                    hasErrLine = true;
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                }
            }

            // Relation check for Coordinator and Warehouse, State.
            if (ZYPCommonFunc.hasValue(sMsgALine.schdCoordPsnCd_A1)
                    && ZYPCommonFunc.hasValue(sMsgALine.rtlWhCd_A1)
                    && ZYPCommonFunc.hasValue(sMsgALine.fromLocStCd_A1)) {

                //QC#18993 MOD START
                S21SsmEZDResult res = NLBL3120Query.getInstance().chkCoord(glblCmpyCd, sMsgALine, slsDt);
                if (BigDecimal.ZERO.compareTo((BigDecimal) res.getResultObject()) == 0) {
                    sMsgALine.schdCoordPsnCd_A1.setErrorInfo(1, NLBM1308E, new String[]{"Coordinator", "Warehouse", "State"});
                    sMsgALine.rtlWhCd_A1.setErrorInfo(1, NLBM1308E, new String[]{"Coordinator", "Warehouse", "State"});
                    sMsgALine.fromLocStCd_A1.setErrorInfo(1, NLBM1308E, new String[]{"Coordinator", "Warehouse", "State"});
                    hasErr = true;
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                }
                //QC#18993 MOD START
            }

            if (ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1)) {
                sMsgALine.locNm_A1.clear();
                String carrNm = NLBL3120Query.getInstance().getCarrNm(sMsgALine.carrCd_A1.getValue());
                if (!ZYPCommonFunc.hasValue(carrNm)) {
                    // Not found
                    sMsgALine.carrCd_A1.setErrorInfo(1, NLZM2278E, new String[]{"Carrier"});
                    hasErrLine = true;
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                } else {
                    sMsgALine.locNm_A1.setValue(carrNm);
                }
            }

            if (ZYPCommonFunc.hasValue(sMsgALine.shpgSvcLvlCd_A1)
                    && ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1)) {

                SHPG_SVC_LVL_CARR_RELNTMsg shpgSvcLvlCarrReln = new SHPG_SVC_LVL_CARR_RELNTMsg();
                shpgSvcLvlCarrReln.setSQLID("001");
                shpgSvcLvlCarrReln.setConditionValue("glblCmpyCd01", glblCmpyCd);
                shpgSvcLvlCarrReln.setConditionValue("shpgSvcLvlCd01", sMsgALine.shpgSvcLvlCd_A1.getValue());
                shpgSvcLvlCarrReln.setConditionValue("carrCd01", sMsgALine.carrCd_A1.getValue());
                SHPG_SVC_LVL_CARR_RELNTMsgArray shpgSvcLvlCarrRelnList = (SHPG_SVC_LVL_CARR_RELNTMsgArray) EZDTBLAccessor.findByCondition(shpgSvcLvlCarrReln);
                if (shpgSvcLvlCarrRelnList.length() == 0) {
                    // Not found
                    sMsgALine.shpgSvcLvlCd_A1.setErrorInfo(1, NLBM1308E
                            , new String[]{"Shipping Service Level", "Carrier"});
                    sMsgALine.carrCd_A1.setErrorInfo(1, NLBM1308E
                            , new String[]{"Shipping Service Level", "Carrier"});
                    hasErrLine = true;
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                }
            }

            // START 2023/08/01 M.Kikushima [QC#61677, ADD]
            if (ZYPCommonFunc.hasValue(sMsgALine.schdCoordStsCd_A1)
                    && SCHD_COORD_STS.PRODUCT_IN_ROUTE_NO_CONFIRMED_DATE.equals(sMsgALine.schdCoordStsCd_A1.getValue())) {
                if (!ZYPCommonFunc.hasValue(sMsgALine.soNum_A1)
                        && ZYPCommonFunc.hasValue(sMsgALine.rwsNum_A1)) {
                    sMsgALine.schdCoordStsCd_A1.setErrorInfo(1, NLBM1381E);
                    hasErrLine = true;
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                }
            }
            
            // END   2023/08/01 M.Kikushima [QC#61677, ADD]

            if(hasErrLine){
                hasErr = true;
            }
        }

        if (!selectVal) {
            /** QC#16280# 12/19/2016 M.Naito Start **/
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                cMsg.A.no(i).xxChkBox_A2.setErrorInfo(1, NLZM2274E, new String[] {"Scheduling Data" });
            }
            /** QC#16280# 12/19/2016 M.Naito Start **/
            return false;
        }

        if (!changedVal) {
            cMsg.setMessageInfo(NLBM1303E, new String[]{"Scheduling Data"});
            return false;
        }
        if (hasErr) {
            int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndexForError(firstErrIdx, cMsg, sMsg);
            int fromNum = nextRecIdx;
            NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
            return false;
        }
        return true;
    }

    /**
     * inputCheckWaringSubmit
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean inputCheckWaringSubmit(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg, String glblCmpyCd, String slsDt) {
        /**
         * QC#15145 Enable Carrier Account# 11/22/2016 T.Tokutomi
         * Start
         **/
        boolean notWarningFlg = true;
        int warningIndex = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

            // QC#19524 ADD START
            if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())) {
                continue;
            }
            // QC#19524 ADD END

            if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.delyOrPickUpRqstFlg_A1.getValue()) && !sMsgALine.carrCd_A1.getValue().equals(sMsgALine.carrCd_AK.getValue())) {
                sMsgALine.carrCd_A1.setErrorInfo(2, NLBM1327W);
                notWarningFlg = false;

                if (warningIndex == -1) {
                    warningIndex = i;
                }
            }

            if (!sMsgALine.carrCd_A1.getValue().equals(sMsgALine.carrCd_OD.getValue())) {
                sMsgALine.carrCd_A1.setErrorInfo(2, NLBM1301W, //
                        new String[] {"Carrier", "assigned by order" });
                notWarningFlg = false;
                if (warningIndex == -1) {
                    warningIndex = i;
                }
            }

            // Master Check
            if (ZYPCommonFunc.hasValue(sMsgALine.carrAcctNum_A1) && ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1)) {

                S21SsmEZDResult result = NLBL3120Query.getInstance().getAcctCarrCnt(//
                        glblCmpyCd//
                        , slsDt//
                        , sMsgALine.shipToCustAcctCd_A1.getValue()//
                        , sMsgALine.carrCd_A1.getValue()//
                        , sMsgALine.carrAcctNum_A1.getValue());

                if (result.isCodeNormal()) {
                    Integer count = (Integer) result.getResultObject();
                    if (count == null || count.intValue() == 0) {
                        sMsgALine.carrCd_A1.setErrorInfo(2, NLBM1328W, new String[] {"Carrier Code", "Carrier Account Number" });
                        notWarningFlg = false;
                        if (warningIndex == -1) {
                            warningIndex = i;
                        }
                    }
                } else {
                    sMsgALine.carrCd_A1.setErrorInfo(2, NLBM1328W, new String[] {"Carrier Code", "Carrier Account Number" });
                    notWarningFlg = false;
                    if (warningIndex == -1) {
                        warningIndex = i;
                    }
                }
            }

        }

        if (!notWarningFlg) {
            int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndexForError(warningIndex, cMsg, sMsg);
            int fromNum = nextRecIdx;
            NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
        }

        return notWarningFlg;
        /** QC#15145 Enable Carrier Account# 11/22/2016 T.Tokutomi End **/
    }

    /**
     * inputCheckReleaseSo
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean inputCheckReleaseSo(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg, String  glblCmpyCd) {

        boolean chkBoxOn = false;
        boolean hasErrLine = false;
        int firstErrIdx = -1;
        boolean hasErr = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxPgFlg_A1.getValue())) {
                continue;
            }

            if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())) {
                continue;
            }

            chkBoxOn = true;
            if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.schdTrxOpenFlg_A1.getValue())) {
                // Not Open Status
                sMsgALine.xxChkBox_A2.setErrorInfo(1, NLBM1307E);
                hasErrLine = true;
            }
            if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.wmsDropRqstFlg_A1.getValue())) {
                // Already wmsDropRqstFlg = Y
                sMsgALine.xxChkBox_A2.setErrorInfo(1, NLBM1310E);
                hasErrLine = true;
            }
            if (hasErrLine) {
                hasErr = true;
                if (firstErrIdx == -1) {
                    firstErrIdx = i;
                }
            }
        }

        if (hasErr) {
            int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndexForError(firstErrIdx, cMsg, sMsg);
            int fromNum = nextRecIdx;
            NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
            return false;
        }
        if (!chkBoxOn) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                NLBL3120_ACMsg cMsgALine = cMsg.A.no(i);
                cMsgALine.xxChkBox_A2.setErrorInfo(1, NLBM0002E);
            }
            return false;
        }
        return true;
    }

    /**
     * changedValueLine
     * @param sMsgALine NLBL3120_ASMsg
     * @return boolean
     */
    private boolean changedValueLine(NLBL3120_ASMsg sMsgALine) {

        boolean changedVal = false;
        // Check changed values
        changedVal = changedValue(sMsgALine.schdCoordPsnCd_A1.getValue(), sMsgALine.schdCoordPsnCd_AK.getValue());
        changedVal = changedValue(sMsgALine.shpgSvcLvlCd_A1.getValue(), sMsgALine.shpgSvcLvlCd_AK.getValue()) || changedVal;
        changedVal = changedValue(sMsgALine.carrCd_A1.getValue(), sMsgALine.carrCd_AK.getValue()) || changedVal;
        changedVal = changedValue(sMsgALine.schdPickUpDt_A1.getValue(), sMsgALine.schdPickUpDt_AK.getValue()) || changedVal;
        changedVal = changedValue(sMsgALine.carrAcctNum_A1.getValue(), sMsgALine.carrAcctNum_AK.getValue()) || changedVal;
        changedVal = changedValue(sMsgALine.schdCoordStsCd_A1.getValue(), sMsgALine.schdCoordStsCd_AK.getValue()) || changedVal;
        return changedVal;
    }

    /**
     * changedValue
     * @param newVal String
     * @param oldVal String
     * @return boolean
     */
    private boolean changedValue(String newVal, String oldVal) {
        if (ZYPCommonFunc.hasValue(newVal)) {
            if (!newVal.equals(oldVal)) {
                // Changed
                return true;
            }
        } else if (ZYPCommonFunc.hasValue(oldVal)) {
            // Changed
            return true;
        }
        return false;
    }

//QC#19137 DEL START
//    /**
//     * getSchdCoordSts
//     * @param sMsgALine NLBL3120_ASMsg
//     * @return String
//     */
//    private String getSchdCoordStsSo(NLBL3120_ASMsg sMsgALine) {
//        String schdCoordSts = null;
//
//        if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_AK)) {
//
//            if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.tempSchdFlg_AK.getValue())) {
//
//                if (!sMsgALine.schdPickUpDt_AK.getValue().equals((sMsgALine.schdPickUpDt_A1.getValue()))) {
//                    schdCoordSts = SCHD_COORD_STS.AWAITING_CUSTOMER_COMMITMENT;
//                } else {
//                    schdCoordSts = sMsgALine.schdCoordStsCd_AK.getValue();
//                }
//
//            } else {
//
//                if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {
//
//                    if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {
//                        schdCoordSts = SCHD_COORD_STS.RE_SCHEDULED;
//                    } else {
//                        schdCoordSts = SCHD_COORD_STS.AWAITING_SCHEDULING;
//                    }
//
//                } else {
//                    schdCoordSts = sMsgALine.schdCoordStsCd_AK.getValue();
//                }
//            }
//
//        } else {
//
//            if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.tempSchdFlg_AK.getValue())) {
//
//                if (!sMsgALine.schdPickUpDt_AK.getValue().equals((sMsgALine.schdPickUpDt_A1.getValue()))) {
//
//                    if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {
//                        schdCoordSts = SCHD_COORD_STS.AWAITING_CUSTOMER_COMMITMENT;
//                    } else {
//                        schdCoordSts = sMsgALine.schdCoordStsCd_AK.getValue();
//                    }
//
//                } else {
//                    schdCoordSts = sMsgALine.schdCoordStsCd_AK.getValue();
//                }
//
//            } else {
//
//                if (!sMsgALine.schdPickUpDt_AK.getValue().equals((sMsgALine.schdPickUpDt_A1.getValue()))) {
//
//                    if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {
//                        schdCoordSts = SCHD_COORD_STS.SCHEDULED;
//                    } else {
//                        schdCoordSts = sMsgALine.schdCoordStsCd_AK.getValue();
//                    }
//
//                } else {
//                    schdCoordSts = sMsgALine.schdCoordStsCd_AK.getValue();
//                }
//
//            }
//        }
//
//        return schdCoordSts;
//    }
//
//    private String getSchdCoordStsRws(NLBL3120_ASMsg sMsgALine) {
//        String schdCoordSts = null;
//
//        if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_AK)) {
//
//            if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.tempSchdFlg_AK.getValue())) {
//                schdCoordSts = SCHD_COORD_STS.RE_SCHEDULED;
//
//            } else {
//                schdCoordSts = SCHD_COORD_STS.AWAITING_SCHEDULING;
//            }
//
//        } else {
//
//            if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.tempSchdFlg_AK.getValue())) {
//                schdCoordSts = SCHD_COORD_STS.SCHEDULED;
//
//            } else {
//                schdCoordSts = SCHD_COORD_STS.AWAITING_SCHEDULING;
//            }
//        }
//
//        return schdCoordSts;
//    }
//QC#19137 DEL END

    private String getRtrnTrkSts(NLBL3120_ASMsg sMsgALine, String glblCmpyCd, String slsDt, String rtrnRsnCd, String mdseCd) {

        if (!ZYPCommonFunc.hasValue(sMsgALine.rtlWhCd_A1)) {
            sMsgALine.xxChkBox_A2.setErrorInfo(1, NLBM0052E, new String[]{"Warehouse"});
            return null;
        }

        NWZC180001PMsg pMsg = new NWZC180001PMsg();
        pMsg.glblCmpyCd.setValue(glblCmpyCd);
        pMsg.slsDt.setValue(slsDt);
        pMsg.xxModeCd.setValue(NWZC180001Constant.PROC_MODE_INBD);
        pMsg.dsOrdCatgCd.setValue(sMsgALine.dsOrdCatgCd_AP.getValue());
        pMsg.dsOrdTpCd.setValue(sMsgALine.dsOrdTpCd_AP.getValue());
        pMsg.postCd.setValue(sMsgALine.addShipToPostCd_AP.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, rtrnRsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);

        NWZC180001 dflWh = new NWZC180001();
        dflWh.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                NWZC180001_xxMsgIdListPMsg msgPMsg = pMsg.xxMsgIdList.no(i);
                String msgId = msgPMsg.xxMsgId.getValue();

                if (!msgId.endsWith("E")) {
                    continue;
                }

                ArrayList<String> msgPrmList = new ArrayList<String>(5);
                for (int j = 4; j >= 0; j--) {

                    if (!ZYPCommonFunc.hasValue(msgPMsg.getValueString("xxMsgPrmTxt_" + j, -1))) {
                        continue;
                    }
                    for (int k = 0; k <= j; k++) {
                        msgPrmList.add(msgPMsg.getValueString("xxMsgPrmTxt_" + k, -1));
                    }
                    break;
                }

                sMsgALine.xxChkBox_A2.setErrorInfo(1, msgId, msgPrmList.toArray(new String[0]));
            }
            return null;
        }

        if (sMsgALine.rtlWhCd_A1.getValue().equals(pMsg.rtlWhCd.getValue())) {
            return RTRN_TRK_STS.P_OR_U_SCHEDULED_BY_LOCAL_WAREHOUSE;
        }

        return RTRN_TRK_STS.PICKUP_SCHEDULED_BY_RMA_GROUP;
    }

    /**
     * doProcess_NLBL3120Scrn00_SaveSearch
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_SaveSearch(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            cMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Search Option Name") });
            return;
        }
        if (NLBL3120CommonLogic.isExistSaveSearchName(cMsg)) {
            cMsg.srchOptNm_S.setErrorInfo(1, NLZM2273E //
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID, "Save") //
                    , converter.convLabel2i18nLabel(SCRN_ID, "Search Option Name") });
            return;
        }

        NLBL3120CommonLogic.callNszc0330forSaveSearch(//
                cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * doProcess_NLBL3120Scrn00_DeleteSearch
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_DeleteSearch(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            cMsg.srchOptPk_S.setErrorInfo(1, NLZM2274E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Saved Search Options") });
            return;
        }

        NLBL3120CommonLogic.callNszc0330forDeleteSearch(//
                cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

}