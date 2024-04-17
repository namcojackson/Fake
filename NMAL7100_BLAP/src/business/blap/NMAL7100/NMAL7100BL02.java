/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7100;

import static business.blap.NMAL7100.constant.NMAL7100Constant.CHK_DA;
import static business.blap.NMAL7100.constant.NMAL7100Constant.CSV_DOWNLOAD_DETAIL_HEADER;
import static business.blap.NMAL7100.constant.NMAL7100Constant.CSV_DOWNLOAD_FILE_NAME;
import static business.blap.NMAL7100.constant.NMAL7100Constant.CSV_DOWNLOAD_HEADER;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM0050E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8020E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8054E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8190E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8191E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8193E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NZZM0000E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NZZM0001W;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NZZM0002I;
import static business.blap.NMAL7100.constant.NMAL7100Constant.TAB_MKT_AUDC;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSchemaInfo;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7100.common.NMAL7100CheckLogic;
import business.blap.NMAL7100.common.NMAL7100CommonLogic;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.PRC_PRMO_QLFY_TPTMsg;
import business.db.PRC_PRMO_QLFY_TPTMsgArray;
import business.file.NMAL7100F00FMsg;
import business.file.NMAL7100F01FMsg;
import business.file.NMAL7100F02FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MKT_PRMO_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_MKT_PRMO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_MKT_PRMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRMO_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7100BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         M.Hara          Create          N/A
 * 2016/01/19   Fujitsu         M.Hara          Update          QC#3002
 * 2016/01/19   Fujitsu         M.Hara          Update          QC#3089
 * 2016/02/24   Fujitsu         W.Honda         Update          CSA-QC#4043
 * 2016/03/14   Fujitsu         Y.kanefusa      Update          QC#4163
 * 2016/04/08   Fujitsu         M.Ohno          Update          QC#5944
 * 2016/09/12   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/09/20   Hitachi         Y.Takeno        Update          QC#14560
 * 2016/11/10   Fujitsu         W.Honda         Update          QC#15842
 * 2016/11/22   Fujitsu         R.Nakamura      Update          QC#16082
 * 2019/03/13   Fujitsu         S.Kosaka        Update          QC#30725
 * 2019/12/06   Fujitsu         S.Kosaka        Update          QC#54215
 *</pre>
 */
