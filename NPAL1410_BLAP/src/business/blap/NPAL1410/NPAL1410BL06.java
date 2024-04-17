/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1410;

import static business.blap.NPAL1410.constant.NPAL1410Constant.APP_KEY_ID_HEADER;
import static business.blap.NPAL1410.constant.NPAL1410Constant.APP_KEY_ID_PARTS;
import static business.blap.NPAL1410.constant.NPAL1410Constant.BUSINESS_APPLICATION_ID;
import static business.blap.NPAL1410.constant.NPAL1410Constant.DEFAULT_SWH;
import static business.blap.NPAL1410.constant.NPAL1410Constant.DEF_PARTS_LINE_NO;
import static business.blap.NPAL1410.constant.NPAL1410Constant.EMAIL_PARAM_DATE_REQ;
import static business.blap.NPAL1410.constant.NPAL1410Constant.EMAIL_PARAM_MESSAGE;
import static business.blap.NPAL1410.constant.NPAL1410Constant.EMAIL_PARAM_PRCH_REQ_NUM;
import static business.blap.NPAL1410.constant.NPAL1410Constant.EMAIL_PARAM_WH_CD;
import static business.blap.NPAL1410.constant.NPAL1410Constant.MAIL_FROM_GROUP_ID;
import static business.blap.NPAL1410.constant.NPAL1410Constant.MAIL_TEMPLATE_ID;
import static business.blap.NPAL1410.constant.NPAL1410Constant.MAIL_TO_GROUP_ID;
import static business.blap.NPAL1410.constant.NPAL1410Constant.ML_LANG;
import static business.blap.NPAL1410.constant.NPAL1410Constant.ML_LANG_CD;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NAPM1583E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM0006E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM0076E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM0224E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1171E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1172E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1435E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1533E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1546E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1547E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1548E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1574E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1584E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM0020E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1640E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPAM1647E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.NPZM0161E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.REQUEST_TYPE_NEW_ALLOC;
import static business.blap.NPAL1410.constant.NPAL1410Constant.RMNF_ORD_NUM_SQ;
import static business.blap.NPAL1410.constant.NPAL1410Constant.RMNF_PRT_REQ_SQ;
import static business.blap.NPAL1410.constant.NPAL1410Constant.RWS_REF_NUM_2;
import static business.blap.NPAL1410.constant.NPAL1410Constant.RWS_REF_NUM_RR;
import static business.blap.NPAL1410.constant.NPAL1410Constant.TAB_CONF;
import static business.blap.NPAL1410.constant.NPAL1410Constant.TAB_PARTS;
import static business.blap.NPAL1410.constant.NPAL1410Constant.TIME_FORMAT;
import static business.blap.NPAL1410.constant.NPAL1410Constant.TRX_LINE_SUB_NUM;
import static business.blap.NPAL1410.constant.NPAL1410Constant.ZZM9000E;
import static business.blap.NPAL1410.constant.NPAL1410Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1410.common.NPAL1410CommonLogic;
import business.db.MDL_NMTMsg;
import business.db.RMNF_ORDTMsg;
import business.db.RMNF_ORD_DTLTMsg;
import business.db.RMNF_PRT_REQTMsg;
import business.db.RMNF_TASKTMsg;
import business.db.RTL_WHTMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC201001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NLZC403001PMsg;
import business.parts.NPZC127001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC048001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC201001.NLZC201001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC210001.NLZC210001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.NLZC403001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.constant.NLZC403001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC127001.NPZC127001;
import com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 07/28/2016   CSAI            Y.Imazu         Update          QC#12545
 * 08/02/2016   CSAI            Y.Imazu         Update          QC#12782
 * 08/15/2016   CSAI            Y.Imazu         Update          QC#13275
 * 08/16/2016   CSAI            Y.Imazu         Update          QC#13406
 * 09/26/2016   CITS            T.Wada          Update          QC#10915
 * 10/04/2016   CITS            T.Wada          Update          QC#13807
 * 10/05/2016   CITS            T.Hakodate      Update          QC#13276
 * 10/07/2016   CITS            T.Wada          Update          QC#13807
 * 10/13/2016   CITS            T.Wada          Update          QC#14012
 * 10/14/2016   CITS            T.Wada          Update          QC#14009
 * 10/19/2016   CITS            T.Wada          Update          QC#14007
 * 10/21/2016   CITS            T.Hakodate      Update          QC#15057
 * 10/25/2016   CITS            T.Wada          Update          QC#13875
 * 03/08/2017   CITS            T.Tokutomi      Update          QC#17895
 * 09/07/2017   CITS            S.Endo          Update          Sol#069(QC#18270)
 * 09/20/2017   CITS            T.Kikuhara      Update          QC#18675(SOL#219)
 * 10/16/2017   CITS            K.Ogino         Update          QC#21805
 * 10/16/2017   CITS            T.Tokutomi      Update          QC#21861
 * 11/09/2017   CITS            T.Tokutomi      Update          QC#22178
 * 12/15/2017   CITS            K.Ogino         Update          QC#22836
 * 01/30/2018   CITS            T.Wada          Update          QC#23072
 * 06/07/2018   CITS            Y.Iwasaki       Update          QC#26351
 * 07/27/2018   CITS            T.Hakodate      Update          QC#27277
 * 01/16/2019   Fujitsu         T.Ogura         Update          QC#29893
 * 12/05/2019   Fujitsu         T.Ogura         Update          QC#54842
*</pre>
 */
