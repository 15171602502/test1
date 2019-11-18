package com.ysk.source.entity;

/**
 * 购物车--实体类
 * 
 * @author admin
 *
 */
public class ShoppingCart {

	private String shoppingCartId;

	private String userId;

	private String drugId;

	private Integer quantity;

	private String addTime;

	private DrugInformation drugInfo;

	private String backupData;

	private String backupText;

	public String getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public DrugInformation getDrugInfo() {
		return drugInfo;
	}

	public void setDrugInfo(DrugInformation drugInfo) {
		this.drugInfo = drugInfo;
	}

	public String getBackupData() {
		return backupData;
	}

	public void setBackupData(String backupData) {
		this.backupData = backupData;
	}

	public String getBackupText() {
		return backupText;
	}

	public void setBackupText(String backupText) {
		this.backupText = backupText;
	}
}