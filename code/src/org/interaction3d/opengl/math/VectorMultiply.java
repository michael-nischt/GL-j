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

import static org.interaction3d.opengl.math.Matrix.*;

final class VectorMultiply extends Vector
{
  protected VectorMultiply(float[] elements)
  {
    super(elements);
  }

  @Override
  public void multiply(Matrix left, Vector right)
  {
    multiplyMatrixVector(left.elements, right.elements);
  }

  private void multiplyMatrixVector(float[] left, float[] right)
  {
    if (elements == right)
    {
      right = elements.clone();
    }

    elements[_1_] = left[_1_1_] * right[0] + left[_1_2_] * right[1] + left[_1_3_] * right[2] + left[_1_4_] * right[3];
    elements[_2_] = left[_2_1_] * right[0] + left[_2_2_] * right[1] + left[_2_3_] * right[2] + left[_2_4_] * right[3];
    elements[_3_] = left[_3_1_] * right[0] + left[_3_2_] * right[1] + left[_3_3_] * right[2] + left[_3_4_] * right[3];
    elements[_4_] = left[_4_1_] * right[0] + left[_4_2_] * right[1] + left[_4_3_] * right[2] + left[_4_4_] * right[3];
  }
}
