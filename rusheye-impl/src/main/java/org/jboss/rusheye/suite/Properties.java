package org.jboss.rusheye.suite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.jboss.rusheye.parser.ElementAdapter;
import org.w3c.dom.Element;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "Properties", propOrder = { "any" })
@XmlSeeAlso({ TypeProperties.class, ImageSource.class })
public class Properties {

    protected List<Element> any;

    @XmlAnyElement(lax = false)
    public List<Element> getAny() {
        if (any == null) {
            any = new ArrayList<Element>();
        }
        return this.any;
    }

    /*
     * logic
     */
    public Object getProperty(final String key) {
        Collection<Element> elements = Collections2.filter(getAny(), new Predicate<Element>() {
            @Override
            public boolean apply(Element element) {
                return element.getLocalName().equals(key);
            }
        });
        if (elements.isEmpty()) {
            return null;
        }
        return elements.iterator().next().getTextContent();
    }

    public void setProperty(String key, Object value) {
        Element elementForRemoval = null;
        for (Element element : getAny()) {
            if (element.getLocalName().equals(key)) {
                elementForRemoval = element;
            }
        }
        if (elementForRemoval != null) {
            getAny().remove(elementForRemoval);
        }
        Element element = new ElementAdapter(key);
        element.setTextContent(value.toString());
        getAny().add(element);
    }

    public void include(Properties includeProperties) {
        for (Element element : includeProperties.getAny()) {
            setProperty(element.getLocalName(), element.getTextContent());
        }
    }

    public int size() {
        return getAny().size();
    }
}