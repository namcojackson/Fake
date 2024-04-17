/**
 * Copyright (c) 2013 Canon USA Inc. All rights reserved.
 */
package com.canon.cusa.s21.batch.NZZ.NZZB003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDAbendException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Updating Month End Flag Y to N or N to Y <br>
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 12/11/2012   CSIA            N.Sasaki        Created         424903
 * 01/02/2013   CSIA            N.Sasaki        Updated         424903
 * 01/06/2013   Hitachi         K.Yamada        Created         WDS Def#3302
 * 
 * </pre>
 */
public class NZZB003001 extends S21BatchMain implements NZZB003001Constant {

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Count */
    private int commitCount = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** Month End Flag */
    private static String mthEndFlg = "";

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        // Parameter - Month End Flag
        mthEndFlg = S21BatchUtil.getUserVariable1();

        new NZZB003001().executeBatch(NZZB003001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        // VAR_CHAR_CONST_NM
        String monthEndMode = ZYPCodeDataUtil.getVarCharConstValue(MONTH_END_MODE, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(monthEndMode)) {
            if (doProc(monthEndMode)) {
                commit();
            } else {
                rollback();
            }
        } else {
            throw new S21AbendException("NZZM0001E", new String[] {"No Record", "Found in " + MONTH_END_MODE });
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    private boolean doProc(String monthEndMode) {
        try {
            String[] varCharConstNmArr = monthEndMode.split(",");

            for (int i = 0; i < varCharConstNmArr.length; i++) {
                // VAR_CHAR_CONST_NM
                String varCharConstNm = varCharConstNmArr[i];

                VAR_CHAR_CONSTTMsg tMsg = new VAR_CHAR_CONSTTMsg();
                setValue(tMsg.glblCmpyCd, glblCmpyCd);
                setValue(tMsg.varCharConstNm, varCharConstNm);

                tMsg = (VAR_CHAR_CONSTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

                if (tMsg != null) {
                    tMsg.varCharConstVal.setValue(mthEndFlg);
                    EZDTBLAccessor.update(tMsg);
                    commitCount++;
                } else {
                    throw new S21AbendException("NZZM0001E", new String[] {"No Record", "Found in " + varCharConstNm });
                }
            }
        } catch (EZDAbendException ex) {
            throw new S21AbendException("NZZM0001E", new String[] {"Error", "this: " + ex });
        }
        return true;
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, commitCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }
}
