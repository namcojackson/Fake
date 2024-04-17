/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3000;

import static business.blap.NMAL3000.constant.NMAL3000Constant.CHK_A;
import static business.blap.NMAL3000.constant.NMAL3000Constant.CSV_FILE_NAME;
import static business.blap.NMAL3000.constant.NMAL3000Constant.SELL_TO_CUST_CD01;
import static business.blap.NMAL3000.constant.NMAL3000Constant.GLBCMPY_CD01;
import static business.blap.NMAL3000.constant.NMAL3000Constant.INSERT_FLG;
import static business.blap.NMAL3000.constant.NMAL3000Constant.LIMIT_DL_ROWNUM;
import static business.blap.NMAL3000.constant.NMAL3000Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL3000.constant.NMAL3000Constant.MAX_ROW;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM8054E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM8187E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NMAM8203W;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NZZM0000E;
import static business.blap.NMAL3000.constant.NMAL3000Constant.NZZM0001W;
import static business.blap.NMAL3000.constant.NMAL3000Constant.RECORD_NOTFOUND;
import static business.blap.NMAL3000.constant.NMAL3000Constant.YYYYMMDD_LENGTH;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL3000.common.NMAL3000CommonLogic;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.file.NMAL3000F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_DLR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

/**
 *<pre>
 * NMAL3000BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/09   SRAA            Y.Chen          Update          QC#5170, QC#5169
 * 2018/12/19   Fujitsu         H.Kumagai       Update          QC#29661
 *</pre>
 */
