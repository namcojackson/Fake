/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7100;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NMAL7100.common.NMAL7100CheckLogic;
import business.blap.NMAL7100.common.NMAL7100CommonLogic;
import business.db.PRC_MKT_PRMOTMsg;
import business.db.PRC_MKT_PRMO_AUDC_CTRLTMsg;
import business.db.PRC_MKT_PRMO_DTLTMsg;
import business.db.PRC_MKT_PRMO_EXCLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MKT_PRMO_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRMO_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.workflow.core.context.S21WfHumanTaskExecutionContext;
import com.canon.cusa.s21.framework.workflow.ezd.business.S21WfBusinessHandlerWF02Support;

import static business.blap.NMAL7100.constant.NMAL7100Constant.NZZM0003E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8020E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8054E;

import static business.blap.NMAL7100.constant.NMAL7100Constant.TAB_MKT_AUDC;
import static business.blap.NMAL7100.constant.NMAL7100Constant.TAB_CAN_NOT_BE_USED;
import static business.blap.NMAL7100.constant.NMAL7100Constant.PRC_MKT_PRMO_SQ;
import static business.blap.NMAL7100.constant.NMAL7100Constant.PRC_MKT_PRMO_AUDC_CTRL_SQ;
import static business.blap.NMAL7100.constant.NMAL7100Constant.PRC_MKT_PRMO_EXCL_SQ;

import static business.blap.NMAL7100.constant.NMAL7100Constant.CHK_MX;
import static business.blap.NMAL7100.constant.NMAL7100Constant.CHK_CX;
import static business.blap.NMAL7100.constant.NMAL7100Constant.CHK_DA;

/** 
 *<pre>
 * NMAL7100BL06
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/15/2015   Fujitsu         M.Hara          Create          CSA
 * 01/19/2016   Fujitsu         M.Hara          Update          QC#3089
 * 04/15/2016   Fujitsu         W.Honda         Update          QC#6154
 * 2016/11/10   Fujitsu         W.Honda         Update          QC#15842
 * 2019/12/06   Fujitsu         C.Hara          Update          QC#54216
</pre>
 */
