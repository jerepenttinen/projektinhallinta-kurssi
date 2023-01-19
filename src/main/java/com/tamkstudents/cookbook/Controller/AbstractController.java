package com.tamkstudents.cookbook.Controller;

import java.lang.reflect.Type;

public class AbstractController<T> {

    boolean isDefined(Comparable val){
        return !val.equals(null);
    }

}
