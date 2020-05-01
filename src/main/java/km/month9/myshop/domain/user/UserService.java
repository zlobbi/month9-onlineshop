package km.month9.myshop.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private UserRepository userRepository;

    public void saveUser(UserRegisterForm form) {
        User u = new User();
        u.setLogin(form.getLogin());
        u.setEmail(form.getEmail());
        u.setPassword(form.getPassword());
        userRepository.save(u);
    }

    public boolean checkUser(UserRegisterForm form) {
       return userRepository.existsByLoginAndEmail(form.getLogin(), form.getEmail());
    }
}
