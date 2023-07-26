package security.jwt.tutorial.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import security.jwt.tutorial.entity.Authority;
import security.jwt.tutorial.entity.User;

import javax.persistence.EntityManager;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Transactional
public class InitService {

    private final EntityManager entityManager;

    public void init(){
        Authority admin = new Authority();
        admin.setAuthorityName("ROLE_ADMIN");

        entityManager.persist(admin);

        Authority user = new Authority();
        user.setAuthorityName("ROLE_USER");

        entityManager.persist(user);

        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi");
        user1.setNickname("admin");
        user1.setActivated(true);

        HashSet<Authority> adminAuthorities = new HashSet<>();
        adminAuthorities.add(admin);
        user1.setAuthorities(adminAuthorities);

        entityManager.persist(user1);

        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword("$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC");
        user2.setNickname("user");
        user2.setActivated(true);

        HashSet<Authority> userAuthorities = new HashSet<>();
        userAuthorities.add(user);
        user2.setAuthorities(userAuthorities);

        entityManager.persist(user2);
    }
}
