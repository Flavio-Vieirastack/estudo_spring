package com.example.alga_works_1.aula1.core.helper;

import org.springframework.stereotype.Component;

@Component
public class IfNull {
    public static void execute(Object object, ExecuteOperation executeOnNull, ExecuteOperation executeOnNotNull) {
        if (object == null) {
            executeOnNull.execute();
        } else {
            executeOnNotNull.execute();
        }
    }
}