public class NMAL7100BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7100CMsg bizMsg = (NMAL7100CMsg) cMsg;
            NMAL7100SMsg glblMsg = (NMAL7100SMsg) sMsg;

            if ("NMAL7100_INIT".equals(screenAplID)) {
                doProcess_NMAL7100_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_SelectAll(bizMsg, glblMsg);
           } else if ("NMAL7100Scrn00_UnselectAll".equals(screenAplID)) {
               doProcess_NMAL7100Scrn00_UnselectAll(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_Search_MktPgm".equals(screenAplID)) {
                // 2019/12/06 QC#54215 Add Start
                NMAL7100CommonLogic.clearFilter(bizMsg);
                // 2019/12/06 QC#54215 Add End
                doProcess_NMAL7100Scrn00_Search_MktPgm(bizMsg, glblMsg, false, false);
            } else if ("NMAL7100Scrn00_Add_MktAud".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_Add_MktAud(bizMsg, glblMsg, false);
            } else if ("NMAL7100Scrn00_Add_CanNotBeUsed".equals(screenAplID)) {
              doProcess_NMAL7100Scrn00_Add_CanNotBeUsed(bizMsg, glblMsg, false);
            } else if ("NMAL7100Scrn00_Add_Detail".equals(screenAplID)) {
              doProcess_NMAL7100Scrn00_Add_Detail(bizMsg, glblMsg, false);
            } else if ("NMAL7100Scrn00_CMN_Submit".equals(screenAplID)) {
              doProcess_NMAL7100Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_CMN_Clear(bizMsg, glblMsg);
                // 2019/12/06 QC#54215 Add Start
                NMAL7100CommonLogic.clearFilter(bizMsg);
                // 2019/12/06 QC#54215 Add End
            } else if ("NMAL7100Scrn00_CMN_Reset".equals(screenAplID)) {
                // 2019/12/06 QC#54215 Add Start
                NMAL7100CommonLogic.clearFilter(bizMsg);
                // 2019/12/06 QC#54215 Add End
                doProcess_NMAL7100Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_Upload_MktAud".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_Upload_MktAud(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_Upload_MktPgm".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_Upload_MktPgm(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_Download_Temlate".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_Download_Temlate(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_DetailApply".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_DetailApply(bizMsg, glblMsg);
            // START QC#3002 01/19/2016 ADD
            } else if ("NMAL7100Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_CMN_Download(bizMsg, glblMsg);
            // END QC#3002 01/19/2016 ADD
            // START QC#15842 11/10/2016 ADD
            } else if ("NMAL7100Scrn00_MassUpd_MktList".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_MassUpd_MktList(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NMAL7100Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7100Scrn00_PagePrev(bizMsg, glblMsg);
            // END QC#15842 11/10/2016 ADD
            // 2019/12/06 QC#54215 Add Start
            } else if ("NMAL7100_NMAL7140".equals(screenAplID)) {
                doProcess_NMAL7100_NMAL7140(bizMsg, glblMsg);
            // 2019/12/06 QC#54215 Add End
            } else {
                return;
            }
            // START QC#3089 01/19/2016 ADD
            NMAL7100CommonLogic.changeApplyBtnFlg(bizMsg, ZYPConstant.FLG_OFF_N);
            // END QC#3089 01/19/2016 ADD
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // START QC#15842 11/10/2016 MOD bizMsg ⇒ glblMsg
    // START QC#3002 01/19/2016 ADD
    private void doProcess_NMAL7100Scrn00_CMN_Download(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_DOWNLOAD_FILE_NAME), ".csv");

        NMAL7100F02FMsg fMsg = new NMAL7100F02FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_DOWNLOAD_DETAIL_HEADER);
        // START QC#5944 04/08/2016 MOD
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            PRC_PRMO_QLFY_TPTMsg inTMsg = new PRC_PRMO_QLFY_TPTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());

            EZDMsg.copy(glblMsg.A.no(i), null, fMsg, null);

            ZYPEZDItemValueSetter.setValue(inTMsg.prcPrmoQlfyTpCd, glblMsg.A.no(i).prcPrmoQlfyTpCd_DA);
            inTMsg = (PRC_PRMO_QLFY_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);

