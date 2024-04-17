/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0010.NSBL0010CMsg;
import business.servlet.NSBL0010.common.NSBL0010CommonLogic;
import business.servlet.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2016/10/19   Hitachi         N.Arai          Update          QC#13901
 * 2017/01/05   Hitachi         N.Arai          Update          QC#13901-2
 * 2017/01/17   Hitachi         N.Arai          Update          QC#16331
 *</pre>
 */
public class NSBL0010Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
     // START 2017/01/17 N.Arai [QC#16331, MOD]
        // return null;
        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
        NSBL0010CMsg bizMsg = new NSBL0010CMsg();
        bizMsg.setBusinessID(NSBL0010Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSBL0010Constant.FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
     // END 2017/01/17 N.Arai [QC#16331, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
// START 2017/01/17 N.Arai [QC#16331, MOD]
        NSBL0010CMsg bizMsg = (NSBL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // get Function List
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NSBL0010Constant.BUSINESS_ID);

        NSBL0010CommonLogic.initCommonButton(this, funcList);

        NSBL0010CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.svcContrBrCd);
//// START 2016/10/19 N.Arai [QC#13901, MOD]
////        scrnMsg.orgLayerNum.clear();
////        scrnMsg.orgCd.clear();
////        scrnMsg.orgNm.clear();
//        scrnMsg.svcContrBrCd.clear();
//        scrnMsg.svcContrBrDescTxt.clear();
//// START 2017/01/05 N.Arai [QC#13901-2, MOD]
////        scrnMsg.lineBizTpCd_H3.clear();
//        scrnMsg.svcLineBizCd_H3.clear();
//// END 2017/01/05 N.Arai [QC#13901-2, MOD]
//        scrnMsg.locNm.clear();
//// END 2016/10/19 N.Arai [QC#13901, MOD]
//        scrnMsg.svcTaskNum.clear();
//        scrnMsg.fsrNum.clear();
//        scrnMsg.techCd.clear();
//        scrnMsg.svcTaskStsCd_H3.clear();
//        scrnMsg.fsrVisitNum.clear();
//        scrnMsg.svcTaskRcvDt_H1.clear();
//        scrnMsg.svcTaskRcvDt_H2.clear();
//        scrnMsg.shipToCustCd.clear();
//        scrnMsg.mdlNm.clear();
//        scrnMsg.techSchdFromDt.clear();
//        scrnMsg.dsSvcCallTpCd_H3.clear();
//        scrnMsg.svcBillTpCd_H3.clear();
//        scrnMsg.xxChkBox_HO.clear();
//// START 2016/10/19 N.Arai [QC#13901, MOD]
////        scrnMsg.xxChkBox_EX.clear();
//// END 2016/10/19 N.Arai [QC#13901, MOD]
//        scrnMsg.xxChkBox_DW.clear();
// END 2017/01/17 N.Arai [QC#16331, MOD]
    }
}
