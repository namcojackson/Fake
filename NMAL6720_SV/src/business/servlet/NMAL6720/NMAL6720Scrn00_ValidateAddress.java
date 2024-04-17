/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6720.NMAL6720CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   SRAA            Y.Chen          Create          QC#4505
 *</pre>
 */
public class NMAL6720Scrn00_ValidateAddress extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.scdLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.thirdLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.frthLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.stCd_P1);
        scrnMsg.addCheckItem(scrnMsg.postCd_H1);
        scrnMsg.addCheckItem(scrnMsg.cntyNm_H1);
        scrnMsg.addCheckItem(scrnMsg.provNm_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID("NMAL6720");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("W".equals(scrnMsg.xxRsltCd_V1.getValue())) {
            this.setResult("SUGGEST");
            Object[] params = new Object[18];

            params[0] = scrnMsg.firstLineAddr_H1;
            params[1] = scrnMsg.scdLineAddr_H1;
            params[2] = scrnMsg.thirdLineAddr_H1;
            params[3] = scrnMsg.frthLineAddr_H1;
            params[4] = scrnMsg.ctyAddr_H1;
            params[5] = scrnMsg.stCd_P1;
            params[6] = scrnMsg.postCd_H1;
            params[7] = scrnMsg.cntyNm_H1;
            params[8] = scrnMsg.provNm_H1;

            params[9] = scrnMsg.firstLineAddr_S1;
            params[10] = scrnMsg.scdLineAddr_S1;
            params[11] = scrnMsg.thirdLineAddr_S1;
            params[12] = scrnMsg.frthLineAddr_S1;
            params[13] = scrnMsg.ctyAddr_S1;
            params[14] = scrnMsg.stCd_S1;
            params[15] = scrnMsg.postCd_S1;
            params[16] = scrnMsg.cntyNm_S1;
            params[17] = scrnMsg.provNm_S1;

            setArgForSubScreen(params);
        } else {
            this.setResult("NO_SUGGEST");

            scrnMsg.addCheckItem(scrnMsg.firstLineAddr_H1);
            scrnMsg.addCheckItem(scrnMsg.scdLineAddr_H1);
            scrnMsg.addCheckItem(scrnMsg.thirdLineAddr_H1);
            scrnMsg.addCheckItem(scrnMsg.frthLineAddr_H1);
            scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
            scrnMsg.addCheckItem(scrnMsg.stCd_P1);
            scrnMsg.addCheckItem(scrnMsg.postCd_H1);
            scrnMsg.addCheckItem(scrnMsg.cntyNm_H1);
            scrnMsg.addCheckItem(scrnMsg.provNm_H1);
            scrnMsg.putErrorScreen();

            scrnMsg.setFocusItem(scrnMsg.firstLineAddr_H1);
        }
    }
}
