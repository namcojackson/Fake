/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLBL3060;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSDateItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3060.common.NLBL3060CommonLogic;
import business.blap.NLBL3060.constant.NLBL3060Constant;
import business.db.SCHD_COORD_WH_PMSNTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 * 2023/04/18   Hitachi         T.Kuroda        Update          QC#61166
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public class NLBL3060BL06 extends S21BusinessHandler implements NLBL3060Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3060Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLBL3060Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_CMN_Submit START -----", null);

        NLBL3060CMsg bizMsg = (NLBL3060CMsg) cMsg;
        NLBL3060SMsg globalMsg = (NLBL3060SMsg) sMsg;

        NLBL3060CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);

        int firstErrNum = -1;
        int procCnt = 0;
        boolean errFlg = false;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            // START 2023/10/18 Y.Ogura [QC#61793, DEL]
//            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).rtlWhCd_A1)) {
//                globalMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, MESSAGE_ID.NFCM0504E.toString(), new String[] {"Retail Warehouse"});
//                errFlg = true;
//            }
            // END 2023/10/18 Y.Ogura [QC#61793, DEL]
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).schdCoordPsnCd_A1)) {
                globalMsg.A.no(i).schdCoordPsnCd_A1.setErrorInfo(1, MESSAGE_ID.NFCM0504E.toString(), new String[] {"Person Code"});
                errFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).effFromDt_A1)) {
                globalMsg.A.no(i).effFromDt_A1.setErrorInfo(1, MESSAGE_ID.NFCM0504E.toString(), new String[] {"Eff From Date"});
                errFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).effThruDt_A1)) {
                globalMsg.A.no(i).effThruDt_A1.setErrorInfo(1 ,MESSAGE_ID.NFCM0504E.toString(), new String[] {"Eff Thru Date"});
                errFlg = true;
            }
            // START 2023/10/18 Y.Ogura [QC#61793, ADD]
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).rtlWhCatgCd_A1) 
                    && !ZYPCommonFunc.hasValue(globalMsg.A.no(i).physWhCd_A1) 
                    && !ZYPCommonFunc.hasValue(globalMsg.A.no(i).rtlWhCd_A1) ) {
                globalMsg.A.no(i).rtlWhCatgCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                globalMsg.A.no(i).physWhCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                globalMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                errFlg = true;
            }
            
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).rtlWhCatgCd_A1) 
                    && ZYPCommonFunc.hasValue(globalMsg.A.no(i).physWhCd_A1) 
                    && ZYPCommonFunc.hasValue(globalMsg.A.no(i).rtlWhCd_A1) ) {
                globalMsg.A.no(i).rtlWhCatgCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                globalMsg.A.no(i).physWhCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                globalMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                errFlg = true;
                
            } else if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).rtlWhCatgCd_A1)) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).physWhCd_A1)) {
                    globalMsg.A.no(i).rtlWhCatgCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                    globalMsg.A.no(i).physWhCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                    errFlg = true;
                } else if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).rtlWhCd_A1)) {
                    globalMsg.A.no(i).rtlWhCatgCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                    globalMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                    errFlg = true;
                }
                
            } else if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).physWhCd_A1)) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).rtlWhCd_A1)) {
                    globalMsg.A.no(i).physWhCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                    globalMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1 ,MESSAGE_ID.NLBM1390E.toString());
                    errFlg = true;
                }
            }
            // END 2023/10/18 Y.Ogura [QC#61793, ADD]
            if (errFlg) {
                if (firstErrNum < 0) {
                    firstErrNum = i;
                }
                NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                return;
            }
        }

        // START 2023/04/18 T.Kuroda [QC#61166, ADD]
        // Logical remove
        for (int cnt = 0; cnt < globalMsg.B.getValidCount(); cnt++) {
            if (NLBL3060CommonLogic.isLogicalRemoveLine(globalMsg, cnt)) {
                procCnt++;

                SCHD_COORD_WH_PMSNTMsg logcRemMsg = new SCHD_COORD_WH_PMSNTMsg();
                // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                ZYPEZDItemValueSetter.setValue(logcRemMsg.schdCoordWhPmsnPk, globalMsg.B.no(cnt).schdCoordWhPmsnPk_B1);
                ZYPEZDItemValueSetter.setValue(logcRemMsg.rtlWhCatgCd, globalMsg.B.no(cnt).rtlWhCatgCd_B1);
                ZYPEZDItemValueSetter.setValue(logcRemMsg.physWhCd, globalMsg.B.no(cnt).physWhCd_B1);
                // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                ZYPEZDItemValueSetter.setValue(logcRemMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(logcRemMsg.rtlWhCd, globalMsg.B.no(cnt).rtlWhCd_B1);
                ZYPEZDItemValueSetter.setValue(logcRemMsg.schdCoordPsnCd, globalMsg.B.no(cnt).schdCoordPsnCd_B1);
                ZYPEZDItemValueSetter.setValue(logcRemMsg.effFromDt, globalMsg.B.no(cnt).effFromDt_B1);
                EZDTBLAccessor.logicalRemove(logcRemMsg);

                if (!RTNCD_NORMAL.equals(logcRemMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString(), new String[]{logcRemMsg.getTableName()});
                    NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, -1);
                    return;
                }
            }
        }
        // END   2023/04/18 T.Kuroda [QC#61166, ADD]

        // START 2023/04/18 T.Kuroda [QC#61166, MOD]
        for (int index = 0; index < globalMsg.A.getValidCount(); index++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(index).ezUpTime_A1)) {
                //--------------------
                // Update
                //--------------------
                if (NLBL3060CommonLogic.isUpdatedLine(globalMsg, index)) {
                    procCnt++;

                    EZDSDateItem effThruDt_B = NLBL3060CommonLogic.getEffThruDtBySchdCoordWhPmsnKey(globalMsg, index);

                    if (effThruDt_B != null && !effThruDt_B.getValue().equals(globalMsg.A.no(index).effThruDt_A1)) {
                        // Validate
                        if (NLBL3060CommonLogic.hasLineError(getGlobalCompanyCode(), globalMsg, index)) {
                            NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, index);
                            return;
                        }

                        // START 2023/10/18 Y.Ogura [QC#61793, DEL]
                        // Remove
//                        SCHD_COORD_WH_PMSNTMsg remMsg = new SCHD_COORD_WH_PMSNTMsg();
//                        ZYPEZDItemValueSetter.setValue(remMsg.glblCmpyCd, getGlobalCompanyCode());
//                        ZYPEZDItemValueSetter.setValue(remMsg.rtlWhCd, globalMsg.A.no(index).rtlWhCd_A1);
//                        ZYPEZDItemValueSetter.setValue(remMsg.schdCoordPsnCd, globalMsg.A.no(index).schdCoordPsnCd_A1);
//                        ZYPEZDItemValueSetter.setValue(remMsg.effFromDt, globalMsg.A.no(index).effFromDt_A1);
//                        EZDTBLAccessor.remove(remMsg);
//
//                        if (!RTNCD_NORMAL.equals(remMsg.getReturnCode())) {
//                            bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString(), new String[]{remMsg.getTableName()});
//                            NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, index);
//                            return;
//                        }
                        // END 2023/10/18 Y.Ogura [QC#61793, DEL]

                        // Check duplicate 
                        // START 2023/10/18 Y.Ogura [QC#61793, MOD]
//                        if (NLBL3060CommonLogic.isDuplicate(
//                                getGlobalCompanyCode()
//                                , globalMsg.A.no(index).rtlWhCd_A1.getValue()
//                                , globalMsg.A.no(index).schdCoordPsnCd_A1.getValue()
//                                , globalMsg.A.no(index).effFromDt_A1.getValue()
//                                , globalMsg.A.no(index).effThruDt_A1.getValue())) {
                        if (NLBL3060CommonLogic.isDuplicateForUpdate(
                                getGlobalCompanyCode()
//                                , schdCoordWhPmsnSq.toString()
                                , globalMsg.A.no(index).schdCoordWhPmsnPk_A1.getValue().toString()
                                , globalMsg.A.no(index).rtlWhCatgCd_A1.getValue()
                                , globalMsg.A.no(index).physWhCd_A1.getValue()
                                , globalMsg.A.no(index).rtlWhCd_A1.getValue()
                                , globalMsg.A.no(index).schdCoordPsnCd_A1.getValue()
                                , globalMsg.A.no(index).effFromDt_A1.getValue()
                                , globalMsg.A.no(index).effThruDt_A1.getValue() )) {
                            // END 2023/10/18 Y.Ogura [QC#61793, MOD]
                            globalMsg.A.no(index).effThruDt_A1.setErrorInfo(1, MESSAGE_ID.NFCM0063E.toString());
                            NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, index);
                            return;
                        }

                        // START 2023/10/18 Y.Ogura [QC#61793, MOD]
                        // Update
                        SCHD_COORD_WH_PMSNTMsg updateMsg = new SCHD_COORD_WH_PMSNTMsg();
                        ZYPEZDItemValueSetter.setValue(updateMsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(updateMsg.schdCoordWhPmsnPk, globalMsg.A.no(index).schdCoordWhPmsnPk_A1.getValue());
                        updateMsg = (SCHD_COORD_WH_PMSNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updateMsg);
                        
                        if (updateMsg != null) {
                            ZYPEZDItemValueSetter.setValue(updateMsg.rtlWhCatgCd, globalMsg.A.no(index).rtlWhCatgCd_A1);
                            ZYPEZDItemValueSetter.setValue(updateMsg.physWhCd, globalMsg.A.no(index).physWhCd_A1);
                            ZYPEZDItemValueSetter.setValue(updateMsg.rtlWhCd, globalMsg.A.no(index).rtlWhCd_A1);
                            ZYPEZDItemValueSetter.setValue(updateMsg.schdCoordPsnCd, globalMsg.A.no(index).schdCoordPsnCd_A1);
                            ZYPEZDItemValueSetter.setValue(updateMsg.effFromDt, globalMsg.A.no(index).effFromDt_A1);
                            ZYPEZDItemValueSetter.setValue(updateMsg.effThruDt, globalMsg.A.no(index).effThruDt_A1);
                            EZDTBLAccessor.update(updateMsg);
                            if (!RTNCD_NORMAL.equals(updateMsg.getReturnCode())) {
                                bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString(), new String[]{updateMsg.getTableName()});
                                NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, index);
                                return;
                            }
                        }
