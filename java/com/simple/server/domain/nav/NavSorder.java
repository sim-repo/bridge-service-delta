package com.simple.server.domain.nav;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.SalesLineMsg;
import com.simple.server.domain.contract.SorderMsg;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NavSorder extends ANavSorderQueue{

	private int tableId = 36;
	
	private Set<NavSalesLine> salesLineList = new HashSet<NavSalesLine>();		
											
		
	public int getTableId() {
		return tableId;
	}
	
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	public Set<NavSalesLine> getSalesLineList() {
		return salesLineList;
	}
	
	public void setSalesLineList(Set<NavSalesLine> salesLineList) {
		this.salesLineList = salesLineList;
	}
	
	public void setNavSalesLine(NavSalesLine salesLine) {
		salesLineList.add(salesLine);
	}
	
	@Override
	public void copyFrom(IContract msg) throws Exception {
		
		if(!(msg instanceof SorderMsg))
			throw new Exception("msg must be instance of SalesOrder");
		
		SorderMsg sorder = (SorderMsg)msg;
			
		
		
		this.setBtxCustomerNo(sorder.getOuterCustomerId());
		this.setBtxSorderNo(sorder.getOuterSorderId());
		this.setBtxDate(sorder.getRequestInDatetime());
		this.setBtxTime(sorder.getRequestInDatetime());		
		this.setBtxUserID(sorder.getOuterUserID());
		this.setJuuid(sorder.getJuuid());
		
		if (sorder.getSorderNo() != null)
			this.setNavSorderNo(sorder.getSorderNo());
		if (sorder.getSorderDate() != null)
			this.setNavSorderDate(sorder.getSorderDate());
		if (sorder.getCustomerId() != null)
			this.setNavCustomerNo(sorder.getCustomerId());
		if (sorder.getCustomerName() != null)	
			this.setNavFullCustomerName(sorder.getCustomerName());
		if (sorder.getAddress() != null)
			this.setNavAddress(sorder.getAddress());
		if (sorder.getComment() != null)
			this.setNavComment1(sorder.getComment());
		if (sorder.getContactId() != null)
			this.setNavContact(sorder.getContactId());
		if (sorder.getContact2Id() != null)
			this.setNavContact2(sorder.getContact2Id());		
		if (sorder.getEmail() != null)
			this.setNavEmail(sorder.getEmail());
		if (sorder.getPhoneNo() != null)			
			this.setNavPhoneNo(sorder.getPhoneNo());
		if (sorder.getPhoneNo2() != null)
			this.setNavPhoneNo2(sorder.getPhoneNo2());
		if (sorder.getFreewareAgreementId() != null)
			this.setNavFreewareAgreementNo(sorder.getFreewareAgreementId());
		if (sorder.getOfficialAgreementId() != null)
			this.setNavOfficialAgreementNo(sorder.getOfficialAgreementId());
		if (sorder.getPrivateAgreementId() != null)
			this.setNavPrivateAgreementNo(sorder.getPrivateAgreementId());			
		if (sorder.getShipmentDate() != null)
			this.setNavShipmentDate(sorder.getShipmentDate());
		if (sorder.getShipmentMethodCode() != null)
			this.setNavShipmentMethodCode(sorder.getShipmentMethodCode());
		if (sorder.getDeliveryTimeFrom() != null)
			this.setNavDeliveryTimeFrom(sorder.getDeliveryTimeFrom());
		if (sorder.getDeliveryTimeTo() != null)
			this.setNavDeliveryTimeTo(sorder.getDeliveryTimeTo());
		if (sorder.getSalespersonId() != null)
			this.setNavSalespersonCode(sorder.getSalespersonId());
		if (sorder.getSimpleWaters() != null)
			this.setNavSimpleWaters(sorder.getSimpleWaters());
		if (sorder.getWineShopCustomerNo() != null)
			this.setNavWineShopCustomerNo(sorder.getWineShopCustomerNo());
		if (sorder.getKladrRegion() != null)
			this.setNavKladrRegion(sorder.getKladrRegion());
		if (sorder.getKladrCity() != null)
			this.setNavKladrCity(sorder.getKladrCity());
		if (sorder.getKladrDistrict() != null)
			this.setNavKladrDistrict(sorder.getKladrDistrict());
		if (sorder.getKladrLocality() != null)
			this.setNavKladrLocality(sorder.getKladrLocality());
		if (sorder.getKladrStreet() != null)
			this.setNavKladrStreet(sorder.getKladrStreet());
		if (sorder.getKladrHouse() != null)
			this.setNavKladrHouse(sorder.getKladrHouse());
		if (sorder.getKladrFlat() != null)
			this.setNavKladrFlat(sorder.getKladrFlat());
		if (sorder.getKladrBuilding() != null)
			this.setNavKladrBuilding(sorder.getKladrBuilding());
		if (sorder.getKladrOffice() != null)
			this.setNavKladrOffice(sorder.getKladrOffice());
		if (sorder.getKladrVladenie() != null)
			this.setNavKladrVladenie(sorder.getKladrVladenie());
		if (sorder.getKladrAdditional() != null)
			this.setNavKladrAdditional(sorder.getKladrAdditional());
		if (sorder.getPaymentTerms() != null)
			this.setNavPaymentTerms(sorder.getPaymentTerms());
		if (sorder.getPreliminaryOrder() != null)
			this.setNavPreliminaryOrder(sorder.getPreliminaryOrder());
		if (sorder.getEndPointId()!= null)		
			this.setNavSorderSource(sorder.getEndPointId().toString());
			
		if(sorder.getCompanyName()!=null)
			this.setNavCompanyName(sorder.getCompanyName());
	
		
		for(SalesLineMsg sl: sorder.getSlList()){
			NavSalesLine navSL = new NavSalesLine();				
			navSL.copyFrom(sl);
			navSL.setSorder(this);			
			this.setNavSalesLine(navSL);
		}
		
	}

	@Override
	public void format() throws Exception {
		INavRec r = this;
		if(!(r instanceof NavSorder))
			throw new Exception("NavSorder must be formatted only");
		NavSorder navSorder = (NavSorder)this;		
		commonFormat(navSorder);
		
		for(NavSalesLine navSL: navSorder.getSalesLineList()){
			navSL.format();
		}
	}

	@Override
	public String toString() {
		return "NavSorder [salesLineList=" + salesLineList + ", entryNo=" + entryNo + ", juuid=" + juuid + ", btxDate="
				+ btxDate + ", btxTime=" + btxTime + ", btxCustomerNo=" + btxCustomerNo + ", btxSorderNo=" + btxSorderNo
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
