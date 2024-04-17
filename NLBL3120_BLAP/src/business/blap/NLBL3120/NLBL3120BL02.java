/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3120;

import static business.blap.NLBL3120.constant.NLBL3120Constant.CSV_FILE_NAME;
import static business.blap.NLBL3120.constant.NLBL3120Constant.CSV_HDR;
import static business.blap.NLBL3120.constant.NLBL3120Constant.FIRST_RECORD;
import static business.blap.NLBL3120.constant.NLBL3120Constant.LIMIT_DL_ROWNUM;
import static business.blap.NLBL3120.constant.NLBL3120Constant.MAX_FETCH_SIZE;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM0002E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1231E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1307E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1308E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLBM1328E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NLZM2278E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NZZM0000E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NZZM0001W;
import static business.blap.NLBL3120.constant.NLBL3120Constant.NZZM0009E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.ZZM9000E;
import static business.blap.NLBL3120.constant.NLBL3120Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3120.common.NLBL3120CommonLogic;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.S21_PSNTMsg;
import business.db.SCHD_COORD_ASGTMsg;
import business.db.SCHD_COORD_ASGTMsgArray;
import business.db.SCHD_COORD_ASG_RELNTMsg;
import business.db.SCHD_COORD_ASG_RELNTMsgArray;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsg;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsgArray;
import business.db.TOCTMsg;
import business.file.NLBL3120F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 * 2017/01/26   CITS            M.Naito         Update          QC#16924
 * 2017/03/03   CITS            K.Kameoka       Update          QC#17825
 * 2017/06/28   CITS            T.Kikuhara      Update          QC#18993
 * 2017/06/28   CITS            T.Kikuhara      Update          QC#19137
 * 2017/06/28   CITS            T.Kikuhara      Update          QC#19520
 * 2018/09/06   CITS            K.Ogino         Update          QC#27425
 *</pre>
 */
