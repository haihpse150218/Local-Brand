package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.BrandAccount;
import com.localbrand.entities.SystemAccount;
import com.localbrand.service.ISystemAdmin;
import com.localbrand.sessionbeans.BrandAccountFacade;
import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.sessionbeans.SystemAccountFacade;

public class SystemAdminService implements ISystemAdmin {
	private static SystemAdminService instance = new SystemAdminService();
	private static BrandFacade brandFacade = new BrandFacade();
	private static BrandAccountFacade brandAccountFacade = new BrandAccountFacade();
	@Override
	public void createNewBrand(Brand b) {
		try {
			brandFacade.create(b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void createBrandAdmin(BrandAccount bAcc) {
		try {
			brandAccountFacade.create(bAcc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void updateBrandStatus (int bid, String status) {
		Brand b = null;
		try {
			b = brandFacade.find(bid);
			b.setStatus(status);
			brandFacade.edit(b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void updateBrandAdminStatus (int bAccId, int status) {
		BrandAccount bAcc = null;
		try {
			bAcc = brandAccountFacade.find(bAccId);
			bAcc.setStatus(status);
			brandAccountFacade.edit(bAcc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void resetBrandAdminPassword (int bAccId, String nPassword) {
		BrandAccount bAcc = null;
		try {
			bAcc = brandAccountFacade.find(bAccId);
			bAcc.setPassword(nPassword);
			brandAccountFacade.edit(bAcc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<BrandAccount> brandAdminList(){
		List<BrandAccount> listbacc = new ArrayList<>();
		try {
			listbacc = brandAccountFacade.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listbacc;
	}
	@Override
	public List<Brand> brandList(){
		List<Brand> listb = new ArrayList<>();
		try {
			listb = brandFacade.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listb;
	}
	public static SystemAdminService getInstance() {
		if(instance == null) {
			instance = new SystemAdminService();
		}
		return instance;
	}
	public SystemAccount CheckAccount(String username, String password) {
		SystemAccountFacade bf = new SystemAccountFacade();
		SystemAccount result = null;
		try {
			for (SystemAccount sa : bf.findAll()) {
				if(sa.getUsername().equalsIgnoreCase(username.trim())){
					if(password.equalsIgnoreCase(sa.getPassword())) {
						result = sa;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
