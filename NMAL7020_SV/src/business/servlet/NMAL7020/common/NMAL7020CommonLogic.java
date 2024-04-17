/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7020.common;

import static business.servlet.NMAL7020.constant.NMAL7020Constant.BIZ_ID;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.BTN_CMN_APL;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.BTN_CMN_APR;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.BTN_CMN_RST;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.FUNC_ID_UPDATE;

import java.util.List;

import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;

import business.servlet.NMAL7020.NMAL7020BMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7020CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7020CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param userProfileService S21UserProfileService
     */
    public static void initCmnBtnProp(S21CommonHandler handler, S21UserProfileService userProfileService) {

        if (NMAL7020CommonLogic.isUpdateUser(userProfileService)) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(FUNC_ID_UPDATE);
    }

    /**
     * addCheckItem
     * @param scrnMsg NMAL7020BMsg
     */
    public static void addCheckItem(NMAL7020BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_O);
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_N);
        scrnMsg.addCheckItem(scrnMsg.newPrcCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);

        scrnMsg.addCheckItem(scrnMsg.mainPrcListInfoFlg);
        scrnMsg.addCheckItem(scrnMsg.prcListAccsTpFlg);
        scrnMsg.addCheckItem(scrnMsg.copyAttrbTrxFlg);
        scrnMsg.addCheckItem(scrnMsg.copyPrcFlg);

        scrnMsg.addCheckItem(scrnMsg.prcCalcTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcPctAmtTpCd);
        scrnMsg.addCheckItem(scrnMsg.copyPrcAmtRate);

        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.effApplyLvlTpCd);
        scrnMsg.addCheckItem(scrnMsg.varCharConstVal_A);
        scrnMsg.addCheckItem(scrnMsg.varCharConstVal_A);
    }

    /**
     * clearMandantory
     * @param scrnMsg NMAL7020BMsg
     */
    public static void clearMandantory(NMAL7020BMsg scrnMsg) {
        if (scrnMsg.prcCatgNm_O.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("prcCatgNm_O");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.prcCatgNm_O.clearErrorInfo();
            }
        }
        if (scrnMsg.prcCatgNm_N.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("prcCatgNm_N");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.prcCatgNm_N.clearErrorInfo();
            }
        }

        if (scrnMsg.mainPrcListInfoFlg.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("mainPrcListInfoFlg");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.mainPrcListInfoFlg.clearErrorInfo();
            }
        }

    }

}
