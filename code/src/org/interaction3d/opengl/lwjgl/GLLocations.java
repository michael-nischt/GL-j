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
package org.interaction3d.opengl.lwjgl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL20.glIsProgram;


abstract class GLLocations<T extends Annotation>
{
  private final Class<T> type;

  GLLocations(Class<T> type)
  {
    this.type = type;
  }

  protected abstract boolean isRequired(T annotation);

  protected abstract int glGetLocation(int gl_program, String name);

  protected void glBindLocation(int gl_program, String name, int location)
  {
    throw new UnsupportedOperationException();
  }

  private static void fields(Class<?> c, List<Field> fields)
  {
    fields.addAll(Arrays.asList(c.getDeclaredFields()));
//        for(Class<?> impl : c.getInterfaces())
//        {
//            fields(impl, fields);
//        }
    Class<?> sc = c.getSuperclass();
    if (sc != null)
      fields(sc, fields);

  }

  public void locations(int gl_program, Object locations)
  {
    locations(gl_program, null, locations);
  }

  public void locations(int gl_program, String prefix, Object[] locationArray)
  {
    for (int i = 0; i < locationArray.length; i++)
    {
      locations(gl_program, prefix + "[" + i + "]", locationArray[i]);
    }
  }

  public void locations(int gl_program, String prefix, Object locations)
  {
    if (prefix == null)
      prefix = "";
    else
      prefix += '.';

    if (!glIsProgram(gl_program))
    {
      throw new IllegalArgumentException("Requries a valid OpenGL program");
    }

    List<Field> fields = new ArrayList<Field>();
    fields(locations.getClass(), fields);
    for (Field field : fields)
    {
      T annotation = field.getAnnotation(type);
      if (annotation == null)
      {
        continue;
      }

      makeAccessible(field);

      Class<?> fieldType = field.getType();
      if (int.class.equals(fieldType))
      {
        location(annotation, gl_program, prefix, locations, field);
      }
      else if (int[].class.equals(fieldType))
      {
        locationArray(annotation, gl_program, prefix, locations, field);
      }
      else
      {
        throw new RuntimeException("Only int and int[] fields are valid for @" + type.getName() + ".");
      }
    }


  }

  private void location(T annotation, int gl_program, String prefix, Object locations, Field field)
  {
    String name = prefix + field.getName();

    int location = glGetLocation(gl_program, name);
    if (isRequired(annotation) && location < 0)
    {
      throw new RuntimeException(type.getName() + " not found: " + name);
    }
    else if (location >= 0)
    {
      try
      {
        field.setInt(locations, location);
      }
      catch (Exception ex)
      {
        throw new RuntimeException("Failed setting " + type.getName() + ": " + name, ex);
      }
    }
    else
    {
      try
      {
        field.setInt(locations, -1);
      }
      catch (Exception ex)
      {
        throw new RuntimeException("Failed setting " + type.getName() + ": " + name, ex);
      }
    }
  }

  private void locationArray(T annotation, int gl_program, String prefix, Object locations, Field field)
  {
    int length;
    Object locationArray;
    try
    {
      locationArray = field.get(locations);
      length = length = Array.getLength(locationArray);
    }
    catch (Exception ex)
    {
      throw new RuntimeException(ex);
    }

    prefix += field.getName();
    for (int i = 0; i < length; i++)
    {
      locationArrayElement(annotation, gl_program, prefix, i, locationArray);
    }
  }

  private void locationArrayElement(T annotation, int gl_program, String prefix, int index, Object locationArray)
  {
    String name = prefix + "[" + index + "]";
    int location = glGetLocation(gl_program, name);
    if (isRequired(annotation) && location < 0)
    {
      throw new RuntimeException(type.getName() + " not found: " + name);
    }
    else if (location >= 0)
    {
      try
      {
        Array.setInt(locationArray, index, location);
      }
      catch (Exception ex)
      {
        throw new RuntimeException("Failed setting " + type.getName() + ": " + name, ex);
      }
    }
    else
    {
      try
      {
        Array.setInt(locationArray, index, -1);
      }
      catch (Exception ex)
      {
        throw new RuntimeException("Failed setting " + type.getName() + ": " + name, ex);
      }
    }
  }

