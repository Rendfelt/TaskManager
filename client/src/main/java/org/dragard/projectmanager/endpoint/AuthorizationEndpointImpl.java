package org.dragard.projectmanager.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-01-31T04:14:36.398+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.projectmanager.dragard.org/", name = "AuthorizationEndpointImpl")
@XmlSeeAlso({ObjectFactory.class})
public interface AuthorizationEndpointImpl {

    @WebMethod
    @Action(input = "http://endpoint.projectmanager.dragard.org/AuthorizationEndpointImpl/registerUserRequest", output = "http://endpoint.projectmanager.dragard.org/AuthorizationEndpointImpl/registerUserResponse")
    @RequestWrapper(localName = "registerUser", targetNamespace = "http://endpoint.projectmanager.dragard.org/", className = "org.dragard.projectmanager.endpoint.RegisterUser")
    @ResponseWrapper(localName = "registerUserResponse", targetNamespace = "http://endpoint.projectmanager.dragard.org/", className = "org.dragard.projectmanager.endpoint.RegisterUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public org.dragard.projectmanager.endpoint.Response registerUser(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    );

    @WebMethod
    @Action(input = "http://endpoint.projectmanager.dragard.org/AuthorizationEndpointImpl/logoutRequest", output = "http://endpoint.projectmanager.dragard.org/AuthorizationEndpointImpl/logoutResponse")
    @RequestWrapper(localName = "logout", targetNamespace = "http://endpoint.projectmanager.dragard.org/", className = "org.dragard.projectmanager.endpoint.Logout")
    @ResponseWrapper(localName = "logoutResponse", targetNamespace = "http://endpoint.projectmanager.dragard.org/", className = "org.dragard.projectmanager.endpoint.LogoutResponse")
    @WebResult(name = "return", targetNamespace = "")
    public org.dragard.projectmanager.endpoint.Response logout(
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token
    );

    @WebMethod
    @Action(input = "http://endpoint.projectmanager.dragard.org/AuthorizationEndpointImpl/loginRequest", output = "http://endpoint.projectmanager.dragard.org/AuthorizationEndpointImpl/loginResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.projectmanager.dragard.org/AuthorizationEndpointImpl/login/Fault/Exception")})
    @RequestWrapper(localName = "login", targetNamespace = "http://endpoint.projectmanager.dragard.org/", className = "org.dragard.projectmanager.endpoint.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://endpoint.projectmanager.dragard.org/", className = "org.dragard.projectmanager.endpoint.LoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public org.dragard.projectmanager.endpoint.Response login(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.projectmanager.dragard.org/AuthorizationEndpointImpl/changePasswordRequest", output = "http://endpoint.projectmanager.dragard.org/AuthorizationEndpointImpl/changePasswordResponse")
    @RequestWrapper(localName = "changePassword", targetNamespace = "http://endpoint.projectmanager.dragard.org/", className = "org.dragard.projectmanager.endpoint.ChangePassword")
    @ResponseWrapper(localName = "changePasswordResponse", targetNamespace = "http://endpoint.projectmanager.dragard.org/", className = "org.dragard.projectmanager.endpoint.ChangePasswordResponse")
    @WebResult(name = "return", targetNamespace = "")
    public org.dragard.projectmanager.endpoint.Response changePassword(
        @WebParam(name = "oldPassword", targetNamespace = "")
        java.lang.String oldPassword,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token
    );
}
