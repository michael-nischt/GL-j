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

final class MatrixMultiply extends Matrix
{
  protected MatrixMultiply(float[] elements)
  {
    super(elements);
  }

  @Override
  public void multiply(Matrix right)
  {
    multiplyMatrix(right.elements);
  }

  @Override
  public void multiply(Matrix left, Matrix right)
  {
    multiplyMatrices(left.elements, right.elements);
  }

  private void multiplyMatrix(float[] right)
  {
    if (elements == right)
    {
      right = elements.clone();
    }

    float c1, c2, c3, c4;

    // 1st row
    c1 = elements[_1_1_];
    c2 = elements[_1_2_];
    c3 = elements[_1_3_];
    c4 = elements[_1_4_];
    elements[_1_1_] = c1 * right[_1_1_] + c2 * right[_2_1_] + c3 * right[_3_1_] + c4 * right[_4_1_];
    elements[_1_2_] = c1 * right[_1_2_] + c2 * right[_2_2_] + c3 * right[_3_2_] + c4 * right[_4_2_];
    elements[_1_3_] = c1 * right[_1_3_] + c2 * right[_2_3_] + c3 * right[_3_3_] + c4 * right[_4_3_];
    elements[_1_4_] = c1 * right[_1_4_] + c2 * right[_2_4_] + c3 * right[_3_4_] + c4 * right[_4_4_];

    // 2nd row
    c1 = elements[_2_1_];
    c2 = elements[_2_2_];
    c3 = elements[_2_3_];
    c4 = elements[_2_4_];
    elements[_2_1_] = c1 * right[_1_1_] + c2 * right[_2_1_] + c3 * right[_3_1_] + c4 * right[_4_1_];
    elements[_2_2_] = c1 * right[_1_2_] + c2 * right[_2_2_] + c3 * right[_3_2_] + c4 * right[_4_2_];
    elements[_2_3_] = c1 * right[_1_3_] + c2 * right[_2_3_] + c3 * right[_3_3_] + c4 * right[_4_3_];
    elements[_2_4_] = c1 * right[_1_4_] + c2 * right[_2_4_] + c3 * right[_3_4_] + c4 * right[_4_4_];

    // 3rd row
    c1 = elements[_3_1_];
    c2 = elements[_3_2_];
    c3 = elements[_3_3_];
    c4 = elements[_3_4_];
    elements[_3_1_] = c1 * right[_1_1_] + c2 * right[_2_1_] + c3 * right[_3_1_] + c4 * right[_4_1_];
    elements[_3_2_] = c1 * right[_1_2_] + c2 * right[_2_2_] + c3 * right[_3_2_] + c4 * right[_4_2_];
    elements[_3_3_] = c1 * right[_1_3_] + c2 * right[_2_3_] + c3 * right[_3_3_] + c4 * right[_4_3_];
    elements[_3_4_] = c1 * right[_1_4_] + c2 * right[_2_4_] + c3 * right[_3_4_] + c4 * right[_4_4_];

    // 4th row
    c1 = elements[_4_1_];
    c2 = elements[_4_2_];
    c3 = elements[_4_3_];
    c4 = elements[_4_4_];
    elements[_4_1_] = c1 * right[_1_1_] + c2 * right[_2_1_] + c3 * right[_3_1_] + c4 * right[_4_1_];
    elements[_4_2_] = c1 * right[_1_2_] + c2 * right[_2_2_] + c3 * right[_3_2_] + c4 * right[_4_2_];
    elements[_4_3_] = c1 * right[_1_3_] + c2 * right[_2_3_] + c3 * right[_3_3_] + c4 * right[_4_3_];
    elements[_4_4_] = c1 * right[_1_4_] + c2 * right[_2_4_] + c3 * right[_3_4_] + c4 * right[_4_4_];
  }

