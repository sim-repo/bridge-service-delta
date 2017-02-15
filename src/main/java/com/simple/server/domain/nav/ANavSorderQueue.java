package com.simple.server.domain.nav;

import com.simple.server.config.ContentType;
import com.simple.server.util.DateConvertHelper;

public abstract class ANavSorderQueue implements INavRec {

	protected Integer entryNo;

	protected String juuid;

	protected String btxDate;

	protected String btxTime;

	protected String btxCustomerNo;

	protected String btxSorderNo;

	protected String btxLineNo = "0";

	protected String navSorderNo = "";

	protected String navOfficialAgreementNo = "";

	protected String navPrivateAgreementNo = "";

	protected String navFreewareAgreementNo = "";

	protected String navCustomerNo = "";

	private String navFullCustomerName = "";

	protected String navCustomerName = "";

	protected String navCustomerName2 = "";

	protected String navCustomerName3 = "";

	protected String navSalespersonCode = "";

	protected String navContact = "";

	protected String navContact2 = "";

	protected String navPhoneNo = "";

	protected String navPhoneNo2 = "";

	protected String navEmail = "";

	protected String navAddress = "";

	protected String navAddress2 = "";

	protected String navAddress3 = "";

	protected String navKladrRegion = "";

	protected String navKladrCity = "";

	protected String navKladrDistrict = "";

	protected String navKladrStreet = "";

	protected String navKladrLocality = "";

	protected String navKladrHouse = "";

	protected String navKladrBuilding = "";

	protected String navKladrFlat = "";

	protected String navKladrVladenie = "";

	protected String navKladrCorpus = "";

	protected String navKladrOffice = "";

	protected String navKladrAdditional = "";

	protected String navDeliveryTimeFrom = DateConvertHelper.NAV_DEFAULT_TIME;

	protected String navDeliveryTimeTo = DateConvertHelper.NAV_DEFAULT_TIME;

	protected String navItemNo = "";

	protected String navQuantity = "";

	protected String navVariantCode = "";

	protected String navLineDiscountPercent = "";

	protected String navLineAmount = "";

	protected String navShipmentDate = DateConvertHelper.NAV_DEFAULT_DATE;

	protected String navPreliminaryOrder = "";

	protected String navSorderSource = "";

	protected String navSorderDate = DateConvertHelper.NAV_DEFAULT_DATE;

	protected String navComment1 = "";

	protected String navComment2 = "";

	protected String navComment3 = "";

	protected String navComment4 = "";

	protected String navShipmentMethodCode = "";

	protected String navWineShopCustomerNo = "";

	protected String navSimpleWaters = "";

	protected String navState = "";

	protected String navError = "";

	protected String navErrorId = "";

	protected String navReplyDateTime = DateConvertHelper.NAV_DEFAULT_DATE;

	protected String btxUserID = "";

	protected String navPaymentTerms = "";

	protected String navCompanyName = "";
	
	protected String navDeliveryComment1 = "";
		
	protected String navDeliveryComment2 = "";
	
	protected String responseContractClass = "";
	
	protected ContentType responseContentType = ContentType.ApplicationJson;
	
	public void copyBtxFields(ANavSorderQueue btxQueue) {
		setBtxDate(btxQueue.getBtxDate());
		setBtxTime(btxQueue.getBtxTime());
		setBtxSorderNo(btxQueue.getBtxSorderNo());
		setBtxCustomerNo(btxQueue.getBtxCustomerNo());
		setBtxUserID(btxQueue.getBtxUserID());
		this.setJuuid(btxQueue.getJuuid());
	}

	public Integer getEntryNo() {
		return entryNo;
	}

	public void setEntryNo(Integer entryNo) {
		this.entryNo = entryNo;
	}

	public String getBtxDate() {
		return btxDate;
	}

	public void setBtxDate(String btxDate) {
		this.btxDate = btxDate;
	}

	public String getBtxTime() {
		return btxTime;
	}

