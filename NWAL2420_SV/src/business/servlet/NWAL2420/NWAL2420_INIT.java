/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2420;

import static business.servlet.NWAL2420.constant.NWAL2420Constant.BTN_10_CLS_GUARD;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.BTN_10_CLS_LABEL;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.BTN_10_CLS_NAME;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.DETAIL_TABLE_ID;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_6;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_AUTHORIZATION_COMMENT;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_EMAIL_ADDRESS;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_RETURN_SUPPLIES;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.IDX_SUFFIX;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.MAX_DETAIL;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.NWAM0270E;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.RADIO_BTN_CUSTOMER;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.SCRN_ID_00;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2420.common.NWAL2420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/14   CUSA            H.Tomimatsu     Create          S21_NA#22139
 *</pre>
 */
public class NWAL2420_INIT extends S21INITCommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2420BMsg scrnMsg = (NWAL2420BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params != null && params.length == IDX_6) {

                EZDMsg.copy((EZDMsgArray) params[IDX_EMAIL_ADDRESS], (String) params[IDX_SUFFIX], scrnMsg.A, DETAIL_TABLE_ID);

                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    if (!scrnMsg.A.no(i).ctacPsnEmlAddr_A.isClear()) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
                    }
                }

                scrnMsg.A.setValidCount(MAX_DETAIL);

                EZDBStringItem rmaRptTpCd = (EZDBStringItem) params[IDX_RETURN_SUPPLIES];

                if (ZYPCommonFunc.hasValue(rmaRptTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rmaRptTpCd, rmaRptTpCd.getValue());

                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rmaRptTpCd, RADIO_BTN_CUSTOMER);
                }

                EZDBStringItem rtrnAuthCmntTxt01 = (EZDBStringItem) params[IDX_AUTHORIZATION_COMMENT];

                if (ZYPCommonFunc.hasValue(rtrnAuthCmntTxt01.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnAuthCmntTxt_01, rtrnAuthCmntTxt01.getValue());

                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnAuthCmntTxt_01, "");
                }

            } else {

                scrnMsg.setMessageInfo(NWAM0270E);
                this.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 0, null);
                return;
            }
        } else {

            scrnMsg.setMessageInfo(NWAM0270E);
            this.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 0, null);
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_ML, ZYPConstant.CHKBOX_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRqstFlg_PR, ZYPConstant.CHKBOX_ON_Y);
        scrnMsg.setFocusItem(scrnMsg.A.no(0).ctacPsnEmlAddr_A);
        NWAL2420CommonLogic.initButton(this);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG(DETAIL_TABLE_ID, scrnMsg.A);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NWAL2420BMsg scrnMsg = (NWAL2420BMsg) bMsg;

        for (int i = 0; scrnMsg.A.length() > i; i++) {
            scrnMsg.A.no(i).ctacPsnEmlAddr_A.setNameForMessage("EMail Address");
        }

    }
}
