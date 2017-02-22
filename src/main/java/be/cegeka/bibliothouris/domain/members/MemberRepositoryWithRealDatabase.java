package be.cegeka.bibliothouris.domain.members;

import org.springframework.data.repository.CrudRepository;

import javax.inject.Named;

/**
 * Created by thomasva on 22/02/2017.
 */
@Named
//Spring boot will initialize a working repository with all the methods of the crudRepo interface (ctrl-click)
public interface MemberRepositoryWithRealDatabase extends CrudRepository<Member,String> {
}
