/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0100;

import static business.blap.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_CURRENT;
import static business.blap.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_FUTURE;
import static business.blap.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_NEW;
import static business.blap.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_SALESDATE;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0124E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0126E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0128E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0151E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.NWCM0152E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.SCRN_ID_00;
import static business.blap.NWCL0100.constant.NWCL0100Constant.TEXT_BILL_TO;
import static business.blap.NWCL0100.constant.NWCL0100Constant.TEXT_LAST_MONTH_END;
import static business.blap.NWCL0100.constant.NWCL0100Constant.TEXT_MONTH_QUOTA;
import static business.blap.NWCL0100.constant.NWCL0100Constant.TEXT_RATE;
import static business.blap.NWCL0100.constant.NWCL0100Constant.TEXT_SALES_DATE;
import static business.blap.NWCL0100.constant.NWCL0100Constant.TEXT_VALID_FROM;
import static business.blap.NWCL0100.constant.NWCL0100Constant.TEXT_VALID_TO;
import static business.blap.NWCL0100.constant.NWCL0100Constant.ZZM9000E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.ZZM9004E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.ZZZM9004E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.ZZZM9012E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.ZZZM9013E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.ZZZM9014E;
import static business.blap.NWCL0100.constant.NWCL0100Constant.ZZBM0085E;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NWCL0100.common.NWCL0100CommonLogic;
import business.db.SPLY_PGM_CONTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWCL0100BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 * 2016/09/06   Hitachi         Y.Takeno        Update          QC#13315
 * 2016/09/07   Hitachi         Y.Takeno        Update          QC#13337
 * 2016/09/08   Hitachi         Y.Takeno        Update          QC#13339
 * 2016/09/08   Hitachi         Y.Takeno        Update          QC#13347
 * 2016/09/15   Hitachi         Y.Takeno        Update          QC#13339-1
 * 2016/09/15   Hitachi         Y.Takeno        Update          QC#13341-1
 * 2017/11/14   Fujitsu         Mz.Takahashi    Update          Sol#265(QC#18788)
 * 2019/08/26   Fujitsu         A.Kazuki        Update          QC#52432
 * 2019/09/12   Fujitsu         R.Matsuki       Update          QC#52432-1
 *</pre>
 */
public class NWCL0100BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWCL0100CMsg bizMsg = (NWCL0100CMsg) cMsg;
            NWCL0100SMsg glblMsg = (NWCL0100SMsg) sMsg;

            if ("NWCL0100Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NWCL0100Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NWCL0100Scrn00_SaveSearch(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_CMN_Submit(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
        Boolean hasError = false;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // 2017/11/14 Sol#265(QC#18788) Add Start
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcGrpPk_A0)){
                if (!NWCL0100CommonLogic.isExistPrcGrpPk(bizMsg, bizMsg.A.no(i).prcGrpPk_A0.getValue())) {
                    bizMsg.A.no(i).prcGrpPk_A0.setErrorInfo(1, ZZBM0085E, new String[] {"Price Group"});
                    hasError = true;
                }
            }
        }

        if (hasError){
            return;
        }

        // 2017/11/14 Sol#265(QC#18788) Add End
        
        
        NWCL0100CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);

        String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());

// 2019/08/26 QC#52432 Add Start
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(slsDt.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(slsDt.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH , 1);
        cal.add(Calendar.DATE, -1);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String lastDayOfLastMonth = sdf.format(cal.getTime());
// 2019/08/26 QC#52432 Add End

        // Input Check
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            String frmDt = glblMsg.A.no(i).effFromDt_A0.getValue();
            String toDt = glblMsg.A.no(i).effThruDt_A0.getValue();
            String acctCd = glblMsg.A.no(i).billToCustAcctCd_A0.getValue();
            String dplyCtrlCd = glblMsg.A.no(i).dplyCtrlCd_A0.getValue();

            if (DISP_CTRL_CD_CURRENT.equals(dplyCtrlCd) || DISP_CTRL_CD_FUTURE.equals(dplyCtrlCd) || DISP_CTRL_CD_NEW.equals(dplyCtrlCd) || DISP_CTRL_CD_SALESDATE.equals(dplyCtrlCd)) {

                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).billToCustAcctCd_A0)) {
                    glblMsg.A.no(i).billToCustAcctCd_A0.setErrorInfo(1, ZZM9000E, new String[] {TEXT_BILL_TO});
                }

                if (ZYPCommonFunc.hasValue(frmDt) && ZYPCommonFunc.hasValue(toDt) && frmDt.compareTo(toDt) > 0) {
                    glblMsg.A.no(i).effFromDt_A0.setErrorInfo(1, NWCM0124E, new String[] {TEXT_VALID_TO, TEXT_VALID_FROM });
                    glblMsg.A.no(i).effThruDt_A0.setErrorInfo(1, NWCM0124E, new String[] {TEXT_VALID_TO, TEXT_VALID_FROM });
                }

