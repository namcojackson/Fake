/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1420;

import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_EFF_FROM_DT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_EFF_THRU_DT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_INVTY_LOC_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_LOC_STS_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_PRCH_AVAL_FLG;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_RMNF_ORD_NUM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_RMNF_TASK_NUM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_RMNF_TASK_STS_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_ROWNUM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_STK_STS_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_TECH_TOC_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.EZUPTIME;
import static business.blap.NPAL1420.constant.NPAL1420Constant.EZUPTIMEZONE;
import static business.blap.NPAL1420.constant.NPAL1420Constant.INVTY_AVAL_QTY;
import static business.blap.NPAL1420.constant.NPAL1420Constant.INVTY_LOC_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.MDSE_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.MDSE_DESC_SHORT_TXT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.MODE_CREATE;
import static business.blap.NPAL1420.constant.NPAL1420Constant.MODE_UPDATE;
import static business.blap.NPAL1420.constant.NPAL1420Constant.NPAM0002E;
import static business.blap.NPAL1420.constant.NPAL1420Constant.NPAM0076E;
import static business.blap.NPAL1420.constant.NPAL1420Constant.NPAM1577W;
import static business.blap.NPAL1420.constant.NPAL1420Constant.NPAM1351E;
import static business.blap.NPAL1420.constant.NPAL1420Constant.OPEN_STS_FLG;
import static business.blap.NPAL1420.constant.NPAL1420Constant.PRT_UNIT_COST_AMT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.PSN_FIRST_NM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.PSN_LAST_NM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_COST_PER_HOUR_AMT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_LBOR_AOT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_LBOR_CMNT_TXT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_LBOR_COST_AMT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_MAIN_UNIT_SER_NUM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_ORD_NUM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_ORD_STS_DESC_TXT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_PRT_QTY;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_TASK_DESC_TXT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_TASK_END_DT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_TASK_NUM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_TASK_PK;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_TASK_START_DT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_USG_RTL_SWH_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_USG_RTL_WH_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RTL_WH_NM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RTRN_REQ_PRT_FLG;
import static business.blap.NPAL1420.constant.NPAL1420Constant.SPACE;
import static business.blap.NPAL1420.constant.NPAL1420Constant.SPCL_INSTN_CMNT_TXT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.STD_CCY_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.TECH_NM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.TECH_NM1;
import static business.blap.NPAL1420.constant.NPAL1420Constant.TECH_NM2;
import static business.blap.NPAL1420.constant.NPAL1420Constant.TECH_TOC_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.THIS_MTH_TOT_STD_COST_AMT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RMNF_TASK_STS_CD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1420.common.NPAL1420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 * 1/29/2018   CITS       T.Wada               Update          QC#23072
 *</pre>
 */
