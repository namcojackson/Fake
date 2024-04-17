/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1010.NLCL1010CMsg;
import business.servlet.NLCL1010.common.NLCL1010CommonLogic;
import business.servlet.NLCL1010.constant.NLCL1010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 *</pre>
 */
public class NLCL1010Scrn00_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;

        if (SER_TRX_EVENT.ITEM_CHANGE_STOCK_IN.equals(scrnMsg.serTrxEventCd_P1.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.oldMdseCd_H0)) {
                scrnMsg.serTrxEventCd_P1.setErrorInfo(1, NLCL1010Constant.NLCM0116E);
                scrnMsg.oldMdseCd_H0.setErrorInfo(1, NLCL1010Constant.NLCM0116E);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.serTrxSrcHdrNum_H0)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.serTrxSrcTpCd_P1)) {
                scrnMsg.serTrxSrcHdrNum_H0.setErrorInfo(1, NLCL1010Constant.NLCM0117E);
                scrnMsg.serTrxSrcTpCd_P1.setErrorInfo(1, NLCL1010Constant.NLCM0117E);
            }
        }

        // 10/15/2015 add start
        if (ZYPCommonFunc.hasValue(scrnMsg.serTrxEventCd_P1) 
        &&  SER_TRX_EVENT.STOCK_STATUS_CHANGE.equals(scrnMsg.serTrxEventCd_P1.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.stkStsCd_F1)) {
                scrnMsg.stkStsCd_F1.setErrorInfo(1, NLCL1010Constant.ZZZM9000E, new String[] {scrnMsg.stkStsCd_F1.getNameForMessage() });
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.stkStsCd_F1)
            &&  ZYPCommonFunc.hasValue(scrnMsg.stkStsCd_T1)
            &&  scrnMsg.stkStsCd_F1.getValue().equals(scrnMsg.stkStsCd_T1.getValue())) {
                scrnMsg.stkStsCd_F1.setErrorInfo(1, NLCL1010Constant.NLCM0035E, new String[] {scrnMsg.stkStsCd_F1.getNameForMessage(), scrnMsg.stkStsCd_T1.getNameForMessage() });
                scrnMsg.stkStsCd_T1.setErrorInfo(1, NLCL1010Constant.NLCM0035E, new String[] {scrnMsg.stkStsCd_F1.getNameForMessage(), scrnMsg.stkStsCd_T1.getNameForMessage() });
            }
            
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSvcFromHourMnTxt_H0)) {
            String regex = "([0-1][0-9]|[2][0-3])([:])([0-5][0-9])";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(scrnMsg.xxSvcFromHourMnTxt_H0.getValue());
            if (!m.find()) {
                scrnMsg.xxSvcFromHourMnTxt_H0.setErrorInfo(1, NLCL1010Constant.NSZM0304E);
            }
        }
        // 10/15/2015 add end

        NLCL1010CommonLogic.checkAddItem(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;

        NLCL1010CMsg bizMsg = new NLCL1010CMsg();
        bizMsg.setBusinessID(NLCL1010Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NLCL1010Constant.FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;
        NLCL1010CMsg bizMsg = (NLCL1010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (NLCL1010Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        NLCL1010CommonLogic.checkAddItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (NLCL1010Constant.MESSAGE_KIND_WARNING.equals(bizMsg.getMessageKind())) {
            return;
        }

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLCL1010Constant.BUSINESS_ID);
        NLCL1010CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLCL1010CommonLogic.setSearchedScrnFieldsState(this, scrnMsg, functionList);

        NLCL1010CommonLogic.setTableAttribute(scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).xxChkBox_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.serNum_H0);
        }

    }
}
