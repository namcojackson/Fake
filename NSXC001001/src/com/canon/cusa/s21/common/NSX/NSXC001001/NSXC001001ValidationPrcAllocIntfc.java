/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_CONTR_INTFCTMsgArray;
import business.db.MDSETMsg;
import business.db.PRC_ALLOC_INTFCTMsg;
import business.db.S21_ORGTMsg;
import business.db.S21_ORGTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.common.NSX.NSXC004001.GetDefCoaTrxCdInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC004001.NSXC004001GetDefCoaTrxCd;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ALLOC_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_ALLOC_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Validation PRC_ALLOC_INTFC
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         Y.Tsuchimoto    Create          N/A(Template only)
 * 2016/01/20   Hitachi         Y.Takeno        Update          N/A
 * 2016/03/24   Hitachi         Y.Tsuchimoto    Update          QC#5662
 * 2016/03/28   Hitachi         Y.Tsuchimoto    Update          QC#5822
 * 2016/04/07   Hitachi         A.Kohinata      Update          QC#6566
 * 2016/05/27   Hitachi         T.Kanasaka      Update          QC#8335
 * 2016/08/09   Hitachi         Y.Takeno        Update          QC#11853
 * 2016/10/11   Hitachi         T.Mizuki        Update          QC#14606
 */
public class NSXC001001ValidationPrcAllocIntfc {

    /** This data has been updated by another user. */
    private static final String ZZZM9004E = "ZZZM9004E";

    /** Failed to update "PRC_ALLOC_INTFC". */
    private static final String NSXM0099E = "NSXM0099E";

    /** [Source Type] field is mandatory. */
    private static final String NSXM0012E = "NSXM0012E";

    /** [Source Ref#] field is mandatory. */
    private static final String NSXM0013E = "NSXM0013E";

    /** [Serial#] field is mandatory. */
    private static final String NSXM0016E = "NSXM0016E";

    /** [IB ID] field is mandatory. */
    private static final String NSXM0017E = "NSXM0017E";

    /** [Mdse Code] field is mandatory. */
    private static final String NSXM0028E = "NSXM0028E";

    /** [IB ID] doesn't exist in the table [Machine Master]. */
    private static final String NSXM0042E = "NSXM0042E";

    /** [Mdse Code] doesn't exist in the table [Merchandise Master]. */
    private static final String NSXM0047E = "NSXM0047E";

    /** [Contract#] should be match each other in the same [Source Ref#]. */
    private static final String NSXM0055E = "NSXM0055E";

    /** [Mdse Code] should be match each other in the same [IB ID]. */
    private static final String NSXM0060E = "NSXM0060E";

    /** [Serial#] should be match each other in the same [IB ID]. */
    private static final String NSXM0061E = "NSXM0061E";

    /** [Interface record related to IB ID] doesn't exist in the table [DS Contract Interface]. */
    private static final String NSXM0080E = "NSXM0080E";

    /** [Rep Code or Rep Name] field is mandatory.*/
    private static final String NSXM0091E = "NSXM0091E";

    /** [Split%] field is mandatory.*/
    private static final String NSXM0092E = "NSXM0092E";

    /** [Rep Code] doesn't exist in the table [S21 Organization Master]. */
    private static final String NSXM0093E = "NSXM0093E";

    /** Amount of Split% should be 100 at each level of Allocation Type. */
    private static final String NSXM0094E = "NSXM0094E";

    /** [Rep Code] has a duplicate of data. */
    private static final String NSXM0095E = "NSXM0095E";

    /** [Allocation Info] cannot be obtained. */
    private static final String NSXM0096E = "NSXM0096E";

    /** PRC_ALLOC_RATE : 100  */
    private static final BigDecimal PRC_ALLOC_RATE_100 = new BigDecimal(100);

