/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1010.NLCL1010CMsg;
import business.servlet.NLCL1010.common.NLCL1010CommonLogic;
import business.servlet.NLCL1010.constant.NLCL1010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 *</pre>
 */
public class NLCL1010_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLCL1010Constant.BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        if (arg != null) {
            if (arg instanceof Object[]) {
                Object[] params = (Object[]) arg;

                if (params.length > 0) {
                    if (params[0] == null) {
                        scrnMsg.serNum_C0.clear();
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_C0, (EZDBStringItem) params[0]);
                    }
                }

                if (params.length > 1) {
                    if (params[1] == null) {
                        scrnMsg.mdseCd_C0.clear();
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_C0, (EZDBStringItem) params[1]);
                    }
                }

                if (params.length > 2) {
                    if (params[2] == null) {
                        scrnMsg.serTrxSrcHdrNum_C0.clear();
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.serTrxSrcHdrNum_C0, (EZDBStringItem) params[2]);
                    }
                }
            }
        }

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

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLCL1010Constant.BUSINESS_ID);
        NLCL1010CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLCL1010CommonLogic.setTableAttribute(scrnMsg);
        NLCL1010CommonLogic.setInitialScrnFieldsState(this, scrnMsg, functionList);

        if (NLCL1010Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.serNum_C0) || ZYPCommonFunc.hasValue(scrnMsg.mdseCd_C0)) {
            NLCL1010CommonLogic.checkSearchItem(scrnMsg);
            scrnMsg.putErrorScreen();

            NLCL1010CommonLogic.setSearchedScrnFieldsState(this, scrnMsg, functionList);
        }
        scrnMsg.setFocusItem(scrnMsg.serNum_C0);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;

        // Search Condition
        scrnMsg.serNum_C0.setNameForMessage("Serial#");
        scrnMsg.mdseCd_C0.setNameForMessage("Item Num");
        scrnMsg.serTrxEventCd_P0.setNameForMessage("Event");
        scrnMsg.serErrStsCd_P0.setNameForMessage("Event Status");
        scrnMsg.xxFromDt_C0.setNameForMessage("Trx Term (From)");
        scrnMsg.xxThruDt_C0.setNameForMessage("Trx Term (To)");
        scrnMsg.fromLocCd_C0.setNameForMessage("Loc From");
        scrnMsg.toLocCd_C0.setNameForMessage("Loc To");
        scrnMsg.serTrxLtstFlg_C0.setNameForMessage("Latest Only");
        scrnMsg.xxRsltFlg_C0.setNameForMessage("Error Only");
        scrnMsg.serTrxSrcTpCd_P0.setNameForMessage("Trx SRC");
        scrnMsg.serTrxSrcHdrNum_C0.setNameForMessage("Trx SRC Header#");
        scrnMsg.serTrxRefNum_C0.setNameForMessage("Serial Trx Ref#");
        scrnMsg.origMdseCd_C0.setNameForMessage("Orig Item");
        // Add Serial Trx
        scrnMsg.serNum_H0.setNameForMessage("Serial#");
        scrnMsg.mdseCd_H0.setNameForMessage("Item Num");
        scrnMsg.serTrxEventCd_P1.setNameForMessage("Event");
        scrnMsg.fromLocCd_H0.setNameForMessage("Loc From");
        scrnMsg.toLocCd_H0.setNameForMessage("Loc To");
        scrnMsg.stkStsCd_F1.setNameForMessage("From Stock Status");          // 10/15/2015 add
        scrnMsg.stkStsCd_T1.setNameForMessage("Stock Status");               // 10/15/2015 add
        scrnMsg.serTrxSrcTpCd_P1.setNameForMessage("Trc SRC");
        scrnMsg.serTrxSrcHdrNum_H0.setNameForMessage("Trx SRC Header#");
        scrnMsg.serTrxSrcLineNum_H0.setNameForMessage("Line");
        scrnMsg.serTrxSrcLineSubNum_H0.setNameForMessage("Line Sub");
        scrnMsg.serTrxRefNum_H0.setNameForMessage("Serial Trx Ref#");
        scrnMsg.oldMdseCd_H0.setNameForMessage("Old Item Num");
        scrnMsg.xxDt10Dt_H0.setNameForMessage("Transaction Date");           // 10/15/2015 add
        scrnMsg.xxSvcFromHourMnTxt_H0.setNameForMessage("Transaction Time"); // 10/15/2015 add
        scrnMsg.serTrxCmntTxt_H0.setNameForMessage("Comment");

    }

}
