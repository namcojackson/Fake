/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.MSG_PARAM_ACCT;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.MSG_PARAM_AFFL;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.MSG_PARAM_BR;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.MSG_PARAM_CC;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.MSG_PARAM_CH;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.MSG_PARAM_CMPY;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.MSG_PARAM_EXTN;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.MSG_PARAM_PROD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.MSG_PARAM_PROJ;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.NPAM1320E;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_ELEMENT_LENGTH_ACCT;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_ELEMENT_LENGTH_AFFL;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_ELEMENT_LENGTH_BR;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_ELEMENT_LENGTH_CC;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_ELEMENT_LENGTH_CH;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_ELEMENT_LENGTH_CMPY;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_ELEMENT_LENGTH_EXTN;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_ELEMENT_LENGTH_PROD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_ELEMENT_LENGTH_PROJ;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.CHARGE_ACCOUNT_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.DOT;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.NPAM1193E;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SEGMENT_TOKEN_LIST_SIZE;

import java.util.regex.Pattern;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1280.common.NPAL1280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : OpenWin_Account
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 01/19/2017   CITS            S.Endo           Update          QC#15717
 * 01/24/2017   CITS            S.Endo           Update          QC#15717
 * 03/17/2020   CITS            M.Furugoori      Update          QC#56122
 *</pre>
 */
public class NPAL1280Scrn00_OpenWin_Account extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        int lineIndex = getButtonSelectNumber();
        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1) 
                && (PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue()))) {

            String[] list = scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.getValue().split(Pattern.quote(DOT), -1);

            // START 03/17/2020 [QC#56122,MOD]
//            if (list.length < SEGMENT_TOKEN_LIST_SIZE) {
            if (list.length != SEGMENT_TOKEN_LIST_SIZE || list == null) {
                scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1193E, new String[] {CHARGE_ACCOUNT_D});
                scrnMsg.addCheckItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
                scrnMsg.putErrorScreen();
            }
            // END 03/17/2020 [QC#56122,MOD]

            // START 03/17/2020 [QC#56122,ADD]
            // coaCmpyCd check
            if (!NPAL1280CommonLogic.validateSegmentElement(list[SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD], SEGMENT_ELEMENT_LENGTH_CMPY)) {
                scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CMPY });
                scrnMsg.addCheckItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
                scrnMsg.putErrorScreen();
            }

            // coaExtnCd check
            if (!NPAL1280CommonLogic.validateSegmentElement(list[SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD], SEGMENT_ELEMENT_LENGTH_EXTN)) {
                scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_EXTN });
                scrnMsg.addCheckItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
                scrnMsg.putErrorScreen();
            }

            // coaCcCd check
            if (!NPAL1280CommonLogic.validateSegmentElement(list[SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD], SEGMENT_ELEMENT_LENGTH_CC)) {
                scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CC });
                scrnMsg.addCheckItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
                scrnMsg.putErrorScreen();
            }

            // coaAcctCd check
            if (!NPAL1280CommonLogic.validateSegmentElement(list[SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD], SEGMENT_ELEMENT_LENGTH_ACCT)) {
                scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_ACCT });
                scrnMsg.addCheckItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
                scrnMsg.putErrorScreen();
            }

            // coaProjCd check
            if (!NPAL1280CommonLogic.validateSegmentElement(list[SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD], SEGMENT_ELEMENT_LENGTH_PROJ)) {
                scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_PROJ });
                scrnMsg.addCheckItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
                scrnMsg.putErrorScreen();
            }

            // coaProdCd check
            if (!NPAL1280CommonLogic.validateSegmentElement(list[SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD], SEGMENT_ELEMENT_LENGTH_PROD)) {
                scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_PROD });
                scrnMsg.addCheckItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
                scrnMsg.putErrorScreen();
            }

            // coaAfflCd check
            if (!NPAL1280CommonLogic.validateSegmentElement(list[SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD], SEGMENT_ELEMENT_LENGTH_AFFL)) {
                scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_AFFL });
                scrnMsg.addCheckItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
                scrnMsg.putErrorScreen();
            }

            // coaChCd check
            if (!NPAL1280CommonLogic.validateSegmentElement(list[SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD], SEGMENT_ELEMENT_LENGTH_CH)) {
                scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_CH });
                scrnMsg.addCheckItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
                scrnMsg.putErrorScreen();
            }

            // coaBrCd check
            if (!NPAL1280CommonLogic.validateSegmentElement(list[SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD], SEGMENT_ELEMENT_LENGTH_BR)) {
                scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1320E, new String[] {MSG_PARAM_BR });
                scrnMsg.addCheckItem(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1);
                scrnMsg.putErrorScreen();
            }
            // END 03/17/2020 [QC#56122,ADD]
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        int lineIndex = getButtonSelectNumber();
        scrnMsg.xxLineNum.setValue(Integer.valueOf(lineIndex).toString());
        Object[] params = NPAL1280CommonLogic.setParamForAccountPopup(scrnMsg, getGlobalCompanyCode(), lineIndex);
        if (params != null) {
            setArgForSubScreen(params);
        }

    }
}
