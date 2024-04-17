/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_01;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_02;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_03;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_04;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_05;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_06;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_07;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_08;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_09;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_10;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_11;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_12;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_13;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_14;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_15;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_16;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_17;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_18;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_19;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_20;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BIZ_APP_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_UPLOAD;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.CNTY_PK_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.CONTR_ASSN_TRGT_TP_DESC_TXT;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.CTRY_CD_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.CTY_ADDR_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.DS_ACCT_NM_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.DUNS_NUM_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.FIRST_LINE_ADDR_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.FRTH_LINE_ADDR_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.GLN_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.HIN_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP_NM_DB;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP_NM_SC;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_RQST_PK;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_RQST_PROC_FLG;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.POST_CD_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.SCD_LINE_ADDR_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ST_CD_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.TAB_UPLOAD;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.THIRD_LINE_ADDR_COL_DFN_NM;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.UPLD_ERR_FLG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL3200.NMAL3200CMsg;
import business.servlet.NMAL3200.common.NMAL3200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : INIT
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 */
public class NMAL3200_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        scrnMsg.xxDplyTab.setValue(TAB_UPLOAD);

        NMAL3200CMsg bizMsg = new NMAL3200CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        NMAL3200CMsg bizMsg = (NMAL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL3200CommonLogic.setCommonButtonInit(this);
        NMAL3200CommonLogic.setAttr(this, scrnMsg);
        this.setButtonEnabled(BTN_UPLOAD, false);
        scrnMsg.xxFileData.setInputProtected(true);
        scrnMsg.setFocusItem(scrnMsg.mktgFldMapNm_DB);

    }

    @Override
    protected void setNameForMessage(EZDBMsg msg) {
        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) msg;
        scrnMsg.mktgFldMapNm_DB.setNameForMessage(MKTG_FLD_MAP_NM_DB);
        scrnMsg.mktgFldMapNm_SC.setNameForMessage(MKTG_FLD_MAP_NM_SC);
        scrnMsg.dsAcctNmColDfnNm.setNameForMessage(DS_ACCT_NM_COL_DFN_NM);
        scrnMsg.firstLineAddrColDfnNm.setNameForMessage(FIRST_LINE_ADDR_COL_DFN_NM);
        scrnMsg.scdLineAddrColDfnNm.setNameForMessage(SCD_LINE_ADDR_COL_DFN_NM);
        scrnMsg.thirdLineAddrColDfnNm.setNameForMessage(THIRD_LINE_ADDR_COL_DFN_NM);
        scrnMsg.frthLineAddrColDfnNm.setNameForMessage(FRTH_LINE_ADDR_COL_DFN_NM);
        scrnMsg.ctyAddrColDfnNm.setNameForMessage(CTY_ADDR_COL_DFN_NM);
        scrnMsg.cntyPkColDfnNm.setNameForMessage(CNTY_PK_COL_DFN_NM);
        scrnMsg.stCdColDfnNm.setNameForMessage(ST_CD_COL_DFN_NM);
        scrnMsg.postCdColDfnNm.setNameForMessage(POST_CD_COL_DFN_NM);
        scrnMsg.ctryCdColDfnNm.setNameForMessage(CTRY_CD_COL_DFN_NM);
        scrnMsg.dunsNumColDfnNm.setNameForMessage(DUNS_NUM_COL_DFN_NM);
        scrnMsg.glnColDfnNm.setNameForMessage(GLN_COL_DFN_NM);
        scrnMsg.hinColDfnNm.setNameForMessage(HIN_COL_DFN_NM);
        scrnMsg.attrbTxtColDfnNm_01.setNameForMessage(ATTRB_TXT_COL_DFN_NM_01);
        scrnMsg.attrbTxtColDfnNm_02.setNameForMessage(ATTRB_TXT_COL_DFN_NM_02);
        scrnMsg.attrbTxtColDfnNm_03.setNameForMessage(ATTRB_TXT_COL_DFN_NM_03);
        scrnMsg.attrbTxtColDfnNm_04.setNameForMessage(ATTRB_TXT_COL_DFN_NM_04);
        scrnMsg.attrbTxtColDfnNm_05.setNameForMessage(ATTRB_TXT_COL_DFN_NM_05);
        scrnMsg.attrbTxtColDfnNm_06.setNameForMessage(ATTRB_TXT_COL_DFN_NM_06);
        scrnMsg.attrbTxtColDfnNm_07.setNameForMessage(ATTRB_TXT_COL_DFN_NM_07);
        scrnMsg.attrbTxtColDfnNm_08.setNameForMessage(ATTRB_TXT_COL_DFN_NM_08);
        scrnMsg.attrbTxtColDfnNm_09.setNameForMessage(ATTRB_TXT_COL_DFN_NM_09);
        scrnMsg.attrbTxtColDfnNm_10.setNameForMessage(ATTRB_TXT_COL_DFN_NM_10);
        scrnMsg.attrbTxtColDfnNm_11.setNameForMessage(ATTRB_TXT_COL_DFN_NM_11);
        scrnMsg.attrbTxtColDfnNm_12.setNameForMessage(ATTRB_TXT_COL_DFN_NM_12);
        scrnMsg.attrbTxtColDfnNm_13.setNameForMessage(ATTRB_TXT_COL_DFN_NM_13);
        scrnMsg.attrbTxtColDfnNm_14.setNameForMessage(ATTRB_TXT_COL_DFN_NM_14);
        scrnMsg.attrbTxtColDfnNm_15.setNameForMessage(ATTRB_TXT_COL_DFN_NM_15);
        scrnMsg.attrbTxtColDfnNm_16.setNameForMessage(ATTRB_TXT_COL_DFN_NM_16);
        scrnMsg.attrbTxtColDfnNm_17.setNameForMessage(ATTRB_TXT_COL_DFN_NM_17);
        scrnMsg.attrbTxtColDfnNm_18.setNameForMessage(ATTRB_TXT_COL_DFN_NM_18);
        scrnMsg.attrbTxtColDfnNm_19.setNameForMessage(ATTRB_TXT_COL_DFN_NM_19);
        scrnMsg.attrbTxtColDfnNm_20.setNameForMessage(ATTRB_TXT_COL_DFN_NM_20);
        scrnMsg.contrAssnTrgtTpCd_SL.setNameForMessage(CONTR_ASSN_TRGT_TP_DESC_TXT);
        scrnMsg.mktgMapRqstPk_DL.setNameForMessage(MKTG_MAP_RQST_PK);
        scrnMsg.mktgFldMapNm_DL.setNameForMessage(MKTG_FLD_MAP_NM);
        scrnMsg.upldErrFlg_SL.setNameForMessage(UPLD_ERR_FLG);
        scrnMsg.mktgMapRqstProcFlg_SL.setNameForMessage(MKTG_MAP_RQST_PROC_FLG);
    }
}
