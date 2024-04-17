/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7090;

import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_GLBL_CMPY_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_PRC_LIST_EQUIP_RQST_PK;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_RQST_STS_TP_CD_NEW;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DOWNLOAD_MAX_ROWS;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_APPLY_A;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_APPLY_B;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_APPLY_C;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_CMN_CLEAR;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_CMN_DELETE;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_CMN_SUBMIT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_COPY;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_DOWNLOAD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_DOWNLOAD_HIS_RQST_RSLT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_INIT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_INSERT_ROW;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_ON_CHANGE_DISCARD_ALL;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_ON_CHANGE_SUBMIT_ALL;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_PAGE_NEXT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_PAGE_PREV;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_REFRESH;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_SEARCH;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_TEMPLATE;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_UPDATE;
import static business.blap.NMAL7090.constant.NMAL7090Constant.EVENT_NM_NMAL7090_UPLOAD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM0007I;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM0052E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8187E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.NMAM8436E;
import static business.blap.NMAL7090.constant.NMAL7090Constant.TABLE_A;
import static business.blap.NMAL7090.constant.NMAL7090Constant.TABLE_B;
import static business.blap.NMAL7090.constant.NMAL7090Constant.TABLE_C;
import static business.blap.NMAL7090.constant.NMAL7090Constant.UPLOAD_DATA_FORMAT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.ZYEM0004E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7090.common.NMAL7090CommonLogic;
import business.blap.NMAL7090.constant.NMAL7090Constant;
import business.file.NMAL7090F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_STS_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NMAL7090 Item Supersessions Mass Add
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 * 2016/05/11   CITS            S.Tanikawa      Update          QC#8176
 * 2016/05/11   CITS            S.Tanikawa      Update          QC#8180
 * 2016/05/11   CITS            S.Tanikawa      Update          QC#8181
 * 2016/11/22   Fujitsu         R.Nakamura      Update          QC#16082
 * 2017/12/06   Fujitsu         Mz.Takahashi    Update          QC#22761
 *</pre>
 */
