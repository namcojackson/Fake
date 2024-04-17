/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM0009E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NLAM1294E;


import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1500 PO Entry
 * Function Name : Add Config
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            N Akaishi       Create          N/A
 * 09/09/2016   CITS            S.Endo          Update          N/A
 * 10/24/2016   CITS            S.Endo          Update          QC#15226
 *</pre>
 */
public class NPAL1500Scrn00_AddConfig extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk)) {

            scrnMsg.svcConfigMstrPk.setErrorInfo(1, NPAM0009E);

        } else {

            if (scrnMsg.A.getValidCount() > 0) {

                checkInputDetail(scrnMsg);
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd_SC.getValue())) {

            scrnMsg.srcRtlWhCd_SC.setErrorInfo(1, NPAM0009E);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.prntVndCd.getValue())) {

            scrnMsg.prntVndCd.setErrorInfo(1, NPAM0009E);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.vndCd.getValue())) {

            scrnMsg.vndCd.setErrorInfo(1, NPAM0009E);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd_DS.getValue())) {

            scrnMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM0009E);
        }
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd_SC);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_DS);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg  = (NPAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
        NPAL1500CommonLogic.moveErrorTab(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd_SC);
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NPAL1500CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);

        scrnMsg.setFocusItem(scrnMsg.svcConfigMstrPk);

    }

    private void checkInputDetail(NPAL1500BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (scrnMsg.svcConfigMstrPk.getValue().equals(scrnMsg.A.no(i).svcConfigMstrPk_A1.getValue())) {

                scrnMsg.svcConfigMstrPk.setErrorInfo(1, NLAM1294E, new String[] {"Config#" });
                break;
            }
        }
    }
}
