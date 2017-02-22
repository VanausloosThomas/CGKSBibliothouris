package be.cegeka.bibliothouris.application;

import be.cegeka.bibliothouris.domain.exceptions.INSSAlreadyExistsException;
import be.cegeka.bibliothouris.domain.members.Member;
import be.cegeka.bibliothouris.domain.members.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Inject
    private MemberService memberService;


    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<Member> getUsers() {
        return memberService.getAllMembers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestParam(value = "INSS", required = true) String INSS,
                                  @RequestParam(value = "Lastname", required = true) String memberLastName,
                                  @RequestParam(value = "Firstname", required = false) String memberFirstName,
                                  @RequestParam(value = "Street", required = false) String streetName,
                                  @RequestParam(value = "Number", required = false) String streetNumber,
                                  @RequestParam(value = "Postalcode", required = true) String postalCode,
                                  @RequestParam(value = "City", required = true) String city) {
        try {
            memberService.addMember(INSS, memberLastName, memberFirstName, streetName, Integer.valueOf(streetNumber), postalCode, city);
            return new ResponseEntity<>("Member added successfully", HttpStatus.ACCEPTED);
        } catch(INSSAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
