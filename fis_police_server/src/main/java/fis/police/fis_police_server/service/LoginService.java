package fis.police.fis_police_server.service;

import fis.police.fis_police_server.service.exceptions.LoginServiceException;

public interface LoginService {
    Object login() throws LoginServiceException;
    Object logout();
}
