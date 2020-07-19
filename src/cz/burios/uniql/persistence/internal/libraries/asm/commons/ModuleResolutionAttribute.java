// ASM: a very small and fast Java bytecode manipulation framework
// Copyright (c) 2000-2011 INRIA, France Telecom
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// 1. Redistributions of source code must retain the above copyright
//    notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
// 3. Neither the name of the copyright holders nor the names of its
//    contributors may be used to endorse or promote products derived from
//    this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
// THE POSSIBILITY OF SUCH DAMAGE.

package cz.burios.uniql.persistence.internal.libraries.asm.commons;

import cz.burios.uniql.persistence.internal.libraries.asm.Attribute;
import cz.burios.uniql.persistence.internal.libraries.asm.ByteVector;
import cz.burios.uniql.persistence.internal.libraries.asm.ClassReader;
import cz.burios.uniql.persistence.internal.libraries.asm.ClassWriter;
import cz.burios.uniql.persistence.internal.libraries.asm.Label;

/**
 * ModuleResolution_attribute. This attribute is specific to the OpenJDK and may change in the
 * future.
 *
 * @author Remi Forax
 */
public final class ModuleResolutionAttribute extends Attribute {
  /**
   * Resolution state of a module meaning that the module is not available from the class-path by
   * default.
   */
  public static final int RESOLUTION_DO_NOT_RESOLVE_BY_DEFAULT = 1;

  /** Resolution state of a module meaning the module is marked as deprecated. */
  public static final int RESOLUTION_WARN_DEPRECATED = 2;

  /**
   * Resolution state of a module meaning the module is marked as deprecated and will be removed in
   * a future release.
   */
  public static final int RESOLUTION_WARN_DEPRECATED_FOR_REMOVAL = 4;

  /**
   * Resolution state of a module meaning the module is not yet standardized, so in incubating mode.
   */
  public static final int RESOLUTION_WARN_INCUBATING = 8;

  public int resolution;

  /**
   * Constructs an attribute with a resolution state value.
   *
   * @param resolution the resolution state among {@link #RESOLUTION_WARN_DEPRECATED}, {@link
   *     #RESOLUTION_WARN_DEPRECATED_FOR_REMOVAL}, and {@link #RESOLUTION_WARN_INCUBATING}.
   */
  public ModuleResolutionAttribute(final int resolution) {
    super("ModuleResolution");
    this.resolution = resolution;
  }

  /**
   * Constructs an empty attribute that can be used as prototype to be passed as argument of the method
   * {@link ClassReader#accept(cz.burios.uniql.persistence.internal.libraries.asm.ClassVisitor, Attribute[], int)}.
   */
  public ModuleResolutionAttribute() {
    this(0);
  }

  @Override
  protected Attribute read(
      ClassReader cr, int off, int len, char[] buf, int codeOff, Label[] labels) {
    int resolution = cr.readUnsignedShort(off);
    return new ModuleResolutionAttribute(resolution);
  }

  @Override
  protected ByteVector write(ClassWriter cw, byte[] code, int len, int maxStack, int maxLocals) {
    ByteVector v = new ByteVector();
    v.putShort(resolution);
    return v;
  }
}