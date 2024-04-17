package com.canon.cusa.s21.api.NWZ.NWZC045001;

import java.io.Serializable;

public class MdseStructureData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String zerothProdCtrlCd = null;

    private String firstProdCtrlCd = null;

    private String scdProdCtrlCd = null;
    
    private String thirdProdCtrlCd = null;
    
    private String frthProdCtrlCd = null;
    
    private String fifthProdCtrlCd = null;
    
    private String sixthProdCtrlCd = null;

    private String svnthProdCtrlCd = null;
    
    private String mdseCd = null;
    

    public MdseStructureData() {
        super();
    }

    public String getFirstProdCtrlCd() {
        return firstProdCtrlCd;
    }

    public String getFifthProdCtrlCd() {
		return fifthProdCtrlCd;
	}

	public void setFifthProdCtrlCd(String fifthProdCtrlCd) {
		this.fifthProdCtrlCd = fifthProdCtrlCd;
	}

	public String getFrthProdCtrlCd() {
		return frthProdCtrlCd;
	}

	public void setFrthProdCtrlCd(String frthProdCtrlCd) {
		this.frthProdCtrlCd = frthProdCtrlCd;
	}

	public String getMdseCd() {
		return mdseCd;
	}

	public void setMdseCd(String mdseCd) {
		this.mdseCd = mdseCd;
	}

	public String getSixthProdCtrlCd() {
		return sixthProdCtrlCd;
	}

	public void setSixthProdCtrlCd(String sixthProdCtrlCd) {
		this.sixthProdCtrlCd = sixthProdCtrlCd;
	}

	public String getSvnthProdCtrlCd() {
		return svnthProdCtrlCd;
	}

	public void setSvnthProdCtrlCd(String svnthProdCtrlCd) {
		this.svnthProdCtrlCd = svnthProdCtrlCd;
	}

	public String getThirdProdCtrlCd() {
		return thirdProdCtrlCd;
	}

	public void setThirdProdCtrlCd(String thirdProdCtrlCd) {
		this.thirdProdCtrlCd = thirdProdCtrlCd;
	}

	public void setFirstProdCtrlCd(String firstProdCtrlCd) {
        this.firstProdCtrlCd = firstProdCtrlCd;
    }

    public String getScdProdCtrlCd() {
        return scdProdCtrlCd;
    }

    public void setScdProdCtrlCd(String scdProdCtrlCd) {
        this.scdProdCtrlCd = scdProdCtrlCd;
    }

    public String getZerothProdCtrlCd() {
        return zerothProdCtrlCd;
    }

    public void setZerothProdCtrlCd(String zerothProdCtrlCd) {
        this.zerothProdCtrlCd = zerothProdCtrlCd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("zerothProdCtrlCd=").append(this.getZerothProdCtrlCd()).append(',');
        sb.append("firstProdCtrlCd=").append(this.getFirstProdCtrlCd()).append(',');
        sb.append("scdProdCtrlCd=").append(this.getScdProdCtrlCd()).append(',');        
        sb.append("thirdProdCtrlCd=").append(this.getThirdProdCtrlCd()).append(',');     
        sb.append("frthProdCtrlCd=").append(this.getFrthProdCtrlCd()).append(',');
        sb.append("fifthProdCtrlCd=").append(this.getFifthProdCtrlCd()).append(',');
        sb.append("sixthProdCtrlCd=").append(this.getSixthProdCtrlCd()).append(',');
        sb.append("svnthProdCtrlCd=").append(this.getSvnthProdCtrlCd()).append(',');
        sb.append("mdseCd=").append(this.getMdseCd());
        return sb.toString();
    }

}