public class NMAL7100BL06 extends S21WfBusinessHandlerWF02Support {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg, S21WfHumanTaskExecutionContext wfExecCtx) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            NMAL7100CMsg bizMsg = (NMAL7100CMsg) cMsg;
            NMAL7100SMsg glblMsg = (NMAL7100SMsg) sMsg;

            if ("NMAL7100Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_Del_MktAud".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_Del_MktAud(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_Del_CanNotBeUsed".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_Del_CanNotBeUsed(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_Del_Detail".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_Del_Detail(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NMAL7100CMsg) cMsg, (NMAL7100SMsg) sMsg);
            } else if ("NMAL7100Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NMAL7100CMsg) cMsg, (NMAL7100SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // START QC#3089 01/19/2016 ADD
            NMAL7100CommonLogic.changeApplyBtnFlg(bizMsg, ZYPConstant.FLG_OFF_N);
            // END QC#3089 01/19/2016 ADD
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL7100Scrn00_Del_MktAud(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        List<Integer> selectRows = new ArrayList<Integer>();
        selectRows = ZYPTableUtil.getSelectedRows(bizMsg.X, CHK_MX, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        for (int idx : selectRows) {

            if (!ZYPCommonFunc.hasValue(bizMsg.X.no(idx).prcMktPrmoAudcCtrlPk_MX)) {
                continue;
            }

            PRC_MKT_PRMO_AUDC_CTRLTMsg tMsg = new PRC_MKT_PRMO_AUDC_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoAudcCtrlPk, bizMsg.X.no(idx).prcMktPrmoAudcCtrlPk_MX);
            tMsg = (PRC_MKT_PRMO_AUDC_CTRLTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.X.no(idx).ezUpTime_MX.getValue(), bizMsg.X.no(idx).ezUpTimeZone_MX.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
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

        ZYPTableUtil.deleteRows(bizMsg.X, selectRows);
    }

    private void doProcess_NMAL7100Scrn00_Del_CanNotBeUsed(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        List<Integer> selectRows = new ArrayList<Integer>();
        selectRows = ZYPTableUtil.getSelectedRows(bizMsg.Y, CHK_CX, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        for (int idx : selectRows) {

            if (!ZYPCommonFunc.hasValue(bizMsg.Y.no(idx).prcMktPrmoExclPk_CX)) {
                continue;
            }

            PRC_MKT_PRMO_EXCLTMsg tMsg = new PRC_MKT_PRMO_EXCLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoExclPk, bizMsg.Y.no(idx).prcMktPrmoExclPk_CX);
            tMsg = (PRC_MKT_PRMO_EXCLTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.Y.no(idx).ezUpTime_CX.getValue(), bizMsg.Y.no(idx).ezUpTimeZone_CX.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
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

        ZYPTableUtil.deleteRows(bizMsg.Y, selectRows);
    }

    private void doProcess_NMAL7100Scrn00_Del_Detail(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        List<Integer> selectRows = new ArrayList<Integer>();
        selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_DA, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        // START QC#15842 11/10/2016 ADD
        NMAL7100CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // END QC#15842 11/10/2016 ADD
        for (int idx : selectRows) {

            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(idx).prcMktPrmoDtlPk_DA)) {
                continue;
            }

            PRC_MKT_PRMO_DTLTMsg tMsg = new PRC_MKT_PRMO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoDtlPk, bizMsg.A.no(idx).prcMktPrmoDtlPk_DA);
            tMsg = (PRC_MKT_PRMO_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(idx).ezUpTime_DA.getValue(), bizMsg.A.no(idx).ezUpTimeZone_DA.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
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

        // START QC#15842 11/10/2016 MOD
//        ZYPTableUtil.deleteRows(bizMsg.A, selectRows);{
        List<Integer> delIdx = ZYPTableUtil.getSelectedRows(glblMsg.A, CHK_DA, ZYPConstant.CHKBOX_ON_Y);
        ZYPTableUtil.deleteRows(glblMsg.A, delIdx);
        if (bizMsg.xxPageShowFromNum.getValueInt() > glblMsg.A.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(glblMsg.A.getValidCount() - delIdx.size()));
        }
        NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
        // END QC#15842 11/10/2016 MOD

    }

    private void doProcess_NMAL7100Scrn00_CMN_Submit(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        // START QC#15842 11/10/2016 ADD
        NMAL7100CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        bizMsg.setCommitSMsg(true);
      // END QC#15842 11/10/2016 ADD
        boolean isSuccess = false;

        // *********************************************************************
        // Header Check
        // *********************************************************************
        String glblCmpyCd = getGlobalCompanyCode();
        isSuccess = NMAL7100CheckLogic.checkHeader(bizMsg, glblCmpyCd);

        if (!isSuccess) {
            return;
        }

        // *********************************************************************
        // Marketing Audience Tab Check
        // *********************************************************************
        isSuccess = NMAL7100CheckLogic.checkCustAudTab(bizMsg, glblCmpyCd);

        if (!isSuccess) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, TAB_MKT_AUDC);
            return;
        }

        // *********************************************************************
        // Can Not Be Used Tab Check
        // *********************************************************************
        isSuccess = NMAL7100CheckLogic.checkCanNotBeUsedTab(bizMsg, glblCmpyCd);

        if (!isSuccess) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, TAB_CAN_NOT_BE_USED);
            return;
        }

        // *********************************************************************
        // Detail Search
        // *********************************************************************
        // START QC#15842 11/10/2016 MOD
