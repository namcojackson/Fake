/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0230;

import static business.blap.NMAL0230.constant.NMAL0230Constant.BOM;
import static business.blap.NMAL0230.constant.NMAL0230Constant.COMPONENT;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_ACTIVE;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_BOM_ITEM;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_BOM_TP;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_CMP;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_CUSA_SET;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_DESC;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_EFF_FROM;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_EFF_THRU;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_FILE_EXT;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_FILE_NAME_DOWNLOAD;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_MAX_ROW;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_MERCH_TP;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_PROD_CD;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_QTY;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_REV;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_REV_CMNT;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_SEQ1;
import static business.blap.NMAL0230.constant.NMAL0230Constant.CSV_SEQ2;
import static business.blap.NMAL0230.constant.NMAL0230Constant.NMAM8298E;
import static business.blap.NMAL0230.constant.NMAL0230Constant.NZZM0000E;
import static business.blap.NMAL0230.constant.NMAL0230Constant.NZZM0001W;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0230.common.NMAL0230CommonLogic;
import business.db.MDSE_ITEM_TPTMsg;
import business.db.MDSE_ITEM_TPTMsgArray;
import business.file.NMAL0230F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NMAL0230BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         C.Tanaka        Create          CSA
 * 2016/06/21   SRAA            K.Aratani       Update          QC#10587
 * 2017/02/07   Fujitsu         K.Ishizuka      Update          QC#16807
 * 2018/07/12   CITS            S.Katsuma       Update          QC#26542
 * 
 *</pre>
 */
