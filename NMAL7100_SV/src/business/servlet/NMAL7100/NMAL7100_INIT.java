/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.BIZ_ID;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.NMAM8234I;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7100.NMAL7100CMsg;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 * 01/19/2016   Fujitsu         M.Hara          Update          QC#3002
 * 09/12/2016   Fujitsu         R.Nakamura      Update          QC#11615
 * 02/24/2017   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7100_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        NMAL7100CMsg bizMsg = new NMAL7100CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null) {
            if (params[0] instanceof EZDBBigDecimalItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcMktPrmoPk_BK, (EZDBBigDecimalItem) params[0]);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcMktPrmoPk_BK, (BigDecimal) params[0]);
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        NMAL7100CMsg bizMsg  = (NMAL7100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Mod Start 2017/02/27 QC#16011
//        setScrnCtrl(scrnMsg);
        NMAL7100CommonLogic.setScrnCtrl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/27 QC#16011

        // Mod Start 2017/02/27 QC#16011
//        NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg);
//        NMAL7100CommonLogic.initCmnBtnProp(this);
        NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
        NMAL7100CommonLogic.initCmnBtnProp(this, getUserProfileService());
        // Mod End 2017/02/27 QC#16011

        scrnMsg.setFocusItem(scrnMsg.prcMktPrmoPk_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg msg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) msg;

        // START QC#3002 01/19/2016 MOD
        scrnMsg.prcMktPrmoPk_H1.setNameForMessage("Marketing Program ID");
        scrnMsg.prcMktPrmoNm_H1.setNameForMessage("Marketing Program Name");
        scrnMsg.prcMktPrmoDescTxt_H1.setNameForMessage("Marketing Program Description");
        scrnMsg.prcMktPrmoCmntTxt_H1.setNameForMessage("Comments/Notes List");
        scrnMsg.prcMktPrmoTpCd_H1.setNameForMessage("Marketing Program Type");
        scrnMsg.effFromDt_H1.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt_H1.setNameForMessage("Effective Date To");
        scrnMsg.effThruDt_DH.setNameForMessage("Effective Date To");

        for (int i = 0; i < scrnMsg.X.length(); i++) {
            scrnMsg.X.no(i).mktPrmoAudcCatgCd_X1.setNameForMessage("Audience Category 1");
            scrnMsg.X.no(i).xxScrItem30Txt_X1.setNameForMessage("Audience Value 1");
            scrnMsg.X.no(i).xxRecNmTxt_X1.setNameForMessage("Audience Name 1");
            scrnMsg.X.no(i).mktPrmoAudcCatgCd_X2.setNameForMessage("Audience Category 2");
            scrnMsg.X.no(i).xxScrItem30Txt_X2.setNameForMessage("Audience Value 2");
            scrnMsg.X.no(i).xxRecNmTxt_X2.setNameForMessage("Audience Name 2");
            scrnMsg.X.no(i).mktPrmoAudcCatgCd_X3.setNameForMessage("Audience Category 3");
            scrnMsg.X.no(i).xxScrItem30Txt_X3.setNameForMessage("Audience Value 3");
            scrnMsg.X.no(i).xxRecNmTxt_X3.setNameForMessage("Audience Name 3");
            scrnMsg.X.no(i).effFromDt_MX.setNameForMessage("Effective Date From");
            scrnMsg.X.no(i).effThruDt_MX.setNameForMessage("Effective Date To");
        }

        for (int i = 0; i < scrnMsg.Y.length(); i++) {
            scrnMsg.Y.no(i).prcCatgCd_CX.setNameForMessage("Price List Type");
            scrnMsg.Y.no(i).effFromDt_CX.setNameForMessage("Effective Date From");
            scrnMsg.Y.no(i).effThruDt_CX.setNameForMessage("Effective Date To");
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcMktPrmoCd_DA.setNameForMessage("Marketing Program Code");
            scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA.setNameForMessage("Promotion Qualifier");
            scrnMsg.A.no(i).prcQlfyValTxt_DA.setNameForMessage("Qualifier Value");
            scrnMsg.A.no(i).mdseCd_DA.setNameForMessage("Promotion Item Code");
            scrnMsg.A.no(i).prmoAmt_DA.setNameForMessage("Promotion Value");
            scrnMsg.A.no(i).effFromDt_DA.setNameForMessage("Date From");
            scrnMsg.A.no(i).effThruDt_DA.setNameForMessage("Date To");
        }

        // END QC#3002 01/19/2016 MOD

        // Script Message.
        ZYPEZDItemValueSetter.setValue(scrnMsg.exMsgTxt_01, S21MessageFunc.clspGetMessage(NMAM8234I).substring(10));
    }

    // Del Start 2017/02/24 QC#16011
//    private void setScrnCtrl(NMAL7100BMsg scrnMsg) {
//        for (int i = 0; i < scrnMsg.X.length(); i++) {
//            scrnMsg.X.no(i).xxRecNmTxt_X1.setInputProtected(true);
//            scrnMsg.X.no(i).xxRecNmTxt_X2.setInputProtected(true);
//            scrnMsg.X.no(i).xxRecNmTxt_X3.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.X.length(); i++) {
//            scrnMsg.Y.no(i).prcCatgNm_CX.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.A.length(); i++) {
//            // Mod Start 2016/09/12 QC#11615
////            scrnMsg.A.no(i).mdseNm_DA.setInputProtected(true);
//            scrnMsg.A.no(i).mdseDescShortTxt_DA.setInputProtected(true);
//            // Mod End 2016/09/12 QC#11615
//            scrnMsg.A.no(i).xxFullNm_D1.setInputProtected(true);
//            scrnMsg.A.no(i).ezInTime_DA.setInputProtected(true);
//            scrnMsg.A.no(i).xxCratDt_DA.setInputProtected(true);
//            scrnMsg.A.no(i).xxFullNm_D2.setInputProtected(true);
//            scrnMsg.A.no(i).ezUpTime_DA.setInputProtected(true);
//            scrnMsg.A.no(i).xxLastEntryDt_DA.setInputProtected(true);
//        }
//    }
    // Del End 2017/02/24 QC#16011
}
