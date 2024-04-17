package business.blap.NMAL0160;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import business.blap.NMAL0160.NMAL0160CMsg;
import business.blap.NMAL0160.common.NMAL0160CommonLogic;
import business.blap.NMAL0160.constant.NMAL0160Constant;
import business.db.MDSETMsg;
import business.db.MDSE_CST_UPD_HIST_DTLTMsg;
import business.db.MDSE_CST_UPD_TPTMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 10/28/2015   SRAA            K.Aratani       Update
 * 11/22/2016   Fujitsu         N.Sugiura       Update          S21_NA#16026
 * 06/13/2017   Hitachi         K.Kasai         Update          S21_NA2#19013
 * 01/11/2020   Fujitsu         M.Ohno          Update          S21_NA#55391
 * 01/18/2020   Fujitsu         M.Ohno          Update          S21_NA#55487
 * 2020/10/13   CITS            K.Ogino         Update          QC#57843
 *</pre>
 */
public class NMAL0160BL06 extends S21BusinessHandler implements NMAL0160Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        NMAL0160CMsg bizMsg = (NMAL0160CMsg) cMsg;
        NMAL0160SMsg shareMsg = (NMAL0160SMsg) sMsg;
        //cMsg.setCommitSMsg(true);
        String scrnAplID = bizMsg.getScreenAplID();
        try {
            if ("NMAL0160Scrn00_CMN_Submit".equals(scrnAplID)) {
                NMAL0160Scrn00_CMN_Submit(bizMsg, shareMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    private void NMAL0160Scrn00_CMN_Submit(NMAL0160CMsg bizMsg, NMAL0160SMsg sMsg) {

        // #####################
        // Input Check
        // #####################
        // Header
        boolean hasError = checkInput_Header(bizMsg);
        if (hasError) {
            return;
        }
        hasError = checkInput_Detail(bizMsg);
        if (hasError) {
            return;
        }
        
        //Save from cMsg to sMsg
        NMAL0160CommonLogic.copyFromSMsgToCMsg(bizMsg, sMsg, false);
        
        // #####################
        // Update
        // #####################        
        // Detail
        Map<String, Object> mdseUpdTimeMap = new HashMap<String, Object>();
        Map<String, Object> mdseUpdZoneMap = new HashMap<String, Object>();
        List<BigDecimal> updPEMdseCstHistHdrPkList = new ArrayList<BigDecimal>();
        List<BigDecimal> updNotPEMdseCstHistHdrPkList = new ArrayList<BigDecimal>();
        boolean changedFlg = false;
        MDSE_CST_UPD_HIST_DTLTMsg detailTMsg = null;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            for (int j = 0; j < sMsg.B.getValidCount(); j++) {

                String apk = String.valueOf(sMsg.A.no(i).mdseCstUpdHistHdrPk_A1.getValue());
                String bpk = String.valueOf(sMsg.B.no(j).mdseCstUpdHistHdrPk_B1.getValue());
                String aMdseCd = sMsg.A.no(i).mdseCd_A1.getValue();
                String bMdseCd = sMsg.B.no(j).mdseCd_B1.getValue();
                String aStsCd = sMsg.A.no(i).mdseCstUpdStsCd_A1.getValue();
                String bStsCd = sMsg.B.no(j).mdseCstUpdStsCd_B1.getValue();
                String aMdseCstUpdEffFromDt = sMsg.A.no(i).mdseCstUpdEffFromDt_A1.getValue();
                String bMdseCstUpdEffFromDt = sMsg.B.no(j).mdseCstUpdEffFromDt_B1.getValue();
                
                // 11/22/2016 S21_NA#16026 Add Start
                String chkMdseCd = sMsg.A.no(j).mdseCd_A1.getValue();
                String chkStsCd = sMsg.A.no(j).mdseCstUpdStsCd_A1.getValue();
                // 11/22/2016 S21_NA#16026 Add End
                // 2020/01/18 S21_NA#55487 Add Start
                if (i != j) {
                    if (MDSE_CST_UPD_STS.APPROVED.equals(aStsCd)
                            && aStsCd.equals(chkStsCd) && aMdseCd.equals(chkMdseCd)
                            && sMsg.A.no(i).mdseCstUpdEffFromDt_A1.getValue().equals(sMsg.A.no(j).mdseCstUpdEffFromDt_A1.getValue())) {

                        int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                        if (!checkDiffelentErrorPage(bizMsg, i, j)) {
                            int errorLineNo2 = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, j);
                            bizMsg.A.no(errorLineNo2).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "NMAM8659E");
                        }
                        bizMsg.A.no(errorLineNo).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "NMAM8659E");

                        return;
                    }
                }
                // 2020/01/18 S21_NA#55487 Add End

                if ((apk+aMdseCd).equals(bpk+bMdseCd)) {
                    if (!aStsCd.equals(bStsCd) || !aMdseCstUpdEffFromDt.equals(bMdseCstUpdEffFromDt)) {
                        detailTMsg = NMAL0160CommonLogic.findDetail(getGlobalCompanyCode()
                        , sMsg.A.no(i).mdseCstUpdHistHdrPk_A1.getValue()
                        , sMsg.A.no(i).mdseCd_A1.getValue());

                        if (detailTMsg == null) {
                            // transfer error's line page
                            int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                            // NDAM0007E=0,The corresponding data
                            // does not exist. Table Name: [@],
                            // Key Field Name: [@], Key Value: [@]
                            bizMsg.A.no(errorLineNo).mdseCstUpdStsCd_A1.setErrorInfo(1, "NDAM0007E", new String[] {"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD",
                                String.valueOf(sMsg.A.no(i).mdseCstUpdHistHdrPk_A1.getValue()) + "," + sMsg.A.no(i).mdseCd_A1.getValue() });
                            return;
                        }

                        if (!aMdseCstUpdEffFromDt.equals(bMdseCstUpdEffFromDt)) {
                            ZYPEZDItemValueSetter.setValue(detailTMsg.mdseCstUpdEffFromDt, aMdseCstUpdEffFromDt);
                        }

                        if (!aStsCd.equals(bStsCd)) {
                            if (aStsCd.equals(MDSE_CST_UPD_STS.PENDING)) {
                                if (updNotPEMdseCstHistHdrPkList.contains(sMsg.A.no(i).mdseCstUpdHistHdrPk_A1.getValue())) {
                                    // Error
                                    int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                                    // Cannot mix the status of
                                    // Approved and Cancelled on same
                                    // request.
                                    bizMsg.A.no(errorLineNo).mdseCstUpdStsCd_A1.setErrorInfo(1, "NMAM8256E");
                                    return;
                                }
                            } else {
                                if (updPEMdseCstHistHdrPkList.contains(sMsg.A.no(i).mdseCstUpdHistHdrPk_A1.getValue())) {
                                    // Error
                                    int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                                    // Cannot mix the status of
                                    // Approved and Cancelled on same
                                    // request.
                                    bizMsg.A.no(errorLineNo).mdseCstUpdStsCd_A1.setErrorInfo(1, "NMAM8256E");
                                    return;
                                }
                            }

                            // 11/22/2016 S21_NA#16026 Add Start
                            if (aStsCd.equals(MDSE_CST_UPD_STS.CANCELLED) && !sMsg.A.no(i).mdseCstUpdEffFromDt_A1.getValue().equals(sMsg.B.no(j).mdseCstUpdEffFromDt_B1.getValue())) {
                                // 01/11/2020 S21_NA#55391 Add Start
                                int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                                // 01/11/2020 S21_NA#55391 Add End
                                // NMAM8660E=0,If Status is Canceled,
                                // Effective Date can not be changed.
                                bizMsg.A.no(errorLineNo).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "NMAM8660E");
                                return;
                            }
                            if (aStsCd.equals(MDSE_CST_UPD_STS.APPROVED) && NMAL0160Query.getInstance().isDuplicateEffectiveDate(aMdseCd, sMsg.A.no(i).mdseCstUpdEffFromDt_A1.getValue(), getGlobalCompanyCode())) {
                                // 01/11/2020 S21_NA#55391 Add Start
                                int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                                // 01/11/2020 S21_NA#55391 Add End
                                // NMAM8659E=0,If Status is Approve,
                                // the same Item Number and Effective
                                // Date can not be entered.
                                bizMsg.A.no(errorLineNo).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "NMAM8659E");
                                return;
                            }
                            // 11/22/2016 S21_NA#16026 Add End

                            ZYPEZDItemValueSetter.setValue(detailTMsg.mdseCstUpdStsCd, aStsCd);

                            String tmpTimeZone = detailTMsg.ezUpTimeZone.getValue();
                            String tmpTime = detailTMsg.ezUpTime.getValue();
                            if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A1.getValue(), sMsg.A.no(i).ezUpTimeZone_A1.getValue(), tmpTime, tmpTimeZone)) {
                                // transfer error's line page
                                int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                                // NMAM8175E=0,This data has been
                                // updated by another user. [
                                // TableName = @ , key = @, value
                                // = @ ]
                                bizMsg.A.no(errorLineNo).mdseCstUpdStsCd_A1.setErrorInfo(1, "NMAM8175E", new String[] {"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD",
                                        String.valueOf(detailTMsg.mdseCstUpdHistHdrPk.getValue() + "," + detailTMsg.mdseCd.getValue()) });
                                return;
                            }
                            S21FastTBLAccessor.update(detailTMsg);
                            if (!RETURN_CD_NORMAL.equals(detailTMsg.getReturnCode())) {
                                // transfer error's line page
                                int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                                // ZZMM0015E=0,Data update
                                // failure. [ TableName = @ , key
                                // = @, value = @ ]
                                bizMsg.A.no(errorLineNo).mdseCstUpdStsCd_A1.setErrorInfo(1, "ZZMM0015E", new String[] {"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD",
                                        String.valueOf(detailTMsg.mdseCstUpdHistHdrPk.getValue() + "," + detailTMsg.mdseCd.getValue()) });
                                return;
                            }
                            changedFlg = true;
                            if (aStsCd.equals(MDSE_CST_UPD_STS.PENDING)) {
                                updPEMdseCstHistHdrPkList.add(detailTMsg.mdseCstUpdHistHdrPk.getValue());
                            } else {
                                updNotPEMdseCstHistHdrPkList.add(detailTMsg.mdseCstUpdHistHdrPk.getValue());
                            }

                            // Only Approved
                            if (MDSE_CST_UPD_STS.APPROVED.equals(aStsCd)) {

                                // get MDSE_CSR_UPD_TP
                                MDSE_CST_UPD_TPTMsg mdseCstUpdTpTMsg = NMAL0160CommonLogic.findMdseCstUpdTp(getGlobalCompanyCode(), sMsg.A.no(i).mdseCstUpdTpCd_A1.getValue());
                                if (mdseCstUpdTpTMsg == null) {
                                    // transfer error's line page
                                    int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                                    // NDAM0007E=0,The
                                    // corresponding data does not
                                    // exist. Table Name: [@], Key
                                    // Field Name: [@], Key Value:
                                    // [@]
                                    bizMsg.A.no(errorLineNo).mdseCstUpdStsCd_A1.setErrorInfo(1, "NDAM0007E", new String[] {"MDSE_CST_UPD_TP", "MDSE_CST_UPD_TP_CD", sMsg.A.no(i).mdseCstUpdTpCd_A1.getValue() });
                                    return;
                                }

                                //START 2017/06/13 K.Kasai [QC#19103,MOD]
                                // Standard Cost & Asset Cost
                                // QC#7499
                                // MDSE Update
                                MDSETMsg mdseTMsg = new MDSETMsg();
                                mdseTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
                                mdseTMsg.mdseCd.setValue(sMsg.A.no(i).mdseCd_A1.getValue());
                                mdseTMsg = (MDSETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(mdseTMsg);
                                if (mdseTMsg == null) {
                                    // transfer error's line page
                                    int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                                    // NDAM0007E=0,The
                                    // corresponding data does not
                                    // exist. Table Name: [@], Key
                                    // Field Name: [@], Key Value:
                                    // [@]
                                    bizMsg.A.no(errorLineNo).mdseCstUpdStsCd_A1.setErrorInfo(1, "NDAM0007E", new String[] {"MDSE", "MDSE_CD", sMsg.A.no(i).mdseCd_A1.getValue() });
                                    return;
                                }
                                // Standard Cost
                                if (FLG_ON_Y.equals(mdseCstUpdTpTMsg.stdCostRelnFlg.getValue())) {

                                    // Next Standard Cost Amount
                                    ZYPEZDItemValueSetter.setValue(mdseTMsg.nextMthTotStdCostAmt, sMsg.A.no(i).rqstTotStdCostAmt_A1);
                                    /// Next Standard Cost Date
                                    // QC#7499
                                    ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCstUpdEffFromDt, sMsg.A.no(i).mdseCstUpdEffFromDt_A1);
                                    // Asset Cost
                                } else {
                                    // Next Asset Cost Amount
                                    ZYPEZDItemValueSetter.setValue(mdseTMsg.nextAssetRecovCostAmt, sMsg.A.no(i).rqstTotStdCostAmt_A1);
                                    // Next Asset Cost Date
                                    // QC#7499
                                    ZYPEZDItemValueSetter.setValue(mdseTMsg.assetRecovCstEffFromDt, sMsg.A.no(i).mdseCstUpdEffFromDt_A1);
                                }
                                tmpTimeZone = mdseTMsg.ezUpTimeZone.getValue();
                                tmpTime = mdseTMsg.ezUpTime.getValue();
                                String updTime = (String) mdseUpdTimeMap.get(sMsg.A.no(i).mdseCd_A1.getValue());
                                if (updTime == null) {
                                    updTime = sMsg.A.no(i).ezUpTime_M1.getValue();
                                }
                                String updZone = (String) mdseUpdZoneMap.get(sMsg.A.no(i).mdseCd_A1.getValue());
                                if (updZone == null) {
                                    updZone = sMsg.A.no(i).ezUpTimeZone_M1.getValue();
                                }

                                if (!ZYPDateUtil.isSameTimeStamp(updTime, updZone, tmpTime, tmpTimeZone)) {
                                    // transfer error's line
                                    // page
                                    int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                                    // NMAM8175E=0,This data
                                    // has been updated by
                                    // another user. [
                                    // TableName = @ , key =
                                    // @, value = @ ]
                                    bizMsg.A.no(errorLineNo).mdseCstUpdStsCd_A1.setErrorInfo(1, "NMAM8175E", new String[] {"MDSE", "MDSE_CD", sMsg.A.no(i).mdseCd_A1.getValue() });
                                    return;
                                }
                                S21FastTBLAccessor.update(mdseTMsg);
                                if (!RETURN_CD_NORMAL.equals(mdseTMsg.getReturnCode())) {
                                    // ZZMM0015E=0,Data update
                                    // failure. [ TableName =
                                    // @ , key = @, value = @
                                    // ]
                                    bizMsg.setMessageInfo("ZZMM0015E", new String[] {"MDSE", "MDSE_CD", sMsg.A.no(i).mdseCd_A1.getValue() });
                                    return;
                                }
                                mdseUpdTimeMap.put(sMsg.A.no(i).mdseCd_A1.getValue(), mdseTMsg.ezUpTime.getValue());
                                mdseUpdZoneMap.put(sMsg.A.no(i).mdseCd_A1.getValue(), mdseTMsg.ezUpTimeZone.getValue());
                                //END 2017/06/13 K.Kasai [QC#19103,MOD]
                            }
                        } else {
                            S21FastTBLAccessor.update(detailTMsg);
                            if (!RETURN_CD_NORMAL.equals(detailTMsg.getReturnCode())) {
                                // transfer error's line page
                                int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
                                // ZZMM0015E=0,Data update
                                // failure. [ TableName = @ , key
                                // = @, value = @ ]
                                bizMsg.A.no(errorLineNo).mdseCstUpdStsCd_A1.setErrorInfo(1, "ZZMM0015E", new String[] {"MDSE_CST_UPD_HIST_DTL", "MDSE_CST_UPD_HIST_HDR_PK,MDSE_CD",
                                        String.valueOf(detailTMsg.mdseCstUpdHistHdrPk.getValue() + "," + detailTMsg.mdseCd.getValue()) });
                                return;
                            }
                            changedFlg = true;
                            break;
                        }
                    }
                // 2020/01/18 S21_NA#55487 Del Start
                // 11/22/2016 S21_NA#16026 Add Start
