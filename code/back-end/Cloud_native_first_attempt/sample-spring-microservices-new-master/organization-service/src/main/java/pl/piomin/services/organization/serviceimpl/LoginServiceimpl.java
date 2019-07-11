package pl.piomin.services.organization.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piomin.services.organization.dao.UserDao;
import pl.piomin.services.organization.entity.User;
import pl.piomin.services.organization.service.LoginService;

@Service
public class LoginServiceimpl implements LoginService {
    @Autowired
    private UserDao UserDao;


    @Override
    public boolean Login(String username, String password) {

        User tempUser=UserDao.findOne(username);
        if(tempUser==null)
        {
           return false;
        }
        else
        {
            if(tempUser.getStar()==-1)
            {
                return false;
            }
            else
            {
                if (tempUser.getPassword().equals(password))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }


}
