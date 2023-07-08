package com.model;

import java.util.ArrayList;

public class AddressManager {
	private ArrayList<Address> Addresses;
	
	public AddressManager() {
		DAOAddress db = new DAOAddress();
		Addresses = db.getAllAddressesFromDB();
	}
	
	public AddressManager(int User) {
		DAOAddress db = new DAOAddress();
		Addresses = db.getAllUserAddrsFromDB(User);
	}
	
	public void addAddr(Address addr) {
		DAOAddress db = new DAOAddress();
		if(addr.getDefault()) {
			newDefault(addr);
		}else {
			db.addAddressToDB(addr);
		}
	}
	
	public void removeAddr(Address addr) {
		DAOAddress db = new DAOAddress();
		db.removeAddrFromDB(addr);
	}
	
	public void modifyAddr(Address oldAddr, Address newAddr) {
		removeAddr(oldAddr);
		addAddr(newAddr);
	}
	
	public void newDefault(Address addr) {
		DAOAddress db = new DAOAddress();
		Addresses = db.getAllAddressesFromDB();
		
		for(Address aDB : Addresses) {
			if(addr.getUser()==aDB.getUser()) {
				if(aDB.getDefault()) {
					removeDefault(aDB);
				}
			}
		}
		db.addAddressToDB(addr);
	}
	
	public void removeDefault(Address addr) {
		DAOAddress db = new DAOAddress();
		db.modifyAddrToDB(addr, "isDefault", false);
	}
	
	public void newBillingAddr(Address addr){
		DAOAddress db = new DAOAddress();
		db.modifyAddrToDB(addr, "isBillingAddress", true);
	}
	
	public void removeBillingAddr(Address addr) {
		DAOAddress db = new DAOAddress();
		db.modifyAddrToDB(addr, "isBillingAddress", false);
	}
	
	public ArrayList<Address> getAddresses(){
		return Addresses;
	}
}
