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

public class Vector implements Cloneable
{
  public static final int _1_ = 0, _2_ = 1, _3_ = 2, _4_ = 3;
  public final float[] elements;

  protected Vector(float[] elements)
  {
    if (elements.length != 4)
    {
      throw new IllegalArgumentException();
    }

    this.elements = elements;
  }

  // <editor-fold defaultstate="collapsed" desc="wrap & copy">
  static public Vector wrap(float[] elements)
  {
    return new Vector(elements);
  }

  static public Vector copy(float[] elements)
  {
    return new Vector(elements.clone());
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="xyzw">
  public float x()
  {
    return elements[_1_];
  }

  public void x(float value)
  {
    elements[_1_] = value;
  }

  public float y()
  {
    return elements[_2_];
  }

  public void y(float value)
  {
    elements[_2_] = value;
  }

  public float z()
  {
    return elements[_3_];
  }

  public void z(float value)
  {
    elements[_3_] = value;
  }

  public float w()
  {
    return elements[_4_];
  }

  public void w(float value)
  {
    elements[_4_] = value;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="equals, hashcode & clone">
  public boolean equals(Vector other)
  {
    return this.elements == other.elements;
  }

  @Override
  public boolean equals(Object other)
  {
    if (other instanceof Vector)
    {
      return ((Vector) other).elements == this.elements;
    }
    return false;
  }

  @Override
  public int hashCode()
  {
    return elements.hashCode();
  }

  @Override
  public Vector clone()
  {
    return new Vector(elements.clone());
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="normalize">
  public void normalize()
  {
    new VectorNormalization(elements).normalize();
  }

  public void normalize(Vector source)
  {
    new VectorNormalization(elements).normalize(source);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="hnormalize">
  public void hnormalize()
  {
    new HomogenousNormalization(elements).hnormalize();
  }

  public void hnormalize(Vector source)
  {
    new HomogenousNormalization(elements).hnormalize(source);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="multiply">
  public void multiply(Matrix left, Vector right)
  {
    new VectorMultiply(elements).multiply(left, right);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="project & unproject">
  public void project(Matrix modelViewProjection, Vector coordinate, int x, int y, int width, int height)
  {
    new Projection(elements).project(modelViewProjection, coordinate, x, y, width, height);
  }

  public void project(Matrix modelViewProjection, Vector coordinate, int x, int y, int width, int height, float near, float far)
  {
    new Projection(elements).project(modelViewProjection, coordinate, x, y, width, height, near, far);
  }

  public void unproject(Matrix invModelViewProjection, Vector coordinate, int x, int y, int width, int height)
  {
    new Projection(elements).unproject(invModelViewProjection, coordinate, x, y, width, height);
  }

  public void unproject(Matrix invModelViewProjection, Vector coordinate, int x, int y, int width, int height, float near, float far)
  {
    new Projection(elements).unproject(invModelViewProjection, coordinate, x, y, width, height, near, far);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="min-max">
  public void min(Vector right)
  {
    new VectorMin(elements).min(right);
  }

  public void min(Vector left, Vector right)
  {
    new VectorMin(elements).min(left, right);
  }

  public void max(Vector right)
  {
    new VectorMax(elements).max(right);
  }

  public void max(Vector left, Vector right)
  {
    new VectorMax(elements).max(left, right);
  }
  // </editor-fold>


  // <editor-fold defaultstate="collapsed" desc="dot">
  public float dot(Vector right)
  {
    return VectorDot.dot(this, right);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="cross product">
  public void cross(Vector right)
  {
    new VectorCross(elements).cross(right);
  }

  public void cross(Vector left, Vector right)
  {
    new VectorCross(elements).cross(left, right);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="zero">
  public static Vector zero()
  {
    float[] elements =
    {
      0, 0, 0, 0,
    };

    return new Vector(elements);
  }

  public void loadZero()
  {
    elements[_1_] = elements[_2_] = elements[_3_] = elements[_4_] = 0;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="coordinate">
  static public Vector coordinate()
  {
    float[] elements =
    {
      0, 0, 0, 1,
    };

    return new Vector(elements);
  }

  static public Vector coordinate(float x)
  {
    float[] elements =
    {
      x, 0, 0, 1,
    };

    return new Vector(elements);
  }

  static public Vector coordinate(float x, float y)
  {
    float[] elements =
    {
      x, y, 0, 1,
    };

    return new Vector(elements);
  }

  static public Vector coordinate(float x, float y, float z)
  {
    float[] elements =
    {
      x, y, z, 1,
    };

    return new Vector(elements);
  }

  static public Vector coordinate(float x, float y, float z, float w)
  {
    float[] elements =
    {
      x, y, z, w,
    };

    return new Vector(elements);
  }

  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="direction">
  static public Vector direction(float x, float y, float z)
  {
    float[] elements =
    {
      x, y, z, 0,
    };

    return new Vector(elements);
  }
  // </editor-fold>
}
