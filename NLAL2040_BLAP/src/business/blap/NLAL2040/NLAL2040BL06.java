/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */

package business.blap.NLAL2040;

import static business.blap.NLAL2040.constant.NLAL2040Constant.AGE;
import static business.blap.NLAL2040.constant.NLAL2040Constant.AGE_FROM;
import static business.blap.NLAL2040.constant.NLAL2040Constant.AGE_TO;
import static business.blap.NLAL2040.constant.NLAL2040Constant.COLCLEAR;
import static business.blap.NLAL2040.constant.NLAL2040Constant.COLSAVE;
import static business.blap.NLAL2040.constant.NLAL2040Constant.DISP_SUBMIT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.DT_ACT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.DT_FROM;
import static business.blap.NLAL2040.constant.NLAL2040Constant.EFF_FROM_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.EFF_THRU_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.FROM_ELPS_MTH_AOT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.FROM_MTR_CNT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.GLBL_CMPY_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.INVTY_OWNR_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.MAX_DISP_LINE;
import static business.blap.NLAL2040.constant.NLAL2040Constant.MDL_ID;
import static business.blap.NLAL2040.constant.NLAL2040Constant.METER;
import static business.blap.NLAL2040.constant.NLAL2040Constant.METER_FROM;
import static business.blap.NLAL2040.constant.NLAL2040Constant.METER_TO;
import static business.blap.NLAL2040.constant.NLAL2040Constant.MODEL;
import static business.blap.NLAL2040.constant.NLAL2040Constant.MODEL_SETUP;
import static business.blap.NLAL2040.constant.NLAL2040Constant.MTR_REQ_MDL_FLG;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLAL2040_DEF_EFF_THRU_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLAM0014E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLAM1295E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLAM1296E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLAM1348E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLAM1349E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLBM1231E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLCM0123E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLCM0125E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLZM2278E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NMAM0836E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NPAM0006E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NPAM1212E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NPAM1232E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NPAM1483E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NPAM1484E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NPAM1485E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NPAM1486E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NPAM1487E;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SEARCH;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SQ_ID;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SUBMIT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.TERMINATED_EFF_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.TO_ELPS_MTH_AOT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.TO_MTR_CNT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.T_MDL_NM;
import static business.blap.NLAL2040.constant.NLAL2040Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLAL2040.common.NLAL2040CommonLogic;
import business.db.DEF_SWH_BY_MDLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 * 12/07/2016   CITS            R.Shimamoto     Update          QC#13056
 * 12/14/2016   CITS            R.Shimamoto     Update          QC#13056-1
 * 12/27/2016   CITS            T.Kikuhara      Update          QC#13056-2
 * 01/25/2016   CITS            T.Kikuhara      Update          QC#17178
 * 06/13/2017   CITS            T.Kikuhara      Update          QC#19087
 * 07/03/2018   CITS            S.Katsuma       Update          QC#24622
 * 07/03/2018   CITS            Y.Iwasaki       Update          QC#25827
 * 01/22/2018	CITS			R.Shimamoto		Update			QC#29944
 * 01/29/2019   CITS            M.Naito         Update          QC#29944-1
 *</pre>
 */
public class NLAL2040BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if (SUBMIT.equals(screenAplID)) {
                doProcess_NLAL2040_SUBMIT((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (COLCLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            } else if (COLSAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NLAL2040CMsg) cMsg, (NLAL2040SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * SUBMIT
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     */
    private void doProcess_NLAL2040_SUBMIT(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        if (!checkBeforeSubmit(cMsg, sMsg)) {
            return;
        }

        insertUpdateLine(cMsg, sMsg);
        
        // 01/23/2019 QC#
        if (!checkFromZeroDb(cMsg, sMsg)) {
        	cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
        	return;
        }

        NLAL2040CommonLogic.search(cMsg, sMsg, SEARCH);
    }

    /**
     * Check Before SUBMIT
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     */
    private boolean checkBeforeSubmit(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        // check Record is Updated Or Not
        if (!checkRecUpd(cMsg, sMsg)) {
            cMsg.setMessageInfo(NLCM0123E);
            return false;
        }

        NLAL2040CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            int errRow = i % MAX_DISP_LINE;

            // 01/22/2019 R.Shimamoto QC#29944 Add Start
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).fromMtrCnt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).toMtrCnt_A1)
            		&& sMsg.A.no(i).fromMtrCnt_A1.getValue().equals(sMsg.A.no(i).toMtrCnt_A1.getValue())) {
            	NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                cMsg.A.no(errRow).fromMtrCnt_A1.setErrorInfo(1, NLAM1348E, new String[] {METER });
                cMsg.A.no(errRow).toMtrCnt_A1.setErrorInfo(1, NLAM1348E, new String[] {METER });
                return false;
            }

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).fromElpsMthAot_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).toElpsMthAot_A1)
            		&& sMsg.A.no(i).fromElpsMthAot_A1.getValue().equals(sMsg.A.no(i).toElpsMthAot_A1.getValue())) {
            	NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                cMsg.A.no(errRow).fromElpsMthAot_A1.setErrorInfo(1, NLAM1348E, new String[] {AGE });
                cMsg.A.no(errRow).toElpsMthAot_A1.setErrorInfo(1, NLAM1348E, new String[] {AGE});
                return false;
            }

            //////////////////////////////
            // Duplicate Check
            //////////////////////////////
            // Meter
