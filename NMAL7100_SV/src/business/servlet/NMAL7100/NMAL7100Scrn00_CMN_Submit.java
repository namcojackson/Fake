/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.BIZ_ID;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7100.NMAL7100CMsg;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 * 01/18/2016   Fujitsu         M.Hara          Update          QC#3002
 * 09/12/2016   Fujitsu         R.Nakamura      Update          QC#11615
 * 2017/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7100Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        // Mod Start 2017/02/27 QC#16011
//        NMAL7100CommonLogic.checkAllItemInput(this, scrnMsg);
        NMAL7100CommonLogic.checkAllItemInput(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/27 QC#16011

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        NMAL7100CMsg bizMsg = new NMAL7100CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        NMAL7100CMsg bizMsg  = (NMAL7100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.prcMktPrmoPk_BK, scrnMsg.prcMktPrmoPk_H1);

        allCheckItem(scrnMsg);

        // Mod Start 2017/02/27 QC#16011
//        NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg);
        NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/27 QC#16011

    }

    private void allCheckItem(NMAL7100BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoPk_H1);
        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoCmntTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);

        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X1);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X2);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X3);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).mktPrmoAudcCatgCd_X2);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).mktPrmoAudcCatgCd_X3);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).effFromDt_MX);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).effThruDt_MX);
        }
        for (int i = 0; i < scrnMsg.Y.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).prcCatgCd_CX);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).prcCatgNm_CX);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).effFromDt_CX);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).effThruDt_CX);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcMktPrmoCd_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcQlfyValTxt_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_DA);
            // Mod Start 2016/09/12 QC#11615
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseNm_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseDescShortTxt_DA);
            // Mod End 2016/09/12 QC#11615
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prmoAmt_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_DA);
        }

        scrnMsg.putErrorScreen();
    }

}
