/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL1510;

import static business.blap.NPAL1510.constant.NPAL1510Constant.CSV_FILE_NAME;
import static business.blap.NPAL1510.constant.NPAL1510Constant.EXTN_CSV;
import static business.blap.NPAL1510.constant.NPAL1510Constant.FETCH_SIZE;
import static business.blap.NPAL1510.constant.NPAL1510Constant.MAX_DOWNLOAD_CNT;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_CMN_COL_CLEAR;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_CMN_COL_SAVE;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_CMN_DOWNLOAD;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_CMN_RESET;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_INIT;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_NEXT;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_ON_CHANGE_SEARCH_OPTION;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_PREV;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAL1510_SEARCH;

import java.sql.ResultSet;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1510.common.NPAL1510CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2016/02/22   CUSA            K.Ogino         Update          QC#4409
 * 2017/10/24   CITS            K.Ogino         Update          QC#20737
 * 2017/11/16   CITS            S.Katsuma       Update          QC#22296
 * 12/26/2017   CITS            K.Ogino         Update          QC#21908
 * 02/10/2020   Fujitsu         T.Ogura         Update          QC#55712
 *</pre>
 */
public class NPAL1510BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NPAL1510CMsg bizMsg = (NPAL1510CMsg) cMsg;
            NPAL1510SMsg glblMsg = (NPAL1510SMsg) sMsg;

            // START 2017/11/16 S.Katsuma [QC#22296,ADD]
//            if (NPAL1510_INIT.equals(screenAplID) || NPAL1510_CMN_RESET.equals(screenAplID)) {
            if (NPAL1510_INIT.equals(screenAplID)) {
                doProcess_NPAL1510_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData((NPAL1510CMsg) cMsg, (NPAL1510SMsg) sMsg);
            } else if (NPAL1510_CMN_RESET.equals(screenAplID)) {
                doProcess_NPAL1510_RESET(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData((NPAL1510CMsg) cMsg, (NPAL1510SMsg) sMsg);
            // END 2017/11/16 S.Katsuma [QC#22296,ADD]
            } else if (NPAL1510_SEARCH.equals(screenAplID)) {
                doProcess_NPAL1510_SEARCH(bizMsg, glblMsg);
            } else if (NPAL1510_ON_CHANGE_SEARCH_OPTION.equals(screenAplID)) {
                doProcess_OnChange_SearchOption(bizMsg, glblMsg);
            } else if (NPAL1510_NEXT.equals(screenAplID)) {
                doProcess_NPAL1510Scrn00_Next(bizMsg, glblMsg);
            } else if (NPAL1510_PREV.equals(screenAplID)) {
                doProcess_NPAL1510Scrn00_Prev(bizMsg, glblMsg);
            } else if (NPAL1510_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NPAL1510Scrn00_CMN_Download(bizMsg, glblMsg);
            } else if (NPAL1510_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if (NPAL1510_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NPAL1510_INIT(NPAL1510CMsg bizMsg, NPAL1510SMsg glblMsg) {
        // START 2017/11/16 S.Katsuma [QC#22296,ADD]
        String poOrdNum = bizMsg.poOrdNum.getValue();
        // QC#21908
        String poOrdSrcCd_SL = bizMsg.poOrdSrcCd_SL.getValue();
        String trxRefNum = bizMsg.trxRefNum.getValue();
        String vndSoNum = bizMsg.vndSoNum.getValue();
        NPAL1510CommonLogic.initilizeMsgForInitAndReset(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.poOrdNum, poOrdNum);
        // QC#21908
        ZYPEZDItemValueSetter.setValue(bizMsg.poOrdSrcCd_SL, poOrdSrcCd_SL);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxRefNum, trxRefNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndSoNum, vndSoNum);
        // END 2017/11/16 S.Katsuma [QC#22296,ADD]

        // QC#20737
        if (ZYPCommonFunc.hasValue(bizMsg.poOrdNum)) {
            bizMsg.xxDplyByCtrlItemCdNm.setValue("ACK");
            doProcess_NPAL1510_SEARCH(bizMsg, glblMsg);
        }
    }

    // START 2017/11/16 S.Katsuma [QC#22296,ADD]
    private void doProcess_NPAL1510_RESET(NPAL1510CMsg bizMsg, NPAL1510SMsg glblMsg) {
        NPAL1510CommonLogic.initilizeMsgForInitAndReset(bizMsg, glblMsg);
    }
    // END 2017/11/16 S.Katsuma [QC#22296,ADD]

    private void doProcess_NPAL1510_SEARCH(NPAL1510CMsg bizMsg, NPAL1510SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        String searchCond1 = ZYPConstant.FLG_OFF_0;
        String searchCond2 = ZYPConstant.FLG_OFF_0;

        NPAL1510CommonLogic.preSearchForSetName(bizMsg, getGlobalCompanyCode());

        if (ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd_SL) //
                || ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) //
                || ZYPCommonFunc.hasValue(bizMsg.shipToCustLocNm) //
                || ZYPCommonFunc.hasValue(bizMsg.trxRefNum) //
                || ZYPCommonFunc.hasValue(bizMsg.destRtlSwhCd) //
                || ZYPCommonFunc.hasValue(bizMsg.poLineStsCd_SL) //
                || ZYPCommonFunc.hasValue(bizMsg.vndPoAckStsCd_SL) //
                || ZYPCommonFunc.hasValue(bizMsg.vndIssOrdNum) //
                || ZYPCommonFunc.hasValue(bizMsg.proNum) //
                || ZYPCommonFunc.hasValue(bizMsg.rtlSwhNm) //
                || ZYPCommonFunc.hasValue(bizMsg.vndInvtyLocCd)//
                || ZYPCommonFunc.hasValue(bizMsg.mdseCd)//
                || ZYPCommonFunc.hasValue(bizMsg.aslMdseCd) //
                || ZYPCommonFunc.hasValue(bizMsg.mdseCd_SB)) {
            searchCond1 = ZYPConstant.FLG_ON_1;
        }

        // START 02/10/2020 T.Ogura [QC#55712,MOD]
//        if (ZYPCommonFunc.hasValue(bizMsg.xxOrdDt_FR) //
//                || ZYPCommonFunc.hasValue(bizMsg.poAckNum) //
//                || ZYPCommonFunc.hasValue(bizMsg.xxOrdDt_TO) //
//                || ZYPCommonFunc.hasValue(bizMsg.vndSoNum)) {
        if (ZYPCommonFunc.hasValue(bizMsg.xxOrdDt_FR) //
                || ZYPCommonFunc.hasValue(bizMsg.xxOrdDt_TO)) {
        // END   02/10/2020 T.Ogura [QC#55712,MOD]
            searchCond2 = ZYPConstant.FLG_ON_1;
        }

        NPAL1510CommonLogic.setDispLevelPoSearchList(bizMsg, searchCond1, searchCond2, glblMsg);
    }

    private void doProcess_OnChange_SearchOption(NPAL1510CMsg cMsg, NPAL1510SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            NPAL1510CommonLogic.callNszc0330forSearchSearchOption(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

        }
    }

    private void doProcess_NPAL1510Scrn00_Next(NPAL1510CMsg cMsg, NPAL1510SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt() + 1);
        NPAL1510CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg.A);
    }

    private void doProcess_NPAL1510Scrn00_Prev(NPAL1510CMsg cMsg, NPAL1510SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        NPAL1510CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg.A);
    }

    private void doProcess_NPAL1510Scrn00_CMN_Download(NPAL1510CMsg bizMsg, NPAL1510SMsg glblMsg) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);

        String searchCond1 = ZYPConstant.FLG_OFF_0;
        String searchCond2 = ZYPConstant.FLG_OFF_0;
        if (ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd_SL) || ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) || ZYPCommonFunc.hasValue(bizMsg.shipToCustLocNm) || ZYPCommonFunc.hasValue(bizMsg.trxRefNum)
                || ZYPCommonFunc.hasValue(bizMsg.destRtlSwhCd) || ZYPCommonFunc.hasValue(bizMsg.poLineStsCd_SL) || ZYPCommonFunc.hasValue(bizMsg.vndPoAckStsCd_SL) || ZYPCommonFunc.hasValue(bizMsg.vndIssOrdNum)
                || ZYPCommonFunc.hasValue(bizMsg.proNum) || ZYPCommonFunc.hasValue(bizMsg.vndInvtyLocCd) || ZYPCommonFunc.hasValue(bizMsg.mdseCd) || ZYPCommonFunc.hasValue(bizMsg.aslMdseCd)|| ZYPCommonFunc.hasValue(bizMsg.mdseCd_SB)) {
            searchCond1 = ZYPConstant.FLG_ON_1;

        }

        // START 02/10/2020 T.Ogura [QC#55712,MOD]
