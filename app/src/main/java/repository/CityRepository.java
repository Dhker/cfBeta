package repository;

import model.City;

public interface CityRepository {

	City GetCityByID(String IDCity) throws Exception;
}
