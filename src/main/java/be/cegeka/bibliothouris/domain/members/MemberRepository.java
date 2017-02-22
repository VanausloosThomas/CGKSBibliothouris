package be.cegeka.bibliothouris.domain.members;

import be.cegeka.bibliothouris.domain.exceptions.INSSAlreadyExistsException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class MemberRepository {

    private List<Member> members = new ArrayList<>();

    public List<Member> getAllMembers() {
        return members;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Member getMember(String inss) throws INSSAlreadyExistsException {
        for (Member member : members) {
            if (member.getINSS().equals(inss)){
                return member;
            }

        }
        throw new INSSAlreadyExistsException("Person with inss: " + inss+ " does not exist");
    }
}
