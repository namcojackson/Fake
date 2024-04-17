/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC211001;

import java.util.ArrayList;

import business.db.SVC_MACH_MSTRTMsg;

/**
 * <pre>
 * Update SO Confirmation
 * Svc Config Machine Bean
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/28/2015   CITS            T.Tokutomi      Create
 *</pre>
 */
public class SvcConfigMachBean {

    /** mainMachine */
    private SVC_MACH_MSTRTMsg mainMachine;

    /** subMachineList */
    private ArrayList<SVC_MACH_MSTRTMsg> subMachine;

    /** mainMachRmv */
    private boolean mainMachRmv;

    /**
     * Default
     */
    public SvcConfigMachBean() {
        this.mainMachine = null;
        this.subMachine = new ArrayList<SVC_MACH_MSTRTMsg>();
        this.mainMachRmv = false;
    }

    /**
     * @return mainMachine
     */
    public SVC_MACH_MSTRTMsg getMainMachine() {
        return mainMachine;
    }

    /**
     * @param mainMachine set mainMachine
     */
    public void setMainMachine(SVC_MACH_MSTRTMsg mainMachine) {
        this.mainMachine = mainMachine;
    }

    /**
     * @return subMachine
     */
    public ArrayList<SVC_MACH_MSTRTMsg> getSubMachine() {
        return subMachine;
    }

    /**
     * @param mainMachRmv set mainMachRmv
     */
    public void setMainMachRmv(boolean mainMachRmv) {
        this.mainMachRmv = mainMachRmv;
    }

    /**
     * @return mainMachRmv
     */
    public boolean isMainMachRmv() {
        return mainMachRmv;
    }

}