//            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
//
//            	boolean errFlg = false;
//
//            	if (j != i && ZYPCommonFunc.hasValue(sMsg.A.no(i).toMtrCnt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(j).fromMtrCnt_A1)
//            			&& ZYPCommonFunc.hasValue(sMsg.A.no(i).mdlId_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(j).mdlId_A1)
//            			&& ZYPCommonFunc.hasValue(sMsg.A.no(i).invtyOwnrCd_AS) && ZYPCommonFunc.hasValue(sMsg.A.no(j).invtyOwnrCd_AS)
//            			&& sMsg.A.no(i).mdlId_A1.getValue().equals(sMsg.A.no(j).mdlId_A1.getValue())
//            			&& sMsg.A.no(i).invtyOwnrCd_AS.getValue().equals(sMsg.A.no(j).invtyOwnrCd_AS.getValue())) {

//            		if (sMsg.A.no(i).mdlId_A1.getValue().equals(sMsg.A.no(j).mdlId_A1.getValue())
//            				&& sMsg.A.no(i).sqId_A1.getValue().equals(sMsg.A.no(j).sqId_A1.getValue())) {
//            			continue;
//            		}
//
//            		if (sMsg.A.no(i).toMtrCnt_A1.getValue().compareTo(sMsg.A.no(j).fromMtrCnt_A1.getValue()) != -1) {
//            			// Duplicate Error
//            			NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
//            			cMsg.A.no(errRow).fromMtrCnt_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(i).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue()});
////            			cMsg.A.no(j).fromMtrCnt_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(j).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue() });
//            			cMsg.A.no(errRow).toMtrCnt_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(i).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue() });
////            			cMsg.A.no(j).toMtrCnt_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(j).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue() });
//                        errFlg = true;
//            		}
//            		if (chkEffTerm(sMsg.A.no(i).fromMtrCnt_A1.getValue(),
//                            sMsg.A.no(i).toMtrCnt_A1.getValue(),
//                            sMsg.A.no(j).fromMtrCnt_A1.getValue(),
//                            sMsg.A.no(j).toMtrCnt_A1.getValue())) {
//
//            			NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
//            			cMsg.A.no(errRow).fromMtrCnt_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(i).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue()});
//            			cMsg.A.no(errRow).toMtrCnt_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(i).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue()});
//            			errFlg = true;
//                    }
//            		
//            	}
//            	if (errFlg) {
//            		cMsg.setMessageInfo(NLAM1349E, new String[] { });
//            		return false;
//            	}
//            }

            // Age
//            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
//
//            	boolean errFlg = false;

//            	if (j != i && ZYPCommonFunc.hasValue(sMsg.A.no(i).toElpsMthAot_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(j).fromElpsMthAot_A1)
//            			&& ZYPCommonFunc.hasValue(sMsg.A.no(i).mdlId_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(j).mdlId_A1)
//            			&& ZYPCommonFunc.hasValue(sMsg.A.no(i).invtyOwnrCd_AS) && ZYPCommonFunc.hasValue(sMsg.A.no(j).invtyOwnrCd_AS)
//            			&& sMsg.A.no(i).mdlId_A1.getValue().equals(sMsg.A.no(j).mdlId_A1.getValue())
//            			&& sMsg.A.no(i).invtyOwnrCd_AS.getValue().equals(sMsg.A.no(j).invtyOwnrCd_AS.getValue())) {

