package repository;


import java.io.File;
import java.util.ArrayList;

import model.Member;

public interface MemberRepository {
 
	
	Member getMemberById(String id) throws Exception;
	 boolean  createMember(Member member) throws Exception;
	 ArrayList<Member> getMemberFriendList(Member member) ;
	boolean updateMemberInformation(Member member) throws Exception;
	void updateMemberChessProfile(Member member) throws Exception;
	Member Connect(String Email, String password) throws Exception;
	Member connectWithFacebook(String fbId)throws Exception ;
	Member connectWithGoogle(String gId) ;
	boolean sendForgetPasswordEmail(String email) throws Exception ;
	void addPhotoToMember(String memberId , File photo) throws Exception ;
}
 