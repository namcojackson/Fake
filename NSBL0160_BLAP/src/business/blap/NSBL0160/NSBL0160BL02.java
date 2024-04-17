/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0160;

import static business.blap.NSBL0160.constant.NSBL0160Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSBL0160.common.NSBL0160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 *
 * Memo Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/11   Hitachi         Y.Igarashi      Create          N/A
 * 2015/10/29   Hitachi         T.Tomita        Update          N/A
 * 2016/02/19   Hitachi         K.Kasai         Update          QC#3689
 * 2017/08/09   Hitachi         U.Kim           Update          QC#18151
 * 2022/05/11   Hitachi         D.Yoshida       Update          QC#56411
 *</pre>
 */
public class NSBL0160BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            // START 2015/10/29 T.Tomita [N/A, MOD]
            if ("NSBL0160_INIT".equals(screenAplID)) {
                doProcess_NSBL0160Scrn00_init((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
            // mod start 2016/02/19 CSA Defect#3689
//            } else if ("NSBL0160Scrn00_ChangeSearchSvcMemoTp".equals(screenAplID)) {
//                doProcess_NSBL0160Scrn00_ChangeSearchSvcMemoTp((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
//            } else if ("NSBL0160Scrn00_ChangeAddSvcMemoTp".equals(screenAplID)) {
//                doProcess_NSBL0160Scrn00_ChangeAddSvcMemoTp((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
            // mod start 2016/02/19 CSA Defect#3689
            } else if ("NSBL0160Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0160Scrn00_Search((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
            } else if ("NSBL0160Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0160Scrn00_PagePrev((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
            } else if ("NSBL0160Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0160Scrn00_PageNext((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
            } else if ("NSBL0160Scrn00_Add_Cmnt".equals(screenAplID)) {
                doProcess_NSBL0160Scrn00_Add_Cmnt((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
            } else if ("NSBL0160Scrn00_CMN_Save".equals(screenAplID)) {
                // START 2017/08/09 U.Kim [QC#18151, MOD]
                // doProcess_NSBL0160Scrn00_Search((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
                doProcess_NSBL0160Scrn00_CMN_Save((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
                // END 2017/08/09 U.Kim [QC#18151, MOD]
            } else if ("NSBL0160Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSBL0160Scrn00_init((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
            // add start 2016/02/19 CSA Defect#3689
            } else if ("NSBL0160Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSBL0160Scrn00_Download((NSBL0160CMsg) cMsg, (NSBL0160SMsg) sMsg);
            // add end 2016/02/19 CSA Defect#3689
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // END 2015/10/29 T.Tomita [N/A, MOD]
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // START 2015/10/29 T.Tomita [N/A, MOD]
    private void doProcess_NSBL0160Scrn00_init(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.B);
        // mod start 2016/02/19 CSA Defect#3689
        // Search Condition
//        cMsg.svcMemoTpCd_SC.clear();
//        cMsg.svcMemoTpCd_SP.clear();
//        cMsg.svcMemoTpDescTxt_SP.clear();
        cMsg.svcMemoRsnCd_SC.clear();
        cMsg.svcMemoRsnCd_SP.clear();
        cMsg.svcMemoRsnDescTxt_SP.clear();
        cMsg.xxFromDt_SC.clear();
        cMsg.xxThruDt_SC.clear();
//        // Add Line
//        cMsg.svcMemoTpCd_AC.clear();
//        cMsg.svcMemoTpCd_AP.clear();
//        cMsg.svcMemoTpDescTxt_AP.clear();
//        cMsg.svcMemoRsnCd_AC.clear();
//        cMsg.svcMemoRsnCd_AP.clear();
//        cMsg.svcMemoRsnDescTxt_AP.clear();
        // mod end 2016/02/19 CSA Defect#3689
        ZYPTableUtil.clear(cMsg.B);

        EZDMsg.copy(cMsg, null, sMsg, null);

        // Pulldown
        NSBL0160CommonLogic.getInitPulldownList(getGlobalCompanyCode(), cMsg);

        // mod start 2016/02/19 CSA Defect#3689
        // START 2022/05/11 [QC#56411, MOD]
//        NSBL0160CommonLogic.searchSvcMemo(getGlobalCompanyCode(), cMsg, sMsg, sMsg.B.length() + 1);
        NSBL0160CommonLogic.searchSvcMemo(getGlobalCompanyCode(), cMsg, sMsg, sMsg.B.length());
        // END   2022/05/11 [QC#56411, MOD]
        // mod end 2016/02/19 CSA Defect#3689

        // Pagenation
        NSBL0160CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        NSBL0160CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSBL0160CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.B.getValidCount(), sMsg.B.getValidCount());
    }

    // mod start 2016/02/19 CSA Defect#3689
//    private void doProcess_NSBL0160Scrn00_ChangeSearchSvcMemoTp(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
//
//        // Clear Service Memo Reason Pulldown
//        cMsg.svcMemoRsnCd_SC.clear();
//        cMsg.svcMemoRsnCd_SP.clear();
//        cMsg.svcMemoRsnDescTxt_SP.clear();
//
//        if (!ZYPCommonFunc.hasValue(cMsg.svcMemoTpCd_SC)) {
//            return;
//        }
//
//        // Setup Service Memo Reason Pulldown
//        NSBL0160CommonLogic.setSearchSvcMemoRsnPulldownList(getGlobalCompanyCode(), cMsg);
//    }
//
//    private void doProcess_NSBL0160Scrn00_ChangeAddSvcMemoTp(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
//
//        // Clear Service Memo Reason Pulldown
//        cMsg.svcMemoRsnCd_SC.clear();
//        cMsg.svcMemoRsnCd_SP.clear();
//        cMsg.svcMemoRsnDescTxt_SP.clear();
//
//        if (!ZYPCommonFunc.hasValue(cMsg.svcMemoTpCd_AC)) {
//            return;
//        }
//
//        // Setup Service Memo Reason Pulldown
//        NSBL0160CommonLogic.setAddLineSvcMemoRsnPulldownList(getGlobalCompanyCode(), cMsg);
//    }
    // mod end 2016/02/19 CSA Defect#3689

    private void doProcess_NSBL0160Scrn00_Search(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(cMsg.B);

        // mod start 2016/02/19 CSA Defect#3689
        // START 2022/05/11 [QC#56411, MOD]
//        NSBL0160CommonLogic.searchSvcMemo(getGlobalCompanyCode(), cMsg, sMsg, sMsg.B.length() + 1);
        NSBL0160CommonLogic.searchSvcMemo(getGlobalCompanyCode(), cMsg, sMsg, sMsg.B.length());
        // END   2022/05/11 [QC#56411, MOD]
        // mod end 2016/02/19 CSA Defect#3689

        // Pagenation
        NSBL0160CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        NSBL0160CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSBL0160CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.B.getValidCount(), sMsg.B.getValidCount());
    }

    private void doProcess_NSBL0160Scrn00_PagePrev(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
        if (!hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }
        NSBL0160CommonLogic.copyBizMsgToGlblMsg(cMsg, sMsg);
        // Pagenation
        setValue(cMsg.xxPageShowFromNum, cMsg.xxPageShowFromNum.getValue().subtract(new BigDecimal(cMsg.B.length())));
        NSBL0160CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSBL0160CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.B.getValidCount(), sMsg.B.getValidCount());
    }

    private void doProcess_NSBL0160Scrn00_PageNext(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
        if (!hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }
        NSBL0160CommonLogic.copyBizMsgToGlblMsg(cMsg, sMsg);
        // Pagenation
        setValue(cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum.getValue().add(BigDecimal.ONE));
        NSBL0160CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSBL0160CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.B.getValidCount(), sMsg.B.getValidCount());
    }

    private void doProcess_NSBL0160Scrn00_Add_Cmnt(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
//        EZDMsg.copy(cMsg.B, null, sMsg.B, null);

//        String existName = NSBL0160CommonLogic.existMemoTpCd(getGlobalCompanyCode(), cMsg, sMsg, cMsg.svcMemoTpCd_SV.getValue());
//        if (hasValue(existName)) {
//            cMsg.svcMemoTpCd_SV.setErrorInfo(1, NSBM0016E, new String[]{existName});
//            return;
//        }

        int thisCnt = sMsg.B.getValidCount();
        if (thisCnt >= sMsg.B.length()) {
            cMsg.setMessageInfo(NSBM0057E);
            return;
        }

        NSBL0160CommonLogic.copyBizMsgToGlblMsg(cMsg, sMsg);
        // mod start 2016/02/19 CSA Defect#3689
        String svcMemoRsnCd = cMsg.svcMemoRsnCd_SC.getValue();
        String svcMemoTpCd = NSBL0160CommonLogic.getSvcMemoTpCd(getGlobalCompanyCode(), svcMemoRsnCd);
        setValue(sMsg.B.no(thisCnt).svcMemoCatgCd_B, cMsg.svcMemoCatgCd_HD);
        setValue(sMsg.B.no(thisCnt).svcMemoTpCd_B, svcMemoTpCd);
        setValue(sMsg.B.no(thisCnt).svcMemoTpDescTxt_B, NSBL0160CommonLogic.getSvcMemoTpDescTxt(getGlobalCompanyCode(), svcMemoTpCd));
        setValue(sMsg.B.no(thisCnt).svcMemoRsnCd_B, svcMemoRsnCd);
        setValue(sMsg.B.no(thisCnt).svcMemoRsnDescTxt_B, NSBL0160CommonLogic.getSvcMemoRsnDescTxt(getGlobalCompanyCode(), svcMemoRsnCd));
        // mod end 2016/02/19 CSA Defect#3689
        setValue(sMsg.B.no(thisCnt).lastUpdUsrId_B, getContextUserInfo().getUserId());
        setValue(sMsg.B.no(thisCnt).fullPsnNm_B, getContextUserInfo().getFullName());
        sMsg.B.setValidCount(thisCnt + 1);

        // Pagenation
        int lastPageFromNum = 0;
        if (sMsg.B.getValidCount() % cMsg.B.length() > 0) {
            lastPageFromNum = (sMsg.B.getValidCount() / cMsg.B.length()) * cMsg.B.length() + 1;
        } else {
            lastPageFromNum = (sMsg.B.getValidCount() / cMsg.B.length() - 1) * cMsg.B.length() + 1;
        }
        setValue(cMsg.xxPageShowFromNum, new BigDecimal(lastPageFromNum));
        NSBL0160CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSBL0160CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.B.getValidCount(), sMsg.B.getValidCount());
    }
    // add start 2016/02/19 CSA Defect#3689

    /**
     * do process (download)
     * @param cMsg NSAL0870CMsg
     * @param sMsg NSAL0870SMsg
     */
    private void doProcess_NSBL0160Scrn00_Download(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NSBL0160Query.getInstance().getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");

            Map<String, Object> ssMParam = NSBL0160CommonLogic.getSsmParam(getGlobalCompanyCode(), cMsg, LIMIT_DOWNLOAD + 1);
            ps = ssmLLClient.createPreparedStatement("searchSvcMemoList", ssMParam, execParam);
            rs = ps.executeQuery();
            NSBL0160CommonLogic.writeCsvFile(cMsg, rs);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }
    // add end 2016/02/19 CSA Defect#3689

//    private void doProcess_NSBL0160Scrn00_CMN_Save(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
//        sMsg.B.clear();
//        sMsg.B.setValidCount(0);
//
//        NSBL0160CommonLogic.searchSvcMemo(getGlobalCompanyCode(), cMsg, sMsg);
//
//        afterSaveSort(cMsg, sMsg);
//
//        cMsg.B.clear();
//        cMsg.B.setValidCount(0);
//
//        EZDMsg.copy(sMsg.B, null, cMsg.B, null);
//
//        NSBL0160CommonLogic.setDispTsFormat(cMsg, sMsg);
//    }
//
//    private void afterSaveSort(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
//        List<NSBL0160_BSMsg> list = new ArrayList<NSBL0160_BSMsg>();
//        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//            NSBL0160_BCMsg bcMsg = cMsg.B.no(i);
//            for (int j = 0; j < sMsg.B.getValidCount(); j++) {
//                NSBL0160_BSMsg target = sMsg.B.no(j);
//                if (hasValue(bcMsg.svcMemoPk) && bcMsg.svcMemoPk.getValue().equals(target.svcMemoPk.getValue())) {
//                    // update data
//                    list.add((NSBL0160_BSMsg) sMsg.B.no(j).clone());
//                    break;
//                }
//                if (isInsertData(cMsg, bcMsg, target)) {
//                    // insert data
//                    list.add((NSBL0160_BSMsg) sMsg.B.no(j).clone());
//                    break;
//                }
//            }
//            if (!hasValue(bcMsg.svcMemoPk) && !hasValue(bcMsg.svcCmntTxt)) {
//                // not insert data
//                NSBL0160_BSMsg newTarget = new NSBL0160_BSMsg();
//                EZDMsg.copy(bcMsg, null, newTarget, null);
//                list.add(newTarget);
//            }
//        }
//
//        sMsg.B.clear();
//        sMsg.B.setValidCount(0);
//
//        for (int i = 0; i < list.size(); i++) {
//            EZDMsg.copy(list.get(i), null, sMsg.B.no(i), null);
//        }
//        sMsg.B.setValidCount(list.size());
//    }
//
//    private boolean isInsertData(NSBL0160CMsg cMsg, NSBL0160_BCMsg bcMsg, NSBL0160_BSMsg target) {
//        if (SVC_MEMO_CATG.DISPATCH_MEMO.equals(cMsg.svcMemoCatgCd_SC.getValue())) {
//            if (bcMsg.svcMemoTpCd.getValue().equals(target.svcMemoTpCd.getValue())
//                    && bcMsg.svcTaskNum.getValue().equals(target.svcTaskNum.getValue())) {
//                return true;
//            }
//        } else {
//            if (bcMsg.svcMemoTpCd.getValue().equals(target.svcMemoTpCd.getValue())) {
//                return true;
//            }
//        }
//        return false;
//    }
    // END 2015/10/29 T.Tomita [N/A, MOD]
    
    // START 2017/08/09 U.Kim [QC#18151, ADD]
    private void doProcess_NSBL0160Scrn00_CMN_Save(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
        cMsg.svcMemoRsnCd_SC.clear();
        doProcess_NSBL0160Scrn00_Search(cMsg, sMsg);
    }
    // END 2017/08/09 U.Kim [QC#18151, ADD]
}
