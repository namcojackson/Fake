/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1420;

import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_DEL_FLG;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_EFF_FROM_DT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_EFF_THRU_DT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_INVTY_LOC_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_PRCH_AVAL_FLG;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_RMNF_ORD_NUM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.DB_PARAM_TECH_TOC_CD;
import static business.blap.NPAL1420.constant.NPAL1420Constant.INVTY_AVAL_QTY;
import static business.blap.NPAL1420.constant.NPAL1420Constant.MODE_CREATE;
import static business.blap.NPAL1420.constant.NPAL1420Constant.MODE_DELETE;
import static business.blap.NPAL1420.constant.NPAL1420Constant.MODE_SUBMIT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.MODE_UPDATE;
import static business.blap.NPAL1420.constant.NPAL1420Constant.NLZM2316E;
import static business.blap.NPAL1420.constant.NPAL1420Constant.NPAM0076E;
import static business.blap.NPAL1420.constant.NPAL1420Constant.NPAM1171E;
import static business.blap.NPAL1420.constant.NPAL1420Constant.NPZM0270E;
import static business.blap.NPAL1420.constant.NPAL1420Constant.REQUEST_TYPE_NEW_ALLOC;
import static business.blap.NPAL1420.constant.NPAL1420Constant.RWS_REF_NUM_RR;
import static business.blap.NPAL1420.constant.NPAL1420Constant.TIME_FORMAT;
import static business.blap.NPAL1420.constant.NPAL1420Constant.TRX_LINE_SUB_NUM;
import static business.blap.NPAL1420.constant.NPAL1420Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1420.common.NPAL1420CommonLogic;
import business.db.RMNF_ORDTMsg;
import business.db.RMNF_TASKTMsg;
import business.db.RMNF_USGTMsg;
import business.db.RTL_WHTMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC201001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NPZC127001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC201001.NLZC201001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC210001.NLZC210001;
import com.canon.cusa.s21.api.NPZ.NPZC127001.NPZC127001;
import com.canon.cusa.s21.api.NPZ.NPZC127001.constant.NPZC127001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
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
 * 1/26/2018   CITS       T.Wada               Update          QC#23072
 * 07/12/2018   CITS        T.Hakodate          Update          QC#26863
 *</pre>
 */
