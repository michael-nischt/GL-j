/*
 * Copyright (c) 2011 Michael Nischt
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * Neither the name of the project's author nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.interaction3d.opengl.math;

final class MatrixInverse extends Matrix
{
  protected MatrixInverse(float[] elements)
  {
    super(elements);
  }

  @Override
  public boolean invert(Matrix source)
  {
    return invertMatrix(source.elements);
  }

  @Override
  public boolean invert(Matrix source, float epsilon)
  {
    return invertMatrix(source.elements, epsilon);
  }

  private boolean invertMatrix(float[] source)
  {
    return invertMatrix(source, 0);
  }

  private boolean invertMatrix(float[] source, float epsilon)
  {
    if (source == elements)
    {
      source = elements.clone();
    }

    float det = source[_1_1_] * source[_2_2_] * source[_3_3_] * source[_4_4_]
      + source[_1_1_] * source[_2_3_] * source[_3_4_] * source[_4_2_]
      + source[_1_1_] * source[_2_4_] * source[_3_2_] * source[_4_3_]
      + source[_1_2_] * source[_2_1_] * source[_3_4_] * source[_4_3_]
      + source[_1_2_] * source[_2_3_] * source[_3_1_] * source[_4_4_]
      + source[_1_2_] * source[_2_4_] * source[_3_3_] * source[_4_1_]
      + source[_1_3_] * source[_2_1_] * source[_3_2_] * source[_4_4_]
      + source[_1_3_] * source[_2_2_] * source[_3_4_] * source[_4_1_]
      + source[_1_3_] * source[_2_4_] * source[_3_1_] * source[_4_2_]
      + source[_1_4_] * source[_2_1_] * source[_3_3_] * source[_4_2_]
      + source[_1_4_] * source[_2_2_] * source[_3_1_] * source[_4_3_]
      + source[_1_4_] * source[_2_3_] * source[_3_2_] * source[_4_1_]
      - source[_1_1_] * source[_2_2_] * source[_3_4_] * source[_4_3_]
      - source[_1_1_] * source[_2_3_] * source[_3_2_] * source[_4_4_]
      - source[_1_1_] * source[_2_4_] * source[_3_3_] * source[_4_2_]
      - source[_1_2_] * source[_2_1_] * source[_3_3_] * source[_4_4_]
      - source[_1_2_] * source[_2_3_] * source[_3_4_] * source[_4_1_]
      - source[_1_2_] * source[_2_4_] * source[_3_1_] * source[_4_3_]
      - source[_1_3_] * source[_2_1_] * source[_3_4_] * source[_4_2_]
      - source[_1_3_] * source[_2_2_] * source[_3_1_] * source[_4_4_]
      - source[_1_3_] * source[_2_4_] * source[_3_2_] * source[_4_1_]
      - source[_1_4_] * source[_2_1_] * source[_3_2_] * source[_4_3_]
      - source[_1_4_] * source[_2_2_] * source[_3_3_] * source[_4_1_]
      - source[_1_4_] * source[_2_3_] * source[_3_1_] * source[_4_2_];


    if (Math.abs(det) < epsilon)
    {
      return false;
    }

    elements[_1_1_] = (source[_2_2_] * source[_3_3_] * source[_4_4_]
      + source[_2_3_] * source[_3_4_] * source[_4_2_]
      + source[_2_4_] * source[_3_2_] * source[_4_3_]
      - source[_2_2_] * source[_3_4_] * source[_4_3_]
      - source[_2_3_] * source[_3_2_] * source[_4_4_]
      - source[_2_4_] * source[_3_3_] * source[_4_2_]) / det;

    elements[_1_2_] = (source[_1_2_] * source[_3_4_] * source[_4_3_]
      + source[_1_3_] * source[_3_2_] * source[_4_4_]
      + source[_1_4_] * source[_3_3_] * source[_4_2_]
      - source[_1_2_] * source[_3_3_] * source[_4_4_]
      - source[_1_3_] * source[_3_4_] * source[_4_2_]
      - source[_1_4_] * source[_3_2_] * source[_4_3_]) / det;

    elements[_1_3_] = (source[_1_2_] * source[_2_3_] * source[_4_4_]
      + source[_1_3_] * source[_2_4_] * source[_4_2_]
      + source[_1_4_] * source[_2_2_] * source[_4_3_]
      - source[_1_2_] * source[_2_4_] * source[_4_3_]
      - source[_1_3_] * source[_2_2_] * source[_4_4_]
      - source[_1_4_] * source[_2_3_] * source[_4_2_]) / det;

    elements[_1_4_] = (source[_1_2_] * source[_2_4_] * source[_3_3_]
      + source[_1_3_] * source[_2_2_] * source[_3_4_]
      + source[_1_4_] * source[_2_3_] * source[_3_2_]
      - source[_1_2_] * source[_2_3_] * source[_3_4_]
      - source[_1_3_] * source[_2_4_] * source[_3_2_]
      - source[_1_4_] * source[_2_2_] * source[_3_3_]) / det;


    elements[_2_1_] = (source[_2_1_] * source[_3_4_] * source[_4_3_]
      + source[_2_3_] * source[_3_1_] * source[_4_4_]
      + source[_2_4_] * source[_3_3_] * source[_4_1_]
      - source[_2_1_] * source[_3_3_] * source[_4_4_]
      - source[_2_3_] * source[_3_4_] * source[_4_1_]
      - source[_2_4_] * source[_3_1_] * source[_4_3_]) / det;

    elements[_2_2_] = (source[_1_1_] * source[_3_3_] * source[_4_4_]
      + source[_1_3_] * source[_3_4_] * source[_4_1_]
      + source[_1_4_] * source[_3_1_] * source[_4_3_]
      - source[_1_1_] * source[_3_4_] * source[_4_3_]
      - source[_1_3_] * source[_3_1_] * source[_4_4_]
      - source[_1_4_] * source[_3_3_] * source[_4_1_]) / det;

    elements[_2_3_] = (source[_1_1_] * source[_2_4_] * source[_4_3_]
      + source[_1_3_] * source[_2_1_] * source[_4_4_]
      + source[_1_4_] * source[_2_3_] * source[_4_1_]
      - source[_1_1_] * source[_2_3_] * source[_4_4_]
      - source[_1_3_] * source[_2_4_] * source[_4_1_]
      - source[_1_4_] * source[_2_1_] * source[_4_3_]) / det;

    elements[_2_4_] = (source[_1_1_] * source[_2_3_] * source[_3_4_]
      + source[_1_3_] * source[_2_4_] * source[_3_1_]
      + source[_1_4_] * source[_2_1_] * source[_3_3_]
      - source[_1_1_] * source[_2_4_] * source[_3_3_]
      - source[_1_3_] * source[_2_1_] * source[_3_4_]
      - source[_1_4_] * source[_2_3_] * source[_3_1_]) / det;

    elements[_3_1_] = (source[_2_1_] * source[_3_2_] * source[_4_4_]
      + source[_2_2_] * source[_3_4_] * source[_4_1_]
      + source[_2_4_] * source[_3_1_] * source[_4_2_]
      - source[_2_1_] * source[_3_4_] * source[_4_2_]
      - source[_2_2_] * source[_3_1_] * source[_4_4_]
      - source[_2_4_] * source[_3_2_] * source[_4_1_]) / det;


    elements[_3_2_] = (source[_1_1_] * source[_3_4_] * source[_4_2_]
      + source[_1_2_] * source[_3_1_] * source[_4_4_]
      + source[_1_4_] * source[_3_2_] * source[_4_1_]
      - source[_1_1_] * source[_3_2_] * source[_4_4_]
      - source[_1_2_] * source[_3_4_] * source[_4_1_]
      - source[_1_4_] * source[_3_1_] * source[_4_2_]) / det;

    elements[_3_3_] = (source[_1_1_] * source[_2_2_] * source[_4_4_]
      + source[_1_2_] * source[_2_4_] * source[_4_1_]
      + source[_1_4_] * source[_2_1_] * source[_4_2_]
      - source[_1_1_] * source[_2_4_] * source[_4_2_]
      - source[_1_2_] * source[_2_1_] * source[_4_4_]
      - source[_1_4_] * source[_2_2_] * source[_4_1_]) / det;

    elements[_3_4_] = (source[_1_1_] * source[_2_4_] * source[_3_2_]
      + source[_1_2_] * source[_2_1_] * source[_3_4_]
      + source[_1_4_] * source[_2_2_] * source[_3_1_]
      - source[_1_1_] * source[_2_2_] * source[_3_4_]
      - source[_1_2_] * source[_2_4_] * source[_3_1_]
      - source[_1_4_] * source[_2_1_] * source[_3_2_]) / det;

    elements[_4_1_] = (source[_2_1_] * source[_3_3_] * source[_4_2_]
      + source[_2_2_] * source[_3_1_] * source[_4_3_]
      + source[_2_3_] * source[_3_2_] * source[_4_1_]
      - source[_2_1_] * source[_3_2_] * source[_4_3_]
      - source[_2_2_] * source[_3_3_] * source[_4_1_]
      - source[_2_3_] * source[_3_1_] * source[_4_2_]) / det;

    elements[_4_2_] = (source[_1_1_] * source[_3_2_] * source[_4_3_]
      + source[_1_2_] * source[_3_3_] * source[_4_1_]
      + source[_1_3_] * source[_3_1_] * source[_4_2_]
      - source[_1_1_] * source[_3_3_] * source[_4_2_]
      - source[_1_2_] * source[_3_1_] * source[_4_3_]
      - source[_1_3_] * source[_3_2_] * source[_4_1_]) / det;

    elements[_4_3_] = (source[_1_1_] * source[_2_3_] * source[_4_2_]
      + source[_1_2_] * source[_2_1_] * source[_4_3_]
      + source[_1_3_] * source[_2_2_] * source[_4_1_]
      - source[_1_1_] * source[_2_2_] * source[_4_3_]
      - source[_1_2_] * source[_2_3_] * source[_4_1_]
      - source[_1_3_] * source[_2_1_] * source[_4_2_]) / det;

    elements[_4_4_] = (source[_1_1_] * source[_2_2_] * source[_3_3_]
      + source[_1_2_] * source[_2_3_] * source[_3_1_]
      + source[_1_3_] * source[_2_1_] * source[_3_2_]
      - source[_1_1_] * source[_2_3_] * source[_3_2_]
      - source[_1_2_] * source[_2_1_] * source[_3_3_]
      - source[_1_3_] * source[_2_2_] * source[_3_1_]) / det;

    return true;
  }
}
