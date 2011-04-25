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

import static org.interaction3d.opengl.math.Check.checkDepthRange;
import static org.interaction3d.opengl.math.Check.checkViewport;

final class Projection extends Vector
{
  protected Projection(float[] elements)
  {
    super(elements);
  }

  @Override
  public void project(Matrix modelViewProjection, Vector coordinate, int x, int y, int width, int height)
  {
    project(modelViewProjection, coordinate, x, y, width, height, 0, 1);
  }

  @Override
  public void project(Matrix modelViewProjection, Vector coordinate, int x, int y, int width, int height, float near, float far)
  {
    checkViewport(x, y, width, height);
    checkDepthRange(near, far);

    multiply(modelViewProjection, coordinate);
    hnormalize();

    //Perspective division
    elements[_1_] = x + width * (elements[_1_] + 1) / 2;
    elements[_2_] = y + height * (elements[_2_] + 1) / 2;
    elements[_3_] = (elements[_3_] + 1) / 2; // z-range is [0,1]
    if (elements[_3_] < near)
    {
      elements[_3_] = near;
    }
    else if (elements[_3_] > far)
    {
      elements[_3_] = far;
    }
    //elements[_4_] = (elements[_4_])*1;
  }

  @Override
  public void unproject(Matrix invModelViewProjection, Vector coordinate, int x, int y, int width, int height)
  {
    unproject(invModelViewProjection, coordinate, x, y, width, height, 0, 1);
  }

  @Override
  public void unproject(Matrix invModelViewProjection, Vector coordinate, int x, int y, int width, int height, float near, float far)
  {
    checkViewport(x, y, width, height);
    checkDepthRange(near, far);

    // use storage: not clean code but no 'new' allocation
    float c1 = coordinate.elements[_1_];
    float c2 = coordinate.elements[_2_];
    float c3 = coordinate.elements[_3_];
    float c4 = coordinate.elements[_4_];

    // h-normalize
    if (c4 != 0)
    {
      c1 /= c4;
      c2 /= c4;
      c3 /= c4;
      c4 = 1;
    }
    //clamp depth range
    if (c3 < near)
    {
      c3 = near;
    }
    else if (c3 > far)
    {
      c3 = far;
    }

    try
    {
      coordinate.elements[_1_] = 2 * (c1 - x) / width - 1;
      coordinate.elements[_2_] = 2 * (c2 - y) / height - 1;
      coordinate.elements[_3_] = 2 * (c3 - near) / far - 1;
      coordinate.elements[_4_] = c4;

      multiply(invModelViewProjection, coordinate);
    }
    finally
    {
      coordinate.elements[_1_] = c1;
      coordinate.elements[_2_] = c2;
      coordinate.elements[_3_] = c3;
      coordinate.elements[_4_] = c4;
    }
    hnormalize();
  }
}