// START QC#14560 09/20/2016 MOD
//            if (ZYPCommonFunc.hasValue(inTMsg.prcPrmoQlfyTpDescTxt)) {
            if (inTMsg != null) {
// END   QC#14560 09/20/2016 MOD
                ZYPEZDItemValueSetter.setValue(fMsg.prcPrmoQlfyTpDescTxt_DA, inTMsg.prcPrmoQlfyTpDescTxt);
            }

            // format Date
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effFromDt_DA)){
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FM, //
                        NMAL7100CommonLogic.formatDt(glblMsg.A.no(i).effFromDt_DA.getValue()));
            }

            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effThruDt_DA)){
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, //
                        NMAL7100CommonLogic.formatDt(glblMsg.A.no(i).effThruDt_DA.getValue()));
            }

            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxCratDt_DA)){
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D1, //
                        NMAL7100CommonLogic.formatDt(glblMsg.A.no(i).xxCratDt_DA.getValue()));
            }

            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxLastEntryDt_DA)){
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D2, //
                        NMAL7100CommonLogic.formatDt(glblMsg.A.no(i).xxLastEntryDt_DA.getValue()));
            }
            csvOutFile.write();
            fMsg.clear();
        }
        // END QC#5944 04/08/2016 MOD

        csvOutFile.close();
    }
    // END QC#3002 01/19/2016 ADD
    // END QC#15842 11/10/2016 MOD bizMsg ⇒ glblMsg

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7100_INIT(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {

        ZYPTableUtil.clear(glblMsg.A);

        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_H1, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        // Create Pull Down.
        createPullDown(bizMsg);

        if (ZYPCommonFunc.hasValue(bizMsg.prcMktPrmoPk_BK))  {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcMktPrmoPk_H1, bizMsg.prcMktPrmoPk_BK);
            doProcess_NMAL7100Scrn00_Search_MktPgm(bizMsg, glblMsg, false, false);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, TAB_MKT_AUDC);
        }

    }

    private void createPullDown(NMAL7100CMsg bizMsg) {
        // *********************************************************************
        // Header
        // *********************************************************************
        ZYPCodeDataUtil.createPulldownList(PRC_MKT_PRMO_TP.class, bizMsg.prcMktPrmoTpCd_L1, bizMsg.prcMktPrmoTpDescTxt_L1);

        // *********************************************************************
        // Marketing Program Audience Tab
        // *********************************************************************
        S21SsmEZDResult ssmResult = NMAL7100Query.getInstance().getPrmoAudcCatg();
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM8020E);
            return;
        }

        // START QC#3002 01/19/2016 MOD
        List< ? > rsltCustList = (List< ? >) ssmResult.getResultObject();
        int idx01 = 0;
        int idx02 = 0;
        int idx03 = 0;
        Map< ? , ? > rsltMap;

        for (int i = 0; i < rsltCustList.size(); i++) {
            rsltMap = (Map< ? , ? >) rsltCustList.get(i);
            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("MKT_PRMO_AUDC_CATG_FLG_01"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.mktPrmoAudcCatgCd_L1.no(idx01), (String) rsltMap.get("MKT_PRMO_AUDC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.mktPrmoAudcCatgDescTxt_L1.no(idx01), (String) rsltMap.get("MKT_PRMO_AUDC_CATG_DESC_TXT"));
                idx01++;
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("MKT_PRMO_AUDC_CATG_FLG_02"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.mktPrmoAudcCatgCd_L2.no(idx02), (String) rsltMap.get("MKT_PRMO_AUDC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.mktPrmoAudcCatgDescTxt_L2.no(idx02), (String) rsltMap.get("MKT_PRMO_AUDC_CATG_DESC_TXT"));
                idx02++;
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("MKT_PRMO_AUDC_CATG_FLG_03"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.mktPrmoAudcCatgCd_L3.no(idx03), (String) rsltMap.get("MKT_PRMO_AUDC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.mktPrmoAudcCatgDescTxt_L3.no(idx03), (String) rsltMap.get("MKT_PRMO_AUDC_CATG_DESC_TXT"));
                idx03++;
            }
        }
        // END QC#3002 01/19/2016 MOD

        // *********************************************************************
        // Can not Be Used Tab
        // *********************************************************************
        // START QC#3002 01/19/2016 ADD
        ZYPCodeDataUtil.createPulldownList(PRC_LIST_TP.class, bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1);
        // End QC#3002 01/19/2016 ADD

        // *********************************************************************
        // Detail Header
        // *********************************************************************
        ZYPCodeDataUtil.createPulldownList(PRC_MKT_PRMO_STS.class, bizMsg.prcMktPrmoStsCd_L1, bizMsg.prcMktPrmoStsDescTxt_L1);

        // *********************************************************************
        // Detail
        // *********************************************************************
        ZYPCodeDataUtil.createPulldownList(PRC_PRMO_QLFY_TP.class, bizMsg.prcPrmoQlfyTpCd_L1, bizMsg.prcPrmoQlfyTpDescTxt_L1);

    }

    private void doProcess_NMAL7100Scrn00_SelectAll(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        NMAL7100CommonLogic.selectUnselect(bizMsg, ZYPConstant.FLG_ON_Y);
    }

    private void doProcess_NMAL7100Scrn00_UnselectAll(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        NMAL7100CommonLogic.selectUnselect(bizMsg, ZYPConstant.FLG_OFF_N);
    }

    private void doProcess_NMAL7100Scrn00_Search_MktPgm(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg, boolean afterSubmit, boolean isApply) {

        // *********************************************************************
        // Clear
        // *********************************************************************
        clearMsg(bizMsg, glblMsg, isApply);

        // *********************************************************************
        // Header Search
        // *********************************************************************
        S21SsmEZDResult ssmResult = null;
        if (ZYPCommonFunc.hasValue(bizMsg.prcMktPrmoPk_H1) ) {
            ssmResult = NMAL7100Query.getInstance().searchMktPgmHdr(bizMsg);
        }

        if (ssmResult == null || ssmResult.isCodeNotFound()) {

            if (!afterSubmit) {
                bizMsg.setMessageInfo(NZZM0000E);
            }
            bizMsg.setCommitSMsg(true);
            return;
        }

        // *********************************************************************
        // Marketing Audience Tab Search(No Record OK)
        // *********************************************************************
        ssmResult = NMAL7100Query.getInstance().searchMktAudList(bizMsg);

        // *********************************************************************
        // Can Not Be Used Tab(No Record OK)
        // *********************************************************************
        ssmResult = NMAL7100Query.getInstance().searchCannotBeUsedList(bizMsg);

        // *********************************************************************
        // Detail Search
        // *********************************************************************
        ssmResult = NMAL7100Query.getInstance().searchMktPgmList(bizMsg, glblMsg, isApply);

        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            if (!ssmResult.isCodeNotFound()) {
                if (!afterSubmit) {
                    bizMsg.setMessageInfo(NMAM8020E);
                }
                bizMsg.setCommitSMsg(true);
                return;
            }
        }

        // START QC#15842 11/10/2016 MOD
        if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
            bizMsg.setMessageInfo(NZZM0001W);
        }

//        EZDMsg.copy(glblMsg.A, null, bizMsg.A, null);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(0);
        NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
        // END QC#15842 11/10/2016 MOD
    }

    private void doProcess_NMAL7100Scrn00_Add_MktAud(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg, boolean afterSubmit) {
        if (bizMsg.X.getValidCount() >= bizMsg.X.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.X.length())});
            return;
        }

        int curIdx = bizMsg.X.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.X.no(curIdx).effFromDt_MX, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        bizMsg.X.setValidCount(curIdx + 1);
    }

    private void doProcess_NMAL7100Scrn00_Add_CanNotBeUsed(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg, boolean afterSubmit) {
        if (bizMsg.Y.getValidCount() >= bizMsg.Y.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.Y.length())});
            return;
        }

        int curIdx = bizMsg.Y.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(curIdx).effFromDt_CX, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        bizMsg.Y.setValidCount(curIdx + 1);
    }

    private void doProcess_NMAL7100Scrn00_Add_Detail(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg, boolean afterSubmit) {
        // START QC#15842 11/10/2016 MOD
//        if (bizMsg.A.getValidCount() >= bizMsg.A.length()) {
//            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.A.length())});
//            return;
//        }
//
//        int curIdx = bizMsg.A.getValidCount();
//
//        bizMsg.A.setValidCount(curIdx + 1);
//        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(curIdx).effFromDt_DA, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        if (glblMsg.A.getValidCount() >= glblMsg.A.length()) {
            if (bizMsg.A.getValidCount() >= bizMsg.A.length()) {
                bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.A.length())});
                return;
            }
        }
        NMAL7100CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(glblMsg.A.getValidCount()).effFromDt_DA, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        bizMsg.xxPageShowFromNum.setValue(glblMsg.A.getValidCount());
        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
        NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
        // END QC#15842 11/10/2016 MOD
    }

    private void doProcess_NMAL7100Scrn00_CMN_Submit(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        doProcess_NMAL7100Scrn00_Search_MktPgm(bizMsg, glblMsg, true, false);
        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    private void clearMsg(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg, boolean isApply) {
        // *********************************************************************
        // Header
        // *********************************************************************
        bizMsg.prcMktPrmoNm_H1.clear();
        bizMsg.prcMktPrmoDescTxt_H1.clear();
        bizMsg.prcMktPrmoCmntTxt_H1.clear();

        ZYPEZDItemValueSetter.setValue(bizMsg.prcMktPrmoTpCd_H1, bizMsg.prcMktPrmoTpCd_L1.no(0));
        bizMsg.effFromDt_H1.clear();
        bizMsg.effThruDt_H1.clear();
        bizMsg.actvFlg_H1.clear();
        bizMsg.dsMsgTxt_H1.clear();

        // *********************************************************************
        // Detail Header
        // *********************************************************************
        if (!isApply) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcMktPrmoStsCd_DH, bizMsg.prcMktPrmoStsCd_L1.no(0));
            bizMsg.prcMktPrmoCd_DH.clear();
        }
        bizMsg.xxFileData_DH.clear();
        bizMsg.effThruDt_DH.clear();

        // *********************************************************************
        // Detail
        // *********************************************************************
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        // *********************************************************************
        // Marketing Audience Tab
        // *********************************************************************
        bizMsg.xxFileData_HC.clear();
        ZYPTableUtil.clear(bizMsg.X);

        // *********************************************************************
        // Can Not Be Used Tab
        // *********************************************************************
        ZYPTableUtil.clear(bizMsg.Y);
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7100Scrn00_CMN_Clear(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        clearMsg(bizMsg, glblMsg, false);
        bizMsg.prcMktPrmoPk_H1.clear();
        bizMsg.prcMktPrmoPk_BK.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_H1, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7100Scrn00_CMN_Reset(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        clearMsg(bizMsg, glblMsg, false);
        doProcess_NMAL7100_INIT(bizMsg, glblMsg);
    }

    private void doProcess_NMAL7100Scrn00_Upload_MktAud(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        /** For duplicate check */
        String path = bizMsg.xxFileData_HC.getTempFilePath();
        NMAL7100F00FMsg fMsg = new NMAL7100F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        // Mod Start 2016/11/22 QC#16082
        String csvPath = ZYPExcelUtil.excelToCsvFile(path);
//        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvPath, fMsg, option);
        // Mod End 2016/11/22 QC#16082

        try {

            if (!NMAL7100CheckLogic.readHeaderCsvFile(mappedFile, bizMsg)) {
                return;
            }
            fMsg.clear();

            int status = -1;
            int idx = bizMsg.X.getValidCount();
            int length = bizMsg.X.length();
            int upCnt = 0;

            // START QC#3002 01/19/2016 ADD
            String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
            // END QC#3002 01/19/2016 ADD

            while ((status = mappedFile.read()) != 1) {

                idx++;
                upCnt++;

                if (idx > length) {

                    bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(length)});
                    idx = length;
                    break;
                }

                if (NMAL7100CheckLogic.validateUpldCust(bizMsg, status, idx, upCnt, length)) {
                    return;
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.X.no(idx - 1).mktPrmoAudcCatgCd_X1, MKT_PRMO_AUDC_CATG.ACCOUNT_NUM);
                ZYPEZDItemValueSetter.setValue(bizMsg.X.no(idx - 1).xxScrItem30Txt_X1, fMsg.dsAcctNum.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.X.no(idx - 1).xxRecNmTxt_X1, fMsg.dsAcctNm.getValue());
                // START QC#3002 01/19/2016 MOD
                ZYPEZDItemValueSetter.setValue(bizMsg.X.no(idx - 1).effFromDt_MX, slsDt);
                // END QC#3002 01/19/2016 MOD

                fMsg.clear();
            }

            if (idx == 0) {

                bizMsg.setMessageInfo(NMAM8193E);
            }

            bizMsg.X.setValidCount(idx);

        } finally {

            mappedFile.close();
            bizMsg.xxFileData_HC.deleteTempFile();
            // Add Start 2016/11/21 QC#16082
            ZYPExcelUtil.deleteFile(csvPath);
            // Add End 2016/11/21 QC#16082
        }
    }

    private void doProcess_NMAL7100Scrn00_Upload_MktPgm(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        /** For duplicate check */
        String path = bizMsg.xxFileData_DH.getTempFilePath();
        NMAL7100F01FMsg fMsg = new NMAL7100F01FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        // Mod Start 2016/11/22 QC#16082
        String csvPath = ZYPExcelUtil.excelToCsvFile(path);
//        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvPath, fMsg, option);
        // Mod End 2016/11/22 QC#16082

        try {

            if (!NMAL7100CheckLogic.readHeaderCsvFile(mappedFile, bizMsg)) {
                return;
            }
            fMsg.clear();

            int status = -1;
            // START QC#15842 11/10/2016 MOD
//            int idx = bizMsg.A.getValidCount();
//            int length = bizMsg.A.length();
            int idx = glblMsg.A.getValidCount();
            int length = glblMsg.A.length();
            // END QC#15842 11/10/2016 MOD
            int upCnt = 0;

            while ((status = mappedFile.read()) != 1) {

                idx++;
                upCnt++;

                if (idx > length) {

                    bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(length)});
                    idx = length;
                    // START QC#15842 11/10/2016 MOD
//                    bizMsg.xxPageShowFromNum.setValue(idx);
//                    break;
                    bizMsg.xxPageShowFromNum.setValue(idx - 1);
                    return;
                    // END QC#15842 11/10/2016 MOD
                }

                if (NMAL7100CheckLogic.validateUpldMktPgm(fMsg, bizMsg, status, idx, upCnt, length)) {
                    // START QC#15842 11/10/2016 ADD
                    bizMsg.xxPageShowFromNum.setValue(idx - 1);
                    glblMsg.A.setValidCount(idx - 1);
                    // END QC#15842 11/10/2016 ADD
                    return;
                }

                // START QC#5944 04/08/2016 ADD
                // Get PrcPrmoQlfyTpDescTxt
                PRC_PRMO_QLFY_TPTMsg inTMsg = new PRC_PRMO_QLFY_TPTMsg();
                PRC_PRMO_QLFY_TPTMsgArray outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcPrmoQlfyTpDescTxt, fMsg.prcPrmoQlfyTpDescTxt);
                outTMsg = (PRC_PRMO_QLFY_TPTMsgArray) S21CodeTableAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    // Not Found
                    String errColNm, errArgs;
                    errColNm = CSV_DOWNLOAD_HEADER[1];

                    errArgs = String.format("%s(Line=%d)", errColNm, upCnt);
                    bizMsg.setMessageInfo(NMAM8191E, new String[]{errArgs});

                    // START QC#15842 11/10/2016 ADD
                    bizMsg.xxPageShowFromNum.setValue(idx - 1);
                    glblMsg.A.setValidCount(idx - 1);
                    // END QC#15842 11/10/2016 ADD
                    return;
                }
                // END QC#5944 04/08/2016 ADD

                // MDSE Exist Check
                ALL_MDSE_VTMsg tmsg = new ALL_MDSE_VTMsg();
                tmsg.setSQLID("001");
                tmsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
                tmsg.setConditionValue("mdseCd01", fMsg.mdseCd.getValue());
                tmsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

                ALL_MDSE_VTMsgArray tmsgArray = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
                if (tmsgArray == null || tmsgArray.length() == 0) {
                    // Not Found
                    String errColNm, errArgs;
                    errColNm = CSV_DOWNLOAD_HEADER[3];

                    errArgs = String.format("%s(Line=%d)", errColNm, upCnt);
                    bizMsg.setMessageInfo(NMAM8191E, new String[]{errArgs});

                    // START QC#15842 11/10/2016 ADD
                    bizMsg.xxPageShowFromNum.setValue(idx - 1);
                    glblMsg.A.setValidCount(idx - 1);
                    // END QC#15842 11/10/2016 ADD
                    return;
                }

                // START QC#15842 11/10/2016 MOD
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).prcMktPrmoCd_DA, fMsg.prcMktPrmoCd);
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).prcPrmoQlfyTpCd_DA, outTMsg.no(0).prcPrmoQlfyTpCd);// QC#5944 04/08/2016 MOD
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).prcQlfyValTxt_DA, fMsg.prcQlfyValTxt);
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).mdseCd_DA, fMsg.mdseCd);
////                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).mdseNm_DA, tmsgArray.no(0).mdseNm);// Mod 2016/09/12 QC#11615
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).mdseDescShortTxt_DA, tmsgArray.no(0).mdseDescShortTxt);// Mod 2016/09/12 QC#11615
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).prmoAmt_DA, fMsg.prmoAmt);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx - 1).prcMktPrmoCd_DA, fMsg.prcMktPrmoCd);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx - 1).prcPrmoQlfyTpCd_DA, outTMsg.no(0).prcPrmoQlfyTpCd);// QC#5944 04/08/2016 MOD
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx - 1).prcQlfyValTxt_DA, fMsg.prcQlfyValTxt);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx - 1).mdseCd_DA, fMsg.mdseCd);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx - 1).mdseDescShortTxt_DA, tmsgArray.no(0).mdseDescShortTxt);// Mod 2016/09/12 QC#11615
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx - 1).prmoAmt_DA, fMsg.prmoAmt);

                //QC4163 2016/03/14 Mod Start
