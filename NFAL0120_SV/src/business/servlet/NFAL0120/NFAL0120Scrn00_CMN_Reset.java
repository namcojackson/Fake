/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0120.NFAL0120CMsg;
import business.servlet.NFAL0120.common.NFAL0120CommonLogic;
import business.servlet.NFAL0120.constant.NFAL0120Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0120Scrn00_CMN_Reset
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0120Scrn00_CMN_Reset extends S21CommonHandler implements NFAL0120Constant {

    /** Singleton instance. */
    private NFAL0120CommonLogic common = new NFAL0120CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        NFAL0120CMsg bizMsg = new NFAL0120CMsg();
       
        /*
        scrnMsg.glDt_FR.setValue(scrnMsg.glDt_FT.getValue());
        scrnMsg.glDt_TO.setValue(scrnMsg.glDt_TT.getValue());

        scrnMsg.sysSrcCd_3.setValue(scrnMsg.sysSrcCd_T.getValue());
        scrnMsg.trxCd_3.setValue(scrnMsg.trxCd_T.getValue());
        scrnMsg.trxRsnCd_3.setValue(scrnMsg.trxRsnCd_T.getValue());
        scrnMsg.sysSrcNm.setValue(scrnMsg.sysSrcNm_T.getValue());
        scrnMsg.trxNm.setValue(scrnMsg.trxNm_T.getValue());
        scrnMsg.trxRsnNm.setValue(scrnMsg.trxRsnNm_T.getValue());

        scrnMsg.ajeId.setValue(scrnMsg.ajeId_T.getValue());
        scrnMsg.coaBrCd_3.setValue(scrnMsg.coaBrCd_T.getValue());
        scrnMsg.coaCcCd.setValue(scrnMsg.coaCcCd_T.getValue());
        scrnMsg.coaAcctCd.setValue(scrnMsg.coaAcctCd_T.getValue());
        scrnMsg.drCoaProdCd.setValue(scrnMsg.drCoaProdCd_T.getValue());
        scrnMsg.coaChCd_3.setValue(scrnMsg.coaChCd_T.getValue());
        scrnMsg.coaAfflCd.setValue(scrnMsg.coaAfflCd_T.getValue());
        scrnMsg.coaProjCd.setValue(scrnMsg.coaProjCd_T.getValue());
        scrnMsg.billToCustCd.setValue(scrnMsg.billToCustCd_T.getValue());
        scrnMsg.vndCd.setValue(scrnMsg.vndCd_T.getValue());
        scrnMsg.ajeInvNum.setValue(scrnMsg.ajeInvNum_T.getValue());
        scrnMsg.prmoPk.setValue(scrnMsg.prmoPk_T.getValue());
        scrnMsg.ajeItemCd.setValue(scrnMsg.ajeItemCd_T.getValue());
        scrnMsg.coaProdCd.setValue(scrnMsg.coaProdCd_T.getValue());
        scrnMsg.soNum.setValue(scrnMsg.soNum_T.getValue());

*/
        bizMsg.setBusinessID("NFAL0120");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFAL0120CommonLogic.initFocusItem(scrnMsg);
        common.changeTableColorByRow(scrnMsg);
    }

}
