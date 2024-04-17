/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_CTY_ADDR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_FIRST_LINE_ADDR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_FRTH_LINE_ADDR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_POST_CD;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_SCD_LINE_ADDR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_ST_CD;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_THIRD_LINE_ADDR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330_NMAL6760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int ixSelRow = -1;
        if ("OpenWin_CustomerConfig".equals(scrEventNm)) {
            ixSelRow = scrnMsg.xxNum_A.getValueInt();
            scrnMsg.setFocusItem(scrnMsg.R.no(ixSelRow).billToCustCd_R);
            if (!ZYPCommonFunc.hasValue(scrnMsg.R.no(ixSelRow).billToLocNum_R)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(ixSelRow).xxGenlFldAreaTxt_R, getBillToSrchTxt(scrnMsg.P));

        } else if ("OpenWin_LocationConfig".equals(scrEventNm)) {
            ixSelRow = scrnMsg.xxNum_A.getValueInt();
            scrnMsg.setFocusItem(scrnMsg.R.no(ixSelRow).billToLocNum_R);
            if (!ZYPCommonFunc.hasValue(scrnMsg.R.no(ixSelRow).billToLocNum_R)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(ixSelRow).xxGenlFldAreaTxt_R, getBillToSrchTxt(scrnMsg.P));

        } else if ("OpenWin_CustomerMeter".equals(scrEventNm)) {
            ixSelRow = scrnMsg.xxNum_Z.getValueInt();
            scrnMsg.setFocusItem(scrnMsg.U.no(ixSelRow).billToCustCd_U);
            if (!ZYPCommonFunc.hasValue(scrnMsg.U.no(ixSelRow).billToLocNum_U)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(ixSelRow).xxGenlFldAreaTxt_U, getBillToSrchTxt(scrnMsg.P));

        } else if ("OpenWin_LocationMeter".equals(scrEventNm)) {
            ixSelRow = scrnMsg.xxNum_Z.getValueInt();
            scrnMsg.setFocusItem(scrnMsg.U.no(ixSelRow).billToLocNum_U);
            if (!ZYPCommonFunc.hasValue(scrnMsg.U.no(ixSelRow).billToLocNum_U)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(ixSelRow).xxGenlFldAreaTxt_U, getBillToSrchTxt(scrnMsg.P));

        }
    }

    private String getBillToSrchTxt(NSAL1330_PBMsgArray popupOutAry) {
        return S21StringUtil.concatStrings(//
                popupOutAry.no(IX_POP_PRM_FIRST_LINE_ADDR).xxPopPrm_P.getValue() // first line address
                , " " //
                , popupOutAry.no(IX_POP_PRM_SCD_LINE_ADDR).xxPopPrm_P.getValue() // second line address
                , " " //
                , popupOutAry.no(IX_POP_PRM_THIRD_LINE_ADDR).xxPopPrm_P.getValue() // ThirdLineAddr
                , " " //
                , popupOutAry.no(IX_POP_PRM_FRTH_LINE_ADDR).xxPopPrm_P.getValue() // FrthLineAddr
                , " " //
                , popupOutAry.no(IX_POP_PRM_CTY_ADDR).xxPopPrm_P.getValue() // CtyAddr
                , "," //
                , popupOutAry.no(IX_POP_PRM_ST_CD).xxPopPrm_P.getValue() // StCd
                , " " //
                , popupOutAry.no(IX_POP_PRM_POST_CD).xxPopPrm_P.getValue() // PostCd
                );
    }
}