public class NPAL1420BL06 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NPAL1420Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NPAL1420Scrn00_CMN_Save((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
            } else if ("NPAL1420Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NPAL1420Scrn00_CMN_Delete((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
            } else if ("NPAL1420Scrn00_CMN_Submit".equals(screenAplID)) {
            	doProcess_NPAL1420Scrn00_CMN_Submit((NPAL1420CMsg) cMsg, (NPAL1420SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * checkQty
     * @param mdseCd
     * @param invtyLocCd
     * @param rmnfOrdNum
     * @param rmnfTaskNum
     * @param rmnfPrtQty
     * @param xxRtrnQty
     * @return
     */
    private String checkQty(String mdseCd, String invtyLocCd, String rmnfOrdNum, String rmnfTaskNum, BigDecimal rmnfPrtQty, BigDecimal xxRtrnQty ) {
    	BigDecimal avalQty = getInvAvalQty(mdseCd, invtyLocCd, rmnfOrdNum, rmnfTaskNum);
        if (avalQty == null) {
        	return NPAM0076E;
        } else if (avalQty.compareTo(rmnfPrtQty.add(xxRtrnQty)) < 0) {
        	return NLZM2316E;
        }
    	return null;
    }

    /**
     * validationCheck
     * @param cMsg
     * @param sMsg
     * @return
     */
    private boolean validationCheck(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        boolean isError = false;
        NPAL1420CommonLogic.copyFromCMsgOntoSmsg(cMsg, sMsg);
        
        if (!checkTechnician(cMsg.techTocCd.getValue())) {
            cMsg.techTocCd.setErrorInfo(1, NPAM0076E, new String[] {"Technician" });
            isError = true;
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
        	if (ZYPCommonFunc.hasValue(cMsg.A.no(i).rmnfPrtUsgComitFlg_A1) && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).rmnfPrtUsgComitFlg_A1.getValue())) {
        		continue;
        	}
        	// Qty Check
        	if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).rmnfPrtQty_A1)) {
        		cMsg.A.no(i).rmnfPrtQty_A1.setValue(BigDecimal.ZERO);
        	}
        	if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).rmnfPrtRelQty_A1)) {
        		cMsg.A.no(i).rmnfPrtRelQty_A1.setValue(BigDecimal.ZERO);
        	}
        	String rslt = checkQty(cMsg.A.no(i).mdseCd_A1.getValue(),
					sMsg.invtyLocCd_H.getValue(), sMsg.rmnfOrdNum.getValue(),
					sMsg.rmnfTaskNum.getValue(), cMsg.A.no(i).rmnfPrtQty_A1
							.getValue(), cMsg.A.no(i).rmnfPrtRelQty_A1.getValue());
        	if (ZYPCommonFunc.hasValue(rslt)) {
        		if (NPAM0076E.equals(rslt)) {
        			cMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {"Item" });
        		} else if(NLZM2316E.equals(rslt)) {
        			cMsg.A.no(i).rmnfPrtQty_A1.setErrorInfo(1, NLZM2316E, new String[] {"Used Qty", "Available Qty"});
        		}
                isError = true;
        	}
        }
        return isError;
    }
    /**
     * doProcess_NPAL1420Scrn00_CMN_Save
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NPAL1420Scrn00_CMN_Save(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        boolean isError = false;

        isError = validationCheck(cMsg, sMsg);

        if (isError) {
            return;
        }
        String glblCmpyCd = getGlobalCompanyCode();
        NPZC127001PMsg pMsg = null;
        if (MODE_CREATE.equals(cMsg.xxModeCd.getValue())) {
            pMsg = execTaskApi(sMsg, glblCmpyCd, MODE_CREATE);
        } else if (MODE_UPDATE.equals(cMsg.xxModeCd.getValue())) {
            if (!tryLock(sMsg, glblCmpyCd)) {
                // error
                cMsg.setMessageInfo(NPZM0270E);
                return;
            }
            pMsg = execTaskApi(sMsg, glblCmpyCd, MODE_UPDATE);
        } else {
            return;
        }
        
        // QC#30019 add start
        // update labor and parts cost.
        if (!updateRemanOrder(sMsg.rmnfOrdNum.getValue(), glblCmpyCd, sMsg.invtyLocCd_H.getValue())) {
            // error
            cMsg.setMessageInfo(NPZM0270E);
            return;
        }
        // QC#30019 add end
        
        
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                String messageId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                cMsg.setMessageInfo(messageId);
            }
            return;
        }
        cMsg.xxModeCd.setValue(MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfTaskNum, pMsg.rmnfTaskNum);
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Save" });
    }

    /**
     * doProcess_NPAL1420Scrn00_CMN_Submit
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NPAL1420Scrn00_CMN_Submit(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        boolean isError = false;

        isError = validationCheck(cMsg, sMsg);

        if (isError) {
            return;
        }
        String glblCmpyCd = getGlobalCompanyCode();
        NPZC127001PMsg pMsg = null;
        pMsg = execTaskApi(sMsg, glblCmpyCd, MODE_SUBMIT);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                String messageId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                cMsg.setMessageInfo(messageId);
            }
            return;
        }

        if(!submitProcess(cMsg, sMsg, glblCmpyCd)) {
            // error
            cMsg.setMessageInfo(NPAM1171E, new String[] {"Reman Task" });
            return;
        }

        cMsg.xxModeCd.setValue(MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfTaskNum, pMsg.rmnfTaskNum);
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
    }

    private boolean tryLock(NPAL1420SMsg sMsg, String glblCmpyCd) {
        RMNF_TASKTMsg tMsg = new RMNF_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rmnfTaskPk, sMsg.rmnfTaskPk);

        tMsg = (RMNF_TASKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            return false;
        }
        String findEzUpTime = sMsg.ezUpTime_H.getValue();
        String findEzUpTimeZone = sMsg.ezUpTimeZone_H.getValue();
        String currentEzUpTime = tMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = tMsg.ezUpTimeZone.getValue();

//        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
//            return false;
//        }
        return true;
    }

    private NPZC127001PMsg execTaskApi(NPAL1420SMsg sMsg, String glblCmpyCd, String mode) {
        NPZC127001PMsg pMsg = new NPZC127001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfOrdNum, sMsg.rmnfOrdNum);
        if (NPZC127001Constant.MODE_UPDATE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC127001Constant.MODE_UPDATE);
            ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskNum, sMsg.rmnfTaskNum);
        } else if(NPZC127001Constant.MODE_CREATE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC127001Constant.MODE_CREATE);
        } else if(MODE_SUBMIT.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, MODE_SUBMIT);
            ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskNum, sMsg.rmnfTaskNum);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskDescTxt, sMsg.rmnfTaskDescTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.techTocCd, sMsg.techTocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskStartDt, sMsg.rmnfTaskStartDt);
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskEndDt, sMsg.rmnfTaskEndDt);
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfLborAot, sMsg.rmnfLborAot);
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfLborCmntTxt, sMsg.rmnfLborCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.spclInstnCmntTxt, sMsg.spclInstnCmntTxt);
        for (int i = 0; (i < sMsg.A.getValidCount()) && (i < pMsg.remanUsgList.length()); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.remanUsgList.no(i).mdseCd, sMsg.A.no(i).mdseCd_A1);

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rmnfPrtQty_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.remanUsgList.no(i).rmnfPrtQty, sMsg.A.no(i).rmnfPrtQty_A1);
            } else {
            	ZYPEZDItemValueSetter.setValue(pMsg.remanUsgList.no(i).rmnfPrtQty, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rmnfPrtRelQty_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.remanUsgList.no(i).rmnfPrtRelQty, sMsg.A.no(i).rmnfPrtRelQty_A1);
            } else {
            	ZYPEZDItemValueSetter.setValue(pMsg.remanUsgList.no(i).rmnfPrtRelQty, BigDecimal.ZERO);
            }

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rmnfPrtUsgComitFlg_A1) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).rmnfPrtUsgComitFlg_A1.getValue())) {
            	ZYPEZDItemValueSetter.setValue(pMsg.remanUsgList.no(i).rmnfPrtUsgComitFlg, ZYPConstant.FLG_ON_Y);
            }
            else {
            	ZYPEZDItemValueSetter.setValue(pMsg.remanUsgList.no(i).rmnfPrtUsgComitFlg, ZYPConstant.FLG_OFF_N);
            }

            pMsg.remanUsgList.setValidCount(i + 1);
        }

        NPZC127001 api = new NPZC127001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }

    private void doProcess_NPAL1420Scrn00_CMN_Delete(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg) {
        NPZC127001PMsg pMsg = deleteTask(sMsg);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                String messageId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                cMsg.setMessageInfo(messageId);
            }
            return;
        }
        cMsg.xxModeCd.setValue(MODE_CREATE);
        cMsg.rmnfTaskNum.clear();
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete" });
    }

    private NPZC127001PMsg deleteTask(NPAL1420SMsg sMsg) {
        NPZC127001PMsg pMsg = new NPZC127001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfOrdNum, sMsg.rmnfOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.rmnfTaskNum, sMsg.rmnfTaskNum);

        NPZC127001 api = new NPZC127001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }

    private boolean checkTechnician(String techTocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_TECH_TOC_CD, techTocCd);
        ssmParam.put(DB_PARAM_EFF_FROM_DT, ZYPDateUtil.getSalesDate());
        ssmParam.put(DB_PARAM_EFF_THRU_DT, ZYPDateUtil.getSalesDate());
        ssmParam.put(DB_PARAM_DEL_FLG, ZYPConstant.FLG_OFF_N);
        S21SsmEZDResult result = NPAL1420Query.getInstance().getTechnician(ssmParam);

        if (result.isCodeNormal()) {
            return true;
        }
        return false;
    }

    private BigDecimal getInvAvalQty(String mdseCd, String invtyLocCd, String rmnfOrdNum, String rmnfTaskNum ) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);
        ssmParam.put(DB_PARAM_PRCH_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(DB_PARAM_INVTY_LOC_CD, invtyLocCd);
        ssmParam.put(DB_PARAM_RMNF_ORD_NUM, rmnfOrdNum);
//        ssmParam.put(DB_PARAM_RMNF_TASK_NUM, rmnfTaskNum);
//        ssmParam.put(DB_PARAM_SHPG_STS_CD, SHPG_STS.SHIPPED);

//        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
//        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        // Execute
        S21SsmEZDResult result = NPAL1420Query.getInstance().getInvAvalQty2(ssmParam);
        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            if (0 < resultMap.size()) {
                return (BigDecimal) ((Map<String, Object>) resultMap.get(0)).get(INVTY_AVAL_QTY);
            }
        }
        return null;
    }
    private boolean submitProcess(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg, String glblCmpyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);


        Map<String, String> spMap = new HashMap<String, String>();
        List<Object[]> rcvInfo = new ArrayList<Object[]>();

        // get usage parts
        S21SsmEZDResult result = NPAL1420CommonLogic.getPartsUsage(sMsg.rmnfOrdNum.getValue(), sMsg.rtlWhCd_H.getValue(), glblCmpyCd, sMsg.rmnfTaskNum.getValue());

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
                List<String> list = NPAL1420CommonLogic.getShippingPlanForPartsUsage((String) rmnfUsgMap.get("RMNF_ORD_NUM"), (String) rmnfUsgMap.get("RMNF_TASK_NUM"), (String) rmnfUsgMap.get("RMNF_USG_LINE_NUM"), glblCmpyCd);

                for (String s : list) {

                    if (!spMap.containsKey(s)) {

                        spMap.put(s, s);
                    }
                }

                // for Receiving info
                if (ZYPConstant.FLG_ON_Y.equals((String) rmnfUsgMap.get("RTRN_REQ_PRT_FLG"))) {

                    rcvInfo.add(new Object[] {(String) rmnfUsgMap.get("INVTY_LOC_CD"), rmnfUsgMap });
                }
                /////////////////////////
                // Create Adjustment
                /////////////////////////
                S21SsmEZDResult res = NPAL1420CommonLogic.getWmsAdjInfo(glblCmpyCd, (String)rmnfUsgMap.get("RMNF_USG_INVTY_LOC_CD"));

                if (res.isCodeNormal()) {
                	
                    Map<String, Object> prtInfo = (Map<String, Object>)res.getResultObject();
                    if (prtInfo != null) {

                        if (!NPAL1420CommonLogic.createAdjIf(cMsg, glblCmpyCd, (String)rmnfUsgMap.get("RMNF_ORD_NUM"),(String)rmnfUsgMap.get("MDSE_CD"),(BigDecimal)rmnfUsgMap.get("RMNF_PRT_QTY"),  
                        		prtInfo)) {
                            return false;
                        }
                    }
                }
                /////////////////////////
                // Update Reman Usg
                /////////////////////////
                //updateRmnfPrtUsgComitFlg(glblCmpyCd, (BigDecimal)rmnfUsgMap.get("RMNF_USG_PK"));
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
                List<Map<String, Object>> soDtlList = NPAL1420CommonLogic.getShippingOrderForSubmit(e.getValue(), glblCmpyCd);

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

                        if (!NPAL1420CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

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

            if (!NPAL1420CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(rcvPO))) {

                return false;
            }

            // call RWS API
            NLZC200001PMsg rws = rwsNLZC2000(rcvPO.poRcvNum.getValue(), glblCmpyCd);

            if (!NPAL1420CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(rws))) {

                return false;
            }
        }
        // unused parts return
        //List<Map<String, Object>> unUsedPartsMapList = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> unUsedPartsMapList = NPAL1420CommonLogic.getUnusedParts(sMsg.rmnfOrdNum.getValue(), glblCmpyCd, sMsg.rmnfTaskNum.getValue(), sMsg.rtlWhCd_H.getValue() );
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//        	if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rmnfPrtRelQty_A1)) {
//            	if (0 < sMsg.A.no(i).rmnfPrtRelQty_A1.getValue().intValue()) {
//        			Map<String, Object> unUsedPartsMap = new HashMap<String, Object>();
//        			unUsedPartsMap.put("RMNF_PRT_REL_QTY", sMsg.A.no(i).rmnfPrtRelQty_A1.getValue());
//        			unUsedPartsMap.put("MDSE_CD", sMsg.A.no(i).mdseCd_A1.getValue());
//        			unUsedPartsMap.put("INVTY_LOC_CD", sMsg.invtyLocCd_H.getValue());
//        			unUsedPartsMapList.add(unUsedPartsMap);
//            	}
//        	}
//        }

        if (unUsedPartsMapList != null && !unUsedPartsMapList.isEmpty()) {

            if (!unusedPartsReturnProcess(cMsg, sMsg, unUsedPartsMapList, glblCmpyCd, slsDt)) {

                return false;
            }
        }

        // Reman Usg RMNF_PRT_USG_COMIT_FLG Update
        updateRmnfPrtUsgComitFlg(glblCmpyCd, sMsg.rmnfOrdNum.getValue(),sMsg.rmnfTaskNum.getValue());

        // Reman order status update for Completed
        if (!updateRemanOrder(sMsg.rmnfOrdNum.getValue(), glblCmpyCd, sMsg.invtyLocCd_H.getValue())) {

            return false;
        }

        return true;
    }
    /**
     * updateRmnfPrtUsgComitFlg
     * @param glblCmpyCd
     * @param rmnfUsgPk
     */
	private void updateRmnfPrtUsgComitFlg(String glblCmpyCd, String rmnfOrdNum, String rmnfTaskNum) {
		List<Map<String, Object>> usgPrtMapList = NPAL1420CommonLogic.getUsgPartsForStsUpd(rmnfOrdNum, glblCmpyCd, rmnfTaskNum);

        if (usgPrtMapList != null && !usgPrtMapList.isEmpty()) {
        	for (Map<String, Object> usgPrtMap : usgPrtMapList) {
        		updateRmnfPrtUsgComitFlg(glblCmpyCd, (BigDecimal)usgPrtMap.get("RMNF_USG_PK"));
        	}

        }
	
	}
	private void updateRmnfPrtUsgComitFlg(String glblCmpyCd, BigDecimal rmnfUsgPk) {
	    RMNF_USGTMsg oldMsg = new RMNF_USGTMsg();
	    ZYPEZDItemValueSetter.setValue(oldMsg.glblCmpyCd, glblCmpyCd);
	    ZYPEZDItemValueSetter.setValue(oldMsg.rmnfUsgPk, rmnfUsgPk);
	    RMNF_USGTMsg newMsg = (RMNF_USGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(oldMsg);
        // RMNF_TOT_COST_AMT
        ZYPEZDItemValueSetter.setValue(newMsg.rmnfPrtUsgComitFlg, ZYPConstant.FLG_ON_Y);
    
	    EZDTBLAccessor.update(newMsg);
	}
	/**
	 * updateRemanOrder
	 * @param rmnfOrdNum
	 * @param glblCmpyCd
	 * @param invtyLocCd
	 * @return
	 */
	private boolean updateRemanOrder(String rmnfOrdNum , String glblCmpyCd, String invtyLocCd) {
		
	    RMNF_ORDTMsg oldMsg = new RMNF_ORDTMsg();
	    ZYPEZDItemValueSetter.setValue(oldMsg.glblCmpyCd, glblCmpyCd);
	    ZYPEZDItemValueSetter.setValue(oldMsg.rmnfOrdNum, rmnfOrdNum);
	    RMNF_ORDTMsg newMsg = (RMNF_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(oldMsg);

	    // RMNF_PRT_USG_COST_AMT
        ZYPEZDItemValueSetter.setValue(newMsg.rmnfPrtUsgCostAmt, NPAL1420CommonLogic.getRmnfPrtUsgCostAmt(glblCmpyCd, rmnfOrdNum, null));
	
        // RMNF_TOT_LBOR_COST_AMT
        ZYPEZDItemValueSetter.setValue(newMsg.rmnfTotLborCostAmt, NPAL1420CommonLogic.getRmnTotLborCostAmt(glblCmpyCd,rmnfOrdNum, null));

        // RMNF_MACH_COST_AMT
        NLXC001001GetInventoryItemCostBean parm = new NLXC001001GetInventoryItemCostBean();
    
        parm.setGlblCmpyCd(glblCmpyCd);
        parm.setInvtyLocCd(invtyLocCd);
        parm.setMdseCd(NPAL1420CommonLogic.getRmnfToCmptMdseCd(newMsg));
        parm.setQty(BigDecimal.ONE);

        parm = NLXC001001GetInventoryItemCost.getInventoryItemCost(parm);
    
        ZYPEZDItemValueSetter.setValue(newMsg.rmnfMachCostAmt, parm.getTotPrcAmt());
    
        // RMNF_TOT_COST_AMT
        ZYPEZDItemValueSetter.setValue(newMsg.rmnfTotCostAmt, NPAL1420CommonLogic.getRmnfTotCostAmt(newMsg));
    
	    EZDTBLAccessor.update(newMsg);
	
	    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newMsg.getReturnCode())) {
	
	        return false;
	    }
	
	    return true;
	}
    private boolean allocationNWZC1070ForPartsUsage(NPAL1420CMsg cMsg, Map<String, Object> rmnfUsgMap, String glblCmpyCd, String slsDt) {

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

        if (!NPAL1420CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }
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
    private boolean shippingPlanUpdateNWZC0030ForPartsUsage(NPAL1420CMsg cMsg, Map<String, Object> rmnfUsgMap, String glblCmpyCd) {

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

        if (!NPAL1420CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }
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
    private boolean createSONLZC2050(NPAL1420CMsg cMsg, String sceOrdTpCd, Map<String, String> shpgPlnNumMap, Map<String, String> retSONumMap, String glblCmpyCd) {

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

            if (!NPAL1420CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

                return false;
            }

            retSONumMap.put(pMsg.shpgPlnNum.getValue(), pMsg.soNum.getValue());

        }

        return true;
    }
    /**
     * receivingPONLZC2010ForSubmit
     * @param sMsg
     * @param dtlList
     * @param glblCmpyCd
     * @param slsDt
     * @return
     */
    private NLZC201001PMsg receivingPONLZC2010ForSubmit(NPAL1420SMsg sMsg, List<Object[]> dtlList, String glblCmpyCd, String slsDt) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, sMsg.rtlWhCd.getValue());
        rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

        NLZC201001PMsg pMsg = new NLZC201001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, SCE_ORD_TP.REMAN_PARTS_USAGE);