	public void setBtxTime(String btxTime) {
		this.btxTime = btxTime;
	}

	public String getBtxCustomerNo() {
		return btxCustomerNo;
	}

	public void setBtxCustomerNo(String btxCustomerNo) {
		this.btxCustomerNo = btxCustomerNo;
	}

	public String getBtxSorderNo() {
		return btxSorderNo;
	}

	public void setBtxSorderNo(String btxSorderNo) {
		this.btxSorderNo = btxSorderNo;
	}

	public String getBtxLineNo() {
		return btxLineNo;
	}

	public void setBtxLineNo(String btxLineNo) {
		this.btxLineNo = btxLineNo;
	}

	public String getNavSorderNo() {
		return navSorderNo;
	}

	public void setNavSorderNo(String navSorderNo) {
		this.navSorderNo = navSorderNo;
	}

	public String getNavOfficialAgreementNo() {
		return navOfficialAgreementNo;
	}

	public void setNavOfficialAgreementNo(String navOfficialAgreementNo) {
		this.navOfficialAgreementNo = navOfficialAgreementNo;
	}

	public String getNavPrivateAgreementNo() {
		return navPrivateAgreementNo;
	}

	public void setNavPrivateAgreementNo(String navPrivateAgreementNo) {
		this.navPrivateAgreementNo = navPrivateAgreementNo;
	}

	public String getNavFreewareAgreementNo() {
		return navFreewareAgreementNo;
	}

	public void setNavFreewareAgreementNo(String navFreewareAgreementNo) {
		this.navFreewareAgreementNo = navFreewareAgreementNo;
	}

	public String getNavCustomerNo() {
		return navCustomerNo;
	}

	public void setNavCustomerNo(String navCustomerNo) {
		this.navCustomerNo = navCustomerNo;
	}

	public String getNavCustomerName() {
		return navCustomerName;
	}

	public void setNavCustomerName(String navCustomerName) {
		this.navCustomerName = navCustomerName;
	}

	public String getNavCustomerName2() {
		return navCustomerName2;
	}

	public void setNavCustomerName2(String navCustomerName2) {
		this.navCustomerName2 = navCustomerName2;
	}

	public String getNavCustomerName3() {
		return navCustomerName3;
	}

	public void setNavCustomerName3(String navCustomerName3) {
		this.navCustomerName3 = navCustomerName3;
	}

	public String getNavSalespersonCode() {
		return navSalespersonCode;
	}

	public void setNavSalespersonCode(String navSalespersonCode) {
		this.navSalespersonCode = navSalespersonCode;
	}

	public String getNavContact() {
		return navContact;
	}

	public void setNavContact(String navContact) {
		this.navContact = navContact;
	}

	public String getNavContact2() {
		return navContact2;
	}

	public void setNavContact2(String navContact2) {
		this.navContact2 = navContact2;
	}

	public String getNavPhoneNo() {
		return navPhoneNo;
	}

	public void setNavPhoneNo(String navPhoneNo) {
		this.navPhoneNo = navPhoneNo;
	}

	public String getNavPhoneNo2() {
		return navPhoneNo2;
	}

	public void setNavPhoneNo2(String navPhoneNo2) {
		this.navPhoneNo2 = navPhoneNo2;
	}

	public String getNavEmail() {
		return navEmail;
	}

	public void setNavEmail(String navEmail) {
		this.navEmail = navEmail;
	}

	public String getNavAddress() {
		return navAddress;
	}

	public void setNavAddress(String navAddress) {
		this.navAddress = navAddress;
	}

	public String getNavAddress2() {
		return navAddress2;
	}

	public void setNavAddress2(String navAddress2) {
		this.navAddress2 = navAddress2;
	}

	public String getNavAddress3() {
		return navAddress3;
	}

	public void setNavAddress3(String navAddress3) {
		this.navAddress3 = navAddress3;
	}

	public String getNavKladrRegion() {
		return navKladrRegion;
	}

