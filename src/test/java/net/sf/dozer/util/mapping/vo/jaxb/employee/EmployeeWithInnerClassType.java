//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b18-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.10.05 at 10:03:40 CEST 
//

package net.sf.dozer.util.mapping.vo.jaxb.employee;

/**
 * Java content class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this java content object. (defined at
 * file:/C:/Tools/dozer-2.3-src/dozer/dozer-src/etc/Employee.xsd line 13)
 * <p>
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;FirstName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;/&gt;
 *         &lt;element name=&quot;BirthDate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}date&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;LastName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;/&gt;
 *         &lt;element name=&quot;Address&quot;&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name=&quot;Street&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 */
public interface EmployeeWithInnerClassType {

  /**
   * Gets the value of the birthDate property.
   * 
   * @return possible object is {@link java.util.Calendar}
   */
  java.util.Calendar getBirthDate();

  /**
   * Sets the value of the birthDate property.
   * 
   * @param value
   *          allowed object is {@link java.util.Calendar}
   */
  void setBirthDate(java.util.Calendar value);

  /**
   * Gets the value of the firstName property.
   * 
   * @return possible object is {@link java.lang.String}
   */
  java.lang.String getFirstName();

  /**
   * Sets the value of the firstName property.
   * 
   * @param value
   *          allowed object is {@link java.lang.String}
   */
  void setFirstName(java.lang.String value);

  /**
   * Gets the value of the address property.
   * 
   * @return possible object is
   *         {@link net.sf.dozer.util.mapping.vo.jaxb.employee.EmployeeWithInnerClassType.AddressType}
   */
  net.sf.dozer.util.mapping.vo.jaxb.employee.EmployeeWithInnerClassType.AddressType getAddress();

  /**
   * Sets the value of the address property.
   * 
   * @param value
   *          allowed object is
   *          {@link net.sf.dozer.util.mapping.vo.jaxb.employee.EmployeeWithInnerClassType.AddressType}
   */
  void setAddress(net.sf.dozer.util.mapping.vo.jaxb.employee.EmployeeWithInnerClassType.AddressType value);

  /**
   * Gets the value of the lastName property.
   * 
   * @return possible object is {@link java.lang.String}
   */
  java.lang.String getLastName();

  /**
   * Sets the value of the lastName property.
   * 
   * @param value
   *          allowed object is {@link java.lang.String}
   */
  void setLastName(java.lang.String value);

  /**
   * Java content class for anonymous complex type.
   * <p>
   * The following schema fragment specifies the expected content contained within this java content object. (defined at
   * file:/C:/Tools/dozer-2.3-src/dozer/dozer-src/etc/Employee.xsd line 20)
   * <p>
   * 
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
   *       &lt;sequence&gt;
   *         &lt;element name=&quot;Street&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   * 
   */
  public interface AddressType {

    /**
     * Gets the value of the street property.
     * 
     * @return possible object is {@link java.lang.String}
     */
    java.lang.String getStreet();

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *          allowed object is {@link java.lang.String}
     */
    void setStreet(java.lang.String value);

  }

}