package be.cegeka.bibliothouris.domain.members;

import be.cegeka.bibliothouris.domain.exceptions.INSSAlreadyExistsException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemberServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberValidator memberValidator;

    @Test
    public void addMember_ShouldCallMemberRepository() throws Exception {
        when(memberValidator.isValid("1",memberRepository)).thenReturn(true);
        memberService.addMember("1","van den Heuvel","Marijn","Street",1,"2","Heers");
        verify(memberRepository).addMember(new Member("1","van den Heuvel","Marijn","Street",1,"2","Heers"));
    }

    @Test
    public void getAllMembers() throws Exception {
        Member user1 = new Member("1","van den Heuvel","Marijn","Street",1,"2","Heers");
        Member user2 = new Member("2","van den Heuvel","Marijn","Street",1,"2","Heers");
        Member user3 = new Member("3","van den Heuvel","Marijn","Street",1,"2","Heers");
        when(memberRepository.getAllMembers()).thenReturn(Arrays.asList(user1, user2, user3));
        assertThat(memberService.getAllMembers()).containsOnly(user1, user2, user3);
    }

    @Test
    public void addMember_ShouldthrowException() throws Exception {
        when(memberValidator.isValid("1",memberRepository)).thenReturn(false);
        expectedException.expectMessage("INSS already exists for (another) user, new user is not added.");
        expectedException.expect(INSSAlreadyExistsException.class);
        memberService.addMember("1","van den Heuvel","Marijn","Street",1,"2","Heers");
    }
}