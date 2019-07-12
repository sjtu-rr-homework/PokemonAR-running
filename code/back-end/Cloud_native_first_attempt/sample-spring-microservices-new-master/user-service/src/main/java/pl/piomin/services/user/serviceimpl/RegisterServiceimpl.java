package pl.piomin.services.user.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piomin.services.user.dao.UserDao;
import pl.piomin.services.user.entity.User;
import pl.piomin.services.user.service.RegisterService;

@Service
public class RegisterServiceimpl implements RegisterService {
    @Autowired
    private UserDao UserDao;

    @Override
    public boolean Register(String username, String password, String email, int star) {

        User tempUser=UserDao.findOne(username);
        if(tempUser==null) {
            UserDao.save(username, password, email, star);
            return true;
        }
        else
        {
            return false;
        }

    }
}
