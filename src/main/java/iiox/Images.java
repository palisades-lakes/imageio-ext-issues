package iiox;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.util.Arrays;

/** Utilities related to assessing image IO.
 *
 * @author palisades dot lakes at gmail dot com
 * @version "2025-12-16
 */
public final class Images {

  //-------------------------------------------------------------------
  // predicates and debugging
  //-------------------------------------------------------------------

  private static final boolean equals (final DataBufferByte a,
                                       final DataBufferByte b) {
    return (a == b) || Arrays.equals(a.getData(), b.getData()); }

  private static final boolean equals (final DataBufferShort a,
                                       final DataBufferShort b) {
    return (a == b) || Arrays.equals(a.getData(), b.getData()); }

  @SuppressWarnings("unused")
  private static final boolean equals (final DataBufferUShort a,
                                       final DataBufferUShort b) {
    return (a == b) || Arrays.equals(a.getData(), b.getData()); }

  private static final boolean equals (final DataBufferInt a,
                                       final DataBufferInt b) {
    return (a == b) || Arrays.equals(a.getData(), b.getData()); }

  private static final boolean equals (final DataBufferFloat a,
                                       final DataBufferFloat b) {
    return (a == b) || Arrays.equals(a.getData(), b.getData()); }

  private static final boolean equals (final DataBufferDouble a,
                                       final DataBufferDouble b) {
    return (a == b) || Arrays.equals(a.getData(), b.getData()); }

  private static final boolean equals (final DataBuffer a,
                                       final DataBuffer b) {
    if (a == b) { return true; }
    if ((a instanceof DataBufferByte) && (b instanceof DataBufferByte)) {
      return equals((DataBufferByte) a, (DataBufferByte) b); }
    if ((a instanceof DataBufferShort) && (b instanceof DataBufferShort)) {
      return equals((DataBufferShort) a, (DataBufferShort) b); }
    if ((a instanceof DataBufferInt) && (b instanceof DataBufferInt)) {
      return equals((DataBufferInt) a, (DataBufferInt) b); }
    if ((a instanceof DataBufferFloat) && (b instanceof DataBufferFloat)) {
      return equals((DataBufferFloat) a, (DataBufferFloat) b); }
    if ((a instanceof DataBufferDouble) && (b instanceof DataBufferDouble)) {
      return equals((DataBufferDouble) a, (DataBufferDouble) b); }

    return false; }

  private static final boolean equals (final Raster a,
                                       final Raster b) {
    // TODO: check SampleModel
    return (a == b) ||
      ((a.getWidth() == b.getWidth()) &&
        (a.getHeight() == b.getHeight()) &&
        (a.getNumBands() == b.getNumBands()) &&
        (a.getTransferType() == b.getTransferType()) &&
        equals(a.getDataBuffer(), b.getDataBuffer())); }

  private static final boolean equals (final RenderedImage a,
                                       final RenderedImage b) {
    // TODO: check ColorModel
    return (a == b) ||
      ((a.getWidth() == b.getWidth()) &&
        (a.getHeight() == b.getHeight()) &&
        equals(a.getData(), b.getData())); }

  //--------------------------------------------------------------------

  public static final String prefix (final File f) {
    final String name = f.getName();
    return name.substring(0,name.lastIndexOf('.')); }

  public static final String extension (final File f) {
    final String name = f.getName();
    return name.substring(name.lastIndexOf('.') + 1); }

  //--------------------------------------------------------------------
  // TODO: deal with modules and class loaders properly...

  public static final boolean classExists (final String name) {
    try {
      // TODO: better way to check for imageio-ext in dependencies
      System.out.println(Class.forName(name));
      return true; }
    catch (final ClassNotFoundException e) { return false;} }

  //--------------------------------------------------------------------

  public static final void readWriteRead (final String path) {

    final File in = new File(path);
    try {
      final String ext = extension(in);
      final String suffix =
        classExists("it.geosolutions.imageioimpl.plugins.tiff.TIFFImageReader")
        ? "-iiox"
        : "-iio";

      final File out = new File(in.getParentFile(),
                                prefix(in) + suffix + "." + ext );
      final BufferedImage imageIn = ImageIO.read(in);
      ImageIO.write(imageIn, ext, out);
      final BufferedImage imageOut = ImageIO.read(out);
      assert equals(imageIn, imageOut); }
    catch (final Throwable t) {
      throw new RuntimeException(in.toString(),t); } }

  //--------------------------------------------------------------------
  // disabled constructor
  //--------------------------------------------------------------------
  private Images () {
    throw new UnsupportedOperationException(
      "Can't instantiate " + getClass()); }
  //--------------------------------------------------------------------
} // end class
//--------------------------------------------------------------------

