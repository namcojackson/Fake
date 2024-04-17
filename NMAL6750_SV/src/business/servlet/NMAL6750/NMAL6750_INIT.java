/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750;

import static business.servlet.NMAL6750.constant.NMAL6750Constant.ACCT_NUM;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BIZ_ID;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.LOC_NUM;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM8228E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM8229E;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6750.NMAL6750CMsg;
import business.servlet.NMAL6750.common.NMAL6750CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         C.Tanaka        Create          CSA
 * 2017/08/16   Fujitsu         H.Nagashima     Update          QC#8598
 * 2017/12/18   Fujitsu         Hd.Sugawara     Update          QC#22286
 *</pre>
 */
public class NMAL6750_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        scrnMsg.dsAcctNum_P1.clear();
        scrnMsg.locNum_P1.clear();
        scrnMsg.ctacPsnNum_P1.clear();

        if (getArgForSubScreen() instanceof Object[]) {
            Object[] param = (Object[]) getArgForSubScreen();
            String strParam;
            for (int i = 0; i < param.length; i++) {
                Object o = param[i];
                strParam = null;
                if (o instanceof EZDBStringItem) {
                    strParam = ((EZDBStringItem) o).getValue();
                }

                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnNum_P1, strParam);
                }
                if (i == 1) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_P1, strParam);
                }
                if (i == 2) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_P1, strParam);
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_P1) && !ZYPCommonFunc.hasValue(scrnMsg.locNum_P1)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_P1)) {
                scrnMsg.setMessageInfo(NMAM8228E, new String[] {ACCT_NUM });
            } else {
                scrnMsg.setMessageInfo(NMAM8228E, new String[] {LOC_NUM });
            }
            throw new EZDFieldErrorException();
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_P1) && ZYPCommonFunc.hasValue(scrnMsg.locNum_P1)) {
            scrnMsg.setMessageInfo(NMAM8229E, new String[] {ACCT_NUM, LOC_NUM });
            throw new EZDFieldErrorException();
        }

        scrnMsg.xxPgFlg.setValue(ZYPConstant.FLG_OFF_0);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;
        NMAL6750CMsg bizMsg = new NMAL6750CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;
        NMAL6750CMsg bizMsg = (NMAL6750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        NMAL6750CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        // Del Start 2017/12/18 QC#22286
        //NMAL6750CommonLogic.setDefaultContactType(scrnMsg); //QC#8598 add
        // Del End 2017/12/18 QC#22286
//        scrnMsg.setFocusItem(scrnMsg.ctacPsnLastNm_H1);
        scrnMsg.setFocusItem(scrnMsg.ctacPsnFirstNm_H1);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;
        NMAL6750CommonLogic.setNameForMessage(scrnMsg);
    }
}
