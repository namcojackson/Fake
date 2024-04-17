/* <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL0480;

import static business.servlet.NSAL0480.constant.NSAL0480Constant.BIZ_ID;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0480.NSAL0480CMsg;
import business.servlet.NSAL0480.common.NSAL0480CommonLogic;
import business.servlet.NSAL0480.constant.NSAL0480Constant.FUNC;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   Fujitsu         M.Yamada        Create          N/A
 * 2015/10/05   Hitachi         Y.Tsuchimoto    Update          NA#CSA
 * 2016/04/06   Hitachi         Y.Takeno        Update          QC#5989
 * 2016/07/14   Hitachi         M.Gotou         Update          QC#11169
 * 2016/09/21   Hitachi         Y.Zhang         Update          QC#12582
 * 2016/10/07   Hitachi         N.Arai          Update          QC#15001
 *</pre>
 */
public class NSAL0480_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;

        NSAL0480CMsg bizMsg = new NSAL0480CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());

        //set Invoker screen value.(from NSAL0510 Sub Contract Search)
        Serializable arg = super.getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length > 0) {
                EZDBStringItem param0 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm_H, param0.getValue());
                // 2015/10/05 CSA Y.Tsuchimoto Add Start
                if (params.length > 1) {
                    EZDBStringItem param1 = (EZDBStringItem) params[1];
                    ZYPEZDItemValueSetter.setValue(scrnMsg.t_ItemCd_H, param1.getValue());
                }
                // 2015/10/05 CSA Y.Tsuchimoto Add End
                // START 2016/04/06 [QC#5989, ADD]
                if (params.length > 2) {
                    EZDBBigDecimalItem param2 = (EZDBBigDecimalItem) params[2];
                    ZYPEZDItemValueSetter.setValue(scrnMsg.mtrGrpPk_H, param2.getValue());
                }
                // END   2016/04/06 [QC#5989, ADD]
            }
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;
        NSAL0480CMsg bizMsg = (NSAL0480CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0480CommonLogic.initControlCommonButton(this);
        NSAL0480CommonLogic.initCommonButton(this);
// START 2016/10/07 N.Arai [QC#15001, MOD]
        scrnMsg.svcSkillDescTxt_H.setInputProtected(true);
// END 2016/10/07 N.Arai [QC#15001, MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcIstlReqFlg_HY, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcIstlReqFlg_HN, ZYPConstant.FLG_OFF_N);
        // 2015/10/05 CSA Y.Tsuchimoto Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.custIstlFlg_HY, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.custIstlFlg_HN, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.siteSrvyReqFlg_HY, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.siteSrvyReqFlg_HN, ZYPConstant.FLG_OFF_N);
        // 2015/10/05 CSA Y.Tsuchimoto Add End

        // START 2016/07/14 M.Gotou [QC#11169, ADD]
        if (scrnMsg.A.getValidCount() > 0) {
            NSAL0480CommonLogic.setViewItemButtonControl(this, true);

            NSAL0480CommonLogic.protectFields(this, scrnMsg);
            NSAL0480CommonLogic.setRowColors(scrnMsg);
        } else {
            NSAL0480CommonLogic.setViewItemButtonControl(this, false);
        }
        scrnMsg.putErrorScreen();
        // END 2016/07/14 M.Gotou [QC#11169, ADD]

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;

        scrnMsg.srchOptPk_S.setNameForMessage("Search Option");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");

        scrnMsg.t_MdlNm_H.setNameForMessage("Model Name");
        scrnMsg.mdlDescTxt_H.setNameForMessage("Model Description");
        scrnMsg.mdlGrpNm_H.setNameForMessage("Model Group");
        scrnMsg.svcSegCd_H.setNameForMessage("Service Segment");
        scrnMsg.mdlActvFlg_H.setNameForMessage("Active Status");
        scrnMsg.xxCratDt_H.setNameForMessage("Create Date");
        scrnMsg.mtrGrpPk_H.setNameForMessage("Meter Group");
        scrnMsg.svcSkillNum_H.setNameForMessage("Service Skills");
        scrnMsg.svcIstlRuleNum_HY.setNameForMessage("Installation Rules");
        scrnMsg.svcIstlReqFlg_HY.setNameForMessage("Installation Required for Revenue");
        scrnMsg.svcIstlReqFlg_HN.setNameForMessage("Installation Required for Revenue");
        scrnMsg.svcIstlRuleNum_HN.setNameForMessage("Deinstallation Rules");
        // START 2016/09/21 Y.Zhang [QC#12582, modify]
        scrnMsg.t_ItemCd_H.setNameForMessage("Item Code");
        scrnMsg.mdseCd_H.setNameForMessage("Supply Item Code");
        // END 2016/09/21 Y.Zhang [QC#12582, modify]
        scrnMsg.imgSplyOemCd_H.setNameForMessage("Supply OEM Code");
        scrnMsg.mdseItemClsTpCd_H.setNameForMessage("Supply Class");
        // 2015/10/05 CSA Y.Tsuchimoto Add Start
        scrnMsg.custIstlFlg_HY.setNameForMessage("Customer Installable");
        scrnMsg.custIstlFlg_HN.setNameForMessage("Customer Installable");
        scrnMsg.siteSrvyReqFlg_HY.setNameForMessage("Site Survey Required");
        scrnMsg.siteSrvyReqFlg_HN.setNameForMessage("Site Survey Required");
        // 2015/10/05 CSA Y.Tsuchimoto Add End

    }
}
