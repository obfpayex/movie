package com.obf.movie.util;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PartialUpdateUtil {

    public static void copyNonNullProperties(Object source, Object destination) throws InvocationTargetException, IllegalAccessException {
        new CustomNullAwareBeanUtil().copyProperties(destination, source);
    }

    public static void copyNonNullProperties(Object source, Object destination, List<String> excludedProperties) throws InvocationTargetException, IllegalAccessException {
        new CustomNullAwareBeanUtil(excludedProperties).copyProperties(destination, source);
    }


    static class CustomNullAwareBeanUtil extends BeanUtilsBean {

        CustomNullAwareBeanUtil() {
        }

        CustomNullAwareBeanUtil(List<String> excludes) {
            this.excludes = excludes;
        }

        private List<String> excludes;

        @Override
        public void copyProperty(Object dest, String name, Object value) throws InvocationTargetException, IllegalAccessException {
            if (value == null) return;
            if (excludes != null && excludes.contains(name)) return;
            super.copyProperty(dest, name, value);
        }

    }

}
