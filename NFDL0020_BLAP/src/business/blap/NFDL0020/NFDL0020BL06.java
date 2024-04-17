package business.blap.NFDL0020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0020.common.NFDL0020CommonLogic;
import business.blap.NFDL0020.constant.NFDL0020Constant;
import business.db.CLT_NOTE_DTLTMsg;
import business.db.CLT_NOTE_HDRTMsg;
import business.db.CLT_NOTE_TPTMsg;
import business.db.CLT_STRGY_TRXTMsg;
import business.db.CLT_STRGY_WRK_ITEM_TRXTMsg;
import business.db.CLT_TASKTMsg;
import business.db.CLT_WRK_ITEMTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.SVC_MEMO_RSNTMsg;
import business.parts.NMZC002001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_NOTE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_STRGY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/06/16   Hitachi         K.Kojima        Update          QC#10200
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10529
 * 2016/07/20   Hitachi         K.Kojima        Update          QC#10188
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#12096
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13907
 * 2016/12/01   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/07/04   Hitachi         E.Kameishi      Update          QC#18754
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/05/11   Hitachi         K.Kojima        Update          QC#21426
 * 2018/06/21   Hitachi         Y.Takeno        Update          QC#25080
 * 2018/07/25   Hitachi         Y.Takeno        Update          QC#25767
 * 2021/05/25   CITS            G.Delgado       Update          QC#58704
 * 2021/06/05   CITS            K.Suzuki        Update          QC#58704-1
 * 2021/07/05   CITS            K.Suzuki        Update          QC#58704-3
 * 2021/10/08   CITS            G.Delgado       Update          QC#59284
 * 2021/10/22   CITS            G.Delgado       Update          QC#59321
 * 2022/01/20   Hitachi         A.Kohinata      Update          QC#56864
 * 2022/12/12   Hitachi         S.Fujita        Update          QC#60406
 *</pre>
 */
