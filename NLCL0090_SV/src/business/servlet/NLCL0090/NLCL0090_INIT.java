/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLCL0090;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0090.NLCL0090CMsg;
import business.servlet.NLCL0090.common.NLCL0090CommonLogic;
import business.servlet.NLCL0090.constant.NLCL0090Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Item Change Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/30/2009   Fujitsu         FXS)KF.Qian     Create          N/A
 * 01/28/2010   Fujitsu         M.Yamada        Update          Change Word from 'TOC' to 'Sales Rep'
 * 04/15/2010   Fujitsu         S.Yoshida       Update          Def.5017
 * 03/03/2016   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public class NLCL0090_INIT extends S21INITCommonHandler implements NLCL0090Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;
        NLCL0090CMsg bizMsg = new NLCL0090CMsg();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            int paramCnt = params.length;

            if (paramCnt > 0) {

                EZDBStringItem param00 = (EZDBStringItem) params[0];

                if (ZYPCommonFunc.hasValue(param00)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.invtyOrdNum_IN, param00);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.invtyOrdNum_BK, param00);
                }
            }
        }

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0090CMsg bizMsg = (NLCL0090CMsg) cMsg;
        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0090CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            NLCL0090CommonLogic.initScrnControlErr(this, scrnMsg);
        }

        scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;

        scrnMsg.rtlWhCd.setNameForMessage("Warehouse");
        scrnMsg.rtlSwhCd.setNameForMessage("Sub Warehouse");
        scrnMsg.locStsCd.setNameForMessage("Location Status");
        scrnMsg.stkStsCd.setNameForMessage("Stock Status");
        scrnMsg.firstInvtyOrdCmntTxt.setNameForMessage("Comment");
        scrnMsg.scdInvtyOrdCmntTxt.setNameForMessage("Comment");
        scrnMsg.thirdInvtyOrdCmntTxt.setNameForMessage("Comment");
        scrnMsg.mdseCd_HF.setNameForMessage("Item Number");
        scrnMsg.mdseCd_HT.setNameForMessage("Item Number");
        scrnMsg.invtyQty_HF.setNameForMessage("Quantity");
        scrnMsg.invtyQty_HT.setNameForMessage("Quantity");
        scrnMsg.serNum_HF.setNameForMessage("Serial Number");
        scrnMsg.serNum_HT.setNameForMessage("Serial Number");

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).mdseCd_DF.setNameForMessage("Item Number");
            scrnMsg.A.no(i).invtyQty_DF.setNameForMessage("Quantity");
            scrnMsg.A.no(i).invtyAvalQty_DF.setNameForMessage("Current Available");
            scrnMsg.A.no(i).serNum_DF.setNameForMessage("Serial Number");
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {

            scrnMsg.B.no(i).mdseCd_DT.setNameForMessage("Item Number");
            scrnMsg.B.no(i).invtyQty_DT.setNameForMessage("Quantity");
            scrnMsg.B.no(i).serNum_DT.setNameForMessage("Serial Number");
        }
    }
}
