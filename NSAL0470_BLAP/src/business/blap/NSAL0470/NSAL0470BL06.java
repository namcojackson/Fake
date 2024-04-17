/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0470;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import static business.blap.NSAL0470.constant.NSAL0470Constant.*;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsgArray;
import business.db.SVC_CONTR_BASE_BLLGTMsg;
import business.db.SVC_CONTR_BASE_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsgArray;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC047099PMsg;
import business.parts.NSZC077001PMsg;
import business.parts.NWZC172001PMsg;
import business.parts.NWZC172002PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.api.NSZ.NSZC102001.NSZC102001TokenObject;
import com.canon.cusa.s21.api.NSZ.NSZC102001.NSZC102001TokenObjectLine;
import com.canon.cusa.s21.api.NWZ.NWZC172001.NWZC172001;
import com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcDate;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetNotArvMachCntForFlt;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.impl.S21NwfTokenImpl;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Complete Contract
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Yamada        Create          N/A
 * 2015/11/30   Hitachi         A.Kohinata      Update          QC1197
 * 2015/12/04   Hitachi         A.Kohinata      Update          QC1153
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC3050
 * 2016/02/16   Hitachi         T.Aoyagi        Update          QC3840
 * 2016/02/19   Hitachi         K.Kasai         Update          QC3689
 * 2016/04/20   Hitachi         K.Kishimoto     Update          QC5130
 * 2016/04/21   Hitachi         A.Kohinata      Update          QC1088
 * 2016/05/19   Hitachi         T.Kanasaka      Update          QC#8138
 * 2016/06/27   Hitachi         O.Okuma         Update          QC#10746
 * 2017/07/24   Hitachi         K.Kasai         Update          QC#18882
 * 2017/08/15   Hitachi         K.Kim           Update          QC#20525
 * 2017/10/06   Hitachi         K.Kojima        Update          QC#21547
 * 2017/10/24   Hitachi         U.Kim           Update          QC#21865
 * 2018/05/10   Hitachi         U.Kim           Update          QC#25482
 * 2018/05/11   CITS            T.Wada          Update          QC#24989
 * 2018/05/24   Hitachi         T.Tomita        Update          QC#24989
 * 2018/05/29   Hitachi         T.Tomita        Update          QC#24989
 * 2018/07/04   Hitachi         K.Kitachi       Update          QC#26891
 * 2018/08/29   Hitachi         K.Kojima        Update          QC#28012
 * 2019/01/25   Hitachi         K.Kitachi       Update          QC#30080
 * 2019/06/13   Hitachi         K.Kitachi       Update          QC#50811
 * 2019/07/24   Hitachi         K.Kishimoto     Update          QC#51788
 * 2019/07/25   Hitachi         K.Kitachi       Update          QC#52070
 * 2019/08/22   Hitachi         A.Kohinata      Update          QC#52413
 * 2019/09/02   Hitachi         K.Kitachi       Update          QC#52695
 * 2020/03/09   CITS            T.Wada          Update          QC#55830
 * 2020/03/24   Hitachi         A.Kohinata      Update          QC#54318
 * 2020/09/03   CITS            T.Wada          Update          QC#57644
 * 2022/08/23   CITS            E.Sanchez       Update          QC#60455
 * 2022/09/27   CITS            E.Sanchez       Update          QC#60455
 * 2022/10/21   Hitachi         D.Yoshida       Update          QC#60068
 *</pre>
 */
public class NSAL0470BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL0470CMsg bizMsg = (NSAL0470CMsg) cMsg;
            NSAL0470SMsg shareMsg = (NSAL0470SMsg) sMsg;

            // START 2016/02/15 T.Aoyagi [QC3050, MOD]
            if ("NSAL0470Scrn00_ActivateContract".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_ActivateContract(bizMsg, shareMsg);
            // END 2016/02/15 T.Aoyagi [QC3050, MOD]
            } else if ("NSAL0470Scrn00_Override_Outcome".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_Override_Outcome(bizMsg, shareMsg);
            // START 2016/04/21 [QC1088, ADD]
            } else if ("NSAL0470Scrn00_SendTo_SuperVisor".equals(screenAplID)) {
                doProcess_NSAL0470Scrn00_SendTo_SuperVisor(bizMsg, shareMsg);
            // END 2016/04/21 [QC1088, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    // START 2016/02/15 T.Aoyagi [QC3050, MOD]
    private void doProcess_NSAL0470Scrn00_ActivateContract(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {
    // END 2016/02/15 T.Aoyagi [QC3050, MOD]

        BigDecimal dsContrPk = sMsg.B.no(0).dsContrPk_B.getValue();
        // START 2016/02/16 T.Aoyagi [QC3840, ADD]
        String dsContrCtrlStsCd = NSAL0470Query.getInstance().getDsContrStsCd(dsContrPk);
        setValue(cMsg.dsContrCtrlStsCd, dsContrCtrlStsCd);
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode(), BUSINESS_ID));
        // END 2016/02/16 T.Aoyagi [QC3840, ADD]
        //START 2017/07/24 K.Kasai [QC#18882,ADD]
        // check all machine arrived in the fleet contract
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue()) && !checkAllMachArv(cMsg, dsContrPk)) {
            setValue(cMsg.xxWrnSkipFlg, FLG_ON_Y);
            return;
        }
        //END 2017/07/24 K.Kasai [QC#18882,ADD]

        BigDecimal prntDsContrTrkPk = updDsContr(cMsg, sMsg.B.no(0));

        if ("E".equals(cMsg.getMessageKind())) {
            return;
        }
        List<DS_CONTR_DTLTMsg> dsContrDtlList = updDsContrDtl(cMsg, dsContrPk, prntDsContrTrkPk);
        if (dsContrDtlList == null || dsContrDtlList.isEmpty()) {
            return;
        }
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList = updDsContrBllgMtr(cMsg, dsContrDtlList, prntDsContrTrkPk);
        // START 2015/11/30 [QC1153,MOD]
        if (dsContrBllgMtrList == null) {
        // END 2015/11/30 [QC1153,MOD]
            return;
        }
        if (!updDsContrPrcEffBase(cMsg, dsContrDtlList, prntDsContrTrkPk)) {
            return;
        }
        if (!updDsContrPrcEffUsg(cMsg, dsContrBllgMtrList, dsContrPk, prntDsContrTrkPk)) {
            return;
        }
        // START 2019/01/25 K.Kitachi [QC#30080, ADD]
        // START 2019/07/24 [QC#51788, DEL]
//        if (!updDsContrRnwEscl(cMsg, dsContrPk)) {
//            return;
//        }
        // END   2019/07/24 [QC#51788, DEL]
        // START 2019/07/25 K.Kitachi [QC#52070, ADD]
        // del start 2020/03/24 QC#54318
//        if (!updDsContrRnwEscl(cMsg, dsContrPk)) {
//            return;
//        }
        // del end 2020/03/24 QC#54318
        // END 2019/07/25 K.Kitachi [QC#52070, ADD]
        // END 2019/01/25 K.Kitachi [QC#30080, ADD]
        if (!createOverrideMemo(cMsg, sMsg, dsContrPk)) {
            return;
        }
        if (!removeDsContrVldRslt(cMsg, dsContrPk)) {
            return;
        }
        // START 2017/10/24 U.Kim [QC#21865, ADD]
        if (!createOrdSch(cMsg, dsContrPk)) {
            return;
        }
        // END 2017/10/24 U.Kim [QC#21865, ADD]
        // START 2019/06/13 K.Kitachi [QC#50811, ADD]
        // QC#55830 Del Start
