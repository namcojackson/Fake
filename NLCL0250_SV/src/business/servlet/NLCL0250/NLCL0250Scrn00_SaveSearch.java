/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0250.NLCL0250CMsg;
import business.servlet.NLCL0250.common.NLCL0250CommonLogic;
import business.servlet.NLCL0250.constant.NLCL0250Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3137
 *</pre>
 */
public class NLCL0250Scrn00_SaveSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;

        NLCL0250CommonLogic.addCheckItemHeader(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;

        StringBuffer stkStsSrchOptTxt = new StringBuffer();
        boolean stkStsfirstChkBox = true;

        for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.S.no(i).xxChkBox_SS.getValue())) {

                if (!stkStsfirstChkBox) {

                    stkStsSrchOptTxt.append(",");

                } else {

                    stkStsfirstChkBox = false;
                }

                stkStsSrchOptTxt.append(scrnMsg.S.no(i).stkStsCd_SS.getValue());
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.srchOptTxt_SS, stkStsSrchOptTxt.toString());

        StringBuffer locStsSrchOptTxt = new StringBuffer();
        boolean locStsfirstChkBox = true;

        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.L.no(i).xxChkBox_LS.getValue())) {

                if (!locStsfirstChkBox) {

                    locStsSrchOptTxt.append(",");

                } else {

                    locStsfirstChkBox = false;
                }

                locStsSrchOptTxt.append(scrnMsg.L.no(i).locStsCd_LS.getValue());
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.srchOptTxt_LS, locStsSrchOptTxt.toString());

        NLCL0250CMsg bizMsg = new NLCL0250CMsg();
        bizMsg.setBusinessID(NLCL0250Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;
        NLCL0250CMsg bizMsg = (NLCL0250CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.putErrorScreen();
    }
}