//            		if (sMsg.A.no(i).mdlId_A1.getValue().equals(sMsg.A.no(j).mdlId_A1.getValue())
//            				&& sMsg.A.no(i).sqId_A1.getValue().equals(sMsg.A.no(j).sqId_A1.getValue())) {
//            			continue;
//            		}
//
//            		if (sMsg.A.no(i).toElpsMthAot_A1.getValue().compareTo(sMsg.A.no(j).fromElpsMthAot_A1.getValue()) != -1) {
//            			// Duplicate Error
//            			NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
//            			cMsg.A.no(errRow).fromElpsMthAot_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(i).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue() });
////            			cMsg.A.no(j).fromElpsMthAot_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(j).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue() });
//            			cMsg.A.no(errRow).toElpsMthAot_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(i).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue() });
////            			cMsg.A.no(j).toElpsMthAot_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(j).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue() });
//                        errFlg = true;
//            		}
//            		if (chkEffTerm(sMsg.A.no(i).fromElpsMthAot_A1.getValue(),
//                            sMsg.A.no(i).toElpsMthAot_A1.getValue(),
//                            sMsg.A.no(j).fromElpsMthAot_A1.getValue(),
//                            sMsg.A.no(j).toElpsMthAot_A1.getValue())) {
//
//            			NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
//            			cMsg.A.no(errRow).fromElpsMthAot_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(i).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue()});
//            			cMsg.A.no(errRow).toElpsMthAot_A1.setErrorInfo(1, NLAM1349E, new String[] {sMsg.A.no(i).t_MdlNm_A1.getValue(),  sMsg.A.no(i).invtyOwnrCd_AS.getValue()});
//            			errFlg = true;
//                    }
//            		
//            	}
//            	if (errFlg) {
//            		cMsg.setMessageInfo(NLAM1349E, new String[] { });
//            		return false;
//            	}
//            }
            // 01/22/2019 R.Shimamoto QC#29944 Add End

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A1)) {
                NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                cMsg.A.no(errRow).effFromDt_A1.setErrorInfo(1, NMAM0836E, new String[] {DT_FROM });
                return false;
            }

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).t_MdlNm_A1)) {
                NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                cMsg.A.no(errRow).t_MdlNm_A1.setErrorInfo(1, NMAM0836E, new String[] {MODEL });
                return false;
            }

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdlId_A1)
                && ZYPDateUtil.compare(sMsg.A.no(i).effFromDt_A1.getValue(), ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue())) < 0) {
                NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                cMsg.A.no(errRow).effFromDt_A1.setErrorInfo(1, NLBM1231E, new String[] {DT_FROM });
                return false;
            }

            String checkEffThruDt = TERMINATED_EFF_DT;
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A1)) {
                checkEffThruDt = sMsg.A.no(i).effThruDt_A1.getValue();
            }
            if (ZYPDateUtil.compare(sMsg.A.no(i).effFromDt_A1.getValue(), checkEffThruDt) > 0) {
                NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                cMsg.A.no(errRow).effFromDt_A1.setErrorInfo(1, NLAM1296E, new String[] {DT_ACT });
                return false;
            }

            // check Model Name Exist in DB
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
            ssmParam.put(T_MDL_NM, sMsg.A.no(i).t_MdlNm_A1.getValue());
            S21SsmEZDResult result = NLAL2040Query.getInstance().checkModelExist(ssmParam);

            if (result.isCodeNormal()) {
                int queryResCnt = result.getQueryResultCount();
                if (queryResCnt > 1) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).t_MdlNm_A1.setErrorInfo(1, NPAM1483E, new String[] {MODEL });
                    return false;
                }

                List<Map<String, Object>> modelList = (List<Map<String, Object>>) result.getResultObject();
                BigDecimal modelId = (BigDecimal) modelList.get(0).get(MDL_ID);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdlId_A1, modelId);
                String mtrReqMdlFlg = (String) modelList.get(0).get(MTR_REQ_MDL_FLG);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mtrReqMdlFlg_A1, mtrReqMdlFlg);

            } else {
                NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                cMsg.A.no(errRow).t_MdlNm_A1.setErrorInfo(1, NLZM2278E, new String[] {MODEL });
                return false;
            }

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).mtrReqMdlFlg_A1.getValue())) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).fromMtrCnt_A1)) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).fromMtrCnt_A1.setErrorInfo(1, NPAM1484E);
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).toMtrCnt_A1)) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).toMtrCnt_A1.setErrorInfo(1, NPAM1484E);
                    return false;
                }

                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).fromElpsMthAot_A1)) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).fromElpsMthAot_A1.setErrorInfo(1, NPAM1485E);
                    return false;
                }
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).toElpsMthAot_A1)) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).toElpsMthAot_A1.setErrorInfo(1, NPAM1485E);
                    return false;
                }

                if (sMsg.A.no(i).fromMtrCnt_A1.getValue().compareTo(sMsg.A.no(i).toMtrCnt_A1.getValue()) > 0) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).fromMtrCnt_A1.setErrorInfo(1, NPAM1212E, new String[] {METER_FROM, METER_TO });
                    return false;
                }
            }
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).mtrReqMdlFlg_A1.getValue())) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).fromElpsMthAot_A1)) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).fromElpsMthAot_A1.setErrorInfo(1, NPAM1486E);
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).toElpsMthAot_A1)) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).toElpsMthAot_A1.setErrorInfo(1, NPAM1486E);
                    return false;
                }

                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).fromMtrCnt_A1)) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).fromMtrCnt_A1.setErrorInfo(1, NPAM1487E);
                    return false;
                }
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).toMtrCnt_A1)) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).toMtrCnt_A1.setErrorInfo(1, NPAM1487E);
                    return false;
                }

                if (sMsg.A.no(i).fromElpsMthAot_A1.getValue().compareTo(sMsg.A.no(i).toElpsMthAot_A1.getValue()) > 0) {
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    cMsg.A.no(errRow).fromElpsMthAot_A1.setErrorInfo(1, NPAM1212E, new String[] {AGE_FROM, AGE_TO });
                    return false;
                }

            }
        }

        // check duplicate record
        if (!checkDuplicate(cMsg, sMsg)) {
            return false;
        }

        if (!checkDuplicateByDb(cMsg, sMsg)) {
            return false;
        }

        return true;
    }


    /**
     * Check Record is Updated Or Not
     * @param cMsg
     * @param sMsg
     * @return true if updated
     */
    private boolean checkRecUpd(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        boolean bRetrun = false;

        // Import Data is check OK
        if (ZYPCommonFunc.hasValue(sMsg.xxRsltFlg) && ZYPConstant.FLG_ON_Y.equals(sMsg.xxRsltFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, sMsg.xxRsltFlg);
            return true;
        }

        // if delete record is exist check OK
        if (sMsg.B != null && sMsg.B.getValidCount() > 0) {
            return true;
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).mdlId_A1) || !ZYPCommonFunc.hasValue(cMsg.A.no(i).sqId_A1)) {
                bRetrun = true;
                continue;
            }
            if (!cMsg.A.no(i).effFromDt_A1.getValue().equals(sMsg.A.no(i).effFromDt_A1.getValue())) {
                bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A1)
                && !cMsg.A.no(i).effThruDt_A1.getValue().equals(sMsg.A.no(i).effThruDt_A1.getValue())) {
                bRetrun = true;
                break;
            }
            if (!cMsg.A.no(i).t_MdlNm_A1.getValue().equals(sMsg.A.no(i).t_MdlNm_A1.getValue())) {
                bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).svcSegDescTxt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).svcSegDescTxt_A1)
                && !cMsg.A.no(i).svcSegDescTxt_A1.getValue().equals(sMsg.A.no(i).svcSegDescTxt_A1.getValue())) {
                bRetrun = true;
                break;
            }
            // 01/22/2019 R.Shimamoto QC#29944 Mod Start.
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).fromMtrCnt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).fromMtrCnt_A1)) {
            	bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).fromMtrCnt_A1) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).fromMtrCnt_A1)) {
            	bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).fromMtrCnt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).fromMtrCnt_A1)
                && !cMsg.A.no(i).fromMtrCnt_A1.getValue().equals(sMsg.A.no(i).fromMtrCnt_A1.getValue())) {
                bRetrun = true;
                break;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).toMtrCnt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).toMtrCnt_A1)) {
            	bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).toMtrCnt_A1) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).toMtrCnt_A1)) {
            	bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).toMtrCnt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).toMtrCnt_A1)
                && !cMsg.A.no(i).toMtrCnt_A1.getValue().equals(sMsg.A.no(i).toMtrCnt_A1.getValue())) {
                bRetrun = true;
                break;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).fromElpsMthAot_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).fromElpsMthAot_A1)) {
            	bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).fromElpsMthAot_A1) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).fromElpsMthAot_A1)) {
            	bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).fromElpsMthAot_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).fromElpsMthAot_A1)
                && !cMsg.A.no(i).fromElpsMthAot_A1.getValue().equals(sMsg.A.no(i).fromElpsMthAot_A1.getValue())) {
                bRetrun = true;
                break;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).toElpsMthAot_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).toElpsMthAot_A1)) {
            	bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).toElpsMthAot_A1) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).toElpsMthAot_A1)) {
            	bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).toElpsMthAot_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).toElpsMthAot_A1)
                && !cMsg.A.no(i).toElpsMthAot_A1.getValue().equals(sMsg.A.no(i).toElpsMthAot_A1.getValue())) {
                bRetrun = true;
                break;
            }
            // 01/22/2019 R.Shimamoto QC#29944 Mod End.
            if (!cMsg.A.no(i).rtlSwhCd_AS.getValue().equals(sMsg.A.no(i).rtlSwhCd_AS.getValue())) {
                bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).thirdPtyDspTpCd_AS) && ZYPCommonFunc.hasValue(sMsg.A.no(i).thirdPtyDspTpCd_AS)
                && !cMsg.A.no(i).thirdPtyDspTpCd_AS.getValue().equals(sMsg.A.no(i).thirdPtyDspTpCd_AS.getValue())) {
                bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).thirdPtyDspTpCd_AS) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).thirdPtyDspTpCd_AS)) {
                bRetrun = true;
                break;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).thirdPtyDspTpCd_AS) && ZYPCommonFunc.hasValue(sMsg.A.no(i).thirdPtyDspTpCd_AS)) {
                bRetrun = true;
                break;
            }
            // QC#13056 Add.
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).invtyOwnrCd_AS) && ZYPCommonFunc.hasValue(sMsg.A.no(i).invtyOwnrCd_AS)
                && !cMsg.A.no(i).invtyOwnrCd_AS.getValue().equals(sMsg.A.no(i).invtyOwnrCd_AS.getValue())) {
                bRetrun = true;
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).invtyOwnrCd_AS) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).invtyOwnrCd_AS)) {
                bRetrun = true;
                break;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).invtyOwnrCd_AS) && ZYPCommonFunc.hasValue(sMsg.A.no(i).invtyOwnrCd_AS)) {
                bRetrun = true;
                break;
            }
        }
        return bRetrun;
    }


    /**
     * .
     */
    private boolean checkDuplicate(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        boolean valid = true;

        for (int i = 0; i < sMsg.A.getValidCount(); ++i) {
            NLAL2040_ASMsg asMsg1 = sMsg.A.no(i);
            String effThruDt1 = asMsg1.effThruDt_A1.getValue();
            if (!ZYPCommonFunc.hasValue(effThruDt1)) {
                effThruDt1 = TERMINATED_EFF_DT;
            }
            for (int j = i+1; j < sMsg.A.getValidCount(); ++j) {
                NLAL2040_ASMsg asMsg2 = sMsg.A.no(j);
                boolean needCheck = false;

                if (ZYPCommonFunc.hasValue(asMsg1.invtyOwnrCd_AS)
                        && ZYPCommonFunc.hasValue(asMsg2.invtyOwnrCd_AS)
                        && asMsg1.mdlId_A1.getValue().compareTo(asMsg2.mdlId_A1.getValue()) == 0
                        && asMsg1.invtyOwnrCd_AS.getValue().equals(asMsg2.invtyOwnrCd_AS.getValue())) {
                    needCheck = true;
                }
                if (!ZYPCommonFunc.hasValue(asMsg1.invtyOwnrCd_AS)
                        && !ZYPCommonFunc.hasValue(asMsg2.invtyOwnrCd_AS)
                        && asMsg1.mdlId_A1.getValue().compareTo(asMsg2.mdlId_A1.getValue()) == 0) {
                    needCheck = true;
                }

                if (!needCheck) {
                    continue;
                }

                String effThruDt2 = asMsg2.effThruDt_A1.getValue();
                if (!ZYPCommonFunc.hasValue(effThruDt2)) {
                    effThruDt2 = TERMINATED_EFF_DT;
                }

                boolean dupDateRange = false;
                if (ZYPDateUtil.compare(effThruDt2, asMsg1.effFromDt_A1.getValue()) > 0
                        && ZYPDateUtil.compare(effThruDt1, asMsg2.effFromDt_A1.getValue()) >= 0) {
                    dupDateRange = true;
                }

                boolean dupMeterRange = false;
                if (ZYPConstant.FLG_ON_Y.equals(asMsg1.mtrReqMdlFlg_A1.getValue())
                        && asMsg2.toMtrCnt_A1.getValue().compareTo(asMsg1.fromMtrCnt_A1.getValue()) >= 0
                        && asMsg1.toMtrCnt_A1.getValue().compareTo(asMsg2.fromMtrCnt_A1.getValue()) >= 0) {
                    dupMeterRange = true;
                }

                boolean dupAgeRange = false;
                if (ZYPConstant.FLG_OFF_N.equals(asMsg1.mtrReqMdlFlg_A1.getValue())
                        && asMsg2.toElpsMthAot_A1.getValue().compareTo(asMsg1.fromElpsMthAot_A1.getValue()) >= 0
                        && asMsg1.toElpsMthAot_A1.getValue().compareTo(asMsg2.fromElpsMthAot_A1.getValue()) >= 0) {
                    dupAgeRange = true;
                }

                if (dupDateRange && (dupMeterRange || dupAgeRange)) {
                    sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NPAM1232E, new String[] {MODEL_SETUP });
                    sMsg.A.no(j).effFromDt_A1.setErrorInfo(1, NPAM1232E, new String[] {MODEL_SETUP });
                    NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                    valid = false;
                    break;
                }
            }
        }

        return valid;
    }

    /**
     * .
     */
    private boolean checkDuplicateByDb(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        boolean valid = true;

        // Preparing phase
        // Index list by MDL_ID
        HashMap<BigDecimal, List<Integer>> mdlIdxMap = new HashMap<BigDecimal, List<Integer>>();
        // SQ_ID list by MDL_ID
        HashMap<BigDecimal, List<String>> mdlSqMap = new HashMap<BigDecimal, List<String>>();
        for (int i = 0; i < sMsg.A.getValidCount(); ++i) {
            BigDecimal mdlId = sMsg.A.no(i).mdlId_A1.getValue();
            if (mdlId == null) {
                continue;
            }

            // Store indexes by MDL_ID
            List<Integer> idxList = mdlIdxMap.get(mdlId);
            if (idxList == null) {
                idxList = new ArrayList<Integer>();
                mdlIdxMap.put(mdlId, idxList);
            }
            idxList.add(Integer.valueOf(i));

            // Store SQ_ID by MDL_ID
            List<String> sqList = mdlSqMap.get(mdlId);
            if (sqList == null) {
                sqList = new ArrayList<String>();
                mdlSqMap.put(mdlId, sqList);
            }
            String sqId = sMsg.A.no(i).sqId_A1.getValue();
            if (ZYPCommonFunc.hasValue(sqId)) {
                sqList.add(sqId);
            }
        }
        // START 2019/01/29 M.Naito [QC#29944-1,ADD]
        // add delete line
        for (int i = 0; i < sMsg.B.getValidCount(); ++i) {
            BigDecimal mdlId = sMsg.B.no(i).mdlId_B1.getValue();
            if (mdlId == null) {
                continue;
            }
            // Store indexes by MDL_ID
            List<Integer> idxList = mdlIdxMap.get(mdlId);
            if (idxList == null) {
                idxList = new ArrayList<Integer>();
                mdlIdxMap.put(mdlId, idxList);
            }
            idxList.add(Integer.valueOf(i));

            // Store SQ_ID by MDL_ID
            List<String> sqList = mdlSqMap.get(mdlId);
            if (sqList == null) {
                sqList = new ArrayList<String>();
                mdlSqMap.put(mdlId, sqList);
            }
            String sqId = sMsg.B.no(i).sqId_B1.getValue();
            if (ZYPCommonFunc.hasValue(sqId)) {
                sqList.add(sqId);
            }
        }
        // END 2019/01/29 M.Naito [QC#29944-1,ADD]

        for (BigDecimal mdlId : mdlIdxMap.keySet()) {
            List<Integer> idxList = mdlIdxMap.get(mdlId);
            List<String> sqList = mdlSqMap.get(mdlId);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
            ssmParam.put(EFF_THRU_DT, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
            ssmParam.put(MDL_ID, mdlId);
            ssmParam.put(SQ_ID, sqList);

            S21SsmEZDResult result = NLAL2040Query.getInstance().getSwfByMdl(ssmParam);
            if (!result.isCodeNormal()) {
                continue;
            }
            // Record list on DB, which won't be updated in this transaction
            List<Map<String, Object>> dbList = (List<Map<String, Object>>) result.getResultObject();

            for (Integer i : idxList) {
                NLAL2040_ASMsg asMsg1 = sMsg.A.no(i);
                String effThruDt1 = asMsg1.effThruDt_A1.getValue();
                if (!ZYPCommonFunc.hasValue(effThruDt1)) {
                    effThruDt1 = TERMINATED_EFF_DT;
                }
                for (int j = 0; j < dbList.size(); ++j) {
                    Map<String, Object> dbMap = dbList.get(j);
                    boolean needCheck = false;

                    String sqId2 = (String) dbMap.get(SQ_ID);
                    String invtyOwnrCd2 = (String) dbMap.get(INVTY_OWNR_CD);

                    if (ZYPCommonFunc.hasValue(asMsg1.sqId_A1)
                            && asMsg1.sqId_A1.getValue().equals(sqId2)) {
                        // Recognize as same record
                        continue;
                    }
                    if (ZYPCommonFunc.hasValue(asMsg1.invtyOwnrCd_AS)
                            && ZYPCommonFunc.hasValue(invtyOwnrCd2)
                            && asMsg1.invtyOwnrCd_AS.getValue().equals(invtyOwnrCd2)) {
                        needCheck = true;
                    }
                    if (!ZYPCommonFunc.hasValue(asMsg1.invtyOwnrCd_AS)
                            && !ZYPCommonFunc.hasValue(invtyOwnrCd2)) {
                        needCheck = true;
                    }

                    if (!needCheck) {
                        continue;
                    }

                    String effFromDt2 = (String) dbMap.get(EFF_FROM_DT);
                    String effThruDt2 = (String) dbMap.get(EFF_THRU_DT);
                    if (!ZYPCommonFunc.hasValue(effThruDt2)) {
                        effThruDt2 = TERMINATED_EFF_DT;
                    }
                    boolean dupDateRange = false;
                    if (ZYPDateUtil.compare(effThruDt2, asMsg1.effFromDt_A1.getValue()) > 0
                            && ZYPDateUtil.compare(effThruDt1, effFromDt2) >= 0) {
                        dupDateRange = true;
                    }

                    BigDecimal fromMtrCnt2 = (BigDecimal) dbMap.get(FROM_MTR_CNT);
                    BigDecimal toMtrCnt2 = (BigDecimal) dbMap.get(TO_MTR_CNT);
                    boolean dupMeterRange = false;
                    if (ZYPConstant.FLG_ON_Y.equals(asMsg1.mtrReqMdlFlg_A1.getValue())
                            // START 2019/01/29 M.Naito [QC#29944-1,MOD]
//                            && toMtrCnt2.compareTo(asMsg1.fromMtrCnt_A1.getValue()) >= 0
//                            && asMsg1.toMtrCnt_A1.getValue().compareTo(fromMtrCnt2) >= 0) {
                            && (ZYPCommonFunc.hasValue(toMtrCnt2) && toMtrCnt2.compareTo(asMsg1.fromMtrCnt_A1.getValue()) >= 0)
                            && (ZYPCommonFunc.hasValue(fromMtrCnt2) && asMsg1.toMtrCnt_A1.getValue().compareTo(fromMtrCnt2) >= 0)) {
                            // END 2019/01/29 M.Naito [QC#29944-1,MOD]
                        dupMeterRange = true;
                    }

                    BigDecimal fromElpsMthAot2 = (BigDecimal) dbMap.get(FROM_ELPS_MTH_AOT);
                    BigDecimal toElpsMthAot2 = (BigDecimal) dbMap.get(TO_ELPS_MTH_AOT);
                    boolean dupAgeRange = false;
                    if (ZYPConstant.FLG_OFF_N.equals(asMsg1.mtrReqMdlFlg_A1.getValue())
                            // START 2019/01/29 M.Naito [QC#29944-1,MOD]
//                            && toElpsMthAot2.compareTo(asMsg1.fromElpsMthAot_A1.getValue()) >= 0
//                            && asMsg1.toElpsMthAot_A1.getValue().compareTo(fromElpsMthAot2) >= 0) {
                            && (ZYPCommonFunc.hasValue(toElpsMthAot2) && toElpsMthAot2.compareTo(asMsg1.fromElpsMthAot_A1.getValue()) >= 0)
                            && (ZYPCommonFunc.hasValue(fromElpsMthAot2) && asMsg1.toElpsMthAot_A1.getValue().compareTo(fromElpsMthAot2) >= 0)) {
                            // END 2019/01/29 M.Naito [QC#29944-1,MOD]
                        dupAgeRange = true;
                    }

                    if (dupDateRange && (dupMeterRange || dupAgeRange)) {
                        sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NPAM1232E, new String[] {MODEL_SETUP });
                        NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                        valid = false;
                        break;
                    }
                }
            }
        }

        return valid;
    }


    /**
    * <pre>
    * Insert Or Update Line
    * </pre>
    * @param cMsg NLAL2040CMsg
    * @param sMsg NLAL2040SMsg
    * @param eventName String
    */
    private void insertUpdateLine(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

        boolean normalFlg = true;

        // START 2018/07/03 S.Katsuma [QC#24622,ADD]
        Set<String> sqIdList = new HashSet<String>();
        // END 2018/07/03 S.Katsuma [QC#24622,ADD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLAL2040_ASMsg bizMsg = new NLAL2040_ASMsg();
            EZDMsg.copy(sMsg.A.no(i), null, bizMsg, null);

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A1)) {
                String effThruDt = ZYPCodeDataUtil.getVarCharConstValue(NLAL2040_DEF_EFF_THRU_DT, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).effThruDt_A1, effThruDt);
            }

            // Check Key Info
            boolean insert=true;
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mdlId_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).sqId_A1)) {

                // QC#17178 add start
                DEF_SWH_BY_MDLTMsg tMsg = new DEF_SWH_BY_MDLTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlId, sMsg.A.no(i).mdlId_A1);
                ZYPEZDItemValueSetter.setValue(tMsg.sqId, sMsg.A.no(i).sqId_A1);
                tMsg = (DEF_SWH_BY_MDLTMsg) EZDTBLAccessor.findByKey(tMsg);
                if (!(tMsg == null || sqIdList.contains(sMsg.A.no(i).sqId_A1.getValue()))) {
                    insert=false;
                }
            }
            
            if(insert) {
                // Get New SQ_ID
                String sqId = getNewSqId(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).mdlId_A1.getValue());
                if (ZYPCommonFunc.hasValue(sqId)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).sqId_A1, sqId);
                    sqIdList.add(sqId);

                    normalFlg = insertDefSwhByMdl(cMsg, sMsg, i);
                } else {
                    cMsg.setMessageInfo(NLCM0125E, new String[] {"DEF_SWH_BY_MDL"});
                    normalFlg = false;
                }
            } else {
                normalFlg = updateDefSwhByMdl(cMsg, sMsg, i);
            }
        }

        // delete row record
        if (normalFlg) {
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                normalFlg = logicalDeleteDefSwhByMdl(cMsg, sMsg, i);
            }
        }

        // Normal End
        if (normalFlg) {
            cMsg.setMessageInfo(ZZZM9003I, new String[] {DISP_SUBMIT });
            // Clear Import Data Flag
            cMsg.xxRsltFlg.clear();
            sMsg.xxRsltFlg.clear();
        }
    }


    /**
    * <pre>
    * Insert DEF_SWH_BY_MDL
    * </pre>
    * @param cMsg NLAL2040CMsg
    * @param sMsg NLAL2040SMsg
    */
    private boolean insertDefSwhByMdl(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg, int i) {

        DEF_SWH_BY_MDLTMsg tMsg = new DEF_SWH_BY_MDLTMsg();
        String tblName = tMsg.getTableName();

        // START 2018/07/03 S.Katsuma [QC#24622,DEL]
        // Get New SQ_ID
//        String sqId = "";
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
//        ssmParam.put(MDL_ID, sMsg.A.no(i).mdlId_A1.getValue());
//        S21SsmEZDResult result = NLAL2040Query.getInstance().getNewSqId(ssmParam);
//        if (result.isCodeNormal()) {
//            sqId = (String) result.getResultObject();
//        } else {
//            cMsg.setMessageInfo(NLCM0125E, new String[] {tblName });
//            return false;
//        }
        // END 2018/07/03 S.Katsuma [QC#24622,DEL]

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, sMsg.A.no(i).mdlId_A1);
        // START 2018/07/03 S.Katsuma [QC#24622,MOD]
