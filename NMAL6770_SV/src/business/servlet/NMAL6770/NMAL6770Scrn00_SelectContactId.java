/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6770;

import static business.servlet.NMAL6770.constant.NMAL6770Constant.BUSINESS_ID;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_00;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_01;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_02;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_03;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_04;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_05;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_06;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_07;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_08;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_09;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_10;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_13;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_14;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_15;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_16;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_17;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_18;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_19;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_20;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_32;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6770.NMAL6770CMsg;
import business.servlet.NMAL6770.constant.NMAL6770Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/23   Fujitsu         M.Nakamura      Create          N/A
 * 2015/10/01   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#2041
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 * 2017/08/28   Fujitsu         H.Nagashima     Update          QC#20780
 *</pre>
 */
public class NMAL6770Scrn00_SelectContactId extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;

        NMAL6770CMsg bizMsg = new NMAL6770CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, new BigDecimal(getButtonSelectNumber()));

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;
        NMAL6770CMsg bizMsg = (NMAL6770CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {
            int index = getButtonSelectNumber();

            // Contact Id
            EZDBStringItem param0 = (EZDBStringItem) params[IDX_00];
            ZYPEZDItemValueSetter.setValue(param0, scrnMsg.A.no(index).ctacPsnNum_A1);

            // Type(CTAC_TP)
            EZDBStringItem param1 = (EZDBStringItem) params[IDX_01];
            ZYPEZDItemValueSetter.setValue(param1, scrnMsg.A.no(index).ctacTpCd_A1);

            // Account Name
            EZDBStringItem param2 = (EZDBStringItem) params[IDX_02];
            ZYPEZDItemValueSetter.setValue(param2, scrnMsg.A.no(index).dsAcctNm_A1);

            // Account Number
            EZDBStringItem param3 = (EZDBStringItem) params[IDX_03];
            ZYPEZDItemValueSetter.setValue(param3, scrnMsg.A.no(index).dsAcctNum_A1);

            // Location Name
            EZDBStringItem param4 = (EZDBStringItem) params[IDX_04];
            ZYPEZDItemValueSetter.setValue(param4, scrnMsg.A.no(index).dsLocNm_A1);

            // Location Number
            EZDBStringItem param5 = (EZDBStringItem) params[IDX_05];
            ZYPEZDItemValueSetter.setValue(param5, scrnMsg.A.no(index).locNum_A1);

            // First Name
            EZDBStringItem param6 = (EZDBStringItem) params[IDX_06];
            ZYPEZDItemValueSetter.setValue(param6, scrnMsg.A.no(index).ctacPsnFirstNm_A1);

            // Last Name
            EZDBStringItem param7 = (EZDBStringItem) params[IDX_07];
            ZYPEZDItemValueSetter.setValue(param7, scrnMsg.A.no(index).ctacPsnLastNm_A1);

            // Phone-Work
            EZDBStringItem param8 = (EZDBStringItem) params[IDX_08];
            ZYPEZDItemValueSetter.setValue(param8, scrnMsg.A.no(index).dsCtacPntValTxt_A1);

            // Email
            EZDBStringItem param9 = (EZDBStringItem) params[IDX_09];
            ZYPEZDItemValueSetter.setValue(param9, scrnMsg.A.no(index).dsCtacPntValTxt_A2);

            // Role
            EZDBStringItem param10 = (EZDBStringItem) params[IDX_10];
            ZYPEZDItemValueSetter.setValue(param10, scrnMsg.A.no(index).dsCtacPsnTtlCd_A1);

            // Ext
            EZDBStringItem param13 = (EZDBStringItem) params[IDX_13];
            ZYPEZDItemValueSetter.setValue(param13, scrnMsg.A.no(index).dsCtacPsnExtnNum_A1);

            // Fax
            EZDBStringItem param14 = (EZDBStringItem) params[IDX_14];
            ZYPEZDItemValueSetter.setValue(param14, scrnMsg.A.no(index).dsCtacPntValTxt_A3);

            // Phone-Work PK
            EZDBBigDecimalItem param15 = (EZDBBigDecimalItem) params[IDX_15];
            ZYPEZDItemValueSetter.setValue(param15, scrnMsg.A.no(index).dsCtacPntPk_A1);

            // Email PK
            EZDBBigDecimalItem param16 = (EZDBBigDecimalItem) params[IDX_16];
            ZYPEZDItemValueSetter.setValue(param16, scrnMsg.A.no(index).dsCtacPntPk_A2);

            // Fax PK
            EZDBBigDecimalItem param17 = (EZDBBigDecimalItem) params[IDX_17];
            ZYPEZDItemValueSetter.setValue(param17, scrnMsg.A.no(index).dsCtacPntPk_A3);

            // Contact Point List
            if (params.length > IDX_18 && params[IDX_18] != null && params[IDX_18] instanceof EZDBMsgArray) {
                EZDBMsgArray param18 = (EZDBMsgArray) params[IDX_18];
                EZDMsg.copy(scrnMsg.P, null, param18, null);
            }

            // Contact PK
            if (params.length > IDX_19 && params[IDX_19] != null) {
                EZDBBigDecimalItem param19 = (EZDBBigDecimalItem) params[IDX_19];
                ZYPEZDItemValueSetter.setValue(param19, scrnMsg.A.no(index).ctacPsnPk_A1);
            }

            // Bill To Code
            if (params.length > IDX_20  && params[IDX_20] != null) {
                EZDBStringItem param20 = (EZDBStringItem) params[IDX_20];
                ZYPEZDItemValueSetter.setValue(param20, scrnMsg.A.no(index).billToCustCd_A1);
            }
            
            // QC#7781
            if(NMAL6770Constant.MULT_SEL_MODE_CD.equals(scrnMsg.xxModeCd_H1.getValue())){
                if (params instanceof Object[]) {
                    // Person Code
//                    if (params.length > IDX_31 && params[IDX_31] != null) {     // QC#20780 mod
                    if (params.length > IDX_32 && params[IDX_32] != null) {
//                        EZDBBigDecimalItem[] param = (EZDBBigDecimalItem[]) params[IDX_31];     // QC#20780 mod
                        EZDBBigDecimalItem[] param = (EZDBBigDecimalItem[]) params[IDX_32];
                        for(EZDBBigDecimalItem item : param){
                            item.clear();
                        }
                        if (param.length > 0) {
                            ZYPEZDItemValueSetter.setValue(param[0], scrnMsg.A.no(index).ctacPsnPk_A1);
                        }
                    }
                }
            }
        }
    }
}
