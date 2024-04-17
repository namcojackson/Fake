/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL7110.NMAL7110CMsg;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import business.servlet.NMAL7110.common.NMAL7110CommonLogic;

import static business.servlet.NMAL7110.constant.NMAL7110Constant.BIZ_ID;
import static business.servlet.NMAL7110.constant.NMAL7110Constant.SCRN_ID_00;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NMAL7110_INIT extends S21INITCommonHandler  {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) bMsg;
        NMAL7110CMsg bizMsg = new NMAL7110CMsg();

        NMAL7110CommonLogic.initControl(scrnMsg);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) bMsg;
        NMAL7110CMsg bizMsg  = (NMAL7110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7110CommonLogic.initCmnBtnProp(this);
        NMAL7110CommonLogic.controlScreen(this, getUserProfileService(), scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg msg) {


        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) msg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.srchOptPk.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Search Option"));
        scrnMsg.srchOptNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name"));

        scrnMsg.prcContrNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price Contract Name"));
        scrnMsg.prcContrNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price Contract#"));
        scrnMsg.assnPgmContrFlg.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "CSAP Contract"));
        scrnMsg.prcContrCustBidNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Bid#"));

        scrnMsg.lineBizTpCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Line of Business"));

        scrnMsg.effFromDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date From"));
        scrnMsg.effThruDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date To"));
        scrnMsg.actvFlg.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Active"));
    }
}