//        isSuccess = NMAL7100CheckLogic.checkDetailList(bizMsg, glblCmpyCd);
        isSuccess = NMAL7100CheckLogic.checkDetailList(bizMsg, glblMsg, glblCmpyCd);
        // END QC#15842 11/10/2016 MOD

        if (!isSuccess) {
            return;
        }

        // *********************************************************************
        // Insert/Update
        // *********************************************************************
        Boolean isExists = ZYPCommonFunc.hasValue(bizMsg.prcMktPrmoPk_BK);
        if (isExists) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcMktPrmoPk_H1, bizMsg.prcMktPrmoPk_BK);
        } else {
            // Get New ID
            BigDecimal uniqKey = ZYPOracleSeqAccessor.getNumberBigDecimal(PRC_MKT_PRMO_SQ);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcMktPrmoPk_H1, uniqKey);
        }

        // *********************************************************************
        // Submit PRC_MKT_PRMO
        // *********************************************************************
        if (!submitPrcMktPrmo(bizMsg, isExists)) {
            return;
        }

        // *********************************************************************
        // Submit PRC_MKT_PRMO_AUDC_CTRL
        // *********************************************************************
        if (!submitPrcMktPrmoAudcCtrl(bizMsg)) {
            return;
        }

        // *********************************************************************
        // Submit PRC_MKT_PRMO_EXCL
        // *********************************************************************
        if (!submitPrmoMktPrmoExcl(bizMsg)) {
            return;
        }

        // *********************************************************************
        // Submit PRC_MKT_PRMO_DTL
        // *********************************************************************
        // START QC#15842 11/10/2016 MOD
