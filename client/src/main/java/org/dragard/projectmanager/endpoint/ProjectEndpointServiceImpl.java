
package org.dragard.projectmanager.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for projectEndpointServiceImpl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="projectEndpointServiceImpl"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="serviceLocator" type="{http://endpoint.projectmanager.dragard.org/}bootstrap" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "projectEndpointServiceImpl", propOrder = {
    "serviceLocator"
})
public class ProjectEndpointServiceImpl {

    protected Bootstrap serviceLocator;

    /**
     * Gets the value of the serviceLocator property.
     * 
     * @return
     *     possible object is
     *     {@link Bootstrap }
     *     
     */
    public Bootstrap getServiceLocator() {
        return serviceLocator;
    }

    /**
     * Sets the value of the serviceLocator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bootstrap }
     *     
     */
    public void setServiceLocator(Bootstrap value) {
        this.serviceLocator = value;
    }

}
