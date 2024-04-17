package canon.salesforce.bulk;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.List;

import oracle.sql.ArrayDescriptor;

public class CanonE404SFQryRsltRec implements java.sql.SQLData, java.io.Serializable {
        private String sfdcid;
        private String attribute1;
        private String attribute2;
        private String attribute3;
        private String attribute4;
        private String attribute5;
        private String attribute6;
        private String attribute7;
        private String attribute8;
        private String attribute9;
        private String attribute10;
        private String attribute11;
        private String attribute12;
        private String attribute13;
        private String attribute14;
        private String attribute15;
        private String attribute16;
        private String attribute17;
        private String attribute18;
        private String attribute19;
        private String attribute20;
        private String jobId;

        public CanonE404SFQryRsltRec() {
        }

        public CanonE404SFQryRsltRec(String sfdcid, String attribute1,
				String attribute2, String attribute3, String attribute4,
				String attribute5, String attribute6, String attribute7,
				String attribute8, String attribute9, String attribute10,
				String attribute11, String attribute12, String attribute13,
				String attribute14, String attribute15, String attribute16,
				String attribute17, String attribute18, String attribute19,
				String attribute20, String jobId) {
			super();
			this.sfdcid = sfdcid;
			this.attribute1 = attribute1;
			this.attribute2 = attribute2;
			this.attribute3 = attribute3;
			this.attribute4 = attribute4;
			this.attribute5 = attribute5;
			this.attribute6 = attribute6;
			this.attribute7 = attribute7;
			this.attribute8 = attribute8;
			this.attribute9 = attribute9;
			this.attribute10 = attribute10;
			this.attribute11 = attribute11;
			this.attribute12 = attribute12;
			this.attribute13 = attribute13;
			this.attribute14 = attribute14;
			this.attribute15 = attribute15;
			this.attribute16 = attribute16;
			this.attribute17 = attribute17;
			this.attribute18 = attribute18;
			this.attribute19 = attribute19;
			this.attribute20 = attribute20;
			this.jobId = jobId;
		}

		public String getAttribute(int pos){
        	String retStr;
        	switch (pos){
        	case 1:
        		retStr = attribute1;
        		break;
        	case 2:
        		retStr = attribute2;
        		break;
        	case 3:
        		retStr = attribute3;
        		break;
        	case 4:
        		retStr = attribute4;
        		break;
        	case 5:
        		retStr = attribute5;
        		break;
        	case 6:
        		retStr = attribute6;
        		break;
        	case 7:
        		retStr = attribute6;
        		break;
        	case 8:
        		retStr = attribute8;
        		break;
        	case 9:
        		retStr = attribute9;
        		break;
        	case 10:
        		retStr = attribute10;
        		break;
        	case 11:
        		retStr = attribute11;
        		break;
        	case 12:
        		retStr = attribute12;
        		break;
        	case 13:
        		retStr = attribute13;
        		break;
        	case 14:
        		retStr = attribute14;
        		break;
        	case 15:
        		retStr = attribute15;
        		break;
        	case 16:
        		retStr = attribute16;
        		break;
        	case 17:
        		retStr = attribute17;
        		break;
        	case 18:
        		retStr = attribute18;
        		break;
        	case 19:
        		retStr = attribute19;
        		break;
        	case 20:
        		retStr = attribute20;
        		break;
        		
        	default:
        		retStr = null;
        		break;
        	}
        	return retStr;
        }
        
