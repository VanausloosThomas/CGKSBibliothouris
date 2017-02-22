package be.cegeka.bibliothouris.domain.members;

import javax.inject.Named;

/**
 * Created by marijnvdh on 25/01/2017.
 */
@Named
public class MemberValidator {

    public boolean isValid(String INSS, MemberRepositoryWithRealDatabase memberRepository) {
        boolean checkValue = true;
        for (Member currentMember : memberRepository.getAllMembers()) {
            if (currentMember.getINSS().equals(INSS)) {
                checkValue = false;
            }
        }
        return checkValue;
    }
}