public class NMAL7090BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7090CMsg bizMsg = (NMAL7090CMsg) cMsg;
            NMAL7090SMsg glblMsg = (NMAL7090SMsg) sMsg;

            if (EVENT_NM_NMAL7090_INIT.equals(screenAplID) || EVENT_NM_NMAL7090_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NMAL7090_INIT(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_SEARCH.equals(screenAplID)) {
                doProcess_NMAL7090_Search(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NMAL7090_PageNext(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_PAGE_PREV.equals(screenAplID)) {
                doProcess_NMAL7090_PagePrev(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_ON_CHANGE_DISCARD_ALL.equals(screenAplID)) {
                doProcess_NMAL7090_OnChangeDiscard(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_ON_CHANGE_SUBMIT_ALL.equals(screenAplID)) {
                doProcess_NMAL7090_OnChangeSubmit(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_REFRESH.equals(screenAplID)) {
                doProcess_NMAL7090_Refresh(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_UPDATE.equals(screenAplID)) {
                doProcess_NMAL7090_Update(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_COPY.equals(screenAplID)) {
                doProcess_NMAL7090_Copy(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_INSERT_ROW.equals(screenAplID)) {
                doProcess_NMAL7090_InsertRow(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_CMN_DELETE.equals(screenAplID)) {
                // UPDATE START 2016/05/11 QC#8180
                doProcess_NMAL7090_Refresh(bizMsg, glblMsg);
                // UPDATE END 2016/05/11 QC#8180

            } else if (EVENT_NM_NMAL7090_APPLY_A.equals(screenAplID)) {
                doProcess_NMAL7090_Apply_A(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_APPLY_B.equals(screenAplID)) {
                doProcess_NMAL7090_Apply_B(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_APPLY_C.equals(screenAplID)) {
                doProcess_NMAL7090_Apply_C(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NMAL7090_Search(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_UPLOAD.equals(screenAplID)) {
                doProcess_NMAL7090_Upload(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_TEMPLATE.equals(screenAplID)) {
                doProcess_NMAL7090_DownloadTemplate(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_DOWNLOAD_HIS_RQST_RSLT.equals(screenAplID)) {
                doProcess_NMAL7090_DownloadHistRqstRslt(bizMsg, glblMsg);

            } else if (EVENT_NM_NMAL7090_DOWNLOAD.equals(screenAplID)) {
                doProcess_NMAL7090_Download(bizMsg, glblMsg);

            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * INIT Event
     * @param cMsg Business Message
     * @param sMsg Global Message
     */
    private void doProcess_NMAL7090_INIT(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.C);

        createPulldownList(cMsg);
    }

    private void createPulldownList(NMAL7090CMsg cMsg) {
        cMsg.prcListTpCd_CP.clear();
        cMsg.prcListTpDescTxt_CP.clear();
        cMsg.prcListTpCd_FP.clear();
        cMsg.prcListTpDescTxt_FP.clear();

        NMAL7090CommonLogic.getPrcListTp(cMsg, getGlobalCompanyCode());
        for (int i = 0; i < cMsg.prcListTpCd_PD.length(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.prcListTpCd_CP.no(i), cMsg.prcListTpCd_PD.no(i));
            ZYPEZDItemValueSetter.setValue(cMsg.prcListTpDescTxt_CP.no(i), cMsg.prcListTpDescTxt_PD.no(i));
            ZYPEZDItemValueSetter.setValue(cMsg.prcListTpCd_FP.no(i), cMsg.prcListTpCd_PD.no(i));
            ZYPEZDItemValueSetter.setValue(cMsg.prcListTpDescTxt_FP.no(i), cMsg.prcListTpDescTxt_PD.no(i));

        }

        NMAL7090CommonLogic.getPrcListActTp(cMsg, getGlobalCompanyCode());
        for (int i = 0; i < cMsg.prcListActTpCd_PD.length(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.prcListActTpCd_CP.no(i), cMsg.prcListActTpCd_PD.no(i));
            ZYPEZDItemValueSetter.setValue(cMsg.prcListActTpDescTxt_CP.no(i), cMsg.prcListActTpDescTxt_PD.no(i));
            ZYPEZDItemValueSetter.setValue(cMsg.prcListActTpCd_FP.no(i), cMsg.prcListActTpCd_PD.no(i));
            ZYPEZDItemValueSetter.setValue(cMsg.prcListActTpDescTxt_FP.no(i), cMsg.prcListActTpDescTxt_PD.no(i));

        }
        NMAL7090CommonLogic.setEquipReqStsTp(cMsg, getGlobalCompanyCode());
    }

    private void doProcess_NMAL7090_Search(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        // ZYPTableUtil.clear(sMsg.X);

        // clear filter Items
        NMAL7090CommonLogic.clearFilterItemsA(cMsg, sMsg);
        NMAL7090CommonLogic.clearFilterItemsB(cMsg, sMsg);
        NMAL7090CommonLogic.clearFilterItemsC(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.xxSelFlg, ZYPConstant.FLG_OFF_N);
        // set Item Supersession List
        NMAL7090CommonLogic.getItemList(cMsg, sMsg);
        // set New Requests List
        NMAL7090CommonLogic.getNewRequest(cMsg, sMsg);
        // set Historical Requests List
        NMAL7090CommonLogic.getHistRequest(cMsg, sMsg);

        // UPDATE START 2016/05/11 QC#8176
        int ttlTblCnt = cMsg.A.getValidCount() + cMsg.B.getValidCount() + cMsg.C.getValidCount();
        if (ttlTblCnt <= 0) {
            // UPDATE START 2016/05/11 QC#8176
            cMsg.setMessageInfo(NMAM0007I);
        }
    }

    private void doProcess_NMAL7090_PageNext(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.xxSelFlg, ZYPConstant.FLG_OFF_N);

        NMAL7090CommonLogic.copyBCMsgToBSMsg(cMsg, sMsg);

        if (TABLE_A.equals(cMsg.xxModeInd.getValue())) {
            // set Item Supersession List
            NMAL7090CommonLogic.getItemList(cMsg, sMsg);
        } else if (TABLE_B.equals(cMsg.xxModeInd.getValue())) {

            // copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.xxPageShowFromNum_B.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.B.length(); i++) {
                if (i < sMsg.B.getValidCount()) {
                    EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.B.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            cMsg.xxPageShowFromNum_B.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum_B.setValue(pagenationFrom + cMsg.B.getValidCount());

        } else if (TABLE_C.equals(cMsg.xxModeInd.getValue())) {
            NMAL7090CommonLogic.getHistRequest(cMsg, sMsg);
        }

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxSelFlg.getValue())) {
            cMsg.setMessageInfo(NMAM0007I);
        }
    }

    private void doProcess_NMAL7090_PagePrev(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.xxSelFlg, ZYPConstant.FLG_OFF_N);

        NMAL7090CommonLogic.copyBCMsgToBSMsg(cMsg, sMsg);

        if (TABLE_A.equals(cMsg.xxModeInd.getValue())) {
            // set Item Supersession List
            NMAL7090CommonLogic.getItemList(cMsg, sMsg);
        } else if (TABLE_B.equals(cMsg.xxModeInd.getValue())) {

            // copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.xxPageShowFromNum_B.getValueInt();

            int i = pagenationFrom;

            for (; i < pagenationFrom + cMsg.B.length(); i++) {
                if (i < sMsg.B.getValidCount()) {
                    EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.B.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            pagenationFrom = pagenationFrom + 1;
            cMsg.xxPageShowFromNum_B.setValue(pagenationFrom);
            cMsg.xxPageShowToNum_B.setValue(pagenationFrom + cMsg.B.getValidCount() - 1);

        } else if (TABLE_C.equals(cMsg.xxModeInd.getValue())) {
            NMAL7090CommonLogic.getHistRequest(cMsg, sMsg);
        }

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxSelFlg.getValue())) {
            cMsg.setMessageInfo(NMAM0007I);
        }
    }

    private void doProcess_NMAL7090_OnChangeSubmit(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.submtFlg_BA.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).submtFlg_B, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rqstDscdFlg_B, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.rqstDscdFlg_BA, ZYPConstant.FLG_OFF_N);

            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).submtFlg_B, ZYPConstant.FLG_OFF_N);
            }
        }
    }

    private void doProcess_NMAL7090_OnChangeDiscard(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.rqstDscdFlg_BA.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rqstDscdFlg_B, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).submtFlg_B, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.submtFlg_BA, ZYPConstant.FLG_OFF_N);

            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rqstDscdFlg_B, ZYPConstant.FLG_OFF_N);
            }
        }
    }

    private void doProcess_NMAL7090_Refresh(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.C);

        // clear filter Items
        NMAL7090CommonLogic.clearFilterItemsA(cMsg, sMsg);
        NMAL7090CommonLogic.clearFilterItemsC(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.xxSelFlg, ZYPConstant.FLG_OFF_N);
        // set Item Supersession List
        NMAL7090CommonLogic.getItemList(cMsg, sMsg);
        // set Historical Requests List
        NMAL7090CommonLogic.getHistRequest(cMsg, sMsg);

        // UPDATE START 2016/05/11 QC#8176
        int ttlTblCnt = cMsg.A.getValidCount() + cMsg.C.getValidCount();
        if (ttlTblCnt <= 0) {
            // UPDATE END 2016/05/11 QC#8176
            cMsg.setMessageInfo(NMAM0007I);
        }
    }

    private void doProcess_NMAL7090_Update(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        // ADD ROW
        NMAL7090CommonLogic.addNewRequest(cMsg, sMsg, EVENT_NM_NMAL7090_UPDATE);
        // Paging after add new request
        NMAL7090CommonLogic.pageAddNewRequest(cMsg, sMsg);
    }

    private void doProcess_NMAL7090_Copy(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        // ADD ROW
        NMAL7090CommonLogic.addNewRequest(cMsg, sMsg, EVENT_NM_NMAL7090_COPY);
        // Paging after add new request
        NMAL7090CommonLogic.pageAddNewRequest(cMsg, sMsg);

    }

    private void doProcess_NMAL7090_InsertRow(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        // ADD ROW
        NMAL7090CommonLogic.addNewRequest(cMsg, sMsg, EVENT_NM_NMAL7090_INSERT_ROW);

        // Paging after add new request
        NMAL7090CommonLogic.pageAddNewRequest(cMsg, sMsg);

    }

    private void doProcess_NMAL7090_Apply_A(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        // WHEN FILTER ITEM HAS VALUE, FILTERING_FLAG IS ON.
        if (!ZYPCommonFunc.hasValue(cMsg.supdFromMdseCd_FA) && !ZYPCommonFunc.hasValue(cMsg.mdseDescShortTxt_FO) && !ZYPCommonFunc.hasValue(cMsg.supdToMdseCd_FA) && !ZYPCommonFunc.hasValue(cMsg.mdseDescShortTxt_FN)
                && !ZYPCommonFunc.hasValue(cMsg.supdCratDt_FA)) {
            ZYPEZDItemValueSetter.setValue(sMsg.xxYesNoCd_FA, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.xxYesNoCd_FA, ZYPConstant.FLG_ON_Y);
        }
        ZYPEZDItemValueSetter.setValue(sMsg.supdFromMdseCd_FA, cMsg.supdFromMdseCd_FA);
        ZYPEZDItemValueSetter.setValue(sMsg.mdseDescShortTxt_FO, cMsg.mdseDescShortTxt_FO);
        ZYPEZDItemValueSetter.setValue(sMsg.supdToMdseCd_FA, cMsg.supdToMdseCd_FA);
        ZYPEZDItemValueSetter.setValue(sMsg.mdseDescShortTxt_FN, cMsg.mdseDescShortTxt_FN);
        ZYPEZDItemValueSetter.setValue(sMsg.supdCratDt_FA, cMsg.supdCratDt_FA);
        NMAL7090CommonLogic.getItemList(cMsg, sMsg);
    }

    private void doProcess_NMAL7090_Apply_B(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.oldMdseCd_FB) && !ZYPCommonFunc.hasValue(cMsg.oldMdseDescShortTxt_FB) && !ZYPCommonFunc.hasValue(cMsg.newMdseCd_FB) && !ZYPCommonFunc.hasValue(cMsg.newMdseDescShortTxt_FB)
                && !ZYPCommonFunc.hasValue(cMsg.prcListTpCd_FS) && !ZYPCommonFunc.hasValue(cMsg.prcListActTpCd_FS)) {
            ZYPEZDItemValueSetter.setValue(sMsg.xxYesNoCd_FB, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.xxYesNoCd_FB, ZYPConstant.FLG_ON_Y);
        }
        ZYPEZDItemValueSetter.setValue(sMsg.oldMdseCd_FB, cMsg.oldMdseCd_FB);
        ZYPEZDItemValueSetter.setValue(sMsg.oldMdseDescShortTxt_FB, cMsg.oldMdseDescShortTxt_FB);
        ZYPEZDItemValueSetter.setValue(sMsg.newMdseCd_FB, cMsg.newMdseCd_FB);
        ZYPEZDItemValueSetter.setValue(sMsg.newMdseDescShortTxt_FB, cMsg.newMdseDescShortTxt_FB);
        ZYPEZDItemValueSetter.setValue(sMsg.prcListTpCd_FS, cMsg.prcListTpCd_FS);
        ZYPEZDItemValueSetter.setValue(sMsg.prcListActTpCd_FS, cMsg.prcListActTpCd_FS);

        NMAL7090CommonLogic.getNewRequest(cMsg, sMsg);
        // NMAL7090CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        //
        // for (int i = 0; i < sMsg.B.getValidCount(); i++) {
        // ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxYesNoCd_B, ZYPConstant.FLG_ON_Y);
        //
        // // oldMdseCd FB
        // if (ZYPCommonFunc.hasValue(cMsg.oldMdseCd_FB)) {
        // if (sMsg.B.no(i).oldMdseCd_B.getValue().indexOf(cMsg.oldMdseCd_FB.getValue()) == -1) {
        // ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxYesNoCd_B, ZYPConstant.FLG_OFF_N);
        // continue;
        // }
        // }
        // // oldMdseDescShortTxt FB
        // if (ZYPCommonFunc.hasValue(cMsg.oldMdseDescShortTxt_FB)) {
        // if (sMsg.B.no(i).oldMdseDescShortTxt_B.getValue().indexOf(cMsg.oldMdseDescShortTxt_FB.getValue()) == -1) {
        // continue;
        // }
        // }
        // // newMdseCd FB
        // if (ZYPCommonFunc.hasValue(cMsg.newMdseCd_FB)) {
        // if (sMsg.B.no(i).newMdseCd_B.getValue().indexOf(cMsg.newMdseCd_FB.getValue()) == -1) {
        // ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxYesNoCd_B, ZYPConstant.FLG_OFF_N);
        // continue;
        // }
        // }
        // // newMdseDescShortTxt FB
        // if (ZYPCommonFunc.hasValue(cMsg.newMdseDescShortTxt_FB)) {
        // if (sMsg.B.no(i).newMdseDescShortTxt_B.getValue().indexOf(cMsg.newMdseDescShortTxt_FB.getValue()) == -1) {
        // ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxYesNoCd_B, ZYPConstant.FLG_OFF_N);
        // continue;
        // }
        // }
        // // prcListTpCd FS
        // if (ZYPCommonFunc.hasValue(cMsg.prcListTpCd_FS)) {
        // if (!sMsg.B.no(i).prcListTpCd_BS.getValue().equals(cMsg.prcListTpCd_FS.getValue())) {
        // ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxYesNoCd_B, ZYPConstant.FLG_OFF_N);
        // continue;
        // }
        // }
        // // prcListActTpCd FS
        // if (ZYPCommonFunc.hasValue(cMsg.prcListActTpCd_FS)) {
        // if (!sMsg.B.no(i).prcListActTpCd_BS.getValue().equals(cMsg.prcListActTpCd_FS.getValue())) {
        // ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxYesNoCd_B, ZYPConstant.FLG_OFF_N);
        // continue;
        // }
        // }
        // }
        // NMAL7090CommonLogic.copySMsgBtoSMsgX(sMsg);

        // Copy 1 page Data(SMsg -> CMsg)
        int i = 0;
        for (; i < sMsg.B.getValidCount(); i++) {
            if (i == cMsg.B.length()) {
                break;
            }
            EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);

        }
        cMsg.B.setValidCount(i);

        // Setting Next Page
        cMsg.xxPageShowFromNum_B.setValue(1);
        cMsg.xxPageShowToNum_B.setValue(cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
    }

    private void doProcess_NMAL7090_Apply_C(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {
        // WHEN FILTER ITEM HAS VALUE, FILTERING_FLAG IS ON.
        if (!ZYPCommonFunc.hasValue(cMsg.oldMdseCd_FC) && !ZYPCommonFunc.hasValue(cMsg.oldMdseDescShortTxt_FC) && !ZYPCommonFunc.hasValue(cMsg.newMdseCd_FC) && !ZYPCommonFunc.hasValue(cMsg.newMdseDescShortTxt_FC)
                && !ZYPCommonFunc.hasValue(cMsg.prcListTpCd_CS) && !ZYPCommonFunc.hasValue(cMsg.prcListActTpCd_CS) && !ZYPCommonFunc.hasValue(cMsg.newPrcAmt_FC) && !ZYPCommonFunc.hasValue(cMsg.rqstStsTpCd_CS)
                // UPDATE START 2016/05/11 QC#8181
                && !ZYPCommonFunc.hasValue(cMsg.xxAuthByNm_FC)) {
                // UPDATE END 2016/05/11 QC#8181
            ZYPEZDItemValueSetter.setValue(sMsg.xxYesNoCd_FC, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.xxYesNoCd_FC, ZYPConstant.FLG_ON_Y);
        }
        ZYPEZDItemValueSetter.setValue(sMsg.oldMdseCd_FC, cMsg.oldMdseCd_FC);
        ZYPEZDItemValueSetter.setValue(sMsg.oldMdseDescShortTxt_FC, cMsg.oldMdseDescShortTxt_FC);
        ZYPEZDItemValueSetter.setValue(sMsg.newMdseCd_FC, cMsg.newMdseCd_FC);
        ZYPEZDItemValueSetter.setValue(sMsg.newMdseDescShortTxt_FC, cMsg.newMdseDescShortTxt_FC);
        ZYPEZDItemValueSetter.setValue(sMsg.prcListTpCd_CS, cMsg.prcListTpCd_CS);
        ZYPEZDItemValueSetter.setValue(sMsg.prcListActTpCd_CS, cMsg.prcListActTpCd_CS);
        ZYPEZDItemValueSetter.setValue(sMsg.newPrcAmt_FC, cMsg.newPrcAmt_FC);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstStsTpCd_CS, cMsg.rqstStsTpCd_CS);
        // UPDATE START 2016/05/11 QC#8181
        ZYPEZDItemValueSetter.setValue(sMsg.xxAuthByNm_FC, cMsg.xxAuthByNm_FC);
        // UPDATE END 2016/05/11 QC#8181
        NMAL7090CommonLogic.getHistRequest(cMsg, sMsg);

    }

    private void doProcess_NMAL7090_DownloadTemplate(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {

        NMAL7090F00FMsg fMsg = new NMAL7090F00FMsg();

        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("UploadTemplate"), ".csv");
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        //QC#22761 Mod Start
        csvOutFile.writeHeader(new String[] {"Old Item Code", "New Item Code", "Update Price List Type", "Update Price List Action", "Price List ID's" ,"Retain Original Price", "New Price", "New Min Price", "New Max Price", "New Lease Payment",
                "New Min Lease Payment", "New Max Lease Payment"});
        //QC#22761 Mod End

        csvOutFile.close();
    }

    private void doProcess_NMAL7090_Upload(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {
        NMAL7090CommonLogic.copyBCMsgToBSMsg(cMsg, sMsg);

        String path = cMsg.xxFileData.getTempFilePath();
        NMAL7090F00FMsg fMsg = new NMAL7090F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;

        // Mod Start 2016/11/22 QC#16082
        String csvPath = ZYPExcelUtil.excelToCsvFile(path);
//        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvPath, fMsg, option);
        // Mod End 2016/11/22 QC#16082
        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }
            int status = -1;
            int cnt = 0;
            while ((status = mappedFile.read()) != 1) {
                cnt++;
                // format error
                if (status == 1000) {
                    cMsg.setMessageInfo(NMAM0052E, new String[] {UPLOAD_DATA_FORMAT });
                    return;
                }

                if (sMsg.B.getValidCount() == sMsg.B.length()) {
                    /** @ cannot be added because it is exceeding the maximum number of row [@] */
                    Integer tblLen = sMsg.B.length();
                    cMsg.setMessageInfo(NMAM8187E, new String[] {"New Request List", tblLen.toString() });
                    return;
                }

                int newIndx = sMsg.B.getValidCount();
                if (ZYPCommonFunc.hasValue(fMsg.oldMdseCd_U1)) {
                    if (!NMAL7090CommonLogic.isExistInMdse(fMsg.oldMdseCd_U1.getValue()) && !NMAL7090CommonLogic.isExistInOrdTakeMdse(fMsg.oldMdseCd_U1.getValue())) {
                        cMsg.setMessageInfo(NMAM8436E, new String[] {Integer.toString(cnt), "Old Item Code", fMsg.oldMdseCd_U1.getValue() });
                        return;
                    }
                } else {
                    cMsg.setMessageInfo(NMAM8436E, new String[] {Integer.toString(cnt), "Old Item Code", "NULL" });
                    return;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).oldMdseCd_B, fMsg.oldMdseCd_U1);

                if (ZYPCommonFunc.hasValue(fMsg.newMdseCd_U1)) {
                    if (!NMAL7090CommonLogic.isExistInMdse(fMsg.newMdseCd_U1.getValue()) && !NMAL7090CommonLogic.isExistInOrdTakeMdse(fMsg.newMdseCd_U1.getValue())) {
                        cMsg.setMessageInfo(NMAM8436E, new String[] {Integer.toString(cnt), "New Item Code", fMsg.newMdseCd_U1.getValue() });
                        return;
                    }
                } else {
                    cMsg.setMessageInfo(NMAM8436E, new String[] {Integer.toString(cnt), "New Item Code", "NULL" });
                    return;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).newMdseCd_B, fMsg.newMdseCd_U1);

                if (ZYPCommonFunc.hasValue(fMsg.prcListTpDescTxt_U1)) {
                    S21SsmEZDResult result = NMAL7090Query.getInstance().getPrcListTpCd(fMsg.prcListTpDescTxt_U1.getValue());
                    if (result.isCodeNormal()) {
                        String prcListTpCd = (String) result.getResultObject();
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).prcListTpCd_BS, prcListTpCd);

                    } else {
                        cMsg.setMessageInfo(NMAM8436E, new String[] {Integer.toString(cnt), "Update Price List Type", fMsg.prcListTpDescTxt_U1.getValue() });
                        return;
                    }
                } else {
                    cMsg.setMessageInfo(NMAM8436E, new String[] {Integer.toString(cnt), "Update Price List Type", "NULL" });
                    return;
                }

                if (ZYPCommonFunc.hasValue(fMsg.prcListActTpDescTxt_U1)) {
                    S21SsmEZDResult result = NMAL7090Query.getInstance().getPrcListActTpCd(fMsg.prcListActTpDescTxt_U1.getValue());
                    if (result.isCodeNormal()) {
                        String prcListActTpCd = (String) result.getResultObject();
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).prcListActTpCd_BS, prcListActTpCd);
                    } else {
                        cMsg.setMessageInfo(NMAM8436E, new String[] {Integer.toString(cnt), "Update Price List Action", fMsg.prcListActTpDescTxt_U1.getValue() });
                        return;
                    }
                } else {
                    cMsg.setMessageInfo(NMAM8436E, new String[] {Integer.toString(cnt), "Update Price List Action", "NULL" });
                    return;
                }
                // setPullDown
                NMAL7090CommonLogic.setPullDownOnBSMsg(sMsg.B.no(newIndx), cMsg);

                //QC#22761 Add Start
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).prcListsDescTxt_B, fMsg.prcListsDescTxt_U1);
                //QC#22761 Add End
                
                if (!ZYPConstant.FLG_ON_Y.equals(fMsg.retanOrigPrcFlg_U1.getValue()) && !ZYPConstant.FLG_OFF_N.equals(fMsg.retanOrigPrcFlg_U1.getValue())) {
                    cMsg.setMessageInfo(NMAM8436E, new String[] {Integer.toString(cnt), "Retain Original Price", fMsg.retanOrigPrcFlg_U1.getValue() });
                    return;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).retanOrigPrcFlg_B, fMsg.retanOrigPrcFlg_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).newPrcAmt_B, fMsg.newPrcAmt_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).newMinPrcAmt_B, fMsg.newMinPrcAmt_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).newMaxPrcAmt_B, fMsg.newMaxPrcAmt_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).newMlyPmtAmt_B, fMsg.newMlyPmtAmt_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).newMinLeasePmtAmt_B, fMsg.newMinLeasePmtAmt_U1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).newMaxLeasePmtAmt_B, fMsg.newMaxLeasePmtAmt_U1);

                //QC#22761 Mod Start
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).submtFlg_B, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).rqstDscdFlg_B, ZYPConstant.FLG_OFF_N);
                //QC#22761 Mod End

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(newIndx).xxNum_B, BigDecimal.valueOf(newIndx));

                sMsg.B.setValidCount(newIndx + 1);
            }

            ZYPTableUtil.clear(cMsg.B);
            NMAL7090CommonLogic.pageAddNewRequest(cMsg, sMsg);

        } finally {
            mappedFile.close();
            cMsg.xxFileData.deleteTempFile();
            // Add Start 2016/11/21 QC#16082
            ZYPExcelUtil.deleteFile(csvPath);
            // Add End 2016/11/21 QC#16082
        }
    }

    private void doProcess_NMAL7090_DownloadHistRqstRslt(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NMAL7090Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL7090Query.getInstance().getClass());

            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("Historical_Request_Result"), ".csv");

            // create csv file, parameters
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("rowNum", DOWNLOAD_MAX_ROWS + 1);
            params.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
            params.put(DB_PRM_PRC_LIST_EQUIP_RQST_PK, cMsg.C.no(cMsg.xxNum_SL.getValueInt()).prcListEquipRqstPk_C.getValue());

            ps = ssmLLClient.createPreparedStatement("getHistRequestForDownload", params, execParam);
            rs = ps.executeQuery();

            NMAL7090CommonLogic.writeCsvFileHistRequest(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void doProcess_NMAL7090_Download(NMAL7090CMsg cMsg, NMAL7090SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NMAL7090Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL7090Query.getInstance().getClass());

            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("New_Request"), ".csv");

            // create csv file, parameters
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("rowNum", DOWNLOAD_MAX_ROWS + 1);
            params.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
            params.put(DB_PRM_RQST_STS_TP_CD_NEW, RQST_STS_TP.NEW);

            ps = ssmLLClient.createPreparedStatement("getNewRequestForDownload", params, execParam);
            rs = ps.executeQuery();

            NMAL7090CommonLogic.writeCsvFileNewRequest(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }
}
