package com.freetest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by md101 on 5/17/15.
 */
public class HigherKind {
    public static void main(String[] args) {

    }

    public void test1() {
        JList<String> JList = new JList<String>();
        JIterable<String> sublist = JList.filter(new MyPredicate<String>());
        // if (5 == sublist.size()) <= not work

        /*
        If one implements a comprehensive tree of subclasses on a class
        that contains several similar methods like the method filter in the example,
        there can occur quite a lot of refined methods.
        And all this to return a collection of the type of the class at hand instead of the super type’s class.
        With the current features of Java, the refinement of such method as filter cannot be avoided.
        As a matter of fact, those refinements are code duplication, which of course is to avoid [3].

        The thing we would like to achieve is to define the method filter in the class Iterable
        so that it returns a collection of the static type of the object that calls the method.
         */

        JList1<String> list1 = new JList1<String>();
        JIterable1<String> sublist2 = list1.filter(new MyPredicate<String>());
        if (5 == list1.size()) {}

        JList2<String> list2 = new JList2();
        JList2<String> jl2 = list2.filter(new MyPredicate<>());
        jl2.size();

        Map m = new HashMap<String, List<String>>();
    }

    class MyPredicate<T> implements IPredicate<T>{
        @Override
        public boolean doesMatch(T t) {
            return false;
        }
    }
}

abstract class JIterable<T> {
    public abstract JIterable<T> filter ( IPredicate<T> p ) ;
}

abstract class JIterable1<T> {
    public abstract JIterable1<T> filter ( IPredicate<T> p ) ;
}

abstract class JIterable2<T, M>{
    public M filter( IPredicate<T> p ) {
        M m = null;
        return m;
    }
}

interface Function<T, E> {}
interface Monad<A> {
    <B> Monad<B> flatMap(Function<? super A, ? extends Monad<? extends B>> f);
}

class JList<T> extends JIterable<T> {

    @Override
    public JIterable<T> filter(IPredicate<T> p) {
        return null;
    }

    public int size() { return 0;}
}

class JList1<T> extends JIterable1<T> {

    @Override
    public JList1<T> filter(IPredicate<T> p) {
        return null;
    }

    public int size() { return 0;}
}

class JList2<T> extends JIterable2<T, JList2<T>> {
    public int size() { return 0;}
}

interface IPredicate<T> {
    boolean doesMatch(T t);
}