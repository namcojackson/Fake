/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL2080.NFBL2080CMsg;
import business.servlet.NFBL2080.common.NFBL2080CommonLogic;
import business.servlet.NFBL2080.constant.NFBL2080Constant.FLG;
import business.servlet.NFBL2080.constant.NFBL2080Constant.TAB;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 *</pre>
 */
public class NFBL2080Scrn00_Click_vndInvNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        NFBL2080CMsg bizMsg = new NFBL2080CMsg();
        bizMsg.setBusinessID("NFBL2080");
        bizMsg.setFunctionCode("20");
        scrnMsg.xxDplyTab.setValue(TAB.Header.name());
        //set selection row
        setSelectRow(scrnMsg, getButtonSelectNumber());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;
        NFBL2080CMsg bizMsg  = (NFBL2080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFBL2080CommonLogic.setHyoSettings(scrnMsg, this);
    }

    /**
     * set selection row
     * @param scrnMsg
     * @param selectRow
     */
    private void setSelectRow(NFBL2080BMsg scrnMsg, int selectRow) {

        for(int iCnt=0; iCnt<scrnMsg.A.getValidCount(); iCnt++ ){
            scrnMsg.A.no(iCnt).xxChkBox_A2.setValue(FLG.N.name());
        }

    	ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectRow).xxChkBox_A2, FLG.Y.name());
    }
}
