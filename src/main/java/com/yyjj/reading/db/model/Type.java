package com.yyjj.reading.db.model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yml
 * @since 2020-03-11
 */
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    private Integer id;

    /**
     * 类型名称
     */
    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Type{" +
            "id=" + id +
            ", typeName=" + typeName +
        "}";
    }
}