// 2019/08/26 QC#52432 Modification Start
//                 if (ZYPCommonFunc.hasValue(toDt) && slsDt.compareTo(toDt) > 0) {
//                    glblMsg.A.no(i).effThruDt_A0.setErrorInfo(1, NWCM0124E, new String[] {TEXT_VALID_TO, TEXT_SALES_DATE });
//                }
                if (ZYPCommonFunc.hasValue(toDt) && lastDayOfLastMonth.compareTo(toDt) > 0) {
// 2019/09/12 QC#52432-1 Mod Start
//                    glblMsg.A.no(i).effThruDt_A0.setErrorInfo(1, NWCM0124E, new String[] {TEXT_VALID_TO, TEXT_SALES_DATE });
                    glblMsg.A.no(i).effThruDt_A0.setErrorInfo(1, NWCM0124E, new String[] {TEXT_VALID_TO, TEXT_LAST_MONTH_END });
// 2019/09/12 QC#52432-1 Mod END
                }
// 2019/08/26 QC#52432 Modification End

// 2016/09/07 QC#13337 Del Start
//                for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
//                    if (S21StringUtil.isEquals(acctCd, glblMsg.A.no(j).billToCustAcctCd_A0.getValue()) && (i != j)) {
//                        if (!(glblMsg.A.no(j).effThruDt_A0.getValue().compareTo(frmDt) < 0 || glblMsg.A.no(j).effFromDt_A0.getValue().compareTo(toDt) > 0)) {
//
//                            if (DISP_CTRL_CD_CURRENT.equals(dplyCtrlCd)) {
//                                glblMsg.A.no(i).effThruDt_A0.setErrorInfo(1, NWCM0126E);
//                            } else {
//                                glblMsg.A.no(i).effFromDt_A0.setErrorInfo(1, NWCM0126E);
//                                glblMsg.A.no(i).effThruDt_A0.setErrorInfo(1, NWCM0126E);
//                            }
//                        }
//                    }
//                }
// 2016/09/07 QC#13337 Del End
            }

            if (DISP_CTRL_CD_FUTURE.equals(dplyCtrlCd) || DISP_CTRL_CD_NEW.equals(dplyCtrlCd) || DISP_CTRL_CD_SALESDATE.equals(dplyCtrlCd)) {

                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).effFromDt_A0)) {
                    glblMsg.A.no(i).effFromDt_A0.setErrorInfo(1, ZZM9000E, new String[] {TEXT_VALID_FROM});
                }