public class NMAL0230BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL0230CMsg bizMsg = (NMAL0230CMsg) cMsg;
            NMAL0230SMsg glblMsg = (NMAL0230SMsg) sMsg;

            if ("NMAL0230_INIT".equals(screenAplID)) {
                doProcess_NMAL0230_INIT(bizMsg, glblMsg);

            } else if ("NMAL0230Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL0230Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL0230Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL0230Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL0230Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL0230Scrn00_PagePrev(bizMsg, glblMsg);
                // Add start S21_NA #16807
            } else if ("NMAL0230Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL0230Scrn00_PageJump(bizMsg, glblMsg);
                // Add end S21_NA #16807
            } else if ("NMAL0230Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL0230Scrn00_Search(bizMsg, glblMsg);

            } else if ("NMAL0230Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL0230Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NMAL0230Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL0230Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL0230_INIT(NMAL0230CMsg bizMsg, NMAL0230SMsg glblMsg) {
        ZYPCodeDataUtil.createPulldownList(COA_PROJ.class, bizMsg.coaMdseTpCd_L1, bizMsg.coaProjDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(COA_PROJ.class, bizMsg.coaMdseTpCd_L2, bizMsg.coaProjDescTxt_L2);
        ZYPCodeDataUtil.createPulldownList(MDSE_CMPSN_TP.class, bizMsg.mdseCmpsnTpCd_L2, bizMsg.mdseCmpsnTpDescTxt_L2);

        ZYPGUITableColumn.getColData(bizMsg, glblMsg);

        MDSE_ITEM_TPTMsg tMsg = new MDSE_ITEM_TPTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("mdseItemTpCd01A", MDSE_ITEM_TP.SET);
        tMsg.setConditionValue("mdseItemTpCd01B", MDSE_ITEM_TP.KIT);
        MDSE_ITEM_TPTMsgArray tMsgArray = (MDSE_ITEM_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            tMsg = tMsgArray.no(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpCd_L1.no(i), tMsg.mdseItemTpCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpDescTxt_L1.no(i), tMsg.mdseItemTpDescTxt);
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL0230Scrn00_CMN_Download(NMAL0230CMsg bizMsg, NMAL0230SMsg glblMsg) {
        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NMAM8298E);
            return;
        }
        NMAL0230Query.getInstance().getRevisionForCsv(bizMsg, new CreateDownloadData(bizMsg));
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL0230Scrn00_CMN_Clear(NMAL0230CMsg bizMsg, NMAL0230SMsg glblMsg) {
        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        glblMsg.clear();
        glblMsg.A.setValidCount(0);

        doProcess_NMAL0230_INIT(bizMsg, glblMsg);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL0230Scrn00_PageNext(NMAL0230CMsg bizMsg, NMAL0230SMsg glblMsg) {

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL0230CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL0230Scrn00_PagePrev(NMAL0230CMsg bizMsg, NMAL0230SMsg glblMsg) {

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL0230CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    // ADD start S21_NA #16807
    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL0230Scrn00_PageJump(NMAL0230CMsg bizMsg, NMAL0230SMsg glblMsg) {

        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL0230CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    // ADD end S21_NA #16807

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL0230Scrn00_Search(NMAL0230CMsg bizMsg, NMAL0230SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL0230Scrn00_TBLColumnSort(NMAL0230CMsg bizMsg, NMAL0230SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL0230CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL0230CMsg bizMsg, NMAL0230SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL0230Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNormal()) {
            // MOD start S21_NA #16807
            // if (ssmResult.getQueryResultCount() >
            // glblMsg.A.length() -1 ) {
            // bizMsg.setMessageInfo(NZZM0001W);
            // glblMsg.A.setValidCount(glblMsg.A.length() -1 );
            if (ssmResult.getQueryResultCount() == glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
                // MOD end S21_NA #16807
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL0230CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        } else {
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            bizMsg.setMessageInfo(NZZM0000E);
        }
    }

    /**
     * Create Download Data class
     * @author C.Tanaka
     */
    private static class CreateDownloadData extends S21SsmBooleanResultSetHandlerSupport {

        /**
         * bizMsg NMAL0230CMsg
         */
        private NMAL0230CMsg bizMsg = null;

        public CreateDownloadData(NMAL0230CMsg cMsg) {
            this.bizMsg = cMsg;
        }

        @Override
        protected Boolean doProcessQueryResult(ResultSet result) throws SQLException {

            if (!result.next()) {
                bizMsg.setMessageInfo(NZZM0000E);
                return Boolean.FALSE;
            }

            NMAL0230F00FMsg fMsg = new NMAL0230F00FMsg();
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), CSV_FILE_EXT);

            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

            int rowCount = 1;
            BigDecimal curRev = null;
            BigDecimal preRev = new BigDecimal(0);
            String curBomItem = null;
            String preBomItem = null;
            String curMdseCd = null;
            String preMdseCd = null;
            int seq1 = 0;
            int seq2 = 1;
            boolean ordTakeFlg = false;

            do {
                if (rowCount > CSV_MAX_ROW) {
                    bizMsg.setMessageInfo(NZZM0001W);
                    break;
                }

                curRev = result.getBigDecimal("CMPSN_REVN_NUM");
                curBomItem = result.getString("MDSE_CD");
                curMdseCd = result.getString("MDSE_CD_PR");
                if (!ZYPCommonFunc.hasValue(curMdseCd)) {
                    curMdseCd = result.getString("MDSE_CD_PR2");
                    ordTakeFlg = true;
                }
                String childMdseCd = result.getString("MDSE_CD_CH");
                String childMdseCd2 = result.getString("MDSE_CD_CH2");

                if (!curBomItem.equals(preBomItem)) {

                    fMsg.clear();
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, CSV_BOM_ITEM);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, BOM + " " + CSV_DESC);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, CSV_BOM_TP);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDplyByItemNm_A2, BOM + " " + CSV_MERCH_TP);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, BOM + " " + CSV_PROD_CD);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, CSV_CUSA_SET);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, BOM + " " + CSV_ACTIVE);
                    csvOutFile.write();
                    rowCount++;

                    fMsg.clear();
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, curBomItem);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, result.getString("MDSE_DESC_SHORT_TXT"));
                    // START 2018/07/12 S.Katsuma [QC#26542,MOD]
