/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1760;

import java.io.Serializable;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1760.NWAL1760CMsg;
import static business.servlet.NWAL1760.constant.NWAL1760Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import business.servlet.NWAL1760.common.NWAL1760CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/25   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1760_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1760BMsg scrnMsg = (NWAL1760BMsg) bMsg;
        NWAL1760CMsg bizMsg = new NWAL1760CMsg();

        // IN Parameter Get
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            int ixP = 0;
            Object[] params = (Object[]) arg;
            setValue(scrnMsg.prcBaseDt,   (EZDBDateItem)   params[ixP++]);
            setValue(scrnMsg.prcCtxTpCd,  (EZDBStringItem) params[ixP++]);
            setValue(scrnMsg.dsOrdCatgCd, (EZDBStringItem) params[ixP++]);
            setValue(scrnMsg.dsOrdTpCd,   (EZDBStringItem) params[ixP++]);
            setValue(scrnMsg.custAcctNum, (EZDBStringItem) params[ixP++]);
            setValue(scrnMsg.csmpNum,     (EZDBStringItem) params[ixP++]);
            setValue(scrnMsg.dlrRefNum,   (EZDBStringItem) params[ixP++]);
            setValue(scrnMsg.prcContrNum, (EZDBStringItem) params[ixP++]);
            setValue(scrnMsg.coaBrCd,     (EZDBStringItem) params[ixP]);

            // 20160305 add
            scrnMsg.prcCatgNm.clear();
            if (params.length == PRM_CNT && params[PRM_CNT - 1] != null) {
                setValue(scrnMsg.prcCatgNm, (EZDBStringItem) params[PRM_CNT - 1]);
            }
            // check the input parameter(s)
            // do nothing
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1760BMsg scrnMsg = (NWAL1760BMsg) bMsg;
        NWAL1760CMsg bizMsg  = (NWAL1760CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1760CommonLogic.initCmnBtnProp(this);
        NWAL1760CommonLogic.setGuiAttr(scrnMsg);
        NWAL1760CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
    }
}