//        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, sMsg.rmnfOrdNum.getValue() + sMsg.rmnfTaskNum.getValue() + RWS_REF_NUM_RR);
        String rwsRefNum = getRwsRefNum(glblCmpyCd, sMsg.rmnfOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, rwsRefNum);

        ZYPEZDItemValueSetter.setValue(pMsg.poRcvFromLocTpCd, rtlWhTMsg.locTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvFromLocCd, rtlWhTMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, rtlWhTMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvDrctShipTpCd, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvEtaDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvTrxHdrNum, sMsg.rmnfOrdNum.getValue());

        for (int i = 0; i < dtlList.size(); i++) {

            Map<String, Object> rmnfUsgMap = (Map<String, Object>) dtlList.get(i)[1];
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).mdseCd, (String) rmnfUsgMap.get("MDSE_CD"));
            // QC#26945 MOD START
            //ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).stkStsCd, STK_STS.WAITING_FOR_INSPECTION);
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).stkStsCd, STK_STS.GOOD);
            // QC#26945 MOD END
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).poRcvQty, (BigDecimal) rmnfUsgMap.get("RMNF_PRT_QTY"));
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).poRcvTrxLineNum, (String) rmnfUsgMap.get("RMNF_USG_LINE_NUM"));
            // QC#26836 MOD START
            //ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).invtyLocCd, sMsg.rtlWhCd.getValue() + sMsg.rtlSwhCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).invtyLocCd, (String) rmnfUsgMap.get("RMNF_USG_RTRN_INVTY_LOC_CD"));
            // QC#26836 MOD END
            ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).shipFromInvtyLocCd, (String) rmnfUsgMap.get("RMNF_USG_INVTY_LOC_CD"));
            pMsg.OrdInfoLIst.setValidCount(i + 1);
        }

        // exec API
        new NLZC201001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }
    private String getMaxRwsRefNum(String glblCmpyCd, String rmnfOrdNum) {
    	String maxRwsRefNum = null;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("poRcvTrxHdrNum", rmnfOrdNum);
        ssmParam.put("sceOrdTpCd", SCE_ORD_TP.REMAN_PARTS_USAGE);

        // Execute
        S21SsmEZDResult result = NPAL1420Query.getInstance().getMaxRwsRefNum(ssmParam);
        if (result.isCodeNormal()) {

        	maxRwsRefNum = (String) result.getResultObject();
        }

        return maxRwsRefNum;
    }
    /**
     * 
     * @param glblCmpyCd
     * @param rmnfOrdNum
     * @return
     */
    private String getRwsRefNum(String glblCmpyCd, String rmnfOrdNum) {
    	String ret = null;

    	String maxRwsRefNum = getMaxRwsRefNum(glblCmpyCd, rmnfOrdNum);

    	if (!ZYPCommonFunc.hasValue(maxRwsRefNum)) {
    		ret = rmnfOrdNum + RWS_REF_NUM_RR + "001" ;
    	} else {
    		String curNum = maxRwsRefNum.substring(maxRwsRefNum.length() - 3 , maxRwsRefNum.length());
    		curNum = String.valueOf(Integer.parseInt(curNum));
    		int n = Integer.parseInt(curNum) + 1 ;
    		String nextNum = String.format("%03d", n);
    		ret = rmnfOrdNum + RWS_REF_NUM_RR + nextNum ;
    	}
    	
    	
    	return ret;
    }
    
    private boolean unusedPartsReturnProcess(NPAL1420CMsg cMsg, NPAL1420SMsg sMsg, List<Map<String, Object>> unUsedPartsMapList, String glblCmpyCd, String slsDt) {

        for (int i = 0; i < unUsedPartsMapList.size(); i++) {

            Map<String, Object> unUsedPartsMap = unUsedPartsMapList.get(i);

            if (!allocationNWZC1070ForSubmitBackInventory(cMsg, sMsg.rmnfOrdNum.getValue(), i + 1, unUsedPartsMap, glblCmpyCd, slsDt)) {

                return false;
            }

            if (!shippingPlanUpdateNWZC0030ForSubmitBackInventory(cMsg, sMsg.rmnfOrdNum.getValue(), i + 1, (BigDecimal) unUsedPartsMap.get("RMNF_PRT_REL_QTY"), glblCmpyCd)) {

                return false;
            }

            // Update Reman Usg
            //updateRmnfPrtUsgComitFlg(glblCmpyCd, (BigDecimal)unUsedPartsMap.get("RMNF_USG_PK"));

        }

        // get Shipping Plan Number list
        Map<String, String> spMap = NPAL1420CommonLogic.getShippingPlan(TRX_SRC_TP.REMAN_PARTS_BACK_INVENTORY, sMsg.rmnfOrdNum.getValue(), glblCmpyCd);

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

            if (!NPAL1420CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(rws))) {

                return false;
            }

            procSoSet.add(e.getValue());
        }
        
        // QC#26863 ADD START
        // REMAN_PARTS_BACK_INVENTORY AUTO CLOSE
        // SO Confirmation
        procSoSet = new HashSet<String>();
        for (Map.Entry<String, String> e : soNumMap.entrySet()) {

            if (procSoSet.contains(e.getValue())) {
                continue;
            }

            // get SO info
            List<NLZC210001PMsg> pMsgList = new ArrayList<NLZC210001PMsg>();
            List<Map<String, Object>> soDtlMapList = NPAL1420CommonLogic.getShippingOrderForSubmit(e.getValue(), glblCmpyCd);
            for (Map<String, Object> soDtlMap : soDtlMapList) {

                NLZC210001PMsg pMsg = new NLZC210001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.whCd, (String) soDtlMap.get("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.soNum, e.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY);
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
                    if (!NPAL1420CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

                        return false;
                    }
                }
            }

            procSoSet.add(e.getValue());
        }
        // QC#26863 ADD END

        return true;
    }
    private boolean allocationNWZC1070ForSubmitBackInventory(NPAL1420CMsg cMsg, String rmnfOrdNum, int lineNum, Map<String, Object> unUsedPartsMap, String glblCmpyCd, String slsDt) {

        NWZC107001PMsg pMsg = createNWZC1070CommonParam(glblCmpyCd, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.REMAN_PARTS_BACK_INVENTORY);
        ZYPEZDItemValueSetter.setValue(pMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.trxRsnCd, TRX_RSN.REMAN_PARTS_TRANSFER_BACK_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, rmnfOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, String.format("%03d", lineNum));
        ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, TRX_LINE_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) unUsedPartsMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) unUsedPartsMap.get("RMNF_USG_INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.WORK_IN_PROCESS_REMAN);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstQty, (BigDecimal) unUsedPartsMap.get("RMNF_PRT_REL_QTY"));
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, (String) unUsedPartsMap.get("RMNF_USG_INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, (String) unUsedPartsMap.get("RMNF_USG_INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) unUsedPartsMap.get("RMNF_USG_INVTY_LOC_CD"));

        // exec API
        new NWZC107001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!NPAL1420CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }
    private boolean shippingPlanUpdateNWZC0030ForSubmitBackInventory(NPAL1420CMsg cMsg, String rmnfOrdNum, int lineNum, BigDecimal rqstQty, String glblCmpyCd) {

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

        if (!NPAL1420CommonLogic.chkApiRslt(cMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

            return false;
        }

        return true;
    }

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



}