        public void setAttribute(String attribute, int pos){
        	switch(pos){
        	case 1:
        		attribute1 = attribute;
        		break;
        	case 2:
        		attribute2 = attribute;
        		break;
        	case 3:
        		attribute3 = attribute;
        		break;
        	case 4:
        		attribute4 = attribute;
        		break;
        	case 5:
        		attribute5 = attribute;
        		break;
        	case 6:
        		attribute6 = attribute;
        		break;
        	case 7:
        		attribute7 = attribute;
        		break;
        	case 8:
        		attribute8 = attribute;
        		break;
        	case 9:
        		attribute9 = attribute;
        		break;
        	case 10:
        		attribute10 = attribute;
        		break;
        	case 11:
        		attribute11 = attribute;
        		break;
        	case 12:
        		attribute12 = attribute;
        		break;
        	case 13:
        		attribute13 = attribute;
        		break;
        	case 14:
        		attribute14 = attribute;
        		break;
        	case 15:
        		attribute15 = attribute;
        		break;
        	case 16:
        		attribute16 = attribute;
        		break;
        	case 17:
        		attribute17 = attribute;
        		break;
        	case 18:
        		attribute18 = attribute;
        		break;
        	case 19:
        		attribute19 = attribute;
        		break;
        	case 20:
        		attribute20 = attribute;
        		break;
        		
        	}
        }
        public String getAttribute1() {
            return attribute1;
        }

        public void setAttribute1(String attribute1) {
            this.attribute1 = attribute1;
        }
        
        public String getAttribute2() {
            return attribute2;
        }

        public void setAttribute2(String attribute2) {
            this.attribute2 = attribute2;
        }
        
        public String getAttribute3() {
            return attribute3;
        }

        public void setAttribute3(String attribute3) {
            this.attribute3 = attribute3;
        }
        
        public String getAttribute4() {
            return attribute4;
        }

        public void setAttribute4(String attribute4) {
            this.attribute4 = attribute4;
        }
        
        public String getAttribute5() {
            return attribute5;
        }

        public void setAttribute5(String attribute5) {
            this.attribute5 = attribute5;
        }
        
        public String getAttribute6() {
            return attribute6;
        }

        public void setAttribute6(String attribute6) {
            this.attribute6 = attribute6;
        }
        
        public String getAttribute7() {
            return attribute7;
        }

        public void setAttribute7(String attribute7) {
            this.attribute7 = attribute7;
        }
        
        public String getAttribute8() {
            return attribute8;
        }

        public void setAttribute8(String attribute8) {
            this.attribute8 = attribute8;
        }
        
        public String getAttribute9() {
            return attribute9;
        }

        public void setAttribute9(String attribute9) {
            this.attribute9 = attribute9;
        }
        
        public String getAttribute10() {
            return attribute10;
        }

        public void setAttribute10(String attribute10) {
            this.attribute10 = attribute10;
        }

        public String getAttribute11() {
			return attribute11;
		}

		public void setAttribute11(String attribute11) {
			this.attribute11 = attribute11;
		}

		public String getAttribute12() {
			return attribute12;
		}

		public void setAttribute12(String attribute12) {
			this.attribute12 = attribute12;
		}

		public String getAttribute13() {
			return attribute13;
		}

		public void setAttribute13(String attribute13) {
			this.attribute13 = attribute13;
		}

		public String getAttribute14() {
			return attribute14;
		}

		public void setAttribute14(String attribute14) {
			this.attribute14 = attribute14;
		}

		public String getAttribute15() {
			return attribute15;
		}

		public void setAttribute15(String attribute15) {
			this.attribute15 = attribute15;
		}

		public String getAttribute16() {
			return attribute16;
		}

		public void setAttribute16(String attribute16) {
			this.attribute16 = attribute16;
		}

		public String getAttribute17() {
			return attribute17;
		}

		public void setAttribute17(String attribute17) {
			this.attribute17 = attribute17;
		}

		public String getAttribute18() {
			return attribute18;
		}

		public void setAttribute18(String attribute18) {
			this.attribute18 = attribute18;
		}

		public String getAttribute19() {
			return attribute19;
		}

		public void setAttribute19(String attribute19) {
			this.attribute19 = attribute19;
		}

		public String getAttribute20() {
			return attribute20;
		}

		public void setAttribute20(String attribute20) {
			this.attribute20 = attribute20;
		}