//        if (ZYPCommonFunc.hasValue(bizMsg.xxOrdDt_FR) || ZYPCommonFunc.hasValue(bizMsg.xxOrdDt_TO) || ZYPCommonFunc.hasValue(bizMsg.vndSoNum)) {
        if (ZYPCommonFunc.hasValue(bizMsg.xxOrdDt_FR) || ZYPCommonFunc.hasValue(bizMsg.xxOrdDt_TO)) {
        // END   02/10/2020 T.Ogura [QC#55712,MOD]
            searchCond2 = ZYPConstant.FLG_ON_1;
        }

        boolean isNormalEnd = false;
        if ("PO".equals(bizMsg.xxDplyByCtrlItemCdNm.getValue())) {
            isNormalEnd = NPAL1510Query.getInstance().getDispLevelPoHeaderSearchForCsv(bizMsg, searchCond1, searchCond2, MAX_DOWNLOAD_CNT + 1);
        } else if ("Detail".equals(bizMsg.xxDplyByCtrlItemCdNm.getValue())) {
            isNormalEnd = NPAL1510Query.getInstance().getDispLevelPoDetailSearchForCsv(bizMsg, searchCond1, searchCond2, MAX_DOWNLOAD_CNT + 1);
        } else if ("ACK".equals(bizMsg.xxDplyByCtrlItemCdNm.getValue())) {
            isNormalEnd = NPAL1510Query.getInstance().getDispLevelPoAckSearchForCsv(bizMsg, searchCond1, searchCond2, MAX_DOWNLOAD_CNT + 1);
        } else {
            isNormalEnd = NPAL1510Query.getInstance().getDispLevelPoHeaderSearchForCsv(bizMsg, searchCond1, searchCond2, MAX_DOWNLOAD_CNT + 1);
        }

        // nothing data.
        if (!isNormalEnd) {
            return;
        }
    }
}
