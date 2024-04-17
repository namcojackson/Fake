/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2620;

import static business.servlet.NMAL2620.constant.NMAL2620Constant.BUSINESS_ID;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.CHK_BOX_A;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.FUNC_CD_UPD;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.NMAM0185E;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.NMAM0835E;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.NMAM8507E;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.SERVICE_MODE_01;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.SERVICE_MODE_02;
import static business.servlet.NMAL2620.constant.NMAL2620Constant.ZZZM9007E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2620.NMAL2620CMsg;
import business.servlet.NMAL2620.common.NMAL2620CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/27   Fujitsu         C.Yokoi         Update          N/A
 *</pre>
 */
public class NMAL2620Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/04/27 Add Start
        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.rqstRsltCmntTxt);
        // Move Resource Mode : Input check
        if (SERVICE_MODE_01.equals(scrnMsg.trtyUpdModeTpCd_V.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxPsnNm_D)) {
                scrnMsg.xxPsnNm_D.setErrorInfo(1, ZZZM9007E, new String[] {"Move Territory To"});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxFromDt)) {
                scrnMsg.xxFromDt.setErrorInfo(1, ZZZM9007E, new String[] {"Move Effective From"});
            } else {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxThruDt)) {
                    if (scrnMsg.xxFromDt.getValue().compareTo(scrnMsg.xxThruDt.getValue()) > 0) {
                        scrnMsg.xxFromDt.setErrorInfo(1, NMAM0185E);
                        scrnMsg.xxThruDt.setErrorInfo(1, NMAM0185E);
                    }
                }

                // Error if move to date is not future date
                if (ZYPDateUtil.getSalesDate().compareTo(scrnMsg.xxFromDt.getValue()) > 0) {
                    scrnMsg.xxFromDt.setErrorInfo(1, NMAM8507E);
                }
            }
            scrnMsg.addCheckItem(scrnMsg.xxPsnNm_D);
            scrnMsg.addCheckItem(scrnMsg.xxFromDt);
            scrnMsg.addCheckItem(scrnMsg.xxThruDt);
            scrnMsg.putErrorScreen();


        // End Territoryies Mode : Input check
        } else if (SERVICE_MODE_02.equals(scrnMsg.trtyUpdModeTpCd_V.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.endDt)) {
                scrnMsg.endDt.setErrorInfo(1, ZZZM9007E, new String[] {"End Date Territory On"});
            }
            scrnMsg.addCheckItem(scrnMsg.endDt);
            scrnMsg.putErrorScreen();

        // Error if mode are not selected
        } else {
            scrnMsg.trtyUpdModeTpCd_V.setErrorInfo(1, ZZZM9007E, new String[] {"Select Mode"});
            scrnMsg.addCheckItem(scrnMsg.trtyUpdModeTpCd_V);
            scrnMsg.addCheckItem(scrnMsg.xxPsnNm_D);
            scrnMsg.addCheckItem(scrnMsg.xxFromDt);
            scrnMsg.addCheckItem(scrnMsg.xxThruDt);
            scrnMsg.addCheckItem(scrnMsg.endDt);
            scrnMsg.putErrorScreen();
        }

        // check selected Checkbox
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHK_BOX_A, ZYPConstant.CHKBOX_ON_Y);
        if (checkList.size() == 0) {
            scrnMsg.setMessageInfo(NMAM0835E);
            throw new EZDFieldErrorException();
        }
        // 2016/04/27 Add End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;
        NMAL2620CMsg bizMsg = new NMAL2620CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // 2016/04/27 Add Start
        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;
        NMAL2620CMsg bizMsg  = (NMAL2620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHK_BOX_A, ZYPConstant.CHKBOX_ON_Y);

        for (int idx : checkList) {
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxChkBox_A);
        }
        scrnMsg.putErrorScreen();

        NMAL2620CommonLogic.controlScreenFields(this, scrnMsg);
        NMAL2620CommonLogic.setRowColors(scrnMsg);
        NMAL2620CommonLogic.protectSearchResult(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_V);
        // 2016/04/27 Add End

    }
}
