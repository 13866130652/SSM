package cn.pojo;

import java.io.Serializable;
import java.util.Date;

//订单类Bill
public class Bill implements Serializable {
	private static final long serialVersionUID = 4478629879631156933L;
	private int id; // id
	private String billCode; // 账单编码
	private String productName; // 商品名称
	private String productDesc; // 商品描述
	private String productUnit; // 商品单位
	private double productCount; // 商品数量
	private double totalPrice; // 总金额
	private int isPayment; // 是否支付
	private int providerId; // 供应商ID
	private int createdBy; // 创建者
	private Date creationDate; // 创建时间
	private int modifyBy; // 更新者
	private Date modifyDate;// 更新时间
	private String providerName;// 供应商名称

	public Bill() {
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", billCode=" + billCode + ", productName=" + productName + ", productDesc="
				+ productDesc + ", productUnit=" + productUnit + ", productCount=" + productCount + ", totalPrice="
				+ totalPrice + ", isPayment=" + isPayment + ", providerId=" + providerId + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate
				+ ", providerName=" + providerName + "]";
	}

	public Bill(int id, String billCode, String productName, String productDesc, String productUnit,
			double productCount, double totalPrice, int isPayment, int providerId, int createdBy, Date creationDate,
			int modifyBy, Date modifyDate, String providerName) {
		this.id = id;
		this.billCode = billCode;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productUnit = productUnit;
		this.productCount = productCount;
		this.totalPrice = totalPrice;
		this.isPayment = isPayment;
		this.providerId = providerId;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.providerName = providerName;
	}

	public int getId() {
		return id;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public double getProductCount() {
		return productCount;
	}

	public void setProductCount(double productCount) {
		this.productCount = productCount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(int isPayment) {
		this.isPayment = isPayment;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
