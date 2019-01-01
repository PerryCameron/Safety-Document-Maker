package com.safety;

import java.io.Serializable;

public abstract class Doc_ObjectSE001B implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3765695413213019693L;
	//Instance variables:
	protected int documentNumber;
	private java.util.Date dateCreated;
	protected String documentType;
	
	// constructor for benefit of sub-classes
	
		public Doc_ObjectSE001B() {
		super();
		setDateCreated(new java.util.Date());
	}

		public int getDocumentNumber() {
			return documentNumber;
		}

		public void setDocumentNumber(int documentNumber) {
			this.documentNumber = documentNumber;
		}

		public java.util.Date getDateCreated() {
			return dateCreated;
		}

		public void setDateCreated(java.util.Date dateCreated) {
			this.dateCreated = dateCreated;
		}

		public String getDocumentType() {
			return documentType;
		}

		public void setDocumentType(String documentType) {
			this.documentType = documentType;
		}

		@Override
		public String toString() {
			return "Document [documentNumber=" + documentNumber + ", dateCreated=" + dateCreated + ", documentType="
					+ documentType + "]";
		}
	
}
