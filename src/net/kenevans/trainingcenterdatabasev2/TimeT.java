//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.22 at 12:06:19 AM EST 
//


package net.kenevans.trainingcenterdatabasev2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Time_t complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Time_t">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2}Duration_t">
 *       &lt;sequence>
 *         &lt;element name="Seconds" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Time_t", propOrder = {
    "seconds"
})
public class TimeT
    extends DurationT
{

    @XmlElement(name = "Seconds")
    @XmlSchemaType(name = "unsignedShort")
    protected int seconds;

    /**
     * Gets the value of the seconds property.
     * 
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Sets the value of the seconds property.
     * 
     */
    public void setSeconds(int value) {
        this.seconds = value;
    }

}