//                } else {
                    // Only Approved
//                    if (MDSE_CST_UPD_STS.APPROVED.equals(aStsCd)
//                            && aStsCd.equals(chkStsCd) && aMdseCd.equals(chkMdseCd)
//                            // 01/11/2020 S21_NA#55391 Mod Start
//                            && sMsg.A.no(i).mdseCstUpdEffFromDt_A1.getValue().equals(sMsg.A.no(j).mdseCstUpdEffFromDt_A1.getValue())) {
//                            // 01/11/2020 S21_NA#55391 Mod End
//                        // 01/11/2020 S21_NA#55391 Mod Start
//                        ////NMAM8659E=0,If Status is Approve, the same Item Number and Effective Date can not be entered.
//                        //sMsg.A.no(i).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "NMAM8659E");
//                        ////NMAM8659E=0,If Status is Approve, the same Item Number and Effective Date can not be entered.
//                        //sMsg.A.no(j).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "NMAM8659E");
//                        int errorLineNo = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, i);
//                        // 2020/01/18 S21_NA#55487 Mod Start
//                        if (!checkDiffelentErrorPage(bizMsg, i, j)) {
//                            int errorLineNo2 = NMAL0160CommonLogic.transferErrorPage(bizMsg, sMsg, j);
//                            bizMsg.A.no(errorLineNo2).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "NMAM8659E");
//                        }
//                        // 2020/01/18 S21_NA#55487 Mod End
//                        bizMsg.A.no(errorLineNo).mdseCstUpdEffFromDt_A1.setErrorInfo(1, "NMAM8659E");
//
//                        // 01/11/2020 S21_NA#55391 Mod End
//
//                        return;
//                    }
                // 11/22/2016 S21_NA#16026 Add End
                // 2020/01/18 S21_NA#55487 Del End
                }
            }
        }
        
        //Search
        // QC#57843 Add
        String invtySnapShotDt = NMAL0160CommonLogic.getMaxSnapShotDt(bizMsg, getGlobalCompanyCode());
        NMAL0160CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), invtySnapShotDt);
        
        if (changedFlg) {
            //NMAM8182I=0,[@] process ended normally.
            bizMsg.setMessageInfo("NMAM8182I", new String[]{"Cost History Update"});
        } else {
            //NMAM8333I=0,No change has been made.
            bizMsg.setMessageInfo("NMAM8333I");
        }
        
    }

    private boolean checkInput_Header(NMAL0160CMsg bizMsg) {

        boolean errorFlg = false;

        if (errorFlg) {
            return true;
        }

        return false;
    }

    private boolean checkInput_Detail(NMAL0160CMsg bizMsg) {

        boolean errorFlg = false;

        if (errorFlg) {
            return true;
        }

        return false;
    }

    // 2020/01/18 S21_NA#55487 Add Start
    private boolean checkDiffelentErrorPage(NMAL0160CMsg bizMsg, int i, int j) {
        if (i / bizMsg.A.length() != j / bizMsg.A.length()) {
            return true;
        }

        return false;
    }
    // 2020/01/18 S21_NA#55487 Add Start

}
