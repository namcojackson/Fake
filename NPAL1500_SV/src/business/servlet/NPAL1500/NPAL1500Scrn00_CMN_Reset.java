/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CITS            N.Akaishi       Create          N/A
 *</pre>
 */
public class NPAL1500Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_HD, scrnMsg.poNum_G1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, scrnMsg.poNum_G1);

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NPAL1500CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);

        //QC#18602(Sol#102) ADD Start
        if (PO_STS.CLOSED.equals(scrnMsg.poStsCd_H.getValue()) && PO_HDR_STS.CLOSED.equals(scrnMsg.poHdrStsCd.getValue())) {
            scrnMsg.xxChkBox_LO.setInputProtected(true);
            scrnMsg.xxChkBox_LO.setValue(ZYPConstant.FLG_OFF_N);
        } else {
            scrnMsg.xxChkBox_LO.setInputProtected(false);
            scrnMsg.xxChkBox_LO.setValue(ZYPConstant.FLG_ON_Y);
        }
        //QC#18602(Sol#102) ADD End

        scrnMsg.setFocusItem(scrnMsg.poNum);
    }
}
