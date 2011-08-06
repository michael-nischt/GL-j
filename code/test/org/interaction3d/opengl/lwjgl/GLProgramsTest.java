package org.interaction3d.opengl.lwjgl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Pbuffer;
import org.lwjgl.opengl.PixelFormat;
import static org.lwjgl.opengl.GLContext.getCapabilities;

import org.interaction3d.opengl.Program.Attribute;
import org.interaction3d.opengl.Program.FragData;
import org.interaction3d.opengl.Program.Uniform;
import static org.interaction3d.opengl.lwjgl.GLPrograms.deleteProgramAndShaders;
import static org.interaction3d.opengl.lwjgl.GLProgramLocations.attributes;
import static org.interaction3d.opengl.lwjgl.GLProgramLocations.uniforms;
import static org.interaction3d.opengl.lwjgl.GLProgramLocations.fragData;
import static org.interaction3d.opengl.lwjgl.GLPrograms.fragmentShader;
import static org.interaction3d.opengl.lwjgl.GLPrograms.vertexShader;
import static org.interaction3d.opengl.lwjgl.GLPrograms.program;
import static org.interaction3d.opengl.lwjgl.ShaderSource.resource;

public class GLProgramsTest
{
    private Pbuffer pbuffer;

    @Before
    public void setUp() throws LWJGLException
    {
        pbuffer = new Pbuffer(1, 1, new PixelFormat(), null);
        pbuffer.makeCurrent();
    }

    @After
    public void tearDown()
    {
        pbuffer.destroy();
        pbuffer = null;
    }

    @Test
    public void testUniforms120()
    {
        if(!getCapabilities().OpenGL21)
        {
            System.out.printf("Ignoring Test: '%s' OpenGL < 2.1\n", "testUniforms120");
            return;
        }

        int vertexShader = vertexShader( resource("/120.vert") );
        int fragmentShader = fragmentShader(  resource("/120.frag") );
        int gl_program = program(vertexShader, fragmentShader);

        if(gl_program == 0)
        {
          fail("could not create program");
        }


        {
            Global global = new Global();
            uniforms(gl_program, global);
            global.check();
        }
        {
            Structure single = new Structure();
            uniforms(gl_program,  "single", single);
            single.check();
        }
        {
            Structure[] multiple = new Structure[2];
            multiple[0] = new Structure();
            multiple[1] = new Structure();
            uniforms(gl_program,  "multiple", multiple);
            for(Structure single : multiple)
            {
                single.check();
            }
        }
        {
            Attributes120 attributes = new Attributes120();
            attributes(gl_program, attributes);
            attributes.check();
        }

        deleteProgramAndShaders(gl_program);
    }

    @Test //@Ignore
    public void testUniforms330()
    {
        if(!getCapabilities().OpenGL33)
        {
            System.out.printf("Ignoring Test: '%s' OpenGL < 3.3\n", "testUniforms330");
            return;
        }

        int vertexShader = vertexShader( resource("/330.vert") );
        int fragmentShader = fragmentShader( resource("/330.frag") );
        int gl_program = program(vertexShader, fragmentShader);

        if(gl_program == 0)
        {
          fail("could not create program");
        }

        {
            Global global = new Global();
            uniforms(gl_program, global);
            global.check();
        }
        {
            Structure single = new Structure();
            //uniforms(gl_program,  "Block", single);
            uniforms(gl_program,  "single", single);
            single.check();
        }
        {
            Structure[] multiple = new Structure[2];
            multiple[0] = new Structure();
            multiple[1] = new Structure();
            uniforms(gl_program,  "multiple", multiple);
            for(Structure single : multiple)
            {
                single.check();
            }
        }
        {
            Attributes330 attributes = new Attributes330();
            attributes(gl_program, attributes);
            attributes.check();
        }
        {
            FragData330 fragData = new FragData330();
            fragData(gl_program, fragData);
            fragData.check();
        }

        deleteProgramAndShaders(gl_program);
    }
}

class NotDefinedAttributes
{
    @Attribute int foo;
    @Attribute int[] bar = new int[2];

    void check()
    {
        assertTrue(foo < 0);
        assertTrue(bar[0] < 0);
        assertTrue(bar[1] < 0);
    }
}

class Attributes120 extends NotDefinedAttributes
{
    @Attribute(required=true) int position = -1;
    @Attribute(required=true) int matrix = -1;

    @Override
    void check()
    {
        assertTrue(position >= 0);
        assertTrue(matrix >= 0);

        super.check();
    }
}

class Attributes330 extends NotDefinedAttributes
{
    @Attribute(required=true) int position = -1;
    @Attribute(required=true) int[] matrix = { -1, -1 };

    @Override
    void check()
    {
        assertEquals(0, position);
        assertEquals(1, matrix[0]);
        assertEquals(1+4, matrix[1]);

        super.check();
    }
}



class NotDefinedUniforms
{
    @Uniform int foo;
    @Uniform int[] bar = new int[2];

    void check()
    {
        assertTrue(foo < 0);
        assertTrue(bar[0] < 0);
        assertTrue(bar[1] < 0);
    }
}

class Global extends NotDefinedUniforms
{
    @Uniform(required=true) int projection = -1;
    @Uniform(required=true) int model = -1;
    @Uniform(required=true) int view = -1;

    @Uniform(required=true) int[] pairs = { -1, -1 };

    @Override
    void check()
    {
        assertTrue(projection >= 0);
        assertTrue(view >= 0);
        assertTrue(model >= 0);

        assertTrue(pairs[0] >= 0);
        assertTrue(pairs[1] >= 0);

        super.check();
    }
}

class Structure extends NotDefinedUniforms
{
    @Uniform(required=true) int quad = -1;
    @Uniform(required=true) final int[] pairs = { -1, -1 };


    @Override
    void check()
    {
        assertTrue(quad >= 0);
        assertTrue(pairs[0] >= 0);
        assertTrue(pairs[1] >= 0);

        super.check();
    }
}

class NotDefinedFragData
{
    @FragData int foo;
    @FragData int[] bar = new int[2];

    void check()
    {
        assertTrue(foo < 0);
        assertTrue(bar[0] < 0);
        assertTrue(bar[1] < 0);
    }
}

class FragData330 extends NotDefinedFragData
{
    @FragData(required=true) int fragColor = -1;
    @FragData(required=true) int[] fragData = { -1, -1 };

    @Override
    void check()
    {
        assertEquals(0, fragColor);
        assertEquals(1, fragData[0]);
        assertEquals(2, fragData[1]);

        super.check();
    }
}