//        if (!submitPrcMktPrmoDtl(bizMsg)) {
        if (!submitPrcMktPrmoDtl(bizMsg, glblMsg)) {
        // END QC#15842 11/10/2016 MOD
            return;
        }

    }

    private boolean submitPrcMktPrmo(NMAL7100CMsg bizMsg, Boolean isExists) {
        PRC_MKT_PRMOTMsg tMsg = new PRC_MKT_PRMOTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoPk, bizMsg.prcMktPrmoPk_H1);

        if (isExists) {
            tMsg = (PRC_MKT_PRMOTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_H1.getValue(), bizMsg.ezUpTimeZone_H1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
            }
            // 2019/12/06 QC#54216 Add Start
            if (!NMAL7100CheckLogic.isChangeForPrcMktPrmo(bizMsg, tMsg)) {
                return true;
            }
            // 2019/12/06 QC#54216 Add End
        }

        ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoNm, bizMsg.prcMktPrmoNm_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoDescTxt, bizMsg.prcMktPrmoDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoCmntTxt, bizMsg.prcMktPrmoCmntTxt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoTpCd, bizMsg.prcMktPrmoTpCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.effThruDt_H1);
        // START QC#3002 01/19/2016 MOD
        if (ZYPCommonFunc.hasValue(bizMsg.actvFlg_H1) && ZYPConstant.FLG_ON_Y.equals(bizMsg.actvFlg_H1.getValue()) ) {
            ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        // END QC#3002 01/19/2016 MOD
        ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_OFF_N);

        if (!submitTbl(tMsg, isExists)) {
            bizMsg.setMessageInfo(NMAM8020E);
            return false;
        }

        return true;
    }

    private boolean submitPrcMktPrmoAudcCtrl(NMAL7100CMsg bizMsg) {
        Boolean isExist;
        String catgCdX1, catgCdX2, catgCdX3;
        String catgValX1, catgValX2, catgValX3;

        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            PRC_MKT_PRMO_AUDC_CTRLTMsg tMsg = new PRC_MKT_PRMO_AUDC_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());

            isExist = ZYPCommonFunc.hasValue(bizMsg.X.no(i).prcMktPrmoAudcCtrlPk_MX);
            if (isExist) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoAudcCtrlPk, bizMsg.X.no(i).prcMktPrmoAudcCtrlPk_MX);
                tMsg = (PRC_MKT_PRMO_AUDC_CTRLTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(bizMsg.X.no(i).ezUpTime_MX.getValue(), bizMsg.X.no(i).ezUpTimeZone_MX.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                // 2019/12/06 QC#54216 Add Start
                if (!NMAL7100CheckLogic.isChangeForAudcCtrl(bizMsg.X.no(i), tMsg)) {
                    continue;
                }
                // 2019/12/06 QC#54216 Add End
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoAudcCtrlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(PRC_MKT_PRMO_AUDC_CTRL_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoPk, bizMsg.prcMktPrmoPk_H1);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.pubFlg_01, ZYPConstant.FLG_OFF_N);

            tMsg.lineBizTpCd_01.clear();
            tMsg.dsAcctNum_01.clear();
            tMsg.coaChCd_01.clear();
            tMsg.dsAcctGrpCd_01.clear();
            tMsg.coaBrCd_01.clear();
            tMsg.csmpNum_01.clear();
            tMsg.prcGrpPk_01.clear();

            catgCdX1 = bizMsg.X.no(i).mktPrmoAudcCatgCd_X1.getValue();
            catgValX1 = bizMsg.X.no(i).xxScrItem30Txt_X1.getValue();
            if (MKT_PRMO_AUDC_CATG.PUBLIC.equals(catgCdX1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.pubFlg_01, ZYPConstant.FLG_ON_Y);
            } else if (MKT_PRMO_AUDC_CATG.BUSINESS_LINE.equals(catgCdX1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd_01, catgValX1);
            } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_NUM.equals(catgCdX1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum_01, catgValX1);
            } else if (MKT_PRMO_AUDC_CATG.COA_CHANNEL.equals(catgCdX1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.coaChCd_01, catgValX1);
            } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_GROUP.equals(catgCdX1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctGrpCd_01, catgValX1);
            } else if (MKT_PRMO_AUDC_CATG.COA_BRANCH.equals(catgCdX1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd_01, catgValX1);
            } else if (MKT_PRMO_AUDC_CATG.CSMP_NUM.equals(catgCdX1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.csmpNum_01, catgValX1);
            } else if (MKT_PRMO_AUDC_CATG.CUSTOMER_PRICE_GROUP.equals(catgCdX1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcGrpPk_01, NMAL7100CommonLogic.convToBigDecimal(catgValX1));
            }

            tMsg.dsAcctNum_02.clear();
            tMsg.dsAcctGrpCd_02.clear();
            tMsg.coaBrCd_02.clear();
            tMsg.csmpNum_02.clear();
            tMsg.prcGrpPk_02.clear();

            catgCdX2 = bizMsg.X.no(i).mktPrmoAudcCatgCd_X2.getValue();
            catgValX2 = bizMsg.X.no(i).xxScrItem30Txt_X2.getValue();
            if (MKT_PRMO_AUDC_CATG.ACCOUNT_NUM.equals(catgCdX2)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum_02, catgValX2);
            } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_GROUP.equals(catgCdX2)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctGrpCd_02, catgValX2);
            } else if (MKT_PRMO_AUDC_CATG.COA_BRANCH.equals(catgCdX2)) {
                ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd_02, catgValX2);
            } else if (MKT_PRMO_AUDC_CATG.CSMP_NUM.equals(catgCdX2)) {
                ZYPEZDItemValueSetter.setValue(tMsg.csmpNum_02, catgValX2);
            } else if (MKT_PRMO_AUDC_CATG.CUSTOMER_PRICE_GROUP.equals(catgCdX2)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcGrpPk_02, NMAL7100CommonLogic.convToBigDecimal(catgValX2));
            }

            tMsg.dsAcctNum_03.clear();
            tMsg.dsAcctGrpCd_03.clear();
            tMsg.coaBrCd_03.clear();
            tMsg.csmpNum_03.clear();
            tMsg.prcGrpPk_03.clear();

            catgCdX3 = bizMsg.X.no(i).mktPrmoAudcCatgCd_X3.getValue();
            catgValX3 = bizMsg.X.no(i).xxScrItem30Txt_X3.getValue();

            if (MKT_PRMO_AUDC_CATG.ACCOUNT_NUM.equals(catgCdX3)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum_03, catgValX3);
            } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_GROUP.equals(catgCdX3)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctGrpCd_03, catgValX3);
            } else if (MKT_PRMO_AUDC_CATG.COA_BRANCH.equals(catgCdX3)) {
                ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd_03, catgValX3);
            } else if (MKT_PRMO_AUDC_CATG.CSMP_NUM.equals(catgCdX3)) {
                ZYPEZDItemValueSetter.setValue(tMsg.csmpNum_03, catgValX3);
            } else if (MKT_PRMO_AUDC_CATG.CUSTOMER_PRICE_GROUP.equals(catgCdX3)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcGrpPk_03, NMAL7100CommonLogic.convToBigDecimal(catgValX3));
            }
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.X.no(i).effFromDt_MX);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.X.no(i).effThruDt_MX);

            if (!submitTbl(tMsg, isExist)) {
                bizMsg.setMessageInfo(NMAM8020E);
                return false;
            }
        }
        return true;
    }

    private boolean submitPrmoMktPrmoExcl(NMAL7100CMsg bizMsg) {
        Boolean isExist;

        for (int i = 0; i < bizMsg.Y.getValidCount(); i++) {
            PRC_MKT_PRMO_EXCLTMsg tMsg = new PRC_MKT_PRMO_EXCLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());

            isExist = ZYPCommonFunc.hasValue(bizMsg.Y.no(i).prcMktPrmoExclPk_CX);
            if (isExist) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoExclPk, bizMsg.Y.no(i).prcMktPrmoExclPk_CX);
                tMsg = (PRC_MKT_PRMO_EXCLTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(bizMsg.Y.no(i).ezUpTime_CX.getValue(), bizMsg.Y.no(i).ezUpTimeZone_CX.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                // 2019/12/06 QC#54216 Add Start
                if (!NMAL7100CheckLogic.isChangeForExcl(bizMsg.Y.no(i), tMsg)) {
                    continue;
                }
                // 2019/12/06 QC#54216 Add End
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoExclPk, ZYPOracleSeqAccessor.getNumberBigDecimal(PRC_MKT_PRMO_EXCL_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoPk, bizMsg.prcMktPrmoPk_H1);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.Y.no(i).prcCatgCd_CX);
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.Y.no(i).effFromDt_CX);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.Y.no(i).effThruDt_CX);

            if (!submitTbl(tMsg, isExist)) {
                bizMsg.setMessageInfo(NMAM8020E);
                return false;
            }
        }
        return true;
    }

