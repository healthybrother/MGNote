package com.mgnote.mgnote.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityUtil {

    private static String[] getNullPropertyNames(@NotNull Object source) {
        Class<?> clz = source.getClass();
        Field[] fields = clz.getDeclaredFields();
        List<String> propertyNames = new ArrayList<>();
        for(Field field : fields) {
            field.setAccessible(true);
            try {
                if(null == field.get(source))
                    propertyNames.add(field.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return propertyNames.toArray(new String[]{});
    }

    public static <T> @NotNull T copyProperties(@NotNull T src, @NotNull T target, boolean ignoreNull) {
        if (ignoreNull) {
            BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
        } else {
            BeanUtils.copyProperties(src, target);
        }
        return target;
    }


}
