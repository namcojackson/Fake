/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1530;

import static business.servlet.NWAL1530.constant.NWAL1530Constant.BIZ_ID;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.MESSAGE_NAME_ORDER_NUM;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.TAB_SHIPPING_DETAIL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1530.NWAL1530CMsg;
import business.servlet.NWAL1530.common.NWAL1530CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/28   Fujitsu         S.Ohki          Create          N/A
 * 2018/03/16   Fujitsu         M.Ohno          Update          QC#22805
 * 2018/07/11   Fujitsu         M.Ohno          Update          QC#19801
 *</pre>
 */
public class NWAL1530_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;

        // Acquisition of the transition parameter
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params != null && params.length > 0) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, param01.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_BK, param01.getValue());
                // 2018/03/16 QC#22805 add start
                if (params.length > 1 && params[1] != null) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.configCatgCd, (String) params[1]);
                }
 
                if (params.length > 2 && params[2] != null && params[2] instanceof List) {
                    List<Object[]> paramList = (List<Object[]>) params[2];

                    int i = 0;
                    for (Object[] paramAry : paramList) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.F.no(i).dsOrdPosnNum_F, (String) paramAry[0]);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.F.no(i).dsCpoLineNum_F, new BigDecimal((String) paramAry[1]));
                        i++;
                        if (scrnMsg.F.length() <= i) {
                            break;
                        }
                    }
                    scrnMsg.F.setValidCount(i);
                }
                // 2018/03/16 QC#22805 add end
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {

            NWAL1530CMsg bizMsg = new NWAL1530CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;
        NWAL1530CMsg bizMsg = (NWAL1530CMsg) cMsg;

        if (null != bizMsg) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
            setButtonEnabled("Search_Order", false);
            scrnMsg.cpoOrdNum.setInputProtected(true);
        }

        NWAL1530CommonLogic.initCmnBtnProp(this);
        // 2018/07/11 QC#19801 add start
        for (int i=0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
        }
        // 2018/07/11 QC#19801 add end

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
        scrnMsg.xxDplyTab.setValue(TAB_SHIPPING_DETAIL);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;
        scrnMsg.cpoOrdNum.setNameForMessage(MESSAGE_NAME_ORDER_NUM);
    }
}