  private void multiplyMatrices(float[] left, float[] right)
  {
    if (elements == left)
    {
      left = elements.clone();
    }
    if (elements == right)
    {
      right = elements.clone();
    }

    // 1st row
    elements[_1_1_] = left[_1_1_] * right[_1_1_] + left[_1_2_] * right[_2_1_] + left[_1_3_] * right[_3_1_] + left[_1_4_] * right[_4_1_];
    elements[_1_2_] = left[_1_1_] * right[_1_2_] + left[_1_2_] * right[_2_2_] + left[_1_3_] * right[_3_2_] + left[_1_4_] * right[_4_2_];
    elements[_1_3_] = left[_1_1_] * right[_1_3_] + left[_1_2_] * right[_2_3_] + left[_1_3_] * right[_3_3_] + left[_1_4_] * right[_4_3_];
    elements[_1_4_] = left[_1_1_] * right[_1_4_] + left[_1_2_] * right[_2_4_] + left[_1_3_] * right[_3_4_] + left[_1_4_] * right[_4_4_];

    // 2nd row
    elements[_2_1_] = left[_2_1_] * right[_1_1_] + left[_2_2_] * right[_2_1_] + left[_2_3_] * right[_3_1_] + left[_2_4_] * right[_4_1_];
    elements[_2_2_] = left[_2_1_] * right[_1_2_] + left[_2_2_] * right[_2_2_] + left[_2_3_] * right[_3_2_] + left[_2_4_] * right[_4_2_];
    elements[_2_3_] = left[_2_1_] * right[_1_3_] + left[_2_2_] * right[_2_3_] + left[_2_3_] * right[_3_3_] + left[_2_4_] * right[_4_3_];
    elements[_2_4_] = left[_2_1_] * right[_1_4_] + left[_2_2_] * right[_2_4_] + left[_2_3_] * right[_3_4_] + left[_2_4_] * right[_4_4_];

    // 3rd row
    elements[_3_1_] = left[_3_1_] * right[_1_1_] + left[_3_2_] * right[_2_1_] + left[_3_3_] * right[_3_1_] + left[_3_4_] * right[_4_1_];
    elements[_3_2_] = left[_3_1_] * right[_1_2_] + left[_3_2_] * right[_2_2_] + left[_3_3_] * right[_3_2_] + left[_3_4_] * right[_4_2_];
    elements[_3_3_] = left[_3_1_] * right[_1_3_] + left[_3_2_] * right[_2_3_] + left[_3_3_] * right[_3_3_] + left[_3_4_] * right[_4_3_];
    elements[_3_4_] = left[_3_1_] * right[_1_4_] + left[_3_2_] * right[_2_4_] + left[_3_3_] * right[_3_4_] + left[_3_4_] * right[_4_4_];

    // 4th row
    elements[_4_1_] = left[_4_1_] * right[_1_1_] + left[_4_2_] * right[_2_1_] + left[_4_3_] * right[_3_1_] + left[_4_4_] * right[_4_1_];
    elements[_4_2_] = left[_4_1_] * right[_1_2_] + left[_4_2_] * right[_2_2_] + left[_4_3_] * right[_3_2_] + left[_4_4_] * right[_4_2_];
    elements[_4_3_] = left[_4_1_] * right[_1_3_] + left[_4_2_] * right[_2_3_] + left[_4_3_] * right[_3_3_] + left[_4_4_] * right[_4_3_];
    elements[_4_4_] = left[_4_1_] * right[_1_4_] + left[_4_2_] * right[_2_4_] + left[_4_3_] * right[_3_4_] + left[_4_4_] * right[_4_4_];


//        // 1st column
//        elements[_1_1_] = left[_1_1_]*right[_1_1_] + left[_1_2_]*right[_2_1_] + left[_1_3_]*right[_3_1_] + left[_1_4_]*right[_4_1_];
//        elements[_2_1_] = left[_2_1_]*right[_1_1_] + left[_2_2_]*right[_2_1_] + left[_2_3_]*right[_3_1_] + left[_2_4_]*right[_4_1_];
//        elements[_3_1_] = left[_3_1_]*right[_1_1_] + left[_3_2_]*right[_2_1_] + left[_3_3_]*right[_3_1_] + left[_3_4_]*right[_4_1_];
//        elements[_4_1_] = left[_4_1_]*right[_1_1_] + left[_4_2_]*right[_2_1_] + left[_4_3_]*right[_3_1_] + left[_4_4_]*right[_4_1_];
//
//        // 2nd column
//        elements[_1_2_] = left[_1_1_]*right[_1_2_] + left[_1_2_]*right[_2_2_] + left[_1_3_]*right[_3_2_] + left[_1_4_]*right[_4_2_];
//        elements[_2_2_] = left[_2_1_]*right[_1_2_] + left[_2_2_]*right[_2_2_] + left[_2_3_]*right[_3_2_] + left[_2_4_]*right[_4_2_];
//        elements[_3_2_] = left[_3_1_]*right[_1_2_] + left[_3_2_]*right[_2_2_] + left[_3_3_]*right[_3_2_] + left[_3_4_]*right[_4_2_];
//        elements[_4_2_] = left[_4_1_]*right[_1_2_] + left[_4_2_]*right[_2_2_] + left[_4_3_]*right[_3_2_] + left[_4_4_]*right[_4_2_];
//
//        // 3rd column
//        elements[_1_3_] = left[_1_1_]*right[_1_3_] + left[_1_2_]*right[_2_3_] + left[_1_3_]*right[_3_3_] + left[_1_4_]*right[_4_3_];
//        elements[_2_3_] = left[_2_1_]*right[_1_3_] + left[_2_2_]*right[_2_3_] + left[_2_3_]*right[_3_3_] + left[_2_4_]*right[_4_3_];
//        elements[_3_3_] = left[_3_1_]*right[_1_3_] + left[_3_2_]*right[_2_3_] + left[_3_3_]*right[_3_3_] + left[_3_4_]*right[_4_3_];
//        elements[_4_3_] = left[_4_1_]*right[_1_3_] + left[_4_2_]*right[_2_3_] + left[_4_3_]*right[_3_3_] + left[_4_4_]*right[_4_3_];
//
//        // 4th column
//        elements[_1_4_] = left[_1_1_]*right[_1_4_] + left[_1_2_]*right[_2_4_] + left[_1_3_]*right[_3_4_] + left[_1_4_]*right[_4_4_];
//        elements[_2_4_] = left[_2_1_]*right[_1_4_] + left[_2_2_]*right[_2_4_] + left[_2_3_]*right[_3_4_] + left[_2_4_]*right[_4_4_];
//        elements[_3_4_] = left[_3_1_]*right[_1_4_] + left[_3_2_]*right[_2_4_] + left[_3_3_]*right[_3_4_] + left[_3_4_]*right[_4_4_];
//        elements[_4_4_] = left[_4_1_]*right[_1_4_] + left[_4_2_]*right[_2_4_] + left[_4_3_]*right[_3_4_] + left[_4_4_]*right[_4_4_];
  }
}