public class NFDL0020BL06 extends S21BusinessHandler implements NFDL0020Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0020Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg);

            } else if ("NFDL0020Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg);

            } else if ("NFDL0020Scrn00_Click_ContactInfoUpdate".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_ContactInfoUpdate(cMsg, sMsg);

            } else if ("NFDL0020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_CMN_Submit((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg);

            } else if ("NFDL0020Scrn00_Click_NoteUpdate".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_NoteUpdate((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_ContactInfoUpdate(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_ContactInfoUpdate================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NMZC002001PMsg apiPMsg = new NMZC002001PMsg();

        if (!ZYPCommonFunc.hasValue(bizMsg.ctacPsnPk_H)) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            if (!ZYPCommonFunc.hasValue(bizMsg.dsCtacPntValTxt_H1) && ZYPCommonFunc.hasValue(bizMsg.dsCtacPntValTxt_H2)) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.dsPrimCtacTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
            } else {
                ZYPEZDItemValueSetter.setValue(apiPMsg.dsPrimCtacTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnPk, bizMsg.ctacPsnPk_H);
        }
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, bizMsg.dsAcctNum_H);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnFirstNm, bizMsg.ctacPsnFirstNm_H);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnLastNm, bizMsg.ctacPsnLastNm_H);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacTpCd, bizMsg.ctacTpCd_H);
        ZYPEZDItemValueSetter.setValue(apiPMsg.effFromDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(apiPMsg.effThruDt, "99991231");

        int cpIdx = 0;

        if (ZYPCommonFunc.hasValue(bizMsg.dsCtacPntValTxt_H1)) {

            if (!ZYPCommonFunc.hasValue(bizMsg.dsCtacPntPk_H1)) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            } else {
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
            }

            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.dsCtacPntValTxt_H1);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntPk, bizMsg.dsCtacPntPk_H1);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            cpIdx++;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.dsCtacPntValTxt_H2)) {

            if (!ZYPCommonFunc.hasValue(bizMsg.dsCtacPntPk_H2)) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            } else {
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
            }

            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.dsCtacPntValTxt_H2);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntPk, bizMsg.dsCtacPntPk_H2);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            cpIdx++;
        }

        apiPMsg.ContactPointInfoList.setValidCount(cpIdx);
        new NMZC002001().execute(apiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return;
                }
            }
        }

        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_ContactInfoUpdate================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_NoteUpdate(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_NoteUpdate================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;

        CLT_NOTE_HDRTMsg inMsg = new CLT_NOTE_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.cltAcctCd, bizMsg.dsAcctNum_H);
        ZYPEZDItemValueSetter.setValue(inMsg.cltAcctTpCd, "20");

        CLT_NOTE_HDRTMsg updMsg = (CLT_NOTE_HDRTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (updMsg == null) {
            updMsg = new CLT_NOTE_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(updMsg.cltHdrNoteTxt, bizMsg.cltHdrNoteTxt_H);
            ZYPEZDItemValueSetter.setValue(updMsg.cratUsrId, getContextUserInfo().getUserId());
            ZYPEZDItemValueSetter.setValue(updMsg.updUsrId, getContextUserInfo().getUserId());
            EZDTBLAccessor.create(updMsg);

        } else {
            ZYPEZDItemValueSetter.setValue(updMsg.cltHdrNoteTxt, bizMsg.cltHdrNoteTxt_H);
            ZYPEZDItemValueSetter.setValue(updMsg.updUsrId, getContextUserInfo().getUserId());
            EZDTBLAccessor.update(updMsg);
        }
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_NoteUpdate================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020_Submit================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        String salesDt = ZYPDateUtil.getSalesDate();
        String userId = getContextUserInfo().getUserId();
        String glblCmpyCd = getGlobalCompanyCode();

        // START 2016/06/22 K.Kojima [QC#10529,ADD]
        boolean errFlag = false;

        // add start 2022/01/20 QC#56864
        if (NFDL0020CommonLogic.hasUpdateFuncId()) {
        // add end 2022/01/20 QC#56864

        if (ZYPCommonFunc.hasValue(bizMsg.xxYesNoCd_GH) && "1".equals(bizMsg.xxYesNoCd_GH.getValue())) {

            if (!ZYPCommonFunc.hasValue(bizMsg.cltTaskTpCd_GH)) {
                bizMsg.cltTaskTpCd_GH.setErrorInfo(1, ZZZM9025E, new String[] {"Type" });
                errFlag = true;
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.cltTaskStsCd_GH)) {
                bizMsg.cltTaskStsCd_GH.setErrorInfo(1, ZZZM9025E, new String[] {"Status" });
                errFlag = true;
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.cltTaskSubjTxt_GH)) {
                bizMsg.cltTaskSubjTxt_GH.setErrorInfo(1, ZZZM9025E, new String[] {"Name" });
                errFlag = true;
            }

            if (ZYPCommonFunc.hasValue(bizMsg.cltTaskOwnrId_GH)) {
                NFDL0020CommonLogic.getCollectionPorsonName(bizMsg, glblCmpyCd);
                // START 2016/07/07 K.Kojima [QC#10337,MOD]
                // if (!ZYPCommonFunc.hasValue(bizMsg.xxPsnNm_G1)) {
                if (!ZYPCommonFunc.hasValue(bizMsg.cltPsnNm_G1)) {
                    // END 2016/07/07 K.Kojima [QC#10337,MOD]
                    bizMsg.cltTaskOwnrId_GH.setErrorInfo(1, ZZZM9006E, new String[] {"Owner" });
                    errFlag = true;
                }
            }

        } else if (ZYPCommonFunc.hasValue(bizMsg.cltTaskSubjTxt_GH)) {

            if (!ZYPCommonFunc.hasValue(bizMsg.cltTaskStsCd_GH)) {
                bizMsg.cltTaskStsCd_GH.setErrorInfo(1, ZZZM9025E, new String[] {"Status" });
                errFlag = true;
            }

        }

        // START 2018/07/25 [QC#25767, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.cltTaskStsCd_GH) &&
                ((CLT_TASK_STS.CLOSE.equals(bizMsg.cltTaskStsCd_GH.getValue()) || (CLT_TASK_STS.CANCEL.equals(bizMsg.cltTaskStsCd_GH.getValue())))
                && !ZYPCommonFunc.hasValue(bizMsg.cltTaskDescTxt_GH))) {
            bizMsg.cltTaskDescTxt_GH.setErrorInfo(1, ZZZM9025E, new String[] {"Notes" });
            errFlag = true;
        }
        // END   2018/07/25 [QC#25767, ADD]

        if (errFlag == true) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Task");
            return;
        }
        // END 2016/06/22 K.Kojima [QC#10529,ADD]

        // START 2021/10/22 G.Delgado [QC#59321,ADD]
        boolean strgyClosed = false;
        if (bizMsg.C.getValidCount() > 0) {
            int idx = bizMsg.xxRadioBtn_CD.getValueInt();
            String cltStrgyStsCdCD = bizMsg.C.no(idx).cltStrgyStsCd_CD.getValue();
            String cltStrgyStsCdCB = bizMsg.C.no(idx).cltStrgyStsCd_CB.getValue();

            // Check if Strategy was closed
            if (!cltStrgyStsCdCD.equals(cltStrgyStsCdCB) && CLT_STRGY_STS.CLOSE.equals(cltStrgyStsCdCD)) {
                strgyClosed = true;
            }
        }
        // END 2021/10/22 G.Delgado [QC#59321,ADD]

        // START 2016/09/26 K.Kojima [QC#13004,ADD]
        // Strategy Tab [Work Item] Check
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            if (!bizMsg.D.no(i).cltWrkItemStsCd_DD.getValue().equals(bizMsg.D.no(i).cltWrkItemStsCd_DB.getValue())) {
                String cltWrkTpCd_DD = bizMsg.D.no(i).cltWrkTpCd_DD.getValue();
                String cltWrkItemStsCd_DD = bizMsg.D.no(i).cltWrkItemStsCd_DD.getValue();
                String cltWrkItemStsCd_DB = bizMsg.D.no(i).cltWrkItemStsCd_DB.getValue();
                boolean checkResult = false;
                if (cltWrkTpCd_DD.equals(CLT_WRK_TP.MANUAL)) {
                    if (cltWrkItemStsCd_DB.equals(CLT_WRK_ITEM_STS.OPEN)) {
                        if (cltWrkItemStsCd_DD.equals(CLT_WRK_ITEM_STS.PENDING) || cltWrkItemStsCd_DD.equals(CLT_WRK_ITEM_STS.SKIP)) {
                            checkResult = true;
                        }
                    } else if (cltWrkItemStsCd_DB.equals(CLT_WRK_ITEM_STS.SKIP)) {
                        if (cltWrkItemStsCd_DD.equals(CLT_WRK_ITEM_STS.OPEN) || cltWrkItemStsCd_DD.equals(CLT_WRK_ITEM_STS.COMPLETED)) {
                            checkResult = true;
                        }
                    }
                } else if (cltWrkTpCd_DD.equals(CLT_WRK_TP.AUTOMATIC)) {
                    // START 2021/05/25 G.Delgado [QC#58704,MOD]
//                    if (cltWrkItemStsCd_DB.equals(CLT_WRK_ITEM_STS.PENDING)) {
//                        if (cltWrkItemStsCd_DD.equals(CLT_WRK_ITEM_STS.OPEN) || cltWrkItemStsCd_DD.equals(CLT_WRK_ITEM_STS.COMPLETED)) {
//                            checkResult = true;
//                        }
//                    } else if (cltWrkItemStsCd_DB.equals(CLT_WRK_ITEM_STS.SKIP)) {
//                        if (cltWrkItemStsCd_DD.equals(CLT_WRK_ITEM_STS.OPEN) || cltWrkItemStsCd_DD.equals(CLT_WRK_ITEM_STS.COMPLETED)) {
//                            checkResult = true;
//                        }
//                    }
                    if (CLT_WRK_ITEM_STS.PENDING.equals(cltWrkItemStsCd_DB) || CLT_WRK_ITEM_STS.OPEN.equals(cltWrkItemStsCd_DB)) {
                        // START 2021/10/22 G.Delgado [QC#59321,MOD]
                        // if (!CLT_WRK_ITEM_STS.SKIP.equals(cltWrkItemStsCd_DD)) {
                        if (!(CLT_WRK_ITEM_STS.CANCEL.equals(cltWrkItemStsCd_DD) && strgyClosed) && !CLT_WRK_ITEM_STS.SKIP.equals(cltWrkItemStsCd_DD)) {
                        // END 2021/10/22 G.Delgado [QC#59321,MOD]
                            bizMsg.D.no(i).cltWrkItemStsCd_DD.setErrorInfo(1, NFDM0055E);
                            errFlag = true;
                        }
                    }
                    // END 2021/05/25 G.Delgado [QC#58704,MOD]
                }
                if (checkResult) {
                    String name1 = ZYPCodeDataUtil.getName(CLT_WRK_TP.class, glblCmpyCd, cltWrkTpCd_DD);
                    String name2 = ZYPCodeDataUtil.getName(CLT_WRK_ITEM_STS.class, glblCmpyCd, cltWrkItemStsCd_DB);
                    String name3 = ZYPCodeDataUtil.getName(CLT_WRK_ITEM_STS.class, glblCmpyCd, cltWrkItemStsCd_DD);
                    bizMsg.D.no(i).cltWrkItemStsCd_DD.setErrorInfo(1, NFDM0040E, new String[] {name1, name2, name3 });
                    errFlag = true;
                }
            }
        }
        if (errFlag == true) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Strategy");
            return;
        }
        // END 2016/09/26 K.Kojima [QC#13004,ADD]
        // START 2021/05/25 G.Delgado [QC#58704,ADD]
        BigDecimal prevCltStrgyTrxPk = BigDecimal.ZERO;
        String maxCltWrkItemWerdDt = null;
        // END 2021/05/25 G.Delgado [QC#58704,ADD]

        // START 2017/07/04 E.Kameishi [QC#18754,ADD]
        // Check Preceding Work Item Status Of Pending Or Open
        List<String> prevWrkItemList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            String cltWrkTpCd_DD = bizMsg.D.no(i).cltWrkTpCd_DD.getValue();
            String cltWrkItemStsCd_DD = bizMsg.D.no(i).cltWrkItemStsCd_DD.getValue();
            String cltWrkItemStsCd_DB = bizMsg.D.no(i).cltWrkItemStsCd_DB.getValue();
            // START 2021/05/25 G.Delgado [QC#58704,ADD]
            EZDCDateItem cltWrkItemWerdDtDD = bizMsg.D.no(i).cltWrkItemWerdDt_DD;
            BigDecimal cltStrgyTrxPk = bizMsg.D.no(i).cltStrgyTrxPk_DD.getValue();

            // Check if cltStrgyTrxPk changed
            if (prevCltStrgyTrxPk.compareTo(cltStrgyTrxPk) != 0) {
                // Get latest actual comp date
                maxCltWrkItemWerdDt = NFDL0020CommonLogic.getStrgyWrkItemMaxActualCompDate(glblCmpyCd, cltStrgyTrxPk);
                prevCltStrgyTrxPk = cltStrgyTrxPk;
            }
            // END 2021/05/25 G.Delgado [QC#58704,ADD]

            if (!cltWrkItemStsCd_DD.equals(cltWrkItemStsCd_DB) && CLT_WRK_TP.MANUAL.equals(cltWrkTpCd_DD) && CLT_WRK_ITEM_STS.COMPLETED.equals(cltWrkItemStsCd_DD)) {
                if (prevWrkItemList.contains(CLT_WRK_ITEM_STS.PENDING) || prevWrkItemList.contains(CLT_WRK_ITEM_STS.OPEN)) {
                    bizMsg.D.no(i).cltWrkItemStsCd_DD.setErrorInfo(1, NFDM0046E);
                    errFlag = true;
                // START 2021/05/25 G.Delgado [QC#58704,ADD]
                } else {
                    if (!ZYPCommonFunc.hasValue(cltWrkItemWerdDtDD)) {
                        // Set default actual comp date
                        ZYPEZDItemValueSetter.setValue(cltWrkItemWerdDtDD, salesDt);
                    }
                    if (!ZYPCommonFunc.hasValue(maxCltWrkItemWerdDt) || ZYPDateUtil.compare(cltWrkItemWerdDtDD.getValue(), maxCltWrkItemWerdDt) > 0) {
                        // Update latest actual comp date
                        maxCltWrkItemWerdDt = cltWrkItemWerdDtDD.getValue();
                    }
                // END 2021/05/25 G.Delgado [QC#58704,ADD]
                }
            }
            prevWrkItemList.add(cltWrkItemStsCd_DD);

            // START 2021/05/25 G.Delgado [QC#58704,ADD]
            String cltWrkItemRwsdDt = bizMsg.D.no(i).cltWrkItemRwsdDt_DD.getValue();

            if (CLT_WRK_ITEM_STS.OPEN.equals(bizMsg.D.no(i).cltWrkItemStsCd_DD.getValue()) && !ZYPCommonFunc.hasValue(bizMsg.D.no(i).cltWrkItemRwsdDt_DD)) {
                // Error if Request start date blank
                bizMsg.D.no(i).cltWrkItemRwsdDt_DD.setErrorInfo(1, NFDM0056E, new String[] {CLT_WRK_ITEM_RWSD_DT_NAME });
                errFlag = true;
            }
            if (CLT_WRK_ITEM_STS.PENDING.equals(cltWrkItemStsCd_DD) || CLT_WRK_ITEM_STS.OPEN.equals(cltWrkItemStsCd_DD)) {
                if (ZYPCommonFunc.hasValue(cltWrkItemRwsdDt) && ZYPCommonFunc.hasValue(maxCltWrkItemWerdDt)
                        && ZYPDateUtil.compare(cltWrkItemRwsdDt, maxCltWrkItemWerdDt) < 0) {
                    // START 2021/06/05 K.Suzuki [QC#58704-1,ADD]
                    if (CLT_WRK_TP.MANUAL.equals(cltWrkTpCd_DD)) {
                        // END 2021/06/05 K.Suzuki [QC#58704-1,ADD]
                        // Error if Request start date earlier than latest actual comp date
                        bizMsg.D.no(i).cltWrkItemRwsdDt_DD.setErrorInfo(1, NFDM0054E);
                        errFlag = true;
                        // START 2021/06/05 K.Suzuki [QC#58704-1,ADD]
                    }
                    // END 2021/06/05 K.Suzuki [QC#58704-1,ADD]
                }
            }
            // END 2021/05/25 G.Delgado [QC#58704,ADD]
        }
        if (errFlag == true) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Strategy");
            return;
        }
        // END 2017/07/04 E.Kameishi [QC#18754,ADD]
        

        // START 2016/09/26 K.Kojima [QC#13907,ADD]
        // Strategy Tab [Strategy] Check
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if (!bizMsg.C.no(i).cltStrgyStsCd_CD.getValue().equals(bizMsg.C.no(i).cltStrgyStsCd_CB.getValue()) && bizMsg.C.no(i).cltStrgyStsCd_CD.getValue().equals(CLT_STRGY_STS.CLOSE)) {
                List<Map<String, Object>> wrkItemList = NFDL0020CommonLogic.getCollectionStrategyWrkItemForStatusCheck(glblCmpyCd, bizMsg.C.no(i).cltStrgyTrxPk_CD.getValue());
                if (wrkItemList == null) {
                    continue;
                }
                for (int wrkItemCount = 0; wrkItemCount < wrkItemList.size(); wrkItemCount++) {
                    Map<String, Object> wrkItemData = wrkItemList.get(wrkItemCount);
                    BigDecimal cltStrgyWrkItemTrxPk = (BigDecimal) wrkItemData.get("CLT_STRGY_WRK_ITEM_TRX_PK");
                    String cltWrkItemStsCd = (String) wrkItemData.get("CLT_WRK_ITEM_STS_CD");
                    for (int scrnCount = 0; scrnCount < bizMsg.D.getValidCount(); scrnCount++) {
                        // START 2018/06/12 J.Kim [QC#21426,ADD]
                        if (!ZYPCommonFunc.hasValue(bizMsg.D.no(scrnCount).cltStrgyWrkItemTrxPk_DD)) {
                            continue;
                        }
                        // END 2018/06/12 J.Kim [QC#21426,ADD]
                        if (cltStrgyWrkItemTrxPk.compareTo(bizMsg.D.no(scrnCount).cltStrgyWrkItemTrxPk_DD.getValue()) == 0) {
                            cltWrkItemStsCd = bizMsg.D.no(scrnCount).cltWrkItemStsCd_DD.getValue();
                            break;
                        }
                    }
                    // START 2021/10/22 G.Delgado [QC#59321,MOD]
                    // if (!cltWrkItemStsCd.equals(CLT_WRK_ITEM_STS.CANCEL) && !cltWrkItemStsCd.equals(CLT_WRK_ITEM_STS.COMPLETED)) {
                    if (!cltWrkItemStsCd.equals(CLT_WRK_ITEM_STS.CANCEL) && !cltWrkItemStsCd.equals(CLT_WRK_ITEM_STS.COMPLETED) && !cltWrkItemStsCd.equals(CLT_WRK_ITEM_STS.SKIP)) {
                    // END 2021/10/22 G.Delgado [QC#59321,MOD]
                        bizMsg.C.no(i).cltStrgyStsCd_CD.setErrorInfo(1, NFDM0039E);
                        errFlag = true;
                        break;
                    }
                }
            }
        }
        if (errFlag == true) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Strategy");
            return;
        }
        // END 2016/09/26 K.Kojima [QC#13907,ADD]

        // add start 2022/01/20 QC#56864
        }
        // add end 2022/01/20 QC#56864

        //Header Note Update
        CLT_NOTE_HDRTMsg inMsg = new CLT_NOTE_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cltAcctCd, bizMsg.dsAcctNum_H);
        ZYPEZDItemValueSetter.setValue(inMsg.cltAcctTpCd, "20");
        CLT_NOTE_HDRTMsg updMsg = (CLT_NOTE_HDRTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (updMsg == null) {
            updMsg = new CLT_NOTE_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(updMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(updMsg.cltAcctCd, bizMsg.dsAcctNum_H);
            ZYPEZDItemValueSetter.setValue(updMsg.cltAcctTpCd, "20");
            ZYPEZDItemValueSetter.setValue(updMsg.cltHdrNoteTxt, bizMsg.cltHdrNoteTxt_H);
            ZYPEZDItemValueSetter.setValue(updMsg.cratUsrId, userId);
            ZYPEZDItemValueSetter.setValue(updMsg.updUsrId, userId);
            EZDTBLAccessor.create(updMsg);

        } else {
            ZYPEZDItemValueSetter.setValue(updMsg.cltHdrNoteTxt, bizMsg.cltHdrNoteTxt_H);
            ZYPEZDItemValueSetter.setValue(updMsg.updUsrId, userId);
            EZDTBLAccessor.update(updMsg);
        }

        //Detail Note Update
        CLT_NOTE_DTLTMsg updNoteMsg = new CLT_NOTE_DTLTMsg();
        if (!ZYPCommonFunc.hasValue(bizMsg.cltNoteDtlPk_FH)) {
            // START 2018/06/21 [QC#25080, MOD]
            // if (ZYPCommonFunc.hasValue(bizMsg.xxYesNoCd_FH) && bizMsg.xxYesNoCd_FH.getValue().equals("1") && ZYPCommonFunc.hasValue(bizMsg.colNoteSubjTxt_FH)) {
            if (ZYPCommonFunc.hasValue(bizMsg.xxYesNoCd_FH) && bizMsg.xxYesNoCd_FH.getValue().equals("1") && ZYPCommonFunc.hasValue(bizMsg.cltNoteTpCd_FH)) {
            // END   2018/06/21 [QC#25080, MOD]
                ZYPEZDItemValueSetter.setValue(updNoteMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updNoteMsg.cltNoteDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CLT_NOTE_DTL_SQ"));
                ZYPEZDItemValueSetter.setValue(updNoteMsg.cltAcctCd, bizMsg.dsAcctNum_H);
                ZYPEZDItemValueSetter.setValue(updNoteMsg.cltAcctTpCd, "20");
                ZYPEZDItemValueSetter.setValue(updNoteMsg.cratDt, salesDt);
                // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
                //ZYPEZDItemValueSetter.setValue(updNoteMsg.cratTs, ZYPDateUtil.getCurrentSystemTime("HHmmss"));
                ZYPEZDItemValueSetter.setValue(updNoteMsg.cratTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
                ZYPEZDItemValueSetter.setValue(updNoteMsg.cratUsrId, userId);
                // START 2018/06/21 [QC#25080, MOD]
                // ZYPEZDItemValueSetter.setValue(updNoteMsg.colNoteSubjTxt, bizMsg.colNoteSubjTxt_FH);
                CLT_NOTE_TPTMsg tMsg = (CLT_NOTE_TPTMsg) ZYPCodeDataUtil.findByCode(CLT_NOTE_TP.class, glblCmpyCd, bizMsg.cltNoteTpCd_FH.getValue());
                if (tMsg != null) {
                    ZYPEZDItemValueSetter.setValue(updNoteMsg.colNoteSubjTxt, tMsg.cltNoteTpDescTxt); 
                }
                // END   2018/06/21 [QC#25080, MOD]
                // START 2018/06/21 [QC#25080, MOD]
                // ZYPEZDItemValueSetter.setValue(updNoteMsg.colNoteTpCd, bizMsg.arCltNoteTpCd_FH);
                ZYPEZDItemValueSetter.setValue(updNoteMsg.colNoteTpCd, bizMsg.cltNoteTpCd_FH);
                // END   2018/06/21 [QC#25080, MOD]
                // START 2018/04/03 J.Kim [QC#25096,MOD]
                // ZYPEZDItemValueSetter.setValue(updNoteMsg.dtlNoteTxt, bizMsg.dtlNoteTxt_FH);
                
                // Start 2022/12/12 S.Fujita [QC#60406, DEL]
                //ZYPEZDItemValueSetter.setValue(updNoteMsg.dtlNoteTxt, bizMsg.xxMlBodyTxt_FH);
                // End 2022/12/12 S.Fujita [QC#60406, DEL]
                
                // END 2018/04/03 J.Kim [QC#25096,MOD]
                EZDTBLAccessor.create(updNoteMsg);
                // START 2016/12/01 H.Ikeda [QC#15823,ADD]
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updNoteMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFDM0013E", new String[] {"CLT_NOTE_DTL" });
                    return;
                }
                // END   2016/12/01 H.Ikeda [QC#15823,ADD]
                
                // Start 2022/12/12 S.Fujita [QC#60406, ADD]
                // update CLOB(DTL_NOTE_CLOD)
                if (!new NoteDtlClobAccessor(updNoteMsg).updateSql(bizMsg.xxMlBodyTxt_FH.getValue())) {
                    bizMsg.setMessageInfo("NFDM0013E", new String[] {"CLT_NOTE_DTL" });
                    return;
                }
                // End 2022/12/12 S.Fujita [QC#60406, ADD]
                
                ZYPEZDItemValueSetter.setValue(bizMsg.cltNoteDtlPk_FH, updNoteMsg.cltNoteDtlPk);
                ZYPEZDItemValueSetter.setValue(bizMsg.cratUsrId_FH, userId);
                // START 2016/06/22 K.Kojima [QC#10529,MOD]
                // ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_FH,
                // getContextUserInfo().getFullName());
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_FH, getContextUserInfo().getLastName() + "," + getContextUserInfo().getFirstName());
                // END 2016/06/22 K.Kojima [QC#10529,MOD]
                // START 2018/06/21 [QC#25080, ADD]
                ZYPEZDItemValueSetter.setValue(bizMsg.cratTsDplyTxt_FH, ZYPDateUtil.formatEzd14ToDisp(updNoteMsg.cratTs.getValue().substring(0, 14)));
                // END   2018/06/21 [QC#25080, ADD]
            }
        } else {
        }

        // add start 2022/01/20 QC#56864
        if (!NFDL0020CommonLogic.hasUpdateFuncId()) {
            return;
        }
        // add end 2022/01/20 QC#56864

        //Task Update
        CLT_TASKTMsg updTaskMsg = new CLT_TASKTMsg();
        if ( !ZYPCommonFunc.hasValue(bizMsg.cltTaskPk_GH)) {
            if (ZYPCommonFunc.hasValue(bizMsg.xxYesNoCd_GH) && bizMsg.xxYesNoCd_GH.getValue().equals("1") && ZYPCommonFunc.hasValue(bizMsg.cltTaskSubjTxt_GH)) {
                //Insert
                ZYPEZDItemValueSetter.setValue(updTaskMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CLT_TASK_SQ"));
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltAcctCd, bizMsg.dsAcctNum_H);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltAcctTpCd, "20");
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskSubjTxt, bizMsg.cltTaskSubjTxt_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskPrtyCd, bizMsg.cltTaskPrtyCd_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskStsCd, bizMsg.cltTaskStsCd_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskTpCd, bizMsg.cltTaskTpCd_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskOwnrId, bizMsg.cltTaskOwnrId_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cratUsrId, userId);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskDescTxt, bizMsg.cltTaskDescTxt_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskSubjTxt, bizMsg.cltTaskSubjTxt_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskRwsdDt, bizMsg.cltTaskRwsdDt_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskRwedDt, bizMsg.cltTaskRwedDt_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskCtacNm, bizMsg.cltTaskCtacNm_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskCtacPhoNum, bizMsg.cltTaskCtacPhoNum_GH);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskCratDt, salesDt);
                // START 2018/07/25 [QC#25767, ADD]
                ZYPEZDItemValueSetter.setValue(updTaskMsg.updUsrId, userId);
                ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskUpdDt, salesDt);
                // END   2018/07/25 [QC#25767, ADD]

                EZDTBLAccessor.create(updTaskMsg);
                // START 2016/12/01 H.Ikeda [QC#15823,ADD]
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTaskMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFDM0004E", new String[] {"CLT_TASK" });
                    return;
                }
                // END   2016/12/01 H.Ikeda [QC#15823,ADD]
                ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskPk_GH, updTaskMsg.cltTaskPk);
                ZYPEZDItemValueSetter.setValue(bizMsg.cratUsrId_GH, userId);
                // START 2016/06/22 K.Kojima [QC#10529,MOD]
                // ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_G2,
                // getContextUserInfo().getFullName());
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_G2, getContextUserInfo().getLastName() + "," + getContextUserInfo().getFirstName());
                ZYPEZDItemValueSetter.setValue(bizMsg.updUsrId_GH, userId);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_G5, getContextUserInfo().getLastName() + "," + getContextUserInfo().getFirstName());
                ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskUpdDt_GH, salesDt);
                // END 2016/06/22 K.Kojima [QC#10529,MOD]
                // START 2016/06/16 K.Kojima [QC#10200,ADD]
                // START 2016/06/22 K.Kojima [QC#10529,MOD]
                // getCollectionPorsonName(bizMsg);
                NFDL0020CommonLogic.getCollectionPorsonName(bizMsg, glblCmpyCd);
                // END 2016/06/22 K.Kojima [QC#10529,MOD]
                // END 2016/06/16 K.Kojima [QC#10200,ADD]
            }
        } else {
            //Update
            ZYPEZDItemValueSetter.setValue(updTaskMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskPk, bizMsg.cltTaskPk_GH);
            updTaskMsg = (CLT_TASKTMsg) EZDTBLAccessor.findByKey(updTaskMsg);

            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskStsCd, bizMsg.cltTaskStsCd_GH);
            // START 2016/06/16 K.Kojima [QC#10200,ADD]
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskSubjTxt, bizMsg.cltTaskSubjTxt_GH);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskPrtyCd, bizMsg.cltTaskPrtyCd_GH);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskTpCd, bizMsg.cltTaskTpCd_GH);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskOwnrId, bizMsg.cltTaskOwnrId_GH);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskDescTxt, bizMsg.cltTaskDescTxt_GH);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskSubjTxt, bizMsg.cltTaskSubjTxt_GH);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskRwsdDt, bizMsg.cltTaskRwsdDt_GH);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskRwedDt, bizMsg.cltTaskRwedDt_GH);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskCtacNm, bizMsg.cltTaskCtacNm_GH);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskCtacPhoNum, bizMsg.cltTaskCtacPhoNum_GH);
            // START 2018/07/25 [QC#25767, ADD]
            ZYPEZDItemValueSetter.setValue(updTaskMsg.updUsrId, userId);
            ZYPEZDItemValueSetter.setValue(updTaskMsg.cltTaskUpdDt, salesDt);
            // END   2018/07/25 [QC#25767, ADD]

            // END 2016/06/16 K.Kojima [QC#10200,ADD]
            EZDTBLAccessor.update(updTaskMsg);
            // START 2016/12/01 H.Ikeda [QC#15823,ADD]
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTaskMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFDM0004E", new String[] {"CLT_TASK" });
                return;
            }
            // END   2016/12/01 H.Ikeda [QC#15823,ADD]
            // START 2018/07/25 [QC#25767, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.updUsrId_GH, userId);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_G5, getContextUserInfo().getLastName() + "," + getContextUserInfo().getFirstName());
            ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskUpdDt_GH, salesDt);
            // END   2018/07/25 [QC#25767, ADD]
            // START 2016/06/16 K.Kojima [QC#10200,ADD]
            // START 2016/06/22 K.Kojima [QC#10529,MOD]
            // getCollectionPorsonName(bizMsg);
            NFDL0020CommonLogic.getCollectionPorsonName(bizMsg, glblCmpyCd);
            // END 2016/06/22 K.Kojima [QC#10529,MOD]
            // END 2016/06/16 K.Kojima [QC#10200,ADD]
        }

        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        String stsMemoRsnCd = ZYPCodeDataUtil.getVarCharConstValue(NFDL0020_STS_MEMO_RSN_CD, glblCmpyCd);
        SVC_MEMO_RSNTMsg svcMemoRsnTMsg = new SVC_MEMO_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(svcMemoRsnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMemoRsnTMsg.svcMemoRsnCd, stsMemoRsnCd);
        svcMemoRsnTMsg = (SVC_MEMO_RSNTMsg) EZDTBLAccessor.findByKey(svcMemoRsnTMsg);
        String stsMemoTxt = "";
        if (svcMemoRsnTMsg != null && ZYPCommonFunc.hasValue(svcMemoRsnTMsg.svcMemoRsnDescTxt)) {
            stsMemoTxt = svcMemoRsnTMsg.svcMemoRsnDescTxt.getValue();
        }

        BigDecimal dsContrPk = null;
        BigDecimal dsContrDtlPk = null;
        String rtnCd = "";
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if ( bizMsg.B.no(i).contrHldFlg_BK.getValue().equals("Y") &&  !bizMsg.B.no(i).contrHldFlg_BD.getValue().equals("Y") ) {
                dsContrPk = bizMsg.B.no(i).dsContrPk_BD.getValue();
                DS_CONTRTMsg dsContrTMsg = getDsContr(glblCmpyCd, dsContrPk);
                if (dsContrTMsg == null) {
                    cMsg.setMessageInfo("NFDM0004E", new String[] {"DS Contract" });
                    return;
                }
                if (ZYPConstant.FLG_ON_Y.compareTo(dsContrTMsg.contrHldFlg.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(dsContrTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                    S21FastTBLAccessor.update(dsContrTMsg);
                    rtnCd = dsContrTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        bizMsg.setMessageInfo("NFDM0004E", new String[] {"DS Contract Detail" });
                        return;
                    }
                }
                DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = getDsContrDtlList(glblCmpyCd, dsContrPk);
                for (int j = 0; j < dsContrDtlTMsgArray.getValidCount(); j++) {
                    DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(j);
                    dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
                    if (ZYPConstant.FLG_ON_Y.compareTo(dsContrDtlTMsg.contrHldFlg.getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                        S21FastTBLAccessor.update(dsContrDtlTMsg);
                        rtnCd = dsContrDtlTMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            bizMsg.setMessageInfo("NFDM0004E", new String[] {"DS Contract Detail" });
                            return;
                        }
                    }

                    // Update DS Contract Billing Meter
                    DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = getDsContrBllgMtrList(glblCmpyCd, dsContrDtlPk);
                    if (dsContrBllgMtrTMsgArray != null) {
                        for (int k = 0; k < dsContrBllgMtrTMsgArray.getValidCount(); k++) {
                            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(k);
                            if (ZYPConstant.FLG_ON_Y.compareTo(dsContrBllgMtrTMsg.contrHldFlg.getValue()) == 0) {
                                ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                                S21FastTBLAccessor.update(dsContrBllgMtrTMsg);
                                rtnCd = dsContrBllgMtrTMsg.getReturnCode();
                                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                    bizMsg.setMessageInfo("NFDM0004E", new String[] {"DS Contract Billing Meter" });
                                    return;
                                }
                            }
                        }
                    }

                    // Update DS Contract Price Effective
                    DS_CONTR_PRC_EFFTMsgArray dsContrPrcEffTMsgArray = getDsContrPrcEffList(glblCmpyCd, dsContrDtlPk);
                    if (dsContrPrcEffTMsgArray != null) {
                        for (int k = 0; k < dsContrPrcEffTMsgArray.getValidCount(); k++) {
                            DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = dsContrPrcEffTMsgArray.no(k);
                            if (ZYPConstant.FLG_ON_Y.compareTo(dsContrPrcEffTMsg.contrHldFlg.getValue()) == 0) {
                                ZYPEZDItemValueSetter.setValue(dsContrPrcEffTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                                S21FastTBLAccessor.update(dsContrPrcEffTMsg);
                                rtnCd = dsContrPrcEffTMsg.getReturnCode();
                                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                    bizMsg.setMessageInfo("NFDM0004E", new String[] {"DS Contract Price Effective" });
                                    return;
                                }
                            }
                        }
                    }
                }
                if (!NSXC001001ContractTracking.callContrTrk(glblCmpyCd, ContrTrkProcMode.USER_OPERATION.code, dsContrPk, userId, salesDt, stsMemoRsnCd, stsMemoTxt, ONBATCH_TYPE.ONLINE)) {
                    bizMsg.setMessageInfo(NSXC001001ContractTracking.ERR_MSG_ID);
                    return;
                }
            }
        }
        // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

        //Strategy Update
        // START 2016/12/01 H.Ikeda [QC#15823,MOD]
        int cCnt = 0;
        CLT_STRGY_TRXTMsg[] updStrMsgs = new CLT_STRGY_TRXTMsg[bizMsg.C.length()];
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if (!bizMsg.C.no(i).cltStrgyStsCd_CD.getValue().equals(bizMsg.C.no(i).cltStrgyStsCd_CB.getValue())) {
                CLT_STRGY_TRXTMsg inStrMsg = new CLT_STRGY_TRXTMsg();
                ZYPEZDItemValueSetter.setValue(inStrMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inStrMsg.cltStrgyTrxPk, bizMsg.C.no(i).cltStrgyTrxPk_CD);

                CLT_STRGY_TRXTMsg updStrMsg = (CLT_STRGY_TRXTMsg) EZDTBLAccessor.findByKey(inStrMsg);
                ZYPEZDItemValueSetter.setValue(updStrMsg.cltStrgyStsCd, bizMsg.C.no(i).cltStrgyStsCd_CD);
                if (bizMsg.C.no(i).cltStrgyStsCd_CD.getValue().equals(CLT_STRGY_STS.CLOSE)) {
                    ZYPEZDItemValueSetter.setValue(updStrMsg.cltStrgyCurFlg, ZYPConstant.FLG_OFF_N);
                }
                updStrMsgs[cCnt] = updStrMsg;
                cCnt++;
            }
        }
        if (cCnt > 0) {
            int rstCnt = S21FastTBLAccessor.update(updStrMsgs);
            if (cCnt != rstCnt) {
                bizMsg.setMessageInfo("NFDM0004E", new String[] {"CLT_STRGY_TRX"});
                return;
            }
        }
        // END   2016/12/01 H.Ikeda [QC#15823,MOD]

        // START 2018/05/11 J.Kim [QC#21426,ADD]
        // Work Item Insert/Update
        int uCnt = 0;
        List<CLT_STRGY_WRK_ITEM_TRXTMsg> wrkTMsgList = new ArrayList<CLT_STRGY_WRK_ITEM_TRXTMsg>();
        CLT_STRGY_WRK_ITEM_TRXTMsg[] cltStrgyWrkItemTrxtMsg = new CLT_STRGY_WRK_ITEM_TRXTMsg[bizMsg.D.length()];
        String billToCustCd = "";
        BigDecimal cltStrgyTrxPk = BigDecimal.ZERO;
        // START 2021/05/25 G.Delgado [QC#58704,ADD]
        // mod start 2022/01/20 QC#56864
        //prevCltStrgyTrxPk = BigDecimal.ZERO;
        BigDecimal prevCltStrgyTrxPk = BigDecimal.ZERO;
        // mod end 2022/01/20 QC#56864
        String cltWrkItemOwnrId = null;
        // END 2021/05/25 G.Delgado [QC#58704,ADD]
        for (int idx = 0; idx < bizMsg.D.getValidCount(); idx++) {

            NFDL0020_DCMsg dMsg = bizMsg.D.no(idx);
            billToCustCd = dMsg.billToCustCd_DD.getValue();
            cltStrgyTrxPk = dMsg.cltStrgyTrxPk_DD.getValue();
            // START 2021/05/25 G.Delgado [QC#58704,ADD]
            String cltWrkItemStsCd = dMsg.cltWrkItemStsCd_DD.getValue();

            if (CLT_WRK_ITEM_STS.PENDING.equals(cltWrkItemStsCd) || CLT_WRK_ITEM_STS.OPEN.equals(cltWrkItemStsCd)) {
                NFDL0020CommonLogic.checkWrkItemRequestDates(glblCmpyCd, salesDt, dMsg);
            }
            // END 2021/05/25 G.Delgado [QC#58704,ADD]

            if (ZYPCommonFunc.hasValue(dMsg.cltStrgyWrkItemTrxPk_DD)) {
                // Update
                if (!NFDL0020CommonLogic.updateWrkItmMsg(dMsg)) {
                    continue;
                }

                CLT_STRGY_WRK_ITEM_TRXTMsg inWrkItmMsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
                ZYPEZDItemValueSetter.setValue(inWrkItmMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inWrkItmMsg.cltStrgyWrkItemTrxPk, dMsg.cltStrgyWrkItemTrxPk_DD);
                CLT_STRGY_WRK_ITEM_TRXTMsg updWrkItmMsg = (CLT_STRGY_WRK_ITEM_TRXTMsg) EZDTBLAccessor.findByKey(inWrkItmMsg);

                if (!NFDL0020CommonLogic.equals(dMsg.cltWrkItemRwsdDt_DD, dMsg.cltWrkItemRwsdDt_DB)) {
                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemRwsdDt, dMsg.cltWrkItemRwsdDt_DD);
                }
                if (!NFDL0020CommonLogic.equals(dMsg.cltWrkItemRwedDt_DD, dMsg.cltWrkItemRwedDt_DB)) {
                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemRwedDt, dMsg.cltWrkItemRwedDt_DD);
                }
                if (!NFDL0020CommonLogic.equals(dMsg.cltWrkItemWsrdDt_DD, dMsg.cltWrkItemWsrdDt_DB)) {
                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemWsrdDt, dMsg.cltWrkItemWsrdDt_DD);
                }
                if (!NFDL0020CommonLogic.equals(dMsg.cltWrkItemWerdDt_DD, dMsg.cltWrkItemWerdDt_DB)) {
                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemWerdDt, dMsg.cltWrkItemWerdDt_DD);
                }
                // START 2021/05/25 G.Delgado [QC#58704,ADD]
                ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemStsCd, dMsg.cltWrkItemStsCd_DD);
                // END 2021/05/25 G.Delgado [QC#58704,ADD]
                // START 2021/07/05 K.Suzuki [QC#58704-3,ADD]
                if (CLT_WRK_ITEM_STS.SKIP.equals(cltWrkItemStsCd) && idx > 0) {
                    String prevDate = getDateOfPrevWorkItem(bizMsg.D.no(idx - 1));
                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemRwsdDt, prevDate);
                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemRwedDt, prevDate);
                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemWsrdDt, prevDate);
                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemWerdDt, prevDate);
                }
                // END 2021/07/05 K.Suzuki [QC#58704-3,ADD]

                cltStrgyWrkItemTrxtMsg[uCnt] = updWrkItmMsg;
                uCnt++;
            } else {
                // START 2021/05/25 G.Delgado [QC#58704,ADD]
                // Check if cltStrgyTrxPk changed
                if (prevCltStrgyTrxPk.compareTo(cltStrgyTrxPk) != 0) {
                    // Get collector
                    S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getCollectorFromCltStrgyTrx(glblCmpyCd, cltStrgyTrxPk);
                    if (ssmResult.isCodeNormal()) {
                        cltWrkItemOwnrId = (String) ssmResult.getResultObject();
                    } else {
                        cltWrkItemOwnrId = null;
                    }
                    prevCltStrgyTrxPk = cltStrgyTrxPk;
                }
                // END 2021/05/25 G.Delgado [QC#58704,ADD]

                // Create
                // START 2021/05/25 G.Delgado [QC#58704,MOD]
                // wrkTMsgList = NFDL0020CommonLogic.createCollectionStrategyWorkItemTransaction(glblCmpyCd, wrkTMsgList, dMsg, billToCustCd, cltStrgyTrxPk);
                wrkTMsgList = NFDL0020CommonLogic.createCollectionStrategyWorkItemTransaction(glblCmpyCd, wrkTMsgList, dMsg, billToCustCd, cltStrgyTrxPk, cltWrkItemOwnrId);
                // END 2021/05/25 G.Delgado [QC#58704,MOD]
            }
        }

        if (uCnt > 0) {
            int rstCnt = S21FastTBLAccessor.update(cltStrgyWrkItemTrxtMsg);
            if (uCnt != rstCnt) {
                bizMsg.setMessageInfo("NFDM0004E", new String[] {"CLT_STRGY_WRK_ITEM_TRX" });
                return;
            }
        }

        if (wrkTMsgList.size() > 0) {
            CLT_STRGY_WRK_ITEM_TRXTMsg[] cltStrgyWrkItemTrxList = new CLT_STRGY_WRK_ITEM_TRXTMsg[wrkTMsgList.size()];
            int insertCnt = S21FastTBLAccessor.insert(wrkTMsgList.toArray(cltStrgyWrkItemTrxList));
            if (insertCnt != wrkTMsgList.size()) {
                bizMsg.setMessageInfo("NFDM0013E", new String[] {"CLT_STRGY_WRK_ITEM_TRX" });
                return;
            }
        }
        // END 2018/05/11 J.Kim [QC#21426,ADD]

        //Work Item Update
        // START 2021/05/25 G.Delgado [QC#58704,DEL]
        // START 2016/12/01 H.Ikeda [QC#15823,MOD]
