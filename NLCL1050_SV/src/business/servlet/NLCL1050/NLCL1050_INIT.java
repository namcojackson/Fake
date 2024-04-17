/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1050;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1050.NLCL1050CMsg;
import business.servlet.NLCL1050.common.NLCL1050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

import static business.servlet.NLCL1050.constant.NLCL1050Constant.BIZ_ID;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.FUNCTION_CD_SEARCH;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   CITS            N.Akaishi       Create          N/A
 *</pre>
 */
public class NLCL1050_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length > 0) {

            EZDBStringItem param01 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.abcAnlsClsNm, param01);

        }

        NLCL1050CMsg bizMsg = new NLCL1050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;
        NLCL1050CMsg bizMsg = (NLCL1050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL1050CommonLogic.commonInit(scrnMsg);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NLCL1050CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);

        if (!ZYPCommonFunc.hasValue(scrnMsg.abcAnlsClsNm)) {
            scrnMsg.setFocusItem(scrnMsg.abcAnlsClsNm);
        } else {
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.setFocusItem(scrnMsg.A.no(0).abcAnlsClsPrtyNum);
            } else {
                scrnMsg.setFocusItem(scrnMsg.abcAnlsClsNm);
            }
        }

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;

        scrnMsg.abcAnlsClsNm.setNameForMessage("ABC Class Name");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).abcAnlsClsPrtyNum.setNameForMessage("Class Priority#");
            scrnMsg.A.no(i).abcAnlsClsTagCd.setNameForMessage("Class Tag Name");
            scrnMsg.A.no(i).abcAnlsClsTagDescTxt.setNameForMessage("Tag Description");
            scrnMsg.A.no(i).cycleCntFreqDaysAot.setNameForMessage("Cycle Count Days");
            scrnMsg.A.no(i).valAsgPct.setNameForMessage("Value Assignment");
        }

    }
}
