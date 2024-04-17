/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1830;

import static business.blap.NWAL1830.constant.NWAL1830Constant.LOAN_ORD_ACTION_LOAN_SELL;
import static business.blap.NWAL1830.constant.NWAL1830Constant.MESSAGE_KIND_ERROR;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NZZM0000E;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NZZM0001W;
import static business.blap.NWAL1830.constant.NWAL1830Constant.RADIO_VAL_NEW_ORD;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1830.common.NWAL1830CommonLogic;
import business.db.RTL_WHTMsg;
import business.db.RWS_HDRTMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1830BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/30   Fujitsu         Y.Taoka         Create          N/A
 * 2017/09/11   Fujitsu         M.Ohno          Update          S21_NA#19800(L3)
 * 2020/05/14   Fujitsu         C.Hara          Update          QC#56509-1
 * 2024/03/14   CITS            J.Cho           Update          QC#63527
 *</pre>
 */
public class NWAL1830BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1830CMsg bizMsg = (NWAL1830CMsg) cMsg;

            if ("NWAL1830_INIT".equals(screenAplID)) {
                doProcess_NWAL1830_INIT(bizMsg);

            } else if ("NWAL1830Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL1830Scrn00_CMN_Save(bizMsg);

            } else if ("NWAL1830Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL1830Scrn00_CMN_Reset(bizMsg);

            } else if ("NWAL1830Scrn00_MassApply".equals(screenAplID)) {
                doProcess_NWAL1830Scrn00_MassApply(bizMsg);

            } else if ("NWAL1830Scrn00_OnChange_OrderCategory".equals(screenAplID)) {
                doProcess_NWAL1830Scrn00_OnChange_OrderCategory(bizMsg);

            } else if ("NWAL1830Scrn00_OnChange_SelectAll".equals(screenAplID)) {
                doProcess_NWAL1830Scrn00_OnChange_SelectAll(bizMsg);

            } else if ("NWAL1830Scrn00_OnChange_ConfigCheck".equals(screenAplID)) {
                doProcess_NWAL1830Scrn00_OnChange_ConfigCheck(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1830_INIT(NWAL1830CMsg bizMsg) {

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
        // Get Status Name
        NWAL1830CommonLogic.getLoanStsNm(bizMsg);

        // Create Action List Box
        if (NWAL1830CommonLogic.createActionPullDown(bizMsg)) {
            // 2017/09/11 S21_NA#19800 add start
            S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getDsOrdCatgListForValSet(bizMsg, LOAN_ORD_ACTION_LOAN_SELL);
            List<Map<String, String>> sellLineCatgList = (List<Map<String, String>>) ssmResult.getResultObject();
            // 2017/09/11 S21_NA#19800 add end
            
            // Create Order Category List Box
            NWAL1830CommonLogic.createCatgPullDown(bizMsg ,sellLineCatgList);
            // Create Order Reason List Box
            NWAL1830CommonLogic.createRsnPullDown(bizMsg, sellLineCatgList);
        }
        // Search
        search(bizMsg);
        setInitVal(bizMsg);
    }

    /**
     * CMN_Save Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1830Scrn00_CMN_Save(NWAL1830CMsg bizMsg) {
        if (!MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            // Search
            search(bizMsg);
            setInitVal(bizMsg);
        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1830Scrn00_CMN_Reset(NWAL1830CMsg bizMsg) {
        // Search
        search(bizMsg);
        setInitVal(bizMsg);
    }

    /**
     * MassApply Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1830Scrn00_MassApply(NWAL1830CMsg bizMsg) {
        // Copy to Line
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg dtlLine = bizMsg.B.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(dtlLine.xxDplyCtrlFlg_B1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(dtlLine.xxChkBox_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(dtlLine.dsOrdLineCatgCd_BA, bizMsg.dsOrdLineCatgCd_OL);
            }
        }
    }

    /**
     * OnChange_OrderCategory Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1830Scrn00_OnChange_OrderCategory(NWAL1830CMsg bizMsg) {
        // 2017/09/11 S21_NA#19800 add start
        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getDsOrdCatgListForValSet(bizMsg, LOAN_ORD_ACTION_LOAN_SELL);
        List<Map<String, String>> sellLineCatgList = (List<Map<String, String>>) ssmResult.getResultObject();
        // 2017/09/11 S21_NA#19800 add end

        // Create Order Reason List Box
        NWAL1830CommonLogic.createRsnPullDown(bizMsg, sellLineCatgList);
    }


    /**
     * OnChange_SelectAll Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1830Scrn00_OnChange_SelectAll(NWAL1830CMsg bizMsg) {

        String val = bizMsg.xxChkBox_OL.getValue();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1830_ACMsg configLine = bizMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(configLine.xxChkBox_A1, val);
        }
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg dtlLine = bizMsg.B.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(dtlLine.xxDplyCtrlFlg_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(dtlLine.xxChkBox_B1, val);
            }
        }
    }
    /**
     * OnChange_ConfigCheck Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1830Scrn00_OnChange_ConfigCheck(NWAL1830CMsg bizMsg) {
        NWAL1830_ACMsg configLine = bizMsg.A.no(bizMsg.xxCellIdx.getValueInt());

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg dtlLine = bizMsg.B.no(i);
            if (configLine.dsCpoConfigPk_A1.getValue().compareTo(dtlLine.dsCpoConfigPk_B1.getValue()) == 0) {
                if (ZYPConstant.FLG_ON_Y.equals(dtlLine.xxDplyCtrlFlg_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dtlLine.xxChkBox_B1, configLine.xxChkBox_A1.getValue());
                }
            }
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     */
    private void search(NWAL1830CMsg bizMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        // Get Config List
        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getConfigList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;

        } else {
            if (ssmResult.getQueryResultCount() > bizMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                bizMsg.A.setValidCount(bizMsg.A.length());
            } else {
                bizMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }
        }
        // Get Detail List
        ssmResult = NWAL1830Query.getInstance().getDetailList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            ZYPTableUtil.clear(bizMsg.A);
            bizMsg.setMessageInfo(NZZM0000E);
            return;

        } else {
            if (ssmResult.getQueryResultCount() > bizMsg.B.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                bizMsg.B.setValidCount(bizMsg.B.length());
            } else {
                bizMsg.B.setValidCount(ssmResult.getQueryResultCount());
            }
        }

        // 2017/09/11 S21_NA#19800 add start
        // get Conversion Data for notIB
        for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
            NWAL1830_BCMsg lineMsg = bizMsg.B.no(j);
            NWAL1830_BCMsg mainMach = getMainMachAction(bizMsg, lineMsg.dsOrdPosnNum_B1.getValue());
            if (mainMach == null) {
                continue;
            }

            if (ZYPConstant.FLG_OFF_N.equals(mainMach.xxDplyCtrlFlg_B1.getValue()) && !ZYPCommonFunc.hasValue(lineMsg.xxScrItem50Txt_B1)) {
                ssmResult = NWAL1830Query.getInstance().getConvInfoForNotIB(bizMsg, lineMsg, mainMach);
                Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();

                if (ssmResult.isCodeNotFound()) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(lineMsg.xxScrItem50Txt_B1, (String) result.get("ACT_LABEL_NM"));
                ZYPEZDItemValueSetter.setValue(lineMsg.procFlg_B1, (String) result.get("PROC_FLG"));
                ZYPEZDItemValueSetter.setValue(lineMsg.cpoOrdNum_B2, (String) result.get("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(lineMsg.dplyLineNum_B2, (String) result.get("DPLY_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(lineMsg.xxDplyCtrlFlg_B1, (String) result.get("CONV_ACT_FLG"));
                ZYPEZDItemValueSetter.setValue(lineMsg.ordLineStsDescTxt_B2, (String) result.get("ORD_LINE_STS_NM"));
            }
        }
        // 2017/09/11 S21_NA#19800 add start

        // CPO Timestamp, Order Type
        if (0 < bizMsg.A.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_OH, bizMsg.A.no(0).ezUpTime_OH);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_OH, bizMsg.A.no(0).ezUpTimeZone_OH);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_OH, bizMsg.A.no(0).dsOrdTpCd_OH);

            // Get Machine Master List
            NWAL1830Query.getInstance().getMachMstrList(bizMsg);
        }
        // Set RowNumber
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxRowNum_B1, BigDecimal.valueOf(i).negate());
            // 2020/05/14 QC#56509-1 Add Start
            // set Default Warehouse
            if (NWAL1830CommonLogic.needsWh(bizMsg.glblCmpyCd.getValue(), bizMsg.B.no(i))) {
                NWZC180001PMsg pMsg = new NWZC180001PMsg();
                if (!NWAL1830CommonLogic.callDefWhApiForRma(pMsg, bizMsg, bizMsg.B.no(i))) {
                    continue;
                }
                // QC#63527 2024/03/14 Start
                ssmResult = NWAL1830Query.getInstance().getCpoDetailInvLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.B.no(i).cpoOrdNum_B1.getValue()
                        , bizMsg.B.no(i).cpoDtlLineNum_B1.getValue(), bizMsg.B.no(i).cpoDtlLineSubNum_B1.getValue());
                String result = (String) ssmResult.getResultObject();

                if (INVTY_OWNR.GMD.equals(result)) {
                    String rtlWhCd = pMsg.rtlWhCd.getValue();

                    RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
                    rtlWhTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
                    rtlWhTMsg.rtlWhCd.setValue(rtlWhCd);

                    rtlWhTMsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhTMsg);

                    String physWhCd = rtlWhTMsg.physWhCd.getValue();

                    ssmResult = NWAL1830Query.getInstance().getRtlWh(bizMsg.glblCmpyCd.getValue(), physWhCd);

                    String rtlWhCdGmd = (String) ssmResult.getResultObject();

                    if (rtlWhCdGmd != null) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).rtlWhCd_B1, rtlWhCdGmd);
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).rtlWhNm_B1, NWAL1830CommonLogic.getRtlWhNm(bizMsg, rtlWhCdGmd));
                    } else {
                        bizMsg.B.no(i).rtlWhCd_B1.clear();
                        bizMsg.B.no(i).rtlWhNm_B1.clear();
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).rtlWhCd_B1, pMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).rtlWhNm_B1, NWAL1830CommonLogic.getRtlWhNm(bizMsg, pMsg.rtlWhCd.getValue()));                    
                }
                // QC#63527 2024/03/14 End
            }
            // 2020/05/14 QC#56509-1 Add End
        }
    }
    /**
     * setInitVal
     * @param bizMsg NWAL1830CMsg
     */
    private void setInitVal(NWAL1830CMsg bizMsg) {
        // defalt Value
        bizMsg.dsOrdLineCatgCd_OL.clear();
        bizMsg.xxChkBox_OL.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_OL, RADIO_VAL_NEW_ORD);
        bizMsg.dsOrdCatgCd_OL.clear();
        bizMsg.dsOrdTpCd_OL.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm_OL, bizMsg.slsRepTocNm_OH);
        bizMsg.cpoOrdNum_OL.clear();
    }

    // 2017/09/11 S21_NA#19800 add start
    /**
     * @param bizMsg
     * @param dsOrdPosnNum
     * @return
     */
    private NWAL1830_BCMsg getMainMachAction(NWAL1830CMsg bizMsg, String dsOrdPosnNum) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
            if (dsOrdPosnNum.equals(bizLineMsg.dsOrdPosnNum_B1.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizLineMsg.baseCmptFlg_B1.getValue())) {
                return bizLineMsg;
            }
        }
        return null;
    }
    // 2017/09/11 S21_NA#19800 add end

}
