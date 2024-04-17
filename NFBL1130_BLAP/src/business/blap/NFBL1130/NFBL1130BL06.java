/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1130;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL1130.common.NFBL1130CommonLogic;
import business.blap.NFBL1130.constant.NFBL1130Constant;
import business.db.CM_ACRL_WRITE_OFFTMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Accrual Write-off Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/10/10   Hitachi         J.Kim           Update          QC#5521
 * 2016/12/07   Fujitsu         H.Ikeda         Update          QC#15823
 * 2019/01/30   Fujitsu         H.Ikeda         Update          QC#30057
 * 2019/03/12   Hitachi         Y.Takeno        Update          QC#30729
 * 2021/03/12   CITS            H.Dimay         Update          QC#57040
 * 2021/04/05   CITS            K.Suzuki        Update          QC#57040
 * 2022/03/28   Hitachi         A.Kohinata      Update          QC#57935(56588)
 * 2022/04/05   Hitachi         R.Onozuka       Update          QC#57935
 * 2022/07/14   Hitachi         K.Kim           Update          QC#60149
 * 2022/08/05   Hitachi         A.Kohinata      Update          QC#59245
 * </pre>
 */
public class NFBL1130BL06 extends S21BusinessHandler implements NFBL1130Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();
            NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
            NFBL1130SMsg glblMsg = (NFBL1130SMsg) sMsg;

            // +++++ [START] : Scrn00
            if ("NFBL1130Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_CMN_Submit(cMsg, sMsg);
            // START 2016/10/10 J.Kim [QC#5521,ADD]
            } else if ("NFBL1130Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NFBL1130Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if ("NFBL1130Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_DeleteSearch(bizMsg, glblMsg);
            } else if ("NFBL1130Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NFBL1130Scrn00_SaveSearch(bizMsg, glblMsg);
            // END 2016/10/10 J.Kim [QC#5521,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Scrn01
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFBL1130Scrn00_CMN_Submit
     * <dd>The method explanation: Submit button
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NFBL1130Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_CMN_Submit================================START", this);
        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        NFBL1130CommonLogic.saveDetailInfo(bizMsg, globalMsg);

        List<Integer> checkedRows = new ArrayList<Integer>();
        boolean isErr = false;
        int firstErrIndex = -1;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).wrtOffFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).wrtOffFlg_A1, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).wrtOffFlg_A1.getValue())
            && !globalMsg.A.no(i).wrtOffFlg_A1.getValue().equals(globalMsg.A.no(i).wrtOffFlg_BF.getValue())) {
                checkedRows.add(i);
                if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).wrtOffDt_A1)) {
                    globalMsg.A.no(i).wrtOffDt_A1.setErrorInfo(1, NFCM0038E);
                    if (!isErr) {
                        isErr = true;
                        firstErrIndex = i;
                    }
                }
                if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).acrlWrtOffRsnCd_S)) {
                    globalMsg.A.no(i).acrlWrtOffRsnCd_S.setErrorInfo(1, NFCM0038E);
                    if (!isErr) {
                        isErr = true;
                        firstErrIndex = i;
                    }
                }
                // START 2022/04/05 R.Onozuka [QC#57935, ADD]
                // check 9segment
                String[] coa = globalMsg.A.no(i).xxCmntTxt_A1.getValue().split("\\.");
                if(!NFBL1130CommonLogic.checkGlCodeCombination(globalMsg, i, coa)){
                    if (!isErr) {
                        isErr = true;
                        firstErrIndex = i;
                    }
                }
                // END 2022/04/05 R.Onozuka [QC#57935, ADD]
                if (!isErr) {
                    // Exclusive Control
                    // START 2019/01/30 H.Ikeda [QC#30057, MOD]
                    // START 2019/03/12 [QC#30729, MOD]
                    // List listCmAcrlWriteOff = NFBL1130CommonLogic.getExclusiveData(globalMsg.A.no(i).poNum_A1.getValue(), globalMsg.A.no(i).apVndCd_A1.getValue(), globalMsg.A.no(i).mdseCd_A1.getValue());
                    // mod start 2022/03/28 QC#57935(56588)
                    //List listCmAcrlWriteOff = NFBL1130CommonLogic.getExclusiveData(globalMsg.A.no(i).poNum_A1.getValue(), globalMsg.A.no(i).apVndCd_A1.getValue(), globalMsg.A.no(i).mdseCd_A1.getValue(), globalMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                    List listCmAcrlWriteOff = NFBL1130CommonLogic.getExclusiveData(globalMsg.A.no(i).poNum_A1.getValue(), globalMsg.A.no(i).poOrdDtlLineNum_A1.getValue(), globalMsg.A.no(i).wrtOffFlg_BF.getValue(), globalMsg.A.no(i).wrtOffDt_BF.getValue());
                    // mod end 2022/03/28 QC#57935(56588)
                    // END   2019/03/12 [QC#30729, MOD]
                    if (listCmAcrlWriteOff == null) {
                        bizMsg.setMessageInfo(NFCM0079E);
                        return;
                    } else {
                        Map mapCmAcrlWriteOff = (Map) listCmAcrlWriteOff.get(0);
                        String ezuptime = (String) mapCmAcrlWriteOff.get(EZUPTIME);
                        String ezuptimezone = (String) mapCmAcrlWriteOff.get(EZUPTIMEZONE);
                        if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(i).ezUpTime_A1.getValue(), globalMsg.A.no(i).ezUpTimeZone_A1.getValue(), ezuptime, ezuptimezone)) {
                            bizMsg.setMessageInfo(NFCM0079E);
                            return;
                        }
                    }