		public String getSfdcid() {
            return sfdcid;
        }

        public void setSfdcid(String sfdcid) {
            this.sfdcid = sfdcid;
        }
       
        public void setJobId(String jobId){
        	this.jobId = jobId;
        }
        
        public String getJobId(){
        	return jobId;
        }

        public String getSQLTypeName() throws SQLException {
            return "CANON_E404_SF_QRY_RSLT_REC";
        }

        public void readSQL(SQLInput stream, String typeName) throws SQLException {
            sfdcid = stream.readString();
            attribute1 = stream.readString();
            attribute2 = stream.readString();
            attribute3 = stream.readString();
            attribute4 = stream.readString();
            attribute5 = stream.readString();
            attribute6 = stream.readString();
            attribute7 = stream.readString();
            attribute8 = stream.readString();
            attribute9 = stream.readString();
            attribute10 = stream.readString();
            attribute11 = stream.readString();
            attribute12 = stream.readString();
            attribute13 = stream.readString();
            attribute14 = stream.readString();
            attribute15 = stream.readString();
            attribute16 = stream.readString();
            attribute17 = stream.readString();
            attribute18 = stream.readString();
            attribute19 = stream.readString();
            attribute20 = stream.readString();
            
            jobId = stream.readString();
        }

        public void writeSQL(SQLOutput stream) throws SQLException {
           
            stream.writeString(sfdcid);
            stream.writeString(attribute1);
            stream.writeString(attribute2);
            stream.writeString(attribute3);
            stream.writeString(attribute4);
            stream.writeString(attribute5);
            stream.writeString(attribute6);
            stream.writeString(attribute7);
            stream.writeString(attribute8);
            stream.writeString(attribute9);
            stream.writeString(attribute10);
            stream.writeString(attribute11);
            stream.writeString(attribute12);
            stream.writeString(attribute13);
            stream.writeString(attribute14);
            stream.writeString(attribute15);
            stream.writeString(attribute16);
            stream.writeString(attribute17);
            stream.writeString(attribute18);
            stream.writeString(attribute19);
            stream.writeString(attribute20);
            stream.writeString(jobId);
        }
        
        public static oracle.sql.ARRAY canonE404SFQryRsltTblListToArray(List list, Connection connection) throws SQLException {
   		 System.out.println("connection : " +connection);
   		 System.out.println("connection : " +connection.getMetaData().getConnection());
   		ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E404_SF_QRY_RSLT_TBL",connection.getMetaData().getConnection());
   		System.out.println("arraydesc : " +arraydesc);
            //ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E404_SF_QRY_RSLT_TBL", connection);
            CanonE404SFQryRsltRec[] elements = (CanonE404SFQryRsltRec[]) list.toArray(new CanonE404SFQryRsltRec[0]);
            oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, connection.getMetaData().getConnection(), elements);
            return array;
        }

        @Override
		public String toString() {
			return "CanonE404SFQryRsltRec [sfdcid=" + sfdcid
					+ ", attribute1=" + attribute1 + ", attribute2="
					+ attribute2 + ", attribute3=" + attribute3
					+ ", attribute4=" + attribute4 + ", attribute5="
					+ attribute5 + ", attribute6=" + attribute6
					+ ", attribute7=" + attribute7 + ", attribute8="
					+ attribute8 + ", attribute9=" + attribute9
					+ ", attribute10=" + attribute10 + ", attribute11="
					+ attribute11 + ", attribute12=" + attribute12
					+ ", attribute13=" + attribute13 + ", attribute14="
					+ attribute14 + ", attribute15=" + attribute15
					+ ", attribute16=" + attribute16 + ", attribute17="
					+ attribute17 + ", attribute18=" + attribute18
					+ ", attribute19=" + attribute19 + ", attribute20="
					+ attribute20 + ", jobId=" + jobId + "]";
		}
    }