public class NPAL1420BL02 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (("NPAL1420_INIT".equals(screenAplID)) || ("NPAL1420Scrn00_CMN_Reset".equals(screenAplID)) || ("NPAL1420Scrn00_CMN_Delete".equals(screenAplID))) {
                doProcess_NPAL1420_INIT((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
            } else if ("NPAL1420Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NPAL1420Scrn00_AddLine((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
            } else if ("NPAL1420Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NPAL1420Scrn00_DeleteLine((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
            } else if ("NPAL1420Scrn00_Search_Tech".equals(screenAplID)) {
                doProcess_NPAL1420Scrn00_Search_Tech((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
            } else if ("NPAL1420Scrn00_Search_Item".equals(screenAplID)) {
                doProcess_NPAL1420Scrn00_Search_Item((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
            } else if ("NPAL1420_NMAL6800".equals(screenAplID)) {
                doProcess_NPAL1420_NMAL6800((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
            } else if ("NPAL1420Scrn00_CMN_Save".equals(screenAplID)) {
                if ("I".equals(((NPAL1420CMsg) cMsg).getMessageKind())) {
                    doProcess_NPAL1420_INIT((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
                }
            } else if ("NPAL1420Scrn00_CMN_Submit".equals(screenAplID)) {
                if ("I".equals(((NPAL1420CMsg) cMsg).getMessageKind())) {
                    doProcess_NPAL1420_INIT((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
                }
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NPAL1420_INIT(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        if (MODE_CREATE.equals(cMsg.xxModeCd.getValue())) {
            searchNew(cMsg, sMsg);
        } else if (MODE_UPDATE.equals(cMsg.xxModeCd.getValue())) {
            searchEdit(cMsg, sMsg);
        } else {
            cMsg.xxModeCd.clear();
        }
    }

    private void searchNew(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_RMNF_ORD_NUM, cMsg.rmnfOrdNum);
        // Execute
        S21SsmEZDResult result = NPAL1420Query.getInstance().searchNew(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            if (0 < resultMap.size()) {
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);

                ZYPEZDItemValueSetter.setValue(sMsg.xxMsgTxt_WH, (String) recode.get(RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitSerNum, (String) recode.get(RMNF_MAIN_UNIT_SER_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum, (String) recode.get(RMNF_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdStsDescTxt, (String) recode.get(RMNF_ORD_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.techNm_H, (String) recode.get(TECH_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCd, (String) recode.get(RMNF_USG_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhCd, (String) recode.get(RMNF_USG_RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.rmnfCostPerHourAmt, (BigDecimal) recode.get(RMNF_COST_PER_HOUR_AMT));
                ZYPEZDItemValueSetter.setValue(sMsg.stdCcyCd_PH, (String) recode.get(STD_CCY_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.stdCcyCd_LC, (String) recode.get(STD_CCY_CD));

                ZYPEZDItemValueSetter.setValue(sMsg.invtyLocCd_H, (String) recode.get(INVTY_LOC_CD));
                // set start date
                sMsg.rmnfTaskStartDt.setValue(ZYPDateUtil.getSalesDate());

                if (RMNF_TASK_STS.CLOSED.equals((String) recode.get(RMNF_TASK_STS_CD))) {
                	cMsg.xxModeCd.clear();
                }
            }
            NPAL1420CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        } else {
            cMsg.xxModeCd.clear();
            // not has search result
            cMsg.setMessageInfo(NPAM0002E);
        }
    }

    private void searchEdit(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_RMNF_ORD_NUM, cMsg.rmnfOrdNum);
        ssmParam.put(DB_PARAM_RMNF_TASK_NUM, cMsg.rmnfTaskNum);
        ssmParam.put(DB_PARAM_RMNF_TASK_STS_CD, RMNF_TASK_STS.OPEN);
//        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
//        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.A.length());
//        ssmParam.put(DB_PARAM_SHPG_STS_CD, SHPG_STS.SHIPPED);

        // Execute
        S21SsmEZDResult result = NPAL1420Query.getInstance().searchEdit(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            for (int i = 0; (i < resultMap.size()) && (i < sMsg.A.length()); i++) {
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);

                if (i == 0) {
                    // Header info
                    ZYPEZDItemValueSetter.setValue(sMsg.xxMsgTxt_WH, (String) recode.get(RTL_WH_NM));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfMainUnitSerNum, (String) recode.get(RMNF_MAIN_UNIT_SER_NUM));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdNum, (String) recode.get(RMNF_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfOrdStsDescTxt, (String) recode.get(RMNF_ORD_STS_DESC_TXT));
                    ZYPEZDItemValueSetter.setValue(sMsg.techNm_H, (String) recode.get(TECH_NM1));
                    // task
                    ZYPEZDItemValueSetter.setValue(sMsg.xxMsgTxt_TS, (String) recode.get(RMNF_ORD_NUM) + "-" + (String) recode.get(RMNF_TASK_NUM));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfTaskNum, (String) recode.get(RMNF_TASK_NUM));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfTaskDescTxt, (String) recode.get(RMNF_TASK_DESC_TXT));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfTaskStartDt, (String) recode.get(RMNF_TASK_START_DT));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfTaskEndDt, (String) recode.get(RMNF_TASK_END_DT));
                    ZYPEZDItemValueSetter.setValue(sMsg.spclInstnCmntTxt, (String) recode.get(SPCL_INSTN_CMNT_TXT));
                    // Labor
                    ZYPEZDItemValueSetter.setValue(sMsg.techTocCd, (String) recode.get(TECH_TOC_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.techNm_L, (String) recode.get(TECH_NM2));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfLborAot, (BigDecimal) recode.get(RMNF_LBOR_AOT));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfLborCmntTxt, (String) recode.get(RMNF_LBOR_CMNT_TXT));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfCostPerHourAmt, (BigDecimal) recode.get(RMNF_COST_PER_HOUR_AMT));
                    ZYPEZDItemValueSetter.setValue(sMsg.stdCcyCd_PH, (String) recode.get(STD_CCY_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfLborCostAmt, (BigDecimal) recode.get(RMNF_LBOR_COST_AMT));
                    ZYPEZDItemValueSetter.setValue(sMsg.stdCcyCd_LC, (String) recode.get(STD_CCY_CD));
                    // Parts Usage
                    ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCd, (String) recode.get(RMNF_USG_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhCd, (String) recode.get(RMNF_USG_RTL_SWH_CD));

                    ZYPEZDItemValueSetter.setValue(sMsg.ezUpTime_H, (String) recode.get(EZUPTIME));
                    ZYPEZDItemValueSetter.setValue(sMsg.ezUpTimeZone_H, (String) recode.get(EZUPTIMEZONE));
                    ZYPEZDItemValueSetter.setValue(sMsg.invtyLocCd_H, (String) recode.get(INVTY_LOC_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.rmnfTaskPk, (BigDecimal) recode.get(RMNF_TASK_PK));

                    if (RMNF_TASK_STS.CLOSED.equals((String) recode.get(RMNF_TASK_STS_CD))) {
                    	cMsg.xxModeCd.clear();
                    }
                }
                if (ZYPCommonFunc.hasValue((String) recode.get(MDSE_CD))) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_A1, (String) recode.get(MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseDescShortTxt_A1, (String) recode.get(MDSE_DESC_SHORT_TXT));
                    if (ZYPConstant.FLG_ON_Y.equals((String) recode.get(RTRN_REQ_PRT_FLG))) {
                        sMsg.A.no(i).rtrnReqPrtFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                    } else {
                        sMsg.A.no(i).rtrnReqPrtFlg_A1.clear();
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prtUnitCostAmt_A1, (BigDecimal) recode.get(PRT_UNIT_COST_AMT));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).invtyAvalQty_A1, (BigDecimal) recode.get(INVTY_AVAL_QTY));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rmnfPrtQty_A1, (BigDecimal) recode.get(RMNF_PRT_QTY));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prtUnitCostAmt_T, getUsedCost(sMsg.A.no(i)));

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rmnfPrtRelQty_A1, (BigDecimal) recode.get("RMNF_PRT_REL_QTY"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rmnfPrtUsgComitFlg_A1, (String) recode.get("RMNF_PRT_USG_COMIT_FLG"));

                    sMsg.A.no(i).xxNum_A1.setValue(i);
                    sMsg.A.no(i).xxSetFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
                    sMsg.A.setValidCount(i + 1);
                }

            }
            ZYPEZDItemValueSetter.setValue(sMsg.prtUnitCostAmt, calculationTotalPartsCost(sMsg));

            NPAL1420CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
//        } else {
//            cMsg.xxModeCd.clear();
//            // not has search result
//            cMsg.setMessageInfo(NPAM0002E);
        }
    }

    private BigDecimal getUsedCost(NPAL1420_ASMsg asMsg) {
        if ((!ZYPCommonFunc.hasValue(asMsg.prtUnitCostAmt_A1)) || (!ZYPCommonFunc.hasValue(asMsg.rmnfPrtQty_A1))) {
            return BigDecimal.ZERO;
        }
        return asMsg.prtUnitCostAmt_A1.getValue().multiply(asMsg.rmnfPrtQty_A1.getValue());
    }

    private void doProcess_NPAL1420Scrn00_AddLine(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        if (sMsg.A.length() == sMsg.A.getValidCount()) {
            cMsg.setMessageInfo(NPAM1351E, new String[] {sMsg.A.length() + "" });
            return;
        }
        NPAL1420CommonLogic.copyFromCMsgOntoSmsg(cMsg, sMsg);

        int index = sMsg.A.getValidCount();
        sMsg.A.no(index).xxNum_A1.setValue(getMaxRownumber(sMsg));
        sMsg.A.no(index).xxSetFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
        sMsg.A.no(index).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
        sMsg.A.no(index).mdseCd_A1.clear();
        sMsg.A.no(index).mdseDescShortTxt_A1.clear();
        sMsg.A.no(index).rtrnReqPrtFlg_A1.clear();
        sMsg.A.no(index).prtUnitCostAmt_A1.clear();
        sMsg.A.no(index).invtyAvalQty_A1.clear();
        sMsg.A.no(index).rmnfPrtQty_A1.clear();
        sMsg.A.no(index).prtUnitCostAmt_T.clear();
        sMsg.A.setValidCount(index + 1);
        NPAL1420CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private int getMaxRownumber(NPAL1420SMsg sMsg) {
        int num = -1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (num < sMsg.A.no(i).xxNum_A1.getValueInt()) {
                num = sMsg.A.no(i).xxNum_A1.getValueInt();
            }
        }
        return (num + 1);
    }

    private BigDecimal calculationTotalPartsCost(NPAL1420SMsg sMsg) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prtUnitCostAmt_T)) {
                total = total.add(sMsg.A.no(i).prtUnitCostAmt_T.getValue());
            }
        }
        return total;
    }

    private void doProcess_NPAL1420Scrn00_DeleteLine(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        NPAL1420CommonLogic.copyFromCMsgOntoSmsg(cMsg, sMsg);

        List<NPAL1420_ASMsg> list = new ArrayList<NPAL1420_ASMsg>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).xxSetFlg_A1.getValue())) {
                    cMsg.setMessageInfo(NPAM1577W, new String[] {"Save" });
                }
            } else {
                NPAL1420_ASMsg line = new NPAL1420_ASMsg();
                EZDMsg.copy(sMsg.A.no(i), null, line, null);
                list.add(line);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            EZDMsg.copy(list.get(i), null, sMsg.A.no(i), null);
        }
        sMsg.A.setValidCount(list.size());
        NPAL1420CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NPAL1420Scrn00_Search_Tech(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_TECH_TOC_CD, cMsg.techTocCd);
        ssmParam.put(DB_PARAM_EFF_FROM_DT, ZYPDateUtil.getSalesDate());
        ssmParam.put(DB_PARAM_EFF_THRU_DT, ZYPDateUtil.getSalesDate());
        S21SsmEZDResult result = NPAL1420Query.getInstance().getTechnician(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            if (0 < resultMap.size()) {
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);
                ZYPEZDItemValueSetter.setValue(cMsg.techNm_L, connectTechName((String) recode.get(PSN_FIRST_NM), (String) recode.get(PSN_LAST_NM)));
            }
        } else {
            cMsg.techTocCd.setErrorInfo(1, NPAM0076E, new String[] {"Technician" });
            cMsg.techNm_L.clear();
        }
    }

    private void doProcess_NPAL1420Scrn00_Search_Item(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        int index = cMsg.xxNum_H.getValueInt();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.A.no(index).mdseCd_A1);
        ssmParam.put(DB_PARAM_PRCH_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(DB_PARAM_INVTY_LOC_CD, sMsg.invtyLocCd_H);
//        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
//        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        ssmParam.put(DB_PARAM_RMNF_ORD_NUM, sMsg.rmnfOrdNum);
//        ssmParam.put(DB_PARAM_RMNF_TASK_NUM, sMsg.rmnfTaskNum);
//        ssmParam.put(DB_PARAM_SHPG_STS_CD, SHPG_STS.SHIPPED);
        // Execute
        S21SsmEZDResult result = NPAL1420Query.getInstance().getInvAvalQty2(ssmParam);
        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            if (0 < resultMap.size()) {
                NPAL1420CommonLogic.copyFromCMsgOntoSmsg(cMsg, sMsg);

                Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseDescShortTxt_A1, (String) recode.get(MDSE_DESC_SHORT_TXT));
                if (ZYPConstant.FLG_ON_Y.equals((String) recode.get(RTRN_REQ_PRT_FLG))) {
                    sMsg.A.no(index).rtrnReqPrtFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                } else {
                    sMsg.A.no(index).rtrnReqPrtFlg_A1.clear();
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prtUnitCostAmt_A1, (BigDecimal) recode.get(THIS_MTH_TOT_STD_COST_AMT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).invtyAvalQty_A1, (BigDecimal) recode.get(INVTY_AVAL_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prtUnitCostAmt_T, getUsedCost(sMsg.A.no(index)));
                //ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rmnfPrtRelQty_A1,  (BigDecimal) recode.get(INVTY_AVAL_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.prtUnitCostAmt, calculationTotalPartsCost(sMsg));
                NPAL1420CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            }
        } else {
            cMsg.A.no(index).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {"Item" });
            cMsg.A.no(index).mdseDescShortTxt_A1.clear();
            cMsg.A.no(index).rtrnReqPrtFlg_A1.clear();
            cMsg.A.no(index).prtUnitCostAmt_A1.clear();
            cMsg.A.no(index).invtyAvalQty_A1.clear();
            cMsg.A.no(index).prtUnitCostAmt_T.clear();
        }
    }

    private void doProcess_NPAL1420_NMAL6800(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        int index = cMsg.xxNum_H.getValueInt();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.A.no(index).mdseCd_A1);
        ssmParam.put(DB_PARAM_INVTY_LOC_CD, sMsg.invtyLocCd_H);
