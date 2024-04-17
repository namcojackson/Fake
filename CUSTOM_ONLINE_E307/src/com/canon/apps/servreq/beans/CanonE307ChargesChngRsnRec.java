package com.canon.apps.servreq.beans;

import canon.apps.common.CanonConstants;
import java.sql.*;

public class CanonE307ChargesChngRsnRec implements SQLData
{

    private String strReasonCode;
    private String strReasonNm;
    private String strReasonDesc;

    public CanonE307ChargesChngRsnRec()
    {
    }

    public CanonE307ChargesChngRsnRec(String strReasonCode, String strReasonNm, String strReasonDesc)
    {
        this.strReasonCode = strReasonCode;
        this.strReasonNm = strReasonNm;
        this.strReasonDesc = strReasonDesc;
    }

    public String getSQLTypeName()
        throws SQLException
    {
        return CanonConstants.SCHEMA_NAME+".CANON_E307_REASON_CODE_REC";
    }

    public void readSQL(SQLInput stream, String typeName)
        throws SQLException
    {
        strReasonCode = stream.readString();
        strReasonNm = stream.readString();
        strReasonDesc = stream.readString();
    }

    public void writeSQL(SQLOutput stream)
        throws SQLException
    {
        stream.writeString(strReasonCode);
        stream.writeString(strReasonNm);
        stream.writeString(strReasonDesc);
    }

    public CanonE307ChargesChngRsnRec(String strReasonCode, String strReasonNm)
    {
        this.strReasonCode = strReasonCode;
        this.strReasonNm = strReasonNm;
        this.strReasonNm = strReasonDesc;
    }

    public String getStrReasonCode()
    {
        return strReasonCode;
    }

    public void setStrReasonCode(String strReasonCode)
    {
        this.strReasonCode = strReasonCode;
    }

    public String getStrReasonNm()
    {
        return strReasonNm;
    }

    public void setStrReasonNm(String strReasonNm)
    {
        this.strReasonNm = strReasonNm;
    }

    public String getStrReasonDesc()
    {
        return strReasonDesc;
    }

    public void setStrReasonDesc(String strReasonDesc)
    {
        this.strReasonDesc = strReasonDesc;
    }

	@Override
	public String toString() {
		return "CanonE307ChargesChngRsnRec [strReasonCode=" + strReasonCode
				+ ", strReasonNm=" + strReasonNm + ", strReasonDesc="
				+ strReasonDesc + "]";
	}

}