//        int dCnt = 0;
//        CLT_STRGY_WRK_ITEM_TRXTMsg[] updWrkItmMsgs = new CLT_STRGY_WRK_ITEM_TRXTMsg[bizMsg.D.length()];
//        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
//            // START 2018/05/11 J.Kim [QC#21426,ADD]
//            if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).cltStrgyWrkItemTrxPk_DD)) {
//                continue;
//            }
//            // END 2018/05/11 J.Kim [QC#21426,ADD]
//            // START 2016/09/26 K.Kojima [QC#13907,MOD]
//            if (!bizMsg.D.no(i).cltWrkItemStsCd_DD.getValue().equals(bizMsg.D.no(i).cltWrkItemStsCd_DB.getValue())) {
//                CLT_STRGY_WRK_ITEM_TRXTMsg inWrkItmMsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
//                ZYPEZDItemValueSetter.setValue(inWrkItmMsg.glblCmpyCd, glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(inWrkItmMsg.cltStrgyWrkItemTrxPk, bizMsg.D.no(i).cltStrgyWrkItemTrxPk_DD);
//
//                CLT_STRGY_WRK_ITEM_TRXTMsg updWrkItmMsg = (CLT_STRGY_WRK_ITEM_TRXTMsg) EZDTBLAccessor.findByKey(inWrkItmMsg);
//                ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemStsCd, bizMsg.D.no(i).cltWrkItemStsCd_DD);
//                if (bizMsg.D.no(i).cltWrkItemStsCd_DD.getValue().equals(CLT_WRK_ITEM_STS.OPEN)) {
//                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemWsrdDt, salesDt);
//                }
//                if (bizMsg.D.no(i).cltWrkItemStsCd_DD.getValue().equals(CLT_WRK_ITEM_STS.COMPLETED)) {
//                    if (!ZYPCommonFunc.hasValue(updWrkItmMsg.cltWrkItemWsrdDt)) {
//                        ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemWsrdDt, salesDt);
//                    }
//                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemWerdDt, salesDt);
//                }
//
//                updWrkItmMsgs[dCnt] = updWrkItmMsg;
//                dCnt++;
//            }
//            // END 2016/09/26 K.Kojima [QC#13907,MOD]
//        }
//        if (dCnt > 0) {
//            int rstCnt = S21FastTBLAccessor.update(updWrkItmMsgs);
//            if (dCnt != rstCnt) {
//                bizMsg.setMessageInfo("NFDM0004E", new String[] {"CLT_STRGY_WRK_ITEM_TRX" });
//                return;
//            }
//        }
//        // END 2016/12/01 H.Ikeda [QC#15823,MOD]
//        // START 2018/05/11 J.Kim [QC#21426,ADD]
//        if (dCnt > 0) {
//            updWrkItmMsgs = setCltWrkItemRwsdDt(bizMsg, glblCmpyCd);
//            if (updWrkItmMsgs[0] != null) {
//                int rstCnt = S21FastTBLAccessor.update(updWrkItmMsgs);
//                if (rstCnt != 1) {
//                    bizMsg.setMessageInfo("NFDM0004E", new String[] {"CLT_STRGY_WRK_ITEM_TRX" });
//                    return;
//                }
//            }
//        }
        // END 2018/05/11 J.Kim [QC#21426,ADD]
        // END 2021/05/25 G.Delgado [QC#58704,DEL]

        // START 2021/05/25 G.Delgado [QC#58704,ADD]
        errFlag = setNextStrgyWorkItem(glblCmpyCd, salesDt, bizMsg);
        if (errFlag) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Strategy");
            return;
        }
        // END 2021/05/25 G.Delgado [QC#58704,ADD]

        EZDDebugOutput.println(1, "doProcess_NFDL0020_Submit================================END", this);
    }

