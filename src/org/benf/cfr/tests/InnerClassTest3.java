package org.benf.cfr.tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lee
 * Date: 04/09/2012
 * Time: 07:09
 */
public class InnerClassTest3 {

    private final int x;

    public InnerClassTest3(int x) {
        this.x = x;
    }

    public int getX() {
        return new Inner1(new ArrayList<String>()).getX(4);
    }

    public class Inner1<E> {
        private final List<E> arg;

        public Inner1(List<E> arg) {
            this.arg = arg;
        }

        public int getX(int y) {
            return 2 + y + InnerClassTest3.this.x;
        }

    }
}