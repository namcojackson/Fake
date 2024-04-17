/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.DEPC_START_DT_FROM;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.DEPC_START_DT_TO;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.FUNC_CD_SRCH;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.NLZM2277E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0060.NLEL0060CMsg;
import business.servlet.NLEL0060.common.NLEL0060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/18   Hitachi         J.Kim           Update          QC#6581
 * 2016/06/16   Hitachi         T.Tsuchida      Update          QC#10145
 * 2016/06/30   Hitachi         J.Kim           Update          QC#10864
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 *</pre>
 */
public class NLEL0060Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        // START 2016/06/16 T.Tsuchida [QC#10145,DEL]
        //if (!ZYPCommonFunc.hasValue(scrnMsg.assetTpCd_H1)) {
        //    scrnMsg.assetTpCd_H1.setErrorInfo(1, NLZM2276E);
        //}
        // END 2016/06/16 T.Tsuchida [QC#10145,DEL]

        if (ZYPDateUtil.compare(scrnMsg.depcStartDt_H1.getValue(), scrnMsg.depcStartDt_H2.getValue()) == 1) {
            scrnMsg.depcStartDt_H1.setErrorInfo(1, NLZM2277E, new String[] {DEPC_START_DT_TO, DEPC_START_DT_FROM });
            scrnMsg.depcStartDt_H2.setErrorInfo(1, NLZM2277E, new String[] {DEPC_START_DT_TO, DEPC_START_DT_FROM });
        }

        scrnMsg.addCheckItem(scrnMsg.assetTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.assetStsCd_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAssetMstrPk_H1);
        scrnMsg.addCheckItem(scrnMsg.assetTagNum_H1);
        scrnMsg.addCheckItem(scrnMsg.depcStartDt_H1);
        scrnMsg.addCheckItem(scrnMsg.depcStartDt_H2);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk_H1);
        // START 2018/03/28 J.Kim [QC#22087,ADD]
        //// START 2016/06/30 J.Kim [QC#10864,ADD]
        //scrnMsg.addCheckItem(scrnMsg.depcCoaAcctCd_H1);
        /// END 2016/06/30 J.Kim [QC#10864,ADD]
        scrnMsg.addCheckItem(scrnMsg.depcCoaAcctCd_F);
        scrnMsg.addCheckItem(scrnMsg.depcCoaAcctCd_T);
        scrnMsg.addCheckItem(scrnMsg.expCoaBrCd_F);
        scrnMsg.addCheckItem(scrnMsg.expCoaBrCd_T);
        scrnMsg.addCheckItem(scrnMsg.expCoaCcCd_F);
        scrnMsg.addCheckItem(scrnMsg.expCoaCcCd_T);
        scrnMsg.addCheckItem(scrnMsg.expCoaExtnCd_F);
        scrnMsg.addCheckItem(scrnMsg.expCoaExtnCd_T);
        // END 2018/03/28 J.Kim [QC#22087,ADD]
        scrnMsg.addCheckItem(scrnMsg.serNum_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rtnWhCd_H1);
        // START 2016/04/18 J.Kim [QC#6581,MOD]
        // scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_H1);
        // END 2016/04/18 J.Kim [QC#6581,MOD]
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum_H1);
        scrnMsg.addCheckItem(scrnMsg.vndCd_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAssetDescTxt_H1);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        NLEL0060CMsg bizMsg = new NLEL0060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLEL0060CommonLogic.setTableBGColor(scrnMsg);
        NLEL0060CommonLogic.initCmnBtnProp(this);
        NLEL0060CommonLogic.ctrlCmnSubBtnProp(this, scrnMsg);

        scrnMsg.acctYrMth_T1.clear();
        scrnMsg.assetRtireRsnCmntTxt_T1.clear();
        scrnMsg.setFocusItem(scrnMsg.assetTpCd_H1);
    }
}