public class NLBL3120BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3120_INIT".equals(screenAplID)) {
                doProcess_NLBL3120_INIT((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);
                getColData((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_Search".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_Search((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_CMN_Reset((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_CMN_Clear((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_PageNext((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_PagePrev((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_PageJump((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_Select_All".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_Select_All((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_UnSelect_All".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_UnSelect_All((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            }  else if ("NLBL3120Scrn00_Apply".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_Apply((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            }  else if ("NLBL3120Scrn00_Search_CoordInfo".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_Search_CoordInfo((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            }  else if ("NLBL3120Scrn00_Search_MgrInfo".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_Search_MgrInfo((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            }  else if ("NLBL3120Scrn00_Search_RtlWHInfo".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_Search_RtlWHInfo((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            }  else if ("NLBL3120Scrn00_Search_SlsRepInfo".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_Search_SlsRepInfo((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            }  else if ("NLBL3120Scrn00_Search_SupvInfo".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_Search_SupvInfo((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            }  else if ("NLBL3120Scrn00_All_Collapse".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_All_Collapse((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            }  else if ("NLBL3120Scrn00_All_Expand".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_All_Expand((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NLBL3120_OnChangeSavedSearchOption((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_CMN_Download((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_TBLColumnSort((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_MoveWin_MngBO".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_MoveWin_MngBO((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_MoveWin_CoordWrkBench".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_MoveWin_CoordWrkBench((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120_NLBL3100".equals(screenAplID)) {
                doProcess_NLBL3120_NLBL3100((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            // After update process
            } else if ("NLBL3120Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_CMN_Submit((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_Release_SO".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_Release_SO((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            /** QC#16924# 01/26/2017 M.Naito Start **/
            } else if ("NLBL3120Scrn00_OnChange_ChkBoxTrxHdrNum".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_OnChange_ChkBoxTrxHdrNum((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            } else if ("NLBL3120Scrn00_OnChange_ChkBoxSoNum".equals(screenAplID)) {
                doProcess_NLBL3120Scrn00_OnChange_ChkBoxSoNum((NLBL3120CMsg) cMsg, (NLBL3120SMsg) sMsg);

            /** QC#16924# 01/26/2017 M.Naito End **/
            // No process
            } else if ("NLBL3120Scrn00_SaveSearch".equals(screenAplID)) {
                // There is no process.
                return;

            } else if ("NLBL3120Scrn00_DeleteSearch".equals(screenAplID)) {
                // There is no process.
                return;

            } else if ("NLBL3120Scrn00_CMN_ColClear".equals(screenAplID)) {
                // There is no process.
                return;

            } else if ("NLBL3120Scrn00_CMN_ColSave".equals(screenAplID)) {
                // There is no process.
                return;

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * doProcess_NLBL3120Scrn00_MoveWin_MngBO
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_MoveWin_MngBO(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        List<Integer> chkList = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A2", ZYPConstant.CHKBOX_ON_Y);

        if (chkList.size() != 1) {
            int firstErrIdx = 0;
            boolean firstFlg = true;
            for (Integer index : chkList) {

                sMsg.A.no(index).xxChkBox_A2.setErrorInfo(1, NZZM0009E);

                if (firstFlg) {
                    firstErrIdx = index;
                    firstFlg = false;
                }
            }

            int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndexForError(firstErrIdx, cMsg, sMsg);
            int fromNum = nextRecIdx;
            NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
            cMsg.setMessageInfo(NZZM0009E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.trxHdrNum_P, sMsg.A.no(chkList.get(FIRST_RECORD)).trxHdrNum_A1);
    }

    /**
     * doProcess_NLBL3120Scrn00_MoveWin_CoordWrkBench
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_MoveWin_CoordWrkBench(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        List<Integer> chkList = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A2", ZYPConstant.CHKBOX_ON_Y);

        if (chkList.size() != 1) {
            int firstErrIdx = 0;
            boolean firstFlg = true;
            for (Integer index : chkList) {

                sMsg.A.no(index).xxChkBox_A2.setErrorInfo(1, NZZM0009E);

                if (firstFlg) {
                    firstErrIdx = index;
                    firstFlg = false;
                }
            }

            int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndexForError(firstErrIdx, cMsg, sMsg);
            int fromNum = nextRecIdx;
            NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
            cMsg.setMessageInfo(NZZM0009E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.trxHdrNum_P, sMsg.A.no(chkList.get(FIRST_RECORD)).trxHdrNum_A1);
    }


    /**
     * doProcess_NLBL3120_INIT
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120_INIT(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        clearAll(cMsg, sMsg);

        createPulldown(cMsg, sMsg);

        S21SsmEZDResult res = NLBL3120Query.getInstance().getMgrAndSupvPsnCd();
        Map<String, String> map = (Map<String, String>) res.getResultObject();
        ZYPEZDItemValueSetter.setValue(cMsg.mgrPsnCd_LG, map.get("MGR_PSN_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.supvPsnCd_LG, map.get("SUPV_PSN_CD"));

        boolean doSearch = false;
        if (ZYPCommonFunc.hasValue(cMsg.trxHdrNum_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.trxHdrNum, cMsg.trxHdrNum_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.t_MdlNm_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm, cMsg.t_MdlNm_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk, cMsg.svcConfigMstrPk_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.soNum_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.soNum, cMsg.soNum_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.rwsNum_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.rwsNum, cMsg.rwsNum_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, cMsg.rtlWhCd_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.schdCoordPsnCd, cMsg.schdCoordPsnCd_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.supvPsnCd_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.supvPsnCd, cMsg.supvPsnCd_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.mgrPsnCd_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.mgrPsnCd, cMsg.mgrPsnCd_BK);
            doSearch = true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.slsRepOrSlsTeamTocCd_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.slsRepOrSlsTeamTocCd, cMsg.slsRepOrSlsTeamTocCd_BK);
            doSearch = true;
        }

        if (doSearch) {
            search(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_Search
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_Search(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        // Master Check
        if (checkInputHeader(cMsg)) {
            // Search
            search(cMsg, sMsg);
        }
        cMsg.xxWrnSkipFlg.clear();
    }

    /**
     * doProcess_NLBL3120Scrn00_CMN_Clear
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_CMN_Clear(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        String col = cMsg.xxComnColOrdTxt.getValue();
        clearAll(cMsg, sMsg);

        createPulldown(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, col);
    }

    /**
     * doProcess_NLBL3120Scrn00_CMN_Reset
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_CMN_Reset(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        doProcess_NLBL3120_INIT(cMsg, sMsg);
        getColData(cMsg, sMsg);
    }

    /**
     * doProcess_NLBL3120Scrn00_PageNext
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_PageNext(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        int fromNum = cMsg.xxPageShowToNum_A.getValueInt();
        int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndex(fromNum, cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
    }

    /**
     * doProcess_NLBL3120Scrn00_PagePrev
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_PagePrev(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        int fromNum = cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1;
        int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndex(fromNum, cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
    }

    /**
     * doProcess_NLBL3120Scrn00_PageJump
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_PageJump(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        int fromNum = cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1);
        int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndex(fromNum, cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
    }

    /**
     * doProcess_NLBL3120Scrn00_Select_All
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_Select_All(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxPgFlg_A1.getValue())) {
                cMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
            } else {
                cMsg.A.no(i).xxChkBox_A2.setValue(ZYPConstant.CHKBOX_ON_Y);
            }
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxPgFlg_A1.getValue())) {
                sMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
            } else {
                sMsg.A.no(i).xxChkBox_A2.setValue(ZYPConstant.CHKBOX_ON_Y);
            }
        }


    }

    /**
     * doProcess_NLBL3120Scrn00_UnSelect_All
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_UnSelect_All(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            cMsg.A.no(i).xxChkBox_A1.clear();
            cMsg.A.no(i).xxChkBox_A2.clear();
        }
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox_A1.clear();
            sMsg.A.no(i).xxChkBox_A2.clear();
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_Apply
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_Apply(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        String  glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // Input check in Detail
        boolean chkBoxOn = false;
        int firstErrIdx = -1;
        boolean hasErr = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

            if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxPgFlg_A1.getValue())
                    && ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())) {
                chkBoxOn = true;
                if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.schdTrxOpenFlg_A1.getValue())) {
                    // Not Open Status
                    sMsgALine.xxChkBox_A2.setErrorInfo(1, NLBM1307E);
                    hasErr = true;
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                }
            }
        }

        if (hasErr) {
            int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndexForError(firstErrIdx, cMsg, sMsg);
            int fromNum = nextRecIdx;
            NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
            return;
        }
        if (!chkBoxOn) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                NLBL3120_ACMsg cMsgALine = cMsg.A.no(i);
                if (!ZYPConstant.CHKBOX_ON_Y.equals(cMsgALine.xxPgFlg_A1.getValue())) {
                    cMsgALine.xxChkBox_A2.setErrorInfo(1, NLBM0002E);
                }
            }
            return;
        }

        // Input check in Apply Info
        if (ZYPCommonFunc.hasValue(cMsg.schdCoordDt_BT)) {
            if (0 < slsDt.compareTo(cMsg.schdCoordDt_BT.getValue())) {
                // Past Date
                cMsg.schdCoordDt_BT.setErrorInfo(1, NLBM1231E, new String[]{"Scheduled Date"});
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd_BT)) {
            cMsg.schdCoordPsnNm_BT.clear();
            S21_PSNTMsg psn = new S21_PSNTMsg();
            psn.glblCmpyCd.setValue(glblCmpyCd);
            psn.psnCd.setValue(cMsg.schdCoordPsnCd_BT.getValue());

            psn = (S21_PSNTMsg) EZDTBLAccessor.findByKey(psn);
            if (psn == null) {
                // Not found
                cMsg.schdCoordPsnCd_BT.setErrorInfo(1, NLZM2278E, new String[]{"Coordinator"});
                return;
            }

            StringBuilder schdCoordPsnNm = new StringBuilder();
            schdCoordPsnNm.append(psn.psnFirstNm.getValue()).append(" ");
            schdCoordPsnNm.append(psn.psnMidNm.getValue()).append(" ");
            schdCoordPsnNm.append(psn.psnLastNm.getValue());
            cMsg.schdCoordPsnNm_BT.setValue(schdCoordPsnNm.toString());
        }

        if (ZYPCommonFunc.hasValue(cMsg.carrNm_BT)) {
            cMsg.carrCd_BT.clear();
            String carrCd = NLBL3120Query.getInstance().getCarrCd(cMsg.carrNm_BT.getValue());
            if (!ZYPCommonFunc.hasValue(carrCd)) {
                // Not found
                cMsg.shpgSvcLvlCd_BT.setErrorInfo(1, NLZM2278E, new String[]{"Assigned Carrier"});
                return;
            } else {
                cMsg.carrCd_BT.setValue(carrCd);
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_BT)
                && ZYPCommonFunc.hasValue(cMsg.carrNm_BT)) {

            SHPG_SVC_LVL_CARR_RELNTMsg shpgSvcLvlCarrReln = new SHPG_SVC_LVL_CARR_RELNTMsg();
            shpgSvcLvlCarrReln.setSQLID("001");
            shpgSvcLvlCarrReln.setConditionValue("glblCmpyCd01", glblCmpyCd);
            shpgSvcLvlCarrReln.setConditionValue("shpgSvcLvlCd01", cMsg.shpgSvcLvlCd_BT.getValue());
            shpgSvcLvlCarrReln.setConditionValue("carrCd01", cMsg.carrCd_BT.getValue());
            SHPG_SVC_LVL_CARR_RELNTMsgArray shpgSvcLvlCarrRelnList = (SHPG_SVC_LVL_CARR_RELNTMsgArray) EZDTBLAccessor.findByCondition(shpgSvcLvlCarrReln);
            if (shpgSvcLvlCarrRelnList.length() == 0) {
                // Not found
                cMsg.shpgSvcLvlCd_BT.setErrorInfo(1, NLBM1308E
                        , new String[]{"Shipping Service Level", "Assigned Carrier"});
                cMsg.carrNm_BT.setErrorInfo(1, NLBM1308E
                        , new String[]{"Shipping Service Level", "Assigned Carrier"});
                return;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd_BT)) {

            SCHD_COORD_ASGTMsg schdCoordAsg = new SCHD_COORD_ASGTMsg();
            schdCoordAsg.setSQLID("003");
            schdCoordAsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            schdCoordAsg.setConditionValue("schdCoordPsnCd01", cMsg.schdCoordPsnCd_BT.getValue());
            schdCoordAsg.setConditionValue("effFromDt01", slsDt);
            schdCoordAsg.setConditionValue("effThruDt01", slsDt);
            SCHD_COORD_ASGTMsgArray schdCoordAsgList = (SCHD_COORD_ASGTMsgArray) EZDTBLAccessor.findByCondition(schdCoordAsg);
            if (schdCoordAsgList.length() == 0) {
                // Not found
                cMsg.schdCoordPsnCd_BT.setErrorInfo(1, NLZM2278E, new String[]{"Coordinator"});
                return;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd)) {

            String userId = getContextUserInfo().getUserId();
            SCHD_COORD_ASG_RELNTMsg schdCoordAsgReln = new SCHD_COORD_ASG_RELNTMsg();
            schdCoordAsgReln.setSQLID("001");
            schdCoordAsgReln.setConditionValue("glblCmpyCd01", glblCmpyCd);
            schdCoordAsgReln.setConditionValue("rtlWhCd01", cMsg.rtlWhCd.getValue());
            schdCoordAsgReln.setConditionValue("mgrPsnCd01", userId);
            schdCoordAsgReln.setConditionValue("supvPsnCd01", userId);
            schdCoordAsgReln.setConditionValue("schdCoordPsnCd01", cMsg.schdCoordPsnCd.getValue());
            SCHD_COORD_ASG_RELNTMsgArray schdCoordAsgRelnList = (SCHD_COORD_ASG_RELNTMsgArray) EZDTBLAccessor.findByCondition(schdCoordAsgReln);
            if (schdCoordAsgRelnList.length() == 0) {
                // Not found
                cMsg.schdCoordPsnCd.setErrorInfo(1, NLBM1328E);
                return;
            }
        }

        //Apply Copy
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())) {

                if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd_BT)) {
                    ZYPEZDItemValueSetter.setValue(sMsgALine.schdCoordPsnCd_A1, cMsg.schdCoordPsnCd_BT);
                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxPsnFirstMidLastNm_A1, cMsg.schdCoordPsnNm_BT);
                }
                if (ZYPCommonFunc.hasValue(cMsg.schdCoordDt_BT)) {
                    ZYPEZDItemValueSetter.setValue(sMsgALine.schdPickUpDt_A1, cMsg.schdCoordDt_BT);
                }
                if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_BT)) {
                    ZYPEZDItemValueSetter.setValue(sMsgALine.shpgSvcLvlCd_A1, cMsg.shpgSvcLvlCd_BT);
                }
                if (ZYPCommonFunc.hasValue(cMsg.carrNm_BT)) {
                    ZYPEZDItemValueSetter.setValue(sMsgALine.locNm_A1, cMsg.carrNm_BT);
                    ZYPEZDItemValueSetter.setValue(sMsgALine.carrCd_A1, cMsg.carrCd_BT);
                }

                // Relation check for Coordinator and Warehouse, State.
                if (ZYPCommonFunc.hasValue(sMsgALine.schdCoordPsnCd_A1)
                        && ZYPCommonFunc.hasValue(sMsgALine.rtlWhCd_A1)
                        && ZYPCommonFunc.hasValue(sMsgALine.fromLocStCd_A1)) {
                    //QC#18993 MOD START
                    S21SsmEZDResult res = NLBL3120Query.getInstance().chkCoord(glblCmpyCd, sMsgALine, slsDt);
                    if (BigDecimal.ZERO.compareTo((BigDecimal) res.getResultObject()) == 0) {
                        sMsgALine.schdCoordPsnCd_A1.setErrorInfo(1, NLBM1308E, new String[]{"Coordinator, Warehouse", "State"});
                        hasErr = true;
                        if (firstErrIdx == -1) {
                            firstErrIdx = i;
                        }
                    }
                    //QC#18993 MOD START
                }
            }
        }
        if (hasErr) {
            int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndexForError(firstErrIdx, cMsg, sMsg);
            int fromNum = nextRecIdx;
            NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
            return;
        }

        int nextRecIdx = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int fromNum = nextRecIdx;
        NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
    }


    /**
     * doProcess_NLBL3120Scrn00_Search_CoordInfo
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_Search_CoordInfo(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        cMsg.schdCoordPsnNm.clear();
        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd)) {
            String psnNm = getPsnNm(cMsg.schdCoordPsnCd, "Coordinator");
            ZYPEZDItemValueSetter.setValue(cMsg.schdCoordPsnNm, psnNm);
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_Search_MgrInfo
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_Search_MgrInfo(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        cMsg.xxPsnFirstMidLastNm_MG.clear();
        if (ZYPCommonFunc.hasValue(cMsg.mgrPsnCd)) {
            String psnNm = getPsnNm(cMsg.mgrPsnCd, "Manager");
            ZYPEZDItemValueSetter.setValue(cMsg.xxPsnFirstMidLastNm_MG, psnNm);
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_Search_SupvInfo
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_Search_SupvInfo(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        cMsg.xxPsnFirstMidLastNm_SV.clear();
        if (ZYPCommonFunc.hasValue(cMsg.supvPsnCd)) {
            String psnNm = getPsnNm(cMsg.supvPsnCd, "Supervisor");
            ZYPEZDItemValueSetter.setValue(cMsg.xxPsnFirstMidLastNm_SV, psnNm);
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_Search_CoordInfo
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private String getPsnNm(EZDCStringItem psnCd, String msgPrm) {

        S21_PSNTMsg psn = new S21_PSNTMsg();
        psn.glblCmpyCd.setValue(getGlobalCompanyCode());
        psn.psnCd.setValue(psnCd.getValue());

        psn = (S21_PSNTMsg) EZDFastTBLAccessor.findByKey(psn);
        if (psn == null) {
            psnCd.setErrorInfo(1, NLZM2278E, new String[]{msgPrm});
            return null;
        }

        StringBuilder psnNm = new StringBuilder();
        psnNm.append(psn.psnFirstNm.getValue()).append(" ");
        // QC#19520 DEL START
        //psnNm.append(psn.psnMidNm.getValue()).append(" ");
        // QC#19520 DEL END
        psnNm.append(psn.psnLastNm.getValue());

        return psnNm.toString();
    }

    /**
     * doProcess_NLBL3120Scrn00_Search_SlsRepInfo
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_Search_SlsRepInfo(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        cMsg.xxPsnFirstMidLastNm_SR.clear();
        if (ZYPCommonFunc.hasValue(cMsg.slsRepOrSlsTeamTocCd)) {
            TOCTMsg toc = new TOCTMsg();
            toc.glblCmpyCd.setValue(getGlobalCompanyCode());
            toc.tocCd.setValue(cMsg.slsRepOrSlsTeamTocCd.getValue());
            toc = (TOCTMsg) EZDFastTBLAccessor.findByKey(toc);
            if (toc == null) {
                cMsg.slsRepOrSlsTeamTocCd.setErrorInfo(1, NLZM2278E, new String[]{"Sales Rep"});
                return;
            }
            cMsg.xxPsnFirstMidLastNm_SR.setValue(toc.tocNm.getValue());
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_Search_RtlWHInfo
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_Search_RtlWHInfo(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        //// Retail WH
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            String  glblCmpyCd = getGlobalCompanyCode();
            RTL_WHTMsg rtlWh = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, cMsg.rtlWhCd);
            rtlWh = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWh);
            if (rtlWh == null) {
                cMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[]{"Warehouse"});
                cMsg.rtlWhNm.clear();
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, rtlWh.rtlWhNm);

        } else {
            cMsg.rtlWhCd.setErrorInfo(1, ZZM9000E, new String[]{"Warehouse"});
            cMsg.rtlWhNm.clear();
        }

    }

    /**
     * doProcess_NLBL3120Scrn00_All_Collapse
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_All_Collapse(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        int eventLine = cMsg.xxNum_EV.getValueInt();
        if (eventLine >= 0) {
            collapse(cMsg, sMsg, cMsg.A.no(eventLine).xxNum_A1.getValueInt());
        } else {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    continue;
                }
                collapse(cMsg, sMsg, i);
            }
        }
        int fromNum = cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1);
        int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndex(fromNum, cMsg, sMsg);

        NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
    }

    /**
     * Collapse
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     * @param eventLine Event Line Number
     */
    private void collapse(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg, int eventLine) {
        // [-] --> [+]
        // Update Summary Line Flag
        String preTrxHdrNum = sMsg.A.no(eventLine).trxHdrNum_A1.getValue();
        boolean flg = false;
        for (int i = eventLine; i < sMsg.A.getValidCount(); i++) {
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);
            if (preTrxHdrNum.equals(sMsgALine.trxHdrNum_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxSmryLineFlg_A1, ZYPConstant.FLG_ON_Y);
                flg = true;
            } else if (flg) {
                break;
            }
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_All_Expand
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_All_Expand(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);
        int eventLine = cMsg.xxNum_EV.getValueInt();
        if (eventLine >= 0) {
            expand(cMsg, sMsg, cMsg.A.no(eventLine).xxNum_A1.getValueInt());
        } else {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxPgFlg_A1.getValue())) {
                    continue;
                }
                expand(cMsg, sMsg, i);
            }
        }
        int fromNum = cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1);
        int nextRecIdx = NLBL3120CommonLogic.getPageStartRowIndex(fromNum, cMsg, sMsg);

        NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
    }

    /**
     * Expand
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     * @param eventLine Event Line Number
     */
    private void expand(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg, int eventLine) {
        // [+] --> [-]
        // Update Summary Line Flag
        NLBL3120_ASMsg preSMsgALine = sMsg.A.no(eventLine);
        String preTrxHdrNum = preSMsgALine.trxHdrNum_A1.getValue();
        String preChkBox = preSMsgALine.xxChkBox_A1.getValue();
        boolean flg = false;
        for (int i = eventLine; i < sMsg.A.getValidCount(); i++) {
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);
            if (preTrxHdrNum.equals(sMsgALine.trxHdrNum_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxSmryLineFlg_A1, ZYPConstant.FLG_OFF_N);
                flg = true;
                if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.xxPgFlg_A1.getValue())) {
                    sMsgALine.xxChkBox_A2.setValue(preChkBox);
                }
            } else if (flg) {
                break;
            }
        }

    }

    /**
     * doProcess_NLBL3120Scrn00_Release_SO
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_Release_SO(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        if (!"E".equals(cMsg.getMessageKind())
                && !"W".equals(cMsg.getMessageKind())) {
            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();
            searchScheduling(cMsg, sMsg);
            if (msgInfo != null) {
                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }

    }

    /**
     * doProcess_NLBL3120_OnChangeSavedSearchOption
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120_OnChangeSavedSearchOption(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            NLBL3120CommonLogic.callNszc0330forSearchOption(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /** QC#16924# 01/26/2017 M.Naito Start **/
    /**
     * doProcess_NLBL3120Scrn00_OnChange_ChkBoxTrxHdrNum
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_OnChange_ChkBoxTrxHdrNum(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();
        String val = "";
        String trxHdrNum = "";

        for (int i = eventLine; i < cMsg.A.getValidCount(); i++) {

            NLBL3120_ACMsg cMsgALine = cMsg.A.no(i);

            if (eventLine == i) {

                val = cMsgALine.xxChkBox_A1.getValue();

                if (ZYPCommonFunc.hasValue(cMsgALine.soNum_A1.getValue())) {
                    // If check xxChkBox_A1 of detail line
                    ZYPEZDItemValueSetter.setValue(cMsgALine.xxChkBox_A1, "");
                } else {
                    trxHdrNum = cMsgALine.trxHdrNum_A1.getValue();
                }

                continue;
            }

            if (trxHdrNum.equals(cMsgALine.trxHdrNum_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(cMsgALine.xxChkBox_A2, val);

            } else {

                break;
            }
        }

        int pageNum = (cMsg.xxPageShowFromNum_A.getValueInt() - 1) + eventLine;

        for (int i = pageNum; i < sMsg.A.getValidCount(); i++) {

            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

            if (trxHdrNum.equals(sMsgALine.trxHdrNum_A1.getValue())) {

                ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A1, val);
                ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, val);

            } else {

                break;
            }
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_OnChange_ChkBoxSoNum
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_OnChange_ChkBoxSoNum(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();
        String val = cMsg.A.no(eventLine).xxChkBox_A2.getValue();
        int sLineNum = (cMsg.xxPageShowFromNum_A.getValueInt() - 1) + eventLine;
        int soNum = sLineNum;

        // ON --> OFF
        String trxHdrNum = sMsg.A.no(sLineNum).trxHdrNum_A1.getValue();

        if (!ZYPConstant.FLG_ON_Y.equals(val)) {

            // sMsg BackLine
            for (int i = sLineNum; i >= 0; i--) {

                NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

                if (i == sLineNum) {
                    sMsg.A.no(i).xxChkBox_A2.clear();
                }

                if (trxHdrNum.equals(sMsgALine.trxHdrNum_A1.getValue())) {
                    sMsg.A.no(i).xxChkBox_A1.clear();
                    soNum = i;

                } else {
                    soNum = i + 1;
                    break;
                }
            }

            // Ahead Line
            trxHdrNum = sMsg.A.no(sLineNum).trxHdrNum_A1.getValue();

            for (int i = sLineNum; i < sMsg.A.getValidCount(); i++) {

                NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

                if (trxHdrNum.equals(sMsgALine.trxHdrNum_A1.getValue())) {
                    sMsgALine.xxChkBox_A1.clear();

                } else {
                    break;
                }
            }

            // cMsg
            if (soNum - (cMsg.xxPageShowFromNum_A.getValueInt() - 1) >= 0) {
                int cMsgHdrRcrd = soNum - (cMsg.xxPageShowFromNum_A.getValueInt() - 1);
                cMsg.A.no(cMsgHdrRcrd).xxChkBox_A1.clear();
            }

            return;
        }

        // OFF --> ON
        int offCount = 0;
        soNum = sLineNum;

        if (ZYPConstant.FLG_ON_Y.equals(val)) {

            // Back Line
            trxHdrNum = sMsg.A.no(sLineNum).trxHdrNum_A1.getValue();

            for (int i = sLineNum; i >= 0; i--) {

                NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

                if (sLineNum == i) {

                    if (!ZYPCommonFunc.hasValue(sMsgALine.soNum_A1.getValue())) {
                        // If check xxChkBox_A2 of header line
                        ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, "");
                    } else {
                        ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, ZYPConstant.CHKBOX_ON_Y);
                    }
                    continue;
                }

                if (trxHdrNum.equals(sMsgALine.trxHdrNum_A1.getValue())) {
                    if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.soNum_A1.getValue())) {
                        offCount++;
                    }

                    soNum = i;

                } else {
                    soNum = i + 1;
                    break;
                }
            }

            // Ahead Line
            trxHdrNum = sMsg.A.no(sLineNum).trxHdrNum_A1.getValue();

            for (int i = sLineNum; i < sMsg.A.getValidCount(); i++) {

                NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

                if (i == sLineNum) {
                    continue;
                }

                if (trxHdrNum.equals(sMsgALine.trxHdrNum_A1.getValue())) {
                    if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())) {
                        offCount++;
                    }

                } else {
                    break;
                }
            }

            if (offCount == 0) {

                for (int i = soNum; i < sMsg.A.getValidCount(); i++) {

                    NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);

                    if (trxHdrNum.equals(sMsgALine.trxHdrNum_A1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);

                    } else {
                        break;
                    }
                }
            }

            // Set xxPageShowFromNum
            if (offCount == 0) {

                if (soNum - (cMsg.xxPageShowFromNum_A.getValueInt() - 1) >= 0) {
                    int cMsgHdrRcrd = soNum - (cMsg.xxPageShowFromNum_A.getValueInt() - 1);
                    cMsg.A.no(cMsgHdrRcrd).xxChkBox_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
                }
            }

            return;
        }
    }
    /** QC#16924# 01/26/2017 M.Naito End **/

    /**
     * doProcess_NLBL3120Scrn00_CMN_Download
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_CMN_Download(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        // Master Check
        if (!checkInputHeader(cMsg)) {
            return;
        }


        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLBL3120Query.getInstance().getClass());

            //create csv file, parameters
            Map<String, Object> ssMParam = null;
            String ssmId = "searchSchd";
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            ssMParam =  NLBL3120Query.getInstance().createSearchCond(cMsg, getGlobalCompanyCode());
            ssMParam.put("rownum", LIMIT_DL_ROWNUM + 1);

            ps = ssmLLClient.createPreparedStatement(ssmId, ssMParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_TBLColumnSort
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_TBLColumnSort(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        NLBL3120CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        String sortItemNm = cMsg.xxSortItemNm_A.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_A.getValue();

        // execute column sort function
        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add("trxHdrNum_A1", sortOrdBy);
        sortKey.add("xxPgFlg_A1", "DESC");
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3120_ASMsg sMsgALine = sMsg.A.no(i);
            sMsgALine.xxNum_A1.setValue(i);
        }

        int nextRecIdx = 0;
        int fromNum = nextRecIdx;
        NLBL3120CommonLogic.pagenation(cMsg, sMsg, nextRecIdx, fromNum);
    }

    /**
     * doProcess_NLBL3120_NLBL3100
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120_NLBL3100(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        int selectIdx = cMsg.xxNum_EV.getValueInt();
        NLBL3120_ACMsg cMsgALine = cMsg.A.no(selectIdx);

        if (!NLBL3120CommonLogic.isAssignCoordPsn(
                getGlobalCompanyCode()
                , cMsgALine.rtlWhCd_A1.getValue()
                , getContextUserInfo().getUserId()
                , cMsgALine.schdCoordPsnCd_A1.getValue())) {
            cMsgALine.schdCoordPsnCd_A1.setErrorInfo(1, NLBM1328E);
        }
    }

    /**
     * doProcess_NLBL3120Scrn00_CMN_Submit
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void doProcess_NLBL3120Scrn00_CMN_Submit(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        if (!"E".equals(cMsg.getMessageKind())
                && !"W".equals(cMsg.getMessageKind())
                && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {
            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();
            searchScheduling(cMsg, sMsg);
            if (msgInfo != null) {
                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

   /**
    * clearAll
    * @param cMsg NLBL3120CMsg
    * @param sMsg NLBL3120SMsg
    */
    private void clearAll(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        // Clear Table
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();

        cMsg.srchOptPk_S.clear();
        cMsg.srchOptNm_S.clear();
        cMsg.trxHdrNum.clear();
        cMsg.dsOrdCatgCd.clear();
        cMsg.dsOrdCatgCd_P.clear();
        cMsg.dsOrdCatgDescTxt_P.clear();
        cMsg.dsOrdTpCd.clear();
        cMsg.dsOrdTpCd_P.clear();
        cMsg.dsOrdTpDescTxt_P.clear();
        cMsg.t_MdlNm.clear();
        cMsg.svcConfigMstrPk.clear();
        cMsg.soNum.clear();
        cMsg.dsSoLineStsCd.clear();
        cMsg.dsSoLineStsCd_P.clear();
        cMsg.dsSoLineStsDescTxt_P.clear();
        cMsg.schdCoordStsCd.clear();
        cMsg.schdCoordStsCd_P.clear();
        cMsg.schdCoordStsDescTxt_P.clear();
        cMsg.rwsNum.clear();
        cMsg.rwsStsCd.clear();
        cMsg.rwsStsCd_P.clear();
        cMsg.rwsStsDescTxt_P.clear();
        cMsg.schdCoordStsCd.clear();
        cMsg.schdCoordStsCd_P.clear();
        cMsg.schdCoordStsDescTxt_P.clear();
        cMsg.rtlWhCd.clear();
        cMsg.rtlWhNm.clear();
        cMsg.schdCoordPsnCd.clear();
        cMsg.schdCoordPsnNm.clear();
        cMsg.supvPsnCd.clear();
        cMsg.xxPsnFirstMidLastNm_SV.clear();
        cMsg.mgrPsnCd.clear();
        cMsg.xxPsnFirstMidLastNm_MG.clear();
        cMsg.slsRepOrSlsTeamTocCd.clear();
        cMsg.xxPsnFirstMidLastNm_SR.clear();
        cMsg.rddDt_FR.clear();
        cMsg.rddDt_TO.clear();
        cMsg.schdCoordDt_FR.clear();
        cMsg.schdCoordDt_TO.clear();
        cMsg.xxChkBox_DS.clear();
        cMsg.xxChkBox_DR.clear();
        cMsg.xxChkBox_CA.clear();
        cMsg.xxChkBox_CN.clear();

        cMsg.xxPageShowFromNum_A.clear();
        cMsg.xxPageShowToNum_A.clear();
        cMsg.xxPageShowOfNum_A.clear();
        cMsg.xxPageShowCurNum_A.clear();
        cMsg.xxPageShowTotNum_A.clear();
        cMsg.xxSortTblNm_A.clear();
        cMsg.xxSortItemNm_A.clear();
        cMsg.xxSortOrdByTxt_A.clear();

        cMsg.schdCoordPsnCd_BT.clear();
        cMsg.schdCoordPsnNm_BT.clear();
        cMsg.schdCoordDt_BT.clear();
        cMsg.shpgSvcLvlCd_BT.clear();
        cMsg.shpgSvcLvlCd_BP.clear();
        cMsg.shpgSvcLvlDescTxt_BP.clear();
        cMsg.carrCd_BT.clear();
        cMsg.carrNm_BT.clear();
    }

    private void search(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        searchScheduling(cMsg, sMsg);
        cMsg.clearErrorInfo();
        if ("E".equals(cMsg.getMessageKind())) {
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
        }
    }

    /**
     * searchScheduling
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void searchScheduling(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {

        S21SsmEZDResult result = null;
        // QC#27425
        List<Map<String, Object>> schdCoordStsList = null;

        result = NLBL3120Query.getInstance().searchSchd(cMsg, sMsg);

        if (result.isCodeNormal()) {
            int queryResCnt = NLBL3120CommonLogic.setSearchSchdResultToGlblMsg(result, sMsg);
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            // QC#19137 ADD START
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).schdOpenFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdCoordStsCd_AL.no(0), sMsg.A.no(i).schdCoordStsCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdCoordStsDescTxt_AL.no(0), sMsg.A.no(i).schdCoordStsDescTxt_A1);
                } else {
                    // QC#27425
                    if (schdCoordStsList == null) {
                        schdCoordStsList = createSchdCoordStsPullDown(sMsg.A.no(i));
                    }

                    if (schdCoordStsList != null) {
                        for (int c = 0; c < schdCoordStsList.size(); c++) {
                            Map<String, Object> resultMap = (Map<String, Object>) schdCoordStsList.get(c);
                            if (c >= sMsg.A.no(i).schdCoordStsCd_AL.length()) {
                                break;
                            }
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdCoordStsCd_AL.no(c), (String) resultMap.get("SCHD_COORD_STS_CD"));
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdCoordStsDescTxt_AL.no(c), (String) resultMap.get("SCHD_COORD_STS_DESC_TXT"));
                        }
                    }
                }
            }
            // QC#19137 ADD END

            //Copy from SMsg to cMsg

            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
            // Set page number
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(queryResCnt);
            cMsg.setMessageInfo(ZZZM9003I, new String[]{"Search"});

        } else {
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
            cMsg.setMessageInfo(NZZM0000E);
        }
    }


    private boolean checkInputHeader(NLBL3120CMsg cMsg) {

        boolean rc = true;
        String  glblCmpyCd = getGlobalCompanyCode();

        //// Retail WH
        cMsg.rtlWhNm.clear();
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            RTL_WHTMsg rtlWh = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, cMsg.rtlWhCd);
            rtlWh = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWh);
            if (rtlWh == null) {
                cMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[]{"Warehouse"});
                rc = false;
            } else {
                cMsg.rtlWhNm.setValue(rtlWh.rtlWhNm.getValue());
            }
        }

        //// Coordinator
        cMsg.schdCoordPsnNm.clear();
        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd)) {
            String psnNm = getPsnNm(cMsg.schdCoordPsnCd, "Coordinator");
            if (psnNm == null) {
                rc = false;
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.schdCoordPsnNm, psnNm);
            }
        }

        //// Supervisor
        cMsg.xxPsnFirstMidLastNm_SV.clear();
        if (ZYPCommonFunc.hasValue(cMsg.supvPsnCd)) {
            String psnNm = getPsnNm(cMsg.supvPsnCd, "Supervisor");
            if (psnNm == null) {
                rc = false;
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.xxPsnFirstMidLastNm_SV, psnNm);
            }
        }

        //// Manager
        cMsg.xxPsnFirstMidLastNm_MG.clear();
        if (ZYPCommonFunc.hasValue(cMsg.mgrPsnCd)) {
            String psnNm = getPsnNm(cMsg.mgrPsnCd, "Manager");
            if (psnNm == null) {
                rc = false;
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.xxPsnFirstMidLastNm_MG, psnNm);
            }
        }

        //// Sales Rep
        cMsg.xxPsnFirstMidLastNm_SR.clear();
        if (ZYPCommonFunc.hasValue(cMsg.slsRepOrSlsTeamTocCd)) {
            TOCTMsg toc = new TOCTMsg();
            ZYPEZDItemValueSetter.setValue(toc.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(toc.tocCd, cMsg.slsRepOrSlsTeamTocCd);
            toc = (TOCTMsg) EZDFastTBLAccessor.findByKey(toc);
            if (toc == null) {
                cMsg.slsRepOrSlsTeamTocCd.setErrorInfo(1, NLZM2278E, new String[]{"Sales Rep"});
                rc = false;
            } else {
                cMsg.xxPsnFirstMidLastNm_SR.setValue(toc.tocNm.getValue());
            }
        }

        ////Model
        if (ZYPCommonFunc.hasValue(cMsg.t_MdlNm)) {
            MDL_NMTMsg mdlNm = new MDL_NMTMsg();
            mdlNm.setSQLID("001");
            mdlNm.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlNm.setConditionValue("t_MdlNm01", cMsg.t_MdlNm.getValue());
            MDL_NMTMsgArray mdlNmList = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlNm);
            if (mdlNmList == null || mdlNmList.getValidCount() == 0) {
                cMsg.t_MdlNm.setErrorInfo(1, NLZM2278E, new String[]{"Model"});
                rc = false;
            }
        }

        // Search Condition
        int srchCond = NLBL3120CommonLogic.selectSearchCondition(cMsg);
        if (srchCond == 0) {
            // Error
            ZYPTableUtil.clear(cMsg.A);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
            cMsg.xxPageShowCurNum_A.clear();
            cMsg.xxPageShowTotNum_A.clear();

            cMsg.setMessageInfo(NZZM0000E);
            rc = false;
        }

        return rc;
    }

    private static void getColData(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        ZYPGUITableColumn.getColData(cMsg, sMsg);
    }

    private void writeCsvFile(NLBL3120CMsg cMsg, ResultSet rs) throws SQLException {

        NLBL3120F00FMsg fMsg = new NLBL3120F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        //write header
        csvOutFile.writeHeader(CSV_HDR);

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        String trxHdrNum = "";

        //write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }
            fMsg.clear();

            if (!trxHdrNum.equals((String) rs.getString("TRX_HDR_NUM"))) {

                ZYPEZDItemValueSetter.setValue(fMsg.trxHdrNum, rs.getString("TRX_HDR_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdCatgDescTxt, rs.getString("DS_ORD_CATG_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdTpDescTxt, rs.getString("DS_ORD_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxExstFlg, rs.getString("EC_BO_EXST_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk, rs.getBigDecimal("CONFIG_CNT"));

                trxHdrNum = (String) rs.getString("TRX_HDR_NUM");
                rs.previous();
                csvOutFile.write();
                continue;
            }

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.soNum, rs.getString("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rwsNum, rs.getString("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.wmsDropFlg, rs.getString("WMS_DROP_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk, rs.getBigDecimal("SHIP_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, rs.getString("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.pickSvcConfigMstrPk, rs.getBigDecimal("PICK_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.schdCoordPsnCd, rs.getString("SCHD_COORD_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxPsnFirstMidLastNm, rs.getString("SCHD_COORD_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.fromLocCd, rs.getString("SHIP_TO_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("SHIP_TO_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.fromLocAddr, rs.getString("SHIP_TO_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(fMsg.fromLocCtyAddr, rs.getString("SHIP_TO_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.fromLocStCd, rs.getString("SHIP_TO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.schdCoordStsDescTxt, rs.getString("SCHD_COORD_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rwsStsDescTxt, rs.getString("SCHD_TRX_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlCd, rs.getString("SHPG_SVC_LVL_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.carrCd, rs.getString("CARR_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.locNm, rs.getString("CARR_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt, rs.getString("TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.carrAcctNum, rs.getString("CARR_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.tocNm, rs.getString("TOC_NM"));

            fMsg.xxTsDsp19Txt_RQ.setValue(formatDt(rs.getString("RDD_DT")));
            fMsg.xxTsDsp19Txt_SC.setValue(formatDt(rs.getString("SCHD_COORD_DT")));
            fMsg.xxTsDsp19Txt_SH.setValue(formatDt(rs.getString("SCHD_TRX_CLO_DT_TM_TS")));
            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * formatDt
     * @param dt date
     * @return after format date
     */
    public String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";

        } else if (dt.length() > 8) {
            dt = dt.substring(0, 8);
        }

        //QC#17825 Start
        // return ZYPDateUtil.formatDisp8ToEzd(dt, true);
        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
        //QC#17825 End
    }

    /**
     * createPulldown
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     */
    private void createPulldown(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        // Setup select box
        NLBL3120CommonLogic.createSavedSearchOptionsPullDown(cMsg, getContextUserInfo().getUserId());

        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(DS_ORD_CATG.class, cMsg.dsOrdCatgCd_P, cMsg.dsOrdCatgDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(DS_ORD_TP.class, cMsg.dsOrdTpCd_P, cMsg.dsOrdTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(DS_SO_LINE_STS.class, cMsg.dsSoLineStsCd_P, cMsg.dsSoLineStsDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(RWS_STS.class, cMsg.rwsStsCd_P, cMsg.rwsStsDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(SCHD_COORD_STS.class, cMsg.schdCoordStsCd_P, cMsg.schdCoordStsDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_BP, cMsg.shpgSvcLvlDescTxt_BP);
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_AP, cMsg.shpgSvcLvlDescTxt_AP);
    }

    // QC#19137 ADD START. Mod QC#27425
    /**
     * create Line SchdCoordStsPullDown
     * @param sMsgALine NLBL3120_ASMsg
     */
    private List<Map<String, Object>> createSchdCoordStsPullDown(NLBL3120_ASMsg sMsgALine) {
        String  glblCmpyCd = getGlobalCompanyCode();

        S21SsmEZDResult ssmResult = NLBL3120Query.getInstance().getSchdCoordStsList(glblCmpyCd, sMsgALine);

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            return resultList;
        }

        return null;
    }
    // QC#19137 ADD END


}