//                if (ZYPDateUtil.isValidDate(fMsg.effFromDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).effFromDt_DA, fMsg.effFromDt);
//                }
//
//                if (ZYPDateUtil.isValidDate(fMsg.effThruDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).effThruDt_DA, fMsg.effThruDt);
//                }
                // 2019/03/13 QC#30725 Add Start
                EZDSchemaInfo ezdSchemaInfo = new NMAL7100_ACMsg().getSchema();
                // 2019/03/13 QC#30725 Add End

                if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_FM)) {
                    // 2019/03/13 QC#30725 Add Start
                    String baseString = fMsg.xxDtTxt_FM.getValue();
                    // 2019/03/13 QC#30725 Add End
                    if (fMsg.xxDtTxt_FM.getValue().contains("/")) {
                        String sepDeleteString = fMsg.xxDtTxt_FM.getValue();
                        // 2019/03/13 QC#30725 Add Start
                        sepDeleteString = NMAL7100CommonLogic.formatDateStr(sepDeleteString);
                        // 2019/03/13 QC#30725 Add End
                        sepDeleteString = sepDeleteString.replaceAll("/", "");
                        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FM, ZYPDateUtil.formatDisp8ToEzd(sepDeleteString));
                    }
                    // 2019/03/13 QC#30725 Mod Start
                    //if (!ZYPDateUtil.isValidDate(fMsg.xxDtTxt_FM.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                    if (!ZYPDateUtil.isValidDate(fMsg.xxDtTxt_FM.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)
                            || NMAL7100CommonLogic.checkItemCnt(fMsg.xxDtTxt_FM.getValue(), ezdSchemaInfo.getAttr("effFromDt_DA").getDigit())) {
                        //bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(idx - 1) + "row, Date From, " + fMsg.xxDtTxt_FM.getValue() });
                        bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(idx - 1) + "row, Date From, " + baseString });
                        // 2019/03/13 QC#30725 Mod End
                        // START QC#15842 11/10/2016 ADD
                        bizMsg.xxPageShowFromNum.setValue(idx - 1);
                        glblMsg.A.setValidCount(idx - 1);
                        // END QC#15842 11/10/2016 ADD
                        return;
                    } else {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).effFromDt_DA, fMsg.xxDtTxt_FM.getValue());
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx - 1).effFromDt_DA, fMsg.xxDtTxt_FM.getValue());
                    }
                } else {
//                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FM, ZYPDateUtil.getSalesDate());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx - 1).effFromDt_DA, ZYPDateUtil.getSalesDate());
                }
                if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_TH)) {
                    // 2019/03/13 QC#30725 Add Start
                    String baseString = fMsg.xxDtTxt_TH.getValue();
                    // 2019/03/13 QC#30725 Add End
                    if (fMsg.xxDtTxt_TH.getValue().contains("/")) {
                        String sepDeleteString = fMsg.xxDtTxt_TH.getValue();
                        // 2019/03/13 QC#30725 Add Start
                        sepDeleteString = NMAL7100CommonLogic.formatDateStr(sepDeleteString);
                        // 2019/03/13 QC#30725 Add End
                        sepDeleteString = sepDeleteString.replaceAll("/", "");
                        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, ZYPDateUtil.formatDisp8ToEzd(sepDeleteString));
                    }
                    // 2019/03/13 QC#30725 Mod Start
                    //if (!ZYPDateUtil.isValidDate(fMsg.xxDtTxt_TH.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                    if (!ZYPDateUtil.isValidDate(fMsg.xxDtTxt_TH.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)
                            || NMAL7100CommonLogic.checkItemCnt(fMsg.xxDtTxt_TH.getValue(), ezdSchemaInfo.getAttr("effThruDt_DA").getDigit())) {
                        //bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(idx - 1) + "row, Date To, " + fMsg.xxDtTxt_TH.getValue() });
                        bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(idx - 1) + "row, Date To, " + baseString });
                        // 2019/03/13 QC#30725 Mod End
                        // START QC#15842 11/10/2016 MOD
                        bizMsg.xxPageShowFromNum.setValue(idx - 1);
                        glblMsg.A.setValidCount(idx - 1);
                        // END QC#15842 11/10/2016 MOD
                        return;
                    } else {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx - 1).effThruDt_DA, fMsg.xxDtTxt_TH.getValue());
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx - 1).effThruDt_DA, fMsg.xxDtTxt_TH.getValue());
                    }
                }
                // END QC#15842 11/10/2016 MOD
                fMsg.clear();
            }

            if (idx == 0) {

                bizMsg.setMessageInfo(NMAM8193E);
            }

            // START QC#15842 11/10/2016 MOD
