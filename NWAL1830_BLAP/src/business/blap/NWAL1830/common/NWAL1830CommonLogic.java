/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1830.common;

import static business.blap.NWAL1830.constant.NWAL1830Constant.NWAM0811E;
import static business.blap.NWAL1830.constant.NWAL1830Constant.PERIOD;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NWAL1830_LOAN_RTRN_RSN_CD;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NWAL1830.NWAL1830CMsg;
import business.blap.NWAL1830.NWAL1830Query;
import business.blap.NWAL1830.NWAL1830_ACMsg;
import business.blap.NWAL1830.NWAL1830_BCMsg;
import business.blap.NWAL1830.NWAL1830_CCMsg;
import business.blap.NWAL1830.NWAL1830_MCMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.MDSETMsg;
import business.db.PRC_CATGTMsg;
import business.db.RTL_WHTMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_rtnDtlPMsgArray;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMA_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL1830CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/30   Fujitsu         Y.Taoka         Create          N/A
 * 2016/11/09   Fujitsu         Y.Kanefusa      Update          S21_NA#15866
 * 2017/12/27   Fujitsu         K.Ishizuka      Update          S21_NA#23204
 * 2018/07/26   Fujitsu         M.Yamada        Update          S21_NA#27004
 * 2019/05/31   Fujitsu         Y.Kanefusa      Update          S21_NA#50556
 * 2019/09/05   Fujitsu         C.Hara          Update          S21_NA#53295
 * 2019/12/13   Fujitsu         Mz.Takahashi    Update          QC#54747
 * 2020/04/13   Fujitsu         C.Hara          Update          QC#56374
 * 2020/04/14   Fujitsu         Y.Kanefusa      Update          S21_NA#56509
 * 2020/05/14   Fujitsu         C.Hara          Update          QC#56509-1
 *</pre>
 */
public class NWAL1830CommonLogic {

    /**
     * Create Action Pulldown
     * @param bizMsg NWAL1830CMsg
     * @return boolean
     */
    public static boolean createActionPullDown(NWAL1830CMsg bizMsg) {
        // Return code
        boolean rc = false;
        // Order Reason
        bizMsg.dsOrdLineCatgCd_CD.clear();
        bizMsg.xxScrItem50Txt_NM.clear();
        bizMsg.dsOrdLineCatgCd_OL.clear();
        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getActionList(bizMsg, ZYPConstant.FLG_ON_Y);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            int size = 0;
            for (int i = 0; i < resultList.size(); i++) {
                if (bizMsg.dsOrdLineCatgCd_CD.length() < i) {
                    break;
                }
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgCd_CD.no(i), resultMap.get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt_NM.no(i), resultMap.get("ACTION_NM_TXT"));

                NWAL1830_CCMsg actionDsOrdLineCatgList = bizMsg.C.no(i);
                ZYPEZDItemValueSetter.setValue(actionDsOrdLineCatgList.dsOrdLineCatgCd, resultMap.get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(actionDsOrdLineCatgList.dsOrdLineDrctnCd, resultMap.get("DS_ORD_LINE_DRCTN_CD"));
                ZYPEZDItemValueSetter.setValue(actionDsOrdLineCatgList.rtlWhCd, resultMap.get("RTL_WH_CD"));
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(actionDsOrdLineCatgList.dsOrdLineDrctnCd.getValue())) {
                    rc = true;
                }
                size++;
            }
            bizMsg.C.setValidCount(size);
        }

        // 2017/09/11 S21_NA#19800 add Start 
        ssmResult = NWAL1830Query.getInstance().getActionList(bizMsg, ZYPConstant.FLG_OFF_N);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            int size = bizMsg.C.getValidCount();
            int addSize = 0;
            for (int i = 0; i < resultList.size(); i++) {
                if (bizMsg.dsOrdLineCatgCd_CD.length() < i + size) {
                    break;
                }
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                NWAL1830_CCMsg actionDsOrdLineCatgList = bizMsg.C.no(size + i);

                ZYPEZDItemValueSetter.setValue(actionDsOrdLineCatgList.dsOrdLineCatgCd, resultMap.get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(actionDsOrdLineCatgList.dsOrdLineDrctnCd, resultMap.get("DS_ORD_LINE_DRCTN_CD"));

                addSize++;
            }
            bizMsg.C.setValidCount(size + addSize);
        }
        // 2017/09/11 S21_NA#19800 add end

        return rc;
    }


    /**
     * Create Category Code Pulldown
     * @param bizMsg NWAL1830CMsg
     */
    public static void createCatgPullDown(NWAL1830CMsg bizMsg,  List<Map<String, String>> sellLineCatgList) {
        // Order Reason
        bizMsg.dsOrdCatgCd_CD.clear();
        bizMsg.dsOrdCatgDescTxt_NM.clear();
        bizMsg.dsOrdCatgCd_OL.clear();
        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getDsOrdCatgList(bizMsg, sellLineCatgList);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                if (bizMsg.dsOrdCatgCd_CD.length() < i) {
                    break;
                }
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_CD.no(i), resultMap.get("DS_ORD_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt_NM.no(i), resultMap.get("DS_ORD_CATG_DESC_TXT"));
            }

        }
    }

    /**
     * Create Reason Code Pulldown
     * @param bizMsg NWAL1820CMsg
     */
    public static void createRsnPullDown(NWAL1830CMsg bizMsg , List<Map<String, String>> sellLineCatgList) {
        // Order Reason
        bizMsg.dsOrdTpCd_CD.clear();
        bizMsg.dsOrdTpDescTxt_NM.clear();
        bizMsg.dsOrdTpCd_OL.clear();
        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getDsOrdTpList(bizMsg, sellLineCatgList);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                if (bizMsg.dsOrdTpCd_CD.length() < i) {
                    break;
                }
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_CD.no(i), resultMap.get("DS_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_NM.no(i), resultMap.get("DS_ORD_TP_DESC_TXT"));
            }

        }
    }

    /**
     * getLoanStsNm
     * @param bizMsg NWAL1830CMsg
     */
    public static void getLoanStsNm(NWAL1830CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrStsTxt_LO, ZYPCodeDataUtil.getVarCharConstValue("NWAL1830_LINE_STS_NM_LOAN", bizMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrStsTxt_CL, ZYPCodeDataUtil.getVarCharConstValue("NWAL1830_LINE_STS_NM_CLOSE", bizMsg.glblCmpyCd.getValue()));
        if (!ZYPCommonFunc.hasValue(bizMsg.xxScrStsTxt_LO)) {
            bizMsg.setMessageInfo(NWAM0811E, new String[]{"VAR_CHAR_CONST KEY=NWAL1830_LINE_STS_NM_LOAN"});
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.xxScrStsTxt_CL)) {
            bizMsg.setMessageInfo(NWAM0811E, new String[]{"VAR_CHAR_CONST KEY=NWAL1830_LINE_STS_NM_CLOSE"});
        }
    }


    /**
     * getActionInbdOutbd
     * @param bizMsg NWAL1830CMsg
     * @param bLineMsg NWAL1830_BCMsg
     * @return String
     */
    public static String getActionInbdOutbd(NWAL1830CMsg bizMsg, NWAL1830_BCMsg bLineMsg) {
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if (bLineMsg.dsOrdLineCatgCd_BA.getValue().equals(bizMsg.C.no(i).dsOrdLineCatgCd.getValue())) {
                return bizMsg.C.no(i).dsOrdLineDrctnCd.getValue();
            }
        }
        return null;
    }

