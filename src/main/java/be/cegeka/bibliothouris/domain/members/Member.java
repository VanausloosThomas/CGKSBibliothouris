package be.cegeka.bibliothouris.domain.members;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    private String INSS;

    private String memberLastName;
    private String memberFirstName;
    private Address address;

    public Member() {
    }

    public Member(String INSS, String memberLastName, String memberFirstName, String streetName, int streetNumber, String postalCode, String city) {
        this.INSS = INSS;
        this.memberLastName = memberLastName;
        this.memberFirstName = memberFirstName;
        this.address = new Address(streetName,streetNumber,postalCode,city);
    }

    public String getINSS() {
        return INSS;
    }

    public String getMemberLastName() {
        return memberLastName;
    }

    public String getMemberFirstName() {
        return memberFirstName;
    }

    public String getMemberCity() {
        return this.address.getCity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (!INSS.equals(member.INSS)) return false;
        if (!memberLastName.equals(member.memberLastName)) return false;
        if (!memberFirstName.equals(member.memberFirstName)) return false;
        return address.equals(member.address);
    }

    @Override
    public int hashCode() {
        int result = INSS.hashCode();
        result = 31 * result + memberLastName.hashCode();
        result = 31 * result + memberFirstName.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
