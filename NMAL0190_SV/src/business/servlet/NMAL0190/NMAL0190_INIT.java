/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0190;

import static business.servlet.NMAL0190.constant.NMAL0190Constant.BIZ_ID;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.NMAM0007I;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_COMPATIBLE_WITH;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_DATE_STAGED;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_ITEM_DESCRIPTION;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_ITEM_NUMBER;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_LAST_UPDATE_BY;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_LAST_UPDATE_DATE;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_RELATIONSHIP_BACKWARD;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_RELATIONSHIP_FORWARD;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_SUPD_DESCRIPTION;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_SUPERSEDES;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.TBL_LBL_SUPERSEDES_ITEM_STATUS;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0190.NMAL0190CMsg;
import business.servlet.NMAL0190.common.NMAL0190CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL0190_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         T.Arima          Create          N/A
 * 2016/03/02   CITS            S.Tanikawa      UPDATE          QC#2669
 *</pre>
 */
public class NMAL0190_INIT extends S21INITCommonHandler {

    @Override
    /**
     * Check Input Event
     * - Check Business App Granted.
     */
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    /**
     * Set Request Date Event
     */
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0190BMsg scrnMsg = (NMAL0190BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[0];

            scrnMsg.supdRelnStagePk_P.setValue(param01.getValue());
        } else {
         scrnMsg.setMessageInfo(NMAM0007I);
          return null;
        }

        NMAL0190CMsg bizMsg = new NMAL0190CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    /**
     * Do Process Event
     * - Set Protect Table
     *  * - Set Focus Item - Forward.
     */
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0190BMsg scrnMsg = (NMAL0190BMsg) bMsg;
        NMAL0190CMsg bizMsg = (NMAL0190CMsg) cMsg;

        // UPDATE START 2016/03/02 QC#2669
        // if (bizMsg != null) {
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // }

        NMAL0190CommonLogic.initCmnBtnProp(this);
        NMAL0190CommonLogic.setScrnLineProtected(scrnMsg);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox);
        scrnMsg.setFocusItem(scrnMsg.A.no(0).mdseItemStsCd_SL);
        // UPDATE END 2016/03/02 QC#2669
    }

    @Override
    /**
     * Set Name for Message Event.
     */
    protected void setNameForMessage(EZDBMsg bMsg) {

        // UPDATE START 2016/03/02 QC#2669
        NMAL0190BMsg scrnMsg = (NMAL0190BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NMAL0190_ABMsg aLineMsg = scrnMsg.A.no(i);
            aLineMsg.supdToMdseCd.setNameForMessage(TBL_LBL_ITEM_NUMBER);
            aLineMsg.mdseDescShortTxt_TO.setNameForMessage(TBL_LBL_ITEM_DESCRIPTION);
            // aLineMsg.xxChkBox.setNameForMessage(TBL_LBL_FORWARD);
            aLineMsg.supdFromMdseCd.setNameForMessage(TBL_LBL_SUPERSEDES);
            aLineMsg.mdseDescShortTxt_FR.setNameForMessage(TBL_LBL_SUPD_DESCRIPTION);
            // aLineMsg.dsctnFlg.setNameForMessage(TBL_LBL_PURCHASE_PROHIBIT);
            aLineMsg.mdseItemStsCd_SL.setNameForMessage(TBL_LBL_SUPERSEDES_ITEM_STATUS);
            aLineMsg.supdRelnStageDt.setNameForMessage(TBL_LBL_DATE_STAGED);
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            NMAL0190_BBMsg bLineMsg = scrnMsg.B.no(i);
            bLineMsg.relnMdseCd.setNameForMessage(TBL_LBL_COMPATIBLE_WITH);
            bLineMsg.xxSupdFlg_FW.setNameForMessage(TBL_LBL_RELATIONSHIP_FORWARD);
            bLineMsg.xxSupdFlg_BW.setNameForMessage(TBL_LBL_RELATIONSHIP_BACKWARD);
            bLineMsg.xxAuthByNm.setNameForMessage(TBL_LBL_LAST_UPDATE_BY);
            bLineMsg.ezUpUserID_F.setNameForMessage(TBL_LBL_LAST_UPDATE_BY);
            bLineMsg.ezUpTime_F.setNameForMessage(TBL_LBL_LAST_UPDATE_DATE);
        }
        // UPDATE END 2016/03/02 QC#2669
    }

}
