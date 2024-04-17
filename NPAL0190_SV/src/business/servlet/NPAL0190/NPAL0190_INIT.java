/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL0190;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL0190.NPAL0190CMsg;
import business.servlet.NPAL0190.common.NPAL0190CommonLogic;
import business.servlet.NPAL0190.constant.NPAL0190Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * PO History Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2012   SRA             N.Otsuji        Create          N/A
 * 03/14/2013   Hitachi         T.Arakawa       Create          K25(QC822)
 * 01/25/2016   CITS            K.Ogino         Update          CSA
 *</pre>
 */
public class NPAL0190_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), NPAL0190Constant.BUSINESS_ID);

        NPAL0190BMsg scrnMsg = (NPAL0190BMsg) bMsg;

        Serializable ser = getArgForSubScreen();

        if (ser != null && ser instanceof Object[]) {

            Object[] params = (Object[]) ser;
            if (params.length > 0 && params[0] instanceof EZDBStringItem) {
                EZDBStringItem poOrdNum = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.poOrdNum_H, poOrdNum);
            }

            if (params.length > 1 && params[1] instanceof EZDBStringItem) {
                EZDBStringItem poDispLineNum = (EZDBStringItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dispPoDtlLineNum_H, poDispLineNum);
            }
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL0190BMsg scrnMsg = (NPAL0190BMsg) bMsg;
        NPAL0190CMsg bizMsg = new NPAL0190CMsg();
        if (!ZYPCommonFunc.hasValue(scrnMsg.poOrdNum_H)) {
            return null;
        }
        bizMsg.setBusinessID(NPAL0190Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NPAL0190Constant.FUNCTION_CODE_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL0190BMsg scrnMsg = (NPAL0190BMsg) bMsg;
        NPAL0190CMsg bizMsg = (NPAL0190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL0190CommonLogic.initializeCommandButtons(this);
        NPAL0190CommonLogic.setInputProtected(scrnMsg);
        NPAL0190CommonLogic.formatTs(scrnMsg);

        NPAL0190CommonLogic.alternateTableRowColors(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.xxComnColOrdTxt);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        // do nothing
    }

}