//                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, result.getString("MDSE_ITEM_TP_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, result.getString("MDSE_ITEM_TP_NM"));
                    // END 2018/07/12 S.Katsuma [QC#26542,MOD]
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDplyByItemNm_A2, result.getString("COA_PROJ_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, result.getString("COA_PROD"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, result.getString("ACTV_FLG"));

                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, ZYPConstant.FLG_OFF_N);
                    if (ZYPCommonFunc.hasValue(result.getString("CSA_MDSE_CD"))) {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, ZYPConstant.FLG_ON_Y);
                    }

                    csvOutFile.write();
                    rowCount++;
                    curRev = result.getBigDecimal("CMPSN_REVN_NUM");
                    preRev = new BigDecimal(0);
                    preBomItem = curBomItem;
                }

                if (ZYPCommonFunc.hasValue(curRev) && !curRev.equals(preRev)) {
                    fMsg.clear();
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, CSV_REV);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, CSV_REV_CMNT);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, CSV_EFF_FROM);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDplyByItemNm_A2, CSV_EFF_THRU);
                    csvOutFile.write();
                    rowCount++;

                    fMsg.clear();
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, result.getString("CMPSN_REVN_NUM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, result.getString("CMPSN_REVN_CMNT_TXT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, result.getString("EFF_FROM_DT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDplyByItemNm_A2, result.getString("EFF_THRU_DT"));
                    csvOutFile.write();
                    rowCount++;

                    fMsg.clear();
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, CSV_SEQ1);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, CSV_SEQ2);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, CSV_CMP);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDplyByItemNm_A2, COMPONENT + " " + CSV_DESC);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, CSV_QTY);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, CSV_MERCH_TP);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, CSV_PROD_CD);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A2, CSV_EFF_FROM);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A3, CSV_EFF_THRU);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A2, CSV_ACTIVE);
                    csvOutFile.write();
                    rowCount++;

                    preRev = curRev;
                    seq1 = 0;
                }

                fMsg.clear();

                if (ZYPCommonFunc.hasValue(curMdseCd) && !curMdseCd.equals(preMdseCd)) {
                    seq1++;
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, seq1 + "");
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, result.getString("CHILD_MDSE_QTY_PR"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A2, result.getString("EFF_FROM_DT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A3, result.getString("EFF_THRU_DT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A2, result.getString("ACTIVE_PR"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, curMdseCd);

                    if (!ordTakeFlg) {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxDplyByItemNm_A2, result.getString("MDSE_DESC_SHORT_TXT_PR"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, result.getString("COA_PROJ_DESC_TXT_PR"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, result.getString("COA_PROD_CD_PR"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxDplyByItemNm_A2, result.getString("MDSE_DESC_SHORT_TXT_PR2"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, result.getString("COA_PROJ_DESC_TXT_PR2"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, result.getString("COA_PROD_CD_PR2"));
                    }
                    csvOutFile.write();
                    rowCount++;

                    preMdseCd = curMdseCd;
                    seq2 = 1;
                }

                if (ZYPCommonFunc.hasValue(childMdseCd) || ZYPCommonFunc.hasValue(childMdseCd2)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, seq1 + "");
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, seq2 + "");
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, result.getString("CHILD_MDSE_QTY_CH"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A2, result.getString("EFF_FROM_DT_CH"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A3, result.getString("EFF_THRU_DT_CH"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A2, result.getString("ACTIVE_CH"));

                    if (ZYPCommonFunc.hasValue(childMdseCd)) {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, childMdseCd);
                        ZYPEZDItemValueSetter.setValue(fMsg.xxDplyByItemNm_A2, result.getString("MDSE_DESC_SHORT_TXT_CH"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, result.getString("COA_PROJ_DESC_TXT_CH"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, result.getString("COA_PROD_CD_CH"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, childMdseCd2);
                        ZYPEZDItemValueSetter.setValue(fMsg.xxDplyByItemNm_A2, result.getString("MDSE_DESC_SHORT_TXT_CH2"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, result.getString("COA_PROJ_DESC_TXT_CH2"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, result.getString("COA_PROD_CD_CH2"));
                    }

                    csvOutFile.write();
                    rowCount++;
                    seq2++;
                }

            } while (result.next());
            csvOutFile.close();

            return Boolean.TRUE;
        }
    }
}
