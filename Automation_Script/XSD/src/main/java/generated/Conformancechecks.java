//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.03 at 02:46:18 PM IST 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;element name="BufferAnalysisChecks" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element ref="{}error" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bufferAnalysisChecksOrError"
})
@XmlRootElement(name = "conformancechecks")
public class Conformancechecks {

    @XmlElements({
        @XmlElement(name = "BufferAnalysisChecks", type = String.class),
        @XmlElement(name = "error", type = Error.class)
    })
    protected List<Object> bufferAnalysisChecksOrError;

    /**
     * Gets the value of the bufferAnalysisChecksOrError property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bufferAnalysisChecksOrError property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBufferAnalysisChecksOrError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * {@link Error }
     * 
     * 
     */
    public List<Object> getBufferAnalysisChecksOrError() {
        if (bufferAnalysisChecksOrError == null) {
            bufferAnalysisChecksOrError = new ArrayList<Object>();
        }
        return this.bufferAnalysisChecksOrError;
    }

}
