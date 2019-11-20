package attendance.system.central.service;

import attendance.system.central.models.entities.AuthorizationEntity;
import attendance.system.central.repositories.postgres.AuthorizationEntityRepository;
import attendance.system.central.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class AuthorizationEntityService implements UserDetailsService {

    private final AuthorizationEntityRepository authorizationEntityRepository;

    @Autowired
    public AuthorizationEntityService(AuthorizationEntityRepository authorizationEntityRepository) {
        this.authorizationEntityRepository = authorizationEntityRepository;
    }

    @Transactional
    public Boolean entityExists(String id) {
        return authorizationEntityRepository.existsById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        AuthorizationEntity authorizationEntity = authorizationEntityRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found"));
        return UserPrincipal.create(authorizationEntity);
    }
}
