/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1570.NWAL1570CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL1570Scrn01_TransitionByContractNumber extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        scrnMsg.xxRowNum.setValue(getButtonSelectNumber());

        NWAL1570CMsg bizMsg = new NWAL1570CMsg();
        bizMsg.setBusinessID("NWAL1570");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570CMsg bizMsg = (NWAL1570CMsg) cMsg;
        NWAL1570BMsg scrnMsg = new NWAL1570BMsg();
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1570_ABMsg recordMsg = scrnMsg.A.no(getButtonSelectNumber());

        List<EZDBBigDecimalItem> param = null;
        param = createNSAL0300Parameters(recordMsg, scrnMsg);

        setArgForSubScreen(param.toArray(new EZDBBigDecimalItem[0]));

    }

    private List<EZDBBigDecimalItem> createNSAL0300Parameters(NWAL1570_ABMsg recordMsg, NWAL1570BMsg scrnMsg) {

        List<EZDBBigDecimalItem> param = new ArrayList<EZDBBigDecimalItem>();

        // [0]:DS Contract PK
        param.add(scrnMsg.dsContrPk);

        return param;
    }

}
