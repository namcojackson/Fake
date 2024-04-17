package com.canon.cusa.s21.common.NYX.NYXC889010.test;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

public class S21NwfUtilTokenObjExt extends S21NwfUtilTokenObj {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    List<S21NwfUtilTokenObjExtLine> lineData = new ArrayList<S21NwfUtilTokenObjExtLine>();
    
    public void addLineData(S21NwfUtilTokenObjExtLine data){
        lineData.add(data);
    }
    
    public S21NwfUtilTokenObjExtLine getLineData(int index){
        S21NwfUtilTokenObjExtLine ret = null;
        
        if (lineData.size() > index){
            ret = lineData.get(index);
        }
        
        return ret;
    }    
}