//        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
//        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        ssmParam.put(DB_PARAM_RMNF_ORD_NUM, sMsg.rmnfOrdNum);
//        ssmParam.put(DB_PARAM_RMNF_TASK_NUM, sMsg.rmnfTaskNum);
//        ssmParam.put(DB_PARAM_SHPG_STS_CD, SHPG_STS.SHIPPED);

        // Execute
        S21SsmEZDResult result = NPAL1420Query.getInstance().getInvAvalQty1(ssmParam);
        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            if (0 < resultMap.size()) {
                NPAL1420CommonLogic.copyFromCMsgOntoSmsg(cMsg, sMsg);

                Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);
                if (ZYPConstant.FLG_ON_Y.equals((String) recode.get(RTRN_REQ_PRT_FLG))) {
                    sMsg.A.no(index).rtrnReqPrtFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                } else {
                    sMsg.A.no(index).rtrnReqPrtFlg_A1.clear();
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prtUnitCostAmt_A1, (BigDecimal) recode.get(THIS_MTH_TOT_STD_COST_AMT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).invtyAvalQty_A1, (BigDecimal) recode.get(INVTY_AVAL_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prtUnitCostAmt_T, getUsedCost(sMsg.A.no(index)));
                ZYPEZDItemValueSetter.setValue(sMsg.prtUnitCostAmt, calculationTotalPartsCost(sMsg));
                NPAL1420CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            }
        }
    }

    private String connectTechName(String firstName, String lastName) {
        StringBuilder sb = new StringBuilder();
        if (ZYPCommonFunc.hasValue(firstName)) {
            sb.append(firstName);
        }
        if (ZYPCommonFunc.hasValue(lastName)) {
            if (0 < sb.length()) {
                sb.append(SPACE);
            }
            sb.append(lastName);
        }
        return sb.toString();
    }
}