//    /**
//     * getRtlWhLoanSell
//     * @param bizMsg NWAL1830CMsg
//     * @param dsOrdLineCatgCd String
//     * @return String
//     */
//    public static String getRtlWhLoanSell(NWAL1830CMsg bizMsg, String dsOrdLineCatgCd) {
//        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            if (dsOrdLineCatgCd.equals(bizMsg.C.no(i).dsOrdLineCatgCd.getValue())) {
//                return bizMsg.C.no(i).rtlWhCd.getValue();
//            }
//        }
//        return null;
//    }

    /**
     * existLineActionLoanReturn
     * @param bizMsg NWAL1830CMsg
     * @return boolean
     */
    public static boolean existLineActionLoanReturn(NWAL1830CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(bizLineMsg.xxDplyCtrlFlg_B1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())) {
                if (DS_ORD_LINE_DRCTN.INBOUND.equals(getActionInbdOutbd(bizMsg, bizLineMsg))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * existLineActionLoanSell
     * @param bizMsg NWAL1830CMsg
     * @return boolean
     */
    public static boolean existLineActionLoanSell(NWAL1830CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(bizLineMsg.xxDplyCtrlFlg_B1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())) {
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(getActionInbdOutbd(bizMsg, bizLineMsg))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * numberingCpoDtlLineNum
     * @param pMsg NWZC150001PMsg
     * @param maxLineNum String
     * @param startIndex int
     * @param mmPkToConvLineNumNew Map<BigDecimal, String[]>
     * @param checkLineList List<NWAL1830_BCMsg>
     */
    public static void numberingCpoDtlLineNum(
            NWZC150001PMsg pMsg, String maxLineNum, int startIndex, Map<BigDecimal, String[]> mmPkToConvLineNumNew, List<NWAL1830_BCMsg> checkLineList) {
        String prePosnNum = "";
        int dsCpoLineNum = 1;
        String nextLineNum = maxLineNum;
        NWAL1830_BCMsg convLine = null;
        for (int i = startIndex; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg lineMsg = pMsg.A.no(i);
            // Get conversion line
            convLine = getOrigLine(lineMsg, checkLineList);

            // CPO_DTL_LINE_NUM
            nextLineNum = getNextCpoDtlLineNum(nextLineNum);
            lineMsg.cpoDtlLineNum_A1.setValue(nextLineNum);
            // DS_CPO_LINE_NUM
            if (prePosnNum.equals(lineMsg.dsOrdPosnNum_A1.getValue())) {
//                lineMsg.dsCpoLineNum_A1.setValue(new BigDecimal(dsCpoLineNum));
            } else {
//                lineMsg.dsCpoLineNum_A1.setValue(BigDecimal.ONE);
                dsCpoLineNum = 1;
            }
            List<String> refCpoDtlList = getBaseComponentDtlNum(pMsg, lineMsg.dsOrdPosnNum_A1.getValue(), CONFIG_CATG.OUTBOUND);
            // QC#15866 2016/11/09 Del Start
            // String dplyLineRefNum = null; 
            // String refCpoDtlLineNum = null;
            // String refCpoDtlLineSubNum = null;
            // QC#15866 2016/11/09 Del End
            if (isSetMerchandise(pMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_A1.getValue())) {
                // Set Component
                lineMsg.cpoDtlLineSubNum_A1.setValue("000");

                int subIndex = 1;
                if (i + subIndex < pMsg.A.getValidCount()) {
                    int lineSubNum = 1;
                    NWZC150001_APMsg childLineMsg = pMsg.A.no(i + subIndex);
                    while (isSameLineByDsCpoLineNum(lineMsg, childLineMsg)) {

                        // Get conversion line
                        NWAL1830_BCMsg convLineChild = getOrigLine(childLineMsg, checkLineList);
                        // CPO_DTL_LINE_NUM
                        childLineMsg.cpoDtlLineNum_A1.setValue(nextLineNum);
                        childLineMsg.cpoDtlLineSubNum_A1.setValue(ZYPCommonFunc.leftPad(String.valueOf(lineSubNum), 3, "0"));
                        // DS_CPO_LINE_NUM
                        childLineMsg.dsCpoLineNum_A1.setValue(new BigDecimal(dsCpoLineNum));
                        childLineMsg.dsCpoLineSubNum_A1.setValue(new BigDecimal(lineSubNum));
                        // REF_CPO_DTL_LINE_NUM
                        if (ZYPConstant.FLG_ON_Y.equals(childLineMsg.baseCmptFlg_A1.getValue())) {
                            //SET Base Compornent = 'Y'
                            // QC#15866 2016/11/09 Del Start
                            //refCpoDtlLineNum = childLineMsg.cpoDtlLineNum_A1.getValue();
                            //refCpoDtlLineSubNum = childLineMsg.cpoDtlLineSubNum_A1.getValue();
                            //dplyLineRefNum = getDplyLineRefNum(childLineMsg);
                            // QC#15866 2016/11/09 Del End

                            childLineMsg.refCpoDtlLineNum_A1.clear();
                            childLineMsg.refCpoDtlLineSubNum_A1.clear();
                            childLineMsg.dplyLineRefNum_A1.clear();
                        } else if (!refCpoDtlList.isEmpty()) {
                            //SET Base Compornent = 'N'
                            // QC#15866 2016/11/09 Mod Start
                            // childLineMsg.refCpoDtlLineNum_A1.setValue(refCpoDtlLineNum);
                            // childLineMsg.refCpoDtlLineSubNum_A1.setValue(refCpoDtlLineSubNum);
                            // childLineMsg.dplyLineRefNum_A1.setValue(dplyLineRefNum);
                            ZYPEZDItemValueSetter.setValue(childLineMsg.refCpoDtlLineNum_A1,refCpoDtlList.get(0));
                            ZYPEZDItemValueSetter.setValue(childLineMsg.refCpoDtlLineSubNum_A1,refCpoDtlList.get(1));
                            ZYPEZDItemValueSetter.setValue(childLineMsg.dplyLineRefNum_A1,refCpoDtlList.get(2));
                            // QC#15866 2016/11/09 Mod Start
                        } else {
                            //Base Compornent does not exit
                            childLineMsg.refCpoDtlLineNum_A1.clear();
                            childLineMsg.refCpoDtlLineSubNum_A1.clear();
                            childLineMsg.dplyLineRefNum_A1.clear();
                        }

                        BigDecimal mmPk = null;
                        if (ZYPCommonFunc.hasValue(childLineMsg.svcMachMstrPk_A1)) {
                            mmPk = childLineMsg.svcMachMstrPk_A1.getValue();
                        } else {
                            mmPk = convLineChild.xxRowNum_B1.getValue();
                        }
                        mmPkToConvLineNumNew.put(mmPk, new String[]{childLineMsg.cpoDtlLineNum_A1.getValue(), childLineMsg.cpoDtlLineSubNum_A1.getValue()});
                        subIndex++;
                        lineSubNum++;
                        if (i + subIndex >= pMsg.A.getValidCount()) {
                            break;
                        }
                        childLineMsg = pMsg.A.no(i + subIndex);
                    }
                    i += (subIndex - 1);
                }
                lineMsg.dsCpoLineNum_A1.setValue(new BigDecimal(dsCpoLineNum));
                // REF_CPO_DTL_LINE_NUM
                if (!refCpoDtlList.isEmpty()) {
                    // Bottom MDSE in SET
                    // QC#15866 2016/11/09 Mod Start
                    // lineMsg.refCpoDtlLineNum_A1.setValue(refCpoDtlLineNum);
                    // lineMsg.refCpoDtlLineSubNum_A1.setValue(refCpoDtlLineSubNum);
                    // lineMsg.dplyLineRefNum_A1.setValue(dplyLineRefNum);
                    ZYPEZDItemValueSetter.setValue(lineMsg.refCpoDtlLineNum_A1,refCpoDtlList.get(0));
                    ZYPEZDItemValueSetter.setValue(lineMsg.refCpoDtlLineSubNum_A1,refCpoDtlList.get(1));
                    ZYPEZDItemValueSetter.setValue(lineMsg.dplyLineRefNum_A1,refCpoDtlList.get(2));
                    // QC#15866 2016/11/09 Mod End
                } else {
                    //Base Compornent does not exit
                    lineMsg.refCpoDtlLineNum_A1.clear();
                    lineMsg.refCpoDtlLineSubNum_A1.clear();
                    lineMsg.dplyLineRefNum_A1.clear();
                }

                BigDecimal mmPk = null;
                if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_A1)) {
                    mmPk = lineMsg.svcMachMstrPk_A1.getValue();
                } else {
                    mmPk = convLine.xxRowNum_B1.getValue();
                }
                mmPkToConvLineNumNew.put(mmPk, new String[]{lineMsg.cpoDtlLineNum_A1.getValue(), lineMsg.cpoDtlLineSubNum_A1.getValue()});

            } else {
                lineMsg.dsCpoLineNum_A1.setValue(new BigDecimal(dsCpoLineNum));
                lineMsg.cpoDtlLineSubNum_A1.setValue("001");
                lineMsg.dsCpoLineSubNum_A1.clear();
                // REF_CPO_DTL_LINE_NUM
                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_A1.getValue())) {
                    lineMsg.refCpoDtlLineNum_A1.clear();
                    lineMsg.refCpoDtlLineSubNum_A1.clear();
                    lineMsg.dplyLineRefNum_A1.clear();
                } else if (!refCpoDtlList.isEmpty()) {
                    // QC#15866 2016/11/09 Mod Start
                    // lineMsg.refCpoDtlLineNum_A1.setValue(refCpoDtlList.get(0));
                    // lineMsg.refCpoDtlLineSubNum_A1.setValue(refCpoDtlList.get(1));
                    // lineMsg.dplyLineRefNum_A1.setValue(getDplyLineRefNum(lineMsg));
                    ZYPEZDItemValueSetter.setValue(lineMsg.refCpoDtlLineNum_A1,refCpoDtlList.get(0));
                    ZYPEZDItemValueSetter.setValue(lineMsg.refCpoDtlLineSubNum_A1,refCpoDtlList.get(1));
                    ZYPEZDItemValueSetter.setValue(lineMsg.dplyLineRefNum_A1,refCpoDtlList.get(2));
                    // QC#15866 2016/11/09 Mod End
                } else {
                    lineMsg.refCpoDtlLineNum_A1.clear();
                    lineMsg.refCpoDtlLineSubNum_A1.clear();
                    lineMsg.dplyLineRefNum_A1.clear();
                }

                BigDecimal mmPk = null;
                if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_A1)) {
                    mmPk = lineMsg.svcMachMstrPk_A1.getValue();
                } else {
                    mmPk = convLine.xxRowNum_B1.getValue();
                }
                mmPkToConvLineNumNew.put(mmPk, new String[]{lineMsg.cpoDtlLineNum_A1.getValue(), lineMsg.cpoDtlLineSubNum_A1.getValue()});

            }
            prePosnNum = lineMsg.dsOrdPosnNum_A1.getValue();
            dsCpoLineNum++;
        }
    }

    /**
     * numberingCpoRtrnLineNum
     * @param pMsg NWZC150001PMsg
     * @param maxLineNum String
     * @param startIndex int
     * @param mmPkToConvLineNumNew Map<String, String[]>
     * @param checkLineList List<NWAL1830_BCMsg>
     */
    public static void numberingCpoRtrnLineNum(NWZC150001PMsg pMsg, String maxLineNum, int startIndex, Map<BigDecimal, String[]> mmPkToConvLineNumNew, List<NWAL1830_BCMsg> checkLineList) {
        String prePosnNum = "";
        int dsCpoLineNum = 1;
        String nextLineNum = maxLineNum;
        NWAL1830_BCMsg convLine = null;
        for (int i = startIndex; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg lineMsg = pMsg.rtnDtl.no(i);

            // Get conversion line
            convLine = getOrigLine(lineMsg, checkLineList);

            // CPO_DTL_LINE_NUM
            nextLineNum = getNextCpoDtlLineNum(nextLineNum);
            lineMsg.cpoDtlLineNum_B1.setValue(nextLineNum);
            // DS_CPO_LINE_NUM
            if (prePosnNum.equals(lineMsg.dsOrdPosnNum_B1.getValue())) {
//                lineMsg.dsCpoLineNum_B1.setValue(new BigDecimal(dsCpoLineNum));
            } else {
//                lineMsg.dsCpoLineNum_B1.setValue(BigDecimal.ONE);
                dsCpoLineNum = 1;
            }
            List<String> refCpoDtlList = getBaseComponentDtlNum(pMsg, lineMsg.dsOrdPosnNum_B1.getValue(), CONFIG_CATG.INBOUND);

            if (isSetMerchandise(pMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_B1.getValue())) {
                // Set Component
                lineMsg.cpoDtlLineSubNum_B1.setValue("000");

                int subIndex = 1;
                if (i + subIndex < pMsg.rtnDtl.getValidCount()) {
                    int lineSubNum = 1;
                    NWZC150001_rtnDtlPMsg childLineMsg = pMsg.rtnDtl.no(i + subIndex);
                    while (isSameLineByDsCpoRtrnLineNum(lineMsg, childLineMsg)) {
                        // Get conversion line
                        NWAL1830_BCMsg convLineChild = getOrigLine(childLineMsg, checkLineList);

                        // CPO_DTL_LINE_NUM
                        childLineMsg.cpoDtlLineNum_B1.setValue(nextLineNum);
                        childLineMsg.cpoDtlLineSubNum_B1.setValue(ZYPCommonFunc.leftPad(String.valueOf(lineSubNum), 3, "0"));
                        // DS_CPO_LINE_NUM
                        childLineMsg.dsCpoLineNum_B1.setValue(new BigDecimal(dsCpoLineNum));
                        childLineMsg.dsCpoLineSubNum_B1.setValue(new BigDecimal(lineSubNum));
                        // REF_CPO_DTL_LINE_NUM
                        if (ZYPConstant.FLG_ON_Y.equals(childLineMsg.baseCmptFlg_B1.getValue())) {
                            childLineMsg.refCpoDtlLineNum_B1.clear();
                            childLineMsg.refCpoDtlLineSubNum_B1.clear();
                            childLineMsg.dplyLineRefNum_B1.clear();
                        } else if (!refCpoDtlList.isEmpty()) {
                            // QC#15866 2016/11/09 Add Start
                            // childLineMsg.refCpoDtlLineNum_B1.setValue(refCpoDtlList.get(0));
                            // childLineMsg.refCpoDtlLineSubNum_B1.setValue(refCpoDtlList.get(1));
                            // childLineMsg.dplyLineRefNum_B1.setValue(getDplyLineRefNumRtrn(lineMsg));
                            ZYPEZDItemValueSetter.setValue(childLineMsg.refCpoDtlLineNum_B1,refCpoDtlList.get(0));
                            ZYPEZDItemValueSetter.setValue(childLineMsg.refCpoDtlLineSubNum_B1,refCpoDtlList.get(1));
                            ZYPEZDItemValueSetter.setValue(childLineMsg.dplyLineRefNum_B1,refCpoDtlList.get(2));
                            // QC#15866 2016/11/09 Add End
                        } else {
                            childLineMsg.refCpoDtlLineNum_B1.clear();
                            childLineMsg.refCpoDtlLineSubNum_B1.clear();
                            childLineMsg.dplyLineRefNum_B1.clear();
                        }

                        BigDecimal mmPk = null;
                        if (ZYPCommonFunc.hasValue(childLineMsg.svcMachMstrPk_B1)) {
                            mmPk = childLineMsg.svcMachMstrPk_B1.getValue();
                        } else {
                            mmPk = convLineChild.xxRowNum_B1.getValue();
                        }
                        mmPkToConvLineNumNew.put(mmPk, new String[]{childLineMsg.cpoDtlLineNum_B1.getValue(), childLineMsg.cpoDtlLineSubNum_B1.getValue()});

                        subIndex++;
                        lineSubNum++;
                        if (i + subIndex >= pMsg.rtnDtl.getValidCount()) {
                            break;
                        }
                        childLineMsg = pMsg.rtnDtl.no(i + subIndex);
                    }
                    i += (subIndex - 1);
                }
                lineMsg.dsCpoLineNum_B1.setValue(new BigDecimal(dsCpoLineNum));
                // REF_CPO_DTL_LINE_NUM
                if (!refCpoDtlList.isEmpty()) {
                    // QC#15866 2016/11/09 Add Start
                    // lineMsg.refCpoDtlLineNum_B1.setValue(refCpoDtlList.get(0));
                    // lineMsg.refCpoDtlLineSubNum_B1.setValue(refCpoDtlList.get(1));
                    // lineMsg.dplyLineRefNum_B1.setValue(getDplyLineRefNumRtrn(lineMsg));
                    ZYPEZDItemValueSetter.setValue(lineMsg.refCpoDtlLineNum_B1,refCpoDtlList.get(0));
                    ZYPEZDItemValueSetter.setValue(lineMsg.refCpoDtlLineSubNum_B1,refCpoDtlList.get(1));
                    ZYPEZDItemValueSetter.setValue(lineMsg.dplyLineRefNum_B1,refCpoDtlList.get(2));
                    // QC#15866 2016/11/09 Add End
                } else {
                    lineMsg.refCpoDtlLineNum_B1.clear();
                    lineMsg.refCpoDtlLineSubNum_B1.clear();
                    lineMsg.dplyLineRefNum_B1.clear();
                }

                BigDecimal mmPk = null;
                if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_B1)) {
                    mmPk = lineMsg.svcMachMstrPk_B1.getValue();
                } else {
                    mmPk = convLine.xxRowNum_B1.getValue();
                }
                mmPkToConvLineNumNew.put(mmPk, new String[]{lineMsg.cpoDtlLineNum_B1.getValue(), lineMsg.cpoDtlLineSubNum_B1.getValue()});

            } else {
                lineMsg.dsCpoLineNum_B1.setValue(new BigDecimal(dsCpoLineNum));
                lineMsg.cpoDtlLineSubNum_B1.setValue("001");
                lineMsg.dsCpoLineSubNum_B1.clear();
                // REF_CPO_DTL_LINE_NUM
                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_B1.getValue())) {
                    lineMsg.refCpoDtlLineNum_B1.clear();
                    lineMsg.refCpoDtlLineSubNum_B1.clear();
                    lineMsg.dplyLineRefNum_B1.clear();
                } else if (!refCpoDtlList.isEmpty()) {
                    // QC#15866 2016/11/09 Add Start
                    // lineMsg.refCpoDtlLineNum_B1.setValue(refCpoDtlList.get(0));
                    // lineMsg.refCpoDtlLineSubNum_B1.setValue(refCpoDtlList.get(1));
                    // lineMsg.dplyLineRefNum_B1.setValue(getDplyLineRefNumRtrn(lineMsg));
                    ZYPEZDItemValueSetter.setValue(lineMsg.refCpoDtlLineNum_B1,refCpoDtlList.get(0));
                    ZYPEZDItemValueSetter.setValue(lineMsg.refCpoDtlLineSubNum_B1,refCpoDtlList.get(1));
                    ZYPEZDItemValueSetter.setValue(lineMsg.dplyLineRefNum_B1,refCpoDtlList.get(2));
                    // QC#15866 2016/11/09 Add Start
                } else {
                    lineMsg.refCpoDtlLineNum_B1.clear();
                    lineMsg.refCpoDtlLineSubNum_B1.clear();
                    lineMsg.dplyLineRefNum_B1.clear();
                }

                BigDecimal mmPk = null;
                if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_B1)) {
                    mmPk = lineMsg.svcMachMstrPk_B1.getValue();
                } else {
                    mmPk = convLine.xxRowNum_B1.getValue();
                }
                mmPkToConvLineNumNew.put(mmPk, new String[]{lineMsg.cpoDtlLineNum_B1.getValue(), lineMsg.cpoDtlLineSubNum_B1.getValue()});
            }
            prePosnNum = lineMsg.dsOrdPosnNum_B1.getValue();
            dsCpoLineNum++;
        }
    }
    /**
     * isSetMerchandise
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return boolean
     */
    private static boolean isSetMerchandise(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdse = getMdseWithRgtnStsCd(glblCmpyCd, mdseCd);
        if (mdse == null) {
            return false;
        }
        return S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET);
    }

    /**
     * select merchandise master data with registration status code.
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    public static MDSETMsg getMdseWithRgtnStsCd(String glblCmpyCd, String mdseCd) {

        final MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);

        if (mdseTMsg == null) {
            return null;
        }

        final String rgtnStsCd = mdseTMsg.rgtnStsCd.getValue();
        if (asList(RGTN_STS.READY_FOR_ORDER_TAKING, RGTN_STS.TERMINATED).contains(rgtnStsCd)) {
            return mdseTMsg;
        } else {
            return null;
        }
    }

    /**
     * select merchandise data from merchandise master using NWXMdseTMsgThreadLocalCache#get()
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    public static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        return NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }

    /**
     * getNextCpoDtlLineNum
     * @param lineNum String
     * @return String
     */
    public static String getNextCpoDtlLineNum(String lineNum) {

        char[] digit1 = S21StringUtil.subStringByLength(lineNum, 0, 1).toCharArray();
        int digit23 = Integer.parseInt(S21StringUtil.subStringByLength(lineNum, 1, 2));

        // increment line number
        digit23++;
        digit23 = digit23 % 100;
        if (digit23 == 0) {
            if (digit1[0] == 0x0039) {
                digit1[0] = 0x0041;
            } else {
                digit1[0]++;
            }
        }

        return String.valueOf(digit1) + ZYPCommonFunc.leftPad(String.valueOf(digit23), 2, "0");
    }

    /**
     * isSameLineByDsCpoLineNum
     * @param lineMsg1 NWZC150001_APMsg
     * @param lineMsg2 NWZC150001_APMsg
     * @return boolean
     */
    private static boolean isSameLineByDsCpoLineNum(NWZC150001_APMsg lineMsg1, NWZC150001_APMsg lineMsg2) {

        if (!S21StringUtil.isEquals(lineMsg1.dsOrdPosnNum_A1.getValue(), lineMsg2.dsOrdPosnNum_A1.getValue())) {
            return false;
        }
        if (compareBigDecimal(lineMsg1.dsCpoLineNum_A1.getValue(), lineMsg2.dsCpoLineNum_A1.getValue()) != 0) {
            return false;
        }
        return true;
    }

    /**
     * isSameLineByDsCpoRtrnLineNum
     * @param lineMsg1 NWZC150001_rtnDtlPMsg
     * @param lineMsg2 NWZC150001_rtnDtlPMsg
     * @return boolean
     */
    private static boolean isSameLineByDsCpoRtrnLineNum(NWZC150001_rtnDtlPMsg lineMsg1, NWZC150001_rtnDtlPMsg lineMsg2) {

        if (!S21StringUtil.isEquals(lineMsg1.dsOrdPosnNum_B1.getValue(), lineMsg2.dsOrdPosnNum_B1.getValue())) {
            return false;
        }
        if (compareBigDecimal(lineMsg1.dsCpoLineNum_B1.getValue(), lineMsg2.dsCpoLineNum_B1.getValue()) != 0) {
            return false;
        }
        return true;
    }

    /**
     * compare to bigdecimal
     * @param source source value
     * @param target target value
     * @return result (0, > 0 , < 0)
     */
    public static int compareBigDecimal(BigDecimal source, BigDecimal target) {

        if (source == null) {
            if (target == null) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (target == null) {
                return 1;
            } else {
                return source.compareTo(target);
            }
        }
    }

    /**
     * getMaxPosnNum
     * @param bizMsg NWAL1830CMsg
     * @param configCatg String
     * @return int
     */
    public static int getMaxPosnNum(NWAL1830CMsg bizMsg, String configCatg) {
        int maxPosnNum = 0;
        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getMaxPosnNum(bizMsg, configCatg);
        if (ssmResult.isCodeNormal()) {
            String maxLineNum = (String) ssmResult.getResultObject();
            if (maxLineNum != null) {
                maxPosnNum = Integer.parseInt(maxLineNum);
            }
        }
        return maxPosnNum;
    }

    /**
     * getMaxLineNum
     * @param bizMsg NWAL1830CMsg
     * @param configCatg String
     * @return String
     */
    public static String getMaxLineNum(NWAL1830CMsg bizMsg, String configCatg) {
        String maxLineNum = "000";
        if (CONFIG_CATG.OUTBOUND.equals(configCatg)) {
            S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getMaxLineNumOutbound(bizMsg);

            if (ssmResult.isCodeNormal()) {
                maxLineNum = (String) ssmResult.getResultObject();
            }

        } else {
            S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getMaxLineNumInbound(bizMsg);

            if (ssmResult.isCodeNormal()) {
                maxLineNum = (String) ssmResult.getResultObject();
            }
        }
        if (maxLineNum == null) {
            maxLineNum = "000";
        }
        return maxLineNum;
    }

    /**
     * getBaseComponentDtlNum
     * @param pMsg NWZC150001PMsg
     * @param dsOrdPosnNum String
     * @param configCatgCd String
     * @return List<String>
     */
    private static List<String> getBaseComponentDtlNum(NWZC150001PMsg pMsg, String dsOrdPosnNum, String configCatgCd) {
        List<String> dtlLineList = new ArrayList<String>(2);
        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                NWZC150001_APMsg lineMsg = pMsg.A.no(i);
                if (dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_A1.getValue()) && ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_A1.getValue())) {
                    dtlLineList.add(lineMsg.cpoDtlLineNum_A1.getValue());
                    dtlLineList.add(lineMsg.cpoDtlLineSubNum_A1.getValue());
                    dtlLineList.add(getDplyLineRefNum(lineMsg)); // QC#15866 2016/11/09 Add
                }
            }
        } else {
            for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
                NWZC150001_rtnDtlPMsg rmaLineMsg = pMsg.rtnDtl.no(i);
                if (dsOrdPosnNum.equals(rmaLineMsg.dsOrdPosnNum_B1.getValue()) && ZYPConstant.FLG_ON_Y.equals(rmaLineMsg.baseCmptFlg_B1.getValue())) {
                    dtlLineList.add(rmaLineMsg.cpoDtlLineNum_B1.getValue());
                    dtlLineList.add(rmaLineMsg.cpoDtlLineSubNum_B1.getValue());
                    dtlLineList.add(getDplyLineRefNumRtrn(rmaLineMsg)); // QC#15866 2016/11/09 Add
                }
            }
        }
        return dtlLineList;
    }

    /**
     * getDplyLineRefNum
     * @param dtlPMsg NWZC150001_APMsg
     * @return String
     */
    private static String getDplyLineRefNum(NWZC150001_APMsg dtlPMsg) {

        String dsOrdPosnNum = dtlPMsg.dsOrdPosnNum_A1.getValue();
        BigDecimal dsCpoLineNum = dtlPMsg.dsCpoLineNum_A1.getValue();
        BigDecimal dsCpoLineSubNum = dtlPMsg.dsCpoLineSubNum_A1.getValue();
        StringBuilder xxLineNum = new StringBuilder();
        xxLineNum.append(dsOrdPosnNum);
        xxLineNum.append(PERIOD);
        xxLineNum.append(dsCpoLineNum);
        if (dsCpoLineSubNum != null) {
            xxLineNum.append(PERIOD);
            xxLineNum.append(dsCpoLineSubNum);
        }
        return xxLineNum.toString();
    }

    /**
     * getDplyLineRefNumRtrn
     * @param dtlPMsg NWZC150001_rtnDtlPMsg
     * @return String
     */
    private static String getDplyLineRefNumRtrn(NWZC150001_rtnDtlPMsg dtlPMsg) {

        String dsOrdPosnNum = dtlPMsg.dsOrdPosnNum_B1.getValue();
        BigDecimal dsCpoLineNum = dtlPMsg.dsCpoLineNum_B1.getValue();
        BigDecimal dsCpoLineSubNum = dtlPMsg.dsCpoLineSubNum_B1.getValue();
        StringBuilder xxLineNum = new StringBuilder();
        xxLineNum.append(dsOrdPosnNum);
        xxLineNum.append(PERIOD);
        xxLineNum.append(dsCpoLineNum);
        if (dsCpoLineSubNum != null) {
            xxLineNum.append(PERIOD);
            xxLineNum.append(dsCpoLineSubNum);
        }
        return xxLineNum.toString();
    }

    /**
     * getSlsRepRoleTp
     * @param bizMsg NWAL1830CMsg
     * @param dsOrdTpCd String
     * @return String
     */
    public static String getSlsRepRoleTp(NWAL1830CMsg bizMsg, String dsOrdTpCd) {
        DS_ORD_TP_PROC_DFNTMsg ordTpProcDfn = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(ordTpProcDfn.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTpProcDfn.dsOrdTpCd, dsOrdTpCd);
        ordTpProcDfn = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(ordTpProcDfn);
        if (ordTpProcDfn == null) {
            return null;
        }
        String lineBizTpCd = ordTpProcDfn.lineBizTpCd.getValue();
        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
            return LINE_BIZ_ROLE_TP.ESS_WRITER;
        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
            return LINE_BIZ_ROLE_TP.LFS_WRITER;
        } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
            return LINE_BIZ_ROLE_TP.PPS_WRITER;
        }
        return null;
    }

    /**
     * getMachMstrListForLine
     * @param bizMsg NWAL1830CMsg
     * @param lineNum String
     * @param lineSubNum String
     * @return List<NWAL1830_MCMsg>
     */
    public static List<NWAL1830_MCMsg> getMachMstrListForLine(NWAL1830CMsg bizMsg, String lineNum, String lineSubNum) {
        List<NWAL1830_MCMsg> machMstrList = new  ArrayList<NWAL1830_MCMsg>();
        for (int i = 0; i < bizMsg.M.getValidCount(); i++) {
            NWAL1830_MCMsg machMstr = bizMsg.M.no(i);
            if (lineNum.equals(machMstr.cpoDtlLineNum.getValue()) && lineSubNum.equals(machMstr.cpoDtlLineSubNum.getValue())) {
                machMstrList.add(machMstr);
            }
        }
        return machMstrList;
    }

    /**
     * getNewLineNum
     * @param mmPk BigDecimal
     * @param mmPkToConvLineNumNew Map<BigDecimal, String[]>
     * @return String[]
     */
    public static String[] getNewLineNum(BigDecimal mmPk, Map<BigDecimal, String[]> mmPkToConvLineNumNew) {

        if (mmPkToConvLineNumNew.containsKey(mmPk)) {
            return mmPkToConvLineNumNew.get(mmPk);
        }
        return new String[]{"000", "000"};
    }

    /**
     * getOrigLine
     * @param dtlPMsg NWZC150001_APMsg
     * @param checkLineList List<NWAL1830_BCMsg>
     * @return NWAL1830_BCMsg
     */
    public static NWAL1830_BCMsg getOrigLine(NWZC150001_APMsg dtlPMsg, List<NWAL1830_BCMsg> checkLineList) {
        return getOrigLine(dtlPMsg.cpoDtlLineNum_A1.getValue(), dtlPMsg.cpoDtlLineSubNum_A1.getValue(), checkLineList);
    }

    /**
     * getOrigLine
     * @param dtlPMsg NWZC150001_rtnDtlPMsg
     * @param checkLineList List<NWAL1830_BCMsg>
     * @return NWAL1830_BCMsg
     */
    public static NWAL1830_BCMsg getOrigLine(NWZC150001_rtnDtlPMsg dtlPMsg, List<NWAL1830_BCMsg> checkLineList) {
        return getOrigLine(dtlPMsg.cpoDtlLineNum_B1.getValue(), dtlPMsg.cpoDtlLineSubNum_B1.getValue(), checkLineList);
    }


    /**
     * getOrigLine
     * @param lineNum String
     * @param lineSubNum String
     * @param checkLineList List<NWAL1830_BCMsg>
     * @return NWAL1830_BCMsg
     */
    public static NWAL1830_BCMsg getOrigLine(String lineNum, String lineSubNum, List<NWAL1830_BCMsg> checkLineList) {
        NWAL1830_BCMsg convLine = null;
        for (NWAL1830_BCMsg bbizMsg : checkLineList)  {
            if (bbizMsg.cpoDtlLineNum_B1.getValue().equals(lineNum)
                    && bbizMsg.cpoDtlLineSubNum_B1.getValue().equals(lineSubNum)) {
                convLine = bbizMsg;
                break;
            }
        }
        return convLine;
    }
    
    // 2017/12/27 S21_NA#23204 ADD START
    public static String getMdseCdFromRtnDtlByLineNum(String lineNum, String lineSubNum, NWZC150001_rtnDtlPMsgArray rtnDtl){
        String mdseCd = null;
        for (int i = 0; i < rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg dtlPMsg = rtnDtl.no(i);
            if (dtlPMsg.cpoDtlLineNum_B1.getValue().equals(lineNum)
                    && dtlPMsg.cpoDtlLineSubNum_B1.getValue().equals(lineSubNum)) {
                mdseCd = dtlPMsg.mdseCd_B1.getValue();
                break;
            }
        }
        return mdseCd;
    }
    // 2017/12/27 S21_NA#23204 ADD END

    // QC#27004
    public static boolean isNotReturnable(String mdseCd, NWAL1830CMsg bizMsg) {
        String rmaReqTpCd = NWAL1830Query.getInstance().getRmaReqTpCd(mdseCd);
        return S21StringUtil.isEquals(RMA_REQ_TP.NOT_RETURNABLE, rmaReqTpCd);
    }

    // QC#50556 2019/05/31 Add Start
    public static void setRtlWhCdAsBaseComponent(NWZC150001PMsg pMsg) {
        NWZC150001_rtnDtlPMsg baseComponentLineMsg = null;
        String predsOrdPosnNum = null;
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg lineMsg = pMsg.rtnDtl.no(i);
            String dsOrdPosnNum = lineMsg.dsOrdPosnNum_B1.getValue();
            // get Base Component Line
            if (!S21StringUtil.isEquals(dsOrdPosnNum, predsOrdPosnNum)) {
                baseComponentLineMsg = getBaseComponentLineForInbound(pMsg, dsOrdPosnNum);
                predsOrdPosnNum = dsOrdPosnNum;
            }
            if (baseComponentLineMsg == null) {
                continue;
            }
            // QC#56509 2020/04/14 Add Start
            if(!ZYPCommonFunc.hasValue(lineMsg.rtlWhCd_B1)){
                continue;
            }
            // QC#56509 2020/04/14 Add End
            ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_B1, baseComponentLineMsg.rtlWhCd_B1);
            ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhCd_B1, baseComponentLineMsg.rtlSwhCd_B1);// QC#56558 2020/04/14 Add
            // 2019/09/05 QC#53295 Add Start
            StringBuilder sb = new StringBuilder();
            sb.append(lineMsg.rtlWhCd_B1.getValue());
            sb.append(lineMsg.rtlSwhCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.invtyLocCd_B1, sb.toString());
            // 2019/09/05 QC#53295 Add End
        }
    }

    public static NWZC150001_rtnDtlPMsg getBaseComponentLineForInbound(NWZC150001PMsg pMsg, String dsOrdPosnNum) {
        for (int j = 0; j < pMsg.rtnDtl.getValidCount(); j++) {
            NWZC150001_rtnDtlPMsg rmaLineMsg = pMsg.rtnDtl.no(j);
            if (dsOrdPosnNum.equals(rmaLineMsg.dsOrdPosnNum_B1.getValue()) && ZYPConstant.FLG_ON_Y.equals(rmaLineMsg.baseCmptFlg_B1.getValue())) {
                return rmaLineMsg;
            }
        }
        return null;
    }
    // QC#50556 2019/05/31 Add End

    // QC#54747 2019/12/13 Add Start
    /**
     * Call NWZC1570 Pricing API (01:Get Price List Mode)
     * @param bizMsg NWAL1830CMsg
     * @param prcCtxTp Price Ctx Type
     * @param isCallPrcListField Called Price List Field
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfGetPriceListMode(NWAL1830CMsg bizMsg, NWZC150001PMsg pHdrMsg, String prcCtxTp, boolean isCallPrcListField) {

        DS_ORD_TP_PROC_DFNTMsg ordTpProcDfn = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(ordTpProcDfn.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTpProcDfn.dsOrdTpCd, pHdrMsg.dsOrdTpCd);
        ordTpProcDfn = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(ordTpProcDfn);
        if (ordTpProcDfn == null) {
            return null;
        }

        NWZC157001PMsg pMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCtxTpCd, prcCtxTp);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, pHdrMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, pHdrMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, ordTpProcDfn.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, pHdrMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.csmpNum, pHdrMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, pHdrMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, pHdrMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.coaBrCd, bizMsg.coaBrCd_OH);
        if (isCallPrcListField) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
        }

        // call NWZC1570 Pricing API
        new NWZC157001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return null;
            }
        }

        return pMsg;
    }

    /**
     * Get Price Category Desc Text
     * @param bizMsg NWAL1830CMsg
     * @param prcCatgCd Price Category Code
     * @return Price Category Desc Text
     */
    public static String getPrcCatgNm(NWAL1830CMsg bizMsg, String prcCatgCd) {

        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, prcCatgCd);

        prcCatgTMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(prcCatgTMsg);

        if (prcCatgTMsg == null) {
            return null;
        }

        return prcCatgTMsg.prcCatgNm.getValue();
    }

    // QC#54747 2019/12/13 Add End

    // 2020/05/14 QC#56509-1 Add Start
    /**
     * <pre>
     * is warehouse required.
     * </pre>
     * @param glblCmpyCd global company Code
     * @param mdseCd merchandise code for check.
     * @return needs warehouse or not.
     */
    public static boolean needsWh(String glblCmpyCd, NWAL1830_BCMsg lineMsg) {

        if (isSetMerchandise(glblCmpyCd, lineMsg.mdseCd_B1.getValue())) {
            return true;
        }
        if (ZYPConstant.FLG_ON_Y.equals(lineMsg.invtyCtrlFlg_B1.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * Call NWZC1800 Default WH API For RMA
     * @param pMsg NWZC180001PMsg
     * @param bizMsg NWAL1830CMsg
     * @param lineMsg NWAL1830_BCMsg
     * @return has API error : false
     */
    public static boolean callDefWhApiForRma(NWZC180001PMsg pMsg, NWAL1830CMsg bizMsg, NWAL1830_BCMsg lineMsg) { // 2018/01/29 S21_NA#19808 Mod  // 2018/09/20 S21_NA#28199 Add Param glblMsg

        NWAL1830_ACMsg configMsg = null;
        for (int n = 0; n < bizMsg.A.getValidCount(); n++) {
            String configPosNum = bizMsg.A.no(n).dsOrdPosnNum_A1.getValue();
            if (S21StringUtil.isEquals(lineMsg.dsOrdPosnNum_B1.getValue(), configPosNum)) {
                configMsg = bizMsg.A.no(n);
                break;
            }
        }
        MDSETMsg mdseTMsg = NWAL1830CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_B1.getValue());

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, configMsg.dsOrdCatgCd_OH.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, configMsg.dsOrdTpCd_OH.getValue());
        String defaultRsnCd = ZYPCodeDataUtil.getVarCharConstValue(NWAL1830_LOAN_RTRN_RSN_CD, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, defaultRsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, configMsg.shipToPostCd_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, lineMsg.ordQty_B1);
        BigDecimal svcMachMstrPk = lineMsg.svcMachMstrPk_B1.getValue();
        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        }

        new NWZC180001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }
        if (!ZYPCommonFunc.hasValue(pMsg.rtlWhCd)) {
            String glblCmpyCd = pMsg.glblCmpyCd.getValue();
            String slsDt = pMsg.slsDt.getValue();
            String dsOrdCatgCd = pMsg.dsOrdCatgCd.getValue();
            String dsOrdTpCd = pMsg.dsOrdTpCd.getValue();
            String dsRtrnRsnCd = pMsg.dsRtrnRsnCd.getValue();
            String postCd = pMsg.postCd.getValue();
            String mdseCd =pMsg.mdseCd.getValue();
            BigDecimal ordQty = pMsg.ordQty.getValue();
            BigDecimal svcConfigMstrPk = configMsg.svcConfigMstrPk_A1.getValue();
            String rtlWhCd = pMsg.rtlWhCd.getValue();
            String serNum = lineMsg.serNum_B1.getValue();

            Map<String, Object> getBaseCmpnt = NWXC150001DsCheck.getBaseComponentInfoForRma(//
                    glblCmpyCd, slsDt, dsOrdCatgCd, dsOrdTpCd, dsRtrnRsnCd, postCd, mdseCd, ordQty, //
                    svcConfigMstrPk, svcMachMstrPk, rtlWhCd, serNum, true);
                ZYPEZDItemValueSetter.setValue(pMsg.rtlWhCd, (String) getBaseCmpnt.get("RTL_WH_CD"));
        }
        return true;
    }

    /**
     * Get Retail Warehouse Name
     * @param bizMsg NWAL1830CMsg
     * @param rtlWhCd Retail Warehouse Code
     * @return Retail Warehouse Name
     */
    public static String getRtlWhNm(NWAL1830CMsg bizMsg, String rtlWhCd) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtlWhCd);
        rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg == null) {
            return null;
        }

        return rtlWhTMsg.rtlWhNm.getValue();
    }
    // 2020/05/14 QC#56509-1 Add End
}
