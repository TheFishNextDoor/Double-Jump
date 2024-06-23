package com.thefishnextdoor.jump.toolkit;

public class EnumTools {

    public static <E extends Enum<E>> E fromString(Class<E> enumClass, String name) {
        if (enumClass == null) {
            throw new IllegalArgumentException("Enum class cannot be null");
        }
        
        if (name == null) {
            return null;
        }

        name = name.trim().replace(" ", "_").replace("-", "_");

        for (E constant : enumClass.getEnumConstants()) {
            if (constant.name().equalsIgnoreCase(name)) {
                return constant;
            }
        }

        return null;
    }
}