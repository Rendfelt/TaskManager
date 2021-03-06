package org.dragard.projectmanager.endpoint;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-02-04T15:14:28.105+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "ProjectEndpointImplService",
                  wsdlLocation = "http://localhost:9090/task-manager/project?wsdl",
                  targetNamespace = "http://endpoint.projectmanager.dragard.org/")
public class ProjectEndpointImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.projectmanager.dragard.org/", "ProjectEndpointImplService");
    public final static QName ProjectEndpointImplPort = new QName("http://endpoint.projectmanager.dragard.org/", "ProjectEndpointImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9090/task-manager/project?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ProjectEndpointImplService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:9090/task-manager/project?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ProjectEndpointImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ProjectEndpointImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ProjectEndpointImplService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ProjectEndpointImplService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ProjectEndpointImplService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ProjectEndpointImplService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns ProjectEndpointImpl
     */
    @WebEndpoint(name = "ProjectEndpointImplPort")
    public ProjectEndpointImpl getProjectEndpointImplPort() {
        return super.getPort(ProjectEndpointImplPort, ProjectEndpointImpl.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ProjectEndpointImpl
     */
    @WebEndpoint(name = "ProjectEndpointImplPort")
    public ProjectEndpointImpl getProjectEndpointImplPort(WebServiceFeature... features) {
        return super.getPort(ProjectEndpointImplPort, ProjectEndpointImpl.class, features);
    }

}
