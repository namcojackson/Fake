/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0120.NFAL0120CMsg;
import business.servlet.NFAL0120.common.NFAL0120CommonLogic;
import business.servlet.NFAL0120.constant.NFAL0120Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0120Scrn00_InquiryBtn
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0120Scrn00_InquiryBtn extends S21CommonHandler implements NFAL0120Constant {

    /** Singleton instance. */
    private NFAL0120CommonLogic common = new NFAL0120CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.glDt_FR);
        scrnMsg.addCheckItem(scrnMsg.glDt_TO);
        //scrnMsg.addCheckItem(scrnMsg.prmoPk);
        // START 2017/12/01 [QC#12525, MOD]
        // scrnMsg.addCheckItem(scrnMsg.coaAcctCd_FM);
        // scrnMsg.addCheckItem(scrnMsg.coaAcctCd_TO);
        scrnMsg.addCheckItem(scrnMsg.coaCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.coaBrCd);
        scrnMsg.addCheckItem(scrnMsg.coaCcCd);
        scrnMsg.addCheckItem(scrnMsg.coaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd);
        scrnMsg.addCheckItem(scrnMsg.coaChCd);
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.coaProjCd);
        scrnMsg.addCheckItem(scrnMsg.coaExtnCd);
        // END   2017/12/01 [QC#12525, MOD]
        scrnMsg.addCheckItem(scrnMsg.jrnlDealDrAmt_FM);
        scrnMsg.addCheckItem(scrnMsg.jrnlDealDrAmt_TO);
        scrnMsg.addCheckItem(scrnMsg.glPerNm);
        
        scrnMsg.putErrorScreen();
        
        //---- start add 2016/05/31
        NFAL0120CommonLogic.chkGlDtFromTo(scrnMsg);
        // START 2017/12/01 [QC#12525, DEL]
        // NFAL0120CommonLogic.chkCoaAcctFromTo(scrnMsg);
        // END   2017/12/01 [QC#12525, DEL]
        NFAL0120CommonLogic.chkAmountFromTo(scrnMsg);
        NFAL0120CommonLogic.chkPerNm(scrnMsg);
        
        scrnMsg.putErrorScreen();
        
        if (!NFAL0120CommonLogic.chkReqdFields(scrnMsg)) {
            //scrnMsg.glDt_FR.setErrorInfo(1, "NFAM0158E");
            //scrnMsg.addCheckItem(scrnMsg.glDt_FR);
            // START 2017/12/04 [QC#12525, MOD]
            if (ZYPCommonFunc.hasValue(scrnMsg.ajeTrxTpCd_3)) {
                scrnMsg.xxQueryFltrTxt_AT.setErrorInfo(1, "ZZM9000E", new String[] {"Transaction Type" });
                scrnMsg.addCheckItem(scrnMsg.xxQueryFltrTxt_AT);
                scrnMsg.putErrorScreen();

            } else {
                scrnMsg.glDt_FR.setErrorInfo(1, "NFAM0206E");
                scrnMsg.glPerNm.setErrorInfo(1, "NFAM0206E");
                scrnMsg.addCheckItem(scrnMsg.glDt_FR);
                scrnMsg.addCheckItem(scrnMsg.glPerNm);
                scrnMsg.setMessageInfo("NFAM0206E");
                scrnMsg.putErrorScreen();
            }
            // END   2017/12/04 [QC#12525, MOD]
            
            throw new EZDFieldErrorException();
        }
        //---- end 2016/05/31
        
        // CSA change - remove this restriction
        // accounting date from to should be in the same month
        /*if (common.chkGlDtRange(scrnMsg) == false) {
           scrnMsg.glDt_FR.setErrorInfo(1, "NFAM0076E");
           scrnMsg.glDt_TO.setErrorInfo(1, "NFAM0076E");
           scrnMsg.addCheckItem(scrnMsg.glDt_FR);
           scrnMsg.addCheckItem(scrnMsg.glDt_TO);
        }
        */
        
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        NFAL0120CMsg bizMsg = new NFAL0120CMsg();
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

        /*
        scrnMsg.addCheckItem(scrnMsg.ajeId);
        scrnMsg.addCheckItem(scrnMsg.coaCcCd);
        scrnMsg.addCheckItem(scrnMsg.coaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.drCoaProdCd);
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.coaProjCd);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.ajeInvNum);
        scrnMsg.addCheckItem(scrnMsg.prmoPk);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd);
        scrnMsg.addCheckItem(scrnMsg.soNum);
        */
        scrnMsg.putErrorScreen();

        // clear sort image
        S21SortColumnIMGController.clearIMG( SCRN_ID, scrnMsg, scrnMsg.A.no( 0 ).getBaseContents() );
        
     // CSA mod. 
        NFAL0120CommonLogic.initLink(this, scrnMsg);
        // ------ end
        
        NFAL0120CommonLogic.initFocusItem(scrnMsg);
        NFAL0120CommonLogic.setInputProtectedForListInputFiled(scrnMsg);
        NFAL0120CommonLogic.changeTableColorByRow(scrnMsg);
    }

}
