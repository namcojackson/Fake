package business.servlet.NPAL1340.common;

import java.util.ArrayList;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1340.NPAL1340BMsg;
import business.servlet.NPAL1340.constant.NPAL1340Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 * 2016/03/25   CSAI            K.Lee           Update          QC#5957
 * 09/20/2017   CITS            T.Tokutomi      Update          QC#21191
 * 08/06/2019   CITS            R.Kurahashi     Update          QC#51625
 *</pre>
 */
public class NPAL1340CommonLogic implements NPAL1340Constant{

    /** S21UserProfileService profileService */
    private static S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
    
    /**
     * The parameter for the Sub Screen transition is set.
     *
     * @param bmsg NPAL1340BMsg
     * @param btnNum btnNum
     * @return ArrayList
     */
    public static ArrayList<EZDBStringItem> getArraySerialNum(NPAL1340BMsg bmsg, int btnNum) {
        ArrayList<EZDBStringItem> serNumArray = new ArrayList<EZDBStringItem>();

        serNumArray.add(bmsg.N.no(btnNum).serNum_A0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_A1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_A2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_A3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_A4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_A5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_A6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_A7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_A8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_A9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_B0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_B1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_B2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_B3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_B4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_B5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_B6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_B7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_B8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_B9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_C0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_C1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_C2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_C3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_C4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_C5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_C6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_C7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_C8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_C9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_D0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_D1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_D2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_D3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_D4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_D5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_D6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_D7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_D8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_D9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_E0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_E1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_E2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_E3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_E4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_E5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_E6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_E7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_E8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_E9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_F0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_F1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_F2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_F3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_F4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_F5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_F6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_F7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_F8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_F9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_G0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_G1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_G2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_G3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_G4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_G5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_G6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_G7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_G8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_G9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_H0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_H1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_H2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_H3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_H4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_H5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_H6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_H7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_H8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_H9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_I0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_I1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_I2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_I3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_I4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_I5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_I6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_I7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_I8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_I9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_J0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_J1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_J2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_J3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_J4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_J5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_J6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_J7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_J8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_J9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_K0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_K1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_K2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_K3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_K4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_K5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_K6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_K7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_K8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_K9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_L0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_L1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_L2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_L3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_L4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_L5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_L6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_L7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_L8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_L9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_M0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_M1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_M2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_M3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_M4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_M5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_M6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_M7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_M8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_M9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_N0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_N1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_N2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_N3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_N4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_N5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_N6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_N7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_N8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_N9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_O0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_O1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_O2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_O3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_O4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_O5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_O6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_O7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_O8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_O9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_P0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_P1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_P2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_P3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_P4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_P5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_P6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_P7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_P8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_P9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_Q0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_Q1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_Q2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_Q3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_Q4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_Q5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_Q6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_Q7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_Q8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_Q9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_R0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_R1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_R2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_R3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_R4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_R5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_R6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_R7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_R8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_R9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_S0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_S1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_S2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_S3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_S4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_S5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_S6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_S7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_S8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_S9);
        serNumArray.add(bmsg.N.no(btnNum).serNum_T0);
        serNumArray.add(bmsg.N.no(btnNum).serNum_T1);
        serNumArray.add(bmsg.N.no(btnNum).serNum_T2);
        serNumArray.add(bmsg.N.no(btnNum).serNum_T3);
        serNumArray.add(bmsg.N.no(btnNum).serNum_T4);
        serNumArray.add(bmsg.N.no(btnNum).serNum_T5);
        serNumArray.add(bmsg.N.no(btnNum).serNum_T6);
        serNumArray.add(bmsg.N.no(btnNum).serNum_T7);
        serNumArray.add(bmsg.N.no(btnNum).serNum_T8);
        serNumArray.add(bmsg.N.no(btnNum).serNum_T9);

        return serNumArray;
    }

