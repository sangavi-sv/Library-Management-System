package controller;

import dao.MemberDAO;
import model.Member;
import java.util.List;

public class MemberController {
    private MemberDAO dao = new MemberDAO();

    public void addMember(Member member) { dao.addMember(member); }
    public List<Member> getAllMembers() { return dao.getAllMembers(); }
    public void deleteMember(int id) { dao.deleteMember(id); }
    public void updateMember(Member member) { dao.updateMember(member); }  
}
