/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.EVENT_NM_OPENWIN_MULTICTRY;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.EVENT_NM_OPENWIN_MULTICUST;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.EVENT_NM_OPENWIN_MULTICUSTGRP;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.EVENT_NM_OPENWIN_MULTIST;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NMAM8090W;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7230_NMAL6870
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7230_NMAL6870 extends S21CommonHandler {

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

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        StringBuilder resStr = new StringBuilder();

        if (!ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt_0.getValue())
                && !ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt_1.getValue())) {
            if (EVENT_NM_OPENWIN_MULTICTRY.equals(scrnMsg.xxScrEventNm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt_HC);

            } else if (EVENT_NM_OPENWIN_MULTIST.equals(scrnMsg.xxScrEventNm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt_HS);

            } else if (EVENT_NM_OPENWIN_MULTICUST.equals(scrnMsg.xxScrEventNm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt_HA);

            } else if (EVENT_NM_OPENWIN_MULTICUSTGRP.equals(scrnMsg.xxScrEventNm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt_HG);

            }

        } else if (EVENT_NM_OPENWIN_MULTICTRY.equals(scrnMsg.xxScrEventNm.getValue())) {

            for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {

                if (i != 0) {
                    resStr.append(",");
                }

                resStr.append(scrnMsg.P.no(i).xxComnScrColValTxt_0.getValue());
            }

            if (resStr.length() <= scrnMsg.getAttr(NMAL7230Bean.xxDsMultMsgDplyTxt_HC).getDigit()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt_HC, resStr.toString());
            } else {
                scrnMsg.setMessageInfo(NMAM8090W, new String[] {scrnMsg.xxDsMultMsgDplyTxt_HC.getNameForMessage()});
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt_HC, resStr.toString().substring(0, scrnMsg.getAttr(NMAL7230Bean.xxDsMultMsgDplyTxt_HC).getDigit()));
            }

            scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt_HC);
        } else if (EVENT_NM_OPENWIN_MULTIST.equals(scrnMsg.xxScrEventNm.getValue())) {

            for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {

                if (i != 0) {
                    resStr.append(",");
                }

                resStr.append(scrnMsg.P.no(i).xxComnScrColValTxt_0.getValue());
            }

            if (resStr.length() <= scrnMsg.getAttr(NMAL7230Bean.xxDsMultMsgDplyTxt_HS).getDigit()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt_HS, resStr.toString());
            } else {
                scrnMsg.setMessageInfo(NMAM8090W, new String[] {scrnMsg.xxDsMultMsgDplyTxt_HS.getNameForMessage()});
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt_HS, resStr.toString().substring(0, scrnMsg.getAttr(NMAL7230Bean.xxDsMultMsgDplyTxt_HS).getDigit()));
            }

            scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt_HS);
        } else if (EVENT_NM_OPENWIN_MULTICUST.equals(scrnMsg.xxScrEventNm.getValue())) {

            for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {

                if (i != 0) {
                    resStr.append(",");
                }

                resStr.append(scrnMsg.P.no(i).xxComnScrColValTxt_1.getValue());
            }

            if (resStr.length() <= scrnMsg.getAttr(NMAL7230Bean.xxDsMultMsgDplyTxt_HA).getDigit()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt_HA, resStr.toString());
            } else {
                scrnMsg.setMessageInfo(NMAM8090W, new String[] {scrnMsg.xxDsMultMsgDplyTxt_HA.getNameForMessage()});
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt_HA, resStr.toString().substring(0, scrnMsg.getAttr(NMAL7230Bean.xxDsMultMsgDplyTxt_HA).getDigit()));
            }

            scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt_HA);
        } else if (EVENT_NM_OPENWIN_MULTICUSTGRP.equals(scrnMsg.xxScrEventNm.getValue())) {

            for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {

                if (i != 0) {
                    resStr.append(",");
                }

                resStr.append(scrnMsg.P.no(i).xxComnScrColValTxt_0.getValue());
            }

            if (resStr.length() <= scrnMsg.getAttr(NMAL7230Bean.xxDsMultMsgDplyTxt_HG).getDigit()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt_HG, resStr.toString());
            } else {
                scrnMsg.setMessageInfo(NMAM8090W, new String[] {scrnMsg.xxDsMultMsgDplyTxt_HG.getNameForMessage()});
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsMultMsgDplyTxt_HG, resStr.toString().substring(0, NMAL7230Bean.xxDsMultMsgDplyTxt_HG.length()));
            }

            scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt_HG);
        }

    }
}
