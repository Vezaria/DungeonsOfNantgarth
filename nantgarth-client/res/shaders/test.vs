#version 330

layout (location = 0) in vec2 in_pos;
layout (location = 1) in vec2 in_tex;

uniform mat4 projection;
uniform mat4 view = mat4(1.0);
uniform mat4 model = mat4(1.0);

out vec2 v_tex;

void main()
{
	gl_Position = projection * view * model * vec4(in_pos, 0.0, 1.0);
	v_tex = in_tex;
}