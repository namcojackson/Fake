/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0100;

import static business.servlet.NMAL0100.constant.NMAL0100Constant.BUSINESS_ID;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.NMAM0192E;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0100.NMAL0100CMsg;
import business.servlet.NMAL0100.common.NMAL0100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0100Scrn00_SaveSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 * 2015/06/19   Fujitsu         M.Yamada        Update          0601
 * 2019/10/18   Fujitsu         C.Hara          Update          QC#53815
 *</pre>
 */
public class NMAL0100Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A); // 2019/10/18 QC#53815 Add
        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseDescShortTxt_H1)
                // 2019/10/18 QC#53815 Mod Start
                // && !ZYPCommonFunc.hasValue(scrnMsg.mdseItemMnfCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseItemMnfNm_H1)
                // 2019/10/18 QC#53815 Mod End
                && !ZYPCommonFunc.hasValue(scrnMsg.mnfItemCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.upcCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseItemStsCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseDescLongTxt_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseItemActvDt_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseItemActvDt_H2)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseItemTpCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseItemClsTpCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.coaMdseTpCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.coaProdCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.prchGrpCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdsePrcGrpCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mktMdlCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mktMdlSegCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.zerothProdCtrlNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.firstProdCtrlNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.scdProdCtrlNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.thirdProdCtrlNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.frthProdCtrlNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.slsMatGrpDescTxt_01)
                && !ZYPCommonFunc.hasValue(scrnMsg.slsMatGrpDescTxt_02)
                && !ZYPCommonFunc.hasValue(scrnMsg.slsMatGrpDescTxt_03)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsCmsnGrpDescTxt_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseCratTmplNm_H1)){
            scrnMsg.setMessageInfo(NMAM0192E);
            throw new EZDFieldErrorException();
        }

        NMAL0100CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        NMAL0100CommonLogic.setPage(scrnMsg, 1);
        NMAL0100CMsg bizMsg = new NMAL0100CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        NMAL0100CMsg bizMsg = (NMAL0100CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0100CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.mdseCd_H1);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }

}
