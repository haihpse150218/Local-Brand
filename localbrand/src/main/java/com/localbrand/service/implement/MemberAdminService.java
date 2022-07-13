package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.BrandAccount;
import com.localbrand.service.IMemberAdminService;
import com.localbrand.sessionbeans.BrandAccountFacade;

public class MemberAdminService implements IMemberAdminService {

	private MemberAdminService() {
	}

	private static MemberAdminService instance = new MemberAdminService();

	public static MemberAdminService getInstance() {
		if (instance == null) {
			instance = new MemberAdminService();
		}
		return instance;
	}

	public List<BrandAccount> getAllMemberByBrandId(int brandId) {
		BrandAccountFacade bf = new BrandAccountFacade();
		List<BrandAccount> result = new ArrayList<BrandAccount>();
		try {
			for (BrandAccount a : bf.findAll()) {
				if (a.getBrandId().getId() == brandId) {
					if(a.getStatus() == 0 || a.getStatus() == 1) {
						result.add(a);
						System.out.println(a.toString());
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public int convertStatusBrandAccount(String status) {
		int result = 1;
		switch (status) {
		case "Active":
			result = 1;
			break;
		case "Disable":
			result = 0;
			break;
		case "Remove":
			result = 2;
			break;
		default:
			break;
		}
		return result;
		
	}
	public String convertStatusBrandAccount(int status) {
		String result = null;
		switch (status) {
		case 1:
			result = "Active";
			
			break;
		case 0:
			result = "Disable";
			break;
		default:
			break;
		}
		return result;
		
	}

}
