/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0410;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.*;
import static business.servlet.NSBL0410.common.NSBL0410CommonLogic.*;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0410.NSBL0410CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Hitachi         M.Gotou         Create          N/A
 * 2016/04/15   Hitachi         M.Gotou         Update          QC#3425
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11685
 * 2016/09/29   Hitachi         J.Sumi          Update          QC#12582
 * 2018/06/01   Hitachi         U.Kim           Update          QC#22393
 *</pre>
 */
public class NSBL0410_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;


        Object[] params = (Object[]) getArgForSubScreen();

        int index = 0;
        setValue(scrnMsg.svcModPk, (EZDBBigDecimalItem) params[index]);

        NSBL0410CMsg bizMsg = new NSBL0410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;
        NSBL0410CMsg bizMsg  = (NSBL0410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        // START 2018/06/01 U.Kim [QC#22393, ADD]
        controlScreenFieldsForINIT(scrnMsg);
        // END 2018/06/01 U.Kim [QC#22393, ADD]
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.svcModCmntTxt);
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;
        scrnMsg.svcModPlnId.setNameForMessage("Mod Plan ID");
        scrnMsg.svcModNm.setNameForMessage("Description");
        scrnMsg.svcModCmntTxt.setNameForMessage("Comment");
        scrnMsg.xxEndDplyTmTxt.setNameForMessage("Estimated Labor");
        scrnMsg.svcMnfCd.setNameForMessage("MU");
        scrnMsg.svcMnfModNum.setNameForMessage("Man Mod#");
        scrnMsg.svcModDocNum.setNameForMessage("Doc#");
        scrnMsg.svcModPrtyCd.setNameForMessage("Doc#");
        scrnMsg.svcModRptTrkFlg.setNameForMessage("Priority");
        scrnMsg.svcModIssDt.setNameForMessage("Issue Date");
        scrnMsg.svcModStartDt.setNameForMessage("Start Date");
        // START 2016/09/29 J.Sumi [QC#12582, MOD]
        scrnMsg.mdseCd.setNameForMessage("Item Code");
        // END 2016/09/29 J.Sumi [QC#12582, MOD]
        // mod start 2016/04/15 CSA Defect#3425
        scrnMsg.mdseCd_F.setNameForMessage("Mdse Code");
        // mod start 2016/04/15 CSA Defect#3425
        // START 2018/06/01 U.Kim [QC#22393, ADD]
        scrnMsg.mdseItemTpCd.setNameForMessage("Item Type");
        scrnMsg.mktMdlCd.setNameForMessage("Marketing Model");
        // END 2018/06/01 U.Kim [QC#22393, ADD]
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcMnfCd_A.setNameForMessage("MU");
            scrnMsg.A.no(i).svcMnfModNum_A.setNameForMessage("Manufacture Mod#");
            scrnMsg.A.no(i).svcModDocNum_A.setNameForMessage("Document#");
            scrnMsg.A.no(i).svcModPrtyDescTxt_A.setNameForMessage("Priority");
            scrnMsg.A.no(i).svcModRptTrkFlg_A.setNameForMessage("Report Tracking");
            scrnMsg.A.no(i).svcModIssDt_A.setNameForMessage("Issue Date");
            scrnMsg.A.no(i).svcModStartDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).svcModOnHldDt_A.setNameForMessage("On-Hold Date");
            scrnMsg.A.no(i).svcModCancDt_A.setNameForMessage("Cancel Date");
            scrnMsg.A.no(i).svcModObslDt_A.setNameForMessage("Obsoleted Date");
            scrnMsg.A.no(i).svcModObslNum_A.setNameForMessage("Obsoleted By Mod#");
        }
    }
}
