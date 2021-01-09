package by.itacademy.security.service;

import by.itacademy.domain.User;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
@RequiredArgsConstructor
public class UserServiceProvider implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) {
        try {
            User searchUser = userRepository.findByLogin(login);
            if (searchUser != null) {
                String userLogin = searchUser.getLogin();
                String userPassword = searchUser.getPassword();
                List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(String.valueOf(searchUser.getRole()));
                org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(userLogin, userPassword, authorityList);
                log.info("User with login " + login + " exists.");
                return user;
            } else {
                log.error("Can't find an user with login " + login + ".");
                throw new SecurityException("Can't find an user with login " + login + ".");
            }
        } catch (RepositoryException e) {
            throw new SecurityException("Security exception while trying to find an user." + e.getMessage());
        }
    }
}
