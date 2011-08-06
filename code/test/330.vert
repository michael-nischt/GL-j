#version 330

// Transform
uniform mat4 projection = mat4(1.0);
uniform mat4 view = mat4(1.0);
uniform mat4 model = mat4(1.0);

uniform vec2 pairs[2];

struct Structure
{
    vec4 quad;
    vec2 pairs[2];
};
uniform Structure single;
uniform Structure multiple[2];

layout(location=0) in vec4 position;
layout(location=1) in mat4 matrix[2];


out vec4 foo;


void main()
{
   vec4 bar;
   {
        vec2 g_pair = min(pairs[0], pairs[1]);
        vec2 s_pair = min(single.pairs[0], single.pairs[1]);
        bar.xy = min (g_pair, s_pair);
   }
   {
        vec2 m0_pair = min(multiple[0].pairs[0], multiple[0].pairs[1]);
        vec2 m1_pair = min(multiple[1].pairs[0], multiple[1].pairs[1]);
        bar.zw = min(m0_pair, m1_pair);
   }
   vec4 baz = single.quad + max(multiple[0].quad, multiple[1].quad);

   foo = matrix[0] * matrix[1] * bar + baz;
   gl_Position = projection * view * model * position;
}
