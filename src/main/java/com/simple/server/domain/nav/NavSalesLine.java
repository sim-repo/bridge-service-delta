package com.simple.server.domain.nav;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.SalesLineMsg;

@Entity
@Table(name = "[Bitrix Queue]")
@DiscriminatorValue("37")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NavSalesLine extends ANavSorderQueue{
	
	private int tableId = 37;
	
	NavSorder sorder;
	
	public void setSorder(NavSorder sorder) {
		this.sorder = sorder;
	}
		
	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	@Override
	public void copyBtxFields(ANavSorderQueue sorderQueue) {		
		super.copyBtxFields(sorderQueue);
		setNavWineShopCustomerNo(sorderQueue.getNavWineShopCustomerNo());
	}

	@Override
	public void copyFrom(IContract msg) throws Exception {
		if(msg == null)
			return;
		if(msg instanceof SalesLineMsg){
			SalesLineMsg sl = (SalesLineMsg)msg;

			if (sl.getOuterLineId() != null)	
				this.setBtxLineNo(sl.getOuterLineId());
			if (sl.getItemNo() != null)	
				this.setNavItemNo(sl.getItemNo());
			if (sl.getQuantity() != null)	
				this.setNavQuantity(sl.getQuantity());
			if (sl.getLineAmount() != null)	
				this.setNavLineAmount(sl.getLineAmount());
			if (sl.getVariantCode() != null)	
				this.setNavVariantCode(sl.getVariantCode());
			if (sl.getLineDiscountPercent() != null)	
				this.setNavLineDiscountPercent(sl.getLineDiscountPercent());
		}
		
	}
	
	@Override
	public void format() throws Exception {
		INavRec r = this;
		if(!(r instanceof NavSalesLine))
			throw new Exception("NavSalesLine must be formatted only");
		NavSalesLine navSL = (NavSalesLine)this;		
		
		String btxCustomerNo = navSL.getBtxCustomerNo();
		navSL.copyBtxFields(sorder);			
		if(btxCustomerNo!=null && btxCustomerNo!="")
			if(btxCustomerNo != sorder.getBtxCustomerNo())
				navSL.setBtxCustomerNo(btxCustomerNo);	
		commonFormat(navSL);
	}

	
	
	
	public NavSorder getSorder() {
		return sorder;
	}

	@Override
	public String toString() {
		return "NavSalesLine [sorder=" + sorder + ", entryNo=" + entryNo + ", juuid=" + juuid + ", btxDate=" + btxDate
				+ ", btxTime=" + btxTime + ", btxCustomerNo=" + btxCustomerNo + ", btxSorderNo=" + btxSorderNo
				+ ", btxLineNo=" + btxLineNo + ", navSorderNo=" + navSorderNo + ", navOfficialAgreementNo="
				+ navOfficialAgreementNo + ", navPrivateAgreementNo=" + navPrivateAgreementNo
				+ ", navFreewareAgreementNo=" + navFreewareAgreementNo + ", navCustomerNo=" + navCustomerNo
				+ ", navCustomerName=" + navCustomerName + ", navCustomerName2=" + navCustomerName2
				+ ", navCustomerName3=" + navCustomerName3 + ", navSalespersonCode=" + navSalespersonCode
				+ ", navContact=" + navContact + ", navContact2=" + navContact2 + ", navPhoneNo=" + navPhoneNo
				+ ", navPhoneNo2=" + navPhoneNo2 + ", navEmail=" + navEmail + ", navAddress=" + navAddress
				+ ", navAddress2=" + navAddress2 + ", navAddress3=" + navAddress3 + ", navKladrRegion=" + navKladrRegion
				+ ", navKladrCity=" + navKladrCity + ", navKladrDistrict=" + navKladrDistrict + ", navKladrStreet="
				+ navKladrStreet + ", navKladrLocality=" + navKladrLocality + ", navKladrHouse=" + navKladrHouse
				+ ", navKladrBuilding=" + navKladrBuilding + ", navKladrFlat=" + navKladrFlat + ", navKladrVladenie="
				+ navKladrVladenie + ", navKladrCorpus=" + navKladrCorpus + ", navKladrOffice=" + navKladrOffice
				+ ", navKladrAdditional=" + navKladrAdditional + ", navDeliveryTimeFrom=" + navDeliveryTimeFrom
				+ ", navDeliveryTimeTo=" + navDeliveryTimeTo + ", navItemNo=" + navItemNo + ", navQuantity="
				+ navQuantity + ", navVariantCode=" + navVariantCode + ", navLineDiscountPercent="
				+ navLineDiscountPercent + ", navLineAmount=" + navLineAmount + ", navShipmentDate=" + navShipmentDate
				+ ", navPreliminaryOrder=" + navPreliminaryOrder + ", navSorderSource=" + navSorderSource
				+ ", navSorderDate=" + navSorderDate + ", navComment1=" + navComment1 + ", navComment2=" + navComment2
				+ ", navComment3=" + navComment3 + ", navComment4=" + navComment4 + ", navShipmentMethodCode="
				+ navShipmentMethodCode + ", navWineShopCustomerNo=" + navWineShopCustomerNo + ", navSimpleWaters="
				+ navSimpleWaters + ", navState=" + navState + ", navError=" + navError + ", navErrorId=" + navErrorId
				+ ", navReplyDateTime=" + navReplyDateTime + ", btxUserID=" + btxUserID + ", navPaymentTerms="
				+ navPaymentTerms + ", navCompanyName=" + navCompanyName + ", navDeliveryComment1="
				+ navDeliveryComment1 + ", navDeliveryComment2=" + navDeliveryComment2 + ", responseContractClass="
				+ responseContractClass + "]";
	}
	
	
	
}
