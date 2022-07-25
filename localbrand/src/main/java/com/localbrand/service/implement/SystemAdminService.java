package com.localbrand.service.implement;

import java.nio.charset.StandardCharsets;
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
		List<BrandAccount> listAllAccount = new ArrayList<>();
		try {
			listAllAccount = brandAccountFacade.findAll();
//			for (BrandAccount ba : listAllAccount) {
//				String convert=ba.getPassword();
//				ba.setPassword(hexToString(convert));
//				listbacc.add(ba);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAllAccount;
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
	public static byte[] hexStringToByteArray(String hex) {
	    int l = hex.length();
	    byte[] data = new byte[l / 2];
	    for (int i = 0; i < l; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
	                + Character.digit(hex.charAt(i + 1), 16));
	    }
	    return data;
	}
	public String hexToString(String hexString) {
		String OutputString = new String();
        char[] Temp_Char = hexString.toCharArray();
        for(int x = 0; x < Temp_Char.length; x=x+2) {
            String Temp_String = ""+Temp_Char[x]+""+Temp_Char[x+1];
            char character = (char)Integer.parseInt(Temp_String, 16);
            OutputString = OutputString + character;
        }
        return OutputString;
	}
	public BrandAccount checkBrandAccount(String username) {
		BrandAccount result = null;
		BrandAccountFacade baf = new BrandAccountFacade();
		try {
			for (BrandAccount ba : baf.findAll()) {
				if(ba.getUsername().equalsIgnoreCase(username.trim())){					
						result = ba;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
