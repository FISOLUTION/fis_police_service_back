package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.service.LoginService;
import fis.police.fis_police_server.service.exceptions.LoginServiceException;

public class LoginServiceImpl implements LoginService {
    @Override
    public Object login() throws LoginServiceException{
        try{
            throw new LoginServiceException("존재하지 않는 아이디입니다");
        } catch (LoginServiceException exception){
            return 404;
        } catch (NullPointerException exception){
            return 404;
        }
        finally {
         // try랑 catch 공통문
        }
    }

    @Override
    public Object logout() {
        return null;
    }
}
