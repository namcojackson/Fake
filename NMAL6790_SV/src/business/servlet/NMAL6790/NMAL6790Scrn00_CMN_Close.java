/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6790;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6790.constant.NMAL6790Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/04/19   Hitachi         T.Tomita        Update          QC#6223
 * 2016/04/22   Hitachi         T.Tomita        Update          QC#4866
 * 2018/10/11   Fujitsu         T.Noguchi       Update          QC#27869
 *</pre>
 */
public class NMAL6790Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {

            setOutputFromHeader(params, scrnMsg);

            scrnMsg.P.clear();
            scrnMsg.P.setValidCount(0);

            // Obtain Checked List
            List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, NMAL6790Constant.CHKBOX_B, ZYPConstant.CHKBOX_ON_Y);
            if (checkList.size() <= 0) {
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    scrnMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NMAL6790Constant.NZZM0011E);
                }
            }

            // mod start 2016/04/19 CSA Defect#6223
            // Put checked row records to output parameter
            int pIndex;
            for (int i : checkList) {
                setOutputFromContactPerson(scrnMsg);
                setOutputFromContactPoint(scrnMsg, i);
                pIndex = scrnMsg.P.getValidCount();
                scrnMsg.P.setValidCount(++pIndex);
            }
            // mod end 2016/04/19 CSA Defect#6223

            // Contact Person/Point List
            if (params[NMAL6790Constant.IDX_17] != null && params[NMAL6790Constant.IDX_17] instanceof EZDBMsgArray) {
                EZDBMsgArray param17 = (EZDBMsgArray) params[NMAL6790Constant.IDX_17];
                EZDMsg.copy(scrnMsg.P, null, param17, null);
            }

            scrnMsg.P.setValidCount(checkList.size());
        }
    }

    private void setOutputFromHeader(Object[] params, NMAL6790BMsg scrnMsg) {

        // Type
        if (ZYPCommonFunc.hasValue(scrnMsg.ctacTpCd_H1)) {
            EZDBStringItem param0 = (EZDBStringItem) params[NMAL6790Constant.IDX_00];
            ZYPEZDItemValueSetter.setValue(param0, scrnMsg.ctacTpCd_H1);
        }

        // Header Inactive Flag
        if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyCtrlFlg_H1)) {
            EZDBStringItem param1 = (EZDBStringItem) params[NMAL6790Constant.IDX_01];
            ZYPEZDItemValueSetter.setValue(param1, scrnMsg.xxDplyCtrlFlg_H1);
        }

        // Account Name
        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H1)) {
            EZDBStringItem param2 = (EZDBStringItem) params[NMAL6790Constant.IDX_02];
            ZYPEZDItemValueSetter.setValue(param2, scrnMsg.dsAcctNm_H1);
        }

        // Account Number
        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
            EZDBStringItem param3 = (EZDBStringItem) params[NMAL6790Constant.IDX_03];
            ZYPEZDItemValueSetter.setValue(param3, scrnMsg.dsAcctNum_H1);
        }

        // Location Number
        if (ZYPCommonFunc.hasValue(scrnMsg.locNum_H1)) {
            EZDBStringItem param4 = (EZDBStringItem) params[NMAL6790Constant.IDX_04];
            ZYPEZDItemValueSetter.setValue(param4, scrnMsg.locNum_H1);
        }

        // Location Name
        if (ZYPCommonFunc.hasValue(scrnMsg.dsLocNm_H1)) {
            EZDBStringItem param5 = (EZDBStringItem) params[NMAL6790Constant.IDX_05];
            ZYPEZDItemValueSetter.setValue(param5, scrnMsg.dsLocNm_H1);
        }

        // First Name
        if (ZYPCommonFunc.hasValue(scrnMsg.ctacPsnFirstNm_H1)) {
            EZDBStringItem param6 = (EZDBStringItem) params[NMAL6790Constant.IDX_06];
            ZYPEZDItemValueSetter.setValue(param6, scrnMsg.ctacPsnFirstNm_H1);
        }

        // Last Name
        if (ZYPCommonFunc.hasValue(scrnMsg.ctacPsnLastNm_H1)) {
            EZDBStringItem param7 = (EZDBStringItem) params[NMAL6790Constant.IDX_07];
            ZYPEZDItemValueSetter.setValue(param7, scrnMsg.ctacPsnLastNm_H1);
        }

        // Phone-Work
        if (ZYPCommonFunc.hasValue(scrnMsg.dsCtacPntValTxt_H1)) {
            EZDBStringItem param8 = (EZDBStringItem) params[NMAL6790Constant.IDX_08];
            ZYPEZDItemValueSetter.setValue(param8, scrnMsg.dsCtacPntValTxt_H1);
        }

        // E-Mail Address
        if (ZYPCommonFunc.hasValue(scrnMsg.dsCtacPntValTxt_H2)) {
            EZDBStringItem param9 = (EZDBStringItem) params[NMAL6790Constant.IDX_09];
            ZYPEZDItemValueSetter.setValue(param9, scrnMsg.dsCtacPntValTxt_H2);
        }

        // Title
        if (ZYPCommonFunc.hasValue(scrnMsg.dsCtacPsnTtlCd_H1)) {
            EZDBStringItem param10 = (EZDBStringItem) params[NMAL6790Constant.IDX_10];
            ZYPEZDItemValueSetter.setValue(param10, scrnMsg.dsCtacPsnTtlCd_H1);
        }

        // Department
        if (ZYPCommonFunc.hasValue(scrnMsg.dsCtacPsnDeptCd_H1)) {
            EZDBStringItem param11 = (EZDBStringItem) params[NMAL6790Constant.IDX_11];
            ZYPEZDItemValueSetter.setValue(param11, scrnMsg.dsCtacPsnDeptCd_H1);
        }

        // Active
        EZDBStringItem param12 = (EZDBStringItem) params[NMAL6790Constant.IDX_12];
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H1)) {
            ZYPEZDItemValueSetter.setValue(param12, scrnMsg.xxChkBox_H1);
        } else {
            ZYPEZDItemValueSetter.setValue(param12, ZYPConstant.FLG_OFF_N);
        }

        // E-Mail
        EZDBStringItem param13 = (EZDBStringItem) params[NMAL6790Constant.IDX_13];
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H2)) {
            ZYPEZDItemValueSetter.setValue(param13, scrnMsg.xxChkBox_H2);
        } else {
            ZYPEZDItemValueSetter.setValue(param13, ZYPConstant.FLG_OFF_N);
        }

        // Serial Number
        if (ZYPCommonFunc.hasValue(scrnMsg.serNum_H1)) {
            EZDBStringItem param14 = (EZDBStringItem) params[NMAL6790Constant.IDX_14];
            ZYPEZDItemValueSetter.setValue(param14, scrnMsg.serNum_H1);
        }

        // Address
        if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyByItemNm_H1)) {
            EZDBStringItem param15 = (EZDBStringItem) params[NMAL6790Constant.IDX_15];
            ZYPEZDItemValueSetter.setValue(param15, scrnMsg.xxDplyByItemNm_H1);
        }

        // IB Contact Role
        // 2018/10/11 QC#27869 Del Start
        //if (ZYPCommonFunc.hasValue(scrnMsg.svcCtacTpCd_H1)) {
        //    EZDBStringItem param16 = (EZDBStringItem) params[NMAL6790Constant.IDX_16];
        //    ZYPEZDItemValueSetter.setValue(param16, scrnMsg.svcCtacTpCd_H1);
        //}
        // 2018/10/11 QC#27869 Del End
    }

    // mod start 2016/04/19 CSA Defect#6223
    private void setOutputFromContactPerson(NMAL6790BMsg scrnMsg) {

        int checkedPersonIndex = scrnMsg.xxRowNum.getValueInt();

        // Contact ID
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).ctacPsnNum_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).ctacPsnNum_P2, scrnMsg.A.no(checkedPersonIndex).ctacPsnNum_A1);
        }

        // Contact Type
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).ctacTpCd_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).ctacTpCd_P2, scrnMsg.A.no(checkedPersonIndex).ctacTpCd_A1);
        }

        // Account Number
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).dsAcctNum_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsAcctNum_P2, scrnMsg.A.no(checkedPersonIndex).dsAcctNum_A1);
        }

        // Location Number
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).locNum_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).locNum_P2, scrnMsg.A.no(checkedPersonIndex).locNum_A1);
        }

        // Account Name
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).dsAcctNm_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsAcctNm_P2, scrnMsg.A.no(checkedPersonIndex).dsAcctNm_A1);
        }

        // Address
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).xxDplyByItemNm_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).xxDplyByItemNm_P2, scrnMsg.A.no(checkedPersonIndex).xxDplyByItemNm_A1);
        }

        // Primary Contact Type
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).dsPrimCtacTpCd_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsPrimCtacTpCd_P2, scrnMsg.A.no(checkedPersonIndex).dsPrimCtacTpCd_A1);
        }

        // Contact Active Flag
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).xxChkBox_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).ctacPsnActvFlg_P2, scrnMsg.A.no(checkedPersonIndex).xxChkBox_A1);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).ctacPsnActvFlg_P2, ZYPConstant.FLG_OFF_N);
        }

        // Title
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).dsCtacPsnTtlCd_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsCtacPsnTtlCd_P2, scrnMsg.A.no(checkedPersonIndex).dsCtacPsnTtlCd_A1);
        }

        // Department
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).dsCtacPsnDeptCd_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsCtacPsnDeptCd_P2, scrnMsg.A.no(checkedPersonIndex).dsCtacPsnDeptCd_A1);
        }

        // Primary Contact Flag
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).dsPrimLocFlg_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsPrimLocFlg_P2, scrnMsg.A.no(checkedPersonIndex).dsPrimLocFlg_A1);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsPrimLocFlg_P2, ZYPConstant.FLG_OFF_N);
        }

        // First Name
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).ctacPsnFirstNm_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).ctacPsnFirstNm_P2, scrnMsg.A.no(checkedPersonIndex).ctacPsnFirstNm_A1);
        }

        // Last Name
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).ctacPsnLastNm_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).ctacPsnLastNm_P2, scrnMsg.A.no(checkedPersonIndex).ctacPsnLastNm_A1);
        }

        // Serial Number
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(checkedPersonIndex).serNum_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).serNum_P2, scrnMsg.A.no(checkedPersonIndex).serNum_A1);
        }

    }

    private void setOutputFromContactPoint(NMAL6790BMsg scrnMsg, int checkedPointIndex) {

        // Contact Point ID
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(checkedPointIndex).dsCtacPntPk_B1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsCtacPntPk_P2, scrnMsg.B.no(checkedPointIndex).dsCtacPntPk_B1);
        }

        // Contact Point Type Code
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(checkedPointIndex).dsCtacPntTpCd_B1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsCtacPntTpCd_P2, scrnMsg.B.no(checkedPointIndex).dsCtacPntTpCd_B1);
        }

        // Contact Point Val Text
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(checkedPointIndex).dsCtacPntValTxt_B1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsCtacPntValTxt_P3, scrnMsg.B.no(checkedPointIndex).dsCtacPntValTxt_B1);
        }

        // Contact Psn Extn Text
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(checkedPointIndex).dsCtacPsnExtnNum_B1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsCtacPsnExtnNum_P2, scrnMsg.B.no(checkedPointIndex).dsCtacPsnExtnNum_B1);
        }

        // Ops Out Flag
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(checkedPointIndex).xxChkBox_B2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsOpsOutFlg_P2, scrnMsg.B.no(checkedPointIndex).xxChkBox_B2);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsOpsOutFlg_P2, ZYPConstant.FLG_OFF_N);
        }

        // Point Active Flag
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(checkedPointIndex).xxChkBox_B3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsCtacPntActvFlg_P2, scrnMsg.B.no(checkedPointIndex).xxChkBox_B3);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).dsCtacPntActvFlg_P2, ZYPConstant.FLG_OFF_N);
        }

        // add start 2016/04/22 CSA Defect#4866
        // IB Contact Role
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(checkedPointIndex).svcCtacTpCd_B1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(scrnMsg.P.getValidCount()).svcCtacTpCd_P2, scrnMsg.B.no(checkedPointIndex).svcCtacTpCd_B1);
        }
        // add end 2016/04/22 CSA Defect#4866

    }
    // mod start 2016/04/19 CSA Defect#6223
}