	public void setNavKladrRegion(String navKladrRegion) {
		this.navKladrRegion = navKladrRegion;
	}

	public String getNavKladrCity() {
		return navKladrCity;
	}

	public void setNavKladrCity(String navKladrCity) {
		this.navKladrCity = navKladrCity;
	}

	public String getNavKladrDistrict() {
		return navKladrDistrict;
	}

	public void setNavKladrDistrict(String navKladrDistrict) {
		this.navKladrDistrict = navKladrDistrict;
	}

	public String getNavKladrStreet() {
		return navKladrStreet;
	}

	public void setNavKladrStreet(String navKladrStreet) {
		this.navKladrStreet = navKladrStreet;
	}

	public String getNavKladrLocality() {
		return navKladrLocality;
	}

	public void setNavKladrLocality(String navKladrLocality) {
		this.navKladrLocality = navKladrLocality;
	}

	public String getNavKladrHouse() {
		return navKladrHouse;
	}

	public void setNavKladrHouse(String navKladrHouse) {
		this.navKladrHouse = navKladrHouse;
	}

	public String getNavKladrBuilding() {
		return navKladrBuilding;
	}

	public void setNavKladrBuilding(String navKladrBuilding) {
		this.navKladrBuilding = navKladrBuilding;
	}

	public String getNavKladrFlat() {
		return navKladrFlat;
	}

	public void setNavKladrFlat(String navKladrFlat) {
		this.navKladrFlat = navKladrFlat;
	}

	public String getNavKladrVladenie() {
		return navKladrVladenie;
	}

	public void setNavKladrVladenie(String navKladrVladenie) {
		this.navKladrVladenie = navKladrVladenie;
	}

	public String getNavKladrCorpus() {
		return navKladrCorpus;
	}

	public void setNavKladrCorpus(String navKladrCorpus) {
		this.navKladrCorpus = navKladrCorpus;
	}

	public String getNavKladrOffice() {
		return navKladrOffice;
	}

	public void setNavKladrOffice(String navKladrOffice) {
		this.navKladrOffice = navKladrOffice;
	}

	public String getNavKladrAdditional() {
		return navKladrAdditional;
	}

	public void setNavKladrAdditional(String navKladrAdditional) {
		this.navKladrAdditional = navKladrAdditional;
	}

	public String getNavDeliveryTimeFrom() {
		return navDeliveryTimeFrom;
	}

	public void setNavDeliveryTimeFrom(String navDeliveryTimeFrom) {
		this.navDeliveryTimeFrom = navDeliveryTimeFrom;
	}

	public String getNavDeliveryTimeTo() {
		return navDeliveryTimeTo;
	}

	public void setNavDeliveryTimeTo(String navDeliveryTimeTo) {
		this.navDeliveryTimeTo = navDeliveryTimeTo;
	}

	public String getNavItemNo() {
		return navItemNo;
	}

	public void setNavItemNo(String navItemNo) {
		this.navItemNo = navItemNo;
	}

	public String getNavQuantity() {
		return navQuantity;
	}

	public void setNavQuantity(String navQuantity) {
		this.navQuantity = navQuantity;
	}

	public String getNavVariantCode() {
		return navVariantCode;
	}

	public void setNavVariantCode(String navVariantCode) {
		this.navVariantCode = navVariantCode;
	}

	public String getNavShipmentDate() {
		return navShipmentDate;
	}

	public void setNavShipmentDate(String navShipmentDate) {
		this.navShipmentDate = navShipmentDate;
	}

	public String getNavPreliminaryOrder() {
		return navPreliminaryOrder;
	}

	public void setNavPreliminaryOrder(String navPreliminaryOrder) {
		this.navPreliminaryOrder = navPreliminaryOrder;
	}

	public String getNavSorderSource() {
		return navSorderSource;
	}

	public void setNavSorderSource(String navSorderSource) {
		this.navSorderSource = navSorderSource;
	}

	public String getNavSorderDate() {
		return navSorderDate;
	}

