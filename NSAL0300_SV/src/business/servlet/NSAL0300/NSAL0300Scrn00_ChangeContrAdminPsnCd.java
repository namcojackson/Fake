/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.common.NSAL0300CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/26   Hitachi         T.Kanasaka      Update          QC4092
 * 2016/04/07   Hitachi         M.Gotou         Update          QC5312,5313
 * 2016/07/28   Hitachi         T.Kanasaka      Update          QC#4806
 * 2017/01/24   Hitachi         N.Arai          Update          QC#17228
 * 2018/06/18   Hitachi         K.Kim           Update          QC#25195
 *</pre>
 */
public class NSAL0300Scrn00_ChangeContrAdminPsnCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.contrAdminPsnCd)) {
            scrnMsg.addCheckItem(scrnMsg.contrAdminPsnCd);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        // START 2018/06/18 K.Kim [QC#25195, ADD]
        scrnMsg.contrAdminPsnCd.clear();
        // END 2018/06/18 K.Kim [QC#25195, ADD]
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg  = (NSAL0300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // add start 2016/04/07 CSA Defect#5312,5313
        this.setResult("no");
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg.getValue())) {
            this.setResult("yes");
            scrnMsg.xxScrEventNm.setValue("OpenWin_Rep");
            scrnMsg.contrAdminPsnCd.clear();
            scrnMsg.xxPsnNm_PP.clear();
            setValue(scrnMsg.svcContrBrCd_WK, scrnMsg.svcContrBrCd);
            scrnMsg.svcContrBrCd.clear();
            ZYPTableUtil.clear(scrnMsg.R);

            setValue(scrnMsg.xxPsnNm_PP, scrnMsg.xxPsnNm.getValue());
            String st = scrnMsg.xxPsnNm_PP.getValue() + "%";
            setValue(scrnMsg.xxPsnNm_PP, st);
            // 0 : Lv1 : Suffix (String: Output Item Suffix)
            // 1 : Lv1 : Window Title (String: Popup Title)
            // 2 : Lv1 : Select Table Name (String: Search SQL)
            // 3 : Lv2 : Where List (List: Search condition columns)
            // 4 : Lv2 : Column List (List: Search result columns)
            // 5 : Lv2 : Sort Condition (List: Sort columns)
            // 6 : Lv2 : Output
            Object[] prm = new Object[7];
            int i = 0;
            prm[i++] = "";
            prm[i++] = "Rep  Search Popup";
         // START 2017/01/24 N.Arai [QC#17228, MOD]
            // prm[i++] = getSelectSQL(scrnMsg, getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            prm[i++] = getSelectSQL(scrnMsg, getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(getGlobalCompanyCode()), PSN_TP.EMPLOYEE);
         // END 2017/01/24 N.Arai [QC#17228, MOD]
            prm[i++] = getSearchConditionSetting(scrnMsg);
            prm[i++] = getDisplayColumnSetting(scrnMsg);
            prm[i++] = getSortSetting(scrnMsg);
            prm[i++] = scrnMsg.R;
            setArgForSubScreen(prm);
            return;
        }
        // add end 2016/04/07 CSA Defect#5312,5313
        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        // add start 2016/04/07 CSA Defect#5312,5313
        //scrnMsg.addCheckItem(scrnMsg.contrAdminPsnCd);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        //scrnMsg.setFocusItem(scrnMsg.xxPsnNm);
        scrnMsg.setFocusItem(scrnMsg.tocNm);
        // mod end 2016/04/07 CSA Defect#5312,5313
    }
}