//        ZYPEZDItemValueSetter.setValue(tMsg.sqId, sqId);
        ZYPEZDItemValueSetter.setValue(tMsg.sqId, sMsg.A.no(i).sqId_A1);
        // END 2018/07/03 S.Katsuma [QC#24622,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.fromMtrCnt, sMsg.A.no(i).fromMtrCnt_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.toMtrCnt, sMsg.A.no(i).toMtrCnt_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.fromElpsMthAot, sMsg.A.no(i).fromElpsMthAot_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.toElpsMthAot, sMsg.A.no(i).toElpsMthAot_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.destRtlSwhCd, sMsg.A.no(i).rtlSwhCd_AS);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyDspTpCd, sMsg.A.no(i).thirdPtyDspTpCd_AS);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, sMsg.A.no(i).effFromDt_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, sMsg.A.no(i).effThruDt_A1);
        // QC#13056 Add.
        ZYPEZDItemValueSetter.setValue(tMsg.invtyOwnrCd, sMsg.A.no(i).invtyOwnrCd_AS);

        EZDTBLAccessor.insert(tMsg);
        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NLCM0125E, new String[] {tblName });
            return false;
        }

        return true;
    }

    /**
    * <pre>
    * Update DEF_SWH_BY_MDL
    * </pre>
    * @param cMsg NLAL2040CMsg
    * @param sMsg NLAL2040SMsg
    */
    private boolean updateDefSwhByMdl(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg, int i) {

        DEF_SWH_BY_MDLTMsg tMsg = new DEF_SWH_BY_MDLTMsg();
        String tblName = tMsg.getTableName();

        tMsg = lockDefSwhByMdl(cMsg, sMsg, i);
        if (tMsg == null) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.fromMtrCnt, sMsg.A.no(i).fromMtrCnt_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.toMtrCnt, sMsg.A.no(i).toMtrCnt_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.fromElpsMthAot, sMsg.A.no(i).fromElpsMthAot_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.toElpsMthAot, sMsg.A.no(i).toElpsMthAot_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.destRtlSwhCd, sMsg.A.no(i).rtlSwhCd_AS);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyDspTpCd, sMsg.A.no(i).thirdPtyDspTpCd_AS);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, sMsg.A.no(i).effFromDt_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, sMsg.A.no(i).effThruDt_A1);
        // QC#13056 Add.
        ZYPEZDItemValueSetter.setValue(tMsg.invtyOwnrCd, sMsg.A.no(i).invtyOwnrCd_AS);

        EZDTBLAccessor.update(tMsg);
        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NLAM1295E, new String[] {tblName });
            return false;
        }

        return true;
    }


    /**
    * Note: record is supposed to be present. Even when record is not
    * found, it sets lock error message.
    * @param bizMsg NLAL2040CMsg
    * @param sMsg NLAL2040_ASMsg
    * @return tMsg if lock is successfully acquired.
    */
    private DEF_SWH_BY_MDLTMsg lockDefSwhByMdl(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg, int i) {
        DEF_SWH_BY_MDLTMsg tMsg = new DEF_SWH_BY_MDLTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, sMsg.A.no(i).mdlId_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.sqId, sMsg.A.no(i).sqId_A1);

        tMsg = (DEF_SWH_BY_MDLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        if (tMsg == null) {
            cMsg.setMessageInfo(NPAM0006E);
            return null;
        }

        // Import Data is check OK
        if (ZYPCommonFunc.hasValue(sMsg.xxRsltFlg) && ZYPConstant.FLG_ON_Y.equals(sMsg.xxRsltFlg.getValue())) {
            return tMsg;
        }

        // Check Time
        String preUpTime = sMsg.A.no(i).xxRqstTs_A1.getValue();
        String preTimeZone = sMsg.A.no(i).xxRqstTz_A1.getValue();
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        if (ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            return tMsg;
        } else {
            cMsg.setMessageInfo(NPAM0006E);
            return null;
        }
    }


    /**
    * <pre>
    * Logical Delete DEF_SWH_BY_MDL
    * </pre>
    * @param cMsg NLAL2040CMsg
    * @param sMsg NLAL2040SMsg
    */
    private boolean logicalDeleteDefSwhByMdl(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg, int i) {

        DEF_SWH_BY_MDLTMsg tMsg = new DEF_SWH_BY_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, sMsg.B.no(i).mdlId_B1);
        ZYPEZDItemValueSetter.setValue(tMsg.sqId, sMsg.B.no(i).sqId_B1);
        EZDTBLAccessor.logicalRemove(tMsg);
        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NLAM0014E);
            return false;
        }
        return true;
    }

    // START 2018/07/03 S.Katsuma [QC#24622,ADD]
    private String getNewSqId(String glblCmpyCd, BigDecimal mdlId) {
        String sqId = null;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(MDL_ID, mdlId);
        S21SsmEZDResult result = NLAL2040Query.getInstance().getNewSqId(ssmParam);
        if (result.isCodeNormal()) {
            sqId = (String) result.getResultObject();
        } else {
            sqId = null;
        }

        return sqId;
    }
    // END 2018/07/03 S.Katsuma [QC#24622,ADD]

    /**
     * checkFromZeroDb
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     * @return boolean
     */
    private boolean checkFromZeroDb(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {

    	boolean valid = true;

        for (int i = 0; i < sMsg.A.getValidCount(); ++i) {

        	String ownrCd = null;
        	if (ZYPCommonFunc.hasValue(sMsg.A.no(i).invtyOwnrCd_AS)) {
        		ownrCd = sMsg.A.no(i).invtyOwnrCd_AS.getValue();
        	}

        	if (!NLAL2040CommonLogic.checkStartZero(cMsg, sMsg.A.no(i).mdlId_A1.getValue(), ownrCd, sMsg.A.no(i).mtrReqMdlFlg_A1.getValue())) {
        		sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLAM1349E, new String[] { });
                NLAL2040CommonLogic.setErrPageLine(sMsg, cMsg, i);
                valid = false;
        	}

        }

        return valid;
    }
}
