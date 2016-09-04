package com.codingblocks.customnavigationdrawer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by HIman$hu on 9/4/2016.
 */
public class CourseDescription implements Serializable {
   ArrayList<CourseDetail> obj;

   public CourseDescription(){
      obj=new ArrayList<>();
   }
}
