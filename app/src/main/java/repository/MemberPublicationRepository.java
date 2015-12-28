package repository;

import java.io.File;
import java.util.ArrayList;

import model.MemberPublication;
import model.Photo;

public interface MemberPublicationRepository {

	MemberPublication addMemberPublication(String IDMember, String TextFormat, String LinkVideo, String WebLink, String visibility) throws Exception;
	boolean  PublicationaddPhoto(File Source)throws Exception;
	ArrayList<MemberPublication> GetFeeds(String IDMember, int perpage, int page)throws Exception;
	ArrayList<MemberPublication> GetPublicationsByIDMember(String IDMember, int perpage, int page) throws  Exception;
	MemberPublication GetOnePublicationByIDMember(String IDMember, String IDPublication)throws Exception;
	ArrayList<Photo> DeletePhotoPublication(String IDMember, String IDPublication, String IDPhoto) throws Exception;
	boolean DeleteMemberPublication(String IDMember, String IDPublication) throws Exception;
}
