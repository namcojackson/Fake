/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6700.NMAL6700CMsg;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/15   Fujitsu         T.Murai         update          #3784
 * 2016/06/29   Fujitsu         M.Yamada        update          QC#19270
 * 2017/07/14   Hitachi         J.Kim           update          QC#19270
 *</pre>
 */
public class NMAL6700_NMAL6770 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2017/07/14 J.Kim [QC#19270,DEL]
        // if ("CMN_Close".equals(getLastGuard())) {
        // return null;
        // }
        // END 2017/07/14 J.Kim [QC#19270,DEL]

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        if (NMAL6700Constant.BTN_OPEN_WIN_CTAC_SEARCH.equals(scrnMsg.xxScrEventNm.getValue())) {
            NMAL6700CMsg bizMsg = new NMAL6700CMsg();
            bizMsg.setBusinessID("NMAL6700");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        } else {
            StringBuffer ctacPsnPkList = new StringBuffer();
            for (int i = 0; i < scrnMsg.Q.length(); i++) {
                BigDecimal ctacPsnPk = scrnMsg.Q.no(i).ctacPsnPk_Q2.getValue();
                if (ZYPCommonFunc.hasValue(ctacPsnPk)) {
                    if (ctacPsnPkList.length() > 0) {
                        ctacPsnPkList.append(NMAL6700Constant.CHAR_COMMA);
                    }
                    ctacPsnPkList.append(ctacPsnPk.toPlainString());
                } else {
                    break;
                }
            }
            // START 2017/07/14 J.Kim [QC#19270,ADD]
            if (ctacPsnPkList.length() == 0) {
                return null;
            }
            // END 2017/07/14 J.Kim [QC#19270,ADD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.G.no(scrnMsg.xxCellIdx.getValueInt()).xxGenlFldAreaTxt_G2, ctacPsnPkList.toString());

            NMAL6700CMsg bizMsg = new NMAL6700CMsg();
            bizMsg.setBusinessID("NMAL6700");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        // START 2017/07/14 J.Kim [QC#19270,DEL]
        // if ("CMN_Close".equals(getLastGuard())) {
        // return;
        // }
        // END 2017/07/14 J.Kim [QC#19270,DEL]

        if (NMAL6700Constant.BTN_OPEN_WIN_CTAC_SEARCH.equals(scrnMsg.xxScrEventNm.getValue())) {
            NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NMAL6700CommonLogic.controlScreenFields(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
            NMAL6700CommonLogic.setBgColor(scrnMsg);
        } else {
            NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;
            // START 2017/07/14 J.Kim [QC#19270,ADD]
            if (bizMsg == null) {
                return;
            }
            // END 2017/07/14 J.Kim [QC#19270,ADD]
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            scrnMsg.setFocusItem(scrnMsg.G.no(scrnMsg.xxCellIdx.getValueInt()).xxGenlFldAreaTxt_G2);
        }
    }
}
