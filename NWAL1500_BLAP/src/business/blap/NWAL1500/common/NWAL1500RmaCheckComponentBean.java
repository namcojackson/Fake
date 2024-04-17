/*
 * <Pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import business.blap.NWAL1500.NWAL1500_DSMsg;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/11/29   Fujitsu         S.Takami        Create          S21_NA#54879
 * </pre>
 */

/**
 * @author q05163
 *
 */
public class NWAL1500RmaCheckComponentBean {
    /** Mdse Cd */
    private String mdseCd = null;
    /** Serial Number */
    private String serNum = null;
    /** Checked Flag */
    private boolean checkedFlg = false;

    /**
     * <Pre>
     * Contractor
     * @param rmaLineMsg RAM Global Line Message
     * </pre>
     */
    public NWAL1500RmaCheckComponentBean(NWAL1500_DSMsg rmaLineMsg) {
        this.mdseCd = rmaLineMsg.mdseCd_RL.getValue();
        if (ZYPCommonFunc.hasValue(rmaLineMsg.serNum_RL)) {
            this.serNum = rmaLineMsg.serNum_RL.getValue();
        } else {
            this.serNum = null;
        }
        this.checkedFlg = false;
    }

    /**
     * <pre>
     * Set checked flag, if mdseCd and serNum are same as this bean.
     * @param flg Checked Flag
     * </pre>
     */
    public void setCheckedFlg(boolean flg) {
        this.checkedFlg = flg;
    }

    /**
     * <pre>
     * Get Checked Flag
     * @return checked flag
     * </pre>
     */
    public boolean getCheckedFlg() {
        return this.checkedFlg;
    }

    /**
     * <pre>
     * check same item of this been
     * @param prmMdseCd Mdse Code
     * @param prmSerNum Serial Number
     * @return
     * </pre>
     */
    public boolean isSameItem(String prmMdseCd, String prmSerNum) {

        if (S21StringUtil.isEquals(mdseCd, this.mdseCd)) {
            if (ZYPCommonFunc.hasValue(this.serNum) //
                    && ZYPCommonFunc.hasValue(serNum)) {
                if (this.serNum.compareTo(serNum) == 0) {
                    return true;
                }
            } else if (!ZYPCommonFunc.hasValue(this.serNum) //
                    && !ZYPCommonFunc.hasValue(serNum)) {
                return true;
            }
        }
        return false;
    }
}
