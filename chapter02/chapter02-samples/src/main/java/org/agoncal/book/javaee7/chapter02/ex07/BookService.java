/*
 */

package org.agoncal.book.javaee7.chapter02.ex07;

/**
 *
 * @author JS
 */
public interface BookService {

  // ======================================
  // =          Business methods          =
  // ======================================
  Book07 createBook(String title, Float price, String description);
  
}