//    private boolean submitPrcMktPrmoDtl(NMAL7100CMsg bizMsg) {// QC#15842 11/10/2016 MOD bizMsg ⇒ glblMsg
    private boolean submitPrcMktPrmoDtl(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        Boolean isExist;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            PRC_MKT_PRMO_DTLTMsg tMsg = new PRC_MKT_PRMO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());

            isExist = ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcMktPrmoDtlPk_DA);
            if (isExist) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoDtlPk, glblMsg.A.no(i).prcMktPrmoDtlPk_DA);
                tMsg = (PRC_MKT_PRMO_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(glblMsg.A.no(i).ezUpTime_DA.getValue(), glblMsg.A.no(i).ezUpTimeZone_DA.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                // 2019/12/06 QC#54216 Add Start
                if (!NMAL7100CheckLogic.isChangeForDtl(glblMsg.A.no(i), tMsg, getGlobalCompanyCode())) {
                    continue;
                }
                // 2019/12/06 QC#54216 Add End
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("PRC_MKT_PRMO_DTL_SQ"));
                ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoPk, bizMsg.prcMktPrmoPk_H1);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoCd, glblMsg.A.no(i).prcMktPrmoCd_DA);
            ZYPEZDItemValueSetter.setValue(tMsg.prcQlfyTpCd, glblMsg.A.no(i).prcPrmoQlfyTpCd_DA);
            // START QC#6154 04/15/2016 ADD
            if (PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(glblMsg.A.no(i).prcPrmoQlfyTpCd_DA.getValue())) {
                NMAL7100CommonLogic.searchSvrModel(glblMsg.A.no(i).prcQlfyValTxt_DA, getGlobalCompanyCode(), true);
            }
            // END QC#6154 04/15/2016 ADD
            ZYPEZDItemValueSetter.setValue(tMsg.prcQlfyValTxt, glblMsg.A.no(i).prcQlfyValTxt_DA);
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, glblMsg.A.no(i).mdseCd_DA);
            ZYPEZDItemValueSetter.setValue(tMsg.prmoAmt, glblMsg.A.no(i).prmoAmt_DA);
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, glblMsg.A.no(i).effFromDt_DA);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, glblMsg.A.no(i).effThruDt_DA);
            ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_OFF_N);

            if (!submitTbl(tMsg, isExist)) {
                bizMsg.setMessageInfo(NMAM8020E);
                return false;
            }
        }
        return true;
    }

    private boolean submitTbl(EZDTMsg inTMsg, boolean isExists) {
        if (isExists) {
            EZDTBLAccessor.update(inTMsg);
        } else {
            EZDTBLAccessor.insert(inTMsg);
        }
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }
}