//                        // Create
//                        SCHD_COORD_WH_PMSNTMsg createMsg = new SCHD_COORD_WH_PMSNTMsg();
//                        ZYPEZDItemValueSetter.setValue(createMsg.glblCmpyCd, getGlobalCompanyCode());
//                        ZYPEZDItemValueSetter.setValue(createMsg.rtlWhCd, globalMsg.A.no(index).rtlWhCd_A1);
//                        ZYPEZDItemValueSetter.setValue(createMsg.schdCoordPsnCd, globalMsg.A.no(index).schdCoordPsnCd_A1);
//                        ZYPEZDItemValueSetter.setValue(createMsg.effFromDt, globalMsg.A.no(index).effFromDt_A1);
//                        ZYPEZDItemValueSetter.setValue(createMsg.effThruDt, globalMsg.A.no(index).effThruDt_A1);
//                        EZDTBLAccessor.create(createMsg);
//
//                        if (!RTNCD_NORMAL.equals(createMsg.getReturnCode())) {
//                            bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString(), new String[]{createMsg.getTableName()});
//                            NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, index);
//                            return;
//                        }
                        // END 2023/10/18 Y.Ogura [QC#61793, MOD]
                    }
                }
            } else {
                //--------------------
                // Create new line
                //--------------------
                procCnt++;

                // Validate
                if (NLBL3060CommonLogic.hasLineError(getGlobalCompanyCode(), globalMsg, index)) {
                    NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, index);
                    return;
                }

                // Check duplicate
                if (NLBL3060CommonLogic.isDuplicate(
                        getGlobalCompanyCode()
                        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                        , globalMsg.A.no(index).rtlWhCatgCd_A1.getValue()
                        , globalMsg.A.no(index).physWhCd_A1.getValue()
                        // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                        , globalMsg.A.no(index).rtlWhCd_A1.getValue()
                        , globalMsg.A.no(index).schdCoordPsnCd_A1.getValue()
                        , globalMsg.A.no(index).effFromDt_A1.getValue()
                        , globalMsg.A.no(index).effThruDt_A1.getValue())) {
                    globalMsg.A.no(index).effFromDt_A1.setErrorInfo(1, MESSAGE_ID.NFCM0063E.toString());
                    globalMsg.A.no(index).effThruDt_A1.setErrorInfo(1, MESSAGE_ID.NFCM0063E.toString());
                    NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, index);
                    return;
                }

                // Create
                SCHD_COORD_WH_PMSNTMsg createMsg = new SCHD_COORD_WH_PMSNTMsg();
                ZYPEZDItemValueSetter.setValue(createMsg.glblCmpyCd, getGlobalCompanyCode());
                // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                ZYPEZDItemValueSetter.setValue(createMsg.schdCoordWhPmsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_COORD_WH_PMSN_SQ));
                ZYPEZDItemValueSetter.setValue(createMsg.rtlWhCatgCd, globalMsg.A.no(index).rtlWhCatgCd_A1);
                ZYPEZDItemValueSetter.setValue(createMsg.physWhCd, globalMsg.A.no(index).physWhCd_A1);
                // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                ZYPEZDItemValueSetter.setValue(createMsg.rtlWhCd, globalMsg.A.no(index).rtlWhCd_A1);
                ZYPEZDItemValueSetter.setValue(createMsg.schdCoordPsnCd, globalMsg.A.no(index).schdCoordPsnCd_A1);
                ZYPEZDItemValueSetter.setValue(createMsg.effFromDt, globalMsg.A.no(index).effFromDt_A1);
                ZYPEZDItemValueSetter.setValue(createMsg.effThruDt, globalMsg.A.no(index).effThruDt_A1);
                EZDTBLAccessor.create(createMsg);

                if (!RTNCD_NORMAL.equals(createMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString(), new String[]{createMsg.getTableName()});
                    NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, index);
                    return;
                }
            }
        }

