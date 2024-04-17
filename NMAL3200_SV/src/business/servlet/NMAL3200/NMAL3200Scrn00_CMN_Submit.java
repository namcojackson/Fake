/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.BIZ_APP_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_SEARCH;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_UPLOAD;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_CMN_SUBMIT;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP_NM_DB;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP_NM_SC;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.NMAM0836E;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.NMAM8229E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL3200.NMAL3200CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : CMN_Submit
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 * 07/20/2016   CITS            K.Ogino         Update          QC#12033
 */
public class NMAL3200Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.mktgFldMapNm_SC);
        scrnMsg.addCheckItem(scrnMsg.mktgFldMapNm_DB);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNmColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.firstLineAddrColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.scdLineAddrColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.thirdLineAddrColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.frthLineAddrColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.ctyAddrColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.cntyPkColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.stCdColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.postCdColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.ctryCdColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.dunsNumColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.glnColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.hinColDfnNm);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_01);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_02);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_03);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_04);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_05);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_06);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_07);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_08);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_09);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_10);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_11);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_12);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_13);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_14);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_15);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_16);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_17);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_18);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_19);
        scrnMsg.addCheckItem(scrnMsg.attrbTxtColDfnNm_20);

        if (!ZYPCommonFunc.hasValue(scrnMsg.mktgFldMapNm_DB) && !ZYPCommonFunc.hasValue(scrnMsg.mktgFldMapNm_SC)) {
            scrnMsg.mktgFldMapNm_DB.setErrorInfo(1, NMAM8229E, new String[] {MKTG_FLD_MAP_NM_DB, MKTG_FLD_MAP_NM_SC });
            scrnMsg.mktgFldMapNm_SC.setErrorInfo(1, NMAM8229E, new String[] {MKTG_FLD_MAP_NM_DB, MKTG_FLD_MAP_NM_SC });
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNmColDfnNm)) {
            scrnMsg.dsAcctNmColDfnNm.setErrorInfo(1, NMAM0836E, null);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.firstLineAddrColDfnNm)) {
            scrnMsg.firstLineAddrColDfnNm.setErrorInfo(1, NMAM0836E, null);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.ctyAddrColDfnNm)) {
            scrnMsg.ctyAddrColDfnNm.setErrorInfo(1, NMAM0836E, null);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.stCdColDfnNm)) {
            scrnMsg.stCdColDfnNm.setErrorInfo(1, NMAM0836E, null);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.postCdColDfnNm)) {
            scrnMsg.postCdColDfnNm.setErrorInfo(1, NMAM0836E, null);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;

        NMAL3200CMsg bizMsg = new NMAL3200CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        scrnMsg.xxScrEventNm.setValue(EVENT_NM_NMAL3200_CMN_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        NMAL3200CMsg bizMsg = (NMAL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.addCheckItem(scrnMsg.mktgFldMapNm_DB);
        scrnMsg.addCheckItem(scrnMsg.mktgFldMapNm_SC);
        scrnMsg.putErrorScreen();

        if (ZYPCommonFunc.hasValue(scrnMsg.mktgFldMapPk)) {
            scrnMsg.setFocusItem(scrnMsg.contrAssnTrgtTpCd_SL);
            this.setButtonEnabled(BTN_SEARCH, false);
            this.setButtonEnabled(BTN_UPLOAD, true);
            scrnMsg.xxFileData.setInputProtected(false);
            if (ZYPCommonFunc.hasValue(scrnMsg.mktgMapRqstPk_UP)) {
                scrnMsg.mktgFldMapNm_SC.setInputProtected(true);
            } else {
                scrnMsg.mktgFldMapNm_SC.setInputProtected(false);
            }
        }
    }
}