public class NPAL1410BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            cMsg.setCommitSMsg(true);

            if ("NPAL1410Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_CMN_Save((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);

            } else if ("NPAL1410Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_CMN_Submit((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);

            } else if ("NPAL1410Scrn00_Create".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_Create((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);

            } else if ("NPAL1410Scrn00_Cancel".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_Cancel((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);

            } else if ("NPAL1410Scrn00_DeleteTask".equals(screenAplID)) {
                doProcess_NPAL1410Scrn00_DeleteTask((NPAL1410CMsg) cMsg, (NPAL1410SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Save Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_CMN_Save(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {

        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        if (saveProcess(cMsg, sMsg, getGlobalCompanyCode())) {

            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Save" });
            NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * submit Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_CMN_Submit(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {

        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        if (submitProcess(cMsg, sMsg, getGlobalCompanyCode())) {

            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
            NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Create Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_Create(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {

        String ezUptimeOrg = sMsg.ezUpTime_HH.getValue();
        String ezUpTimeZoneOrg = sMsg.ezUpTimeZone_HH.getValue();

        String glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(cMsg.rmnfStartDt_H1) && !cMsg.rmnfStartDt_H1.isError()) {
            cMsg.rmnfStartDt_H1.setValue(ZYPDateUtil.getSalesDate(glblCmpyCd));
        }
        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        // save process
        if (saveProcess(cMsg, sMsg, glblCmpyCd)) {

            // create process
            if (createProcess(cMsg, sMsg, glblCmpyCd)) {
                cMsg.setMessageInfo(ZZZM9003I, new String[] {"Create" });
                NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            } else {
                // QC#14012
                ZYPEZDItemValueSetter.setValue(sMsg.ezUpTime_HH, ezUptimeOrg);
                ZYPEZDItemValueSetter.setValue(sMsg.ezUpTimeZone_HH, ezUpTimeZoneOrg);
            }
        }
    }

    /**
     * <pre>
     * Cancel Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_Cancel(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {

        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        if (cancelProcess(cMsg, sMsg, getGlobalCompanyCode())) {

            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Cancel" });
            NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Delete Task Event
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     */
    private void doProcess_NPAL1410Scrn00_DeleteTask(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        NPAL1410CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.D.getValidCount(); i++) {

            NPAL1410_DSMsg row = sMsg.D.no(i);

            if (!ZYPConstant.FLG_ON_Y.equals(row.xxChkBox_D1.getValue())) {

                continue;
            }

            // get reman task PK
            RMNF_TASKTMsg tMsg = new RMNF_TASKTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfTaskPk, row.rmnfTaskPk_DH);
            tMsg = (RMNF_TASKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

            if (tMsg == null) {

                cMsg.setMessageInfo(NPAM1171E, new String[] {"Reman Task" });
                return;
            }

            // date check
            if (!ZYPDateUtil.isSameTimeStamp(row.ezUpTime_DH.getValue(), row.ezUpTimeZone_DH.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {

                cMsg.setMessageInfo(NPAM0006E);
                return;
            }

            // delete task
            NPZC127001PMsg pMsg = new NPZC127001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC127001Constant.MODE_DELETE);
            ZYPEZDItemValueSetter.setValue(pMsg.rmnfOrdNum, sMsg.rmnfOrdNum_BK);
            ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskNum, row.rmnfTaskNum_DH);

            (new NPZC127001()).execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (0 < pMsg.xxMsgIdList.getValidCount()) {

                cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                return;
            }
        }

        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete Reman Task" });

        // remove deleted row
        List<NPAL1410_DSMsg> tempList = new ArrayList<NPAL1410_DSMsg>();

        for (int i = 0; i < sMsg.D.getValidCount(); i++) {

            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.D.no(i).xxChkBox_D1.getValue())) {

                tempList.add(sMsg.D.no(i));
            }
        }

        for (int i = 0; i < tempList.size(); i++) {

            EZDMsg.copy(tempList.get(i), null, sMsg.D.no(i), null);
        }

        sMsg.D.setValidCount(tempList.size());
        NPAL1410CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * Submit process
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean submitProcess(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // close all task
        if (!closeAllTask(cMsg, sMsg.rmnfOrdNum_BK.getValue(), glblCmpyCd, slsDt)) {

            return false;
        }

        Map<String, String> spMap = new HashMap<String, String>();
        List<Object[]> rcvInfo = new ArrayList<Object[]>();

        // get usage parts
        S21SsmEZDResult result = NPAL1410CommonLogic.getPartsUsage(sMsg.rmnfOrdNum_BK.getValue(), sMsg.rmnfRtlWhCd_H1.getValue(), glblCmpyCd);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> rmnfUsgMapList = (List<Map<String, Object>>) result.getResultObject();

            for (Map<String, Object> rmnfUsgMap : rmnfUsgMapList) {

                if (!allocationNWZC1070ForPartsUsage(cMsg, rmnfUsgMap, glblCmpyCd, slsDt)) {

                    return false;
                }

                if (!shippingPlanUpdateNWZC0030ForPartsUsage(cMsg, rmnfUsgMap, glblCmpyCd)) {

                    return false;
                }

                // get Shipping Plan Number list
                List<String> list = NPAL1410CommonLogic.getShippingPlanForPartsUsage((String) rmnfUsgMap.get("RMNF_ORD_NUM"), (String) rmnfUsgMap.get("RMNF_TASK_NUM"), (String) rmnfUsgMap.get("RMNF_USG_LINE_NUM"), glblCmpyCd);

                for (String s : list) {

                    if (!spMap.containsKey(s)) {

                        spMap.put(s, s);
                    }
                }

                // for Receiving info
                if (ZYPConstant.FLG_ON_Y.equals((String) rmnfUsgMap.get("RTRN_REQ_PRT_FLG"))) {

                    rcvInfo.add(new Object[] {(String) rmnfUsgMap.get("INVTY_LOC_CD"), rmnfUsgMap });
                }
            }
        }

        if (0 < spMap.size()) {

            // SO number Map<Shipping Plan Number, SO number>
            Map<String, String> soNumMap = new HashMap<String, String>();

            // SO Create
            if (!createSONLZC2050(cMsg, SCE_ORD_TP.REMAN_PARTS_USAGE, spMap, soNumMap, glblCmpyCd)) {

                return false;
            }

            // Close SO
            HashSet<String> procSoSet = new HashSet<String>();

            for (Map.Entry<String, String> e : soNumMap.entrySet()) {

                if (procSoSet.contains(e.getValue())) {

                    continue;
                }

                List<NLZC210001PMsg> pMsgList = new ArrayList<NLZC210001PMsg>();
                List<Map<String, Object>> soDtlList = NPAL1410CommonLogic.getShippingOrderForSubmit(e.getValue(), glblCmpyCd);

                for (Map<String, Object> soDtlMap : soDtlList) {

                    NLZC210001PMsg pMsg = new NLZC210001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.whCd, (String) soDtlMap.get("INVTY_LOC_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.soNum, e.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, SCE_ORD_TP.REMAN_PARTS_USAGE);
                    ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd, SO_PROC_STS.SHIP);
                    ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, slsDt + ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT));
                    ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, (String) soDtlMap.get("SO_SLP_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd_DT, SO_PROC_STS.SHIP);
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) soDtlMap.get("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.fromStkStsCd, (String) soDtlMap.get("FROM_STK_STS_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipQty, (BigDecimal) soDtlMap.get("SHPG_QTY"));
                    ZYPEZDItemValueSetter.setValue(pMsg.totFrtAmt, BigDecimal.ZERO);
                    pMsgList.add(pMsg);
                }

                if (!pMsgList.isEmpty()) {

                    new NLZC210001().execute(pMsgList, null, ONBATCH_TYPE.ONLINE);

                    for (NLZC210001PMsg pMsg : pMsgList) {

                        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

                            return false;
                        }
                    }
                }

                procSoSet.add(e.getValue());
            }
        }

        if (0 < rcvInfo.size()) {

            // PO Receive
            NLZC201001PMsg rcvPO = receivingPONLZC2010ForSubmit(sMsg, rcvInfo, glblCmpyCd, slsDt);

            if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(rcvPO))) {

                return false;
            }

            // call RWS API
            NLZC200001PMsg rws = rwsNLZC2000(rcvPO.poRcvNum.getValue(), glblCmpyCd);

            if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(rws))) {

                return false;
            }
        }

        // unused parts return
        List<Map<String, Object>> unUsedPartsMapList = NPAL1410CommonLogic.getUnusedParts(sMsg.rmnfOrdNum_BK.getValue(), glblCmpyCd);

        if (unUsedPartsMapList != null && !unUsedPartsMapList.isEmpty()) {

            if (!unusedPartsReturnProcess(cMsg, sMsg, unUsedPartsMapList, glblCmpyCd, slsDt)) {

                return false;
            }
        }

        // Reman order status update for Completed
        if (!updateRemanOrderSts(cMsg, sMsg, RMNF_ORD_STS.COMPLETED, glblCmpyCd)) {

            return false;
        }

        // QC#10915(Send WMS) Start
        S21SsmEZDResult res = NPAL1410CommonLogic.getWmsAdjInfo(sMsg.rmnfOrdNum_BK.getValue(), glblCmpyCd);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> prtInfoList = (List<Map<String, Object>>) res.getResultObject();
            if (prtInfoList != null && !prtInfoList.isEmpty()) {
                if (!NPAL1410CommonLogic.createAdjIf(cMsg, prtInfoList)) {
                    return false;
                }
            }
        }
        // QC#10915(Send WMS) End

        return true;
    }

    /**
     * <pre>
     * cancel process
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     */
    private boolean cancelProcess(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        if (RMNF_ORD_STS.SAVED.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {

            // Update Reman order for Cancel
            if (!updateRemanOrderSts(cMsg, sMsg, RMNF_ORD_STS.CANCELLED, glblCmpyCd)) {

                return false;
            }
            // QC#13807
        } else if (RMNF_ORD_STS.IN_PROCESS.equals(sMsg.rmnfOrdStsCd_HH.getValue()) || RMNF_ORD_STS.SO_CANCELLED.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {

            // Get Open SO for Reman Main Machine
            Map<String, Object> ssmParamMach = new HashMap<String, Object>();
            ssmParamMach.put("glblCmpyCd", glblCmpyCd);
            ssmParamMach.put("rmnfOrdNum", sMsg.rmnfOrdNum_BK.getValue());

            List<String> notInList = new ArrayList<String>();
            notInList.add(SCE_ORD_TP.REMAN_ITEM_CHANGE);
            notInList.add(SCE_ORD_TP.REMAN_LOCATOR_TRANSFER);
            ssmParamMach.put("sceOrdTpCd", notInList);

            ssmParamMach.put("flgY", ZYPConstant.FLG_ON_Y);
            ssmParamMach.put("flgN", ZYPConstant.FLG_OFF_N);
            S21SsmEZDResult resultMach = NPAL1410Query.getInstance().getOpenSoForMach(ssmParamMach);

            if (resultMach.isCodeNormal()) {

                BigDecimal openSoCnt = (BigDecimal) resultMach.getResultObject();

                if (ZYPCommonFunc.hasValue(openSoCnt) && openSoCnt.compareTo(BigDecimal.ZERO) > 0) {

                    cMsg.setMessageInfo(NPAM1574E);
                    return false;
                }
            }
            // unused parts return
            List<Map<String, Object>> unUsedPartsMapList = NPAL1410CommonLogic.getUnusedParts(sMsg.rmnfOrdNum_BK.getValue(), glblCmpyCd);

            if (unUsedPartsMapList != null && !unUsedPartsMapList.isEmpty()) {
                String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
                if (!unusedPartsReturnProcess(cMsg, sMsg, unUsedPartsMapList, glblCmpyCd, slsDt)) {
                    return false;
                }
            }

            if (!updateRemanOrderSts(cMsg, sMsg, RMNF_ORD_STS.CANCELLED, glblCmpyCd)) {

                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * unusedPartsReturnProcess
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param unUsedPartsMapList List<Map<String, Object>>
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean true : OK, false : NG
     */
    private boolean unusedPartsReturnProcess(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, List<Map<String, Object>> unUsedPartsMapList, String glblCmpyCd, String slsDt) {

        for (int i = 0; i < unUsedPartsMapList.size(); i++) {

            Map<String, Object> unUsedPartsMap = unUsedPartsMapList.get(i);

            if (!allocationNWZC1070ForSubmitBackInventory(cMsg, sMsg.rmnfOrdNum_BK.getValue(), i + 1, unUsedPartsMap, glblCmpyCd, slsDt)) {

                return false;
            }

            if (!shippingPlanUpdateNWZC0030ForSubmitBackInventory(cMsg, sMsg.rmnfOrdNum_BK.getValue(), i + 1, (BigDecimal) unUsedPartsMap.get("UNUSED_QTY"), glblCmpyCd)) {

                return false;
            }
        }

        // get Shipping Plan Number list
        Map<String, String> spMap = NPAL1410CommonLogic.getShippingPlan(TRX_SRC_TP.REMAN_PARTS_BACK_INVENTORY, sMsg.rmnfOrdNum_BK.getValue(), glblCmpyCd);

        // SO number Map<Shipping Plan Number, SO number>
        Map<String, String> soNumMap = new HashMap<String, String>();

        // SO Create(Reman Item Change)
        if (!createSONLZC2050(cMsg, SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY, spMap, soNumMap, glblCmpyCd)) {

            return false;
        }

        // Call RWS API
        HashSet<String> procSoSet = new HashSet<String>();

        for (Map.Entry<String, String> e : soNumMap.entrySet()) {

            if (procSoSet.contains(e.getValue())) {

                continue;
            }

            NLZC200001PMsg rws = rwsNLZC2000ForSO(e.getValue(), glblCmpyCd);

            if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(rws))) {

                return false;
            }

            procSoSet.add(e.getValue());
        }
        //09/07/2017 CITS S.Endo DEL Sol#069(QC#18270) START
        // SO Confirmation
//        procSoSet = new HashSet<String>();
//
//        for (Map.Entry<String, String> e : soNumMap.entrySet()) {
//
//            if (procSoSet.contains(e.getValue())) {
//
//                continue;
//            }
//
//            // get SO info
//            List<NLZC210001PMsg> pMsgList = new ArrayList<NLZC210001PMsg>();
//            List<Map<String, Object>> soDtlMapList = NPAL1410CommonLogic.getShippingOrderForSubmit(e.getValue(), glblCmpyCd);
//
//            for (Map<String, Object> soDtlMap : soDtlMapList) {
//
//                NLZC210001PMsg pMsg = new NLZC210001PMsg();
//                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(pMsg.whCd, (String) soDtlMap.get("INVTY_LOC_CD"));
//                ZYPEZDItemValueSetter.setValue(pMsg.soNum, e.getValue());
//                ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY);
//                ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd, SO_PROC_STS.SHIP);
//                ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, slsDt + ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT));
//                ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, (String) soDtlMap.get("SO_SLP_NUM"));
//                ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd_DT, SO_PROC_STS.SHIP);
//                ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) soDtlMap.get("MDSE_CD"));
//                ZYPEZDItemValueSetter.setValue(pMsg.fromStkStsCd, (String) soDtlMap.get("FROM_STK_STS_CD"));
//                ZYPEZDItemValueSetter.setValue(pMsg.shipQty, (BigDecimal) soDtlMap.get("SHPG_QTY"));
//                ZYPEZDItemValueSetter.setValue(pMsg.totFrtAmt, BigDecimal.ZERO);
//                pMsgList.add(pMsg);
//            }
//
//            if (!pMsgList.isEmpty()) {
//
//                new NLZC210001().execute(pMsgList, null, ONBATCH_TYPE.ONLINE);
//
//                for (NLZC210001PMsg pMsg : pMsgList) {
//
//                    if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {
//
//                        return false;
//                    }
//                }
//            }
//
//            procSoSet.add(e.getValue());
//        }
        //09/07/2017 CITS S.Endo DEL Sol#069(QC#18270) END
        return true;
    }

    /**
     * <pre>
     * save main Process
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     */
    private boolean saveProcess(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        // START 2019/01/16 T.Ogura [QC#29893,ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.rmnfOrdStsCd_HH) && !sMsg.rmnfMainUnitSerNum_H1.getValue().equals(sMsg.rmnfMainUnitSerNum_HH.getValue())) {
            cMsg.setMessageInfo(NPAM1640E);
            return false;
        }
        // END   2019/01/16 T.Ogura [QC#29893,ADD]

        boolean ret = true;
        // check input
        if (inputCheckHeaderStep1(cMsg, sMsg, glblCmpyCd)) {
            ret = inputCheckHeaderStep2(cMsg, sMsg, glblCmpyCd);
        } else {
            ret = false;
        }
        
        // for config tab
        if (!RMNF_ORD_STS.COMPLETED.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {

	        if (!inputCheckConfigTabStep1(cMsg, sMsg, glblCmpyCd)) {
	            cMsg.xxDplyTab.setValue(TAB_CONF);
	            return false;
	        }
        }
        if (inputCheckPartsTabStep1(cMsg, sMsg, glblCmpyCd)) {
            if (!inputCheckPartsTabStep2(cMsg, sMsg, glblCmpyCd)) {
                cMsg.xxDplyTab.setValue(TAB_PARTS);
                return false;
            }
        } else {
            cMsg.xxDplyTab.setValue(TAB_PARTS);
            return false;
        }

        if (!ret) {
            return false;
        }

        if (ZYPCommonFunc.hasValue(sMsg.rmnfOrdStsCd_HH)) {
            if (RMNF_ORD_STS.SAVED.equals(sMsg.rmnfOrdStsCd_HH.getValue()) || RMNF_ORD_STS.SO_CANCELLED.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {
                if (!deleteDetail(sMsg.rmnfOrdNum_BK.getValue(), glblCmpyCd)) {
                    cMsg.setMessageInfo(NPAM1171E, new String[] {"Reman order Detail" });
                    return false;
                }
                // delete Parts Req
                if (!deletePartsReq(sMsg.rmnfOrdNum_BK.getValue(), glblCmpyCd)) {
                    cMsg.setMessageInfo(NPAM1171E, new String[] {"Reman Parts Request" });
                    return false;
                }
                // get and check config
                if (!getConfigForSaveProcess(cMsg, sMsg, glblCmpyCd)) {
                    return false;
                }
            }
            // update reman order header data
            if (!updateHeader(cMsg, sMsg, glblCmpyCd)) {
                return false;
            }
        } else {
            // create reman order header data
            if (!createHeader(cMsg, sMsg, glblCmpyCd)) {
                return false;
            }
        }
        // QC#13807
        if ((!ZYPCommonFunc.hasValue(sMsg.rmnfOrdStsCd_HH)) || (RMNF_ORD_STS.SAVED.equals(sMsg.rmnfOrdStsCd_HH.getValue())) || (RMNF_ORD_STS.SO_CANCELLED.equals(sMsg.rmnfOrdStsCd_HH.getValue()))) {
            if (!createDetail(cMsg, sMsg, glblCmpyCd)) {
                return false;
            }
            if (!createPartsReq(cMsg, sMsg, glblCmpyCd)) {
                return false;
            }
            // QC#23072
        } else if (RMNF_ORD_STS.IN_PROCESS.equals(sMsg.rmnfOrdStsCd_HH.getValue()) || RMNF_ORD_STS.COMPLETED.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {
            if (!addAndDeletePartsReq(cMsg, sMsg, glblCmpyCd)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * getConfigForSaveProcess
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean getConfigForSaveProcess(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> recodeSerial = NPAL1410CommonLogic.checkGetConfigAndGetSerial(cMsg, sMsg, glblCmpyCd);

        if (recodeSerial == null) {

            return false;
        }

        if (ZYPCommonFunc.hasValue((BigDecimal) recodeSerial.get("SVC_CONFIG_MSTR_PK"))) {

            // Get Configration
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("svcConfigMstrPk", (BigDecimal) recodeSerial.get("SVC_CONFIG_MSTR_PK"));
            S21SsmEZDResult result = NPAL1410Query.getInstance().searchConfigration(ssmParam);

            if (!result.isCodeNormal()) {

                cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM0224E, new String[] {"Serial#" });
                return false;
            }

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

            if (sMsg.A.getValidCount() == 0) {

                for (int i = 0; i < resultMapList.size(); i++) {

                    if (sMsg.A.length() <= i) {

                        break;
                    }

                    // Config Detail
                    NPAL1410CommonLogic.setConfigDtl(sMsg, i, (Map<String, Object>) resultMapList.get(i), true);
                }

            } else {

                if (!checkConfigForSaveProcess(cMsg, sMsg, recodeSerial, resultMapList)) {

                    return false;
                }
            }

        } else {

            if (sMsg.A.getValidCount() == 0) {

                NPAL1410CommonLogic.setConfigDtl(sMsg, 0, recodeSerial, true);

            } else {

                if (!checkConfigForSaveProcess(cMsg, sMsg, recodeSerial, null)) {

                    return false;
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitMdseCd_HH, (String) recodeSerial.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.svcConfigMstrPk_A1, (BigDecimal) recodeSerial.get("SVC_CONFIG_MSTR_PK"));

        if (ZYPCommonFunc.hasValue((String) recodeSerial.get("T_MDL_NM"))) {

            ZYPEZDItemValueSetter.setValue(sMsg.rmnfMdlId_HH, (BigDecimal) recodeSerial.get("T_MDL_ID"));
            ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H1, (String) recodeSerial.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_A1, (String) recodeSerial.get("T_MDL_NM"));

        } else {

            MDL_NMTMsg tMsg = NPAL1410CommonLogic.searchModel((String) recodeSerial.get("MDSE_CD"), glblCmpyCd);
            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.rmnfMdlId_HH, tMsg.t_MdlId);
                ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H1, tMsg.t_MdlNm);
                ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_A1, tMsg.t_MdlNm);
            }
        }

        return true;
    }

    /**
     * <pre>
     * checkConfigForSaveProcess
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param mm Map<String, Object>
     * @param configList List<Map<String, Object>>
     */
    private boolean checkConfigForSaveProcess(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, Map<String, Object> mm, List<Map<String, Object>> configList) {

        // Check Configulation ID
        if (ZYPCommonFunc.hasValue(sMsg.svcConfigMstrPk_A1)) {

            if (sMsg.svcConfigMstrPk_A1.getValue().compareTo((BigDecimal) mm.get("SVC_CONFIG_MSTR_PK")) != 0) {

                cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM1546E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue((BigDecimal) mm.get("SVC_CONFIG_MSTR_PK"))) {

            if (((BigDecimal) mm.get("SVC_CONFIG_MSTR_PK")).compareTo(sMsg.svcConfigMstrPk_A1.getValue()) != 0) {

                cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM1546E);
                return false;
            }
        }

        // Service Machine Master PK
        Map<String, BigDecimal> confMap = new HashMap<String, BigDecimal>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).addConfigFlg_A1.getValue())) {

                confMap.put(sMsg.A.no(i).mdseCd_A1.getValue(), sMsg.A.no(i).svcMachMstrPk_AH.getValue());
            }
        }

        if (configList == null) {

            // Main unit only
            if (confMap.size() != 1) {

                cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM1546E);
                return false;
            }

            if (confMap.containsKey((String) mm.get("CMPT_MDSE_CD")) && (confMap.get((String) mm.get("CMPT_MDSE_CD")).compareTo((BigDecimal) mm.get("SVC_MACH_MSTR_PK")) != 0)) {

                cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM1546E);
                return false;
            }

        } else {

            Map<String, BigDecimal> confDBMap = new HashMap<String, BigDecimal>();

            for (Map<String, Object> conf : configList) {

                confDBMap.put((String) conf.get("CMPT_MDSE_CD"), (BigDecimal) conf.get("SVC_MACH_MSTR_PK"));
            }

            if (confDBMap.size() != confMap.size()) {

                // mismatch config detail count
                cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM1546E);
                return false;
            }

            for (Map.Entry<String, BigDecimal> conf : confDBMap.entrySet()) {

                if (confMap.containsKey(conf.getKey()) && (confMap.get(conf.getKey()).compareTo(conf.getValue()) != 0)) {

                    cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NPAM1546E);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * <pre>
     * createHeader
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean createHeader(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        //QC#18675 ADD START
        setMdlIdByMachMstApi(cMsg, sMsg, glblCmpyCd);
        //QC#18675 ADD END

        RMNF_ORDTMsg tMsg = new RMNF_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, ZYPOracleSeqAccessor.getNumberString(RMNF_ORD_NUM_SQ, 8));
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdTpCd, sMsg.rmnfOrdTpCd_SE.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdStsCd, ZYPConstant.FLG_ON_1);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfMainUnitMdseCd, sMsg.rmnfMainUnitMdseCd_HH.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfMainUnitSerNum, sMsg.rmnfMainUnitSerNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfMdlId, sMsg.rmnfMdlId_HH.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, sMsg.svcConfigMstrPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfRtlWhCd, sMsg.rmnfRtlWhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfRtlSwhCd, sMsg.rmnfRtlSwhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfInvtyLocCd, sMsg.rmnfInvtyLocCd_HH.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.whLoctrCd, sMsg.whLoctrCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.ownrTechTocCd, sMsg.ownrTechTocCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfCratDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfStartDt, sMsg.rmnfStartDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfEndDt, sMsg.rmnfEndDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtUsgCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfTotLborAot, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfTotLborCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOthCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfMachCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfTotCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdCmntTxt, sMsg.rmnfOrdCmntTxt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfUsgRtlWhCd, sMsg.rmnfRtlWhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfUsgRtlSwhCd, DEFAULT_SWH);
        //QC#18675 ADD START
        ZYPEZDItemValueSetter.setValue(tMsg.origMdlId, sMsg.origMdlId_HH.getValue());
        //QC#18675 ADD END
        EZDTBLAccessor.insert(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

            cMsg.setMessageInfo(NPAM1172E, new String[] {"Reman order Header" });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum_H1, tMsg.rmnfOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum_BK, tMsg.rmnfOrdNum.getValue());
        return true;
    }

    /**
     * <pre>
     * updateHeader
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean updateHeader(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        RMNF_ORDTMsg tMsg = new RMNF_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, sMsg.rmnfOrdNum_BK.getValue());
        tMsg = (RMNF_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        if (tMsg == null) {

            cMsg.setMessageInfo(NPAM1171E, new String[] {"Reman order Header" });
            return false;
        }

        // date check
        if (!isSameTimeStamp(sMsg, tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {

            cMsg.setMessageInfo(NPAM0006E);
            return false;
        }
        // QC13807
        if (RMNF_ORD_STS.SO_CANCELLED.equals(tMsg.rmnfOrdStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdStsCd, RMNF_ORD_STS.SAVED);
        }

        //QC#18675 ADD START
        setMdlIdByMachMstApi(cMsg, sMsg, glblCmpyCd);
        //QC#18675 ADD END

        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdTpCd, sMsg.rmnfOrdTpCd_SE.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfMainUnitMdseCd, sMsg.rmnfMainUnitMdseCd_HH.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfMainUnitSerNum, sMsg.rmnfMainUnitSerNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfMdlId, sMsg.rmnfMdlId_HH.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, sMsg.svcConfigMstrPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfRtlWhCd, sMsg.rmnfRtlWhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfRtlSwhCd, sMsg.rmnfRtlSwhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfInvtyLocCd, sMsg.rmnfInvtyLocCd_HH.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.whLoctrCd, sMsg.whLoctrCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.ownrTechTocCd, sMsg.ownrTechTocCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfStartDt, sMsg.rmnfStartDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdCmntTxt, sMsg.rmnfOrdCmntTxt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfUsgRtlWhCd, sMsg.rmnfRtlWhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfMachCostAmt, sMsg.rmnfMachCostAmt_H1.getValue());

        // RMNF_PRT_USG_COST_AMT
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtUsgCostAmt, NPAL1410CommonLogic.getRmnfPrtUsgCostAmt(glblCmpyCd, sMsg.rmnfOrdNum_BK.getValue(), null));
        // RMNF_TOT_LBOR_COST_AMT
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfTotLborCostAmt, NPAL1410CommonLogic.getRmnTotLborCostAmt(glblCmpyCd, sMsg.rmnfOrdNum_BK.getValue(), null));
//        ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtUsgCostAmt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(tMsg.rmnfTotLborAot, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(tMsg.rmnfUsgRtlSwhCd, DEFAULT_SWH);

        //QC#18675 ADD START
        ZYPEZDItemValueSetter.setValue(tMsg.origMdlId, sMsg.origMdlId_HH.getValue());
        //QC#18675 ADD END

        EZDTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

            cMsg.setMessageInfo(NPAM1171E, new String[] {"Reman order Header" });
            return false;
        }

        // re search
        tMsg = (RMNF_ORDTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {

            ZYPEZDItemValueSetter.setValue(sMsg.ezUpTime_HH, tMsg.ezUpTime.getValue());
            ZYPEZDItemValueSetter.setValue(sMsg.ezUpTimeZone_HH, tMsg.ezUpTimeZone.getValue());
        }

        return true;
    }

    /**
     * <pre>
     * deleteDetail
     * </pre>
     * @param rmnfOrdNum String
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean deleteDetail(String rmnfOrdNum, String glblCmpyCd) {

        RMNF_ORD_DTLTMsg tMsg = new RMNF_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, rmnfOrdNum);
        EZDTBLAccessor.removeByPartialKey(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

            return false;
        }

        return true;
    }

    /**
     * <pre>
     * createDetail
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean createDetail(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            RMNF_ORD_DTLTMsg tMsg = new RMNF_ORD_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, sMsg.rmnfOrdNum_BK.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdDtlLineNum, String.format("%03d", i + 1));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rmnfOrdDtlLineNum_AH, tMsg.rmnfOrdDtlLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.cmptMdseCd, sMsg.A.no(i).mdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.cmptSerNum, sMsg.A.no(i).serNum_A1.getValue());
            // QC#22836
            ZYPEZDItemValueSetter.setValue(tMsg.stkStsCd, sMsg.A.no(i).stkStsCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.srcRtlSwhCd, sMsg.A.no(i).srcRtlSwhCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_AH.getValue());

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rmnfToCmptMdseCd_A1)) {

                ZYPEZDItemValueSetter.setValue(tMsg.rmnfToCmptMdseCd, sMsg.A.no(i).rmnfToCmptMdseCd_A1.getValue());

            } else {

                ZYPEZDItemValueSetter.setValue(tMsg.rmnfToCmptMdseCd, sMsg.A.no(i).mdseCd_A1.getValue());
            }

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rmnfToCmptSerNum_A1)) {

                ZYPEZDItemValueSetter.setValue(tMsg.rmnfToCmptSerNum, sMsg.A.no(i).rmnfToCmptSerNum_A1.getValue());

            } else {

                ZYPEZDItemValueSetter.setValue(tMsg.rmnfToCmptSerNum, sMsg.A.no(i).serNum_A1.getValue());
            }

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).rmvConfigFlg_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(tMsg.rmvConfigFlg, ZYPConstant.FLG_ON_Y);

            } else {

                ZYPEZDItemValueSetter.setValue(tMsg.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
            }

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).addConfigFlg_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(tMsg.addConfigFlg, ZYPConstant.FLG_ON_Y);

            } else {

                ZYPEZDItemValueSetter.setValue(tMsg.addConfigFlg, ZYPConstant.FLG_OFF_N);
            }

            EZDTBLAccessor.insert(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

                cMsg.setMessageInfo(NPAM1172E, new String[] {"Reman order Detail" });
                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * deletePartsReq
     * </pre>
     * @param rmnfOrdNum String
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean deletePartsReq(String rmnfOrdNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rmnfOrdNum", rmnfOrdNum);
        ssmParam.put("notComp", ZYPConstant.FLG_OFF_0);

        S21SsmEZDResult result = NPAL1410Query.getInstance().getPartsPk(ssmParam);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

            for (Map<String, Object> resultMap : resultMapList) {

                RMNF_PRT_REQTMsg tMsg = new RMNF_PRT_REQTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqPk, (BigDecimal) resultMap.get("RMNF_PRT_REQ_PK"));
                EZDTBLAccessor.remove(tMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * <pre>
     * createPartsReq
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean createPartsReq(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        int num = DEF_PARTS_LINE_NO;

        //QC#26868 MOD START
        // Create Standard Parts Request with screen value.
//        int maxPrtReqLineNum = NPAL1410CommonLogic.getMaxPrtReqLineNum(sMsg.rmnfOrdNum_H1.getValue(), glblCmpyCd);
//        if (maxPrtReqLineNum > 0) {
//            num = maxPrtReqLineNum + 1;
//        }
//
//        if (ZYPCommonFunc.hasValue(sMsg.t_MdlNm_H1)) {
//            ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_HH, sMsg.t_MdlNm_H1);
//            S21SsmEZDResult resultParts = NPAL1410CommonLogic.getStarndardParts(sMsg, glblCmpyCd);
//            if (resultParts.isCodeNormal()) {
//                List<Map> resultPartsMap = (List<Map>) resultParts.getResultObject();
//                for (int i = 0; i < resultPartsMap.size(); i++) {
//
//                    Map<String, Object> record = (Map<String, Object>) resultPartsMap.get(i);
//                    if (NPAL1410CommonLogic.isOpenedSoStdParts(sMsg.rmnfOrdNum_H1.getValue(), (String) record.get("MDSE_CD"), glblCmpyCd)) {
//                        continue;
//                    }
//
//                    RMNF_PRT_REQTMsg tMsg = new RMNF_PRT_REQTMsg();
//                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, sMsg.rmnfOrdNum_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqPk, ZYPOracleSeqAccessor.getNumberBigDecimal(RMNF_PRT_REQ_SQ));
//                    ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqLineNum, String.format("%03d", num));
//                    ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, (String) record.get("MDSE_CD"));
//                    ZYPEZDItemValueSetter.setValue(tMsg.prtRqstQty, (BigDecimal) record.get("PRT_RQST_QTY"));
//                    ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, PROCR_TP.WAREHOUSE);
//
//                    ZYPEZDItemValueSetter.setValue(tMsg.srcRtlWhCd, sMsg.rmnfRtlWhCd_H1.getValue());
//
//                    ZYPEZDItemValueSetter.setValue(tMsg.srcRtlSwhCd, DEFAULT_SWH);
//
//                    Map<String, String> swhList = NPAL1410CommonLogic.getRtlSwhList(sMsg.rmnfRtlWhCd_H1.getValue(), glblCmpyCd);
//
//                    String temp = checkEnableWH(sMsg.rmnfRtlWhCd_H1.getValue() + DEFAULT_SWH, APP_KEY_ID_PARTS, glblCmpyCd);
//
//                    if (temp != null) {
//                        cMsg.setMessageInfo(temp);
//                        return false;
//                    }
//
//                    if (!swhList.containsKey(DEFAULT_SWH)) {
//
//                        cMsg.rmnfRtlWhCd_H1.setErrorInfo(1, NPAM0076E, new String[] {"Warehouse" });
//                        return false;
//
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(tMsg.srcInvtyLocCd, swhList.get(DEFAULT_SWH));
//                    }
//
//                    ZYPEZDItemValueSetter.setValue(tMsg.rmnfStdPrtFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtRqstProcCd, ZYPConstant.FLG_OFF_0);
//                    EZDTBLAccessor.insert(tMsg);
//
//                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//                        cMsg.setMessageInfo(NPAM1172E, new String[] {"Reman Parts Request" });
//                        return false;
//                    }
//                    num++;
//                }
//            }
//        }
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            // SO Opened
            if (ZYPConstant.FLG_ON_1.equals(sMsg.B.no(i).rmnfPrtRqstProcCd_B1.getValue())) {
                continue;
            }

            // Delete Check On
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxChkBox_B1.getValue())) {
                continue;
            }

            RMNF_PRT_REQTMsg tMsg = new RMNF_PRT_REQTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, sMsg.rmnfOrdNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqPk, ZYPOracleSeqAccessor.getNumberBigDecimal(RMNF_PRT_REQ_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqLineNum, String.format("%03d", num));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, sMsg.B.no(i).mdseCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.prtRqstQty, sMsg.B.no(i).prtRqstQty_B1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, PROCR_TP.WAREHOUSE);
            ZYPEZDItemValueSetter.setValue(tMsg.srcRtlWhCd, sMsg.rmnfRtlWhCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.srcRtlSwhCd, sMsg.B.no(i).srcRtlSwhCd_BH.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.srcInvtyLocCd, sMsg.B.no(i).srcInvtyLocCd_BH.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfStdPrtFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtRqstProcCd, ZYPConstant.FLG_OFF_0);
            EZDTBLAccessor.insert(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

                cMsg.setMessageInfo(NPAM1172E, new String[] {"Reman Parts Request" });
                return false;
            }
            num++;
        }
        //QC#26868 MOD END

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            // Delete Check On
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_C1.getValue())) {
                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxEdtModeFlg_C1.getValue())) {
                continue;
            }
            // SO Opend
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxEdtModeFlg_C1.getValue())
                    || ZYPConstant.FLG_ON_1.equals(sMsg.C.no(i).rmnfPrtRqstProcCd_C1.getValue())) {
                continue;
            }

            RMNF_PRT_REQTMsg tMsg = new RMNF_PRT_REQTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, sMsg.rmnfOrdNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqPk, ZYPOracleSeqAccessor.getNumberBigDecimal(RMNF_PRT_REQ_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqLineNum, String.format("%03d", num));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, sMsg.C.no(i).mdseCd_C1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.prtRqstQty, sMsg.C.no(i).prtRqstQty_C1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, PROCR_TP.WAREHOUSE);
            ZYPEZDItemValueSetter.setValue(tMsg.srcRtlWhCd, sMsg.rmnfRtlWhCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.srcRtlSwhCd, sMsg.C.no(i).srcRtlSwhCd_CH.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.srcInvtyLocCd, sMsg.C.no(i).srcInvtyLocCd_CH.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfStdPrtFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtRqstProcCd, ZYPConstant.FLG_OFF_0);
            EZDTBLAccessor.insert(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NPAM1172E, new String[] {"Reman Parts Request" });
                return false;
            }
            num++;
        }

        return true;
    }

    /**
     * <pre>
     * addPartsReq
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean addAndDeletePartsReq(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        int num = DEF_PARTS_LINE_NO;

        int maxPrtReqLineNum = NPAL1410CommonLogic.getMaxPrtReqLineNum(sMsg.rmnfOrdNum_H1.getValue(), glblCmpyCd);
        if (maxPrtReqLineNum > 0) {
            num = maxPrtReqLineNum + 1;
        }

        // QC#27277 ADD START
        // delete standard parts
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxChkBox_B1.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).rmnfPrtReqPk_B1)) {
                    RMNF_PRT_REQTMsg tMsg = new RMNF_PRT_REQTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqPk, sMsg.B.no(i).rmnfPrtReqPk_B1.getValue());
                    EZDTBLAccessor.remove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM1171E, new String[] {"Reman Parts Request" });
                        return false;
                    }
                }
                continue;
            }
        }

        // QC#27277 ADD END
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            // QC#13807 If Del Check Box FlgOn -> Delete Parts Req
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_C1.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).rmnfPrtReqPk_C1)) {
                    RMNF_PRT_REQTMsg tMsg = new RMNF_PRT_REQTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqPk, sMsg.C.no(i).rmnfPrtReqPk_C1.getValue());
                    EZDTBLAccessor.remove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPAM1171E, new String[] {"Reman Parts Request" });
                        return false;
                    }
                }
                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxEdtModeFlg_C1.getValue())) {

                continue;
            }

            // QC#27277 ADD START
            // so does not exists.
            if (ZYPConstant.FLG_OFF_0.equals(sMsg.C.no(i).rmnfPrtRqstProcCd_C1.getValue())) {

                RMNF_PRT_REQTMsg updateTMsg = new RMNF_PRT_REQTMsg();
                ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updateTMsg.rmnfPrtReqPk, sMsg.C.no(i).rmnfPrtReqPk_C1.getValue());

                updateTMsg = (RMNF_PRT_REQTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updateTMsg);

                ZYPEZDItemValueSetter.setValue(updateTMsg.mdseCd, sMsg.C.no(i).mdseCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(updateTMsg.prtRqstQty, sMsg.C.no(i).prtRqstQty_C1.getValue());

                EZDTBLAccessor.update(updateTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NPAM1171E, new String[] {"Reman Parts Request" });
                    return false;
                }

            } else {

                if (0 <= sMsg.C.no(i).xxNewRowNum_C1.getValueInt()) {

                    continue;
                }

                // add new line
                RMNF_PRT_REQTMsg tMsg = new RMNF_PRT_REQTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, sMsg.rmnfOrdNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqPk, ZYPOracleSeqAccessor.getNumberBigDecimal(RMNF_PRT_REQ_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqLineNum, String.format("%03d", (num + 1)));
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, sMsg.C.no(i).mdseCd_C1.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.prtRqstQty, sMsg.C.no(i).prtRqstQty_C1.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, PROCR_TP.WAREHOUSE);
                ZYPEZDItemValueSetter.setValue(tMsg.srcRtlWhCd, sMsg.rmnfRtlWhCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.srcRtlSwhCd, sMsg.C.no(i).srcRtlSwhCd_CH.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.srcInvtyLocCd, sMsg.C.no(i).srcInvtyLocCd_CH.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfStdPrtFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtRqstProcCd, ZYPConstant.FLG_OFF_0);
                EZDTBLAccessor.insert(tMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

                    cMsg.setMessageInfo(NPAM1172E, new String[] {"Reman Parts Request" });
                    return false;
                }
                num++;
            }
        }

        return true;
    }

    /**
     * <pre>
     * inputCheckHeaderStep1
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean inputCheckHeaderStep1(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        boolean ret = true;

        // Reman Warehouse
        Map<String, Object> item = NPAL1410CommonLogic.getWhInfo(cMsg.rmnfRtlWhCd_H1.getValue(), cMsg.rmnfRtlSwhCd_H1.getValue(), glblCmpyCd);

        if (item != null) {

            ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_H1, (String) item.get("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.rmnfInvtyLocCd_HH, (String) item.get("INVTY_LOC_CD"));

        } else {

            cMsg.rmnfRtlWhCd_H1.setErrorInfo(1, NPAM0076E, new String[] {"Warehouse" });
            cMsg.rmnfRtlSwhCd_H1.setErrorInfo(1, NPAM0076E, new String[] {"Warehouse" });
            ret = false;
        }

        // Tech
        String name = NPAL1410CommonLogic.getTechName(cMsg.ownrTechTocCd_H1.getValue(), glblCmpyCd);

        if (name != null) {

            ZYPEZDItemValueSetter.setValue(sMsg.techNm_H1, name);

        } else {

            cMsg.ownrTechTocCd_H1.setErrorInfo(1, NPAM0076E, new String[] {"Technician" });
            cMsg.techNm_H1.clear();
            ret = false;
        }

        // Locator
        if (!NPAL1410CommonLogic.existLocator(sMsg.rmnfRtlWhCd_H1.getValue(), sMsg.whLoctrCd_H1.getValue(), glblCmpyCd)) {

            cMsg.whLoctrCd_H1.setErrorInfo(1, NPAM0076E, new String[] {"Locator" });
            ret = false;
        }

        return ret;
    }

    /**
     * <pre>
     * inputCheckHeaderStep2
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean inputCheckHeaderStep2(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        // for Header
        String temp = checkEnableWH(sMsg.rmnfRtlWhCd_H1.getValue() + sMsg.rmnfRtlSwhCd_H1.getValue(), APP_KEY_ID_HEADER, glblCmpyCd);

        if (temp != null) {

            cMsg.setMessageInfo(temp);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * inputCheckConfigTabStep1
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean inputCheckConfigTabStep1(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        boolean ret = true;
        cMsg.xxPageShowFromNum.setValue(1);
        // QC#14007
        if (0 == sMsg.A.getValidCount()) {
            cMsg.rmnfMainUnitSerNum_H1.setErrorInfo(1, NAPM1583E);
            ret = false;
        }

        while (0 < sMsg.A.getValidCount()) {

            NPAL1410CommonLogic.setPagePos(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);
            int cMsgCount = 0;

            for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }

            cMsg.A.setValidCount(cMsgCount);

            if (!inputCheckConfigTabOfPage(cMsg, sMsg, glblCmpyCd)) {

                ret = false;
                break;
            }

            if (cMsg.xxPageShowToNum.getValue().compareTo(cMsg.xxPageShowOfNum.getValue()) == 0) {

                break;
            }

            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length());
        }

        return ret;
    }

    /**
     * <pre>
     * inputCheckConfigTabOfPage
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean inputCheckConfigTabOfPage(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        boolean ret = true;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).mdseCd_A1)) {

                cMsg.A.no(i).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Item Number" });
                ret = false;

            } else {

                // check for original item
                Map<String, Object> item = NPAL1410CommonLogic.getItemInfo(cMsg.A.no(i).mdseCd_A1.getValue(), glblCmpyCd);

                if (item == null) {

                    cMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {"Item" });
                    ret = false;

                } else {

                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdseDescShortTxt_A1, (String) item.get("MDSE_DESC_SHORT_TXT"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(NPAL1410CommonLogic.getSMsgIndexFromCMsg(cMsg, i)).mdseDescShortTxt_A1, (String) item.get("MDSE_DESC_SHORT_TXT"));
                }
            }

            // check for convert item
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).rmnfToCmptMdseCd_A1)) {

                Map<String, Object> item = NPAL1410CommonLogic.getItemInfo(cMsg.A.no(i).rmnfToCmptMdseCd_A1.getValue(), glblCmpyCd);

                if (item == null) {

                    cMsg.A.no(i).rmnfToCmptMdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {"Item" });
                    ret = false;

                } else {

                    if ((ZYPConstant.FLG_ON_Y.equals((String) item.get("SHPG_SER_TAKE_FLG"))) && (!ZYPCommonFunc.hasValue(cMsg.A.no(i).rmnfToCmptSerNum_A1))) {

                        cMsg.A.no(i).rmnfToCmptSerNum_A1.setErrorInfo(1, NPAM1435E);
                        ret = false;
                    }

                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdseDescShortTxt_A2, (String) item.get("MDSE_DESC_SHORT_TXT"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(NPAL1410CommonLogic.getSMsgIndexFromCMsg(cMsg, i)).mdseDescShortTxt_A2, (String) item.get("MDSE_DESC_SHORT_TXT"));
                }
            }

            // check added config item
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).addConfigFlg_A1.getValue())) {

                // QC#22178 Modify. add serial item check.
                if (NPAL1410CommonLogic.isSerialTakeItem(glblCmpyCd, cMsg.A.no(i).mdseCd_A1.getValue())) {

                    Map<String, Object> item = NPAL1410CommonLogic.getSerial(cMsg.A.no(i).serNum_A1.getValue(), cMsg.A.no(i).mdseCd_A1.getValue(), glblCmpyCd);

                    if (item == null) {

                        cMsg.A.no(i).serNum_A1.setErrorInfo(1, NPAM0224E, new String[] {"Serial#" });
                        ret = false;

                    } else {

                        if (SVC_MACH_TP.MACHINE.equals((String) item.get("SVC_MACH_TP_CD"))) {

                            cMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPAM1547E);
                            ret = false;

                        } else if (ZYPCommonFunc.hasValue((BigDecimal) item.get("SVC_CONFIG_MSTR_PK"))) {

                            cMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPAM1548E);
                            ret = false;
                        }

                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).svcMachMstrPk_AH, (BigDecimal) item.get("SVC_MACH_MSTR_PK"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(NPAL1410CommonLogic.getSMsgIndexFromCMsg(cMsg, i)).svcMachMstrPk_AH, (BigDecimal) item.get("SVC_MACH_MSTR_PK"));
                        // QC#22836
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).stkStsCd_A1, (String) item.get("STK_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(NPAL1410CommonLogic.getSMsgIndexFromCMsg(cMsg, i)).stkStsCd_A1, (String) item.get("STK_STS_CD"));
                    }
                }

                // QC#22178 Modify. Add convert Item.
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).rmnfToCmptMdseCd_A1)) {

                    Map<String, Object> item = NPAL1410CommonLogic.getItemInfo(cMsg.A.no(i).rmnfToCmptMdseCd_A1.getValue(), glblCmpyCd);

                    if (item == null) {

                        cMsg.A.no(i).rmnfToCmptMdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {"Item" });
                        ret = false;

                    } else {

                        if ((ZYPConstant.FLG_ON_Y.equals((String) item.get("SHPG_SER_TAKE_FLG"))) && (!ZYPCommonFunc.hasValue(cMsg.A.no(i).rmnfToCmptSerNum_A1))) {

                            cMsg.A.no(i).rmnfToCmptSerNum_A1.setErrorInfo(1, NPAM1435E);
                            ret = false;
                        }

                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdseDescShortTxt_A2, (String) item.get("MDSE_DESC_SHORT_TXT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(NPAL1410CommonLogic.getSMsgIndexFromCMsg(cMsg, i)).mdseDescShortTxt_A2, (String) item.get("MDSE_DESC_SHORT_TXT"));
                    }
                }
            }

            //QC#18675 ADD START
            // QC#22836 check Stock status and Invty Location
            Map<String, Object> item = NPAL1410CommonLogic.getSerial(cMsg.A.no(i).serNum_A1.getValue(), cMsg.A.no(i).mdseCd_A1.getValue(), glblCmpyCd);
            if (item != null) {
                String stkStsCd = (String) item.get("STK_STS_CD");
                if (!ZYPCommonFunc.hasValue(stkStsCd) || !stkStsCd.equals(cMsg.A.no(i).stkStsCd_A1.getValue())) {
                    cMsg.A.no(i).stkStsCd_A1.setErrorInfo(1, NPAM0020E, new String[]{"Service Machine Master"});
                    ret = false;
                }
                String invtyLocCd = (String) item.get("CUR_LOC_NUM");
                if (!ZYPCommonFunc.hasValue(invtyLocCd) || !invtyLocCd.equals(cMsg.rmnfRtlWhCd_H1.getValue() + cMsg.A.no(i).srcRtlSwhCd_A1.getValue())) {
                    cMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, NPAM0020E, new String[]{"Service Machine Master"});
                    ret = false;
                }
            } else {
                // Non Serial Config Check
                if (ZYPCommonFunc.hasValue(sMsg.svcConfigMstrPk_A1) && ZYPCommonFunc.hasValue(cMsg.A.no(i).svcMachMstrPk_AH)) {
                    Map<String, Object> ssmParam = new HashMap<String, Object>();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("svcConfigMstrPk", sMsg.svcConfigMstrPk_A1.getValue());
                    ssmParam.put("svcMachMstrPk", cMsg.A.no(i).svcMachMstrPk_AH.getValue());
                    S21SsmEZDResult result = NPAL1410Query.getInstance().searchConfigration(ssmParam);
                    if (!result.isCodeNormal()) {
                        ret = false;
                    }
                    List<Map> resultMap = (List<Map>) result.getResultObject();
                    Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);
                    String stkStsCd = (String) recode.get("STK_STS_CD");
                    if (!ZYPCommonFunc.hasValue(stkStsCd) || !stkStsCd.equals(cMsg.A.no(i).stkStsCd_A1.getValue())) {
                        cMsg.A.no(i).stkStsCd_A1.setErrorInfo(1, NPAM0020E, new String[]{"Service Machine Master"});
                        ret = false;
                    }
                    String invtyLocCd = (String) recode.get("CUR_LOC_NUM");
                    if (!ZYPCommonFunc.hasValue(invtyLocCd) || !invtyLocCd.equals(cMsg.rmnfRtlWhCd_H1.getValue() + cMsg.A.no(i).srcRtlSwhCd_A1.getValue())) {
                        cMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, NPAM0020E, new String[]{"Service Machine Master"});
                        ret = false;
                    }
                }
            }
            //QC#18675 ADD END

        }

        return ret;
    }

    /**
     * <pre>
     * inputCheckPartsTabStep1
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean inputCheckPartsTabStep1(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        boolean ret = true;

        // for Standard Parts
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            Map<String, Object> item = NPAL1410CommonLogic.getItemInfo(sMsg.B.no(i).mdseCd_B1.getValue(), glblCmpyCd);

            if (item == null) {

                cMsg.B.no(i).mdseCd_B1.setErrorInfo(1, NPAM0076E, new String[] {"Item Number" });
                ret = false;

            } else {

                // QC#15057
                if (!NPAL1410CommonLogic.chkMdseIntangible(sMsg.B.no(i).mdseCd_B1.getValue(), glblCmpyCd)) {

                    cMsg.B.no(i).mdseCd_B1.setErrorInfo(1, NPAM1584E);
                    ret = false;

                } else {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mdseDescShortTxt_B1, (String) item.get("MDSE_DESC_SHORT_TXT"));
                }

            }

        }

        // for Non Standard Parts
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            Map<String, Object> item = NPAL1410CommonLogic.getItemInfo(sMsg.C.no(i).mdseCd_C1.getValue(), glblCmpyCd);

            if (item == null) {

                cMsg.C.no(i).mdseCd_C1.setErrorInfo(1, NPAM0076E, new String[] {"Item Number" });
                ret = false;

            } else {

                // QC#15057
                if (!NPAL1410CommonLogic.chkMdseIntangible(sMsg.C.no(i).mdseCd_C1.getValue(), glblCmpyCd)) {

                    cMsg.C.no(i).mdseCd_C1.setErrorInfo(1, NPAM1584E);
                    ret = false;

                // START 2019/12/05 T.Ogura [QC#54842,ADD]
                } else if (!NPAL1410CommonLogic.chkIbTrackableItem(sMsg.C.no(i).mdseCd_C1.getValue(), glblCmpyCd)) {

                    cMsg.C.no(i).mdseCd_C1.setErrorInfo(1, NPAM1647E);
                    ret = false;

                // END   2019/12/05 T.Ogura [QC#54842,ADD]
                } else {

                    ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).mdseDescShortTxt_C1, (String) item.get("MDSE_DESC_SHORT_TXT"));
                }

            }

        }

        return ret;
    }

    /**
     * <pre>
     * inputCheckPartsTabStep2
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean inputCheckPartsTabStep2(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        Map<String, String> swhList = NPAL1410CommonLogic.getRtlSwhList(sMsg.rmnfRtlWhCd_H1.getValue(), glblCmpyCd);

        // for Standard Parts
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            String temp = checkEnableWH(sMsg.rmnfRtlWhCd_H1.getValue() + sMsg.B.no(i).srcRtlSwhCd_BH.getValue(), APP_KEY_ID_PARTS, glblCmpyCd);

            if (temp != null) {

                cMsg.setMessageInfo(temp);
                return false;
            }

            if (!swhList.containsKey(sMsg.B.no(i).srcRtlSwhCd_BH.getValue())) {

                cMsg.rmnfRtlWhCd_H1.setErrorInfo(1, NPAM0076E, new String[] {"Warehouse" });
                return false;

            } else {

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcInvtyLocCd_BH, swhList.get(sMsg.B.no(i).srcRtlSwhCd_BH.getValue()));
            }
        }

        // for Non Standard Parts
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            String temp = checkEnableWH(sMsg.rmnfRtlWhCd_H1.getValue() + sMsg.C.no(i).srcRtlSwhCd_CH.getValue(), APP_KEY_ID_PARTS, glblCmpyCd);

            if (temp != null) {

                cMsg.setMessageInfo(temp);
                return false;
            }

            if (!swhList.containsKey(sMsg.C.no(i).srcRtlSwhCd_CH.getValue())) {

                cMsg.rmnfRtlWhCd_H1.setErrorInfo(1, NPAM0076E, new String[] {"Warehouse" });
                return false;

            } else {

                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).srcInvtyLocCd_CH, swhList.get(sMsg.C.no(i).srcRtlSwhCd_CH.getValue()));
            }
        }

        return true;
    }

    /**
     * <pre>
     * isAddComponent
     * </pre>
     * @param sMsg NPAL1410SMsg
     * @return boolean
     */
    private boolean isAddComponent(NPAL1410SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).addConfigFlg_A1.getValue())) {

                return true;
            }
        }

        return false;
    }

    /**
     * <pre>
     * createProcessForSaveStatus
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean createProcessForSaveStatus(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd, String slsDt) {

        String temp = null;

        // check Locator

        temp = NPAL1410CommonLogic.findSameRemanOrder(sMsg.rmnfRtlWhCd_H1.getValue(), sMsg.whLoctrCd_H1.getValue(), sMsg.rmnfOrdNum_BK.getValue(), glblCmpyCd);

        if (temp != null) {
            // QC#14009
            cMsg.whLoctrCd_H1.setErrorInfo(1, NPAM1533E, new String[] {temp });
            return false;
        }

        // check config serial
        Map<String, String> returnCd = new HashMap<String, String>();

        if (!serialCheckConfigItem(cMsg, sMsg, returnCd, glblCmpyCd)) {

            cMsg.xxDplyTab.setValue(TAB_CONF);
            return false;
        }

        // create config ID
        if ((!ZYPCommonFunc.hasValue(sMsg.svcConfigMstrPk_A1)) && isAddComponent(sMsg)) {

            temp = updateRemanOrderConfig(sMsg, glblCmpyCd);

            if (temp != null) {

                cMsg.setMessageInfo(temp);
                return false;
            }
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rtlWhCd", sMsg.rmnfRtlWhCd_H1.getValue());
        ssmParam.put("prtyLocFlg", ZYPConstant.FLG_ON_Y);
        S21SsmEZDResult result = NPAL1410Query.getInstance().getPriorityInventoryCd(ssmParam);
        String priorityInvtyCd = null;
        if(result.isCodeNormal()){
            priorityInvtyCd = (String) result.getResultObject();
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // Inventory Allocation
            if (!allocationNWZC1070ForInventory(cMsg, sMsg, sMsg.A.no(i), glblCmpyCd, slsDt, priorityInvtyCd)) {

                return false;
            }

            // Shipping Plan Update
            if (!shippingPlanUpdateNWZC0030ForInventory(cMsg, sMsg, sMsg.A.no(i), glblCmpyCd)) {

                return false;
            }
        }

        // get Shipping Plan Number list Map<TRX_LINE_NUM,
        // SHPG_PLN_NUM>
        Map<String, String> spMap = NPAL1410CommonLogic.getShippingPlan(TRX_SRC_TP.REMANUFACTURING, sMsg.rmnfOrdNum_BK.getValue(), glblCmpyCd);

        // SO number Map<Shipping Plan Number, SO number>
        Map<String, String> soNumMap = new HashMap<String, String>();

        // SO Create(Reman Item Change)
        if (!createSONLZC2050(cMsg, SCE_ORD_TP.REMAN_ITEM_CHANGE, spMap, soNumMap, glblCmpyCd)) {

            return false;
        }

        // Machine Master Allocation Mod QC#22836
        if (ZYPCommonFunc.hasValue(sMsg.svcConfigMstrPk_A1)) {
        	Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", glblCmpyCd);
            param.put("svcConfigMstrPk", sMsg.svcConfigMstrPk_A1.getValue());
            S21SsmEZDResult confResult = NPAL1410Query.getInstance().searchConfigration(param);

            if (confResult.isCodeNormal()) {
                List<Map> resultList = (List<Map>) confResult.getResultObject();
                for (int i = 0; i < resultList.size(); i++) {

                    if (!updateMachineMasterNSZC0010(cMsg, sMsg.A.no(i).svcMachMstrPk_AH.getValue(), sMsg.rmnfOrdNum_BK.getValue(), sMsg.A.no(i).rmnfOrdDtlLineNum_AH.getValue(), glblCmpyCd, slsDt, sMsg.svcConfigMstrPk_A1.getValue())) {

                        return false;
                    }
                }
            } else {
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                    if (NLZC403001Constant.RETURN_CODE_NA.equals(returnCd.get(String.valueOf(i))) || NLZC403001Constant.RETURN_CODE_NORMAL.equals(returnCd.get(String.valueOf(i)))) {

                        if (!updateMachineMasterNSZC0010(cMsg, sMsg.A.no(i).svcMachMstrPk_AH.getValue(), sMsg.rmnfOrdNum_BK.getValue(), sMsg.A.no(i).rmnfOrdDtlLineNum_AH.getValue(), glblCmpyCd, slsDt, null)) {

                            return false;
                        }
                    }
                }
            }

        } else {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                if (NLZC403001Constant.RETURN_CODE_NA.equals(returnCd.get(String.valueOf(i))) || NLZC403001Constant.RETURN_CODE_NORMAL.equals(returnCd.get(String.valueOf(i)))) {

                    if (!updateMachineMasterNSZC0010(cMsg, sMsg.A.no(i).svcMachMstrPk_AH.getValue(), sMsg.rmnfOrdNum_BK.getValue(), sMsg.A.no(i).rmnfOrdDtlLineNum_AH.getValue(), glblCmpyCd, slsDt, null)) {

                        return false;
                    }
                }
            }
        }

        // create RWS
        List<NPAL1410_ASMsg> delOnList = new ArrayList<NPAL1410_ASMsg>();
        List<NPAL1410_ASMsg> delOffList = new ArrayList<NPAL1410_ASMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).rmvConfigFlg_A1.getValue())) {

                delOnList.add(sMsg.A.no(i));

            } else {

                delOffList.add(sMsg.A.no(i));
            }
        }

        boolean doubleMode = ((0 < delOnList.size()) && (0 < delOffList.size()));

        if (0 < delOnList.size()) {

            if (!createRWS(cMsg, sMsg, delOnList, true, false, glblCmpyCd, slsDt, soNumMap)) {

                return false;
            }
        }

        if (0 < delOffList.size()) {

            if (!createRWS(cMsg, sMsg, delOffList, false, doubleMode, glblCmpyCd, slsDt, soNumMap)) {

                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * createRWS
     * </pre>
     * @param cMsg NPAL1410SMsg
     * @param sMsg NPAL1410SMsg
     * @param dtlList List<NPAL1410_ASMsg>
     * @param isDeleteFlg boolean
     * @param isDouble boolean
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean createRWS(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, List<NPAL1410_ASMsg> dtlList, boolean isDeleteFlg, boolean isDouble, String glblCmpyCd, String slsDt, Map<String, String> soNumMap) {

        String rwsRefNum = "";

        List<String> soNumList = new ArrayList<String>();

        for (Map.Entry<String, String> e : soNumMap.entrySet()) {

            if (!soNumList.contains(e.getValue())) {

                rwsRefNum = e.getValue();

                break;
            }

        }

        if (isDouble) {

            rwsRefNum = rwsRefNum + RWS_REF_NUM_2;
        }

        // PO Receiving
        NLZC201001PMsg rcvPO = receivingPONLZC2010ForCreate(sMsg, dtlList, isDeleteFlg, rwsRefNum, glblCmpyCd, slsDt);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(rcvPO))) {

            return false;
        }

        // call RWS API
        NLZC200001PMsg rws = rwsNLZC2000(rcvPO.poRcvNum.getValue(), glblCmpyCd);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(rws))) {

            return false;
        }

        for (int i = 0; i < rws.RWSNumList.getValidCount(); i++) {

            // call RWS Serial API
            NLZC304001PMsg pMsg = new NLZC304001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rws.RWSNumList.no(i).rwsNum.getValue());

            // get RWS Line number list
            Map<String, String> rwsDtlList = getRwsSerialDtlLineNumList(rws.RWSNumList.no(i).rwsNum.getValue(), glblCmpyCd);
            int index = 0;

            for (NPAL1410_ASMsg line : dtlList) {
                // QC#22178 Update condition
                if (rwsDtlList.containsKey(line.rmnfOrdDtlLineNum_AH.getValue()) ) {

                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(index).rwsDtlLineNum, rwsDtlList.get(line.rmnfOrdDtlLineNum_AH.getValue()));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(index).serNum, line.rmnfToCmptSerNum_A1);
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(index).mdseCd, line.rmnfToCmptMdseCd_A1);
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(index).putAwayChkStsCd, PUT_AWAY_CHK_STS.NO_NEED);
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(index).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                    pMsg.SerialList.setValidCount(++index);
                }
            }

            if (0 < index) {

                // exec API
                new NLZC304001().execute(pMsg, ONBATCH_TYPE.ONLINE);

                if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * <pre>
     * getRwsDtlLineNumList
     * </pre>
     * QC#22178 Modify Method.
     * @param rwsNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    private Map<String, String> getRwsSerialDtlLineNumList(String rwsNum, String glblCmpyCd) {

        Map<String, String> ret = new HashMap<String, String>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rwsNum", rwsNum);

        // Execute
        S21SsmEZDResult result = NPAL1410Query.getInstance().getRwsDtlLineNumList(ssmParam);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

            for (Map<String, Object> resultMap : resultMapList) {
                if (ZYPConstant.FLG_ON_Y.equals((String) resultMap.get("SER_NUM_TAKE_FLG"))) {
                    ret.put((String) resultMap.get("PO_RCV_TRX_LINE_NUM"), (String) resultMap.get("RWS_DTL_LINE_NUM"));
                }
            }
        }

        return ret;
    }

    /**
     * <pre>
     * createProcess
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean createProcess(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

//        if (!RMNF_ORD_STS.COMPLETED.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {

            if (RMNF_ORD_STS.SAVED.equals(sMsg.rmnfOrdStsCd_HH.getValue()) || RMNF_ORD_STS.SO_CANCELLED.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {

                // order status = saved
                if (!createProcessForSaveStatus(cMsg, sMsg, glblCmpyCd, slsDt)) {

                    return false;
                }
            }
 //       }

        // get reman parts
        Map<String, RMNF_PRT_REQTMsg> existPartsMap = new HashMap<String, RMNF_PRT_REQTMsg>();
        Map<String, RMNF_PRT_REQTMsg> notExistPartsMap = new HashMap<String, RMNF_PRT_REQTMsg>();

        S21SsmEZDResult result = NPAL1410CommonLogic.getRemanParts(sMsg.rmnfOrdNum_BK.getValue(), glblCmpyCd);
        List<String> mapKeys = new ArrayList<String>();
        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

            for (Map<String, Object> resultMap : resultMapList) {

                boolean extFlas = false;
                RMNF_PRT_REQTMsg tMsg = new RMNF_PRT_REQTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqPk, (BigDecimal) resultMap.get("RMNF_PRT_REQ_PK"));
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfOrdNum, (String) resultMap.get("RMNF_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtReqLineNum, (String) resultMap.get("RMNF_PRT_REQ_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, (String) resultMap.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(tMsg.prtRqstQty, (BigDecimal) resultMap.get("PRT_RQST_QTY"));
                ZYPEZDItemValueSetter.setValue(tMsg.procrTpCd, (String) resultMap.get("PROCR_TP_CD"));
                ZYPEZDItemValueSetter.setValue(tMsg.srcRtlWhCd, (String) resultMap.get("SRC_RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(tMsg.srcRtlSwhCd, (String) resultMap.get("SRC_RTL_SWH_CD"));
                ZYPEZDItemValueSetter.setValue(tMsg.srcInvtyLocCd, (String) resultMap.get("SRC_INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(tMsg.soNum, (String) resultMap.get("SO_NUM"));
                ZYPEZDItemValueSetter.setValue(tMsg.prchReqNum, (String) resultMap.get("PRCH_REQ_NUM"));
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfStdPrtFlg, (String) resultMap.get("RMNF_STD_PRT_FLG"));
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfPrtRqstProcCd, (String) resultMap.get("RMNF_PRT_RQST_PROC_CD"));
                ZYPEZDItemValueSetter.setValue(tMsg.ezUpTime, (String) resultMap.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(tMsg.ezUpTimeZone, (String) resultMap.get("EZUPTIMEZONE"));

                if (((BigDecimal) resultMap.get("INVTY_AVAL_QTY")).compareTo((BigDecimal) resultMap.get("PRT_RQST_QTY")) >= 0) {

                    extFlas = true;
                    existPartsMap.put(tMsg.rmnfPrtReqLineNum.getValue(), tMsg);
                    mapKeys.add(tMsg.rmnfPrtReqLineNum.getValue());

                } else if (ZYPCommonFunc.hasValue((String) resultMap.get("PRCH_REQ_NUM"))) {

                    // skip . PR was already created
                    extFlas = true;
                }

                if (!extFlas) {
                    notExistPartsMap.put(tMsg.rmnfPrtReqLineNum.getValue(), tMsg);
                    mapKeys.add(tMsg.rmnfPrtReqLineNum.getValue());
                }
            }
        }

        if (0 < existPartsMap.size()) {

            if (!createProcessForExistParts(cMsg, sMsg, existPartsMap, glblCmpyCd, slsDt, mapKeys)) {

                return false;
            }
        }

        /** QC#17895 T.Tokutomi 2017/03/08 : Delete createProcessForNotExistParts method. **/

        // Reman order status update for InProcess
        if (RMNF_ORD_STS.SAVED.equals(sMsg.rmnfOrdStsCd_HH.getValue()) || RMNF_ORD_STS.SO_CANCELLED.equals(sMsg.rmnfOrdStsCd_HH.getValue())) {

            if (!updateRemanOrderSts(cMsg, sMsg, RMNF_ORD_STS.IN_PROCESS, glblCmpyCd)) {

                return false;
            }
        }

        return true;
    }

    /**
     * <pre>
     * createProcessForExistParts
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param reqMap Map<String, RMNF_PRT_REQTMsg>
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean createProcessForExistParts(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, Map<String, RMNF_PRT_REQTMsg> reqMap, String glblCmpyCd, String slsDt, List<String> mapKeys) {

        for (String mapKey : mapKeys) {

            if(!reqMap.containsKey(mapKey)){
                //not Allocate Item Key.
                continue;
            }

            RMNF_PRT_REQTMsg tmsg = reqMap.get(mapKey);
            // Inventory Allocation
            if (!allocationNWZC1070ForParts(cMsg, tmsg, sMsg.whLoctrCd_H1.getValue(), glblCmpyCd, slsDt)) {

                return false;
            }

            // Shipping Plan Update
            if (!shippingPlanUpdateNWZC0030ForParts(cMsg, tmsg, glblCmpyCd)) {

                return false;
            }
        }

        // get Shipping Plan Number
        // Map<RMNF_PRT_REQ_LINE_NUM,SHPG_PLN_NUM>
        Map<String, String> spMap = NPAL1410CommonLogic.getShippingPlanForParts(sMsg.rmnfOrdNum_BK.getValue(), TRX_SRC_TP.REMAN_PARTS_LOCATOR_TRANSFER, glblCmpyCd, ZYPConstant.FLG_ON_Y);

        // SO number Map<Shipping Plan Number, SO number>
        Map<String, String> soNumMap = new HashMap<String, String>();

        // SO Create(Reman Item Change)
        if (!createSONLZC2050(cMsg, SCE_ORD_TP.REMAN_LOCATOR_TRANSFER, spMap, soNumMap, glblCmpyCd)) {

            return false;
        }

        // call RWS API
        List<String> soNumList = new ArrayList<String>();

        for (Map.Entry<String, String> e : soNumMap.entrySet()) {

            if (!soNumList.contains(e.getValue())) {

                NLZC200001PMsg rws = rwsNLZC2000ForSO(e.getValue(), glblCmpyCd);

                if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(rws))) {

                    return false;
                }

                soNumList.add(e.getValue());
            }
        }

        // update RMNF_PRT_REQ
        String errMsgId = updateRemanPartsRequest(reqMap, spMap, soNumMap);

        if (errMsgId != null) {

            cMsg.setMessageInfo(errMsgId);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * updateRemanPartsRequest
     * </pre>
     * @param reqMap Map<String, RMNF_PRT_REQTMsg>
     * @param spMap Map<String, String>
     * @param soNumMap Map<String, String>
     * @return String
     */
    private String updateRemanPartsRequest(Map<String, RMNF_PRT_REQTMsg> reqMap, Map<String, String> spMap, Map<String, String> soNumMap) {

        for (Map.Entry<String, String> e : spMap.entrySet()) {

            RMNF_PRT_REQTMsg newMsg = (RMNF_PRT_REQTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(reqMap.get(e.getKey()));

            // status Completed
            ZYPEZDItemValueSetter.setValue(newMsg.soNum, soNumMap.get(e.getValue()));
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfPrtRqstProcCd, ZYPConstant.FLG_ON_1);
            EZDTBLAccessor.update(newMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newMsg.getReturnCode())) {

                return NPAM1171E;
            }
        }

        return null;
    }

    /**
     * updateRemanOrderSts
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param rmnfOrdStsCd String
     * @param glblCmpyCd String
     * @return boolean true : OK, false : NG
     */
    private boolean updateRemanOrderSts(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String rmnfOrdStsCd, String glblCmpyCd) {

        RMNF_ORDTMsg oldMsg = new RMNF_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(oldMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(oldMsg.rmnfOrdNum, sMsg.rmnfOrdNum_BK.getValue());
        RMNF_ORDTMsg newMsg = (RMNF_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(oldMsg);

        // check date
        if (!isSameTimeStamp(sMsg, newMsg.ezUpTime.getValue(), newMsg.ezUpTimeZone.getValue())) {

            cMsg.setMessageInfo(NPAM0006E);
            return false;
        }

        // status Completed.
        ZYPEZDItemValueSetter.setValue(newMsg.rmnfOrdStsCd, rmnfOrdStsCd);

        if (RMNF_ORD_STS.COMPLETED.equals(rmnfOrdStsCd)) {

            // RMNF_END_DT
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfEndDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

            // RMNF_ORD_CMNT_TXT
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfOrdCmntTxt, sMsg.rmnfOrdCmntTxt_H1.getValue());

            // update Reman Cost.
            // RMNF_PRT_USG_COST_AMT
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfPrtUsgCostAmt, NPAL1410CommonLogic.getRmnfPrtUsgCostAmt(glblCmpyCd, sMsg.rmnfOrdNum_BK.getValue(), null));

            // RMNF_TOT_LBOR_COST_AMT
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfTotLborCostAmt, NPAL1410CommonLogic.getRmnTotLborCostAmt(glblCmpyCd, sMsg.rmnfOrdNum_BK.getValue(), null));

            // RMNF_MACH_COST_AMT
            NLXC001001GetInventoryItemCostBean parm = new NLXC001001GetInventoryItemCostBean();

            parm.setGlblCmpyCd(glblCmpyCd);
            parm.setInvtyLocCd(NPAL1410CommonLogic.getInvtyLocCd(sMsg));
            parm.setMdseCd(NPAL1410CommonLogic.getRmnfToCmptMdseCd(newMsg));
            parm.setQty(BigDecimal.ONE);

            parm = NLXC001001GetInventoryItemCost.getInventoryItemCost(parm);

            ZYPEZDItemValueSetter.setValue(newMsg.rmnfMachCostAmt, parm.getTotPrcAmt());

            // RMNF_TOT_COST_AMT
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfTotCostAmt, NPAL1410CommonLogic.getRmnfTotCostAmt(newMsg));
        }

        EZDTBLAccessor.update(newMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newMsg.getReturnCode())) {

            cMsg.setMessageInfo(NPAM1171E);
            return false;
        }

        // re search
        newMsg = (RMNF_ORDTMsg) EZDTBLAccessor.findByKey(newMsg);

        if (newMsg != null) {

            ZYPEZDItemValueSetter.setValue(sMsg.ezUpTime_HH, newMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(sMsg.ezUpTimeZone_HH, newMsg.ezUpTimeZone);
        }

        return true;
    }

    /**
     * updateRemanOrderConfig
     * @param sMsg NPAL1410SMsg
     * @param configMstrPk BigDecimal
     * @param glblCmpyCd String
     * @return String
     */
    private String updateRemanOrderConfig(NPAL1410SMsg sMsg, String glblCmpyCd) {

        BigDecimal configMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_CONFIG_MSTR_SQ);

        RMNF_ORDTMsg oldMsg = new RMNF_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(oldMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(oldMsg.rmnfOrdNum, sMsg.rmnfOrdNum_BK.getValue());
        RMNF_ORDTMsg newMsg = (RMNF_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(oldMsg);

        // check date
        if (!isSameTimeStamp(sMsg, newMsg.ezUpTime.getValue(), newMsg.ezUpTimeZone.getValue())) {

            return NPAM0006E;
        }

        // status Completed.
        ZYPEZDItemValueSetter.setValue(newMsg.svcConfigMstrPk, configMstrPk);
        EZDTBLAccessor.update(newMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newMsg.getReturnCode())) {

            return NPAM1171E;
        }

        // re search
        newMsg = (RMNF_ORDTMsg) EZDTBLAccessor.findByKey(newMsg);

        if (newMsg != null) {

            ZYPEZDItemValueSetter.setValue(sMsg.ezUpTime_HH, newMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(sMsg.ezUpTimeZone_HH, newMsg.ezUpTimeZone);
            ZYPEZDItemValueSetter.setValue(sMsg.svcConfigMstrPk_A1, configMstrPk);
        }

        return null;
    }

    /**
     * updateRemanPartsRequestForPOReq
     * @param reqMap Map<String, RMNF_PRT_REQTMsg>
     * @param prchReqNum String
     * @return String
     */
    private String updateRemanPartsRequestForPOReq(Map<String, RMNF_PRT_REQTMsg> reqMap, String prchReqNum) {

        for (Map.Entry<String, RMNF_PRT_REQTMsg> e : reqMap.entrySet()) {

            RMNF_PRT_REQTMsg newMsg = (RMNF_PRT_REQTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(e.getValue());

            // status Completed.
            ZYPEZDItemValueSetter.setValue(newMsg.prchReqNum, prchReqNum);
            ZYPEZDItemValueSetter.setValue(newMsg.procrTpCd, PROCR_TP.SUPPLIER);
            ZYPEZDItemValueSetter.setValue(newMsg.rmnfPrtRqstProcCd, ZYPConstant.FLG_ON_1);
            EZDTBLAccessor.update(newMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newMsg.getReturnCode())) {

                return NPAM1171E;
            }
        }

        return null;
    }

    /** QC#17895 T.Tokutomi 2017/03/08 : Delete createProcessForNotExistParts method. **/

    /**
     * <pre>
     * serialCheckConfigItem
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param retReturnCd Map<String, String>
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean serialCheckConfigItem(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, Map<String, String> retReturnCd, String glblCmpyCd) {

        boolean ret = true;
        cMsg.xxPageShowFromNum.setValue(1);

        while (true) {

            NPAL1410CommonLogic.setPagePos(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);
            int cMsgCount = 0;

            for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }

            cMsg.A.setValidCount(cMsgCount);

            if (!serialCheckConfigItemOfPage(cMsg, sMsg, retReturnCd, glblCmpyCd)) {

                ret = false;
                break;
            }

            if (cMsg.xxPageShowToNum.getValue().compareTo(cMsg.xxPageShowOfNum.getValue()) == 0) {

                break;
            }

            cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length());
        }

        return ret;
    }

    /**
     * <pre>
     * serialCheckConfigItemOfPage
     * </pre>
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param retReturnCd Map<String, String>
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean serialCheckConfigItemOfPage(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, Map<String, String> retReturnCd, String glblCmpyCd) {

        boolean ret = true;
        NLZC403001PMsg pMsg = null;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            // check for original item
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).serNum_A1)) {

                // QC#22836
                pMsg = checkSerialNLZC4030(NLZC403001Constant.MODE_OUTBOUND, cMsg.A.no(i).mdseCd_A1.getValue(), cMsg.A.no(i).serNum_A1.getValue(), cMsg.rmnfRtlWhCd_H1.getValue() + cMsg.A.no(i).srcRtlSwhCd_A1.getValue(), glblCmpyCd);
                retReturnCd.put(String.valueOf(NPAL1410CommonLogic.getSMsgIndexFromCMsg(cMsg, i)), pMsg.xxRsltStsCd.getValue());

                if ((NLZC403001Constant.RETURN_CODE_ERROR.equals(pMsg.xxRsltStsCd.getValue())) && (0 < pMsg.xxMsgIdList.getValidCount())) {

                    cMsg.A.no(i).serNum_A1.setErrorInfo(1, pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    ret = false;
                }

                // check for convert item
                if ((!cMsg.A.no(i).mdseCd_A1.getValue().equals(cMsg.A.no(i).rmnfToCmptMdseCd_A1.getValue())) || (!cMsg.A.no(i).serNum_A1.getValue().equals(cMsg.A.no(i).rmnfToCmptSerNum_A1.getValue()))) {

                    pMsg = checkSerialNLZC4030(NLZC403001Constant.MODE_INBOUND, cMsg.A.no(i).rmnfToCmptMdseCd_A1.getValue(), cMsg.A.no(i).rmnfToCmptSerNum_A1.getValue(), cMsg.rmnfInvtyLocCd_HH.getValue(), glblCmpyCd);

                    if ((NLZC403001Constant.RETURN_CODE_ERROR.equals(pMsg.xxRsltStsCd.getValue())) && (0 < pMsg.xxMsgIdList.getValidCount())) {

                        cMsg.A.no(i).rmnfToCmptSerNum_A1.setErrorInfo(1, pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                        ret = false;
                    }
                }
            }
        }

        return ret;
    }

    /**
     * checkEnableWH
     * @param invtyLocCd String
     * @param bizAppKeyId String
     * @param glblCmpyCd String
     * @return String error message
     */
    private String checkEnableWH(String invtyLocCd, String bizAppKeyId, String glblCmpyCd) {

        NMXC100001EnableWHData result = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, invtyLocCd, BUSINESS_APPLICATION_ID, null, ZYPConstant.FLG_ON_Y, bizAppKeyId);
        return result.getXxMsgId();
    }

    /**
     * call Serial Validation API(NLZC4030)
     * @param xxModeCd String
     * @param mdseCd String
     * @param serNum String
     * @param invtyLocCd String
     * @param glblCmpyCd String
     * @return NLZC403001PMsg
     */
    private NLZC403001PMsg checkSerialNLZC4030(String xxModeCd, String mdseCd, String serNum, String invtyLocCd, String glblCmpyCd) {

        NLZC403001PMsg pMsg = new NLZC403001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, xxModeCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, invtyLocCd);

        // exec API
        new NLZC403001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }

    /**
     * call Allocation for non CPO API(NWZC1070)
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param dtlMsg NPAL1410_ASMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean allocationNWZC1070ForInventory(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, NPAL1410_ASMsg dtlMsg, String glblCmpyCd, String slsDt, String priorityInvtyCd) {

        NWZC107001PMsg pMsg = createNWZC1070CommonParam(glblCmpyCd, slsDt);

        // QC#18675 ADD START. Update QC#22178. Update QC#22836.
        if (ZYPConstant.FLG_ON_Y.equals(dtlMsg.addConfigFlg_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, dtlMsg.stkStsCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, sMsg.rmnfRtlWhCd_H1.getValue() + dtlMsg.srcRtlSwhCd_A1.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, dtlMsg.stkStsCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, sMsg.rmnfRtlWhCd_H1.getValue() + dtlMsg.srcRtlSwhCd_A1.getValue());
        }
        // QC#18675 ADD END

        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMANUFACTURING);
        ZYPEZDItemValueSetter.setValue(pMsg.trxCd, TRX.REMANUFACTURING);
        ZYPEZDItemValueSetter.setValue(pMsg.trxRsnCd, TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, sMsg.rmnfOrdNum_BK.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, dtlMsg.rmnfOrdDtlLineNum_AH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, dtlMsg.mdseCd_A1.getValue());
        
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstQty, dtlMsg.svcMachQty_AH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, sMsg.rmnfInvtyLocCd_HH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, sMsg.rmnfInvtyLocCd_HH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, sMsg.rmnfInvtyLocCd_HH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, sMsg.whLoctrCd_H1.getValue());

        // exec API
        new NWZC107001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * allocationNWZC1070ForParts
     * @param cMsg NPAL1410CMsg
     * @param dtlMsg RMNF_PRT_REQTMsg
     * @param whLoctrCd String
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean allocationNWZC1070ForParts(NPAL1410CMsg cMsg, RMNF_PRT_REQTMsg dtlMsg, String whLoctrCd, String glblCmpyCd, String slsDt) {

        NWZC107001PMsg pMsg = createNWZC1070CommonParam(glblCmpyCd, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMAN_PARTS_LOCATOR_TRANSFER);
        ZYPEZDItemValueSetter.setValue(pMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.trxRsnCd, TRX_RSN.REMAN_PARTS_TRANSFER_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, dtlMsg.rmnfOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, dtlMsg.rmnfPrtReqLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, dtlMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, dtlMsg.srcInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstQty, dtlMsg.prtRqstQty.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, dtlMsg.srcInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, dtlMsg.srcInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, dtlMsg.srcInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, whLoctrCd);

        // exec API
        new NWZC107001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * allocationNWZC1070ForCancel
     * @param cMsg NPAL1410CMsg
     * @param rmnfPrtReqMap Map<String, Object>
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean allocationNWZC1070ForCancel(NPAL1410CMsg cMsg, Map<String, Object> rmnfPrtReqMap, String glblCmpyCd, String slsDt) {

        NWZC107001PMsg pMsg = createNWZC1070CommonParam(glblCmpyCd, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMAN_PARTS_BACK_INVENTORY);
        ZYPEZDItemValueSetter.setValue(pMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.trxRsnCd, TRX_RSN.REMAN_PARTS_TRANSFER_BACK_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, (String) rmnfPrtReqMap.get("RMNF_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, (String) rmnfPrtReqMap.get("RMNF_PRT_REQ_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) rmnfPrtReqMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) rmnfPrtReqMap.get("SRC_INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_REMAN);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstQty, (BigDecimal) rmnfPrtReqMap.get("PRT_RQST_QTY"));
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, (String) rmnfPrtReqMap.get("SRC_INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, (String) rmnfPrtReqMap.get("SRC_INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) rmnfPrtReqMap.get("SRC_INVTY_LOC_CD"));

        // exec API
        new NWZC107001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * allocationNWZC1070ForPartsUsage
     * @param cMsg NPAL1410CMsg
     * @param rmnfUsgMap Map<String, Object>
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean allocationNWZC1070ForPartsUsage(NPAL1410CMsg cMsg, Map<String, Object> rmnfUsgMap, String glblCmpyCd, String slsDt) {

        NWZC107001PMsg pMsg = createNWZC1070CommonParam(glblCmpyCd, slsDt);

        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMAN_PARTS_USAGE);
        ZYPEZDItemValueSetter.setValue(pMsg.trxCd, TRX.REMANUFACTURING);
        ZYPEZDItemValueSetter.setValue(pMsg.trxRsnCd, TRX_RSN.PARTS_USAGE_FOR_REMAN);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, (String) rmnfUsgMap.get("RMNF_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, (String) rmnfUsgMap.get("RMNF_TASK_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, (String) rmnfUsgMap.get("RMNF_USG_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) rmnfUsgMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) rmnfUsgMap.get("RMNF_USG_INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_REMAN);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstQty, (BigDecimal) rmnfUsgMap.get("RMNF_PRT_QTY"));
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, (String) rmnfUsgMap.get("RMNF_USG_INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, (String) rmnfUsgMap.get("RMNF_USG_INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) rmnfUsgMap.get("RMNF_USG_INVTY_LOC_CD"));

        // exec API
        new NWZC107001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * allocationNWZC1070ForSubmitBackInventory
     * @param cMsg NPAL1410CMsg
     * @param rmnfOrdNum String
     * @param lineNum int
     * @param unUsedPartsMap Map<String, Object>
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean allocationNWZC1070ForSubmitBackInventory(NPAL1410CMsg cMsg, String rmnfOrdNum, int lineNum, Map<String, Object> unUsedPartsMap, String glblCmpyCd, String slsDt) {

        NWZC107001PMsg pMsg = createNWZC1070CommonParam(glblCmpyCd, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMAN_PARTS_BACK_INVENTORY);
        ZYPEZDItemValueSetter.setValue(pMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.trxRsnCd, TRX_RSN.REMAN_PARTS_TRANSFER_BACK_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, rmnfOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, String.format("%03d", lineNum));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) unUsedPartsMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) unUsedPartsMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_REMAN);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstQty, (BigDecimal) unUsedPartsMap.get("UNUSED_QTY"));
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, (String) unUsedPartsMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, (String) unUsedPartsMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) unUsedPartsMap.get("INVTY_LOC_CD"));

        // exec API
        new NWZC107001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * createNWZC1070CommonParam
     * @param glblCmpyCd String
     * @param slsDt String
     * @return NWZC107001PMsg
     */
    private NWZC107001PMsg createNWZC1070CommonParam(String glblCmpyCd, String slsDt) {

        NWZC107001PMsg pMsg = new NWZC107001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxSysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, REQUEST_TYPE_NEW_ALLOC);
        ZYPEZDItemValueSetter.setValue(pMsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, SHPG_SVC_LVL.BESTWAY);
        ZYPEZDItemValueSetter.setValue(pMsg.xxOrdTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(pMsg.rsdDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxUnitPrc, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.xxSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        return pMsg;
    }

    /**
     * call Shipping Plan Update API(NWZC0030)
     * @param cMsg NPAL1410CMsg
     * @param sMsg NPAL1410SMsg
     * @param dtlMsg NPAL1410_ASMsg
     * @param glblCmpyCd
     * @return boolean
     */
    private boolean shippingPlanUpdateNWZC0030ForInventory(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, NPAL1410_ASMsg dtlMsg, String glblCmpyCd) {

        NWZC003001PMsg pMsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMANUFACTURING);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, sMsg.rmnfOrdNum_BK.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, dtlMsg.rmnfOrdDtlLineNum_AH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.avalSoQty, dtlMsg.svcMachQty_AH.getValue());

        // exec API
        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        pMsgList.add(pMsg);
        new NWZC003001().execute(pMsgList, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * shippingPlanUpdateNWZC0030ForParts
     * @param cMsg NPAL1410CMsg
     * @param dtlMsg RMNF_PRT_REQTMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean shippingPlanUpdateNWZC0030ForParts(NPAL1410CMsg cMsg, RMNF_PRT_REQTMsg dtlMsg, String glblCmpyCd) {

        NWZC003001PMsg pMsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMAN_PARTS_LOCATOR_TRANSFER);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, dtlMsg.rmnfOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, dtlMsg.rmnfPrtReqLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.avalSoQty, dtlMsg.prtRqstQty.getValue());

        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        pMsgList.add(pMsg);

        // exec API
        new NWZC003001().execute(pMsgList, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * shippingPlanUpdateNWZC0030ForCancel
     * @param cMsg NPAL1410CMsg
     * @param rmnfPrtReqMap Map<String, Object>
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean shippingPlanUpdateNWZC0030ForCancel(NPAL1410CMsg cMsg, Map<String, Object> rmnfPrtReqMap, String glblCmpyCd) {

        NWZC003001PMsg pMsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMAN_PARTS_BACK_INVENTORY);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, (String) rmnfPrtReqMap.get("RMNF_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, (String) rmnfPrtReqMap.get("RMNF_PRT_REQ_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.avalSoQty, (BigDecimal) rmnfPrtReqMap.get("PRT_RQST_QTY"));

        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        pMsgList.add(pMsg);

        // exec API
        new NWZC003001().execute(pMsgList, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * shippingPlanUpdateNWZC0030ForPartsUsage
     * @param cMsg NPAL1410CMsg
     * @param rmnfUsgMap Map<String, Object>
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean shippingPlanUpdateNWZC0030ForPartsUsage(NPAL1410CMsg cMsg, Map<String, Object> rmnfUsgMap, String glblCmpyCd) {

        NWZC003001PMsg pMsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMAN_PARTS_USAGE);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, (String) rmnfUsgMap.get("RMNF_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, (String) rmnfUsgMap.get("RMNF_TASK_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, (String) rmnfUsgMap.get("RMNF_USG_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.avalSoQty, (BigDecimal) rmnfUsgMap.get("RMNF_PRT_QTY"));

        // exec API
        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        pMsgList.add(pMsg);

        new NWZC003001().execute(pMsgList, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * shippingPlanUpdateNWZC0030ForSubmitBackInventory
     * @param cMsg NPAL1410CMsg
     * @param rmnfOrdNum String
     * @param lineNum int
     * @param rqstQty BigDecimal
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean shippingPlanUpdateNWZC0030ForSubmitBackInventory(NPAL1410CMsg cMsg, String rmnfOrdNum, int lineNum, BigDecimal rqstQty, String glblCmpyCd) {

        NWZC003001PMsg pMsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMAN_PARTS_BACK_INVENTORY);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, rmnfOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, String.format("%03d", lineNum));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.avalSoQty, rqstQty);

        // exec API
        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        pMsgList.add(pMsg);
        new NWZC003001().execute(pMsgList, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * <pre>
     * call NLZC205001 API<br />
     * create SO from Shipping plan
     * </pre>
     * @param sceOrdTpCd SCE order type
     * @param shpgPlnNumMap shipping plan number -
     * Map[RMNF_PRT_REQ_LINE_NUM (unused) , Shipping plan number ]
     * @param retSONumMap return value - Map[Shipping plan number,
     * created SO number]
     * @param globalCompanyCode String
     * @return error message
     */
    private boolean createSONLZC2050(NPAL1410CMsg cMsg, String sceOrdTpCd, Map<String, String> shpgPlnNumMap, Map<String, String> retSONumMap, String glblCmpyCd) {

        if (shpgPlnNumMap.size() == 0) {

            return true;
        }

        Map<String, String> m =  new TreeMap<String, String>();
        for (Map.Entry<String, String> e : shpgPlnNumMap.entrySet()) {
            m.put(e.getKey(), e.getValue());
        }
        List<NLZC205001PMsg> pMsgList = new ArrayList<NLZC205001PMsg>();

//        for (Map.Entry<String, String> e : shpgPlnNumMap.entrySet()) {
        for (Map.Entry<String, String> e : m.entrySet()) {

            NLZC205001PMsg pMsg = new NLZC205001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, e.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC205001.MODE_NEW);
            pMsgList.add(pMsg);
        }

        // exec API
        new NLZC205001().execute(pMsgList, ONBATCH_TYPE.ONLINE);

        for (NLZC205001PMsg pMsg : pMsgList) {

            if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

                return false;
            }

            retSONumMap.put(pMsg.shpgPlnNum.getValue(), pMsg.soNum.getValue());

        }

        return true;
    }

    /**
     * updateMachineMasterNSZC0010
     * @param cMsg NPAL1410CMsg
     * @param svcMachMstrPk BigDecimal
     * @param rmnfOrdNum String
     * @param rmnfOrdDtlLineNum String
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcConfigMstrPk BigDecimal
     * @return boolean
     */
    private boolean updateMachineMasterNSZC0010(NPAL1410CMsg cMsg, BigDecimal svcMachMstrPk, String rmnfOrdNum, String rmnfOrdDtlLineNum, String glblCmpyCd, String slsDt, BigDecimal svcConfigMstrPk) {

        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, rmnfOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, rmnfOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, svcConfigMstrPk);

        // exec API
        new NSZC001001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

    /**
     * receivingPONLZC2010ForCreate
     * @param sMsg NPAL1410SMsg
     * @param dtlList List<NPAL1410_ASMsg
     * @param isDeleteFlg boolean
     * @param rwsRefNum String
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private NLZC201001PMsg receivingPONLZC2010ForCreate(NPAL1410SMsg sMsg, List<NPAL1410_ASMsg> dtlList, boolean isDeleteFlg, String rwsRefNum, String glblCmpyCd, String slsDt) {

        NLZC201001PMsg pMsg = new NLZC201001PMsg();

        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, sMsg.rmnfRtlWhCd_H1.getValue());
        tMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, SCE_ORD_TP.REMAN_ITEM_CHANGE);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, rwsRefNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvFromLocTpCd, tMsg.locTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvFromLocCd, tMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, tMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvDrctShipTpCd, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvEtaDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvTrxHdrNum, sMsg.rmnfOrdNum_BK.getValue());

        if (!isDeleteFlg) {

            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, sMsg.svcConfigMstrPk_A1);
        }

        for (int i = 0; i < dtlList.size(); i++) {

            // order info
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).mdseCd, dtlList.get(i).rmnfToCmptMdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).stkStsCd, STK_STS.GOOD);
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).poRcvQty, dtlList.get(i).svcMachQty_AH.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).poRcvTrxLineNum, dtlList.get(i).rmnfOrdDtlLineNum_AH.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).invtyLocCd, sMsg.rmnfInvtyLocCd_HH.getValue());
            // QC#22836
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).shipFromInvtyLocCd, sMsg.rmnfRtlWhCd_H1.getValue() + dtlList.get(i).srcRtlSwhCd_A1.getValue());
            pMsg.OrdInfoLIst.setValidCount(i + 1);
        }

        // exec API
        new NLZC201001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }

    /**
     * receivingPONLZC2010ForSubmit
     * @param sMsg NPAL1410SMsg
     * @param dtlList List<Object[]>
     * @param glblCmpyCd String
     * @param slsDt String
     * @return NLZC201001PMsg
     */
    private NLZC201001PMsg receivingPONLZC2010ForSubmit(NPAL1410SMsg sMsg, List<Object[]> dtlList, String glblCmpyCd, String slsDt) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, sMsg.rmnfRtlWhCd_H1.getValue());
        rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

        NLZC201001PMsg pMsg = new NLZC201001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, SCE_ORD_TP.REMAN_PARTS_USAGE);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, sMsg.rmnfOrdNum_BK.getValue() + RWS_REF_NUM_RR);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvFromLocTpCd, rtlWhTMsg.locTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvFromLocCd, rtlWhTMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, rtlWhTMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvDrctShipTpCd, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvEtaDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvTrxHdrNum, sMsg.rmnfOrdNum_BK.getValue());

        for (int i = 0; i < dtlList.size(); i++) {

            Map<String, Object> rmnfUsgMap = (Map<String, Object>) dtlList.get(i)[1];
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).mdseCd, (String) rmnfUsgMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).stkStsCd, STK_STS.WAITING_FOR_INSPECTION);
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).poRcvQty, (BigDecimal) rmnfUsgMap.get("RMNF_PRT_QTY"));
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).poRcvTrxLineNum, (String) rmnfUsgMap.get("RMNF_USG_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).invtyLocCd, (String) dtlList.get(i)[0]);
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).shipFromInvtyLocCd, (String) rmnfUsgMap.get("RMNF_USG_INVTY_LOC_CD"));
            pMsg.OrdInfoLIst.setValidCount(i + 1);
        }

        // exec API
        new NLZC201001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }

    /**
     * rwsNLZC2000
     * @param poRcvNum String
     * @param glblCmpyCd String
     * @return NLZC201001PMsg
     */
    private NLZC200001PMsg rwsNLZC2000(String poRcvNum, String glblCmpyCd) {

        NLZC200001PMsg pMsg = new NLZC200001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.inbdSrcTpCd, INBD_SRC_TP.PO_RECEIVING);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvNum, poRcvNum);

        // exec API
        new NLZC200001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }

    /**
     * rwsNLZC2000ForSO
     * @param soNum String
     * @param glblCmpyCd String
     * @return NLZC201001PMsg
     */
    private NLZC200001PMsg rwsNLZC2000ForSO(String soNum, String glblCmpyCd) {

        NLZC200001PMsg pMsg = new NLZC200001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.inbdSrcTpCd, INBD_SRC_TP.SO);
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, soNum);

        // exec API
        new NLZC200001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }

    /**
     * closeAllTask
     * @param cMsg NPAL1410CMsg
     * @param rmnfOrdNum String
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean closeAllTask(NPAL1410CMsg cMsg, String rmnfOrdNum, String glblCmpyCd, String slsDt) {

        S21SsmEZDResult result = NPAL1410CommonLogic.getAllTask(rmnfOrdNum, glblCmpyCd);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

            for (Map<String, Object> resultMap : resultMapList) {

                if (!closeTask(cMsg, rmnfOrdNum, (String) resultMap.get("RMNF_TASK_NUM"), resultMap, glblCmpyCd, slsDt)) {

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * closeTask
     * @param cMsg NPAL1410CMsg
     * @param rmnfOrdNum String
     * @param rmnfTaskNum String
     * @param taskInfo Map<String, Object>
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    private boolean closeTask(NPAL1410CMsg cMsg, String rmnfOrdNum, String rmnfTaskNum, Map<String, Object> taskInfo, String glblCmpyCd, String slsDt) {

        S21SsmEZDResult result = NPAL1410CommonLogic.getAllUsage(rmnfOrdNum, rmnfTaskNum, glblCmpyCd);
        // QC#13282
        // if (result.isCodeNormal()) {

        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) result.getResultObject();

        // QC#13282
        // if (resultMapList == null || resultMapList.isEmpty()) {
        // return true;
        // }

        // call API
        NPZC127001PMsg pMsg = new NPZC127001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfOrdNum, rmnfOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC127001Constant.MODE_COMPLETION);
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskNum, rmnfTaskNum);
        ZYPEZDItemValueSetter.setValue(pMsg.techTocCd, (String) taskInfo.get("TECH_TOC_CD"));
        // QC#13282
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskStartDt, (String) taskInfo.get("RMNF_TASK_START_DT"));
        if (ZYPCommonFunc.hasValue((String) taskInfo.get("RMNF_TASK_END_DT"))) {
            ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskEndDt, (String) taskInfo.get("RMNF_TASK_END_DT"));
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskEndDt, slsDt);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.rmnfLborAot, (BigDecimal) taskInfo.get("RMNF_LBOR_AOT"));
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfLborCmntTxt, (String) taskInfo.get("RMNF_LBOR_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.spclInstnCmntTxt, (String) taskInfo.get("SPCL_INSTN_CMNT_TXT"));

        // QC#13282
        if (resultMapList != null && !resultMapList.isEmpty()) {
            for (int i = 0; (i < resultMapList.size()) && (i < pMsg.remanUsgList.length()); i++) {

                Map<String, Object> resultMap = (Map<String, Object>) resultMapList.get(i);
                ZYPEZDItemValueSetter.setValue(pMsg.remanUsgList.no(i).mdseCd, (String) resultMap.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.remanUsgList.no(i).rmnfPrtQty, (BigDecimal) resultMap.get("RMNF_PRT_QTY"));
                ZYPEZDItemValueSetter.setValue(pMsg.remanUsgList.no(i).delFlg, ZYPConstant.FLG_OFF_N);
                pMsg.remanUsgList.setValidCount(i + 1);
            }
        }
        NPZC127001 api = new NPZC127001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!NPAL1410CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }
        // }

        return true;
    }

    /**
     * isSameTimeStamp
     * @param sMsg NPAL1410SMsg
     * @param ezUpTime String
     * @param ezUpTimeZone String
     * @return returns true if timestamps are the same.
     */
    private boolean isSameTimeStamp(NPAL1410SMsg sMsg, String ezUpTime, String ezUpTimeZone) {

        String preUpTime = sMsg.ezUpTime_HH.getValue();
        String preTimeZone = sMsg.ezUpTimeZone_HH.getValue();

        return ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, ezUpTime, ezUpTimeZone);
    }

    /**
     * isSameTimeStamp
     * @param msg StringBuilder
     * @param prNum String
     * @param whCd String
     * @param cratDt String
     * @param globalCompanyCode String
     */
    private void sendMail(StringBuilder msg, String prNum, String whCd, String cratDt, String glblCmpyCd) {

        S21Mail mail = new S21Mail(glblCmpyCd);
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_TO_GROUP_ID);
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_FROM_GROUP_ID);

        // Set address
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();

        if (toAddrList.isEmpty()) {

            return;
        }

        mail.setToAddressList(toAddrList);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

        if (template == null) {

            throw new S21AbendException(NPZM0161E);
        }

        template.setTemplateParameter(EMAIL_PARAM_PRCH_REQ_NUM, prNum);
        template.setTemplateParameter(EMAIL_PARAM_WH_CD, whCd);
        template.setTemplateParameter(EMAIL_PARAM_MESSAGE, msg.toString());
        template.setTemplateParameter(EMAIL_PARAM_DATE_REQ, ZYPDateUtil.formatEzd8ToDisp(cratDt, true));

        // Set mail subject
        mail.setSubject(template.getSubject(ML_LANG), ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }


    //QC#18675 ADD START
    private void setMdlIdByMachMstApi(NPAL1410CMsg cMsg, NPAL1410SMsg sMsg, String glblCmpyCd) {

        NSZC048001PMsg pMsg = new NSZC048001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));

        // QC#21805 Start
        boolean isFirst = true;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            // QC#21861 Modify
            String mdseCd = cMsg.A.no(i).rmnfToCmptMdseCd_A1.getValue();
            if(!ZYPCommonFunc.hasValue(cMsg.A.no(i).rmnfToCmptMdseCd_A1)){
                mdseCd = cMsg.A.no(i).mdseCd_A1.getValue();
            }
            
            
            if(ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).mainUnitLineFlg_A1.getValue())){
                ZYPEZDItemValueSetter.setValue(pMsg.prntMdseCd, mdseCd);
                isFirst = false;
            } else {
                if (isFirst) {
                    ZYPEZDItemValueSetter.setValue(pMsg.prntMdseCd, mdseCd);
                    isFirst = false;
                }
                int ix = pMsg.xxChildMdseCdList.getValidCount();
                ZYPEZDItemValueSetter.setValue(pMsg.xxChildMdseCdList.no(ix++).childMdseCd, mdseCd);
                pMsg.xxChildMdseCdList.setValidCount(ix);
            }
        }
        // QC#21805 End

        NSZC048001 smApi = new NSZC048001();
        smApi.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.mdlId)) {
            return;
        }
        MDL_NMTMsg inMsg = new MDL_NMTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.t_GlblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.t_MdlId, pMsg.mdlId);
        MDL_NMTMsg tMsg = (MDL_NMTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(sMsg.rmnfMdlId_HH, tMsg.t_MdlId.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_H1, tMsg.t_MdlNm.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.t_MdlNm_A1, tMsg.t_MdlNm.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfMdlId_HH, tMsg.t_MdlId.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_H1, tMsg.t_MdlNm.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_A1, tMsg.t_MdlNm.getValue());

    }

}
