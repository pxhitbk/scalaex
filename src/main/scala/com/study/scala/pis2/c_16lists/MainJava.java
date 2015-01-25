package com.study.scala.pis2.c_16lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import scala.actors.threadpool.Arrays;

public class MainJava {
	static Integer[] nums = {3, 6, 4, 10, 5, 2, 8}  ;
	public static void main(String[] args) {
		List<Integer> lnums = Arrays.asList((Integer[])nums);
		List<Integer> s = isort(lnums);
		printList(s);
	}
	
	static List<Integer> isort(List<Integer> xs)  {
		if (xs.isEmpty()) {
			System.out.println("<isort> xs: ()");
			return xs;
		}
		else {
			System.out.println("<isort> " + lstToString(xs));
			return insert(xs.get(0), isort(tail(xs)));
		}
	}
		  
	  private static List<Integer> insert(Integer x, List<Integer> xs) {
		  System.out.println("<insert> x:" + x + "/ xs=" + lstToStr(xs));
	    if (xs.isEmpty() || x <= xs.get(0)) {
	    	System.out.println("\tappend x:" + x + "/ xs=" + lstToStr(xs));
	    	return insertHead(x, xs);
    	}
	    else {
	    	System.out.println("\tappend x:" + x + "/ xs.tail=" + lstToStr(tail(xs)));
	    	return insertHead(xs.get(0), insert(x, tail(xs)));
		}
	  }
	  
	  static List<Integer> tail(List<Integer> list) {
		  if (list.size() <= 1)
			  return new ArrayList<Integer>();
		  else 
			  return list.subList(1, list.size());
	  }
	  
	  static void printList(List<Integer> l) {
		  for(Integer i : l)
			  System.out.println(i + ", ");
	  }
	  
	  static String lstToStr(List<Integer> l) {
		  if (l == null)
			  return "NULL";
		  else if (l.isEmpty())
			  return "()";
		  StringBuilder s = new StringBuilder("(");
		  
		  for(Integer i : l)
			  s.append(i).append(", ");
		  int idx = s.toString().lastIndexOf(", ");
		  s.replace(idx, idx+1, "");
		  s.append(")");
		  return s.toString();
	  }
	  
	  static List<Integer> insertHead(Integer h, List<Integer> l) {
//		  System.out.println("<insertHead> h:" + h + " to list " + lstToStr(l) );
		  l.add(0, h);
		  return l;
	  }
	  
	  static <E> String lstToString(List<E> lst) {
	        Iterator<E> it = lst.iterator();
	        if (! it.hasNext())
	            return "[]";

	        StringBuilder sb = new StringBuilder();
	        sb.append('[');
	        for (;;) {
	            E e = it.next();
	            sb.append(e == lst ? "(this Collection)" : e);
	            if (! it.hasNext())
	                return sb.append(']').toString();
	            sb.append(',').append(' ');
	        }
	    }

}
