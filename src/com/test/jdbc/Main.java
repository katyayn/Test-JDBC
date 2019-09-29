/**
 * 
 */
package com.test.jdbc;

/**
 * @author katyayn
 *
 */
public class Main {

    public static void main(String args[]) {

        try {
            Test_JDBC test_JDBC = new Test_JDBC();

//            test_JDBC.createBlankTables();
//            test_JDBC.insertNames();
            System.out.println(test_JDBC.getNamesList());

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }
}
