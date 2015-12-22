package repository;


import java.util.ArrayList;

import model.Language;

public interface LanguageRepository {
   
	ArrayList<Language> getAllLanguages() throws Exception;
	ArrayList<Language> getMemberLanguages(String id) throws Exception;
	boolean deleteMemberLanguage(String memberId, String languageId) throws Exception;
	boolean  setDefaultLanguage(String memberId, String languageId) throws Exception;
	boolean addLanguageToMember(String memberId, String languageId, boolean isDefault) throws Exception;
	
}
