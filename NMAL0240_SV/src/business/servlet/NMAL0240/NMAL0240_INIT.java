/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0240;

import static business.servlet.NMAL0240.constant.NMAL0240Constant.BIZ_ID;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.FUNC_CD_20;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0240.NMAL0240CMsg;
import business.servlet.NMAL0240.common.NMAL0240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL0240_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/18   SRAA            Y.Chen          Update          QC#2645
 * 2018/03/14   CITS            K.Ogino         Update          QC#22324
 *</pre>
 */
public class NMAL0240_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;
        NMAL0240CMsg bizMsg = new NMAL0240CMsg();

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, (EZDBStringItem) params[0]);
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;
        NMAL0240CMsg bizMsg = (NMAL0240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0240CommonLogic.initCmnBtnProp(this, getUserProfileService());
        NMAL0240CommonLogic.controlFields(this, scrnMsg, false);
        NMAL0240CommonLogic.controlRevisionFields(this, scrnMsg);
        // QC#2645
        NMAL0240CommonLogic.controlTableRowColor(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.mdseCd);
        // QC#22324
        ZYPTableUtil.clear(scrnMsg.R);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

        scrnMsg.mdseItemTpCd.setNameForMessage("BOM Type");
        scrnMsg.mdseCd.setNameForMessage("BOM Item#");
        scrnMsg.mdseDescShortTxt.setNameForMessage("BOM Item Description");
        scrnMsg.coaMdseTpCd.setNameForMessage("BOM Merch Type");
        scrnMsg.xxScrItem61Txt.setNameForMessage("BOM Product Code");
        scrnMsg.actvFlg.setNameForMessage("BOM Active");
        scrnMsg.xxChkBox.setNameForMessage("CUSA Set");

        scrnMsg.cmpsnRevnNum_A.setNameForMessage("Revision");
        scrnMsg.cmpsnRevnCmntTxt_A.setNameForMessage("Revision Comments");
        scrnMsg.effFromDt_A.setNameForMessage("Start Date");
        scrnMsg.effThruDt_A.setNameForMessage("End Date");

        scrnMsg.cmpsnRevnNum_B.setNameForMessage("Revision");
        scrnMsg.cmpsnRevnCmntTxt_B.setNameForMessage("Revision Comments");
        scrnMsg.effFromDt_B.setNameForMessage("Start Date");
        scrnMsg.effThruDt_B.setNameForMessage("End Date");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdseCd_A1.setNameForMessage("Component");
            scrnMsg.A.no(i).childMdseQty_A1.setNameForMessage("Quantity");
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).mdseCd_B1.setNameForMessage("Component");
            scrnMsg.B.no(i).childMdseQty_B1.setNameForMessage("Quantity");
        }
    }
}
