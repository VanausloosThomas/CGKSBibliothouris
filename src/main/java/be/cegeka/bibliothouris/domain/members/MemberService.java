package be.cegeka.bibliothouris.domain.members;

import be.cegeka.bibliothouris.domain.exceptions.INSSAlreadyExistsException;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class
MemberService {

    /*@Inject
    private MemberRepository memberRepository;*/
    @Inject
    private MemberRepositoryWithRealDatabase memberRepositoryWithRealDatabase;
    @Inject
    private MemberValidator memberValidator;

    public void addMember(String INSS,String memberLastName,String memberFirstName, String streetName, int streetNumber, String postalCode, String city) throws INSSAlreadyExistsException {
        Member member=new Member(INSS,memberLastName,memberFirstName,streetName,streetNumber,postalCode,city);
        //MemberValidator validator = new MemberValidator();
        if (memberValidator.isValid(member.getINSS(),memberRepositoryWithRealDatabase)) {
            memberRepositoryWithRealDatabase.save(member);
        } else {
            throw new INSSAlreadyExistsException("INSS already exists for (another) user, new user is not added.");
        }
    }

    public Iterable<Member> getAllMembers() {
        return memberRepositoryWithRealDatabase.findAll();
    }

    public Member getMember(String INSS) throws INSSAlreadyExistsException {
        return memberRepositoryWithRealDatabase.findOne(INSS);
    }
}