	public void setNavSorderDate(String navSorderDate) {
		this.navSorderDate = navSorderDate;
	}

	public String getNavComment1() {
		return navComment1;
	}

	public void setNavComment1(String navComment1) {
		this.navComment1 = navComment1;
	}

	public String getNavComment2() {
		return navComment2;
	}

	public void setNavComment2(String navComment2) {
		this.navComment2 = navComment2;
	}

	public String getNavComment3() {
		return navComment3;
	}

	public void setNavComment3(String navComment3) {
		this.navComment3 = navComment3;
	}

	public String getNavComment4() {
		return navComment4;
	}

	public void setNavComment4(String navComment4) {
		this.navComment4 = navComment4;
	}

	public String getNavShipmentMethodCode() {
		return navShipmentMethodCode;
	}

	public void setNavShipmentMethodCode(String navShipmentMethodCode) {
		this.navShipmentMethodCode = navShipmentMethodCode;
	}

	public String getNavWineShopCustomerNo() {
		return navWineShopCustomerNo;
	}

	public void setNavWineShopCustomerNo(String navWineShopCustomerNo) {
		this.navWineShopCustomerNo = navWineShopCustomerNo;
	}

	public String getNavSimpleWaters() {
		return navSimpleWaters;
	}

	public void setNavSimpleWaters(String navSimpleWaters) {
		this.navSimpleWaters = navSimpleWaters;
	}

	public String getNavState() {
		return navState;
	}

	public void setNavState(String navState) {
		this.navState = navState;
	}

	public String getNavError() {
		return navError;
	}

	public void setNavError(String navError) {
		this.navError = navError;
	}

	public String getNavReplyDateTime() {
		return navReplyDateTime;
	}

	public void setNavReplyDateTime(String navReplyDateTime) {
		this.navReplyDateTime = navReplyDateTime;
	}

	public String getBtxUserID() {
		return btxUserID;
	}

	public void setNavUserID(String btxUserID) {
		this.btxUserID = btxUserID;
	}

	public String getNavLineDiscountPercent() {
		return navLineDiscountPercent;
	}

	public void setNavLineDiscountPercent(String navLineDiscountPercent) {
		this.navLineDiscountPercent = navLineDiscountPercent;
	}

	public String getNavLineAmount() {
		return navLineAmount;
	}

	public void setNavLineAmount(String navLineAmount) {
		this.navLineAmount = navLineAmount;
	}

	public String getNavPaymentTerms() {
		return navPaymentTerms;
	}

	public void setNavPaymentTerms(String navPaymentTerms) {
		this.navPaymentTerms = navPaymentTerms;
	}

	public void setBtxUserID(String btxUserID) {
		this.btxUserID = btxUserID;
	}

