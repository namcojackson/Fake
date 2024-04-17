/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL0170;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL0170.NPAL0170CMsg;
import business.servlet.NPAL0170.common.NPAL0170CommonLogic;
import business.servlet.NPAL0170.constant.NPAL0170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * PO Text Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/24/2012   SRA             N.Otsuji        Create          N/A
 * 02/05/2013   CSAI            K.Lee           Update          WDS#69
 * 01/21/2016   CITS            K.Ogino         Update          CSA
 * 03/08/2016   CITS            K.Ogino         Update          QC#4975
 * 03/29/2016   CITS            T.Tokutomi      Update          QC#5755
 * </pre>
 */
public class NPAL0170_INIT extends S21INITCommonHandler implements NPAL0170Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg ezdBMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        NPAL0170BMsg bMsg = (NPAL0170BMsg) ezdBMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params.length > 0 && params[0] instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(bMsg.poOrdNum, (EZDBStringItem) params[0]);
            }
            if (params.length > 1 && params[1] instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(bMsg.poOrdDtlLineNum, (EZDBStringItem) params[1]);
            }
            if (params.length > 2 && params[2] instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(bMsg.prchReqNum, (EZDBStringItem) params[2]);
            }
            if (params.length > 3 && params[3] instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(bMsg.prchReqLineNum, (EZDBStringItem) params[3]);
            }
            if (params.length > 4 && params[4] instanceof EZDBBigDecimalItem) {
                ZYPEZDItemValueSetter.setValue(bMsg.prchReqLineSubNum, (EZDBBigDecimalItem) params[4]);
            }
            if (params.length > 5 && params[5] instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(bMsg.poMsgTpCd, (EZDBStringItem) params[5]);
            }
            if (!(ZYPCommonFunc.hasValue(bMsg.poMsgTpCd))) {
                ZYPEZDItemValueSetter.setValue(bMsg.poMsgTpCd, PO_MSG_TP.INTERNAL_PO_MESSAGE);
            }
            if (ZYPCommonFunc.hasValue(bMsg.prchReqLineNum) && ZYPCommonFunc.hasValue(bMsg.prchReqLineSubNum)) {
                ZYPEZDItemValueSetter.setValue(bMsg.xxLineNum, bMsg.prchReqLineNum.getValue() + "," + bMsg.prchReqLineSubNum.getValueInt());
            } else if (ZYPCommonFunc.hasValue(bMsg.prchReqLineNum)) {
                ZYPEZDItemValueSetter.setValue(bMsg.xxLineNum, bMsg.prchReqLineNum.getValue());
            } else if (ZYPCommonFunc.hasValue(bMsg.prchReqLineSubNum)) {
                ZYPEZDItemValueSetter.setValue(bMsg.xxLineNum, bMsg.prchReqLineSubNum.getValue().toString());
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL0170BMsg scrnMsg = (NPAL0170BMsg) bMsg;

        // get param
        Serializable arg = getArgForSubScreen();

        // Set Msg
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params.length == 7) {
                NPAL0170CommonLogic.setInitMsg(scrnMsg, (EZDMsgArray) params[6]);
            }
        }

        NPAL0170CMsg bizMsg = new NPAL0170CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL0170BMsg scrnMsg = (NPAL0170BMsg) bMsg;
        NPAL0170CMsg bizMsg = (NPAL0170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NPAL0170CommonLogic.controlItemsOnInit(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        // no process.
    }
}
