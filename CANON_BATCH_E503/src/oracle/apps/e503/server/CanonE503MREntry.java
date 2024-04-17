package oracle.apps.e503.server;

import java.sql.Timestamp;

public class CanonE503MREntry{
	private String svcMachMasterPk = null;
	private String svcTtlPhysMtrPk = null;
	private String svcBwPhysMtrPk = null;
	private String meterRead = null;
	private Timestamp mtrReadDt = null;
	private int  noOfMeters = 0;
	private String serialNumber = null;
	private String totalRead = null;
	private String bwRead = null;
	
	
	
	public String getSvcMachMasterPk() {
		return svcMachMasterPk;
	}
	public void setSvcMachMasterPk(String svcMachMasterPk) {
		this.svcMachMasterPk = svcMachMasterPk;
	}
	public String getSvcTtlPhysMtrPk() {
		return svcTtlPhysMtrPk;
	}
	public void setSvcTtlPhysMtrPk(String svcTtlPhysMtrPk) {
		this.svcTtlPhysMtrPk = svcTtlPhysMtrPk;
	}
	public String getSvcBwPhysMtrPk() {
		return svcBwPhysMtrPk;
	}
	public void setSvcBwPhysMtrPk(String svcBwPhysMtrPk) {
		this.svcBwPhysMtrPk = svcBwPhysMtrPk;
	}
	public String getMeterRead() {
		return meterRead;
	}
	public void setMeterRead(String meterRead) {
		this.meterRead = meterRead;
	}
	public Timestamp getMtrReadDt() {
		return mtrReadDt;
	}
	public void setMtrReadDt(Timestamp mtrReadDt) {
		this.mtrReadDt = mtrReadDt;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getTotalRead() {
		return totalRead;
	}
	public void setTotalRead(String totalRead) {
		this.totalRead = totalRead;
	}
	public String getBwRead() {
		return bwRead;
	}
	public void setBwRead(String bwRead) {
		this.bwRead = bwRead;
	}
	public int getNoOfMeters() {
		return noOfMeters;
	}
	public void setNoOfMeters(int noOfMeters) {
		this.noOfMeters = noOfMeters;
	}
	
	
}
