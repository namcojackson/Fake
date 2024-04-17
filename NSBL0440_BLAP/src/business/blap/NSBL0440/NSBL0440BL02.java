/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0440;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSBL0440.constant.NSBL0440Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0440.common.NSBL0440CommonLogic;
import business.blap.NSBL0440.constant.NSBL0440Constant.COL_NM;
import business.db.SVC_MODTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
/**
 *<pre>
 * Mods Machine Level Status
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         O.Okuma         Create          N/A
 * 2016/04/18   Hitachi         M.Gotou         Update          QC#3425
 * 2016/06/22   Hitachi         M.Gotou         Update          QC#8911
 * 2016/07/14   Hitachi         O.Okuma         Update          QC#11647
 * 2018/02/02   Hitachi         M.Kidokoro      Update          QC#18150
 * 2018/06/01   Hitachi         U.Kim           Update          QC#22393
 * 2018/08/08   Hitachi         K.Kitachi       Update          QC#27460
 * 2019/03/22   Hitachi         K.Kitamura      Update          QC#30815
 * 2019/04/02   Hitachi         A.Kohinata      Update          QC#31027
 * 2022/09/08   CITS            E.Sanchez       Update          QC#60527
 *</pre>
 */
public class NSBL0440BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0440CMsg cMsg = (NSBL0440CMsg) arg0;
        NSBL0440SMsg sMsg = (NSBL0440SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0440_INIT".equals(screenAplID)) {
                doProcess_NSBL0440_INIT(cMsg, sMsg);
                // add start 2016/04/18 CSA Defect#3425
            } else if ("NSBL0440Scrn00_Filter".equals(screenAplID)) {
                doProcess_NSBL0440Scrn00_Filter(cMsg, sMsg);
            // add end 2016/04/18 CSA Defect#3425
            } else if ("NSBL0440Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSBL0440Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSBL0440Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0440Scrn00_CMN_Submit(cMsg, sMsg);
            // START 2018/02/02 M.Kidokoro [QC#18150, ADD]
            } else if ("NSBL0440Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSBL0440Scrn00_CMN_Download(cMsg, sMsg);
            // END 2018/02/02 M.Kidokoro [QC#18150, ADD]
            } else if ("NSBL0440Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSBL0440Scrn00_PageJump(cMsg, sMsg);
            } else if ("NSBL0440Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0440Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0440Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0440Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0440Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0440Scrn00_TBLColumnSort(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0440_INIT(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {
        // add start 2016/04/18 CSA Defect#3425
        cMsg.mdseCd_F.clear();
        cMsg.xxChkBox_OR.clear();
        cMsg.serNum_F.clear();
        // add end 2016/04/18 CSA Defect#3425
        init(cMsg, sMsg);
    }

    // add start 2016/04/18 CSA Defect#3425
    private void doProcess_NSBL0440Scrn00_Filter(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {

        init(cMsg, sMsg);
    }
    // add end 2016/04/18 CSA Defect#3425

   private void doProcess_NSBL0440Scrn00_CMN_Reset(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {

        doProcess_NSBL0440_INIT(cMsg, sMsg);
    }

    private void doProcess_NSBL0440Scrn00_CMN_Submit(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {

        doProcess_NSBL0440_INIT(cMsg, sMsg);
    }

    private void doProcess_NSBL0440Scrn00_PageJump(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {

        if (!checkdata(cMsg, sMsg)) {
            return;
        }

        int pagenationFrom = NSBL0440CommonLogic.convertPageNumToFirstRowIndex(cMsg);
        NSBL0440CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);

    }

    // START 2018/02/02 M.Kidokoro [QC#18150, ADD]
    private void doProcess_NSBL0440Scrn00_CMN_Download(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {
        createDownloadFile(cMsg, sMsg);
    }
    // END 2018/02/02 M.Kidokoro [QC#18150, ADD]

    private void doProcess_NSBL0440Scrn00_PageNext(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {

        if (!checkdata(cMsg, sMsg)) {
            return;
        }

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0440CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0440Scrn00_PagePrev(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {

        if (!checkdata(cMsg, sMsg)) {
            return;
        }

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0440CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0440Scrn00_TBLColumnSort(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {

        if (!checkdata(cMsg, sMsg)) {
            return;
        }

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

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
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum, new BigDecimal(cMsg.A.getValidCount()));
        }
    }

    private void init(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {
        NSBL0440CommonLogic.clearMsg(cMsg, sMsg);

        NSBL0440CommonLogic.createPullDown(cMsg);
        NSBL0440CommonLogic.setInitParams(cMsg, sMsg);

        NSBL0440CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        if (!hasValue(cMsg.svcModPk)) {
            cMsg.setMessageInfo(NZZM0012E, new String[]{SVC_MOD_PK});
            return;
        }

        findSvcMod(cMsg);
        findDtlInfo(cMsg, sMsg);

        // set Paging Data
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void findSvcMod(NSBL0440CMsg cMsg) {
        SVC_MODTMsg tMsg = new SVC_MODTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcModPk, cMsg.svcModPk);

        SVC_MODTMsg rtMsg =  (SVC_MODTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (rtMsg != null) {
            setValue(cMsg.svcModPlnId, rtMsg.svcModPlnId);
            setValue(cMsg.svcModNm, rtMsg.svcModNm);
        }
    }
    private void findDtlInfo(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcModPk", cMsg.svcModPk.getValue());
        // START 2022/09/08 E.Sanchez [QC#60527, DEL]
        // queryMap.put("limitCnt", sMsg.A.length() + 1);
        // END 2022/09/08 E.Sanchez [QC#60527, DEL]
        // add start 2016/04/18 CSA Defect#3425
        queryMap.put("xxChkBox_OR", cMsg.xxChkBox_OR.getValue());
        queryMap.put("mdseCd", cMsg.mdseCd_F.getValue());
        queryMap.put("serNum", cMsg.serNum_F.getValue());
        // add end 2016/04/18 CSA Defect#3425
        // add start 2016/06/22 CSA Defect#8911
        queryMap.put("mdseCdLen", MDSE_CODE_LEN_8);
        // add end 2016/06/22 CSA Defect#8911
        // add start 2016/07/14 CSA Defect#11647
        queryMap.put("svcModProcStsCd_op", SVC_MOD_PROC_STS.OPEN);
        // add end 2016/07/14 CSA Defect#11647
        // START 2019/03/22 S.Kitamura [QC#30815, ADD]
        queryMap.put("svcModProcStsCd_canc", SVC_MOD_PROC_STS.CANCELLED);
        queryMap.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        // END 2019/03/22 S.Kitamura [QC#30815, ADD]
        // START 2018/06/01 U.Kim [QC#22393, ADD]
        queryMap.put("svcMachMstrStsCd", cMsg.svcMachMstrStsCd.getValue());
        queryMap.put("svcModProcStsCd", cMsg.svcModProcStsCd.getValue());
        // END 2018/06/01 U.Kim [QC#22393, ADD]
        // START 2018/08/08 K.Kitachi [QC#27460, ADD]
        List<String> mdseItemRelnTpCdList = new ArrayList<String>();
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REFURBISHED);
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REMANUFACTURED);
        queryMap.put("mdseItemRelnTpCdList", mdseItemRelnTpCdList);
        // END 2018/08/08 K.Kitachi [QC#27460, ADD]
// START 2022/09/08 E.Sanchez [QC#60527, MOD]
//         S21SsmEZDResult ssmResult = NSBL0440Query.getInstance().getDtlInfo(queryMap, sMsg.A);
//
//         if (ssmResult.isCodeNormal()) {
//            int queryResCnt = ssmResult.getQueryResultCount();
//            if (queryResCnt > sMsg.A.length()) {
//                cMsg.setMessageInfo(NZZM0001W);
//                queryResCnt = sMsg.A.length();
//            }
//
//            int i = 0;
//            for (; i < sMsg.A.getValidCount(); i++) {
//                if (i == cMsg.A.length()) {
//                    break;
//                }
//                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
//            }
//            cMsg.A.setValidCount(i);
//
//            cMsg.setMessageInfo(NSBM0005I);
//            cMsg.xxPageShowFromNum.setValue(1);
//            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
//            cMsg.xxPageShowOfNum.setValue(queryResCnt);
//        } else {
//            cMsg.setMessageInfo(ZZZM9001E);
//            cMsg.xxPageShowFromNum.clear();
//            cMsg.xxPageShowToNum.clear();
//            cMsg.xxPageShowOfNum.clear();
//        }
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NSBL0440Query.getInstance().getClass());
            ps = ssmLLClient.createPreparedStatement("getDtlInfo", queryMap, execParam);
            rs = ps.executeQuery();
            ZYPTableUtil.clear(sMsg.A);
            filterResults(cMsg, sMsg, rs);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
// END 2022/09/08 E.Sanchez [QC#60527, MOD]
    }

    private boolean checkdata(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0440CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

        if (!NSBL0440CommonLogic.checkData(sMsg)) {
            NSBL0440CommonLogic.pagenation(cMsg, sMsg, rowIndex);
            return false;
        }
        return true;
    }

    // START 2018/02/02 M.Kidokoro [QC#18150, ADD]
    private void createDownloadFile(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NSBL0440Query.getInstance().getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID  + "_" + getUserProfileService().getContextUserInfo().getUserId()), CSV_FILE_EXT);

            Map<String, Object> ssMParam = new HashMap<String, Object>();
            ssMParam.put("glblCmpyCd", getGlobalCompanyCode());
            ssMParam.put("svcModPk", cMsg.svcModPk.getValue());
            ssMParam.put("limitCnt", LIMIT_DL_ROWNUM);
            ssMParam.put("xxChkBox_OR", cMsg.xxChkBox_OR.getValue());
            ssMParam.put("mdseCd", cMsg.mdseCd_F.getValue());
            ssMParam.put("serNum", cMsg.serNum_F.getValue());
            ssMParam.put("mdseCdLen", MDSE_CODE_LEN_8);
            ssMParam.put("svcModProcStsCd_op", SVC_MOD_PROC_STS.OPEN);
            // add start 2019/04/02 QC#31027
            ssMParam.put("svcModProcStsCd_canc", SVC_MOD_PROC_STS.CANCELLED);
            ssMParam.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            List<String> mdseItemRelnTpCdList = new ArrayList<String>();
            mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REFURBISHED);
            mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REMANUFACTURED);
            ssMParam.put("mdseItemRelnTpCdList", mdseItemRelnTpCdList);
            // add end 2019/04/02 QC#31027
            ps = ssmLLClient.createPreparedStatement("getDownloadFileData", ssMParam, execParam);
            rs = ps.executeQuery();

            NSBL0440CommonLogic.writeCsvFile(cMsg, rs);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }
    // END 2018/02/02 M.Kidokoro [QC#18150, ADD]

    // START 2022/09/08 E.Sanchez [QC#60527, ADD]
    private void filterResults(NSBL0440CMsg cMsg, NSBL0440SMsg sMsg, ResultSet rs) throws SQLException {

        if (!rs.next()) {
            cMsg.setMessageInfo(ZZZM9001E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }

        List<Map<String, Object>> serNumRangesList = NSBL0440Query.getInstance().getSvcModSerRng(getGlobalCompanyCode(), cMsg.svcModPk.getValue());

        int resultCnt = 0;
        do {
            BigDecimal svcModDtlPk = rs.getBigDecimal(COL_NM.SVC_MOD_DTL_PK.toString());
            String serNum = rs.getString(COL_NM.SER_NUM.toString());

            if (!NSBL0440CommonLogic.isWithinRange(serNum, svcModDtlPk, serNumRangesList)) {
                continue;
            }

            NSBL0440_ASMsg msgItem = (NSBL0440_ASMsg) sMsg.A.getMyComponent();
            setValue(msgItem.svcModStsPk_A, rs.getBigDecimal(COL_NM.SVC_MOD_STS_PK.toString()));
            setValue(msgItem.mdseCd_A, rs.getString(COL_NM.MDSE_CD.toString()));
            setValue(msgItem.serNum_A, serNum);
            setValue(msgItem.svcMachMstrStsDescTxt_A, rs.getString(COL_NM.SVC_MACH_MSTR_STS_DESC_TXT.toString()));
            setValue(msgItem.svcModProcStsCd_A, rs.getString(COL_NM.SVC_MOD_PROC_STS_CD.toString()));
            setValue(msgItem.svcModProcStsDescTxt_A, rs.getString(COL_NM.SVC_MOD_PROC_STS_DESC_TXT.toString()));
            setValue(msgItem.svcTaskNum_A, rs.getString(COL_NM.SVC_TASK_NUM.toString()));
            setValue(msgItem.svcTaskCloDt_A, rs.getString(COL_NM.SVC_TASK_CLO_DT.toString()));
            setValue(msgItem.svcModOptCd_A, rs.getString(COL_NM.SVC_MOD_OPT_CD.toString()));
            setValue(msgItem.svcModOptDt_A, rs.getString(COL_NM.SVC_MOD_OPT_DT.toString()));
            setValue(msgItem.svcModNoteTxt_A, rs.getString(COL_NM.SVC_MOD_NOTE_TXT.toString()));
            setValue(msgItem.ezUpTime_A, rs.getString(COL_NM.EZUPTIME.toString()));
            setValue(msgItem.ezUpTimeZone_A, rs.getString(COL_NM.EZUPTIMEZONE.toString()));
            setValue(msgItem.svcModDtlPk_A, svcModDtlPk);
            setValue(msgItem.svcMachMstrPk_A, rs.getBigDecimal(COL_NM.SVC_MACH_MSTR_PK.toString()));
            setValue(msgItem.xxRecHistCratTs_A, rs.getString(COL_NM.XX_REC_HIST_CRAT_TS.toString()));
            setValue(msgItem.xxRecHistCratByNm_A, rs.getString(COL_NM.XX_REC_HIST_CRAT_BY_NM.toString()));
            setValue(msgItem.xxRecHistUpdTs_A, rs.getString(COL_NM.XX_REC_HIST_UPD_TS.toString()));
            setValue(msgItem.xxRecHistUpdByNm_A, rs.getString(COL_NM.XX_REC_HIST_UPD_BY_NM.toString()));
            setValue(msgItem.xxRecHistTblNm_A, rs.getString(COL_NM.XX_REC_HIST_TBL_NM.toString()));
            EZDMsg.copy(msgItem, null, sMsg.A.no(resultCnt), null);
            resultCnt++;
            if (resultCnt == sMsg.A.length()) {
                break;
            }

        } while (rs.next());      

        if (resultCnt == sMsg.A.length() && rs.next()) {
            cMsg.setMessageInfo(NZZM0001W);
        } else if (resultCnt == 0) {
            cMsg.setMessageInfo(ZZZM9001E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }

        sMsg.A.setValidCount(resultCnt);
        
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);

        cMsg.setMessageInfo(NSBM0005I);
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(resultCnt);
    }
    // END 2022/09/08 E.Sanchez [QC#60527, ADD]
}
