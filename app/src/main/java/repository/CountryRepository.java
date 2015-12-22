package repository;

import java.util.ArrayList;

import model.Country;


public interface CountryRepository {
 
	
	Country getCountryById(String id) throws Exception;
	ArrayList<Country> getAll() throws Exception;
}