//        if(!callBllgSchdApiRecovMode(cMsg, dsContrPk)){
//            return;
//        }
        // QC#55830 Del End
        // END 2019/06/13 K.Kitachi [QC#50811, ADD]
        cMsg.setMessageInfo(NZZM0002I);
        //START 2017/07/24 K.Kasai [QC#18882,ADD]
        setValue(cMsg.xxWrnSkipFlg, FLG_OFF_N);
        //END 2017/07/24 K.Kasai [QC#18882,ADD]
    }

    private void doProcess_NSAL0470Scrn00_Override_Outcome(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getDoNotOverrideVld(cMsg);
        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<String> errVldList = (List<String>) ssmResult.getResultObject();

            cMsg.setMessageInfo(NSAM0393E, new String[] {errVldList.get(0)});
            return;
        }

        // START 2016/02/15 T.Aoyagi [QC3050, MOD]
        doProcess_NSAL0470Scrn00_ActivateContract(cMsg, sMsg);
        // END 2016/02/15 T.Aoyagi [QC3050, MOD]
    }

    // START 2016/04/21 [QC1088, ADD]
    private void doProcess_NSAL0470Scrn00_SendTo_SuperVisor(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getDoNotOverrideVld(cMsg);
        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<String> errVldList = (List<String>) ssmResult.getResultObject();
            cMsg.setMessageInfo(NSAM0393E, new String[] {errVldList.get(0) });
            return;
        }

        BigDecimal contrRepCnt = NSAL0470Query.getInstance().getContrRepCnt(cMsg);
        if (BigDecimal.ZERO.compareTo(contrRepCnt) == 0) {
            cMsg.setMessageInfo(NSAM0063E, new String[] {"Target User", "Contract Rep" });
            return;
        }

        BigDecimal dsContrPk = sMsg.B.no(0).dsContrPk_B.getValue();

        BigDecimal prntDsContrTrkPk = updDsContrForSendSupv(cMsg, sMsg.B.no(0));
        if (cMsg.getMessageInfo() != null) {
            return;
        }
        List<DS_CONTR_DTLTMsg> dsContrDtlList = updDsContrDtlForSendSupv(cMsg, dsContrPk, prntDsContrTrkPk);
        if (dsContrDtlList == null || dsContrDtlList.isEmpty()) {
            return;
        }
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList = updDsContrBllgMtrForSendSupv(cMsg, dsContrDtlList, prntDsContrTrkPk);
        if (dsContrBllgMtrList == null || dsContrBllgMtrList.isEmpty()) {
            return;
        }
        if (!updDsContrPrcEffBaseForSendSupv(cMsg, dsContrDtlList, prntDsContrTrkPk)) {
            return;
        }
        if (!updDsContrPrcEffUsgForSendSupv(cMsg, dsContrBllgMtrList, dsContrPk, prntDsContrTrkPk)) {
            return;
        }

        ssmResult = NSAL0470Query.getInstance().getDsContrForWf(cMsg);
        if (!ssmResult.isCodeNormal()) {
            return;
        }
        Map<String, Object> contrMap = (Map<String, Object>) ssmResult.getResultObject();
        startWf(cMsg, contrMap);

        cMsg.setMessageInfo(NZZM0002I);
    }
    // END 2016/04/21 [QC1088, ADD]

    private BigDecimal updDsContr(NSAL0470CMsg cMsg, NSAL0470_BSMsg bSMsg) {
        BigDecimal dsContrPk = bSMsg.dsContrPk_B.getValue();
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inMsg.dsContrPk, dsContrPk);

        inMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        if (inMsg == null) {
            cMsg.setMessageInfo(NZZM0003E);
            return null;
        }

        String findEzUpTime = bSMsg.ezUpTime_B.getValue();
        String findEzUpTimeZone = bSMsg.ezUpTimeZone_B.getValue();
        String currentEzUpTime = inMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = inMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NZZM0003E);
            return null;
        }

        if (FLG_ON_Y.equals(inMsg.qltyAsrnHldFlg.getValue())) {
            setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
            // QC#57644 Add Start
            if (DS_CONTR_STS.DRAFT.equals(inMsg.dsContrStsCd.getValue()) || DS_CONTR_STS.ENTERED.equals(inMsg.dsContrStsCd.getValue())) {
                if (cMsg.slsDt.getValue().compareTo(inMsg.contrVrsnEffFromDt.getValue()) >= 0) {
                    setValue(inMsg.dsContrStsCd, DS_CONTR_STS.EFFECTIVE);
                } else {
                    setValue(inMsg.dsContrStsCd, DS_CONTR_STS.APPROVED);
                }
            }
            // QC#57644 Add End
        } else {
            //Mod Start 04/20/2016 <QC#5130>
            // START 2016/05/19 T.Kanasaka [QC#8138, MOD]
            if (cMsg.slsDt.getValue().compareTo(inMsg.contrVrsnEffFromDt.getValue()) >= 0) {
                setValue(inMsg.dsContrStsCd, DS_CONTR_STS.EFFECTIVE);
            } else {
                setValue(inMsg.dsContrStsCd, DS_CONTR_STS.APPROVED);
            }
            // END 2016/05/19 T.Kanasaka [QC#8138, MOD]
            //Mod End   04/20/2016 <QC#5130>
        }

        S21FastTBLAccessor.update(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {inMsg.getTableName()});
            return null;
        }

        // call contract tracking api
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
        setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
        setValue(pMsg.dsContrPk, dsContrPk);
        setValue(pMsg.stsMemoRsnCd, DS_CONTR_TRK_RSN.COMPLETE_CONTRACT);
        // START 2015/11/30 [QC1197,ADD]
        setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
        // END 2015/11/30 [QC1197,ADD]

        new NSZC077001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return null;
        }

        return pMsg.prntDsContrTrkPk.getValue();
    }

    private List<DS_CONTR_DTLTMsg> updDsContrDtl(NSAL0470CMsg cMsg, BigDecimal dsContrPk, BigDecimal prntDsContrTrkPk) {

        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getUpdDsContrDtl(cMsg, dsContrPk);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTRTMsg().getTableName()});
            return null;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> dsContrDtlList = (List<Map<String, Object>>) ssmResult.getResultObject();

        List<DS_CONTR_DTLTMsg> updList = new ArrayList<DS_CONTR_DTLTMsg>(dsContrDtlList.size());
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>(dsContrDtlList.size());

        boolean isMacheneInstalled = false;            // QC#24989 Add

        for (Map<String, Object> dsContrDtl : dsContrDtlList) {

            // QC#24989 Add Start
            String sortNum = (String)dsContrDtl.get("SORT_NUM");
            // Mod Start 2018/05/24 QC#24989
            boolean machInstlSts = isMachInstl((String) dsContrDtl.get("SVC_MACH_MSTR_STS_CD"));
            if (sortNum.equals("1") && machInstlSts) {
                isMacheneInstalled = true;
            } else if (sortNum.equals("2")) {
                // START 2022/09/27 E.Sanchez [QC#60455, MOD]
                String dsContrDtlStsCd  = (String) dsContrDtl.get("DS_CONTR_DTL_STS_CD");
                // if (!isMacheneInstalled) {
                // START 2022/10/21 [QC#60068, MOD]
//                if (!isMacheneInstalled && !DS_CONTR_DTL_STS.RENEWAL_HOLD.equals(dsContrDtlStsCd)) {
                // END 2022/09/27 E.Sanchez [QC#60455, MOD]
                if (!isMacheneInstalled && ((!DS_CONTR_DTL_STS.RENEWAL_HOLD.equals(dsContrDtlStsCd)) && (!DS_CONTR_DTL_STS.TERMINATED.equals(dsContrDtlStsCd)))) {
                // END   2022/10/21 [QC#60068, MOD]
                    continue;
                }
            }
            // Mod End 2018/05/24 QC#24989
            // QC#24989 Add End

            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
            setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(inMsg.dsContrDtlPk, (BigDecimal) dsContrDtl.get("DS_CONTR_DTL_PK"));

            inMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
            if (inMsg == null) {
                cMsg.setMessageInfo(NZZM0003E);
                return null;
            }
            // Mod Start 2018/05/24 QC#24989
            if (DS_CONTR_CTRL_STS.QA_HOLD.equals((String) dsContrDtl.get("DS_CONTR_CTRL_STS_CD")) && !DS_CONTR_DTL_STS.SUBMITED.equals(inMsg.dsContrDtlStsCd.getValue())) {
//                setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
            // Mod End 2018/05/24 QC#24989
                // START 2017/10/06 K.Kojima [QC#21547,ADD]
                if (DS_CONTR_DTL_STS.RENEWAL_HOLD.equals(inMsg.dsContrDtlStsCd.getValue())) {
                    setValue(inMsg.dsContrDtlStsCd, getDsContrDtlStsCd(cMsg, inMsg.contrEffFromDt.getValue()));
                }
                // START 2018/05/10 U.Kim [QC#25482, ADD]
                if (!removeStagingInfo(cMsg, dsContrDtlList)) {
                    return null;
                }
                // END 2018/05/10 U.Kim [QC#25482, ADD]
                // END 2017/10/06 K.Kojima [QC#21547,ADD]
            // Mod Start 2018/05/29 QC#24989
            } else if (machInstlSts || DS_CONTR_DTL_TP.FLEET.equals(inMsg.dsContrDtlTpCd.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(inMsg.dsContrDtlTpCd.getValue())) {
            // Mod End 2018/05/29 QC#24989
                // START 2016/02/16 T.Aoyagi [QC3840, MOD]
//                setValue(inMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.SUBMITED);
                // Mod Start 04/20/2016 <QC#5130>
                // START 2016/05/19 T.Kanasaka [QC#8138, MOD]
//                String thruDt = inMsg.contrEffThruDt.getValue();
//                if (ZYPCommonFunc.hasValue(inMsg.contrCloDt)) {
//                    thruDt = inMsg.contrCloDt.getValue();
//                }
                setValue(inMsg.dsContrDtlStsCd, getDsContrDtlStsCd(cMsg, inMsg.contrEffFromDt.getValue()));
                // END 2016/05/19 T.Kanasaka [QC#8138, MOD]
                // Mod End   04/20/2016 <QC#5130>
                // END 2016/02/16 T.Aoyagi [QC3840, MOD]
            }
            // Mod Start 2018/05/24 QC#24989
            setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
            // Mod End 2018/05/24 QC#24989
            updList.add(inMsg);

            NSZC077001PMsg pMsg = new NSZC077001PMsg();
            setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
            setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
            setValue(pMsg.dsContrPk, dsContrPk);
            setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
            setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
            // START 2015/11/30 [QC1197,ADD]
            setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
            // END 2015/11/30 [QC1197,ADD]
            trkList.add(pMsg);
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (updCnt != updList.size()) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTRTMsg().getTableName()});
                return null;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);

        for (NSZC077001PMsg pMsg : trkList) {
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                return null;
            }
        }

        return updList;
    }

    private List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtr(NSAL0470CMsg cMsg, List<DS_CONTR_DTLTMsg> dsContrDtlList, BigDecimal prntDsContrTrkPk) {

        List<DS_CONTR_BLLG_MTRTMsg> updList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_DTLTMsg dsContrDtl : dsContrDtlList) {
            S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getUpdDsContrBllgMtr(cMsg,
                    dsContrDtl.dsContrDtlPk.getValue());
            if (ssmResult.isCodeNotFound()) {
                continue;
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> dsContrBllgMtrList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> dsContrBllgMtr : dsContrBllgMtrList) {
                DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
                setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(inMsg.dsContrBllgMtrPk, (BigDecimal) dsContrBllgMtr.get("DS_CONTR_BLLG_MTR_PK"));

                inMsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                if (inMsg == null) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return null;
                }
                if (DS_CONTR_CTRL_STS.QA_HOLD.equals((String) dsContrBllgMtr.get("DS_CONTR_CTRL_STS_CD"))) {
                    setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
                    // START 2022/08/23 E.Sanchez [QC#60455, ADD]
                    if (DS_CONTR_DTL_STS.RENEWAL_HOLD.equals(inMsg.dsContrBllgMtrStsCd.getValue())) {
                        setValue(inMsg.dsContrBllgMtrStsCd, getDsContrDtlStsCd(cMsg, dsContrDtl.contrEffFromDt.getValue()));
                    }
                    // END 2022/08/23 E.Sanchez [QC#60455, ADD]
                } else {
                    // START 2016/02/16 T.Aoyagi [QC3840, MOD]
//                    setValue(inMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SUBMITED);
                    // Mod Start 04/20/2016 <QC#5130>
                    // START 2016/05/19 T.Kanasaka [QC#8138, MOD]
//                    String thruDt = dsContrDtl.contrEffThruDt.getValue();
//                    if (ZYPCommonFunc.hasValue(dsContrDtl.contrCloDt)) {
//                        thruDt = dsContrDtl.contrCloDt.getValue();
//                    }
                    setValue(inMsg.dsContrBllgMtrStsCd, getDsContrDtlStsCd(cMsg, dsContrDtl.contrEffFromDt.getValue()));
                    // END 2016/05/19 T.Kanasaka [QC#8138, MOD]
                    // Mod End  04/20/2016 <QC#5130>
                    // END 2016/02/16 T.Aoyagi [QC3840, MOD]
                }
                updList.add(inMsg);

                NSZC077001PMsg pMsg = new NSZC077001PMsg();
                setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
                setValue(pMsg.dsContrPk, dsContrDtl.dsContrPk);
                setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(pMsg.dsContrBllgMtrPk, inMsg.dsContrBllgMtrPk);
                setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                // START 2015/11/30 [QC1197,ADD]
                setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
                // END 2015/11/30 [QC1197,ADD]
                trkList.add(pMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_BLLG_MTRTMsg[0]));
            if (updCnt != updList.size()) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTR_BLLG_MTRTMsg().getTableName()});
                return null;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);

        for (NSZC077001PMsg pMsg : trkList) {
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                return null;
            }
        }

        return updList;
    }

    private boolean updDsContrPrcEffBase(NSAL0470CMsg cMsg, List<DS_CONTR_DTLTMsg> dsContrDtlList, BigDecimal prntDsContrTrkPk) {

        List<DS_CONTR_PRC_EFFTMsg> updList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_DTLTMsg dsContrDtl : dsContrDtlList) {
            S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getUpdDsContrPrcEffBase(cMsg,
                    dsContrDtl.dsContrDtlPk.getValue());
            if (ssmResult.isCodeNotFound()) {
                continue;
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> dsContrPrcEffList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
                setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(inMsg.dsContrPrcEffPk, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));

                inMsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                if (inMsg == null) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                if (DS_CONTR_CTRL_STS.QA_HOLD.equals((String) dsContrPrcEff.get("DS_CONTR_CTRL_STS_CD"))) {
                    setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
                    // START 2017/10/06 K.Kojima [QC#21547,ADD]
                    if (DS_CONTR_DTL_STS.ACTIVE.equals(inMsg.dsContrPrcEffStsCd.getValue()) || DS_CONTR_DTL_STS.SIGNED.equals(inMsg.dsContrPrcEffStsCd.getValue())) {
                        setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(cMsg, (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT")));
                    } else if (DS_CONTR_DTL_STS.RENEWAL_HOLD.equals(inMsg.dsContrPrcEffStsCd.getValue())) {
                        setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(cMsg, (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT")));
                    }
                } else {
                    setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(cMsg, (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT")));
                    // END 2017/10/06 K.Kojima [QC#21547,ADD]
                }
                // START 2017/08/15 K.Kim [QC#20525, MOD]
//              } else {
                    // START 2016/02/16 T.Aoyagi [QC3840, MOD]
//                    setValue(inMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SUBMITED);
                    // Mod Start 04/20/2016 <QC#5130>
                    // START 2016/05/19 T.Kanasaka [QC#8138, MOD]
//                    String thruDt = dsContrDtl.contrEffThruDt.getValue();
//                    if (ZYPCommonFunc.hasValue(dsContrDtl.contrCloDt)) {
//                        thruDt = dsContrDtl.contrCloDt.getValue();
//                    }
//                  setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(cMsg, dsContrDtl.contrEffFromDt.getValue()));
                    // Mod Start 04/20/2016 <QC#5130>
                    // END 2016/02/16 T.Aoyagi [QC3840, MOD]
//              }
                // START 2017/10/06 K.Kojima [QC#20523,DEL]
                // setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(cMsg, (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT")));
                // END 2017/10/06 K.Kojima [QC#20523,DEL]
                // START 2017/08/15 K.Kim [QC#20525, MOD]
                updList.add(inMsg);

                NSZC077001PMsg pMsg = new NSZC077001PMsg();
                setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
                setValue(pMsg.dsContrPk, dsContrDtl.dsContrPk);
                setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(pMsg.dsContrPrcEffPk, inMsg.dsContrPrcEffPk);
                setValue(pMsg.contrPrcEffFromDt, inMsg.contrPrcEffFromDt);
                setValue(pMsg.contrPrcEffThruDt, inMsg.contrPrcEffThruDt);
                setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                // START 2015/11/30 [QC1197,ADD]
                setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
                // END 2015/11/30 [QC1197,ADD]
                trkList.add(pMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_PRC_EFFTMsg[0]));
            if (updCnt != updList.size()) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTR_PRC_EFFTMsg().getTableName()});
                return false;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);

        for (NSZC077001PMsg pMsg : trkList) {
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                return false;
            }
        }

        return true;
    }

    private boolean updDsContrPrcEffUsg(NSAL0470CMsg cMsg, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList, BigDecimal dsContrPk, BigDecimal prntDsContrTrkPk) {

        List<DS_CONTR_PRC_EFFTMsg> updList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_BLLG_MTRTMsg dsContrBllgMtr : dsContrBllgMtrList) {
            S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getUpdDsContrPrcEffUsg(cMsg,
                    dsContrBllgMtr.dsContrBllgMtrPk.getValue());
            if (ssmResult.isCodeNotFound()) {
                continue;
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> dsContrPrcEffList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
                setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(inMsg.dsContrPrcEffPk, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));

                inMsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                if (inMsg == null) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                if (DS_CONTR_CTRL_STS.QA_HOLD.equals((String) dsContrPrcEff.get("DS_CONTR_CTRL_STS_CD"))) {
                    setValue(inMsg.qltyAsrnHldFlg, FLG_OFF_N);
                    // START 2017/10/06 K.Kojima [QC#21547,ADD]
                    if (DS_CONTR_DTL_STS.ACTIVE.equals(inMsg.dsContrPrcEffStsCd.getValue()) || DS_CONTR_DTL_STS.SIGNED.equals(inMsg.dsContrPrcEffStsCd.getValue())) {
                        setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(cMsg, (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT")));
                    } else if (DS_CONTR_DTL_STS.RENEWAL_HOLD.equals(inMsg.dsContrPrcEffStsCd.getValue())) {
                        setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(cMsg, (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT")));
                    }
                } else {
                    setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(cMsg, (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT")));
                    // END 2017/10/06 K.Kojima [QC#21547,ADD]
                }
                // START 2017/08/15 K.Kim [QC#20525, MOD]
//              } else {
                    // START 2016/02/16 T.Aoyagi [QC3840, MOD]
//                    setValue(inMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SUBMITED);
                    // Mod Start 04/20/2016 <QC#5130>
                    // START 2016/05/19 T.Kanasaka [QC#8138, MOD]
//                    String thruDt = inMsg.contrPrcEffThruDt.getValue();
//                  setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(cMsg, inMsg.contrPrcEffFromDt.getValue()));
                    // END 2016/05/19 T.Kanasaka [QC#8138, MOD]
                    // Mod End  04/20/2016 <QC#5130>
                    // END 2016/02/16 T.Aoyagi [QC3840, MOD]
//              }
                // setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(cMsg, (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT")));
                // START 2017/08/15 K.Kim [QC#20525, MOD]
                updList.add(inMsg);

                NSZC077001PMsg pMsg = new NSZC077001PMsg();
                setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
                setValue(pMsg.dsContrPk, dsContrPk);
                setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(pMsg.dsContrBllgMtrPk, inMsg.dsContrBllgMtrPk);
                setValue(pMsg.dsContrPrcEffPk, inMsg.dsContrPrcEffPk);
                setValue(pMsg.contrPrcEffFromDt, inMsg.contrPrcEffFromDt);
                setValue(pMsg.contrPrcEffThruDt, inMsg.contrPrcEffThruDt);
                setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                // START 2015/11/30 [QC1197,ADD]
                setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
                // END 2015/11/30 [QC1197,ADD]
                trkList.add(pMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_PRC_EFFTMsg[0]));
            if (updCnt != updList.size()) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTR_PRC_EFFTMsg().getTableName()});
                return false;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);

        for (NSZC077001PMsg pMsg : trkList) {
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                return false;
            }
        }

        return true;
    }

    private boolean createOverrideMemo(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg, BigDecimal dsContrPk) {

        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().findErrContrVldRslt(cMsg, sMsg);
        if (ssmResult.isCodeNotFound()) {
            return true;
        }

        StringBuilder sb = new StringBuilder();
        String separtor = System.getProperty("line.separator");

        sb.append("These validation results have been overridden as success by ");
        sb.append(getUserProfileService().getContextUserInfo().getUserId());
        sb.append(" on ");
        sb.append(ZYPDateUtil.getSalesDate());
        sb.append(separtor);

        @SuppressWarnings("unchecked")
        List<Map<String, String>> errVldRsltList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (Map<String, String> errVldRslt : errVldRsltList) {

            sb.append("Serial#:");
            sb.append(errVldRslt.get("SER_NUM"));
            sb.append("  Validation Name:");
            sb.append(errVldRslt.get("DS_CONTR_VLD_NM"));
            sb.append("  Result:");
            sb.append(errVldRslt.get("DS_CONTR_VLD_STS_DESC_TXT"));
            sb.append(separtor);

        }

        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
        setValue(inMsg.dsContrPk, dsContrPk);
        setValue(inMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(inMsg.svcMemoTpCd, SVC_MEMO_TP.OVERRIDE_CONTRACT_VALIDATION);
        // add start 2016/02/22 CSA Defect#3689
        String svcMemoRsnCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_MEMO_RSN_FOR_CPLT_CONTR, getGlobalCompanyCode());
        setValue(inMsg.svcMemoRsnCd, svcMemoRsnCd);
        // add end 2016/02/22 CSA Defect#3689

        // START 2016/06/27 [QC10746, MOD]
        if (sb.length() > SVC_MEMO_SIZE) {
            setValue(inMsg.svcCmntTxt, sb.substring(0, SVC_MEMO_SIZE));
        } else {
            setValue(inMsg.svcCmntTxt, sb.toString());
        }
        // END 2016/06/27 [QC10746, MOD]

        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {inMsg.getTableName()});
            return false;
        }
        return true;
    }

    private boolean removeDsContrVldRslt(NSAL0470CMsg cMsg, BigDecimal dsContrPk) {

        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getContrVldRsltForRemove(cMsg, dsContrPk);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        List<DS_CONTR_VLD_RSLT_WRKTMsg> delList = new ArrayList<DS_CONTR_VLD_RSLT_WRKTMsg>();

        @SuppressWarnings("unchecked")
        List<BigDecimal> vldRsltPkList = (List<BigDecimal>) ssmResult.getResultObject();
        for (BigDecimal vldRsltPk : vldRsltPkList) {
            DS_CONTR_VLD_RSLT_WRKTMsg inMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(inMsg.dsContrVldRsltWrkPk, vldRsltPk);

            inMsg = (DS_CONTR_VLD_RSLT_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
            if (inMsg == null) {
                cMsg.setMessageInfo(NZZM0003E);
                return false;
            }
            delList.add(inMsg);
        }

        if (!delList.isEmpty()) {
            int delCnt = S21FastTBLAccessor.removePhysical(delList.toArray(new DS_CONTR_VLD_RSLT_WRKTMsg[0]));
            if (delCnt != delList.size()) {
                cMsg.setMessageInfo("NSAM0031E", new String[] {new DS_CONTR_PRC_EFFTMsg().getTableName()});
                return false;
            }
        }
        return true;
    }

    // START 2016/02/16 T.Aoyagi [QC3840, ADD]
    // Mod Start 04/20/2016 <QC#5130>
    // START 2016/05/19 T.Kanasaka [QC#8138, MOD]
    private String getDsContrDtlStsCd(NSAL0470CMsg cMsg, String effFromDt) {

        String dsContrDtlStsCd = "";

        if (cMsg.slsDt.getValue().compareTo(effFromDt) >= 0) {
            dsContrDtlStsCd = DS_CONTR_DTL_STS.ACTIVE;
        } else {
            dsContrDtlStsCd = DS_CONTR_DTL_STS.SIGNED;
        }
        return dsContrDtlStsCd;
    }
    // END 2016/05/19 T.Kanasaka [QC#8138, MOD]
    // Mod End   04/20/2016 <QC#5130>
    // END 2016/02/16 T.Aoyagi [QC3840, ADD]

    // START 2016/04/21 [QC1088, ADD]
    private BigDecimal updDsContrForSendSupv(NSAL0470CMsg cMsg, NSAL0470_BSMsg bSMsg) {
        BigDecimal dsContrPk = bSMsg.dsContrPk_B.getValue();
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inMsg.dsContrPk, dsContrPk);

        inMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        if (inMsg == null) {
            cMsg.setMessageInfo(NZZM0003E);
            return null;
        }

        String findEzUpTime = bSMsg.ezUpTime_B.getValue();
        String findEzUpTimeZone = bSMsg.ezUpTimeZone_B.getValue();
        String currentEzUpTime = inMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = inMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NZZM0003E);
            return null;
        }

        setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_ON_Y);
        S21FastTBLAccessor.update(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {inMsg.getTableName() });
            return null;
        }

        // call contract tracking api
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
        setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
        setValue(pMsg.dsContrPk, dsContrPk);
        setValue(pMsg.stsMemoRsnCd, DS_CONTR_TRK_RSN.COMPLETE_CONTRACT);
        setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());

        new NSZC077001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return null;
        }
        return pMsg.prntDsContrTrkPk.getValue();
    }

    private List<DS_CONTR_DTLTMsg> updDsContrDtlForSendSupv(NSAL0470CMsg cMsg, BigDecimal dsContrPk, BigDecimal prntDsContrTrkPk) {
        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getUpdDsContrDtl(cMsg, dsContrPk);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTRTMsg().getTableName() });
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> dsContrDtlList = (List<Map<String, Object>>) ssmResult.getResultObject();

        List<DS_CONTR_DTLTMsg> updList = new ArrayList<DS_CONTR_DTLTMsg>(dsContrDtlList.size());
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>(dsContrDtlList.size());

        for (Map<String, Object> dsContrDtl : dsContrDtlList) {
            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
            setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(inMsg.dsContrDtlPk, (BigDecimal) dsContrDtl.get("DS_CONTR_DTL_PK"));

            inMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
            if (inMsg == null) {
                cMsg.setMessageInfo(NZZM0003E);
                return null;
            }
            setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_ON_Y);
            updList.add(inMsg);

            NSZC077001PMsg pMsg = new NSZC077001PMsg();
            setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
            setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
            setValue(pMsg.dsContrPk, dsContrPk);
            setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
            setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
            setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
            trkList.add(pMsg);
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_DTLTMsg[updList.size()]));
            if (updCnt != updList.size()) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTRTMsg().getTableName() });
                return null;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);

        for (NSZC077001PMsg pMsg : trkList) {
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                return null;
            }
        }
        return updList;
    }

    private List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrForSendSupv(NSAL0470CMsg cMsg, List<DS_CONTR_DTLTMsg> dsContrDtlList, BigDecimal prntDsContrTrkPk) {
        List<DS_CONTR_BLLG_MTRTMsg> updList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_DTLTMsg dsContrDtl : dsContrDtlList) {
            S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getUpdDsContrBllgMtr(cMsg, dsContrDtl.dsContrDtlPk.getValue());
            if (ssmResult.isCodeNotFound()) {
                continue;
            }
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> dsContrBllgMtrList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> dsContrBllgMtr : dsContrBllgMtrList) {
                DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
                setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(inMsg.dsContrBllgMtrPk, (BigDecimal) dsContrBllgMtr.get("DS_CONTR_BLLG_MTR_PK"));

                inMsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                if (inMsg == null) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return null;
                }
                setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_ON_Y);
                updList.add(inMsg);

                NSZC077001PMsg pMsg = new NSZC077001PMsg();
                setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
                setValue(pMsg.dsContrPk, dsContrDtl.dsContrPk);
                setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(pMsg.dsContrBllgMtrPk, inMsg.dsContrBllgMtrPk);
                setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
                trkList.add(pMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_BLLG_MTRTMsg[updList.size()]));
            if (updCnt != updList.size()) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTR_BLLG_MTRTMsg().getTableName() });
                return null;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);

        for (NSZC077001PMsg pMsg : trkList) {
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                return null;
            }
        }
        return updList;
    }

    private boolean updDsContrPrcEffBaseForSendSupv(NSAL0470CMsg cMsg, List<DS_CONTR_DTLTMsg> dsContrDtlList, BigDecimal prntDsContrTrkPk) {
        List<DS_CONTR_PRC_EFFTMsg> updList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_DTLTMsg dsContrDtl : dsContrDtlList) {
            S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getUpdDsContrPrcEffBase(cMsg, dsContrDtl.dsContrDtlPk.getValue());
            if (ssmResult.isCodeNotFound()) {
                continue;
            }
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> dsContrPrcEffList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
                setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(inMsg.dsContrPrcEffPk, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));

                inMsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                if (inMsg == null) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_ON_Y);
                updList.add(inMsg);

                NSZC077001PMsg pMsg = new NSZC077001PMsg();
                setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
                setValue(pMsg.dsContrPk, dsContrDtl.dsContrPk);
                setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(pMsg.dsContrPrcEffPk, inMsg.dsContrPrcEffPk);
                setValue(pMsg.contrPrcEffFromDt, inMsg.contrPrcEffFromDt);
                setValue(pMsg.contrPrcEffThruDt, inMsg.contrPrcEffThruDt);
                setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
                trkList.add(pMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_PRC_EFFTMsg[0]));
            if (updCnt != updList.size()) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTR_PRC_EFFTMsg().getTableName() });
                return false;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);

        for (NSZC077001PMsg pMsg : trkList) {
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                return false;
            }
        }
        return true;
    }

    private boolean updDsContrPrcEffUsgForSendSupv(NSAL0470CMsg cMsg, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList, BigDecimal dsContrPk, BigDecimal prntDsContrTrkPk) {
        List<DS_CONTR_PRC_EFFTMsg> updList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();

        for (DS_CONTR_BLLG_MTRTMsg dsContrBllgMtr : dsContrBllgMtrList) {
            S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getUpdDsContrPrcEffUsg(cMsg, dsContrBllgMtr.dsContrBllgMtrPk.getValue());
            if (ssmResult.isCodeNotFound()) {
                continue;
            }
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> dsContrPrcEffList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
                setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(inMsg.dsContrPrcEffPk, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));

                inMsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                if (inMsg == null) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                setValue(inMsg.qltyAsrnHldPendApvlFlg, FLG_ON_Y);
                updList.add(inMsg);

                NSZC077001PMsg pMsg = new NSZC077001PMsg();
                setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
                setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
                setValue(pMsg.dsContrPk, dsContrPk);
                setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
                setValue(pMsg.dsContrBllgMtrPk, inMsg.dsContrBllgMtrPk);
                setValue(pMsg.dsContrPrcEffPk, inMsg.dsContrPrcEffPk);
                setValue(pMsg.contrPrcEffFromDt, inMsg.contrPrcEffFromDt);
                setValue(pMsg.contrPrcEffThruDt, inMsg.contrPrcEffThruDt);
                setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
                setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());
                trkList.add(pMsg);
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_PRC_EFFTMsg[0]));
            if (updCnt != updList.size()) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTR_PRC_EFFTMsg().getTableName() });
                return false;
            }
        }

        // call contract tracking api
        new NSZC077001().execute(trkList, ONBATCH_TYPE.ONLINE);

        for (NSZC077001PMsg pMsg : trkList) {
            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                return false;
            }
        }
        return true;
    }

    private void startWf(NSAL0470CMsg cMsg, Map<String, Object> contrMap) {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        try {
            context = factory.getContex();
            process = context.newProcess(WF_PROCESS_NM);
            process.setDocumentId(cMsg.dsContrNum.getValue());

            // set Token Object
            NSZC102001TokenObject tokenBiz = setTokenBiz(cMsg, contrMap);
            S21NwfTokenImpl token = (S21NwfTokenImpl) process.getToken();
            token.setTokenObj(tokenBiz);

            // Start Workflow
            token.start();

        } catch (S21NwfSystemException e) {
            cMsg.setMessageInfo(NSAM0459E);
        } catch (S21NwfBizException e) {
            // Business Error Logic
            // Auto Approve Process Call APIs
            // Approve API / Reject API / Process End API Error
            cMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
        } catch (S21NwfException e) {
            // System Error Logic
            cMsg.setMessageInfo(NSAM0459E);
        }
    }

    private NSZC102001TokenObject setTokenBiz(NSAL0470CMsg cMsg, Map<String, Object> contrMap) {
        // Business Token Object
        NSZC102001TokenObject tokenBiz = new NSZC102001TokenObject();

        // Set Condition Data
        tokenBiz.setBizId(WF_PROCESS_NM);
        tokenBiz.setCondNum1((BigDecimal) contrMap.get("SVC_RG_PK"));
        tokenBiz.setCondStr1((String) contrMap.get("SVC_CONTR_BR_CD"));
        tokenBiz.setCondStr2(cMsg.contrAdminPsnCd.getValue());

        // Set Screen Data
        tokenBiz.setAttribute1Lbl("Contract#");
        tokenBiz.setAttribute1(cMsg.dsContrNum.getValue());
        tokenBiz.setAttribute2Lbl("Contract Admin");
        tokenBiz.setAttribute2(cMsg.contrAdminPsnCd.getValue());

        // Set Header Data For Mail
        tokenBiz.setHdrAttrb1(cMsg.dsContrNum.getValue());
        tokenBiz.setHdrAttrb2(cMsg.contrAdminPsnCd.getValue());
        tokenBiz.setHdrAttrb3(getGlobalCompanyCode());
        tokenBiz.setHdrAttrb4((String) contrMap.get("DS_ACCT_NM"));
        tokenBiz.setHdrAttrb5((String) contrMap.get("DS_ACCT_NUM"));
        tokenBiz.setHdrAttrb6((String) contrMap.get("DS_CONTR_CATG_DESC_TXT"));

        // Set Line Data For Mail
        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getContrVldRsltForWf((BigDecimal) contrMap.get("DS_CONTR_PK"));
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> errVldRsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (Map<String, Object> errVldRslt : errVldRsltList) {
                NSZC102001TokenObjectLine line = new NSZC102001TokenObjectLine();
                line.setDtlAttrb1((String) errVldRslt.get("DS_CONTR_VLD_STS_DESC_TXT"));
                line.setDtlAttrb2((String) errVldRslt.get("DS_CONTR_VLD_ERR_TRX"));
                line.setDtlAttrb3((String) errVldRslt.get("DS_CONTR_VLD_RSLT_MSG_TXT"));
                tokenBiz.addLineData(line);
            }
        }

        // START 2018/07/04 K.Kitachi [QC#26891, ADD]
        tokenBiz.setBizScreenId("NSAL0300");
        tokenBiz.setBizScreenParams(String.valueOf((BigDecimal) contrMap.get("DS_CONTR_PK")));
        // END 2018/07/04 K.Kitachi [QC#26891, ADD]

        return tokenBiz;
    }
    // END 2016/04/21 [QC1088, ADD]

    //START 2017/07/24 K.Kasai [QC#18882,ADD]
    private boolean checkAllMachArv(NSAL0470CMsg cMsg, BigDecimal dsContrPk) {

        // check Not Arrived Machine in Fleet Contract
        BigDecimal notArvMachCnt = NSXC001001GetNotArvMachCntForFlt.getNotArvMachCnt(getGlobalCompanyCode(), dsContrPk);

        if (BigDecimal.ZERO.compareTo(notArvMachCnt) < 0) {
            cMsg.setMessageInfo(NSAM0689W, new String[] {});
            return false;
        }
        return true;
    }
    //END 2017/07/24 K.Kasai [QC#18882,ADD]

    //START 2017/10/24 U.Kim [QC#21865, ADD]
    private boolean createOrdSch(NSAL0470CMsg cMsg, BigDecimal dsContrPk) {
        NWZC172001 api = new NWZC172001();
        S21SsmEZDResult ssmResult = NSAL0470Query.getInstance().getOrdSchInfo(dsContrPk);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> ordSchInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (Map<String, Object> ordSchInfo : ordSchInfoList) {
                NWZC172001PMsg nwzc172001PMsg = new NWZC172001PMsg();
                setValue(nwzc172001PMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(nwzc172001PMsg.slsDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode(), BUSINESS_ID));
                setValue(nwzc172001PMsg.xxRqstTpCd, NWZC172001Constant.RQST_TP_SCHD);
                setValue(nwzc172001PMsg.dsContrPk, dsContrPk);
                setValue(nwzc172001PMsg.refCpoOrdNum, (String) ordSchInfo.get("CPO_ORD_NUM"));
                setValue(nwzc172001PMsg.mdlId, (BigDecimal) ordSchInfo.get("MDL_ID"));
                setValue(nwzc172001PMsg.serNum, (String) ordSchInfo.get("SER_NUM"));
                setValue(nwzc172001PMsg.svcMachMstrPk, (BigDecimal) ordSchInfo.get("SVC_MACH_MSTR_PK"));
                setValue(nwzc172001PMsg.svcConfigMstrPk, (BigDecimal) ordSchInfo.get("SVC_CONFIG_MSTR_PK"));
                setValue(nwzc172001PMsg.dsContrNum, (String) ordSchInfo.get("DS_CONTR_NUM"));
                setValue(nwzc172001PMsg.dsContrSqNum, (String) ordSchInfo.get("DS_CONTR_SQ_NUM"));
                setValue(nwzc172001PMsg.schdAgmtVldFromDt, (String) ordSchInfo.get("CONTR_EFF_FROM_DT"));
                setValue(nwzc172001PMsg.schdAgmtVldThruDt, (String) ordSchInfo.get("CONTR_EFF_THRU_DT"));
                setValue(nwzc172001PMsg.dsContrDtlPk, (BigDecimal) ordSchInfo.get("DS_CONTR_DTL_PK"));

                List<NWZC172002PMsg> nwzc172002PMsgList = new ArrayList<NWZC172002PMsg>();
                api.execute(nwzc172001PMsg, nwzc172002PMsgList, ONBATCH_TYPE.ONLINE);
                if (ZYPCommonFunc.hasValue(nwzc172001PMsg.xxMsgIdList.no(0).xxMsgId)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nwzc172001PMsg);
                    S21ApiMessage msg = msgList.get(0);
                    cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    return false;
                }
            }
        }
        return true;
    }
    //END 2017/10/24 U.Kim [QC#21865, ADD]

    // START 2018/05/10 U.Kim [QC#25482, ADD]
    private boolean removeStagingInfo(NSAL0470CMsg cMsg, List<Map<String, Object>> dsContrDtlList) {

        List<SVC_CONTR_BLLGTMsg> contrBllgList = new ArrayList<SVC_CONTR_BLLGTMsg>();
        List<SVC_CONTR_BASE_BLLGTMsg> contrBaseBllgList = new ArrayList<SVC_CONTR_BASE_BLLGTMsg>();
        List<SVC_CONTR_MTR_BLLGTMsg> contrMtrBllgList = new ArrayList<SVC_CONTR_MTR_BLLGTMsg>();
        List<SVC_CONTR_XS_MTR_BLLGTMsg> contrXsMtrBllgList = new ArrayList<SVC_CONTR_XS_MTR_BLLGTMsg>();
        List<SVC_CONTR_ADDL_CHRG_BLLGTMsg> contrAddlChrgBllgList = new ArrayList<SVC_CONTR_ADDL_CHRG_BLLGTMsg>();
        List<DS_CONTR_BLLG_SCHDTMsg> dsContrBllgSchdList = new ArrayList<DS_CONTR_BLLG_SCHDTMsg>();

        for (Map<String, Object> dsContrDtl : dsContrDtlList) {
            // get svcContrBllg
            S21SsmEZDResult svcContrBllgResult = NSAL0470Query.getInstance().getSvcContrBllg((BigDecimal) dsContrDtl.get("DS_CONTR_DTL_PK"));
            if (svcContrBllgResult.isCodeNotFound()) {
                continue;
            }
            List<Map<String, Object>> stagingInfoList = (List<Map<String, Object>>) svcContrBllgResult.getResultObject();
            for (Map<String, Object> stagingInfo : stagingInfoList) {
                SVC_CONTR_BLLGTMsg svcContrBllgMsg = new SVC_CONTR_BLLGTMsg();
                setValue(svcContrBllgMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(svcContrBllgMsg.svcContrBllgPk, (BigDecimal) stagingInfo.get("SVC_CONTR_BLLG_PK"));
                svcContrBllgMsg = (SVC_CONTR_BLLGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(svcContrBllgMsg);
                if (svcContrBllgMsg == null) {
                    continue;
                }
                contrBllgList.add(svcContrBllgMsg);

                // get svcContrBaseBllg
                SVC_CONTR_BASE_BLLGTMsgArray svcContrBaseBllgArray = NSAL0470Query.getInstance().getSvcContrBaseBllgForUpdateNoWait((BigDecimal) stagingInfo.get("SVC_CONTR_BLLG_PK"));
                for (int i = 0; i < svcContrBaseBllgArray.length(); i++) {
                    contrBaseBllgList.add(svcContrBaseBllgArray.no(i));
                }

                // get svcContrMtrBllg
                SVC_CONTR_MTR_BLLGTMsgArray svcContrMtrBllgArray = NSAL0470Query.getInstance().getSvcContrMtrBllgForUpdateNoWait((BigDecimal) stagingInfo.get("SVC_CONTR_BLLG_PK"));
                for (int i = 0; i < svcContrMtrBllgArray.length(); i++) {
                    contrMtrBllgList.add(svcContrMtrBllgArray.no(i));
                    // get svcContrXsMtrBllg
                    SVC_CONTR_XS_MTR_BLLGTMsgArray svcContrSxMtrBllgArray = NSAL0470Query.getInstance().getSvcContrXsMtrBllgForUpdateNoWait(svcContrMtrBllgArray.no(i).svcContrMtrBllgPk.getValue());
                    for (int j = 0; j < svcContrSxMtrBllgArray.length(); j++) {
                        contrXsMtrBllgList.add(svcContrSxMtrBllgArray.no(j));
                    }
                }
                // get svcContrAddlChrgBllg
                SVC_CONTR_ADDL_CHRG_BLLGTMsgArray svcContrAddlChrgBllgArray = NSAL0470Query.getInstance().getSvcContrAddlChrgBllgForUpdateNoWait((BigDecimal) stagingInfo.get("SVC_CONTR_BLLG_PK"));
                for (int i = 0; i < svcContrAddlChrgBllgArray.length(); i++) {
                    contrAddlChrgBllgList.add(svcContrAddlChrgBllgArray.no(i));
                }
                // return dsContrBllgSchd
                DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = NSAL0470Query.getInstance().getDsContrBllgSchdForUpdateNoWait((BigDecimal) stagingInfo.get("DS_CONTR_BLLG_SCHD_PK"));
                if (dsContrBllgSchdTMsg != null && dsContrBllgSchdTMsg.invFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                    setValue(dsContrBllgSchdTMsg.bllgStageFlg, FLG_OFF_N);
                    setValue(dsContrBllgSchdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
                    setValue(dsContrBllgSchdTMsg.bllgCalcFlg, FLG_OFF_N);
                    dsContrBllgSchdTMsg.readMtrCnt.clear();
                    dsContrBllgSchdTMsg.bllgMtrCnt.clear();
                    dsContrBllgSchdTMsg.mtrChrgDealAmt.clear();
                    dsContrBllgSchdTMsg.mtrChrgFuncAmt.clear();
                    dsContrBllgSchdTMsg.slsTaxRate.clear();
                    dsContrBllgSchdTMsg.dealTaxAmt.clear();
                    dsContrBllgSchdTMsg.funcTaxAmt.clear();

                    dsContrBllgSchdList.add(dsContrBllgSchdTMsg);
                // START 2018/08/29 K.Kojima [QC#28012,ADD]
                } else if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrBllgSchdTMsg.dsContrDtlTpCd.getValue())){
                    setValue(dsContrBllgSchdTMsg.bllgStageFlg, FLG_ON_Y);
                    setValue(dsContrBllgSchdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.CLOSE);
                    setValue(dsContrBllgSchdTMsg.bllgCalcFlg, FLG_ON_Y);
                    dsContrBllgSchdTMsg.readMtrCnt.clear();
                    dsContrBllgSchdTMsg.bllgMtrCnt.clear();
                    dsContrBllgSchdTMsg.mtrChrgDealAmt.clear();
                    dsContrBllgSchdTMsg.mtrChrgFuncAmt.clear();
                    dsContrBllgSchdTMsg.slsTaxRate.clear();
                    dsContrBllgSchdTMsg.dealTaxAmt.clear();
                    dsContrBllgSchdTMsg.funcTaxAmt.clear();

                    dsContrBllgSchdList.add(dsContrBllgSchdTMsg);
                // END 2018/08/29 K.Kojima [QC#28012,ADD]
                }
            }
        }
        if (!contrBllgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.removeLogical(contrBllgList.toArray(new SVC_CONTR_BLLGTMsg[0]));
            if (updCnt != contrBllgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {new SVC_CONTR_BLLGTMsg().getTableName() });
                return false;
            }
        }
        if (!contrBaseBllgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.removeLogical(contrBaseBllgList.toArray(new SVC_CONTR_BASE_BLLGTMsg[0]));
            if (updCnt != contrBaseBllgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {new SVC_CONTR_BASE_BLLGTMsg().getTableName() });
                return false;
            }
        }
        if (!contrMtrBllgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.removeLogical(contrMtrBllgList.toArray(new SVC_CONTR_MTR_BLLGTMsg[0]));
            if (updCnt != contrMtrBllgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {new SVC_CONTR_MTR_BLLGTMsg().getTableName() });
                return false;
            }
        }
        if (!contrXsMtrBllgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.removeLogical(contrXsMtrBllgList.toArray(new SVC_CONTR_XS_MTR_BLLGTMsg[0]));
            if (updCnt != contrXsMtrBllgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {new SVC_CONTR_XS_MTR_BLLGTMsg().getTableName() });
                return false;
            }
        }
        if (!contrAddlChrgBllgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.removeLogical(contrAddlChrgBllgList.toArray(new SVC_CONTR_ADDL_CHRG_BLLGTMsg[0]));
            if (updCnt != contrAddlChrgBllgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {new SVC_CONTR_ADDL_CHRG_BLLGTMsg().getTableName() });
                return false;
            }
        }
        if (!dsContrBllgSchdList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(dsContrBllgSchdList.toArray(new DS_CONTR_BLLG_SCHDTMsg[0]));
            if (updCnt != dsContrBllgSchdList.size()) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTR_BLLG_SCHDTMsg().getTableName() });
                return false;
            }
        }
        if (!cancelWFBySvcContrBllg(cMsg, contrBllgList)) {
            return false;
        }
        return true;
    }

    private boolean cancelWFBySvcContrBllg(NSAL0470CMsg cMsg, List<SVC_CONTR_BLLGTMsg> svcContrBllgTMsgList) {
        String prefixDocId;
        String dsContrCatgCd;
        for (SVC_CONTR_BLLGTMsg inTMsg : svcContrBllgTMsgList) {
            if (inTMsg.usgChrgFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            prefixDocId = null;
            dsContrCatgCd = inTMsg.dsContrCatgCd.getValue();
            if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
                prefixDocId = PREFIX_DOC_ID_REG;
            } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                prefixDocId = PREFIX_DOC_ID_FLT;
            } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && ZYPCommonFunc.hasValue(inTMsg.svcMachMstrPk)) {
                prefixDocId = PREFIX_DOC_ID_AGG;
            }
            // Cancel Preview Billing WF
            if (!executeWorkFlowCancel(cMsg, inTMsg, prefixDocId, WF_PROCESS_NM_PREV_BLLG)) {
                return false;
            }

            // Cancel Negative Billing WF
            if (!executeWorkFlowCancel(cMsg, inTMsg, prefixDocId, WF_PROCESS_NM_NEGA_BLLG)) {
                return false;
            }
        }
        return true;
    }

    private boolean executeWorkFlowCancel(NSAL0470CMsg cMsg, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, String prefixDocId, String processNm) {

        boolean resExecWF = true;
        if (!ZYPCommonFunc.hasValue(prefixDocId)) {
            return resExecWF;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(prefixDocId);
        if (PREFIX_DOC_ID_REG.equals(prefixDocId) || PREFIX_DOC_ID_AGG.equals(prefixDocId)) {
            sb.append(svcContrBllgTMsg.svcMachMstrPk.getValue().toPlainString());
        } else {
            sb.append(svcContrBllgTMsg.dsContrPk.getValue().toPlainString());
        }
        sb.append(svcContrBllgTMsg.billToCustCd.getValue());
        sb.append(svcContrBllgTMsg.svcContrBllgThruDt.getValue());
        String documentId = sb.toString();

        resExecWF = cancelWF(cMsg, documentId, processNm);

        // START 2019/09/02 K.Kitachi [QC#52695, ADD]
        if (resExecWF) {
            sb.append(svcContrBllgTMsg.dsContrDtlPk.getValue().toPlainString());
            documentId = sb.toString();
            resExecWF = cancelWF(cMsg, documentId, processNm);
        }
        // END 2019/09/02 K.Kitachi [QC#52695, ADD]

        return resExecWF;
    }

    private boolean cancelWF(NSAL0470CMsg cMsg, String documentId, String processNm) {

        boolean resCancelWF = false;
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        List<S21NwfProcess> processes = null;

        try {

            context = factory.getContex();
            processes = context.getProcess(processNm, documentId);

            for (S21NwfProcess nwfProcess : processes) {
                if (nwfProcess.isActive()) {
                    nwfProcess.getToken().cancel();
                }
            }
            resCancelWF = true;
        } catch (S21NwfSystemException e) {
            cMsg.setMessageInfo(NSZM0866E);
        } catch (S21NwfBizException e) {
            cMsg.setMessageInfo(NSZM0866E);
        } catch (S21NwfException e) {
            cMsg.setMessageInfo(NSZM0866E);
        }

        return resCancelWF;
    }
    // END 2018/05/10 U.Kim [QC#25482, ADD]
    // Add Start 2018/05/24 QC#24989
    private boolean isMachInstl(String svcMachMstrStsCd) {
        if (SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd) || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
            return true;
        }
        return false;
    }
    // Add End 2018/05/24 QC#24989

    // START 2019/01/25 K.Kitachi [QC#30080, ADD]
    // del start 2020/03/24 QC#54318
