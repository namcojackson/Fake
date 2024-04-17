/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1880;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1880.NWAL1880CMsg;
import business.servlet.NWAL1880.constant.NWAL1880Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL1880_NMAL6870 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;

        //NWAL1880CMsg bizMsg = new NWAL1880CMsg();
        //bizMsg.setBusinessID("NWAL1880");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            if (i != 0) {
                sb.append(scrnMsg.xxSplCharTxt.getValue());
            }
            if ("OpenWin_Team".equals(scrEventNm)) {
                sb.append(scrnMsg.Z.no(i).xxComnScrColValTxt_1.getValue());
            } else if ("OpenWin_Zone".equals(scrEventNm)) {
                sb.append(scrnMsg.Z.no(i).xxComnScrColValTxt_1.getValue());
            } else if ("OpenWin_OrderProcessor".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxOrdTeamSrchTxt)) {
                    sb.append(scrnMsg.Z.no(i).xxComnScrColValTxt_1.getValue());
                } else {
                    sb.append(scrnMsg.Z.no(i).xxComnScrColValTxt_0.getValue());
                }
            }
        }

        String srchtxt = sb.toString();

        if ("OpenWin_Team".equals(scrEventNm)) {
            if (NWAL1880Constant.SRCH_TXT_MAX < sb.length()) {
                scrnMsg.setMessageInfo(NWAL1880Constant.NWAM0860W, new String[] {scrnMsg.xxOrdTeamSrchTxt.getNameForMessage()});
                srchtxt = sb.substring(0, NWAL1880Constant.SRCH_TXT_MAX);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdTeamSrchTxt, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxOrdTeamSrchTxt);
        } else if ("OpenWin_Zone".equals(scrEventNm)) {
            if (NWAL1880Constant.SRCH_TXT_MAX < sb.length()) {
                scrnMsg.setMessageInfo(NWAL1880Constant.NWAM0860W, new String[] {scrnMsg.xxOrdZnSrchTxt.getNameForMessage()});
                srchtxt = sb.substring(0, NWAL1880Constant.SRCH_TXT_MAX);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdZnSrchTxt, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxOrdZnSrchTxt);
        } else if ("OpenWin_OrderProcessor".equals(scrEventNm)) {
            if (NWAL1880Constant.SRCH_TXT_MAX < sb.length()) {
                scrnMsg.setMessageInfo(NWAL1880Constant.NWAM0860W, new String[] {scrnMsg.xxCratByUsrSrchTxt.getNameForMessage()});
                srchtxt = sb.substring(0, NWAL1880Constant.SRCH_TXT_MAX);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCratByUsrSrchTxt, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxCratByUsrSrchTxt);
        }

    }
}