public class NMAL3000BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL3000CMsg bizMsg = (NMAL3000CMsg) cMsg;
            NMAL3000SMsg glblMsg = (NMAL3000SMsg) sMsg;

            if ("NMAL3000_INIT".equals(screenAplID)) {
                doProcess_NMAL3000_INIT(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_Copy_DelrAM".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_Copy_DelrAM(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_DeleteRow_DelrAM".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_DeleteRow_DelrAM(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_InsertRow_DelrAM".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_InsertRow_DelrAM(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_OnChangeSavedSearchOption(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_SaveSearch(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_Search(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_Search_Account".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_Search_Account(bizMsg, glblMsg);

            } else if ("NMAL3000Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL3000Scrn00_TBLColumnSort(bizMsg, glblMsg);

            }  else if ("NMAL3000Scrn00_CMN_Clear".equals(screenAplID)) {

                doProcess_NMAL3000Scrn00_CMN_Clear(bizMsg, glblMsg);

            // 2018/12/19 Add Start QC#29661
            }  else if ("NMAL3000Scrn00_Select_All".equals(screenAplID)) {

                doProcess_NMAL3000Scrn00_Select_All(bizMsg, glblMsg);

            }  else if ("NMAL3000Scrn00_UnSelect_All".equals(screenAplID)) {

                doProcess_NMAL3000Scrn00_UnSelect_All(bizMsg, glblMsg);
            // 2018/12/19 Add End QC#29661

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
    private void  doProcess_NMAL3000Scrn00_CMN_Clear(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {
        doProcess_NMAL3000_INIT(bizMsg, glblMsg);
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000_INIT(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPCodeDataUtil.createPulldownList(DS_ACCT_DLR.class, bizMsg.dsAcctDlrCd_L, bizMsg.dsAcctDlrDescTxt_L);
        NMAL3000CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());

    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_CMN_Download(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL3000Query.getInstance().getClass());
            //create csv file, parameters
            Map<String, Object> ssMParam = null;
            String ssmId = "getDlrArzList";
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");
            ssMParam =  NMAL3000Query.getInstance().createSearchCond(bizMsg, getGlobalCompanyCode());
            ps = ssmLLClient.createPreparedStatement(ssmId, ssMParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(bizMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    private void writeCsvFile(NMAL3000CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL3000F00FMsg fMsg = new NMAL3000F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        //write header
        csvOutFile.writeHeader(new String[]{
              "Account Number"
              , "Dealer Code"
              , "Account Name"
              , "Primary Location#"
              , "Primary Address"
              , "City"
              , "State"
              , "Zip"
              , "Marketing Model"
              , "Sales"
              , "Service"
              , "Effective Date From"
              , "Effective Date To"
              , "Update By"
              , "Date Updated"});

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        do {

            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                csvOutFile.close();
                break;
            }

            fMsg.clear();
            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctCustNum_A, rs.getString("ACCOUNT_NUMBER"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctDlrCd_A, rs.getString("ACCOUNT_DEALER_CODE"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A, rs.getString("ACCOUNT_NAME"));
            ZYPEZDItemValueSetter.setValue(fMsg.locNum_A, rs.getString("PRIMARY_LOCATION"));
            // QC#5170
            // ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr_A, rs.getString("PRIMARY_ADDRESS"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_A, rs.getString("PRIMARY_ADDRESS"));
            ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr_A, rs.getString("CITY"));
            ZYPEZDItemValueSetter.setValue(fMsg.stCd_A, rs.getString("STATE"));
            ZYPEZDItemValueSetter.setValue(fMsg.postCd_A, rs.getString("ZIP"));
            ZYPEZDItemValueSetter.setValue(fMsg.mktMdlCd_A, rs.getString("MODEL"));
            ZYPEZDItemValueSetter.setValue(fMsg.slsAuthFlg_A, rs.getString("SALES"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcAuthFlg_A, rs.getString("SERVICE"));
            fMsg.xxTsDsp19Txt_RQ.setValue(formatDt(rs.getString("EFFECTIVE_DATE_FROM")));
            fMsg.xxTsDsp19Txt_SC.setValue(formatDt(rs.getString("EFFECTIVE_DATE_TO")));
            ZYPEZDItemValueSetter.setValue(fMsg.upldUserId_A, rs.getString("UPDATE_BY"));
            fMsg.xxTsDsp19Txt_SH.setValue(formatDt(rs.getString("DATE_UPDATE")));
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

        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }
        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }


    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_CMN_Reset(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {
        doProcess_NMAL3000_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_CMN_Submit(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * Copy_DelrAM Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_Copy_DelrAM(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        NMAL3000CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(glblMsg.A, CHK_A, ZYPConstant.FLG_ON_Y);

        if (selectRows.size() == RECORD_NOTFOUND) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        if (glblMsg.A.getValidCount() + selectRows.size() >= MAX_ROW) {
            bizMsg.setMessageInfo(NMAM8187E, new String[] {"Details", String.valueOf(MAX_ROW) });
            return;
        }

        for (int selectidx : selectRows) {

            int idx = glblMsg.A.getValidCount();

            //Account Number
            if (ZYPCommonFunc.hasValue(bizMsg.dsAcctCustNum_CO)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).dsAcctCustNum_A, bizMsg.dsAcctCustNum_CO);
            } else {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).dsAcctCustNum_A, glblMsg.A.no(selectidx).dsAcctCustNum_A);
            }


            SELL_TO_CUSTTMsgArray tmsgArray = getAccountNumInfo(bizMsg, glblMsg.A.no(idx).dsAcctCustNum_A.getValue());
            SELL_TO_CUSTTMsg tmsg = null;
            if (tmsgArray.length() > RECORD_NOTFOUND) {
                tmsg = (SELL_TO_CUSTTMsg) tmsgArray.get(0);
            }

            //dealer
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(selectidx).dsAcctDlrCd_A)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).dsAcctDlrCd_A , glblMsg.A.no(selectidx).dsAcctDlrCd_A);
            } else {
                if (tmsg != null) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).dsAcctDlrCd_A , tmsg.dsAcctDlrCd);
                }
            }

            //Account NM
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(selectidx).dsAcctNm_A)) {

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).dsAcctNm_A , glblMsg.A.no(selectidx).dsAcctNm_A);
            } else {
                if (tmsg != null) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).dsAcctNm_A , tmsg.dsAcctNm);
                }
            }

            //Primary Location#
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(selectidx).locNum_A)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).locNum_A , glblMsg.A.no(selectidx).locNum_A);
            } else {
                if (tmsg != null) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).locNum_A , tmsg.locNum);
                }
            }

            //Primary Address
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(selectidx).xxAllLineAddr_A)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).xxAllLineAddr_A , glblMsg.A.no(selectidx).xxAllLineAddr_A);
            } else {
                if (tmsg != null) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).xxAllLineAddr_A , tmsg.firstLineAddr);
                }
            }

            //City
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(selectidx).ctyAddr_A)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).ctyAddr_A , glblMsg.A.no(selectidx).ctyAddr_A);
            } else {
                if (tmsg != null) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).ctyAddr_A , tmsg.ctyAddr);
                }
            }

            //state
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(selectidx).stCd_A)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).stCd_A , glblMsg.A.no(selectidx).stCd_A);
            } else {
                if (tmsg != null) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).stCd_A , tmsg.stCd);
                }
            }

            //Zip
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(selectidx).postCd_A)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).postCd_A , glblMsg.A.no(selectidx).postCd_A);
            } else {
                if (tmsg != null) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).postCd_A , tmsg.postCd);
                }
            }

            //Model
            if (ZYPCommonFunc.hasValue(bizMsg.mktMdlCd_CO)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).mktMdlCd_A , bizMsg.mktMdlCd_CO);
            } else {

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).mktMdlCd_A, glblMsg.A.no(selectidx).mktMdlCd_A);
            }

            //Sales
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).slsAuthFlg_A , glblMsg.A.no(selectidx).slsAuthFlg_A);

            //Service
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).svcAuthFlg_A , glblMsg.A.no(selectidx).svcAuthFlg_A);

            //Effective Date from
            if (ZYPCommonFunc.hasValue(bizMsg.effFromDt_CO)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).effFromDt_A , bizMsg.effFromDt_CO);
            } else {
                String slsDt = ZYPDateUtil.getSalesDate();
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).effFromDt_A, slsDt);
            }

            //Update By
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).upldUserId_A , glblMsg.A.no(selectidx).upldUserId_A);

            //Date update
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).upldDt_A , glblMsg.A.no(selectidx).upldDt_A);

            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctCustNum_CO) && !ZYPCommonFunc.hasValue(bizMsg.mktMdlCd_CO)) {

                if (ZYPCommonFunc.hasValue(bizMsg.effFromDt_CO)) {
                    String effDateto   = getEffDateTo(bizMsg.effFromDt_CO.getValue());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(selectidx).effThruDt_A, effDateto);

                } else {
                    String slsDt = ZYPDateUtil.getSalesDate();
                    String effDateto   = getEffDateTo(slsDt);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(selectidx).effThruDt_A, effDateto);
                }

                S21UserInfo userInfo = getContextUserInfo();
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(selectidx).upldUserId_A , userInfo.getUserId());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(selectidx).upldDt_A , ZYPDateUtil.getSalesDate());
            }
            glblMsg.A.setValidCount(idx + 1);
            glblMsg.A.no(idx).xxRowId_A.setValue(INSERT_FLG);
            glblMsg.A.no(selectidx).xxChkBox_A.setValue(ZYPConstant.FLG_OFF_N);

        }

        int pageIndex = glblMsg.A.getValidCount() - 1;
        NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, pageIndex);
    }

    private String getEffDateTo(String date) {
        return ZYPDateUtil.addDays(date, -1);
    }

    /**
     * DeleteRow_DelrAM Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_DeleteRow_DelrAM(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {
        //doNothing
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_DeleteSearch(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {
        return;
    }

    /**
     * InsertRow_DelrAM Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_InsertRow_DelrAM(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        NMAL3000CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        int nextIdx = glblMsg.A.getValidCount();
        glblMsg.A.setValidCount(nextIdx + 1);
        glblMsg.A.no(nextIdx).xxRowId_A.setValue(INSERT_FLG);
        final String slsDt = ZYPDateUtil.getSalesDate();
        glblMsg.A.no(nextIdx).effFromDt_A.setValue(slsDt);
        NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, nextIdx);
    }

    /**
     * OnChangeSavedSearchOption Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_OnChangeSavedSearchOption(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NMAL3000CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_PageJump(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        NMAL3000CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        int page = (bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1;
        NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, page);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_PageNext(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        NMAL3000CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        int page = bizMsg.xxPageShowToNum.getValueInt() + 1;
        NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, page);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_PagePrev(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        NMAL3000CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        int page = bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length();
        NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, page);
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_SaveSearch(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {
        //donothing
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_Search(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        // search
        search(bizMsg, glblMsg);
    }

    /**
     * Search_Account Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_Search_Account(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        SELL_TO_CUSTTMsgArray tmsgArray = getAccountNumInfo(bizMsg, bizMsg.A.no(bizMsg.xxRowNum.getValueInt()).dsAcctCustNum_A.getValue());

        if (tmsgArray.length() == RECORD_NOTFOUND) {
            bizMsg.setMessageInfo(NMAM8203W , new String[]{"Accout related Information"});
            return;
        }
        SELL_TO_CUSTTMsg tmsg = (SELL_TO_CUSTTMsg) tmsgArray.get(0);
        int rowNum = bizMsg.xxRowNum.getValueInt();
        StringBuilder sb = new StringBuilder();
        sb.append(tmsg.firstLineAddr.getValue());
        sb.append(" ");
        sb.append(tmsg.scdLineAddr.getValue());
        sb.append(" ");
        sb.append(tmsg.thirdLineAddr.getValue());
        sb.append(" ");
        sb.append(tmsg.frthLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(rowNum).dsAcctDlrCd_A, tmsg.dsAcctDlrCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(rowNum).dsAcctNm_A, tmsg.dsAcctNm.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(rowNum).locNum_A, tmsg.locNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(rowNum).ctyAddr_A, tmsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(rowNum).stCd_A, tmsg.stCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(rowNum).postCd_A, tmsg.postCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(rowNum).xxAllLineAddr_A, sb.toString());
    }


    private SELL_TO_CUSTTMsgArray getAccountNumInfo(NMAL3000CMsg bizMsg , String acctCustNum) {
        SELL_TO_CUSTTMsg tmsg = new SELL_TO_CUSTTMsg();
        SELL_TO_CUSTTMsgArray tmsgArray = new SELL_TO_CUSTTMsgArray();
        tmsg.setSQLID("001");
        tmsg.setConditionValue(SELL_TO_CUST_CD01, acctCustNum);
        tmsg.setConditionValue(GLBCMPY_CD01, getGlobalCompanyCode());
        tmsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
        return tmsgArray;
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL3000Scrn00_TBLColumnSort(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        NMAL3000CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        String sortItemNm = bizMsg.xxSortItemNm_A.getValue();
        String sortOrdBy  = bizMsg.xxSortOrdByTxt_A.getValue();
        // execute column sort function
        S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());
        NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, 1);
    }


    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);

        S21SsmEZDResult ssmResult = NMAL3000Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            bizMsg.setCommitSMsg(true);
            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            // Back up
            EZDMsg.copy(glblMsg.A, "A", glblMsg.B, "B");
            NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, 1);
        }
    }

     // 2018/12/19 Add Start QC#29661
    /**
     * SelectAll
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void  doProcess_NMAL3000Scrn00_Select_All(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {
        NMAL3000CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NMAL3000CommonLogic.selectUnselect(glblMsg, ZYPConstant.FLG_ON_Y);
        NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, 1);
    }

    // 2018/12/19 Add Start QC#29661
    /**
     * SelectAll
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void  doProcess_NMAL3000Scrn00_UnSelect_All(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {
        NMAL3000CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NMAL3000CommonLogic.selectUnselect(glblMsg, ZYPConstant.FLG_OFF_N);
        NMAL3000CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, 1);
    }
    // 2018/12/19 Add End QC#29661
}
