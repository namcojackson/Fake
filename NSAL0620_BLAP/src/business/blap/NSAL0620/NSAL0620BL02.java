/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0620;

import static business.blap.NSAL0620.constant.NSAL0620Constant.CSV_FILE_NAME;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LIMIT_DL_ROWNUM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.MAX_FETCH_SIZE;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0620.common.NSAL0620CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/25   Hitachi         M.Gotou         Update          QC4595
 * 2016/04/18   Hitachi         T.Kanasaka      Update          QC#5397
 * 2016/04/25   Hitachi         M.Gotou         Update          QC#4326
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2017/02/27   Hitachi         N.Arai          Update          QC#17804
 * 2018/06/26   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/11/05   Hitachi         K.Fujimoto      Update          QC#28627
 * 2019/08/30   Hitachi         T.Aoyagi        Update          QC#53005
 * 2022/10/13   Hitachi         M.Komatsu       Update          QC#60078,60537
 * 2024/02/07   Hitachi         K.Watanabe      Update          QC#63464
 *</pre>
 */
public class NSAL0620BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplId = cMsg.getScreenAplID();

            if ("NSAL0620_INIT".equals(screenAplId)) {
                doProcess_NSAL0620_INIT((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);
                ZYPGUITableColumn.getColData((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                // events of NSAL0620Scrn00.
            } else if (screenAplId.startsWith("NSAL0620Scrn00")) {

                if (screenAplId.endsWith("_SrchContr")) {
                    doProcess_NSAL0620_SrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_ChgSavedSrchOpt")) {
                    doProcess_NSAL0620_ChgSavedSrchOpt((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_SaveSrchOpt")) {
                    doProcess_NSAL0620_SaveSrchOpt((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_DelSrchOpt")) {
                    doProcess_NSAL0620_DelSrchOpt((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_PagePrev")) {
                    doProcess_NSAL0620_PagePrev((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_PageNext")) {
                    doProcess_NSAL0620_PageNext((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_CMN_Clear")) {
                    doProcess_NSAL0620_CMN_Clear((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_CMN_Download")) {
                    doProcess_NSAL0620_CMN_Download((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_CMN_Return")) {
                    doProcess_NSAL0620_CMN_Return((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_TBLColumnSort")) {
                    doProcess_NSAL0620_TBLColumnSort((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_PagePrev")) {
                    doProcess_NSAL0620_PagePrev((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_PageNext")) {
                    doProcess_NSAL0620_PageNext((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_SelectAll_Contr")) {
                    doProcess_NSAL0620_SelectAll_Contr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_UnSelectAll_Contr")) {
                    doProcess_NSAL0620_UnSelectAll_Contr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                } else if (screenAplId.endsWith("_CMN_Clear")) {
                    doProcess_NSAL0620_CMN_Clear((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

                // START 2018/06/26 T.Ogura [QC#26336,ADD]
                } else if (screenAplId.endsWith("_Meter_History")) {
                    doProcess_NSAL0620_Meter_History((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);
                // END   2018/06/26 T.Ogura [QC#26336,ADD]
                }
            // START 2016/04/18 T.Kanasaka [QC#5397, ADD]
            } else if (screenAplId.equals("NSAL0620_NSAL0380")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0390")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0400")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0460")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0630")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0640")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0650")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0660")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0670")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0690")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0700")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0710")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0720")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0730")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0740")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            } else if (screenAplId.equals("NSAL0620_NSAL0750")) {
                doProcess_NSAL0620_ReSrchContr((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);

            // END 2016/04/18 T.Kanasaka [QC#5397, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0620_TBLColumnSort(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt.getValue();

        // START 2017/02/27 N.Arai [QC#17804 ADD]
        NSAL0620CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // END 2017/02/27 N.Arai [QC#17804 ADD]

        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Show 1st page
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        }

    }

    private void doProcess_NSAL0620_DelSrchOpt(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        return;
    }

    private void doProcess_NSAL0620_SaveSrchOpt(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        return;
    }

    private void doProcess_NSAL0620_ChgSavedSrchOpt(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            NSAL0620CommonLogic.callNszc0330forSearchOption(cMsg, sMsg, getContextUserInfo().getUserId());
        }
    }

    private void doProcess_NSAL0620_CMN_Return(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        return;
    }

    private void doProcess_NSAL0620_CMN_Download(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NSAL0620Query.getInstance().getClass());

            //create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");

            Map<String, Object> ssMParam = NSAL0620Query.getInstance().getSsmParam(cMsg, sMsg, LIMIT_DL_ROWNUM);
            ps = ssmLLClient.createPreparedStatement("getSearchData", ssMParam, execParam);
            rs = ps.executeQuery();
            NSAL0620CommonLogic.writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    private void doProcess_NSAL0620_CMN_Clear(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        sMsg.A.clear();
        cMsg.dsContrNum_H.clear();
        // add start 2018/11/06 CSA Defect#28627
        cMsg.contrLinkNum_H.clear();
        // add end   2018/11/06 CSA Defect#28627
        cMsg.dsContrCratDt_H1.clear();
        cMsg.dsContrCratDt_H2.clear();
        // mod start 2016/04/25 CSA Defect#4326
        cMsg.dsContrRptGrpNum_H.clear();
        // mod end 2016/04/25 CSA Defect#4326
        cMsg.svcContrRefCmntTxt_H.clear();
        cMsg.dsAcctNm_H.clear();
        cMsg.dsAcctNum_H.clear();
        cMsg.xxGenlFldAreaTxt_H1.clear();
        cMsg.billToCustCd_H1.clear();
        // add start 2016/07/01 CSA Defect#11261
        cMsg.mdseDescShortTxt_H.clear();
        // add end 2016/07/01 CSA Defect#11261
        cMsg.dsAcctNm_H3.clear();
        cMsg.serNum_H.clear();
        // START 2022/10/13 M.Komatsu [QC#60537,ADD]
        cMsg.mdseCd_H.clear();
        // END 2022/10/13 M.Komatsu [QC#60537,ADD]
        cMsg.t_MdlNm_H.clear();
        // START 2019/08/30 [QC#53005,ADD]
        cMsg.svcConfigMstrPk_H.clear();
        // END 2019/08/30 [QC#53005,ADD]
        // mod start 2016/04/25 CSA Defect#4326
        cMsg.dsAcctNm_H2.clear();
        // mod end 2016/04/25 CSA Defect#4326
        cMsg.xxGenlFldAreaTxt_H2.clear();
        cMsg.locNum_H.clear();
        cMsg.xxFromDt_H.clear();
        cMsg.xxThruDt_H.clear();
        cMsg.srchOptNm_S.clear();

        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // cMsg.dsContrCatgCd_H.clear();
        // cMsg.dsContrCtrlStsCd_H.clear();
        // cMsg.dsContrClsCd_H.clear();
        cMsg.xxComnScrColValTxt_H1.clear();
        cMsg.xxComnScrColValTxt_H2.clear();
        cMsg.xxComnScrColValTxt_H3.clear();
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        cMsg.svcPgmMdseCd_H.clear();
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // cMsg.svcMachMstrStsCd_H.clear();
        // cMsg.bllgCycleCd_HB.clear();
        // cMsg.bllgCycleCd_HM.clear();
        cMsg.xxComnScrColValTxt_H4.clear();
        cMsg.xxComnScrColValTxt_H5.clear();
        cMsg.xxComnScrColValTxt_H6.clear();
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        cMsg.srchOptPk_S.clear();
        cMsg.xxGenlFldAreaTxt_B.clear();

        cMsg.xxRadioBtn_H1.clear();
        cMsg.xxRadioBtn_H2.clear();

        // START 2024/02/07 K.Watanabe [QC#63464,ADD]
        NSAL0620CommonLogic.initSetToSrchCntZero(cMsg.xxSrchCnt);
        // END 2024/02/07 K.Watanabe [QC#63464,ADD]
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    private void doProcess_NSAL0620_SrchContr(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        NSAL0620CommonLogic.getSearchData(cMsg, sMsg);

        NSAL0620CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        NSAL0620CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSAL0620CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0620_PageNext(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        NSAL0620CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValue().add(BigDecimal.ONE));
        NSAL0620CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0620CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0620_PagePrev(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        NSAL0620CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValue().subtract(new BigDecimal(cMsg.A.length())));
        NSAL0620CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0620CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0620_SelectAll_Contr(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // add start 2016/03/25 CSA Defect#4595
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).svcMachMstrPk_A)) {
                // add end 2016/03/25 CSA Defect#4595
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
            }
        }
    }

    private void doProcess_NSAL0620_UnSelectAll_Contr(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * doProcess_NSAL0620_INIT
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     */
    private void doProcess_NSAL0620_INIT(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        NSAL0620CommonLogic.createPullDown(cMsg, getContextUserInfo().getUserId());
        NSAL0620CommonLogic.createList(cMsg);

        // START 2024/02/07 K.Watanabe [QC#63464,ADD]
        NSAL0620CommonLogic.initSetToSrchCntZero(cMsg.xxSrchCnt);
        // END 2024/02/07 K.Watanabe [QC#63464,ADD]

    }

    // START 2016/04/18 T.Kanasaka [QC#5397, ADD]
    private void doProcess_NSAL0620_ReSrchContr(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        // save pageNum
        BigDecimal pageShowFromNum = cMsg.xxPageShowFromNum.getValue();

        // save checkbox
        NSAL0620CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        List<BigDecimal> checkedDsContrDtlPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                checkedDsContrDtlPkList.add(sMsg.A.no(i).dsContrDtlPk_A.getValue());
            }
        }

        // research
        NSAL0620CommonLogic.getSearchData(cMsg, sMsg);

        // restore pageNum
        cMsg.xxPageShowFromNum.setValue(pageShowFromNum);

        // restore checkbox
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (checkedDsContrDtlPkList.contains(sMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
            }
        }

        NSAL0620CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0620CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }
    // END 2016/04/18 T.Kanasaka [QC#5397, ADD]

    // START 2018/06/26 T.Ogura [QC#26336,ADD]
    /**
     * Meter History Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NSAL0620_Meter_History(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        NSAL0620CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        if (!NSAL0620CommonLogic.checkSelect(cMsg, sMsg)) {
            return;
        }

        NSAL0620Query.getInstance().createCSV(cMsg, sMsg);
    }
    // END   2018/06/26 T.Ogura [QC#26336,ADD]
}
