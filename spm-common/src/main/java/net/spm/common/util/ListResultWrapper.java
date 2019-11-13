package net.spm.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;

/**
 * @author Vincent 
 */
public class ListResultWrapper<T> {
	private List<T> items;
	 
    public ListResultWrapper() {
        items = new ArrayList<T>();
    }
 
    public ListResultWrapper(List<T> items) {
        this.items = items;
    }
 
    @XmlAnyElement(lax=true)
    public List<T> getItems() {
        return items;
    }
    
}