    /**
     * The parameter for the Sub Screen transition is set.
     *
     * @param bmsg NPAL1340BMsg
     * @param btnNum btnNum
     * @return ArrayList
     */
    public static ArrayList<EZDBBigDecimalItem> getArrayRowNum(NPAL1340BMsg bmsg, int btnNum) {
        ArrayList<EZDBBigDecimalItem> rowArray = new ArrayList<EZDBBigDecimalItem>();

        rowArray.add(bmsg.N.no(btnNum).xxRowNum_A0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_A1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_A2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_A3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_A4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_A5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_A6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_A7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_A8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_A9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_B0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_B1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_B2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_B3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_B4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_B5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_B6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_B7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_B8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_B9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_C0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_C1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_C2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_C3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_C4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_C5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_C6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_C7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_C8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_C9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_D0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_D1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_D2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_D3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_D4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_D5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_D6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_D7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_D8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_D9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_E0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_E1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_E2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_E3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_E4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_E5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_E6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_E7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_E8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_E9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_F0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_F1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_F2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_F3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_F4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_F5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_F6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_F7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_F8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_F9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_G0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_G1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_G2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_G3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_G4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_G5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_G6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_G7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_G8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_G9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_H0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_H1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_H2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_H3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_H4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_H5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_H6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_H7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_H8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_H9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_I0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_I1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_I2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_I3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_I4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_I5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_I6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_I7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_I8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_I9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_J0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_J1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_J2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_J3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_J4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_J5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_J6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_J7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_J8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_J9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_K0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_K1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_K2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_K3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_K4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_K5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_K6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_K7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_K8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_K9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_L0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_L1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_L2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_L3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_L4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_L5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_L6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_L7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_L8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_L9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_M0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_M1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_M2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_M3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_M4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_M5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_M6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_M7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_M8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_M9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_N0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_N1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_N2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_N3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_N4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_N5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_N6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_N7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_N8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_N9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_O0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_O1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_O2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_O3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_O4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_O5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_O6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_O7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_O8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_O9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_P0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_P1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_P2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_P3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_P4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_P5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_P6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_P7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_P8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_P9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_Q0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_Q1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_Q2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_Q3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_Q4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_Q5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_Q6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_Q7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_Q8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_Q9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_R0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_R1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_R2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_R3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_R4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_R5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_R6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_R7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_R8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_R9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_S0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_S1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_S2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_S3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_S4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_S5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_S6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_S7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_S8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_S9);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_T0);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_T1);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_T2);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_T3);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_T4);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_T5);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_T6);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_T7);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_T8);
        rowArray.add(bmsg.N.no(btnNum).xxRowNum_T9);

        return rowArray;
    }

    /**
     * The parameter for the Sub Screen transition is set.
     *
     * @param bmsg NPAL1340BMsg
     * @param btnNum btnNum
     * @return ArrayList
     */
    public static ArrayList<EZDBStringItem> getArrayProcFlg(NPAL1340BMsg bmsg, int btnNum) {
        ArrayList<EZDBStringItem> flgArray = new ArrayList<EZDBStringItem>();

        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_A0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_A1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_A2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_A3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_A4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_A5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_A6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_A7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_A8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_A9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_B0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_B1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_B2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_B3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_B4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_B5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_B6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_B7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_B8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_B9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_C0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_C1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_C2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_C3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_C4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_C5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_C6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_C7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_C8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_C9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_D0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_D1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_D2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_D3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_D4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_D5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_D6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_D7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_D8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_D9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_E0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_E1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_E2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_E3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_E4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_E5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_E6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_E7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_E8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_E9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_F0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_F1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_F2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_F3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_F4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_F5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_F6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_F7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_F8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_F9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_G0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_G1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_G2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_G3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_G4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_G5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_G6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_G7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_G8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_G9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_H0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_H1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_H2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_H3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_H4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_H5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_H6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_H7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_H8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_H9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_I0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_I1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_I2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_I3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_I4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_I5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_I6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_I7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_I8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_I9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_J0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_J1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_J2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_J3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_J4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_J5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_J6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_J7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_J8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_J9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_K0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_K1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_K2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_K3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_K4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_K5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_K6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_K7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_K8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_K9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_L0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_L1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_L2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_L3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_L4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_L5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_L6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_L7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_L8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_L9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_M0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_M1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_M2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_M3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_M4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_M5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_M6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_M7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_M8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_M9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_N0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_N1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_N2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_N3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_N4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_N5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_N6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_N7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_N8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_N9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_O0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_O1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_O2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_O3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_O4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_O5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_O6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_O7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_O8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_O9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_P0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_P1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_P2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_P3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_P4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_P5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_P6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_P7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_P8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_P9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_Q0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_Q1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_Q2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_Q3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_Q4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_Q5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_Q6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_Q7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_Q8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_Q9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_R0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_R1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_R2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_R3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_R4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_R5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_R6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_R7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_R8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_R9);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_S0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_S1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_S2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_S3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_S4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_S5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_S6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_S7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_S8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_S9); 
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_T0);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_T1);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_T2);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_T3);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_T4);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_T5);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_T6);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_T7);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_T8);
        flgArray.add(bmsg.N.no(btnNum).xxSetFlg_T9);

        return flgArray;
    }


    /**
     * @param  scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NPAL1340(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);

        scrnAppli.setButtonConfirmMsg("CMN_Reset", "ZZM8102I", new String[]{"Reset"}, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 0);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg   NPAL1340BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NPAL1340BMsg scrnMsg) {

        // Role Check
        boolean hasUpdRoleFlg = hasUpdateRole();

        scrnMsg.setInputProtected(false);

        scrnAppli.setButtonProperties("OnClick_Search", "OnClick_Search", "Search", 1, null);
        scrnAppli.setButtonProperties("OnClick_Prev", "OnClick_Prev", "Prev", 1, null);
        scrnAppli.setButtonProperties("OnClick_Next", "OnClick_Next", "Next", 1, null);
        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);

        if (hasUpdRoleFlg) {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }

        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        scrnMsg.cpoOrdNum_H2.setInputProtected(true);
        scrnMsg.prntVndCd_H2.setInputProtected(true);
        scrnMsg.prntVndNm_H2.setInputProtected(true);
        scrnMsg.vndCd_H2.setInputProtected(true);
        scrnMsg.vndNm_H2.setInputProtected(true);
        scrnMsg.sellToCustCd_H2.setInputProtected(true);
        scrnMsg.billToCustCd_H2.setInputProtected(true);
        scrnMsg.dsAcctNm_H2.setInputProtected(true);
        scrnMsg.custIssPoNum_H2.setInputProtected(true);
        scrnMsg.custIssPoDt_H2.setInputProtected(true);
        scrnMsg.xxAllLineAddr_H2.setInputProtected(true);
        scrnMsg.vndIssPoOrdNum_H2.setInputProtected(true);
        scrnMsg.poHdrStsDescTxt_H2.setInputProtected(true);

        scrnMsg.frtCondDescTxt_H2.setInputProtected(true);
        scrnMsg.spTotFuncFrtAmt_H2.setInputProtected(false);
        scrnMsg.entPoDtlDealNetAmt_H2.setInputProtected(true);
        scrnMsg.ccyCd_H2.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dispPoDtlLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem8Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem8Txt_A2.setInputProtected(true);
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).poDispQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).poBalQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).poRcvQty_A1.setInputProtected(false);
            scrnMsg.A.no(i).proNum_A1.setInputProtected(false);
            scrnMsg.A.no(i).poOrdDtlCmntTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndIssPoOrdNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).poRcvEtaDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).domInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).cpoOrdTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).cpoDtlLineSubNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).shpgPlnNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).poRcvNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).poRcvLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).carrCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem20Txt_A1.setInputProtected(true);

            // QC#51625
//            if (!hasUpdRoleFlg || scrnMsg.A.no(i).poStsCd_A1.getValue().equals(PO_STS.CLOSED) || scrnMsg.A.no(i).poStsCd_A1.getValue().equals(PO_STS.CANCELLED)) {
            if (!hasUpdRoleFlg || !scrnMsg.A.no(i).shpgStsCd_A1.getValue().equals(SHPG_STS.P_OR_O_PRINTED)) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(true);
                scrnMsg.A.no(i).poRcvQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).proNum_A1.setInputProtected(true);
                // QC#21191
                scrnMsg.A.no(i).carrNm_A1.setInputProtected(true);
                scrnAppli.setButtonProperties("OpenWin_Carrier", i, "OpenWin_Carrier", "Carr", 0, null);
            } else {
                if (scrnMsg.xxChkBox_H2.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || scrnMsg.xxChkBox_H3.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                    scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(false);
                }
                scrnMsg.A.no(i).poRcvQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).proNum_A1.setInputProtected(false);
                // QC#21191
                scrnMsg.A.no(i).carrNm_A1.setInputProtected(false);
                scrnAppli.setButtonProperties("OpenWin_Carrier", i, "OpenWin_Carrier", "Carr", 1, null);
            }
            scrnAppli.setButtonProperties("OpenWin_Serial", i, "OpenWin_Serial", "Ser", 1, null);
        }

        if (hasUpdRoleFlg && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg.getValue()))) {
            scrnMsg.vndInvNum_H2.setInputProtected(false);
            scrnMsg.invDt_H2.setInputProtected(false);
            scrnMsg.spTotFuncFrtAmt_H2.setInputProtected(false);
            if (!ZYPCommonFunc.hasValue(scrnMsg.invDt_H2)) {
                String slsDt = ZYPDateUtil.getSalesDate();
                ZYPEZDItemValueSetter.setValue(scrnMsg.invDt_H2, slsDt);
            }

            if (scrnMsg.xxChkBox_H2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                scrnMsg.xxChkBox_H2.setInputProtected(false);
                scrnMsg.xxChkBox_H3.setInputProtected(true);
            } else if (scrnMsg.xxChkBox_H3.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                scrnMsg.xxChkBox_H2.setInputProtected(true);
                scrnMsg.xxChkBox_H3.setInputProtected(false);
            } else {
                scrnMsg.xxChkBox_H2.setInputProtected(false);
                scrnMsg.xxChkBox_H3.setInputProtected(false);
            }

            scrnMsg.poOrdDtlCmntTxt_H2.setInputProtected(false);
            scrnAppli.setButtonEnabled("btn2", true);
            scrnMsg.setFocusItem(scrnMsg.vndInvNum_H2);
        } else {
            scrnMsg.vndInvNum_H2.setInputProtected(true);
            scrnMsg.invDt_H2.setInputProtected(true);
            scrnMsg.spTotFuncFrtAmt_H2.setInputProtected(true);
            scrnMsg.xxChkBox_H2.setInputProtected(true);
            scrnMsg.xxChkBox_H3.setInputProtected(true);
            scrnMsg.poOrdDtlCmntTxt_H2.setInputProtected(true);
            scrnAppli.setButtonEnabled("btn2", false);
        }

    }

    /**
     * It is confirmed whether it is a user who has the update authority.
     *
     * @param profSer S21UserProfileService
     * @return boolean
     */
    public static boolean hasUpdateRole() {
        boolean hasUpdRoleFlg = false;
        String uid = profileService.getContextUserInfo().getUserId();
        hasUpdRoleFlg = profileService.isFunctionGranted(uid, ROLE_UPDATE);
        return hasUpdRoleFlg;
    }
}
