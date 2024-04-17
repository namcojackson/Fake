/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3000;

import static business.blap.NMAL3000.constant.NMAL3000Constant.CHK_A;
import static business.blap.NMAL3000.constant.NMAL3000Constant.SELL_TO_CUST_CD01;
import static business.blap.NMAL3000.constant.NMAL3000Constant.GLBCMPY_CD01;
import static business.blap.NMAL3000.constant.NMAL3000Constant.INSERT_FLG;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM0014E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM0070E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM0072E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM0176E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM0182E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM0185E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM8020E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM8054E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM8121E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NZZM0003E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.RECORD_NOTFOUND;
import static business.blap.NMAL3000.constant.NMAL3000Constant.TABLE_NAME_DS_DLR_AUTH_INFO;
import static business.blap.NMAL3000.constant.NMAL3000Constant.ZZM9000E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL3000.common.NMAL3000CommonLogic;
import business.blap.NMAL3000.constant.NMAL3000Constant;
import business.db.DS_DLR_AUTH_INFOTMsg;
import business.db.MKT_MDLTMsg;
import business.db.MKT_MDLTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;



/**
 *<pre>
 * NMAL3000BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/09   SRAA            Y.Chen          Update          QC#5169
 * 2016/09/09   SRAA            Y.Chen          Update          QC#12721
 * 2016/09/13   SRAA            Y.Chen          Update          QC#13966
 * 2018/11/27   Fujitsu         R.Nakamura      Update          QC#27336
 *</pre>
 */