// START 2021/05/25 G.Delgado [QC#58704,DEL]
//    private CLT_STRGY_WRK_ITEM_TRXTMsg[] setCltWrkItemRwsdDt(NFDL0020CMsg bizMsg, String glblCmpyCd) {
//
//        String salesDt = ZYPDateUtil.getSalesDate();
//        CLT_STRGY_WRK_ITEM_TRXTMsg[] updWrkItmMsgs = new CLT_STRGY_WRK_ITEM_TRXTMsg[bizMsg.D.length()];
//        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
//            NFDL0020_DCMsg dcMsg = bizMsg.D.no(i);
//            String cltWrkItemStsCd = dcMsg.cltWrkItemStsCd_DD.getValue();
//
//            // START 2018/06/12 J.Kim [QC#21426,MOD]
//            // if (CLT_WRK_TP.MANUAL.equals(cltWrkTpCd) && (CLT_WRK_ITEM_STS.OPEN.equals(cltWrkItemStsCd) || CLT_WRK_ITEM_STS.PENDING.equals(cltWrkItemStsCd))) {
//            if (CLT_WRK_ITEM_STS.OPEN.equals(cltWrkItemStsCd) || CLT_WRK_ITEM_STS.PENDING.equals(cltWrkItemStsCd)) {
//                // END 2018/06/12 J.Kim [QC#21426,MOD]
//                CLT_STRGY_WRK_ITEM_TRXTMsg inWrkItmMsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
//                ZYPEZDItemValueSetter.setValue(inWrkItmMsg.glblCmpyCd, glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(inWrkItmMsg.cltStrgyWrkItemTrxPk, bizMsg.D.no(i).cltStrgyWrkItemTrxPk_DD);
//
//                CLT_STRGY_WRK_ITEM_TRXTMsg updWrkItmMsg = (CLT_STRGY_WRK_ITEM_TRXTMsg) EZDTBLAccessor.findByKey(inWrkItmMsg);
//                if (!ZYPCommonFunc.hasValue(updWrkItmMsg.cltWrkItemRwsdDt)) {
//                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemRwsdDt, salesDt);
//                }
//                if (!ZYPCommonFunc.hasValue(updWrkItmMsg.cltWrkItemRwedDt)) {
//                    ZYPEZDItemValueSetter.setValue(updWrkItmMsg.cltWrkItemRwedDt, salesDt);
//                }
//                updWrkItmMsgs[0] = updWrkItmMsg;
//                break;
//            }
//        }
//        return updWrkItmMsgs;
//    }
// END 2021/05/25 G.Delgado [QC#58704,DEL]

    private DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg prmTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    private DS_CONTR_DTLTMsgArray getDsContrDtlList(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        return (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    private DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEffList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_PRC_EFFTMsg prmTMsg = new DS_CONTR_PRC_EFFTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    // START 2021/05/25 G.Delgado [QC#58704,ADD]
    /**
     * Set next strategy work item
     * @param glblCmpyCd String
     * @param salesDt String
     * @param bizMsg NFDL0020CMsg
     * @return boolean
     */
    private boolean setNextStrgyWorkItem(String glblCmpyCd, String salesDt, NFDL0020CMsg bizMsg) {

        // Get sorted work items
        getCollectionStrategyWrkItem(getGlobalCompanyCode(), bizMsg);

        boolean prevCompleted = false;
        // START 2021/10/08 G.Delgado [QC#59284,ADD]
        String completedCltWrkItemCd = null;
        // END 2021/10/08 G.Delgado [QC#59284,ADD]
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            String cltWrkTpCd = bizMsg.D.no(i).cltWrkTpCd_DD.getValue();
            String cltWrkItemStsCd = bizMsg.D.no(i).cltWrkItemStsCd_DD.getValue();
            String cltWrkItemCd = bizMsg.D.no(i).cltWrkItemCd_DD.getValue();
            EZDCDateItem cltWrkItemRwsdDtDD = bizMsg.D.no(i).cltWrkItemRwsdDt_DD;
            EZDCDateItem cltWrkItemRwedDtDD = bizMsg.D.no(i).cltWrkItemRwedDt_DD;

            if (prevCompleted && (CLT_WRK_ITEM_STS.PENDING.equals(cltWrkItemStsCd) || CLT_WRK_ITEM_STS.OPEN.equals(cltWrkItemStsCd))) {
                // START 2021/10/08 G.Delgado [QC#59284,MOD]
//                if (!ZYPCommonFunc.hasValue(cltWrkItemCd)) {
//                    break;
//                }
//
//                CLT_WRK_ITEMTMsg cltWrkItemTMsg = NFDL0020Query.findByKeyForCltWrkItem(glblCmpyCd, cltWrkItemCd);
//                if (cltWrkItemTMsg == null) {
//                    break;
//                }
//
//                if (!ZYPCommonFunc.hasValue(cltWrkItemRwsdDtDD) && ZYPCommonFunc.hasValue(cltWrkItemTMsg.cltWrkWaitDaysAot)) {
                if (!ZYPCommonFunc.hasValue(cltWrkItemRwsdDtDD)) {
                    CLT_WRK_ITEMTMsg cltWrkItemTMsg = NFDL0020Query.findByKeyForCltWrkItem(glblCmpyCd, completedCltWrkItemCd);
                    if (cltWrkItemTMsg != null && ZYPCommonFunc.hasValue(cltWrkItemTMsg.cltWrkWaitDaysAot)) {
                    // END 2021/10/08 G.Delgado [QC#59284,MOD]
                        // Sales date + CLT_WRK_WAIT_DAYS_AOT
                        ZYPEZDItemValueSetter.setValue(cltWrkItemRwsdDtDD, ZYPDateUtil.addDays(salesDt, cltWrkItemTMsg.cltWrkWaitDaysAot.getValueInt()));
                    // START 2021/10/08 G.Delgado [QC#59284,ADD]
                    }
                    // END 2021/10/08 G.Delgado [QC#59284,ADD]
                }

                if (CLT_WRK_TP.AUTOMATIC.equals(cltWrkTpCd)) {
                    // Request Start Date
                    ZYPEZDItemValueSetter.setValue(cltWrkItemRwedDtDD, cltWrkItemRwsdDtDD);

                // START 2021/10/08 G.Delgado [QC#59284,MOD]
                // } else if (!ZYPCommonFunc.hasValue(cltWrkItemRwedDtDD) && CLT_WRK_TP.MANUAL.equals(cltWrkTpCd) && ZYPCommonFunc.hasValue(cltWrkItemTMsg.cltWrkNextWaitDaysAot)) {
                } else if (!ZYPCommonFunc.hasValue(cltWrkItemRwedDtDD) && CLT_WRK_TP.MANUAL.equals(cltWrkTpCd)) {
                    CLT_WRK_ITEMTMsg cltWrkItemTMsg = NFDL0020Query.findByKeyForCltWrkItem(glblCmpyCd, cltWrkItemCd);
                    if (cltWrkItemTMsg != null && ZYPCommonFunc.hasValue(cltWrkItemTMsg.cltWrkNextWaitDaysAot)) {
                    // END 2021/10/08 G.Delgado [QC#59284,MOD]
                        // Request start date + CLT_WRK_NEXT_WAIT_DAYS_AOT - 1
                        ZYPEZDItemValueSetter.setValue(cltWrkItemRwedDtDD, ZYPDateUtil.addDays(cltWrkItemRwsdDtDD.getValue(), cltWrkItemTMsg.cltWrkNextWaitDaysAot.getValueInt() - 1));
                    // START 2021/10/08 G.Delgado [QC#59284,ADD]
                    }
                    // END 2021/10/08 G.Delgado [QC#59284,ADD]
                }

                // Get latest actual comp date
                String maxCltWrkItemRwsdDt = NFDL0020CommonLogic.getStrgyWrkItemMaxActualCompDate(glblCmpyCd, bizMsg.D.no(i).cltStrgyTrxPk_DD.getValue());
                if (ZYPCommonFunc.hasValue(maxCltWrkItemRwsdDt) && ZYPDateUtil.compare(cltWrkItemRwsdDtDD.getValue(), maxCltWrkItemRwsdDt) < 0) {
                    // START 2021/06/05 K.Suzuki [QC#58704-1,ADD]
                    if (CLT_WRK_TP.MANUAL.equals(cltWrkTpCd)) {
                        // END 2021/06/05 K.Suzuki [QC#58704-1,ADD]
                        // Error if Request start date earlier than latest actual comp date
                        cltWrkItemRwsdDtDD.setErrorInfo(1, NFDM0054E);
                        return true;
                        // START 2021/06/05 K.Suzuki [QC#58704-1,ADD]
                    }
                    // END 2021/06/05 K.Suzuki [QC#58704-1,ADD]
                }

                // Update DB
                if (!cltWrkItemRwsdDtDD.getValue().equals(bizMsg.D.no(i).cltWrkItemRwsdDt_DB.getValue())
                        || !cltWrkItemRwedDtDD.getValue().equals(bizMsg.D.no(i).cltWrkItemRwedDt_DB.getValue())) {

                    CLT_STRGY_WRK_ITEM_TRXTMsg wrkItmMsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
                    ZYPEZDItemValueSetter.setValue(wrkItmMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(wrkItmMsg.cltStrgyWrkItemTrxPk, bizMsg.D.no(i).cltStrgyWrkItemTrxPk_DD);
                    wrkItmMsg = (CLT_STRGY_WRK_ITEM_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(wrkItmMsg);

                    if (wrkItmMsg != null) {
                        ZYPEZDItemValueSetter.setValue(wrkItmMsg.cltWrkItemRwsdDt, cltWrkItemRwsdDtDD);
                        ZYPEZDItemValueSetter.setValue(wrkItmMsg.cltWrkItemRwedDt, cltWrkItemRwedDtDD);

                        S21FastTBLAccessor.update(wrkItmMsg);

                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wrkItmMsg.getReturnCode())) {
                            bizMsg.setMessageInfo("NFDM0004E", new String[] {"CLT_STRGY_WRK_ITEM_TRX" });
                            return true;
                        }
                    }
                }
                break;
            }

            if (CLT_WRK_ITEM_STS.COMPLETED.equals(cltWrkItemStsCd) && CLT_WRK_TP.MANUAL.equals(cltWrkTpCd)) {
                prevCompleted = true;
                // START 2021/10/08 G.Delgado [QC#59284,ADD]
                completedCltWrkItemCd = cltWrkItemCd;
                // END 2021/10/08 G.Delgado [QC#59284,ADD]
            }
        }

        // Return no error
        return false;
    }

    /**
     * Get collection strategy work items
     * @param glblCmpyCd String
     * @param bizMsg NFDL0020CMsg
     */
    private void getCollectionStrategyWrkItem(String glblCmpyCd, NFDL0020CMsg bizMsg) {

        BigDecimal cltStrgyTrxPk = null;
        if (bizMsg.C.getValidCount() > 0) {
            int selectIdx = bizMsg.xxRadioBtn_CD.getValueInt();
            cltStrgyTrxPk = bizMsg.C.no(selectIdx).cltStrgyTrxPk_CD.getValue();
        }

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("cltAcctCd", bizMsg.dsAcctNum_H.getValue());
        if (ZYPCommonFunc.hasValue(cltStrgyTrxPk)) {
            ssmMap.put("cltStrgyTrxPk", String.valueOf(cltStrgyTrxPk));
        }
        ssmMap.put("rowNum", String.valueOf(bizMsg.D.length() + 1));
        ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        ssmMap.put("cltPsnNm", ZYPCodeDataUtil.getVarCharConstValue(NFDL0020_ASSIGNED_TO_BATCH, glblCmpyCd));
        ssmMap.put("cltPrintStsCdIsPrinted", CLT_PRINT_STS.PRINTED);
        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_C.getValue())) {
            ssmMap.put("cltStrgyStsCd", CLT_STRGY_STS.OPEN);
        }

        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getCollectionStrategyWrkItem(ssmMap, bizMsg);

        if (!ssmResult.isCodeNormal()) {
            bizMsg.D.setValidCount(0);
        }
    }
    // END 2021/05/25 G.Delgado [QC#58704,ADD]

    // START 2021/07/05 K.Suzuki [QC#58704-3,ADD]
    /**
     * Get Actual Comp Date or Request Start Date of Prev Work Item
     * @param dMsg NFDL0020_DCMsg
     */
    private String getDateOfPrevWorkItem(NFDL0020_DCMsg dMsg) {

        String prevDate = "";

        if (ZYPCommonFunc.hasValue(dMsg.cltWrkItemWerdDt_DD)) {
            prevDate = dMsg.cltWrkItemWerdDt_DD.getValue();
        } else if (ZYPCommonFunc.hasValue(dMsg.cltWrkItemRwsdDt_DD)) {
            prevDate = dMsg.cltWrkItemRwsdDt_DD.getValue();
        }

        return prevDate;
    }
    // END 2021/07/05 K.Suzuki [QC#58704-3,ADD]
}