  public void linkLocations(int gl_program, Object locations)
  {
    linkLocations(gl_program, null, locations);
  }

  public void linkLocations(int gl_program, String prefix, Object[] locationArray)
  {
    for (int i = 0; i < locationArray.length; i++)
    {
      linkLocations(gl_program, prefix + "[" + i + "]", locationArray[i]);
    }
  }

  public void linkLocations(int gl_program, String prefix, Object locations)
  {
    if (prefix == null)
      prefix = "";
    else
      prefix += '.';

    if (!glIsProgram(gl_program))
    {
      throw new IllegalArgumentException("Requries a valid OpenGL program");
    }

    List<Field> fields = new ArrayList<Field>();
    fields(locations.getClass(), fields);
    for (Field field : fields)
    {
      T annotation = field.getAnnotation(type);
      if (annotation == null)
      {
        continue;
      }

      makeAccessible(field);

      Class<?> fieldType = field.getType();
      if (int.class.equals(fieldType))
      {
        linkLocation(annotation, gl_program, prefix, locations, field);
      }
      else if (int[].class.equals(fieldType))
      {
        linkLocationArray(annotation, gl_program, prefix, locations, field);
      }
      else
      {
        throw new RuntimeException("Only int and int[] fields are valid for @" + type.getName() + ".");
      }
    }

    GLPrograms.linkProgram(gl_program);
  }

  private void linkLocation(T annotation, int gl_program, String prefix, Object locations, Field field)
  {
    String name = prefix + field.getName();

    int location = glGetLocation(gl_program, name);
    if (isRequired(annotation) && location < 0)
    {
      throw new RuntimeException(type.getName() + " not found: " + name);
    }
    else if (location >= 0)
    {
      try
      {
        int value = field.getInt(locations);
        if (value < 0)
        {
          throw new IllegalArgumentException("Failed setting " + type.getName() + ": " + name + "(value < 0)");
        }
        glBindLocation(gl_program, name, value);
      }
      catch (Exception ex)
      {
        throw new RuntimeException("Failed setting " + type.getName() + ": " + name, ex);
      }
    }
  }

  private void linkLocationArray(T annotation, int gl_program, String prefix, Object locations, Field field)
  {
    int length;
    Object locationArray;
    try
    {
      locationArray = field.get(locations);
      length = Array.getLength(locationArray);
    }
    catch (Exception ex)
    {
      throw new RuntimeException(ex);
    }

    prefix += field.getName();
    for (int i = 0; i < length; i++)
    {
      linkLocationArrayElement(annotation, gl_program, prefix, i, locationArray);
    }
  }

  private void linkLocationArrayElement(T annotation, int gl_program, String prefix, int index, Object locationArray)
  {
    String name = prefix + "[" + index + "]";
    int location = glGetLocation(gl_program, name);
    if (isRequired(annotation) && location < 0)
    {
      throw new RuntimeException(type.getName() + " not found: " + name);
    }
    else if (location >= 0)
    {
      try
      {
        int value = Array.getInt(locationArray, index);
        if (value < 0)
        {
          throw new IllegalArgumentException("Failed setting " + type.getName() + ": " + name + "(value < 0)");
        }
        glBindLocation(gl_program, name, value);
      }
      catch (Exception ex)
      {
        throw new RuntimeException("Failed setting " + type.getName() + ": " + name, ex);
      }
    }
  }

  private static void makeAccessible(Field field)
  {
    if ((field.getModifiers() & Field.PUBLIC) == 0)
    {
      field.setAccessible(true);
    }
  }
}