public class NMAL3000BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL3000CMsg bizMsg = (NMAL3000CMsg) cMsg;
            NMAL3000SMsg glblMsg = (NMAL3000SMsg) sMsg;

            if ("NMAL3000Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_DeleteRow_DelrAM".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_DeleteRow_DelrAM(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * DeleteRow_CondGrp Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_DeleteRow_DelrAM(NMAL3000CMsg bizMsg , NMAL3000SMsg glblMsg) {

        NMAL3000CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        boolean checkFlag = false;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxChkBox_A)) {
                checkFlag = true;
            }
        }

        if (!checkFlag) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(glblMsg.A, CHK_A, FLG_ON_Y);
        for (int idx : selectRows) {

            if (INSERT_FLG.equals(glblMsg.A.no(idx).xxRowId_A.getValue())) {
                continue;
            }

            MKT_MDLTMsg mkttms = getMdl(glblMsg.A.no(idx).mktMdlCd_A.getValue());
            DS_DLR_AUTH_INFOTMsg tMsg = getDlrAuthInfo(glblMsg.A.no(idx).dsAcctCustNum_A.getValue()
                    , glblMsg.A.no(idx).effFromDt_A.getValue()
                    , mkttms);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(
                          glblMsg.A.no(idx).ezUpTime_A.getValue()
                        , glblMsg.A.no(idx).ezUpTimeZone_A.getValue()
                        , tMsg.ezUpTime.getValue()
                        , tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                }

                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return;
                }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            return;
        }

        ZYPTableUtil.deleteRows(glblMsg.A, selectRows);
        // Add Start 2018/11/27 QC#27336
        ZYPTableUtil.deleteRows(glblMsg.B, selectRows);
        // Add End 2018/11/27 QC#27336

        if (selectRows.size() > 0) {
            int pageIndex = 0;
            if (selectRows.size() == bizMsg.A.getValidCount()) {
                pageIndex = glblMsg.A.getValidCount() - 1;
            } else {
                pageIndex = selectRows.get(0);
            }
            NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, pageIndex);
        }

    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_CMN_Submit(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        NMAL3000CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        List<Integer> accountErrorIdxList = new ArrayList<Integer>(glblMsg.A.getValidCount());
        List<Integer> modelErrorIdxList = new ArrayList<Integer>(glblMsg.A.getValidCount());
        List<Integer> dateErrorIdxList = new ArrayList<Integer>(glblMsg.A.getValidCount());
        List<Integer> salesServiceEerrorIdxList = new ArrayList<Integer>(glblMsg.A.getValidCount());

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            checkAccountNo(glblMsg, accountErrorIdxList, i);
            checkModelExist(glblMsg, modelErrorIdxList, i);
            checkSalesService(glblMsg, salesServiceEerrorIdxList, i);
            checkDateFromTo(glblMsg, dateErrorIdxList, i);
        }

        String[] chkAttrbNmList = new String[]{
                "dsAcctCustNum_A",
                "dsAcctDlrCd_A",
                "locNum_A",
                "mktMdlCd_A",
                "slsAuthFlg_A",
                "svcAuthFlg_A",
                "effFromDt_A",
                "effThruDt_A"};

        Integer[] errIdxList = NMAL3000CommonLogic.checkDupAttrb(glblMsg.A, chkAttrbNmList);
        if (errIdxList.length > 0) {
            for (int errIdx : errIdxList) {
                glblMsg.A.no(errIdx).xxChkBox_A.setErrorInfo(1, NMAM0072E, new String[]{"Dealer Authorization Detail"});
            }
        }

        String[] grpAttrbNmList2 = new String[]{
                "dsAcctCustNum_A",
                "mktMdlCd_A"};

        Integer[] errIdxList2 = NMAL3000CommonLogic.checkDupTermByGrp(glblMsg.A, "effFromDt_A", "effThruDt_A", grpAttrbNmList2);
        if (errIdxList2.length > 0) {
            for (int errIdx : errIdxList2) {
                glblMsg.A.no(errIdx).effFromDt_A.setErrorInfo(1, NMAM0072E, new String[]{"Combination of Dealer Authorization and Effective date"});
                glblMsg.A.no(errIdx).effThruDt_A.setErrorInfo(1, NMAM0072E, new String[]{"Combination of Dealer Authorization and Effective date"});
            }
        }

        int pageIdx = -1;
        if (accountErrorIdxList.size() > 0) {
            pageIdx = accountErrorIdxList.get(0);

        } else if (modelErrorIdxList.size() > 0) {
            pageIdx = modelErrorIdxList.get(0);

        } else if (salesServiceEerrorIdxList.size() > 0) {
            pageIdx = salesServiceEerrorIdxList.get(0);

        } else if (dateErrorIdxList.size() > 0) {
            pageIdx = dateErrorIdxList.get(0);

        } else if (errIdxList.length > 0) {
            pageIdx = errIdxList[0];

        } else if (errIdxList2.length > 0) {
            pageIdx = errIdxList2[0];
        }

        if (pageIdx != -1) {
           NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, pageIdx);
           return;
        }

        for (int i = 0; i < glblMsg.A.length(); i++) {
            String rowIdA = glblMsg.A.no(i).xxRowId_A.getValue();
            if (!ZYPCommonFunc.hasValue(rowIdA)) {
                continue;
            }

            if (rowIdA.equals(INSERT_FLG)) {
                MKT_MDLTMsg mkttms = getMdl(glblMsg.A.no(i).mktMdlCd_A.getValue());

                DS_DLR_AUTH_INFOTMsg tMsg = new DS_DLR_AUTH_INFOTMsg();
                setValueTmsg(glblMsg, i, mkttms, tMsg);
                EZDTBLAccessor.create(tMsg);

                if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0176E, new String[]{TABLE_NAME_DS_DLR_AUTH_INFO});
                    return;
                }

                // Add Start 2018/11/28 QC#27336
                if (NMAL3000CommonLogic.checkOverlapData(glblMsg, i, mkttms)) {
                    glblMsg.A.no(i).effFromDt_A.setErrorInfo(1, NMAM0072E, new String[]{"Combination of Dealer Authorization and Effective date"});
                    glblMsg.A.no(i).effThruDt_A.setErrorInfo(1, NMAM0072E, new String[]{"Combination of Dealer Authorization and Effective date"});
                    NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, i);
                    return;
                }
                // Add End 2018/11/28 QC#27336
                continue;
            }

            for (int j = 0; j < glblMsg.B.length(); j++) {
                String rowIdB = glblMsg.B.no(j).xxRowId_B.getValue();
                if (!ZYPCommonFunc.hasValue(rowIdB)) {
                    continue;
                }

                if (rowIdA.equals(rowIdB)) {
                    if (glblMsg.A.no(i).dsAcctCustNum_A.getValue().equals(glblMsg.B.no(j).dsAcctCustNum_B.getValue())
                            && glblMsg.A.no(i).dsAcctDlrCd_A.getValue().equals(glblMsg.B.no(j).dsAcctDlrCd_B.getValue())
                            && glblMsg.A.no(i).mktMdlCd_A.getValue().equals(glblMsg.B.no(j).mktMdlCd_B.getValue())
                            && (glblMsg.A.no(i).slsAuthFlg_A.getValue().equals(glblMsg.B.no(j).slsAuthFlg_B.getValue())
                               || (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).slsAuthFlg_A) && glblMsg.B.no(j).slsAuthFlg_B.getValue().equals(ZYPConstant.FLG_OFF_N)))
                            && (glblMsg.A.no(i).svcAuthFlg_A.getValue().equals(glblMsg.B.no(j).svcAuthFlg_B.getValue())
                               || (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).svcAuthFlg_A) && glblMsg.B.no(j).svcAuthFlg_B.getValue().equals(ZYPConstant.FLG_OFF_N)))
                            && glblMsg.A.no(i).effFromDt_A.getValue().equals(glblMsg.B.no(j).effFromDt_B.getValue())
                            && glblMsg.A.no(i).effThruDt_A.getValue().equals(glblMsg.B.no(j).effThruDt_B.getValue())
                            ) {
                        break;
                    } else {
                        // Mod Start 2018/11/27 QC#27336
//                        MKT_MDLTMsg mkttms = getMdl(glblMsg.A.no(i).mktMdlCd_A.getValue());
//                        DS_DLR_AUTH_INFOTMsg tMsg = getDlrAuthInfo(glblMsg.A.no(i).dsAcctCustNum_A.getValue()
//                                , glblMsg.A.no(i).effFromDt_A.getValue()
//                                , mkttms);
                        MKT_MDLTMsg mkttms = getMdl(glblMsg.B.no(j).mktMdlCd_B.getValue());
                        DS_DLR_AUTH_INFOTMsg tMsg = getDlrAuthInfo(glblMsg.B.no(j).dsAcctCustNum_B.getValue()
                                , glblMsg.B.no(j).effFromDt_B.getValue()
                                , mkttms);
                        // Mod End 2018/11/27 QC#27336

                        if (!ZYPDateUtil.isSameTimeStamp(
                                glblMsg.A.no(i).ezUpTime_A.getValue()
                                , glblMsg.A.no(i).ezUpTimeZone_A.getValue()
                                , tMsg.ezUpTime.getValue()
                                , tMsg.ezUpTimeZone.getValue()
                                )) {
                            bizMsg.setMessageInfo(NZZM0003E);
                            return;
                        }

                        // Mod Start 2018/11/28 QC#27336
                        if (glblMsg.A.no(i).dsAcctCustNum_A.getValue().equals(glblMsg.B.no(j).dsAcctCustNum_B.getValue())
                                && glblMsg.A.no(i).mktMdlCd_A.getValue().equals(glblMsg.B.no(j).mktMdlCd_B.getValue())
                                && glblMsg.A.no(i).effFromDt_A.getValue().equals(glblMsg.B.no(j).effFromDt_B.getValue())
                                ) {
                            setValueTmsg(glblMsg, i, mkttms, tMsg);
                            EZDTBLAccessor.update(tMsg);
                        } else {
                            tMsg = new DS_DLR_AUTH_INFOTMsg();
                            setValueForDelTmsg(glblMsg, j, mkttms, tMsg);
                            EZDTBLAccessor.remove(tMsg);
                            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                                bizMsg.setMessageInfo(NMAM0176E, new String[]{TABLE_NAME_DS_DLR_AUTH_INFO});
                                return;
                            }
                            tMsg = new DS_DLR_AUTH_INFOTMsg();
                            setValueTmsg(glblMsg, i, mkttms, tMsg);
                            EZDTBLAccessor.create(tMsg);
                        }
                        // Mod End 2018/11/28 QC#27336

                        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NMAM0176E, new String[]{TABLE_NAME_DS_DLR_AUTH_INFO});
                            return;
                        }

                        // Add Start 2018/11/28 QC#27336
                        if (NMAL3000CommonLogic.checkOverlapData(glblMsg, i, mkttms)) {
                            glblMsg.A.no(i).effFromDt_A.setErrorInfo(1, NMAM0072E, new String[]{"Combination of Dealer Authorization and Effective date"});
                            glblMsg.A.no(i).effThruDt_A.setErrorInfo(1, NMAM0072E, new String[]{"Combination of Dealer Authorization and Effective date"});
                            NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, i);
                            return;
                        }
                        // Add End 2018/11/28 QC#27336
                    }
                    break;
                }
            }
        }
    }

    private void checkDateFromTo(NMAL3000SMsg glblMsg, List<Integer> dateErrorIdxList, int i) {
        if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effFromDt_A)
                && ZYPCommonFunc.hasValue(glblMsg.A.no(i).effThruDt_A)) {

            if (ZYPDateUtil.compare(glblMsg.A.no(i).effThruDt_A.getValue()
                , glblMsg.A.no(i).effFromDt_A.getValue()) < 0) {
                // Mod Start 2018/11/28 QC#27336
//                if (INSERT_FLG.equals(glblMsg.A.no(i).xxRowId_A.getValue())) {
                glblMsg.A.no(i).effFromDt_A.setErrorInfo(1, NMAM0185E);
//                }
                // Mod End 2018/11/28 QC#27336
                glblMsg.A.no(i).effThruDt_A.setErrorInfo(1, NMAM0185E);
                dateErrorIdxList.add(i);
            }
        }
    }

    private void checkSalesService(NMAL3000SMsg glblMsg, List<Integer> salesServiceEerrorIdxList, int i) {
        if (
             // QC#12721
             // (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).slsAuthFlg_A) && ZYPCommonFunc.hasValue(glblMsg.A.no(i).svcAuthFlg_A)) || 
                (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).slsAuthFlg_A) && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).svcAuthFlg_A))
        ) {
            glblMsg.A.no(i).slsAuthFlg_A.setErrorInfo(1, NMAM0070E , new String[]{"Sales", "Service"});
            glblMsg.A.no(i).svcAuthFlg_A.setErrorInfo(1, NMAM0070E,  new String[]{"Sales", "Service"});
            salesServiceEerrorIdxList.add(i);
        }
    }

    private void checkModelExist(NMAL3000SMsg glblMsg, List<Integer> modelErrorIdxList, int i) {
        MKT_MDLTMsg mkttms = new MKT_MDLTMsg();
        MKT_MDLTMsgArray resultList = new MKT_MDLTMsgArray();
        ZYPEZDItemValueSetter.setValue(mkttms.mktMdlCd, glblMsg.A.no(i).mktMdlCd_A.getValue());
        resultList = (MKT_MDLTMsgArray) S21CodeTableAccessor.findByCondition(mkttms);

        if (resultList.length() == RECORD_NOTFOUND) {
            glblMsg.A.no(i).mktMdlCd_A.setErrorInfo(1, NMAM8121E , new String[]{"Marketing Model"});
            modelErrorIdxList.add(i);
        }
    }

    private void checkAccountNo(NMAL3000SMsg glblMsg, List<Integer> accountErrorIdxList, int i) {
        SELL_TO_CUSTTMsg tmsg = new SELL_TO_CUSTTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue(SELL_TO_CUST_CD01, glblMsg.A.no(i).dsAcctCustNum_A.getValue());
        tmsg.setConditionValue(GLBCMPY_CD01, getGlobalCompanyCode());
        SELL_TO_CUSTTMsgArray tmsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
        if (tmsgArray.length() == RECORD_NOTFOUND) {
            glblMsg.A.no(i).dsAcctCustNum_A.setErrorInfo(1, NMAM8121E , new String[]{"Account Number"});
            accountErrorIdxList.add(i);
        }
    }

    private DS_DLR_AUTH_INFOTMsg getDlrAuthInfo(String account, String effFrom , MKT_MDLTMsg mkttms) {

        DS_DLR_AUTH_INFOTMsg tMsg = new DS_DLR_AUTH_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctCustNum, account);
        ZYPEZDItemValueSetter.setValue(tMsg.mktMdlCd, mkttms.mktMdlCd);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, effFrom);
        tMsg = (DS_DLR_AUTH_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        return tMsg;
    }

    private MKT_MDLTMsg getMdl(String modelnm) {
        MKT_MDLTMsg mkttms = new MKT_MDLTMsg();
        MKT_MDLTMsgArray resultList = new MKT_MDLTMsgArray();
        ZYPEZDItemValueSetter.setValue(mkttms.mktMdlCd, modelnm);
        resultList = (MKT_MDLTMsgArray) S21CodeTableAccessor.findByCondition(mkttms);
        mkttms = (MKT_MDLTMsg) resultList.get(0);
        return mkttms;
    }

    private void setValueTmsg(NMAL3000SMsg glblMsg, int i, MKT_MDLTMsg mkttms, DS_DLR_AUTH_INFOTMsg tMsg) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctCustNum, glblMsg.A.no(i).dsAcctCustNum_A);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctDlrCd, glblMsg.A.no(i).dsAcctDlrCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.mktMdlCd, mkttms.mktMdlCd);

        if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).slsAuthFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.slsAuthFlg, glblMsg.A.no(i).slsAuthFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.slsAuthFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).svcAuthFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.svcAuthFlg, glblMsg.A.no(i).svcAuthFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.svcAuthFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.A.no(i).effFromDt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.A.no(i).effThruDt_A);
        // QC#13966
        ZYPEZDItemValueSetter.setValue(tMsg.upldUserId, this.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(tMsg.upldDt, ZYPDateUtil.getSalesDate());
    }

    // Add Start 2018/11/28 QC#27336
    private void setValueForDelTmsg(NMAL3000SMsg glblMsg, int i, MKT_MDLTMsg mkttms, DS_DLR_AUTH_INFOTMsg tMsg) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctCustNum, glblMsg.B.no(i).dsAcctCustNum_B);
        ZYPEZDItemValueSetter.setValue(tMsg.mktMdlCd, mkttms.mktMdlCd);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.B.no(i).effFromDt_B);
    }
    // Add End 2018/11/28 QC#27336

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_DeleteSearch(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, NMAM0014E // [@] is not selected.
                    , new String[] {converter.convLabel2i18nLabel(NMAL3000Constant.SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NMAL3000CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_SaveSearch(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(NMAL3000Constant.SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NMAL3000CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NMAM0182E
                    , new String[] {
                    converter.convLabel2i18nLabel(NMAL3000Constant.SCRN_ID_00, "Save") //
                    , converter.convLabel2i18nLabel(NMAL3000Constant.SCRN_ID_00, "Search Option Name") });
            return;
        }

        NMAL3000CommonLogic.callNszc0330forSaveSearch(
                bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

        return;
    }
}
