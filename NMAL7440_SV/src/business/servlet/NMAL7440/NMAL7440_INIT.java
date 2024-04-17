/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7440;

import static business.servlet.NMAL7440.constant.NMAL7440Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7440.NMAL7440CMsg;
import business.servlet.NMAL7440.common.NMAL7440CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * NMAL7440_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/05   Fujitsu         T.Noguchi       Create          QC#29324
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7440_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7440BMsg scrnMsg = (NMAL7440BMsg) bMsg;

        Object[] arg = (Object[]) getArgForSubScreen();
        NMAL7440CommonLogic.setInputParam(scrnMsg, arg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7440BMsg scrnMsg = (NMAL7440BMsg) bMsg;

        NMAL7440CMsg bizMsg = new NMAL7440CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7440BMsg scrnMsg = (NMAL7440BMsg) bMsg;
        NMAL7440CMsg bizMsg  = (NMAL7440CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7440CommonLogic.initCmnBtnProp(this);
        NMAL7440CommonLogic.setScrnCtrl(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7440BMsg scrnMsg = (NMAL7440BMsg) bMsg;

        scrnMsg.prcGrpPk.setNameForMessage("Price Group ID");
        scrnMsg.prcGrpNm.setNameForMessage("Price Group Name");
        scrnMsg.prcGrpTrgtTpCd.setNameForMessage("Target Context");
        scrnMsg.prcGrpTrgtAttrbCd.setNameForMessage("Target Attribute Name");
        scrnMsg.xxPrcQlfyValSrchTxt_TF.setNameForMessage("Target From");
        // 2023/04/20 QC#61200 Add Start
        scrnMsg.dsAcctNm.setNameForMessage("Description");
        // 2023/04/20 QC#61200 Add End
        scrnMsg.xxPrcQlfyValSrchTxt_TT.setNameForMessage("Target To");
        scrnMsg.prcGrpPrcdNum.setNameForMessage("Precedence#");
        scrnMsg.effFromDt_H1.setNameForMessage("Date From");
        scrnMsg.effFromDt_H2.setNameForMessage("Date From");
        scrnMsg.effThruDt_H1.setNameForMessage("Date To");
        scrnMsg.effThruDt_H2.setNameForMessage("Date To");
    }
}
