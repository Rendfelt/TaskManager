
package org.dragard.projectmanager.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setProjectEndpoint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setProjectEndpoint"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://endpoint.projectmanager.dragard.org/}projectEndpointServiceImpl" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setProjectEndpoint", propOrder = {
    "arg0"
})
public class SetProjectEndpoint {

    protected ProjectEndpointServiceImpl arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link ProjectEndpointServiceImpl }
     *     
     */
    public ProjectEndpointServiceImpl getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProjectEndpointServiceImpl }
     *     
     */
    public void setArg0(ProjectEndpointServiceImpl value) {
        this.arg0 = value;
    }

}
