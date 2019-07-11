package pl.piomin.services.organization.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piomin.services.organization.dao.UserDao;
import pl.piomin.services.organization.entity.User;
import pl.piomin.services.organization.service.RegisterService;

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