// 2016/09/08 QC#13347 Del Start
//                if (ZYPCommonFunc.hasValue(frmDt) && slsDt.compareTo(frmDt) > 0) {
//                    glblMsg.A.no(i).effFromDt_A0.setErrorInfo(1, NWCM0124E, new String[] {TEXT_VALID_FROM, TEXT_SALES_DATE });
//                }
// 2016/09/08 QC#13347 Del End

                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).splyPgmUnitAmtRate_A0)) {
                    glblMsg.A.no(i).splyPgmUnitAmtRate_A0.setErrorInfo(1, ZZM9000E, new String[] {TEXT_RATE});
                }

                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).splyPgmMlyQuotQty_A0)) {
                    glblMsg.A.no(i).splyPgmMlyQuotQty_A0.setErrorInfo(1, ZZM9000E, new String[] {TEXT_MONTH_QUOTA});
                }

                // 2016/09/08 QC#13339 Mod Start
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).splyPgmUnitAmtRate_A0) && !ZYPCommonFunc.isNumeric(glblMsg.A.no(i).splyPgmUnitAmtRate_A0.getValue().toString())) {
                    glblMsg.A.no(i).splyPgmUnitAmtRate_A0.setErrorInfo(1, ZZM9004E);
                } else if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).splyPgmUnitAmtRate_A0) && BigDecimal.ZERO.compareTo(glblMsg.A.no(i).splyPgmUnitAmtRate_A0.getValue()) > 0) {
                    glblMsg.A.no(i).splyPgmUnitAmtRate_A0.setErrorInfo(1, NWCM0151E); // 2016/09/15 QC#13339-1 Mod
                }

                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).splyPgmMlyQuotQty_A0) && !ZYPCommonFunc.isNumeric(glblMsg.A.no(i).splyPgmMlyQuotQty_A0.getValue().toString())) {
                    glblMsg.A.no(i).splyPgmMlyQuotQty_A0.setErrorInfo(1, ZZM9004E);
                } else if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).splyPgmMlyQuotQty_A0) && BigDecimal.ZERO.compareTo(glblMsg.A.no(i).splyPgmMlyQuotQty_A0.getValue()) > 0) {
                    glblMsg.A.no(i).splyPgmMlyQuotQty_A0.setErrorInfo(1, NWCM0151E); // 2016/09/15 QC#13339-1 Mod
                }
                // 2016/09/08 QC#13339 Mod End
            }

            // 2016/09/06 QC#13315 Add Start
            if (DISP_CTRL_CD_NEW.equals(dplyCtrlCd) && ZYPCommonFunc.hasValue(glblMsg.A.no(i).billToCustAcctCd_A0)) {
                if (!NWCL0100CommonLogic.isExistDsAcctCust(bizMsg, glblMsg.A.no(i).billToCustAcctCd_A0.getValue())) {
                    glblMsg.A.no(i).billToCustAcctCd_A0.setErrorInfo(1, NWCM0152E, new String[] {TEXT_BILL_TO}); // 2016/09/15 QC#13341-1 Mod
                }
            }
            // 2016/09/06 QC#13315 Add End
        }

        // 2016/09/07 QC#13337 Del Start
        NWCL0100CommonLogic.checkValidTermDuplication(glblMsg);
        // 2016/09/07 QC#13337 Del End

        if (!NWCL0100CommonLogic.showFirstErrorPage(bizMsg, glblMsg)) {
            return;
        }

        SPLY_PGM_CONTRTMsg splyPgmContrTMsg = null;
        List<SPLY_PGM_CONTRTMsg> insSplyPgmContrTMsgAry = new ArrayList<SPLY_PGM_CONTRTMsg>();
        List<SPLY_PGM_CONTRTMsg> updSplyPgmContrTMsgAry = new ArrayList<SPLY_PGM_CONTRTMsg>();
        List<SPLY_PGM_CONTRTMsg> delSplyPgmContrTMsgAry = new ArrayList<SPLY_PGM_CONTRTMsg>();

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWCL0100_ASMsg glblAMsg = glblMsg.A.no(i);

            if (!ZYPCommonFunc.hasValue(glblAMsg.autoDrCratFlg_A0)) {
                ZYPEZDItemValueSetter.setValue(glblAMsg.autoDrCratFlg_A0, ZYPConstant.FLG_OFF_N);
            }

            // Set Insert Data
            if (!ZYPCommonFunc.hasValue(glblAMsg.splyPgmContrPk_A0)) {
                splyPgmContrTMsg = new SPLY_PGM_CONTRTMsg();
                EZDMsg.copy(glblAMsg, "A0", splyPgmContrTMsg, "");

                ZYPEZDItemValueSetter.setValue(splyPgmContrTMsg.splyPgmContrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPLY_PGM_CONTR_SQ));
                ZYPEZDItemValueSetter.setValue(splyPgmContrTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(splyPgmContrTMsg.contrCratPsnCd, userId);
                ZYPEZDItemValueSetter.setValue(splyPgmContrTMsg.contrCratDt, slsDt);
                ZYPEZDItemValueSetter.setValue(splyPgmContrTMsg.contrUpdPsnCd, userId);
                ZYPEZDItemValueSetter.setValue(splyPgmContrTMsg.contrUpdDt, slsDt);

                insSplyPgmContrTMsgAry.add(splyPgmContrTMsg);
                continue;
            }

            // Set Update Data
            BigDecimal splyPgmContrPk = glblAMsg.splyPgmContrPk_A0.getValue();
            String dsAcctNm = glblAMsg.dsAcctNm_A0.getValue();
            String effFromDt = glblAMsg.effFromDt_A0.getValue();
            String effThruDt = glblAMsg.effThruDt_A0.getValue();
            BigDecimal splyPgmUnitAmtRate = glblAMsg.splyPgmUnitAmtRate_A0.getValue();
            BigDecimal splyPgmMlyQuotQty = glblAMsg.splyPgmMlyQuotQty_A0.getValue();
            String autoDrCratFlg = glblAMsg.autoDrCratFlg_A0.getValue();
            String contrCratPsnCd = glblAMsg.contrCratPsnCd_A0.getValue();
            String contrCratDt = glblAMsg.contrCratDt_A0.getValue();
            String contrUpdPsnCd = glblAMsg.contrUpdPsnCd_A0.getValue();
            String contrUpdDt = glblAMsg.contrUpdDt_A0.getValue();

            // 2017/11/14 Sol#265(QC#18788) Add Start
            BigDecimal prcGrpPk = new BigDecimal(-1);

            if (ZYPCommonFunc.hasValue(glblAMsg.prcGrpPk_A0)){
                prcGrpPk = glblAMsg.prcGrpPk_A0.getValue();
            }
            // 2017/11/14 Sol#265(QC#18788) Add End

            for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                NWCL0100_BSMsg glblBMsg = glblMsg.B.no(j);
                if (splyPgmContrPk.compareTo(glblBMsg.splyPgmContrPk_B0.getValue()) != 0) {
                    continue;
                }
                
                // 2017/11/14 Sol#265(QC#18788) Add Start
                BigDecimal prfGrpPk_B0 = new BigDecimal(-1);
                
                if (ZYPCommonFunc.hasValue(glblBMsg.prcGrpPk_B0)){
                    prfGrpPk_B0 = glblBMsg.prcGrpPk_B0.getValue();
                }
                // 2017/11/14 Sol#265(QC#18788) Add End

                if (!dsAcctNm.equals(glblBMsg.dsAcctNm_B0.getValue()) || !effFromDt.equals(glblBMsg.effFromDt_B0.getValue()) || !effThruDt.equals(glblBMsg.effThruDt_B0.getValue())
                        || !autoDrCratFlg.equals(glblBMsg.autoDrCratFlg_B0.getValue()) || !contrCratPsnCd.equals(glblBMsg.contrCratPsnCd_B0.getValue()) || !contrCratDt.equals(glblBMsg.contrCratDt_B0.getValue())
                        || !contrUpdPsnCd.equals(glblBMsg.contrUpdPsnCd_B0.getValue()) || !contrUpdDt.equals(glblBMsg.contrUpdDt_B0.getValue()) || splyPgmUnitAmtRate.compareTo(glblBMsg.splyPgmUnitAmtRate_B0.getValue()) != 0
                        || splyPgmMlyQuotQty.compareTo(glblBMsg.splyPgmMlyQuotQty_B0.getValue()) != 0
                        // 2017/11/14 Sol#265(QC#18788) Add Start
                        || !prcGrpPk.equals(prfGrpPk_B0)) {
                        // 2017/11/14 Sol#265(QC#18788) Add End

                    splyPgmContrTMsg = new SPLY_PGM_CONTRTMsg();
                    EZDMsg.copy(glblAMsg, "A0", splyPgmContrTMsg, "");
                    splyPgmContrTMsg.glblCmpyCd.setValue(glblCmpyCd);
                    splyPgmContrTMsg.ezUpTime.setValue(glblAMsg.ezUpTime_A0.getValue());
                    splyPgmContrTMsg.ezUpTimeZone.setValue(glblAMsg.ezUpTimeZone_A0.getValue());

                    ZYPEZDItemValueSetter.setValue(splyPgmContrTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(splyPgmContrTMsg.contrUpdPsnCd, userId);
                    ZYPEZDItemValueSetter.setValue(splyPgmContrTMsg.contrUpdDt, slsDt);

                    updSplyPgmContrTMsgAry.add(splyPgmContrTMsg);
                }
                break;
            }
        }

        // Set Delete Data
        final List<Integer> deleteRows = ZYPTableUtil.getSelectedRows(glblMsg.B, "xxPgFlg_B0", ZYPConstant.FLG_ON_Y);
        for (int delIdx : deleteRows) {
            NWCL0100_BSMsg glblBMsg = glblMsg.B.no(delIdx);
            splyPgmContrTMsg = new SPLY_PGM_CONTRTMsg();
            splyPgmContrTMsg.glblCmpyCd.setValue(glblCmpyCd);
            splyPgmContrTMsg.splyPgmContrPk.setValue(glblBMsg.splyPgmContrPk_B0.getValue());
            delSplyPgmContrTMsgAry.add(splyPgmContrTMsg);
        }

        if (delSplyPgmContrTMsgAry.size() > 0) {
            int res = S21FastTBLAccessor.removeLogical((SPLY_PGM_CONTRTMsg[]) delSplyPgmContrTMsgAry.toArray(new SPLY_PGM_CONTRTMsg[delSplyPgmContrTMsgAry.size()]));
            if (res != delSplyPgmContrTMsgAry.size()) {
                bizMsg.setMessageInfo(ZZZM9014E, new String[] {String.valueOf(res) });
            }
        }

        if (insSplyPgmContrTMsgAry.size() > 0) {
            int res = S21FastTBLAccessor.insert((SPLY_PGM_CONTRTMsg[]) insSplyPgmContrTMsgAry.toArray(new SPLY_PGM_CONTRTMsg[insSplyPgmContrTMsgAry.size()]));
            if (res != insSplyPgmContrTMsgAry.size()) {
                bizMsg.setMessageInfo(ZZZM9012E, new String[] {String.valueOf(res) });
            }
        }

        if (updSplyPgmContrTMsgAry.size() > 0) {

            List<SPLY_PGM_CONTRTMsg> updPrvSplyPgmContrTMsgAry = new ArrayList<SPLY_PGM_CONTRTMsg>();

            for (SPLY_PGM_CONTRTMsg curSplyPgmContrTMsg : updSplyPgmContrTMsgAry) {
                SPLY_PGM_CONTRTMsg prvSplyPgmContrTMsg = (SPLY_PGM_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(curSplyPgmContrTMsg);
                if (!ZYPDateUtil.isSameTimeStamp(prvSplyPgmContrTMsg.ezUpTime.getValue(), prvSplyPgmContrTMsg.ezUpTimeZone.getValue(), curSplyPgmContrTMsg.ezUpTime.getValue(), curSplyPgmContrTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(ZZZM9004E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(prvSplyPgmContrTMsg.effFromDt, curSplyPgmContrTMsg.effFromDt);
                ZYPEZDItemValueSetter.setValue(prvSplyPgmContrTMsg.effThruDt, curSplyPgmContrTMsg.effThruDt);
                ZYPEZDItemValueSetter.setValue(prvSplyPgmContrTMsg.splyPgmUnitAmtRate, curSplyPgmContrTMsg.splyPgmUnitAmtRate);
                ZYPEZDItemValueSetter.setValue(prvSplyPgmContrTMsg.splyPgmMlyQuotQty, curSplyPgmContrTMsg.splyPgmMlyQuotQty);
                ZYPEZDItemValueSetter.setValue(prvSplyPgmContrTMsg.autoDrCratFlg, curSplyPgmContrTMsg.autoDrCratFlg);
                ZYPEZDItemValueSetter.setValue(prvSplyPgmContrTMsg.contrUpdPsnCd, curSplyPgmContrTMsg.contrUpdPsnCd);
                ZYPEZDItemValueSetter.setValue(prvSplyPgmContrTMsg.contrUpdDt, curSplyPgmContrTMsg.contrUpdDt);
                // 2017/11/14 Sol#265(QC#18788) Add Start
                ZYPEZDItemValueSetter.setValue(prvSplyPgmContrTMsg.prcGrpPk, curSplyPgmContrTMsg.prcGrpPk);
                // 2017/11/14 Sol#265(QC#18788) Add End

                updPrvSplyPgmContrTMsgAry.add(prvSplyPgmContrTMsg);
            }

            int res = S21FastTBLAccessor.update((SPLY_PGM_CONTRTMsg[]) updPrvSplyPgmContrTMsgAry.toArray(new SPLY_PGM_CONTRTMsg[updPrvSplyPgmContrTMsgAry.size()]));
            if (res != updPrvSplyPgmContrTMsgAry.size()) {
                bizMsg.setMessageInfo(ZZZM9013E, new String[] {String.valueOf(res) });
            }
        }
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_DeleteSearch(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S0)) {

            bizMsg.srchOptPk_S0.setErrorInfo(1, ZZM9000E, new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NWCL0100CommonLogic.callNszc0330forDeleteSearch(bizMsg, glblMsg, getContextUserInfo().getUserId());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0100Scrn00_SaveSearch(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S0) && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S0)) {

            bizMsg.srchOptNm_S0.setErrorInfo(1, ZZM9000E, new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NWCL0100CommonLogic.isExistSaveSearchName(bizMsg)) {

            bizMsg.srchOptNm_S0.setErrorInfo(1, NWCM0128E, new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NWCL0100CommonLogic.callNszc0330forSaveSearch(bizMsg, glblMsg, getContextUserInfo().getUserId());
    }
}