    /** SPCL_FLT_MDSE_CD */
    private static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001ValidationPrcAllocIntfc.class);

    /**
     * <pre>
     * validationPrcAllocIntfc
     * </pre>
     * @param tMsgList List<PRC_ALLOC_INTFCTMsg>
     * @return rtnBean ValidationReturnBean
     */
    public static ValidationReturnBean validationPrcAllocIntfc(List<PRC_ALLOC_INTFCTMsg> tMsgList) {
        ValidationReturnBean resultPMsg = new ValidationReturnBean();

        int errorCount = 0;
        int errorTotalCount = 0;

        resultPMsg.xxMsgIdList.setValidCount(resultPMsg.xxMsgIdList.length());

        for (PRC_ALLOC_INTFCTMsg tMsg : tMsgList) {

            // Input check
            errorCount = checkInput(resultPMsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }

            // Master check
            errorCount = masterCheck(resultPMsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }
        }

        if (errorTotalCount == 0) {
            // Consistency check
            errorCount = consistencyCheck(resultPMsg, tMsgList, errorTotalCount + errorCount);
            errorTotalCount += errorCount;

            // Contract Header validation
            errorCount = headerCheck(resultPMsg, tMsgList, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount == 0) {

                // Contract Detail validation
                errorCount = detailCheck(resultPMsg, tMsgList, errorTotalCount + errorCount);
                errorTotalCount += errorCount;
            }
        }

        resultPMsg.xxMsgIdList.setValidCount(errorTotalCount);

        return resultPMsg;
    }

    private static int checkInput(ValidationReturnBean prmPmsg, PRC_ALLOC_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;

        if (!hasValue(tMsg.dsContrSrcRefNum)) {
            setErrorMessage(prmPmsg, NSXM0013E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0013E);
            errorCount++;
        }
        if (!hasValue(tMsg.contrIntfcSrcTpCd)) {
            setErrorMessage(prmPmsg, NSXM0012E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0012E);
            errorCount++;
        }
        // START 2016/08/09 Y.Takeno [QC#11853, DEL]
//        if (!PRC_ALLOC_INTFC_TP.CONTRACT.equals(tMsg.prcAllocIntfcTpCd.getValue()) && isInstlBaseCtrlFlg(tMsg.glblCmpyCd.getValue(), tMsg.mdseCd.getValue()) && !hasValue(tMsg.serNum)) {
//            setErrorMessage(prmPmsg, NSXM0016E, errorTotalCount + errorCount);
//            setErrorStatusAndMessage(tMsg, NSXM0016E);
//            errorCount++;
//        }
        // END 2016/08/09 Y.Takeno [QC#11853, DEL]
//        if (!PRC_ALLOC_INTFC_TP.CONTRACT.equals(tMsg.prcAllocIntfcTpCd.getValue()) && !hasValue(tMsg.svcMachMstrPk)) {
//            setErrorMessage(prmPmsg, NSXM0017E, errorTotalCount + errorCount);
//            setErrorStatusAndMessage(tMsg, NSXM0017E);
//            errorCount++;
//        }
//        if (!PRC_ALLOC_INTFC_TP.CONTRACT.equals(tMsg.prcAllocIntfcTpCd.getValue()) && !hasValue(tMsg.mdseCd)) {
//            setErrorMessage(prmPmsg, NSXM0028E, errorTotalCount + errorCount);
//            setErrorStatusAndMessage(tMsg, NSXM0028E);
//            errorCount++;
//        }
        if (!hasValue(tMsg.tocCd) || !hasValue(tMsg.tocNm)) {
            setErrorMessage(prmPmsg, NSXM0091E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0091E);
            errorCount++;
        }
        if (!hasValue(tMsg.prcAllocRate)) {
            setErrorMessage(prmPmsg, NSXM0092E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0092E);
            errorCount++;
        }

        return errorCount;
    }

    private static int masterCheck(ValidationReturnBean prmPmsg, PRC_ALLOC_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;

        if (hasValue(tMsg.svcMachMstrPk) && !checkIbId(tMsg.glblCmpyCd.getValue(), tMsg.svcMachMstrPk.getValue())) {
            setErrorMessage(prmPmsg, NSXM0042E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0042E);
            errorCount++;
        }
        if (hasValue(tMsg.mdseCd) && !checkMdse(tMsg.glblCmpyCd.getValue(), tMsg.mdseCd.getValue())) {
            setErrorMessage(prmPmsg, NSXM0047E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0047E);
            errorCount++;
        }
        if (hasValue(tMsg.tocCd) && !checkS21Org(tMsg.glblCmpyCd.getValue(), tMsg.tocCd.getValue())) {
            setErrorMessage(prmPmsg, NSXM0093E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0093E);
            errorCount++;
        }
        if (hasValue(tMsg.contrIntfcSrcTpCd) && hasValue(tMsg.dsContrSrcRefNum) && hasValue(tMsg.svcMachMstrPk)) {
            if (!checkDsContrIntf(tMsg)) {
                setErrorMessage(prmPmsg, NSXM0080E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0080E);
                errorCount++;
            }
        }

        return errorCount;
    }

    private static boolean checkIbId(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrPk", svcMachMstrPk);
        SVC_MACH_MSTRTMsg tMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null) {
            return true;
        }
        return false;
    }

    private static boolean checkMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("mdseCd", mdseCd);
        MDSETMsg tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null) {
            return true;
        }
        String fltMdseCd = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, glblCmpyCd);
        if (mdseCd.equals(fltMdseCd)) {
            return true;
        }
        return false;
    }

    private static boolean checkS21Org(String glblCmpyCd, String tocCd) {
        S21_ORGTMsg inMsg = new S21_ORGTMsg();
        // mod start 2016/10/11 CSA QC#14606
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("tocCd01", tocCd);
        inMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21_ORGTMsgArray outArray = (S21_ORGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return false;
        } else {
            return true;
        }
        // mod end 2016/10/11 CSA QC#14606
    }

    private static boolean checkDsContrIntf(PRC_ALLOC_INTFCTMsg prcAllocMsg) {

        DS_CONTR_INTFCTMsg inMsg = new DS_CONTR_INTFCTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", prcAllocMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("contrIntfcSrcTpCd01", prcAllocMsg.contrIntfcSrcTpCd.getValue());
        inMsg.setConditionValue("dsContrSrcRefNum01", prcAllocMsg.dsContrSrcRefNum.getValue());
        if ((int) EZDTBLAccessor.count(inMsg) > 0) {
            return true;
        }
        return false;
    }

    private static int consistencyCheck(ValidationReturnBean prmPmsg, List<PRC_ALLOC_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        // Split% amount check
        errorCount = splitCheck(prmPmsg, tMsgList, errorTotalCount + errorCount);
        errorTotalCount += errorCount;

        // Rep Code duplication check
        errorCount = repCodeCheck(prmPmsg, tMsgList, errorTotalCount + errorCount);
        errorTotalCount += errorCount;

        // Allocation check
        errorCount = allocationCheck(prmPmsg, tMsgList, errorTotalCount + errorCount);
        errorTotalCount += errorCount;

        return errorCount;
    }

    private static int splitCheck(ValidationReturnBean prmPmsg, List<PRC_ALLOC_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        Map<String, List<PRC_ALLOC_INTFCTMsg>> groupDataMap = new HashMap<String, List<PRC_ALLOC_INTFCTMsg>>();
        for (PRC_ALLOC_INTFCTMsg tMsg : tMsgList) {

            StringBuilder builder = new StringBuilder();
            builder.append(tMsg.contrIntfcSrcTpCd.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrSrcRefNum.getValue());
            builder.append(",");
            builder.append(tMsg.svcMachMstrPk.getValue());
            builder.append(",");
            builder.append(tMsg.prcAllocIntfcTpCd.getValue());
            String groupKey = builder.toString();

            if (!groupDataMap.containsKey(groupKey)) {
                groupDataMap.put(groupKey, new ArrayList<PRC_ALLOC_INTFCTMsg>());
            }
            List<PRC_ALLOC_INTFCTMsg> tMsgGroupList = groupDataMap.get(groupKey);
            tMsgGroupList.add(tMsg);
        }

        for (List<PRC_ALLOC_INTFCTMsg> tMsgGroupList : groupDataMap.values()) {
            BigDecimal prcAllocRateTotal = new BigDecimal(0);
            for (PRC_ALLOC_INTFCTMsg tMsg : tMsgGroupList) {
                prcAllocRateTotal = prcAllocRateTotal.add(tMsg.prcAllocRate.getValue());
            }

            if (prcAllocRateTotal.compareTo(PRC_ALLOC_RATE_100) != 0) {
                for (PRC_ALLOC_INTFCTMsg tMsg : tMsgGroupList) {
                    setErrorMessage(prmPmsg, NSXM0094E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0094E);
                    errorCount++;
                }
            }
        }

        return errorCount;
    }

    private static int repCodeCheck(ValidationReturnBean prmPmsg, List<PRC_ALLOC_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        Map<String, List<PRC_ALLOC_INTFCTMsg>> groupDataMap = new HashMap<String, List<PRC_ALLOC_INTFCTMsg>>();
        for (PRC_ALLOC_INTFCTMsg tMsg : tMsgList) {

            StringBuilder builder = new StringBuilder();
            builder.append(tMsg.contrIntfcSrcTpCd.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrSrcRefNum.getValue());
            builder.append(",");
            builder.append(tMsg.svcMachMstrPk.getValue());
            builder.append(",");
            builder.append(tMsg.prcAllocIntfcTpCd.getValue());
            String groupKey = builder.toString();

            if (!groupDataMap.containsKey(groupKey)) {
                groupDataMap.put(groupKey, new ArrayList<PRC_ALLOC_INTFCTMsg>());
            }
            List<PRC_ALLOC_INTFCTMsg> tMsgGroupList = groupDataMap.get(groupKey);
            tMsgGroupList.add(tMsg);
        }

        for (List<PRC_ALLOC_INTFCTMsg> tMsgGroupList : groupDataMap.values()) {
            Map<String, String> tocCdMap = new HashMap<String, String>();
            for (PRC_ALLOC_INTFCTMsg tMsg : tMsgGroupList) {
                String tocCd = tMsg.tocCd.getValue();
                if (!tocCdMap.containsKey(tocCd)) {
                    tocCdMap.put(tocCd, tocCd);

                } else {
                    setErrorMessage(prmPmsg, NSXM0095E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0095E);
                    errorCount++;
                }
            }
        }

        return errorCount;
    }

    private static int allocationCheck(ValidationReturnBean prmPmsg, List<PRC_ALLOC_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        for (PRC_ALLOC_INTFCTMsg tMsg : tMsgList) {
            String prcAllocIntfcTpCd = tMsg.prcAllocIntfcTpCd.getValue();

            if (!PRC_ALLOC_INTFC_TP.CONTRACT.equals(prcAllocIntfcTpCd)) {

                DS_CONTR_INTFCTMsg inMsg = new DS_CONTR_INTFCTMsg();
                inMsg.setSQLID("002");
                inMsg.setConditionValue("glblCmpyCd01", tMsg.glblCmpyCd.getValue());
                inMsg.setConditionValue("contrIntfcSrcTpCd01", tMsg.contrIntfcSrcTpCd.getValue());
                inMsg.setConditionValue("dsContrSrcRefNum01", tMsg.dsContrSrcRefNum.getValue());

                String dsAcctNum = null;
                //Del Start 05/27/2016 <QC#8335>
//                String intgMdseCd = null;
                //Del End   05/27/2016 <QC#8335>
                String svcPgmMdseCd = null;
                // START 2016/04/07 [QC#6566, ADD]
                String dsContrCatgCd = null;
                String svcLineBizCd = null;
                // END 2016/04/07 [QC#6566, ADD]
                DS_CONTR_INTFCTMsgArray tMsgArray = (DS_CONTR_INTFCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
                if (tMsgArray.length() > 0) {
                    dsAcctNum = tMsgArray.no(0).dsAcctNum.getValue();
                    //Del Start 05/27/2016 <QC#8335>
//                    intgMdseCd = tMsgArray.no(0).intgMdseCd.getValue();
                    //Del End   05/27/2016 <QC#8335>
                    svcPgmMdseCd = tMsgArray.no(0).svcPgmMdseCd.getValue();
                    // START 2016/04/07 [QC#6566, ADD]
                    dsContrCatgCd = tMsgArray.no(0).dsContrCatgCd.getValue();
                    svcLineBizCd = tMsgArray.no(0).svcLineBizCd.getValue();
                    // END 2016/04/07 [QC#6566, ADD]
                }

                String baseChrgFlg;
                if (!PRC_ALLOC_INTFC_TP.USAGE.equals(prcAllocIntfcTpCd)) {
                    baseChrgFlg = ZYPConstant.FLG_ON_Y;
                } else {
                    baseChrgFlg = ZYPConstant.FLG_OFF_N;
                }

                String usgChrgFlg;
                if (!PRC_ALLOC_INTFC_TP.BASE.equals(prcAllocIntfcTpCd)) {
                    usgChrgFlg = ZYPConstant.FLG_ON_Y;
                } else {
                    usgChrgFlg = ZYPConstant.FLG_OFF_N;
                }

                GetDefCoaTrxCdInfoBean gdctciInfoBean = new GetDefCoaTrxCdInfoBean();
                gdctciInfoBean.setGlblCmpyCd(tMsg.glblCmpyCd.getValue());
                if (!PRC_ALLOC_INTFC_TP.CONTRACT.equals(prcAllocIntfcTpCd)) {
                    gdctciInfoBean.setSvcMachMstrPk(tMsg.svcMachMstrPk.getValue());
                    gdctciInfoBean.setMdseCd(tMsg.mdseCd.getValue());
                }
                gdctciInfoBean.setDsAcctNum(dsAcctNum);
                gdctciInfoBean.setBaseChrgFlg(baseChrgFlg);
                gdctciInfoBean.setUsgChrgFlg(usgChrgFlg);
                gdctciInfoBean.setAddlChrgFlg(ZYPConstant.FLG_OFF_N);
                gdctciInfoBean.setTocCd(tMsg.tocCd.getValue());
                //Mod Start 05/27/2016 <QC#8335>
//                if (PRC_ALLOC_INTFC_TP.USAGE.equals(prcAllocIntfcTpCd)) {
//                    gdctciInfoBean.setIntgMdseCd(intgMdseCd);
//                }
//                if (PRC_ALLOC_INTFC_TP.BASE.equals(prcAllocIntfcTpCd)) {
//                    gdctciInfoBean.setSvcPgmMdseCd(svcPgmMdseCd);
//                }
                gdctciInfoBean.setSvcPgmMdseCd(svcPgmMdseCd);
                //Mod End   05/27/2016 <QC#8335>
                // START 2016/04/07 [QC#6566, ADD]
                if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !ZYPCommonFunc.hasValue(tMsg.svcMachMstrPk)) {
                    gdctciInfoBean.setFleetLineFlg(ZYPConstant.FLG_ON_Y);
                } else {
                    gdctciInfoBean.setFleetLineFlg(ZYPConstant.FLG_OFF_N);
                }
                gdctciInfoBean.setSvcLineBizCd(svcLineBizCd);
                // END 2016/04/07 [QC#6566, ADD]
                gdctciInfoBean.setXxModeCd(NSXC004001GetDefCoaTrxCd.XX_MODE_02);

                // NSXC004001
                GetDefCoaTrxCdInfoBean outListInfoBean = NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(gdctciInfoBean);
                if (outListInfoBean == null) {
                    setErrorMessage(prmPmsg, NSXM0096E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0096E);
                    errorCount++;
                }
            }
        }

        return errorCount;
    }

    private static int headerCheck(ValidationReturnBean prmPmsg, List<PRC_ALLOC_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        Map<String, List<PRC_ALLOC_INTFCTMsg>> groupDataMap = new HashMap<String, List<PRC_ALLOC_INTFCTMsg>>();
        for (PRC_ALLOC_INTFCTMsg tMsg : tMsgList) {

            StringBuilder builder = new StringBuilder();
            builder.append(tMsg.glblCmpyCd.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrIntfcBatNum.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrSrcRefNum.getValue());
            builder.append(",");
            builder.append(tMsg.contrIntfcSrcTpCd.getValue());
            String groupKey = builder.toString();

            if (!groupDataMap.containsKey(groupKey)) {
                groupDataMap.put(groupKey, new ArrayList<PRC_ALLOC_INTFCTMsg>());
            }
            List<PRC_ALLOC_INTFCTMsg> tMsgGroupList = groupDataMap.get(groupKey);
            tMsgGroupList.add(tMsg);
        }

        for (List<PRC_ALLOC_INTFCTMsg> tMsgGroupList : groupDataMap.values()) {

            String dsContrNumBase = null;
            for (PRC_ALLOC_INTFCTMsg tMsg : tMsgGroupList) {

                List<Map<String, Object>> result = getDsContrNumForHeaderConsistencyCheck(tMsg);

                if (result != null && result.size() == 1) {
                    if (dsContrNumBase == null) {
                        dsContrNumBase = (String) result.get(0).get("DS_CONTR_NUM");

                    } else {
                        String dsContrNum = (String) result.get(0).get("DS_CONTR_NUM");
                        if (!dsContrNumBase.equals(dsContrNum)) {
                            setErrorMessage(prmPmsg, NSXM0055E, errorTotalCount + errorCount);
                            setErrorStatusAndMessage(tMsg, NSXM0055E);
                            errorCount++;
                        }
                    }

                } else {
                    setErrorMessage(prmPmsg, NSXM0055E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0055E);
                    errorCount++;
                }
            }
        }

        return errorCount;
    }

    private static List<Map<String, Object>> getDsContrNumForHeaderConsistencyCheck(PRC_ALLOC_INTFCTMsg tMsg) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        prmMap.put("dsContrIntfcBatNum", tMsg.dsContrIntfcBatNum.getValue());
        prmMap.put("dsContrSrcRefNum", tMsg.dsContrSrcRefNum.getValue());
        prmMap.put("contrIntfcSrcTpCd", tMsg.contrIntfcSrcTpCd.getValue());
        // 2016/03/28 START [QC#5822, ADD]
        if (CONTR_INTFC_SRC_TP.ORDER.equals(tMsg.contrIntfcSrcTpCd.getValue())) {
            prmMap.put("cpoSvcDtlPk", tMsg.cpoSvcDtlPk.getValue());
        }
        // 2016/03/28 END   [QC#5822, ADD]
        return (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getDsContrNumForHeaderConsistencyCheck", prmMap);
    }

    private static int detailCheck(ValidationReturnBean prmPmsg, List<PRC_ALLOC_INTFCTMsg> tMsgList, int errorTotalCount) {

        int errorCount = 0;

        Map<String, PRC_ALLOC_INTFCTMsg> groupDataMap = new HashMap<String, PRC_ALLOC_INTFCTMsg>();
        for (PRC_ALLOC_INTFCTMsg tMsg : tMsgList) {
            StringBuilder builder = new StringBuilder();
            builder.append(tMsg.contrIntfcSrcTpCd.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrSrcRefNum.getValue());
            builder.append(",");
            builder.append(tMsg.svcMachMstrPk.getValue());
            String groupKey = builder.toString();

            if (groupDataMap.containsKey(groupKey)) {
                PRC_ALLOC_INTFCTMsg tMsgMap = groupDataMap.get(groupKey);
                if (!tMsgMap.mdseCd.getValue().equals(tMsg.mdseCd.getValue())) {
                    setErrorMessage(prmPmsg, NSXM0060E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0060E);
                    errorCount++;
                }

                if (!tMsgMap.serNum.getValue().equals(tMsg.serNum.getValue())) {
                    setErrorMessage(prmPmsg, NSXM0061E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0061E);
                    errorCount++;
                }

            } else {
                groupDataMap.put(groupKey, tMsg);
            }
        }

        return errorCount;
    }

    private static void setErrorMessage(ValidationReturnBean prmPmsg, String errorMsgId, int errCnt) {
        if (prmPmsg == null || errorMsgId == null) {
            return;
        }
        if (errCnt >= prmPmsg.xxMsgIdList.length()) {
            return;
        }
        setValue(prmPmsg.xxMsgIdList.no(errCnt).xxMsgId, errorMsgId);
    }

    private static void setErrorStatusAndMessage(PRC_ALLOC_INTFCTMsg tMsg, String errorMsgId) {
        if (tMsg == null || errorMsgId == null) {
            return;
        }
        setValue(tMsg.intfcErrMsgTxt, S21MessageFunc.clspGetMessage(errorMsgId));
    }

    // START 2016/03/24 Y.Tsuchimoto [QC#5662 ADD]
    /**
     * <pre>
     * updateValidationResult
     * </pre>
     * @param resultPMsg ValidationReturnBean
     * @param rstMsgList List<PRC_ALLOC_INTFCTMsg>
     */
    public static void updateValidationResult(ValidationReturnBean resultPMsg, List<PRC_ALLOC_INTFCTMsg> rstMsgList) {
        int errCnt = 0;
        if (resultPMsg == null) {
            resultPMsg = new ValidationReturnBean();
        }
        resultPMsg.xxMsgIdList.setValidCount(resultPMsg.xxMsgIdList.length());
        for (int i = 0; i < rstMsgList.size(); i++) {
            PRC_ALLOC_INTFCTMsg rstMsg = rstMsgList.get(i);
            if (hasValue(rstMsg.prcAllocIntfcPk)) {
                // for update
                PRC_ALLOC_INTFCTMsg tMsg = getPrcAllocIntfc(rstMsg.glblCmpyCd.getValue(), rstMsg.prcAllocIntfcPk.getValue());
                if (tMsg == null) {
                    setErrorMessage(resultPMsg, ZZZM9004E, errCnt);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(rstMsg.ezUpTime.getValue(), rstMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    setErrorMessage(resultPMsg, ZZZM9004E, errCnt);
                    return;
                }
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                    setValue(tMsg.allocIntfcStsCd, ALLOC_INTFC_STS.ERROR);
                } else {
                    setValue(tMsg.intfcErrMsgTxt, (String) null);
                    setValue(tMsg.allocIntfcStsCd, ALLOC_INTFC_STS.NORMAL);
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    setErrorMessage(resultPMsg, NSXM0099E, errCnt);
                    setErrorStatusAndMessage(tMsg, NSXM0099E);

                    return;
                }
            }
        }
    }

    private static PRC_ALLOC_INTFCTMsg getPrcAllocIntfc(String glblCmpyCd, BigDecimal prcAllocIntfcPk) {
        PRC_ALLOC_INTFCTMsg prmTMsg = new PRC_ALLOC_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.prcAllocIntfcPk, prcAllocIntfcPk);
        return (PRC_ALLOC_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }
    // END   2016/03/24 Y.Tsuchimoto [QC#5662 ADD]
}