//        int i = 0;
//
//        for (; i < globalMsg.B.getValidCount(); i++) {
//
//            if (NLBL3060CommonLogic.isUpdatedLine(globalMsg, i)) {
//                procCnt++;
//                if (NLBL3060CommonLogic.hasLineError(getGlobalCompanyCode(), globalMsg, i)) {
//                    if (firstErrNum < 0) {
//                        firstErrNum = i;
//                    }
//                    NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
//                    return;
//                }
//                SCHD_COORD_WH_PMSNTMsg delMsg = new SCHD_COORD_WH_PMSNTMsg();
//                ZYPEZDItemValueSetter.setValue(delMsg.glblCmpyCd, getGlobalCompanyCode());
//                ZYPEZDItemValueSetter.setValue(delMsg.rtlWhCd, globalMsg.B.no(i).rtlWhCd_B1);
//                ZYPEZDItemValueSetter.setValue(delMsg.schdCoordPsnCd, globalMsg.B.no(i).schdCoordPsnCd_B1);
//                ZYPEZDItemValueSetter.setValue(delMsg.effFromDt, globalMsg.B.no(i).effFromDt_B1);
//                ZYPEZDItemValueSetter.setValue(delMsg.effThruDt, globalMsg.B.no(i).effThruDt_B1);
//                EZDTBLAccessor.remove(delMsg);
//
//                String returnCode = delMsg.getReturnCode();
//                if (!RTNCD_NORMAL.equals(returnCode)) {
//                	bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString(), new String[]{delMsg.getTableName()});
//                    if (firstErrNum < 0) {
//                        firstErrNum = i;
//                    }
//                    NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
//                    return;
//                }
//
//                if (NLBL3060CommonLogic.isDuplicate(getGlobalCompanyCode(), globalMsg.A.no(i).rtlWhCd_A1.getValue(), globalMsg.A.no(i).schdCoordPsnCd_A1.getValue(), globalMsg.A.no(i).effFromDt_A1.getValue(), globalMsg.A.no(i).effThruDt_A1.getValue())) {
//                    globalMsg.A.no(i).effThruDt_A1.setErrorInfo(1, MESSAGE_ID.NFCM0063E.toString(), new String[] {});
//                    if (firstErrNum < 0) {
//                        firstErrNum = i;
//                    }
//                    NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
//                    return;
//                }
//
//                SCHD_COORD_WH_PMSNTMsg createMsg = new SCHD_COORD_WH_PMSNTMsg();
//                ZYPEZDItemValueSetter.setValue(createMsg.glblCmpyCd, getGlobalCompanyCode());
//                ZYPEZDItemValueSetter.setValue(createMsg.rtlWhCd, globalMsg.A.no(i).rtlWhCd_A1);
//                ZYPEZDItemValueSetter.setValue(createMsg.schdCoordPsnCd, globalMsg.A.no(i).schdCoordPsnCd_A1);
//                ZYPEZDItemValueSetter.setValue(createMsg.effFromDt, globalMsg.A.no(i).effFromDt_A1);
//                ZYPEZDItemValueSetter.setValue(createMsg.effThruDt, globalMsg.A.no(i).effThruDt_A1);
//                EZDTBLAccessor.create(createMsg);
//
//                returnCode = createMsg.getReturnCode();
//                if (!RTNCD_NORMAL.equals(returnCode)) {
//                    bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString(), new String[]{createMsg.getTableName()});
//                    if (firstErrNum < 0) {
//                        firstErrNum = i;
//                    }
//                    NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
//                    return;
//                }
//            }
//        }
//
//        for (; i < globalMsg.A.getValidCount(); i++) {
//            procCnt++;
//            if (NLBL3060CommonLogic.hasLineError(getGlobalCompanyCode(), globalMsg, i)) {
//                if (firstErrNum < 0) {
//                    firstErrNum = i;
//                }
//                NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
//                return;
//            }
//
//            if (NLBL3060CommonLogic.isDuplicate(getGlobalCompanyCode(), globalMsg.A.no(i).rtlWhCd_A1.getValue(), globalMsg.A.no(i).schdCoordPsnCd_A1.getValue(), globalMsg.A.no(i).effFromDt_A1.getValue(), globalMsg.A.no(i).effThruDt_A1.getValue())) {
//                globalMsg.A.no(i).effFromDt_A1.setErrorInfo(1, MESSAGE_ID.NFCM0063E.toString(), new String[] {});
//                globalMsg.A.no(i).effThruDt_A1.setErrorInfo(1, MESSAGE_ID.NFCM0063E.toString(), new String[] {});
//                if (firstErrNum < 0) {
//                    firstErrNum = i;
//                }
//                NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
//                return;
//            }
//
//            SCHD_COORD_WH_PMSNTMsg createMsg = new SCHD_COORD_WH_PMSNTMsg();
//            ZYPEZDItemValueSetter.setValue(createMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(createMsg.rtlWhCd, globalMsg.A.no(i).rtlWhCd_A1);
//            ZYPEZDItemValueSetter.setValue(createMsg.schdCoordPsnCd, globalMsg.A.no(i).schdCoordPsnCd_A1);
//            ZYPEZDItemValueSetter.setValue(createMsg.effFromDt, globalMsg.A.no(i).effFromDt_A1);
//            ZYPEZDItemValueSetter.setValue(createMsg.effThruDt, globalMsg.A.no(i).effThruDt_A1);
//            EZDTBLAccessor.create(createMsg);
//            String returnCode = createMsg.getReturnCode();
//            if (!RTNCD_NORMAL.equals(returnCode)) {
//                bizMsg.setMessageInfo(MESSAGE_ID.NLBM1295E.toString() , new String[]{createMsg.getTableName()});
//                if (firstErrNum < 0) {
//                    firstErrNum = i;
//                }
//                NLBL3060CommonLogic.firstErrorPage(bizMsg, globalMsg, i);
//                return;
//            }
//        }

        if (procCnt == 0) {
            bizMsg.setMessageInfo(MESSAGE_ID.NFCM0764E.toString());
        }
        // END   2023/04/18 T.Kuroda [QC#61166, MOD]

        EZDDebugOutput.println(1, "----- doProcess_NLBL3060Scrn00_CMN_Submit END -----", null);
    }
}
