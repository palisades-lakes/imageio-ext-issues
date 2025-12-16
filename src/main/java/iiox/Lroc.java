package iiox;

/** Demonstrate misreading of NASA TIFF image.
 * <pre>>
 *  j --source 25 -ea src/main/java/iiox/Lroc.java
 * </pre>
 *
 * @see "https://svs.gsfc.nasa.gov/vis/a000000/a004700/a004720/lroc_color_poles_2k.tif"
 *
 * @author palisades dot lakes at gmail dot com
 * @version "2025-12-16
 */
public final class Lroc {

  //--------------------------------------------------------------------

  @SuppressWarnings("unused")
  public static final void main (final String[] args) {
    Images.readWriteRead("images/lroc/lroc_color_poles_2k.tif");
    //Images.readWriteRead("images/lroc/lroc_color_poles_2k-iio.tif");
  }

  //--------------------------------------------------------------------
  // disabled constructor
  //--------------------------------------------------------------------
  private Lroc () {
    throw new UnsupportedOperationException(
      "Can't instantiate " + getClass()); }
  //--------------------------------------------------------------------
} // end class
//--------------------------------------------------------------------

