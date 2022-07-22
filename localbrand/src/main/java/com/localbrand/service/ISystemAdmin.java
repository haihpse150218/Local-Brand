package com.localbrand.service;

import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.BrandAccount;

public interface ISystemAdmin {
	public void createNewBrand(Brand b);
	public void createBrandAdmin(BrandAccount bAcc);
	public void updateBrandStatus (int bid, String status);
	public void updateBrandAdminStatus (int bAccId, int status);
	public void resetBrandAdminPassword (int bAccId, String nPassword);
	public List<BrandAccount> brandAdminList();
	public List<Brand> brandList();
}
