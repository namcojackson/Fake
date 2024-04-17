/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2010;

import static business.servlet.NWAL2010.constant.NWAL2010Constant.BIZ_ID;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2010.NWAL2010CMsg;
import business.servlet.NWAL2010.common.NWAL2010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *NWAL2010Scrn00_Delete
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/12/16   Hitachi         R.Takau         Create          QC#60823
 * 2024/04/10   CITS            M.Okamura       Update          QC#63757
 *</pre>
 */
public class NWAL2010Scrn00_CMN_Delete extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;
        NWAL2010CMsg bizMsg = new NWAL2010CMsg();
        
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;
        NWAL2010CMsg bizMsg = (NWAL2010CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        NWAL2010CommonLogic.initCmnBtnProp(this);
        NWAL2010CommonLogic.setCmnBtnProp(scrnMsg,this);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // Start 2024/04/10 M.Okamura [QC#63757,DEL]
        //NWAL2010CommonLogic.setCrCardProp(this, scrnMsg);
        // End 2024/04/10 M.Okamura [QC#63757,DEL]
        NWAL2010CommonLogic.setBgColor(scrnMsg);

        if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            scrnMsg.setFocusItem(scrnMsg.sellToCustCd);
        } else {
            scrnMsg.setFocusItem(scrnMsg.crCardCustRefNum);
        }
    }
}
