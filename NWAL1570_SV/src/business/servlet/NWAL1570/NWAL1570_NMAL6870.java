/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL1570.constant.NWAL1570Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL1570_NMAL6870 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
            return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            if (i != 0) {
                sb.append(scrnMsg.xxSplCharTxt.getValue());
            }
            sb.append(scrnMsg.Z.no(i).xxComnScrColValTxt_1.getValue());
        }

        String srchtxt = sb.toString();
        if (NWAL1570Constant.SRCH_TXT_MAX < sb.length()) {
            srchtxt = sb.substring(0, NWAL1570Constant.SRCH_TXT_MAX);
        }

        if ("OpenWin_OrderSource".equals(scrEventNm)) {
            if (NWAL1570Constant.SRCH_TXT_MAX < sb.length()) {
                scrnMsg.setMessageInfo(NWAL1570Constant.NWAM0860W, new String[] {scrnMsg.xxCpoSrcTpSrchTxt.getNameForMessage()});
                srchtxt = sb.substring(0, NWAL1570Constant.SRCH_TXT_MAX);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoSrcTpSrchTxt, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxCpoSrcTpSrchTxt);
        } else if ("OpenWin_LOB".equals(scrEventNm)) {
            if (NWAL1570Constant.SRCH_TXT_MAX < sb.length()) {
                scrnMsg.setMessageInfo(NWAL1570Constant.NWAM0860W, new String[] {scrnMsg.xxDsBizLineSrchTxt.getNameForMessage()});
                srchtxt = sb.substring(0, NWAL1570Constant.SRCH_TXT_MAX);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsBizLineSrchTxt, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxDsBizLineSrchTxt);
        } else if ("OpenWin_OrderCategory".equals(scrEventNm)) {
            if (NWAL1570Constant.SRCH_TXT_MAX < sb.length()) {
                scrnMsg.setMessageInfo(NWAL1570Constant.NWAM0860W, new String[] {scrnMsg.xxDsOrdCatgSrchTxt.getNameForMessage()});
                srchtxt = sb.substring(0, NWAL1570Constant.SRCH_TXT_MAX);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdCatgSrchTxt, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxDsOrdCatgSrchTxt);
        } else if ("OpenWin_OrderReason".equals(scrEventNm)) {
            if (NWAL1570Constant.SRCH_TXT_MAX < sb.length()) {
                scrnMsg.setMessageInfo(NWAL1570Constant.NWAM0860W, new String[] {scrnMsg.xxDsOrdTpSrchTxt.getNameForMessage()});
                srchtxt = sb.substring(0, NWAL1570Constant.SRCH_TXT_MAX);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdTpSrchTxt, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxDsOrdTpSrchTxt);
        } else if ("OpenWin_SubReason".equals(scrEventNm)) {
            if (NWAL1570Constant.SRCH_TXT_MAX < sb.length()) {
                scrnMsg.setMessageInfo(NWAL1570Constant.NWAM0860W, new String[] {scrnMsg.xxDsOrdRsnSrchTxt.getNameForMessage()});
                srchtxt = sb.substring(0, NWAL1570Constant.SRCH_TXT_MAX);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdRsnSrchTxt, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxDsOrdRsnSrchTxt);
        }

    }
}