//    private boolean updDsContrRnwEscl(NSAL0470CMsg cMsg, BigDecimal dsContrPk) {
//
//        NSAL0470Query query = NSAL0470Query.getInstance();
//        S21SsmEZDResult ssmResult = query.getDsContrRnwEscl(dsContrPk);
//        if (ssmResult.isCodeNotFound()) {
//            return true;
//        }
//
//        List<DS_CONTR_RNW_ESCLTMsg> updList = new ArrayList<DS_CONTR_RNW_ESCLTMsg>();
//        List<Map<String, Object>> dsContrRnwEsclList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        // add start 2019/08/22 QC#52413
//        for (Map<String, Object> dsContrRnwEscl : dsContrRnwEsclList) {
//            if (ZYPCommonFunc.hasValue((String) dsContrRnwEscl.get("UPLFT_PCY_DT"))) {
//                return true;
//            }
//        }
//        // add end 2019/08/22 QC#52413
//
//        for (Map<String, Object> dsContrRnwEscl : dsContrRnwEsclList) {
//            BigDecimal dsContrRnwEsclPk = (BigDecimal) dsContrRnwEscl.get("DS_CONTR_RNW_ESCL_PK");
//            String pcyDt = (String) dsContrRnwEscl.get("UPLFT_PCY_DT");
//            String fromDt = (String) dsContrRnwEscl.get("CONTR_EFF_FROM_DT");
//            String thruDt = (String) dsContrRnwEscl.get("CONTR_EFF_THRU_DT");
//
//            String calcDt = null;
//            if (ZYPCommonFunc.hasValue(pcyDt)) {
//                // START 2019/07/25 K.Kitachi [QC#52070, MOD]
////                if (ZYPDateUtil.compare(fromDt, pcyDt) <= 0 && ZYPDateUtil.compare(pcyDt, thruDt) <= 0) {
////                    continue;
////                }
//                continue;
//                // END 2019/07/25 K.Kitachi [QC#52070, MOD]
//            } else {
//                calcDt = calcPoyDt(fromDt);
//                if (ZYPDateUtil.compare(thruDt, calcDt) < 0) {
//                    continue;
//                }
//            }
//
//            DS_CONTR_RNW_ESCLTMsg inMsg = query.getDsContrRnwEsclForUpdateNoWait(dsContrRnwEsclPk);
//            if (inMsg == null) {
//                cMsg.setMessageInfo(NZZM0003E);
//                return false;
//            }
//
//            // START 2019/07/25 K.Kitachi [QC#52070, MOD]
////            if (ZYPCommonFunc.hasValue(pcyDt)) {
////                inMsg.fixTermInMthAot.clear();
////                inMsg.uplftFixedDt.clear();
////                inMsg.uplftPcyDt.clear();
////            } else {
////                setValue(inMsg.fixTermInMthAot, new BigDecimal(NUM_OF_MTH_12));
////                setValue(inMsg.uplftFixedDt, ZYPDateUtil.addDays(calcDt, -1));
////                setValue(inMsg.uplftPcyDt, calcDt);
////            }
//            setValue(inMsg.fixTermInMthAot, new BigDecimal(NUM_OF_MTH_12));
//            setValue(inMsg.uplftFixedDt, ZYPDateUtil.addDays(calcDt, -1));
//            setValue(inMsg.uplftPcyDt, calcDt);
//            // END 2019/07/25 K.Kitachi [QC#52070, MOD]
//
//            updList.add(inMsg);
//        }
//
//        if (updList.isEmpty()) {
//            return true;
//        }
//
//        int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_RNW_ESCLTMsg[0]));
//        if (updCnt != updList.size()) {
//            cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTR_RNW_ESCLTMsg().getTableName() });
//            return false;
//        }
//
//        return true;
//    }
//
//    private String calcPoyDt(String fromDt) {
//        String startDt = fromDt;
//        if (ZYPCommonFunc.hasValue(fromDt) && !fromDt.substring(NUM_OF_6).equals("01")) {
//            startDt = NSXC001001CalcDate.getNextMonthStartDate(startDt);
//        }
//        return NSXC001001CalcDate.addMonths(startDt, NUM_OF_MTH_12);
//    }
    // del end 2020/03/24 QC#54318
    // END 2019/01/25 K.Kitachi [QC#30080, ADD]

    // START 2019/06/13 K.Kitachi [QC#50811, ADD]
    private boolean callBllgSchdApiRecovMode(NSAL0470CMsg cMsg, BigDecimal dsContrPk) {
        NSZC047099PMsg pMsg = new NSZC047099PMsg();
        setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(pMsg.xxModeCd, "99");
        setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode(), BUSINESS_ID));
        setValue(pMsg.dsContrPk, dsContrPk);
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }
        return true;
    }
    // END 2019/06/13 K.Kitachi [QC#50811, ADD]
}