	public String getJuuid() {
		return juuid;
	}

	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}

	public String getNavErrorId() {
		return navErrorId;
	}

	public void setNavErrorId(String navErrorId) {
		this.navErrorId = navErrorId;
	}

	public String getNavFullCustomerName() {
		return navFullCustomerName;
	}

	public void setNavFullCustomerName(String navFullCustomerName) {
		this.navFullCustomerName = navFullCustomerName;
	}

	public String getNavCompanyName() {
		return navCompanyName;
	}

	public void setNavCompanyName(String navCompanyName) {
		this.navCompanyName = navCompanyName;
	}

	public String getNavDeliveryComment1() {
		return navDeliveryComment1;
	}

	public void setNavDeliveryComment1(String navDeliveryComment1) {
		this.navDeliveryComment1 = navDeliveryComment1;
	}

	public String getNavDeliveryComment2() {
		return navDeliveryComment2;
	}

	public void setNavDeliveryComment2(String navDeliveryComment2) {
		this.navDeliveryComment2 = navDeliveryComment2;
	}
	
	public String getResponseContractClass() {
		return responseContractClass;
	}

	public void setResponseContractClass(String responseContractClass) {
		this.responseContractClass = responseContractClass;
	}

	public ContentType getResponseContentType() {
		return responseContentType;
	}

	@Override
	public void setResponseContentType(ContentType responseContentType) {
		this.responseContentType = responseContentType;
	}

	public void splitCustomerName(String fullName) {
		if (fullName == null || fullName == "")
			return;

		setNavCustomerName(fullName.substring(0, fullName.length() > 30 ? 30 : fullName.length()));

		if (fullName.length() > 30)
			setNavCustomerName2(fullName.substring(30, fullName.length() > 60 ? 60 : fullName.length()));

		if (fullName.length() > 60)
			setNavCustomerName3(fullName.substring(60, fullName.length() > 90 ? 90 : fullName.length()));
	}

	public void splitAddress(String address) {
		if (address == null || address == "")
			return;

		setNavAddress(address.substring(0, address.length() > 30 ? 30 : address.length()));

		if (address.length() > 30)
			setNavAddress2(address.substring(30, address.length() > 60 ? 60 : address.length()));

		if (address.length() > 60)
			setNavAddress3(address.substring(60, address.length() > 160 ? 160 : address.length()));
	}

	public void splitComment(String comment) {

		if (comment == null || comment == "")
			return;

		setNavComment1(comment.substring(0, comment.length() > 80 ? 80 : comment.length()));

		if (comment.length() > 80)
			setNavComment2(comment.substring(80, comment.length() > 160 ? 160 : comment.length()));

		if (comment.length() > 160)
			setNavAddress3(comment.substring(160, comment.length() > 240 ? 240 : comment.length()));
	}
	
	
	public void splitDeliveryComment(String comment) {

		if (comment == null || comment == "")
			return;

		setNavDeliveryComment1(comment.substring(0, comment.length() > 250 ? 250 : comment.length()));

		if (comment.length() > 250)
			setNavDeliveryComment2(comment.substring(250, comment.length() > 500 ? 500 : comment.length()));
	}

	public void commonFormat(ANavSorderQueue rec) throws Exception{

		rec.splitCustomerName(rec.getNavFullCustomerName());
		rec.splitAddress(rec.getNavAddress());
		rec.splitComment(rec.getNavComment1());
		rec.splitDeliveryComment(rec.getNavDeliveryComment1());		
		
		if(rec.getNavDeliveryTimeFrom() != null && rec.getNavDeliveryTimeFrom() != "")			
			rec.setNavDeliveryTimeFrom(DateConvertHelper.timeToNavFormat(rec.getNavDeliveryTimeFrom()));	
						
		if(rec.getNavDeliveryTimeTo() != null && rec.getNavDeliveryTimeTo() != "")
			rec.setNavDeliveryTimeTo(DateConvertHelper.timeToNavFormat(rec.getNavDeliveryTimeTo()));
		
		if(rec.getBtxTime() != null && rec.getBtxTime() != "")
			rec.setBtxTime(DateConvertHelper.timeToNavFormat(rec.getBtxTime()));
		
		if(rec.getNavReplyDateTime() != null && rec.getNavReplyDateTime() != "")
			rec.setNavReplyDateTime(DateConvertHelper.dateToNavFormat(rec.getNavReplyDateTime()));		
		
		if (rec.getNavShipmentDate() != null && rec.getNavShipmentDate() != "")
			rec.setNavShipmentDate(DateConvertHelper.dateToNavFormat(rec.getNavShipmentDate()));		
		
		if(rec.getBtxDate() != null && rec.getBtxDate() != "")
			rec.setBtxDate(DateConvertHelper.dateToNavFormat(rec.getBtxDate()));
		
		if(rec.getNavShipmentDate() != null && rec.getNavShipmentDate() != "")
			rec.setNavShipmentDate(DateConvertHelper.dateToNavFormat(rec.getNavShipmentDate()));
		
		if(rec.getNavSorderDate() != null && rec.getNavSorderDate() != "")
			rec.setNavSorderDate(DateConvertHelper.dateToNavFormat(rec.getNavSorderDate()));			
	}
		
}