//                    List listCmAcrlWriteOff = NFBL1130CommonLogic.getCmAcrlWriteOffPkList(globalMsg.A.no(i).poNum_A1.getValue(), globalMsg.A.no(i).apVndCd_A1.getValue(), globalMsg.A.no(i).mdseCd_A1.getValue());
//                    if (listCmAcrlWriteOff == null) {
//                        bizMsg.setMessageInfo(NFCM0079E);
//                        return; 
//                    } else {
//                        for (int j = 0; j < listCmAcrlWriteOff.size(); j++) {
//                            Map mapCmAcrlWriteOff = (Map) listCmAcrlWriteOff.get(j);
//                            BigDecimal cmAcrlWriteOffPk = (BigDecimal) mapCmAcrlWriteOff.get(CM_ACRL_WRITE_OFF_PK);
//                            String ezuptime = (String) mapCmAcrlWriteOff.get(EZUPTIME);
//                            String ezuptimezone = (String) mapCmAcrlWriteOff.get(EZUPTIMEZONE);
//                            for (int k = 0; k < globalMsg.B.getValidCount(); k++) {
//                                if (cmAcrlWriteOffPk.compareTo(globalMsg.B.no(k).cmAcrlWriteOffPk_B1.getValue()) == 0) {
//                                    if (!ZYPDateUtil.isSameTimeStamp(globalMsg.B.no(k).ezUpTime_B1.getValue(), globalMsg.B.no(k).ezUpTimeZone_B1.getValue(), ezuptime, ezuptimezone)) {
//                                        bizMsg.setMessageInfo(NFCM0079E);
//                                        return;
//                                    }
//                                        
//                                }
//                            }
//                        }
//                    }
                    // END   2019/01/30 H.Ikeda [QC#30057, MOD]
                }
            // START 2021/03/15 H.Dimay [QC#57040,ADD]
            } else if (ZYPConstant.FLG_OFF_N.equals(globalMsg.A.no(i).wrtOffFlg_A1.getValue())
                        && !globalMsg.A.no(i).wrtOffFlg_A1.getValue().equals(globalMsg.A.no(i).wrtOffFlg_BF.getValue())) {
                    checkedRows.add(i);
                    // START 2022/07/14 [QC#60149, MOD]
                    // check 9segment
                    String[] coa = globalMsg.A.no(i).xxCmntTxt_A1.getValue().split("\\.");
                    if(!NFBL1130CommonLogic.checkGlCodeCombination(globalMsg, i, coa)){
                        if (!isErr) {
                            isErr = true;
                            firstErrIndex = i;
                        }
                    }
                    if (!isErr) {
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).wrtOffDt_A1, CONST_INIT_VAL);
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acrlWrtOffRsnCd_S, CONST_INIT_VAL);
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acrlWrtOffCmntTxt_A1, CONST_INIT_VAL);
                    }
                    // END 2022/07/14 [QC#60149, MOD]
            // END 2021/03/15 H.Dimay [QC#57040,ADD]
            }
        }
        if (isErr) {
            NFBL1130CommonLogic.moveToFirstErrorPage(bizMsg, globalMsg, firstErrIndex);
            return;
        }
        if (checkedRows.isEmpty()) {
            bizMsg.setMessageInfo(NFAM0075E);
            return;
        }

        // Write off
        // START 2016/12/06 H.Ikeda [QC#15823,MOD]
        // START 2019/03/12 [QC#30729, MOD]
        // CM_ACRL_WRITE_OFFTMsg cmAcrlWriteOffs[] = new CM_ACRL_WRITE_OFFTMsg[globalMsg.A.getValidCount()];
        // int aCnt = 0;
        // for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
        //     if (globalMsg.A.no(i).wrtOffFlg_A1.getValue().equals(ZYPConstant.FLG_ON_Y)
        //     && !globalMsg.A.no(i).wrtOffFlg_A1.getValue().equals(globalMsg.A.no(i).wrtOffFlg_BF.getValue())) {
        //         List listCmAcrlWriteOff = NFBL1130CommonLogic.getCmAcrlWriteOffPkList(globalMsg.A.no(i).poNum_A1.getValue(), globalMsg.A.no(i).apVndCd_A1.getValue(), globalMsg.A.no(i).mdseCd_A1.getValue());
        //         for (int j = 0; j < listCmAcrlWriteOff.size(); j++) {
        //             Map mapCmAcrlWriteOff = (Map) listCmAcrlWriteOff.get(j);
        //             BigDecimal cmAcrlWriteOffPk = (BigDecimal) mapCmAcrlWriteOff.get(CM_ACRL_WRITE_OFF_PK);
        //             CM_ACRL_WRITE_OFFTMsg cmAcrlWriteOff = new CM_ACRL_WRITE_OFFTMsg();
        //             ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.glblCmpyCd, GLBL_CMPY_CD);
        //             ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.cmAcrlWriteOffPk, cmAcrlWriteOffPk);
        //             cmAcrlWriteOff = (CM_ACRL_WRITE_OFFTMsg) EZDTBLAccessor.findByKeyForUpdate(cmAcrlWriteOff);
        //             if (cmAcrlWriteOff != null) {
        //                 ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.wrtOffFlg, ZYPConstant.FLG_ON_Y);
        //                 ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.acrlWrtOffRsnCd, globalMsg.A.no(i).acrlWrtOffRsnCd_S.getValue());
        //                 ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.wrtOffDt, globalMsg.A.no(i).wrtOffDt_A1.getValue());
        //                 ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.acrlWrtOffCmntTxt, globalMsg.A.no(i).acrlWrtOffCmntTxt_A1.getValue());
        //
        //                 cmAcrlWriteOffs[aCnt] = cmAcrlWriteOff;
        //                 aCnt++;
        //                 //EZDTBLAccessor.update(cmAcrlWriteOff);
        //             }
        //         }
        //     }
        // }
        // if (aCnt > 0) {
        //     int updateCnt = S21FastTBLAccessor.update(cmAcrlWriteOffs);
        //     if (updateCnt != aCnt) {
        //         bizMsg.setMessageInfo("NFZM0027E");
        //         return;
        //     }
        // }
        List<CM_ACRL_WRITE_OFFTMsg> cmAcrlWriteOffList = new ArrayList<CM_ACRL_WRITE_OFFTMsg>();
        // START 2022/04/05 R.Onozuka [QC357935, ADD]
        List<CM_ACRL_WRITE_OFFTMsg> insCmAcrlWriteOffList = new ArrayList<CM_ACRL_WRITE_OFFTMsg>();
        // END 2022/04/05 R.Onozuka [QC357935, ADD]
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            // START 2021/03/12 H.Dimay [QC#57040, MOD]
            //if (globalMsg.A.no(i).wrtOffFlg_A1.getValue().equals(ZYPConstant.FLG_ON_Y)
            //&& !globalMsg.A.no(i).wrtOffFlg_A1.getValue().equals(globalMsg.A.no(i).wrtOffFlg_BF.getValue())) {
            if (!globalMsg.A.no(i).wrtOffFlg_A1.getValue().equals(globalMsg.A.no(i).wrtOffFlg_BF.getValue())) {
            // END 2021/03/12 H.Dimay [QC#57040,MOD] 
                // START 2019/03/12 [QC#30729, MOD]
                // List listCmAcrlWriteOff = NFBL1130CommonLogic.getCmAcrlWriteOffPkList(globalMsg.A.no(i).poNum_A1.getValue(), globalMsg.A.no(i).apVndCd_A1.getValue(), globalMsg.A.no(i).mdseCd_A1.getValue());
                // mod start 2022/03/28 QC#57935(56588)
                //List listCmAcrlWriteOff = NFBL1130CommonLogic.getCmAcrlWriteOffPkList(globalMsg.A.no(i).poNum_A1.getValue(), globalMsg.A.no(i).apVndCd_A1.getValue(), globalMsg.A.no(i).mdseCd_A1.getValue(), globalMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                List listCmAcrlWriteOff = NFBL1130CommonLogic.getCmAcrlWriteOffPkList(globalMsg.A.no(i).poNum_A1.getValue(), globalMsg.A.no(i).poOrdDtlLineNum_A1.getValue(), globalMsg.A.no(i).wrtOffFlg_BF.getValue(), globalMsg.A.no(i).wrtOffDt_BF.getValue());
                // mod end 2022/03/28 QC#57935(56588)
                // END   2019/03/12 [QC#30729, MOD]
                Map <String, String> apVndInvLineMap = new HashMap<String, String>();
                Map <String, String> apVndInvSqMap = new HashMap<String, String>();
                Map <String, BigDecimal> poQtyMap = new HashMap<String, BigDecimal>();

                for (int j = 0; j < listCmAcrlWriteOff.size(); j++) {
                    Map mapCmAcrlWriteOff = (Map) listCmAcrlWriteOff.get(j);
                    BigDecimal cmAcrlWriteOffPk = (BigDecimal) mapCmAcrlWriteOff.get(CM_ACRL_WRITE_OFF_PK);
                    CM_ACRL_WRITE_OFFTMsg cmAcrlWriteOff = new CM_ACRL_WRITE_OFFTMsg();
                    ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.glblCmpyCd, GLBL_CMPY_CD);
                    ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.cmAcrlWriteOffPk, cmAcrlWriteOffPk);
                    cmAcrlWriteOff = (CM_ACRL_WRITE_OFFTMsg) EZDTBLAccessor.findByKeyForUpdate(cmAcrlWriteOff);
                    if (cmAcrlWriteOff != null) {
                        // START 2021/03/12 H.Dimay [QC#57040, MOD]
                        //ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.wrtOffFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.wrtOffFlg, globalMsg.A.no(i).wrtOffFlg_A1.getValue());
                        // END 2021/03/12 H.Dimay [QC#57040, MOD]
                        ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.acrlWrtOffRsnCd, globalMsg.A.no(i).acrlWrtOffRsnCd_S.getValue());
                        ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.wrtOffDt, globalMsg.A.no(i).wrtOffDt_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(cmAcrlWriteOff.acrlWrtOffCmntTxt, globalMsg.A.no(i).acrlWrtOffCmntTxt_A1.getValue());

                        cmAcrlWriteOffList.add(cmAcrlWriteOff);
                        // START 2022/04/05 R.Onozuka [QC#57935, ADD]
                        if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).apVndInvNum_A1)) {
                            apVndInvSqMap.put(globalMsg.A.no(i).apVndInvNum_A1.getValue(), cmAcrlWriteOff.apVndInvSqNum.getValue());
                            apVndInvLineMap.put(globalMsg.A.no(i).apVndInvNum_A1.getValue(), cmAcrlWriteOff.apVndInvLineNum.getValue());
                        }
                        poQtyMap.put(globalMsg.A.no(i).poNum_A1.getValue(), cmAcrlWriteOff.poQty.getValue());
                        // END 2022/04/05 R.Onozuka [QC#57935, ADD]
                    }
                }
                // START 2022/04/05 R.Onozuka [QC#57935, ADD]
                String[] coa = globalMsg.A.no(i).xxCmntTxt_A1.getValue().split("\\.");

                // generate new record
                CM_ACRL_WRITE_OFFTMsg insTmsg = new CM_ACRL_WRITE_OFFTMsg();

                // set values same as normal record
                ZYPEZDItemValueSetter.setValue(insTmsg.cmAcrlWriteOffPk, ZYPOracleSeqAccessor.getNumberBigDecimal(CM_ACRL_WRITE_OFF_SQ));
                ZYPEZDItemValueSetter.setValue(insTmsg.glblCmpyCd, GLBL_CMPY_CD);
                ZYPEZDItemValueSetter.setValue(insTmsg.apVndCd, globalMsg.A.no(i).apVndCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.poNum, globalMsg.A.no(i).poNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.mdseCd, globalMsg.A.no(i).mdseCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.locNum, cmAcrlWriteOffList.get(0).locNum);
                ZYPEZDItemValueSetter.setValue(insTmsg.delyOrdNum, globalMsg.A.no(i).delyOrdNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.invQty, globalMsg.A.no(i).invQty_A1.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.poQty, poQtyMap.get(globalMsg.A.no(i).poNum_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(insTmsg.invRcvQty, globalMsg.A.no(i).invRcvQty_A1.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.thisMthFobCostAmt, globalMsg.A.no(i).thisMthFobCostAmt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.acInvTaxAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insTmsg.entDealNetUnitPrcAmt, globalMsg.A.no(i).acInvJrnlCostAmt_A2.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.wrtOffFlg, globalMsg.A.no(i).wrtOffFlg_A1.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.acrlWrtOffRsnCd, globalMsg.A.no(i).acrlWrtOffRsnCd_S.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.wrtOffDt, globalMsg.A.no(i).wrtOffDt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.acrlWrtOffCmntTxt, globalMsg.A.no(i).acrlWrtOffCmntTxt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(insTmsg.poOrdDtlLineNum, globalMsg.A.no(i).poOrdDtlLineNum_A1.getValue());

                // set specific values of write off or cancel record
                // set 9segment
                ZYPEZDItemValueSetter.setValue(insTmsg.coaCmpyCd, coa[0]);
                ZYPEZDItemValueSetter.setValue(insTmsg.coaBrCd, coa[1]);
                ZYPEZDItemValueSetter.setValue(insTmsg.coaCcCd, coa[2]);
                ZYPEZDItemValueSetter.setValue(insTmsg.coaAcctCd, coa[3]);
                ZYPEZDItemValueSetter.setValue(insTmsg.coaProdCd, coa[4]);
                ZYPEZDItemValueSetter.setValue(insTmsg.coaChCd, coa[5]);
                ZYPEZDItemValueSetter.setValue(insTmsg.coaAfflCd, coa[6]);
                ZYPEZDItemValueSetter.setValue(insTmsg.coaProjCd, coa[7]);
                ZYPEZDItemValueSetter.setValue(insTmsg.coaExtnCd, coa[8]);

                // Set AJE_CM_INTFC_TP_CD as not journalized
                ZYPEZDItemValueSetter.setValue(insTmsg.ajeCmIntfcTpCd, ZYPConstant.FLG_OFF_0);

                // Set Difference after Calculation
                BigDecimal stkInAmt = globalMsg.A.no(i).acInvJrnlCostAmt_A1.getValue();
                BigDecimal invAmt = globalMsg.A.no(i).acInvJrnlCostAmt_A3.getValue();
                BigDecimal wrtOffAmt = stkInAmt.subtract(invAmt);

                // del start 2022/08/05 QC#59245
                // Set Difference after Calculation from Unit Price
                //if (globalMsg.A.no(i).invQty_A1.getValue().equals(globalMsg.A.no(i).invRcvQty_A1.getValue())) {
                //    BigDecimal stkInTotal = globalMsg.A.no(i).invRcvQty_A1.getValue().multiply(globalMsg.A.no(i).thisMthFobCostAmt_A1.getValue());
                //    BigDecimal invTotal = globalMsg.A.no(i).invQty_A1.getValue().multiply(globalMsg.A.no(i).acInvJrnlCostAmt_AP.getValue());
                //    wrtOffAmt = stkInTotal.subtract(invTotal);
                //}
                // del end 2022/08/05 QC#59245

                // set Debit or Credit
                // Write-Off
                if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).wrtOffFlg_A1.getValue())) {
                    // Stock-In
                    if (wrtOffAmt.compareTo(BigDecimal.ZERO) > 0) {
                        ZYPEZDItemValueSetter.setValue(insTmsg.drCrTpCd, CR);
                        ZYPEZDItemValueSetter.setValue(insTmsg.acInvJrnlCostAmt, wrtOffAmt);
                        ZYPEZDItemValueSetter.setValue(insTmsg.stkInDt, globalMsg.A.no(i).stkInDt_A1.getValue());
                    // AP Invoice
                    } else {
                        ZYPEZDItemValueSetter.setValue(insTmsg.drCrTpCd, DR);
                        ZYPEZDItemValueSetter.setValue(insTmsg.acInvJrnlCostAmt, wrtOffAmt.abs());
                        ZYPEZDItemValueSetter.setValue(insTmsg.apVndInvNum, globalMsg.A.no(i).apVndInvNum_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(insTmsg.apVndInvSqNum, apVndInvSqMap.get(globalMsg.A.no(i).apVndInvNum_A1.getValue()));
                        ZYPEZDItemValueSetter.setValue(insTmsg.apVndInvLineNum, apVndInvLineMap.get(globalMsg.A.no(i).apVndInvNum_A1.getValue()));
                        ZYPEZDItemValueSetter.setValue(insTmsg.invDt, globalMsg.A.no(i).invDt_A1.getValue());
                    }
                // Write-Off Cancel
                } else {
                    // Stock-In
                    if (wrtOffAmt.compareTo(BigDecimal.ZERO) > 0) {
                        ZYPEZDItemValueSetter.setValue(insTmsg.drCrTpCd, DR);
                        ZYPEZDItemValueSetter.setValue(insTmsg.acInvJrnlCostAmt, wrtOffAmt);
                        ZYPEZDItemValueSetter.setValue(insTmsg.stkInDt, globalMsg.A.no(i).stkInDt_A1.getValue());
                    // AP Invoice
                    } else {
                        ZYPEZDItemValueSetter.setValue(insTmsg.drCrTpCd, CR);
                        ZYPEZDItemValueSetter.setValue(insTmsg.acInvJrnlCostAmt, wrtOffAmt.abs());
                        ZYPEZDItemValueSetter.setValue(insTmsg.apVndInvNum, globalMsg.A.no(i).apVndInvNum_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(insTmsg.apVndInvSqNum, apVndInvSqMap.get(globalMsg.A.no(i).apVndInvNum_A1.getValue()));
                        ZYPEZDItemValueSetter.setValue(insTmsg.apVndInvLineNum, apVndInvLineMap.get(globalMsg.A.no(i).apVndInvNum_A1.getValue()));
                        ZYPEZDItemValueSetter.setValue(insTmsg.invDt, globalMsg.A.no(i).invDt_A1.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(insTmsg.wrtOffDt, ZYPDateUtil.getSalesDate(GLBL_CMPY_CD));
                }
                insCmAcrlWriteOffList.add(insTmsg);
                // END 2022/04/05 R.Onozuka [QC#57935, ADD]
            }
        }
        if (cmAcrlWriteOffList.size() > 0) {
            int updateCnt = S21FastTBLAccessor.update(cmAcrlWriteOffList.toArray(new CM_ACRL_WRITE_OFFTMsg[]{}));
            // START 2022/04/05 R.Onozuka [QC#57935, ADD/MOD]
            int insertCnt = S21FastTBLAccessor.insert(insCmAcrlWriteOffList.toArray(new CM_ACRL_WRITE_OFFTMsg[]{}));
            //if (updateCnt != cmAcrlWriteOffList.size()) {
            if ((updateCnt != cmAcrlWriteOffList.size()) || insertCnt != insCmAcrlWriteOffList.size()) {
            // END 2022/04/05 R.Onozuka [QC#57935, ADD/MOD]
                bizMsg.setMessageInfo("NFZM0027E");
                return;
            }
        }
        // END   2019/03/12 [QC#30729, MOD]
        // END   2016/12/06 H.Ikeda [QC#15823,MOD]
        // Clear detail information
        NFBL1130CommonLogic.clearLines(bizMsg, globalMsg);
        // Search records
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_WO, ZYPConstant.FLG_ON_Y);
        NFBL1130CommonLogic.searchRecord(bizMsg, globalMsg);

        bizMsg.setMessageInfo(NZZM0002I);

        EZDDebugOutput.println(5, "doProcess_NFBL1130Scrn00_CMN_Submit================================END", this);
    }

    // START 2016/10/10 J.Kim [QC#5521,ADD]
    /**
     * Delete_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL1130Scrn00_DeleteSearch(NFBL1130CMsg bizMsg, NFBL1130SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // set API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        // call Search Option API
        if (NFBL1130CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NFBL1130CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);
            bizMsg.srchOptPk_S.clear();
        }
    }

    /**
     * Save_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL1130Scrn00_SaveSearch(NFBL1130CMsg bizMsg, NFBL1130SMsg glblMsg) {

        if (NFBL1130CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, "NFBM0226E", new String[] {"Save", "Search Option Name" });
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();

        // set search option value to API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) && NFBL1130CommonLogic.isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            NFBL1130CommonLogic.setSelectSaveSearchName(bizMsg, pMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, userId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.poNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.delyOrdNum);
        if (ZYPCommonFunc.hasValue(bizMsg.invRcvQty)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.invRcvQty.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.xxChkBox_WO);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.apVndInvNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.prntVndNm);
        if (ZYPCommonFunc.hasValue(bizMsg.invQty)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.invQty.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.xxChkBox_PM);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.acrlCoaAcctCd_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.rwsNum);
        if (ZYPCommonFunc.hasValue(bizMsg.invDt_FR)) {
            if (ZYPDateUtil.isValidDate(bizMsg.invDt_FR.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.invDt_FR.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.invDt_TO)) {
            if (ZYPDateUtil.isValidDate(bizMsg.invDt_TO.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.invDt_TO.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.stkInDt_FR)) {
            if (ZYPDateUtil.isValidDate(bizMsg.stkInDt_FR.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.stkInDt_FR.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.stkInDt_TO)) {
            if (ZYPDateUtil.isValidDate(bizMsg.stkInDt_TO.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.stkInDt_TO.getValue());
            }
        }

        // add start 2022/08/05 QC#59245
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.xxChkBox_CV);
        // add end 2022/08/05 QC#59245

        // call Search Option API
        if (NFBL1130CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NFBL1130CommonLogic.createPulldownListSaveOpt(bizMsg, userId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
        }
    }
    // END 2016/10/10 J.Kim [QC#5521,ADD]
}