//            bizMsg.A.setValidCount(idx);

            bizMsg.xxPageShowFromNum.setValue(idx - 1);
            glblMsg.A.setValidCount(idx);
            // END QC#15842 11/10/2016 MOD

        } finally {

            mappedFile.close();
            bizMsg.xxFileData.deleteTempFile();
            // Add Start 2016/11/21 QC#16082
            ZYPExcelUtil.deleteFile(csvPath);
            // Add End 2016/11/21 QC#16082

            // START QC#15842 11/10/2016 MOD
            NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
            // END QC#15842 11/10/2016 MOD
        }
    }

    private void doProcess_NMAL7100Scrn00_Download_Temlate(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_DOWNLOAD_FILE_NAME), ".csv");

        NMAL7100F00FMsg fMsg = new NMAL7100F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER);

        csvOutFile.close();
    }

    private void doProcess_NMAL7100Scrn00_DetailApply(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        doProcess_NMAL7100Scrn00_Search_MktPgm(bizMsg, glblMsg, false, true);
    }

    // START QC#15842 11/10/2016 ADD
    private void doProcess_NMAL7100Scrn00_MassUpd_MktList(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_DA, ZYPConstant.FLG_ON_Y);

        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NMAM8190E);
            return;
        }

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

       for (int idx : selectRows) {
           ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).effThruDt_DA, bizMsg.effThruDt_DH);
       }

       NMAL7100CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    private void doProcess_NMAL7100Scrn00_PageJump(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        NMAL7100CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    private void doProcess_NMAL7100Scrn00_PageNext(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        NMAL7100CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    private void doProcess_NMAL7100Scrn00_PagePrev(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        NMAL7100CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }
    // END QC#15842 11/10/2016 ADD

    // 2019/12/06 QC#54215 Add Start
    /**
     * Filter
     * @param bizMsg NMAL7100CMsg
     * @param glblMsg NMAL7100SMsg
     */
    private void doProcess_NMAL7100_NMAL7140(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        doProcess_NMAL7100Scrn00_Search_MktPgm(bizMsg, glblMsg, false, false);
    }
    // 2019/12/06 QC#54215 Add End
}
